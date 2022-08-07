package com.superrtc.call;

import android.graphics.Point;
import android.opengl.GLES20;
import android.opengl.Matrix;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public class RendererCommon {
    private static /* synthetic */ int[] $SWITCH_TABLE$com$superrtc$call$RendererCommon$ScalingType;
    private static float BALANCED_VISIBLE_FRACTION = 0.5625f;

    /* loaded from: classes2.dex */
    public interface GlDrawer {
        void drawOes(int i, float[] fArr, int i2, int i3, int i4, int i5);

        void drawRgb(int i, float[] fArr, int i2, int i3, int i4, int i5);

        void drawYuv(int[] iArr, float[] fArr, int i, int i2, int i3, int i4);

        void release();
    }

    /* loaded from: classes2.dex */
    public interface RendererEvents {
        void onFirstFrameRendered();

        void onFrameResolutionChanged(int i, int i2, int i3);
    }

    /* loaded from: classes2.dex */
    public enum ScalingType {
        SCALE_ASPECT_FIT,
        SCALE_ASPECT_FILL,
        SCALE_ASPECT_BALANCED
    }

    static /* synthetic */ int[] $SWITCH_TABLE$com$superrtc$call$RendererCommon$ScalingType() {
        int[] iArr = $SWITCH_TABLE$com$superrtc$call$RendererCommon$ScalingType;
        if (iArr == null) {
            iArr = new int[ScalingType.values().length];
            try {
                iArr[ScalingType.SCALE_ASPECT_BALANCED.ordinal()] = 3;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ScalingType.SCALE_ASPECT_FILL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ScalingType.SCALE_ASPECT_FIT.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            $SWITCH_TABLE$com$superrtc$call$RendererCommon$ScalingType = iArr;
        }
        return iArr;
    }

    /* loaded from: classes2.dex */
    public static class YuvUploader {
        private ByteBuffer copyBuffer;

        public void uploadYuvData(int[] outputYuvTextures, int width, int height, int[] strides, ByteBuffer[] planes) {
            ByteBuffer packedByteBuffer;
            int[] planeWidths = {width, width / 2, width / 2};
            int[] planeHeights = {height, height / 2, height / 2};
            int copyCapacityNeeded = 0;
            for (int i = 0; i < 3; i++) {
                if (strides[i] > planeWidths[i]) {
                    copyCapacityNeeded = Math.max(copyCapacityNeeded, planeWidths[i] * planeHeights[i]);
                }
            }
            if (copyCapacityNeeded > 0 && (this.copyBuffer == null || this.copyBuffer.capacity() < copyCapacityNeeded)) {
                this.copyBuffer = ByteBuffer.allocateDirect(copyCapacityNeeded);
            }
            for (int i2 = 0; i2 < 3; i2++) {
                GLES20.glActiveTexture(33984 + i2);
                GLES20.glBindTexture(3553, outputYuvTextures[i2]);
                if (strides[i2] == planeWidths[i2]) {
                    packedByteBuffer = planes[i2];
                } else {
                    VideoRenderer.nativeCopyPlane(planes[i2], planeWidths[i2], planeHeights[i2], strides[i2], this.copyBuffer, planeWidths[i2]);
                    packedByteBuffer = this.copyBuffer;
                }
                GLES20.glTexImage2D(3553, 0, 6409, planeWidths[i2], planeHeights[i2], 0, 6409, 5121, packedByteBuffer);
            }
        }
    }

    public static final float[] identityMatrix() {
        return new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    }

    public static final float[] verticalFlipMatrix() {
        return new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f};
    }

    public static final float[] horizontalFlipMatrix() {
        return new float[]{-1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f};
    }

    public static float[] rotateTextureMatrix(float[] textureMatrix, float rotationDegree) {
        float[] rotationMatrix = new float[16];
        Matrix.setRotateM(rotationMatrix, 0, rotationDegree, 0.0f, 0.0f, 1.0f);
        adjustOrigin(rotationMatrix);
        return multiplyMatrices(textureMatrix, rotationMatrix);
    }

    public static float[] multiplyMatrices(float[] a, float[] b) {
        float[] resultMatrix = new float[16];
        Matrix.multiplyMM(resultMatrix, 0, a, 0, b, 0);
        return resultMatrix;
    }

    public static float[] getLayoutMatrix(boolean mirror, float videoAspectRatio, float displayAspectRatio) {
        float scaleX = 1.0f;
        float scaleY = 1.0f;
        if (displayAspectRatio > videoAspectRatio) {
            scaleY = videoAspectRatio / displayAspectRatio;
        } else {
            scaleX = displayAspectRatio / videoAspectRatio;
        }
        if (mirror) {
            scaleX *= -1.0f;
        }
        float[] matrix = new float[16];
        Matrix.setIdentityM(matrix, 0);
        Matrix.scaleM(matrix, 0, scaleX, scaleY, 1.0f);
        adjustOrigin(matrix);
        return matrix;
    }

    public static Point getDisplaySize(ScalingType scalingType, float videoAspectRatio, int maxDisplayWidth, int maxDisplayHeight) {
        return getDisplaySize(convertScalingTypeToVisibleFraction(scalingType), videoAspectRatio, maxDisplayWidth, maxDisplayHeight);
    }

    private static void adjustOrigin(float[] matrix) {
        matrix[12] = matrix[12] - ((matrix[0] + matrix[4]) * 0.5f);
        matrix[13] = matrix[13] - ((matrix[1] + matrix[5]) * 0.5f);
        matrix[12] = matrix[12] + 0.5f;
        matrix[13] = matrix[13] + 0.5f;
    }

    private static float convertScalingTypeToVisibleFraction(ScalingType scalingType) {
        switch ($SWITCH_TABLE$com$superrtc$call$RendererCommon$ScalingType()[scalingType.ordinal()]) {
            case 1:
                return 1.0f;
            case 2:
                return 0.0f;
            case 3:
                return BALANCED_VISIBLE_FRACTION;
            default:
                throw new IllegalArgumentException();
        }
    }

    private static Point getDisplaySize(float minVisibleFraction, float videoAspectRatio, int maxDisplayWidth, int maxDisplayHeight) {
        if (minVisibleFraction == 0.0f || videoAspectRatio == 0.0f) {
            return new Point(maxDisplayWidth, maxDisplayHeight);
        }
        return new Point(Math.min(maxDisplayWidth, Math.round((maxDisplayHeight / minVisibleFraction) * videoAspectRatio)), Math.min(maxDisplayHeight, Math.round((maxDisplayWidth / minVisibleFraction) / videoAspectRatio)));
    }
}
