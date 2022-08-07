package com.vsf2f.f2f.ui.utils.photo;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.Photo.util.FileUtils;
import com.hy.frame.util.FolderUtil;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.dialog.BigimgMenuDialog;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import com.vsf2f.f2f.ui.utils.area.DateUtils;
import com.vsf2f.f2f.ui.utils.photo.PhotoPagerAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class PhotoPreviewActivity extends BaseActivity implements PhotoPagerAdapter.PhotoViewClickListener {
    public static final String EXTRA_CURRENT_ITEM = "extra_current_item";
    public static final String EXTRA_DELETE = "extra_can_delete";
    public static final String EXTRA_PHOTOS = "extra_photos";
    public static final String EXTRA_RESULT = "preview_result";
    public static final int REQUEST_PREVIEW = 99;
    private PhotoPagerAdapter mPagerAdapter;
    private ViewPagerFixed mViewPager;
    private ArrayList<String> paths;
    private BigimgMenuDialog picDialog;
    private int right_resId = 0;
    private boolean editMode = false;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_image_preview;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.image, 0);
        this.mViewPager = (ViewPagerFixed) getView(R.id.vp_photos);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.paths = new ArrayList<>();
        ArrayList<String> pathArr = getIntent().getStringArrayListExtra(EXTRA_PHOTOS);
        if (pathArr != null) {
            this.paths.addAll(pathArr);
        }
        int currentItem = getIntent().getIntExtra(EXTRA_CURRENT_ITEM, 0);
        this.editMode = getIntent().getBooleanExtra(EXTRA_DELETE, true);
        if (this.editMode) {
            this.right_resId = R.mipmap.ico_remove_white;
        }
        this.mPagerAdapter = new PhotoPagerAdapter(this, this.paths);
        this.mPagerAdapter.setPhotoViewClickListener(this);
        this.mViewPager.setAdapter(this.mPagerAdapter);
        this.mViewPager.setCurrentItem(currentItem);
        this.mViewPager.setOffscreenPageLimit(5);
        this.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.vsf2f.f2f.ui.utils.photo.PhotoPreviewActivity.1
            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                PhotoPreviewActivity.this.updateActionBarTitle();
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int position) {
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int state) {
            }
        });
        updateActionBarTitle();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onLeftClick() {
        onBackPressed();
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        delPic();
    }

    private void showDlg(final String imgUrl) {
        if (this.picDialog == null) {
            this.picDialog = new BigimgMenuDialog(this.context);
            this.picDialog.init(new BigimgMenuDialog.ConfirmDlgListener() { // from class: com.vsf2f.f2f.ui.utils.photo.PhotoPreviewActivity.2
                @Override // com.vsf2f.f2f.ui.dialog.BigimgMenuDialog.ConfirmDlgListener
                public void onDlgSavePhotoClick(BigimgMenuDialog dlg) {
                    PhotoPreviewActivity.this.savePicture(imgUrl);
                }

                @Override // com.vsf2f.f2f.ui.dialog.BigimgMenuDialog.ConfirmDlgListener
                public void onDlgCancelClick(BigimgMenuDialog dlg) {
                    PhotoPreviewActivity.this.picDialog.dismiss();
                }
            });
        }
        this.picDialog.show();
    }

    @Override // com.vsf2f.f2f.ui.utils.photo.PhotoPagerAdapter.PhotoViewClickListener
    public void OnPhotoLongListener(View view, int position) {
        if (!this.editMode) {
            showDlg(this.paths.get(position));
        }
    }

    @Override // com.vsf2f.f2f.ui.utils.photo.PhotoPagerAdapter.PhotoViewClickListener
    public void OnPhotoTapListener(View view, float v, float v1) {
        onBackPressed();
    }

    public void updateActionBarTitle() {
        setTitle(getString(R.string.image_index, new Object[]{Integer.valueOf(this.mViewPager.getCurrentItem() + 1), Integer.valueOf(this.paths.size())}));
        setHeaderRight(this.right_resId);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_RESULT, this.paths);
        setResult(-1, intent);
        finish();
        super.onBackPressed();
    }

    public void savePicture(final String imgUrl) {
        final String filePath = FolderUtil.getCachePathImage() + File.separator + DateUtils.getNowDateStr() + ".jpg";
        MyToast.show(this.context, "保存中...");
        ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.ui.utils.photo.PhotoPreviewActivity.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    InputStream is = new URL(imgUrl).openStream();
                    FileUtils.saveBitmap(PhotoPreviewActivity.this.context, BitmapFactory.decodeStream(is), filePath, true);
                    MyToast.show(PhotoPreviewActivity.this.context, "图片成功保存至" + filePath);
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

    public void delPic() {
        final int index = this.mViewPager.getCurrentItem();
        final String deletedPath = this.paths.get(index);
        Snackbar snackbar = Snackbar.make(getWindow().getDecorView().findViewById(16908290), (int) R.string.deleted_a_photo, 0);
        if (this.paths.size() <= 1) {
            new AlertDialog.Builder(this).setTitle(R.string.confirm_to_delete).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() { // from class: com.vsf2f.f2f.ui.utils.photo.PhotoPreviewActivity.5
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    PhotoPreviewActivity.this.paths.remove(index);
                    PhotoPreviewActivity.this.onBackPressed();
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.vsf2f.f2f.ui.utils.photo.PhotoPreviewActivity.4
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
        } else {
            snackbar.show();
            this.paths.remove(index);
            this.mPagerAdapter.notifyDataSetChanged();
        }
        snackbar.setActionTextColor(getResources().getColor(R.color.white));
        snackbar.setAction(R.string.undo, new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.utils.photo.PhotoPreviewActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PhotoPreviewActivity.this.paths.size() > 0) {
                    PhotoPreviewActivity.this.paths.add(index, deletedPath);
                } else {
                    PhotoPreviewActivity.this.paths.add(deletedPath);
                }
                PhotoPreviewActivity.this.mPagerAdapter.notifyDataSetChanged();
                PhotoPreviewActivity.this.mViewPager.setCurrentItem(index, true);
            }
        });
    }
}
