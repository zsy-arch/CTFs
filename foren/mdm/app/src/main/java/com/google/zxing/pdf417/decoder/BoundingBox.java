package com.google.zxing.pdf417.decoder;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class BoundingBox {
    private ResultPoint bottomLeft;
    private ResultPoint bottomRight;
    private BitMatrix image;
    private int maxX;
    private int maxY;
    private int minX;
    private int minY;
    private ResultPoint topLeft;
    private ResultPoint topRight;

    public BoundingBox(BitMatrix image, ResultPoint topLeft, ResultPoint bottomLeft, ResultPoint topRight, ResultPoint bottomRight) throws NotFoundException {
        if (!(topLeft == null && topRight == null) && (!(bottomLeft == null && bottomRight == null) && ((topLeft == null || bottomLeft != null) && (topRight == null || bottomRight != null)))) {
            init(image, topLeft, bottomLeft, topRight, bottomRight);
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public BoundingBox(BoundingBox boundingBox) {
        init(boundingBox.image, boundingBox.topLeft, boundingBox.bottomLeft, boundingBox.topRight, boundingBox.bottomRight);
    }

    private void init(BitMatrix image, ResultPoint topLeft, ResultPoint bottomLeft, ResultPoint topRight, ResultPoint bottomRight) {
        this.image = image;
        this.topLeft = topLeft;
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
        this.bottomRight = bottomRight;
        calculateMinMaxValues();
    }

    public static BoundingBox merge(BoundingBox leftBox, BoundingBox rightBox) throws NotFoundException {
        if (leftBox == null) {
            return rightBox;
        }
        return rightBox == null ? leftBox : new BoundingBox(leftBox.image, leftBox.topLeft, leftBox.bottomLeft, rightBox.topRight, rightBox.bottomRight);
    }

    public BoundingBox addMissingRows(int missingStartRows, int missingEndRows, boolean isLeft) throws NotFoundException {
        ResultPoint newTopLeft = this.topLeft;
        ResultPoint newBottomLeft = this.bottomLeft;
        ResultPoint newTopRight = this.topRight;
        ResultPoint newBottomRight = this.bottomRight;
        if (missingStartRows > 0) {
            ResultPoint top = isLeft ? this.topLeft : this.topRight;
            int newMinY = ((int) top.getY()) - missingStartRows;
            if (newMinY < 0) {
                newMinY = 0;
            }
            ResultPoint newTop = new ResultPoint(top.getX(), newMinY);
            if (isLeft) {
                newTopLeft = newTop;
            } else {
                newTopRight = newTop;
            }
        }
        if (missingEndRows > 0) {
            ResultPoint bottom = isLeft ? this.bottomLeft : this.bottomRight;
            int newMaxY = ((int) bottom.getY()) + missingEndRows;
            if (newMaxY >= this.image.getHeight()) {
                newMaxY = this.image.getHeight() - 1;
            }
            ResultPoint newBottom = new ResultPoint(bottom.getX(), newMaxY);
            if (isLeft) {
                newBottomLeft = newBottom;
            } else {
                newBottomRight = newBottom;
            }
        }
        calculateMinMaxValues();
        return new BoundingBox(this.image, newTopLeft, newBottomLeft, newTopRight, newBottomRight);
    }

    private void calculateMinMaxValues() {
        if (this.topLeft == null) {
            this.topLeft = new ResultPoint(0.0f, this.topRight.getY());
            this.bottomLeft = new ResultPoint(0.0f, this.bottomRight.getY());
        } else if (this.topRight == null) {
            this.topRight = new ResultPoint(this.image.getWidth() - 1, this.topLeft.getY());
            this.bottomRight = new ResultPoint(this.image.getWidth() - 1, this.bottomLeft.getY());
        }
        this.minX = (int) Math.min(this.topLeft.getX(), this.bottomLeft.getX());
        this.maxX = (int) Math.max(this.topRight.getX(), this.bottomRight.getX());
        this.minY = (int) Math.min(this.topLeft.getY(), this.topRight.getY());
        this.maxY = (int) Math.max(this.bottomLeft.getY(), this.bottomRight.getY());
    }

    public int getMinX() {
        return this.minX;
    }

    public int getMaxX() {
        return this.maxX;
    }

    public int getMinY() {
        return this.minY;
    }

    public int getMaxY() {
        return this.maxY;
    }

    public ResultPoint getTopLeft() {
        return this.topLeft;
    }

    public ResultPoint getTopRight() {
        return this.topRight;
    }

    public ResultPoint getBottomLeft() {
        return this.bottomLeft;
    }

    public ResultPoint getBottomRight() {
        return this.bottomRight;
    }
}
