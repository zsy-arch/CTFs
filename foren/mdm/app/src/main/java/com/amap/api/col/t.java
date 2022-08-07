package com.amap.api.col;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.WindowManager;
import com.amap.api.maps.model.Marker;

/* compiled from: SensorEventHelper.java */
/* loaded from: classes.dex */
public class t implements SensorEventListener {
    private SensorManager a;
    private Sensor b;
    private float e;
    private Context f;
    private k g;
    private Marker h;
    private long c = 0;
    private final int d = 100;
    private boolean i = true;

    public t(Context context, k kVar) {
        this.f = context.getApplicationContext();
        this.g = kVar;
        try {
            this.a = (SensorManager) context.getSystemService("sensor");
            this.b = this.a.getDefaultSensor(3);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a() {
        if (this.a != null && this.b != null) {
            this.a.registerListener(this, this.b, 3);
        }
    }

    public void b() {
        if (this.a != null && this.b != null) {
            this.a.unregisterListener(this, this.b);
        }
    }

    public void a(Marker marker) {
        this.h = marker;
    }

    public void a(boolean z) {
        this.i = z;
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        try {
            if (System.currentTimeMillis() - this.c >= 100) {
                if (this.g.a() == null || this.g.a().getAnimateionsCount() <= 0) {
                    switch (sensorEvent.sensor.getType()) {
                        case 3:
                            float a = (sensorEvent.values[0] + a(this.f)) % 360.0f;
                            if (a > 180.0f) {
                                a -= 360.0f;
                            } else if (a < -180.0f) {
                                a += 360.0f;
                            }
                            if (Math.abs(this.e - a) >= 3.0f) {
                                if (Float.isNaN(a)) {
                                    a = 0.0f;
                                }
                                this.e = a;
                                if (this.h != null) {
                                    if (this.i) {
                                        this.g.a(z.d(this.e));
                                        this.h.setRotateAngle(-this.e);
                                    } else {
                                        this.h.setRotateAngle(360.0f - this.e);
                                    }
                                }
                                this.c = System.currentTimeMillis();
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static int a(Context context) {
        switch (((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation()) {
            case 0:
                return 0;
            case 1:
                return 90;
            case 2:
                return 180;
            case 3:
                return -90;
            default:
                return 0;
        }
    }
}
