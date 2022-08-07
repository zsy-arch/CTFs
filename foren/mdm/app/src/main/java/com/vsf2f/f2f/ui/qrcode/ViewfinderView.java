package com.vsf2f.f2f.ui.qrcode;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.google.zxing.ResultPoint;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.qrcode.camera.CameraManager;
import java.util.Collection;
import java.util.HashSet;

/* loaded from: classes2.dex */
public final class ViewfinderView extends View {
    private static final long ANIMATION_DELAY = 100;
    private static final int OPAQUE = 255;
    private static final int[] SCANNER_ALPHA = {0, 64, 128, 192, 255, 192, 128, 64};
    private Collection<ResultPoint> lastPossibleResultPoints;
    private Bitmap resultBitmap;
    private final Paint framePaint = new Paint();
    private final Paint borderPaint = new Paint();
    private final Paint sightPaint = new Paint();
    private final Paint flickerPaint = new Paint();
    private int scannerAlpha = 0;
    private Collection<ResultPoint> possibleResultPoints = new HashSet(5);

    public ViewfinderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources resources = getResources();
        this.framePaint.setColor(resources.getColor(R.color.viewfinder_frame));
        this.borderPaint.setColor(resources.getColor(R.color.viewfinder_border));
        this.sightPaint.setColor(resources.getColor(R.color.viewfinder_sight));
        this.flickerPaint.setColor(resources.getColor(R.color.viewfinder_flicker));
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        Rect frame;
        CameraManager cm = CameraManager.get();
        if (!(cm == null || (frame = cm.getFramingRect()) == null)) {
            int width = canvas.getWidth();
            int height = canvas.getHeight();
            canvas.drawRect(0.0f, 0.0f, width, frame.top, this.framePaint);
            canvas.drawRect(0.0f, frame.top, frame.left, frame.bottom + 1, this.framePaint);
            canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1, this.framePaint);
            canvas.drawRect(0.0f, frame.bottom + 1, width, height, this.framePaint);
            if (this.resultBitmap != null) {
                canvas.drawBitmap(Bitmap.createScaledBitmap(this.resultBitmap, frame.width(), frame.height(), false), frame.left, frame.top, this.borderPaint);
                return;
            }
            canvas.drawRect(frame.left - 2, frame.top - 2, frame.left + 80, frame.top + 2, this.borderPaint);
            canvas.drawRect(frame.left - 2, frame.top - 2, frame.left + 2, frame.top + 80, this.borderPaint);
            canvas.drawRect(frame.right - 80, frame.top - 2, frame.right + 2, frame.top + 2, this.borderPaint);
            canvas.drawRect(frame.right - 2, frame.top - 2, frame.right + 2, frame.top + 80, this.borderPaint);
            canvas.drawRect(frame.left - 2, frame.bottom - 2, frame.left + 80, frame.bottom + 2, this.borderPaint);
            canvas.drawRect(frame.left - 2, frame.bottom - 80, frame.left + 2, frame.bottom + 2, this.borderPaint);
            canvas.drawRect(frame.right - 80, frame.bottom - 2, frame.right + 2, frame.bottom + 2, this.borderPaint);
            canvas.drawRect(frame.right - 2, frame.bottom - 80, frame.right + 2, frame.bottom + 2, this.borderPaint);
            this.sightPaint.setAlpha(SCANNER_ALPHA[this.scannerAlpha]);
            this.scannerAlpha = (this.scannerAlpha + 1) % SCANNER_ALPHA.length;
            int middle_v = (frame.height() / 2) + frame.top;
            int middle_h = (frame.width() / 2) + frame.left;
            canvas.drawRect(middle_h - 100, middle_v - 1, middle_h + 100, middle_v + 1, this.sightPaint);
            canvas.drawRect(middle_h - 1, middle_v - 100, middle_h + 1, middle_v + 100, this.sightPaint);
            Collection<ResultPoint> currentPossible = this.possibleResultPoints;
            Collection<ResultPoint> currentLast = this.lastPossibleResultPoints;
            if (currentPossible.isEmpty()) {
                this.lastPossibleResultPoints = null;
            } else {
                this.possibleResultPoints = new HashSet(5);
                this.lastPossibleResultPoints = currentPossible;
                this.flickerPaint.setAlpha(255);
                for (ResultPoint point : currentPossible) {
                    canvas.drawCircle(frame.left + point.getX(), frame.top + point.getY(), 6.0f, this.flickerPaint);
                }
            }
            if (currentLast != null) {
                this.flickerPaint.setAlpha(127);
                for (ResultPoint point2 : currentLast) {
                    canvas.drawCircle(frame.left + point2.getX(), frame.top + point2.getY(), 3.0f, this.flickerPaint);
                }
            }
            postInvalidateDelayed(ANIMATION_DELAY, frame.left, frame.top, frame.right, frame.bottom);
        }
    }

    public void drawViewfinder() {
        this.resultBitmap = null;
        invalidate();
    }

    public void drawResultBitmap(Bitmap barcode) {
        this.resultBitmap = barcode;
        invalidate();
    }

    public void addPossibleResultPoint(ResultPoint point) {
        this.possibleResultPoints.add(point);
    }
}
