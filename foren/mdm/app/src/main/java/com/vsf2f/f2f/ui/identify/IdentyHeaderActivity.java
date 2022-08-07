package com.vsf2f.f2f.ui.identify;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.CameraUtil;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.PermissionUtil;
import com.em.DemoHelper;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.CameraDocument;
import com.hy.frame.util.Constant;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.RealInfoBean;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;
import com.vsf2f.f2f.ui.utils.listener.UploadPicListener;
import com.vsf2f.f2f.ui.utils.photo.ImageUtil;
import com.vsf2f.f2f.ui.utils.upload.UploadUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpHost;

/* loaded from: classes2.dex */
public class IdentyHeaderActivity extends BaseActivity {
    private AjaxParams ajaxParams;
    private String headerPath;
    private ImageView iv_header;
    private ImageView iv_noedit;
    private RealInfoBean realInfoBean;
    private int type;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_identy_header;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.identy_real, R.drawable.icon_help_white);
        this.iv_header = (ImageView) getViewAndClick(R.id.iv_header);
        this.type = getBundle().getInt("type");
        this.iv_noedit = (ImageView) getViewAndClick(R.id.iv_noedit);
        Button btn_commit = (Button) getViewAndClick(R.id.btn_commit);
        this.ajaxParams = (AjaxParams) getBundle().getSerializable("ajax");
        this.realInfoBean = (RealInfoBean) getBundle().getSerializable("realinfo");
        if (this.realInfoBean != null) {
            Glide.with(this.context).load(this.realInfoBean.getCertifyAvatar() + "").error((int) R.drawable.ease_default_image).into(this.iv_header);
            this.headerPath = this.realInfoBean.getCertifyAvatar();
        }
        if (this.type <= 1) {
            btn_commit.setVisibility(8);
            this.iv_noedit.setVisibility(0);
            return;
        }
        this.iv_noedit.setVisibility(8);
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

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        Bundle bundle = new Bundle();
        bundle.putString(Constant.FLAG, ComUtil.getZCApi(this.context, getStringIds(Integer.valueOf((int) R.string.URL_HELP_IDENTY_REAL))));
        startAct(WebKitLocalActivity.class, bundle);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.iv_header /* 2131755016 */:
                showCameraAction();
                return;
            case R.id.btn_commit /* 2131755972 */:
                if (TextUtils.isEmpty(this.headerPath)) {
                    MyToast.show(getApplicationContext(), "请上传图片");
                    return;
                }
                getClient().showDialogNow(R.string.toast_uploading);
                if (this.headerPath.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
                    dealEditData(this.headerPath);
                    return;
                }
                try {
                    dealEditData(ImageUtil.compressImage(this.headerPath, System.currentTimeMillis() + "_1", 1024));
                    return;
                } catch (Exception e) {
                    Toast.makeText(this.context, "图片文件太大，请重试", 0).show();
                    return;
                }
            case R.id.iv_noedit /* 2131756040 */:
                MyToast.show(getApplicationContext(), "实名认证正在认证中或已认证");
                return;
            default:
                return;
        }
    }

    private void showCameraAction() {
        if (PermissionUtil.getCameraPermissions(this, 111)) {
            try {
                startActivityForResult(CameraUtil.getCameraIntent(this.context), Constant.FLAG_UPLOAD_TAKE_PICTURE);
            } catch (Exception e) {
                MyToast.show(this.context, getString(R.string.msg_no_camera));
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String path;
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case Constant.FLAG_UPLOAD_TAKE_PICTURE /* 1041 */:
                    if (data == null || data.getData() == null) {
                        path = CameraDocument.getPath(this.context, CameraUtil.getCacheUri(this.context));
                    } else {
                        path = CameraDocument.getPath(this.context, data.getData());
                    }
                    showPic(path);
                    return;
                default:
                    return;
            }
        }
    }

    private void showPic(String paths) {
        this.headerPath = paths;
        Glide.with(this.context).load(new File(paths)).into(this.iv_header);
    }

    private void dealEditData(String path) {
        List<String> allPaths = new ArrayList<>();
        allPaths.add(path);
        new UploadUtils().UploadFileGetUrl(this, "0001_auth/", DemoHelper.getInstance().getCurrentUserName(), "auth", 19, allPaths, new UploadPicListener() { // from class: com.vsf2f.f2f.ui.identify.IdentyHeaderActivity.1
            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onSuccess(@Nullable List<Map<String, String>> list, @Nullable List<String> picIds) {
                IdentyHeaderActivity.this.getClient().dialogDismiss();
                if (picIds == null) {
                    MyToast.show(IdentyHeaderActivity.this.getApplicationContext(), IdentyHeaderActivity.this.getString(R.string.upload_failed));
                    return;
                }
                if (IdentyHeaderActivity.this.ajaxParams == null) {
                    IdentyHeaderActivity.this.ajaxParams = new AjaxParams();
                }
                IdentyHeaderActivity.this.ajaxParams.put("certifyAvatar", picIds.get(0));
                IdentyHeaderActivity.this.commitIdentify();
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onFailed() {
                IdentyHeaderActivity.this.getClient().dialogDismiss();
                MyToast.show(IdentyHeaderActivity.this.getApplicationContext(), IdentyHeaderActivity.this.getString(R.string.upload_failed));
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onProgress(long currentSize, long totalSize) {
                MyLog.e("onProgress", currentSize + "/" + totalSize);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void commitIdentify() {
        getClient().post(R.string.BASIC_CERTIFY, ComUtil.getXDDApi(this.context, getString(R.string.BASIC_CERTIFY)), this.ajaxParams, String.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        super.onRequestSuccess(result);
        switch (result.getRequestCode()) {
            case R.string.BASIC_CERTIFY /* 2131296480 */:
                if (result.getErrorCode() == 0) {
                    MyToast.show(this.context, "实名认证申请成功");
                    getApp().removeFinish(IdentyIdCardActivity.class);
                    setResult(-1);
                    finish();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
