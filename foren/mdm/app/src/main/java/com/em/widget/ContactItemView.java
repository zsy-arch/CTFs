package com.em.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.vsf2f.f2f.R;

/* loaded from: classes.dex */
public class ContactItemView extends LinearLayout {
    private TextView unreadMsgView;

    public ContactItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ContactItemView(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ContactItemView);
        String name = ta.getString(1);
        Drawable image = ta.getDrawable(0);
        ta.recycle();
        LayoutInflater.from(context).inflate(R.layout.em_widget_contact_item, this);
        ImageView avatar = (ImageView) findViewById(R.id.avatar);
        TextView nameView = (TextView) findViewById(R.id.name);
        this.unreadMsgView = (TextView) findViewById(R.id.unread_msg_number);
        if (image != null) {
            avatar.setImageDrawable(image);
        }
        nameView.setText(name);
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadMsgView.setText(String.valueOf(unreadCount));
    }

    public void showUnreadMsgView() {
        this.unreadMsgView.setVisibility(0);
    }

    public void hideUnreadMsgView() {
        this.unreadMsgView.setVisibility(4);
    }
}
