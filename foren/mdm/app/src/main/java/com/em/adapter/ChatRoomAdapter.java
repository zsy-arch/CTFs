package com.em.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.hyphenate.chat.EMChatRoom;
import com.vsf2f.f2f.R;
import java.util.List;

/* loaded from: classes.dex */
public class ChatRoomAdapter extends ArrayAdapter<EMChatRoom> {
    private String addChatRoomString;
    private LayoutInflater inflater;

    public ChatRoomAdapter(Context context, int res, List<EMChatRoom> groups) {
        super(context, res, groups);
        this.inflater = LayoutInflater.from(context);
        this.addChatRoomString = context.getResources().getString(R.string.add_public_chat_room);
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return 3;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        }
        if (position != 1) {
            return 2;
        }
        return 1;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        if (getItemViewType(position) == 0) {
            if (convertView == null) {
                convertView = this.inflater.inflate(R.layout.em_search_bar_with_padding, parent, false);
            }
            final EditText query = (EditText) convertView.findViewById(R.id.query);
            final ImageButton clearSearch = (ImageButton) convertView.findViewById(R.id.search_clear);
            query.addTextChangedListener(new TextWatcher() { // from class: com.em.adapter.ChatRoomAdapter.1
                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    ChatRoomAdapter.this.getFilter().filter(s);
                    if (s.length() > 0) {
                        clearSearch.setVisibility(0);
                    } else {
                        clearSearch.setVisibility(4);
                    }
                }

                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable s) {
                }
            });
            clearSearch.setOnClickListener(new View.OnClickListener() { // from class: com.em.adapter.ChatRoomAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    query.getText().clear();
                }
            });
        } else if (getItemViewType(position) == 1) {
            if (convertView == null) {
                convertView = this.inflater.inflate(R.layout.em_row_add_group, parent, false);
            }
            ((ImageView) convertView.findViewById(R.id.avatar)).setImageResource(R.drawable.em_add_public_group);
            ((TextView) convertView.findViewById(R.id.name)).setText(this.addChatRoomString);
            ((TextView) convertView.findViewById(R.id.header)).setVisibility(0);
        } else {
            if (convertView == null) {
                convertView = this.inflater.inflate(R.layout.em_row_group, parent, false);
            }
            ((TextView) convertView.findViewById(R.id.name)).setText(getItem(position - 2).getName());
        }
        return convertView;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public int getCount() {
        return super.getCount() + 2;
    }
}
