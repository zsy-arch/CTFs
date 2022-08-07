package com.vsf2f.f2f.ui.user.change;

import android.view.View;
import android.widget.EditText;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class ChangeAliActivity extends BaseActivity {
    private String ali_id;
    private String alinum;
    private EditText editReal;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_bind_ali;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.ali_change, R.string.ensure);
        this.editReal = (EditText) getView(R.id.editRealname);
        this.ali_id = getBundle().getString(Constant.FLAG2);
        this.alinum = getBundle().getString(Constant.FLAG3);
        this.editReal.setText(this.alinum);
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        super.onRightClick();
        requestData();
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        super.onRequestSuccess(result);
        MyToast.show(this.context, getString(R.string.toast_change_success));
        setResult(-1);
        finish();
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
        MyToast.show(this.context, getString(R.string.toast_change_failed));
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        String bankNumber = this.editReal.getText().toString();
        if (HyUtil.isEmpty(bankNumber)) {
            MyToast.show(this, "请输入您的支付宝账号");
            return;
        }
        AjaxParams params = new AjaxParams();
        params.put("id", this.ali_id);
        params.put("bankCode", "EAT_ALI");
        params.put("bankNumber", bankNumber);
        getClient().setShowDialog(true);
        getClient().post(R.string.API_USER_BIND_ALI, ComUtil.getZCApi(this.context, getString(R.string.API_USER_BIND_ALI)), params, String.class);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
    }
}
