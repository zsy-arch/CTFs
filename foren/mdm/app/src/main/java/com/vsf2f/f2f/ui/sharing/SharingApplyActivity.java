package com.vsf2f.f2f.ui.sharing;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.adapter.BaseAdapter;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.hy.frame.view.recycler.callback.XRefreshViewListener;
import com.hy.frame.view.recycler.xRefreshView.XRefreshView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.SharingListBean;
import com.vsf2f.f2f.bean.SharingRecordBean;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;
import com.vsf2f.f2f.ui.view.MyListview;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Marker;

/* loaded from: classes2.dex */
public class SharingApplyActivity extends BaseActivity implements XRefreshViewListener {
    private SharingListAdapter listAdapter;
    private MyListview listView;
    private XRefreshView xrv_pull;
    private long lastId = 0;
    private List<SharingRecordBean> data = new ArrayList();

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_sharing_apply;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.apply_record, R.drawable.icon_help_white);
        this.listView = (MyListview) findViewById(R.id.sharing_record_list);
        this.xrv_pull = (XRefreshView) findViewById(R.id.xrv_parent);
        this.xrv_pull.setXRefreshViewListener(this);
        this.xrv_pull.setPullRefreshEnable(true);
        this.xrv_pull.setPullLoadEnable(true);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.listAdapter = new SharingListAdapter(this.context, this.data);
        this.listView.setAdapter((ListAdapter) this.listAdapter);
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.lastId = 0L;
        requestList(0L);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    public void requestList(long lastId) {
        if (lastId == 0) {
            Toast.makeText(this.context, "还没有接口", 0).show();
            this.xrv_pull.stopLoadMore();
            return;
        }
        getClient().get(R.string.API_SHARING_LIST, ComUtil.getZCApi(this.context, getString(R.string.API_SHARING_LIST)) + "?lastId=" + lastId + "&field=createTime&direction=DESC", SharingListBean.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_SHARING_LIST /* 2131296426 */:
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
                        return;
                    } else {
                        this.lastId = listBean.getLastId();
                        return;
                    }
                } else {
                    return;
                }
            default:
                return;
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
        this.xrv_pull.stopRefresh();
        this.xrv_pull.stopLoadMore();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        Bundle bundle = new Bundle();
        bundle.putString(Constant.FLAG_TITLE, getString(R.string.records_transaction_plat));
        bundle.putString(Constant.FLAG, ComUtil.getZCApi(this.context, getString(R.string.URL_HELP_COMMON_CODE, new Object[]{"gxb_trade_record"})));
        bundle.putBoolean(Constant.FLAG2, false);
        startAct(WebKitLocalActivity.class, bundle);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
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
    class SharingListAdapter extends BaseAdapter<SharingRecordBean> {
        private List<SharingRecordBean> data;

        private SharingListAdapter(Context context, List<SharingRecordBean> data) {
            super(context);
            this.data = data;
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
                view = LayoutInflater.from(getContext()).inflate(R.layout.item_sharing_list_plat, (ViewGroup) null);
                h = new ViewHolder();
                h.ll = (LinearLayout) view.findViewById(R.id.item_sharing_ll);
                h.txt1 = (TextView) view.findViewById(R.id.item_sharing_txt1);
                h.txt2 = (TextView) view.findViewById(R.id.item_sharing_txt2);
                h.txt3 = (TextView) view.findViewById(R.id.item_sharing_txt3);
                h.txt4 = (TextView) view.findViewById(R.id.item_sharing_txt4);
                view.setTag(h);
            } else {
                h = (ViewHolder) view.getTag();
            }
            SharingRecordBean recordBean = getItem(i);
            h.txt1.setText(HyUtil.getDateTime(recordBean.getTradeTime(), "yy.MM.dd HH:mm"));
            h.txt2.setText(recordBean.getUserName() + "");
            if (recordBean.getType() == 1) {
                h.txt3.setText(" 买入 ");
                h.txt4.setTextColor(SharingApplyActivity.this.getResources().getColor(R.color.txt_red_finace));
                h.txt4.setText(Marker.ANY_NON_NULL_MARKER + recordBean.getTradeNum());
            } else if (recordBean.getType() == 3) {
                h.txt3.setText(" 出让 ");
                h.txt4.setTextColor(SharingApplyActivity.this.getResources().getColor(R.color.holo_green_light));
                h.txt4.setText("-" + recordBean.getTradeNum());
            } else {
                h.txt3.setText("??");
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

            ViewHolder() {
            }
        }
    }
}
