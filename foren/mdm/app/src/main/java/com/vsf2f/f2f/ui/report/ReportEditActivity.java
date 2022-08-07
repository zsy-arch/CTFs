package com.vsf2f.f2f.ui.report;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.em.DemoHelper;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.PicUrlPathAdapter;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.utils.listener.UploadPicListener;
import com.vsf2f.f2f.ui.utils.photo.PhotoPickerActivity;
import com.vsf2f.f2f.ui.utils.photo.PhotoPickerIntent;
import com.vsf2f.f2f.ui.utils.photo.PhotoPreviewActivity;
import com.vsf2f.f2f.ui.utils.photo.PhotoPreviewIntent;
import com.vsf2f.f2f.ui.utils.photo.SelectModel;
import com.vsf2f.f2f.ui.utils.upload.UploadUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class ReportEditActivity extends BaseActivity {
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private String contact;
    private String description;
    private EditText et_description;
    private EditText et_tel;
    private GridView gv_pic;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private String objId;
    private int objType;
    private PicUrlPathAdapter picAdapter;
    private String reportType;
    private String reportedUser;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_report_edit;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.report_title, 0);
        this.et_description = (EditText) getView(R.id.et_description);
        this.et_tel = (EditText) getView(R.id.et_tel);
        this.gv_pic = (GridView) getView(R.id.gv_pic);
        setOnClickListener(R.id.btn_commit);
        setOnClickListener(R.id.report_llyAddpic);
        this.gv_pic.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.vsf2f.f2f.ui.report.ReportEditActivity.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PhotoPreviewIntent intent = new PhotoPreviewIntent(ReportEditActivity.this.context);
                intent.setCurrentItem(i);
                intent.setPhotoPaths(ReportEditActivity.this.imagePaths);
                ReportEditActivity.this.startActivityForResult(intent, 20);
            }
        });
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        Bundle bundle = getBundle();
        this.objId = bundle.getString("objId");
        this.objType = bundle.getInt("objType");
        this.reportType = bundle.getString("reportType");
        this.reportedUser = bundle.getString("reportedUser");
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.btn_commit /* 2131755972 */:
                if (this.imagePaths.size() == 0) {
                    MyToast.show(getApplicationContext(), "请上传凭证图片");
                    return;
                }
                this.description = this.et_description.getText().toString();
                this.contact = this.et_tel.getText().toString();
                if (TextUtils.isEmpty(this.description)) {
                    MyToast.show(getApplicationContext(), "请填写举报理由");
                    return;
                } else if (TextUtils.isEmpty(this.contact)) {
                    MyToast.show(getApplicationContext(), "请填写联系方式");
                    return;
                } else {
                    getClient().showDialogNow(R.string.toast_uploading);
                    dealEditData();
                    return;
                }
            case R.id.report_llyAddpic /* 2131756120 */:
                PhotoPickerIntent intent = new PhotoPickerIntent(this.context);
                intent.setSelectModel(SelectModel.MULTI);
                intent.setShowCamera(true);
                intent.setMaxTotal(3);
                intent.setSelectedPaths(this.imagePaths);
                startActivityForResult(intent, 10);
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
                case 10:
                    loadAdapter(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));
                    return;
                case 20:
                    loadAdapter(data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT));
                    return;
                default:
                    return;
            }
        }
    }

    private void loadAdapter(ArrayList<String> paths) {
        this.imagePaths.clear();
        this.imagePaths.addAll(paths);
        if (this.picAdapter == null) {
            this.picAdapter = new PicUrlPathAdapter(this, this.imagePaths);
            this.gv_pic.setAdapter((ListAdapter) this.picAdapter);
            return;
        }
        this.picAdapter.notifyDataSetChanged();
    }

    private void dealEditData() {
        new UploadUtils().UploadFileGetUrl(this, "", DemoHelper.getInstance().getCurrentUserName(), Constant.ADVERT_BUCKET, 18, this.imagePaths, new UploadPicListener() { // from class: com.vsf2f.f2f.ui.report.ReportEditActivity.2
            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onSuccess(@Nullable List<Map<String, String>> list, @Nullable List<String> picIds) {
                if (picIds == null) {
                    MyToast.show(ReportEditActivity.this.getApplicationContext(), ReportEditActivity.this.getString(R.string.upload_failed));
                    ReportEditActivity.this.getClient().dialogDismiss();
                    return;
                }
                ReportEditActivity.this.commitRefund(picIds);
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onFailed() {
                ReportEditActivity.this.getClient().dialogDismiss();
                MyToast.show(ReportEditActivity.this.getApplicationContext(), ReportEditActivity.this.getString(R.string.upload_failed));
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onProgress(long currentSize, long totalSize) {
                ReportEditActivity.this.getClient().showDialogNow(ReportEditActivity.this.getString(R.string.toast_uploading_progress, new Object[]{Long.valueOf(currentSize), Long.valueOf(totalSize)}));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void commitRefund(List<String> picIds) {
        AjaxParams params = new AjaxParams();
        if (picIds != null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < picIds.size(); i++) {
                if (i == 0) {
                    sb.append(picIds.get(i));
                } else {
                    sb.append("," + picIds.get(i));
                }
            }
            params.put("picturesUrlList", sb.toString());
        }
        params.put("reportedUser", this.reportedUser);
        params.put("description", this.description);
        params.put("reportType", this.reportType);
        params.put("contact", this.contact);
        params.put("objType", this.objType);
        params.put("objId", this.objId);
        params.put("fromApp", 1);
        getClient().post(R.string.API_REPORT, ComUtil.getZCApi(this.context, getString(R.string.API_REPORT)), params, String.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        Bundle bundle = new Bundle();
        bundle.putInt("title", R.string.report_title);
        bundle.putInt("msg", R.string.report_success);
        startAct(ReportSuccessActivity.class, bundle);
        finish();
    }
}
