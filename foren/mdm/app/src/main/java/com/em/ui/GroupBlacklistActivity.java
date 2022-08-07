package com.em.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.easeui.EaseConstant;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class GroupBlacklistActivity extends BaseActivity {
    private BlacklistAdapter adapter;
    private String groupId;
    private ListView listView;
    private ProgressBar progressBar;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.em.ui.BaseActivity, com.easeui.ui.EaseBaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.em_activity_group_blacklist);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.listView = (ListView) findViewById(R.id.list);
        this.groupId = getIntent().getStringExtra(EaseConstant.EXTRA_GROUP_ID);
        registerForContextMenu(this.listView);
        final String st1 = getResources().getString(R.string.get_failed_please_check);
        ThreadPool.newThreadPool(new Runnable() { // from class: com.em.ui.GroupBlacklistActivity.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    List<String> blockedList = EMClient.getInstance().groupManager().getBlockedUsers(GroupBlacklistActivity.this.groupId);
                    if (blockedList != null) {
                        Collections.sort(blockedList);
                        GroupBlacklistActivity.this.adapter = new BlacklistAdapter(GroupBlacklistActivity.this, 1, blockedList);
                        GroupBlacklistActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.GroupBlacklistActivity.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                GroupBlacklistActivity.this.listView.setAdapter((ListAdapter) GroupBlacklistActivity.this.adapter);
                                GroupBlacklistActivity.this.progressBar.setVisibility(4);
                            }
                        });
                    }
                } catch (HyphenateException e) {
                    GroupBlacklistActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.GroupBlacklistActivity.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            Toast.makeText(GroupBlacklistActivity.this.getApplicationContext(), st1, 0).show();
                            GroupBlacklistActivity.this.progressBar.setVisibility(4);
                        }
                    });
                }
            }
        });
    }

    @Override // android.app.Activity, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.em_remove_from_blacklist, menu);
    }

    @Override // android.app.Activity
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() != R.id.remove) {
            return super.onContextItemSelected(item);
        }
        removeOutBlacklist(this.adapter.getItem(((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position));
        return true;
    }

    void removeOutBlacklist(String tobeRemoveUser) {
        final String st2 = getResources().getString(R.string.Removed_from_the_failure);
        try {
            EMClient.getInstance().groupManager().unblockUser(this.groupId, tobeRemoveUser);
            this.adapter.remove(tobeRemoveUser);
        } catch (HyphenateException e) {
            e.printStackTrace();
            runOnUiThread(new Runnable() { // from class: com.em.ui.GroupBlacklistActivity.2
                @Override // java.lang.Runnable
                public void run() {
                    Toast.makeText(GroupBlacklistActivity.this.getApplicationContext(), st2, 0).show();
                }
            });
        }
    }

    @Override // com.easeui.ui.EaseBaseActivity
    public void back(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class BlacklistAdapter extends ArrayAdapter<String> {
        public BlacklistAdapter(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getContext(), R.layout.item_row_contact, null);
            }
            ((TextView) convertView.findViewById(R.id.name)).setText(getItem(position));
            return convertView;
        }
    }
}
