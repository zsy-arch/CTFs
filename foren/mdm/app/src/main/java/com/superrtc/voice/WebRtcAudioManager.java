package com.superrtc.voice;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.os.Build;
import com.superrtc.call.Logging;

/* loaded from: classes2.dex */
public class WebRtcAudioManager {
    private static final int BITS_PER_SAMPLE = 16;
    private static final int CHANNELS = 1;
    private static final boolean DEBUG = false;
    private static final int DEFAULT_FRAME_PER_BUFFER = 256;
    private static final String TAG = "WebRtcAudioManager";
    private final AudioManager audioManager;
    private int channels;
    private final Context context;
    private boolean hardwareAEC;
    private boolean hardwareAGC;
    private boolean hardwareNS;
    private boolean initialized = false;
    private int inputBufferSize;
    private boolean lowLatencyOutput;
    private final long nativeAudioManager;
    private int nativeChannels;
    private int nativeSampleRate;
    private int outputBufferSize;
    private int sampleRate;
    private static boolean blacklistDeviceForOpenSLESUsage = false;
    private static boolean blacklistDeviceForOpenSLESUsageIsOverridden = false;
    private static int audiosamplerate = 0;
    private static final String[] AUDIO_MODES = {"MODE_NORMAL", "MODE_RINGTONE", "MODE_IN_CALL", "MODE_IN_COMMUNICATION"};

    private native void nativeCacheAudioParameters(int i, int i2, boolean z, boolean z2, boolean z3, boolean z4, int i3, int i4, long j);

    public static synchronized void setBlacklistDeviceForOpenSLESUsage(boolean enable) {
        synchronized (WebRtcAudioManager.class) {
            blacklistDeviceForOpenSLESUsageIsOverridden = true;
            blacklistDeviceForOpenSLESUsage = enable;
        }
    }

    public static void setAudioSampleRate(int samplerate) {
        if (samplerate == 8000 || samplerate == 11025 || samplerate == 22050 || samplerate == 16000 || samplerate == 44100 || samplerate == 48000) {
            audiosamplerate = samplerate;
        }
    }

    WebRtcAudioManager(Context context, long nativeAudioManager) {
        Logging.d(TAG, "ctor" + WebRtcAudioUtils.getThreadInfo());
        this.context = context;
        this.nativeAudioManager = nativeAudioManager;
        this.audioManager = (AudioManager) context.getSystemService("audio");
        storeAudioParameters();
        nativeCacheAudioParameters(this.sampleRate, this.channels, this.hardwareAEC, this.hardwareAGC, this.hardwareNS, this.lowLatencyOutput, this.outputBufferSize, this.inputBufferSize, nativeAudioManager);
    }

    private boolean init() {
        Logging.d(TAG, "init" + WebRtcAudioUtils.getThreadInfo());
        if (!this.initialized) {
            this.initialized = true;
        }
        return true;
    }

    private void dispose() {
        Logging.d(TAG, "dispose" + WebRtcAudioUtils.getThreadInfo());
        if (!this.initialized) {
        }
    }

    private boolean isCommunicationModeEnabled() {
        return true;
    }

    private boolean isDeviceBlacklistedForOpenSLESUsage() {
        boolean blacklisted;
        if (blacklistDeviceForOpenSLESUsageIsOverridden) {
            blacklisted = blacklistDeviceForOpenSLESUsage;
        } else {
            blacklisted = WebRtcAudioUtils.deviceIsBlacklistedForOpenSLESUsage();
        }
        if (blacklisted) {
            Logging.e(TAG, String.valueOf(Build.MODEL) + " is blacklisted for OpenSL ES usage!");
        }
        return blacklisted;
    }

    private void storeAudioParameters() {
        int minOutputFrameSize;
        this.channels = 1;
        this.sampleRate = getNativeOutputSampleRate();
        this.hardwareAEC = isAcousticEchoCancelerSupported();
        this.hardwareAGC = isAutomaticGainControlSupported();
        this.hardwareNS = isNoiseSuppressorSupported();
        Logging.d(TAG, "HW_Audio_Process hardwareAEC: " + this.hardwareAEC + ", hardwareAGC: " + this.hardwareAGC + " hardwareNS: " + this.hardwareNS + " ,sampleRate:" + this.sampleRate);
        this.hardwareAEC = false;
        this.hardwareAGC = false;
        this.hardwareNS = false;
        this.lowLatencyOutput = isLowLatencyOutputSupported();
        if (this.lowLatencyOutput) {
            minOutputFrameSize = getLowLatencyOutputFramesPerBuffer();
        } else {
            minOutputFrameSize = getMinOutputFrameSize(this.sampleRate, this.channels);
        }
        this.outputBufferSize = minOutputFrameSize;
        this.inputBufferSize = getMinInputFrameSize(this.sampleRate, this.channels);
    }

    private boolean hasEarpiece() {
        return this.context.getPackageManager().hasSystemFeature("android.hardware.telephony");
    }

    private boolean isLowLatencyOutputSupported() {
        return isOpenSLESSupported() && this.context.getPackageManager().hasSystemFeature("android.hardware.audio.low_latency");
    }

    public boolean isLowLatencyInputSupported() {
        return WebRtcAudioUtils.runningOnLollipopOrHigher() && isLowLatencyOutputSupported();
    }

    private int getNativeOutputSampleRate() {
        int sampleRateHz;
        if (WebRtcAudioUtils.runningOnEmulator()) {
            Logging.d(TAG, "Running emulator, overriding sample rate to 8 kHz.");
            return 8000;
        } else if (WebRtcAudioUtils.isDefaultSampleRateOverridden()) {
            Logging.d(TAG, "Default sample rate is overriden to " + WebRtcAudioUtils.getDefaultSampleRateHz() + " Hz");
            return WebRtcAudioUtils.getDefaultSampleRateHz();
        } else {
            if (WebRtcAudioUtils.runningOnJellyBeanMR1OrHigher()) {
                sampleRateHz = getSampleRateOnJellyBeanMR10OrHigher();
            } else {
                sampleRateHz = WebRtcAudioUtils.getDefaultSampleRateHz();
            }
            if (audiosamplerate != 0) {
                sampleRateHz = audiosamplerate;
            }
            Logging.d(TAG, "Sample rate is set to " + sampleRateHz + " Hz");
            return sampleRateHz;
        }
    }

    @TargetApi(17)
    private int getSampleRateOnJellyBeanMR10OrHigher() {
        String sampleRateString = this.audioManager.getProperty("android.media.property.OUTPUT_SAMPLE_RATE");
        if (sampleRateString == null) {
            return WebRtcAudioUtils.getDefaultSampleRateHz();
        }
        return Integer.parseInt(sampleRateString);
    }

    @TargetApi(17)
    private int getLowLatencyOutputFramesPerBuffer() {
        String framesPerBuffer;
        assertTrue(isLowLatencyOutputSupported());
        if (WebRtcAudioUtils.runningOnJellyBeanMR1OrHigher() && (framesPerBuffer = this.audioManager.getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER")) != null) {
            return Integer.parseInt(framesPerBuffer);
        }
        return 256;
    }

    private static boolean isAcousticEchoCancelerSupported() {
        return WebRtcAudioEffects.canUseAcousticEchoCanceler();
    }

    private static boolean isAutomaticGainControlSupported() {
        return WebRtcAudioEffects.canUseAutomaticGainControl();
    }

    private static boolean isNoiseSuppressorSupported() {
        return WebRtcAudioEffects.canUseNoiseSuppressor();
    }

    private static int getMinOutputFrameSize(int sampleRateInHz, int numChannels) {
        int channelConfig;
        int bytesPerFrame = numChannels * 2;
        if (numChannels == 1) {
            channelConfig = 4;
        } else if (numChannels != 2) {
            return -1;
        } else {
            channelConfig = 12;
        }
        return AudioTrack.getMinBufferSize(sampleRateInHz, channelConfig, 2) / bytesPerFrame;
    }

    private int getLowLatencyInputFramesPerBuffer() {
        assertTrue(isLowLatencyInputSupported());
        return getLowLatencyOutputFramesPerBuffer();
    }

    private static int getMinInputFrameSize(int sampleRateInHz, int numChannels) {
        boolean z = true;
        int bytesPerFrame = numChannels * 2;
        if (numChannels != 1) {
            z = false;
        }
        assertTrue(z);
        return AudioRecord.getMinBufferSize(sampleRateInHz, 16, 2) / bytesPerFrame;
    }

    private static boolean isOpenSLESSupported() {
        return WebRtcAudioUtils.runningOnGingerBreadOrHigher();
    }

    private static void assertTrue(boolean condition) {
        if (!condition) {
            throw new AssertionError("Expected condition to be true");
        }
    }
}
