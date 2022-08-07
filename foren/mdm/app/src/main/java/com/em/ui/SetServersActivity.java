package com.em.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import com.easeui.widget.EaseTitleBar;
import com.em.DemoModel;
import com.vsf2f.f2f.R;

/* loaded from: classes.dex */
public class SetServersActivity extends BaseActivity {
    DemoModel demoModel;
    EditText imEdit;
    EditText restEdit;
    EaseTitleBar titleBar;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.em.ui.BaseActivity, com.easeui.ui.EaseBaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_servers);
        this.restEdit = (EditText) findViewById(R.id.et_rest);
        this.imEdit = (EditText) findViewById(R.id.et_im);
        this.titleBar = (EaseTitleBar) findViewById(R.id.title_bar);
        this.demoModel = new DemoModel(this);
        if (this.demoModel.getRestServer() != null) {
            this.restEdit.setText(this.demoModel.getRestServer());
        }
        if (this.demoModel.getIMServer() != null) {
            this.imEdit.setText(this.demoModel.getIMServer());
        }
        this.titleBar.setLeftLayoutClickListener(new View.OnClickListener() { // from class: com.em.ui.SetServersActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                SetServersActivity.this.onBackPressed();
            }
        });
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        if (!TextUtils.isEmpty(this.restEdit.getText())) {
            this.demoModel.setRestServer(this.restEdit.getText().toString());
        }
        if (!TextUtils.isEmpty(this.imEdit.getText())) {
            this.demoModel.setIMServer(this.imEdit.getText().toString());
        }
        super.onBackPressed();
    }
}
