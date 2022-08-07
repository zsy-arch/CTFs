package com.vsf2f.f2f.ui.circle;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.em.DemoHelper;
import com.em.ui.EditActivity;
import com.em.ui.ImageGridActivity;
import com.hy.frame.adapter.IAdapterListener;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.FolderUtil;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.KeyValueView;
import com.hy.http.AjaxParams;
import com.litepal.crud.DataSupport;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.DBCircleDraftBean;
import com.vsf2f.f2f.ui.dialog.SaveDraftDialog;
import com.vsf2f.f2f.ui.user.pwd.LoginActivity;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.utils.listener.UploadPicListener;
import com.vsf2f.f2f.ui.utils.photo.ImageUtil;
import com.vsf2f.f2f.ui.utils.upload.UploadUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class CirclesAddVideoActivity extends BaseActivity implements IAdapterListener {
    private static final int REQUEST_CODE_SELECT_VIDEO = 11;
    private static final String TAG_THRIFT_FLOW = "TAG_THRIFT_FLOW";
    private String ADDVIDEO_TYPE = "4";
    private EditText editcontent;
    private ImageView imgVideo;
    private KeyValueView kvpublic;
    private String pic;
    private String picPath;
    private String username;
    private String video;
    private String videoPath;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_circles_addvideo;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.circle_add_video, R.string.release);
        if (isNoLogin()) {
            MyToast.show(this.context, (int) R.string.login_hint);
            startAct(LoginActivity.class);
            return;
        }
        this.editcontent = (EditText) getView(R.id.circles_add_txtContent);
        this.kvpublic = (KeyValueView) getViewAndClick(R.id.kv_ispublic);
        this.imgVideo = (ImageView) getViewAndClick(R.id.circles_add_imgVideo);
        this.username = DemoHelper.getInstance().getCurrentUserName();
        this.kvpublic.setSelected(AppShare.get(this.context).getBoolean("TAG_THRIFT_FLOW"));
        this.kvpublic.getTxtKey().setText(this.kvpublic.isSelected() ? R.string.no_public : R.string.is_public);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.kv_ispublic /* 2131755228 */:
                this.kvpublic.setSelected(!this.kvpublic.isSelected());
                this.kvpublic.getTxtKey().setText(this.kvpublic.isSelected() ? R.string.no_public : R.string.is_public);
                AppShare.get(this.context).putBoolean("TAG_THRIFT_FLOW", this.kvpublic.isSelected());
                return;
            case R.id.circles_add_imgVideo /* 2131755234 */:
                startActivityForResult(new Intent(this, ImageGridActivity.class), 11);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case 11:
                    if (data != null) {
                        data.getIntExtra("dur", 0);
                        this.videoPath = data.getStringExtra("path");
                        MediaMetadataRetriever media = new MediaMetadataRetriever();
                        media.setDataSource(this.videoPath);
                        Bitmap bitmap = media.getFrameAtTime();
                        this.imgVideo.setImageBitmap(bitmap);
                        this.picPath = FolderUtil.getCachePathAlbum() + File.separator + System.currentTimeMillis() + ".png";
                        ImageUtil.compressImage(bitmap, this.picPath);
                        ((ImageView) getView(R.id.circles_add_imgPlay)).setVisibility(0);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    @Override // com.hy.frame.adapter.IAdapterListener
    public void onItemClick(int i, Object o, int i1) {
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        String content = this.editcontent.getText().toString();
        if (TextUtils.isEmpty(this.videoPath)) {
            MyToast.show(this.context, "请选择要发布的视频");
        } else if (!new File(this.picPath).exists()) {
            MyToast.show(this.context, "未找到文件，请重试");
        } else if (HyUtil.hasSpecialChar(content)) {
            MyToast.show(this.context, "请勿输入特殊字符");
        } else {
            dealEditData(content);
            getApp().removeFinish(CirclesAddActivity.class);
        }
    }

    private void dealEditData(final String content) {
        getClient().showDialogNow(R.string.uploading);
        List<String> paths = new ArrayList<>();
        paths.add(this.videoPath);
        new UploadUtils().UploadFileGetUrl(this, "0003_video/", this.username, Constant.CIRCLES_BUCKET, 102, paths, new UploadPicListener() { // from class: com.vsf2f.f2f.ui.circle.CirclesAddVideoActivity.1
            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onSuccess(@Nullable List<Map<String, String>> list, @Nullable List<String> picIds) {
                MyLog.e("video = " + picIds);
                CirclesAddVideoActivity.this.video = picIds.get(0);
                if (CirclesAddVideoActivity.this.pic != null) {
                    CirclesAddVideoActivity.this.postPicCircle(content);
                }
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onFailed() {
                MyLog.e("video = fail");
                MyToast.show(CirclesAddVideoActivity.this.context, CirclesAddVideoActivity.this.getString(R.string.upload_failed));
                CirclesAddVideoActivity.this.getClient().dialogDismiss();
                CirclesAddVideoActivity.this.publishResult(false);
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onProgress(long currentSize, long totalSize) {
                MyLog.e("onProgress", currentSize + "/" + totalSize);
            }
        });
        List<String> paths2 = new ArrayList<>();
        paths2.add(this.picPath);
        new UploadUtils().UploadFileGetUrl(this.context, "", this.username, Constant.CIRCLES_BUCKET, 10, paths2, new UploadPicListener() { // from class: com.vsf2f.f2f.ui.circle.CirclesAddVideoActivity.2
            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onSuccess(@Nullable List<Map<String, String>> list, @Nullable List<String> picIds) {
                MyLog.e("pic = " + picIds);
                CirclesAddVideoActivity.this.pic = picIds.get(0);
                if (CirclesAddVideoActivity.this.video != null) {
                    CirclesAddVideoActivity.this.postPicCircle(content);
                }
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onFailed() {
                MyLog.e("pic = fail");
                MyToast.show(CirclesAddVideoActivity.this.context, CirclesAddVideoActivity.this.getString(R.string.upload_failed));
                CirclesAddVideoActivity.this.getClient().dialogDismiss();
                CirclesAddVideoActivity.this.publishResult(false);
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onProgress(long currentSize, long totalSize) {
                MyLog.e("onProgress", currentSize + "/" + totalSize);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postPicCircle(String content) {
        saveCirclePic();
        AjaxParams params = new AjaxParams();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("type", "video");
            jsonObject.put("title", "");
            jsonObject.put(EditActivity.CONTENT, ComUtil.UTF(content));
            jsonObject.put("picUrl", this.pic);
            jsonObject.put("videoUrl", this.video);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put(EditActivity.CONTENT, jsonObject.toString());
        params.put("udName", DemoHelper.getInstance().getDeviceModel());
        params.put("udid", DemoHelper.getInstance().getDeviceUni());
        params.put("type", this.ADDVIDEO_TYPE);
        params.put("categoryId", 0);
        getClient().post(R.string.API_CIRCLES_PUBLIC, ComUtil.getF2FApi(this.context, getString(R.string.API_CIRCLES_PUBLIC)), params, String.class, false);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        super.onRequestSuccess(result);
        publishResult(true);
        finish();
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        MyLog.e(result.getMsg());
        publishResult(false);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void publishResult(boolean success) {
        getApp().remove(this);
        MyToast.show(getApp(), success ? getString(R.string.toast_publish_success) : "发布失败，请点击重试");
        Intent intent = new Intent(this.context, CircleActivity.class);
        intent.putExtra("publishType", this.ADDVIDEO_TYPE);
        intent.putExtra("publishState", success);
        startActivity(intent);
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onLeftClick() {
        onBackPressed();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        hideSoftKeyboard();
        if (!TextUtils.isEmpty(this.editcontent.getText().toString()) || !TextUtils.isEmpty(this.videoPath)) {
            new SaveDraftDialog(this.context, new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.circle.CirclesAddVideoActivity.3
                @Override // com.hy.frame.common.BaseDialog.IConfirmListener
                public void onDlgConfirm(BaseDialog dlg, int flag) {
                    if (flag == 1) {
                        CirclesAddVideoActivity.this.saveCirclePic();
                    } else {
                        CirclesAddVideoActivity.this.queryAllResult();
                    }
                    CirclesAddVideoActivity.this.finish();
                }
            }).show();
        } else {
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveCirclePic() {
        DBCircleDraftBean bean = new DBCircleDraftBean();
        String content = this.editcontent.getText().toString();
        bean.setUsername(this.username);
        bean.setText_content(content);
        bean.setType(this.ADDVIDEO_TYPE);
        queryAllResult();
        Log.e("5200", "查询到图片保存结果" + bean.save());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void queryAllResult() {
        List<DBCircleDraftBean> beans = DataSupport.findAll(DBCircleDraftBean.class, new long[0]);
        for (int i = 0; i < beans.size(); i++) {
            if (beans.get(i) != null && this.ADDVIDEO_TYPE.equals(beans.get(i).getType()) && this.username.equals(beans.get(i).getUsername())) {
                beans.get(i).delete();
            }
        }
    }
}
