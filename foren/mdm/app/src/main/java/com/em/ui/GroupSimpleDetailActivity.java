package com.em.ui;

import android.app.ProgressDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.cdlinglu.common.BaseActivity;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMGroupInfo;
import com.hyphenate.exceptions.HyphenateException;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.ThreadPool;

/* loaded from: classes.dex */
public class GroupSimpleDetailActivity extends BaseActivity {
    private Button btn_add_group;
    private EMGroup group;
    private String groupId;
    private TextView tv_admin;
    private TextView tv_introduction;
    private TextView tv_name;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.em_activity_group_simle_details;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        String groupname;
        initHeaderBack(R.string.group_chat_information, 0);
        this.tv_name = (TextView) findViewById(R.id.name);
        this.tv_admin = (TextView) findViewById(R.id.tv_admin);
        this.btn_add_group = (Button) getViewAndClick(R.id.btn_add_to_group);
        this.tv_introduction = (TextView) findViewById(R.id.tv_introduction);
        EMGroupInfo groupInfo = (EMGroupInfo) getIntent().getSerializableExtra("groupinfo");
        if (groupInfo != null) {
            groupname = groupInfo.getGroupName();
            this.groupId = groupInfo.getGroupId();
        } else {
            this.group = PublicGroupsSearchActivity.searchedGroup;
            if (this.group != null) {
                groupname = this.group.getGroupName();
                this.groupId = this.group.getGroupId();
            } else {
                return;
            }
        }
        this.tv_name.setText(groupname);
        if (this.group != null) {
            showGroupDetail();
        } else {
            ThreadPool.newThreadPool(new Runnable() { // from class: com.em.ui.GroupSimpleDetailActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        GroupSimpleDetailActivity.this.group = EMClient.getInstance().groupManager().getGroupFromServer(GroupSimpleDetailActivity.this.groupId);
                        GroupSimpleDetailActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.GroupSimpleDetailActivity.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                GroupSimpleDetailActivity.this.showGroupDetail();
                            }
                        });
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                        final String st1 = GroupSimpleDetailActivity.this.getResources().getString(R.string.failed_to_get_group_chat_information);
                        GroupSimpleDetailActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.GroupSimpleDetailActivity.1.2
                            @Override // java.lang.Runnable
                            public void run() {
                                Toast.makeText(GroupSimpleDetailActivity.this, st1 + e.getMessage(), 0).show();
                            }
                        });
                    }
                }
            });
        }
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
            case R.id.btn_add_to_group /* 2131756555 */:
                addToGroup();
                return;
            default:
                return;
        }
    }

    public void addToGroup() {
        String st1 = getResources().getString(R.string.is_sending_a_request);
        final String st2 = getResources().getString(R.string.request_to_join);
        final String st3 = getResources().getString(R.string.send_the_request_is);
        final String st4 = getResources().getString(R.string.join_the_group_chat);
        final String st5 = getResources().getString(R.string.failed_to_join_the_group_chat);
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage(st1);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        ThreadPool.newThreadPool(new Runnable() { // from class: com.em.ui.GroupSimpleDetailActivity.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (GroupSimpleDetailActivity.this.group.isMembersOnly()) {
                        EMClient.getInstance().groupManager().applyJoinToGroup(GroupSimpleDetailActivity.this.groupId, st2);
                    } else {
                        EMClient.getInstance().groupManager().joinGroup(GroupSimpleDetailActivity.this.groupId);
                    }
                    GroupSimpleDetailActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.GroupSimpleDetailActivity.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            pd.dismiss();
                            if (GroupSimpleDetailActivity.this.group.isMembersOnly()) {
                                Toast.makeText(GroupSimpleDetailActivity.this, st3, 0).show();
                            } else {
                                Toast.makeText(GroupSimpleDetailActivity.this, st4, 0).show();
                            }
                            GroupSimpleDetailActivity.this.btn_add_group.setEnabled(false);
                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    GroupSimpleDetailActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.GroupSimpleDetailActivity.2.2
                        @Override // java.lang.Runnable
                        public void run() {
                            pd.dismiss();
                            Toast.makeText(GroupSimpleDetailActivity.this, st5 + e.getMessage(), 0).show();
                        }
                    });
                }
            }
        });
    }

    public void showGroupDetail() {
        if (!this.group.getMembers().contains(EMClient.getInstance().getCurrentUser())) {
            this.btn_add_group.setEnabled(true);
        }
        this.tv_name.setText(this.group.getGroupName());
        this.tv_admin.setText(this.group.getOwner());
        this.tv_introduction.setText(this.group.getDescription());
    }
}
