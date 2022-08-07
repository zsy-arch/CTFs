package com.cdlinglu.common;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import com.baidu.mobstat.StatService;
import com.em.DemoHelper;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.Constant;
import com.hy.frame.util.MyToast;
import com.hy.http.IMyHttpListener;
import com.hy.http.MyHttpClient;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.UserInfo;

/* loaded from: classes.dex */
public abstract class BaseFragment extends com.hy.frame.common.BaseFragment implements IMyHttpListener {
    public int getTitle() {
        return 0;
    }

    protected void initHeaderBack(@StringRes int title, @DrawableRes int right) {
        setHeaderLeft(R.drawable.icon_back);
        setHeaderRight(right);
        setTitle(title);
    }

    protected void initHeaderBackTxt(@StringRes int title, @StringRes int right) {
        setHeaderLeft(R.drawable.icon_back);
        setHeaderRightTxt(right);
        setTitle(title);
    }

    protected UserInfo getUserInfo() {
        return getUserInfo(false, false);
    }

    protected UserInfo getUserInfo(boolean userPic) {
        return getUserInfo(userPic, false);
    }

    protected UserInfo getUserInfo(boolean userPic, boolean thirdParty) {
        return DemoHelper.getInstance().getCurrentUserInfo(userPic, thirdParty);
    }

    public boolean isNoLogin() {
        return !isLogin();
    }

    public boolean isLogin() {
        return new AppShare(this.context).getString(Constant.USER_TOKEN) != null;
    }

    public void onStartData() {
    }

    @Override // com.hy.frame.common.BaseFragment
    protected MyHttpClient getClient() {
        if (super.getClient() == null) {
            setClient(new MyHttpClient(this.context, this));
        }
        return super.getClient();
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        StatService.onPageStart(getContext(), "fragment");
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() {
        super.onPause();
        StatService.onPageEnd(getContext(), "fragment");
    }

    @Override // com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
    }

    @Override // com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        MyToast.show(this.context, result.getMsg());
    }
}
