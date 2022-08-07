package com.vsf2f.f2f.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes2.dex */
public class TribeAvatar extends ViewGroup {
    int column;
    int row;

    public TribeAvatar(Context context, AttributeSet attrs) throws Exception {
        this(context, attrs, 0);
    }

    public TribeAvatar(Context context, AttributeSet attrs, int defStyleAttr) throws Exception {
        super(context, attrs, defStyleAttr);
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, widthMeasureSpec);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        if (childCount >= 5) {
            putImg(childCount, 3);
        } else {
            putImg(childCount, 2);
        }
    }

    private int setVertical(int childCount, int imgWidth) {
        if (childCount == 5 || childCount == 6) {
            return imgWidth / 2;
        }
        return 0;
    }

    private void setThreePicPosition(int i, int imgWidth, int left, int top, int right, int bottom) {
        View view = getChildAt(i - 1);
        if (i == 1) {
            view.layout(imgWidth / 2, 0, (imgWidth / 2) + imgWidth, imgWidth);
            this.row++;
            this.column = 0;
            return;
        }
        view.layout(left, top, right, bottom);
        this.column++;
    }

    private void setFivePicPosition(int i, int imgWidth, int childCount, int left, int top, int right, int bottom) {
        View view = getChildAt(i - 1);
        int centerVertical = setVertical(childCount, imgWidth);
        if (i == 1 || i == 2) {
            int centerHorizontal = imgWidth / 2;
            view.layout(left + centerHorizontal, top + centerVertical, right + centerHorizontal, bottom + centerVertical);
            this.column++;
            if (i == 2) {
                this.row++;
                this.column = 0;
                return;
            }
            return;
        }
        view.layout(left, top + centerVertical, right, bottom + centerVertical);
        this.column++;
    }

    private void setOthersPicPosition(int i, int imgWidth, int columnMax, int childCount, int left, int top, int right, int bottom) {
        View view = getChildAt(i - 1);
        int centerVertical = setVertical(childCount, imgWidth);
        view.layout(left, top + centerVertical, right, bottom + centerVertical);
        this.column++;
        if (i % columnMax == 0) {
            this.row++;
            this.column = 0;
        }
    }

    private void putImg(int childCount, int columnMax) {
        int imgWidth = getWidth() / columnMax;
        this.row = 0;
        this.column = 0;
        for (int i = 1; i <= childCount; i++) {
            int left = imgWidth * this.column;
            int top = imgWidth * this.row;
            int right = imgWidth + left;
            int bottom = imgWidth + top;
            switch (childCount) {
                case 3:
                    setThreePicPosition(i, imgWidth, left, top, right, bottom);
                    break;
                case 4:
                default:
                    setOthersPicPosition(i, imgWidth, columnMax, childCount, left, top, right, bottom);
                    if (childCount == 7 && i == 7) {
                        getChildAt(i - 1).layout(imgWidth, imgWidth * 2, imgWidth * 2, (imgWidth * 2) + imgWidth);
                        break;
                    }
                    break;
                case 5:
                    setFivePicPosition(i, imgWidth, childCount, left, top, right, bottom);
                    break;
            }
        }
    }
}
