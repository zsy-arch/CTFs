package com.vsf2f.f2f.ui.needs.demand;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.em.DemoHelper;
import com.em.ui.ChatActivity;
import com.google.gson.Gson;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.OrderDetailHoldView;
import com.vsf2f.f2f.bean.ApplyPayInfoBean;
import com.vsf2f.f2f.bean.DemandDetailBean;
import com.vsf2f.f2f.bean.OrderDetailBean;
import com.vsf2f.f2f.bean.result.CertUserInfoBean;
import com.vsf2f.f2f.ui.dialog.CancelOrderDialog;
import com.vsf2f.f2f.ui.dialog.OrderRepriceDialog;
import com.vsf2f.f2f.ui.dialog.SureChoiceDialog;
import com.vsf2f.f2f.ui.needs.service.ServiceInfoActivity;
import com.vsf2f.f2f.ui.qrcode.QrcodeUtil;

/* loaded from: classes2.dex */
public class OrderDetailActivity extends BaseActivity {
    private CancelOrderDialog cancelDialog;
    private int doPosition;
    private OrderDetailHoldView holdView;
    private ImageView iv_icon;
    private LinearLayout ll_address;
    private LinearLayout ll_code;
    private LinearLayout ll_message;
    private LinearLayout ll_modify;
    private LinearLayout ll_operation;
    private OrderDetailBean orderDetailBean;
    private int orderId;
    private OrderRepriceDialog orderRepriceDialog;
    private ImageView order_erweima;
    private SureChoiceDialog sureChoiceDialog;
    private TextView tv_address;
    private TextView tv_amount_price;
    private TextView tv_content;
    private TextView tv_modify;
    private TextView tv_modify_price;
    private TextView tv_order_no;
    private TextView tv_order_state;
    private TextView tv_order_time;
    private TextView tv_pay_type;
    private TextView tv_price;
    private TextView tv_service_code;
    private TextView tv_service_msg;
    private TextView tv_service_type;
    private TextView tv_sum_price;
    private TextView tv_tel;
    private TextView tv_title;
    private TextView tv_user;
    private TextView tv_user_type;
    private int type = 0;
    private boolean isService = false;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.order_info, 0);
        this.orderId = getBundle().getInt("id");
        this.isService = getBundle().getBoolean("isService");
        this.iv_icon = (ImageView) getView(R.id.iv_icon);
        this.order_erweima = (ImageView) getView(R.id.order_erweima);
        this.ll_operation = (LinearLayout) getView(R.id.ll_operation);
        this.ll_message = (LinearLayout) getView(R.id.ll_message);
        this.holdView = new OrderDetailHoldView(this.ll_operation, this);
        this.tv_modify = (TextView) getViewAndClick(R.id.tv_modify);
        this.tv_pay_type = (TextView) getView(R.id.tv_pay_type);
        this.tv_sum_price = (TextView) getView(R.id.tv_sum_price);
        this.tv_modify_price = (TextView) getView(R.id.tv_modify_price);
        this.tv_amount_price = (TextView) getView(R.id.tv_amount_price);
        this.tv_service_code = (TextView) getView(R.id.tv_service_code);
        this.tv_service_type = (TextView) getView(R.id.tv_service_type);
        this.tv_service_msg = (TextView) getView(R.id.tv_service_msg);
        this.ll_modify = (LinearLayout) getView(R.id.ll_modify);
        this.ll_code = (LinearLayout) getView(R.id.ll_code);
        this.ll_code.setVisibility(8);
        this.tv_tel = (TextView) getView(R.id.tv_tel);
        this.tv_user = (TextView) getView(R.id.tv_user);
        this.tv_title = (TextView) getView(R.id.tv_title);
        this.tv_price = (TextView) getView(R.id.tv_price);
        this.tv_address = (TextView) getView(R.id.tv_address);
        this.tv_content = (TextView) getView(R.id.tv_content);
        this.tv_order_no = (TextView) getView(R.id.tv_order_no);
        this.tv_order_time = (TextView) getView(R.id.tv_order_time);
        this.tv_order_state = (TextView) getView(R.id.tv_order_state);
        this.tv_user_type = (TextView) getView(R.id.tv_user_type);
        this.ll_address = (LinearLayout) getView(R.id.ll_address);
        setOnClickListener(R.id.ll_order_action);
    }

    public void initDialog() {
        this.sureChoiceDialog = new SureChoiceDialog(this, this);
        this.cancelDialog = new CancelOrderDialog(this, new CancelOrderDialog.CancelOrderImpl() { // from class: com.vsf2f.f2f.ui.needs.demand.OrderDetailActivity.1
            @Override // com.vsf2f.f2f.ui.dialog.CancelOrderDialog.CancelOrderImpl
            public void cancelOrder(String checkReason) {
                OrderDetailActivity.this.closeOrder(checkReason);
            }
        });
        this.cancelDialog.setIsService(this.isService);
        this.orderRepriceDialog = new OrderRepriceDialog(this, this);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        initDialog();
        if (!this.isService) {
            this.ll_message.setVisibility(8);
        }
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        if (this.isService) {
            getServiceOrder();
        } else {
            getDetail();
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    public void toDetail(View view) {
        Bundle bundle = new Bundle();
        if (this.isService) {
            bundle.putInt("id", this.orderDetailBean.getShareServiceObj().getMoId());
            bundle.putInt("shotid", this.orderDetailBean.getShareServiceSnapshotId());
            startAct(ServiceInfoActivity.class, bundle);
            return;
        }
        bundle.putInt("id", this.orderDetailBean.getShareObj().getMoId());
        bundle.putInt("shotid", this.orderDetailBean.getShareServiceSnapshotId());
        startAct(DemandInfoActivity.class, bundle);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.tv_pay /* 2131756011 */:
                doFunction(8);
                return;
            case R.id.tv_cancel /* 2131756080 */:
                doFunction(0);
                return;
            case R.id.tv_reject /* 2131756081 */:
                doFunction(9);
                return;
            case R.id.tv_accept /* 2131756082 */:
                doFunction(10);
                return;
            case R.id.tv_sure /* 2131756083 */:
                doFunction(2);
                return;
            case R.id.tv_comment /* 2131756084 */:
                doFunction(3);
                return;
            case R.id.tv_doservice /* 2131756085 */:
                doFunction(4);
                return;
            case R.id.tv_return /* 2131756086 */:
                doFunction(1);
                return;
            case R.id.tv_serviced /* 2131756087 */:
                doFunction(5);
                return;
            case R.id.tv_deal /* 2131756088 */:
                doFunction(6);
                return;
            case R.id.tv_refund /* 2131756089 */:
                doFunction(7);
                return;
            case R.id.ll_order_action /* 2131756092 */:
                if (this.orderDetailBean != null) {
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("isService", this.isService);
                    bundle.putSerializable("data", this.orderDetailBean);
                    startAct(OrderActionActivity.class, bundle);
                    return;
                }
                return;
            case R.id.tv_modify /* 2131756101 */:
                this.orderRepriceDialog.show();
                return;
            case R.id.btn_sure /* 2131756169 */:
                this.sureChoiceDialog.dismiss();
                if (this.doPosition == 2) {
                    getClient().setShowDialog(true);
                    AjaxParams params = new AjaxParams();
                    if (this.isService) {
                        params.put("shareServiceOrderId", this.orderDetailBean.getMoId());
                        getClient().post(R.string.API_SERVICE_CONFIRMORDER, ComUtil.getXDDApi(this.context, getString(R.string.API_SERVICE_CONFIRMORDER)), params, String.class);
                        return;
                    }
                    params.put("shareId", this.orderDetailBean.getShareObj().getMoId());
                    params.put("shareOrderId", this.orderDetailBean.getMoId());
                    getClient().post(R.string.API_CONFIRM_ORDER, ComUtil.getXDDApi(this.context, getString(R.string.API_CONFIRM_ORDER)), params, String.class);
                    return;
                } else if (this.doPosition == 9) {
                    refuseOrder();
                    return;
                } else if (this.doPosition == 10) {
                    acceptOrder();
                    return;
                } else {
                    return;
                }
            case R.id.btn_cancel /* 2131756208 */:
                this.sureChoiceDialog.dismiss();
                return;
            case R.id.btn_cancel_price /* 2131756265 */:
                this.orderRepriceDialog.dismiss();
                return;
            case R.id.btn_sure_price /* 2131756266 */:
                this.orderRepriceDialog.dismiss();
                changeFund(this.orderRepriceDialog.getPrice());
                return;
            default:
                return;
        }
    }

    public void toChat(View view) {
        String username;
        if (this.orderDetailBean != null) {
            Bundle bundle = new Bundle();
            if (this.isService) {
                if (this.type == 1) {
                    username = this.orderDetailBean.getShareServiceObj().getServiceUserObj().getUserName();
                } else {
                    username = this.orderDetailBean.getShareServiceObj().getPublishUserObj().getUserName();
                }
            } else if (this.type == 0) {
                username = this.orderDetailBean.getShareObj().getServiceUserObj().getUserName();
            } else {
                username = this.orderDetailBean.getShareObj().getPublishUserObj().getUserName();
            }
            bundle.putString("username", username);
            startAct(ChatActivity.class, bundle);
        }
    }

    private void sureOrder() {
        this.sureChoiceDialog.setContent(getString(R.string.prompt), getString(R.string.is_sure_service_) + this.orderDetailBean.getMoId());
        this.sureChoiceDialog.show();
    }

    private void changeFund(String price) {
        String url;
        if (!TextUtils.isEmpty(price)) {
            try {
                Double.valueOf(price);
                getClient().setShowDialog(true);
                AjaxParams params = new AjaxParams();
                if (this.isService) {
                    params.put("shareServiceOrderId", this.orderDetailBean.getMoId());
                    url = ComUtil.getXDDApi(this.context, getString(R.string.API_SERVICE_CHANGEFUNDS));
                    params.put("changeType", (this.type + 1) % 2);
                } else {
                    params.put("shareOrderId", this.orderDetailBean.getMoId());
                    url = ComUtil.getXDDApi(this.context, getString(R.string.API_CHANGE_FUNDS));
                    params.put("changeType", this.type);
                }
                params.put("changeMoney", price);
                params.put("description", "");
                getClient().post(R.string.API_CHANGE_FUNDS, url, params, ApplyPayInfoBean.class, false);
            } catch (Exception e) {
                MyToast.show(getApplicationContext(), (int) R.string.money_not_right);
            }
        }
    }

    private void completeOrder() {
        getClient().setShowDialog(true);
        AjaxParams params = new AjaxParams();
        if (this.isService) {
            params.put("shareServiceOrderId", this.orderDetailBean.getMoId());
            getClient().setShowDialog(true);
            getClient().post(R.string.API_SERVICE_COMPLETEORDER, ComUtil.getXDDApi(this.context, getString(R.string.API_SERVICE_COMPLETEORDER)), params, String.class);
            return;
        }
        params.put("shareOrderId", this.orderDetailBean.getMoId());
        params.put("shareId", this.orderDetailBean.getShareObj().getMoId());
        getClient().post(R.string.API_COMPLETE_ORDER, ComUtil.getXDDApi(this.context, getString(R.string.API_COMPLETE_ORDER)), params, String.class);
    }

    private void refuseOrder() {
        AjaxParams params = new AjaxParams();
        params.put("shareServiceOrderId", this.orderDetailBean.getMoId());
        getClient().setShowDialog(true);
        getClient().post(R.string.API_SERVICE_REJECTSERVICE, ComUtil.getXDDApi(this.context, getString(R.string.API_SERVICE_REJECTSERVICE)), params, String.class);
    }

    private void acceptOrder() {
        AjaxParams params = new AjaxParams();
        params.put("shareServiceOrderId", this.orderDetailBean.getMoId());
        getClient().setShowDialog(true);
        getClient().post(R.string.API_SERVICE_AGREESERVICE, ComUtil.getXDDApi(this.context, getString(R.string.API_SERVICE_AGREESERVICE)), params, String.class);
    }

    public void closeOrder(String reason) {
        getClient().setShowDialog(true);
        AjaxParams params = new AjaxParams();
        if (this.isService) {
            params.put("shareServiceOrderId", this.orderDetailBean.getMoId());
            params.put("closeReason", reason);
            getClient().post(R.string.API_SERVICE_CANCLE, ComUtil.getXDDApi(this.context, getString(R.string.API_SERVICE_CANCLE)), params, String.class, false);
            return;
        }
        params.put("shareOrderId", this.orderDetailBean.getMoId());
        params.put("closeReason", reason);
        getClient().post(R.string.API_CLOSE_REWARD, ComUtil.getXDDApi(this.context, getString(R.string.API_CLOSE_REWARD)), params, String.class, false);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void doFunction(int function) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("isService", this.isService);
        this.doPosition = function;
        switch (function) {
            case 0:
                this.cancelDialog.show();
                return;
            case 1:
                bundle.putInt("moId", this.orderDetailBean.getMoId());
                startAct(RefundActivity.class, bundle);
                return;
            case 2:
                sureOrder();
                return;
            case 3:
                bundle.putInt("type", this.type);
                bundle.putBoolean("isService", this.isService);
                bundle.putSerializable("data", this.orderDetailBean);
                startAct(CommentOrderActivity.class, bundle);
                return;
            case 4:
                bundle.putInt("id", this.orderDetailBean.getMoId());
                bundle.putBoolean("isService", this.isService);
                startAct(StartServiceActivity.class, bundle);
                return;
            case 5:
                completeOrder();
                return;
            case 6:
                bundle.putBoolean("deal", true);
                break;
            case 7:
                break;
            case 8:
                Gson gson = new Gson();
                DemandDetailBean beanTemp = (DemandDetailBean) gson.fromJson(gson.toJson(this.orderDetailBean.getShareServiceObj()), (Class<Object>) DemandDetailBean.class);
                beanTemp.setPayAmount(this.orderDetailBean.getAmount());
                beanTemp.setDemandSnapShotId(this.orderDetailBean.getShareServiceSnapshotId());
                beanTemp.setBizId(this.orderDetailBean.getMoId());
                beanTemp.setMoId(this.orderDetailBean.getMoId());
                beanTemp.setBizType(6);
                bundle.putSerializable("needinfo", beanTemp);
                bundle.putString(Constant.FLAG_TYPE, "");
                bundle.putInt(Constant.FLAG_TITLE, R.string.buy_service);
                startAct(EntrustActivity.class, bundle);
                return;
            case 9:
                this.sureChoiceDialog.setContent("提示", "是否拒绝订单：" + this.orderDetailBean.getMoId());
                this.sureChoiceDialog.show();
                return;
            case 10:
                this.sureChoiceDialog.setContent("提示", "是否确认接单：" + this.orderDetailBean.getMoId());
                this.sureChoiceDialog.show();
                return;
            default:
                return;
        }
        bundle.putInt("type", this.type);
        bundle.putBoolean("isService", this.isService);
        bundle.putSerializable("data", this.orderDetailBean);
        startAct(DealDisputeRefundActivity.class, bundle);
    }

    private void getDetail() {
        getClient().setShowDialog(true);
        getClient().get(R.string.API_ORDER_DETAIL, ComUtil.getXDDApi(this.context, getString(R.string.API_ORDER_DETAIL)) + "?shareOrderId=" + this.orderId, OrderDetailBean.class);
    }

    private void getServiceOrder() {
        getClient().setShowDialog(true);
        getClient().get(R.string.API_ORDER_SERVICE, ComUtil.getXDDApi(this.context, getString(R.string.API_ORDER_SERVICE)) + "?shareServiceOrderId=" + this.orderId, OrderDetailBean.class);
    }

    private void refreshData() {
        if (this.isService) {
            getServiceOrder();
        } else {
            getDetail();
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        int tempType;
        OrderDetailBean.ShareObjBean shareObj;
        String other;
        String statusStr;
        String codeStr;
        if (result.getErrorCode() != 0) {
            MyToast.show(getApplicationContext(), result.getMsg());
            return;
        }
        switch (result.getRequestCode()) {
            case R.string.API_CHANGE_FUNDS /* 2131296299 */:
                ApplyPayInfoBean payInfoBean = (ApplyPayInfoBean) result.getObj();
                if (payInfoBean.getNeedPay() == 0) {
                    MyToast.show(getApplicationContext(), "改价成功");
                    if (this.isService) {
                        getServiceOrder();
                        return;
                    } else {
                        getDetail();
                        return;
                    }
                } else {
                    payInfoBean.setBizId(payInfoBean.getMoId() + "");
                    payInfoBean.setPayTitle("订单改价");
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("payinfo", payInfoBean);
                    bundle.putString(Constant.FLAG_TYPE, getClass().getName());
                    bundle.putInt(Constant.FLAG_TITLE, R.string.closing_price);
                    startAct(EntrustActivity.class, bundle);
                    return;
                }
            case R.string.API_CLOSE_REWARD /* 2131296313 */:
            case R.string.API_SERVICE_CANCLE /* 2131296406 */:
                MyToast.show(getApplicationContext(), "订单取消成功");
                refreshData();
                return;
            case R.string.API_COMPLETE_ORDER /* 2131296316 */:
            case R.string.API_SERVICE_COMPLETEORDER /* 2131296410 */:
                MyToast.show(getApplicationContext(), "申请成功，等待对方确认");
                refreshData();
                return;
            case R.string.API_CONFIRM_ORDER /* 2131296317 */:
            case R.string.API_SERVICE_CONFIRMORDER /* 2131296411 */:
                MyToast.show(getApplicationContext(), "确认成功");
                refreshData();
                return;
            case R.string.API_ORDER_DETAIL /* 2131296386 */:
            case R.string.API_ORDER_SERVICE /* 2131296387 */:
                this.orderDetailBean = (OrderDetailBean) result.getObj();
                if (this.orderDetailBean.getCreator().equals(DemoHelper.getInstance().getCurrentUserName())) {
                    if (this.isService) {
                        this.type = 1;
                    } else {
                        this.type = 0;
                    }
                } else if (this.isService) {
                    this.type = 0;
                } else {
                    this.type = 1;
                }
                if (this.isService) {
                    tempType = (this.type + 1) % 2;
                    shareObj = this.orderDetailBean.getShareServiceObj();
                } else {
                    tempType = this.type;
                    shareObj = this.orderDetailBean.getShareObj();
                }
                if (tempType == 0) {
                    this.orderRepriceDialog.setTitle("我要加价(补贴)", "最少加价0.01元");
                    this.tv_modify.setText("补差价");
                    this.tv_user_type.setText("帮助人信息");
                    CertUserInfoBean userObjBean = shareObj.getServiceUserObj();
                    String code = this.orderDetailBean.getValidCode();
                    this.tv_service_code.setText(code);
                    if (!TextUtils.isEmpty(code)) {
                        this.ll_code.setVisibility(0);
                        if (this.isService) {
                            codeStr = "vsf2f://startService?moId=" + this.orderDetailBean.getMoId() + "&validCode=" + this.orderDetailBean.getValidCode();
                        } else {
                            codeStr = "vsf2f://startShare?moId=" + this.orderDetailBean.getMoId() + "&validCode=" + this.orderDetailBean.getValidCode();
                        }
                        this.order_erweima.setImageBitmap(QrcodeUtil.createQrImage(codeStr, 100, 100));
                    } else {
                        this.ll_code.setVisibility(8);
                    }
                    if (this.isService) {
                        this.tv_user.setText(shareObj.getContactUser());
                        this.tv_tel.setText(shareObj.getContactPhone() + "");
                        this.ll_address.setVisibility(0);
                        this.tv_address.setText(shareObj.getAddress());
                    } else {
                        this.tv_user.setText(userObjBean.getNickName());
                        this.tv_tel.setText(userObjBean.getPhone() + "");
                        this.ll_address.setVisibility(8);
                    }
                } else {
                    this.orderRepriceDialog.setTitle("我要改价(减价)", "最少减价0.01元");
                    this.ll_code.setVisibility(8);
                    this.tv_modify.setText("改 价");
                    CertUserInfoBean userObjBean2 = shareObj.getPublishUserObj();
                    this.tv_user_type.setText("求助人信息");
                    this.ll_address.setVisibility(0);
                    if (this.isService) {
                        this.tv_user.setText(this.orderDetailBean.getContactUser());
                        this.tv_tel.setText(this.orderDetailBean.getContactPhone() + "");
                        this.tv_address.setText(this.orderDetailBean.getServiceAddress());
                    } else {
                        this.tv_user.setText(userObjBean2.getNickName());
                        this.tv_tel.setText(userObjBean2.getPhone() + "");
                        this.tv_address.setText(shareObj.getAddress());
                    }
                }
                this.tv_order_no.setText(this.orderDetailBean.getMoId() + "");
                this.tv_order_time.setText(this.orderDetailBean.getCreateTime());
                if (this.orderDetailBean.getStatus() != 10 && this.orderDetailBean.getStatus() != 11 && this.orderDetailBean.getStatus() != 13) {
                    this.tv_modify.setVisibility(8);
                } else if (this.isService || shareObj.getPayType() == 0) {
                    this.tv_modify.setVisibility(0);
                } else {
                    this.tv_modify.setVisibility(8);
                }
                this.tv_sum_price.setText("￥" + this.orderDetailBean.getAmount());
                if (this.orderDetailBean.getOtherAmount() == 0.0d) {
                    this.ll_modify.setVisibility(8);
                    this.tv_amount_price.setText("￥" + this.orderDetailBean.getAmount());
                } else {
                    this.ll_modify.setVisibility(0);
                    String other2 = this.orderDetailBean.getOtherAmount() + "";
                    if (other2.startsWith("-")) {
                        other = other2.replace("-", "- ￥");
                    } else {
                        other = "+ ￥" + other2;
                    }
                    this.tv_modify_price.setText(other);
                    this.tv_amount_price.setText("￥" + HyUtil.formatMoney(Double.valueOf(this.orderDetailBean.getAmount() + this.orderDetailBean.getOtherAmount())));
                }
                if (this.isService) {
                    this.ll_address.setVisibility(0);
                    this.tv_service_msg.setText(this.orderDetailBean.getBookMessage());
                }
                if (shareObj.getPayType() == 0) {
                    this.tv_pay_type.setText("在线支付");
                } else {
                    this.tv_pay_type.setText("线下交易");
                }
                this.tv_service_type.setText(shareObj.getServiceModeStr());
                if (HyUtil.isEmpty(shareObj.getImgUrlList())) {
                    Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.mipmap.img_no_pic)).into(this.iv_icon);
                } else {
                    Glide.with((FragmentActivity) this).load(shareObj.getImgUrlList().get(0)).error((int) R.mipmap.img_no_pic).into(this.iv_icon);
                }
                this.tv_title.setText(shareObj.getTitle());
                this.tv_content.setText(shareObj.getDescription());
                this.tv_price.setText("￥" + shareObj.getReward() + " / " + shareObj.getUnit());
                switch (this.orderDetailBean.getStatus()) {
                    case 10:
                        statusStr = "进行中";
                        if (!this.isService) {
                            if (this.type != 0) {
                                this.holdView.setFunction(0, 4);
                                break;
                            } else {
                                this.holdView.setFunction(0);
                                break;
                            }
                        } else if (this.type != 0) {
                            this.holdView.setFunction(0);
                            break;
                        } else {
                            this.holdView.setFunction(0, 4);
                            break;
                        }
                    case 11:
                        statusStr = "服务中";
                        if (!this.isService) {
                            if (this.type == 0) {
                                if (this.orderDetailBean.getShareObj().getPayType() != 0) {
                                    this.holdView.setFunction(new int[0]);
                                    break;
                                } else {
                                    this.holdView.setFunction(1);
                                    break;
                                }
                            } else {
                                this.holdView.setFunction(5);
                                break;
                            }
                        } else if (this.type != 0) {
                            this.holdView.setFunction(1);
                            break;
                        } else {
                            this.holdView.setFunction(5);
                            break;
                        }
                    case 12:
                        statusStr = "已关闭";
                        if (this.isService) {
                            if (this.type != 0) {
                                this.holdView.setFunction(new int[0]);
                                break;
                            } else {
                                this.holdView.setFunction(new int[0]);
                                break;
                            }
                        } else {
                            this.holdView.setFunction(new int[0]);
                            break;
                        }
                    case 13:
                        statusStr = "待确认";
                        if (!this.isService) {
                            if (this.type == 0) {
                                if (this.orderDetailBean.getShareObj().getPayType() != 0) {
                                    this.holdView.setFunction(2);
                                    break;
                                } else {
                                    this.holdView.setFunction(1, 2);
                                    break;
                                }
                            } else {
                                this.holdView.setFunction(new int[0]);
                                break;
                            }
                        } else if (this.type != 0) {
                            this.holdView.setFunction(1, 2);
                            break;
                        } else {
                            this.holdView.setFunction(new int[0]);
                            break;
                        }
                    case 14:
                        statusStr = "已取消";
                        this.holdView.setFunction(new int[0]);
                        break;
                    case 15:
                        statusStr = "已申诉";
                        this.holdView.setFunction(new int[0]);
                        break;
                    case 16:
                        statusStr = "待接单";
                        if (this.type != 0) {
                            if (this.orderDetailBean.getPayStatus() != 0) {
                                this.holdView.setFunction(0);
                                break;
                            } else {
                                this.holdView.setFunction(0, 8);
                                break;
                            }
                        } else {
                            this.holdView.setFunction(9, 10);
                            break;
                        }
                    case 17:
                    case 18:
                    case 19:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    default:
                        statusStr = "未知状态";
                        break;
                    case 20:
                        statusStr = "退款中";
                        if (!this.isService) {
                            if (this.type != 1) {
                                this.holdView.setFunction(2, 7);
                                break;
                            } else {
                                this.holdView.setFunction(6);
                                break;
                            }
                        } else if (this.type != 0) {
                            this.holdView.setFunction(2, 7);
                            break;
                        } else {
                            this.holdView.setFunction(6);
                            break;
                        }
                    case 21:
                        statusStr = "已退款";
                        this.holdView.setFunction(7);
                        break;
                    case 22:
                        statusStr = "拒绝退款";
                        if (!this.isService) {
                            if (this.type != 1) {
                                this.holdView.setFunction(2, 6);
                                break;
                            } else {
                                this.holdView.setFunction(7);
                                break;
                            }
                        } else if (this.type != 0) {
                            this.holdView.setFunction(2, 6);
                            break;
                        } else {
                            this.holdView.setFunction(7);
                            break;
                        }
                    case 30:
                        statusStr = "待评价";
                        this.holdView.setFunction(3);
                        break;
                    case 31:
                        if (!this.isService) {
                            if (this.type != 0) {
                                statusStr = "需求方已评价";
                                this.holdView.setFunction(3);
                                break;
                            } else {
                                statusStr = "已评价";
                                this.holdView.setFunction(new int[0]);
                                break;
                            }
                        } else if (this.type != 1) {
                            statusStr = "需求方已评价";
                            this.holdView.setFunction(3);
                            break;
                        } else {
                            statusStr = "已评价";
                            this.holdView.setFunction(new int[0]);
                            break;
                        }
                    case 32:
                        if (!this.isService) {
                            if (this.type != 0) {
                                statusStr = "已评价";
                                this.holdView.setFunction(new int[0]);
                                break;
                            } else {
                                statusStr = "服务商已评价";
                                this.holdView.setFunction(3);
                                break;
                            }
                        } else if (this.type != 1) {
                            statusStr = "已评价";
                            this.holdView.setFunction(new int[0]);
                            break;
                        } else {
                            statusStr = "服务商已评价";
                            this.holdView.setFunction(3);
                            break;
                        }
                    case 33:
                        statusStr = "已互评";
                        this.holdView.setFunction(new int[0]);
                        break;
                }
                this.tv_order_state.setText(statusStr);
                return;
            case R.string.API_SERVICE_AGREESERVICE /* 2131296402 */:
                MyToast.show(getApplicationContext(), "确认接单成功");
                refreshData();
                return;
            case R.string.API_SERVICE_REJECTSERVICE /* 2131296419 */:
                MyToast.show(getApplicationContext(), "拒绝接单成功");
                refreshData();
                return;
            default:
                return;
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
        switch (result.getRequestCode()) {
            case R.string.API_ORDER_DETAIL /* 2131296386 */:
                if (this.orderDetailBean == null) {
                    this.orderDetailBean = (OrderDetailBean) result.getObj();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
