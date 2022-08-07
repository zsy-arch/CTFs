package com.cdlinglu.utils.x5WebView;

import android.content.Context;
import android.view.View;
import android.widget.Toast;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes.dex */
public class LongPressListenerWrapper implements View.OnLongClickListener {
    private Context mContext;
    private WebView webview;

    public LongPressListenerWrapper(WebView webview, Context context) {
        this.webview = webview;
        this.mContext = context;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View v) {
        switch (this.webview.getHitTestResult().getType()) {
            case 0:
                return doUnknownAreaPressEvent();
            case 1:
            case 6:
                return doAnchorLongPressEvent();
            case 2:
            case 3:
            case 4:
            case 7:
            case 8:
            default:
                return false;
            case 5:
                return doImageLongPressEvent();
            case 9:
                return doTextLongPressEvent();
        }
    }

    private boolean doImageLongPressEvent() {
        Toast.makeText(this.mContext, "长按图片", 0).show();
        return true;
    }

    private boolean doEdiableAreaLongPressEvent() {
        Toast.makeText(this.mContext, "长按输入框", 0).show();
        return true;
    }

    private boolean doTextLongPressEvent() {
        return false;
    }

    private boolean doAnchorLongPressEvent() {
        Toast.makeText(this.mContext, "长按超链接", 0).show();
        return true;
    }

    private boolean doUnknownAreaPressEvent() {
        Toast.makeText(this.mContext, "空白区域", 0).show();
        return true;
    }
}
