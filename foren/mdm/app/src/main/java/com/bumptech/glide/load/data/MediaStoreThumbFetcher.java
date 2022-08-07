package com.bumptech.glide.load.data;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.resource.bitmap.ImageHeaderParser;
import com.em.ui.EditActivity;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class MediaStoreThumbFetcher implements DataFetcher<InputStream> {
    private static final ThumbnailStreamOpenerFactory DEFAULT_FACTORY = new ThumbnailStreamOpenerFactory();
    private static final int MINI_HEIGHT = 384;
    private static final int MINI_WIDTH = 512;
    private static final String TAG = "MediaStoreThumbFetcher";
    private final Context context;
    private final DataFetcher<InputStream> defaultFetcher;
    private final ThumbnailStreamOpenerFactory factory;
    private final int height;
    private InputStream inputStream;
    private final Uri mediaStoreUri;
    private final int width;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface ThumbnailQuery {
        Cursor queryPath(Context context, Uri uri);
    }

    public MediaStoreThumbFetcher(Context context, Uri mediaStoreUri, DataFetcher<InputStream> defaultFetcher, int width, int height) {
        this(context, mediaStoreUri, defaultFetcher, width, height, DEFAULT_FACTORY);
    }

    MediaStoreThumbFetcher(Context context, Uri mediaStoreUri, DataFetcher<InputStream> defaultFetcher, int width, int height, ThumbnailStreamOpenerFactory factory) {
        this.context = context;
        this.mediaStoreUri = mediaStoreUri;
        this.defaultFetcher = defaultFetcher;
        this.width = width;
        this.height = height;
        this.factory = factory;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.bumptech.glide.load.data.DataFetcher
    public InputStream loadData(Priority priority) throws Exception {
        ThumbnailStreamOpener fetcher = this.factory.build(this.mediaStoreUri, this.width, this.height);
        if (fetcher != null) {
            this.inputStream = openThumbInputStream(fetcher);
        }
        if (this.inputStream == null) {
            this.inputStream = this.defaultFetcher.loadData(priority);
        }
        return this.inputStream;
    }

    private InputStream openThumbInputStream(ThumbnailStreamOpener fetcher) {
        InputStream result;
        try {
            result = fetcher.open(this.context, this.mediaStoreUri);
        } catch (FileNotFoundException e) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Failed to find thumbnail file", e);
            }
            result = null;
        }
        int orientation = -1;
        if (result != null) {
            orientation = fetcher.getOrientation(this.context, this.mediaStoreUri);
        }
        if (orientation != -1) {
            return new ExifOrientationStream(result, orientation);
        }
        return result;
    }

    @Override // com.bumptech.glide.load.data.DataFetcher
    public void cleanup() {
        if (this.inputStream != null) {
            try {
                this.inputStream.close();
            } catch (IOException e) {
            }
        }
        this.defaultFetcher.cleanup();
    }

    @Override // com.bumptech.glide.load.data.DataFetcher
    public String getId() {
        return this.mediaStoreUri.toString();
    }

    @Override // com.bumptech.glide.load.data.DataFetcher
    public void cancel() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isMediaStoreUri(Uri uri) {
        return uri != null && EditActivity.CONTENT.equals(uri.getScheme()) && "media".equals(uri.getAuthority());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isMediaStoreVideo(Uri uri) {
        return isMediaStoreUri(uri) && uri.getPathSegments().contains("video");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class FileService {
        FileService() {
        }

        public boolean exists(File file) {
            return file.exists();
        }

        public long length(File file) {
            return file.length();
        }

        public File get(String path) {
            return new File(path);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class ThumbnailStreamOpener {
        private static final FileService DEFAULT_SERVICE = new FileService();
        private ThumbnailQuery query;
        private final FileService service;

        public ThumbnailStreamOpener(ThumbnailQuery query) {
            this(DEFAULT_SERVICE, query);
        }

        public ThumbnailStreamOpener(FileService service, ThumbnailQuery query) {
            this.service = service;
            this.query = query;
        }

        public int getOrientation(Context context, Uri uri) {
            int orientation = -1;
            InputStream is = null;
            try {
                try {
                    is = context.getContentResolver().openInputStream(uri);
                    orientation = new ImageHeaderParser(is).getOrientation();
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e) {
                        }
                    }
                } catch (IOException e2) {
                    if (Log.isLoggable(MediaStoreThumbFetcher.TAG, 3)) {
                        Log.d(MediaStoreThumbFetcher.TAG, "Failed to open uri: " + uri, e2);
                    }
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e3) {
                        }
                    }
                }
                return orientation;
            } catch (Throwable th) {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e4) {
                    }
                }
                throw th;
            }
        }

        public InputStream open(Context context, Uri uri) throws FileNotFoundException {
            Uri thumbnailUri = null;
            Cursor cursor = this.query.queryPath(context, uri);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        thumbnailUri = parseThumbUri(cursor);
                    }
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
            if (thumbnailUri != null) {
                return context.getContentResolver().openInputStream(thumbnailUri);
            }
            return null;
        }

        private Uri parseThumbUri(Cursor cursor) {
            String path = cursor.getString(0);
            if (TextUtils.isEmpty(path)) {
                return null;
            }
            File file = this.service.get(path);
            if (!this.service.exists(file) || this.service.length(file) <= 0) {
                return null;
            }
            return Uri.fromFile(file);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class ImageThumbnailQuery implements ThumbnailQuery {
        private static final String[] PATH_PROJECTION = {"_data"};
        private static final String PATH_SELECTION = "kind = 1 AND image_id = ?";

        ImageThumbnailQuery() {
        }

        @Override // com.bumptech.glide.load.data.MediaStoreThumbFetcher.ThumbnailQuery
        public Cursor queryPath(Context context, Uri uri) {
            return context.getContentResolver().query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, PATH_PROJECTION, PATH_SELECTION, new String[]{uri.getLastPathSegment()}, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class VideoThumbnailQuery implements ThumbnailQuery {
        private static final String[] PATH_PROJECTION = {"_data"};
        private static final String PATH_SELECTION = "kind = 1 AND video_id = ?";

        VideoThumbnailQuery() {
        }

        @Override // com.bumptech.glide.load.data.MediaStoreThumbFetcher.ThumbnailQuery
        public Cursor queryPath(Context context, Uri uri) {
            return context.getContentResolver().query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, PATH_PROJECTION, PATH_SELECTION, new String[]{uri.getLastPathSegment()}, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class ThumbnailStreamOpenerFactory {
        ThumbnailStreamOpenerFactory() {
        }

        public ThumbnailStreamOpener build(Uri uri, int width, int height) {
            if (!MediaStoreThumbFetcher.isMediaStoreUri(uri) || width > 512 || height > MediaStoreThumbFetcher.MINI_HEIGHT) {
                return null;
            }
            if (MediaStoreThumbFetcher.isMediaStoreVideo(uri)) {
                return new ThumbnailStreamOpener(new VideoThumbnailQuery());
            }
            return new ThumbnailStreamOpener(new ImageThumbnailQuery());
        }
    }
}
