package com.vsf2f.f2f.ui.otay;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.em.DemoModel;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class OtayActivity extends Activity {
    private FlakeView flakeView;
    private int num = 50;
    private MediaPlayer player;
    private PopupWindow pop;

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_otayonii);
        this.num = getIntent().getIntExtra("num", 50);
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        initView();
    }

    protected void initView() {
        this.flakeView = new FlakeView(this);
        this.flakeView.setLayerType(0, null);
        this.flakeView.addFlakes(this.num);
        LinearLayout container = (LinearLayout) findViewById(R.id.container);
        container.addView(this.flakeView);
        this.pop = new PopupWindow(-1, -1);
        this.pop.showAtLocation(container, 17, 0, 0);
        this.pop.setOutsideTouchable(true);
        this.pop.setFocusable(true);
        DemoModel demoModel = new DemoModel(this);
        if (demoModel.getSettingMsgNotification() && demoModel.getSettingMsgSound()) {
            this.player = MediaPlayer.create(this, (int) R.raw.beans);
            this.player.setAudioStreamType(3);
            this.player.start();
        }
        new Handler().postDelayed(new Runnable() { // from class: com.vsf2f.f2f.ui.otay.OtayActivity.1
            @Override // java.lang.Runnable
            public void run() {
                OtayActivity.this.flakeView.setStop(true);
                OtayActivity.this.pop.dismiss();
            }
        }, 5000L);
        ((TextView) findViewById(R.id.txt_num)).setText("" + this.num);
        ((TextView) findViewById(R.id.txt_prompt)).setText(this.num + " 金豆奖励领取成功");
        findViewById(R.id.btn_stop).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.otay.OtayActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OtayActivity.this.flakeView.removeFlakes();
                OtayActivity.this.pop.dismiss();
                if (OtayActivity.this.player != null) {
                    OtayActivity.this.player.release();
                }
                OtayActivity.this.finish();
            }
        });
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        this.flakeView.removeFlakes();
        if (this.player != null) {
            this.player.release();
        }
        this.pop.dismiss();
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4 || event.getAction() != 1) {
            return super.onKeyDown(keyCode, event);
        }
        finish();
        return true;
    }
}
