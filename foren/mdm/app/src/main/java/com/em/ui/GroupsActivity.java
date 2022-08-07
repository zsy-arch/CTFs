package com.em.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.easeui.EaseConstant;
import com.easeui.utils.EaseCommonUtils;
import com.em.DemoHelper;
import com.em.adapter.GroupAdapter;
import com.hy.frame.adapter.IAdapterListener;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.HyUtil;
import com.hy.frame.view.RefreshRecyclerView;
import com.hy.frame.view.recycler.callback.XRefreshViewListener;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.GroupBean;
import com.vsf2f.f2f.bean.GroupInfoBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes.dex */
public class GroupsActivity extends BaseActivity implements XRefreshViewListener, IAdapterListener {
    public static GroupsActivity instance;
    private GroupAdapter adapter;
    private RefreshRecyclerView group_list;
    protected List<GroupBean> grouplist = new ArrayList();

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.em_fragment_groups;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        instance = this;
        initHeaderBack(R.string.group_chat, 0);
        setOnClickListener(R.id.group_add);
        this.group_list = (RefreshRecyclerView) getView(R.id.group_list);
        this.group_list.setOnRefreshListener(this);
        this.group_list.setLoadMoreEnabled(false);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        getGroups();
    }

    protected void sortGroupList() {
        List<GroupBean> groupslist = new ArrayList<>();
        for (GroupBean group : this.grouplist) {
            group.setGroupHead(EaseCommonUtils.setUserInitialLetter(group.getGroupName()));
            groupslist.add(group);
        }
        Collections.sort(groupslist, new Comparator<GroupBean>() { // from class: com.em.ui.GroupsActivity.1
            public int compare(GroupBean lhs, GroupBean rhs) {
                if (lhs.getGroupHead().equals(rhs.getGroupHead())) {
                    return lhs.getGroupName().compareTo(rhs.getGroupName());
                }
                if ("#".equals(lhs.getGroupHead())) {
                    return 1;
                }
                if ("#".equals(rhs.getGroupHead())) {
                    return -1;
                }
                return lhs.getGroupHead().compareTo(rhs.getGroupHead());
            }
        });
        setGroupList(groupslist);
    }

    public void setGroupList(List<GroupBean> groupslist) {
        this.grouplist.clear();
        this.grouplist.addAll(groupslist);
        updateUI();
    }

    public void getGroups() {
        getClient().get(R.string.API_GET_GROUP, ComUtil.getF2FApi(this.context, getString(R.string.API_GET_GROUP)), null, GroupInfoBean.class, true);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_GET_GROUP /* 2131296353 */:
                this.group_list.setRefreshComplete();
                this.grouplist.clear();
                List<GroupInfoBean> list = (List) result.getObj();
                if (HyUtil.isEmpty(list)) {
                    list = new ArrayList<>();
                }
                updateToDB(list);
                return;
            default:
                return;
        }
    }

    private void updateToDB(List<GroupInfoBean> list) {
        if (list.size() != 0) {
            for (GroupInfoBean group : list) {
                this.grouplist.add(new GroupBean(group));
            }
            if (this.grouplist.size() != 0) {
                sortGroupList();
            }
        }
        DemoHelper.getInstance().updateGroupsList(this.grouplist);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
        this.group_list.setRefreshFail();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        if (this.adapter == null) {
            this.adapter = new GroupAdapter(this.context, this.grouplist);
            this.adapter.setListener(this);
            this.group_list.setAdapter(this.adapter);
            return;
        }
        this.adapter.setDatas(this.grouplist);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.group_add /* 2131756648 */:
                startAct(NewGroupActivity.class);
                return;
            default:
                return;
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.grouplist = new ArrayList(DemoHelper.getInstance().getGroupsList().values());
        sortGroupList();
    }

    @Override // com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        instance = null;
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onRefresh() {
        getGroups();
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onLoadMore(boolean isSilence) {
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onRelease(float direction) {
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onHeaderMove(double headerMovePercent, int offsetY) {
    }

    @Override // com.hy.frame.adapter.IAdapterListener
    public void onItemClick(int id, Object obj, int position) {
        Bundle bundle = new Bundle();
        bundle.putString("username", ((GroupBean) obj).getGroupId());
        bundle.putInt(EaseConstant.EXTRA_CHAT_TYPE, 2);
        startActForResult(ChatActivity.class, bundle, 120);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 120 && resultCode == -1) {
            updateUI();
        }
    }
}
