package pl.droidsonroids.gif;

import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import java.io.IOException;
import pl.droidsonroids.gif.annotations.Beta;

@Beta
/* loaded from: classes2.dex */
public class GifTexImage2D {
    private final GifInfoHandle mGifInfoHandle;

    public GifTexImage2D(InputSource inputSource, @Nullable GifOptions options) throws IOException {
        options = options == null ? new GifOptions() : options;
        this.mGifInfoHandle = inputSource.open();
        this.mGifInfoHandle.setOptions(options.inSampleSize, options.inIsOpaque);
        this.mGifInfoHandle.initTexImageDescriptor();
    }

    public int getFrameDuration(@IntRange(from = 0) int index) {
        return this.mGifInfoHandle.getFrameDuration(index);
    }

    public void seekToFrame(@IntRange(from = 0) int index) {
        this.mGifInfoHandle.seekToFrameGL(index);
    }

    public int getNumberOfFrames() {
        return this.mGifInfoHandle.getNumberOfFrames();
    }

    public void glTexImage2D(int target, int level) {
        this.mGifInfoHandle.glTexImage2D(target, level);
    }

    public void glTexSubImage2D(int target, int level) {
        this.mGifInfoHandle.glTexSubImage2D(target, level);
    }

    public void startDecoderThread() {
        this.mGifInfoHandle.startDecoderThread();
    }

    public void stopDecoderThread() {
        this.mGifInfoHandle.stopDecoderThread();
    }

    public void recycle() {
        this.mGifInfoHandle.recycle();
    }

    public int getWidth() {
        return this.mGifInfoHandle.getWidth();
    }

    public int getHeight() {
        return this.mGifInfoHandle.getHeight();
    }

    protected final void finalize() throws Throwable {
        try {
            recycle();
        } finally {
            super.finalize();
        }
    }
}
