package com.vsf2f.f2f.ui.user;

import android.graphics.BitmapFactory;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.Photo.util.FileUtils;
import com.easeui.widget.photoview.EasePhotoView;
import com.hy.frame.adapter.ViewPagerAdapter;
import com.hy.frame.util.Constant;
import com.hy.frame.util.FolderUtil;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.CircleReadPicListBean;
import com.vsf2f.f2f.ui.dialog.BigimgMenuDialog;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import com.vsf2f.f2f.ui.utils.area.DateUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class EnlargeActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    private ArrayList<View> imgViews;
    private EasePhotoView imgenlarde;
    private int index;
    private ArrayList<CircleReadPicListBean> list;
    private String path;
    private BigimgMenuDialog picDialog;
    private ViewPager viewPager;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_enlarge;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.img_details, 0);
        this.imgenlarde = (EasePhotoView) getViewAndClick(R.id.img_enlarge);
        this.viewPager = (ViewPager) getView(R.id.img_viewpager);
        setOnClickListener(R.id.bigimg_btnMenu);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.list = new ArrayList<>();
        this.imgViews = new ArrayList<>();
        if (getBundle() == null) {
            return;
        }
        if (getBundle().getString(Constant.FLAG) != null) {
            this.path = getBundle().getString(Constant.FLAG);
            this.index = -1;
            this.imgenlarde.setVisibility(0);
            this.imgenlarde.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.vsf2f.f2f.ui.user.EnlargeActivity.1
                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View v) {
                    EnlargeActivity.this.showDlg();
                    return false;
                }
            });
            this.viewPager.setVisibility(8);
            ComUtil.displayImage(this.context, this.imgenlarde, this.path);
        } else if (getBundle().getParcelableArrayList(Constant.FLAG) != null) {
            this.list = getBundle().getParcelableArrayList(Constant.FLAG);
            this.index = getBundle().getInt(Constant.FLAG_ID);
            this.imgenlarde.setVisibility(8);
            this.viewPager.setVisibility(0);
            for (int i = 0; i < this.list.size(); i++) {
                ImageView img = new ImageView(this);
                ComUtil.displayImage(this.context, img, this.list.get(i).getPath());
                img.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.vsf2f.f2f.ui.user.EnlargeActivity.2
                    @Override // android.view.View.OnLongClickListener
                    public boolean onLongClick(View v) {
                        EnlargeActivity.this.showDlg();
                        return false;
                    }
                });
                img.setAdjustViewBounds(true);
                this.imgViews.add(img);
            }
            updateUI();
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        this.viewPager.setAdapter(new ViewPagerAdapter(this.imgViews));
        this.viewPager.addOnPageChangeListener(this);
        this.viewPager.setCurrentItem(this.index, true);
        if (this.list != null) {
            setTitle(getString(R.string.img_details) + "(" + (this.index + 1) + "/" + this.list.size() + ")");
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.img_enlarge /* 2131755305 */:
                finish();
                return;
            case R.id.img_viewpager /* 2131755306 */:
            default:
                return;
            case R.id.bigimg_btnMenu /* 2131755307 */:
                showDlg();
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDlg() {
        if (this.picDialog == null) {
            this.picDialog = new BigimgMenuDialog(this.context);
            this.picDialog.init(new BigimgMenuDialog.ConfirmDlgListener() { // from class: com.vsf2f.f2f.ui.user.EnlargeActivity.3
                @Override // com.vsf2f.f2f.ui.dialog.BigimgMenuDialog.ConfirmDlgListener
                public void onDlgSavePhotoClick(BigimgMenuDialog dlg) {
                    EnlargeActivity.this.savePicture();
                }

                @Override // com.vsf2f.f2f.ui.dialog.BigimgMenuDialog.ConfirmDlgListener
                public void onDlgCancelClick(BigimgMenuDialog dlg) {
                    EnlargeActivity.this.picDialog.dismiss();
                }
            });
        }
        this.picDialog.show();
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int state) {
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int position) {
        this.index = position;
        if (this.list != null) {
            setTitle(getString(R.string.img_details) + "(" + (this.index + 1) + "/" + this.list.size() + ")");
        }
    }

    public void savePicture() {
        final String filePath = FolderUtil.getCachePathImage() + File.separator + DateUtils.getNowDateStr() + ".jpg";
        final String imgUrl = this.index == -1 ? this.path : this.list.get(this.index).getPath();
        MyToast.show(this.context, "...");
        ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.ui.user.EnlargeActivity.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    InputStream is = new URL(imgUrl).openStream();
                    FileUtils.saveBitmap(EnlargeActivity.this.context, BitmapFactory.decodeStream(is), filePath, true);
                    MyToast.show(EnlargeActivity.this.context, "图片成功保存至" + filePath);
                    is.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    MyLog.e("FileNotFoundException=" + e);
                } catch (IOException e2) {
                    e2.printStackTrace();
                    MyLog.e("IOException=" + e2);
                } catch (Exception e3) {
                    e3.printStackTrace();
                    MyLog.e("Exception=" + e3);
                }
            }
        });
    }
}
