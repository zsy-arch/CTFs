package com.em.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.PermissionUtil;
import com.easeui.domain.EaseUser;
import com.easeui.widget.EaseAlertDialog;
import com.em.DemoHelper;
import com.em.adapter.NewFriendsMsgAdapter;
import com.em.db.DemoDBManager;
import com.em.db.InviteMessgeDao;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.result.AddFriendBean;
import com.vsf2f.f2f.ui.MainActivity;
import com.vsf2f.f2f.ui.user.PhoneContactsActivity;
import org.greenrobot.eventbus.EventBus;
import u.aly.av;

/* loaded from: classes.dex */
public class NewFriendsMsgActivity extends BaseActivity {
    private String friendname;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.em_activity_new_friends_msg;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.new_friends, R.mipmap.ico_remove_white);
        ((ListView) getView(R.id.list)).setAdapter((ListAdapter) new NewFriendsMsgAdapter(this, 1, new InviteMessgeDao(this).getMessagesList()));
        DemoHelper.getInstance().clearNewInviteMessage();
        EventBus.getDefault().post(12);
        setOnClickListener(R.id.contacts_item);
    }

    public void addFriend(String friendName) {
        this.friendname = friendName;
        String url = ComUtil.getF2FApi(this.context, getString(R.string.API_ADD_FRIEND, new Object[]{DemoHelper.getInstance().getCurrentUserName(), friendName}));
        getClient().setShowDialog(false);
        getClient().post(R.string.API_ADD_FRIEND, url, null, AddFriendBean.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_ADD_FRIEND /* 2131296283 */:
                if (result.getObj() != null) {
                    AddFriendBean addFriend = (AddFriendBean) result.getObj();
                    if (addFriend.getSeccuse().size() > 0) {
                        MyLog.e(av.aG, "Friend Seccuse size:" + addFriend.getSeccuse().size());
                        DemoHelper.getInstance().saveContact(new EaseUser(addFriend.getSeccuse().get(this.friendname)));
                        MyToast.show(this, (int) R.string.add_success);
                        sentHelloMessage();
                        MainActivity.getInstance().setRefreshFriend();
                        EventBus.getDefault().post(12);
                        return;
                    } else if (addFriend.getFailed().size() > 0) {
                        MyToast.show(this.context, addFriend.getFailed().get(this.friendname));
                        MyLog.e(av.aG, "Friend Failed size:" + addFriend.getFailed().size());
                        return;
                    } else {
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
        MyLog.e(av.aG, av.aG);
    }

    private void sentHelloMessage() {
        EMMessage message = EMMessage.createTxtSendMessage("我通过了你的好友申请，现在我们可以开始聊天了", this.friendname);
        message.setAttribute("userName", DemoHelper.getInstance().getCurrentUserNick());
        message.setAttribute("headURL", DemoHelper.getInstance().getCurrentUserPic().getSpath());
        EMClient.getInstance().chatManager().sendMessage(message);
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.contacts_item /* 2131756562 */:
                if (PermissionUtil.getContactsPermissions(this, 111)) {
                    startAct(PhoneContactsActivity.class);
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        new EaseAlertDialog(this, R.string.exit_clear_, new EaseAlertDialog.AlertDialogListener() { // from class: com.em.ui.NewFriendsMsgActivity.1
            @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
            public void onResult(boolean confirmed, Bundle bundle) {
                if (confirmed) {
                    DemoDBManager.getInstance().clearMessage();
                    NewFriendsMsgActivity.this.finish();
                }
            }
        }, true).show();
    }
}
