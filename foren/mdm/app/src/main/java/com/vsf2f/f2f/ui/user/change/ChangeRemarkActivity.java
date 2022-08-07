package com.vsf2f.f2f.ui.user.change;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.easeui.EaseConstant;
import com.em.DemoHelper;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.Constant;

/* loaded from: classes2.dex */
public class ChangeRemarkActivity extends BaseActivity {
    private ImageView cancelView;
    private String oldRemark = "";
    private EditText remarkView;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_change_remark;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.change_remark, 0);
        this.remarkView = (EditText) getView(R.id.et_remark);
        this.cancelView = (ImageView) getViewAndClick(R.id.iv_cancel);
        setOnClickListener(R.id.btn_save_remark);
        this.remarkView.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.user.change.ChangeRemarkActivity.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                String content = s.toString();
                if (TextUtils.isEmpty(content) && ChangeRemarkActivity.this.cancelView.getVisibility() == 0) {
                    ChangeRemarkActivity.this.cancelView.setVisibility(8);
                } else if (!TextUtils.isEmpty(content) && ChangeRemarkActivity.this.cancelView.getVisibility() == 8) {
                    ChangeRemarkActivity.this.cancelView.setVisibility(0);
                }
            }
        });
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        if (getBundle() != null) {
            this.oldRemark = getBundle().getString(EaseConstant.EXTRA_NICK_NAME);
            if (this.remarkView != null) {
                this.remarkView.setText(this.oldRemark);
                this.remarkView.requestFocus();
            }
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.iv_cancel /* 2131755878 */:
                this.remarkView.setText("");
                return;
            case R.id.btn_save_remark /* 2131755879 */:
                String remark = this.remarkView.getText().toString().trim();
                if (TextUtils.isEmpty(remark)) {
                    remark = "";
                }
                if (remark.equals(this.oldRemark)) {
                    finish();
                    return;
                }
                String friendName = getBundle().getString("username");
                if (!TextUtils.isEmpty(friendName)) {
                    String username = DemoHelper.getInstance().getCurrentUserName();
                    if (!TextUtils.isEmpty(username)) {
                        changeRemark(username, friendName, remark);
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void changeRemark(String username, String friendName, String remark) {
        if (!ComUtil.checkContent(remark, Constant.CONTENTMARTCH)) {
            MyToast.show(this, (int) R.string.no_martch);
            return;
        }
        getClient().setShowDialog(true);
        AjaxParams params = new AjaxParams();
        params.put("nickName", ComUtil.UTF(remark));
        getClient().post(R.string.API_FRIEND_REMARK, ComUtil.getF2FApi(this.context, getString(R.string.API_FRIEND_REMARK, new Object[]{username, friendName})), params);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        int requestCode = result.getRequestCode();
        int errorCode = result.getErrorCode();
        switch (requestCode) {
            case R.string.API_FRIEND_REMARK /* 2131296345 */:
                if (errorCode == 0) {
                    Intent intent = new Intent();
                    intent.putExtra(EaseConstant.EXTRA_NICK_NAME, this.remarkView.getText().toString().trim());
                    setResult(-1, intent);
                    finish();
                    return;
                }
                MyToast.show(this, (int) R.string.toast_change_failed);
                return;
            default:
                return;
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
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
}
