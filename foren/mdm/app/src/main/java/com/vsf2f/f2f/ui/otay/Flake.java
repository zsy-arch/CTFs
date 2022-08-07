package com.vsf2f.f2f.ui.otay;

import android.content.Context;
import android.graphics.Bitmap;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class Flake {
    static HashMap<Integer, Bitmap> bitmapMap = new HashMap<>();
    Bitmap bitmap;
    int height;
    float rotation;
    float rotationSpeed;
    float speed;
    int width;
    float x;
    float y;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Flake createFlake(float xRange, Bitmap originalBitmap, Context context) {
        Flake flake = new Flake();
        if (context.getResources().getDisplayMetrics().widthPixels >= 1080) {
            flake.width = (int) ((((float) Math.random()) * 80.0f) + 50.0f);
        } else {
            flake.width = (int) ((((float) Math.random()) * 50.0f) + 50.0f);
        }
        flake.height = flake.width;
        flake.x = ((float) Math.random()) * (xRange - flake.width);
        flake.y = 0.0f - (flake.height + (((float) Math.random()) * flake.height));
        flake.speed = (((float) Math.random()) * 150.0f) + 50.0f;
        flake.rotation = (((float) Math.random()) * 180.0f) - 90.0f;
        flake.rotationSpeed = (((float) Math.random()) * 90.0f) - 45.0f;
        flake.bitmap = bitmapMap.get(Integer.valueOf(flake.width));
        if (flake.bitmap == null) {
            flake.bitmap = Bitmap.createScaledBitmap(originalBitmap, flake.width, flake.height, true);
            bitmapMap.put(Integer.valueOf(flake.width), flake.bitmap);
        }
        return flake;
    }
}
