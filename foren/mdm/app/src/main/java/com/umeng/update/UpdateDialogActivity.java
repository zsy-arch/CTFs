package com.umeng.update;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.alimama.mobile.MMAdView;
import java.io.File;
import u.upd.a;
import u.upd.c;

/* loaded from: classes2.dex */
public class UpdateDialogActivity extends Activity {
    UpdateResponse b;
    ViewGroup e;
    MMAdView f;
    int a = 6;
    boolean c = false;
    File d = null;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(c.a(this).d("umeng_update_dialog"));
        this.b = (UpdateResponse) getIntent().getExtras().getSerializable("response");
        String string = getIntent().getExtras().getString("file");
        boolean z = getIntent().getExtras().getBoolean("force");
        boolean z2 = string != null;
        if (z2) {
            this.d = new File(string);
        }
        int b = c.a(this).b("umeng_update_content");
        int b2 = c.a(this).b("umeng_update_wifi_indicator");
        final int b3 = c.a(this).b("umeng_update_id_ok");
        int b4 = c.a(this).b("umeng_update_id_cancel");
        final int b5 = c.a(this).b("umeng_update_id_ignore");
        int b6 = c.a(this).b("umeng_update_id_close");
        int b7 = c.a(this).b("umeng_update_id_check");
        this.e = (ViewGroup) findViewById(c.a(this).b("umeng_update_frame"));
        if (this.e != null && this.b.display_ads) {
            String slotId = UpdateConfig.getSlotId();
            if (!TextUtils.isEmpty(slotId)) {
                this.f = new MMAdView(this);
                if (this.f.init(slotId)) {
                    this.e.addView(this.f, new ViewGroup.LayoutParams(-1, -2));
                }
            } else {
                Log.w(UpdateConfig.a, "尚未设置推广位id,无法展示推广内容。");
            }
        }
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.umeng.update.UpdateDialogActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (b3 == view.getId()) {
                    UpdateDialogActivity.this.a = 5;
                } else if (b5 == view.getId()) {
                    UpdateDialogActivity.this.a = 7;
                } else if (UpdateDialogActivity.this.c) {
                    UpdateDialogActivity.this.a = 7;
                }
                UpdateDialogActivity.this.finish();
            }
        };
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.umeng.update.UpdateDialogActivity.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z3) {
                UpdateDialogActivity.this.c = z3;
            }
        };
        if (b2 > 0) {
            findViewById(b2).setVisibility(a.k(this) ? 8 : 0);
        }
        if (z) {
            findViewById(b7).setVisibility(8);
        }
        findViewById(b3).setOnClickListener(onClickListener);
        findViewById(b4).setOnClickListener(onClickListener);
        findViewById(b5).setOnClickListener(onClickListener);
        findViewById(b6).setOnClickListener(onClickListener);
        ((CheckBox) findViewById(b7)).setOnCheckedChangeListener(onCheckedChangeListener);
        String a = this.b.a(this, z2);
        TextView textView = (TextView) findViewById(b);
        textView.requestFocus();
        textView.setText(a);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        UmengUpdateAgent.a(this.a, this, this.b, this.d);
        if (this.f != null) {
            this.f.destroy();
        }
    }
}
