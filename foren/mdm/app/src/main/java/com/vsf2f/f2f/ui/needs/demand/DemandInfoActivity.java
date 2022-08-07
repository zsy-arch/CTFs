package com.vsf2f.f2f.ui.needs.demand;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.server.audio.MusicPlayer;
import com.cdlinglu.server.audio.VoicePlayer;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.ShareUtils;
import com.easeui.EaseConstant;
import com.easeui.widget.EaseAlertDialog;
import com.em.DemoHelper;
import com.em.ui.ChatActivity;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.Constant;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.MyGridView;
import com.hy.frame.view.NavView;
import com.hyphenate.chat.MessageEncoder;
import com.tencent.open.SocialConstants;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.DemandPicAdapter;
import com.vsf2f.f2f.bean.DemandDetailBean;
import com.vsf2f.f2f.bean.DemandUserInfo;
import com.vsf2f.f2f.ui.dialog.MoreOperateDialog;
import com.vsf2f.f2f.ui.report.ReportMainActivity;
import com.vsf2f.f2f.ui.user.change.BindPhoneActivity;
import com.vsf2f.f2f.ui.utils.photo.PhotoPreviewIntent;
import com.vsf2f.f2f.ui.view.IdentyStateView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class DemandInfoActivity extends BaseActivity {
    private static final int REQUEST_PREVIEW_CODE = 1001;
    private ImageView _ivSex;
    private ImageView _ivVip;
    private MyGridView _mgvPic;
    private NavView _nvHelp;
    private ImageView _rivUserhead;
    private TextView _tvAge;
    private TextView _tvMode;
    private TextView _tvName;
    private TextView _tvPrice;
    private TextView _tvTel;
    private TextView _tvTelor;
    private TextView _tvTime;
    private TextView _tvType;
    private TextView _tvUnit;
    private TextView _tvZhima;
    private TextView _txtAddress;
    private TextView _txtContent;
    private TextView _txtTitle;
    private MusicPlayer _voice;
    private int demandId;
    private DemandDetailBean detailBean;
    private MoreOperateDialog operateDialog;
    private int shotid;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_demand_info;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.title_demand_detail, 0);
        this.demandId = getBundle().getInt("id");
        this.shotid = getBundle().getInt("shotid");
        if (this.shotid == 0) {
            setHeaderRight(R.drawable.icon_menu_more);
        }
        this._mgvPic = (MyGridView) getView(R.id.DemandInfo_mgvPic);
        this._tvTelor = (TextView) getView(R.id.DemandInfo_tvTelor);
        this._tvTel = (TextView) getView(R.id.DemandInfo_tvTel);
        this._tvZhima = (TextView) getView(R.id.DemandInfo_zhima);
        this._tvName = (TextView) getView(R.id.DemandInfo_name);
        this._tvAge = (TextView) getView(R.id.DemandInfo_age);
        this._tvType = (TextView) getView(R.id.DemandInfo_type);
        this._tvMode = (TextView) getView(R.id.DemandInfo_mode);
        this._tvTime = (TextView) getView(R.id.DemandInfo_tvTime);
        this._tvPrice = (TextView) getView(R.id.DemandInfo_price);
        this._tvUnit = (TextView) getView(R.id.DemandInfo_txtUnit);
        this._txtTitle = (TextView) getView(R.id.DemandInfo_title);
        this._txtContent = (TextView) getView(R.id.DemandInfo_content);
        this._txtAddress = (TextView) getView(R.id.DemandInfo_address);
        this._ivSex = (ImageView) getView(R.id.DemandInfo_ivSex);
        this._ivVip = (ImageView) getView(R.id.DemandInfo_ivVip);
        this._rivUserhead = (ImageView) getView(R.id.DemandInfo_rivUserhead);
        this._voice = (MusicPlayer) getViewAndClick(R.id.DemandInfo_VoicePlayer);
        this._nvHelp = (NavView) getViewAndClick(R.id.DemandInfo_nvHelp);
        setOnClickListener(R.id.DemandInfo_nvChat);
        setOnClickListener(R.id.DemandInfo_nvEdit);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this._mgvPic.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.vsf2f.f2f.ui.needs.demand.DemandInfoActivity.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (DemandInfoActivity.this.detailBean != null && DemandInfoActivity.this.detailBean.getImgUrlList().size() != 0) {
                    PhotoPreviewIntent intent = new PhotoPreviewIntent(DemandInfoActivity.this.context);
                    intent.setCurrentItem(i);
                    intent.setDeleteMode(false);
                    intent.setPhotoPaths((ArrayList) DemandInfoActivity.this.detailBean.getImgUrlList());
                    DemandInfoActivity.this.startActivityForResult(intent, 1001);
                }
            }
        });
        getDetailInfo();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        if (this.detailBean != null) {
            this._txtTitle.setText(this.detailBean.getTitle());
            this._txtAddress.setText(this.detailBean.getAddress());
            this._txtContent.setText(this.detailBean.getDescription());
            this._tvType.setText(this.detailBean.getTypeName());
            this._tvTime.setText(this.detailBean.getPublishTime());
            this._tvMode.setText(this.detailBean.getServiceModeStr());
            this._tvPrice.setText(this.detailBean.getReward() + " 元 ");
            this._tvUnit.setText(" / " + this.detailBean.getUnit());
            this._tvTelor.setText(this.detailBean.getContactUser());
            this._tvTel.setText(this.detailBean.getContactPhone());
            DemandUserInfo userObjBean = this.detailBean.getPublishUserObj();
            ((IdentyStateView) getView(R.id.identyStateView)).setStatus(userObjBean.getCertMobile(), userObjBean.getCertRealname(), userObjBean.getCertZhima(), userObjBean.getCertAlipay(), userObjBean.getCertWechat(), userObjBean.getCertQq());
            this._tvZhima.setText(userObjBean.getZmScore() + "");
            this._tvName.setText(userObjBean.getNickName() + "");
            if (userObjBean.getAge() != 0) {
                this._tvAge.setText(userObjBean.getAge() + "岁");
            }
            if (userObjBean.getGender() == -1) {
                this._ivSex.setVisibility(8);
            } else {
                this._ivSex.setSelected(userObjBean.getGender() == 1);
            }
            if ("4".equals(userObjBean.getLv())) {
                this._ivVip.setVisibility(0);
            } else {
                this._ivVip.setVisibility(8);
            }
            Glide.with((FragmentActivity) this).load(userObjBean.getUserPic().getPath()).error((int) R.mipmap.def_head).into(this._rivUserhead);
            if (TextUtils.isEmpty(this.detailBean.getVoiceDuration()) || TextUtils.isEmpty(this.detailBean.getVoiceFullUrl())) {
                this._voice.setVisibility(4);
            } else {
                this._voice.setDuration(this.detailBean.getVoiceDuration());
            }
            this._mgvPic.setAdapter((ListAdapter) new DemandPicAdapter(this, this.detailBean.getImgUrlList()));
            if (!OrderDetailActivity.class.getSimpleName().equals(getIntent().getStringExtra(Constant.LAST_ACT))) {
                String user = DemoHelper.getInstance().getCurrentUserName();
                if (TextUtils.isEmpty(user) || !user.equals(this.detailBean.getPublishUser())) {
                    setHeaderRight(R.drawable.icon_menu_more);
                    findViewById(R.id.ll_opre).setVisibility(0);
                    return;
                }
                findViewById(R.id.ll_mine).setVisibility(0);
            }
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        if (!isLogin()) {
            MyToast.show(getApplicationContext(), "请先登录");
        } else if (this.detailBean != null) {
            String cusname = DemoHelper.getInstance().getCurrentUserName();
            String demandname = this.detailBean.getPublishUserObj().getUserName();
            if (!TextUtils.isEmpty(demandname)) {
                Bundle bundle = new Bundle();
                switch (v.getId()) {
                    case R.id.DemandInfo_VoicePlayer /* 2131755934 */:
                        new VoicePlayer(this, this.detailBean.getVoiceFullUrl(), this._voice).onClick(v);
                        return;
                    case R.id.DemandInfo_nvEdit /* 2131755951 */:
                        bundle.putInt("id", this.detailBean.getMoId());
                        startAct(DemandDetailActivity.class, bundle);
                        return;
                    case R.id.DemandInfo_nvChat /* 2131755953 */:
                        if (cusname.equals(demandname)) {
                            MyToast.show(getApp(), "不能和自己聊天");
                            return;
                        }
                        Map<String, String> map = new HashMap<>();
                        map.put("type", "demand");
                        map.put("moId", this.detailBean.getMoId() + "");
                        map.put("title", this.detailBean.getTitle() + "");
                        map.put(SocialConstants.PARAM_APP_DESC, this.detailBean.getDescription() + "");
                        map.put("reward", this.detailBean.getReward() + "");
                        map.put("unit", this.detailBean.getUnit() + "");
                        map.put("serviceMode", this.detailBean.getServiceMode() + "");
                        map.put("address", this.detailBean.getAddress() + "");
                        map.put("voiceUrl", this.detailBean.getVoiceFullUrl() + "");
                        map.put("voiceDuration", this.detailBean.getVoiceDuration() + "");
                        map.put("publishUser", demandname);
                        bundle.putString(MessageEncoder.ATTR_EXT, new JSONObject(map).toString());
                        bundle.putString("username", demandname);
                        bundle.putBoolean(EaseConstant.BACK_TYPE, false);
                        startAct(ChatActivity.class, bundle);
                        return;
                    case R.id.DemandInfo_nvHelp /* 2131755954 */:
                        if (!UserShared.getInstance().getIsVerifyState(this.context)) {
                            return;
                        }
                        if (cusname.equals(demandname)) {
                            MyToast.show(getApp(), "不能接自己的需求");
                            return;
                        } else if (this.detailBean.getStatus() == 10) {
                            MyToast.show(getApp(), "此需求方尚未支付定金");
                            return;
                        } else {
                            bundle.putSerializable("data", this.detailBean);
                            startAct(ToHelpActivity.class, bundle);
                            return;
                        }
                    default:
                        return;
                }
            }
        }
    }

    private void getDetailInfo() {
        getClient().setShowDialog(true);
        getClient().get(R.string.API_DEMAND_DETAIL, this.shotid != 0 ? ComUtil.getXDDApi(this.context, getString(R.string.API_DEMAND_DETAIL)) + "?shareSnapshotId=" + this.shotid : ComUtil.getXDDApi(this.context, getString(R.string.API_DEMAND_DETAIL)) + "?moId=" + this.demandId, DemandDetailBean.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_CIRCLES_PUBLIC /* 2131296309 */:
                MyToast.show(this.context, "分享圈子成功");
                return;
            case R.string.API_DEMAND_DETAIL /* 2131296324 */:
                this.detailBean = (DemandDetailBean) result.getObj();
                updateUI();
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        if (this.operateDialog == null) {
            this.operateDialog = new MoreOperateDialog(this.context);
            this.operateDialog.setListener(new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.needs.demand.DemandInfoActivity.2
                @Override // com.hy.frame.common.BaseDialog.IConfirmListener
                public void onDlgConfirm(BaseDialog dlg, int flag) {
                    if (flag == R.id.more_btnShare2) {
                        DemandInfoActivity.this.shareCircle();
                    } else {
                        DemandInfoActivity.this.operateType();
                    }
                }
            });
            this.operateDialog.showBtnReport();
            if (this.detailBean != null && this.detailBean.getStatus() == 11) {
                this.operateDialog.showBtnShare2();
            }
        }
        this.operateDialog.show();
    }

    public void shareCircle() {
        if (!isLogin()) {
            MyToast.show(getApplicationContext(), "请先登录");
        } else if (UserShared.getInstance().getInt(com.vsf2f.f2f.ui.utils.Constant.CERT_MOBILE) != 1) {
            new EaseAlertDialog(this.context, (int) R.string.prompt, (int) R.string.not_bing_phone_prompt, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.needs.demand.DemandInfoActivity.3
                @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                public void onResult(boolean confirmed, Bundle bundle) {
                    if (confirmed) {
                        DemandInfoActivity.this.startAct(BindPhoneActivity.class);
                    }
                    DemandInfoActivity.this.finish();
                }
            }, true).show();
        } else {
            getClient().setShowDialog(true);
            getClient().post(R.string.API_CIRCLES_PUBLIC, ComUtil.getF2FApi(this.context, getString(R.string.API_CIRCLES_PUBLIC)), new ShareUtils(this.context).shareNeeds(this.detailBean, "demand"), String.class, false);
        }
    }

    public void operateType() {
        if (!isLogin()) {
            MyToast.show(getApplicationContext(), "请先登录");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("objType", 4);
        bundle.putString("objId", this.detailBean.getMoId() + "");
        bundle.putString("reportedUser", this.detailBean.getPublishUser() + "");
        startAct(ReportMainActivity.class, bundle);
    }

    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        VoicePlayer.stopVoice();
    }

    @Override // com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        VoicePlayer.stopVoice();
    }
}
