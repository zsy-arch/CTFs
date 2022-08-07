package com.bumptech.glide;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawableTransformation;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.provider.LoadProvider;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.DrawableCrossFadeFactory;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import java.io.File;
import java.io.InputStream;

/* loaded from: classes.dex */
public class GifRequestBuilder<ModelType> extends GenericRequestBuilder<ModelType, InputStream, GifDrawable, GifDrawable> implements BitmapOptions, DrawableOptions {
    /* JADX INFO: Access modifiers changed from: package-private */
    public GifRequestBuilder(LoadProvider<ModelType, InputStream, GifDrawable, GifDrawable> loadProvider, Class<GifDrawable> transcodeClass, GenericRequestBuilder<ModelType, ?, ?, ?> other) {
        super(loadProvider, transcodeClass, other);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bumptech.glide.GenericRequestBuilder
    public GifRequestBuilder<ModelType> thumbnail(GenericRequestBuilder<?, ?, ?, GifDrawable> thumbnailRequest) {
        super.thumbnail((GenericRequestBuilder) thumbnailRequest);
        return this;
    }

    public GifRequestBuilder<ModelType> thumbnail(GifRequestBuilder<?> thumbnailRequest) {
        super.thumbnail((GenericRequestBuilder) thumbnailRequest);
        return this;
    }

    @Override // com.bumptech.glide.GenericRequestBuilder
    public GifRequestBuilder<ModelType> thumbnail(float sizeMultiplier) {
        super.thumbnail(sizeMultiplier);
        return this;
    }

    @Override // com.bumptech.glide.GenericRequestBuilder
    public GifRequestBuilder<ModelType> sizeMultiplier(float sizeMultiplier) {
        super.sizeMultiplier(sizeMultiplier);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bumptech.glide.GenericRequestBuilder
    public GifRequestBuilder<ModelType> decoder(ResourceDecoder<InputStream, GifDrawable> decoder) {
        super.decoder((ResourceDecoder) decoder);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bumptech.glide.GenericRequestBuilder
    public GifRequestBuilder<ModelType> cacheDecoder(ResourceDecoder<File, GifDrawable> cacheDecoder) {
        super.cacheDecoder((ResourceDecoder) cacheDecoder);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bumptech.glide.GenericRequestBuilder
    public GifRequestBuilder<ModelType> encoder(ResourceEncoder<GifDrawable> encoder) {
        super.encoder((ResourceEncoder) encoder);
        return this;
    }

    @Override // com.bumptech.glide.GenericRequestBuilder
    public GifRequestBuilder<ModelType> priority(Priority priority) {
        super.priority(priority);
        return this;
    }

    @Override // com.bumptech.glide.BitmapOptions
    public GifRequestBuilder<ModelType> centerCrop() {
        return transformFrame(this.glide.getBitmapCenterCrop());
    }

    @Override // com.bumptech.glide.BitmapOptions
    public GifRequestBuilder<ModelType> fitCenter() {
        return transformFrame(this.glide.getBitmapFitCenter());
    }

    public GifRequestBuilder<ModelType> transformFrame(BitmapTransformation... bitmapTransformations) {
        return transform((Transformation<GifDrawable>[]) toGifTransformations(bitmapTransformations));
    }

    public GifRequestBuilder<ModelType> transformFrame(Transformation<Bitmap>... bitmapTransformations) {
        return transform((Transformation<GifDrawable>[]) toGifTransformations(bitmapTransformations));
    }

    private GifDrawableTransformation[] toGifTransformations(Transformation<Bitmap>[] bitmapTransformations) {
        GifDrawableTransformation[] transformations = new GifDrawableTransformation[bitmapTransformations.length];
        for (int i = 0; i < bitmapTransformations.length; i++) {
            transformations[i] = new GifDrawableTransformation(bitmapTransformations[i], this.glide.getBitmapPool());
        }
        return transformations;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bumptech.glide.GenericRequestBuilder
    public GifRequestBuilder<ModelType> transform(Transformation<GifDrawable>... transformations) {
        super.transform((Transformation[]) transformations);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bumptech.glide.GenericRequestBuilder
    public GifRequestBuilder<ModelType> transcoder(ResourceTranscoder<GifDrawable, GifDrawable> transcoder) {
        super.transcoder((ResourceTranscoder) transcoder);
        return this;
    }

    @Override // com.bumptech.glide.DrawableOptions
    public GifRequestBuilder<ModelType> crossFade() {
        super.animate(new DrawableCrossFadeFactory());
        return this;
    }

    @Override // com.bumptech.glide.DrawableOptions
    public GifRequestBuilder<ModelType> crossFade(int duration) {
        super.animate(new DrawableCrossFadeFactory(duration));
        return this;
    }

    @Override // com.bumptech.glide.DrawableOptions
    @Deprecated
    public GifRequestBuilder<ModelType> crossFade(Animation animation, int duration) {
        super.animate(new DrawableCrossFadeFactory(animation, duration));
        return this;
    }

    @Override // com.bumptech.glide.DrawableOptions
    public GifRequestBuilder<ModelType> crossFade(int animationId, int duration) {
        super.animate(new DrawableCrossFadeFactory(this.context, animationId, duration));
        return this;
    }

    @Override // com.bumptech.glide.GenericRequestBuilder
    public GifRequestBuilder<ModelType> dontAnimate() {
        super.dontAnimate();
        return this;
    }

    @Override // com.bumptech.glide.GenericRequestBuilder
    public GifRequestBuilder<ModelType> animate(int animationId) {
        super.animate(animationId);
        return this;
    }

    @Override // com.bumptech.glide.GenericRequestBuilder
    @Deprecated
    public GifRequestBuilder<ModelType> animate(Animation animation) {
        super.animate(animation);
        return this;
    }

    @Override // com.bumptech.glide.GenericRequestBuilder
    public GifRequestBuilder<ModelType> animate(ViewPropertyAnimation.Animator animator) {
        super.animate(animator);
        return this;
    }

    @Override // com.bumptech.glide.GenericRequestBuilder
    public GifRequestBuilder<ModelType> placeholder(int resourceId) {
        super.placeholder(resourceId);
        return this;
    }

    @Override // com.bumptech.glide.GenericRequestBuilder
    public GifRequestBuilder<ModelType> placeholder(Drawable drawable) {
        super.placeholder(drawable);
        return this;
    }

    @Override // com.bumptech.glide.GenericRequestBuilder
    public GifRequestBuilder<ModelType> fallback(Drawable drawable) {
        super.fallback(drawable);
        return this;
    }

    @Override // com.bumptech.glide.GenericRequestBuilder
    public GifRequestBuilder<ModelType> fallback(int resourceId) {
        super.fallback(resourceId);
        return this;
    }

    @Override // com.bumptech.glide.GenericRequestBuilder
    public GifRequestBuilder<ModelType> error(int resourceId) {
        super.error(resourceId);
        return this;
    }

    @Override // com.bumptech.glide.GenericRequestBuilder
    public GifRequestBuilder<ModelType> error(Drawable drawable) {
        super.error(drawable);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bumptech.glide.GenericRequestBuilder
    public GifRequestBuilder<ModelType> listener(RequestListener<? super ModelType, GifDrawable> requestListener) {
        super.listener((RequestListener) requestListener);
        return this;
    }

    @Override // com.bumptech.glide.GenericRequestBuilder
    public GifRequestBuilder<ModelType> skipMemoryCache(boolean skip) {
        super.skipMemoryCache(skip);
        return this;
    }

    @Override // com.bumptech.glide.GenericRequestBuilder
    public GifRequestBuilder<ModelType> diskCacheStrategy(DiskCacheStrategy strategy) {
        super.diskCacheStrategy(strategy);
        return this;
    }

    @Override // com.bumptech.glide.GenericRequestBuilder
    public GifRequestBuilder<ModelType> override(int width, int height) {
        super.override(width, height);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bumptech.glide.GenericRequestBuilder
    public GifRequestBuilder<ModelType> sourceEncoder(Encoder<InputStream> sourceEncoder) {
        super.sourceEncoder((Encoder) sourceEncoder);
        return this;
    }

    @Override // com.bumptech.glide.GenericRequestBuilder
    public GifRequestBuilder<ModelType> dontTransform() {
        super.dontTransform();
        return this;
    }

    @Override // com.bumptech.glide.GenericRequestBuilder
    public GifRequestBuilder<ModelType> signature(Key signature) {
        super.signature(signature);
        return this;
    }

    @Override // com.bumptech.glide.GenericRequestBuilder
    public GifRequestBuilder<ModelType> load(ModelType model) {
        super.load((GifRequestBuilder<ModelType>) model);
        return this;
    }

    @Override // com.bumptech.glide.GenericRequestBuilder
    public GifRequestBuilder<ModelType> clone() {
        return (GifRequestBuilder) super.clone();
    }

    @Override // com.bumptech.glide.GenericRequestBuilder
    void applyFitCenter() {
        fitCenter();
    }

    @Override // com.bumptech.glide.GenericRequestBuilder
    void applyCenterCrop() {
        centerCrop();
    }
}
