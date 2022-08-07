package com.amap.api.col;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;

/* compiled from: NaviSensorHelper.java */
/* loaded from: classes.dex */
public class fc {
    private Context a;
    private Sensor b;
    private SensorManager c;
    private HandlerThread d;
    private a e;
    private Timer f;
    private float g = 0.0f;
    private int h = 0;
    private boolean i = false;
    private SensorEventListener j = new SensorEventListener() { // from class: com.amap.api.col.fc.1
        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            float f = sensorEvent.values[0];
            if (Math.abs(f - fc.this.g) > 3.0f) {
                fc.this.g = f;
                fc.this.i = true;
                Log.i("Sensor", ",lastDirection=" + fc.this.g + ",lastAccuracy=" + fc.this.h);
            }
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i) {
            if (sensor.getType() == 3) {
                fc.this.h = i;
            }
        }
    };

    /* compiled from: NaviSensorHelper.java */
    /* loaded from: classes.dex */
    interface a {
        void a(boolean z, int i, float f);
    }

    public fc(Context context) {
        this.a = context;
        Log.i("Sensor", "NaviSensorHelper()~");
    }

    public void a(a aVar) {
        this.e = aVar;
    }

    public void a() {
        try {
            Log.e("Sensor", "startSensor~");
            this.c = (SensorManager) this.a.getSystemService("sensor");
            this.b = this.c.getDefaultSensor(3);
            this.d = new HandlerThread(getClass().getName() + "_NaviSensorThread");
            this.d.start();
            this.c.registerListener(this.j, this.b, 1, new Handler(this.d.getLooper()));
            if (this.f == null) {
                this.f = new Timer();
                this.f.schedule(new TimerTask() { // from class: com.amap.api.col.fc.2
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        Log.i("Sensor", Thread.currentThread().getName() + ",lastDirection=" + fc.this.g);
                        if (fc.this.e != null) {
                            fc.this.e.a(fc.this.i, fc.this.h, fc.this.g);
                        }
                    }
                }, 0L, 1000L);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void b() {
        try {
            this.a = null;
            this.b = null;
            if (this.c != null) {
                this.c.unregisterListener(this.j);
                this.c = null;
            }
            if (this.d != null) {
                if (Build.VERSION.SDK_INT >= 18) {
                    this.d.quitSafely();
                } else {
                    this.d.quit();
                }
                this.d = null;
            }
            this.e = null;
            if (this.f != null) {
                this.f.cancel();
                this.f = null;
            }
            this.i = false;
            Log.e("Sensor", "stopSensor~");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
