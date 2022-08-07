package com.superrtc.voice;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Process;
import com.superrtc.call.Logging;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
class WebRtcAudioTrack {
    private static final int BITS_PER_SAMPLE = 16;
    private static final int BUFFERS_PER_SECOND = 100;
    private static final int CALLBACK_BUFFER_SIZE_MS = 10;
    private static final boolean DEBUG = false;
    private static final String TAG = "WebRtcAudioTrack";
    private final AudioManager audioManager;
    private ByteBuffer byteBuffer;
    private final Context context;
    private final long nativeAudioTrack;
    private AudioTrack audioTrack = null;
    private AudioTrackThread audioThread = null;

    private native void nativeCacheDirectBufferAddress(ByteBuffer byteBuffer, long j);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeGetPlayoutData(int i, long j);

    /* loaded from: classes2.dex */
    private class AudioTrackThread extends Thread {
        private volatile boolean keepAlive = true;

        public AudioTrackThread(String name) {
            super(name);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            boolean z;
            boolean z2 = true;
            Process.setThreadPriority(-19);
            Logging.d(WebRtcAudioTrack.TAG, "AudioTrackThread" + WebRtcAudioUtils.getThreadInfo());
            try {
                WebRtcAudioTrack.this.audioTrack.play();
                WebRtcAudioTrack.assertTrue(WebRtcAudioTrack.this.audioTrack.getPlayState() == 3);
                int sizeInBytes = WebRtcAudioTrack.this.byteBuffer.capacity();
                while (this.keepAlive) {
                    WebRtcAudioTrack.this.nativeGetPlayoutData(sizeInBytes, WebRtcAudioTrack.this.nativeAudioTrack);
                    if (sizeInBytes <= WebRtcAudioTrack.this.byteBuffer.remaining()) {
                        z = true;
                    } else {
                        z = false;
                    }
                    WebRtcAudioTrack.assertTrue(z);
                    int bytesWritten = WebRtcAudioUtils.runningOnLollipopOrHigher() ? writeOnLollipop(WebRtcAudioTrack.this.audioTrack, WebRtcAudioTrack.this.byteBuffer, sizeInBytes) : writePreLollipop(WebRtcAudioTrack.this.audioTrack, WebRtcAudioTrack.this.byteBuffer, sizeInBytes);
                    if (bytesWritten != sizeInBytes) {
                        Logging.e(WebRtcAudioTrack.TAG, "AudioTrack.write failed: " + bytesWritten);
                        if (bytesWritten == -3) {
                            this.keepAlive = false;
                        }
                    }
                    WebRtcAudioTrack.this.byteBuffer.rewind();
                }
                try {
                    WebRtcAudioTrack.this.audioTrack.stop();
                } catch (IllegalStateException e) {
                    Logging.e(WebRtcAudioTrack.TAG, "AudioTrack.stop failed: " + e.getMessage());
                }
                if (WebRtcAudioTrack.this.audioTrack.getPlayState() != 1) {
                    z2 = false;
                }
                WebRtcAudioTrack.assertTrue(z2);
                WebRtcAudioTrack.this.audioTrack.flush();
            } catch (IllegalStateException e2) {
                Logging.e(WebRtcAudioTrack.TAG, "AudioTrack.play failed: " + e2.getMessage());
            }
        }

        @TargetApi(21)
        private int writeOnLollipop(AudioTrack audioTrack, ByteBuffer byteBuffer, int sizeInBytes) {
            return audioTrack.write(byteBuffer, sizeInBytes, 0);
        }

        private int writePreLollipop(AudioTrack audioTrack, ByteBuffer byteBuffer, int sizeInBytes) {
            return audioTrack.write(byteBuffer.array(), byteBuffer.arrayOffset(), sizeInBytes);
        }

        public void joinThread() {
            this.keepAlive = false;
            while (isAlive()) {
                try {
                    join();
                } catch (InterruptedException e) {
                }
            }
        }
    }

    WebRtcAudioTrack(Context context, long nativeAudioTrack) {
        Logging.d(TAG, "ctor" + WebRtcAudioUtils.getThreadInfo());
        this.context = context;
        this.nativeAudioTrack = nativeAudioTrack;
        this.audioManager = (AudioManager) context.getSystemService("audio");
    }

    private void initPlayout(int sampleRate, int channels) {
        boolean z = true;
        Logging.d(TAG, "initPlayout(sampleRate=" + sampleRate + ", channels=" + channels + ")");
        this.byteBuffer = ByteBuffer.allocateDirect((sampleRate / 100) * channels * 2);
        Logging.d(TAG, "byteBuffer.capacity: " + this.byteBuffer.capacity());
        nativeCacheDirectBufferAddress(this.byteBuffer, this.nativeAudioTrack);
        int minBufferSizeInBytes = AudioTrack.getMinBufferSize(sampleRate, 4, 2);
        Logging.d(TAG, "AudioTrack.getMinBufferSize: " + minBufferSizeInBytes);
        assertTrue(this.audioTrack == null);
        assertTrue(this.byteBuffer.capacity() < minBufferSizeInBytes);
        try {
            this.audioTrack = new AudioTrack(0, sampleRate, 4, 2, minBufferSizeInBytes, 1);
            assertTrue(this.audioTrack.getState() == 1);
            assertTrue(this.audioTrack.getPlayState() == 1);
            if (this.audioTrack.getStreamType() != 0) {
                z = false;
            }
            assertTrue(z);
        } catch (IllegalArgumentException e) {
            Logging.d(TAG, e.getMessage());
        }
    }

    private boolean startPlayout() {
        boolean z = false;
        Logging.d(TAG, "startPlayout");
        assertTrue(this.audioTrack != null);
        if (this.audioThread == null) {
            z = true;
        }
        assertTrue(z);
        this.audioThread = new AudioTrackThread("AudioTrackJavaThread");
        this.audioThread.start();
        return true;
    }

    private boolean stopPlayout() {
        Logging.d(TAG, "stopPlayout");
        assertTrue(this.audioThread != null);
        this.audioThread.joinThread();
        this.audioThread = null;
        if (this.audioTrack != null) {
            this.audioTrack.release();
            this.audioTrack = null;
        }
        return true;
    }

    private int getStreamMaxVolume() {
        Logging.d(TAG, "getStreamMaxVolume");
        assertTrue(this.audioManager != null);
        return this.audioManager.getStreamMaxVolume(0);
    }

    private boolean setStreamVolume(int volume) {
        Logging.d(TAG, "setStreamVolume(" + volume + ")");
        assertTrue(this.audioManager != null);
        if (isVolumeFixed()) {
            Logging.e(TAG, "The device implements a fixed volume policy.");
            return false;
        }
        this.audioManager.setStreamVolume(0, volume, 0);
        return true;
    }

    @TargetApi(21)
    private boolean isVolumeFixed() {
        if (!WebRtcAudioUtils.runningOnLollipopOrHigher()) {
            return false;
        }
        return this.audioManager.isVolumeFixed();
    }

    private int getStreamVolume() {
        Logging.d(TAG, "getStreamVolume");
        assertTrue(this.audioManager != null);
        return this.audioManager.getStreamVolume(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void assertTrue(boolean condition) {
        if (!condition) {
            throw new AssertionError("Expected condition to be true");
        }
    }
}
