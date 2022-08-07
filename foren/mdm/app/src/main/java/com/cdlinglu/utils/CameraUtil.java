package com.cdlinglu.utils;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import com.hy.frame.common.BaseFragment;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.CameraDocument;
import com.hy.frame.util.Constant;
import com.hy.frame.util.FolderUtil;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.vsf2f.f2f.ui.utils.photo.PhotoPickerActivity;
import com.vsf2f.f2f.ui.utils.photo.PhotoPickerIntent;
import com.vsf2f.f2f.ui.utils.photo.SelectModel;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/* loaded from: classes.dex */
public class CameraUtil {
    private static final String URI_CACHE = "CAMERA_URI_CACHE2";
    private static final String URI_IMAGE = "CAMERA_URI_IMAGE2";
    private Activity act;
    private BaseFragment fragment;
    private CameraDealListener listener;

    /* loaded from: classes.dex */
    public interface CameraDealListener {
        void onCameraCutSuccess(String str);

        void onCameraPickSuccess(String str);

        void onCameraTakeSuccess(String str);

        void onFunctionCancel(String str);
    }

    public CameraUtil(Activity act, CameraDealListener listener) {
        this.act = act;
        this.listener = listener;
    }

    public CameraUtil(BaseFragment fragment, CameraDealListener listener) {
        this.fragment = fragment;
        this.listener = listener;
    }

    private Context getContext() {
        return this.act == null ? this.fragment.getContext() : this.act;
    }

    private boolean initPhotoData() {
        String path = FolderUtil.getCachePathCrop();
        if (path == null) {
            MyToast.show(getContext(), "没有SD卡，不能拍照");
            return false;
        }
        AppShare.get(getContext()).putString(URI_IMAGE, path + File.separator + "pic" + System.currentTimeMillis() + ".jpg");
        return true;
    }

    public static Intent getCameraIntent(Context context) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        Uri contentUri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new ContentValues());
        if (contentUri != null) {
            AppShare.get(context).putString(URI_CACHE, contentUri.toString());
            intent.putExtra("output", contentUri);
            intent.putExtra("autofocus", true);
            intent.putExtra("fullScreen", false);
            intent.putExtra("showActionIcons", false);
        }
        return intent;
    }

    public void onDlgCameraClick() {
        if (initPhotoData() && PermissionUtil.getCameraPermissions(this.act, 111)) {
            try {
                startActivityForResult(getCameraIntent(getContext()), Constant.FLAG_UPLOAD_TAKE_PICTURE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onDlgPhotoClick() {
        if (initPhotoData()) {
            PhotoPickerIntent intent = new PhotoPickerIntent(this.act);
            intent.setSelectModel(SelectModel.SINGLE);
            intent.setShowCamera(false);
            startActivityForResult(intent, 82);
        }
    }

    public void cropImageUri(int aspectX, int aspectY, int unit) {
        cropImageUri(getCacheUri(getContext()), aspectX, aspectY, unit);
    }

    public void cropImageUri(String path, int aspectX, int aspectY, int unit) {
        cropImageUri(getImageUri(path), aspectX, aspectY, unit);
    }

    private void cropImageUri(Uri uri, int aspectX, int aspectY, int unit) {
        Uri imageUri = getImageUri();
        if (uri == null || imageUri == null) {
            MyLog.e(getClass(), "地址未初始化");
            return;
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(1);
        intent.addFlags(2);
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("scale", true);
        intent.putExtra("scaleUpIfNeeded", true);
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        intent.putExtra("outputX", aspectX * unit);
        intent.putExtra("outputY", aspectY * unit);
        intent.putExtra("return-data", false);
        intent.putExtra("output", imageUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, 13);
    }

    private void startActivityForResult(Intent intent, int requestCode) {
        if (this.act == null) {
            this.fragment.startActivityForResult(intent, requestCode);
        } else {
            this.act.startActivityForResult(intent, requestCode);
        }
    }

    public static Uri getCacheUri(Context context) {
        return Uri.parse(AppShare.get(context).getString(URI_CACHE));
    }

    public Uri getImageUri() {
        return Uri.fromFile(new File(AppShare.get(getContext()).getString(URI_IMAGE)));
    }

    public Uri getImageUri(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            return FileProvider.getUriForFile(getContext(), getContext().getPackageName() + ".fileProvider", new File(path));
        }
        return Uri.fromFile(new File(path));
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String path;
        if (resultCode == -1) {
            try {
                switch (requestCode) {
                    case 12:
                        if (data != null && data.getData() != null) {
                            String path2 = CameraDocument.getPath(getContext(), data.getData());
                            File f = new File(path2);
                            if (!f.exists() || f.length() <= 0) {
                                MyLog.e(getClass(), "选择的图片不存在");
                                return;
                            } else if (this.listener != null) {
                                this.listener.onCameraPickSuccess(path2);
                                return;
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    case 13:
                        Uri imageUri = getImageUri();
                        File f2 = new File(imageUri.getPath());
                        if (!f2.exists() || f2.length() <= 0) {
                            if (data == null || data.getData() == null) {
                                MyLog.e(getClass(), "剪切未知情况");
                                return;
                            }
                            MyLog.e(getClass(), "剪切其他情况");
                            String path3 = CameraDocument.getPath(getContext(), data.getData());
                            if (this.listener != null) {
                                this.listener.onCameraCutSuccess(path3);
                                return;
                            }
                            return;
                        } else if (this.listener != null) {
                            this.listener.onCameraCutSuccess(imageUri.getPath());
                            return;
                        } else {
                            return;
                        }
                    case 82:
                        List<String> list = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                        if (HyUtil.isNoEmpty(list)) {
                            String path4 = list.get(0);
                            File f3 = new File(path4);
                            if (!f3.exists() || f3.length() <= 0) {
                                MyLog.e(getClass(), "选择的图片不存在");
                                return;
                            } else if (this.listener != null) {
                                this.listener.onCameraPickSuccess(path4);
                                return;
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    case Constant.FLAG_UPLOAD_TAKE_PICTURE /* 1041 */:
                        if (data == null || data.getData() == null) {
                            path = CameraDocument.getPath(getContext(), getCacheUri(getContext()));
                        } else {
                            path = CameraDocument.getPath(getContext(), data.getData());
                        }
                        File f4 = new File(path);
                        if (!f4.exists() || f4.length() <= 0) {
                            MyLog.e(getClass(), "拍照存储异常");
                            return;
                        } else if (this.listener != null) {
                            this.listener.onCameraTakeSuccess(path);
                            return;
                        } else {
                            return;
                        }
                    default:
                        return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            this.listener.onFunctionCancel(requestCode + "");
        }
    }

    private boolean saveFile(Uri uri, File f) {
        getContext().getContentResolver();
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            Bitmap bmp = BitmapFactory.decodeFile(CameraDocument.getPath(getContext(), uri));
            if (bmp == null || bmp.getWidth() < 1) {
                return false;
            }
            FileOutputStream fOut = new FileOutputStream(f);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
            return true;
        } catch (Exception e) {
            MyLog.e(getClass(), "saveFile Error");
            return false;
        }
    }
}
