package com.tencent.smtt.sdk;

import android.graphics.SurfaceTexture;
import android.os.Bundle;

/* loaded from: classes.dex */
public class TbsMediaPlayer {

    /* renamed from: a */
    public n f1248a;

    /* loaded from: classes.dex */
    public interface TbsMediaPlayerListener {
        public static final int MEDIA_EXTRA_AUDIOTRACK_INDEX = 104;
        public static final int MEDIA_EXTRA_AUDIOTRACK_TITLES = 103;
        public static final int MEDIA_EXTRA_SUBTITLE_COUNT = 101;
        public static final int MEDIA_EXTRA_SUBTITLE_INDEX = 102;
        public static final int MEDIA_INFO_BAD_INTERLEAVING = 800;
        public static final int MEDIA_INFO_BUFFERING_END = 702;
        public static final int MEDIA_INFO_BUFFERING_PERCENTAGE = 790;
        public static final int MEDIA_INFO_BUFFERING_START = 701;
        public static final int MEDIA_INFO_EXTERNAL_METADATA_UPDATE = 803;
        public static final int MEDIA_INFO_HAVE_VIDEO_DATA = 752;
        public static final int MEDIA_INFO_METADATA_UPDATE = 802;
        public static final int MEDIA_INFO_NETWORK_BANDWIDTH = 703;
        public static final int MEDIA_INFO_NOT_SEEKABLE = 801;
        public static final int MEDIA_INFO_NO_VIDEO_DATA = 751;
        public static final int MEDIA_INFO_SUBTITLE_TIMED_OUT = 902;
        public static final int MEDIA_INFO_TIMED_TEXT_ERROR = 900;
        public static final int MEDIA_INFO_UNSUPPORTED_SUBTITLE = 901;
        public static final int MEDIA_INFO_VIDEO_TRACK_LAGGING = 700;
        public static final int ROTATE_ACTION_HASROTATE = 3;
        public static final int ROTATE_ACTION_NOTHING = 1;
        public static final int ROTATE_ACTION_SETDEGREE = 2;
        public static final int ROTATE_ACTION_UNKNOWN = 0;

        void onBufferingUpdate(float f);

        void onPlayerCompleted();

        void onPlayerError(String str, int i, int i2, Throwable th);

        void onPlayerExtra(int i, Object obj);

        void onPlayerInfo(int i, int i2);

        void onPlayerPaused();

        void onPlayerPlaying();

        void onPlayerPrepared(long j, int i, int i2, int i3, int i4);

        void onPlayerProgress(long j);

        void onPlayerSeeked(long j);

        void onPlayerSubtitle(String str);
    }

    public TbsMediaPlayer(n nVar) {
        this.f1248a = null;
        this.f1248a = nVar;
    }

    public void audio(int i) {
        this.f1248a.b(i);
    }

    public void close() {
        this.f1248a.e();
    }

    public float getVolume() {
        return this.f1248a.b();
    }

    public boolean isAvailable() {
        return this.f1248a.a();
    }

    public void pause() {
        this.f1248a.c();
    }

    public void play() {
        this.f1248a.d();
    }

    public void seek(long j) {
        this.f1248a.a(j);
    }

    public void setPlayerListener(TbsMediaPlayerListener tbsMediaPlayerListener) {
        this.f1248a.a(tbsMediaPlayerListener);
    }

    public void setSurfaceTexture(SurfaceTexture surfaceTexture) {
        this.f1248a.a(surfaceTexture);
    }

    public void setVolume(float f) {
        this.f1248a.a(f);
    }

    public void startPlay(String str, Bundle bundle) {
        this.f1248a.a(str, bundle);
    }

    public void subtitle(int i) {
        this.f1248a.a(i);
    }
}
