package com.vsf2f.f2f.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.em.DemoHelper;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.hy.frame.util.MyToast;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.GoldCoinBean;
import com.vsf2f.f2f.ui.utils.GameUtil;
import com.vsf2f.f2f.ui.utils.web.WebUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class MyCoinsActivity extends BaseActivity {
    private GoldCoinBean.ListBean goldBean;
    private TextView txtBalance;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_my_coins;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.mygoldcoin_title, 0);
        setOnClickListener(R.id.mycoins_btn_givecoins);
        setOnClickListener(R.id.mycoins_btn_sellcoins);
        setOnClickListener(R.id.mycoins_btn_buymoney);
        setOnClickListener(R.id.mycoins_btn_dynamic);
        setOnClickListener(R.id.mycoins_btnHelp);
        this.txtBalance = (TextView) getView(R.id.mycoins_txtBalance);
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
            jsonObject.put("bizContent", "{\"userName\":\"" + DemoHelper.getInstance().getCurrentUserName() + "\"}");
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
                    if (goldCoinBean.getList().get(i).getType() == 0) {
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
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.mycoins_btnHelp /* 2131755425 */:
                bundle.putString(Constant.FLAG, "http://zc.vjkeji.com/mobile/help/game_rules.html");
                bundle.putBoolean(Constant.FLAG2, false);
                startAct(WebKitLocalActivity.class, bundle);
                return;
            case R.id.mycoins_btn_givecoins /* 2131755438 */:
                MyToast.show(this.context, "暂未开放");
                return;
            case R.id.mycoins_btn_sellcoins /* 2131755439 */:
                if (this.goldBean != null) {
                    bundle.putString("type", f.ae);
                    bundle.putString("golds", this.goldBean.getNum());
                    startActForResult(MyCoinsChangeActivity.class, bundle, 111);
                    return;
                }
                return;
            case R.id.mycoins_btn_buymoney /* 2131755440 */:
                bundle.putString(Constant.FLAG, WebUtils.getTokenUrl(this.context, ComUtil.getZCApi(this.context, getString(R.string.API_GOLD_BUY))));
                bundle.putBoolean(Constant.FLAG2, false);
                startActForResult(TbsWebActivity.class, bundle, 111);
                return;
            case R.id.mycoins_btn_dynamic /* 2131755441 */:
                bundle.putInt("type", 0);
                startAct(MyBeansInfoActivity.class, bundle);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            setResult(-1);
            requestData();
        }
    }
}
