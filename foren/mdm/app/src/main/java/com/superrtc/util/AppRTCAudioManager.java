package com.superrtc.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.util.Log;
import com.yolanda.nohttp.Headers;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class AppRTCAudioManager {
    private static /* synthetic */ int[] $SWITCH_TABLE$com$superrtc$util$AppRTCAudioManager$AudioDevice = null;
    private static final String TAG = "AppRTCAudioManager";
    private final Context apprtcContext;
    private AudioManager audioManager;
    private final Runnable onStateChangeListener;
    private AppRTCProximitySensor proximitySensor;
    private AudioDevice selectedAudioDevice;
    private BroadcastReceiver wiredHeadsetReceiver;
    private boolean initialized = false;
    private int savedAudioMode = -2;
    private boolean savedIsSpeakerPhoneOn = false;
    private boolean savedIsMicrophoneMute = false;
    private final AudioDevice defaultAudioDevice = AudioDevice.SPEAKER_PHONE;
    private final Set<AudioDevice> audioDevices = new HashSet();

    /* loaded from: classes2.dex */
    public enum AudioDevice {
        SPEAKER_PHONE,
        WIRED_HEADSET,
        EARPIECE
    }

    static /* synthetic */ int[] $SWITCH_TABLE$com$superrtc$util$AppRTCAudioManager$AudioDevice() {
        int[] iArr = $SWITCH_TABLE$com$superrtc$util$AppRTCAudioManager$AudioDevice;
        if (iArr == null) {
            iArr = new int[AudioDevice.values().length];
            try {
                iArr[AudioDevice.EARPIECE.ordinal()] = 3;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[AudioDevice.SPEAKER_PHONE.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[AudioDevice.WIRED_HEADSET.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            $SWITCH_TABLE$com$superrtc$util$AppRTCAudioManager$AudioDevice = iArr;
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onProximitySensorChangedState() {
        if (this.audioDevices.size() == 2 && this.audioDevices.contains(AudioDevice.EARPIECE) && this.audioDevices.contains(AudioDevice.SPEAKER_PHONE)) {
            if (this.proximitySensor.sensorReportsNearState()) {
                setAudioDevice(AudioDevice.EARPIECE);
            } else {
                setAudioDevice(AudioDevice.SPEAKER_PHONE);
            }
        }
    }

    public static AppRTCAudioManager create(Context context, Runnable deviceStateChangeListener) {
        return new AppRTCAudioManager(context, deviceStateChangeListener);
    }

    private AppRTCAudioManager(Context context, Runnable deviceStateChangeListener) {
        this.proximitySensor = null;
        this.apprtcContext = context;
        this.onStateChangeListener = deviceStateChangeListener;
        this.audioManager = (AudioManager) context.getSystemService("audio");
        this.proximitySensor = AppRTCProximitySensor.create(context, new Runnable() { // from class: com.superrtc.util.AppRTCAudioManager.1
            @Override // java.lang.Runnable
            public void run() {
                AppRTCAudioManager.this.onProximitySensorChangedState();
            }
        });
        AppRTCUtils.logDeviceInfo(TAG);
    }

    public void init() {
        Log.d(TAG, "init");
        if (!this.initialized) {
            this.savedAudioMode = this.audioManager.getMode();
            this.savedIsSpeakerPhoneOn = this.audioManager.isSpeakerphoneOn();
            this.savedIsMicrophoneMute = this.audioManager.isMicrophoneMute();
            this.audioManager.requestAudioFocus(null, 0, 2);
            this.audioManager.setMode(3);
            setMicrophoneMute(false);
            updateAudioDeviceState(hasWiredHeadset());
            registerForWiredHeadsetIntentBroadcast();
            this.initialized = true;
        }
    }

    public void close() {
        Log.d(TAG, Headers.HEAD_VALUE_CONNECTION_CLOSE);
        if (this.initialized) {
            unregisterForWiredHeadsetIntentBroadcast();
            setSpeakerphoneOn(this.savedIsSpeakerPhoneOn);
            setMicrophoneMute(this.savedIsMicrophoneMute);
            this.audioManager.setMode(this.savedAudioMode);
            this.audioManager.abandonAudioFocus(null);
            if (this.proximitySensor != null) {
                this.proximitySensor.stop();
                this.proximitySensor = null;
            }
            this.initialized = false;
        }
    }

    public void setAudioDevice(AudioDevice device) {
        Log.d(TAG, "setAudioDevice(device=" + device + ")");
        AppRTCUtils.assertIsTrue(this.audioDevices.contains(device));
        switch ($SWITCH_TABLE$com$superrtc$util$AppRTCAudioManager$AudioDevice()[device.ordinal()]) {
            case 1:
                setSpeakerphoneOn(true);
                this.selectedAudioDevice = AudioDevice.SPEAKER_PHONE;
                break;
            case 2:
                setSpeakerphoneOn(false);
                this.selectedAudioDevice = AudioDevice.WIRED_HEADSET;
                break;
            case 3:
                setSpeakerphoneOn(false);
                this.selectedAudioDevice = AudioDevice.EARPIECE;
                break;
            default:
                Log.e(TAG, "Invalid audio device selection");
                break;
        }
        onAudioManagerChangedState();
    }

    public Set<AudioDevice> getAudioDevices() {
        return Collections.unmodifiableSet(new HashSet(this.audioDevices));
    }

    public AudioDevice getSelectedAudioDevice() {
        return this.selectedAudioDevice;
    }

    private void registerForWiredHeadsetIntentBroadcast() {
        IntentFilter filter = new IntentFilter("android.intent.action.HEADSET_PLUG");
        this.wiredHeadsetReceiver = new BroadcastReceiver() { // from class: com.superrtc.util.AppRTCAudioManager.2
            private static final int HAS_MIC = 1;
            private static final int HAS_NO_MIC = 0;
            private static final int STATE_PLUGGED = 1;
            private static final int STATE_UNPLUGGED = 0;

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                boolean hasWiredHeadset = true;
                int state = intent.getIntExtra("state", 0);
                Log.d(AppRTCAudioManager.TAG, "BroadcastReceiver.onReceive" + AppRTCUtils.getThreadInfo() + ": a=" + intent.getAction() + ", s=" + (state == 0 ? "unplugged" : "plugged") + ", m=" + (intent.getIntExtra("microphone", 0) == 1 ? "mic" : "no mic") + ", n=" + intent.getStringExtra("name") + ", sb=" + isInitialStickyBroadcast());
                if (state != 1) {
                    hasWiredHeadset = false;
                }
                switch (state) {
                    case 0:
                        AppRTCAudioManager.this.updateAudioDeviceState(hasWiredHeadset);
                        return;
                    case 1:
                        if (AppRTCAudioManager.this.selectedAudioDevice != AudioDevice.WIRED_HEADSET) {
                            AppRTCAudioManager.this.updateAudioDeviceState(hasWiredHeadset);
                            return;
                        }
                        return;
                    default:
                        Log.e(AppRTCAudioManager.TAG, "Invalid state");
                        return;
                }
            }
        };
        this.apprtcContext.registerReceiver(this.wiredHeadsetReceiver, filter);
    }

    private void unregisterForWiredHeadsetIntentBroadcast() {
        this.apprtcContext.unregisterReceiver(this.wiredHeadsetReceiver);
        this.wiredHeadsetReceiver = null;
    }

    private void setSpeakerphoneOn(boolean on) {
        if (this.audioManager.isSpeakerphoneOn() != on) {
            this.audioManager.setSpeakerphoneOn(on);
        }
    }

    private void setMicrophoneMute(boolean on) {
        if (this.audioManager.isMicrophoneMute() != on) {
            this.audioManager.setMicrophoneMute(on);
        }
    }

    private boolean hasEarpiece() {
        return this.apprtcContext.getPackageManager().hasSystemFeature("android.hardware.telephony");
    }

    @Deprecated
    private boolean hasWiredHeadset() {
        return this.audioManager.isWiredHeadsetOn();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAudioDeviceState(boolean hasWiredHeadset) {
        this.audioDevices.clear();
        if (hasWiredHeadset) {
            this.audioDevices.add(AudioDevice.WIRED_HEADSET);
        } else {
            this.audioDevices.add(AudioDevice.SPEAKER_PHONE);
            if (hasEarpiece()) {
                this.audioDevices.add(AudioDevice.EARPIECE);
            }
        }
        Log.d(TAG, "audioDevices: " + this.audioDevices);
        if (hasWiredHeadset) {
            setAudioDevice(AudioDevice.WIRED_HEADSET);
        } else {
            setAudioDevice(this.defaultAudioDevice);
        }
    }

    private void onAudioManagerChangedState() {
        boolean z = true;
        Log.d(TAG, "onAudioManagerChangedState: devices=" + this.audioDevices + ", selected=" + this.selectedAudioDevice);
        if (this.audioDevices.size() == 2) {
            if (!this.audioDevices.contains(AudioDevice.EARPIECE) || !this.audioDevices.contains(AudioDevice.SPEAKER_PHONE)) {
                z = false;
            }
            AppRTCUtils.assertIsTrue(z);
            this.proximitySensor.start();
        } else if (this.audioDevices.size() == 1) {
            this.proximitySensor.stop();
        } else {
            Log.e(TAG, "Invalid device list");
        }
        if (this.onStateChangeListener != null) {
            this.onStateChangeListener.run();
        }
    }
}
