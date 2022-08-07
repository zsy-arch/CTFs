package com.vsf2f.f2f.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;

/* loaded from: classes2.dex */
public class SysMsgDialog extends Dialog {
    private Button btnConfirm;
    private String content;
    private String href;
    private ImageView imgClose;
    private boolean showCancel;
    private String title;
    private TextView txtContent;
    private TextView txtTitle;

    public SysMsgDialog(Context context, String title, String content, String href, boolean showCancel) {
        super(context);
        this.href = href;
        this.title = title;
        this.content = content;
        this.showCancel = showCancel;
        setCancelable(true);
        setCanceledOnTouchOutside(false);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.dlg_sys_msg);
        this.imgClose = (ImageView) findViewById(R.id.warn_imgClose);
        this.btnConfirm = (Button) findViewById(R.id.warn_btnConfirm);
        this.txtTitle = (TextView) findViewById(R.id.warn_txtTitle);
        this.txtContent = (TextView) findViewById(R.id.warn_txtContent);
        View.OnClickListener listener = new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.dialog.SysMsgDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.warn_imgClose /* 2131756376 */:
                        SysMsgDialog.this.onCancel(view);
                        return;
                    case R.id.warn_btnConfirm /* 2131756377 */:
                        SysMsgDialog.this.onConfirm(view);
                        return;
                    default:
                        return;
                }
            }
        };
        this.imgClose.setOnClickListener(listener);
        this.btnConfirm.setOnClickListener(listener);
        if (HyUtil.isNoEmpty(this.title)) {
            this.txtTitle.setText(this.title);
        }
        if (HyUtil.isNoEmpty(this.content)) {
            this.txtContent.setText(this.content);
        }
        this.btnConfirm.setText(R.string.see);
        this.imgClose.setVisibility(this.showCancel ? 0 : 8);
    }

    public void onConfirm(View view) {
        dismiss();
        Intent intent = new Intent(getContext(), WebKitLocalActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constant.FLAG, this.href);
        bundle.putString(Constant.FLAG_TITLE, com.vsf2f.f2f.ui.utils.Constant.FLAG_AUTO_TITLE);
        intent.putExtra(Constant.BUNDLE, bundle);
        intent.addFlags(268435456);
        getContext().startActivity(intent);
    }

    public void onCancel(View view) {
        dismiss();
    }
}
