package com.vsf2f.f2f.ui.packet;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.easeui.utils.RedPacketUtil;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.HyUtil;
import com.hyphenate.chat.EMMessage;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.RedPacketDetailAdapter;
import com.vsf2f.f2f.bean.packet.RedPacketDetailBean;
import com.vsf2f.f2f.bean.packet.RedPacketInfoBean;
import com.vsf2f.f2f.ui.utils.GameUtil;
import com.vsf2f.f2f.ui.view.MyListview;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class RedPacketDetailActivity extends BaseActivity implements RedPacketDetailAdapter.getPacketListener {
    private boolean isNormal;
    private ImageView ivAvatar;
    private RedPacketInfoBean redInfo;
    private TextView tvGreeting;
    private TextView tvNickname;
    private TextView txtRedCount;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_redpacket_detail;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.mdm_redpacket, R.string.redpacket_record);
        this.ivAvatar = (ImageView) getView(R.id.red_detail_ivAvatar);
        this.tvNickname = (TextView) getView(R.id.red_detail_txtNickname);
        this.tvGreeting = (TextView) getView(R.id.red_detail_txtGreeting);
        this.txtRedCount = (TextView) getView(R.id.red_detail_txtRedCount);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        if (getBundle() != null) {
            Bundle bundle = getBundle();
            this.redInfo = (RedPacketInfoBean) bundle.getSerializable("red");
            this.isNormal = bundle.getBoolean("normal");
        }
        updateUI();
        requestData();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        try {
            getClient().setShowDialog(true);
            JSONObject jsonContent = new JSONObject();
            jsonContent.put("baseId", this.redInfo.redPacketId);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("method", "vsf2f.account.cash.redpacket.detail");
            jsonObject.put("bizContent", ComUtil.UTF(jsonContent.toString()));
            getClient().post(22, ComUtil.getZCApi(this.context, getString(R.string.API_RED_PACKET)), GameUtil.getVsSign(jsonObject.toString()), null, RedPacketDetailBean.class, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case 22:
                showStatus((RedPacketDetailBean) result.getObj());
                return;
            default:
                return;
        }
    }

    private void showStatus(RedPacketDetailBean redPacketDetail) {
        boolean z = true;
        if (redPacketDetail.getType() == 1) {
            findViewById(R.id.red_detail_ivFight).setVisibility(0);
        }
        int status = redPacketDetail.getStatus();
        if (HyUtil.isNoEmpty(redPacketDetail.getDetailList())) {
            getView(R.id.red_detail_llyList).setVisibility(0);
            if (status == 0) {
                this.txtRedCount.setText(getString(R.string.redpacket_detail_count, new Object[]{redPacketDetail.getTotalNum() + "", redPacketDetail.getSurplusNum() + ""}));
            } else if (redPacketDetail.getSurplusNum() == 0) {
                this.txtRedCount.setText(getString(R.string.redpacket_detail_count2, new Object[]{redPacketDetail.getTotalNum() + ""}));
            } else if (status == 2) {
                this.txtRedCount.setText("红包已过期，" + getString(R.string.redpacket_detail_count, new Object[]{redPacketDetail.getTotalNum() + "", redPacketDetail.getSurplusNum() + ""}));
            }
            RedPacketDetailAdapter redListAdapter = new RedPacketDetailAdapter(this.context, redPacketDetail.getDetailList(), this);
            if (!(status != 0 && redPacketDetail.getType() == 1 && redPacketDetail.getModule() == 1)) {
                z = false;
            }
            redListAdapter.showLucky(z);
            ((MyListview) findViewById(R.id.red_detail_listRed)).setAdapter((ListAdapter) redListAdapter);
        } else if (status == 0) {
            getView(R.id.red_detail_llySend).setVisibility(0);
            ((TextView) getView(R.id.red_detail_txtNotReceive)).setText(String.format("红包金额%s元,等待被领取！", this.redInfo.getTotalAmount()));
        } else if (status == 2) {
            getView(R.id.red_detail_llyOvertime).setVisibility(0);
        }
        if (this.isNormal && status != 0) {
            RedPacketUtil.sendRedPacketAckMessage((EMMessage) getBundle().getParcelable("msg"), status + 1);
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        Glide.with(this.context).load(this.redInfo.getHeadURL()).into(this.ivAvatar);
        this.tvGreeting.setText(this.redInfo.getRedpacketKeyRedpacketGreeting());
        this.tvNickname.setText(this.redInfo.getUserName());
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        startAct(RedPacketRecordActivity.class);
    }

    @Override // com.vsf2f.f2f.adapter.RedPacketDetailAdapter.getPacketListener
    public void getPacket(String money) {
        getView(R.id.red_detail_llyMoney).setVisibility(0);
        ((TextView) getView(R.id.red_detail_txtMoney)).setText(money);
    }
}
