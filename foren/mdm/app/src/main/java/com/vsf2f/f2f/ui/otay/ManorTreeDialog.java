package com.vsf2f.f2f.ui.otay;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.cdlinglu.common.MyHttpClient;
import com.cdlinglu.utils.ComUtil;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.Constant;
import com.hy.http.IMyHttpListener;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;
import com.vsf2f.f2f.ui.user.pwd.LoginActivity;
import com.vsf2f.f2f.ui.utils.GameUtil;
import com.vsf2f.f2f.ui.utils.web.WebUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ManorTreeDialog extends BaseDialog {
    private String json;

    public ManorTreeDialog(Context context) {
        super(context);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_manor_tree;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(-1.0f, -1.0f, 17);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("method", "vsf2f.game.zqly.manor.open");
            jsonObject.put("bizContent", "{\"init\":true,\"name\":\"\"}");
            this.json = GameUtil.getVsSign(jsonObject.toString());
            setOnClickListener(R.id.btn_pact);
            setOnClickListener(R.id.btn_yes);
            setOnClickListener(R.id.btn_no);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startGame() {
        UserShared.getInstance().setOpenManor(true);
        GameUtil.startGame(getContext());
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
        AppShare.get(getContext()).putBoolean("tree", false);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.btn_no /* 2131756270 */:
                dismiss();
                return;
            case R.id.btn_yes /* 2131756271 */:
                if (ComUtil.isNoLogin(getContext())) {
                    AppShare.get(getContext()).putBoolean("tree", true);
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                } else {
                    MyHttpClient myHttpClient = new MyHttpClient(getContext(), new IMyHttpListener() { // from class: com.vsf2f.f2f.ui.otay.ManorTreeDialog.1
                        @Override // com.hy.http.IMyHttpListener
                        public void onRequestSuccess(ResultInfo result) {
                            ManorTreeDialog.this.startGame();
                        }

                        @Override // com.hy.http.IMyHttpListener
                        public void onRequestError(ResultInfo result) {
                            ManorTreeDialog.this.startGame();
                        }
                    });
                    myHttpClient.setShowDialog("正在开通……");
                    myHttpClient.post(R.string.API_GAME_POST, ComUtil.getGAMEApi(getContext(), getContext().getString(R.string.API_GAME_POST)), this.json, null, String.class, false);
                }
                dismiss();
                return;
            case R.id.btn_pact /* 2131756272 */:
                Bundle bundleWeb = new Bundle();
                bundleWeb.putString(Constant.FLAG, WebUtils.getTokenUrl(getContext(), ComUtil.getZCApi(getContext(), getContext().getString(R.string.API_GAME_PACT))));
                bundleWeb.putBoolean(Constant.FLAG2, false);
                Intent intentWeb = new Intent(getContext(), WebKitLocalActivity.class);
                intentWeb.putExtra(Constant.BUNDLE, bundleWeb);
                getContext().startActivity(intentWeb);
                return;
            default:
                return;
        }
    }

    @Override // android.app.Dialog
    public void setOnDismissListener(@Nullable DialogInterface.OnDismissListener listener) {
        super.setOnDismissListener(listener);
    }
}
