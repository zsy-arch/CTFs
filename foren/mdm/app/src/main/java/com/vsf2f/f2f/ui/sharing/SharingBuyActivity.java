package com.vsf2f.f2f.ui.sharing;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.em.DemoHelper;
import com.hy.frame.adapter.BaseAdapter;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.hy.frame.view.recycler.callback.XRefreshViewListener;
import com.hy.frame.view.recycler.xRefreshView.XRefreshView;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.SharingBuyOrderBean;
import com.vsf2f.f2f.bean.SharingComBean;
import com.vsf2f.f2f.bean.SharingListBean;
import com.vsf2f.f2f.bean.SharingRecordBean;
import com.vsf2f.f2f.ui.dialog.PayPwdDialog;
import com.vsf2f.f2f.ui.dialog.SharingBuyComDialog;
import com.vsf2f.f2f.ui.dialog.SharingBuyDialog;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;
import com.vsf2f.f2f.ui.utils.FingerUtil;
import com.vsf2f.f2f.ui.view.MyListview;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class SharingBuyActivity extends BaseActivity implements XRefreshViewListener {
    private SharingBuyComDialog buyComDialog;
    private SharingSellAdapter listAdapter;
    private MyListview listView;
    private PayPwdDialog pwdDialog;
    private TextView tvBuyCom;
    private LinearLayout tvNoList;
    private XRefreshView xrv_pull;
    private long lastId = 0;
    private List<SharingRecordBean> data = new ArrayList();
    private Handler handler = new Handler();

    /* loaded from: classes2.dex */
    public interface BuyListener {
        void buyItem(String str);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_sharing_buy;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.sharing_title_buy, R.drawable.icon_help_white);
        this.listView = (MyListview) findViewById(R.id.sharing_record_list_buy);
        this.xrv_pull = (XRefreshView) findViewById(R.id.xrv_parent);
        this.xrv_pull.setXRefreshViewListener(this);
        this.xrv_pull.setPullRefreshEnable(true);
        this.xrv_pull.setPullLoadEnable(false);
        this.tvNoList = (LinearLayout) findViewById(R.id.sharing_buy_no_list);
        this.tvBuyCom = (TextView) getViewAndClick(R.id.sharing_buy_com);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        requestList(0L);
        this.listAdapter = new SharingSellAdapter(this.context, this.data, new BuyListener() { // from class: com.vsf2f.f2f.ui.sharing.SharingBuyActivity.1
            @Override // com.vsf2f.f2f.ui.sharing.SharingBuyActivity.BuyListener
            public void buyItem(final String id) {
                SharingBuyActivity.this.pwdDialog = new PayPwdDialog(SharingBuyActivity.this.context, new PayPwdDialog.OutPwdListener() { // from class: com.vsf2f.f2f.ui.sharing.SharingBuyActivity.1.1
                    @Override // com.vsf2f.f2f.ui.dialog.PayPwdDialog.OutPwdListener
                    public void outPwd(String password) {
                        SharingBuyActivity.this.requestBuy(id, password);
                    }
                });
                SharingBuyActivity.this.pwdDialog.show();
            }
        });
        this.listView.setAdapter((ListAdapter) this.listAdapter);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    public void requestList(long lastId) {
        if (lastId == -1) {
            Toast.makeText(this.context, (int) R.string.no_more, 0).show();
            this.xrv_pull.stopLoadMore();
            return;
        }
        getClient().get(R.string.API_SHARING_SELL_LIST, ComUtil.getZCApi(this.context, getString(R.string.API_SHARING_SELL_LIST)) + "?lastId=" + lastId, SharingListBean.class);
    }

    public void requestCom() {
        getClient().setShowDialog(true);
        getClient().get(R.string.API_SHARING_COM, ComUtil.getZCApi(this.context, getString(R.string.API_SHARING_COM)), SharingComBean.class);
    }

    public void requestBuyCom(String num, String price, String pwdMd5) {
        AjaxParams params = new AjaxParams();
        params.put("num", Integer.parseInt(num) + "");
        params.put("userName", DemoHelper.getInstance().getCurrentUserName());
        params.put("payPwd", pwdMd5);
        params.put("timestamp", System.currentTimeMillis() + "");
        params.put("nonceStr", ComUtil.getRandomString(32));
        params.put("sign", ComUtil.encryptParam(params));
        getClient().setShowDialog(true);
        getClient().post(R.string.API_SHARING_BUYCOM, ComUtil.getZCApi(this.context, getString(R.string.API_SHARING_BUYCOM)), params, SharingBuyOrderBean.class);
    }

    public void requestBuy(String id, String pwdMd5) {
        AjaxParams params = new AjaxParams();
        params.put("recordId", id);
        params.put("userName", DemoHelper.getInstance().getCurrentUserName());
        params.put("payPwd", pwdMd5);
        params.put("timestamp", System.currentTimeMillis() + "");
        params.put("nonceStr", ComUtil.getRandomString(32));
        params.put("sign", ComUtil.encryptParam(params));
        getClient().setShowDialog(true);
        getClient().post(R.string.API_SHARING_BUY, ComUtil.getZCApi(this.context, getString(R.string.API_SHARING_BUY)), params, SharingBuyOrderBean.class);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_SHARING_BUY /* 2131296421 */:
                break;
            case R.string.API_SHARING_BUYCOM /* 2131296422 */:
                this.buyComDialog.dismiss();
                break;
            case R.string.API_SHARING_COM /* 2131296424 */:
                this.buyComDialog = new SharingBuyComDialog(this.context, Double.valueOf(((SharingComBean) result.getObj()).getNum()), Double.valueOf(getBundle().getDouble("minWorth")), Double.valueOf(getBundle().getDouble("maxWorth")), getBundle().getInt("minBuyNum"), getBundle().getInt("maxBuyNum"), new SharingBuyComDialog.BuyComImpl() { // from class: com.vsf2f.f2f.ui.sharing.SharingBuyActivity.4
                    @Override // com.vsf2f.f2f.ui.dialog.SharingBuyComDialog.BuyComImpl
                    public void buyCom(final String num, final String price) {
                        SharingBuyActivity.this.pwdDialog = new PayPwdDialog(SharingBuyActivity.this.context, new PayPwdDialog.OutPwdListener() { // from class: com.vsf2f.f2f.ui.sharing.SharingBuyActivity.4.1
                            @Override // com.vsf2f.f2f.ui.dialog.PayPwdDialog.OutPwdListener
                            public void outPwd(String password) {
                                SharingBuyActivity.this.requestBuyCom(num, price, password);
                            }
                        });
                        SharingBuyActivity.this.pwdDialog.show();
                    }
                });
                this.buyComDialog.show();
                return;
            case R.string.API_SHARING_SELL_LIST /* 2131296430 */:
                this.xrv_pull.stopRefresh();
                this.xrv_pull.stopLoadMore();
                SharingListBean listBean = (SharingListBean) result.getObj();
                if (listBean != null) {
                    if (this.lastId == 0) {
                        this.data = listBean.getTradeRecord();
                    } else {
                        this.data.addAll(listBean.getTradeRecord());
                    }
                    this.listAdapter.setData(this.data);
                    if (listBean.getLastId() == 0) {
                        this.lastId = -1L;
                    } else {
                        this.lastId = listBean.getLastId();
                    }
                    if (HyUtil.isEmpty(listBean.getTradeRecord())) {
                        this.tvNoList.setVisibility(0);
                        if ("1".equals(DemoHelper.getInstance().readConfig().getGxb_buy_com())) {
                            this.tvBuyCom.setVisibility(4);
                            this.handler.postDelayed(new Runnable() { // from class: com.vsf2f.f2f.ui.sharing.SharingBuyActivity.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    SharingBuyActivity.this.tvBuyCom.setVisibility(0);
                                }
                            }, 1500L);
                            return;
                        }
                        return;
                    }
                    this.tvNoList.setVisibility(8);
                    return;
                }
                return;
            default:
                return;
        }
        this.pwdDialog.saveDismiss();
        final SharingBuyOrderBean orderBean = (SharingBuyOrderBean) result.getObj();
        FingerUtil.showOpenFinger(this.context, new FingerUtil.overListener() { // from class: com.vsf2f.f2f.ui.sharing.SharingBuyActivity.3
            @Override // com.vsf2f.f2f.ui.utils.FingerUtil.overListener
            public void over() {
                Bundle bundle = new Bundle();
                bundle.putString(Constant.FLAG, orderBean.getPayUrl());
                SharingBuyActivity.this.startAct(WebKitLocalActivity.class, bundle);
                SharingBuyActivity.this.finish();
            }
        });
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
        this.xrv_pull.stopRefresh();
        this.xrv_pull.stopLoadMore();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        startAct(SharingMainActivity.class);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.sharing_buy_com /* 2131755611 */:
                requestCom();
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        Bundle bundle = new Bundle();
        bundle.putString(Constant.FLAG_TITLE, getString(R.string.sharing_title_buy));
        bundle.putString(Constant.FLAG, ComUtil.getZCApi(this.context, getString(R.string.URL_HELP_COMMON_CODE, new Object[]{"gxb_buy"})));
        bundle.putBoolean(Constant.FLAG2, false);
        startAct(WebKitLocalActivity.class, bundle);
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onRefresh() {
        this.lastId = 0L;
        requestList(0L);
        this.xrv_pull.setLoadComplete(false);
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onLoadMore(boolean isSilence) {
        requestList(this.lastId);
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onRelease(float direction) {
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onHeaderMove(double headerMovePercent, int offsetY) {
    }

    /* loaded from: classes2.dex */
    class SharingSellAdapter extends BaseAdapter<SharingRecordBean> {
        private List<SharingRecordBean> data;
        private BuyListener listener;

        private SharingSellAdapter(Context context, List<SharingRecordBean> data, BuyListener listener) {
            super(context);
            this.data = data;
            this.listener = listener;
        }

        public void setData(List<SharingRecordBean> data) {
            this.data = data;
            notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.data.size();
        }

        @Override // android.widget.Adapter
        public SharingRecordBean getItem(int i) {
            return this.data.get(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder h;
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.item_sharing_list_buy, (ViewGroup) null);
                h = new ViewHolder();
                h.ll = (LinearLayout) view.findViewById(R.id.item_sharing_ll);
                h.txt1 = (TextView) view.findViewById(R.id.item_sharing_txt1);
                h.txt2 = (TextView) view.findViewById(R.id.item_sharing_txt2);
                h.txt3 = (TextView) view.findViewById(R.id.item_sharing_txt3);
                h.txt4 = (TextView) view.findViewById(R.id.item_sharing_txt4);
                h.txt5 = (TextView) view.findViewById(R.id.item_sharing_txt5);
                view.setTag(h);
            } else {
                h = (ViewHolder) view.getTag();
            }
            final SharingRecordBean tradeBean = getItem(i);
            h.txt1.setText(HyUtil.getDateTime(tradeBean.getTradeTime(), "HH:mm"));
            h.txt2.setText(tradeBean.getUserName() + "");
            h.txt3.setText(tradeBean.getTradeNum() + "个");
            h.txt4.setText(HyUtil.formatToMoney(Double.valueOf(tradeBean.getShareWorth())));
            h.txt5.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.sharing.SharingBuyActivity.SharingSellAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    SharingBuyDialog buyDialog = new SharingBuyDialog(SharingSellAdapter.this.getContext(), tradeBean.getTradeMoney(), tradeBean.getShareWorth(), tradeBean.getTradeNum());
                    buyDialog.setListener(new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.sharing.SharingBuyActivity.SharingSellAdapter.1.1
                        @Override // com.hy.frame.common.BaseDialog.IConfirmListener
                        public void onDlgConfirm(BaseDialog dlg, int flag) {
                            if (flag == 0) {
                                SharingSellAdapter.this.listener.buyItem(tradeBean.getRecordId() + "");
                            }
                        }
                    });
                    buyDialog.show();
                }
            });
            if (i % 2 == 0) {
                h.ll.setBackgroundResource(R.color.gray_f);
            }
            return view;
        }

        /* loaded from: classes2.dex */
        class ViewHolder {
            LinearLayout ll;
            TextView txt1;
            TextView txt2;
            TextView txt3;
            TextView txt4;
            TextView txt5;

            ViewHolder() {
            }
        }
    }
}
