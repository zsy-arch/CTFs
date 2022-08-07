package com.cdlinglu.utils.x5WebView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class X5PopMenu extends LinearLayout {
    private static final float SCALE_HEIGHT = 0.1f;
    private static final float SCALE_WIDTH = 0.3f;
    private static ArrayList<Button> buttons;
    private static Dialog dialog;
    private static Context mContext;
    private static ViewGroup parentView;
    private static X5PopMenu popMenu;

    private X5PopMenu(Context context) {
        this(context, null);
    }

    private X5PopMenu(Context context, AttributeSet attr) {
        super(context, attr);
        mContext = context;
    }

    public static X5PopMenu createInstance(Context context) {
        mContext = context;
        if (popMenu == null) {
            popMenu = new X5PopMenu(mContext);
            popMenu.setOrientation(1);
        }
        return popMenu;
    }

    public static void showInParent(ViewGroup parentView2, final int posX, final int poxY) {
        if (popMenu != null && parentView2 != null) {
            popMenu.initView();
            dialog = new Dialog(mContext) { // from class: com.cdlinglu.utils.x5WebView.X5PopMenu.1
                @Override // android.app.Dialog
                protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(X5PopMenu.popMenu);
                    WindowManager.LayoutParams params = getWindow().getAttributes();
                    params.x = posX;
                    params.y = poxY;
                    getWindow().setAttributes(params);
                    getWindow().setLayout(-2, -2);
                }
            };
            dialog.show();
        }
    }

    private synchronized void initView() {
        if (mContext != null) {
            WindowManager windowManager = (WindowManager) mContext.getSystemService("window");
            int deviceWidth = windowManager.getDefaultDisplay().getWidth();
            int deviceHeight = windowManager.getDefaultDisplay().getHeight();
            if (buttons != null) {
                int size = buttons.size();
                int btnWidth = (int) (deviceWidth * SCALE_WIDTH);
                int btnHeight = (int) (deviceHeight * SCALE_HEIGHT);
                for (int i = 0; i < size; i++) {
                    Button button = buttons.get(i);
                    button.setBackgroundColor(-16777216);
                    button.setTextColor(-1);
                    button.setAlpha(0.5f);
                    button.setTextSize(10.0f);
                    button.setWidth(btnWidth);
                    button.setHeight(btnHeight);
                    addView(button);
                }
            }
        }
    }

    public void createNewButton(String btnName, View.OnClickListener listener) {
        if (buttons == null) {
            buttons = new ArrayList<>();
        }
        Button button = new Button(mContext);
        button.setText(btnName);
        button.setOnClickListener(listener);
        buttons.add(button);
    }

    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
