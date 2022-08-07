package com.hy.frame.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import com.hy.frame.R;

/* loaded from: classes2.dex */
public class TimerButton extends Button implements Runnable {
    private static final int DEFAULT_INTERVAL = 120;
    private static final int UPDATE_DOING = 2;
    private static final int UPDATE_END = 0;
    private int timer = 0;
    private int status = 0;

    public TimerButton(Context context) {
        super(context);
    }

    public TimerButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TimerButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void start() {
        start(120);
    }

    public void end() {
        this.status = 0;
        setText(R.string.timer_send_re);
        setEnabled(true);
    }

    private void update(int time) {
        setText(String.format(getHint().toString(), Integer.valueOf(time)));
    }

    public void prepare() {
        setText(R.string.timer_sending);
        setEnabled(false);
    }

    public void start(int interval) {
        if (this.status != 2) {
            this.status = 2;
            setEnabled(false);
            this.timer = interval;
            run();
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        this.timer--;
        if (this.timer < 1) {
            end();
            return;
        }
        update(this.timer);
        postDelayed(this, 1000L);
    }
}
