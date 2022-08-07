package com.em.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.PermissionUtil;
import com.easeui.EaseConstant;
import com.easeui.widget.EaseAlertDialog;
import com.em.DemoHelper;
import com.em.db.InviteMessgeDao;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hyphenate.chat.EMClient;
import com.tencent.open.SocialConstants;
import com.tencent.smtt.sdk.WebView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.UserProfileBean;
import com.vsf2f.f2f.bean.result.FriendNexusBean;
import com.vsf2f.f2f.ui.MainActivity;
import com.vsf2f.f2f.ui.dialog.MoreOperateDialog;
import com.vsf2f.f2f.ui.needs.demand.UserNeedsActivity;
import com.vsf2f.f2f.ui.report.ReportMainActivity;
import com.vsf2f.f2f.ui.user.EnlargeActivity;
import com.vsf2f.f2f.ui.user.SendVerifyActivity;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;
import com.vsf2f.f2f.ui.user.change.ChangeRemarkActivity;
import com.vsf2f.f2f.ui.utils.web.WebUtils;
import com.vsf2f.f2f.ui.view.IdentyStateView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class UserProfileActivity extends BaseActivity {
    private static final int REQUEST_CHANGE_REMARK = 108;
    private static final int REQUEST_SEND_APPLY = 109;
    private Button btnAddFriend;
    private Button btnDelFriend;
    private Button btnSendMessage;
    private String friendName = "";
    private ImageView ivAvatar;
    private ImageView ivCom;
    private ImageView ivSex;
    private ImageView ivVip;
    private LinearLayout llPhone;
    private LinearLayout llPhoto;
    private LinearLayout llRemark;
    private TextView tvAge;
    private TextView tvContent;
    private TextView tvNickname;
    private TextView tvPhone;
    private TextView tvRemark;
    private TextView tvScore;
    private TextView tvShop;
    private TextView tvZhima;
    private UserProfileBean userInfo;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.em_activity_user_profile;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.empty, R.string.report_title);
        this.ivAvatar = (ImageView) getView(R.id.user_ivAvatar);
        this.ivVip = (ImageView) getView(R.id.user_imgVip);
        this.ivSex = (ImageView) getView(R.id.user_ivSex);
        this.ivCom = (ImageView) getView(R.id.user_ivCom);
        this.tvNickname = (TextView) getView(R.id.user_tvNickname);
        this.tvContent = (TextView) getView(R.id.user_tvContent);
        this.tvRemark = (TextView) getView(R.id.user_tvRemark);
        this.tvPhone = (TextView) getView(R.id.user_tvPhone);
        this.tvScore = (TextView) getView(R.id.user_tvScore);
        this.tvZhima = (TextView) getView(R.id.user_tvZhima);
        this.tvAge = (TextView) getView(R.id.user_tvAge);
        this.tvShop = (TextView) getViewAndClick(R.id.user_tvShop);
        this.llPhoto = (LinearLayout) getViewAndClick(R.id.user_llPhoto);
        this.llPhone = (LinearLayout) getViewAndClick(R.id.user_llPhone);
        this.llRemark = (LinearLayout) getViewAndClick(R.id.user_llRemark);
        this.btnAddFriend = (Button) getViewAndClick(R.id.btn_add_friend);
        this.btnDelFriend = (Button) getViewAndClick(R.id.btn_delete_friend);
        this.btnSendMessage = (Button) getViewAndClick(R.id.btn_send_message);
        setOnClickListener(R.id.user_profile_demand);
        setOnClickListener(R.id.user_profile_service);
        setOnClickListener(R.id.user_profile_comment);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        if (getBundle() != null) {
            Bundle bundle = getBundle();
            if (!TextUtils.isEmpty(bundle.getString("username"))) {
                this.friendName = bundle.getString("username");
                getFriendInfo2(this.friendName);
            }
        }
    }

    private void getFriendInfo2(String friendName) {
        getClient().setShowDialog(true);
        getClient().get(R.string.API_GET_FRIEND_INFO2, ComUtil.getZCApi(this.context, getString(R.string.API_GET_FRIEND_INFO2, new Object[]{"customer", friendName})), UserProfileBean.class);
    }

    public void deleteFriend(String username, String friendName) {
        getClient().setShowDialog(true);
        getClient().post(R.string.API_DELETE_FRIEND, ComUtil.getF2FApi(this.context, "android/user/" + username + "/friends/" + friendName + "/delete.android"), FriendNexusBean.class);
    }

    public void checkAddFriend(String friendName) {
        getClient().get(R.string.API_CHECK_FRIEND, ComUtil.getF2FApi(this.context, getString(R.string.API_CHECK_FRIEND, new Object[]{DemoHelper.getInstance().getCurrentUserName(), friendName}) + "?checkRole=1"), String.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_CHECK_FRIEND /* 2131296302 */:
                if (result.getObj() != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("username", this.friendName);
                    startActForResult(SendVerifyActivity.class, bundle, 109);
                    return;
                }
                return;
            case R.string.API_DELETE_FRIEND /* 2131296320 */:
                if (result.getObj() != null) {
                    FriendNexusBean bean = (FriendNexusBean) result.getObj();
                    if (bean.getSeccuse().isEmpty() && !bean.getFailed().isEmpty()) {
                        MyLog.e("删除好友:非好友");
                    }
                    if (this.userInfo != null) {
                        DemoHelper.getInstance().deleteContact(this.userInfo.getCustomer().getUserName());
                        new InviteMessgeDao(this.context).deleteMessage(this.userInfo.getCustomer().getUserName());
                        MainActivity.getInstance().refreshFriendList();
                    }
                    finish();
                    return;
                }
                return;
            case R.string.API_GET_FRIEND_INFO /* 2131296351 */:
                if (result.getObj() != null) {
                    this.userInfo = (UserProfileBean) result.getObj();
                    updateUI();
                    return;
                }
                return;
            case R.string.API_GET_FRIEND_INFO2 /* 2131296352 */:
                if (result.getObj() != null) {
                    this.userInfo = (UserProfileBean) result.getObj();
                    updateUI();
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
        switch (result.getRequestCode()) {
            case R.string.API_GET_FRIEND_INFO /* 2131296351 */:
                MyToast.show(this, (int) R.string.get_data_failed);
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        if (this.userInfo != null) {
            this.tvScore.setText(this.userInfo.getPoints() + "分");
            UserProfileBean.CustomerBean customerBean = this.userInfo.getCustomer();
            this.friendName = customerBean.getUserName();
            this.tvZhima.setText(customerBean.getZmScore() + "");
            if (!"0".equals(customerBean.getLv())) {
                this.ivVip.setVisibility(0);
            } else {
                this.ivVip.setVisibility(8);
            }
            if (customerBean.getSeller() != null && !TextUtils.isEmpty(customerBean.getSellerName())) {
                this.tvShop.setText(customerBean.getSellerName());
            }
            if (!TextUtils.isEmpty(customerBean.getSellerName())) {
                this.tvShop.setText(customerBean.getSellerName());
            }
            if (customerBean.getNickName() != null) {
                this.tvNickname.setText(customerBean.getNickName());
            } else {
                this.tvNickname.setText(this.friendName);
            }
            if (customerBean.getAge() != 0) {
                this.tvAge.setText(customerBean.getAge() + "岁");
                this.tvAge.setVisibility(0);
            } else {
                this.tvAge.setVisibility(8);
            }
            if (customerBean.getContent() != null) {
                this.tvContent.setText(customerBean.getContent());
            }
            if ("-1".equals(customerBean.getGender())) {
                this.ivSex.setVisibility(8);
            } else {
                this.ivSex.setSelected("1".equals(customerBean.getGender()));
            }
            if (HyUtil.isEmpty(customerBean.getUserPic().getSpath())) {
                this.ivAvatar.setImageResource(R.mipmap.def_head);
            } else {
                Glide.with((FragmentActivity) this).load(customerBean.getUserPic().getSpath()).error((int) R.mipmap.def_head).into(this.ivAvatar);
                this.ivAvatar.setOnClickListener(this);
            }
            this.llPhoto.removeAllViews();
            List<UserProfileBean.UserPicBean> pics = this.userInfo.getPhotoAlbum();
            for (int i = 0; i < pics.size(); i++) {
                addPhoto(pics.get(i).getSpath());
            }
            boolean isMe = this.friendName.equals(DemoHelper.getInstance().getCurrentUserName());
            boolean isFriend = DemoHelper.getInstance().isContact(this.friendName).booleanValue();
            if (isFriend || isMe) {
                if (isFriend) {
                    this.btnSendMessage.setVisibility(0);
                    this.btnDelFriend.setVisibility(0);
                    this.llRemark.setVisibility(0);
                    this.tvRemark.setText(DemoHelper.getInstance().getContactList().get(this.friendName).getNick());
                    DemoHelper.getInstance().upContactAvatar(this.friendName, customerBean.getUserPic().getSpath());
                }
                if (isMe) {
                    this.llRemark.setVisibility(8);
                    this.llPhone.setEnabled(false);
                }
                if (!TextUtils.isEmpty(customerBean.getPhone())) {
                    this.tvPhone.setText(customerBean.getPhone());
                    this.llPhone.setVisibility(0);
                } else {
                    this.tvPhone.setText("无");
                    this.llPhone.setEnabled(false);
                    this.llPhone.setClickable(false);
                }
            } else {
                this.btnAddFriend.setVisibility(0);
                this.llRemark.setVisibility(8);
                this.llPhone.setVisibility(8);
            }
            ((IdentyStateView) getView(R.id.identyStateView)).setStatus(customerBean.getCertMobile(), customerBean.getCertRealname(), customerBean.getCertZhima(), customerBean.getCertAlipay(), customerBean.getCertWechat(), customerBean.getCertQq());
        }
    }

    private void addPhoto(String url) {
        View imgview = LayoutInflater.from(this.context).inflate(R.layout.item_imgview_44dp, (ViewGroup) null);
        ImageView img = (ImageView) getView(imgview, R.id.image);
        img.setEnabled(false);
        img.setClickable(false);
        Glide.with((FragmentActivity) this).load(url).error((int) R.drawable.img_empty).into(img);
        this.llPhoto.addView(imgview);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        int id = v.getId();
        if (this.userInfo != null) {
            switch (id) {
                case R.id.user_ivAvatar /* 2131756580 */:
                    Bundle bundle3 = new Bundle();
                    bundle3.putString(Constant.FLAG, this.userInfo.getCustomer().getUserPic().getPath());
                    startAct(EnlargeActivity.class, bundle3);
                    return;
                case R.id.user_imgVip /* 2131756581 */:
                case R.id.user_tvNickname /* 2131756582 */:
                case R.id.user_txtZhimaCode /* 2131756583 */:
                case R.id.user_tvZhima /* 2131756584 */:
                case R.id.user_ivSex /* 2131756585 */:
                case R.id.user_tvAge /* 2131756586 */:
                case R.id.user_ivCom /* 2131756587 */:
                case R.id.user_llPhotos /* 2131756591 */:
                case R.id.user_tvRemark /* 2131756594 */:
                case R.id.user_llScore /* 2131756595 */:
                case R.id.user_tvScore /* 2131756596 */:
                case R.id.user_llShop /* 2131756597 */:
                case R.id.user_tvPhone /* 2131756600 */:
                case R.id.user_points /* 2131756601 */:
                case R.id.user_tvContent /* 2131756602 */:
                default:
                    return;
                case R.id.user_profile_demand /* 2131756588 */:
                    toUserNeeds("demand");
                    return;
                case R.id.user_profile_service /* 2131756589 */:
                    toUserNeeds(NotificationCompat.CATEGORY_SERVICE);
                    return;
                case R.id.user_profile_comment /* 2131756590 */:
                    toUserNeeds("comment");
                    return;
                case R.id.user_llPhoto /* 2131756592 */:
                    ArrayList<String> picStrs = new ArrayList<>();
                    ArrayList<String> picStrs2 = new ArrayList<>();
                    List<UserProfileBean.UserPicBean> pics = this.userInfo.getPhotoAlbum();
                    for (int i = 0; i < pics.size(); i++) {
                        picStrs.add(pics.get(i).getSpath());
                        picStrs2.add(pics.get(i).getPath());
                    }
                    Bundle bundle1 = new Bundle();
                    bundle1.putStringArrayList(SocialConstants.PARAM_IMAGE, picStrs);
                    bundle1.putStringArrayList("pics2", picStrs2);
                    startAct(GridPicActivity.class, bundle1);
                    return;
                case R.id.user_llRemark /* 2131756593 */:
                    Bundle bundle = new Bundle();
                    bundle.putString("username", this.friendName);
                    bundle.putString(EaseConstant.EXTRA_NICK_NAME, this.tvRemark.getText().toString());
                    startActForResult(ChangeRemarkActivity.class, bundle, 108);
                    return;
                case R.id.user_tvShop /* 2131756598 */:
                    if (TextUtils.isEmpty(this.userInfo.getCustomer().getSellerName())) {
                        MyToast.show(this.context, "对方还没有开店");
                        return;
                    }
                    Bundle bundle2 = new Bundle();
                    bundle2.putString(Constant.FLAG, WebUtils.getTokenUrl(this.context, "/m/shop/" + this.friendName + ".mobile"));
                    bundle2.putBoolean(Constant.FLAG2, true);
                    startAct(WebKitLocalActivity.class, bundle2);
                    return;
                case R.id.user_llPhone /* 2131756599 */:
                    final String tel = this.userInfo.getCustomer().getPhone();
                    new AlertDialog.Builder(this).setTitle(R.string.call_the_phone).setIcon(R.drawable.ease_chat_voice_call_receive).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() { // from class: com.em.ui.UserProfileActivity.3
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialog, int which) {
                            if (PermissionUtil.getCallPhonePermissions(UserProfileActivity.this, 111)) {
                                Intent intent = new Intent("android.intent.action.CALL");
                                intent.setData(Uri.parse(WebView.SCHEME_TEL + tel));
                                if (ActivityCompat.checkSelfPermission(UserProfileActivity.this, "android.permission.CALL_PHONE") == 0) {
                                    UserProfileActivity.this.startActivity(intent);
                                }
                            }
                        }
                    }).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).show();
                    return;
                case R.id.btn_send_message /* 2131756603 */:
                    Bundle bun = new Bundle();
                    bun.putSerializable("username", this.friendName);
                    startAct(ChatActivity.class, bun);
                    finish();
                    return;
                case R.id.btn_add_friend /* 2131756604 */:
                    addContact(this.friendName);
                    return;
                case R.id.btn_delete_friend /* 2131756605 */:
                    new AlertDialog.Builder(this).setTitle(R.string.delete_friend).setIcon(17301564).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() { // from class: com.em.ui.UserProfileActivity.2
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialog, int which) {
                            UserProfileActivity.this.deleteFriend(DemoHelper.getInstance().getCurrentUserName(), UserProfileActivity.this.friendName);
                        }
                    }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.em.ui.UserProfileActivity.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                    return;
            }
        }
    }

    private void toUserNeeds(String type) {
        Bundle bundle1 = new Bundle();
        bundle1.putString(com.vsf2f.f2f.ui.utils.Constant.USER_BUCKET, this.userInfo.getCustomer().getUserName());
        bundle1.putString("type", type);
        startAct(UserNeedsActivity.class, bundle1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case 108:
                    if (data != null) {
                        String remark = data.getStringExtra(EaseConstant.EXTRA_NICK_NAME);
                        if (TextUtils.isEmpty(remark)) {
                            remark = this.userInfo.getCustomer().getNickName();
                        }
                        if (TextUtils.isEmpty(remark)) {
                            remark = this.friendName;
                        }
                        this.tvRemark.setText(remark);
                        MainActivity.getInstance().refreshFriendList();
                        DemoHelper.getInstance().upContactNick(this.friendName, remark);
                        setResult(-1);
                        return;
                    }
                    return;
                case 109:
                    this.btnAddFriend.setOnClickListener(null);
                    this.btnAddFriend.setBackgroundResource(R.color.txt_red_deep);
                    this.btnAddFriend.setText("已发送好友申请");
                    return;
                default:
                    return;
            }
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    public void addContact(String userName) {
        if (EMClient.getInstance().getCurrentUser().equals(userName)) {
            new EaseAlertDialog(this, (int) R.string.not_add_myself).show();
        } else if (DemoHelper.getInstance().getContactList().containsKey(userName)) {
            new EaseAlertDialog(this, (int) R.string.This_user_is_already_your_friend).show();
        } else if (EMClient.getInstance().contactManager().getBlackListUsernames().contains(userName)) {
            new EaseAlertDialog(this, (int) R.string.user_already_in_blacklist).show();
        } else {
            checkAddFriend(this.friendName);
        }
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        MoreOperateDialog dialog = new MoreOperateDialog(this.context);
        dialog.setListener(new BaseDialog.IConfirmListener() { // from class: com.em.ui.UserProfileActivity.4
            @Override // com.hy.frame.common.BaseDialog.IConfirmListener
            public void onDlgConfirm(BaseDialog dlg, int flag) {
                UserProfileActivity.this.operateType();
            }
        });
        dialog.showBtnReport();
        dialog.show();
    }

    public void operateType() {
        Bundle bundle = new Bundle();
        bundle.putInt("objType", 3);
        bundle.putString("objId", this.friendName);
        bundle.putString("reportedUser", this.friendName);
        startAct(ReportMainActivity.class, bundle);
    }
}
