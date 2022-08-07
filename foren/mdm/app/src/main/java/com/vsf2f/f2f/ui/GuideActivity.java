package com.vsf2f.f2f.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.em.DemoHelper;
import com.hy.frame.adapter.ViewPagerAdapter;
import com.hy.frame.util.FolderUtil;
import com.umeng.update.UpdateConfig;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.ConfigUtil;
import com.vsf2f.f2f.ui.utils.Constant;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    private static final int[] pics = {R.drawable.img_guide1, R.drawable.img_guide2, R.drawable.img_guide3};
    private final int REQUEST_CODE_PERMISSION = 1008;
    private Button btnOpen;
    private ImageView[] dots;
    private int index;
    private int position;
    private ViewPager vPager;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        getWindow().setFlags(1024, 1024);
        return R.layout.act_guide;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        hideHeader();
        FolderUtil.deleteDir(FolderUtil.getCacheUserCode());
        DemoHelper.getInstance().getServiceTime(this);
        askMultiplePermission();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        List<View> views = new ArrayList<>();
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(-2, -2);
        this.btnOpen = (Button) getViewAndClick(R.id.btn_open_now);
        this.vPager = (ViewPager) getView(R.id.wel_viewpager);
        int[] iArr = pics;
        for (int pic : iArr) {
            ImageView img = new ImageView(this);
            img.setLayoutParams(mParams);
            img.setImageResource(pic);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            views.add(img);
        }
        this.vPager.addOnPageChangeListener(this);
        this.vPager.setAdapter(new ViewPagerAdapter(views));
        initDots();
        updateConfig();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    public void updateConfig() {
        ConfigUtil.updateConfig(this, ComUtil.getVersion(this));
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.btn_open_now /* 2131755355 */:
                toNextPage();
                return;
            default:
                return;
        }
    }

    private void toNextPage() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Constant.FLAG_ACT, Constant.ACT_GUIDE);
        startActivity(intent);
    }

    private void initDots() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll_wel_dot);
        this.dots = new ImageView[pics.length];
        for (int i = 0; i < pics.length; i++) {
            this.dots[i] = new ImageView(this.context);
            this.dots[i].setImageResource(R.drawable.icon_dot);
            this.dots[i].setEnabled(false);
            this.dots[i].setTag(Integer.valueOf(i));
            ll.addView(this.dots[i]);
        }
        this.dots[this.vPager.getCurrentItem()].setEnabled(true);
    }

    private void setCurDot(int position) {
        this.dots[this.index].setEnabled(false);
        this.dots[position].setEnabled(true);
        this.index = position;
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int state) {
        if (state == 0 && this.position == pics.length - 1) {
            this.btnOpen.setVisibility(0);
        } else {
            this.btnOpen.setVisibility(8);
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int position) {
        setCurDot(position);
        this.position = position;
    }

    public void askMultiplePermission() {
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE", UpdateConfig.f, "android.permission.READ_CONTACTS", "android.permission.SEND_SMS", "android.permission.CAMERA", "android.permission.RECORD_AUDIO", "android.permission.ACCESS_COARSE_LOCATION"}, 1008);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    }
}
