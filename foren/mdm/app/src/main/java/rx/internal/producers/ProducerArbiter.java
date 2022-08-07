package rx.internal.producers;

import rx.Producer;

/* loaded from: classes2.dex */
public final class ProducerArbiter implements Producer {
    static final Producer NULL_PRODUCER = new Producer() { // from class: rx.internal.producers.ProducerArbiter.1
        @Override // rx.Producer
        public void request(long n) {
        }
    };
    Producer currentProducer;
    boolean emitting;
    long missedProduced;
    Producer missedProducer;
    long missedRequested;
    long requested;

    @Override // rx.Producer
    public void request(long n) {
        if (n < 0) {
            throw new IllegalArgumentException("n >= 0 required");
        } else if (n != 0) {
            synchronized (this) {
                try {
                    if (this.emitting) {
                        this.missedRequested += n;
                    } else {
                        this.emitting = true;
                        try {
                            long u2 = this.requested + n;
                            if (u2 < 0) {
                                u2 = Long.MAX_VALUE;
                            }
                            this.requested = u2;
                            Producer p = this.currentProducer;
                            if (p != null) {
                                p.request(n);
                            }
                            emitLoop();
                            if (1 == 0) {
                                synchronized (this) {
                                    try {
                                        this.emitting = false;
                                    } catch (Throwable th) {
                                        throw th;
                                    }
                                }
                            }
                        } catch (Throwable th2) {
                            if (0 == 0) {
                                synchronized (this) {
                                    try {
                                        this.emitting = false;
                                    } catch (Throwable th3) {
                                        throw th3;
                                    }
                                }
                            }
                            throw th2;
                        }
                    }
                } catch (Throwable th4) {
                    throw th4;
                }
            }
        }
    }

    public void produced(long n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n > 0 required");
        }
        synchronized (this) {
            try {
                if (this.emitting) {
                    this.missedProduced += n;
                    return;
                }
                this.emitting = true;
                try {
                    long r = this.requested;
                    if (r != Long.MAX_VALUE) {
                        long u2 = r - n;
                        if (u2 < 0) {
                            throw new IllegalStateException("more items arrived than were requested");
                        }
                        this.requested = u2;
                    }
                    emitLoop();
                    if (1 == 0) {
                        synchronized (this) {
                            try {
                                this.emitting = false;
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                    }
                } catch (Throwable th2) {
                    if (0 == 0) {
                        synchronized (this) {
                            try {
                                this.emitting = false;
                            } catch (Throwable th3) {
                                throw th3;
                            }
                        }
                    }
                    throw th2;
                }
            } catch (Throwable th4) {
                throw th4;
            }
        }
    }

    public void setProducer(Producer newProducer) {
        synchronized (this) {
            try {
                if (this.emitting) {
                    if (newProducer == null) {
                        newProducer = NULL_PRODUCER;
                    }
                    this.missedProducer = newProducer;
                    return;
                }
                this.emitting = true;
                try {
                    this.currentProducer = newProducer;
                    if (newProducer != null) {
                        newProducer.request(this.requested);
                    }
                    emitLoop();
                    if (1 == 0) {
                        synchronized (this) {
                            try {
                                this.emitting = false;
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                    }
                } catch (Throwable th2) {
                    if (0 == 0) {
                        synchronized (this) {
                            try {
                                this.emitting = false;
                            } catch (Throwable th3) {
                                throw th3;
                            }
                        }
                    }
                    throw th2;
                }
            } catch (Throwable th4) {
                throw th4;
            }
        }
    }

    public void emitLoop() {
        while (true) {
            synchronized (this) {
                long localRequested = this.missedRequested;
                long localProduced = this.missedProduced;
                Producer localProducer = this.missedProducer;
                if (localRequested == 0 && localProduced == 0 && localProducer == null) {
                    this.emitting = false;
                    return;
                }
                this.missedRequested = 0L;
                this.missedProduced = 0L;
                this.missedProducer = null;
                long r = this.requested;
                if (r != Long.MAX_VALUE) {
                    long u2 = r + localRequested;
                    if (u2 < 0 || u2 == Long.MAX_VALUE) {
                        r = Long.MAX_VALUE;
                        this.requested = Long.MAX_VALUE;
                    } else {
                        long v = u2 - localProduced;
                        if (v < 0) {
                            throw new IllegalStateException("more produced than requested");
                        }
                        r = v;
                        this.requested = v;
                    }
                }
                if (localProducer == null) {
                    Producer p = this.currentProducer;
                    if (!(p == null || localRequested == 0)) {
                        p.request(localRequested);
                    }
                } else if (localProducer == NULL_PRODUCER) {
                    this.currentProducer = null;
                } else {
                    this.currentProducer = localProducer;
                    localProducer.request(r);
                }
            }
        }
    }
}
