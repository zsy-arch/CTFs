package com.em.ui;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.cdlinglu.common.BaseActivity;
import com.easeui.EaseConstant;
import com.hyphenate.EMChatRoomChangeListener;
import com.hyphenate.chat.EMChatRoom;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCursorResult;
import com.hyphenate.exceptions.HyphenateException;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class PublicChatRoomsActivity extends BaseActivity {
    private ChatRoomAdapter adapter;
    private List<EMChatRoom> chatRoomList;
    private String cursor;
    private EditText etSearch;
    private LinearLayout footLoadingLayout;
    private ProgressBar footLoadingPB;
    private TextView footLoadingText;
    private ImageButton ibClean;
    private boolean isLoading;
    private ListView listView;
    private List<EMChatRoom> rooms;
    private boolean isFirstLoading = true;
    private boolean hasMoreData = true;
    private final int pagesize = 50;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.em_activity_public_groups;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.chat_room, 0);
        this.etSearch = (EditText) findViewById(R.id.query);
        this.ibClean = (ImageButton) findViewById(R.id.search_clear);
        this.etSearch.setHint(R.string.search);
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        this.listView = (ListView) findViewById(R.id.list);
        this.chatRoomList = new ArrayList();
        this.rooms = new ArrayList();
        View footView = getLayoutInflater().inflate(R.layout.em_listview_footer_view, (ViewGroup) this.listView, false);
        this.footLoadingLayout = (LinearLayout) footView.findViewById(R.id.loading_layout);
        this.footLoadingPB = (ProgressBar) footView.findViewById(R.id.loading_bar);
        this.footLoadingText = (TextView) footView.findViewById(R.id.loading_text);
        this.listView.addFooterView(footView, null, false);
        this.footLoadingLayout.setVisibility(8);
        this.etSearch.addTextChangedListener(new TextWatcher() { // from class: com.em.ui.PublicChatRoomsActivity.1
            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (PublicChatRoomsActivity.this.adapter != null) {
                    PublicChatRoomsActivity.this.adapter.getFilter().filter(s);
                }
                if (s.length() > 0) {
                    PublicChatRoomsActivity.this.ibClean.setVisibility(0);
                } else {
                    PublicChatRoomsActivity.this.ibClean.setVisibility(4);
                }
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }
        });
        this.ibClean.setOnClickListener(new View.OnClickListener() { // from class: com.em.ui.PublicChatRoomsActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                PublicChatRoomsActivity.this.etSearch.getText().clear();
            }
        });
        loadAndShowData();
        EMClient.getInstance().chatroomManager().addChatRoomChangeListener(new EMChatRoomChangeListener() { // from class: com.em.ui.PublicChatRoomsActivity.3
            @Override // com.hyphenate.EMChatRoomChangeListener
            public void onChatRoomDestroyed(String roomId, String roomName) {
                PublicChatRoomsActivity.this.chatRoomList.clear();
                if (PublicChatRoomsActivity.this.adapter != null) {
                    PublicChatRoomsActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.PublicChatRoomsActivity.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (PublicChatRoomsActivity.this.adapter != null) {
                                PublicChatRoomsActivity.this.adapter.notifyDataSetChanged();
                                PublicChatRoomsActivity.this.loadAndShowData();
                            }
                        }
                    });
                }
            }

            @Override // com.hyphenate.EMChatRoomChangeListener
            public void onMemberJoined(String roomId, String participant) {
            }

            @Override // com.hyphenate.EMChatRoomChangeListener
            public void onMemberExited(String roomId, String roomName, String participant) {
            }

            @Override // com.hyphenate.EMChatRoomChangeListener
            public void onRemovedFromChatRoom(String roomId, String roomName, String participant) {
            }
        });
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.em.ui.PublicChatRoomsActivity.4
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PublicChatRoomsActivity.this.startActivity(new Intent(PublicChatRoomsActivity.this, ChatActivity.class).putExtra(EaseConstant.EXTRA_CHAT_TYPE, 3).putExtra("username", PublicChatRoomsActivity.this.adapter.getItem(position).getId()));
            }
        });
        this.listView.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.em.ui.PublicChatRoomsActivity.5
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == 0 && PublicChatRoomsActivity.this.cursor != null) {
                    int lasPos = view.getLastVisiblePosition();
                    if (PublicChatRoomsActivity.this.hasMoreData && !PublicChatRoomsActivity.this.isLoading && lasPos == PublicChatRoomsActivity.this.listView.getCount() - 1) {
                        PublicChatRoomsActivity.this.loadAndShowData();
                    }
                }
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadAndShowData() {
        ThreadPool.newThreadPool(new Runnable() { // from class: com.em.ui.PublicChatRoomsActivity.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    PublicChatRoomsActivity.this.isLoading = true;
                    final EMCursorResult<EMChatRoom> result = EMClient.getInstance().chatroomManager().fetchPublicChatRoomsFromServer(50, PublicChatRoomsActivity.this.cursor);
                    final List<EMChatRoom> chatRooms = (List) result.getData();
                    PublicChatRoomsActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.PublicChatRoomsActivity.6.1
                        @Override // java.lang.Runnable
                        public void run() {
                            PublicChatRoomsActivity.this.chatRoomList.addAll(chatRooms);
                            if (chatRooms.size() != 0) {
                                PublicChatRoomsActivity.this.cursor = result.getCursor();
                            }
                            if (PublicChatRoomsActivity.this.isFirstLoading) {
                                PublicChatRoomsActivity.this.isFirstLoading = false;
                                PublicChatRoomsActivity.this.adapter = new ChatRoomAdapter(PublicChatRoomsActivity.this, 1, PublicChatRoomsActivity.this.chatRoomList);
                                PublicChatRoomsActivity.this.listView.setAdapter((ListAdapter) PublicChatRoomsActivity.this.adapter);
                                PublicChatRoomsActivity.this.rooms.addAll(chatRooms);
                            } else {
                                if (chatRooms.size() < 50) {
                                    PublicChatRoomsActivity.this.hasMoreData = false;
                                    PublicChatRoomsActivity.this.footLoadingLayout.setVisibility(0);
                                    PublicChatRoomsActivity.this.footLoadingPB.setVisibility(8);
                                    PublicChatRoomsActivity.this.footLoadingText.setText(PublicChatRoomsActivity.this.getResources().getString(R.string.no_more_messages));
                                }
                                PublicChatRoomsActivity.this.adapter.notifyDataSetChanged();
                            }
                            PublicChatRoomsActivity.this.isLoading = false;
                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    PublicChatRoomsActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.PublicChatRoomsActivity.6.2
                        @Override // java.lang.Runnable
                        public void run() {
                            PublicChatRoomsActivity.this.isLoading = false;
                            PublicChatRoomsActivity.this.footLoadingLayout.setVisibility(8);
                            Toast.makeText(PublicChatRoomsActivity.this, PublicChatRoomsActivity.this.getResources().getString(R.string.failed_to_load_data), 0).show();
                        }
                    });
                }
            }
        });
    }

    public void search(View view) {
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

    /* loaded from: classes.dex */
    private class ChatRoomAdapter extends ArrayAdapter<EMChatRoom> {
        private RoomFilter filter;
        private LayoutInflater inflater;

        public ChatRoomAdapter(Context context, int res, List<EMChatRoom> rooms) {
            super(context, res, rooms);
            this.inflater = LayoutInflater.from(context);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = this.inflater.inflate(R.layout.em_row_group, parent, false);
            }
            ((TextView) convertView.findViewById(R.id.name)).setText(getItem(position).getName());
            return convertView;
        }

        @Override // android.widget.ArrayAdapter, android.widget.Filterable
        public Filter getFilter() {
            if (this.filter == null) {
                this.filter = new RoomFilter();
            }
            return this.filter;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public class RoomFilter extends Filter {
            private RoomFilter() {
            }

            @Override // android.widget.Filter
            protected Filter.FilterResults performFiltering(CharSequence constraint) {
                Filter.FilterResults results = new Filter.FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    results.values = PublicChatRoomsActivity.this.rooms;
                    results.count = PublicChatRoomsActivity.this.rooms.size();
                } else {
                    List<EMChatRoom> roomss = new ArrayList<>();
                    for (EMChatRoom chatRoom : PublicChatRoomsActivity.this.rooms) {
                        if (chatRoom.getName().contains(constraint)) {
                            roomss.add(chatRoom);
                        }
                    }
                    results.values = roomss;
                    results.count = roomss.size();
                }
                return results;
            }

            @Override // android.widget.Filter
            protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
                PublicChatRoomsActivity.this.chatRoomList.clear();
                PublicChatRoomsActivity.this.chatRoomList.addAll((List) results.values);
                ChatRoomAdapter.this.notifyDataSetChanged();
            }
        }
    }
}
