package com.em.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.easeui.EaseConstant;
import com.easeui.adapter.EaseContactAdapter;
import com.easeui.domain.EaseUser;
import com.easeui.utils.EaseUserUtils;
import com.easeui.widget.EaseSidebar;
import com.hy.frame.bean.ResultInfo;
import com.hyphenate.chat.EMClient;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.GroupInfoBean;
import com.vsf2f.f2f.bean.GroupMembersBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes.dex */
public class PickMemberActivity extends BaseActivity {
    private ListView listView;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_pick_at_user;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.select_group_members, 0);
        String groupId = getIntent().getStringExtra(EaseConstant.EXTRA_GROUP_ID);
        this.listView = (ListView) findViewById(R.id.list);
        ((EaseSidebar) findViewById(R.id.sidebar)).setListView(this.listView);
        getGroupInfo(groupId);
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

    public void updateList(List<GroupMembersBean> groupMembersList) {
        List<EaseUser> contactList = new ArrayList<>();
        for (GroupMembersBean member : groupMembersList) {
            contactList.add(new EaseUser(member));
        }
        Collections.sort(contactList, new Comparator<EaseUser>() { // from class: com.em.ui.PickMemberActivity.1
            public int compare(EaseUser lhs, EaseUser rhs) {
                if (lhs.getInitialLetter().equals(rhs.getInitialLetter())) {
                    return lhs.getNick().compareTo(rhs.getNick());
                }
                if ("#".equals(lhs.getInitialLetter())) {
                    return 1;
                }
                if ("#".equals(rhs.getInitialLetter())) {
                    return -1;
                }
                return lhs.getInitialLetter().compareTo(rhs.getInitialLetter());
            }
        });
        this.listView.setAdapter((ListAdapter) new PickContactAdapter(this, 0, contactList));
    }

    public void getGroupInfo(String groupId) {
        getClient().get(R.string.API_GET_GROUP_INFO, ComUtil.getF2FApi(this.context, getString(R.string.API_GET_GROUP_INFO, new Object[]{EaseUserUtils.getGroupBizId(groupId)})) + "?countUser=0&loadfriends=1", null, GroupInfoBean.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_GET_GROUP_INFO /* 2131296355 */:
                GroupInfoBean groupInfo = (GroupInfoBean) result.getObj();
                if (groupInfo != null) {
                    updateList(groupInfo.getFriends());
                    final boolean isOwner = EMClient.getInstance().getCurrentUser().equals(groupInfo.getCreatorName());
                    if (isOwner) {
                        addHeadView();
                    }
                    this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.em.ui.PickMemberActivity.2
                        @Override // android.widget.AdapterView.OnItemClickListener
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if (!isOwner) {
                                EaseUser user = (EaseUser) PickMemberActivity.this.listView.getItemAtPosition(position);
                                if (!EMClient.getInstance().getCurrentUser().equals(user.getUsername())) {
                                    PickMemberActivity.this.setResult(-1, new Intent().putExtra("username", user.getUsername()));
                                } else {
                                    return;
                                }
                            } else if (position != 0) {
                                EaseUser user2 = (EaseUser) PickMemberActivity.this.listView.getItemAtPosition(position);
                                if (EMClient.getInstance().getCurrentUser().equals(user2.getUsername())) {
                                    Toast.makeText(PickMemberActivity.this.context, "不能@自己", 0).show();
                                    return;
                                }
                                PickMemberActivity.this.setResult(-1, new Intent().putExtra("username", user2.getUsername()));
                            } else {
                                PickMemberActivity.this.setResult(-1, new Intent().putExtra("username", PickMemberActivity.this.getString(R.string.all_members)));
                            }
                            PickMemberActivity.this.finish();
                        }
                    });
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
    }

    private void addHeadView() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_row_contact, (ViewGroup) this.listView, false);
        ((TextView) view.findViewById(R.id.name)).setText(getString(R.string.all_members));
        ((ImageView) view.findViewById(R.id.avatar)).setImageResource(R.drawable.ease_groups_icon);
        this.listView.addHeaderView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class PickContactAdapter extends EaseContactAdapter {
        public PickContactAdapter(Context context, int resource, List<EaseUser> users) {
            super(context, resource, users);
        }
    }
}
