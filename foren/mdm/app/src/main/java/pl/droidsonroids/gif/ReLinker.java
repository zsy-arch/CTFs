package pl.droidsonroids.gif;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: classes2.dex */
class ReLinker {
    private static final int COPY_BUFFER_SIZE = 8192;
    private static final String LIB_DIR = "lib";
    private static final String MAPPED_BASE_LIB_NAME = System.mapLibraryName("pl_droidsonroids_gif");
    private static final int MAX_TRIES = 5;

    private ReLinker() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SuppressLint({"UnsafeDynamicallyLoadedCode"})
    public static void loadLibrary(Context context) {
        synchronized (ReLinker.class) {
            System.load(unpackLibrary(context).getAbsolutePath());
        }
    }

    private static File unpackLibrary(Context context) {
        InputStream inputStream;
        FileOutputStream fileOut;
        Throwable th;
        String outputFileName = MAPPED_BASE_LIB_NAME + BuildConfig.VERSION_NAME;
        File outputFile = new File(context.getDir(LIB_DIR, 0), outputFileName);
        if (outputFile.isFile()) {
            return outputFile;
        }
        File cachedLibraryFile = new File(context.getCacheDir(), outputFileName);
        if (cachedLibraryFile.isFile()) {
            return cachedLibraryFile;
        }
        final String mappedSurfaceLibraryName = System.mapLibraryName("pl_droidsonroids_gif_surface");
        FilenameFilter filter = new FilenameFilter() { // from class: pl.droidsonroids.gif.ReLinker.1
            @Override // java.io.FilenameFilter
            public boolean accept(File dir, String filename) {
                return filename.startsWith(ReLinker.MAPPED_BASE_LIB_NAME) || filename.startsWith(mappedSurfaceLibraryName);
            }
        };
        clearOldLibraryFiles(outputFile, filter);
        clearOldLibraryFiles(cachedLibraryFile, filter);
        ZipFile zipFile = null;
        try {
            zipFile = openZipFile(new File(context.getApplicationInfo().sourceDir));
            int tries = 0;
            while (true) {
                int tries2 = tries + 1;
                if (tries >= 5) {
                    break;
                }
                ZipEntry libraryEntry = findLibraryEntry(zipFile);
                if (libraryEntry == null) {
                    throw new IllegalStateException("Library " + MAPPED_BASE_LIB_NAME + " for supported ABIs not found in APK file");
                }
                inputStream = null;
                fileOut = null;
                try {
                    inputStream = zipFile.getInputStream(libraryEntry);
                    FileOutputStream fileOut2 = new FileOutputStream(outputFile);
                    try {
                        copy(inputStream, fileOut2);
                        closeSilently(inputStream);
                        closeSilently(fileOut2);
                        setFilePermissions(outputFile);
                        break;
                    } catch (IOException e) {
                        fileOut = fileOut2;
                        if (tries2 > 2) {
                            outputFile = cachedLibraryFile;
                        }
                        closeSilently(inputStream);
                        closeSilently(fileOut);
                        tries = tries2;
                    } catch (Throwable th2) {
                        th = th2;
                        fileOut = fileOut2;
                        closeSilently(inputStream);
                        closeSilently(fileOut);
                        throw th;
                    }
                } catch (IOException e2) {
                } catch (Throwable th3) {
                    th = th3;
                }
                closeSilently(inputStream);
                closeSilently(fileOut);
                tries = tries2;
            }
        } finally {
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException e3) {
                }
            }
        }
        return outputFile;
    }

    @TargetApi(21)
    private static ZipEntry findLibraryEntry(ZipFile zipFile) {
        if (Build.VERSION.SDK_INT >= 21) {
            for (String abi : Build.SUPPORTED_ABIS) {
                ZipEntry libraryEntry = getEntry(zipFile, abi);
                if (libraryEntry != null) {
                    return libraryEntry;
                }
            }
        }
        return getEntry(zipFile, Build.CPU_ABI);
    }

    private static ZipEntry getEntry(ZipFile zipFile, String abi) {
        return zipFile.getEntry("lib/" + abi + "/" + MAPPED_BASE_LIB_NAME);
    }

    private static ZipFile openZipFile(File apkFile) {
        int tries = 0;
        ZipFile zipFile = null;
        while (true) {
            tries++;
            if (tries >= 5) {
                break;
            }
            try {
                zipFile = new ZipFile(apkFile, 1);
                break;
            } catch (IOException e) {
            }
        }
        if (zipFile != null) {
            return zipFile;
        }
        throw new IllegalStateException("Could not open APK file: " + apkFile.getAbsolutePath());
    }

    private static void clearOldLibraryFiles(File outputFile, FilenameFilter filter) {
        File[] fileList = outputFile.getParentFile().listFiles(filter);
        if (fileList != null) {
            for (File file : fileList) {
                file.delete();
            }
        }
    }

    @SuppressLint({"SetWorldReadable"})
    private static void setFilePermissions(File outputFile) {
        outputFile.setReadable(true, false);
        outputFile.setExecutable(true, false);
        outputFile.setWritable(true);
    }

    private static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] buf = new byte[8192];
        while (true) {
            int bytesRead = in.read(buf);
            if (bytesRead != -1) {
                out.write(buf, 0, bytesRead);
            } else {
                return;
            }
        }
    }

    private static void closeSilently(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }
}
