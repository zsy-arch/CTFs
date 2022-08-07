package bccsejw.sxexrix.zaswnwt.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.tencent.smtt.sdk.WebView;
import d.a.a.d.f;

/* loaded from: classes.dex */
public class MyWebView extends WebView {
    public long z = 0;

    public MyWebView(Context context) {
        super(context, null, 0, false);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (1 == motionEvent.getAction()) {
            if (this.z > 0 && motionEvent.getEventTime() - this.z <= 300) {
                z = true;
            }
            this.z = motionEvent.getEventTime();
        }
        if (z) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.tencent.smtt.sdk.WebView
    public void loadUrl(String str) {
        super.post(new f(this, str));
    }

    public MyWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0, false);
    }

    public MyWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, false);
    }
}
