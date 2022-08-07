package pl.droidsonroids.gif;

import android.support.annotation.NonNull;
import java.io.IOException;

/* loaded from: classes2.dex */
public class GifIOException extends IOException {
    private static final long serialVersionUID = 13038402904505L;
    @NonNull
    public final GifError reason;

    private GifIOException(@NonNull GifError reason) {
        super(reason.getFormattedDescription());
        this.reason = reason;
    }

    GifIOException(int errorCode) {
        this(GifError.fromCode(errorCode));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GifIOException fromCode(int nativeErrorCode) {
        if (nativeErrorCode == GifError.NO_ERROR.errorCode) {
            return null;
        }
        return new GifIOException(nativeErrorCode);
    }
}
