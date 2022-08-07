package com.amap.api.maps.utils.overlay;

import android.view.animation.LinearInterpolator;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.animation.TranslateAnimation;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes.dex */
public class SmoothMoveMarker {
    private TranslateAnimation animation;
    private BitmapDescriptor descriptor;
    private LatLng endPoint;
    private LatLng lastEndPoint;
    private AMap mAMap;
    private MoveListener moveListener;
    private LatLng tempPosition;
    private long duration = 10000;
    private LinkedList<LatLng> points = new LinkedList<>();
    private LinkedList<Double> eachDistance = new LinkedList<>();
    private double totalDistance = 0.0d;
    private double remainDistance = 0.0d;
    private Marker marker = null;
    private int index = 0;
    private boolean useDefaultDescriptor = false;
    private Thread moveThread = null;
    private Timer timer = null;
    boolean exitFlag = false;

    /* loaded from: classes.dex */
    public interface MoveListener {
        void move(double d);
    }

    static /* synthetic */ int access$1008(SmoothMoveMarker smoothMoveMarker) {
        int i = smoothMoveMarker.index;
        smoothMoveMarker.index = i + 1;
        return i;
    }

    public SmoothMoveMarker(AMap aMap) {
        this.mAMap = aMap;
    }

    public void setPoints(List<LatLng> list) {
        synchronized (this) {
            this.points.clear();
            for (LatLng latLng : list) {
                this.points.add(latLng);
            }
            if (list.size() > 1) {
                this.endPoint = list.get(list.size() - 1);
                this.lastEndPoint = list.get(list.size() - 2);
            }
            this.eachDistance.clear();
            this.totalDistance = 0.0d;
            for (int i = 0; i < list.size() - 1; i++) {
                double calculateLineDistance = AMapUtils.calculateLineDistance(list.get(i), list.get(i + 1));
                this.eachDistance.add(Double.valueOf(calculateLineDistance));
                this.totalDistance = calculateLineDistance + this.totalDistance;
            }
            this.remainDistance = this.totalDistance;
            LatLng removeFirst = this.points.removeFirst();
            if (this.marker != null) {
                this.marker.setPosition(removeFirst);
                checkMarkerIcon();
            } else {
                if (this.descriptor == null) {
                    this.useDefaultDescriptor = true;
                }
                this.marker = this.mAMap.addMarker(new MarkerOptions().belowMaskLayer(true).position(removeFirst).icon(this.descriptor).title("").anchor(0.5f, 0.5f));
            }
        }
    }

    private void checkMarkerIcon() {
        if (!this.useDefaultDescriptor) {
            return;
        }
        if (this.descriptor == null) {
            this.useDefaultDescriptor = true;
            return;
        }
        this.marker.setIcon(this.descriptor);
        this.useDefaultDescriptor = false;
    }

    public void setTotalDuration(int i) {
        this.duration = i * 1000;
    }

    public void startSmoothMove() {
        if (this.points.size() >= 1) {
            this.index = 0;
            this.exitFlag = false;
            if (this.moveThread != null) {
                this.moveThread.interrupt();
            }
            this.moveThread = new Thread(new Runnable() { // from class: com.amap.api.maps.utils.overlay.SmoothMoveMarker.1
                @Override // java.lang.Runnable
                public void run() {
                    a();
                }

                /* JADX INFO: Access modifiers changed from: private */
                public void a() {
                    try {
                        if (SmoothMoveMarker.this.points.size() < 1) {
                            if (SmoothMoveMarker.this.moveListener != null) {
                                SmoothMoveMarker.this.moveListener.move(0.0d);
                            }
                            SmoothMoveMarker.this.setEndRotate();
                            return;
                        }
                        double doubleValue = ((Double) SmoothMoveMarker.this.eachDistance.poll()).doubleValue();
                        long j = (long) (SmoothMoveMarker.this.duration * (doubleValue / SmoothMoveMarker.this.totalDistance));
                        SmoothMoveMarker.this.remainDistance -= doubleValue;
                        if (SmoothMoveMarker.this.moveListener != null) {
                            if (SmoothMoveMarker.this.remainDistance < 0.0d) {
                                SmoothMoveMarker.this.remainDistance = 0.0d;
                            }
                            SmoothMoveMarker.this.moveListener.move(SmoothMoveMarker.this.remainDistance);
                        }
                        LatLng position = SmoothMoveMarker.this.marker.getPosition();
                        if (SmoothMoveMarker.this.tempPosition != null && AMapUtils.calculateLineDistance(SmoothMoveMarker.this.tempPosition, position) < 10.0f) {
                            position = (LatLng) SmoothMoveMarker.this.points.poll();
                            SmoothMoveMarker.this.marker.setPosition(position);
                        }
                        SmoothMoveMarker.this.tempPosition = position;
                        LatLng latLng = (LatLng) SmoothMoveMarker.this.points.poll();
                        if (SmoothMoveMarker.this.timer != null) {
                            SmoothMoveMarker.this.timer.cancel();
                        }
                        SmoothMoveMarker.this.timer = new Timer();
                        SmoothMoveMarker.this.timer.schedule(new TimerTask() { // from class: com.amap.api.maps.utils.overlay.SmoothMoveMarker.1.1
                            @Override // java.util.TimerTask, java.lang.Runnable
                            public void run() {
                                try {
                                    if (SmoothMoveMarker.this.points.size() > 0) {
                                        SmoothMoveMarker.access$1008(SmoothMoveMarker.this);
                                        a();
                                        return;
                                    }
                                    if (SmoothMoveMarker.this.moveListener != null) {
                                        SmoothMoveMarker.this.moveListener.move(0.0d);
                                    }
                                    SmoothMoveMarker.this.setEndRotate();
                                } catch (Throwable th) {
                                    th.printStackTrace();
                                }
                            }
                        }, j);
                        if (latLng != null) {
                            SmoothMoveMarker.this.marker.setRotateAngle((360.0f - SmoothMoveMarker.this.getRotate(position, latLng)) + SmoothMoveMarker.this.mAMap.getCameraPosition().bearing);
                            SmoothMoveMarker.this.animation = new TranslateAnimation(latLng);
                            SmoothMoveMarker.this.animation.setInterpolator(new LinearInterpolator());
                            SmoothMoveMarker.this.animation.setDuration(j);
                            if (SmoothMoveMarker.this.exitFlag || Thread.interrupted()) {
                                SmoothMoveMarker.this.marker.setAnimation(null);
                                return;
                            }
                            SmoothMoveMarker.this.marker.setAnimation(SmoothMoveMarker.this.animation);
                            SmoothMoveMarker.this.marker.startAnimation();
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
            this.moveThread.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEndRotate() {
        if (this.marker != null) {
            this.marker.setRotateAngle((360.0f - getRotate(this.lastEndPoint, this.endPoint)) + this.mAMap.getCameraPosition().bearing);
            this.marker.setPosition(this.endPoint);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getRotate(LatLng latLng, LatLng latLng2) {
        if (latLng == null || latLng2 == null) {
            return 0.0f;
        }
        double d = latLng.latitude;
        double d2 = latLng2.latitude;
        return (float) ((Math.atan2(latLng2.longitude - latLng.longitude, d2 - d) / 3.141592653589793d) * 180.0d);
    }

    public void stopMove() {
        this.exitFlag = true;
        if (this.marker != null) {
            this.marker.setAnimation(null);
        }
        if (this.timer != null) {
            this.timer.cancel();
        }
        if (this.moveThread != null) {
            this.moveThread.interrupt();
        }
        this.index = 0;
    }

    public Marker getMarker() {
        return this.marker;
    }

    public LatLng getPosition() {
        if (this.marker == null) {
            return null;
        }
        return this.marker.getPosition();
    }

    public int getIndex() {
        return this.index;
    }

    public void destroy() {
        stopMove();
        if (this.descriptor != null) {
            this.descriptor.recycle();
        }
        if (this.marker != null) {
            this.marker.destroy();
            this.marker = null;
        }
        this.points.clear();
        this.eachDistance.clear();
    }

    public void setDescriptor(BitmapDescriptor bitmapDescriptor) {
        if (this.descriptor != null) {
            this.descriptor.recycle();
        }
        this.descriptor = bitmapDescriptor;
        if (this.marker != null) {
            this.marker.setIcon(bitmapDescriptor);
        }
    }

    public void setRotate(float f) {
        if (this.marker != null) {
            this.marker.setRotateAngle(360.0f - f);
        }
    }

    public void setVisible(boolean z) {
        if (this.marker != null) {
            this.marker.setVisible(z);
        }
    }

    public void setMoveListener(MoveListener moveListener) {
        this.moveListener = moveListener;
    }
}
