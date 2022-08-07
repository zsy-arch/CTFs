package com.vsf2f.f2f.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.em.DemoHelper;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyToast;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.GoldCoinBean;
import com.vsf2f.f2f.ui.utils.GameUtil;
import com.vsf2f.f2f.ui.utils.web.WebUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class MyCoinsChangeActivity extends BaseActivity {
    private EditText edtSellNum;
    private EditText edtSpendNum;
    private EditText edtTogetMoney;
    private EditText edtTogetNum;
    private double golds;
    private TextView txtBalance;
    private TextView txtCoinsBuy;
    private TextView txtTobeans;
    private TextView txtTogoods;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_my_coins_change;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.mycoinchange_title, 0);
        setOnClickListener(R.id.mycoins_btnHelp);
        setOnClickListener(R.id.mycoins_txtDetail);
        String goldStr = getBundle().getString("golds");
        this.golds = Double.parseDouble(goldStr);
        this.txtCoinsBuy = (TextView) getViewAndClick(R.id.mycoins_txtCoinsBuy);
        this.txtBalance = (TextView) findViewById(R.id.mycoins_txtBalance);
        this.txtBalance.setText(goldStr);
        if (f.ae.equals(getBundle().getString("type"))) {
            setTitle(R.string.mycoinsell_title);
            getView(R.id.mycoins_llySellCoins).setVisibility(0);
            getView(R.id.mycoins_llyChangeCoins).setVisibility(8);
            setOnClickListener(R.id.mycoins_txtChangeMoney);
            this.edtTogetMoney = (EditText) getView(R.id.mycoins_edtTogetMoney);
            this.edtSellNum = (EditText) getView(R.id.mycoins_edtSellNum);
            this.edtSellNum.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.user.MyCoinsChangeActivity.1
                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (!TextUtils.isEmpty(charSequence)) {
                        int coins = Integer.parseInt(charSequence.toString());
                        if (coins > MyCoinsChangeActivity.this.golds) {
                            coins = (int) MyCoinsChangeActivity.this.golds;
                            MyCoinsChangeActivity.this.edtSellNum.setText(String.valueOf(coins));
                            MyCoinsChangeActivity.this.edtSellNum.setSelection(String.valueOf(coins).length());
                        }
                        MyCoinsChangeActivity.this.edtTogetMoney.setText(String.format("￥%s", HyUtil.formatToMoney(coins + "")));
                        return;
                    }
                    MyCoinsChangeActivity.this.edtTogetMoney.setText("￥0.00");
                }

                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable editable) {
                }
            });
            return;
        }
        setOnClickListener(R.id.mycoins_txtChangeBeans);
        this.edtSpendNum = (EditText) getView(R.id.mycoins_edtSpendNum);
        this.edtTogetNum = (EditText) getView(R.id.mycoins_edtTogetNum);
        this.edtTogetNum.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.user.MyCoinsChangeActivity.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence)) {
                    double coins = Integer.parseInt(charSequence.toString()) / 20.0d;
                    if (coins > MyCoinsChangeActivity.this.golds) {
                        MyCoinsChangeActivity.this.edtSpendNum.setText("");
                        MyCoinsChangeActivity.this.txtCoinsBuy.setVisibility(0);
                        return;
                    }
                    MyCoinsChangeActivity.this.edtSpendNum.setText(String.format("%s", Double.valueOf(coins)));
                    MyCoinsChangeActivity.this.txtCoinsBuy.setVisibility(8);
                    return;
                }
                MyCoinsChangeActivity.this.edtSpendNum.setText("0.00");
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }
        });
        this.txtTobeans = (TextView) getViewAndClick(R.id.mycoins_txtTobeans);
        this.txtTogoods = (TextView) getViewAndClick(R.id.mycoins_txtTogoods);
        this.txtTobeans.setSelected(true);
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
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("method", "vsf2f.game.zqly.manor.coffer.query");
            jsonObject.put("bizContent", ComUtil.UTF(String.format("{\"userName\":\"%s\"}", DemoHelper.getInstance().getCurrentUserName())));
            getClient().post(33, ComUtil.getGAMEApi(this.context, getString(R.string.API_GAME_POST)), GameUtil.getVsSign(jsonObject.toString()), null, GoldCoinBean.class, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void requestBuy(int num) {
        try {
            getClient().setShowDialog("正在兑换，请稍候……");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("method", "vsf2f.game.zqly.manor.coffer.buy");
            jsonObject.put("bizContent", ComUtil.UTF(String.format("{\"userName\":\"%s\",\"type\":1,\"num\":%s}", DemoHelper.getInstance().getCurrentUserName(), Integer.valueOf(num))));
            getClient().post(11, ComUtil.getGAMEApi(this.context, getString(R.string.API_GAME_POST)), GameUtil.getVsSign(jsonObject.toString()), null, String.class, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void requestSell(int num) {
        try {
            getClient().setShowDialog("正在出售，请稍候……");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("method", "vsf2f.game.zqly.manor.coffer.sell");
            jsonObject.put("bizContent", ComUtil.UTF(String.format("{\"userName\":\"%s\",\"type\":0,\"num\":%s}", DemoHelper.getInstance().getCurrentUserName(), Integer.valueOf(num))));
            getClient().post(22, ComUtil.getGAMEApi(this.context, getString(R.string.API_GAME_POST)), GameUtil.getVsSign(jsonObject.toString()), null, String.class, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case 11:
                this.edtSpendNum.setText("0.00");
                this.edtTogetNum.setText("");
                MyToast.show(this.context, "兑换成功");
                setResult(-1);
                requestData();
                return;
            case 22:
                this.edtSellNum.setText("");
                this.edtTogetMoney.setText("￥0.00");
                MyToast.show(this.context, "出售成功");
                setResult(-1);
                requestData();
                return;
            case 33:
                String coins = "";
                GoldCoinBean goldCoinBean = (GoldCoinBean) result.getObj();
                int i = 0;
                while (true) {
                    if (i < goldCoinBean.getList().size()) {
                        if (goldCoinBean.getList().get(i).getType() == 0) {
                            coins = goldCoinBean.getList().get(i).getNum();
                            updateUI();
                        } else {
                            i++;
                        }
                    }
                }
                if (!TextUtils.isEmpty(coins)) {
                    getBundle().putString("golds", coins);
                    this.golds = Double.parseDouble(coins);
                    this.txtBalance.setText(coins);
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.mycoins_btnHelp /* 2131755425 */:
                Bundle bundle3 = new Bundle();
                bundle3.putString(Constant.FLAG, "http://zc.vjkeji.com/mobile/help/game_rules.html");
                bundle3.putBoolean(Constant.FLAG2, false);
                startAct(WebKitLocalActivity.class, bundle3);
                return;
            case R.id.mycoins_txtDetail /* 2131755442 */:
                startAct(MyBeansInfoActivity.class, getBundle());
                return;
            case R.id.mycoins_txtTobeans /* 2131755444 */:
                view.setSelected(true);
                this.txtTogoods.setSelected(false);
                return;
            case R.id.mycoins_txtTogoods /* 2131755445 */:
                MyToast.show(this.context, "暂未开放！");
                return;
            case R.id.mycoins_txtCoinsBuy /* 2131755447 */:
                Bundle bundle2 = new Bundle();
                bundle2.putString(Constant.FLAG, WebUtils.getTokenUrl(this.context, ComUtil.getZCApi(this.context, getString(R.string.API_GOLD_BUY))));
                bundle2.putBoolean(Constant.FLAG2, false);
                startAct(TbsWebActivity.class, bundle2);
                return;
            case R.id.mycoins_txtChangeBeans /* 2131755449 */:
                String beans = this.edtTogetNum.getText().toString();
                if (TextUtils.isEmpty(beans)) {
                    MyToast.show(this.context, "请输入金豆数量");
                    return;
                }
                int beansNum = Integer.parseInt(beans);
                if (beansNum == 0) {
                    MyToast.show(this.context, "金豆最少一个起");
                    return;
                } else {
                    requestBuy(beansNum);
                    return;
                }
            case R.id.mycoins_txtChangeMoney /* 2131755453 */:
                String coins2 = this.edtSellNum.getText().toString();
                if (!TextUtils.isEmpty(coins2)) {
                    int coinsNum = Integer.parseInt(coins2);
                    if (coinsNum >= 10) {
                        requestSell(coinsNum);
                        return;
                    } else {
                        MyToast.show(this.context, "请输入大于10的数量");
                        return;
                    }
                } else {
                    MyToast.show(this.context, "请输入金币数量");
                    return;
                }
            default:
                return;
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11) {
            requestData();
        }
    }
}
