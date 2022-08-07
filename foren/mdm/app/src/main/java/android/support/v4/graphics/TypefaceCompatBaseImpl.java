package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.support.v4.graphics.TypefaceCompat;
import android.support.v4.provider.FontsContractCompat;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: Access modifiers changed from: package-private */
@RequiresApi(14)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class TypefaceCompatBaseImpl implements TypefaceCompat.TypefaceCompatImpl {
    private static final String CACHE_FILE_PREFIX = "cached_font_";
    private static final String TAG = "TypefaceCompatBaseImpl";

    /* loaded from: classes.dex */
    public interface StyleExtractor<T> {
        int getWeight(T t);

        boolean isItalic(T t);
    }

    private static <T> T findBestFont(T[] fonts, int style, StyleExtractor<T> extractor) {
        int targetWeight = (style & 1) == 0 ? 400 : 700;
        boolean isTargetItalic = (style & 2) != 0;
        T best = null;
        int bestScore = Integer.MAX_VALUE;
        for (T font : fonts) {
            int score = (Math.abs(extractor.getWeight(font) - targetWeight) * 2) + (extractor.isItalic(font) == isTargetItalic ? 0 : 1);
            if (best == null || bestScore > score) {
                best = font;
                bestScore = score;
            }
        }
        return best;
    }

    protected FontsContractCompat.FontInfo findBestInfo(FontsContractCompat.FontInfo[] fonts, int style) {
        return (FontsContractCompat.FontInfo) findBestFont(fonts, style, new StyleExtractor<FontsContractCompat.FontInfo>() { // from class: android.support.v4.graphics.TypefaceCompatBaseImpl.1
            public int getWeight(FontsContractCompat.FontInfo info) {
                return info.getWeight();
            }

            public boolean isItalic(FontsContractCompat.FontInfo info) {
                return info.isItalic();
            }
        });
    }

    public Typeface createFromInputStream(Context context, InputStream is) {
        Typeface typeface = null;
        File tmpFile = TypefaceCompatUtil.getTempFile(context);
        if (tmpFile != null) {
            try {
                if (TypefaceCompatUtil.copyToFile(tmpFile, is)) {
                    typeface = Typeface.createFromFile(tmpFile.getPath());
                }
            } catch (RuntimeException e) {
            } finally {
                tmpFile.delete();
            }
        }
        return typeface;
    }

    @Override // android.support.v4.graphics.TypefaceCompat.TypefaceCompatImpl
    public Typeface createFromFontInfo(Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontsContractCompat.FontInfo[] fonts, int style) {
        Typeface typeface = null;
        if (fonts.length >= 1) {
            InputStream is = null;
            try {
                is = context.getContentResolver().openInputStream(findBestInfo(fonts, style).getUri());
                typeface = createFromInputStream(context, is);
            } catch (IOException e) {
            } finally {
                TypefaceCompatUtil.closeQuietly(is);
            }
        }
        return typeface;
    }

    private FontResourcesParserCompat.FontFileResourceEntry findBestEntry(FontResourcesParserCompat.FontFamilyFilesResourceEntry entry, int style) {
        return (FontResourcesParserCompat.FontFileResourceEntry) findBestFont(entry.getEntries(), style, new StyleExtractor<FontResourcesParserCompat.FontFileResourceEntry>() { // from class: android.support.v4.graphics.TypefaceCompatBaseImpl.2
            public int getWeight(FontResourcesParserCompat.FontFileResourceEntry entry2) {
                return entry2.getWeight();
            }

            public boolean isItalic(FontResourcesParserCompat.FontFileResourceEntry entry2) {
                return entry2.isItalic();
            }
        });
    }

    @Override // android.support.v4.graphics.TypefaceCompat.TypefaceCompatImpl
    @Nullable
    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry entry, Resources resources, int style) {
        FontResourcesParserCompat.FontFileResourceEntry best = findBestEntry(entry, style);
        if (best == null) {
            return null;
        }
        return TypefaceCompat.createFromResourcesFontFile(context, resources, best.getResourceId(), best.getFileName(), style);
    }

    @Override // android.support.v4.graphics.TypefaceCompat.TypefaceCompatImpl
    @Nullable
    public Typeface createFromResourcesFontFile(Context context, Resources resources, int id, String path, int style) {
        Typeface typeface = null;
        File tmpFile = TypefaceCompatUtil.getTempFile(context);
        if (tmpFile != null) {
            try {
                if (TypefaceCompatUtil.copyToFile(tmpFile, resources, id)) {
                    typeface = Typeface.createFromFile(tmpFile.getPath());
                }
            } catch (RuntimeException e) {
            } finally {
                tmpFile.delete();
            }
        }
        return typeface;
    }
}
