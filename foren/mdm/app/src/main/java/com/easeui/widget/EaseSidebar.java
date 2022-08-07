package com.easeui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.hyphenate.util.DensityUtil;
import com.vsf2f.f2f.R;

/* loaded from: classes.dex */
public class EaseSidebar extends View {
    private Context context;
    private TextView header;
    private float height;
    private ListView mListView;
    private Paint paint;
    private SectionIndexer sectionIndex = null;
    private String[] sections;

    public void setListView(ListView listView) {
        this.mListView = listView;
    }

    public EaseSidebar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        this.sections = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
        this.paint = new Paint(1);
        this.paint.setColor(Color.parseColor("#8C8C8C"));
        this.paint.setTextAlign(Paint.Align.CENTER);
        this.paint.setTextSize(DensityUtil.sp2px(this.context, 14.0f));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float center = getWidth() / 2;
        this.height = getHeight() / this.sections.length;
        for (int i = this.sections.length - 1; i > -1; i--) {
            canvas.drawText(this.sections[i], center, this.height * (i + 1), this.paint);
        }
    }

    private int sectionForPoint(float y) {
        int index = (int) (y / this.height);
        if (index < 0) {
            index = 0;
        }
        if (index > this.sections.length - 1) {
            return this.sections.length - 1;
        }
        return index;
    }

    private void setHeaderTextAndscroll(MotionEvent event) {
        if (this.mListView != null) {
            String headerString = this.sections[sectionForPoint(event.getY())];
            this.header.setText(headerString);
            ListAdapter adapter = this.mListView.getAdapter();
            if (this.sectionIndex == null) {
                if (adapter instanceof HeaderViewListAdapter) {
                    this.sectionIndex = (SectionIndexer) ((HeaderViewListAdapter) adapter).getWrappedAdapter();
                } else if (adapter instanceof SectionIndexer) {
                    this.sectionIndex = (SectionIndexer) adapter;
                } else {
                    throw new RuntimeException("listview sets adapter does not implement SectionIndexer interface");
                }
            }
            String[] adapterSections = (String[]) this.sectionIndex.getSections();
            try {
                for (int i = adapterSections.length - 1; i > -1; i--) {
                    if (adapterSections[i].equals(headerString)) {
                        this.mListView.setSelection(this.sectionIndex.getPositionForSection(i));
                        return;
                    }
                }
            } catch (Exception e) {
                Log.e("setHeaderTextAndScroll", e.getMessage());
            }
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case 0:
                if (this.header == null) {
                    this.header = (TextView) ((View) getParent()).findViewById(R.id.floating_header);
                }
                setHeaderTextAndscroll(event);
                this.header.setVisibility(0);
                setBackgroundResource(R.drawable.ease_sidebar_background_pressed);
                return true;
            case 1:
                this.header.setVisibility(4);
                setBackgroundColor(0);
                return true;
            case 2:
                setHeaderTextAndscroll(event);
                return true;
            case 3:
                this.header.setVisibility(4);
                setBackgroundColor(0);
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }
}
