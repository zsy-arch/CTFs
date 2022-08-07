package com.amap.api.col;

import android.opengl.GLES20;

/* compiled from: GLShader.java */
/* loaded from: classes.dex */
public class cu {
    public int j;

    protected boolean a(String str) {
        this.j = d(str);
        return this.j != 0;
    }

    protected int b(String str) {
        int glGetAttribLocation = GLES20.glGetAttribLocation(this.j, str);
        if (glGetAttribLocation < 0) {
        }
        return glGetAttribLocation;
    }

    protected int c(String str) {
        int glGetUniformLocation = GLES20.glGetUniformLocation(this.j, str);
        if (glGetUniformLocation < 0) {
        }
        return glGetUniformLocation;
    }

    public void b() {
        GLES20.glUseProgram(this.j);
    }

    public static int d(String str) {
        String str2 = "amap_sdk_shaders/" + str;
        String a = da.a(str2);
        if (a == null) {
            throw new IllegalArgumentException("shader file not found: " + str2);
        }
        int indexOf = a.indexOf(36);
        if (indexOf < 0 || a.charAt(indexOf + 1) != '$') {
            throw new IllegalArgumentException("not a shader file " + str2);
        }
        return a(a.substring(0, indexOf), a.substring(indexOf + 2));
    }

    public static int a(String str, String str2) {
        int a = a(35633, str);
        int a2 = a(35632, str2);
        int glCreateProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(glCreateProgram, a);
        GLES20.glAttachShader(glCreateProgram, a2);
        GLES20.glLinkProgram(glCreateProgram);
        return glCreateProgram;
    }

    public static int a(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        return glCreateShader;
    }
}
