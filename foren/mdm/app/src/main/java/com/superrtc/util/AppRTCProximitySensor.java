package com.superrtc.util;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.util.Log;
import com.superrtc.util.AppRTCUtils;

/* loaded from: classes2.dex */
public class AppRTCProximitySensor implements SensorEventListener {
    private static final String TAG = "AppRTCProximitySensor";
    private final Runnable onSensorStateListener;
    private final SensorManager sensorManager;
    private final AppRTCUtils.NonThreadSafe nonThreadSafe = new AppRTCUtils.NonThreadSafe();
    private Sensor proximitySensor = null;
    private boolean lastStateReportIsNear = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AppRTCProximitySensor create(Context context, Runnable sensorStateListener) {
        return new AppRTCProximitySensor(context, sensorStateListener);
    }

    private AppRTCProximitySensor(Context context, Runnable sensorStateListener) {
        Log.d(TAG, TAG + AppRTCUtils.getThreadInfo());
        this.onSensorStateListener = sensorStateListener;
        this.sensorManager = (SensorManager) context.getSystemService("sensor");
    }

    public boolean start() {
        checkIfCalledOnValidThread();
        Log.d(TAG, "start" + AppRTCUtils.getThreadInfo());
        if (!initDefaultSensor()) {
            return false;
        }
        this.sensorManager.registerListener(this, this.proximitySensor, 3);
        return true;
    }

    public void stop() {
        checkIfCalledOnValidThread();
        Log.d(TAG, "stop" + AppRTCUtils.getThreadInfo());
        if (this.proximitySensor != null) {
            this.sensorManager.unregisterListener(this, this.proximitySensor);
        }
    }

    public boolean sensorReportsNearState() {
        checkIfCalledOnValidThread();
        return this.lastStateReportIsNear;
    }

    @Override // android.hardware.SensorEventListener
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        checkIfCalledOnValidThread();
        AppRTCUtils.assertIsTrue(sensor.getType() == 8);
        if (accuracy == 0) {
            Log.e(TAG, "The values returned by this sensor cannot be trusted");
        }
    }

    @Override // android.hardware.SensorEventListener
    public final void onSensorChanged(SensorEvent event) {
        boolean z;
        checkIfCalledOnValidThread();
        if (event.sensor.getType() == 8) {
            z = true;
        } else {
            z = false;
        }
        AppRTCUtils.assertIsTrue(z);
        if (event.values[0] < this.proximitySensor.getMaximumRange()) {
            Log.d(TAG, "Proximity sensor => NEAR state");
            this.lastStateReportIsNear = true;
        } else {
            Log.d(TAG, "Proximity sensor => FAR state");
            this.lastStateReportIsNear = false;
        }
        if (this.onSensorStateListener != null) {
            this.onSensorStateListener.run();
        }
        Log.d(TAG, "onSensorChanged" + AppRTCUtils.getThreadInfo() + ": accuracy=" + event.accuracy + ", timestamp=" + event.timestamp + ", distance=" + event.values[0]);
    }

    private boolean initDefaultSensor() {
        if (this.proximitySensor != null) {
            return true;
        }
        this.proximitySensor = this.sensorManager.getDefaultSensor(8);
        if (this.proximitySensor == null) {
            return false;
        }
        logProximitySensorInfo();
        return true;
    }

    private void logProximitySensorInfo() {
        if (this.proximitySensor != null) {
            StringBuilder info = new StringBuilder("Proximity sensor: ");
            info.append("name=" + this.proximitySensor.getName());
            info.append(", vendor: " + this.proximitySensor.getVendor());
            info.append(", power: " + this.proximitySensor.getPower());
            info.append(", resolution: " + this.proximitySensor.getResolution());
            info.append(", max range: " + this.proximitySensor.getMaximumRange());
            if (Build.VERSION.SDK_INT >= 9) {
                info.append(", min delay: " + this.proximitySensor.getMinDelay());
            }
            if (Build.VERSION.SDK_INT >= 20) {
                info.append(", type: " + this.proximitySensor.getStringType());
            }
            if (Build.VERSION.SDK_INT >= 21) {
                info.append(", max delay: " + this.proximitySensor.getMaxDelay());
                info.append(", reporting mode: " + this.proximitySensor.getReportingMode());
                info.append(", isWakeUpSensor: " + this.proximitySensor.isWakeUpSensor());
            }
            Log.d(TAG, info.toString());
        }
    }

    private void checkIfCalledOnValidThread() {
        if (!this.nonThreadSafe.calledOnValidThread()) {
            throw new IllegalStateException("Method is not called on valid thread");
        }
    }
}
