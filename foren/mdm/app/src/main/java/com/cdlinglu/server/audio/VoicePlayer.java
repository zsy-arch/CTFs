package com.cdlinglu.server.audio;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.View;
import com.cdlinglu.common.MyApplication;
import com.danikula.videocache.HttpProxyCacheServer;
import com.easeui.controller.EaseUI;
import com.hy.frame.util.MyLog;
import com.vsf2f.f2f.R;
import java.io.File;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public class VoicePlayer implements View.OnClickListener {
    private static VoicePlayer currentPlayListener = null;
    private static boolean isPlaying = false;
    private static String playMsgId;
    private Activity activity;
    private MediaPlayer mediaPlayer = null;
    private HttpProxyCacheServer myProxy;
    private MusicPlayer player;
    private String url;

    public VoicePlayer(Activity context, String url, MusicPlayer player) {
        this.activity = context;
        this.player = player;
        this.url = url;
        this.myProxy = MyApplication.getProxy(context);
    }

    public static void stopVoice() {
        if (currentPlayListener != null && isPlaying) {
            currentPlayListener.stopPlayVoice();
        }
    }

    public void stopPlayVoice() {
        this.player.setStop();
        if (this.mediaPlayer != null) {
            this.mediaPlayer.stop();
            this.mediaPlayer.release();
        }
        isPlaying = false;
        playMsgId = null;
    }

    public void playVoice(String filePath) {
        if (!new File(filePath).exists()) {
        }
        MyLog.e("Voice=" + filePath);
        playMsgId = filePath;
        AudioManager audioManager = (AudioManager) this.activity.getSystemService("audio");
        this.mediaPlayer = new MediaPlayer();
        if (EaseUI.getInstance().getSettingsProvider().isSpeakerOpened()) {
            audioManager.setMode(0);
            audioManager.setSpeakerphoneOn(true);
            this.mediaPlayer.setAudioStreamType(3);
        } else {
            audioManager.setSpeakerphoneOn(false);
            audioManager.setMode(2);
            this.mediaPlayer.setAudioStreamType(0);
        }
        try {
            this.mediaPlayer.setDataSource(filePath);
            this.mediaPlayer.prepare();
            this.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.cdlinglu.server.audio.VoicePlayer.1
                @Override // android.media.MediaPlayer.OnCompletionListener
                public void onCompletion(MediaPlayer mp) {
                    VoicePlayer.this.mediaPlayer.release();
                    VoicePlayer.this.mediaPlayer = null;
                    VoicePlayer.this.stopPlayVoice();
                }
            });
            isPlaying = true;
            currentPlayListener = this;
            this.mediaPlayer.start();
            showAnimation();
        } catch (Exception e) {
            System.out.println();
        }
    }

    private void showAnimation() {
        this.player.setStart();
        ((AnimationDrawable) this.player.getImg_voice().getDrawable()).start();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        this.activity.getResources().getString(R.string.download_voice_click_later);
        if (isPlaying) {
            if (playMsgId == null || !playMsgId.equals(this.url)) {
                currentPlayListener.stopPlayVoice();
            } else {
                currentPlayListener.stopPlayVoice();
                return;
            }
        }
        playVoice(this.url);
    }

    private MediaPlayer getMediaPlayer(Context context) {
        MediaPlayer mediaplayer = new MediaPlayer();
        try {
            Class<?> cMediaTimeProvider = Class.forName("android.media.MediaTimeProvider");
            Class<?> cSubtitleController = Class.forName("android.media.SubtitleController");
            Class<?> iSubtitleControllerAnchor = Class.forName("android.media.SubtitleController$Anchor");
            Object subtitleInstance = cSubtitleController.getConstructor(Context.class, cMediaTimeProvider, Class.forName("android.media.SubtitleController$Listener")).newInstance(context, null, null);
            Field f = cSubtitleController.getDeclaredField("mHandler");
            f.setAccessible(true);
            try {
                f.set(subtitleInstance, new Handler());
                f.setAccessible(false);
                mediaplayer.getClass().getMethod("setSubtitleAnchor", cSubtitleController, iSubtitleControllerAnchor).invoke(mediaplayer, subtitleInstance, null);
            } catch (IllegalAccessException e) {
                f.setAccessible(false);
            }
        } catch (Exception e2) {
            MyLog.d("getMediaPlayer crash ,exception = " + e2);
        }
        return mediaplayer;
    }
}
