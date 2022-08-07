package com.easeui.widget.emojicon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.hyphenate.util.DensityUtil;
import com.vsf2f.f2f.R;
import java.util.ArrayList;
import java.util.List;

@SuppressLint({"NewApi"})
/* loaded from: classes.dex */
public class EaseEmojiconIndicatorView extends LinearLayout {
    private Context context;
    private int dotHeight;
    private List<ImageView> dotViews;
    private Bitmap selectedBitmap;
    private Bitmap unselectedBitmap;

    public EaseEmojiconIndicatorView(Context context, AttributeSet attrs, int defStyle) {
        this(context, null);
    }

    public EaseEmojiconIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.dotHeight = 12;
        init(context, attrs);
    }

    public EaseEmojiconIndicatorView(Context context) {
        this(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        this.dotHeight = DensityUtil.dip2px(context, this.dotHeight);
        this.selectedBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ease_dot_emojicon_selected);
        this.unselectedBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ease_dot_emojicon_unselected);
        setGravity(1);
    }

    public void init(int count) {
        this.dotViews = new ArrayList();
        for (int i = 0; i < count; i++) {
            RelativeLayout rl = new RelativeLayout(this.context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(this.dotHeight, this.dotHeight);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(13);
            ImageView imageView = new ImageView(this.context);
            if (i == 0) {
                imageView.setImageBitmap(this.selectedBitmap);
                rl.addView(imageView, layoutParams);
            } else {
                imageView.setImageBitmap(this.unselectedBitmap);
                rl.addView(imageView, layoutParams);
            }
            addView(rl, params);
            this.dotViews.add(imageView);
        }
    }

    public void updateIndicator(int count) {
        if (this.dotViews != null) {
            for (int i = 0; i < this.dotViews.size(); i++) {
                if (i >= count) {
                    this.dotViews.get(i).setVisibility(8);
                    ((View) this.dotViews.get(i).getParent()).setVisibility(8);
                } else {
                    this.dotViews.get(i).setVisibility(0);
                    ((View) this.dotViews.get(i).getParent()).setVisibility(0);
                }
            }
            if (count > this.dotViews.size()) {
                int diff = count - this.dotViews.size();
                for (int i2 = 0; i2 < diff; i2++) {
                    RelativeLayout rl = new RelativeLayout(this.context);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(this.dotHeight, this.dotHeight);
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                    layoutParams.addRule(13);
                    ImageView imageView = new ImageView(this.context);
                    imageView.setImageBitmap(this.unselectedBitmap);
                    rl.addView(imageView, layoutParams);
                    rl.setVisibility(8);
                    imageView.setVisibility(8);
                    addView(rl, params);
                    this.dotViews.add(imageView);
                }
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.selectedBitmap != null) {
            this.selectedBitmap.recycle();
        }
        if (this.unselectedBitmap != null) {
            this.unselectedBitmap.recycle();
        }
    }

    public void selectTo(int position) {
        for (ImageView iv : this.dotViews) {
            iv.setImageBitmap(this.unselectedBitmap);
        }
        this.dotViews.get(position).setImageBitmap(this.selectedBitmap);
    }

    public void selectTo(int startPosition, int targetPostion) {
        this.dotViews.get(startPosition).setImageBitmap(this.unselectedBitmap);
        this.dotViews.get(targetPostion).setImageBitmap(this.selectedBitmap);
    }
}
