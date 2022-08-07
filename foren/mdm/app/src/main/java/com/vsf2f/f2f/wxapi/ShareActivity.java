package com.vsf2f.f2f.wxapi;

import android.app.Activity;
import android.os.Bundle;
import com.cdlinglu.utils.ShareUtils;
import com.hy.frame.util.Constant;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.ShareThirdBean;
import com.vsf2f.f2f.ui.other.BaseUiListener;

/* loaded from: classes2.dex */
public class ShareActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_empty);
        ShareUtils shareUtils = new ShareUtils(this);
        Bundle bundle = getIntent().getBundleExtra(Constant.BUNDLE);
        int flag = bundle.getInt(Constant.FLAG_ID);
        shareUtils.setShare((ShareThirdBean) bundle.getParcelable(Constant.FLAG));
        switch (flag) {
            case 11:
                shareUtils.shareTask(1);
                return;
            case 12:
                shareUtils.shareTask(0);
                return;
            case 13:
                shareUtils.setListener(new BaseUiListener(this));
                shareUtils.qqShare();
                return;
            case 14:
                shareUtils.setListener(new BaseUiListener(this));
                shareUtils.qqZoneShare();
                return;
            default:
                return;
        }
    }
}
