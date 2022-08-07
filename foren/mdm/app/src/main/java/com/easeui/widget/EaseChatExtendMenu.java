package com.easeui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.hyphenate.util.DensityUtil;
import com.vsf2f.f2f.R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class EaseChatExtendMenu extends GridView {
    protected Context context;
    private List<ChatMenuItemModel> itemModels;

    /* loaded from: classes.dex */
    public interface EaseChatExtendMenuItemClickListener {
        void onClick(int i, View view);
    }

    public EaseChatExtendMenu(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    public EaseChatExtendMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.itemModels = new ArrayList();
        init(context, attrs);
    }

    public EaseChatExtendMenu(Context context) {
        super(context);
        this.itemModels = new ArrayList();
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EaseChatExtendMenu);
        int numColumns = ta.getInt(0, 4);
        ta.recycle();
        setNumColumns(numColumns);
        setGravity(16);
        setStretchMode(2);
        setVerticalSpacing(DensityUtil.dip2px(context, 8.0f));
    }

    public void init() {
        setAdapter((ListAdapter) new ItemAdapter(this.context, this.itemModels));
    }

    public void registerMenuItem(String name, int drawableRes, int itemId, EaseChatExtendMenuItemClickListener listener) {
        ChatMenuItemModel item = new ChatMenuItemModel();
        item.id = itemId;
        item.name = name;
        item.image = drawableRes;
        item.clickListener = listener;
        this.itemModels.add(item);
    }

    public void registerMenuItem(int nameRes, int drawableRes, int itemId, EaseChatExtendMenuItemClickListener listener) {
        registerMenuItem(this.context.getString(nameRes), drawableRes, itemId, listener);
    }

    /* loaded from: classes.dex */
    private class ItemAdapter extends ArrayAdapter<ChatMenuItemModel> {
        private Context context;

        public ItemAdapter(Context context, List<ChatMenuItemModel> objects) {
            super(context, 0, objects);
            this.context = context;
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = new ChatMenuItem(this.context);
            }
            ChatMenuItem menuItem = (ChatMenuItem) convertView;
            menuItem.setImage(getItem(position).image);
            menuItem.setText(getItem(position).name);
            menuItem.setOnClickListener(new View.OnClickListener() { // from class: com.easeui.widget.EaseChatExtendMenu.ItemAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    if (ItemAdapter.this.getItem(position).clickListener != null) {
                        ItemAdapter.this.getItem(position).clickListener.onClick(ItemAdapter.this.getItem(position).id, v);
                    }
                }
            });
            return convertView;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ChatMenuItemModel {
        EaseChatExtendMenuItemClickListener clickListener;
        int id;
        int image;
        String name;

        ChatMenuItemModel() {
        }
    }

    /* loaded from: classes.dex */
    class ChatMenuItem extends LinearLayout {
        private ImageView imageView;
        private TextView textView;

        public ChatMenuItem(EaseChatExtendMenu this$0, Context context, AttributeSet attrs, int defStyle) {
            this(context, attrs);
        }

        public ChatMenuItem(Context context, AttributeSet attrs) {
            super(context, attrs);
            init(context, attrs);
        }

        public ChatMenuItem(Context context) {
            super(context);
            init(context, null);
        }

        private void init(Context context, AttributeSet attrs) {
            LayoutInflater.from(context).inflate(R.layout.ease_chat_menu_item, this);
            this.imageView = (ImageView) findViewById(R.id.image);
            this.textView = (TextView) findViewById(R.id.text);
        }

        public void setText(int resid) {
            this.textView.setText(resid);
        }

        public void setText(String text) {
            this.textView.setText(text);
        }

        public void setImage(int resid) {
            this.imageView.setImageResource(resid);
        }
    }
}
