package com.amap.api.maps;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.amap.api.col.dt;
import com.amap.api.col.hm;
import com.amap.api.col.o;
import com.autonavi.amap.mapcore.interfaces.IAMap;
import com.autonavi.amap.mapcore.interfaces.IMapFragmentDelegate;

/* loaded from: classes.dex */
public class TextureMapView extends FrameLayout {
    private IMapFragmentDelegate a;
    private AMap b;
    private int c;

    public TextureMapView(Context context) {
        super(context);
        this.c = 0;
        getMapFragmentDelegate().setContext(context);
    }

    public TextureMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = 0;
        this.c = attributeSet.getAttributeIntValue(16842972, 0);
        getMapFragmentDelegate().setContext(context);
        getMapFragmentDelegate().setVisibility(this.c);
    }

    public TextureMapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = 0;
        this.c = attributeSet.getAttributeIntValue(16842972, 0);
        getMapFragmentDelegate().setContext(context);
        getMapFragmentDelegate().setVisibility(this.c);
    }

    public TextureMapView(Context context, AMapOptions aMapOptions) {
        super(context);
        this.c = 0;
        getMapFragmentDelegate().setContext(context);
        getMapFragmentDelegate().setOptions(aMapOptions);
    }

    protected IMapFragmentDelegate getMapFragmentDelegate() {
        if (this.a == null) {
            try {
                this.a = (IMapFragmentDelegate) hm.a(getContext(), dt.e(), "com.amap.api.wrapper.MapFragmentDelegateWrapper", o.class, new Class[]{Integer.TYPE}, new Object[]{1});
            } catch (Throwable th) {
            }
            if (this.a == null) {
                this.a = new o(1);
            }
        }
        return this.a;
    }

    public AMap getMap() {
        try {
            IAMap map = getMapFragmentDelegate().getMap();
            if (map == null) {
                return null;
            }
            if (this.b == null) {
                this.b = new AMap(map);
            }
            return this.b;
        } catch (Throwable th) {
            return null;
        }
    }

    public final void onCreate(Bundle bundle) {
        try {
            addView(getMapFragmentDelegate().onCreateView(null, null, bundle), new ViewGroup.LayoutParams(-1, -1));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void onResume() {
        try {
            getMapFragmentDelegate().onResume();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void onPause() {
        try {
            getMapFragmentDelegate().onPause();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void onDestroy() {
        try {
            getMapFragmentDelegate().onDestroy();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void onLowMemory() {
        try {
            getMapFragmentDelegate().onLowMemory();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        try {
            getMapFragmentDelegate().onSaveInstanceState(bundle);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        getMapFragmentDelegate().setVisibility(i);
    }
}
