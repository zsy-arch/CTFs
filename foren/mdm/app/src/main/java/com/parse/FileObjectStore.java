package com.parse;

import bolts.Task;
import com.parse.ParseObject;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;
import org.json.JSONException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class FileObjectStore<T extends ParseObject> implements ParseObjectStore<T> {
    private final String className;
    private final ParseObjectCurrentCoder coder;
    private final File file;

    private static ParseObjectSubclassingController getSubclassingController() {
        return ParseCorePlugins.getInstance().getSubclassingController();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void saveToDisk(ParseObjectCurrentCoder coder, ParseObject current, File file) {
        try {
            ParseFileUtils.writeJSONObjectToFile(file, coder.encode(current.getState(), null, PointerEncoder.get()));
        } catch (IOException e) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T extends ParseObject> T getFromDisk(ParseObjectCurrentCoder coder, File file, ParseObject.State.Init builder) {
        try {
            return (T) ParseObject.from(coder.decode(builder, ParseFileUtils.readFileToJSONObject(file), ParseDecoder.get()).isComplete(true).build());
        } catch (IOException | JSONException e) {
            return null;
        }
    }

    public FileObjectStore(Class<T> clazz, File file, ParseObjectCurrentCoder coder) {
        this(getSubclassingController().getClassName(clazz), file, coder);
    }

    public FileObjectStore(String className, File file, ParseObjectCurrentCoder coder) {
        this.className = className;
        this.file = file;
        this.coder = coder;
    }

    @Override // com.parse.ParseObjectStore
    public Task<Void> setAsync(final T object) {
        return Task.call(new Callable<Void>() { // from class: com.parse.FileObjectStore.1
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                FileObjectStore.saveToDisk(FileObjectStore.this.coder, object, FileObjectStore.this.file);
                return null;
            }
        }, ParseExecutors.io());
    }

    @Override // com.parse.ParseObjectStore
    public Task<T> getAsync() {
        return Task.call(new Callable<T>() { // from class: com.parse.FileObjectStore.2
            @Override // java.util.concurrent.Callable
            public T call() throws Exception {
                if (!FileObjectStore.this.file.exists()) {
                    return null;
                }
                return (T) FileObjectStore.getFromDisk(FileObjectStore.this.coder, FileObjectStore.this.file, ParseObject.State.newBuilder(FileObjectStore.this.className));
            }
        }, ParseExecutors.io());
    }

    @Override // com.parse.ParseObjectStore
    public Task<Boolean> existsAsync() {
        return Task.call(new Callable<Boolean>() { // from class: com.parse.FileObjectStore.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Boolean call() throws Exception {
                return Boolean.valueOf(FileObjectStore.this.file.exists());
            }
        }, ParseExecutors.io());
    }

    @Override // com.parse.ParseObjectStore
    public Task<Void> deleteAsync() {
        return Task.call(new Callable<Void>() { // from class: com.parse.FileObjectStore.4
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                if (!FileObjectStore.this.file.exists() || ParseFileUtils.deleteQuietly(FileObjectStore.this.file)) {
                    return null;
                }
                throw new RuntimeException("Unable to delete");
            }
        }, ParseExecutors.io());
    }
}
