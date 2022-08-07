package com.amap.api.col;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.services.core.AMapException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/* compiled from: FakeInstanceMultiPoint.java */
/* loaded from: classes.dex */
public class ae {
    static a d;
    float[] a;
    private BitmapDescriptor h;
    private FloatBuffer i;
    private FloatBuffer j;
    private ShortBuffer k;
    private int e = 6;
    private boolean f = false;
    private boolean g = false;
    private int l = 0;
    int b = 0;
    int c = 0;

    public ae(float[] fArr) {
        this.a = null;
        this.a = fArr;
    }

    private void a(float[] fArr) {
        if (fArr != null) {
            if (this.i == null) {
                ByteBuffer allocateDirect = ByteBuffer.allocateDirect(fArr.length * 200 * 4);
                allocateDirect.order(ByteOrder.nativeOrder());
                this.i = allocateDirect.asFloatBuffer();
            }
            this.i.clear();
            for (int i = 0; i < 200; i++) {
                int i2 = 0;
                for (float f : fArr) {
                    if (i2 % this.e == 3) {
                        this.i.put(i);
                    } else {
                        this.i.put(f);
                    }
                    i2++;
                }
            }
            this.i.position(0);
            if (this.k == null) {
                ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect(2400);
                allocateDirect2.order(ByteOrder.nativeOrder());
                this.k = allocateDirect2.asShortBuffer();
                short[] sArr = new short[AMapException.CODE_AMAP_SERVICE_INVALID_PARAMS];
                for (int i3 = 0; i3 < 200; i3++) {
                    sArr[(i3 * 6) + 0] = (short) ((i3 * 4) + 0);
                    sArr[(i3 * 6) + 1] = (short) ((i3 * 4) + 1);
                    sArr[(i3 * 6) + 2] = (short) ((i3 * 4) + 2);
                    sArr[(i3 * 6) + 3] = (short) ((i3 * 4) + 0);
                    sArr[(i3 * 6) + 4] = (short) ((i3 * 4) + 2);
                    sArr[(i3 * 6) + 5] = (short) ((i3 * 4) + 3);
                }
                this.k.put(sArr);
                this.k.flip();
            }
            this.f = true;
        }
    }

    public void a() {
        if (this.a != null && !this.f) {
            a(this.a);
        }
    }

    public void a(BitmapDescriptor bitmapDescriptor) {
        this.h = bitmapDescriptor;
    }

    private void a(Bitmap bitmap) {
        if (bitmap != null) {
            if (this.l == 0) {
                int[] iArr = new int[1];
                GLES20.glGenTextures(1, iArr, 0);
                this.l = iArr[0];
            }
            if (this.l != 0) {
                GLES20.glBindTexture(3553, this.l);
                GLES20.glTexParameterf(3553, 10241, 9728.0f);
                GLES20.glTexParameterf(3553, 10240, 9729.0f);
                GLES20.glTexParameterf(3553, 10242, 33071.0f);
                GLES20.glTexParameterf(3553, 10243, 33071.0f);
                GLUtils.texImage2D(3553, 0, bitmap, 0);
                if (this.l != 0) {
                    this.g = true;
                }
            }
        }
    }

    public boolean b() {
        return this.f;
    }

    public void a(float[] fArr, float[] fArr2, float[] fArr3, float f, float f2, float f3, float f4, int i) {
        if (!this.g && this.h != null) {
            a(this.h.getBitmap());
        }
        if (this.l != 0) {
            e();
            GLES20.glUseProgram(d.i);
            GLES20.glUniform4f(d.g, f, f2, f3, f4);
            GLES20.glUniform3fv(d.f, i, fArr3, 0);
            GLES20.glDisable(2929);
            GLES20.glEnable(3042);
            GLES20.glBlendFunc(770, 771);
            GLES20.glBlendColor(1.0f, 1.0f, 1.0f, 1.0f);
            GLES20.glBindTexture(3553, this.l);
            GLES20.glEnableVertexAttribArray(d.c);
            GLES20.glBindBuffer(34962, this.b);
            GLES20.glVertexAttribPointer(d.c, 4, 5126, false, this.e * 4, 0);
            GLES20.glEnableVertexAttribArray(d.e);
            GLES20.glVertexAttribPointer(d.e, 2, 5126, false, this.e * 4, 16);
            GLES20.glUniformMatrix4fv(d.d, 1, false, fArr, 0);
            GLES20.glUniformMatrix4fv(d.h, 1, false, fArr2, 0);
            GLES20.glBindBuffer(34963, this.c);
            GLES20.glDrawElements(4, i * 6, 5123, 0);
            GLES20.glBindTexture(3553, 0);
            GLES20.glBindBuffer(34962, 0);
            GLES20.glDisableVertexAttribArray(d.c);
            GLES20.glDisableVertexAttribArray(d.e);
            GLES20.glUseProgram(0);
        }
    }

    private void e() {
        if (this.b == 0) {
            int[] iArr = new int[2];
            GLES20.glGenBuffers(2, iArr, 0);
            this.b = iArr[0];
            this.c = iArr[1];
            GLES20.glBindBuffer(34962, this.b);
            GLES20.glBufferData(34962, this.i.limit() * 4, this.i, 35044);
            GLES20.glBindBuffer(34963, this.c);
            GLES20.glBufferData(34963, 2400, this.k, 35044);
            a("bindVbo");
            this.i.clear();
            this.i = null;
        }
    }

    public void c() {
        if (this.i != null) {
            this.i.clear();
        }
        if (this.j != null) {
            this.j.clear();
        }
        if (this.k != null) {
            this.k.clear();
        }
        if (this.h != null) {
            this.h.recycle();
        }
        f();
        this.a = null;
        this.f = false;
        this.g = false;
        this.b = 0;
        this.c = 0;
    }

    private static void f() {
        d = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FakeInstanceMultiPoint.java */
    /* loaded from: classes.dex */
    public static class a extends cu {
        String a = "precision highp float;\n        attribute vec4 aVertex;\n        attribute vec2 aTexture;\n        uniform vec4 aMapAttribute;\n        uniform mat4 aMVPMatrix;\n        uniform mat4 aProjection;\n        uniform vec3 aInstanceOffset[200];\n        varying vec2 texture;\n        mat4 rotationMatrix(vec3 axis, float angle)\n        {\n           axis = normalize(axis);\n           float s = sin(angle);\n           float c = cos(angle);\n           float oc = 1.0 - c;\n           return mat4(oc * axis.x * axis.x + c,           oc * axis.x * axis.y - axis.z * s,  oc * axis.z * axis.x + axis.y * s,  0.0,\n                 oc * axis.x * axis.y + axis.z * s,  oc * axis.y * axis.y + c,           oc * axis.y * axis.z - axis.x * s,  0.0,\n                 oc * axis.z * axis.x - axis.y * s,  oc * axis.y * axis.z + axis.x * s,  oc * axis.z * axis.z + c,           0.0,\n                 0.0,                                0.0,                                0.0,                                1.0);\n        }\n        void main(){\n            int instance = int(aVertex.w);\n            vec3 offset_value = aInstanceOffset[instance];\n            mat4 marker_rotate_mat4 = rotationMatrix(vec3(0,0,1.0), offset_value.z * 0.01745);\n            float map_rotate = -aMapAttribute.z * 0.01745;\n            float map_tilt = aMapAttribute.w * 0.01745;\n            //tilt旋转矩阵\n            mat4 map_tilt_mat4 = rotationMatrix(vec3(1,0,0), map_tilt);\n            //bearing旋转矩阵\n            mat4 map_rotate_mat4 = rotationMatrix(vec3(0,0,1), map_rotate);\n                 \n            //旋转图片\n            vec4 pos_1 = marker_rotate_mat4 * vec4(aVertex.xy * aMapAttribute.xy, 0,1);\n                  \n            //让marker站立，tilt旋转之后z轴值有可能不是0\n            vec4 pos_2 =  map_tilt_mat4 * pos_1;\n                  \n            //旋转 bearing\n            vec4 pos_3 =  map_rotate_mat4 * pos_2;\n            gl_Position = aProjection * aMVPMatrix * vec4(pos_3.xy + offset_value.xy, pos_3.z, 1.0);\n            texture = aTexture;\n        }";
        String b = "        precision highp float;\n        varying vec2 texture;\n        uniform sampler2D aTextureUnit0;\n        void main(){\n            vec4 tempColor = texture2D(aTextureUnit0, texture);\n            gl_FragColor = tempColor;\n        }";
        int c;
        int d;
        int e;
        int f;
        int g;
        int h;
        int i;

        public void a() {
            int glCreateShader = GLES20.glCreateShader(35633);
            int glCreateShader2 = GLES20.glCreateShader(35632);
            GLES20.glShaderSource(glCreateShader, this.a);
            GLES20.glCompileShader(glCreateShader);
            GLES20.glShaderSource(glCreateShader2, this.b);
            GLES20.glCompileShader(glCreateShader2);
            this.i = GLES20.glCreateProgram();
            GLES20.glAttachShader(this.i, glCreateShader);
            GLES20.glAttachShader(this.i, glCreateShader2);
            GLES20.glLinkProgram(this.i);
            this.c = GLES20.glGetAttribLocation(this.i, "aVertex");
            ae.a("get aVertex");
            this.e = GLES20.glGetAttribLocation(this.i, "aTexture");
            this.d = GLES20.glGetUniformLocation(this.i, "aMVPMatrix");
            this.h = GLES20.glGetUniformLocation(this.i, "aProjection");
            this.f = GLES20.glGetUniformLocation(this.i, "aInstanceOffset");
            this.g = GLES20.glGetUniformLocation(this.i, "aMapAttribute");
        }
    }

    public static void d() {
        d = new a();
        d.a();
    }

    public static void a(String str) {
        int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            Log.e("amap", str + ": glError " + glGetError);
            throw new RuntimeException(str + ": glError " + glGetError);
        }
    }
}
