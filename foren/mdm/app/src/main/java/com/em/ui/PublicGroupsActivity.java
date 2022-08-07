package com.em.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCursorResult;
import com.hyphenate.chat.EMGroupInfo;
import com.hyphenate.exceptions.HyphenateException;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class PublicGroupsActivity extends BaseActivity {
    private GroupsAdapter adapter;
    private String cursor;
    private LinearLayout footLoadingLayout;
    private ProgressBar footLoadingPB;
    private TextView footLoadingText;
    private List<EMGroupInfo> groupsList;
    private boolean isLoading;
    private ListView listView;
    private ProgressBar pb;
    private boolean isFirstLoading = true;
    private boolean hasMoreData = true;
    private final int pagesize = 20;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.em.ui.BaseActivity, com.easeui.ui.EaseBaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.em_activity_public_groups);
        this.pb = (ProgressBar) findViewById(R.id.progressBar);
        this.listView = (ListView) findViewById(R.id.list);
        this.groupsList = new ArrayList();
        View footView = getLayoutInflater().inflate(R.layout.em_listview_footer_view, (ViewGroup) this.listView, false);
        this.footLoadingLayout = (LinearLayout) footView.findViewById(R.id.loading_layout);
        this.footLoadingPB = (ProgressBar) footView.findViewById(R.id.loading_bar);
        this.footLoadingText = (TextView) footView.findViewById(R.id.loading_text);
        this.listView.addFooterView(footView, null, false);
        this.footLoadingLayout.setVisibility(8);
        loadAndShowData();
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.em.ui.PublicGroupsActivity.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PublicGroupsActivity.this.startActivity(new Intent(PublicGroupsActivity.this, GroupSimpleDetailActivity.class).putExtra("groupinfo", PublicGroupsActivity.this.adapter.getItem(position)));
            }
        });
        this.listView.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.em.ui.PublicGroupsActivity.2
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == 0 && PublicGroupsActivity.this.listView.getCount() != 0) {
                    int lasPos = view.getLastVisiblePosition();
                    if (PublicGroupsActivity.this.hasMoreData && !PublicGroupsActivity.this.isLoading && lasPos == PublicGroupsActivity.this.listView.getCount() - 1) {
                        PublicGroupsActivity.this.loadAndShowData();
                    }
                }
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadAndShowData() {
        ThreadPool.newThreadPool(new Runnable() { // from class: com.em.ui.PublicGroupsActivity.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    PublicGroupsActivity.this.isLoading = true;
                    final EMCursorResult<EMGroupInfo> result = EMClient.getInstance().groupManager().getPublicGroupsFromServer(20, PublicGroupsActivity.this.cursor);
                    final List<EMGroupInfo> returnGroups = (List) result.getData();
                    PublicGroupsActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.PublicGroupsActivity.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            PublicGroupsActivity.this.groupsList.addAll(returnGroups);
                            if (returnGroups.size() != 0) {
                                PublicGroupsActivity.this.cursor = result.getCursor();
                                if (returnGroups.size() == 20) {
                                    PublicGroupsActivity.this.footLoadingLayout.setVisibility(0);
                                }
                            }
                            if (PublicGroupsActivity.this.isFirstLoading) {
                                PublicGroupsActivity.this.pb.setVisibility(4);
                                PublicGroupsActivity.this.isFirstLoading = false;
                                PublicGroupsActivity.this.adapter = new GroupsAdapter(PublicGroupsActivity.this, 1, PublicGroupsActivity.this.groupsList);
                                PublicGroupsActivity.this.listView.setAdapter((ListAdapter) PublicGroupsActivity.this.adapter);
                            } else {
                                if (returnGroups.size() < 20) {
                                    PublicGroupsActivity.this.hasMoreData = false;
                                    PublicGroupsActivity.this.footLoadingLayout.setVisibility(0);
                                    PublicGroupsActivity.this.footLoadingPB.setVisibility(8);
                                    PublicGroupsActivity.this.footLoadingText.setText("No more data");
                                }
                                PublicGroupsActivity.this.adapter.notifyDataSetChanged();
                            }
                            PublicGroupsActivity.this.isLoading = false;
                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    PublicGroupsActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.PublicGroupsActivity.3.2
                        @Override // java.lang.Runnable
                        public void run() {
                            PublicGroupsActivity.this.isLoading = false;
                            PublicGroupsActivity.this.pb.setVisibility(4);
                            PublicGroupsActivity.this.footLoadingLayout.setVisibility(8);
                            Toast.makeText(PublicGroupsActivity.this, "load failed, please check your network or try it later", 0).show();
                        }
                    });
                }
            }
        });
    }

    /* loaded from: classes.dex */
    private class GroupsAdapter extends ArrayAdapter<EMGroupInfo> {
        private LayoutInflater inflater;

        public GroupsAdapter(Context context, int res, List<EMGroupInfo> groups) {
            super(context, res, groups);
            this.inflater = LayoutInflater.from(context);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = this.inflater.inflate(R.layout.em_row_group, parent, false);
            }
            ((TextView) convertView.findViewById(R.id.name)).setText(getItem(position).getGroupName());
            return convertView;
        }
    }
}
