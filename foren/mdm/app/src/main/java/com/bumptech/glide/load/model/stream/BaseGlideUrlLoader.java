package com.bumptech.glide.load.model.stream;

import android.content.Context;
import android.text.TextUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.Headers;
import com.bumptech.glide.load.model.ModelCache;
import com.bumptech.glide.load.model.ModelLoader;
import java.io.InputStream;

/* loaded from: classes.dex */
public abstract class BaseGlideUrlLoader<T> implements StreamModelLoader<T> {
    private final ModelLoader<GlideUrl, InputStream> concreteLoader;
    private final ModelCache<T, GlideUrl> modelCache;

    protected abstract String getUrl(T t, int i, int i2);

    public BaseGlideUrlLoader(Context context) {
        this(context, (ModelCache) null);
    }

    public BaseGlideUrlLoader(Context context, ModelCache<T, GlideUrl> modelCache) {
        this(Glide.buildModelLoader(GlideUrl.class, InputStream.class, context), modelCache);
    }

    public BaseGlideUrlLoader(ModelLoader<GlideUrl, InputStream> concreteLoader) {
        this(concreteLoader, (ModelCache) null);
    }

    public BaseGlideUrlLoader(ModelLoader<GlideUrl, InputStream> concreteLoader, ModelCache<T, GlideUrl> modelCache) {
        this.concreteLoader = concreteLoader;
        this.modelCache = modelCache;
    }

    @Override // com.bumptech.glide.load.model.ModelLoader
    public DataFetcher<InputStream> getResourceFetcher(T model, int width, int height) {
        GlideUrl result = null;
        if (this.modelCache != null) {
            result = this.modelCache.get(model, width, height);
        }
        if (result == null) {
            String stringURL = getUrl(model, width, height);
            if (TextUtils.isEmpty(stringURL)) {
                return null;
            }
            result = new GlideUrl(stringURL, getHeaders(model, width, height));
            if (this.modelCache != null) {
                this.modelCache.put(model, width, height, result);
            }
        }
        return this.concreteLoader.getResourceFetcher(result, width, height);
    }

    protected Headers getHeaders(T model, int width, int height) {
        return Headers.DEFAULT;
    }
}
