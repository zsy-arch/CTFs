package com.autonavi.ae.gmap.style;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.autonavi.ae.gmap.style.MapTilsCacheAndResManager;
import com.autonavi.ae.gmap.utils.GLFileUtil;
import com.autonavi.ae.gmap.utils.GLMapUtil;
import com.autonavi.amap.mapcore.FileUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class MapTilsCacheAndResManagerImpl extends MapTilsCacheAndResManager {
    private static final int CREATE_DIR_COUNT = 5;
    private static final long Style_Update_Internal_Time = 43200000;
    private static volatile MapTilsCacheAndResManagerImpl instance = null;
    private String mCacheDir;
    private String mCachePath;
    private Context mContext;
    private String mMapExtResPath;

    public MapTilsCacheAndResManagerImpl(Context context, String str) {
        this.mCacheDir = "";
        this.mCacheDir = str;
        this.mContext = context;
    }

    @Override // com.autonavi.ae.gmap.style.MapTilsCacheAndResManager
    public void setMapCachePath(String str) {
        this.mCacheDir = str;
    }

    @Override // com.autonavi.ae.gmap.style.MapTilsCacheAndResManager
    public String getMapCachePath() {
        return this.mCacheDir;
    }

    @Override // com.autonavi.ae.gmap.style.MapTilsCacheAndResManager
    public void checkDir() {
        int i = 0;
        File file = new File(this.mCacheDir, MapTilsCacheAndResManager.MAP_RES_EXT_PATH_NAME);
        while (!file.exists()) {
            i++;
            if (i >= 5) {
                break;
            }
            file.mkdir();
        }
        this.mMapExtResPath = file.toString() + "/";
    }

    public synchronized void reset() {
        instance = null;
    }

    @Override // com.autonavi.ae.gmap.style.MapTilsCacheAndResManager
    public byte[] getStyleData(String str, MapTilsCacheAndResManager.RetStyleIconsFile retStyleIconsFile) {
        try {
            return getStyleIconsData(str, 1, retStyleIconsFile);
        } catch (IOException e) {
            return null;
        }
    }

    @Override // com.autonavi.ae.gmap.style.MapTilsCacheAndResManager
    public byte[] getIconsData(String str, MapTilsCacheAndResManager.RetStyleIconsFile retStyleIconsFile) {
        try {
            return getStyleIconsData(str, 2, retStyleIconsFile);
        } catch (IOException e) {
            return null;
        }
    }

    @Override // com.autonavi.ae.gmap.style.MapTilsCacheAndResManager
    public byte[] getOtherResData(String str) {
        try {
            InputStream open = this.mContext.getAssets().open("map_assets/" + str);
            int available = open.available();
            if (available == 0) {
                return null;
            }
            byte[] bArr = new byte[available];
            for (int i = 0; i < available; i += open.read(bArr, i, available - i)) {
            }
            open.close();
            return bArr;
        } catch (IOException e) {
            return null;
        } catch (OutOfMemoryError e2) {
            return null;
        }
    }

    @Override // com.autonavi.ae.gmap.style.MapTilsCacheAndResManager
    public byte[] getOtherResDataFromDisk(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return FileUtil.readFileContents(str);
    }

    @Override // com.autonavi.ae.gmap.style.MapTilsCacheAndResManager
    public void saveFile(String str, int i, int i2, byte[] bArr) {
        if (bArr != null && bArr.length > 0) {
            File[] listFiles = new File(this.mMapExtResPath).listFiles();
            if (listFiles != null) {
                String str2 = str + "_";
                int length = listFiles.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        break;
                    }
                    File file = listFiles[i3];
                    if (file.getName().contains(str2)) {
                        file.delete();
                        break;
                    }
                    i3++;
                }
            }
            addUdateRecorder(str + "_" + i);
            GLFileUtil.writeDatasToFile(this.mMapExtResPath + (str + "_" + i + "_" + i2 + ".data"), bArr);
        }
    }

    private String getFilePreName(String str) {
        if (str == null) {
            return null;
        }
        String[] split = str.split("_");
        if (split.length >= 3) {
            return split[0] + "_" + split[1] + "_" + split[2];
        }
        return null;
    }

    private void setRetFile(MapTilsCacheAndResManager.RetStyleIconsFile retStyleIconsFile, String str) {
        if (retStyleIconsFile != null) {
            retStyleIconsFile.fullName = str;
            String[] split = str.split("_|\\.");
            if (split.length >= 2) {
                retStyleIconsFile.name = split[0] + "_" + split[1];
                try {
                    retStyleIconsFile.clientVersion = Integer.parseInt(split[2]);
                    if (split.length > 3) {
                        retStyleIconsFile.serverVersion = Integer.parseInt(split[3]);
                    } else {
                        retStyleIconsFile.serverVersion = 1;
                    }
                } catch (NumberFormatException e) {
                    retStyleIconsFile.clientVersion = 1;
                    retStyleIconsFile.serverVersion = 1;
                }
            }
        }
    }

    private byte[] getStyleIconsData(String str, int i, MapTilsCacheAndResManager.RetStyleIconsFile retStyleIconsFile) throws IOException {
        String filePreName = getFilePreName(str);
        if (filePreName == null) {
            return null;
        }
        MapTilsCacheAndResManager.RetStyleIconsFile retStyleIconsFile2 = new MapTilsCacheAndResManager.RetStyleIconsFile();
        setRetFile(retStyleIconsFile2, str);
        File[] listFiles = new File(this.mMapExtResPath).listFiles();
        if (listFiles != null) {
            try {
                for (File file : listFiles) {
                    if (file.getName().contains(filePreName)) {
                        setRetFile(retStyleIconsFile, file.getName());
                        if (retStyleIconsFile2.serverVersion < retStyleIconsFile.serverVersion) {
                            byte[] readFileContents = GLFileUtil.readFileContents(file.getAbsolutePath());
                            if (readFileContents != null && readFileContents.length > 0) {
                                return readFileContents;
                            }
                        } else {
                            GLFileUtil.deleteFile(file);
                        }
                    }
                }
            } catch (OutOfMemoryError e) {
            }
        }
        setRetFile(retStyleIconsFile, str);
        return GLMapUtil.decodeAssetResData(this.mContext, "map_assets/" + str);
    }

    static void copyAssertToTmp(Context context, String str, File file) {
        byte[] decodeAssetResData;
        if (!file.exists()) {
            try {
                if (file.createNewFile() && (decodeAssetResData = GLMapUtil.decodeAssetResData(context, str)) != null) {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(decodeAssetResData);
                    fileOutputStream.close();
                }
            } catch (IOException e) {
            }
        }
    }

    @Override // com.autonavi.ae.gmap.style.MapTilsCacheAndResManager
    public void addUdateRecorder(String str) {
        SharedPreferences.Editor edit = this.mContext.getSharedPreferences("styles_icons_update_recorder", 0).edit();
        edit.putLong(str, System.currentTimeMillis());
        edit.commit();
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0023, code lost:
        if ((java.lang.System.currentTimeMillis() - r2) < com.autonavi.ae.gmap.style.MapTilsCacheAndResManagerImpl.Style_Update_Internal_Time) goto L_0x0025;
     */
    @Override // com.autonavi.ae.gmap.style.MapTilsCacheAndResManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean canUpate(java.lang.String r7) {
        /*
            r6 = this;
            r0 = 0
            monitor-enter(r6)
            r1 = 1
            android.content.Context r2 = r6.mContext     // Catch: all -> 0x0027
            java.lang.String r3 = "styles_icons_update_recorder"
            r4 = 0
            android.content.SharedPreferences r2 = r2.getSharedPreferences(r3, r4)     // Catch: all -> 0x0027
            r4 = -1
            long r2 = r2.getLong(r7, r4)     // Catch: all -> 0x0027
            r4 = 0
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 <= 0) goto L_0x002a
            long r4 = java.lang.System.currentTimeMillis()     // Catch: all -> 0x0027
            long r2 = r4 - r2
            r4 = 43200000(0x2932e00, double:2.1343636E-316)
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 >= 0) goto L_0x002a
        L_0x0025:
            monitor-exit(r6)
            return r0
        L_0x0027:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        L_0x002a:
            r0 = r1
            goto L_0x0025
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.ae.gmap.style.MapTilsCacheAndResManagerImpl.canUpate(java.lang.String):boolean");
    }

    @Override // com.autonavi.ae.gmap.style.MapTilsCacheAndResManager
    public byte[] getPolyIconFilePath(String str, MapTilsCacheAndResManager.RetStyleIconsFile retStyleIconsFile) {
        try {
            String filePreName = getFilePreName(str);
            if (TextUtils.isEmpty(filePreName)) {
                return null;
            }
            MapTilsCacheAndResManager.RetStyleIconsFile retStyleIconsFile2 = new MapTilsCacheAndResManager.RetStyleIconsFile();
            setRetFile(retStyleIconsFile2, str);
            File[] listFiles = new File(this.mMapExtResPath).listFiles();
            if (listFiles != null) {
                try {
                    for (File file : listFiles) {
                        if (file.getName().contains(filePreName)) {
                            setRetFile(retStyleIconsFile, file.getName());
                            if (retStyleIconsFile2.serverVersion >= retStyleIconsFile.serverVersion) {
                                GLFileUtil.deleteFile(file);
                            } else if (file.length() > 0) {
                                return (file.getAbsolutePath() + "\u0000").getBytes("utf-8");
                            }
                        }
                    }
                } catch (Throwable th) {
                }
            }
            copyAssetsFile(str);
            setRetFile(retStyleIconsFile, str);
            return (this.mMapExtResPath + str + "\u0000").getBytes("utf-8");
        } catch (Throwable th2) {
            th2.printStackTrace();
            return null;
        }
    }

    private void copyAssetsFile(String str) {
        try {
            File file = new File(this.mMapExtResPath + str);
            if (file == null || !file.exists()) {
                InputStream open = this.mContext.getAssets().open("map_assets/" + str);
                if (open.available() != 0) {
                    FileOutputStream fileOutputStream = new FileOutputStream(this.mMapExtResPath + str);
                    byte[] bArr = new byte[1024];
                    for (int read = open.read(bArr); read > 0; read = open.read(bArr)) {
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileOutputStream.flush();
                    open.close();
                    fileOutputStream.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
