package com.em.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.easeui.EaseConstant;
import com.easeui.adapter.EaseContactAdapter;
import com.easeui.domain.EaseUser;
import com.easeui.widget.EaseSidebar;
import com.em.DemoHelper;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.hy.frame.util.MyToast;
import com.vsf2f.f2f.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes.dex */
public class GroupPickContactsActivity extends BaseActivity {
    private int RESULT = 0;
    private String bizID;
    private PickContactAdapter contactAdapter;
    private List<EaseUser> contactList;
    private List<String> existMembers;
    private String imID;
    protected boolean isCreatingNewGroup;
    private String type;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.em_activity_group_pick_contacts;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.select_contacts, R.string.button_add);
        this.existMembers = (List) getBundle().getParcelable("users");
        if (this.existMembers == null) {
            this.existMembers = new ArrayList();
        }
        this.contactList = new ArrayList();
        for (EaseUser user : DemoHelper.getInstance().getContactList().values()) {
            this.contactList.add(user);
        }
        Collections.sort(this.contactList, new Comparator<EaseUser>() { // from class: com.em.ui.GroupPickContactsActivity.1
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
        ListView listView = (ListView) findViewById(R.id.list);
        this.contactAdapter = new PickContactAdapter(this, R.layout.item_row_contact_with_checkbox, this.contactList);
        listView.setAdapter((ListAdapter) this.contactAdapter);
        ((EaseSidebar) getView(R.id.sidebar)).setListView(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.em.ui.GroupPickContactsActivity.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((CheckBox) view.findViewById(R.id.checkbox)).toggle();
            }
        });
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        if (getBundle() != null) {
            this.type = getBundle().getString(Constant.FLAG_TYPE);
            this.bizID = getBundle().getString(Constant.FLAG);
            this.imID = getBundle().getString(Constant.FLAG2);
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    public void addGroupMembers(String users) {
        getClient().post(R.string.API_ADD_GROUP_MEMBER, ComUtil.getF2FApi(this.context, getString(R.string.API_ADD_GROUP_MEMBER, new Object[]{users, this.bizID})));
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onLeftClick() {
        if (this.type.equals(f.bf)) {
            Bundle bundle = new Bundle();
            bundle.putString("username", this.imID);
            bundle.putInt(EaseConstant.EXTRA_CHAT_TYPE, 2);
            startAct(ChatActivity.class, bundle);
        } else if (this.type.equals("add")) {
            setResult(this.RESULT);
        }
        super.onLeftClick();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        onLeftClick();
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        String ids = getToBeAddMembers();
        if (!TextUtils.isEmpty(ids)) {
            addGroupMembers(ids);
        } else {
            onLeftClick();
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_ADD_GROUP_MEMBER /* 2131296286 */:
                MyToast.show(this.context, "添加成功");
                this.RESULT = -1;
                onLeftClick();
                return;
            default:
                return;
        }
    }

    private String getToBeAddMembers() {
        String ids = "";
        int length = this.contactAdapter.isCheckedArray.length;
        for (int i = 0; i < length; i++) {
            String username = this.contactAdapter.getItem(i).getUsername();
            if (this.contactAdapter.isCheckedArray[i] && !this.existMembers.contains(username)) {
                ids = ids + username + ",";
            }
        }
        if (ids.length() > 1) {
            return ids.substring(0, ids.length() - 1);
        }
        return ids;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class PickContactAdapter extends EaseContactAdapter {
        private boolean[] isCheckedArray;

        public PickContactAdapter(Context context, int resource, List<EaseUser> users) {
            super(context, resource, users);
            this.isCheckedArray = new boolean[users.size()];
        }

        @Override // com.easeui.adapter.EaseContactAdapter, android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            final String username = getItem(position).getUsername();
            final CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox);
            if (checkBox != null) {
                if (GroupPickContactsActivity.this.existMembers == null || !GroupPickContactsActivity.this.existMembers.contains(username)) {
                    checkBox.setButtonDrawable(R.drawable.em_checkbox_bg_selector);
                } else {
                    checkBox.setButtonDrawable(R.drawable.em_checkbox_bg_gray_selector);
                }
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.em.ui.GroupPickContactsActivity.PickContactAdapter.1
                    @Override // android.widget.CompoundButton.OnCheckedChangeListener
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (GroupPickContactsActivity.this.existMembers.contains(username)) {
                            isChecked = true;
                            checkBox.setChecked(true);
                        }
                        PickContactAdapter.this.isCheckedArray[position] = isChecked;
                    }
                });
                if (GroupPickContactsActivity.this.existMembers.contains(username)) {
                    checkBox.setChecked(true);
                    this.isCheckedArray[position] = true;
                } else {
                    checkBox.setChecked(this.isCheckedArray[position]);
                }
            }
            return view;
        }
    }
}
