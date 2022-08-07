package com.amap.api.services.help;

import android.content.Context;
import com.amap.api.services.a.ao;
import com.amap.api.services.a.av;
import com.amap.api.services.a.cf;
import com.amap.api.services.a.h;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.interfaces.IInputtipsSearch;
import java.util.List;

/* loaded from: classes.dex */
public final class Inputtips {
    private IInputtipsSearch a;

    /* loaded from: classes.dex */
    public interface InputtipsListener {
        void onGetInputtips(List<Tip> list, int i);
    }

    public Inputtips(Context context, InputtipsListener inputtipsListener) {
        this.a = null;
        try {
            this.a = (IInputtipsSearch) cf.a(context, h.a(true), "com.amap.api.services.dynamic.InputtipsSearchWrapper", ao.class, new Class[]{Context.class, InputtipsListener.class}, new Object[]{context, inputtipsListener});
        } catch (av e) {
            e.printStackTrace();
        }
        if (this.a == null) {
            try {
                this.a = new ao(context, inputtipsListener);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public Inputtips(Context context, InputtipsQuery inputtipsQuery) {
        this.a = null;
        try {
            this.a = (IInputtipsSearch) cf.a(context, h.a(true), "com.amap.api.services.dynamic.InputtipsSearchWrapper", ao.class, new Class[]{Context.class, InputtipsQuery.class}, new Object[]{context, inputtipsQuery});
        } catch (av e) {
            e.printStackTrace();
        }
        if (this.a == null) {
            try {
                this.a = new ao(context, inputtipsQuery);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public InputtipsQuery getQuery() {
        if (this.a != null) {
            return this.a.getQuery();
        }
        return null;
    }

    public void setQuery(InputtipsQuery inputtipsQuery) {
        if (this.a != null) {
            this.a.setQuery(inputtipsQuery);
        }
    }

    public void setInputtipsListener(InputtipsListener inputtipsListener) {
        if (this.a != null) {
            this.a.setInputtipsListener(inputtipsListener);
        }
    }

    public void requestInputtipsAsyn() {
        if (this.a != null) {
            this.a.requestInputtipsAsyn();
        }
    }

    public List<Tip> requestInputtips() throws AMapException {
        if (this.a != null) {
            return this.a.requestInputtips();
        }
        return null;
    }

    public void requestInputtips(String str, String str2) throws AMapException {
        if (this.a != null) {
            this.a.requestInputtips(str, str2);
        }
    }

    public void requestInputtips(String str, String str2, String str3) throws AMapException {
        if (this.a != null) {
            this.a.requestInputtips(str, str2, str3);
        }
    }
}
