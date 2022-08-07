package com.cdlinglu.server.audio;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.cdlinglu.server.audio.MusicService;

/* loaded from: classes.dex */
public class MusicPlayerUtils {
    private static MusicPlayerUtils playerUtils;
    private boolean isBind;
    private MusicService musicService;
    private ServiceConnection sc = new ServiceConnection() { // from class: com.cdlinglu.server.audio.MusicPlayerUtils.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MusicPlayerUtils.this.musicService = ((MusicService.MyBinder) iBinder).getService();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            MusicPlayerUtils.this.musicService = null;
        }
    };

    public static synchronized MusicPlayerUtils getInstance() {
        MusicPlayerUtils musicPlayerUtils;
        synchronized (MusicPlayerUtils.class) {
            if (playerUtils == null) {
                playerUtils = new MusicPlayerUtils();
            }
            musicPlayerUtils = playerUtils;
        }
        return musicPlayerUtils;
    }

    public void bindService(Context context) {
        try {
            if (!this.isBind) {
                context.bindService(new Intent(context, MusicService.class), this.sc, 1);
                this.isBind = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unBindService(Context context) {
        try {
            if (this.isBind) {
                context.getApplicationContext().unbindService(this.sc);
                this.isBind = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playMusicByUrl(String url) {
        if (this.musicService != null) {
            this.musicService.playByUrl(url);
        }
    }

    public void playMusicByUrl(String url, MusicPlayer player) {
        if (this.musicService != null) {
            this.musicService.playByUrl(url, player);
        }
    }

    public void pauseAudio() {
        if (this.musicService != null) {
            this.musicService.playPause();
        }
    }
}
