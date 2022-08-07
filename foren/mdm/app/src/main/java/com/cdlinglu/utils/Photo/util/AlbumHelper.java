package com.cdlinglu.utils.Photo.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;
import com.yolanda.nohttp.db.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class AlbumHelper {
    Context context;
    ContentResolver cr;
    final String TAG = getClass().getSimpleName();
    HashMap<String, String> thumbnailList = new HashMap<>();
    List<HashMap<String, String>> albumList = new ArrayList();
    HashMap<String, ImageBucket> bucketList = new HashMap<>();
    boolean hasBuildImagesBucketList = false;

    public void init(Context context) {
        if (this.context == null) {
            this.context = context;
            this.cr = context.getContentResolver();
        }
    }

    private void getThumbnail() {
        getThumbnailColumnData(this.cr.query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, new String[]{Field.ID, "image_id", "_data"}, null, null, null));
    }

    private void getThumbnailColumnData(Cursor cur) {
        if (cur.moveToFirst()) {
            int _idColumn = cur.getColumnIndex(Field.ID);
            int image_idColumn = cur.getColumnIndex("image_id");
            int dataColumn = cur.getColumnIndex("_data");
            do {
                cur.getInt(_idColumn);
                int image_id = cur.getInt(image_idColumn);
                this.thumbnailList.put("" + image_id, cur.getString(dataColumn));
            } while (cur.moveToNext());
        }
    }

    void getAlbum() {
        getAlbumColumnData(this.cr.query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, new String[]{Field.ID, "album", "album_art", "album_key", "artist", "numsongs"}, null, null, null));
    }

    private void getAlbumColumnData(Cursor cur) {
        if (cur.moveToFirst()) {
            int _idColumn = cur.getColumnIndex(Field.ID);
            int albumColumn = cur.getColumnIndex("album");
            int albumArtColumn = cur.getColumnIndex("album_art");
            int albumKeyColumn = cur.getColumnIndex("album_key");
            int artistColumn = cur.getColumnIndex("artist");
            int numOfSongsColumn = cur.getColumnIndex("numsongs");
            do {
                int _id = cur.getInt(_idColumn);
                String album = cur.getString(albumColumn);
                String albumArt = cur.getString(albumArtColumn);
                String albumKey = cur.getString(albumKeyColumn);
                String artist = cur.getString(artistColumn);
                int numOfSongs = cur.getInt(numOfSongsColumn);
                Log.i(this.TAG, _id + " album:" + album + " albumArt:" + albumArt + "albumKey: " + albumKey + " artist: " + artist + " numOfSongs: " + numOfSongs + "---");
                HashMap<String, String> hash = new HashMap<>();
                hash.put(Field.ID, _id + "");
                hash.put("album", album);
                hash.put("albumArt", albumArt);
                hash.put("albumKey", albumKey);
                hash.put("artist", artist);
                hash.put("numOfSongs", numOfSongs + "");
                this.albumList.add(hash);
            } while (cur.moveToNext());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0173  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void buildImagesBucketList() {
        /*
            Method dump skipped, instructions count: 566
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cdlinglu.utils.Photo.util.AlbumHelper.buildImagesBucketList():void");
    }

    public List<ImageBucket> getImagesBucketList(boolean refresh) {
        if (refresh || (!refresh && !this.hasBuildImagesBucketList)) {
            buildImagesBucketList();
        }
        List<ImageBucket> tmpList = new ArrayList<>();
        for (Map.Entry<String, ImageBucket> entry : this.bucketList.entrySet()) {
            tmpList.add(entry.getValue());
        }
        return tmpList;
    }

    String getOriginalImagePath(String image_id) {
        Log.i(this.TAG, "---(^o^)----" + image_id);
        Cursor cursor = this.cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{Field.ID, "_data"}, "_id=" + image_id, null, null);
        if (cursor == null) {
            return null;
        }
        cursor.moveToFirst();
        return cursor.getString(cursor.getColumnIndex("_data"));
    }
}
