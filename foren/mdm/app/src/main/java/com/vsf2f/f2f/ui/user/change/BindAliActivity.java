package com.vsf2f.f2f.ui.user.change;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class BindAliActivity extends BaseActivity {
    private EditText editALi;
    private EditText editReal;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_bind_ali;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.ali_bind, R.string.ensure);
        this.editALi = (EditText) getView(R.id.edit_ali);
        this.editReal = (EditText) getView(R.id.edit_aliName);
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        super.onRightClick();
        requestData();
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        super.onRequestSuccess(result);
        MyToast.show(this, (int) R.string.bind_account_success);
        setResult(-1);
        finish();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        String bankNumber = this.editALi.getText().toString().trim();
        if (HyUtil.isEmpty(bankNumber)) {
            MyToast.show(this, (int) R.string.ali_account_hint);
            return;
        }
        String editName = this.editReal.getText().toString().trim();
        if (TextUtils.isEmpty(editName)) {
            MyToast.show(this, (int) R.string.real_name_hint);
            return;
        }
        AjaxParams params = new AjaxParams();
        params.put("realname", editName);
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
