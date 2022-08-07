package com.superrtc.voice;

import android.content.Context;
import android.media.AudioRecord;
import android.os.Process;
import com.superrtc.call.Logging;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public class WebRtcAudioRecord {
    private static final int BITS_PER_SAMPLE = 16;
    private static final int BUFFERS_PER_SECOND = 100;
    private static final int BUFFER_SIZE_FACTOR = 2;
    private static final int CALLBACK_BUFFER_SIZE_MS = 10;
    private static final boolean DEBUG = false;
    private static final String TAG = "WebRtcAudioRecord";
    private static volatile boolean microphoneMute = false;
    private AudioRecord audioRecord = null;
    private AudioRecordThread audioThread = null;
    private ByteBuffer byteBuffer;
    private final Context context;
    private WebRtcAudioEffects effects;
    private byte[] emptyBytes;
    private final long nativeAudioRecord;

    private native void nativeCacheDirectBufferAddress(ByteBuffer byteBuffer, long j);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeDataIsRecorded(int i, long j);

    /* loaded from: classes2.dex */
    private class AudioRecordThread extends Thread {
        private volatile boolean keepAlive = true;

        public AudioRecordThread(String name) {
            super(name);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Process.setThreadPriority(-19);
            Logging.d(WebRtcAudioRecord.TAG, "AudioRecordThread" + WebRtcAudioUtils.getThreadInfo());
            WebRtcAudioRecord.assertTrue(WebRtcAudioRecord.this.audioRecord.getRecordingState() == 3);
            System.nanoTime();
            while (this.keepAlive) {
                int bytesRead = WebRtcAudioRecord.this.audioRecord.read(WebRtcAudioRecord.this.byteBuffer, WebRtcAudioRecord.this.byteBuffer.capacity());
                if (bytesRead == WebRtcAudioRecord.this.byteBuffer.capacity()) {
                    if (WebRtcAudioRecord.microphoneMute) {
                        WebRtcAudioRecord.this.byteBuffer.clear();
                        WebRtcAudioRecord.this.byteBuffer.put(WebRtcAudioRecord.this.emptyBytes);
                    }
                    WebRtcAudioRecord.this.nativeDataIsRecorded(bytesRead, WebRtcAudioRecord.this.nativeAudioRecord);
                } else {
                    Logging.e(WebRtcAudioRecord.TAG, "AudioRecord.read failed: " + bytesRead);
                    if (bytesRead == -3) {
                        this.keepAlive = false;
                    }
                }
            }
            try {
                WebRtcAudioRecord.this.audioRecord.stop();
            } catch (IllegalStateException e) {
                Logging.e(WebRtcAudioRecord.TAG, "AudioRecord.stop failed: " + e.getMessage());
            }
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

    WebRtcAudioRecord(Context context, long nativeAudioRecord) {
        this.effects = null;
        Logging.d(TAG, "ctor" + WebRtcAudioUtils.getThreadInfo());
        this.context = context;
        this.nativeAudioRecord = nativeAudioRecord;
        this.effects = WebRtcAudioEffects.create();
    }

    private boolean enableBuiltInAEC(boolean enable) {
        Logging.d(TAG, "enableBuiltInAEC(" + enable + ')');
        if (this.effects != null) {
            return this.effects.setAEC(enable);
        }
        Logging.e(TAG, "Built-in AEC is not supported on this platform");
        return false;
    }

    private boolean enableBuiltInAGC(boolean enable) {
        Logging.d(TAG, "enableBuiltInAGC(" + enable + ')');
        if (this.effects != null) {
            return this.effects.setAGC(enable);
        }
        Logging.e(TAG, "Built-in AGC is not supported on this platform");
        return false;
    }

    private boolean enableBuiltInNS(boolean enable) {
        Logging.d(TAG, "enableBuiltInNS(" + enable + ')');
        if (this.effects != null) {
            return this.effects.setNS(enable);
        }
        Logging.e(TAG, "Built-in NS is not supported on this platform");
        return false;
    }

    private int initRecording(int sampleRate, int channels) {
        Logging.d(TAG, "initRecording(sampleRate=" + sampleRate + ", channels=" + channels + ")");
        if (!WebRtcAudioUtils.hasPermission(this.context, "android.permission.RECORD_AUDIO")) {
            Logging.e(TAG, "RECORD_AUDIO permission is missing");
            return -1;
        } else if (this.audioRecord != null) {
            Logging.e(TAG, "InitRecording() called twice without StopRecording()");
            return -1;
        } else {
            int framesPerBuffer = sampleRate / 100;
            this.byteBuffer = ByteBuffer.allocateDirect(channels * 2 * framesPerBuffer);
            Logging.d(TAG, "byteBuffer.capacity: " + this.byteBuffer.capacity());
            this.emptyBytes = new byte[this.byteBuffer.capacity()];
            nativeCacheDirectBufferAddress(this.byteBuffer, this.nativeAudioRecord);
            int minBufferSize = AudioRecord.getMinBufferSize(sampleRate, 16, 2);
            if (minBufferSize == -1 || minBufferSize == -2) {
                Logging.e(TAG, "AudioRecord.getMinBufferSize failed: " + minBufferSize);
                return -1;
            }
            Logging.d(TAG, "AudioRecord.getMinBufferSize: " + minBufferSize);
            int bufferSizeInBytes = Math.max(minBufferSize * 2, this.byteBuffer.capacity());
            Logging.d(TAG, "bufferSizeInBytes: " + bufferSizeInBytes);
            try {
                this.audioRecord = new AudioRecord(7, sampleRate, 16, 2, bufferSizeInBytes);
                if (this.audioRecord == null || this.audioRecord.getState() != 1) {
                    Logging.e(TAG, "Failed to create a new AudioRecord instance");
                    return -1;
                }
                Logging.d(TAG, "AudioRecord session ID: " + this.audioRecord.getAudioSessionId() + ", audio format: " + this.audioRecord.getAudioFormat() + ", channels: " + this.audioRecord.getChannelCount() + ", sample rate: " + this.audioRecord.getSampleRate());
                if (this.effects == null) {
                    return framesPerBuffer;
                }
                this.effects.enable(this.audioRecord.getAudioSessionId());
                return framesPerBuffer;
            } catch (IllegalArgumentException e) {
                Logging.e(TAG, e.getMessage());
                return -1;
            }
        }
    }

    private boolean startRecording() {
        Logging.d(TAG, "startRecording");
        assertTrue(this.audioRecord != null);
        assertTrue(this.audioThread == null);
        try {
            this.audioRecord.startRecording();
            if (this.audioRecord.getRecordingState() != 3) {
                Logging.e(TAG, "AudioRecord.startRecording failed");
                return false;
            }
            this.audioThread = new AudioRecordThread("AudioRecordJavaThread");
            this.audioThread.start();
            return true;
        } catch (IllegalStateException e) {
            Logging.e(TAG, "AudioRecord.startRecording failed: " + e.getMessage());
            return false;
        }
    }

    private boolean stopRecording() {
        Logging.d(TAG, "stopRecording");
        assertTrue(this.audioThread != null);
        this.audioThread.joinThread();
        this.audioThread = null;
        if (this.effects != null) {
            this.effects.release();
        }
        this.audioRecord.release();
        this.audioRecord = null;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void assertTrue(boolean condition) {
        if (!condition) {
            throw new AssertionError("Expected condition to be true");
        }
    }

    public static void setMicrophoneMute(boolean mute) {
        Logging.w(TAG, "setMicrophoneMute API will be deprecated soon.");
        microphoneMute = mute;
    }
}
