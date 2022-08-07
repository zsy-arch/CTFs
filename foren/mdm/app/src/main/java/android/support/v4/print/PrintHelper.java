package android.support.v4.print;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.print.pdf.PrintedPdfDocument;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public final class PrintHelper {
    public static final int COLOR_MODE_COLOR = 2;
    public static final int COLOR_MODE_MONOCHROME = 1;
    public static final int ORIENTATION_LANDSCAPE = 1;
    public static final int ORIENTATION_PORTRAIT = 2;
    public static final int SCALE_MODE_FILL = 2;
    public static final int SCALE_MODE_FIT = 1;
    private final PrintHelperVersionImpl mImpl;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    private @interface ColorMode {
    }

    /* loaded from: classes.dex */
    public interface OnPrintFinishCallback {
        void onFinish();
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    private @interface Orientation {
    }

    /* loaded from: classes.dex */
    interface PrintHelperVersionImpl {
        int getColorMode();

        int getOrientation();

        int getScaleMode();

        void printBitmap(String str, Bitmap bitmap, OnPrintFinishCallback onPrintFinishCallback);

        void printBitmap(String str, Uri uri, OnPrintFinishCallback onPrintFinishCallback) throws FileNotFoundException;

        void setColorMode(int i);

        void setOrientation(int i);

        void setScaleMode(int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    private @interface ScaleMode {
    }

    public static boolean systemSupportsPrint() {
        return Build.VERSION.SDK_INT >= 19;
    }

    /* loaded from: classes.dex */
    private static final class PrintHelperStub implements PrintHelperVersionImpl {
        int mColorMode;
        int mOrientation;
        int mScaleMode;

        private PrintHelperStub() {
            this.mScaleMode = 2;
            this.mColorMode = 2;
            this.mOrientation = 1;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public void setScaleMode(int scaleMode) {
            this.mScaleMode = scaleMode;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public int getScaleMode() {
            return this.mScaleMode;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public int getColorMode() {
            return this.mColorMode;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public void setColorMode(int colorMode) {
            this.mColorMode = colorMode;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public void setOrientation(int orientation) {
            this.mOrientation = orientation;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public int getOrientation() {
            return this.mOrientation;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public void printBitmap(String jobName, Bitmap bitmap, OnPrintFinishCallback callback) {
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public void printBitmap(String jobName, Uri imageFile, OnPrintFinishCallback callback) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(19)
    /* loaded from: classes.dex */
    public static class PrintHelperApi19 implements PrintHelperVersionImpl {
        private static final String LOG_TAG = "PrintHelperApi19";
        private static final int MAX_PRINT_SIZE = 3500;
        final Context mContext;
        int mOrientation;
        BitmapFactory.Options mDecodeOptions = null;
        private final Object mLock = new Object();
        int mScaleMode = 2;
        int mColorMode = 2;
        protected boolean mPrintActivityRespectsOrientation = true;
        protected boolean mIsMinMarginsHandlingCorrect = true;

        PrintHelperApi19(Context context) {
            this.mContext = context;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public void setScaleMode(int scaleMode) {
            this.mScaleMode = scaleMode;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public int getScaleMode() {
            return this.mScaleMode;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public void setColorMode(int colorMode) {
            this.mColorMode = colorMode;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public void setOrientation(int orientation) {
            this.mOrientation = orientation;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public int getOrientation() {
            if (this.mOrientation == 0) {
                return 1;
            }
            return this.mOrientation;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public int getColorMode() {
            return this.mColorMode;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isPortrait(Bitmap bitmap) {
            return bitmap.getWidth() <= bitmap.getHeight();
        }

        protected PrintAttributes.Builder copyAttributes(PrintAttributes other) {
            PrintAttributes.Builder b = new PrintAttributes.Builder().setMediaSize(other.getMediaSize()).setResolution(other.getResolution()).setMinMargins(other.getMinMargins());
            if (other.getColorMode() != 0) {
                b.setColorMode(other.getColorMode());
            }
            return b;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public void printBitmap(final String jobName, final Bitmap bitmap, final OnPrintFinishCallback callback) {
            PrintAttributes.MediaSize mediaSize;
            if (bitmap != null) {
                final int fittingMode = this.mScaleMode;
                PrintManager printManager = (PrintManager) this.mContext.getSystemService("print");
                if (isPortrait(bitmap)) {
                    mediaSize = PrintAttributes.MediaSize.UNKNOWN_PORTRAIT;
                } else {
                    mediaSize = PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE;
                }
                printManager.print(jobName, new PrintDocumentAdapter() { // from class: android.support.v4.print.PrintHelper.PrintHelperApi19.1
                    private PrintAttributes mAttributes;

                    @Override // android.print.PrintDocumentAdapter
                    public void onLayout(PrintAttributes oldPrintAttributes, PrintAttributes newPrintAttributes, CancellationSignal cancellationSignal, PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle) {
                        boolean changed = true;
                        this.mAttributes = newPrintAttributes;
                        PrintDocumentInfo info = new PrintDocumentInfo.Builder(jobName).setContentType(1).setPageCount(1).build();
                        if (newPrintAttributes.equals(oldPrintAttributes)) {
                            changed = false;
                        }
                        layoutResultCallback.onLayoutFinished(info, changed);
                    }

                    @Override // android.print.PrintDocumentAdapter
                    public void onWrite(PageRange[] pageRanges, ParcelFileDescriptor fileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
                        PrintHelperApi19.this.writeBitmap(this.mAttributes, fittingMode, bitmap, fileDescriptor, cancellationSignal, writeResultCallback);
                    }

                    @Override // android.print.PrintDocumentAdapter
                    public void onFinish() {
                        if (callback != null) {
                            callback.onFinish();
                        }
                    }
                }, new PrintAttributes.Builder().setMediaSize(mediaSize).setColorMode(this.mColorMode).build());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Matrix getMatrix(int imageWidth, int imageHeight, RectF content, int fittingMode) {
            float scale;
            Matrix matrix = new Matrix();
            float scale2 = content.width() / imageWidth;
            if (fittingMode == 2) {
                scale = Math.max(scale2, content.height() / imageHeight);
            } else {
                scale = Math.min(scale2, content.height() / imageHeight);
            }
            matrix.postScale(scale, scale);
            matrix.postTranslate((content.width() - (imageWidth * scale)) / 2.0f, (content.height() - (imageHeight * scale)) / 2.0f);
            return matrix;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Type inference failed for: r0v3, types: [android.support.v4.print.PrintHelper$PrintHelperApi19$2] */
        public void writeBitmap(final PrintAttributes attributes, final int fittingMode, final Bitmap bitmap, final ParcelFileDescriptor fileDescriptor, final CancellationSignal cancellationSignal, final PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
            final PrintAttributes pdfAttributes;
            if (this.mIsMinMarginsHandlingCorrect) {
                pdfAttributes = attributes;
            } else {
                pdfAttributes = copyAttributes(attributes).setMinMargins(new PrintAttributes.Margins(0, 0, 0, 0)).build();
            }
            new AsyncTask<Void, Void, Throwable>() { // from class: android.support.v4.print.PrintHelper.PrintHelperApi19.2
                /* JADX INFO: Access modifiers changed from: protected */
                public Throwable doInBackground(Void... params) {
                    RectF contentRect;
                    try {
                        if (cancellationSignal.isCanceled()) {
                            return null;
                        }
                        PrintedPdfDocument pdfDocument = new PrintedPdfDocument(PrintHelperApi19.this.mContext, pdfAttributes);
                        Bitmap maybeGrayscale = PrintHelperApi19.this.convertBitmapForColorMode(bitmap, pdfAttributes.getColorMode());
                        if (cancellationSignal.isCanceled()) {
                            return null;
                        }
                        PdfDocument.Page page = pdfDocument.startPage(1);
                        if (PrintHelperApi19.this.mIsMinMarginsHandlingCorrect) {
                            contentRect = new RectF(page.getInfo().getContentRect());
                        } else {
                            PrintedPdfDocument dummyDocument = new PrintedPdfDocument(PrintHelperApi19.this.mContext, attributes);
                            PdfDocument.Page dummyPage = dummyDocument.startPage(1);
                            contentRect = new RectF(dummyPage.getInfo().getContentRect());
                            dummyDocument.finishPage(dummyPage);
                            dummyDocument.close();
                        }
                        Matrix matrix = PrintHelperApi19.this.getMatrix(maybeGrayscale.getWidth(), maybeGrayscale.getHeight(), contentRect, fittingMode);
                        if (!PrintHelperApi19.this.mIsMinMarginsHandlingCorrect) {
                            matrix.postTranslate(contentRect.left, contentRect.top);
                            page.getCanvas().clipRect(contentRect);
                        }
                        page.getCanvas().drawBitmap(maybeGrayscale, matrix, null);
                        pdfDocument.finishPage(page);
                        if (cancellationSignal.isCanceled()) {
                            pdfDocument.close();
                            if (fileDescriptor != null) {
                                try {
                                    fileDescriptor.close();
                                } catch (IOException e) {
                                }
                            }
                            if (maybeGrayscale == bitmap) {
                                return null;
                            }
                            maybeGrayscale.recycle();
                            return null;
                        }
                        pdfDocument.writeTo(new FileOutputStream(fileDescriptor.getFileDescriptor()));
                        pdfDocument.close();
                        if (fileDescriptor != null) {
                            try {
                                fileDescriptor.close();
                            } catch (IOException e2) {
                            }
                        }
                        if (maybeGrayscale == bitmap) {
                            return null;
                        }
                        maybeGrayscale.recycle();
                        return null;
                    } catch (Throwable th) {
                        return th;
                    }
                }

                /* JADX INFO: Access modifiers changed from: protected */
                public void onPostExecute(Throwable throwable) {
                    if (cancellationSignal.isCanceled()) {
                        writeResultCallback.onWriteCancelled();
                    } else if (throwable == null) {
                        writeResultCallback.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});
                    } else {
                        Log.e(PrintHelperApi19.LOG_TAG, "Error writing printed content", throwable);
                        writeResultCallback.onWriteFailed(null);
                    }
                }
            }.execute(new Void[0]);
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public void printBitmap(String jobName, Uri imageFile, OnPrintFinishCallback callback) throws FileNotFoundException {
            PrintDocumentAdapter printDocumentAdapter = new AnonymousClass3(jobName, imageFile, callback, this.mScaleMode);
            PrintManager printManager = (PrintManager) this.mContext.getSystemService("print");
            PrintAttributes.Builder builder = new PrintAttributes.Builder();
            builder.setColorMode(this.mColorMode);
            if (this.mOrientation == 1 || this.mOrientation == 0) {
                builder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE);
            } else if (this.mOrientation == 2) {
                builder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_PORTRAIT);
            }
            printManager.print(jobName, printDocumentAdapter, builder.build());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: android.support.v4.print.PrintHelper$PrintHelperApi19$3  reason: invalid class name */
        /* loaded from: classes.dex */
        public class AnonymousClass3 extends PrintDocumentAdapter {
            private PrintAttributes mAttributes;
            Bitmap mBitmap = null;
            AsyncTask<Uri, Boolean, Bitmap> mLoadBitmap;
            final /* synthetic */ OnPrintFinishCallback val$callback;
            final /* synthetic */ int val$fittingMode;
            final /* synthetic */ Uri val$imageFile;
            final /* synthetic */ String val$jobName;

            AnonymousClass3(String str, Uri uri, OnPrintFinishCallback onPrintFinishCallback, int i) {
                this.val$jobName = str;
                this.val$imageFile = uri;
                this.val$callback = onPrintFinishCallback;
                this.val$fittingMode = i;
            }

            /* JADX WARN: Type inference failed for: r0v3, types: [android.support.v4.print.PrintHelper$PrintHelperApi19$3$1] */
            @Override // android.print.PrintDocumentAdapter
            public void onLayout(final PrintAttributes oldPrintAttributes, final PrintAttributes newPrintAttributes, final CancellationSignal cancellationSignal, final PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle) {
                boolean changed = true;
                synchronized (this) {
                    this.mAttributes = newPrintAttributes;
                }
                if (cancellationSignal.isCanceled()) {
                    layoutResultCallback.onLayoutCancelled();
                } else if (this.mBitmap != null) {
                    PrintDocumentInfo info = new PrintDocumentInfo.Builder(this.val$jobName).setContentType(1).setPageCount(1).build();
                    if (newPrintAttributes.equals(oldPrintAttributes)) {
                        changed = false;
                    }
                    layoutResultCallback.onLayoutFinished(info, changed);
                } else {
                    this.mLoadBitmap = new AsyncTask<Uri, Boolean, Bitmap>() { // from class: android.support.v4.print.PrintHelper.PrintHelperApi19.3.1
                        @Override // android.os.AsyncTask
                        protected void onPreExecute() {
                            cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: android.support.v4.print.PrintHelper.PrintHelperApi19.3.1.1
                                @Override // android.os.CancellationSignal.OnCancelListener
                                public void onCancel() {
                                    AnonymousClass3.this.cancelLoad();
                                    cancel(false);
                                }
                            });
                        }

                        /* JADX INFO: Access modifiers changed from: protected */
                        public Bitmap doInBackground(Uri... uris) {
                            try {
                                return PrintHelperApi19.this.loadConstrainedBitmap(AnonymousClass3.this.val$imageFile);
                            } catch (FileNotFoundException e) {
                                return null;
                            }
                        }

                        /* JADX INFO: Access modifiers changed from: protected */
                        public void onPostExecute(Bitmap bitmap) {
                            PrintAttributes.MediaSize mediaSize;
                            super.onPostExecute((AnonymousClass1) bitmap);
                            if (bitmap != null && (!PrintHelperApi19.this.mPrintActivityRespectsOrientation || PrintHelperApi19.this.mOrientation == 0)) {
                                synchronized (this) {
                                    mediaSize = AnonymousClass3.this.mAttributes.getMediaSize();
                                }
                                if (!(mediaSize == null || mediaSize.isPortrait() == PrintHelperApi19.isPortrait(bitmap))) {
                                    Matrix rotation = new Matrix();
                                    rotation.postRotate(90.0f);
                                    bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), rotation, true);
                                }
                            }
                            AnonymousClass3.this.mBitmap = bitmap;
                            if (bitmap != null) {
                                layoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(AnonymousClass3.this.val$jobName).setContentType(1).setPageCount(1).build(), !newPrintAttributes.equals(oldPrintAttributes));
                            } else {
                                layoutResultCallback.onLayoutFailed(null);
                            }
                            AnonymousClass3.this.mLoadBitmap = null;
                        }

                        /* JADX INFO: Access modifiers changed from: protected */
                        public void onCancelled(Bitmap result) {
                            layoutResultCallback.onLayoutCancelled();
                            AnonymousClass3.this.mLoadBitmap = null;
                        }
                    }.execute(new Uri[0]);
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void cancelLoad() {
                synchronized (PrintHelperApi19.this.mLock) {
                    if (PrintHelperApi19.this.mDecodeOptions != null) {
                        PrintHelperApi19.this.mDecodeOptions.requestCancelDecode();
                        PrintHelperApi19.this.mDecodeOptions = null;
                    }
                }
            }

            @Override // android.print.PrintDocumentAdapter
            public void onFinish() {
                super.onFinish();
                cancelLoad();
                if (this.mLoadBitmap != null) {
                    this.mLoadBitmap.cancel(true);
                }
                if (this.val$callback != null) {
                    this.val$callback.onFinish();
                }
                if (this.mBitmap != null) {
                    this.mBitmap.recycle();
                    this.mBitmap = null;
                }
            }

            @Override // android.print.PrintDocumentAdapter
            public void onWrite(PageRange[] pageRanges, ParcelFileDescriptor fileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
                PrintHelperApi19.this.writeBitmap(this.mAttributes, this.val$fittingMode, this.mBitmap, fileDescriptor, cancellationSignal, writeResultCallback);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Bitmap loadConstrainedBitmap(Uri uri) throws FileNotFoundException {
            BitmapFactory.Options decodeOptions;
            Bitmap bitmap = null;
            if (uri == null || this.mContext == null) {
                throw new IllegalArgumentException("bad argument to getScaledBitmap");
            }
            BitmapFactory.Options opt = new BitmapFactory.Options();
            opt.inJustDecodeBounds = true;
            loadBitmap(uri, opt);
            int w = opt.outWidth;
            int h = opt.outHeight;
            if (w > 0 && h > 0) {
                int imageSide = Math.max(w, h);
                int sampleSize = 1;
                while (imageSide > 3500) {
                    imageSide >>>= 1;
                    sampleSize <<= 1;
                }
                if (sampleSize > 0 && Math.min(w, h) / sampleSize > 0) {
                    synchronized (this.mLock) {
                        try {
                            this.mDecodeOptions = new BitmapFactory.Options();
                            this.mDecodeOptions.inMutable = true;
                            this.mDecodeOptions.inSampleSize = sampleSize;
                            decodeOptions = this.mDecodeOptions;
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                    try {
                        bitmap = loadBitmap(uri, decodeOptions);
                        synchronized (this.mLock) {
                            try {
                                this.mDecodeOptions = null;
                            } catch (Throwable th2) {
                                throw th2;
                            }
                        }
                    } catch (Throwable th3) {
                        synchronized (this.mLock) {
                            try {
                                this.mDecodeOptions = null;
                                throw th3;
                            } catch (Throwable th4) {
                                throw th4;
                            }
                        }
                    }
                }
            }
            return bitmap;
        }

        private Bitmap loadBitmap(Uri uri, BitmapFactory.Options o) throws FileNotFoundException {
            if (uri == null || this.mContext == null) {
                throw new IllegalArgumentException("bad argument to loadBitmap");
            }
            InputStream is = null;
            try {
                is = this.mContext.getContentResolver().openInputStream(uri);
                return BitmapFactory.decodeStream(is, null, o);
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException t) {
                        Log.w(LOG_TAG, "close fail ", t);
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Bitmap convertBitmapForColorMode(Bitmap original, int colorMode) {
            if (colorMode != 1) {
                return original;
            }
            Bitmap grayscale = Bitmap.createBitmap(original.getWidth(), original.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(grayscale);
            Paint p = new Paint();
            ColorMatrix cm = new ColorMatrix();
            cm.setSaturation(0.0f);
            p.setColorFilter(new ColorMatrixColorFilter(cm));
            c.drawBitmap(original, 0.0f, 0.0f, p);
            c.setBitmap(null);
            return grayscale;
        }
    }

    @RequiresApi(20)
    /* loaded from: classes.dex */
    private static class PrintHelperApi20 extends PrintHelperApi19 {
        PrintHelperApi20(Context context) {
            super(context);
            this.mPrintActivityRespectsOrientation = false;
        }
    }

    @RequiresApi(23)
    /* loaded from: classes.dex */
    private static class PrintHelperApi23 extends PrintHelperApi20 {
        @Override // android.support.v4.print.PrintHelper.PrintHelperApi19
        protected PrintAttributes.Builder copyAttributes(PrintAttributes other) {
            PrintAttributes.Builder b = super.copyAttributes(other);
            if (other.getDuplexMode() != 0) {
                b.setDuplexMode(other.getDuplexMode());
            }
            return b;
        }

        PrintHelperApi23(Context context) {
            super(context);
            this.mIsMinMarginsHandlingCorrect = false;
        }
    }

    @RequiresApi(24)
    /* loaded from: classes.dex */
    private static class PrintHelperApi24 extends PrintHelperApi23 {
        PrintHelperApi24(Context context) {
            super(context);
            this.mIsMinMarginsHandlingCorrect = true;
            this.mPrintActivityRespectsOrientation = true;
        }
    }

    public PrintHelper(Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            this.mImpl = new PrintHelperApi24(context);
        } else if (Build.VERSION.SDK_INT >= 23) {
            this.mImpl = new PrintHelperApi23(context);
        } else if (Build.VERSION.SDK_INT >= 20) {
            this.mImpl = new PrintHelperApi20(context);
        } else if (Build.VERSION.SDK_INT >= 19) {
            this.mImpl = new PrintHelperApi19(context);
        } else {
            this.mImpl = new PrintHelperStub();
        }
    }

    public void setScaleMode(int scaleMode) {
        this.mImpl.setScaleMode(scaleMode);
    }

    public int getScaleMode() {
        return this.mImpl.getScaleMode();
    }

    public void setColorMode(int colorMode) {
        this.mImpl.setColorMode(colorMode);
    }

    public int getColorMode() {
        return this.mImpl.getColorMode();
    }

    public void setOrientation(int orientation) {
        this.mImpl.setOrientation(orientation);
    }

    public int getOrientation() {
        return this.mImpl.getOrientation();
    }

    public void printBitmap(String jobName, Bitmap bitmap) {
        this.mImpl.printBitmap(jobName, bitmap, (OnPrintFinishCallback) null);
    }

    public void printBitmap(String jobName, Bitmap bitmap, OnPrintFinishCallback callback) {
        this.mImpl.printBitmap(jobName, bitmap, callback);
    }

    public void printBitmap(String jobName, Uri imageFile) throws FileNotFoundException {
        this.mImpl.printBitmap(jobName, imageFile, (OnPrintFinishCallback) null);
    }

    public void printBitmap(String jobName, Uri imageFile, OnPrintFinishCallback callback) throws FileNotFoundException {
        this.mImpl.printBitmap(jobName, imageFile, callback);
    }
}
