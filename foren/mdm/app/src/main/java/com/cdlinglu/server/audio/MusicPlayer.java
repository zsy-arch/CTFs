package com.cdlinglu.server.audio;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.vsf2f.f2f.R;

/* loaded from: classes.dex */
public class MusicPlayer extends LinearLayout {
    private ImageView img_voice;
    private TextView txt_voice;

    public ImageView getImg_voice() {
        return this.img_voice;
    }

    public MusicPlayer(Context context) {
        this(context, null);
    }

    public MusicPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.my_voice_player, this);
        this.img_voice = (ImageView) findViewById(R.id.img_voice);
        this.txt_voice = (TextView) findViewById(R.id.txt_voice);
    }

    public void setStart() {
        this.img_voice.setImageResource(R.drawable.voice_icon_demand);
    }

    public void setStop() {
        this.img_voice.setImageResource(R.drawable.icon_playvoicenotes3);
    }

    public void setDuration(String duration) {
        this.txt_voice.setText(duration);
    }
}
