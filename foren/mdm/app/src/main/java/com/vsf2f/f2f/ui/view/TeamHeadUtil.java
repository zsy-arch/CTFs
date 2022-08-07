package com.vsf2f.f2f.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import com.bumptech.glide.Glide;
import com.cdlinglu.utils.Photo.util.FileUtils;
import com.hy.frame.util.MyLog;
import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;

/* loaded from: classes2.dex */
public class TeamHeadUtil implements Synthesizer {
    private String currentTargetID;
    private boolean loadOk;
    private int mColumnCount;
    private Context mContext;
    private int mRowCount;
    private int maxHeight;
    private int maxWidth;
    private MultiImageData multiImageData;
    private int targetImageSize;
    private int bgColor = -3355444;
    private int mGap = 6;
    Callback callback = new Callback() { // from class: com.vsf2f.f2f.ui.view.TeamHeadUtil.2
        @Override // com.vsf2f.f2f.ui.view.TeamHeadUtil.Callback
        public void onCall(Object obj, String targetID, boolean complete) {
            if (TextUtils.equals(TeamHeadUtil.this.currentTargetID, targetID)) {
                if (obj instanceof File) {
                    if (complete) {
                        TeamHeadUtil.this.loadOk = true;
                    }
                } else if ((obj instanceof Bitmap) && complete) {
                    TeamHeadUtil.this.loadOk = true;
                }
            }
        }
    };

    /* loaded from: classes2.dex */
    interface Callback {
        void onCall(Object obj, String str, boolean z);
    }

    public TeamHeadUtil(Context mContext) {
        this.mContext = mContext;
        init();
    }

    private void init() {
        this.multiImageData = new MultiImageData();
    }

    public int getMaxWidth() {
        return this.maxWidth;
    }

    public void setMaxWidthHeight(int maxWidth, int maxHeight) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
    }

    public int getMaxHeight() {
        return this.maxHeight;
    }

    public MultiImageData getMultiImageData() {
        return this.multiImageData;
    }

    public void setDefaultImage(int defaultImageResId) {
        this.multiImageData.setDefaultImageResId(defaultImageResId);
    }

    public int getBgColor() {
        return this.bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public int getGap() {
        return this.mGap;
    }

    public void setGap(int mGap) {
        this.mGap = mGap;
    }

    protected int[] calculateGridParam(int imagesSize) {
        int[] gridParam = new int[2];
        if (imagesSize < 3) {
            gridParam[0] = 1;
            gridParam[1] = imagesSize;
        } else if (imagesSize <= 4) {
            gridParam[0] = 2;
            gridParam[1] = 2;
        } else {
            gridParam[0] = (imagesSize % 3 == 0 ? 0 : 1) + (imagesSize / 3);
            gridParam[1] = 3;
        }
        return gridParam;
    }

    @Override // com.vsf2f.f2f.ui.view.Synthesizer
    public Bitmap synthesizeImageList() {
        Bitmap mergeBitmap = Bitmap.createBitmap(this.maxWidth, this.maxHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mergeBitmap);
        drawDrawable(canvas);
        canvas.save(31);
        canvas.restore();
        return mergeBitmap;
    }

    @Override // com.vsf2f.f2f.ui.view.Synthesizer
    public boolean asyncLoadImageList() {
        boolean loadSuccess = true;
        List<String> imageUrls = this.multiImageData.getImageUrls();
        for (int i = 0; i < imageUrls.size(); i++) {
            String imageUrl = imageUrls.get(i);
            if (!TextUtils.isEmpty(imageUrl)) {
                try {
                    this.multiImageData.putBitmap(asyncLoadImage(imageUrl, this.targetImageSize), i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    this.multiImageData.putBitmap(null, i);
                    loadSuccess = false;
                } catch (ExecutionException e2) {
                    e2.printStackTrace();
                    this.multiImageData.putBitmap(null, i);
                    loadSuccess = false;
                }
            }
        }
        return loadSuccess;
    }

    @Override // com.vsf2f.f2f.ui.view.Synthesizer
    public void drawDrawable(Canvas canvas) {
        canvas.drawColor(this.bgColor);
        int size = this.multiImageData.size();
        int t_center = (this.maxHeight + this.mGap) / 2;
        int b_center = (this.maxHeight - this.mGap) / 2;
        int l_center = (this.maxWidth + this.mGap) / 2;
        int r_center = (this.maxWidth - this.mGap) / 2;
        int center = (this.maxHeight - this.targetImageSize) / 2;
        for (int i = 0; i < size; i++) {
            int rowNum = i / this.mColumnCount;
            int columnNum = i % this.mColumnCount;
            int left = (int) (((this.mColumnCount == 1 ? columnNum + 0.5d : columnNum) * this.targetImageSize) + (this.mGap * (columnNum + 1)));
            int top = (int) (((this.mColumnCount == 1 ? rowNum + 0.5d : rowNum) * this.targetImageSize) + (this.mGap * (rowNum + 1)));
            int right = left + this.targetImageSize;
            int bottom = top + this.targetImageSize;
            Bitmap bitmap = this.multiImageData.getBitmap(i);
            if (size == 1) {
                drawBitmapAtPosition(canvas, left, top, right, bottom, bitmap);
            } else if (size == 2) {
                drawBitmapAtPosition(canvas, left, center, right, center + this.targetImageSize, bitmap);
            } else if (size == 3) {
                if (i == 0) {
                    drawBitmapAtPosition(canvas, center, top, center + this.targetImageSize, bottom, bitmap);
                } else {
                    drawBitmapAtPosition(canvas, (this.mGap * i) + (this.targetImageSize * (i - 1)), t_center, (this.mGap * i) + (this.targetImageSize * i), t_center + this.targetImageSize, bitmap);
                }
            } else if (size == 4) {
                drawBitmapAtPosition(canvas, left, top, right, bottom, bitmap);
            } else if (size == 5) {
                if (i == 0) {
                    drawBitmapAtPosition(canvas, r_center - this.targetImageSize, r_center - this.targetImageSize, r_center, r_center, bitmap);
                } else if (i == 1) {
                    drawBitmapAtPosition(canvas, l_center, r_center - this.targetImageSize, l_center + this.targetImageSize, r_center, bitmap);
                } else {
                    drawBitmapAtPosition(canvas, (this.mGap * (i - 1)) + (this.targetImageSize * (i - 2)), t_center, (this.mGap * (i - 1)) + (this.targetImageSize * (i - 1)), t_center + this.targetImageSize, bitmap);
                }
            } else if (size == 6) {
                if (i < 3) {
                    drawBitmapAtPosition(canvas, (this.mGap * (i + 1)) + (this.targetImageSize * i), b_center - this.targetImageSize, (this.mGap * (i + 1)) + (this.targetImageSize * (i + 1)), b_center, bitmap);
                } else {
                    drawBitmapAtPosition(canvas, (this.mGap * (i - 2)) + (this.targetImageSize * (i - 3)), t_center, (this.mGap * (i - 2)) + (this.targetImageSize * (i - 2)), t_center + this.targetImageSize, bitmap);
                }
            } else if (size == 7) {
                if (i == 0) {
                    drawBitmapAtPosition(canvas, center, this.mGap, center + this.targetImageSize, this.mGap + this.targetImageSize, bitmap);
                } else if (i <= 0 || i >= 4) {
                    drawBitmapAtPosition(canvas, (this.mGap * (i - 3)) + (this.targetImageSize * (i - 4)), t_center + (this.targetImageSize / 2), (this.mGap * (i - 3)) + (this.targetImageSize * (i - 3)), (this.targetImageSize / 2) + t_center + this.targetImageSize, bitmap);
                } else {
                    drawBitmapAtPosition(canvas, (this.mGap * i) + (this.targetImageSize * (i - 1)), center, (this.mGap * i) + (this.targetImageSize * i), center + this.targetImageSize, bitmap);
                }
            } else if (size == 8) {
                if (i == 0) {
                    drawBitmapAtPosition(canvas, r_center - this.targetImageSize, this.mGap, r_center, this.mGap + this.targetImageSize, bitmap);
                } else if (i == 1) {
                    drawBitmapAtPosition(canvas, l_center, this.mGap, l_center + this.targetImageSize, this.mGap + this.targetImageSize, bitmap);
                } else if (i <= 1 || i >= 5) {
                    drawBitmapAtPosition(canvas, (this.mGap * (i - 4)) + (this.targetImageSize * (i - 5)), t_center + (this.targetImageSize / 2), (this.mGap * (i - 4)) + (this.targetImageSize * (i - 4)), (this.targetImageSize / 2) + t_center + this.targetImageSize, bitmap);
                } else {
                    drawBitmapAtPosition(canvas, (this.mGap * (i - 1)) + (this.targetImageSize * (i - 2)), center, (this.mGap * (i - 1)) + (this.targetImageSize * (i - 1)), center + this.targetImageSize, bitmap);
                }
            } else if (size == 9) {
                drawBitmapAtPosition(canvas, left, top, right, bottom, bitmap);
            }
        }
    }

    public void drawBitmapAtPosition(Canvas canvas, int left, int top, int right, int bottom, Bitmap bitmap) {
        if (bitmap == null && this.multiImageData.getDefaultImageResId() > 0) {
            bitmap = BitmapFactory.decodeResource(this.mContext.getResources(), this.multiImageData.getDefaultImageResId());
        }
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, (Rect) null, new Rect(left, top, right, bottom), (Paint) null);
        }
    }

    private Bitmap asyncLoadImage(String imageUrl, int targetImageSize) throws InterruptedException, ExecutionException {
        return Glide.with(this.mContext).load(imageUrl).asBitmap().centerCrop().into(targetImageSize, targetImageSize).get();
    }

    /* JADX WARN: Type inference failed for: r1v10, types: [com.vsf2f.f2f.ui.view.TeamHeadUtil$1] */
    public void load() {
        int[] gridParam = calculateGridParam(this.multiImageData.size());
        this.mRowCount = gridParam[0];
        this.mColumnCount = gridParam[1];
        this.targetImageSize = (this.maxWidth - ((this.mColumnCount + 1) * this.mGap)) / (this.mColumnCount == 1 ? 2 : this.mColumnCount);
        new Thread() { // from class: com.vsf2f.f2f.ui.view.TeamHeadUtil.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                boolean loadSuccess = TeamHeadUtil.this.asyncLoadImageList();
                Bitmap bitmap = TeamHeadUtil.this.synthesizeImageList();
                if (loadSuccess) {
                    FileUtils.saveBitmap(bitmap, TeamHeadUtil.this.multiImageData.getSavePath());
                    MyLog.e("保存图片：" + TeamHeadUtil.this.multiImageData.getSavePath());
                }
            }
        }.start();
    }
}
