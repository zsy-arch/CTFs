package com.em.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.cdlinglu.common.BaseActivity;
import com.easeui.EaseConstant;
import com.easeui.adapter.EaseContactAdapter;
import com.easeui.domain.EaseUser;
import com.easeui.widget.EaseSidebar;
import com.em.DemoHelper;
import com.vsf2f.f2f.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class PickContactNoCheckboxActivity extends BaseActivity {
    protected EaseContactAdapter contactAdapter;
    private List<EaseUser> contactList;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.em_activity_pick_contact_no_checkbox;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.select_contacts, 0);
        ListView listView = (ListView) findViewById(R.id.list);
        ((EaseSidebar) findViewById(R.id.sidebar)).setListView(listView);
        this.contactList = new ArrayList();
        getContactList();
        this.contactAdapter = new EaseContactAdapter(this, R.layout.item_row_contact, this.contactList);
        listView.setAdapter((ListAdapter) this.contactAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.em.ui.PickContactNoCheckboxActivity.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PickContactNoCheckboxActivity.this.onListItemClick(position);
            }
        });
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
    }

    protected void onListItemClick(int position) {
        setResult(-1, new Intent().putExtra("username", this.contactAdapter.getItem(position).getUsername()));
        finish();
    }

    private void getContactList() {
        this.contactList.clear();
        for (Map.Entry<String, EaseUser> entry : DemoHelper.getInstance().getContactList().entrySet()) {
            if (!entry.getKey().equals(EaseConstant.NEW_FRIENDS_USERNAME) && !entry.getKey().equals(EaseConstant.GROUP_USERNAME) && !entry.getKey().equals(EaseConstant.CHAT_ROOM) && !entry.getKey().equals(EaseConstant.CHAT_ROBOT)) {
                this.contactList.add(entry.getValue());
            }
        }
        Collections.sort(this.contactList, new Comparator<EaseUser>() { // from class: com.em.ui.PickContactNoCheckboxActivity.2
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
}
