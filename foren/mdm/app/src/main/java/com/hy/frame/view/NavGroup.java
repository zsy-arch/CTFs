package com.hy.frame.view;

import android.content.Context;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/* loaded from: classes2.dex */
public class NavGroup extends LinearLayout implements View.OnClickListener {
    private View.OnClickListener mChildOnClickListener;
    private OnCheckedChangeListener mOnCheckedChangeListener;
    private PassThroughHierarchyChangeListener mPassThroughListener;
    private int mCheckedId = -1;
    private boolean mProtectFromCheckedChange = false;
    private boolean canClickCurrent = false;

    /* loaded from: classes2.dex */
    public interface OnCheckedChangeListener {
        void onCheckedChanged(NavGroup navGroup, NavView navView, @IdRes int i);
    }

    public NavGroup(Context context) {
        super(context);
        setOrientation(1);
        init();
    }

    public NavGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        this.mPassThroughListener = new PassThroughHierarchyChangeListener();
        super.setOnHierarchyChangeListener(this.mPassThroughListener);
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener listener) {
        this.mPassThroughListener.mOnHierarchyChangeListener = listener;
    }

    public void setCanClickCurrent() {
        this.canClickCurrent = true;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (this.mCheckedId != -1) {
            this.mProtectFromCheckedChange = true;
            setCheckedStateForView(this.mCheckedId, true);
            this.mProtectFromCheckedChange = false;
            setCheckedId(this.mCheckedId);
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (child instanceof NavView) {
            NavView nav = (NavView) child;
            if (nav.isChecked()) {
                this.mProtectFromCheckedChange = true;
                if (this.mCheckedId != -1) {
                    setCheckedStateForView(this.mCheckedId, false);
                }
                this.mProtectFromCheckedChange = false;
                setCheckedId(nav.getId());
            }
        }
        super.addView(child, index, params);
    }

    public void check(@IdRes int id) {
        if (id == -1 || id != this.mCheckedId) {
            if (this.mCheckedId != -1) {
                setCheckedStateForView(this.mCheckedId, false);
            }
            if (id != -1) {
                setCheckedStateForView(id, true);
            }
            setCheckedId(id);
        }
    }

    private void setCheckedId(@IdRes int id) {
        this.mCheckedId = id;
        if (this.mOnCheckedChangeListener != null) {
            this.mOnCheckedChangeListener.onCheckedChanged(this, (NavView) findViewById(id), this.mCheckedId);
        }
    }

    private void setCheckedStateForView(int viewId, boolean checked) {
        View checkedView = findViewById(viewId);
        if (checkedView != null && (checkedView instanceof NavView)) {
            ((NavView) checkedView).setChecked(checked);
        }
    }

    @IdRes
    public int getCheckedNavViewId() {
        return this.mCheckedId;
    }

    public void clearCheck() {
        check(-1);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.mOnCheckedChangeListener = listener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class PassThroughHierarchyChangeListener implements ViewGroup.OnHierarchyChangeListener {
        private ViewGroup.OnHierarchyChangeListener mOnHierarchyChangeListener;

        private PassThroughHierarchyChangeListener() {
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewAdded(View parent, View child) {
            if (parent == NavGroup.this && (child instanceof NavView)) {
                if (child.getId() != -1) {
                }
                child.setOnClickListener(NavGroup.this);
            }
            if (this.mOnHierarchyChangeListener != null) {
                this.mOnHierarchyChangeListener.onChildViewAdded(parent, child);
            }
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewRemoved(View parent, View child) {
            if (parent == NavGroup.this && (child instanceof NavView)) {
                child.setOnClickListener(null);
            }
            if (this.mOnHierarchyChangeListener != null) {
                this.mOnHierarchyChangeListener.onChildViewRemoved(parent, child);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        onChildClick(v);
    }

    public void setCheckedChildByPosition(int position) {
        onChildClick(getChildAt(position));
    }

    public void setCheckedChildById(int id) {
        onChildClick(findViewById(id));
    }

    private void onChildClick(View v) {
        if (v.getId() != -1 && (v instanceof NavView)) {
            if (!((NavView) v).isChecked()) {
                int size = getChildCount();
                for (int i = 0; i < size; i++) {
                    View child = getChildAt(i);
                    if (v.getId() != child.getId() && (child instanceof NavView)) {
                        ((NavView) child).setChecked(false);
                    }
                }
                check(v.getId());
            } else if (this.canClickCurrent) {
                setCheckedId(this.mCheckedId);
            }
        }
    }
}
