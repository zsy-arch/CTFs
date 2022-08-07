package com.vsf2f.f2f.ui.needs.service;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.NotificationCompat;
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
import com.hy.http.AjaxParams;
import com.hyphenate.chat.MessageEncoder;
import com.tencent.open.SocialConstants;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.DemandPicAdapter;
import com.vsf2f.f2f.bean.DemandDetailBean;
import com.vsf2f.f2f.bean.DemandUserInfo;
import com.vsf2f.f2f.ui.dialog.MoreOperateDialog;
import com.vsf2f.f2f.ui.needs.demand.OrderDetailActivity;
import com.vsf2f.f2f.ui.report.ReportMainActivity;
import com.vsf2f.f2f.ui.user.change.BindPhoneActivity;
import com.vsf2f.f2f.ui.utils.photo.PhotoPreviewIntent;
import com.vsf2f.f2f.ui.view.IdentyStateView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ServiceInfoActivity extends BaseActivity {
    private static final int REQUEST_PREVIEW_CODE = 1001;
    private MyGridView _mgvPic;
    private NavView _nvState;
    private TextView _tvPrice;
    private TextView _tvTel;
    private TextView _tvTelor;
    private TextView _tvUnit;
    private TextView _txtContent;
    private TextView _txtNum;
    private TextView _txtTitle;
    private MusicPlayer _voice;
    private int demandId;
    private DemandDetailBean detailBean;
    private ImageView iv_rivUserhead;
    private ImageView iv_sex;
    private ImageView iv_vip;
    private int shotid;
    private int soldState;
    private TextView tv_age;
    private TextView tv_name;
    private TextView tv_service_area;
    private TextView tv_service_mode;
    private TextView tv_service_type;
    private TextView tv_zhima;

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_service_info;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.title_service_detail, 0);
        this.demandId = getBundle().getInt("id");
        this.shotid = getBundle().getInt("shotid");
        if (this.shotid == 0) {
            setHeaderRight(R.drawable.icon_menu_more);
        }
        this.iv_rivUserhead = (ImageView) getView(R.id.ServiceInfo_rivUserhead);
        this.iv_sex = (ImageView) getView(R.id.ServiceInfo_ivSex);
        this.iv_vip = (ImageView) getView(R.id.ServiceInfo_ivVip);
        this.tv_name = (TextView) getView(R.id.ServiceInfo_tvName);
        this.tv_zhima = (TextView) getView(R.id.ServiceInfo_tvZhima);
        this.tv_age = (TextView) getView(R.id.ServiceInfo_tvAge);
        this.tv_service_mode = (TextView) getView(R.id.tv_service_mode);
        this.tv_service_type = (TextView) getView(R.id.tv_service_type);
        this.tv_service_area = (TextView) getView(R.id.tv_service_area);
        this._mgvPic = (MyGridView) getView(R.id.ServiceInfo_mgvPic);
        this._tvTelor = (TextView) getView(R.id.ServiceInfo_tvTelor);
        this._tvTel = (TextView) getView(R.id.ServiceInfo_tvTel);
        this._tvPrice = (TextView) getView(R.id.ServiceInfo_txtPrice);
        this._tvUnit = (TextView) getView(R.id.ServiceInfo_txtUnit);
        this._txtNum = (TextView) getView(R.id.ServiceInfo_txtNum);
        this._txtTitle = (TextView) getView(R.id.ServiceInfo_txtTitle);
        this._txtContent = (TextView) getView(R.id.ServiceInfo_txtContent);
        this._voice = (MusicPlayer) getViewAndClick(R.id.ServiceInfo_VoicePlayer);
        this._nvState = (NavView) getViewAndClick(R.id.ServiceInfo_nvState);
        setOnClickListener(R.id.ServiceInfo_nvEdit);
        setOnClickListener(R.id.ServiceInfo_nvChat);
        setOnClickListener(R.id.ServiceInfo_nvHelp);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this._mgvPic.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.vsf2f.f2f.ui.needs.service.ServiceInfoActivity.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (ServiceInfoActivity.this.detailBean != null && ServiceInfoActivity.this.detailBean.getImgUrlList().size() != 0) {
                    PhotoPreviewIntent intent = new PhotoPreviewIntent(ServiceInfoActivity.this.context);
                    intent.setCurrentItem(i);
                    intent.setDeleteMode(false);
                    intent.setPhotoPaths((ArrayList) ServiceInfoActivity.this.detailBean.getImgUrlList());
                    ServiceInfoActivity.this.startActivityForResult(intent, 1001);
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
            this._txtContent.setText(this.detailBean.getDescription());
            this._txtNum.setText("（库存：" + this.detailBean.getInventory() + "）");
            this._tvTelor.setText(this.detailBean.getContactUser() + "");
            this._tvTel.setText(this.detailBean.getContactPhone() + "");
            this._tvPrice.setText(this.detailBean.getReward() + " 元 ");
            this._tvUnit.setText(" / " + this.detailBean.getUnit());
            if (this.detailBean.getPublishUserObj().getAge() != 0) {
                this.tv_age.setText(this.detailBean.getPublishUserObj().getAge() + "岁");
            }
            this.tv_service_mode.setText(this.detailBean.getServiceModeStr());
            this.tv_service_type.setText(this.detailBean.getShareTypeObj().getName());
            this.tv_service_area.setText(this.detailBean.getAreaStr());
            DemandUserInfo userObjBean = this.detailBean.getPublishUserObj();
            ((IdentyStateView) getView(R.id.identyStateView)).setStatus(userObjBean.getCertMobile(), userObjBean.getCertRealname(), userObjBean.getCertZhima(), userObjBean.getCertAlipay(), userObjBean.getCertWechat(), userObjBean.getCertQq());
            if (userObjBean.getGender() == -1) {
                this.iv_sex.setVisibility(8);
            } else {
                this.iv_sex.setSelected(userObjBean.getGender() == 1);
            }
            if ("4".equals(userObjBean.getLv())) {
                this.iv_vip.setVisibility(0);
            } else {
                this.iv_vip.setVisibility(8);
            }
            Glide.with(this.context).load(userObjBean.getUserPic().getPath()).error((int) R.mipmap.def_head).into(this.iv_rivUserhead);
            this.tv_name.setText(userObjBean.getNickName() + "");
            this.tv_zhima.setText(userObjBean.getZmScore() + "分");
            if (TextUtils.isEmpty(this.detailBean.getVoiceDuration()) || TextUtils.isEmpty(this.detailBean.getVoiceFullUrl())) {
                this._voice.setVisibility(4);
            } else {
                this._voice.setDuration(this.detailBean.getVoiceDuration());
                this._voice.setVisibility(0);
            }
            this._mgvPic.setAdapter((ListAdapter) new DemandPicAdapter(this, this.detailBean.getImgUrlList()));
            if (!OrderDetailActivity.class.getSimpleName().equals(getIntent().getStringExtra(Constant.LAST_ACT))) {
                String user = DemoHelper.getInstance().getCurrentUserName();
                if (TextUtils.isEmpty(user) || !user.equals(this.detailBean.getPublishUser())) {
                    setHeaderRight(R.drawable.icon_menu_more);
                    findViewById(R.id.ll_opre).setVisibility(0);
                } else {
                    findViewById(R.id.ll_mine).setVisibility(0);
                }
            }
            this.soldState = this.detailBean.getStatus();
            if (this.soldState == 1) {
                this._nvState.setText(R.string.down_service);
            }
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        int i = 0;
        if (!isLogin()) {
            MyToast.show(getApplicationContext(), "请先登录");
        } else if (this.detailBean != null) {
            String cusname = DemoHelper.getInstance().getCurrentUserName();
            String sername = this.detailBean.getPublishUserObj().getUserName();
            if (!TextUtils.isEmpty(sername)) {
                Bundle bundle = new Bundle();
                switch (v.getId()) {
                    case R.id.ServiceInfo_VoicePlayer /* 2131756134 */:
                        new VoicePlayer(this, this.detailBean.getVoiceFullUrl(), this._voice).onClick(v);
                        return;
                    case R.id.ServiceInfo_nvState /* 2131756146 */:
                        if (this.soldState != 1) {
                            i = 1;
                        }
                        changeSoldStatus(i);
                        return;
                    case R.id.ServiceInfo_nvEdit /* 2131756147 */:
                        bundle.putInt("moId", this.detailBean.getMoId());
                        startAct(ServiceModifyActivity.class, bundle);
                        return;
                    case R.id.ServiceInfo_nvChat /* 2131756148 */:
                        if (cusname.equals(sername)) {
                            MyToast.show(getApp(), "不能和自己聊天");
                            return;
                        }
                        Map<String, String> map = new HashMap<>();
                        map.put("type", NotificationCompat.CATEGORY_SERVICE);
                        map.put("moId", this.detailBean.getMoId() + "");
                        map.put("title", this.detailBean.getTitle() + "");
                        map.put(SocialConstants.PARAM_APP_DESC, this.detailBean.getDescription() + "");
                        map.put("reward", this.detailBean.getReward() + "");
                        map.put("unit", this.detailBean.getUnit() + "");
                        map.put("serviceMode", this.detailBean.getServiceMode() + "");
                        map.put("address", this.detailBean.getAddress() + "");
                        map.put("voiceUrl", this.detailBean.getVoiceFullUrl() + "");
                        map.put("voiceDuration", this.detailBean.getVoiceDuration() + "");
                        map.put("publishUser", sername);
                        JSONObject json = new JSONObject(map);
                        bundle.putString("username", sername);
                        bundle.putString(MessageEncoder.ATTR_EXT, json.toString());
                        bundle.putBoolean(EaseConstant.BACK_TYPE, false);
                        startAct(ChatActivity.class, bundle);
                        return;
                    case R.id.ServiceInfo_nvHelp /* 2131756149 */:
                        if (!UserShared.getInstance().getIsVerifyState(this.context)) {
                            return;
                        }
                        if (cusname.equals(sername)) {
                            MyToast.show(getApp(), "不能接自己的服务");
                            return;
                        }
                        bundle.putSerializable("data", this.detailBean);
                        startAct(BespeakActivity.class, bundle);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private void getDetailInfo() {
        getClient().setShowDialog(true);
        getClient().get(R.string.API_SERVICE_DETAIL, this.shotid != 0 ? ComUtil.getXDDApi(this.context, getString(R.string.API_SERVICE_DETAIL)) + "?shareServiceSnapshotId=" + this.shotid : ComUtil.getXDDApi(this.context, getString(R.string.API_SERVICE_DETAIL)) + "?shareServiceId=" + this.demandId, DemandDetailBean.class);
    }

    private void changeSoldStatus(int type) {
        AjaxParams params = new AjaxParams();
        params.put("shareServiceId", this.detailBean.getMoId());
        params.put("type", type);
        getClient().setShowDialog(true);
        getClient().post(R.string.API_MYSERVICE_UPDATESTATUS, ComUtil.getXDDApi(this.context, getString(R.string.API_MYSERVICE_UPDATESTATUS)), params, String.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        int requestCode = result.getRequestCode();
        if (result.getErrorCode() == 0) {
            switch (requestCode) {
                case R.string.API_CIRCLES_PUBLIC /* 2131296309 */:
                    MyToast.show(this.context, "分享圈子成功");
                    return;
                case R.string.API_MYSERVICE_UPDATESTATUS /* 2131296376 */:
                    if (this.soldState == 1) {
                        this.soldState = 0;
                        this._nvState.setText(R.string.up_service);
                        MyToast.show(this.context, "下架成功");
                        return;
                    }
                    this.soldState = 1;
                    this._nvState.setText(R.string.down_service);
                    MyToast.show(this.context, "上架成功");
                    return;
                case R.string.API_SERVICE_DETAIL /* 2131296412 */:
                    this.detailBean = (DemandDetailBean) result.getObj();
                    updateUI();
                    return;
                default:
                    return;
            }
        }
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        MoreOperateDialog operateDialog = new MoreOperateDialog(this.context);
        operateDialog.setListener(new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.needs.service.ServiceInfoActivity.2
            @Override // com.hy.frame.common.BaseDialog.IConfirmListener
            public void onDlgConfirm(BaseDialog dlg, int flag) {
                if (flag == R.id.more_btnShare2) {
                    ServiceInfoActivity.this.shareCircle();
                } else {
                    ServiceInfoActivity.this.operateType();
                }
            }
        });
        operateDialog.showBtnReport();
        if (this.soldState == 1) {
            operateDialog.showBtnShare2();
        }
        operateDialog.show();
    }

    public void shareCircle() {
        if (!isLogin()) {
            MyToast.show(getApplicationContext(), "请先登录");
            return;
        }
        getClient().setShowDialog(true);
        getClient().post(R.string.API_CIRCLES_PUBLIC, ComUtil.getF2FApi(this.context, getString(R.string.API_CIRCLES_PUBLIC)), new ShareUtils(this.context).shareNeeds(this.detailBean, NotificationCompat.CATEGORY_SERVICE), String.class, false);
    }

    public void operateType() {
        if (!isLogin()) {
            MyToast.show(getApplicationContext(), "请先登录");
        } else if (UserShared.getInstance().getInt(com.vsf2f.f2f.ui.utils.Constant.CERT_MOBILE) != 1) {
            new EaseAlertDialog(this.context, (int) R.string.prompt, (int) R.string.not_bing_phone_prompt, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.needs.service.ServiceInfoActivity.3
                @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                public void onResult(boolean confirmed, Bundle bundle) {
                    if (confirmed) {
                        ServiceInfoActivity.this.startAct(BindPhoneActivity.class);
                    }
                    ServiceInfoActivity.this.finish();
                }
            }, true).show();
        } else if (this.detailBean != null) {
            Bundle bundle = new Bundle();
            bundle.putInt("objType", 5);
            bundle.putString("objId", this.detailBean.getMoId() + "");
            bundle.putString("reportedUser", this.detailBean.getPublishUser() + "");
            startAct(ReportMainActivity.class, bundle);
        } else {
            MyToast.show(this.context, "无法获取数据");
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        VoicePlayer.stopVoice();
    }

    @Override // com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        VoicePlayer.stopVoice();
        super.onDestroy();
    }
}
