package com.vsf2f.f2f.ui.packet;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.em.DemoHelper;
import com.hy.frame.adapter.MyBaseAdapter;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.HyUtil;
import com.hy.frame.view.NavGroup;
import com.hy.frame.view.NavView;
import com.hy.frame.view.recycler.callback.XRefreshViewListener;
import com.hy.frame.view.recycler.xRefreshView.XRefreshView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.packet.RedPacketRecordBean;
import com.vsf2f.f2f.ui.utils.GameUtil;
import com.vsf2f.f2f.ui.view.MyListview;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class RedPacketRecordActivity extends BaseActivity implements NavGroup.OnCheckedChangeListener, XRefreshViewListener {
    private XRefreshView recordPage1;
    private XRefreshView recordPage2;
    private int type = 0;
    private long lastId1 = 0;
    private long lastId2 = 0;
    private List<RedPacketRecordBean.RowsBean> list1 = new ArrayList();
    private List<RedPacketRecordBean.RowsBean> list2 = new ArrayList();

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_redpacket_record;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.red_packet_record, 0);
        this.recordPage1 = (XRefreshView) getView(R.id.red_packet_recordPage1);
        this.recordPage1.setXRefreshViewListener(this);
        this.recordPage1.setPullLoadEnable(true);
        this.recordPage2 = (XRefreshView) getView(R.id.red_packet_recordPage2);
        this.recordPage2.setXRefreshViewListener(this);
        this.recordPage2.setPullLoadEnable(true);
        ((NavGroup) getView(R.id.red_packet_grouptype)).setOnCheckedChangeListener(this);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        requestData();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        requestData(0L);
    }

    public void requestData(long lastId) {
        int requestCode;
        try {
            getClient().setShowDialog(true);
            JSONObject jsonContent = new JSONObject();
            jsonContent.put("lastId", lastId);
            jsonContent.put("isCount", 1);
            JSONObject jsonObject = new JSONObject();
            if (this.type == 0) {
                if (this.lastId1 == -1) {
                    this.recordPage1.stopLoadMore();
                    Toast.makeText(this.context, "没有更多了", 0).show();
                    return;
                }
                requestCode = 22;
                jsonObject.put("method", "vsf2f.account.cash.redpacket.send.record");
            } else if (this.lastId2 == -1) {
                this.recordPage2.stopLoadMore();
                Toast.makeText(this.context, "没有更多了", 0).show();
                return;
            } else {
                requestCode = 33;
                jsonObject.put("method", "vsf2f.account.cash.redpacket.take.record");
            }
            jsonObject.put("bizContent", ComUtil.UTF(jsonContent.toString()));
            getClient().post(requestCode, ComUtil.getZCApi(this.context, getString(R.string.API_RED_PACKET)), GameUtil.getVsSign(jsonObject.toString()), null, RedPacketRecordBean.class, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        updateUI(result.getRequestCode(), (RedPacketRecordBean) result.getObj());
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        if (result.getRequestCode() == 22) {
            this.recordPage1.stopRefresh();
            this.recordPage1.stopLoadMore();
        } else {
            this.recordPage2.stopRefresh();
            this.recordPage2.stopLoadMore();
        }
        super.onRequestError(result);
    }

    public void updateUI(int requestCode, RedPacketRecordBean redPacketRecord) {
        ImageView ivAvatar;
        TextView tvNickname;
        TextView tvAllMoney;
        TextView tvRedCount;
        if (requestCode == 22) {
            this.recordPage1.stopRefresh();
            this.recordPage1.stopLoadMore();
            ivAvatar = (ImageView) getView(R.id.red_detail_ivAvatar);
            tvNickname = (TextView) getView(R.id.red_detail_txtNickname);
            tvAllMoney = (TextView) getView(R.id.red_detail_txtAllMoney);
            tvRedCount = (TextView) getView(R.id.red_detail_txtRedCount);
            MyListview myListview = (MyListview) findViewById(R.id.red_detail_listRed);
            if (this.lastId1 == 0) {
                this.list1.clear();
            }
            if (HyUtil.isNoEmpty(redPacketRecord.getRows())) {
                this.list1.addAll(redPacketRecord.getRows());
            } else {
                Toast.makeText(this.context, "没有更多了", 0).show();
            }
            this.lastId1 = redPacketRecord.getCondition().getLastId();
            myListview.setAdapter((ListAdapter) new RedListAdapter(this.context, this.list1));
        } else {
            this.recordPage2.stopRefresh();
            this.recordPage2.stopLoadMore();
            ivAvatar = (ImageView) getView(R.id.red_detail_ivAvatar2);
            tvNickname = (TextView) getView(R.id.red_detail_txtNickname2);
            tvAllMoney = (TextView) getView(R.id.red_detail_txtAllMoney2);
            tvRedCount = (TextView) getView(R.id.red_detail_txtRedCount2);
            MyListview myListview2 = (MyListview) findViewById(R.id.red_detail_listRed2);
            if (this.lastId2 == 0) {
                this.list2.clear();
            }
            if (HyUtil.isNoEmpty(redPacketRecord.getRows())) {
                this.list2.addAll(redPacketRecord.getRows());
            } else {
                Toast.makeText(this.context, "没有更多了", 0).show();
            }
            this.lastId2 = redPacketRecord.getCondition().getLastId();
            myListview2.setAdapter((ListAdapter) new RedListAdapter(this.context, this.list2));
        }
        Glide.with(this.context).load(DemoHelper.getInstance().getCurrentUserPic().getSpath()).error((int) R.mipmap.def_head2).into(ivAvatar);
        tvNickname.setText(DemoHelper.getInstance().getCurrentUserNick());
        tvRedCount.setText(redPacketRecord.getRecordCount().getTotalNum() + "");
        tvAllMoney.setText(HyUtil.formatToMoney(Double.valueOf(redPacketRecord.getRecordCount().getTotalAmount())));
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
    }

    @Override // com.hy.frame.view.NavGroup.OnCheckedChangeListener
    public void onCheckedChanged(NavGroup group, NavView nav, int checkedId) {
        if (checkedId == R.id.nv_type1) {
            this.type = 0;
            this.recordPage1.setVisibility(0);
            this.recordPage2.setVisibility(8);
            return;
        }
        this.type = 1;
        this.recordPage1.setVisibility(8);
        this.recordPage2.setVisibility(0);
        if (this.lastId2 == 0) {
            requestData();
        }
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onRefresh() {
        if (this.type == 0) {
            this.lastId1 = 0L;
        } else {
            this.lastId2 = 0L;
        }
        requestData();
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onLoadMore(boolean isSilence) {
        if (this.type == 0) {
            requestData(this.lastId1);
        } else {
            requestData(this.lastId2);
        }
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onRelease(float direction) {
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onHeaderMove(double headerMovePercent, int offsetY) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class RedListAdapter extends MyBaseAdapter<RedPacketRecordBean.RowsBean> {
        public RedListAdapter(Context context, List<RedPacketRecordBean.RowsBean> datas) {
            super(context, datas);
        }

        @Override // android.widget.Adapter
        public View getView(int position, View v, ViewGroup group) {
            String title;
            if (v == null) {
                v = inflate(R.layout.item_detail_redpacket);
                new ItemHolder(v);
            }
            ItemHolder h = (ItemHolder) v.getTag();
            RedPacketRecordBean.RowsBean item = getItem(position);
            int sign = 0;
            if (RedPacketRecordActivity.this.type == 0) {
                h.avatar.setVisibility(8);
                if (item.getRedPacketModule() == 0) {
                    title = String.format("给%s的红包", item.getReceiveNick());
                } else if (item.getRedPacketType() == 1) {
                    title = "拼手气红包";
                    sign = R.drawable.icon_redpacket_sign_fight;
                } else {
                    title = "普通红包";
                    sign = R.drawable.icon_redpacket_sign_normal;
                }
            } else {
                Glide.with(RedPacketRecordActivity.this.context).load(item.getUser().getPicUrl()).error((int) R.mipmap.def_head2).into(h.avatar);
                if (item.getRedPacketModule() == 1) {
                    if (item.getRedPacketType() == 1) {
                        sign = R.drawable.icon_redpacket_sign_fight;
                    } else {
                        sign = R.drawable.icon_redpacket_sign_normal;
                    }
                }
                title = item.getSendNick();
            }
            h.name.setText(title);
            h.sign.setImageResource(sign);
            h.time.setText(item.getCreateTimeStr());
            h.money.setText(String.format("%s 元", HyUtil.formatToMoney(Double.valueOf(item.getAmount()))));
            if (item.getRedPacketModule() == 1 && item.getLuckiestFlag() == 1) {
                h.lucky.setVisibility(0);
            } else {
                h.lucky.setVisibility(8);
            }
            return v;
        }

        /* loaded from: classes2.dex */
        class ItemHolder {
            private ImageView avatar;
            private LinearLayout ll_parent;
            private TextView lucky;
            private TextView money;
            private TextView name;
            private ImageView sign;
            private TextView time;

            public ItemHolder(View v) {
                v.setTag(this);
                this.ll_parent = (LinearLayout) RedListAdapter.this.getView(v, R.id.ll_parent);
                this.avatar = (ImageView) RedListAdapter.this.getView(v, R.id.avatar);
                this.sign = (ImageView) RedListAdapter.this.getView(v, R.id.sign);
                this.lucky = (TextView) RedListAdapter.this.getView(v, R.id.lucky);
                this.money = (TextView) RedListAdapter.this.getView(v, R.id.money);
                this.name = (TextView) RedListAdapter.this.getView(v, R.id.name);
                this.time = (TextView) RedListAdapter.this.getView(v, R.id.time);
            }
        }
    }
}
