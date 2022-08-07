package com.bumptech.glide.load.model;

import android.content.Context;
import android.net.Uri;
import com.alipay.sdk.cons.b;
import com.bumptech.glide.load.data.DataFetcher;
import com.em.ui.EditActivity;
import org.apache.http.HttpHost;

/* loaded from: classes.dex */
public abstract class UriLoader<T> implements ModelLoader<Uri, T> {
    private final Context context;
    private final ModelLoader<GlideUrl, T> urlLoader;

    protected abstract DataFetcher<T> getAssetPathFetcher(Context context, String str);

    protected abstract DataFetcher<T> getLocalUriFetcher(Context context, Uri uri);

    public UriLoader(Context context, ModelLoader<GlideUrl, T> urlLoader) {
        this.context = context;
        this.urlLoader = urlLoader;
    }

    public final DataFetcher<T> getResourceFetcher(Uri model, int width, int height) {
        String scheme = model.getScheme();
        if (isLocalUri(scheme)) {
            if (!AssetUriParser.isAssetUri(model)) {
                return getLocalUriFetcher(this.context, model);
            }
            return getAssetPathFetcher(this.context, AssetUriParser.toAssetPath(model));
        } else if (this.urlLoader == null) {
            return null;
        } else {
            if (HttpHost.DEFAULT_SCHEME_NAME.equals(scheme) || b.a.equals(scheme)) {
                return this.urlLoader.getResourceFetcher(new GlideUrl(model.toString()), width, height);
            }
            return null;
        }
    }

    private static boolean isLocalUri(String scheme) {
        return "file".equals(scheme) || EditActivity.CONTENT.equals(scheme) || "android.resource".equals(scheme);
    }
}
