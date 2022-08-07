package com.easeui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.easeui.adapter.EaseContactAdapter;
import com.easeui.domain.EaseUser;
import com.vsf2f.f2f.R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class EaseContactList extends RelativeLayout {
    private static final int MSG_UPDATE_LIST = 0;
    protected EaseContactAdapter adapter;
    protected List<EaseUser> allContactList;
    protected List<EaseUser> contactList;
    protected Context context;
    Handler handler;
    protected Drawable initialLetterBg;
    protected int initialLetterColor;
    protected ListView listView;
    private View loadMore;
    private int page;
    private int pageSize;
    protected int primaryColor;
    protected int primarySize;
    protected boolean showSiderBar;
    protected EaseSidebar sidebar;
    private int totalSize;

    static /* synthetic */ int access$008(EaseContactList x0) {
        int i = x0.page;
        x0.page = i + 1;
        return i;
    }

    public EaseContactList(Context context) {
        super(context);
        this.contactList = new ArrayList();
        this.allContactList = new ArrayList();
        this.page = 1;
        this.pageSize = 100;
        this.handler = new Handler() { // from class: com.easeui.widget.EaseContactList.2
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        if (EaseContactList.this.adapter != null) {
                            EaseContactList.this.contactList.clear();
                            if (EaseContactList.this.allContactList.size() > EaseContactList.this.pageSize) {
                                EaseContactList.this.contactList.addAll(EaseContactList.this.allContactList.subList(0, EaseContactList.this.page * EaseContactList.this.pageSize));
                            } else {
                                EaseContactList.this.contactList.addAll(EaseContactList.this.allContactList);
                            }
                            EaseContactList.this.adapter.notifyDataSetChanged();
                            break;
                        }
                        break;
                }
                super.handleMessage(msg);
            }
        };
        init(context, null);
    }

    public EaseContactList(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.contactList = new ArrayList();
        this.allContactList = new ArrayList();
        this.page = 1;
        this.pageSize = 100;
        this.handler = new Handler() { // from class: com.easeui.widget.EaseContactList.2
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        if (EaseContactList.this.adapter != null) {
                            EaseContactList.this.contactList.clear();
                            if (EaseContactList.this.allContactList.size() > EaseContactList.this.pageSize) {
                                EaseContactList.this.contactList.addAll(EaseContactList.this.allContactList.subList(0, EaseContactList.this.page * EaseContactList.this.pageSize));
                            } else {
                                EaseContactList.this.contactList.addAll(EaseContactList.this.allContactList);
                            }
                            EaseContactList.this.adapter.notifyDataSetChanged();
                            break;
                        }
                        break;
                }
                super.handleMessage(msg);
            }
        };
        init(context, attrs);
    }

    public EaseContactList(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        this.loadMore = LayoutInflater.from(context).inflate(R.layout.layout_load_more_friend, (ViewGroup) null);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EaseContactList);
        this.primaryColor = ta.getColor(0, 0);
        this.primarySize = ta.getDimensionPixelSize(1, 0);
        this.showSiderBar = ta.getBoolean(2, true);
        this.initialLetterBg = ta.getDrawable(3);
        this.initialLetterColor = ta.getColor(4, 0);
        ta.recycle();
        this.loadMore.findViewById(R.id.tv_load_more).setOnClickListener(new View.OnClickListener() { // from class: com.easeui.widget.EaseContactList.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                EaseContactList.access$008(EaseContactList.this);
                if (EaseContactList.this.page * EaseContactList.this.pageSize > EaseContactList.this.totalSize) {
                    if ((EaseContactList.this.page - 1) * EaseContactList.this.pageSize != EaseContactList.this.totalSize) {
                        EaseContactList.this.contactList.addAll(EaseContactList.this.allContactList.subList((EaseContactList.this.page - 1) * EaseContactList.this.pageSize, EaseContactList.this.totalSize - 1));
                    }
                    EaseContactList.this.loadMore.setVisibility(8);
                    EaseContactList.this.listView.removeFooterView(EaseContactList.this.loadMore);
                } else {
                    EaseContactList.this.contactList.addAll(EaseContactList.this.allContactList.subList((EaseContactList.this.page - 1) * EaseContactList.this.pageSize, EaseContactList.this.page * EaseContactList.this.pageSize));
                }
                EaseContactList.this.adapter.notifyDataSetChanged();
            }
        });
        LayoutInflater.from(context).inflate(R.layout.ease_widget_contact_list, this);
        this.listView = (ListView) findViewById(R.id.list);
        this.sidebar = (EaseSidebar) findViewById(R.id.sidebar);
        if (!this.showSiderBar) {
            this.sidebar.setVisibility(8);
        }
    }

    public void init(List<EaseUser> contactList) {
        this.allContactList.clear();
        this.allContactList.addAll(contactList);
        this.totalSize = this.allContactList.size();
        this.contactList.clear();
        if (this.totalSize > this.pageSize) {
            if (this.listView.getFooterViewsCount() == 0) {
                this.loadMore.setVisibility(0);
                this.listView.addFooterView(this.loadMore);
            }
            this.contactList.addAll(this.allContactList.subList(0, this.pageSize));
        } else {
            this.loadMore.setVisibility(8);
            this.listView.removeFooterView(this.loadMore);
            this.contactList.addAll(this.allContactList);
        }
        this.adapter = new EaseContactAdapter(this.context, R.layout.item_row_contact_list, this.contactList);
        this.adapter.setPrimaryColor(this.primaryColor).setPrimarySize(this.primarySize).setInitialLetterBg(this.initialLetterBg).setInitialLetterColor(this.initialLetterColor);
        this.listView.setAdapter((ListAdapter) this.adapter);
        if (this.showSiderBar) {
            this.sidebar.setListView(this.listView);
        }
    }

    public void setContactList(List<EaseUser> contactList) {
        this.allContactList.clear();
        this.allContactList.addAll(contactList);
        this.totalSize = this.allContactList.size();
        if (this.totalSize <= this.pageSize) {
            this.loadMore.setVisibility(8);
            this.listView.removeFooterView(this.loadMore);
        } else if (this.listView.getFooterViewsCount() == 0) {
            this.loadMore.setVisibility(0);
            this.listView.addFooterView(this.loadMore);
        }
        refresh();
    }

    public void refresh() {
        this.page = 1;
        this.handler.sendMessage(this.handler.obtainMessage(0));
    }

    public void deleteRefresh(EaseUser deleteBean) {
        this.contactList.remove(deleteBean);
        this.allContactList.remove(deleteBean);
        this.adapter.notifyDataSetChanged();
    }

    public void filter(CharSequence str) {
        this.adapter.getFilter().filter(str);
    }

    public ListView getListView() {
        return this.listView;
    }

    public void setShowSiderBar(boolean showSiderBar) {
        if (showSiderBar) {
            this.sidebar.setVisibility(0);
        } else {
            this.sidebar.setVisibility(8);
        }
    }
}
