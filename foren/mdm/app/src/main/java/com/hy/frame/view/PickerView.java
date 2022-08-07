package com.hy.frame.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.hy.frame.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes2.dex */
public class PickerView extends View {
    public static final float MARGIN_ALPHA = 2.8f;
    public static final float SPEED = 10.0f;
    private int mCurrentSelected;
    private List<String> mDataList;
    private float mLastDownY;
    private Paint mPaint;
    private onSelectListener mSelectListener;
    private MyTimerTask mTask;
    private int mViewHeight;
    private int mViewWidth;
    private Paint nPaint;
    private Timer timer;
    private boolean loop = true;
    private float mMaxTextSize = 80.0f;
    private float mMinTextSize = 40.0f;
    private float mMaxTextAlpha = 255.0f;
    private float mMinTextAlpha = 120.0f;
    private int mColorText = 3355443;
    private int nColorText = 6710886;
    private float mMoveLen = 0.0f;
    private boolean isInit = false;
    Handler updateHandler = new Handler() { // from class: com.hy.frame.view.PickerView.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (Math.abs(PickerView.this.mMoveLen) < 10.0f) {
                PickerView.this.mMoveLen = 0.0f;
                if (PickerView.this.mTask != null) {
                    PickerView.this.mTask.cancel();
                    PickerView.this.mTask = null;
                    PickerView.this.performSelect();
                }
            } else {
                PickerView.this.mMoveLen -= (PickerView.this.mMoveLen / Math.abs(PickerView.this.mMoveLen)) * 10.0f;
            }
            PickerView.this.invalidate();
        }
    };
    private boolean canScroll = true;

    /* loaded from: classes2.dex */
    public interface onSelectListener {
        void onSelect(String str);
    }

    public PickerView(Context context) {
        super(context);
        init();
    }

    public PickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setOnSelectListener(onSelectListener listener) {
        this.mSelectListener = listener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performSelect() {
        if (this.mSelectListener != null) {
            this.mSelectListener.onSelect(this.mDataList.get(this.mCurrentSelected));
        }
    }

    public void setData(List<String> datas) {
        this.mDataList = datas;
        this.mCurrentSelected = datas.size() / 4;
        invalidate();
    }

    public void setSelected(int selected) {
        this.mCurrentSelected = selected;
        if (this.loop) {
            int distance = (this.mDataList.size() / 2) - this.mCurrentSelected;
            if (distance < 0) {
                for (int i = 0; i < (-distance); i++) {
                    moveHeadToTail();
                    this.mCurrentSelected--;
                }
            } else if (distance > 0) {
                for (int i2 = 0; i2 < distance; i2++) {
                    moveTailToHead();
                    this.mCurrentSelected++;
                }
            }
        }
        invalidate();
    }

    public void setSelected(String mSelectItem) {
        for (int i = 0; i < this.mDataList.size(); i++) {
            if (this.mDataList.get(i).equals(mSelectItem)) {
                setSelected(i);
                return;
            }
        }
    }

    private void moveHeadToTail() {
        if (this.loop) {
            this.mDataList.remove(0);
            this.mDataList.add(this.mDataList.get(0));
        }
    }

    private void moveTailToHead() {
        if (this.loop) {
            this.mDataList.remove(this.mDataList.size() - 1);
            this.mDataList.add(0, this.mDataList.get(this.mDataList.size() - 1));
        }
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.mViewHeight = getMeasuredHeight();
        this.mViewWidth = getMeasuredWidth();
        this.mMaxTextSize = this.mViewHeight / 7.0f;
        this.mMinTextSize = this.mMaxTextSize / 2.2f;
        this.isInit = true;
        invalidate();
    }

    private void init() {
        this.timer = new Timer();
        this.mDataList = new ArrayList();
        this.mPaint = new Paint(1);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        this.mPaint.setColor(getResources().getColor(R.color.dimgray));
        this.nPaint = new Paint(1);
        this.nPaint.setStyle(Paint.Style.FILL);
        this.nPaint.setTextAlign(Paint.Align.CENTER);
        this.nPaint.setColor(this.mColorText);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.isInit) {
            drawData(canvas);
        }
    }

    private void drawData(Canvas canvas) {
        float scale = parabola(this.mViewHeight / 4.0f, this.mMoveLen);
        this.mPaint.setTextSize(((this.mMaxTextSize - this.mMinTextSize) * scale) + this.mMinTextSize);
        this.mPaint.setAlpha((int) (((this.mMaxTextAlpha - this.mMinTextAlpha) * scale) + this.mMinTextAlpha));
        float x = (float) (this.mViewWidth / 2.0d);
        Paint.FontMetricsInt fmi = this.mPaint.getFontMetricsInt();
        canvas.drawText(this.mDataList.get(this.mCurrentSelected), x, (float) (((float) ((this.mViewHeight / 2.0d) + this.mMoveLen)) - ((fmi.bottom / 2.0d) + (fmi.top / 2.0d))), this.mPaint);
        for (int i = 1; this.mCurrentSelected - i >= 0; i++) {
            drawOtherText(canvas, i, -1);
        }
        for (int i2 = 1; this.mCurrentSelected + i2 < this.mDataList.size(); i2++) {
            drawOtherText(canvas, i2, 1);
        }
    }

    private void drawOtherText(Canvas canvas, int position, int type) {
        float d = (2.8f * this.mMinTextSize * position) + (type * this.mMoveLen);
        float scale = parabola(this.mViewHeight / 4.0f, d);
        this.nPaint.setTextSize(((this.mMaxTextSize - this.mMinTextSize) * scale) + this.mMinTextSize);
        this.nPaint.setAlpha((int) (((this.mMaxTextAlpha - this.mMinTextAlpha) * scale) + this.mMinTextAlpha));
        float y = (float) ((this.mViewHeight / 2.0d) + (type * d));
        Paint.FontMetricsInt fmi = this.nPaint.getFontMetricsInt();
        canvas.drawText(this.mDataList.get(this.mCurrentSelected + (type * position)), (float) (this.mViewWidth / 2.0d), (float) (y - ((fmi.bottom / 2.0d) + (fmi.top / 2.0d))), this.nPaint);
    }

    private float parabola(float zero, float x) {
        float f = (float) (1.0d - Math.pow(x / zero, 2.0d));
        if (f < 0.0f) {
            return 0.0f;
        }
        return f;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case 0:
                doDown(event);
                break;
            case 1:
                doUp(event);
                break;
            case 2:
                this.mMoveLen += event.getY() - this.mLastDownY;
                if (this.mMoveLen > (this.mMinTextSize * 2.8f) / 2.0f) {
                    if (!this.loop && this.mCurrentSelected == 0) {
                        this.mLastDownY = event.getY();
                        invalidate();
                        break;
                    } else {
                        if (!this.loop) {
                            this.mCurrentSelected--;
                        }
                        moveTailToHead();
                        this.mMoveLen -= this.mMinTextSize * 2.8f;
                        this.mLastDownY = event.getY();
                        invalidate();
                        break;
                    }
                } else {
                    if (this.mMoveLen < ((-2.8f) * this.mMinTextSize) / 2.0f) {
                        if (this.mCurrentSelected == this.mDataList.size() - 1) {
                            this.mLastDownY = event.getY();
                            invalidate();
                            break;
                        } else {
                            if (!this.loop) {
                                this.mCurrentSelected++;
                            }
                            moveHeadToTail();
                            this.mMoveLen += this.mMinTextSize * 2.8f;
                        }
                    }
                    this.mLastDownY = event.getY();
                    invalidate();
                }
                break;
        }
        return true;
    }

    private void doDown(MotionEvent event) {
        if (this.mTask != null) {
            this.mTask.cancel();
            this.mTask = null;
        }
        this.mLastDownY = event.getY();
    }

    private void doUp(MotionEvent event) {
        if (Math.abs(this.mMoveLen) < 1.0E-4d) {
            this.mMoveLen = 0.0f;
            return;
        }
        if (this.mTask != null) {
            this.mTask.cancel();
            this.mTask = null;
        }
        this.mTask = new MyTimerTask(this.updateHandler);
        this.timer.schedule(this.mTask, 0L, 10L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class MyTimerTask extends TimerTask {
        Handler handler;

        public MyTimerTask(Handler handler) {
            this.handler = handler;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            this.handler.sendMessage(this.handler.obtainMessage());
        }
    }

    public void setCanScroll(boolean canScroll) {
        this.canScroll = canScroll;
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (this.canScroll) {
            return super.dispatchTouchEvent(event);
        }
        return false;
    }

    public void setIsLoop(boolean isLoop) {
        this.loop = isLoop;
    }
}
