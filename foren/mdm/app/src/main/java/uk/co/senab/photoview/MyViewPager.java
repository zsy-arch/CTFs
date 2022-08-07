package uk.co.senab.photoview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.hy.frame.util.MyLog;

/* loaded from: classes2.dex */
public class MyViewPager extends ViewPager {
    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // android.support.v4.view.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (ArrayIndexOutOfBoundsException e) {
            MyLog.e("image viewpager error2");
            return false;
        } catch (IllegalArgumentException e2) {
            MyLog.e("image viewpager error1");
            return false;
        }
    }
}
