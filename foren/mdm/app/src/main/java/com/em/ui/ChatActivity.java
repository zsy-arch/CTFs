package com.em.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import com.cdlinglu.common.BaseActivity;
import com.easeui.EaseConstant;
import com.easeui.utils.EaseUserUtils;
import com.em.DemoHelper;
import com.em.runtimepermissions.PermissionsManager;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.GroupBean;

/* loaded from: classes.dex */
public class ChatActivity extends BaseActivity {
    public static ChatActivity activityInstance;
    private boolean backToList = true;
    private ChatFragment chatFragment;
    protected int chatType;
    private String toChatUsername;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.em_activity_chat;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        hideHeader();
        activityInstance = this;
        if (getBundle() != null) {
            this.toChatUsername = getBundle().getString("username");
            this.backToList = getBundle().getBoolean(EaseConstant.BACK_TYPE, true);
            if (!TextUtils.isEmpty(this.toChatUsername)) {
                if (!this.toChatUsername.equals(DemoHelper.getInstance().getCurrentUserName())) {
                    this.chatFragment = new ChatFragment();
                    this.chatType = getBundle().getInt(EaseConstant.EXTRA_CHAT_TYPE, 1);
                    this.chatFragment.setArguments(getBundle());
                    getSupportFragmentManager().beginTransaction().add(R.id.container, this.chatFragment).commit();
                    return;
                }
                MyToast.show(this.context, (int) R.string.cant_chat_with_yourself);
            }
        }
        finish();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        if (this.toChatUsername.equals(intent.getStringExtra("username"))) {
            super.onNewIntent(intent);
            return;
        }
        startActivity(intent);
        finish();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        this.chatFragment.onBackPressed();
        finish();
    }

    public String getToChatUsername() {
        return this.toChatUsername;
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        GroupBean group;
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == 111 && this.chatType == 2 && (group = EaseUserUtils.getGroupInfo(this.toChatUsername)) != null) {
            String gn = group.getGroupName();
            if (this.chatFragment != null && HyUtil.isNoEmpty(gn)) {
                this.chatFragment.refreshTitle(gn + "");
            }
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
    }

    @Override // com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        MyLog.e("chatactivity:onDestroy");
    }
}
