package com.vsf2f.f2f.ui.circle;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.CameraUtil;
import com.cdlinglu.utils.ComUtil;
import com.em.DemoHelper;
import com.google.gson.Gson;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.KeyValueView;
import com.hy.http.AjaxParams;
import com.hyphenate.chat.MessageEncoder;
import com.litepal.crud.DataSupport;
import com.umeng.analytics.a;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.DBCircleDraftBean;
import com.vsf2f.f2f.bean.ImageBean;
import com.vsf2f.f2f.ui.MainActivity;
import com.vsf2f.f2f.ui.dialog.PictureDialog;
import com.vsf2f.f2f.ui.dialog.SaveDraftDialog;
import com.vsf2f.f2f.ui.user.pwd.LoginActivity;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import com.vsf2f.f2f.ui.utils.listener.UploadPicListener;
import com.vsf2f.f2f.ui.utils.photo.PhotoPickerActivity;
import com.vsf2f.f2f.ui.utils.photo.PhotoPickerIntent;
import com.vsf2f.f2f.ui.utils.photo.SelectModel;
import com.vsf2f.f2f.ui.utils.upload.UploadUtils;
import com.vsf2f.f2f.ui.view.richEditor.RichTextEditor;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class CirclesAddArtActivity extends BaseActivity implements CameraUtil.CameraDealListener, PictureDialog.ConfirmDlgListener {
    private static final int REQUEST_CAMERA_CODE = 100;
    private static final String TAG_THRIFT_FLOW = "TAG_THRIFT_FLOW";
    private String ADDART_TYPE = "3";
    private CameraUtil camera;
    private RichTextEditor editor;
    private KeyValueView kvpublic;
    private PictureDialog pictrueDlg;
    private EditText titleView;
    private String topImageId;
    private String topImagePath;
    private ImageView topImageView;
    private int uploadNum;
    private int uploadTolal;
    private String username;

    static /* synthetic */ int access$108(CirclesAddArtActivity x0) {
        int i = x0.uploadNum;
        x0.uploadNum = i + 1;
        return i;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_circles_addart;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.circle_add_art, R.string.release);
        this.titleView = (EditText) getView(R.id.circles_addart_editTitle);
        this.kvpublic = (KeyValueView) getViewAndClick(R.id.kv_ispublic);
        this.editor = (RichTextEditor) getView(R.id.editor_circles_addart);
        this.topImageView = (ImageView) getViewAndClick(R.id.circles_addart_imgCover);
        this.editor.setLayoutClickListener(new RichTextEditor.LayoutClickListener() { // from class: com.vsf2f.f2f.ui.circle.CirclesAddArtActivity.1
            @Override // com.vsf2f.f2f.ui.view.richEditor.RichTextEditor.LayoutClickListener
            public void layoutClick() {
            }
        });
        setOnClickListener(R.id.ll_addpic_circles_addart);
        RelativeLayout rlyTop = (RelativeLayout) getView(R.id.circles_addart_rlyTop);
        int width = getResources().getDisplayMetrics().widthPixels - (getResources().getDimensionPixelSize(R.dimen.padding_normal) * 2);
        rlyTop.getLayoutParams().width = width;
        rlyTop.getLayoutParams().height = width / 2;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        if (isLogin()) {
            this.username = DemoHelper.getInstance().getCurrentUserName();
            updateUI();
            queryDraftResult();
            return;
        }
        MyToast.show(this.context, (int) R.string.login_hint);
        startAct(LoginActivity.class);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        this.kvpublic.setSelected(AppShare.get(this.context).getBoolean("TAG_THRIFT_FLOW"));
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        boolean z = true;
        switch (view.getId()) {
            case R.id.circles_addart_imgCover /* 2131755220 */:
                showPictureDlg();
                return;
            case R.id.ll_addpic_circles_addart /* 2131755223 */:
                PhotoPickerIntent intent = new PhotoPickerIntent(this.context);
                intent.setSelectModel(SelectModel.SINGLE);
                intent.setShowCamera(true);
                startActivityForResult(intent, 100);
                return;
            case R.id.kv_ispublic /* 2131755228 */:
                KeyValueView keyValueView = this.kvpublic;
                if (this.kvpublic.isSelected()) {
                    z = false;
                }
                keyValueView.setSelected(z);
                this.kvpublic.getTxtKey().setText(this.kvpublic.isSelected() ? R.string.no_public : R.string.is_public);
                AppShare.get(this.context).putBoolean("TAG_THRIFT_FLOW", this.kvpublic.isSelected());
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        final String title = this.titleView.getText().toString().trim();
        final List<RichTextEditor.EditData> sEditorDatas = this.editor.buildEditData();
        if (TextUtils.isEmpty(title)) {
            MyToast.show(this, (int) R.string.toast_publish_title);
        } else if (TextUtils.isEmpty(this.topImagePath)) {
            MyToast.show(this, (int) R.string.toast_publish_cover);
        } else if (sEditorDatas.size() <= 0) {
            MyToast.show(this, (int) R.string.toast_publish_content);
        } else if (sEditorDatas.size() != 1 || !TextUtils.isEmpty(sEditorDatas.get(0).getInputStr())) {
            getClient().showDialogNow(R.string.uploading);
            ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.ui.circle.CirclesAddArtActivity.2
                @Override // java.lang.Runnable
                public void run() {
                    CirclesAddArtActivity.this.postCircle(title, sEditorDatas);
                }
            });
            saveCircleArt();
        } else {
            MyToast.show(this, (int) R.string.toast_publish_content);
        }
    }

    private JSONArray dealEditData(List<RichTextEditor.EditData> sEditorDatas) {
        int imgCount = 0;
        JSONArray imgs = new JSONArray();
        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 0; i < sEditorDatas.size(); i++) {
            Map<String, String> map = new HashMap<>();
            RichTextEditor.EditData data = sEditorDatas.get(i);
            String imagePath = data.getImagePath();
            try {
                if (!TextUtils.isEmpty(imagePath)) {
                    imgCount++;
                    JSONObject img = new JSONObject();
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(imagePath, options);
                    img.put("width", options.outWidth);
                    img.put(MessageEncoder.ATTR_IMG_HEIGHT, options.outHeight);
                    String objectKey = ComUtil.getObjectKey(this.username, imagePath);
                    img.put("path", objectKey);
                    img.put("size", new File(imagePath).length());
                    imgs.put(img);
                    map.put("id", objectKey);
                    map.put("path", imagePath);
                    list.add(map);
                } else {
                    String content = data.getInputStr();
                    if (!TextUtils.isEmpty(content)) {
                        map.put("remark", ComUtil.UTF(content));
                        JSONObject img2 = new JSONObject();
                        img2.put("remark", ComUtil.UTF(content));
                        imgs.put(img2);
                        list.add(map);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        new UploadUtils().UploadArtPicOSS(this, this.username, Constant.CIRCLES_BUCKET, 10, imgCount, list, new UploadPicListener() { // from class: com.vsf2f.f2f.ui.circle.CirclesAddArtActivity.3
            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onSuccess(List<Map<String, String>> list2, List<String> picIds) {
                if (CirclesAddArtActivity.access$108(CirclesAddArtActivity.this) >= 2) {
                    CirclesAddArtActivity.this.finish();
                }
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onFailed() {
                MyLog.e(Integer.valueOf((int) R.string.upload_failed));
                CirclesAddArtActivity.this.publishResult(false);
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onProgress(long currentSize, long totalSize) {
                MyLog.e("onProgress", currentSize + "/" + totalSize);
            }
        });
        return imgs;
    }

    private JSONObject upCoverPic() {
        Map<String, String> maps = new HashMap<>();
        String topImageId = ComUtil.getObjectKey(this.username, this.topImagePath);
        maps.put(topImageId, this.topImagePath);
        new UploadUtils().UploadCirclePic(this, this.username, Constant.CIRCLES_BUCKET, "", maps, new UploadPicListener() { // from class: com.vsf2f.f2f.ui.circle.CirclesAddArtActivity.4
            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onSuccess(List<Map<String, String>> list, List<String> picIds) {
                if (CirclesAddArtActivity.access$108(CirclesAddArtActivity.this) >= 2) {
                    CirclesAddArtActivity.this.finish();
                }
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onFailed() {
                MyToast.show(CirclesAddArtActivity.this.context, (int) R.string.upload_failed);
                CirclesAddArtActivity.this.topImageView.setImageResource(0);
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onProgress(long currentSize, long totalSize) {
                MyLog.e("onProgress", currentSize + "/" + totalSize);
            }
        });
        JSONObject img = new JSONObject();
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(this.topImagePath, options);
            img.put("width", options.outWidth);
            img.put(MessageEncoder.ATTR_IMG_HEIGHT, options.outHeight);
            img.put("path", topImageId);
            img.put("size", new File(this.topImagePath).length());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return img;
    }

    public void postCircle(String title, List<RichTextEditor.EditData> sEditorDatas) {
        try {
            AjaxParams params = new AjaxParams();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("title", ComUtil.UTF(title));
            jsonObject.put("udName", DemoHelper.getInstance().getDeviceModel());
            jsonObject.put("udid", DemoHelper.getInstance().getDeviceUni());
            jsonObject.put("type", this.ADDART_TYPE);
            jsonObject.put("coverPic", upCoverPic());
            jsonObject.put("picList", dealEditData(sEditorDatas));
            getClient().post(R.string.API_CIRCLES_PUBLIC, ComUtil.getF2FApi(this.context, getString(R.string.API_CIRCLES_PUBLIC)), jsonObject.toString(), params, String.class, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void queryDraftResult() {
        List<DBCircleDraftBean> beans = DataSupport.findAll(DBCircleDraftBean.class, new long[0]);
        for (int i = 0; i < beans.size(); i++) {
            if (beans.get(i) != null && this.ADDART_TYPE.equals(beans.get(i).getType()) && this.username.equals(beans.get(i).getUsername())) {
                queryPicResult(beans.get(i));
            }
        }
    }

    private void queryPicResult(DBCircleDraftBean bean) {
        this.titleView.setText(bean.getText_title());
        this.titleView.requestFocus();
        if (!TextUtils.isEmpty(bean.getImg_cover())) {
            this.topImagePath = bean.getImg_cover();
            this.topImageView.setImageURI(Uri.fromFile(new File(this.topImagePath)));
            if (!TextUtils.isEmpty(bean.getImg_cover_id())) {
                this.topImageId = bean.getImg_cover_id();
            } else {
                onCameraCutSuccess(this.topImagePath);
            }
        }
        try {
            JSONArray jsonArray = new JSONArray(bean.getText_content());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = (JSONObject) jsonArray.get(i);
                String img = obj.optString("id");
                if (!TextUtils.isEmpty(img)) {
                    this.editor.insertImage(img);
                } else {
                    String txt = obj.optString("remark");
                    if (!TextUtils.isEmpty(txt)) {
                        this.editor.insertText(txt);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showPictureDlg() {
        if (this.camera == null) {
            this.camera = new CameraUtil(this, this);
        }
        if (this.pictrueDlg == null) {
            this.pictrueDlg = new PictureDialog(this.context);
            this.pictrueDlg.init(this);
        }
        this.pictrueDlg.show();
    }

    @Override // com.cdlinglu.utils.CameraUtil.CameraDealListener
    public void onCameraTakeSuccess(String path) {
        MyLog.e("onCameraTakeSuccess: " + path);
        this.camera.cropImageUri(2, 1, 256);
    }

    @Override // com.cdlinglu.utils.CameraUtil.CameraDealListener
    public void onCameraPickSuccess(String path) {
        MyLog.e("onCameraPickSuccess: " + path);
        this.camera.cropImageUri(path, 2, 1, a.q);
    }

    @Override // com.cdlinglu.utils.CameraUtil.CameraDealListener
    public void onCameraCutSuccess(String path) {
        File file = new File(path);
        this.topImagePath = path;
        this.topImageView.setImageURI(Uri.fromFile(file));
    }

    @Override // com.cdlinglu.utils.CameraUtil.CameraDealListener
    public void onFunctionCancel(String uri) {
    }

    @Override // com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.vsf2f.f2f.ui.dialog.PictureDialog.ConfirmDlgListener
    public void onDlgCameraClick(PictureDialog dlg) {
        if (this.camera != null) {
            this.camera.onDlgCameraClick();
        }
    }

    @Override // com.vsf2f.f2f.ui.dialog.PictureDialog.ConfirmDlgListener
    public void onDlgPhotoClick(PictureDialog dlg) {
        if (this.camera != null) {
            this.camera.onDlgPhotoClick();
        }
    }

    @Override // com.vsf2f.f2f.ui.dialog.PictureDialog.ConfirmDlgListener
    public void onDlgCancelClick(PictureDialog dlg) {
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_CIRCLES_PUBLIC /* 2131296309 */:
                getApp().removeFinish(CirclesAddActivity.class);
                publishResult(true);
                removeDraft();
                return;
            default:
                return;
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
        switch (result.getRequestCode()) {
            case R.string.API_CIRCLES_PUBLIC /* 2131296309 */:
                publishResult(false);
                break;
        }
        finish();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != -1) {
            return;
        }
        if (requestCode == 100) {
            try {
                this.editor.insertImage(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (this.camera != null) {
            this.camera.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent ev) {
        InputMethodManager imm;
        if (ev.getAction() == 0) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev) && (imm = (InputMethodManager) getSystemService("input_method")) != null) {
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
            return super.dispatchTouchEvent(ev);
        } else if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        } else {
            return onTouchEvent(ev);
        }
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v == null || !(v instanceof EditText)) {
            return false;
        }
        int[] leftTop = {0, 0};
        v.getLocationInWindow(leftTop);
        int left = leftTop[0];
        int top = leftTop[1];
        return event.getX() <= ((float) left) || event.getX() >= ((float) (left + v.getWidth())) || event.getY() <= ((float) top) || event.getY() >= ((float) (top + v.getHeight()));
    }

    public void saveCircleArt() {
        DBCircleDraftBean andcommetsyBean = new DBCircleDraftBean();
        andcommetsyBean.setText_title(this.titleView.getText().toString());
        andcommetsyBean.setText_content(editPicResult(this.editor.buildEditData()));
        andcommetsyBean.setUsername(this.username);
        andcommetsyBean.setImg_cover(this.topImagePath);
        andcommetsyBean.setImg_cover_id(this.topImageId);
        andcommetsyBean.setType(this.ADDART_TYPE);
        removeDraft();
        andcommetsyBean.save();
    }

    public void removeDraft() {
        List<DBCircleDraftBean> beans = DataSupport.findAll(DBCircleDraftBean.class, new long[0]);
        for (int i = 0; i < beans.size(); i++) {
            if (beans.get(i) != null && beans.get(i) != null && this.ADDART_TYPE.equals(beans.get(i).getType()) && this.username.equals(beans.get(i).getUsername())) {
                beans.get(i).delete();
            }
        }
    }

    private String editPicResult(List<RichTextEditor.EditData> sEditorDatas) {
        List<ImageBean> list = new ArrayList<>();
        for (int i = 0; i < sEditorDatas.size(); i++) {
            ImageBean imageBean = new ImageBean();
            RichTextEditor.EditData data = sEditorDatas.get(i);
            if (!TextUtils.isEmpty(data.getImagePath())) {
                list.add(imageBean);
            } else {
                String content = data.getInputStr();
                if (!TextUtils.isEmpty(content)) {
                    imageBean.setRemark(content);
                    list.add(imageBean);
                }
            }
        }
        return new Gson().toJson(list);
    }

    public void publishResult(boolean success) {
        getApp().remove(this);
        MyToast.show(getApp(), success ? getString(R.string.toast_publish_success) : "发布失败，请点击重试");
        Intent intent = new Intent(this.context, CircleActivity.class);
        intent.putExtra("publishType", this.ADDART_TYPE);
        intent.putExtra("publishState", success);
        intent.putExtra(com.hy.frame.util.Constant.LAST_ACT, MainActivity.class);
        startActivity(intent);
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onLeftClick() {
        onBackPressed();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        hideSoftKeyboard();
        if (this.topImagePath != null || !TextUtils.isEmpty(this.titleView.getText().toString())) {
            new SaveDraftDialog(this.context, new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.circle.CirclesAddArtActivity.5
                @Override // com.hy.frame.common.BaseDialog.IConfirmListener
                public void onDlgConfirm(BaseDialog dlg, int flag) {
                    if (flag == 1) {
                        CirclesAddArtActivity.this.saveCircleArt();
                    } else {
                        CirclesAddArtActivity.this.removeDraft();
                    }
                    CirclesAddArtActivity.this.finish();
                }
            }).show();
        } else {
            finish();
        }
    }
}
