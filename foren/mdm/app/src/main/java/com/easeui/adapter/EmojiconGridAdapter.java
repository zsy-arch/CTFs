package com.easeui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.easeui.domain.EaseEmojicon;
import com.easeui.utils.EaseSmileUtils;
import com.vsf2f.f2f.R;
import java.util.List;

/* loaded from: classes.dex */
public class EmojiconGridAdapter extends ArrayAdapter<EaseEmojicon> {
    private EaseEmojicon.Type emojiconType;

    public EmojiconGridAdapter(Context context, int textViewResourceId, List<EaseEmojicon> objects, EaseEmojicon.Type emojiconType) {
        super(context, textViewResourceId, objects);
        this.emojiconType = emojiconType;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = this.emojiconType == EaseEmojicon.Type.BIG_EXPRESSION ? View.inflate(getContext(), R.layout.ease_row_big_expression, null) : View.inflate(getContext(), R.layout.ease_row_expression, null);
        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_expression);
        TextView textView = (TextView) convertView.findViewById(R.id.tv_name);
        EaseEmojicon emojicon = getItem(position);
        if (!(textView == null || emojicon.getName() == null)) {
            textView.setText(emojicon.getName());
        }
        if (EaseSmileUtils.DELETE_KEY.equals(emojicon.getEmojiText())) {
            imageView.setImageResource(R.drawable.icon_keyboard_delete2);
        } else if (emojicon.getIcon() != 0) {
            imageView.setImageResource(emojicon.getIcon());
        } else if (emojicon.getIconPath() != null) {
            Glide.with(getContext()).load(emojicon.getIconPath()).error((int) R.drawable.ease_default_expression).into(imageView);
        }
        return convertView;
    }
}
