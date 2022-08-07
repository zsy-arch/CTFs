package com.vsf2f.f2f.ui.otay;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.View;
import com.vsf2f.f2f.R;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class FlakeView extends View {
    private long prevTime;
    private long startTime;
    private boolean stop;
    private int numFlakes = 0;
    private int frames = 0;
    private float fps = 0.0f;
    private String fpsString = "";
    private String numFlakesString = "";
    private Matrix m = new Matrix();
    private ArrayList<Flake> flakes = new ArrayList<>();
    public ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
    private Paint textPaint = new Paint(1);

    static /* synthetic */ int access$110(FlakeView x0) {
        int i = x0.numFlakes;
        x0.numFlakes = i - 1;
        return i;
    }

    public FlakeView(Context context) {
        super(context);
        this.textPaint.setColor(-1);
        this.textPaint.setTextSize(24.0f);
        this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.vsf2f.f2f.ui.otay.FlakeView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator arg0) {
                long nowTime = System.currentTimeMillis();
                float secs = ((float) (nowTime - FlakeView.this.prevTime)) / 300.0f;
                FlakeView.this.prevTime = nowTime;
                for (int i = 0; i < FlakeView.this.numFlakes; i++) {
                    Flake flake = (Flake) FlakeView.this.flakes.get(i);
                    flake.y += flake.speed * secs;
                    if (flake.y > FlakeView.this.getHeight()) {
                        if (FlakeView.this.stop) {
                            FlakeView.this.flakes.remove(flake);
                            FlakeView.access$110(FlakeView.this);
                        } else {
                            flake.y = 0 - flake.height;
                        }
                    }
                    flake.rotation += flake.rotationSpeed * secs;
                }
                FlakeView.this.invalidate();
            }
        });
        this.animator.setRepeatCount(-1);
        this.animator.setDuration(1000L);
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    private void setNumFlakes(int quantity) {
        this.numFlakes = quantity;
        this.numFlakesString = "numFlakes: " + this.numFlakes;
    }

    private int getNumFlakes() {
        return this.numFlakes;
    }

    public void addFlakes(int quantity) {
        Bitmap body = BitmapFactory.decodeResource(getResources(), R.drawable.icon_otay3);
        for (int i = 0; i < quantity; i++) {
            this.flakes.add(Flake.createFlake(getWidth(), body, getContext()));
        }
        setNumFlakes(this.numFlakes + quantity);
    }

    public void subtractFlakes(int quantity) {
        for (int i = 0; i < quantity; i++) {
            this.flakes.remove((this.numFlakes - i) - 1);
        }
        setNumFlakes(this.numFlakes - quantity);
    }

    public void removeFlakes() {
        subtractFlakes(getNumFlakes());
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.flakes.clear();
        this.numFlakes = 0;
        addFlakes(16);
        this.animator.cancel();
        this.startTime = System.currentTimeMillis();
        this.prevTime = this.startTime;
        this.frames = 0;
        this.animator.start();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < this.numFlakes; i++) {
            Flake flake = this.flakes.get(i);
            this.m.setTranslate((-flake.width) / 2, (-flake.height) / 2);
            this.m.postRotate(flake.rotation);
            this.m.postTranslate((flake.width / 2) + flake.x, (flake.height / 2) + flake.y);
            canvas.drawBitmap(flake.bitmap, this.m, null);
        }
        this.frames++;
        long nowTime = System.currentTimeMillis();
        long deltaTime = nowTime - this.startTime;
        if (deltaTime > 1000) {
            this.fps = this.frames / (((float) deltaTime) / 1000.0f);
            this.startTime = nowTime;
            this.frames = 0;
        }
    }

    public void pause() {
        this.animator.cancel();
    }

    public void resume() {
        this.animator.start();
    }
}
