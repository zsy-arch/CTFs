package com.vsf2f.f2f.ui.needs.star;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.easeui.widget.EaseSidebar;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.EaseAddressAdapter;
import com.vsf2f.f2f.ui.utils.area.AddressPicker;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class EaseAddressList extends RelativeLayout {
    static final int MSG_UPDATE_LIST = 0;
    protected static final String TAG = EaseAddressList.class.getSimpleName();
    protected EaseAddressAdapter adapter;
    protected ArrayList<AddressPicker.City> contactList;
    protected Context context;
    Handler handler;
    protected Drawable initialLetterBg;
    protected int initialLetterColor;
    protected ListView listView;
    protected int primaryColor;
    protected int primarySize;
    protected boolean showSiderBar;
    protected EaseSidebar sidebar;

    public EaseAddressList(Context context) {
        super(context);
        this.handler = new Handler() { // from class: com.vsf2f.f2f.ui.needs.star.EaseAddressList.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        if (EaseAddressList.this.adapter != null) {
                            EaseAddressList.this.adapter.clear();
                            EaseAddressList.this.adapter.addAll(new ArrayList(EaseAddressList.this.contactList));
                            EaseAddressList.this.adapter.notifyDataSetChanged();
                            break;
                        }
                        break;
                }
                super.handleMessage(msg);
            }
        };
        init(context, null);
    }

    public EaseAddressList(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.handler = new Handler() { // from class: com.vsf2f.f2f.ui.needs.star.EaseAddressList.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        if (EaseAddressList.this.adapter != null) {
                            EaseAddressList.this.adapter.clear();
                            EaseAddressList.this.adapter.addAll(new ArrayList(EaseAddressList.this.contactList));
                            EaseAddressList.this.adapter.notifyDataSetChanged();
                            break;
                        }
                        break;
                }
                super.handleMessage(msg);
            }
        };
        init(context, attrs);
    }

    public EaseAddressList(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EaseContactList);
        this.primaryColor = ta.getColor(0, 0);
        this.primarySize = ta.getDimensionPixelSize(1, 0);
        this.showSiderBar = ta.getBoolean(2, true);
        this.initialLetterBg = ta.getDrawable(3);
        this.initialLetterColor = ta.getColor(4, 0);
        ta.recycle();
        LayoutInflater.from(context).inflate(R.layout.ease_widget_contact_list, this);
        this.listView = (ListView) findViewById(R.id.list);
        this.sidebar = (EaseSidebar) findViewById(R.id.sidebar);
        if (!this.showSiderBar) {
            this.sidebar.setVisibility(8);
        }
    }

    public void init(ArrayList<AddressPicker.City> contactList) {
        this.contactList = contactList;
        this.adapter = new EaseAddressAdapter(this.context, R.layout.layout_item_address, new ArrayList(contactList));
        this.adapter.setPrimaryColor(this.primaryColor).setPrimarySize(this.primarySize).setInitialLetterBg(this.initialLetterBg).setInitialLetterColor(this.initialLetterColor);
        this.listView.setAdapter((ListAdapter) this.adapter);
        if (this.showSiderBar) {
            this.sidebar.setListView(this.listView);
        }
    }

    public void refresh() {
        this.handler.sendMessage(this.handler.obtainMessage(0));
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
