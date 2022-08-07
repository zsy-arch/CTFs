package com.loc;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.hyphenate.util.EMPrivateConstant;

/* compiled from: AMapSensorManager.java */
/* loaded from: classes2.dex */
public final class bz implements SensorEventListener {
    SensorManager a;
    Sensor b;
    Sensor c;
    Sensor d;
    private Context p;
    public boolean e = false;
    public double f = 0.0d;
    public float g = 0.0f;
    private float q = 1013.25f;
    private float r = 0.0f;
    double h = 0.0d;
    double i = 0.0d;
    double j = 0.0d;
    double k = 0.0d;
    double[] l = new double[3];
    volatile double m = 0.0d;
    long n = 0;
    long o = 0;

    public bz(Context context) {
        this.p = null;
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        try {
            this.p = context;
            if (this.a == null) {
                this.a = (SensorManager) this.p.getSystemService("sensor");
            }
            try {
                this.b = this.a.getDefaultSensor(6);
            } catch (Throwable th) {
            }
            try {
                this.c = this.a.getDefaultSensor(11);
            } catch (Throwable th2) {
            }
            try {
                this.d = this.a.getDefaultSensor(1);
            } catch (Throwable th3) {
            }
        } catch (Throwable th4) {
            f.a(th4, "AMapSensorManager", "<init>");
        }
    }

    public final double a(double d) {
        return this.f + d;
    }

    public final void a() {
        if (this.a != null && !this.e) {
            this.e = true;
            try {
                if (this.b != null) {
                    this.a.registerListener(this, this.b, 3);
                }
            } catch (Throwable th) {
                f.a(th, "AMapSensorManager", "registerListener mPressure");
            }
            try {
                if (this.c != null) {
                    this.a.registerListener(this, this.c, 3);
                }
            } catch (Throwable th2) {
                f.a(th2, "AMapSensorManager", "registerListener mRotationVector");
            }
            try {
                if (this.d != null) {
                    this.a.registerListener(this, this.d, 1);
                }
            } catch (Throwable th3) {
                f.a(th3, "AMapSensorManager", "registerListener mAcceleroMeterVector");
            }
        }
    }

    public final void a(float f) {
        if (f <= 0.0f) {
            this.q = 1013.25f;
        } else {
            this.q = f;
        }
    }

    public final float b() {
        return this.g;
    }

    public final void c() {
        if (this.a != null && this.e) {
            this.e = false;
            try {
                if (this.b != null) {
                    this.a.unregisterListener(this, this.b);
                }
            } catch (Throwable th) {
            }
            try {
                if (this.c != null) {
                    this.a.unregisterListener(this, this.c);
                }
            } catch (Throwable th2) {
            }
            try {
                if (this.d != null) {
                    this.a.unregisterListener(this, this.d);
                }
            } catch (Throwable th3) {
            }
        }
    }

    public final float d() {
        return this.r;
    }

    public final double e() {
        return this.k;
    }

    public final void f() {
        try {
            c();
            this.b = null;
            this.c = null;
            this.a = null;
            this.d = null;
            this.e = false;
        } catch (Throwable th) {
            f.a(th, "AMapSensorManager", EMPrivateConstant.EMMultiUserConstant.ITEM_DESTROY);
        }
    }

    @Override // android.hardware.SensorEventListener
    public final void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // android.hardware.SensorEventListener
    public final void onSensorChanged(SensorEvent sensorEvent) {
        float[] fArr;
        if (sensorEvent != null) {
            switch (sensorEvent.sensor.getType()) {
                case 1:
                    try {
                        if (this.d != null) {
                            float[] fArr2 = (float[]) sensorEvent.values.clone();
                            this.l[0] = (this.l[0] * 0.800000011920929d) + (fArr2[0] * 0.19999999f);
                            this.l[1] = (this.l[1] * 0.800000011920929d) + (fArr2[1] * 0.19999999f);
                            this.l[2] = (this.l[2] * 0.800000011920929d) + (fArr2[2] * 0.19999999f);
                            this.h = fArr2[0] - this.l[0];
                            this.i = fArr2[1] - this.l[1];
                            this.j = fArr2[2] - this.l[2];
                            long currentTimeMillis = System.currentTimeMillis();
                            if (currentTimeMillis - this.n >= 100) {
                                double sqrt = Math.sqrt((this.h * this.h) + (this.i * this.i) + (this.j * this.j));
                                this.o++;
                                this.n = currentTimeMillis;
                                this.m += sqrt;
                                if (this.o >= 30) {
                                    this.k = this.m / this.o;
                                    this.m = 0.0d;
                                    this.o = 0L;
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        return;
                    } catch (Throwable th) {
                        f.a(th, "AMapSensorManager", "accelerometer");
                        return;
                    }
                case 6:
                    try {
                        if (this.b != null) {
                            float[] fArr3 = (float[]) sensorEvent.values.clone();
                            if (fArr3 != null) {
                                this.g = fArr3[0];
                            }
                            if (fArr3 != null) {
                                this.f = cx.a(SensorManager.getAltitude(this.q, fArr3[0]));
                                return;
                            }
                            return;
                        }
                        return;
                    } catch (Throwable th2) {
                        f.a(th2, "AMapSensorManager", "doComputeAltitude");
                        return;
                    }
                case 11:
                    try {
                        if (this.c != null && (fArr = (float[]) sensorEvent.values.clone()) != null) {
                            float[] fArr4 = new float[9];
                            SensorManager.getRotationMatrixFromVector(fArr4, fArr);
                            float[] fArr5 = new float[3];
                            SensorManager.getOrientation(fArr4, fArr5);
                            this.r = (float) Math.toDegrees(fArr5[0]);
                            this.r = (float) Math.floor(this.r > 0.0f ? this.r : this.r + 360.0f);
                            return;
                        }
                        return;
                    } catch (Throwable th3) {
                        f.a(th3, "AMapSensorManager", "doComputeBearing");
                        return;
                    }
                default:
                    return;
            }
        }
    }
}
