package com.vsf2f.f2f.ui.circle;

import android.view.View;
import com.cdlinglu.common.BaseActivity;
import com.em.utils.UserShared;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class CirclesAddActivity extends BaseActivity {
    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_circles_add;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.release, 0);
        setOnClickListener(R.id.circles_add_txtText);
        setOnClickListener(R.id.circles_add_txtPic);
        setOnClickListener(R.id.circles_add_txtArt);
        setOnClickListener(R.id.circles_add_txtVideo);
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        super.onRightClick();
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
    public void onLeftClick() {
        onBackPressed();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        setResult(-1);
        finish();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        if (UserShared.getInstance().getIsVerifyState(this.context)) {
            switch (view.getId()) {
                case R.id.circles_add_txtText /* 2131755214 */:
                    startAct(CirclesAddTextActivity.class);
                    return;
                case R.id.circles_add_txtPic /* 2131755215 */:
                    startAct(CirclesAddPicActivity.class);
                    return;
                case R.id.circles_add_txtVideo /* 2131755216 */:
                    startAct(CirclesAddVideoActivity.class);
                    return;
                case R.id.circles_add_txtArt /* 2131755217 */:
                    startAct(CirclesAddArtActivity.class);
                    return;
                default:
                    return;
            }
        }
    }
}
