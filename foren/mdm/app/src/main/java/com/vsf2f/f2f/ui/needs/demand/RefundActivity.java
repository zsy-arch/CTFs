package com.vsf2f.f2f.ui.needs.demand;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
public class RefundActivity extends BaseActivity {
    public static final int FLAG_TYPE_PLATE = 3;
    public static final int FLAG_TYPE_REFUND = 0;
    public static final int FLAG_TYPE_REREFUND = 1;
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private EditText et_reason;
    private GridView gv_pic;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private boolean isService;
    private int moId;
    private PicUrlPathAdapter picAdapter;
    private int type;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_refund;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        this.moId = getBundle().getInt("moId");
        this.type = getBundle().getInt("type", 0);
        this.isService = getBundle().getBoolean("isService");
        switch (this.type) {
            case 0:
                initHeaderBackTxt(R.string.apply_refund, 0);
                break;
            case 1:
                initHeaderBackTxt(R.string.apply_refund_re, 0);
                break;
            case 3:
                initHeaderBackTxt(R.string.apply_refund_pl, 0);
                break;
        }
        setOnClickListener(R.id.btn_commit);
        this.et_reason = (EditText) getView(R.id.et_reason);
        this.gv_pic = (GridView) getView(R.id.gv_pic);
        this.picAdapter = new PicUrlPathAdapter(this, this.imagePaths, 3, R.dimen.edit_five_left, R.dimen.spacing);
        this.gv_pic.setAdapter((ListAdapter) this.picAdapter);
        this.gv_pic.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.vsf2f.f2f.ui.needs.demand.RefundActivity.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PhotoPreviewIntent intent = new PhotoPreviewIntent(RefundActivity.this.context);
                intent.setCurrentItem(i);
                intent.setPhotoPaths(RefundActivity.this.imagePaths);
                RefundActivity.this.startActivityForResult(intent, 20);
            }
        });
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

    public void addPic(View view) {
        PhotoPickerIntent intent = new PhotoPickerIntent(this.context);
        intent.setSelectModel(SelectModel.MULTI);
        intent.setShowCamera(true);
        intent.setMaxTotal(3);
        intent.setSelectedPaths(this.imagePaths);
        startActivityForResult(intent, 10);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.btn_commit /* 2131755972 */:
                if (this.imagePaths.size() == 0) {
                    MyToast.show(getApplicationContext(), "请上传凭证图片");
                    return;
                }
                getClient().showDialogNow(R.string.toast_uploading);
                dealEditData();
                return;
            default:
                return;
        }
    }

    private void dealEditData() {
        new UploadUtils().UploadFileGetUrl(this, "", DemoHelper.getInstance().getCurrentUserName(), Constant.PRODUCTS_BUCKET, 17, this.imagePaths, new UploadPicListener() { // from class: com.vsf2f.f2f.ui.needs.demand.RefundActivity.2
            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onSuccess(@Nullable List<Map<String, String>> list, @Nullable List<String> picIds) {
                if (picIds == null) {
                    RefundActivity.this.getClient().dialogDismiss();
                    MyToast.show(RefundActivity.this.getApplicationContext(), RefundActivity.this.getString(R.string.upload_failed));
                    return;
                }
                RefundActivity.this.commitRefund(picIds);
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onFailed() {
                RefundActivity.this.getClient().dialogDismiss();
                MyToast.show(RefundActivity.this.getApplicationContext(), RefundActivity.this.getString(R.string.upload_failed));
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onProgress(long currentSize, long totalSize) {
                RefundActivity.this.getClient().showDialogNow(RefundActivity.this.getString(R.string.toast_uploading_progress, new Object[]{Long.valueOf(currentSize), Long.valueOf(totalSize)}));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void commitRefund(List<String> picIds) {
        String detail = this.et_reason.getText().toString();
        AjaxParams params = new AjaxParams();
        if (picIds != null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < picIds.size(); i++) {
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(picIds.get(i));
            }
            params.put("imgList", sb.toString());
        }
        getClient().setShowDialog(true);
        params.put("description", detail);
        if (!this.isService) {
            params.put("shareOrderId", this.moId);
            if (this.type == 3) {
                getClient().post(R.string.API_ORDER_APPEAL, ComUtil.getXDDApi(this.context, getString(R.string.API_ORDER_APPEAL)), params, String.class);
            } else {
                getClient().post(R.string.API_APPLY_REFUND, ComUtil.getXDDApi(this.context, getString(R.string.API_APPLY_REFUND)), params, String.class);
            }
        } else {
            params.put("shareServiceOrderId", this.moId);
            if (this.type == 3) {
                getClient().post(R.string.API_SERVICE_APPEAL, ComUtil.getXDDApi(this.context, getString(R.string.API_SERVICE_APPEAL)), params, String.class);
            } else {
                getClient().post(R.string.API_SERVICE_APPLYREFUND, ComUtil.getXDDApi(this.context, getString(R.string.API_SERVICE_APPLYREFUND)), params, String.class);
            }
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        if (result.getErrorCode() == 0) {
            Bundle bundle = new Bundle();
            switch (this.type) {
                case 0:
                    bundle.putInt("title", R.string.apply_refund);
                    bundle.putInt("msg", R.string.refund_success);
                    break;
                case 1:
                    bundle.putInt("title", R.string.apply_refund_re);
                    bundle.putInt("msg", R.string.re_refund_success);
                    break;
                case 3:
                    bundle.putInt("title", R.string.apply_refund_pl);
                    bundle.putInt("msg", R.string.refund_plate_success);
                    break;
            }
            startAct(CommentSuccessActivity.class, bundle);
            setResult(-1);
            finish();
            return;
        }
        MyToast.show(getApplicationContext(), result.getMsg());
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
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
        if (this.imagePaths == null) {
            this.imagePaths = new ArrayList<>();
        }
        this.imagePaths.clear();
        this.imagePaths.addAll(paths);
        this.picAdapter.notifyDataSetChanged();
    }
}
