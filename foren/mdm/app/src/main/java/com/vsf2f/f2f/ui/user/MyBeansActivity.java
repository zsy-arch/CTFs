package com.vsf2f.f2f.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.easeui.widget.EaseAlertDialog;
import com.em.DemoHelper;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.GoldCoinBean;
import com.vsf2f.f2f.ui.MainActivity;
import com.vsf2f.f2f.ui.circle.CircleActivity;
import com.vsf2f.f2f.ui.needs.demand.DemandPublishActivity;
import com.vsf2f.f2f.ui.needs.service.ServicePublishActivity;
import com.vsf2f.f2f.ui.shop.GoodsPublishActivity;
import com.vsf2f.f2f.ui.shop.ShopMainActivity;
import com.vsf2f.f2f.ui.user.change.BindPhoneActivity;
import com.vsf2f.f2f.ui.utils.GameUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class MyBeansActivity extends BaseActivity {
    private GoldCoinBean.ListBean goldBean;
    private TextView txtBalance;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_my_beans;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.mygoldbean_title, 0);
        setOnClickListener(R.id.mycoins_btn_exchange);
        setOnClickListener(R.id.mybeans_publish_demand);
        setOnClickListener(R.id.mybeans_publish_service);
        setOnClickListener(R.id.mybeans_publish_circle);
        setOnClickListener(R.id.mybeans_publish_product);
        setOnClickListener(R.id.mybeans_publish_chat);
        setOnClickListener(R.id.mybeans_btn_dynamic);
        setOnClickListener(R.id.mycoins_btnHelp);
        this.txtBalance = (TextView) getView(R.id.mybeans_txtBalance);
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
    public void requestData() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("method", "vsf2f.game.zqly.manor.coffer.query");
            jsonObject.put("bizContent", ComUtil.UTF(String.format("{\"userName\":\"%s\"}", DemoHelper.getInstance().getCurrentUserName())));
            getClient().post(R.string.API_GAME_POST, ComUtil.getGAMEApi(this.context, getString(R.string.API_GAME_POST)), GameUtil.getVsSign(jsonObject.toString()), null, GoldCoinBean.class, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_GAME_POST /* 2131296348 */:
                GoldCoinBean goldCoinBean = (GoldCoinBean) result.getObj();
                for (int i = 0; i < goldCoinBean.getList().size(); i++) {
                    if (goldCoinBean.getList().get(i).getType() == 1) {
                        this.goldBean = goldCoinBean.getList().get(i);
                        updateUI();
                        return;
                    }
                }
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        if (this.goldBean != null) {
            this.txtBalance.setText(this.goldBean.getNum());
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.mycoins_btn_exchange /* 2131755424 */:
                if (this.goldBean != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("golds", this.goldBean.getNum());
                    startActForResult(MyCoinsChangeActivity.class, bundle, 111);
                    return;
                }
                return;
            case R.id.mycoins_btnHelp /* 2131755425 */:
                Bundle bundle3 = new Bundle();
                bundle3.putString(Constant.FLAG, "http://zc.vjkeji.com/mobile/help/game_rules.html");
                bundle3.putBoolean(Constant.FLAG2, false);
                startAct(WebKitLocalActivity.class, bundle3);
                return;
            case R.id.mybeans_btn_dynamic /* 2131755426 */:
                Bundle bundle2 = new Bundle();
                bundle2.putInt("type", 1);
                startAct(MyBeansInfoActivity.class, bundle2);
                return;
            case R.id.mybeans_publish_demand /* 2131755427 */:
                if (UserShared.getInstance().getIsVerifyState(this.context)) {
                    startAct(DemandPublishActivity.class);
                    setResult(-1);
                    return;
                }
                return;
            case R.id.mybeans_publish_service /* 2131755428 */:
                if (UserShared.getInstance().getIsVerifyState(this.context)) {
                    startAct(ServicePublishActivity.class);
                    setResult(-1);
                    return;
                }
                return;
            case R.id.mybeans_publish_circle /* 2131755429 */:
                Bundle bundle4 = new Bundle();
                bundle4.putBoolean("add", true);
                startAct(CircleActivity.class, bundle4);
                setResult(-1);
                return;
            case R.id.mybeans_publish_product /* 2131755430 */:
                if (!UserShared.getInstance().getIsVerifyState(this.context)) {
                    return;
                }
                if (UserShared.getInstance().getInt(com.vsf2f.f2f.ui.utils.Constant.CERT_MOBILE) != 1) {
                    new EaseAlertDialog(this.context, (int) R.string.prompt, (int) R.string.not_bing_phone_prompt, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.user.MyBeansActivity.1
                        @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                        public void onResult(boolean confirmed, Bundle bundle5) {
                            if (confirmed) {
                                MyBeansActivity.this.startAct(BindPhoneActivity.class);
                            }
                        }
                    }, true).show();
                    return;
                }
                if (DemoHelper.getInstance().isOpenStore() == 1) {
                    startAct(GoodsPublishActivity.class);
                } else {
                    startAct(ShopMainActivity.class);
                }
                setResult(-1);
                return;
            case R.id.user_fnc_llyLevel /* 2131755431 */:
            default:
                return;
            case R.id.mybeans_publish_chat /* 2131755432 */:
                Intent intent = new Intent(this.context, MainActivity.class);
                intent.putExtra(com.vsf2f.f2f.ui.utils.Constant.FLAG_INTENT, 0);
                intent.addFlags(268435456);
                startActivity(intent);
                return;
        }
    }
}
