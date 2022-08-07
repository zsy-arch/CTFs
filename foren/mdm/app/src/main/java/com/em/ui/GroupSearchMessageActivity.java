package com.em.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.cdlinglu.utils.TimeUtil;
import com.easeui.EaseConstant;
import com.easeui.utils.EaseUserUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import java.util.List;

/* loaded from: classes.dex */
public class GroupSearchMessageActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton clearSearch;
    private String groupId;
    private ListView listView;
    private List<EMMessage> messageList;
    private SearchedMessageAdapter messageaAdapter;
    private ProgressDialog pd;
    private EditText query;
    private TextView searchView;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.em.ui.BaseActivity, com.easeui.ui.EaseBaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.em_activity_group_search_message);
        this.query = (EditText) findViewById(R.id.query);
        this.clearSearch = (ImageButton) findViewById(R.id.search_clear);
        this.listView = (ListView) findViewById(R.id.listview);
        TextView emptyView = (TextView) findViewById(R.id.tv_no_result);
        this.listView.setEmptyView(emptyView);
        emptyView.setVisibility(4);
        this.searchView = (TextView) findViewById(R.id.tv_search);
        this.groupId = getIntent().getStringExtra(EaseConstant.EXTRA_GROUP_ID);
        ((TextView) findViewById(R.id.tv_cancel)).setOnClickListener(this);
        this.searchView.setOnClickListener(this);
        this.query.addTextChangedListener(new TextWatcher() { // from class: com.em.ui.GroupSearchMessageActivity.1
            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    GroupSearchMessageActivity.this.clearSearch.setVisibility(0);
                } else {
                    GroupSearchMessageActivity.this.clearSearch.setVisibility(4);
                }
                GroupSearchMessageActivity.this.searchView.setVisibility(0);
                GroupSearchMessageActivity.this.listView.setVisibility(4);
                GroupSearchMessageActivity.this.searchView.setText(String.format(GroupSearchMessageActivity.this.getString(R.string.search_container), s));
            }
        });
        this.query.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.em.ui.GroupSearchMessageActivity.2
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId != 3) {
                    return false;
                }
                GroupSearchMessageActivity.this.searchMessages();
                GroupSearchMessageActivity.this.hideSoftKeyboard();
                return true;
            }
        });
        this.clearSearch.setOnClickListener(new View.OnClickListener() { // from class: com.em.ui.GroupSearchMessageActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                GroupSearchMessageActivity.this.query.getText().clear();
                GroupSearchMessageActivity.this.searchView.setText("");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void searchMessages() {
        this.pd = new ProgressDialog(this);
        this.pd.setMessage(getString(R.string.searching));
        this.pd.setCanceledOnTouchOutside(false);
        this.pd.show();
        ThreadPool.newThreadPool(new Runnable() { // from class: com.em.ui.GroupSearchMessageActivity.4
            @Override // java.lang.Runnable
            public void run() {
                List<EMMessage> resultList = EMClient.getInstance().chatManager().getConversation(GroupSearchMessageActivity.this.groupId).searchMsgFromDB(GroupSearchMessageActivity.this.query.getText().toString(), System.currentTimeMillis(), 50, (String) null, EMConversation.EMSearchDirection.UP);
                if (GroupSearchMessageActivity.this.messageList == null) {
                    GroupSearchMessageActivity.this.messageList = resultList;
                } else {
                    GroupSearchMessageActivity.this.messageList.clear();
                    GroupSearchMessageActivity.this.messageList.addAll(resultList);
                }
                GroupSearchMessageActivity.this.onSearchResulted();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSearchResulted() {
        runOnUiThread(new Runnable() { // from class: com.em.ui.GroupSearchMessageActivity.5
            @Override // java.lang.Runnable
            public void run() {
                GroupSearchMessageActivity.this.pd.dismiss();
                GroupSearchMessageActivity.this.searchView.setVisibility(4);
                GroupSearchMessageActivity.this.listView.setVisibility(0);
                if (GroupSearchMessageActivity.this.messageaAdapter == null) {
                    GroupSearchMessageActivity.this.messageaAdapter = new SearchedMessageAdapter(GroupSearchMessageActivity.this, 1, GroupSearchMessageActivity.this.messageList);
                    GroupSearchMessageActivity.this.listView.setAdapter((ListAdapter) GroupSearchMessageActivity.this.messageaAdapter);
                    return;
                }
                GroupSearchMessageActivity.this.messageaAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel /* 2131756080 */:
                finish();
                return;
            case R.id.tv_search /* 2131756126 */:
                hideSoftKeyboard();
                searchMessages();
                return;
            default:
                return;
        }
    }

    /* loaded from: classes.dex */
    private class SearchedMessageAdapter extends ArrayAdapter<EMMessage> {
        public SearchedMessageAdapter(Context context, int resource, List<EMMessage> objects) {
            super(context, resource, objects);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.em_row_search_message, parent, false);
            }
            ViewHolder holder = (ViewHolder) convertView.getTag();
            if (holder == null) {
                holder = new ViewHolder();
                holder.name = (TextView) convertView.findViewById(R.id.name);
                holder.message = (TextView) convertView.findViewById(R.id.message);
                holder.time = (TextView) convertView.findViewById(R.id.time);
                holder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
                convertView.setTag(holder);
            }
            EMMessage message = getItem(position);
            EaseUserUtils.setUserNick(message.getFrom(), holder.name);
            EaseUserUtils.setContactAvatar(getContext(), message.getFrom(), holder.avatar);
            holder.time.setText(TimeUtil.formatChatTime(message.getMsgTime()));
            holder.message.setText(((EMTextMessageBody) message.getBody()).getMessage());
            return convertView;
        }
    }

    /* loaded from: classes.dex */
    private static class ViewHolder {
        ImageView avatar;
        TextView message;
        TextView name;
        TextView time;

        private ViewHolder() {
        }
    }
}
