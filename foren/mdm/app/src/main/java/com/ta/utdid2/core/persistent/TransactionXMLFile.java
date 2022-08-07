package com.ta.utdid2.core.persistent;

import com.ta.utdid2.core.persistent.MySharedPreferences;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes2.dex */
public class TransactionXMLFile {
    private static final Object GLOBAL_COMMIT_LOCK = new Object();
    public static final int MODE_PRIVATE = 0;
    public static final int MODE_WORLD_READABLE = 1;
    public static final int MODE_WORLD_WRITEABLE = 2;
    private File mPreferencesDir;
    private final Object mSync = new Object();
    private HashMap<File, MySharedPreferencesImpl> sSharedPrefs = new HashMap<>();

    public TransactionXMLFile(String dir) {
        if (dir == null || dir.length() <= 0) {
            throw new RuntimeException("Directory can not be empty");
        }
        this.mPreferencesDir = new File(dir);
    }

    private File makeFilename(File base, String name) {
        if (name.indexOf(File.separatorChar) < 0) {
            return new File(base, name);
        }
        throw new IllegalArgumentException("File " + name + " contains a path separator");
    }

    private File getPreferencesDir() {
        File file;
        synchronized (this.mSync) {
            file = this.mPreferencesDir;
        }
        return file;
    }

    private File getSharedPrefsFile(String name) {
        return makeFilename(getPreferencesDir(), String.valueOf(name) + ".xml");
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x0054 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.ta.utdid2.core.persistent.MySharedPreferences getMySharedPreferences(java.lang.String r17, int r18) {
        /*
            Method dump skipped, instructions count: 199
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ta.utdid2.core.persistent.TransactionXMLFile.getMySharedPreferences(java.lang.String, int):com.ta.utdid2.core.persistent.MySharedPreferences");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static File makeBackupFile(File prefsFile) {
        return new File(String.valueOf(prefsFile.getPath()) + ".bak");
    }

    /* loaded from: classes2.dex */
    private static final class MySharedPreferencesImpl implements MySharedPreferences {
        private static final Object mContent = new Object();
        private boolean hasChange = false;
        private final File mBackupFile;
        private final File mFile;
        private WeakHashMap<MySharedPreferences.OnSharedPreferenceChangeListener, Object> mListeners;
        private Map mMap;
        private final int mMode;

        MySharedPreferencesImpl(File file, int mode, Map initialContents) {
            this.mFile = file;
            this.mBackupFile = TransactionXMLFile.makeBackupFile(file);
            this.mMode = mode;
            this.mMap = initialContents == null ? new HashMap() : initialContents;
            this.mListeners = new WeakHashMap<>();
        }

        @Override // com.ta.utdid2.core.persistent.MySharedPreferences
        public boolean checkFile() {
            return this.mFile != null && new File(this.mFile.getAbsolutePath()).exists();
        }

        public void setHasChange(boolean hasChange) {
            synchronized (this) {
                this.hasChange = hasChange;
            }
        }

        public boolean hasFileChanged() {
            boolean z;
            synchronized (this) {
                z = this.hasChange;
            }
            return z;
        }

        public void replace(Map newContents) {
            if (newContents != null) {
                synchronized (this) {
                    this.mMap = newContents;
                }
            }
        }

        @Override // com.ta.utdid2.core.persistent.MySharedPreferences
        public void registerOnSharedPreferenceChangeListener(MySharedPreferences.OnSharedPreferenceChangeListener listener) {
            synchronized (this) {
                this.mListeners.put(listener, mContent);
            }
        }

        @Override // com.ta.utdid2.core.persistent.MySharedPreferences
        public void unregisterOnSharedPreferenceChangeListener(MySharedPreferences.OnSharedPreferenceChangeListener listener) {
            synchronized (this) {
                this.mListeners.remove(listener);
            }
        }

        @Override // com.ta.utdid2.core.persistent.MySharedPreferences
        public Map<String, ?> getAll() {
            HashMap hashMap;
            synchronized (this) {
                hashMap = new HashMap(this.mMap);
            }
            return hashMap;
        }

        @Override // com.ta.utdid2.core.persistent.MySharedPreferences
        public String getString(String key, String defValue) {
            String v;
            synchronized (this) {
                v = (String) this.mMap.get(key);
                if (v == null) {
                    v = defValue;
                }
            }
            return v;
        }

        @Override // com.ta.utdid2.core.persistent.MySharedPreferences
        public int getInt(String key, int defValue) {
            synchronized (this) {
                Integer v = (Integer) this.mMap.get(key);
                if (v != null) {
                    defValue = v.intValue();
                }
            }
            return defValue;
        }

        @Override // com.ta.utdid2.core.persistent.MySharedPreferences
        public long getLong(String key, long defValue) {
            synchronized (this) {
                Long v = (Long) this.mMap.get(key);
                if (v != null) {
                    defValue = v.longValue();
                }
            }
            return defValue;
        }

        @Override // com.ta.utdid2.core.persistent.MySharedPreferences
        public float getFloat(String key, float defValue) {
            synchronized (this) {
                Float v = (Float) this.mMap.get(key);
                if (v != null) {
                    defValue = v.floatValue();
                }
            }
            return defValue;
        }

        @Override // com.ta.utdid2.core.persistent.MySharedPreferences
        public boolean getBoolean(String key, boolean defValue) {
            synchronized (this) {
                Boolean v = (Boolean) this.mMap.get(key);
                if (v != null) {
                    defValue = v.booleanValue();
                }
            }
            return defValue;
        }

        @Override // com.ta.utdid2.core.persistent.MySharedPreferences
        public boolean contains(String key) {
            boolean containsKey;
            synchronized (this) {
                containsKey = this.mMap.containsKey(key);
            }
            return containsKey;
        }

        /* loaded from: classes2.dex */
        public final class EditorImpl implements MySharedPreferences.MyEditor {
            private final Map<String, Object> mModified = new HashMap();
            private boolean mClear = false;

            public EditorImpl() {
            }

            @Override // com.ta.utdid2.core.persistent.MySharedPreferences.MyEditor
            public MySharedPreferences.MyEditor putString(String key, String value) {
                synchronized (this) {
                    this.mModified.put(key, value);
                }
                return this;
            }

            @Override // com.ta.utdid2.core.persistent.MySharedPreferences.MyEditor
            public MySharedPreferences.MyEditor putInt(String key, int value) {
                synchronized (this) {
                    this.mModified.put(key, Integer.valueOf(value));
                }
                return this;
            }

            @Override // com.ta.utdid2.core.persistent.MySharedPreferences.MyEditor
            public MySharedPreferences.MyEditor putLong(String key, long value) {
                synchronized (this) {
                    this.mModified.put(key, Long.valueOf(value));
                }
                return this;
            }

            @Override // com.ta.utdid2.core.persistent.MySharedPreferences.MyEditor
            public MySharedPreferences.MyEditor putFloat(String key, float value) {
                synchronized (this) {
                    this.mModified.put(key, Float.valueOf(value));
                }
                return this;
            }

            @Override // com.ta.utdid2.core.persistent.MySharedPreferences.MyEditor
            public MySharedPreferences.MyEditor putBoolean(String key, boolean value) {
                synchronized (this) {
                    this.mModified.put(key, Boolean.valueOf(value));
                }
                return this;
            }

            @Override // com.ta.utdid2.core.persistent.MySharedPreferences.MyEditor
            public MySharedPreferences.MyEditor remove(String key) {
                synchronized (this) {
                    this.mModified.put(key, this);
                }
                return this;
            }

            @Override // com.ta.utdid2.core.persistent.MySharedPreferences.MyEditor
            public MySharedPreferences.MyEditor clear() {
                synchronized (this) {
                    this.mClear = true;
                }
                return this;
            }

            @Override // com.ta.utdid2.core.persistent.MySharedPreferences.MyEditor
            public boolean commit() {
                Throwable th;
                boolean hasListeners = true;
                List<String> keysModified = null;
                Set<MySharedPreferences.OnSharedPreferenceChangeListener> listeners = null;
                synchronized (TransactionXMLFile.GLOBAL_COMMIT_LOCK) {
                    try {
                        if (MySharedPreferencesImpl.this.mListeners.size() <= 0) {
                            hasListeners = false;
                        }
                        if (hasListeners) {
                            List<String> keysModified2 = new ArrayList<>();
                            try {
                                listeners = new HashSet<>(MySharedPreferencesImpl.this.mListeners.keySet());
                                keysModified = keysModified2;
                            } catch (Throwable th2) {
                                th = th2;
                                throw th;
                            }
                        }
                        synchronized (this) {
                            if (this.mClear) {
                                MySharedPreferencesImpl.this.mMap.clear();
                                this.mClear = false;
                            }
                            for (Map.Entry<String, Object> e : this.mModified.entrySet()) {
                                String k = e.getKey();
                                Object v = e.getValue();
                                if (v == this) {
                                    MySharedPreferencesImpl.this.mMap.remove(k);
                                } else {
                                    MySharedPreferencesImpl.this.mMap.put(k, v);
                                }
                                if (hasListeners) {
                                    keysModified.add(k);
                                }
                            }
                            this.mModified.clear();
                        }
                        boolean returnValue = MySharedPreferencesImpl.this.writeFileLocked();
                        if (returnValue) {
                            MySharedPreferencesImpl.this.setHasChange(true);
                        }
                        if (hasListeners) {
                            for (int i = keysModified.size() - 1; i >= 0; i--) {
                                String key = keysModified.get(i);
                                for (MySharedPreferences.OnSharedPreferenceChangeListener listener : listeners) {
                                    if (listener != null) {
                                        listener.onSharedPreferenceChanged(MySharedPreferencesImpl.this, key);
                                    }
                                }
                            }
                        }
                        return returnValue;
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
            }
        }

        @Override // com.ta.utdid2.core.persistent.MySharedPreferences
        public MySharedPreferences.MyEditor edit() {
            return new EditorImpl();
        }

        private FileOutputStream createFileOutputStream(File file) {
            FileOutputStream str = null;
            try {
                str = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                if (!file.getParentFile().mkdir()) {
                    return null;
                }
                try {
                    str = new FileOutputStream(file);
                } catch (FileNotFoundException e2) {
                }
            }
            return str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean writeFileLocked() {
            if (this.mFile.exists()) {
                if (this.mBackupFile.exists()) {
                    this.mFile.delete();
                } else if (!this.mFile.renameTo(this.mBackupFile)) {
                    return false;
                }
            }
            try {
                FileOutputStream str = createFileOutputStream(this.mFile);
                if (str == null) {
                    return false;
                }
                XmlUtils.writeMapXml(this.mMap, str);
                str.close();
                this.mBackupFile.delete();
                return true;
            } catch (IOException | XmlPullParserException e) {
                if (!this.mFile.exists()) {
                    return false;
                }
                this.mFile.delete();
                return false;
            }
        }
    }
}
