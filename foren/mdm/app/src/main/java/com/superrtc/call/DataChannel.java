package com.superrtc.call;

import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public class DataChannel {
    private final long nativeDataChannel;
    private long nativeObserver;

    /* loaded from: classes2.dex */
    public interface Observer {
        void onBufferedAmountChange(long j);

        void onMessage(Buffer buffer);

        void onStateChange();
    }

    /* loaded from: classes2.dex */
    public enum State {
        CONNECTING,
        OPEN,
        CLOSING,
        CLOSED
    }

    private native long registerObserverNative(Observer observer);

    private native boolean sendNative(byte[] bArr, boolean z);

    private native void unregisterObserverNative(long j);

    public native long bufferedAmount();

    public native void close();

    public native void dispose();

    public native String label();

    public native State state();

    /* loaded from: classes2.dex */
    public static class Init {
        public int id;
        public int maxRetransmitTimeMs;
        public int maxRetransmits;
        public boolean negotiated;
        public boolean ordered;
        public String protocol;

        public Init() {
            this.ordered = true;
            this.maxRetransmitTimeMs = -1;
            this.maxRetransmits = -1;
            this.protocol = "";
            this.negotiated = false;
            this.id = -1;
        }

        private Init(boolean ordered, int maxRetransmitTimeMs, int maxRetransmits, String protocol, boolean negotiated, int id) {
            this.ordered = true;
            this.maxRetransmitTimeMs = -1;
            this.maxRetransmits = -1;
            this.protocol = "";
            this.negotiated = false;
            this.id = -1;
            this.ordered = ordered;
            this.maxRetransmitTimeMs = maxRetransmitTimeMs;
            this.maxRetransmits = maxRetransmits;
            this.protocol = protocol;
            this.negotiated = negotiated;
            this.id = id;
        }
    }

    /* loaded from: classes2.dex */
    public static class Buffer {
        public final boolean binary;
        public final ByteBuffer data;

        public Buffer(ByteBuffer data, boolean binary) {
            this.data = data;
            this.binary = binary;
        }
    }

    public DataChannel(long nativeDataChannel) {
        this.nativeDataChannel = nativeDataChannel;
    }

    public void registerObserver(Observer observer) {
        if (this.nativeObserver != 0) {
            unregisterObserverNative(this.nativeObserver);
        }
        this.nativeObserver = registerObserverNative(observer);
    }

    public void unregisterObserver() {
        unregisterObserverNative(this.nativeObserver);
    }

    public boolean send(Buffer buffer) {
        byte[] data = new byte[buffer.data.remaining()];
        buffer.data.get(data);
        return sendNative(data, buffer.binary);
    }
}
