package com.superrtc.voice;

import android.annotation.TargetApi;
import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.AudioEffect;
import android.media.audiofx.AutomaticGainControl;
import android.media.audiofx.NoiseSuppressor;
import android.os.Build;
import com.superrtc.call.Logging;
import java.util.UUID;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class WebRtcAudioEffects {
    private static final boolean DEBUG = false;
    private static final String TAG = "WebRtcAudioEffects";
    private AcousticEchoCanceler aec = null;
    private AutomaticGainControl agc = null;
    private NoiseSuppressor ns = null;
    private boolean shouldEnableAec = false;
    private boolean shouldEnableAgc = false;
    private boolean shouldEnableNs = false;
    private static final UUID AOSP_ACOUSTIC_ECHO_CANCELER = UUID.fromString("bb392ec0-8d4d-11e0-a896-0002a5d5c51b");
    private static final UUID AOSP_AUTOMATIC_GAIN_CONTROL = UUID.fromString("aa8130e0-66fc-11e0-bad0-0002a5d5c51b");
    private static final UUID AOSP_NOISE_SUPPRESSOR = UUID.fromString("c06c8400-8e06-11e0-9cb6-0002a5d5c51b");
    private static Boolean canUseAcousticEchoCanceler = null;
    private static Boolean canUseAutomaticGainControl = null;
    private static Boolean canUseNoiseSuppressor = null;

    public static boolean isAcousticEchoCancelerSupported() {
        return WebRtcAudioUtils.runningOnJellyBeanOrHigher() && AcousticEchoCanceler.isAvailable();
    }

    public static boolean isAutomaticGainControlSupported() {
        return WebRtcAudioUtils.runningOnJellyBeanOrHigher() && AutomaticGainControl.isAvailable();
    }

    public static boolean isNoiseSuppressorSupported() {
        return WebRtcAudioUtils.runningOnJellyBeanOrHigher() && NoiseSuppressor.isAvailable();
    }

    public static boolean isAcousticEchoCancelerBlacklisted() {
        boolean isBlacklisted = WebRtcAudioUtils.getBlackListedModelsForAecUsage().contains(Build.MODEL);
        if (isBlacklisted) {
            Logging.w(TAG, String.valueOf(Build.MODEL) + " is blacklisted for HW AEC usage!");
        }
        return isBlacklisted;
    }

    public static boolean isAutomaticGainControlBlacklisted() {
        boolean isBlacklisted = WebRtcAudioUtils.getBlackListedModelsForAgcUsage().contains(Build.MODEL);
        if (isBlacklisted) {
            Logging.w(TAG, String.valueOf(Build.MODEL) + " is blacklisted for HW AGC usage!");
        }
        return isBlacklisted;
    }

    public static boolean isNoiseSuppressorBlacklisted() {
        boolean isBlacklisted = WebRtcAudioUtils.getBlackListedModelsForNsUsage().contains(Build.MODEL);
        if (isBlacklisted) {
            Logging.w(TAG, String.valueOf(Build.MODEL) + " is blacklisted for HW NS usage!");
        }
        return isBlacklisted;
    }

    @TargetApi(18)
    private static boolean isAcousticEchoCancelerExcludedByUUID() {
        AudioEffect.Descriptor[] queryEffects = AudioEffect.queryEffects();
        for (AudioEffect.Descriptor d : queryEffects) {
            if (d.type.equals(AudioEffect.EFFECT_TYPE_AEC) && d.uuid.equals(AOSP_ACOUSTIC_ECHO_CANCELER)) {
                return true;
            }
        }
        return false;
    }

    @TargetApi(18)
    private static boolean isAutomaticGainControlExcludedByUUID() {
        AudioEffect.Descriptor[] queryEffects = AudioEffect.queryEffects();
        for (AudioEffect.Descriptor d : queryEffects) {
            if (d.type.equals(AudioEffect.EFFECT_TYPE_AGC) && d.uuid.equals(AOSP_AUTOMATIC_GAIN_CONTROL)) {
                return true;
            }
        }
        return false;
    }

    @TargetApi(18)
    private static boolean isNoiseSuppressorExcludedByUUID() {
        AudioEffect.Descriptor[] queryEffects = AudioEffect.queryEffects();
        for (AudioEffect.Descriptor d : queryEffects) {
            if (d.type.equals(AudioEffect.EFFECT_TYPE_NS) && d.uuid.equals(AOSP_NOISE_SUPPRESSOR)) {
                return true;
            }
        }
        return false;
    }

    public static boolean canUseAcousticEchoCanceler() {
        if (canUseAcousticEchoCanceler == null) {
            canUseAcousticEchoCanceler = new Boolean(isAcousticEchoCancelerSupported() && !WebRtcAudioUtils.useWebRtcBasedAcousticEchoCanceler() && !isAcousticEchoCancelerBlacklisted() && !isAcousticEchoCancelerExcludedByUUID());
            Logging.d(TAG, "canUseAcousticEchoCanceler: " + canUseAcousticEchoCanceler);
        }
        return canUseAcousticEchoCanceler.booleanValue();
    }

    public static boolean canUseAutomaticGainControl() {
        if (canUseAutomaticGainControl == null) {
            canUseAutomaticGainControl = new Boolean(isAutomaticGainControlSupported() && !WebRtcAudioUtils.useWebRtcBasedAutomaticGainControl() && !isAutomaticGainControlBlacklisted() && !isAutomaticGainControlExcludedByUUID());
            Logging.d(TAG, "canUseAutomaticGainControl: " + canUseAutomaticGainControl);
        }
        return canUseAutomaticGainControl.booleanValue();
    }

    public static boolean canUseNoiseSuppressor() {
        if (canUseNoiseSuppressor == null) {
            canUseNoiseSuppressor = new Boolean(isNoiseSuppressorSupported() && !WebRtcAudioUtils.useWebRtcBasedNoiseSuppressor() && !isNoiseSuppressorBlacklisted() && !isNoiseSuppressorExcludedByUUID());
            Logging.d(TAG, "canUseNoiseSuppressor: " + canUseNoiseSuppressor);
        }
        return canUseNoiseSuppressor.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static WebRtcAudioEffects create() {
        if (WebRtcAudioUtils.runningOnJellyBeanOrHigher()) {
            return new WebRtcAudioEffects();
        }
        Logging.w(TAG, "API level 16 or higher is required!");
        return null;
    }

    private WebRtcAudioEffects() {
        Logging.d(TAG, "ctor" + WebRtcAudioUtils.getThreadInfo());
    }

    public boolean setAEC(boolean enable) {
        Logging.d(TAG, "setAEC(" + enable + ")");
        if (!canUseAcousticEchoCanceler()) {
            Logging.w(TAG, "Platform AEC is not supported");
            this.shouldEnableAec = false;
            return false;
        } else if (this.aec == null || enable == this.shouldEnableAec) {
            this.shouldEnableAec = enable;
            return true;
        } else {
            Logging.e(TAG, "Platform AEC state can't be modified while recording");
            return false;
        }
    }

    public boolean setAGC(boolean enable) {
        Logging.d(TAG, "setAGC(" + enable + ")");
        if (!canUseAutomaticGainControl()) {
            Logging.w(TAG, "Platform AGC is not supported");
            this.shouldEnableAgc = false;
            return false;
        } else if (this.agc == null || enable == this.shouldEnableAgc) {
            this.shouldEnableAgc = enable;
            return true;
        } else {
            Logging.e(TAG, "Platform AGC state can't be modified while recording");
            return false;
        }
    }

    public boolean setNS(boolean enable) {
        Logging.d(TAG, "setNS(" + enable + ")");
        if (!canUseNoiseSuppressor()) {
            Logging.w(TAG, "Platform NS is not supported");
            this.shouldEnableNs = false;
            return false;
        } else if (this.ns == null || enable == this.shouldEnableNs) {
            this.shouldEnableNs = enable;
            return true;
        } else {
            Logging.e(TAG, "Platform NS state can't be modified while recording");
            return false;
        }
    }

    public void enable(int audioSession) {
        boolean enable;
        boolean enable2;
        boolean enable3;
        Logging.d(TAG, "enable(audioSession=" + audioSession + ")");
        assertTrue(this.aec == null);
        assertTrue(this.agc == null);
        assertTrue(this.ns == null);
        AudioEffect.Descriptor[] queryEffects = AudioEffect.queryEffects();
        for (AudioEffect.Descriptor d : queryEffects) {
            if (effectTypeIsVoIP(d.type)) {
                Logging.d(TAG, "name: " + d.name + ", mode: " + d.connectMode + ", implementor: " + d.implementor + ", UUID: " + d.uuid);
            }
        }
        if (isAcousticEchoCancelerSupported()) {
            this.aec = AcousticEchoCanceler.create(audioSession);
            if (this.aec != null) {
                boolean enabled = this.aec.getEnabled();
                if (!this.shouldEnableAec || !canUseAcousticEchoCanceler()) {
                    enable3 = false;
                } else {
                    enable3 = true;
                }
                if (this.aec.setEnabled(enable3) != 0) {
                    Logging.e(TAG, "Failed to set the AcousticEchoCanceler state");
                }
                Logging.d(TAG, "AcousticEchoCanceler: was " + (enabled ? "enabled" : "disabled") + ", enable: " + enable3 + ", is now: " + (this.aec.getEnabled() ? "enabled" : "disabled"));
            } else {
                Logging.e(TAG, "Failed to create the AcousticEchoCanceler instance");
            }
        }
        if (isAutomaticGainControlSupported()) {
            this.agc = AutomaticGainControl.create(audioSession);
            if (this.agc != null) {
                boolean enabled2 = this.agc.getEnabled();
                if (!this.shouldEnableAgc || !canUseAutomaticGainControl()) {
                    enable2 = false;
                } else {
                    enable2 = true;
                }
                if (this.agc.setEnabled(enable2) != 0) {
                    Logging.e(TAG, "Failed to set the AutomaticGainControl state");
                }
                Logging.d(TAG, "AutomaticGainControl: was " + (enabled2 ? "enabled" : "disabled") + ", enable: " + enable2 + ", is now: " + (this.agc.getEnabled() ? "enabled" : "disabled"));
            } else {
                Logging.e(TAG, "Failed to create the AutomaticGainControl instance");
            }
        }
        if (isNoiseSuppressorSupported()) {
            this.ns = NoiseSuppressor.create(audioSession);
            if (this.ns != null) {
                boolean enabled3 = this.ns.getEnabled();
                if (!this.shouldEnableNs || !canUseNoiseSuppressor()) {
                    enable = false;
                } else {
                    enable = true;
                }
                if (this.ns.setEnabled(enable) != 0) {
                    Logging.e(TAG, "Failed to set the NoiseSuppressor state");
                }
                Logging.d(TAG, "NoiseSuppressor: was " + (enabled3 ? "enabled" : "disabled") + ", enable: " + enable + ", is now: " + (this.ns.getEnabled() ? "enabled" : "disabled"));
                return;
            }
            Logging.e(TAG, "Failed to create the NoiseSuppressor instance");
        }
    }

    public void release() {
        Logging.d(TAG, "release");
        if (this.aec != null) {
            this.aec.release();
            this.aec = null;
        }
        if (this.agc != null) {
            this.agc.release();
            this.agc = null;
        }
        if (this.ns != null) {
            this.ns.release();
            this.ns = null;
        }
    }

    @TargetApi(18)
    private boolean effectTypeIsVoIP(UUID type) {
        if (!WebRtcAudioUtils.runningOnJellyBeanMR2OrHigher()) {
            return false;
        }
        if ((!AudioEffect.EFFECT_TYPE_AEC.equals(type) || !isAcousticEchoCancelerSupported()) && ((!AudioEffect.EFFECT_TYPE_AGC.equals(type) || !isAutomaticGainControlSupported()) && (!AudioEffect.EFFECT_TYPE_NS.equals(type) || !isNoiseSuppressorSupported()))) {
            return false;
        }
        return true;
    }

    private static void assertTrue(boolean condition) {
        if (!condition) {
            throw new AssertionError("Expected condition to be true");
        }
    }
}
