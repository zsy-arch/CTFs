package com.bumptech.glide.load.resource.file;

import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class FileToStreamDecoder<T> implements ResourceDecoder<File, T> {
    private static final FileOpener DEFAULT_FILE_OPENER = new FileOpener();
    private final FileOpener fileOpener;
    private ResourceDecoder<InputStream, T> streamDecoder;

    public FileToStreamDecoder(ResourceDecoder<InputStream, T> streamDecoder) {
        this(streamDecoder, DEFAULT_FILE_OPENER);
    }

    FileToStreamDecoder(ResourceDecoder<InputStream, T> streamDecoder, FileOpener fileOpener) {
        this.streamDecoder = streamDecoder;
        this.fileOpener = fileOpener;
    }

    public Resource<T> decode(File source, int width, int height) throws IOException {
        InputStream is = null;
        try {
            is = this.fileOpener.open(source);
            return this.streamDecoder.decode(is, width, height);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public String getId() {
        return "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class FileOpener {
        FileOpener() {
        }

        public InputStream open(File file) throws FileNotFoundException {
            return new FileInputStream(file);
        }
    }
}
