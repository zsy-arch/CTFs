package com.easeui.widget.photoview;

import android.graphics.RectF;
import android.view.View;
import android.widget.ImageView;
import com.easeui.widget.photoview.PhotoViewAttacher;

/* loaded from: classes.dex */
public interface IPhotoView {
    boolean canZoom();

    RectF getDisplayRect();

    float getMaxScale();

    float getMidScale();

    float getMinScale();

    float getScale();

    ImageView.ScaleType getScaleType();

    void setAllowParentInterceptOnEdge(boolean z);

    void setMaxScale(float f);

    void setMidScale(float f);

    void setMinScale(float f);

    void setOnLongClickListener(View.OnLongClickListener onLongClickListener);

    void setOnMatrixChangeListener(PhotoViewAttacher.OnMatrixChangedListener onMatrixChangedListener);

    void setOnPhotoTapListener(PhotoViewAttacher.OnPhotoTapListener onPhotoTapListener);

    void setOnViewTapListener(PhotoViewAttacher.OnViewTapListener onViewTapListener);

    void setScaleType(ImageView.ScaleType scaleType);

    void setZoomable(boolean z);

    void zoomTo(float f, float f2, float f3);
}
