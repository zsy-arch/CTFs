package rx.exceptions;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public final class CompositeException extends RuntimeException {
    private static final long serialVersionUID = 3026362227162912146L;
    private Throwable cause;
    private final List<Throwable> exceptions;
    private final String message;

    @Deprecated
    public CompositeException(String messagePrefix, Collection<? extends Throwable> errors) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        List<Throwable> localExceptions = new ArrayList<>();
        if (errors != null) {
            for (Throwable ex : errors) {
                if (ex instanceof CompositeException) {
                    linkedHashSet.addAll(((CompositeException) ex).getExceptions());
                } else if (ex != null) {
                    linkedHashSet.add(ex);
                } else {
                    linkedHashSet.add(new NullPointerException());
                }
            }
        } else {
            linkedHashSet.add(new NullPointerException());
        }
        localExceptions.addAll(linkedHashSet);
        this.exceptions = Collections.unmodifiableList(localExceptions);
        this.message = this.exceptions.size() + " exceptions occurred. ";
    }

    public CompositeException(Collection<? extends Throwable> errors) {
        this(null, errors);
    }

    public CompositeException(Throwable... errors) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        List<Throwable> localExceptions = new ArrayList<>();
        if (errors != null) {
            for (Throwable ex : errors) {
                if (ex instanceof CompositeException) {
                    linkedHashSet.addAll(((CompositeException) ex).getExceptions());
                } else if (ex != null) {
                    linkedHashSet.add(ex);
                } else {
                    linkedHashSet.add(new NullPointerException());
                }
            }
        } else {
            linkedHashSet.add(new NullPointerException());
        }
        localExceptions.addAll(linkedHashSet);
        this.exceptions = Collections.unmodifiableList(localExceptions);
        this.message = this.exceptions.size() + " exceptions occurred. ";
    }

    public List<Throwable> getExceptions() {
        return this.exceptions;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }

    @Override // java.lang.Throwable
    public synchronized Throwable getCause() {
        if (this.cause == null) {
            Throwable localCause = new CompositeExceptionCausalChain();
            Set<Throwable> seenCauses = new HashSet<>();
            Throwable chain = localCause;
            Iterator<Throwable> it = this.exceptions.iterator();
            while (it.hasNext()) {
                Throwable e = it.next();
                if (!seenCauses.contains(e)) {
                    seenCauses.add(e);
                    for (Throwable child : getListOfCauses(e)) {
                        if (seenCauses.contains(child)) {
                            e = new RuntimeException("Duplicate found in causal chain so cropping to prevent loop ...");
                        } else {
                            seenCauses.add(child);
                        }
                    }
                    try {
                        chain.initCause(e);
                    } catch (Throwable th) {
                    }
                    chain = getRootCause(chain);
                }
            }
            this.cause = localCause;
        }
        return this.cause;
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream s) {
        printStackTrace(new WrappedPrintStream(s));
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter s) {
        printStackTrace(new WrappedPrintWriter(s));
    }

    /* JADX INFO: Multiple debug info for r4v2 java.util.Iterator<java.lang.Throwable>: [D('i$' int), D('i$' java.util.Iterator)] */
    private void printStackTrace(PrintStreamOrWriter s) {
        StringBuilder b = new StringBuilder(128);
        b.append(this).append('\n');
        for (StackTraceElement myStackElement : getStackTrace()) {
            b.append("\tat ").append(myStackElement).append('\n');
        }
        int i = 1;
        for (Throwable ex : this.exceptions) {
            b.append("  ComposedException ").append(i).append(" :\n");
            appendStackTrace(b, ex, "\t");
            i++;
        }
        synchronized (s.lock()) {
            s.println(b.toString());
        }
    }

    private void appendStackTrace(StringBuilder b, Throwable ex, String prefix) {
        b.append(prefix).append(ex).append('\n');
        for (StackTraceElement stackElement : ex.getStackTrace()) {
            b.append("\t\tat ").append(stackElement).append('\n');
        }
        if (ex.getCause() != null) {
            b.append("\tCaused by: ");
            appendStackTrace(b, ex.getCause(), "");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static abstract class PrintStreamOrWriter {
        abstract Object lock();

        abstract void println(Object obj);

        PrintStreamOrWriter() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class WrappedPrintStream extends PrintStreamOrWriter {
        private final PrintStream printStream;

        WrappedPrintStream(PrintStream printStream) {
            this.printStream = printStream;
        }

        @Override // rx.exceptions.CompositeException.PrintStreamOrWriter
        Object lock() {
            return this.printStream;
        }

        @Override // rx.exceptions.CompositeException.PrintStreamOrWriter
        void println(Object o) {
            this.printStream.println(o);
        }
    }

    /* loaded from: classes2.dex */
    static final class WrappedPrintWriter extends PrintStreamOrWriter {
        private final PrintWriter printWriter;

        WrappedPrintWriter(PrintWriter printWriter) {
            this.printWriter = printWriter;
        }

        @Override // rx.exceptions.CompositeException.PrintStreamOrWriter
        Object lock() {
            return this.printWriter;
        }

        @Override // rx.exceptions.CompositeException.PrintStreamOrWriter
        void println(Object o) {
            this.printWriter.println(o);
        }
    }

    /* loaded from: classes2.dex */
    static final class CompositeExceptionCausalChain extends RuntimeException {
        static final String MESSAGE = "Chain of Causes for CompositeException In Order Received =>";
        private static final long serialVersionUID = 3875212506787802066L;

        CompositeExceptionCausalChain() {
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return MESSAGE;
        }
    }

    private List<Throwable> getListOfCauses(Throwable ex) {
        List<Throwable> list = new ArrayList<>();
        Throwable root = ex.getCause();
        if (root != null && root != ex) {
            while (true) {
                list.add(root);
                Throwable cause = root.getCause();
                if (cause == null || cause == root) {
                    break;
                }
                root = root.getCause();
            }
        }
        return list;
    }

    private Throwable getRootCause(Throwable e) {
        Throwable root = e.getCause();
        if (root == null || root == e) {
            return e;
        }
        while (true) {
            Throwable cause = root.getCause();
            if (cause == null || cause == root) {
                break;
            }
            root = root.getCause();
        }
        return root;
    }
}
