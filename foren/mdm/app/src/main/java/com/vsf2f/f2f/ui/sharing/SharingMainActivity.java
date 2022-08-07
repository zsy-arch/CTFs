package com.vsf2f.f2f.ui.sharing;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.TimeUtil;
import com.easeui.widget.EaseAlertDialog;
import com.em.utils.UserShared;
import com.hy.frame.adapter.BaseAdapter;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.HyUtil;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.SharingBean;
import com.vsf2f.f2f.bean.SharingListBean;
import com.vsf2f.f2f.bean.SharingRecordBean;
import com.vsf2f.f2f.ui.user.change.BindPhoneActivity;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.view.MyListview;
import java.util.List;
import org.slf4j.Marker;

/* loaded from: classes2.dex */
public class SharingMainActivity extends BaseActivity {
    private boolean isShowList;
    private Handler mHandler = new Handler();
    private TextView mText1;
    private TextView mText2;
    private ViewFlipper mViewflipper;
    private int num;
    private SharingBean sharingBean;
    private SharingListBean sharingListBean;
    private TextView tvAssets;
    private TextView tvEnable;
    private TextView tvFrozen;
    private TextView tvIncrement;
    private TextView tvSharing;
    private TextView tvWorth;

    static /* synthetic */ int access$108(SharingMainActivity x0) {
        int i = x0.num;
        x0.num = i + 1;
        return i;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_sharing_main;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.sharing_title, R.string.details_sharing);
        this.tvWorth = (TextView) findViewById(R.id.sharing_main_tvWorth);
        this.tvEnable = (TextView) findViewById(R.id.sharing_main_tvEnable);
        this.tvFrozen = (TextView) findViewById(R.id.sharing_main_tvFrozen);
        this.tvAssets = (TextView) findViewById(R.id.sharing_main_tvAssets);
        this.tvSharing = (TextView) findViewById(R.id.sharing_main_tvSharing);
        this.tvIncrement = (TextView) findViewById(R.id.sharing_tvIncrement);
        setOnClickListener(R.id.sharing_main_btnGive);
        setOnClickListener(R.id.sharing_main_btnSell);
        setOnClickListener(R.id.sharing_main_btnBuy);
        setOnClickListener(R.id.sharing_main_llThaw);
        setOnClickListener(R.id.sharing_main_llRecord);
        final LinearLayout llList = (LinearLayout) findViewById(R.id.sharing_main_llList);
        findViewById(R.id.sharing_btnShowList).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.sharing.SharingMainActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SharingMainActivity.this.isShowList) {
                    llList.setVisibility(8);
                    SharingMainActivity.this.isShowList = false;
                    return;
                }
                llList.setVisibility(0);
                SharingMainActivity.this.isShowList = true;
            }
        });
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        requestList();
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        requestData();
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_SHARING_LIST /* 2131296426 */:
                this.sharingListBean = (SharingListBean) result.getObj();
                if (this.sharingListBean != null) {
                    updateList();
                    return;
                }
                return;
            case R.string.API_SHARING_MAIN /* 2131296427 */:
                this.sharingBean = (SharingBean) result.getObj();
                if (this.sharingBean != null) {
                    updateUI();
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        getClient().get(R.string.API_SHARING_MAIN, ComUtil.getZCApi(this.context, getString(R.string.API_SHARING_MAIN)), SharingBean.class);
    }

    public void requestList() {
        getClient().get(R.string.API_SHARING_LIST, ComUtil.getZCApi(this.context, getString(R.string.API_SHARING_LIST)) + "?lastId=0&field=createTime&direction=DESC", SharingListBean.class);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        if (this.sharingBean != null) {
            this.tvSharing.setText(this.sharingBean.getShareMoney() + "");
            this.tvEnable.setText(this.sharingBean.getShareMoneyEnable() + "");
            this.tvFrozen.setText(this.sharingBean.getShareMoneyDisable() + "");
            this.tvWorth.setText(this.sharingBean.getWorth() + "");
            String grow = this.sharingBean.getGrowNum() + "";
            if (!grow.startsWith("-")) {
                grow = Marker.ANY_NON_NULL_MARKER + grow;
            }
            this.tvIncrement.setText("昨增长" + grow);
            this.tvAssets.setText(HyUtil.formatToMoney(Double.valueOf(this.sharingBean.getShareMoney() * this.sharingBean.getWorth())));
            UserShared.getInstance().saveShareMoney(this.sharingBean);
            showList();
        }
    }

    public void updateList() {
        if (this.sharingListBean != null && HyUtil.isNoEmpty(this.sharingListBean.getTradeRecord())) {
            this.mText1 = (TextView) findViewById(16908308);
            this.mText2 = (TextView) findViewById(16908309);
            this.mViewflipper = (ViewFlipper) findViewById(R.id.viewflipper);
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.push_bottom_in);
            Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.push_top_out);
            this.mViewflipper.setInAnimation(animation);
            this.mViewflipper.setOutAnimation(animation1);
            this.mHandler.post(new Runnable() { // from class: com.vsf2f.f2f.ui.sharing.SharingMainActivity.2
                @Override // java.lang.Runnable
                public void run() {
                    SharingRecordBean recordBean = SharingMainActivity.this.sharingListBean.getTradeRecord().get(SharingMainActivity.this.num);
                    String str = (TimeUtil.formatDisplayTime(recordBean.getTradeTime(), "yyyy-MM-dd HH:mm") + "面粉 ") + "<font color='#3B94F9'>" + recordBean.getUserName() + "</font>";
                    if (recordBean.getType() == 1) {
                        str = str + " 收购 +";
                    } else if (recordBean.getType() == 3) {
                        str = str + " 出让 -";
                    }
                    String str2 = (str + recordBean.getTradeNum()) + "个共享宝.";
                    if (SharingMainActivity.this.num % 2 == 1) {
                        SharingMainActivity.this.mText1.setText(Html.fromHtml(str2));
                    } else {
                        SharingMainActivity.this.mText2.setText(Html.fromHtml(str2));
                    }
                    SharingMainActivity.access$108(SharingMainActivity.this);
                    if (SharingMainActivity.this.num == SharingMainActivity.this.sharingListBean.getTradeRecord().size()) {
                        SharingMainActivity.this.num = 0;
                    }
                    SharingMainActivity.this.mViewflipper.showNext();
                    SharingMainActivity.this.mHandler.postDelayed(this, 3000L);
                }
            });
        }
    }

    public void showList() {
        if (this.sharingBean != null) {
            ((MyListview) findViewById(R.id.sharing_main_list)).setAdapter((ListAdapter) new SharingDetailAdapter(this.context, this.sharingBean.getGrowRecord()));
        }
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        startAct(SharingSetActivity.class);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.sharing_main_llRecord /* 2131755622 */:
                startAct(SharingListActivity.class);
                return;
            case R.id.sharing_main_llThaw /* 2131755626 */:
                if (UserShared.getInstance().getIsVerifyState(this.context)) {
                    startAct(SharingThawActivity.class);
                    return;
                }
                return;
            case R.id.sharing_main_btnGive /* 2131755631 */:
                if (UserShared.getInstance().getInt(Constant.CERT_MOBILE) != 1) {
                    new EaseAlertDialog(this.context, (int) R.string.prompt, (int) R.string.not_bing_phone_prompt, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.sharing.SharingMainActivity.4
                        @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                        public void onResult(boolean confirmed, Bundle bundle2) {
                            if (confirmed) {
                                SharingMainActivity.this.startAct(BindPhoneActivity.class);
                            }
                        }
                    }, true).show();
                    return;
                } else if (this.sharingBean != null) {
                    startAct(SharingGiveActivity.class);
                    return;
                } else {
                    return;
                }
            case R.id.sharing_main_btnSell /* 2131755632 */:
                if (this.sharingBean != null && UserShared.getInstance().getIsVerifyState(this.context)) {
                    if (UserShared.getInstance().getInt(Constant.CERT_MOBILE) != 1) {
                        new EaseAlertDialog(this.context, (int) R.string.prompt, (int) R.string.not_bing_phone_prompt, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.sharing.SharingMainActivity.3
                            @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                            public void onResult(boolean confirmed, Bundle bundle2) {
                                if (confirmed) {
                                    SharingMainActivity.this.startAct(BindPhoneActivity.class);
                                }
                            }
                        }, true).show();
                        return;
                    }
                    bundle.putDouble("worth", this.sharingBean.getWorth());
                    bundle.putDouble("minWorth", this.sharingBean.getMinWorth());
                    bundle.putDouble("maxWorth", this.sharingBean.getMaxWorth());
                    startAct(SharingSellActivity.class, bundle);
                    return;
                }
                return;
            case R.id.sharing_main_btnBuy /* 2131755633 */:
                if (this.sharingBean != null && UserShared.getInstance().getIsVerifyState(this.context)) {
                    bundle.putInt("minBuyNum", this.sharingBean.getMinBuyNum());
                    bundle.putInt("maxBuyNum", this.sharingBean.getMaxBuyNum());
                    bundle.putDouble("minWorth", this.sharingBean.getMinWorth());
                    bundle.putDouble("maxWorth", this.sharingBean.getMaxWorth());
                    startAct(SharingBuyActivity.class, bundle);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* loaded from: classes2.dex */
    public class SharingDetailAdapter extends BaseAdapter<SharingBean.GrowRecordBean> {
        private List<SharingBean.GrowRecordBean> data;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SharingDetailAdapter(Context context, List<SharingBean.GrowRecordBean> data) {
            super(context);
            SharingMainActivity.this = this$0;
            this.data = data;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.data.size();
        }

        @Override // android.widget.Adapter
        public SharingBean.GrowRecordBean getItem(int i) {
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
                view = LayoutInflater.from(getContext()).inflate(R.layout.item_sharing_list, (ViewGroup) null);
                h = new ViewHolder();
                h.ll = (LinearLayout) view.findViewById(R.id.item_sharing_ll);
                h.txt1 = (TextView) view.findViewById(R.id.item_sharing_txt1);
                h.txt2 = (TextView) view.findViewById(R.id.item_sharing_txt2);
                h.txt3 = (TextView) view.findViewById(R.id.item_sharing_txt3);
                view.setTag(h);
            } else {
                h = (ViewHolder) view.getTag();
            }
            SharingBean.GrowRecordBean growRecordBean = getItem(i);
            h.txt1.setText(growRecordBean.getTypeName());
            h.txt2.setText(growRecordBean.getYesterdayNum());
            h.txt3.setText(growRecordBean.getTodayNum());
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

            ViewHolder() {
                SharingDetailAdapter.this = this$1;
            }
        }
    }
}
