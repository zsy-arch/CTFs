package com.vsf2f.f2f.ui.needs.demand;

import android.view.View;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.Constant;

/* loaded from: classes2.dex */
public class CommentSuccessActivity extends BaseActivity {
    private int msg;
    private int subMsg;
    private int title;
    private TextView tv_submsg;
    private TextView tv_title;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_comment_success;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.comment_success, 0);
        this.title = getBundle().getInt("title");
        this.msg = getBundle().getInt("msg");
        this.subMsg = getBundle().getInt(Constant.SUB_MSG);
        this.tv_title = (TextView) getView(R.id.tv_title);
        this.tv_submsg = (TextView) getView(R.id.tv_submsg);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        if (this.title != 0) {
            setTitle(this.title);
        }
        if (this.msg != 0) {
            this.tv_title.setText(getString(this.msg));
        }
        if (this.subMsg != 0) {
            this.tv_submsg.setText(getString(this.subMsg));
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
    }
}
