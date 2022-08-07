package com.hy.frame.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.hy.frame.R;
import com.hy.frame.adapter.ViewPagerAdapter;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.lidroid.xutils.BitmapUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class MyScrollView extends RelativeLayout implements ViewPager.OnPageChangeListener, Runnable {
    private static final int DEFAULT_INTERVAL = 3000;
    private ViewPagerAdapter adapter;
    private View.OnClickListener clickListener;
    private BitmapUtils fb;
    private boolean isDrag;
    private boolean isOpenAuto;
    private IScrollListener listener;
    private LinearLayout llyPoint;
    private int pointResId;
    private int scrollCount;
    private long timer;
    private ViewPager vPager;
    private List<View> views;

    /* loaded from: classes2.dex */
    public interface IScrollListener {
        void onViewChange(int i, int i2);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyScrollView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        this.vPager = new ViewPager(context);
        this.vPager.setOnPageChangeListener(this);
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(-1, -1);
        rlp.alignWithParent = true;
        rlp.addRule(6);
        addView(this.vPager, rlp);
        this.llyPoint = new LinearLayout(context);
        RelativeLayout.LayoutParams prlp = new RelativeLayout.LayoutParams(-1, getResources().getDimensionPixelSize(R.dimen.gallery_point_height));
        prlp.alignWithParent = true;
        prlp.addRule(8);
        this.llyPoint.setGravity(17);
        this.llyPoint.setPadding(HyUtil.dip2px(context, 2.0f), HyUtil.dip2px(context, 2.0f), HyUtil.dip2px(context, 2.0f), HyUtil.dip2px(context, 2.0f));
        addView(this.llyPoint, prlp);
    }

    public ViewPager getViewPager() {
        return this.vPager;
    }

    public void addImage(int drawId) {
        ImageView img = new ImageView(getContext());
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        img.setImageResource(drawId);
        img.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        img.setOnClickListener(this.clickListener);
        addPage(img);
    }

    public void addImage(String path) {
        if (path != null) {
            if (this.fb == null) {
                this.fb = new BitmapUtils(getContext());
            }
            ImageView img = new ImageView(getContext());
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            img.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            this.fb.display(img, path);
            img.setOnClickListener(this.clickListener);
            addPage(img);
        }
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener l) {
        this.clickListener = l;
    }

    public void addPage(View v) {
        if (this.views == null) {
            this.views = new ArrayList();
        }
        this.views.add(v);
        addPoint();
    }

    public void show() {
        show(this.views);
    }

    public void show(List<View> views) {
        this.views = views;
        if (this.adapter == null) {
            this.adapter = new ViewPagerAdapter(views);
            this.vPager.setAdapter(this.adapter);
            return;
        }
        this.adapter.refresh(views);
    }

    public void setPointResId(int pointResId) {
        this.pointResId = pointResId;
    }

    private void addPoint() {
        RotundityImageView img = new RotundityImageView(getContext());
        int width = HyUtil.dip2px(getContext(), 8.0f);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(width, width);
        if (this.pointResId != 0) {
            img.setBackgroundResource(this.pointResId);
        } else {
            img.setBackgroundResource(R.drawable.btn_circle_selector);
        }
        int padding = HyUtil.dip2px(getContext(), 4.0f);
        llp.setMargins(padding, padding, padding, padding);
        if (this.llyPoint.getChildCount() == 0) {
            img.setSelected(true);
        }
        this.llyPoint.addView(img, llp);
    }

    public void setPointGravity(int gravity) {
        if (this.llyPoint != null) {
            this.llyPoint.setGravity(gravity);
        }
    }

    public void hidePoint() {
        if (this.llyPoint != null) {
            this.llyPoint.setVisibility(8);
        }
    }

    public LinearLayout getLlyPoint() {
        return this.llyPoint;
    }

    private int getCount() {
        if (this.views == null) {
            return 0;
        }
        return this.views.size();
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int state) {
        if (state == 1) {
            this.isDrag = true;
        }
        if (state == 0) {
            this.isDrag = false;
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    public int getPostion() {
        if (this.vPager != null) {
            return this.vPager.getCurrentItem();
        }
        return 0;
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int position) {
        this.isDrag = false;
        int size = this.llyPoint.getChildCount();
        for (int i = 0; i < size; i++) {
            View v = this.llyPoint.getChildAt(i);
            if (position == i) {
                v.setSelected(true);
            } else {
                v.setSelected(false);
            }
        }
        if (this.scrollCount >= 3) {
            this.scrollCount = 2;
        }
        if (this.listener != null) {
            this.listener.onViewChange(this.views.size(), position + 1);
        }
    }

    public void startAuto() {
        startAuto(3000);
    }

    public void startAuto(int interval) {
        if (this.adapter == null) {
            MyLog.e("NO CALLED SHOW!");
        } else if (!this.isOpenAuto) {
            this.isOpenAuto = true;
            this.timer = interval;
            run();
        }
    }

    public void closeAuto() {
        this.isOpenAuto = false;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.scrollCount++;
        postDelayed(this, this.timer);
        if (!this.isDrag && this.scrollCount >= 3 && this.isOpenAuto && this.vPager != null && isShown() && getCount() > 1) {
            int pager = this.vPager.getCurrentItem();
            if (pager < getCount() - 1) {
                this.vPager.setCurrentItem(pager + 1);
            } else {
                this.vPager.setCurrentItem(0);
            }
        }
    }

    public void setListener(IScrollListener listener) {
        this.listener = listener;
    }
}
