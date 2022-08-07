package com.vsf2f.f2f.ui.needs.demand;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.em.DemoHelper;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.KeyValueView;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.PicUrlPathAdapter;
import com.vsf2f.f2f.ui.dialog.StringListDialog;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.utils.listener.UploadPicListener;
import com.vsf2f.f2f.ui.utils.photo.PhotoPickerActivity;
import com.vsf2f.f2f.ui.utils.photo.PhotoPickerIntent;
import com.vsf2f.f2f.ui.utils.photo.PhotoPreviewActivity;
import com.vsf2f.f2f.ui.utils.photo.SelectModel;
import com.vsf2f.f2f.ui.utils.upload.UploadUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class OrderCancelActivity extends BaseActivity {
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private String amount;
    private EditText et_reason;
    private GridView gv_pic;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private boolean isService;
    private KeyValueView kv_refund_reason;
    private StringListDialog listDialog;
    private int moId;
    private PicUrlPathAdapter picAdapter;
    private TextView tv_count;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_refuse_refund;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.cancel_order, 0);
        this.moId = getBundle().getInt("id");
        this.amount = getBundle().getString("amount");
        this.isService = getBundle().getBoolean("isService");
        setOnClickListener(R.id.btn_commit);
        this.kv_refund_reason = (KeyValueView) getViewAndClick(R.id.kv_refund_reason);
        this.et_reason = (EditText) getView(R.id.et_reason);
        this.tv_count = (TextView) getView(R.id.tv_count);
        this.gv_pic = (GridView) getView(R.id.gv_pic);
        this.et_reason.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.needs.demand.OrderCancelActivity.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                OrderCancelActivity.this.tv_count.setText(s.length() + "/200 字");
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
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

    private void closeOrder(String reason) {
        AjaxParams params = new AjaxParams();
        params.put("shareOrderId", this.moId);
        params.put("closeReason", reason);
        getClient().setShowDialog(true);
        getClient().post(R.string.API_CLOSE_REWARD, ComUtil.getXDDApi(this.context, getString(R.string.API_CLOSE_REWARD)), params, String.class);
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
            case R.id.kv_refund_reason /* 2131756118 */:
                if (this.listDialog == null) {
                    final String[] strs = getResources().getStringArray(R.array.refund_reason);
                    this.listDialog = new StringListDialog(this.context, Arrays.asList(strs), new StringListDialog.onClickListListener() { // from class: com.vsf2f.f2f.ui.needs.demand.OrderCancelActivity.2
                        @Override // com.vsf2f.f2f.ui.dialog.StringListDialog.onClickListListener
                        public void onClickList(int checkReason) {
                            OrderCancelActivity.this.kv_refund_reason.setValue(strs[checkReason]);
                        }
                    });
                }
                this.listDialog.show();
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
            this.picAdapter = new PicUrlPathAdapter(this, this.imagePaths, 3, R.dimen.edit_five_left, R.dimen.spacing);
            this.gv_pic.setAdapter((ListAdapter) this.picAdapter);
            return;
        }
        this.picAdapter.notifyDataSetChanged();
    }

    private void dealEditData() {
        new UploadUtils().UploadFileGetUrl(this, "", DemoHelper.getInstance().getCurrentUserName(), Constant.PRODUCTS_BUCKET, 17, this.imagePaths, new UploadPicListener() { // from class: com.vsf2f.f2f.ui.needs.demand.OrderCancelActivity.3
            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onSuccess(@Nullable List<Map<String, String>> list, @Nullable List<String> picIds) {
                OrderCancelActivity.this.getClient().dialogDismiss();
                if (picIds == null) {
                    MyToast.show(OrderCancelActivity.this.getApplicationContext(), OrderCancelActivity.this.getString(R.string.upload_failed));
                }
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onFailed() {
                OrderCancelActivity.this.getClient().dialogDismiss();
                MyToast.show(OrderCancelActivity.this.getApplicationContext(), OrderCancelActivity.this.getString(R.string.upload_failed));
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onProgress(long currentSize, long totalSize) {
                MyLog.e("onProgress", currentSize + "/" + totalSize);
            }
        });
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        Bundle bundle = new Bundle();
        if (result.getErrorCode() == 0) {
            switch (result.getRequestCode()) {
                case R.string.API_REJECT_REFUND /* 2131296396 */:
                case R.string.API_SERVICE_REJECTREFUND /* 2131296418 */:
                    bundle.putInt("title", R.string.title_refuse_refund);
                    bundle.putInt("msg", R.string.commit_refund_success);
                    bundle.putInt(Constant.SUB_MSG, R.string.wait_demander_sure);
                    startAct(CommentSuccessActivity.class, bundle);
                    setResult(-1);
                    finish();
                    return;
                default:
                    return;
            }
        } else {
            MyToast.show(getApplicationContext(), result.getMsg());
        }
    }

    public void addPic(View view) {
        PhotoPickerIntent intent = new PhotoPickerIntent(this.context);
        intent.setSelectModel(SelectModel.MULTI);
        intent.setShowCamera(true);
        intent.setMaxTotal(3);
        intent.setSelectedPaths(this.imagePaths);
        startActivityForResult(intent, 10);
    }
}
