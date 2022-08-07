package com.vsf2f.f2f.ui.photo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.cdlinglu.utils.Photo.util.Bimp;
import com.cdlinglu.utils.Photo.util.ImageItem;
import com.cdlinglu.utils.Photo.util.PublicWay;
import com.cdlinglu.utils.Photo.util.Res;
import com.cdlinglu.utils.Photo.zoom.ViewPagerFixed;
import com.em.RedPacketConstant;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.circle.CirclesAddPicActivity;
import java.util.ArrayList;
import java.util.List;
import uk.co.senab.photoview.PhotoView;

/* loaded from: classes2.dex */
public class GalleryActivity extends Activity {
    private MyPageAdapter adapter;
    private Button back_bt;
    private Button del_bt;
    private Intent intent;
    private Context mContext;
    private ViewPagerFixed pager;
    RelativeLayout photo_relativeLayout;
    private int position;
    private TextView positionTextView;
    private Button send_bt;
    private List<ImageItem> tempSelectBitmap;
    private int location = 0;
    private ArrayList<View> listViews = null;
    public List<Bitmap> bmp = new ArrayList();
    public List<String> drr = new ArrayList();
    public List<String> del = new ArrayList();
    private final int num = 9;
    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() { // from class: com.vsf2f.f2f.ui.photo.GalleryActivity.1
        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public void onPageSelected(int arg0) {
            GalleryActivity.this.location = arg0;
        }

        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int arg0) {
        }
    };

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plugin_camera_gallery);
        PublicWay.activityList.add(this);
        this.mContext = this;
        this.back_bt = (Button) findViewById(Res.getWidgetID("gallery_back"));
        this.send_bt = (Button) findViewById(Res.getWidgetID("send_button"));
        this.del_bt = (Button) findViewById(Res.getWidgetID("gallery_del"));
        this.back_bt.setOnClickListener(new BackListener());
        this.send_bt.setOnClickListener(new GallerySendListener());
        this.del_bt.setOnClickListener(new DelListener());
        this.intent = getIntent();
        this.intent.getExtras();
        this.position = Integer.parseInt(this.intent.getStringExtra(RequestParameters.POSITION));
        isShowOkBt();
        this.pager = (ViewPagerFixed) findViewById(Res.getWidgetID("gallery01"));
        this.pager.setOnPageChangeListener(this.pageChangeListener);
        this.adapter = new MyPageAdapter(this.listViews);
        this.pager.setAdapter(this.adapter);
        this.pager.setPageMargin(getResources().getDimensionPixelOffset(Res.getDimenID("margin_normal")));
        this.pager.setCurrentItem(this.intent.getIntExtra(RedPacketConstant.EXTRA_RED_PACKET_ID, 0));
    }

    private void initListViews(Bitmap bm) {
        if (this.listViews == null) {
            this.listViews = new ArrayList<>();
        }
        PhotoView img = new PhotoView(this);
        img.setBackgroundColor(-16777216);
        img.setImageBitmap(bm);
        img.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.listViews.add(img);
    }

    /* loaded from: classes2.dex */
    private class BackListener implements View.OnClickListener {
        private BackListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            GalleryActivity.this.finish();
        }
    }

    /* loaded from: classes2.dex */
    private class DelListener implements View.OnClickListener {
        private DelListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            if (GalleryActivity.this.listViews.size() == 1) {
                GalleryActivity.this.tempSelectBitmap.clear();
                Bimp.max = 0;
                GalleryActivity.this.send_bt.setText(Res.getString("finish") + "(" + GalleryActivity.this.tempSelectBitmap.size() + "/9)");
                GalleryActivity.this.sendBroadcast(new Intent("data.broadcast.action"));
                GalleryActivity.this.finish();
                return;
            }
            GalleryActivity.this.tempSelectBitmap.remove(GalleryActivity.this.location);
            Bimp.max--;
            GalleryActivity.this.pager.removeAllViews();
            GalleryActivity.this.listViews.remove(GalleryActivity.this.location);
            GalleryActivity.this.adapter.setListViews(GalleryActivity.this.listViews);
            GalleryActivity.this.send_bt.setText(Res.getString("finish") + "(" + GalleryActivity.this.tempSelectBitmap.size() + "/9)");
            GalleryActivity.this.adapter.notifyDataSetChanged();
        }
    }

    /* loaded from: classes2.dex */
    private class GallerySendListener implements View.OnClickListener {
        private GallerySendListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            GalleryActivity.this.finish();
            GalleryActivity.this.intent.setClass(GalleryActivity.this.mContext, CirclesAddPicActivity.class);
            GalleryActivity.this.startActivity(GalleryActivity.this.intent);
        }
    }

    public void isShowOkBt() {
        if (this.tempSelectBitmap.size() > 0) {
            this.send_bt.setText(Res.getString("finish") + "(" + this.tempSelectBitmap.size() + "/9)");
            this.send_bt.setPressed(true);
            this.send_bt.setClickable(true);
            this.send_bt.setTextColor(-1);
            return;
        }
        this.send_bt.setPressed(false);
        this.send_bt.setClickable(false);
        this.send_bt.setTextColor(Color.parseColor("#E1E0DE"));
    }

    /* loaded from: classes2.dex */
    class MyPageAdapter extends PagerAdapter {
        private ArrayList<View> listViews;
        private int size;

        public MyPageAdapter(ArrayList<View> listViews) {
            this.listViews = listViews;
            this.size = listViews == null ? 0 : listViews.size();
        }

        public void setListViews(ArrayList<View> listViews) {
            this.listViews = listViews;
            this.size = listViews == null ? 0 : listViews.size();
        }

        @Override // android.support.v4.view.PagerAdapter
        public int getCount() {
            return this.size;
        }

        @Override // android.support.v4.view.PagerAdapter
        public int getItemPosition(Object object) {
            return -2;
        }

        @Override // android.support.v4.view.PagerAdapter
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPagerFixed) arg0).removeView(this.listViews.get(arg1 % this.size));
        }

        @Override // android.support.v4.view.PagerAdapter
        public void finishUpdate(View arg0) {
        }

        @Override // android.support.v4.view.PagerAdapter
        public Object instantiateItem(View arg0, int arg1) {
            try {
                ((ViewPagerFixed) arg0).addView(this.listViews.get(arg1 % this.size), 0);
            } catch (Exception e) {
            }
            return this.listViews.get(arg1 % this.size);
        }

        @Override // android.support.v4.view.PagerAdapter
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
    }
}
