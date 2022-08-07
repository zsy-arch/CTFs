package com.bumptech.glide.load.model.file_descriptor;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.ResourceLoader;

/* loaded from: classes.dex */
public class FileDescriptorResourceLoader extends ResourceLoader<ParcelFileDescriptor> implements FileDescriptorModelLoader<Integer> {

    /* loaded from: classes.dex */
    public static class Factory implements ModelLoaderFactory<Integer, ParcelFileDescriptor> {
        @Override // com.bumptech.glide.load.model.ModelLoaderFactory
        public ModelLoader<Integer, ParcelFileDescriptor> build(Context context, GenericLoaderFactory factories) {
            return new FileDescriptorResourceLoader(context, factories.buildModelLoader(Uri.class, ParcelFileDescriptor.class));
        }

        @Override // com.bumptech.glide.load.model.ModelLoaderFactory
        public void teardown() {
        }
    }

    public FileDescriptorResourceLoader(Context context) {
        this(context, Glide.buildFileDescriptorModelLoader(Uri.class, context));
    }

    public FileDescriptorResourceLoader(Context context, ModelLoader<Uri, ParcelFileDescriptor> uriLoader) {
        super(context, uriLoader);
    }
}
