package com.vsf2f.f2f.ui.user;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.cdlinglu.common.BaseActivity;
import com.easeui.domain.EaseUser;
import com.easeui.utils.EaseUserUtils;
import com.hy.frame.bean.ResultInfo;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class MyCoinsGiveActivity extends BaseActivity {
    private EditText etCoins;
    private EditText etRemark;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_my_coins_give;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.mymoney_give, 0);
        String username = getBundle().getString("username");
        EaseUser user = EaseUserUtils.getContactInfo(username);
        ((TextView) findViewById(R.id.mymoney_ivName)).setText(username);
        ((TextView) findViewById(R.id.mymoney_ivNick)).setText(user.getNick());
        Glide.with(this.context).load(user.getAvatar()).into((ImageView) findViewById(R.id.mymoney_ivAvatar));
        this.etCoins = (EditText) getView(R.id.mycoins_etGiveCoins);
        this.etCoins.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.user.MyCoinsGiveActivity.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        requestData();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        result.getRequestCode();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.mycoins_tvGiveEnsure /* 2131755460 */:
                startAct(MyBeansInfoActivity.class, getBundle());
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11) {
            requestData();
        }
    }
}
