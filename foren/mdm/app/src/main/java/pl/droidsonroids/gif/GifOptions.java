package pl.droidsonroids.gif;

import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import pl.droidsonroids.gif.annotations.Beta;

@Beta
/* loaded from: classes2.dex */
public class GifOptions {
    boolean inIsOpaque;
    char inSampleSize;

    public GifOptions() {
        reset();
    }

    private void reset() {
        this.inSampleSize = (char) 1;
        this.inIsOpaque = false;
    }

    public void setInSampleSize(@IntRange(from = 1, to = 65535) int inSampleSize) {
        if (inSampleSize < 1 || inSampleSize > 65535) {
            this.inSampleSize = (char) 1;
        } else {
            this.inSampleSize = (char) inSampleSize;
        }
    }

    public void setInIsOpaque(boolean inIsOpaque) {
        this.inIsOpaque = inIsOpaque;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFrom(@Nullable GifOptions source) {
        if (source == null) {
            reset();
            return;
        }
        this.inIsOpaque = source.inIsOpaque;
        this.inSampleSize = source.inSampleSize;
    }
}
