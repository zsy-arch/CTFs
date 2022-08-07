package com.lidroid.xutils.task;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes2.dex */
public class PriorityObjectBlockingQueue<E> extends AbstractQueue<E> implements BlockingQueue<E>, Serializable {
    private static final long serialVersionUID = -6903933977591709194L;
    private final int capacity;
    private final AtomicInteger count;
    transient Node<E> head;
    private transient Node<E> last;
    private final Condition notEmpty;
    private final Condition notFull;
    private final ReentrantLock putLock;
    private final ReentrantLock takeLock;

    private void signalNotEmpty() {
        ReentrantLock takeLock = this.takeLock;
        takeLock.lock();
        try {
            this.notEmpty.signal();
        } finally {
            takeLock.unlock();
        }
    }

    private void signalNotFull() {
        ReentrantLock putLock = this.putLock;
        putLock.lock();
        try {
            this.notFull.signal();
        } finally {
            putLock.unlock();
        }
    }

    private synchronized E opQueue(Node<E> node) {
        E e;
        if (node == null) {
            e = _dequeue();
        } else {
            _enqueue(node);
            e = null;
        }
        return e;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void _enqueue(Node<E> node) {
        boolean added = false;
        Node node2 = this.head;
        while (true) {
            if (node2.next == null) {
                break;
            }
            Node<T> node3 = node2.next;
            if (node3.getPriority().ordinal() > node.getPriority().ordinal()) {
                node2.next = node;
                node.next = node3;
                added = true;
                break;
            }
            node2 = node2.next;
        }
        if (!added) {
            this.last.next = node;
            this.last = node;
        }
    }

    private E _dequeue() {
        Node node = (Node<E>) this.head;
        Node<E> first = (Node<E>) node.next;
        node.next = node;
        this.head = first;
        E x = first.getValue();
        first.setValue(null);
        return x;
    }

    void fullyLock() {
        this.putLock.lock();
        this.takeLock.lock();
    }

    void fullyUnlock() {
        this.takeLock.unlock();
        this.putLock.unlock();
    }

    public PriorityObjectBlockingQueue() {
        this(Integer.MAX_VALUE);
    }

    public PriorityObjectBlockingQueue(int capacity) {
        this.count = new AtomicInteger();
        this.takeLock = new ReentrantLock();
        this.notEmpty = this.takeLock.newCondition();
        this.putLock = new ReentrantLock();
        this.notFull = this.putLock.newCondition();
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        Node<E> node = new Node<>(null);
        this.head = node;
        this.last = node;
    }

    public PriorityObjectBlockingQueue(Collection<? extends E> c) {
        this(Integer.MAX_VALUE);
        ReentrantLock putLock = this.putLock;
        putLock.lock();
        int n = 0;
        try {
            for (Object obj : c) {
                if (obj == null) {
                    throw new NullPointerException();
                } else if (n == this.capacity) {
                    throw new IllegalStateException("Queue full");
                } else {
                    opQueue(new Node<>(obj));
                    n++;
                }
            }
            this.count.set(n);
        } finally {
            putLock.unlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        return this.count.get();
    }

    @Override // java.util.concurrent.BlockingQueue
    public int remainingCapacity() {
        return this.capacity - this.count.get();
    }

    @Override // java.util.concurrent.BlockingQueue
    public void put(E e) throws InterruptedException {
        if (e == null) {
            throw new NullPointerException();
        }
        Node<E> node = new Node<>(e);
        ReentrantLock putLock = this.putLock;
        AtomicInteger count = this.count;
        putLock.lockInterruptibly();
        while (count.get() == this.capacity) {
            try {
                this.notFull.await();
            } finally {
                putLock.unlock();
            }
        }
        opQueue(node);
        int c = count.getAndIncrement();
        if (c + 1 < this.capacity) {
            this.notFull.signal();
        }
        if (c == 0) {
            signalNotEmpty();
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        if (e == null) {
            throw new NullPointerException();
        }
        long nanos = unit.toNanos(timeout);
        ReentrantLock putLock = this.putLock;
        AtomicInteger count = this.count;
        putLock.lockInterruptibly();
        while (count.get() == this.capacity) {
            try {
                if (nanos <= 0) {
                    return false;
                }
                nanos = this.notFull.awaitNanos(nanos);
            } finally {
                putLock.unlock();
            }
        }
        opQueue(new Node<>(e));
        int c = count.getAndIncrement();
        if (c + 1 < this.capacity) {
            this.notFull.signal();
        }
        if (c == 0) {
            signalNotEmpty();
        }
        return true;
    }

    @Override // java.util.Queue, java.util.concurrent.BlockingQueue
    public boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        AtomicInteger count = this.count;
        if (count.get() == this.capacity) {
            return false;
        }
        int c = -1;
        Node<E> node = new Node<>(e);
        ReentrantLock putLock = this.putLock;
        putLock.lock();
        try {
            if (count.get() < this.capacity) {
                opQueue(node);
                c = count.getAndIncrement();
                if (c + 1 < this.capacity) {
                    this.notFull.signal();
                }
            }
            if (c == 0) {
                signalNotEmpty();
            }
            return c >= 0;
        } finally {
            putLock.unlock();
        }
    }

    /* JADX WARN: Finally extract failed */
    @Override // java.util.concurrent.BlockingQueue
    public E take() throws InterruptedException {
        AtomicInteger count = this.count;
        ReentrantLock takeLock = this.takeLock;
        takeLock.lockInterruptibly();
        while (count.get() == 0) {
            try {
                this.notEmpty.await();
            } catch (Throwable th) {
                takeLock.unlock();
                throw th;
            }
        }
        E x = opQueue(null);
        int c = count.getAndDecrement();
        if (c > 1) {
            this.notEmpty.signal();
        }
        takeLock.unlock();
        if (c == this.capacity) {
            signalNotFull();
        }
        return x;
    }

    @Override // java.util.concurrent.BlockingQueue
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        long nanos = unit.toNanos(timeout);
        AtomicInteger count = this.count;
        ReentrantLock takeLock = this.takeLock;
        takeLock.lockInterruptibly();
        while (count.get() == 0) {
            try {
                if (nanos <= 0) {
                    return null;
                }
                nanos = this.notEmpty.awaitNanos(nanos);
            } finally {
                takeLock.unlock();
            }
        }
        E x = opQueue(null);
        int c = count.getAndDecrement();
        if (c > 1) {
            this.notEmpty.signal();
        }
        takeLock.unlock();
        if (c != this.capacity) {
            return x;
        }
        signalNotFull();
        return x;
    }

    /* JADX WARN: Finally extract failed */
    @Override // java.util.Queue
    public E poll() {
        E x = null;
        AtomicInteger count = this.count;
        if (count.get() != 0) {
            x = null;
            int c = -1;
            ReentrantLock takeLock = this.takeLock;
            takeLock.lock();
            try {
                if (count.get() > 0) {
                    x = opQueue(null);
                    c = count.getAndDecrement();
                    if (c > 1) {
                        this.notEmpty.signal();
                    }
                }
                takeLock.unlock();
                if (c == this.capacity) {
                    signalNotFull();
                }
            } catch (Throwable th) {
                takeLock.unlock();
                throw th;
            }
        }
        return x;
    }

    @Override // java.util.Queue
    public E peek() {
        E e = null;
        if (this.count.get() != 0) {
            ReentrantLock takeLock = this.takeLock;
            takeLock.lock();
            try {
                Node<E> first = this.head.next;
                if (first != null) {
                    e = first.getValue();
                }
            } finally {
                takeLock.unlock();
            }
        }
        return e;
    }

    void unlink(Node<E> p, Node<E> trail) {
        p.setValue(null);
        trail.next = (Node<E>) p.next;
        if (this.last == p) {
            this.last = trail;
        }
        if (this.count.getAndDecrement() == this.capacity) {
            this.notFull.signal();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingQueue
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }
        fullyLock();
        try {
            Node<E> trail = this.head;
            for (Node<E> p = trail.next; p != null; p = p.next) {
                if (o.equals(p.getValue())) {
                    unlink(p, trail);
                    return true;
                }
                trail = p;
            }
            return false;
        } finally {
            fullyUnlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingQueue
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        fullyLock();
        try {
            for (Node node = this.head.next; node != null; node = node.next) {
                if (o.equals(node.getValue())) {
                    return true;
                }
            }
            return false;
        } finally {
            fullyUnlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray() {
        fullyLock();
        try {
            Object[] a = new Object[this.count.get()];
            Node node = this.head.next;
            int k = 0;
            while (node != null) {
                int k2 = k + 1;
                a[k] = node.getValue();
                node = node.next;
                k = k2;
            }
            return a;
        } finally {
            fullyUnlock();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection
    public <T> T[] toArray(T[] a) {
        fullyLock();
        try {
            int size = this.count.get();
            if (a.length < size) {
                a = (T[]) ((Object[]) Array.newInstance(a.getClass().getComponentType(), size));
            }
            Node node = this.head.next;
            int k = 0;
            while (node != null) {
                a[k] = node.getValue();
                node = node.next;
                k++;
            }
            if (a.length > k) {
                a[k] = null;
            }
            return a;
        } finally {
            fullyUnlock();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        fullyLock();
        try {
            Node node = (Node<E>) this.head;
            while (true) {
                Node node2 = node.next;
                if (node2 == null) {
                    break;
                }
                node.next = node;
                node2.setValue(null);
                node = (Node<E>) node2;
            }
            this.head = this.last;
            if (this.count.getAndSet(0) == this.capacity) {
                this.notFull.signal();
            }
        } finally {
            fullyUnlock();
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> c) {
        return drainTo(c, Integer.MAX_VALUE);
    }

    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> c, int maxElements) {
        if (c == null) {
            throw new NullPointerException();
        } else if (c == this) {
            throw new IllegalArgumentException();
        } else if (maxElements <= 0) {
            return 0;
        } else {
            boolean signalNotFull = false;
            ReentrantLock takeLock = this.takeLock;
            takeLock.lock();
            try {
                int n = Math.min(maxElements, this.count.get());
                Node<E> h = this.head;
                int i = 0;
                while (i < n) {
                    Node<E> p = h.next;
                    c.add((Object) p.getValue());
                    p.setValue(null);
                    h.next = (Node<T>) h;
                    h = p;
                    i++;
                }
                if (i > 0) {
                    this.head = h;
                    signalNotFull = this.count.getAndAdd(-i) == this.capacity;
                }
                return n;
            } finally {
                takeLock.unlock();
                if (0 != 0) {
                    signalNotFull();
                }
            }
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new Itr();
    }

    /* loaded from: classes2.dex */
    private class Itr implements Iterator<E> {
        private Node<E> current;
        private E currentElement;
        private Node<E> lastRet;

        Itr() {
            PriorityObjectBlockingQueue.this.fullyLock();
            try {
                this.current = PriorityObjectBlockingQueue.this.head.next;
                if (this.current != null) {
                    this.currentElement = this.current.getValue();
                }
            } finally {
                PriorityObjectBlockingQueue.this.fullyUnlock();
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.current != null;
        }

        private Node<E> nextNode(Node<E> p) {
            while (true) {
                Node<E> s = p.next;
                if (s == p) {
                    return PriorityObjectBlockingQueue.this.head.next;
                }
                if (s == null || s.getValue() != null) {
                    return s;
                }
                p = s;
            }
        }

        @Override // java.util.Iterator
        public E next() {
            PriorityObjectBlockingQueue.this.fullyLock();
            try {
                if (this.current == null) {
                    throw new NoSuchElementException();
                }
                E x = this.currentElement;
                this.lastRet = this.current;
                this.current = nextNode(this.current);
                this.currentElement = this.current == null ? null : this.current.getValue();
                return x;
            } finally {
                PriorityObjectBlockingQueue.this.fullyUnlock();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x001e, code lost:
            r5.this$0.unlink(r1, r2);
         */
        @Override // java.util.Iterator
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void remove() {
            /*
                r5 = this;
                com.lidroid.xutils.task.Node<E> r3 = r5.lastRet
                if (r3 != 0) goto L_0x000a
                java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
                r3.<init>()
                throw r3
            L_0x000a:
                com.lidroid.xutils.task.PriorityObjectBlockingQueue r3 = com.lidroid.xutils.task.PriorityObjectBlockingQueue.this
                r3.fullyLock()
                com.lidroid.xutils.task.Node<E> r0 = r5.lastRet     // Catch: all -> 0x002d
                r3 = 0
                r5.lastRet = r3     // Catch: all -> 0x002d
                com.lidroid.xutils.task.PriorityObjectBlockingQueue r3 = com.lidroid.xutils.task.PriorityObjectBlockingQueue.this     // Catch: all -> 0x002d
                com.lidroid.xutils.task.Node<E> r2 = r3.head     // Catch: all -> 0x002d
                com.lidroid.xutils.task.Node<T> r1 = r2.next     // Catch: all -> 0x002d
            L_0x001a:
                if (r1 == 0) goto L_0x0023
                if (r1 != r0) goto L_0x0029
                com.lidroid.xutils.task.PriorityObjectBlockingQueue r3 = com.lidroid.xutils.task.PriorityObjectBlockingQueue.this     // Catch: all -> 0x002d
                r3.unlink(r1, r2)     // Catch: all -> 0x002d
            L_0x0023:
                com.lidroid.xutils.task.PriorityObjectBlockingQueue r3 = com.lidroid.xutils.task.PriorityObjectBlockingQueue.this
                r3.fullyUnlock()
                return
            L_0x0029:
                r2 = r1
                com.lidroid.xutils.task.Node<T> r1 = r1.next     // Catch: all -> 0x002d
                goto L_0x001a
            L_0x002d:
                r3 = move-exception
                com.lidroid.xutils.task.PriorityObjectBlockingQueue r4 = com.lidroid.xutils.task.PriorityObjectBlockingQueue.this
                r4.fullyUnlock()
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lidroid.xutils.task.PriorityObjectBlockingQueue.Itr.remove():void");
        }
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        fullyLock();
        try {
            s.defaultWriteObject();
            for (Node node = this.head.next; node != null; node = node.next) {
                s.writeObject(node.getValue());
            }
            s.writeObject(null);
        } finally {
            fullyUnlock();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        this.count.set(0);
        Node<E> node = new Node<>(null);
        this.head = node;
        this.last = node;
        while (true) {
            Object readObject = s.readObject();
            if (readObject != null) {
                add(readObject);
            } else {
                return;
            }
        }
    }
}
