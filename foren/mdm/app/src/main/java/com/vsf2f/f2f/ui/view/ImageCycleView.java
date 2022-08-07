package com.vsf2f.f2f.ui.view;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bumptech.glide.Glide;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.util.ImageUtil;
import com.vsf2f.f2f.R;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpHost;

/* loaded from: classes2.dex */
public class ImageCycleView extends LinearLayout {
    float distance;
    private boolean isAuto;
    private boolean isMatch;
    private boolean isSelf;
    private ImageCycleAdapter mAdvAdapter;
    private ViewPager mAdvPager;
    private Context mContext;
    private ViewGroup mGroup;
    public Handler mHandler;
    private int mImageCount;
    private int mImageIndex;
    private Runnable mImageTimerTask;
    private ImageView mImageView;
    private ImageView[] mImageViews;
    private float mScale;
    float startX;

    /* loaded from: classes2.dex */
    public interface ImageCycleViewListener {
        void displayImage(String str, ImageView imageView);

        void onImageClick(int i, View view);

        void turnToEnd(boolean z);
    }

    public ImageCycleView(Context context) {
        super(context);
        this.mAdvPager = null;
        this.mImageView = null;
        this.mImageViews = null;
        this.mImageIndex = 0;
        this.mImageCount = 0;
        this.isSelf = false;
        this.mHandler = new Handler();
        this.mImageTimerTask = new Runnable() { // from class: com.vsf2f.f2f.ui.view.ImageCycleView.1
            @Override // java.lang.Runnable
            public void run() {
                if (ImageCycleView.this.mImageViews == null) {
                    return;
                }
                if (ImageCycleView.this.mAdvPager.getCurrentItem() == ImageCycleView.this.mImageCount - 1) {
                    ImageCycleView.this.mAdvPager.setCurrentItem(0);
                } else {
                    ImageCycleView.this.mAdvPager.setCurrentItem(ImageCycleView.this.mAdvPager.getCurrentItem() + 1);
                }
            }
        };
        this.isMatch = false;
        this.startX = 0.0f;
        this.distance = 0.0f;
    }

    public void setNull() {
        this.mImageViews = null;
        this.mGroup = null;
        this.mAdvPager = null;
        this.mImageView = null;
        this.mAdvAdapter = null;
    }

    public ImageCycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mAdvPager = null;
        this.mImageView = null;
        this.mImageViews = null;
        this.mImageIndex = 0;
        this.mImageCount = 0;
        this.isSelf = false;
        this.mHandler = new Handler();
        this.mImageTimerTask = new Runnable() { // from class: com.vsf2f.f2f.ui.view.ImageCycleView.1
            @Override // java.lang.Runnable
            public void run() {
                if (ImageCycleView.this.mImageViews == null) {
                    return;
                }
                if (ImageCycleView.this.mAdvPager.getCurrentItem() == ImageCycleView.this.mImageCount - 1) {
                    ImageCycleView.this.mAdvPager.setCurrentItem(0);
                } else {
                    ImageCycleView.this.mAdvPager.setCurrentItem(ImageCycleView.this.mAdvPager.getCurrentItem() + 1);
                }
            }
        };
        this.isMatch = false;
        this.startX = 0.0f;
        this.distance = 0.0f;
        this.mContext = context;
        this.mScale = context.getResources().getDisplayMetrics().density;
        LayoutInflater.from(context).inflate(R.layout.ad_cycle_view, this);
        this.mAdvPager = (ViewPager) findViewById(R.id.adv_pager);
        this.mAdvPager.setOnPageChangeListener(new GuidePageChangeListener());
        this.mGroup = (ViewGroup) findViewById(R.id.viewGroup);
    }

    public void setImageResources(List<String> imageUrlList, ImageCycleViewListener imageCycleViewListener) {
        this.mGroup.removeAllViews();
        this.mImageCount = imageUrlList.size();
        this.mImageViews = new ImageView[this.mImageCount];
        int i = 0;
        while (i < this.mImageCount) {
            this.mImageView = new ImageView(this.mContext);
            int imageParams = (int) ((this.mScale * 10.0f) + 0.5f);
            int imagePadding = (int) ((this.mScale * 5.0f) + 0.5f);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(imageParams, imageParams);
            params.leftMargin = 15;
            this.mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            this.mImageView.setLayoutParams(params);
            this.mImageView.setPadding(imagePadding, imagePadding, imagePadding, imagePadding);
            this.mImageViews[i] = this.mImageView;
            if (!this.isSelf) {
                this.mImageViews[i].setEnabled(i == 0);
                if (i == 0) {
                    this.mImageViews[i].setBackgroundResource(R.mipmap.dot_selected);
                } else {
                    this.mImageViews[i].setBackgroundResource(R.mipmap.dot_normal);
                }
            }
            this.mGroup.addView(this.mImageViews[i]);
            i++;
        }
        this.mAdvAdapter = new ImageCycleAdapter(this.mContext.getApplicationContext(), imageUrlList, imageCycleViewListener);
        this.mAdvPager.setAdapter(this.mAdvAdapter);
    }

    public void setStart() {
        this.isAuto = true;
        startImageTimerTask(3000);
    }

    public void setRollPage() {
        this.isAuto = false;
        startImageTimerTask(1000);
    }

    public void setStop() {
        this.isAuto = false;
        stopImageTimerTask();
    }

    public void setCurrentItem(int position) {
        this.mAdvPager.setCurrentItem(position);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startImageTimerTask(int time) {
        stopImageTimerTask();
        this.mHandler.postDelayed(this.mImageTimerTask, time);
    }

    private void stopImageTimerTask() {
        this.mHandler.removeCallbacks(this.mImageTimerTask);
        this.mHandler.removeCallbacksAndMessages(null);
    }

    /* loaded from: classes2.dex */
    private final class GuidePageChangeListener implements ViewPager.OnPageChangeListener {
        private GuidePageChangeListener() {
        }

        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int state) {
            if (state == 0 && ImageCycleView.this.isAuto) {
                ImageCycleView.this.startImageTimerTask(3000);
            }
        }

        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public void onPageSelected(int index) {
            int index2 = index % ImageCycleView.this.mImageViews.length;
            ImageCycleView.this.mImageIndex = index2;
            if (!ImageCycleView.this.isSelf) {
                ImageCycleView.this.mImageViews[index2].setBackgroundResource(R.mipmap.dot_selected);
                for (int i = 0; i < ImageCycleView.this.mImageViews.length; i++) {
                    if (index2 != i) {
                        ImageCycleView.this.mImageViews[i].setBackgroundResource(R.mipmap.dot_normal);
                    }
                }
            }
        }
    }

    public int getmImageCount() {
        return this.mImageCount;
    }

    public void setScaleMatch(boolean isMatch) {
        this.isMatch = isMatch;
    }

    /* loaded from: classes2.dex */
    private class ImageCycleAdapter extends PagerAdapter {
        private List<String> mAdList;
        private ImageCycleViewListener mImageCycleViewListener;
        private ArrayList<ImageView> mImageViewCacheList = new ArrayList<>();

        public ImageCycleAdapter(Context context, List<String> adList, ImageCycleViewListener imageCycleViewListener) {
            this.mAdList = new ArrayList();
            this.mAdList = adList;
            this.mImageCycleViewListener = imageCycleViewListener;
        }

        @Override // android.support.v4.view.PagerAdapter
        public int getCount() {
            return this.mAdList.size();
        }

        @Override // android.support.v4.view.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override // android.support.v4.view.PagerAdapter
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView;
            int absoindex = position % this.mAdList.size();
            String imageUrl = this.mAdList.get(absoindex);
            if (this.mImageViewCacheList.isEmpty()) {
                imageView = new ImageView(container.getContext());
                imageView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
                if (ImageCycleView.this.isMatch) {
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                } else {
                    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                }
            } else {
                imageView = this.mImageViewCacheList.remove(0);
            }
            if (position > 1 && absoindex == 0) {
                this.mImageCycleViewListener.turnToEnd(true);
            }
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.view.ImageCycleView.ImageCycleAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    ImageCycleAdapter.this.mImageCycleViewListener.onImageClick(ImageCycleView.this.mImageIndex, v);
                }
            });
            imageView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            if (ImageCycleView.this.isMatch) {
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }
            container.addView(imageView);
            if (imageUrl == null) {
                imageView.setImageResource(R.drawable.img_empty);
            } else if (imageUrl.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
                ComUtil.displayImage(ImageCycleView.this.getContext(), imageView, imageUrl);
            } else if (imageUrl.equals("img_banner_demand")) {
                Glide.with(ImageCycleView.this.getContext()).load(Integer.valueOf((int) R.drawable.img_banner_demand)).into(imageView);
            } else if (imageUrl.equals("img_banner_service")) {
                Glide.with(ImageCycleView.this.getContext()).load(Integer.valueOf((int) R.drawable.img_banner_service)).into(imageView);
            } else if (imageUrl.equals("img_banner_manor")) {
                Glide.with(ImageCycleView.this.getContext()).load(Integer.valueOf((int) R.drawable.img_banner_manor)).into(imageView);
            } else {
                imageView.setImageBitmap(ImageUtil.getSmallBitmap(imageUrl));
            }
            return imageView;
        }

        @Override // android.support.v4.view.PagerAdapter
        public void destroyItem(ViewGroup container, int position, Object object) {
            ImageView view = (ImageView) object;
            ImageCycleView.this.mAdvPager.removeView(view);
            this.mImageViewCacheList.add(view);
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == 0) {
            this.distance = 0.0f;
            this.startX = ev.getRawX();
        }
        if (ev.getAction() == 2) {
            this.distance = 0.0f;
            this.distance = ev.getRawX() - this.startX;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
