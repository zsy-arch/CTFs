package com.bumptech.glide.load.model;

import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.Encoder;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes.dex */
public class ImageVideoWrapperEncoder implements Encoder<ImageVideoWrapper> {
    private final Encoder<ParcelFileDescriptor> fileDescriptorEncoder;
    private String id;
    private final Encoder<InputStream> streamEncoder;

    public ImageVideoWrapperEncoder(Encoder<InputStream> streamEncoder, Encoder<ParcelFileDescriptor> fileDescriptorEncoder) {
        this.streamEncoder = streamEncoder;
        this.fileDescriptorEncoder = fileDescriptorEncoder;
    }

    public boolean encode(ImageVideoWrapper data, OutputStream os) {
        return data.getStream() != null ? this.streamEncoder.encode(data.getStream(), os) : this.fileDescriptorEncoder.encode(data.getFileDescriptor(), os);
    }

    @Override // com.bumptech.glide.load.Encoder
    public String getId() {
        if (this.id == null) {
            this.id = this.streamEncoder.getId() + this.fileDescriptorEncoder.getId();
        }
        return this.id;
    }
}
