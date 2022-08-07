package com.easeui.ui;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.bumptech.glide.Glide;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.Photo.util.FileUtils;
import com.easeui.model.EaseImageCache;
import com.easeui.utils.EaseLoadLocalBigImgTask;
import com.easeui.widget.photoview.EasePhotoView;
import com.em.db.DemoDBManager;
import com.em.db.UserDao;
import com.hy.frame.adapter.ViewPagerAdapter;
import com.hy.frame.util.FolderUtil;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.util.ImageUtils;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.ChatImageBean;
import com.vsf2f.f2f.ui.dialog.BigimgMenuDialog;
import com.vsf2f.f2f.ui.utils.area.DateUtils;
import com.yolanda.nohttp.cookie.CookieDisk;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class EaseShowBigImageActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    private Bitmap bitmap;
    private EasePhotoView image;
    private List<ChatImageBean> imageslist;
    private ArrayList<View> imgViews;
    private boolean isDownloaded;
    private boolean isShowVPager;
    private ProgressBar loadLocalPb;
    private String localFilePath;
    private ProgressDialog pd;
    private BigimgMenuDialog picDialog;
    private String remotepath;
    private String secret;
    private Uri uri;
    private ViewPager viewPager;
    private int default_res = R.drawable.ease_default_image;
    private int index = 0;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.ease_activity_show_big_image;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        hideHeader();
        this.image = (EasePhotoView) getViewAndClick(R.id.bigimg_image);
        this.loadLocalPb = (ProgressBar) getView(R.id.bigimg_pbLoad);
        setOnClickListener(R.id.bigimg_btnMenu);
        this.uri = (Uri) getIntent().getParcelableExtra(CookieDisk.URI);
        this.default_res = getIntent().getIntExtra("default_image", R.mipmap.def_head);
        this.remotepath = getIntent().getExtras().getString("remotepath");
        this.localFilePath = getIntent().getExtras().getString("localUrl");
        this.secret = getIntent().getExtras().getString("secret");
        MyLog.d("show big image uri:" + this.uri + " remotepath:" + this.remotepath);
        this.viewPager = (ViewPager) getView(R.id.img_viewpager);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        String username = getIntent().getStringExtra("username");
        String chatname = getIntent().getStringExtra(UserDao.IMAGE_COLUMN_NAME_CHATNAME);
        String msgid = getIntent().getStringExtra(UserDao.IMAGE_COLUMN_NAME_ID);
        this.imgViews = new ArrayList<>();
        Map<String, ChatImageBean> imagesMap = DemoDBManager.getInstance().getImages(username, chatname);
        if (imagesMap == null || imagesMap.size() <= 1 || TextUtils.isEmpty(msgid) || !imagesMap.containsKey(msgid)) {
            this.isShowVPager = false;
            this.image.setImageBitmap(getBitmap());
            this.image.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.easeui.ui.EaseShowBigImageActivity.2
                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View v) {
                    EaseShowBigImageActivity.this.showDlg();
                    return false;
                }
            });
            return;
        }
        this.isShowVPager = true;
        this.viewPager.setVisibility(0);
        this.image.setVisibility(8);
        Object[] keys = imagesMap.keySet().toArray();
        for (int j = 0; j < keys.length; j++) {
            if (keys[j].equals(msgid)) {
                this.index = j;
            }
        }
        this.imageslist = new ArrayList(imagesMap.values());
        for (int i = 0; i < imagesMap.size(); i++) {
            ImageView img = new ImageView(this);
            img.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.easeui.ui.EaseShowBigImageActivity.1
                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View v) {
                    EaseShowBigImageActivity.this.showDlg();
                    return false;
                }
            });
            img.setAdjustViewBounds(true);
            this.imgViews.add(img);
        }
        updateUI();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    public void initViewPager() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        this.viewPager.setAdapter(new ViewPagerAdapter(this.imgViews));
        this.viewPager.addOnPageChangeListener(this);
        this.viewPager.setCurrentItem(this.index, true);
        if (this.index == 0) {
            showVPagerPic();
        }
    }

    private void showVPagerPic() {
        if (this.imageslist.get(this.index).getLocalpath() != null) {
            File file = new File(this.imageslist.get(this.index).getLocalpath());
            if (file.exists()) {
                Glide.with((FragmentActivity) this).load(file).into((ImageView) this.imgViews.get(this.index));
                return;
            }
        }
        getBitmap(this.index, this.imageslist.get(this.index).getHttpurl(), this.imageslist.get(this.index).getLocalpath());
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.bigimg_btnMenu /* 2131755307 */:
                showDlg();
                return;
            case R.id.bigimg_image /* 2131756391 */:
                finish();
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDlg() {
        if (this.picDialog == null) {
            this.picDialog = new BigimgMenuDialog(this.context);
            this.picDialog.init(new BigimgMenuDialog.ConfirmDlgListener() { // from class: com.easeui.ui.EaseShowBigImageActivity.3
                @Override // com.vsf2f.f2f.ui.dialog.BigimgMenuDialog.ConfirmDlgListener
                public void onDlgSavePhotoClick(BigimgMenuDialog dlg) {
                    if (EaseShowBigImageActivity.this.isShowVPager) {
                        EaseShowBigImageActivity.this.saveImageVP();
                    } else {
                        EaseShowBigImageActivity.this.saveImage();
                    }
                }

                @Override // com.vsf2f.f2f.ui.dialog.BigimgMenuDialog.ConfirmDlgListener
                public void onDlgCancelClick(BigimgMenuDialog dlg) {
                    EaseShowBigImageActivity.this.picDialog.dismiss();
                }
            });
        }
        this.picDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveImage() {
        String filePath = FolderUtil.getCachePathImage() + File.separator + DateUtils.getNowDateStr() + ".jpg";
        FileUtils.saveBitmap(this.context, getBitmap(), filePath, true);
        MyToast.show(this.context, "图片成功保存至" + filePath);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveImageVP() {
        String filePath = FolderUtil.getCachePathImage() + File.separator + DateUtils.getNowDateStr() + ".jpg";
        FileUtils.saveBitmap(this.context, BitmapFactory.decodeFile(this.imageslist.get(this.index).getLocalpath()), filePath, true);
        MyToast.show(this.context, "图片成功保存至" + filePath);
    }

    public void getBitmap(int index, String remotepath, String localFilePath) {
        MyLog.d("download remote image");
        Map<String, String> maps = new HashMap<>();
        if (!TextUtils.isEmpty(this.secret)) {
            maps.put("share-secret", this.secret);
        }
        if (!TextUtils.isEmpty(remotepath) && !TextUtils.isEmpty(localFilePath)) {
            downloadImageVP(index, remotepath, localFilePath, maps);
        }
    }

    public Bitmap getBitmap() {
        if (this.bitmap == null) {
            if (this.uri != null && new File(this.uri.getPath()).exists()) {
                MyLog.d("showbigimage file exists. directly show it");
                getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
                this.bitmap = EaseImageCache.getInstance().get(this.uri.getPath());
                if (this.bitmap == null) {
                    EaseLoadLocalBigImgTask task = new EaseLoadLocalBigImgTask(this, this.uri.getPath(), this.image, this.loadLocalPb, ImageUtils.SCALE_IMAGE_WIDTH, ImageUtils.SCALE_IMAGE_HEIGHT);
                    if (Build.VERSION.SDK_INT > 10) {
                        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                    } else {
                        task.execute(new Void[0]);
                    }
                }
            } else if (this.remotepath != null) {
                MyLog.d("download remote image");
                Map<String, String> maps = new HashMap<>();
                if (!TextUtils.isEmpty(this.secret)) {
                    maps.put("share-secret", this.secret);
                }
                downloadImage(this.remotepath, maps);
            }
        }
        return this.bitmap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLoadDlg(boolean isShow) {
        if (this.pd == null && isShow) {
            String str1 = getResources().getString(R.string.download_the_pictures);
            this.pd = new ProgressDialog(this);
            this.pd.setProgressStyle(0);
            this.pd.setCanceledOnTouchOutside(false);
            this.pd.setMessage(str1);
        }
    }

    @SuppressLint({"NewApi"})
    private void downloadImage(String remoteFilePath, Map<String, String> headers) {
        showLoadDlg(true);
        File temp = new File(this.localFilePath);
        final String tempPath = temp.getParent() + "/temp_" + temp.getName();
        EMClient.getInstance().chatManager().downloadFile(remoteFilePath, tempPath, headers, new EMCallBack() { // from class: com.easeui.ui.EaseShowBigImageActivity.4
            @Override // com.hyphenate.EMCallBack
            public void onSuccess() {
                EaseShowBigImageActivity.this.runOnUiThread(new Runnable() { // from class: com.easeui.ui.EaseShowBigImageActivity.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        new File(tempPath).renameTo(new File(EaseShowBigImageActivity.this.localFilePath));
                        DisplayMetrics metrics = new DisplayMetrics();
                        EaseShowBigImageActivity.this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
                        int screenWidth = metrics.widthPixels;
                        int screenHeight = metrics.heightPixels;
                        EaseShowBigImageActivity.this.bitmap = ImageUtils.decodeScaleImage(EaseShowBigImageActivity.this.localFilePath, screenWidth, screenHeight);
                        if (EaseShowBigImageActivity.this.bitmap == null) {
                            EaseShowBigImageActivity.this.image.setImageResource(EaseShowBigImageActivity.this.default_res);
                        } else {
                            EaseShowBigImageActivity.this.image.setImageBitmap(EaseShowBigImageActivity.this.bitmap);
                            EaseImageCache.getInstance().put(EaseShowBigImageActivity.this.localFilePath, EaseShowBigImageActivity.this.bitmap);
                            EaseShowBigImageActivity.this.isDownloaded = true;
                        }
                        if (!EaseShowBigImageActivity.this.isFinishing() && !EaseShowBigImageActivity.this.isDestroyed()) {
                            EaseShowBigImageActivity.this.showLoadDlg(false);
                        }
                    }
                });
            }

            @Override // com.hyphenate.EMCallBack
            public void onError(int error, String msg) {
                MyLog.d("offline file transfer error:" + msg);
                File file = new File(tempPath);
                if (file.exists() && file.isFile()) {
                    file.delete();
                }
                EaseShowBigImageActivity.this.runOnUiThread(new Runnable() { // from class: com.easeui.ui.EaseShowBigImageActivity.4.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (!EaseShowBigImageActivity.this.isFinishing() && !EaseShowBigImageActivity.this.isDestroyed()) {
                            EaseShowBigImageActivity.this.image.setImageResource(EaseShowBigImageActivity.this.default_res);
                            EaseShowBigImageActivity.this.showLoadDlg(false);
                        }
                    }
                });
            }

            @Override // com.hyphenate.EMCallBack
            public void onProgress(int progress, String status) {
                MyLog.d("Progress: " + progress);
                EaseShowBigImageActivity.this.getResources().getString(R.string.download_the_pictures_new);
                EaseShowBigImageActivity.this.runOnUiThread(new Runnable() { // from class: com.easeui.ui.EaseShowBigImageActivity.4.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if (!EaseShowBigImageActivity.this.isFinishing() && !EaseShowBigImageActivity.this.isDestroyed()) {
                            EaseShowBigImageActivity.this.showLoadDlg(true);
                        }
                    }
                });
            }
        });
    }

    @SuppressLint({"NewApi"})
    private void downloadImageVP(final int position, String remoteFilePath, final String localFilePath, Map<String, String> headers) {
        showLoadDlg(true);
        File temp = new File(localFilePath);
        final String tempPath = temp.getParent() + "/temp_" + temp.getName();
        EMClient.getInstance().chatManager().downloadFile(remoteFilePath, tempPath, headers, new EMCallBack() { // from class: com.easeui.ui.EaseShowBigImageActivity.5
            @Override // com.hyphenate.EMCallBack
            public void onSuccess() {
                EaseShowBigImageActivity.this.runOnUiThread(new Runnable() { // from class: com.easeui.ui.EaseShowBigImageActivity.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        new File(tempPath).renameTo(new File(localFilePath));
                        DisplayMetrics metrics = new DisplayMetrics();
                        EaseShowBigImageActivity.this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
                        try {
                            Bitmap bitmap = ImageUtils.decodeScaleImage(localFilePath, metrics.widthPixels, metrics.heightPixels);
                            if (bitmap == null) {
                                ((ImageView) EaseShowBigImageActivity.this.imgViews.get(position)).setImageResource(EaseShowBigImageActivity.this.default_res);
                            } else {
                                ((ImageView) EaseShowBigImageActivity.this.imgViews.get(position)).setImageBitmap(bitmap);
                                EaseImageCache.getInstance().put(localFilePath, bitmap);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (!EaseShowBigImageActivity.this.isFinishing() && !EaseShowBigImageActivity.this.isDestroyed()) {
                            EaseShowBigImageActivity.this.showLoadDlg(false);
                        }
                    }
                });
            }

            @Override // com.hyphenate.EMCallBack
            public void onError(int error, String msg) {
                MyLog.d("offline file transfer error:" + msg);
                File file = new File(tempPath);
                if (file.exists() && file.isFile()) {
                    file.delete();
                }
                EaseShowBigImageActivity.this.runOnUiThread(new Runnable() { // from class: com.easeui.ui.EaseShowBigImageActivity.5.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (!EaseShowBigImageActivity.this.isFinishing() && !EaseShowBigImageActivity.this.isDestroyed()) {
                            EaseShowBigImageActivity.this.image.setImageResource(EaseShowBigImageActivity.this.default_res);
                            EaseShowBigImageActivity.this.showLoadDlg(false);
                        }
                    }
                });
            }

            @Override // com.hyphenate.EMCallBack
            public void onProgress(final int progress, String status) {
                MyLog.d("Progress: " + progress);
                final String str2 = EaseShowBigImageActivity.this.getResources().getString(R.string.download_the_pictures_new);
                EaseShowBigImageActivity.this.runOnUiThread(new Runnable() { // from class: com.easeui.ui.EaseShowBigImageActivity.5.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if (!EaseShowBigImageActivity.this.isFinishing() && !EaseShowBigImageActivity.this.isDestroyed()) {
                            EaseShowBigImageActivity.this.pd.setMessage(str2 + progress + "%");
                        }
                    }
                });
            }
        });
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.isDownloaded) {
            setResult(-1);
        }
        finish();
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int position) {
        this.index = position;
        showVPagerPic();
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int state) {
    }
}
