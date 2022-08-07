package com.easeui.widget.chatrow;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;
import com.easeui.controller.EaseUI;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMVoiceMessageBody;
import com.vsf2f.f2f.R;
import java.io.File;

/* loaded from: classes.dex */
public class EaseChatRowVoicePlayClickListener implements View.OnClickListener {
    private static final String TAG = "VoicePlayClickListener";
    public static EaseChatRowVoicePlayClickListener currentPlayListener = null;
    public static boolean isPlaying = false;
    public static String playMsgId;
    private Activity activity;
    private BaseAdapter adapter;
    private EMMessage.ChatType chatType;
    private ImageView iv_read_status;
    private EMMessage message;
    private EMVoiceMessageBody voiceBody;
    private ImageView voiceIconView;
    private AnimationDrawable voiceAnimation = null;
    private MediaPlayer mediaPlayer = null;

    public EaseChatRowVoicePlayClickListener(EMMessage message, ImageView v, ImageView iv_read_status, BaseAdapter adapter, Activity context) {
        this.message = message;
        this.voiceBody = (EMVoiceMessageBody) message.getBody();
        this.iv_read_status = iv_read_status;
        this.adapter = adapter;
        this.voiceIconView = v;
        this.activity = context;
        this.chatType = message.getChatType();
    }

    static void stopVoice() {
        if (currentPlayListener != null && isPlaying) {
            currentPlayListener.stopPlayVoice();
        }
    }

    public void stopPlayVoice() {
        this.voiceAnimation.stop();
        if (this.message.direct() == EMMessage.Direct.RECEIVE) {
            this.voiceIconView.setImageResource(R.drawable.ease_chatfrom_voice_playing_f3);
        } else {
            this.voiceIconView.setImageResource(R.drawable.ease_chatto_voice_playing_f3);
        }
        if (this.mediaPlayer != null) {
            this.mediaPlayer.stop();
            this.mediaPlayer.release();
        }
        isPlaying = false;
        playMsgId = null;
        this.adapter.notifyDataSetChanged();
    }

    public void playVoice(String filePath) {
        if (new File(filePath).exists()) {
            playMsgId = this.message.getMsgId();
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
                this.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.easeui.widget.chatrow.EaseChatRowVoicePlayClickListener.1
                    @Override // android.media.MediaPlayer.OnCompletionListener
                    public void onCompletion(MediaPlayer mp) {
                        EaseChatRowVoicePlayClickListener.this.mediaPlayer.release();
                        EaseChatRowVoicePlayClickListener.this.mediaPlayer = null;
                        EaseChatRowVoicePlayClickListener.this.stopPlayVoice();
                    }
                });
                isPlaying = true;
                currentPlayListener = this;
                this.mediaPlayer.start();
                showAnimation();
                if (this.message.direct() == EMMessage.Direct.RECEIVE) {
                    if (!this.message.isAcked() && this.chatType == EMMessage.ChatType.Chat) {
                        EMClient.getInstance().chatManager().ackMessageRead(this.message.getFrom(), this.message.getMsgId());
                    }
                    if (!this.message.isListened() && this.iv_read_status != null && this.iv_read_status.getVisibility() == 0) {
                        this.iv_read_status.setVisibility(4);
                        this.message.setListened(true);
                        EMClient.getInstance().chatManager().setMessageListened(this.message);
                    }
                }
            } catch (Exception e) {
                System.out.println();
            }
        }
    }

    private void showAnimation() {
        if (this.message.direct() == EMMessage.Direct.RECEIVE) {
            this.voiceIconView.setImageResource(R.drawable.voice_from_icon);
        } else {
            this.voiceIconView.setImageResource(R.drawable.voice_to_icon);
        }
        this.voiceAnimation = (AnimationDrawable) this.voiceIconView.getDrawable();
        this.voiceAnimation.start();
    }

    /* JADX WARN: Type inference failed for: r2v13, types: [com.easeui.widget.chatrow.EaseChatRowVoicePlayClickListener$2] */
    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        String st = this.activity.getResources().getString(R.string.download_voice_click_later);
        if (isPlaying) {
            if (playMsgId == null || !playMsgId.equals(this.message.getMsgId())) {
                currentPlayListener.stopPlayVoice();
            } else {
                currentPlayListener.stopPlayVoice();
                return;
            }
        }
        if (this.message.direct() == EMMessage.Direct.SEND) {
            playVoice(this.voiceBody.getLocalUrl());
        } else if (this.message.status() == EMMessage.Status.SUCCESS) {
            File file = new File(this.voiceBody.getLocalUrl());
            if (!file.exists() || !file.isFile()) {
                Toast.makeText(this.activity, "未找到语音文件", 0).show();
            } else {
                playVoice(this.voiceBody.getLocalUrl());
            }
        } else if (this.message.status() == EMMessage.Status.INPROGRESS) {
            Toast.makeText(this.activity, st, 0).show();
        } else if (this.message.status() == EMMessage.Status.FAIL) {
            Toast.makeText(this.activity, st, 0).show();
            new AsyncTask<Void, Void, Void>() { // from class: com.easeui.widget.chatrow.EaseChatRowVoicePlayClickListener.2
                /* JADX INFO: Access modifiers changed from: protected */
                public Void doInBackground(Void... params) {
                    EMClient.getInstance().chatManager().downloadAttachment(EaseChatRowVoicePlayClickListener.this.message);
                    return null;
                }

                /* JADX INFO: Access modifiers changed from: protected */
                public void onPostExecute(Void result) {
                    super.onPostExecute((AnonymousClass2) result);
                    EaseChatRowVoicePlayClickListener.this.adapter.notifyDataSetChanged();
                }
            }.execute(new Void[0]);
        }
    }
}
