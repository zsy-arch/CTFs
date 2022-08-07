package com.vsf2f.f2f.ui.user;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationManagerCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import com.alipay.sdk.app.AuthTask;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.common.MyApplication;
import com.cdlinglu.utils.CameraUtil;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.FlagUtil;
import com.cdlinglu.utils.IDUtil;
import com.easeui.EaseConstant;
import com.em.DemoHelper;
import com.em.ui.UserProfileActivity;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.KeyValueView;
import com.hy.frame.view.LoadingDialog;
import com.hy.frame.view.RotundityImageView;
import com.hy.http.AjaxParams;
import com.hyphenate.util.HanziToPinyin;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.PayStyleBean;
import com.vsf2f.f2f.bean.ThirdPartyBean;
import com.vsf2f.f2f.bean.UserInfo;
import com.vsf2f.f2f.ui.MainActivity;
import com.vsf2f.f2f.ui.dialog.PictureDialog;
import com.vsf2f.f2f.ui.identify.IdentifyActivity;
import com.vsf2f.f2f.ui.user.change.BindAliActivity;
import com.vsf2f.f2f.ui.user.change.BindPhoneActivity;
import com.vsf2f.f2f.ui.user.change.ChangeContentActivity;
import com.vsf2f.f2f.ui.user.change.ChangeInfoActivity;
import com.vsf2f.f2f.ui.user.change.ChangeSexActivity;
import com.vsf2f.f2f.ui.utils.Base64Helper;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import com.vsf2f.f2f.ui.utils.ali.AuthResult;
import com.vsf2f.f2f.ui.utils.listener.UploadPicListener;
import com.vsf2f.f2f.ui.utils.upload.UploadUtils;
import com.vsf2f.f2f.wxapi.WXEntryActivity;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Marker;

/* loaded from: classes2.dex */
public class UserDataActivity extends BaseActivity implements CameraUtil.CameraDealListener, PictureDialog.ConfirmDlgListener {
    private static final int SDK_AUTH_FLAG = 1;
    private int FLAG_WX;
    private CameraUtil camera;
    private UserInfo detail;
    private RotundityImageView ivAvatar;
    private KeyValueView kvAge;
    private KeyValueView kvAliAccount;
    private KeyValueView kvAliThird;
    private KeyValueView kvEmail;
    private KeyValueView kvIdcard;
    private KeyValueView kvInviter;
    private KeyValueView kvNickname;
    private KeyValueView kvPersonal;
    private KeyValueView kvPhone;
    private KeyValueView kvQqThird;
    private KeyValueView kvRealname;
    private KeyValueView kvSex;
    private KeyValueView kvUserName;
    private KeyValueView kvWxAccount;
    private KeyValueView kvWxThird;
    private LoadingDialog loadDlg;
    private IUiListener loginListener;
    private Tencent mTencent;
    private String openid;
    private PictureDialog pictureDlg;
    private IUiListener userInfoListener;
    @SuppressLint({"HandlerLeak"})
    private Handler mHandler = new Handler() { // from class: com.vsf2f.f2f.ui.user.UserDataActivity.2
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    AuthResult authResult = new AuthResult((Map) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        String authcode = authResult.getAuthCode();
                        String userid = authResult.getUserId();
                        ThirdPartyBean thirdParty = new ThirdPartyBean();
                        thirdParty.setOpenId(userid);
                        thirdParty.setUserId(userid);
                        thirdParty.setAuthCode(authcode);
                        thirdParty.setPartyType(4);
                        UserDataActivity.this.bindThird(thirdParty.toJson().toString());
                        return;
                    } else if (TextUtils.equals(resultStatus, "6001")) {
                        Toast.makeText(UserDataActivity.this.context, UserDataActivity.this.getString(R.string.be_canceled), 0).show();
                        return;
                    } else {
                        Toast.makeText(UserDataActivity.this.context, "授权失败", 0).show();
                        return;
                    }
                default:
                    return;
            }
        }
    };
    private BroadcastReceiver receiver = new BroadcastReceiver() { // from class: com.vsf2f.f2f.ui.user.UserDataActivity.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(WXEntryActivity.ACTION_SHARE_SUCCESS)) {
                String jsonWx = intent.getStringExtra(Constant.FLAG);
                if (UserDataActivity.this.FLAG_WX == 1) {
                    UserDataActivity.this.bindWxAccount(jsonWx);
                } else {
                    UserDataActivity.this.bindThird(jsonWx);
                }
            }
        }
    };

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_user_data;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.userData, 0);
        setOnClickListener(R.id.user_data_kvHead);
        this.ivAvatar = (RotundityImageView) getView(R.id.user_data_imgHead);
        this.kvUserName = (KeyValueView) getViewAndClick(R.id.user_data_kvUserName);
        this.kvPhone = (KeyValueView) getViewAndClick(R.id.user_data_kvPhone);
        this.kvInviter = (KeyValueView) getViewAndClick(R.id.user_data_kvInviter);
        this.kvIdcard = (KeyValueView) getViewAndClick(R.id.user_data_kvIdcard);
        this.kvRealname = (KeyValueView) getViewAndClick(R.id.user_data_kvRealname);
        this.kvNickname = (KeyValueView) getViewAndClick(R.id.user_data_kvNickname);
        this.kvSex = (KeyValueView) getViewAndClick(R.id.user_data_kvSex);
        this.kvAge = (KeyValueView) getViewAndClick(R.id.user_data_kvAge);
        this.kvEmail = (KeyValueView) getViewAndClick(R.id.user_data_kvEmail);
        this.kvPersonal = (KeyValueView) getViewAndClick(R.id.user_data_kvPersonal);
        this.kvQqThird = (KeyValueView) getViewAndClick(R.id.user_data_kvQqThird);
        this.kvAliThird = (KeyValueView) getViewAndClick(R.id.user_data_kvAliThird);
        this.kvWxThird = (KeyValueView) getViewAndClick(R.id.user_data_kvWxThird);
        this.kvAliAccount = (KeyValueView) getViewAndClick(R.id.user_data_kvAliAccount);
        this.kvWxAccount = (KeyValueView) getViewAndClick(R.id.user_data_kvWxAccount);
        initBind();
        requestMoneyAccount();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        initHeaderBack(R.string.userData, 0);
        this.detail = getUserInfo(true, true);
        updateUI();
        if (!UserShared.getInstance().checkThirdParty()) {
            requestData();
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        getClient().get(R.string.API_USER_PERSONAL_DATA, ComUtil.getZCApi(this.context, getString(R.string.API_USER_PERSONAL_DATA, new Object[]{"info"}) + "?aliwx=1"), new AjaxParams(), UserInfo.class, false);
    }

    public void requestThirdParty() {
        getClient().get(R.string.API_THIRD_PARTY_BIND, ComUtil.getZCApi(this.context, getString(R.string.API_THIRD_PARTY_BIND, new Object[]{this.detail.getUserName()})), new AjaxParams(), PayStyleBean.class, true);
    }

    public void requestMoneyAccount() {
        getClient().get(R.string.API_USER_BANK_ITEMS, ComUtil.getZCApi(this.context, getString(R.string.API_USER_BANK_ITEMS)), new AjaxParams(), PayStyleBean.class, true);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        getClient().setShowDialog(false);
        switch (result.getRequestCode()) {
            case R.string.API_ALI_THREE_LOGIN /* 2131296290 */:
                final String orderInfo = "";
                try {
                    orderInfo = Base64Helper.decode((String) result.getObj(), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.ui.user.UserDataActivity.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Map<String, String> result2 = new AuthTask(UserDataActivity.this).authV2(orderInfo, true);
                        Message msg = new Message();
                        msg.obj = result2;
                        msg.what = 1;
                        UserDataActivity.this.mHandler.sendMessage(msg);
                    }
                });
                return;
            case R.string.API_THIRD_PARTY_BIND /* 2131296445 */:
                List<PayStyleBean> listBind = (List) result.getObj();
                if (listBind.size() != 0) {
                    for (PayStyleBean bind : listBind) {
                        UserShared.getInstance().saveThirdParty(bind.getType(), bind);
                    }
                    this.detail = UserShared.getInstance().readThirdParty(this.detail);
                    updateUI();
                    return;
                }
                return;
            case R.string.API_USER_BANK_ITEMS /* 2131296454 */:
                if (result.getObj() == null) {
                    this.kvWxAccount.setValue(R.string.no_bind);
                    this.kvAliAccount.setValue(R.string.no_bind);
                    this.kvWxAccount.setClickable(true);
                    this.kvAliAccount.setClickable(true);
                    return;
                }
                List<PayStyleBean> datas = (List) result.getObj();
                for (int i = 0; i < datas.size(); i++) {
                    if (datas.get(i).getType() == 1) {
                        String nickName = datas.get(i).getBankNickName();
                        KeyValueView keyValueView = this.kvWxAccount;
                        if (TextUtils.isEmpty(nickName)) {
                            nickName = "已绑定";
                        }
                        keyValueView.setValue(nickName);
                        this.kvWxAccount.setClickable(false);
                        this.kvWxAccount.getImgRight().setVisibility(4);
                    }
                    if (datas.get(i).getType() == 0) {
                        String nickName2 = datas.get(i).getBankNumber();
                        KeyValueView keyValueView2 = this.kvAliAccount;
                        if (TextUtils.isEmpty(nickName2)) {
                            nickName2 = "已绑定";
                        }
                        keyValueView2.setValue(nickName2);
                        this.kvAliAccount.setClickable(false);
                        this.kvAliAccount.getImgRight().setVisibility(4);
                    }
                }
                return;
            case R.string.API_USER_BIND_WX /* 2131296458 */:
                requestMoneyAccount();
                return;
            case R.string.API_USER_CHANGE_DATA /* 2131296461 */:
                MyToast.show(this, getString(R.string.head_change_success));
                MainActivity.getInstance().refreshMine();
                requestData();
                return;
            case R.string.API_USER_PERSONAL_DATA /* 2131296471 */:
                this.detail = (UserInfo) result.getObj();
                DemoHelper.getInstance().saveCurrentUserInfo(this.detail);
                UserShared.getInstance().saveThirdParty(this.detail);
                AppShare.get(this.context).putString("avatar", this.detail.getUserPic().getSpath());
                updateUI();
                return;
            case R.string.API_USER_THIRD_ACCOUNT /* 2131296474 */:
                MyToast.show(this, getString(R.string.bind_account_success));
                requestData();
                return;
            default:
                return;
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
        switch (result.getRequestCode()) {
            case R.string.API_USER_PERSONAL_DATA /* 2131296471 */:
                if (this.detail == null) {
                    this.detail = getUserInfo(true, true);
                    updateUI();
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        if (HyUtil.isEmpty(this.detail.getUserPic().getSpath())) {
            this.ivAvatar.setImageResource(R.mipmap.def_head);
        } else {
            ComUtil.displayHead(this.context, this.ivAvatar, this.detail.getUserPic().getSpath());
        }
        this.kvUserName.setValue(HyUtil.isEmpty(this.detail.getUserName()) ? getString(R.string.no_set) : this.detail.getUserName());
        if (HyUtil.isEmpty(this.detail.getPhone())) {
            this.kvPhone.setValue(R.string.no_set);
        } else {
            this.kvPhone.setValue(Marker.ANY_NON_NULL_MARKER + this.detail.getCountryCode() + HanziToPinyin.Token.SEPARATOR + this.detail.getPhone());
            this.kvPhone.getImgRight().setVisibility(4);
            this.kvPhone.setClickable(false);
        }
        if (HyUtil.isEmpty(this.detail.getSourceGuid())) {
            this.kvInviter.setValue(R.string.no_set);
        } else {
            String value = "";
            if (this.detail.getSourceName() != null) {
                value = value + this.detail.getSourceName();
            }
            if (this.detail.getSourcePhone() != null) {
                value = value + this.detail.getSourcePhone();
            }
            KeyValueView keyValueView = this.kvInviter;
            if (TextUtils.isEmpty(value)) {
                value = this.detail.getSourceGuid();
            }
            keyValueView.setValue(value);
        }
        if (HyUtil.isEmpty(this.detail.getIdcard())) {
            this.kvIdcard.setValue(R.string.no_set);
        } else {
            this.kvIdcard.setValue(this.detail.getIdcard());
            this.kvIdcard.getImgRight().setVisibility(4);
            this.kvIdcard.setClickable(false);
        }
        if (HyUtil.isEmpty(this.detail.getName())) {
            this.kvRealname.setValue(R.string.no_set);
        } else {
            this.kvRealname.setValue(this.detail.getName());
            this.kvRealname.getImgRight().setVisibility(4);
            this.kvRealname.setClickable(false);
        }
        this.kvNickname.setValue(HyUtil.isEmpty(this.detail.getNickName()) ? getString(R.string.no_set) : this.detail.getNickName());
        if (!HyUtil.isEmpty(this.detail.getGender())) {
            String gender = this.detail.getGender();
            char c = 65535;
            switch (gender.hashCode()) {
                case 48:
                    if (gender.equals("0")) {
                        c = 0;
                        break;
                    }
                    break;
                case 49:
                    if (gender.equals("1")) {
                        c = 1;
                        break;
                    }
                    break;
                case 1444:
                    if (gender.equals("-1")) {
                        c = 2;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    this.kvSex.setValue(getString(R.string.sex_woman));
                    break;
                case 1:
                    this.kvSex.setValue(getString(R.string.sex_man));
                    break;
                case 2:
                    this.kvSex.setValue(getString(R.string.sex_secret));
                    break;
            }
        } else {
            this.kvSex.setValue(R.string.no_set);
        }
        this.kvAge.setValue(HyUtil.isEmpty(this.detail.getAge()) ? getString(R.string.no_set) : this.detail.getAge());
        this.kvEmail.setValue(HyUtil.isEmpty(this.detail.getEmail()) ? getString(R.string.no_set) : this.detail.getEmail());
        this.kvPersonal.setValue(HyUtil.isEmpty(this.detail.getContetnt()) ? getString(R.string.no_set) : this.detail.getContetnt());
        if (this.detail.getAli() == null) {
            this.kvAliThird.setValue(R.string.no_bind);
        } else {
            if (this.detail.getAli().getBankNickName() != null) {
                this.kvAliThird.setValue(this.detail.getAli().getBankNickName());
            } else {
                this.kvAliThird.setValue("已绑定");
            }
            this.kvAliThird.getImgRight().setVisibility(4);
            this.kvAliThird.setClickable(false);
        }
        if (this.detail.getQq() == null) {
            this.kvQqThird.setValue(R.string.no_bind);
        } else {
            if (this.detail.getQq().getBankNickName() != null) {
                this.kvQqThird.setValue(this.detail.getQq().getBankNickName());
            } else {
                this.kvQqThird.setValue("已绑定");
            }
            this.kvQqThird.getImgRight().setVisibility(4);
            this.kvQqThird.setClickable(false);
        }
        if (this.detail.getWx() == null) {
            this.kvWxThird.setValue(R.string.no_bind);
            return;
        }
        if (this.detail.getWx().getBankNickName() != null) {
            this.kvWxThird.setValue(this.detail.getWx().getBankNickName());
        } else {
            this.kvWxThird.setValue("已绑定");
        }
        this.kvWxThird.getImgRight().setVisibility(4);
        this.kvWxThird.setClickable(false);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.user_data_kvWxAccount /* 2131755418 */:
                this.FLAG_WX = 1;
                WechatLogin();
                return;
            case R.id.user_data_kvAliAccount /* 2131755419 */:
                startActForResult(BindAliActivity.class, 999);
                return;
            case R.id.user_data_kvHead /* 2131755721 */:
                showPictureDlg();
                return;
            case R.id.user_data_kvNickname /* 2131755723 */:
                bundle.putParcelable(Constant.FLAG, this.detail);
                bundle.putString(Constant.FLAG2, FlagUtil.CHANGE_NICKNAME);
                startActForResult(ChangeInfoActivity.class, bundle, 111);
                return;
            case R.id.user_data_kvSex /* 2131755725 */:
                bundle.putParcelable(Constant.FLAG, this.detail);
                startActForResult(ChangeSexActivity.class, bundle, 111);
                return;
            case R.id.user_data_kvAge /* 2131755726 */:
                bundle.putParcelable(Constant.FLAG, this.detail);
                bundle.putString(Constant.FLAG2, FlagUtil.CHANGE_AGE);
                startActForResult(ChangeInfoActivity.class, bundle, 111);
                return;
            case R.id.user_data_kvEmail /* 2131755727 */:
                bundle.putParcelable(Constant.FLAG, this.detail);
                bundle.putString(Constant.FLAG2, FlagUtil.CHANGE_EAMIL);
                startActForResult(ChangeInfoActivity.class, bundle, 111);
                return;
            case R.id.user_data_kvPersonal /* 2131755728 */:
                bundle.putParcelable(Constant.FLAG, this.detail);
                startActForResult(ChangeContentActivity.class, bundle, 111);
                return;
            case R.id.user_data_kvPhone /* 2131755729 */:
                if (this.detail.getPhone() == null) {
                    startActForResult(BindPhoneActivity.class, bundle, 111);
                    return;
                }
                this.kvPhone.setValue(this.detail.getPhone());
                this.kvPhone.setClickable(false);
                return;
            case R.id.user_data_kvInviter /* 2131755730 */:
                if (!TextUtils.isEmpty(this.detail.getSourceUser())) {
                    bundle.putString("username", this.detail.getSourceUser());
                    startAct(UserProfileActivity.class, bundle);
                    return;
                }
                return;
            case R.id.user_data_kvIdcard /* 2131755731 */:
                if (TextUtils.isEmpty(this.detail.getIdcard())) {
                    startAct(IdentifyActivity.class);
                    return;
                }
                return;
            case R.id.user_data_kvRealname /* 2131755732 */:
                if (TextUtils.isEmpty(this.detail.getName())) {
                    startAct(IdentifyActivity.class);
                    return;
                }
                return;
            case R.id.user_data_kvWxThird /* 2131755733 */:
                this.FLAG_WX = 0;
                WechatLogin();
                return;
            case R.id.user_data_kvAliThird /* 2131755734 */:
                loginAli();
                return;
            case R.id.user_data_kvQqThird /* 2131755735 */:
                bindQQ();
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        showLoadDlg(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(this.receiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initBind() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(WXEntryActivity.ACTION_SHARE_SUCCESS);
        filter.setPriority(NotificationManagerCompat.IMPORTANCE_UNSPECIFIED);
        registerReceiver(this.receiver, filter);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (this.camera != null) {
            this.camera.onActivityResult(requestCode, resultCode, data);
        }
        if (requestCode == 11101) {
            Tencent.onActivityResultData(requestCode, resultCode, data, this.loginListener);
            if (resultCode == -1) {
                new com.tencent.connect.UserInfo(this, this.mTencent.getQQToken()).getUserInfo(this.userInfoListener);
            }
        } else if (resultCode == -1) {
            switch (requestCode) {
                case 111:
                    this.detail = getUserInfo(true, true);
                    updateUI();
                    return;
                case 999:
                    requestMoneyAccount();
                    return;
                default:
                    return;
            }
        }
    }

    private void showPictureDlg() {
        if (this.camera == null) {
            this.camera = new CameraUtil(this, this);
        }
        if (this.pictureDlg == null) {
            this.pictureDlg = new PictureDialog(this.context);
            this.pictureDlg.init(this);
        }
        this.pictureDlg.show();
    }

    @Override // com.cdlinglu.utils.CameraUtil.CameraDealListener
    public void onCameraTakeSuccess(String path) {
        MyLog.e("onCameraTakeSuccess: " + path);
        this.camera.cropImageUri(1, 1, 500);
    }

    @Override // com.cdlinglu.utils.CameraUtil.CameraDealListener
    public void onCameraPickSuccess(String path) {
        MyLog.e("onCameraPickSuccess: " + path);
        this.camera.cropImageUri(path, 1, 1, 500);
    }

    @Override // com.cdlinglu.utils.CameraUtil.CameraDealListener
    public void onCameraCutSuccess(String uri) {
        getClient().showDialogNow(R.string.toast_uploading);
        this.ivAvatar.setImageURI(Uri.fromFile(new File(uri)));
        List<String> paths = new ArrayList<>();
        paths.add(uri);
        new UploadUtils().UploadPicturesGetOSS(this, this.detail.getUserName(), com.vsf2f.f2f.ui.utils.Constant.USER_BUCKET, 3, paths, null, new UploadPicListener() { // from class: com.vsf2f.f2f.ui.user.UserDataActivity.3
            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onSuccess(List<Map<String, String>> list, List<String> picIds) {
                UserDataActivity.this.updateAvatar(picIds.get(0));
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onFailed() {
                MyToast.show(UserDataActivity.this.context, (int) R.string.upload_failed);
                UserDataActivity.this.getClient().dialogDismiss();
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onProgress(long currentSize, long totalSize) {
                MyLog.e("upload", "onProgress:" + currentSize + "/" + totalSize);
            }
        });
    }

    @Override // com.cdlinglu.utils.CameraUtil.CameraDealListener
    public void onFunctionCancel(String uri) {
    }

    @Override // com.vsf2f.f2f.ui.dialog.PictureDialog.ConfirmDlgListener
    public void onDlgCameraClick(PictureDialog dlg) {
        if (this.camera != null) {
            this.camera.onDlgCameraClick();
        }
    }

    @Override // com.vsf2f.f2f.ui.dialog.PictureDialog.ConfirmDlgListener
    public void onDlgPhotoClick(PictureDialog dlg) {
        if (this.camera != null) {
            this.camera.onDlgPhotoClick();
        }
    }

    @Override // com.vsf2f.f2f.ui.dialog.PictureDialog.ConfirmDlgListener
    public void onDlgCancelClick(PictureDialog dlg) {
    }

    private void showLoadDlg(boolean isShow) {
        if (this.loadDlg == null) {
            if (isShow) {
                this.loadDlg = new LoadingDialog(this.context);
            } else {
                return;
            }
        }
        if (isShow) {
            this.loadDlg.show();
        } else {
            this.loadDlg.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAvatar(String requestId) {
        AjaxParams params = new AjaxParams();
        params.put("id", this.detail.getId());
        if (TextUtils.isEmpty(requestId)) {
            MyToast.show(this.context, getString(R.string.head_upload_failed));
            getClient().dialogDismiss();
            return;
        }
        params.put("attachmentId", requestId);
        getClient().post(R.string.API_USER_CHANGE_DATA, ComUtil.getZCApi(this.context, getString(R.string.API_USER_CHANGE_DATA)), params);
    }

    private void loginAli() {
        getClient().get(R.string.API_ALI_THREE_LOGIN, ComUtil.getZCApi(this.context, getString(R.string.API_ALI_THREE_LOGIN)));
    }

    private void WechatLogin() {
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        if (MyApplication.wxApi.isWXAppInstalled()) {
            MyApplication.wxApi.sendReq(req);
            showLoadDlg(true);
            return;
        }
        MyToast.show(this.context, (int) R.string.no_install_wx);
    }

    public void bindQQ() {
        try {
            this.mTencent = Tencent.createInstance(IDUtil.QQ_ID, this);
            if (this.mTencent == null || !this.mTencent.isSupportSSOLogin(this)) {
                MyToast.show(this.context, (int) R.string.no_install_qq);
            } else {
                initQqListener();
                this.mTencent.login(this, com.vsf2f.f2f.ui.utils.Constant.ALL, this.loginListener);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initQqListener() {
        this.loginListener = new IUiListener() { // from class: com.vsf2f.f2f.ui.user.UserDataActivity.5
            @Override // com.tencent.tauth.IUiListener
            public void onComplete(Object o) {
                Toast.makeText(UserDataActivity.this.context, "授权成功", 0).show();
                JSONObject object = (JSONObject) o;
                try {
                    UserDataActivity.this.openid = object.getString("openid");
                    String accessToken = object.getString(Constants.PARAM_ACCESS_TOKEN);
                    String expires = object.getString(Constants.PARAM_EXPIRES_IN);
                    UserDataActivity.this.mTencent.setOpenId(UserDataActivity.this.openid);
                    UserDataActivity.this.mTencent.setAccessToken(accessToken, expires);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override // com.tencent.tauth.IUiListener
            public void onError(UiError uiError) {
                Toast.makeText(UserDataActivity.this.context, "授权失败", 0).show();
                MyLog.e("LoginError:", uiError.toString());
            }

            @Override // com.tencent.tauth.IUiListener
            public void onCancel() {
                Toast.makeText(UserDataActivity.this.context, UserDataActivity.this.getString(R.string.be_canceled), 0).show();
            }
        };
        this.userInfoListener = new IUiListener() { // from class: com.vsf2f.f2f.ui.user.UserDataActivity.6
            @Override // com.tencent.tauth.IUiListener
            public void onComplete(Object o) {
                if (o != null) {
                    try {
                        JSONObject jo = (JSONObject) o;
                        String nickName = jo.getString(EaseConstant.EXTRA_NICK_NAME);
                        String figureurl = jo.getString("figureurl");
                        ThirdPartyBean thirdParty = new ThirdPartyBean();
                        thirdParty.setPartyType(1);
                        thirdParty.setOpenId(UserDataActivity.this.openid);
                        thirdParty.setPicUrl(figureurl);
                        thirdParty.setNickName(nickName);
                        UserDataActivity.this.bindThird(thirdParty);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override // com.tencent.tauth.IUiListener
            public void onError(UiError uiError) {
            }

            @Override // com.tencent.tauth.IUiListener
            public void onCancel() {
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindThird(ThirdPartyBean thirdParty) {
        AjaxParams params = new AjaxParams();
        getClient().setShowDialog(true);
        switch (thirdParty.getPartyType()) {
            case 1:
                params.put("openId", ComUtil.UTF(thirdParty.getOpenId()));
                params.put("nickName", ComUtil.UTF(thirdParty.getNickName()));
                params.put("picUrl", ComUtil.UTF(thirdParty.getPicUrl()));
                params.put("partyType", thirdParty.getPartyType());
                break;
        }
        getClient().post(R.string.API_USER_THIRD_ACCOUNT, ComUtil.getZCApi(this.context, getString(R.string.API_USER_THIRD_ACCOUNT, new Object[]{this.detail.getUserName()})), params, String.class, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindThird(String jsonStr) {
        AjaxParams params = new AjaxParams();
        getClient().setShowDialog(true);
        getClient().post(R.string.API_USER_THIRD_ACCOUNT, ComUtil.getZCApi(this.context, getString(R.string.API_USER_THIRD_ACCOUNT, new Object[]{this.detail.getUserName()})), jsonStr, params, String.class, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindWxAccount(String jsonStr) {
        AjaxParams params = new AjaxParams();
        String url = ComUtil.getZCApi(this.context, getString(R.string.API_USER_BIND_WX));
        getClient().setShowDialog(true);
        getClient().post(R.string.API_USER_BIND_WX, url, jsonStr, params, String.class, false);
    }
}
