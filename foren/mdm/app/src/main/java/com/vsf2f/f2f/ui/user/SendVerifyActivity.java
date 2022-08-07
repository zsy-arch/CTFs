package com.vsf2f.f2f.ui.user;

import android.app.ProgressDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.cdlinglu.common.BaseActivity;
import com.easeui.widget.EaseSwitchButton;
import com.em.DemoHelper;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hyphenate.chat.EMClient;
import com.hyphenate.util.HanziToPinyin;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import com.yolanda.nohttp.cache.CacheDisk;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class SendVerifyActivity extends BaseActivity {
    private EditText editText;
    private String friendName;
    private ProgressDialog progressDialog;
    private EaseSwitchButton sbShowName;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_send_verify;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.apply_verify, R.string.button_send);
        if (getBundle() == null) {
            finish();
        }
        this.friendName = getBundle().getString("username");
        this.sbShowName = (EaseSwitchButton) getView(R.id.switch_sbShowName);
        this.editText = (EditText) getView(R.id.send_editText);
        this.editText.requestFocus();
        setOnClickListener(R.id.send_rlyShowName);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        addContact();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.send_rlyShowName /* 2131755575 */:
                if (this.sbShowName.isSwitchOpen()) {
                    this.sbShowName.closeSwitch();
                    return;
                } else {
                    this.sbShowName.openSwitch();
                    return;
                }
            default:
                return;
        }
    }

    public void addContact() {
        String str1 = getResources().getString(R.string.is_sending_a_request);
        this.progressDialog = new ProgressDialog(this);
        this.progressDialog.setMessage(str1);
        this.progressDialog.setCanceledOnTouchOutside(false);
        this.progressDialog.show();
        ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.ui.user.SendVerifyActivity.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    String message = SendVerifyActivity.this.editText.getText().toString().trim();
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put(CacheDisk.HEAD, DemoHelper.getInstance().getCurrentUserPic().getSpath());
                    jsonObject.put("msg", message);
                    String nick = DemoHelper.getInstance().getCurrentNickname();
                    if (SendVerifyActivity.this.sbShowName.isSwitchOpen()) {
                        String realname = SendVerifyActivity.this.getUserInfo().getName();
                        if (!TextUtils.isEmpty(realname)) {
                            nick = nick + "(" + realname + ")";
                            message = message + "(" + realname + ")";
                        }
                    }
                    jsonObject.put("nick", nick);
                    EMClient.getInstance().contactManager().addContact(SendVerifyActivity.this.friendName, message);
                    SendVerifyActivity.this.runOnUiThread(new Runnable() { // from class: com.vsf2f.f2f.ui.user.SendVerifyActivity.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            SendVerifyActivity.this.progressDialog.dismiss();
                            Toast.makeText(SendVerifyActivity.this.getApp(), SendVerifyActivity.this.getResources().getString(R.string.send_successful), 0).show();
                        }
                    });
                    SendVerifyActivity.this.setResult(-1);
                    SendVerifyActivity.this.finish();
                } catch (Exception e) {
                    SendVerifyActivity.this.runOnUiThread(new Runnable() { // from class: com.vsf2f.f2f.ui.user.SendVerifyActivity.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            SendVerifyActivity.this.progressDialog.dismiss();
                            String msg = e.getMessage().replace(HanziToPinyin.Token.SEPARATOR, "");
                            if (msg.contains("Userisnotlogin")) {
                                MyToast.show(SendVerifyActivity.this.getApp(), "通讯账号未登陆，请去会话页面重试");
                            }
                            if (msg.equals("Serverresponsetimeout")) {
                                MyToast.show(SendVerifyActivity.this.getApp(), SendVerifyActivity.this.getString(R.string.Request_add_buddy_failure));
                            } else {
                                MyToast.show(SendVerifyActivity.this.getApp(), SendVerifyActivity.this.getString(R.string.Request_add_buddy_failure));
                            }
                            MyLog.e(msg);
                        }
                    });
                }
            }
        });
    }
}
