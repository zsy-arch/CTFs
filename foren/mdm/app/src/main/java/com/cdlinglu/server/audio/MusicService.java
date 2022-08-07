package com.cdlinglu.server.audio;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import com.cdlinglu.common.MyApplication;
import com.danikula.videocache.CacheListener;
import com.danikula.videocache.HttpProxyCacheServer;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import java.io.File;

/* loaded from: classes.dex */
public class MusicService extends Service implements CacheListener {
    private AudioManager audiomanage;
    public final IBinder binder;
    private String currentUrl;
    private Context mContext;
    public MediaPlayer mp;
    private String[] musicDir;
    private int musicIndex;
    private HttpProxyCacheServer myProxy;
    private OnCacheListener onCacheListener;
    private String url;

    /* loaded from: classes.dex */
    public interface OnCacheListener {
        void getCacheProgress(int i);
    }

    @Override // com.danikula.videocache.CacheListener
    public void onCacheAvailable(File cacheFile, String url, int percentsAvailable) {
        if (this.onCacheListener != null) {
            this.onCacheListener.getCacheProgress(percentsAvailable);
        }
    }

    /* loaded from: classes.dex */
    public class MyBinder extends Binder {
        public MyBinder() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public MusicService getService() {
            return MusicService.this;
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.audiomanage = (AudioManager) getSystemService("audio");
        this.mp = new MediaPlayer();
        this.myProxy = MyApplication.getProxy(this);
    }

    public MusicService() {
        this.musicDir = new String[]{Environment.getExternalStorageDirectory().getAbsolutePath() + "/Music/仙剑奇侠传六-主题曲-《誓言成晖》.mp3", Environment.getExternalStorageDirectory().getAbsolutePath() + "/Music/仙剑奇侠传六-主题曲-《剑客不能说》.mp3", Environment.getExternalStorageDirectory().getAbsolutePath() + "/Music/仙剑奇侠传六-主题曲-《镜中人》.mp3", Environment.getExternalStorageDirectory().getAbsolutePath() + "/Music/仙剑奇侠传六-主题曲-《浪花》.mp3"};
        this.url = "http://www.ejoooo.com/audio/mp3/201611290520201435.mp3";
        this.musicIndex = 1;
        this.binder = new MyBinder();
    }

    public MusicService(Context context, OnCacheListener onCacheListener) {
        this.musicDir = new String[]{Environment.getExternalStorageDirectory().getAbsolutePath() + "/Music/仙剑奇侠传六-主题曲-《誓言成晖》.mp3", Environment.getExternalStorageDirectory().getAbsolutePath() + "/Music/仙剑奇侠传六-主题曲-《剑客不能说》.mp3", Environment.getExternalStorageDirectory().getAbsolutePath() + "/Music/仙剑奇侠传六-主题曲-《镜中人》.mp3", Environment.getExternalStorageDirectory().getAbsolutePath() + "/Music/仙剑奇侠传六-主题曲-《浪花》.mp3"};
        this.url = "http://www.ejoooo.com/audio/mp3/201611290520201435.mp3";
        this.musicIndex = 1;
        this.binder = new MyBinder();
        this.mContext = context;
        this.onCacheListener = onCacheListener;
        try {
            checkCachedState();
            HttpProxyCacheServer proxy = MyApplication.getProxy(this);
            proxy.registerCacheListener(this, this.url);
            String proxyUrl = proxy.getProxyUrl(this.url);
            this.mp.reset();
            this.mp.setDataSource(proxyUrl);
            this.mp.prepareAsync();
            this.mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.cdlinglu.server.audio.MusicService.1
                @Override // android.media.MediaPlayer.OnPreparedListener
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
            this.musicIndex = 1;
        } catch (Exception e) {
            Log.d("hint", "can't get to the song");
            e.printStackTrace();
        }
    }

    public void playByUrl(String audioUrl) {
        playByUrl(audioUrl, null);
    }

    public void playByUrl(String audioUrl, MusicPlayer player) {
        try {
            if (this.mp == null) {
                this.mp = new MediaPlayer();
            }
            if (audioUrl == null || !audioUrl.equals(this.currentUrl) || !this.mp.isPlaying()) {
                this.audiomanage.setMode(0);
                if (player != null) {
                    setOverListener(player);
                    player.setStart();
                    ((AnimationDrawable) player.getImg_voice().getDrawable()).start();
                }
                this.currentUrl = audioUrl;
                checkCachedState();
                this.myProxy.registerCacheListener(this, audioUrl);
                String proxyUrl = this.myProxy.getProxyUrl(audioUrl);
                this.mp.reset();
                this.mp.setDataSource(proxyUrl);
                this.mp.prepareAsync();
                this.mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.cdlinglu.server.audio.MusicService.2
                    @Override // android.media.MediaPlayer.OnPreparedListener
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                    }
                });
                return;
            }
            this.mp.pause();
            if (player != null) {
                player.setStop();
            }
        } catch (Exception e) {
            this.audiomanage.setMode(1);
            MyToast.show(getApplicationContext(), "语音播放失败");
            MyLog.e("Exception  " + e.toString());
            e.printStackTrace();
        }
    }

    private void checkCachedState() {
        if (MyApplication.getProxy(this).isCached(this.url) && this.onCacheListener != null) {
            this.onCacheListener.getCacheProgress(100);
        }
    }

    public void setOverListener(final MusicPlayer player) {
        if (this.mp != null) {
            this.mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.cdlinglu.server.audio.MusicService.3
                @Override // android.media.MediaPlayer.OnCompletionListener
                public void onCompletion(MediaPlayer mediaPlayer) {
                    MusicService.this.audiomanage.setMode(1);
                    player.setStop();
                }
            });
        }
    }

    public void playOrPause() {
        if (this.mp != null) {
            if (this.mp.isPlaying()) {
                this.mp.pause();
            } else {
                this.mp.start();
            }
        }
    }

    public void playPause() {
        if (this.mp != null && this.mp.isPlaying()) {
            this.mp.pause();
        }
    }

    public void stop() {
        if (this.mp != null) {
            this.mp.stop();
            try {
                this.mp.prepareAsync();
                this.mp.prepare();
                this.mp.seekTo(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void nextMusic() {
        if (this.mp != null && this.musicIndex < 3) {
            this.mp.stop();
            try {
                this.mp.reset();
                this.mp.setDataSource(this.musicDir[this.musicIndex + 1]);
                this.musicIndex++;
                this.mp.prepare();
                this.mp.seekTo(0);
                this.mp.start();
            } catch (Exception e) {
                Log.d("hint", "can't jump next music");
                e.printStackTrace();
            }
        }
    }

    public void preMusic() {
        if (this.mp != null && this.musicIndex > 0) {
            this.mp.stop();
            try {
                this.mp.reset();
                this.mp.setDataSource(this.musicDir[this.musicIndex - 1]);
                this.musicIndex--;
                this.mp.prepare();
                this.mp.seekTo(0);
                this.mp.start();
            } catch (Exception e) {
                Log.d("hint", "can't jump pre music");
                e.printStackTrace();
            }
        }
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.mp.stop();
        this.mp.release();
        this.mp = null;
        MyApplication.getProxy(getApplication()).unregisterCacheListener(this);
        super.onDestroy();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.binder;
    }
}
