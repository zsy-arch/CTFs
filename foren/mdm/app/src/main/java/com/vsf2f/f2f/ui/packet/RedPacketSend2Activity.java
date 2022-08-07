package com.vsf2f.f2f.ui.packet;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.easeui.utils.EaseUserUtils;
import com.easeui.utils.RedPacketUtil;
import com.easeui.widget.EaseAlertDialog;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.NavGroup;
import com.hy.frame.view.NavView;
import com.hy.http.AjaxParams;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.GroupInfoBean;
import com.vsf2f.f2f.bean.UserFundCenterBean;
import com.vsf2f.f2f.bean.packet.RedPacketInfoBean;
import com.vsf2f.f2f.ui.dialog.PayPwdDialog;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;
import com.vsf2f.f2f.ui.utils.FingerUtil;
import com.vsf2f.f2f.ui.utils.GameUtil;
import com.vsf2f.f2f.ui.utils.web.WebUtils;
import com.vsf2f.f2f.ui.view.PointLengthFilter;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class RedPacketSend2Activity extends BaseActivity implements NavGroup.OnCheckedChangeListener {
    private TextView btn_ensure;
    private TextView btn_pay;
    private TextView btn_recharge;
    private EditText et_content;
    private EditText et_money;
    private EditText et_num;
    private String md5pwd;
    private PayPwdDialog payDialog;
    private RedPacketInfoBean redPacketInfo;
    private TextView tv_money;
    private TextView txt_cost;
    private int num = 0;
    private double money = 0.0d;
    private int cusPage = 1;
    private int type = 1;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_send_redpacket2;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.send_redpacket, R.drawable.icon_help_white);
        this.txt_cost = (TextView) getView(R.id.txt_cost);
        this.tv_money = (TextView) getView(R.id.tv_money);
        this.et_num = (EditText) getView(R.id.et_num);
        this.et_money = (EditText) getView(R.id.et_money);
        this.et_money.setFilters(new InputFilter[]{new PointLengthFilter(5)});
        this.et_content = (EditText) getView(R.id.et_content);
        this.btn_recharge = (TextView) getViewAndClick(R.id.red_pay_recharge);
        this.btn_ensure = (TextView) getViewAndClick(R.id.btn_ensure);
        this.btn_pay = (TextView) getViewAndClick(R.id.btn_pay);
        setOnClickListener(R.id.btn_finish);
        ((NavGroup) getView(R.id.red_packet_grouptype)).setOnCheckedChangeListener(this);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        String groupId = getBundle().getString("username");
        getGroupInfo(groupId);
        this.redPacketInfo = new RedPacketInfoBean(groupId);
        EMClient.getInstance().groupManager().getGroup(groupId);
        this.et_money.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.packet.RedPacketSend2Activity.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String str = charSequence.toString();
                if (!TextUtils.isEmpty(str)) {
                    RedPacketSend2Activity.this.money = Double.parseDouble(str);
                    if (RedPacketSend2Activity.this.type == 0) {
                        RedPacketSend2Activity.this.txt_cost.setText(HyUtil.formatToMoney(Double.valueOf(RedPacketSend2Activity.this.money * RedPacketSend2Activity.this.num)));
                    } else {
                        RedPacketSend2Activity.this.txt_cost.setText(HyUtil.formatToMoney(Double.valueOf(RedPacketSend2Activity.this.money)));
                    }
                    RedPacketSend2Activity.this.btn_ensure.setEnabled(true);
                    return;
                }
                RedPacketSend2Activity.this.money = 0.0d;
                RedPacketSend2Activity.this.txt_cost.setText("0.00");
                RedPacketSend2Activity.this.btn_ensure.setEnabled(false);
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }
        });
        this.et_num.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.packet.RedPacketSend2Activity.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String str = charSequence.toString();
                if (!TextUtils.isEmpty(str)) {
                    RedPacketSend2Activity.this.num = Integer.parseInt(str);
                    if (RedPacketSend2Activity.this.type == 0) {
                        RedPacketSend2Activity.this.txt_cost.setText(HyUtil.formatToMoney(Double.valueOf(RedPacketSend2Activity.this.money * RedPacketSend2Activity.this.num)));
                    }
                    RedPacketSend2Activity.this.btn_ensure.setEnabled(true);
                    return;
                }
                RedPacketSend2Activity.this.num = 0;
                if (RedPacketSend2Activity.this.type == 0) {
                    RedPacketSend2Activity.this.txt_cost.setText("0.00");
                }
                RedPacketSend2Activity.this.btn_ensure.setEnabled(false);
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    public void getGroupInfo(String groupId) {
        String bizId = EaseUserUtils.getGroupBizId(groupId);
        getClient().get(R.string.API_GET_GROUP_INFO, ComUtil.getF2FApi(this.context, getString(R.string.API_GET_GROUP_INFO, new Object[]{bizId})) + "?countUser=1", new AjaxParams(), GroupInfoBean.class, false);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    public void requestBalance() {
        getClient().get(R.string.API_USER_FUNDS, ComUtil.getZCApi(this.context, getString(R.string.API_USER_FUNDS)), UserFundCenterBean.class);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ensure /* 2131755180 */:
                if (this.money == 0.0d) {
                    MyToast.show(this.context, (int) R.string.toast_input_zero_money);
                    return;
                } else if (this.num == 0) {
                    MyToast.show(this.context, (int) R.string.toast_input_zero_num);
                    return;
                } else if (this.num > 100) {
                    MyToast.show(this.context, (int) R.string.toast_num_too_big);
                    return;
                } else if (this.type == 1 && this.money < 0.01d * this.num) {
                    MyToast.show(this.context, (int) R.string.redpacket_send_toosmall);
                    return;
                } else if ((this.type == 0 && this.money * this.num > 20000.0d) || (this.type == 1 && this.money > 20000.0d)) {
                    MyToast.show(this.context, (int) R.string.redpacket_send_toobig);
                    return;
                } else if ((this.type == 0 && this.money > 200.0d) || (this.type == 1 && this.money / this.num > 200.0d)) {
                    MyToast.show(this.context, (int) R.string.redpacket_send_toobig3);
                    return;
                } else if (HyUtil.hasSpecialChar(this.et_content.getText().toString())) {
                    MyToast.show(this.context, (int) R.string.toast_cant_character);
                    return;
                } else {
                    String cost = this.txt_cost.getText().toString();
                    ((TextView) findViewById(R.id.red_pay_txtMoney)).setText(cost);
                    ((TextView) findViewById(R.id.red_pay_txtMoney2)).setText(cost);
                    hideSoftKeyboard();
                    requestBalance();
                    changePage(2);
                    return;
                }
            case R.id.red_pay_recharge /* 2131755564 */:
                Bundle bundle = new Bundle();
                bundle.putString(Constant.FLAG, WebUtils.getTokenUrl(this.context, getString(R.string.URL_MONEY_RECHARGE)));
                startActForResult(WebKitLocalActivity.class, bundle, 111);
                return;
            case R.id.btn_pay /* 2131755565 */:
                final String cost2 = this.txt_cost.getText().toString();
                this.payDialog = new PayPwdDialog(this.context, new PayPwdDialog.OutPwdListener() { // from class: com.vsf2f.f2f.ui.packet.RedPacketSend2Activity.3
                    @Override // com.vsf2f.f2f.ui.dialog.PayPwdDialog.OutPwdListener
                    public void outPwd(String password) {
                        RedPacketSend2Activity.this.md5pwd = password;
                        RedPacketSend2Activity.this.sendRedPacket(cost2, password);
                    }
                });
                this.payDialog.show();
                return;
            case R.id.btn_finish /* 2131755568 */:
                onLeftClick();
                return;
            default:
                return;
        }
    }

    public void changePage(int page) {
        this.cusPage = page;
        if (page == 1) {
            setTitle(R.string.send_redpacket);
            findViewById(R.id.red_packet_page1).setVisibility(0);
            findViewById(R.id.red_packet_page2).setVisibility(8);
        } else if (page == 2) {
            setTitle(R.string.pay);
            findViewById(R.id.red_packet_page1).setVisibility(8);
            findViewById(R.id.red_packet_page2).setVisibility(0);
        } else if (page == 3) {
            setTitle(R.string.pay_success);
            findViewById(R.id.red_packet_page2).setVisibility(8);
            findViewById(R.id.red_packet_page3).setVisibility(0);
            initFinger();
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        onLeftClick();
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onLeftClick() {
        if (this.cusPage == 2) {
            changePage(1);
        } else {
            finish();
        }
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        Bundle bundle = new Bundle();
        bundle.putString(Constant.FLAG, ComUtil.getZCApi(this.context, getStringIds(Integer.valueOf((int) R.string.URL_HELP_REDPAKCET))));
        startAct(WebKitLocalActivity.class, bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendRedPacket(String cost, String pwdMd5) {
        String content = this.et_content.getText().toString();
        if (TextUtils.isEmpty(content)) {
            content = this.et_content.getHint().toString();
        }
        try {
            getClient().setShowDialog(true);
            JSONObject jsonContent = new JSONObject();
            jsonContent.put("type", this.type);
            this.redPacketInfo.setModule(1);
            jsonContent.put(f.aj, 1);
            jsonContent.put("receiveGroup", this.redPacketInfo.toGroupId);
            this.redPacketInfo.setRedpacketKeyRedpacketGreeting(content);
            jsonContent.put("description", content);
            if (this.type == 0) {
                this.redPacketInfo.setSingleAmount(this.money + "");
                jsonContent.put("singleAmount", this.money);
            }
            this.redPacketInfo.setTotalAmount(cost);
            jsonContent.put("totalAmount", cost);
            jsonContent.put("totalNum", this.num);
            jsonContent.put("payPwd", pwdMd5);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("method", "vsf2f.account.cash.redpacket.sendout");
            jsonObject.put("bizContent", ComUtil.UTF(jsonContent.toString()));
            getClient().post(22, ComUtil.getZCApi(this.context, getString(R.string.API_RED_PACKET)), GameUtil.getVsSign(jsonObject.toString()), null, String.class, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case 22:
                try {
                    this.redPacketInfo.redPacketId = new JSONObject(result.getObjStr()).optString("baseId");
                    this.redPacketInfo.moneyType = this.type;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("red", this.redPacketInfo);
                setResult(-1, new Intent().putExtra(Constant.BUNDLE, bundle));
                this.payDialog.dismiss();
                ComUtil.setPaypwd(this.context, this.md5pwd);
                ((TextView) findViewById(R.id.red_pay_txtMoney4)).setText(this.redPacketInfo.getTotalAmount());
                changePage(3);
                EMClient.getInstance().chatManager().sendMessage(RedPacketUtil.createRPMessage(this.context, this.redPacketInfo, EMMessage.ChatType.GroupChat));
                return;
            case R.string.API_GET_GROUP_INFO /* 2131296355 */:
                ((TextView) findViewById(R.id.tv_prompt_group)).setText(String.format("群人数 %d人", Integer.valueOf(((GroupInfoBean) result.getObj()).getMembersNum())));
                return;
            case R.string.API_USER_CHANGE_DATA_EXT /* 2131296462 */:
                if (((Boolean) result.getObj()).booleanValue()) {
                    setResult(-1);
                    try {
                        ComUtil.setFinger(this.context, true);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    new EaseAlertDialog(this.context, R.string.already_open_finger, null, false).show();
                    return;
                }
                return;
            case R.string.API_USER_FUNDS /* 2131296467 */:
                UserFundCenterBean centerBean = (UserFundCenterBean) result.getObj();
                if (centerBean != null) {
                    String balance = centerBean.getAccount().getCash();
                    if (!TextUtils.isEmpty(balance)) {
                        TextView txt3 = (TextView) findViewById(R.id.red_pay_txtMoney3);
                        if (Double.parseDouble(balance) >= Double.parseDouble(this.txt_cost.getText().toString())) {
                            this.btn_pay.setEnabled(true);
                            this.btn_recharge.setVisibility(8);
                            txt3.setTextColor(getResources().getColor(R.color.black));
                            txt3.setText(HyUtil.formatToMoney(balance));
                            return;
                        }
                        this.btn_pay.setEnabled(false);
                        this.btn_recharge.setVisibility(0);
                        txt3.setTextColor(getResources().getColor(R.color.txt_red));
                        txt3.setText("余额不足,请充值钱包！");
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void initFinger() {
        final int prompt;
        if (FingerUtil.checkFingerModule(this.context) && (prompt = AppShare.get(this.context).getInt("toast_finger_num")) < 3 && !ComUtil.isFinger(this.context)) {
            new EaseAlertDialog(this.context, "", "开启指纹支付，支付时可通过验证指纹快速完成付款.", (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.packet.RedPacketSend2Activity.4
                @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                public void onResult(boolean confirmed, Bundle bundle) {
                    if (confirmed) {
                        RedPacketSend2Activity.this.requestFinger(ComUtil.getPaypwd(RedPacketSend2Activity.this.context));
                        return;
                    }
                    AppShare.get(RedPacketSend2Activity.this.context).putInt("toast_finger_num", prompt + 1);
                }
            }, true).setOkBtn("开启指纹支付").show();
        }
    }

    public void requestFinger(String pwdMd5) {
        JSONObject jsonObj = new JSONObject();
        try {
            JSONObject jsonDatas = new JSONObject();
            JSONObject jsonCheck = new JSONObject();
            jsonDatas.put("fingerprintPayment", 1);
            jsonCheck.put("payPwd", pwdMd5);
            jsonObj.put("type", "PAY_PWD");
            jsonObj.put("datas", jsonDatas);
            jsonObj.put("check", jsonCheck);
            MyLog.e(jsonObj.toString() + "");
            getClient().setShowDialog(true);
            getClient().post(R.string.API_USER_CHANGE_DATA_EXT, ComUtil.getZCApi(this.context, getString(R.string.API_USER_CHANGE_DATA_EXT)), jsonObj.toString() + "", null, Boolean.class, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111) {
            requestBalance();
        }
    }

    @Override // com.hy.frame.view.NavGroup.OnCheckedChangeListener
    public void onCheckedChanged(NavGroup group, NavView nav, int checkedId) {
        TextView tvfight = (TextView) findViewById(R.id.tv_prompt_fight);
        if (checkedId == R.id.nv_type1) {
            this.type = 1;
            this.tv_money.setText("总金额");
            changedPacket(false);
            tvfight.setText(R.string.redpacket_send_fight);
            return;
        }
        this.type = 0;
        this.tv_money.setText("单个金额");
        changedPacket(true);
        tvfight.setText(R.string.redpacket_send_normal);
    }

    private void changedPacket(boolean toNormal) {
        if (this.num != 0 && this.money != 0.0d) {
            if (toNormal) {
                this.money = HyUtil.formatMoney2(Double.valueOf(this.money / this.num)).doubleValue();
                this.txt_cost.setText(HyUtil.formatToMoney(Double.valueOf(this.money * this.num)));
            } else {
                this.money *= this.num;
                this.txt_cost.setText(HyUtil.formatToMoney(Double.valueOf(this.money)));
            }
            this.et_money.setText(HyUtil.formatToMoney(Double.valueOf(this.money)));
        }
    }
}
