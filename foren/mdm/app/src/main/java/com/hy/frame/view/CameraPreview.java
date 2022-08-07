package com.hy.frame.view;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.hy.frame.util.MyLog;
import java.io.IOException;
import java.util.List;

/* loaded from: classes2.dex */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    private Camera mCamera;
    private SurfaceHolder mHolder;
    private Camera.Size mPreviewSize;
    private List<Camera.Size> mSupportedPreviewSizes;

    public CameraPreview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CameraPreview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CameraPreview(Context context) {
        super(context);
        init();
    }

    private void init() {
        MyLog.d(getClass(), "initialize");
        this.mHolder = getHolder();
        this.mHolder.addCallback(this);
        this.mHolder.setType(3);
    }

    public void setCamera(Camera camera) {
        this.mCamera = camera;
        if (this.mCamera != null) {
            this.mSupportedPreviewSizes = this.mCamera.getParameters().getSupportedPreviewSizes();
            requestLayout();
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder holder) {
        MyLog.d(getClass(), "surfaceCreated");
        try {
            if (this.mCamera != null) {
                this.mCamera.setPreviewDisplay(holder);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
            MyLog.d(getClass(), "Error setting camera preview display: " + e1.getMessage());
        }
        try {
            if (this.mCamera != null) {
                this.mCamera.startPreview();
            }
            MyLog.d(getClass(), "surfaceCreated successfully! ");
        } catch (Exception e) {
            MyLog.d(getClass(), "Error setting camera preview: " + e.getMessage());
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        MyLog.d(getClass(), "surface changed");
        if (this.mHolder.getSurface() != null) {
            try {
                if (this.mCamera != null) {
                    this.mCamera.stopPreview();
                }
            } catch (Exception e) {
            }
            if (this.mCamera != null) {
                Camera.Parameters parameters = this.mCamera.getParameters();
                parameters.setPictureFormat(256);
                parameters.setPreviewSize(this.mPreviewSize.width, this.mPreviewSize.height);
                requestLayout();
                this.mCamera.setParameters(parameters);
                this.mCamera.setDisplayOrientation(90);
                MyLog.d(getClass(), "camera set parameters successfully!: " + parameters);
            }
            try {
                if (this.mCamera != null) {
                    this.mCamera.setPreviewDisplay(this.mHolder);
                    this.mCamera.startPreview();
                }
            } catch (Exception e2) {
                MyLog.d(getClass(), "Error starting camera preview: " + e2.getMessage());
            }
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder holder) {
        MyLog.d(getClass(), "surfaceDestroyed");
        if (this.mCamera != null) {
            this.mCamera.stopPreview();
            this.mCamera.release();
        }
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = resolveSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        int height = resolveSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);
        if (this.mSupportedPreviewSizes != null) {
            this.mPreviewSize = getOptimalPreviewSize(this.mSupportedPreviewSizes, width, height);
        }
    }

    private Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h) {
        double targetRatio = w / h;
        if (sizes == null) {
            return null;
        }
        Camera.Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;
        for (Camera.Size size : sizes) {
            if (Math.abs((size.width / size.height) - targetRatio) <= 0.1d && Math.abs(size.height - h) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - h);
            }
        }
        if (optimalSize != null) {
            return optimalSize;
        }
        double minDiff2 = Double.MAX_VALUE;
        for (Camera.Size size2 : sizes) {
            if (Math.abs(size2.height - h) < minDiff2) {
                optimalSize = size2;
                minDiff2 = Math.abs(size2.height - h);
            }
        }
        return optimalSize;
    }
}
