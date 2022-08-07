package com.autonavi.ae.gmap.gloverlay;

import android.content.Context;
import android.graphics.Bitmap;
import com.autonavi.ae.gmap.gloverlay.GLOverlay;
import com.autonavi.amap.mapcore.interfaces.IAMap;
import java.io.Serializable;
import java.util.List;
import java.util.Vector;

/* loaded from: classes.dex */
public abstract class BaseMapOverlay<T extends GLOverlay, E> implements Serializable {
    private static final long serialVersionUID = 1;
    protected Context mContext;
    protected int mEngineID;
    protected T mGLOverlay;
    protected Vector<E> mItemList;
    protected int mLastFocusedIndex;
    protected IAMap mMapView;

    public abstract void addItem(E e);

    protected abstract void iniGLOverlay();

    public abstract void resumeMarker(Bitmap bitmap);

    public BaseMapOverlay(int i, Context context, IAMap iAMap) {
        this.mItemList = null;
        this.mEngineID = 1;
        this.mEngineID = i;
        this.mContext = context;
        this.mMapView = iAMap;
        this.mItemList = new Vector<>();
        iniGLOverlay();
    }

    public T getGLOverlay() {
        return this.mGLOverlay;
    }

    public void setVisible(boolean z) {
        if (this.mGLOverlay != null) {
            this.mGLOverlay.setVisible(z);
        }
    }

    public boolean isVisible() {
        if (this.mGLOverlay != null) {
            return this.mGLOverlay.isVisible();
        }
        return false;
    }

    public void setClickable(boolean z) {
        if (this.mGLOverlay != null) {
            this.mGLOverlay.setClickable(z);
        }
    }

    public boolean isClickable() {
        if (this.mGLOverlay != null) {
            return this.mGLOverlay.isClickable();
        }
        return false;
    }

    public int getSize() {
        return this.mItemList.size();
    }

    public boolean clear() {
        try {
            this.mItemList.clear();
            clearFocus();
            if (this.mGLOverlay != null) {
                this.mGLOverlay.removeAll();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean removeAll() {
        return clear();
    }

    public void clearFocus() {
        this.mLastFocusedIndex = -1;
        this.mGLOverlay.clearFocus();
    }

    public final E getItem(int i) {
        E e;
        try {
            synchronized (this.mItemList) {
                if (i < 0 || i > this.mItemList.size() - 1) {
                    e = null;
                } else {
                    e = this.mItemList.get(i);
                }
            }
            return e;
        } catch (IndexOutOfBoundsException e2) {
            return null;
        }
    }

    public void removeItem(int i) {
        if (i >= 0) {
            try {
                if (i <= this.mItemList.size() - 1) {
                    if (i == this.mLastFocusedIndex) {
                        this.mLastFocusedIndex = -1;
                        clearFocus();
                    }
                    this.mItemList.remove(i);
                    if (this.mGLOverlay != null) {
                        this.mGLOverlay.removeItem(i);
                    }
                }
            } catch (IndexOutOfBoundsException e) {
            }
        }
    }

    public void removeItem(E e) {
        if (e != null) {
            try {
                synchronized (this.mItemList) {
                    removeItem(this.mItemList.indexOf(e));
                }
            } catch (IndexOutOfBoundsException e2) {
            }
        }
    }

    public List<E> getItems() {
        return this.mItemList;
    }

    protected void finalize() throws Throwable {
        releaseInstance();
        super.finalize();
    }

    public void releaseInstance() {
        if (getGLOverlay() != null) {
            if (this.mMapView != null && this.mMapView.isMaploaded()) {
                this.mMapView.removeGLOverlay(this);
            }
            getGLOverlay().releaseInstance();
        }
    }
}
