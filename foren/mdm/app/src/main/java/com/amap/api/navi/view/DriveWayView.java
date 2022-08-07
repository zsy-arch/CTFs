package com.amap.api.navi.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.amap.api.col.fo;
import com.amap.api.navi.AMapNaviView;

/* loaded from: classes.dex */
public class DriveWayView extends ImageView {
    private int height;
    private int width;
    private int driveWayWidth = 0;
    private int driveWayHeight = 0;
    private int driveWaySize = 0;
    private int[] driveWayBackgroundId = {1191313451, 1191313452, 1191313453, 1191313454, 1191313455, 1191313456, 1191313457, 1191313458, 1191313459, 1191313460, 1191313461, 1191313462, 1191313463, 1191313464, 1191313465};
    private int[] driveWayForegroundId = {1191313466, 1191313467, 1191313453, 1191313470, 1191313455, 1191313473, 1191313457, 1191313458, 1191313479, 1191313460, 1191313461, 1191313462, 1191313463, 1191313488, 1191313465};
    private AMapNaviView mAMapNaviView = null;
    private Bitmap[] driveWayBitMaps = null;
    private Bitmap[] driveWayBitMapBgs = null;

    public DriveWayView(Context context) {
        super(context);
    }

    public DriveWayView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DriveWayView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public int getDriveWayWidth() {
        return this.driveWayWidth;
    }

    public void setAMapNaviView(AMapNaviView aMapNaviView) {
        this.mAMapNaviView = aMapNaviView;
    }

    public void loadDriveWayBitmap(byte[] bArr, byte[] bArr2) {
        this.driveWaySize = parseDriveWaySize(bArr);
        if (this.driveWaySize != 0) {
            this.driveWayBitMapBgs = new Bitmap[this.driveWaySize];
            this.driveWayBitMaps = new Bitmap[this.driveWaySize];
            int i = 0;
            while (i < this.driveWaySize) {
                this.driveWayBitMapBgs[i] = BitmapFactory.decodeResource(fo.a(), this.driveWayBackgroundId[bArr[i]]);
                if (isComplexLane(bArr[i])) {
                    this.driveWayBitMaps[i] = complexBitmap(bArr[i], bArr2[i]);
                } else if (isThisLaneRecommended(bArr2[i])) {
                    this.driveWayBitMaps[i] = BitmapFactory.decodeResource(fo.a(), this.driveWayForegroundId[bArr2[i]]);
                } else {
                    this.driveWayBitMaps[i] = this.driveWayBitMapBgs[i];
                }
                i++;
            }
            if (this.driveWayBitMapBgs[i - 1] != null) {
                this.driveWayWidth = this.driveWayBitMapBgs[i - 1].getWidth();
                this.driveWayHeight = this.driveWayBitMapBgs[i - 1].getHeight();
            }
            this.height = this.driveWayHeight;
            this.width = this.driveWayWidth * this.driveWaySize;
            setImageBitmap(produceFinalBitmap());
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    private int parseDriveWaySize(byte[] bArr) {
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            if (bArr[i] == 15) {
                return i;
            }
        }
        return 0;
    }

    private boolean isThisLaneRecommended(byte b) {
        return b != 15;
    }

    private boolean isComplexLane(int i) {
        return i == 14 || i == 2 || i == 4 || i == 9 || i == 10 || i == 11 || i == 12 || i == 6 || i == 7;
    }

    private Bitmap complexBitmap(int i, int i2) {
        Bitmap bitmap = null;
        if (i == 10) {
            if (i2 == 0) {
                bitmap = BitmapFactory.decodeResource(fo.a(), 1191313482);
            } else if (i2 == 8) {
                bitmap = BitmapFactory.decodeResource(fo.a(), 1191313483);
            }
        } else if (i == 9) {
            if (i2 == 0) {
                bitmap = BitmapFactory.decodeResource(fo.a(), 1191313480);
            } else if (i2 == 5) {
                bitmap = BitmapFactory.decodeResource(fo.a(), 1191313481);
            }
        } else if (i == 2) {
            if (i2 == 0) {
                bitmap = BitmapFactory.decodeResource(fo.a(), 1191313468);
            } else if (i2 == 1) {
                bitmap = BitmapFactory.decodeResource(fo.a(), 1191313469);
            }
        } else if (i == 4) {
            if (i2 == 0) {
                bitmap = BitmapFactory.decodeResource(fo.a(), 1191313471);
            } else if (i2 == 3) {
                bitmap = BitmapFactory.decodeResource(fo.a(), 1191313472);
            }
        } else if (i == 6) {
            if (i2 == 1) {
                bitmap = BitmapFactory.decodeResource(fo.a(), 1191313474);
            } else if (i2 == 3) {
                bitmap = BitmapFactory.decodeResource(fo.a(), 1191313475);
            }
        } else if (i == 7) {
            if (i2 == 0) {
                bitmap = BitmapFactory.decodeResource(fo.a(), 1191313476);
            } else if (i2 == 1) {
                bitmap = BitmapFactory.decodeResource(fo.a(), 1191313477);
            } else if (i2 == 3) {
                bitmap = BitmapFactory.decodeResource(fo.a(), 1191313478);
            }
        } else if (i == 11) {
            if (i2 == 5) {
                bitmap = BitmapFactory.decodeResource(fo.a(), 1191313485);
            } else if (i2 == 1) {
                bitmap = BitmapFactory.decodeResource(fo.a(), 1191313484);
            }
        } else if (i == 12) {
            if (i2 == 8) {
                bitmap = BitmapFactory.decodeResource(fo.a(), 1191313487);
            } else if (i2 == 3) {
                bitmap = BitmapFactory.decodeResource(fo.a(), 1191313486);
            }
        } else if (i == 14) {
            if (i2 == 1) {
                bitmap = BitmapFactory.decodeResource(fo.a(), 1191313489);
            } else if (i2 == 5) {
                bitmap = BitmapFactory.decodeResource(fo.a(), 1191313490);
            }
        }
        if (bitmap == null) {
            return BitmapFactory.decodeResource(fo.a(), this.driveWayBackgroundId[i]);
        }
        return bitmap;
    }

    public void setDefaultTopMargin(int i) {
        int i2;
        if (this.mAMapNaviView != null) {
            if (this.mAMapNaviView.isOrientationLandscape()) {
                i2 = 10 + i;
            } else if (this.mAMapNaviView.isShowRoadEnlarge()) {
                i2 = ((i * 3) / 8) - (this.driveWayHeight >> 1);
            } else {
                i2 = 10 + i;
            }
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getLayoutParams();
            layoutParams.setMargins(0, i2, 0, 0);
            setLayoutParams(layoutParams);
        }
    }

    public void recycleResource() {
        for (int i = 0; i < this.driveWaySize; i++) {
            if (this.driveWayBitMaps[i] != null) {
                this.driveWayBitMaps[i].recycle();
                this.driveWayBitMaps[i] = null;
            }
            if (this.driveWayBitMapBgs[i] != null) {
                this.driveWayBitMapBgs[i].recycle();
                this.driveWayBitMapBgs[i] = null;
            }
        }
        this.driveWaySize = 0;
    }

    Bitmap produceFinalBitmap() {
        Bitmap createBitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        for (int i = 0; i < this.driveWaySize; i++) {
            if (this.driveWayBitMaps[i] != null) {
                canvas.drawBitmap(this.driveWayBitMaps[i], this.driveWayWidth * i, 0.0f, (Paint) null);
            }
        }
        return createBitmap;
    }

    public int getDriveWaySize() {
        return this.driveWaySize;
    }

    public int getDriveWayBgHeight() {
        return this.driveWayHeight;
    }
}
