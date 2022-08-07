package com.mob.tools.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import com.mob.tools.MobLog;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* loaded from: classes2.dex */
public class SharePrefrenceHelper {
    private Context context;
    private SharedPreferences prefrence;

    public SharePrefrenceHelper(Context c) {
        this.context = c.getApplicationContext();
    }

    public Object get(String key) {
        try {
            String base64 = getString(key);
            if (TextUtils.isEmpty(base64)) {
                return null;
            }
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(Base64.decode(base64, 2)));
            Object readObject = ois.readObject();
            ois.close();
            return readObject;
        } catch (Throwable t) {
            MobLog.getInstance().w(t);
            return null;
        }
    }

    public boolean getBoolean(String key) {
        return this.prefrence.getBoolean(key, false);
    }

    public float getFloat(String key) {
        return this.prefrence.getFloat(key, 0.0f);
    }

    public int getInt(String key) {
        return this.prefrence.getInt(key, 0);
    }

    public long getLong(String key) {
        return this.prefrence.getLong(key, 0L);
    }

    public String getString(String key) {
        return this.prefrence.getString(key, "");
    }

    public void open(String name) {
        open(name, 0);
    }

    public void open(String name, int version) {
        this.prefrence = this.context.getSharedPreferences(name + "_" + version, 0);
    }

    public void put(String key, Object value) {
        if (value != null) {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(value);
                oos.flush();
                oos.close();
                putString(key, Base64.encodeToString(baos.toByteArray(), 2));
            } catch (Throwable t) {
                MobLog.getInstance().w(t);
            }
        }
    }

    public void putBoolean(String key, Boolean value) {
        SharedPreferences.Editor editor = this.prefrence.edit();
        editor.putBoolean(key, value.booleanValue());
        editor.commit();
    }

    public void putFloat(String key, Float value) {
        SharedPreferences.Editor editor = this.prefrence.edit();
        editor.putFloat(key, value.floatValue());
        editor.commit();
    }

    public void putInt(String key, Integer value) {
        SharedPreferences.Editor editor = this.prefrence.edit();
        editor.putInt(key, value.intValue());
        editor.commit();
    }

    public void putLong(String key, Long value) {
        SharedPreferences.Editor editor = this.prefrence.edit();
        editor.putLong(key, value.longValue());
        editor.commit();
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = this.prefrence.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void remove(String key) {
        SharedPreferences.Editor editor = this.prefrence.edit();
        editor.remove(key);
        editor.commit();
    }
}
