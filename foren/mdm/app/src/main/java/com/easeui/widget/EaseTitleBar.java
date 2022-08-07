package com.easeui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.vsf2f.f2f.R;

/* loaded from: classes.dex */
public class EaseTitleBar extends RelativeLayout {
    protected ImageView leftImage;
    protected RelativeLayout leftLayout;
    protected ImageView rightImage;
    protected RelativeLayout rightLayout;
    protected RelativeLayout titleLayout;
    protected TextView titleView;

    public EaseTitleBar(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    public EaseTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public EaseTitleBar(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.ease_widget_title_bar, this);
        this.leftLayout = (RelativeLayout) findViewById(R.id.left_layout);
        this.leftImage = (ImageView) findViewById(R.id.left_image);
        this.rightLayout = (RelativeLayout) findViewById(R.id.right_layout);
        this.rightImage = (ImageView) findViewById(R.id.right_image);
        this.titleView = (TextView) findViewById(R.id.title);
        this.titleLayout = (RelativeLayout) findViewById(R.id.root);
        parseStyle(context, attrs);
    }

    private void parseStyle(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EaseTitleBar);
            this.titleView.setText(ta.getString(0));
            Drawable leftDrawable = ta.getDrawable(1);
            if (leftDrawable != null) {
                this.leftImage.setImageDrawable(leftDrawable);
            }
            Drawable rightDrawable = ta.getDrawable(2);
            if (rightDrawable != null) {
                this.rightImage.setImageDrawable(rightDrawable);
            }
            Drawable background = ta.getDrawable(3);
            if (background != null) {
                this.titleLayout.setBackgroundDrawable(background);
            }
            ta.recycle();
        }
    }

    public void setLeftImageResource(int resId) {
        this.leftImage.setImageResource(resId);
    }

    public void setRightImageResource(int resId) {
        this.rightImage.setImageResource(resId);
    }

    public void setLeftLayoutClickListener(View.OnClickListener listener) {
        this.leftLayout.setOnClickListener(listener);
    }

    public void setRightLayoutClickListener(View.OnClickListener listener) {
        this.rightLayout.setOnClickListener(listener);
    }

    public void setLeftLayoutVisibility(int visibility) {
        this.leftLayout.setVisibility(visibility);
    }

    public void setRightLayoutVisibility(int visibility) {
        this.rightLayout.setVisibility(visibility);
    }

    public void setTitle(String title) {
        this.titleView.setText(title);
    }

    @Override // android.view.View
    public void setBackgroundColor(int color) {
        this.titleLayout.setBackgroundColor(color);
    }

    public RelativeLayout getLeftLayout() {
        return this.leftLayout;
    }

    public RelativeLayout getRightLayout() {
        return this.rightLayout;
    }
}
