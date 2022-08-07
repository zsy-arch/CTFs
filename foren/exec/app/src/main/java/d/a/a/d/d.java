package d.a.a.d;

import android.app.Dialog;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tencent.smtt.utils.TbsLog;
import e.b.a.e;
import e.b.a.h;

/* loaded from: classes.dex */
public class d extends Dialog {

    /* renamed from: a */
    public TextView f1628a = (TextView) findViewById(e.b.a.d.tv_kindly_reminder);

    /* renamed from: b */
    public TextView f1629b = (TextView) findViewById(e.b.a.d.tv_tip_content);

    /* renamed from: e */
    public LinearLayout f1632e = (LinearLayout) findViewById(e.b.a.d.ll_double_button_container);

    /* renamed from: c */
    public TextView f1630c = (TextView) findViewById(e.b.a.d.tv_cancel);

    /* renamed from: d */
    public TextView f1631d = (TextView) findViewById(e.b.a.d.tv_confirm);
    public TextView f = (TextView) findViewById(e.b.a.d.tv_single_button_confirm);

    /* loaded from: classes.dex */
    public interface a {
    }

    public d(Context context) {
        super(context, h.TransparentDialogStyle);
        setContentView(e.common_tip_dialog);
        setCanceledOnTouchOutside(false);
        this.f1629b.setMovementMethod(new ScrollingMovementMethod());
        Window window = getWindow();
        window.setWindowAnimations(h.dialogWindowAnim);
        window.setType(TbsLog.TBSLOG_CODE_SDK_BASE);
        window.setFlags(1024, 1024);
        window.setLayout(-1, -1);
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        super.onBackPressed();
        dismiss();
    }
}
