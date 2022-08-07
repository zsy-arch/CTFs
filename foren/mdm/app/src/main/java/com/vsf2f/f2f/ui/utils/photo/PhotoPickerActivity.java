package com.vsf2f.f2f.ui.utils.photo;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.internal.view.SupportMenu;
import android.support.v7.widget.ListPopupWindow;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.CameraUtil;
import com.cdlinglu.utils.PermissionUtil;
import com.hy.frame.util.CameraDocument;
import com.hy.frame.util.Constant;
import com.hy.frame.util.MyToast;
import com.vsf2f.f2f.R;
import com.yolanda.nohttp.db.Field;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class PhotoPickerActivity extends BaseActivity {
    public static final int DEFAULT_MAX_TOTAL = 9;
    public static final String EXTRA_DEFAULT_SELECTED_LIST = "default_result";
    public static final String EXTRA_IMAGE_CONFIG = "image_config";
    public static final String EXTRA_RESULT = "select_result";
    public static final String EXTRA_SELECT_COUNT = "max_select_count";
    public static final String EXTRA_SELECT_MODE = "select_count_mode";
    public static final String EXTRA_SELECT_MUST = "select_count_must";
    public static final String EXTRA_SHOW_CAMERA = "show_camera";
    public static final String EXTRA_TAKE_CAMERA = "take_camera";
    private static final int LOADER_ALL = 0;
    private static final int LOADER_CATEGORY = 1;
    public static final int MODE_MULTI = 1;
    public static final int MODE_SINGLE = 0;
    public static final int PERMISSION_REQUEST_CODE = 11;
    private Button btnAlbum;
    private Button btnPreview;
    private ImageConfig imageConfig;
    private int mDesireImageCount;
    private FolderAdapter mFolderAdapter;
    private ListPopupWindow mFolderPopupWindow;
    private GridView mGridView;
    private ImageGridAdapter mImageAdapter;
    private View mPopupAnchorView;
    private ArrayList<String> resultList = new ArrayList<>();
    private ArrayList<Folder> mResultFolder = new ArrayList<>();
    private int selectMode = 0;
    private boolean hasFolderGened = false;
    private boolean mIsShowCamera = false;
    private boolean takeCamera = false;
    private boolean mustSelect = true;
    private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallback = new LoaderManager.LoaderCallbacks<Cursor>() { // from class: com.vsf2f.f2f.ui.utils.photo.PhotoPickerActivity.5
        private final String[] IMAGE_PROJECTION = {"_data", "_display_name", "date_added", Field.ID};

        @Override // android.support.v4.app.LoaderManager.LoaderCallbacks
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            StringBuilder selectionArgs = new StringBuilder();
            if (PhotoPickerActivity.this.imageConfig != null) {
                if (PhotoPickerActivity.this.imageConfig.minWidth != 0) {
                    selectionArgs.append("width >= " + PhotoPickerActivity.this.imageConfig.minWidth);
                }
                if (PhotoPickerActivity.this.imageConfig.minHeight != 0) {
                    selectionArgs.append("".equals(selectionArgs.toString()) ? "" : " and ");
                    selectionArgs.append("height >= " + PhotoPickerActivity.this.imageConfig.minHeight);
                }
                if (((float) PhotoPickerActivity.this.imageConfig.minSize) != 0.0f) {
                    selectionArgs.append("".equals(selectionArgs.toString()) ? "" : " and ");
                    selectionArgs.append("_size >= " + PhotoPickerActivity.this.imageConfig.minSize);
                }
                if (PhotoPickerActivity.this.imageConfig.mimeType != null) {
                    selectionArgs.append(" and (");
                    int len = PhotoPickerActivity.this.imageConfig.mimeType.length;
                    for (int i = 0; i < len; i++) {
                        if (i != 0) {
                            selectionArgs.append(" or ");
                        }
                        selectionArgs.append("mime_type = '" + PhotoPickerActivity.this.imageConfig.mimeType[i] + "'");
                    }
                    selectionArgs.append(")");
                }
            }
            if (id == 0) {
                return new CursorLoader(PhotoPickerActivity.this.context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, this.IMAGE_PROJECTION, selectionArgs.toString(), null, this.IMAGE_PROJECTION[2] + " DESC");
            }
            if (id != 1) {
                return null;
            }
            String selectionStr = selectionArgs.toString();
            if (!"".equals(selectionStr)) {
                selectionStr = selectionStr + " and" + selectionStr;
            }
            return new CursorLoader(PhotoPickerActivity.this.context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, this.IMAGE_PROJECTION, this.IMAGE_PROJECTION[0] + " like '%" + args.getString("path") + "%'" + selectionStr, null, this.IMAGE_PROJECTION[2] + " DESC");
        }

        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            if (data != null) {
                List<Image> images = new ArrayList<>();
                if (data.getCount() > 0) {
                    data.moveToFirst();
                    do {
                        String path = data.getString(data.getColumnIndexOrThrow(this.IMAGE_PROJECTION[0]));
                        Image image = new Image(path, data.getString(data.getColumnIndexOrThrow(this.IMAGE_PROJECTION[1])), data.getLong(data.getColumnIndexOrThrow(this.IMAGE_PROJECTION[2])));
                        images.add(image);
                        if (!PhotoPickerActivity.this.hasFolderGened) {
                            File folderFile = new File(path).getParentFile();
                            Folder folder = new Folder();
                            folder.name = folderFile.getName();
                            folder.path = folderFile.getAbsolutePath();
                            folder.cover = image;
                            if (!PhotoPickerActivity.this.mResultFolder.contains(folder)) {
                                List<Image> imageList = new ArrayList<>();
                                imageList.add(image);
                                folder.images = imageList;
                                PhotoPickerActivity.this.mResultFolder.add(folder);
                            } else {
                                ((Folder) PhotoPickerActivity.this.mResultFolder.get(PhotoPickerActivity.this.mResultFolder.indexOf(folder))).images.add(image);
                            }
                        }
                    } while (data.moveToNext());
                    PhotoPickerActivity.this.mImageAdapter.setData(images);
                    if (PhotoPickerActivity.this.resultList != null && PhotoPickerActivity.this.resultList.size() > 0) {
                        PhotoPickerActivity.this.mImageAdapter.setDefaultSelected(PhotoPickerActivity.this.resultList);
                    }
                    PhotoPickerActivity.this.mFolderAdapter.setData(PhotoPickerActivity.this.mResultFolder);
                    PhotoPickerActivity.this.hasFolderGened = true;
                }
            }
        }

        @Override // android.support.v4.app.LoaderManager.LoaderCallbacks
        public void onLoaderReset(Loader<Cursor> loader) {
        }
    };

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_photopicker;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.image, 0);
        this.mGridView = (GridView) getView(R.id.grid);
        this.mGridView.setNumColumns(getNumColnums());
        this.btnAlbum = (Button) getView(R.id.btnAlbum);
        this.btnPreview = (Button) getView(R.id.btnPreview);
        this.mPopupAnchorView = getView(R.id.photo_picker_footer);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.takeCamera = getIntent().getBooleanExtra(EXTRA_TAKE_CAMERA, false);
        if (this.takeCamera) {
            showCameraAction();
            return;
        }
        this.imageConfig = (ImageConfig) getIntent().getParcelableExtra(EXTRA_IMAGE_CONFIG);
        checkPermissionAndLoadImages();
        this.mDesireImageCount = getIntent().getIntExtra(EXTRA_SELECT_COUNT, 9);
        this.mustSelect = getIntent().getExtras().getBoolean(EXTRA_SELECT_MUST, false);
        this.selectMode = getIntent().getExtras().getInt(EXTRA_SELECT_MODE, 0);
        if (this.selectMode == 1) {
            ArrayList<String> tmp = getIntent().getStringArrayListExtra(EXTRA_DEFAULT_SELECTED_LIST);
            if (tmp != null && tmp.size() > 0) {
                this.resultList.addAll(tmp);
            }
        } else {
            this.btnPreview.setVisibility(4);
        }
        this.mIsShowCamera = getIntent().getBooleanExtra(EXTRA_SHOW_CAMERA, false);
        this.mImageAdapter = new ImageGridAdapter(this.context, this.mIsShowCamera, getItemImageWidth());
        this.mImageAdapter.showSelectIndicator(this.selectMode == 1);
        this.mGridView.setAdapter((ListAdapter) this.mImageAdapter);
        this.mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.vsf2f.f2f.ui.utils.photo.PhotoPickerActivity.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (!PhotoPickerActivity.this.mImageAdapter.isShowCamera() || i != 0) {
                    PhotoPickerActivity.this.selectImageFromGrid((Image) adapterView.getAdapter().getItem(i), PhotoPickerActivity.this.selectMode);
                } else if (PhotoPickerActivity.this.selectMode == 1 && PhotoPickerActivity.this.mDesireImageCount == PhotoPickerActivity.this.resultList.size()) {
                    MyToast.show(PhotoPickerActivity.this.context, PhotoPickerActivity.this.getString(R.string.msg_amount_limit));
                } else {
                    PhotoPickerActivity.this.showCameraAction();
                }
            }
        });
        this.mFolderAdapter = new FolderAdapter(this.context);
        this.btnAlbum.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.utils.photo.PhotoPickerActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (PhotoPickerActivity.this.mFolderPopupWindow == null) {
                    PhotoPickerActivity.this.createPopupFolderList();
                }
                if (PhotoPickerActivity.this.mFolderPopupWindow.isShowing()) {
                    PhotoPickerActivity.this.mFolderPopupWindow.dismiss();
                    return;
                }
                PhotoPickerActivity.this.mFolderPopupWindow.show();
                int index = PhotoPickerActivity.this.mFolderAdapter.getSelectIndex();
                if (index != 0) {
                    index--;
                }
                PhotoPickerActivity.this.mFolderPopupWindow.getListView().setSelection(index);
            }
        });
        this.btnPreview.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.utils.photo.PhotoPickerActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                PhotoPreviewIntent intent = new PhotoPreviewIntent(PhotoPickerActivity.this.context);
                intent.setCurrentItem(0);
                intent.setPhotoPaths(PhotoPickerActivity.this.resultList);
                PhotoPickerActivity.this.startActivityForResult(intent, 99);
            }
        });
        refreshActionStatus();
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
        complete();
    }

    private void loadImageForSDCard() {
        getSupportLoaderManager().initLoader(0, null, this.mLoaderCallback);
    }

    private void checkPermissionAndLoadImages() {
        if (PermissionUtil.getExternalStoragePermissions(this, 11)) {
            loadImageForSDCard();
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode != 11) {
            return;
        }
        if (grantResults.length <= 0 || grantResults[0] != 0) {
            finish();
        } else {
            checkPermissionAndLoadImages();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createPopupFolderList() {
        this.mFolderPopupWindow = new ListPopupWindow(this.context);
        this.mFolderPopupWindow.setBackgroundDrawable(new ColorDrawable(SupportMenu.CATEGORY_MASK));
        this.mFolderPopupWindow.setAdapter(this.mFolderAdapter);
        this.mFolderPopupWindow.setWidth(-1);
        this.mFolderPopupWindow.setContentWidth(-1);
        int folderViewHeight = this.mFolderAdapter.getCount() * (getResources().getDimensionPixelOffset(R.dimen.folder_cover_size) + getResources().getDimensionPixelOffset(R.dimen.folder_padding) + getResources().getDimensionPixelOffset(R.dimen.folder_padding));
        int screenHeigh = getResources().getDisplayMetrics().heightPixels;
        if (folderViewHeight >= screenHeigh) {
            this.mFolderPopupWindow.setHeight(Math.round(screenHeigh * 0.6f));
        } else {
            this.mFolderPopupWindow.setHeight(-2);
        }
        this.mFolderPopupWindow.setAnchorView(this.mPopupAnchorView);
        this.mFolderPopupWindow.setModal(true);
        this.mFolderPopupWindow.setAnimationStyle(2131427514);
        this.mFolderPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.vsf2f.f2f.ui.utils.photo.PhotoPickerActivity.4
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                PhotoPickerActivity.this.mFolderAdapter.setSelectIndex(position);
                new Handler().postDelayed(new Runnable() { // from class: com.vsf2f.f2f.ui.utils.photo.PhotoPickerActivity.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        PhotoPickerActivity.this.mFolderPopupWindow.dismiss();
                        if (position == 0) {
                            PhotoPickerActivity.this.getSupportLoaderManager().restartLoader(0, null, PhotoPickerActivity.this.mLoaderCallback);
                            PhotoPickerActivity.this.btnAlbum.setText(R.string.all_image);
                            PhotoPickerActivity.this.mImageAdapter.setShowCamera(PhotoPickerActivity.this.mIsShowCamera);
                        } else {
                            Folder folder = (Folder) parent.getAdapter().getItem(position);
                            if (folder != null) {
                                PhotoPickerActivity.this.mImageAdapter.setData(folder.images);
                                PhotoPickerActivity.this.btnAlbum.setText(folder.name);
                                if (PhotoPickerActivity.this.resultList != null && PhotoPickerActivity.this.resultList.size() > 0) {
                                    PhotoPickerActivity.this.mImageAdapter.setDefaultSelected(PhotoPickerActivity.this.resultList);
                                }
                            }
                            PhotoPickerActivity.this.mImageAdapter.setShowCamera(false);
                        }
                        PhotoPickerActivity.this.mGridView.smoothScrollToPosition(0);
                    }
                }, 100L);
            }
        });
    }

    public void onSingleImageSelected(String path) {
        Intent data = new Intent();
        this.resultList.add(path);
        data.putStringArrayListExtra(EXTRA_RESULT, this.resultList);
        setResult(-1, data);
        finish();
    }

    public void onImageSelected(String path) {
        if (!this.resultList.contains(path)) {
            this.resultList.add(path);
        }
        refreshActionStatus();
    }

    public void onImageUnselected(String path) {
        if (this.resultList.contains(path)) {
            this.resultList.remove(path);
        }
        refreshActionStatus();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String path;
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case 99:
                    ArrayList<String> pathArr = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
                    if (pathArr != null && pathArr.size() != this.resultList.size()) {
                        this.resultList = pathArr;
                        refreshActionStatus();
                        this.mImageAdapter.setDefaultSelected(this.resultList);
                        return;
                    }
                    return;
                case Constant.FLAG_UPLOAD_TAKE_PICTURE /* 1041 */:
                    if (data == null || data.getData() == null) {
                        path = CameraDocument.getPath(this.context, CameraUtil.getCacheUri(this.context));
                    } else {
                        path = CameraDocument.getPath(this.context, data.getData());
                    }
                    this.resultList.add(path);
                    complete();
                    return;
                default:
                    return;
            }
        }
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        this.mGridView.setNumColumns(getNumColnums());
        this.mImageAdapter.setItemSize(getItemImageWidth());
        if (this.mFolderPopupWindow != null) {
            if (this.mFolderPopupWindow.isShowing()) {
                this.mFolderPopupWindow.dismiss();
            }
            this.mFolderPopupWindow.setHeight(Math.round(getResources().getDisplayMetrics().heightPixels * 0.6f));
        }
        super.onConfigurationChanged(newConfig);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCameraAction() {
        if (PermissionUtil.getCameraPermissions(this, 111)) {
            try {
                startActivityForResult(CameraUtil.getCameraIntent(this.context), Constant.FLAG_UPLOAD_TAKE_PICTURE);
            } catch (Exception e) {
                MyToast.show(this.context, getString(R.string.msg_no_camera));
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void selectImageFromGrid(Image image, int mode) {
        if (image == null) {
            return;
        }
        if (mode == 1) {
            if (this.resultList.contains(image.path)) {
                this.resultList.remove(image.path);
                onImageUnselected(image.path);
            } else if (this.mDesireImageCount == this.resultList.size()) {
                Toast.makeText(this.context, (int) R.string.msg_amount_limit, 0).show();
                return;
            } else {
                this.resultList.add(image.path);
                onImageSelected(image.path);
            }
            this.mImageAdapter.select(image);
        } else if (mode == 0) {
            onSingleImageSelected(image.path);
        }
    }

    private void refreshActionStatus() {
        boolean hasSelected = true;
        if (this.selectMode == 1) {
            setHeaderRightTxt(getString(R.string.done_with_count, new Object[]{Integer.valueOf(this.resultList.size()), Integer.valueOf(this.mDesireImageCount)}));
            if (this.resultList.size() <= 0) {
                hasSelected = false;
            }
            if (this.mustSelect) {
                setHeaderRightEnabled(hasSelected);
            }
            this.btnPreview.setEnabled(hasSelected);
            if (hasSelected) {
                this.btnPreview.setText(getResources().getString(R.string.preview) + "(" + this.resultList.size() + ")");
            } else {
                this.btnPreview.setText(getResources().getString(R.string.preview));
            }
        }
    }

    private int getItemImageWidth() {
        int cols = getNumColnums();
        return (getResources().getDisplayMetrics().widthPixels - ((cols - 1) * getResources().getDimensionPixelOffset(R.dimen.space_size))) / cols;
    }

    private int getNumColnums() {
        int cols = getResources().getDisplayMetrics().widthPixels / getResources().getDisplayMetrics().densityDpi;
        if (cols < 3) {
            return 3;
        }
        return cols;
    }

    private void complete() {
        Intent intent = new Intent();
        intent.putStringArrayListExtra(EXTRA_RESULT, this.resultList);
        setResult(-1, intent);
        finish();
    }

    protected void setMenuBackGround() {
        getLayoutInflater().setFactory(new LayoutInflater.Factory() { // from class: com.vsf2f.f2f.ui.utils.photo.PhotoPickerActivity.6
            @Override // android.view.LayoutInflater.Factory
            public View onCreateView(String name, Context context, AttributeSet attrs) {
                if (name.equalsIgnoreCase("com.android.internal.view.menu.IconMenuItemView")) {
                    try {
                        final View view = PhotoPickerActivity.this.getLayoutInflater().createView(name, null, attrs);
                        new Handler().post(new Runnable() { // from class: com.vsf2f.f2f.ui.utils.photo.PhotoPickerActivity.6.1
                            @Override // java.lang.Runnable
                            public void run() {
                                view.setBackgroundResource(R.drawable.btn_action);
                            }
                        });
                        return view;
                    } catch (InflateException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e2) {
                        e2.printStackTrace();
                    }
                }
                return null;
            }
        });
    }
}
