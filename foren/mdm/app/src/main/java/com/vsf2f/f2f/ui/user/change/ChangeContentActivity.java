package com.vsf2f.f2f.ui.user.change;

import android.view.View;
import android.widget.EditText;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.em.ui.EditActivity;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.UserInfo;

/* loaded from: classes2.dex */
public class ChangeContentActivity extends BaseActivity {
    private UserInfo detail;
    private EditText editContent;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_change_content;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.personal, 0);
        this.editContent = (EditText) getView(R.id.edit_content);
        setOnClickListener(R.id.btn_ensure);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.detail = getUserInfo();
        if (this.detail.getContetnt() != null) {
            this.editContent.setText(this.detail.getContetnt());
            this.editContent.requestFocus();
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        String content = this.editContent.getText().toString().trim();
        if (content.equals(this.detail.getContetnt())) {
            onLeftClick();
            return;
        }
        AjaxParams params = new AjaxParams();
        params.put("id", this.detail.getId());
        params.put(EditActivity.CONTENT, content);
        getClient().setShowDialog(true);
        getClient().post(R.string.API_USER_CHANGE_DATA, ComUtil.getZCApi(this.context, getString(R.string.API_USER_CHANGE_DATA)), params, String.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        super.onRequestSuccess(result);
        UserShared.getInstance().save(EditActivity.CONTENT, this.editContent.getText().toString());
        setResult(-1);
        finish();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ensure /* 2131755180 */:
                requestData();
                return;
            default:
                return;
        }
    }
}
