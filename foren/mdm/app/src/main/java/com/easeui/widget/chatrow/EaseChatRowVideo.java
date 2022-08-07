package com.easeui.widget.chatrow;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.cdlinglu.utils.TimeUtil;
import com.easeui.model.EaseImageCache;
import com.easeui.ui.EaseShowVideoActivity;
import com.easeui.utils.EaseCommonUtils;
import com.easeui.widget.chatrow.EaseChatRow;
import com.em.db.UserDao;
import com.hy.frame.util.FolderUtil;
import com.hy.frame.util.MyLog;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMFileMessageBody;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMVideoMessageBody;
import com.hyphenate.util.ImageUtils;
import com.hyphenate.util.TextFormater;
import com.vsf2f.f2f.R;
import java.io.File;

/* loaded from: classes.dex */
public class EaseChatRowVideo extends EaseChatRowFile {
    private ImageView imageView;
    private TextView sizeView;
    private TextView timeLengthView;

    public EaseChatRowVideo(Context context, EaseChatRow.GetClientListener listener, EMMessage message, int position, BaseAdapter adapter) {
        super(context, listener, message, position, adapter);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRowFile, com.easeui.widget.chatrow.EaseChatRow
    protected void onInflateView() {
        this.inflater.inflate(this.message.direct() == EMMessage.Direct.RECEIVE ? R.layout.ease_row_received_video : R.layout.ease_row_sent_video, this);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRowFile, com.easeui.widget.chatrow.EaseChatRow
    protected void onFindViewById() {
        this.imageView = (ImageView) findViewById(R.id.chatting_content_iv);
        this.sizeView = (TextView) findViewById(R.id.chatting_size_iv);
        this.timeLengthView = (TextView) findViewById(R.id.chatting_length_iv);
        ImageView imageView = (ImageView) findViewById(R.id.chatting_status_btn);
        this.percentageView = (TextView) findViewById(R.id.percentage);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRowFile, com.easeui.widget.chatrow.EaseChatRow
    protected void onSetUpView() {
        EMVideoMessageBody videoBody = (EMVideoMessageBody) this.message.getBody();
        String localThumb = videoBody.getLocalThumb();
        if (localThumb != null) {
            showVideoThumbView(localThumb, this.imageView, videoBody.getThumbnailUrl(), this.message);
        }
        if (videoBody.getDuration() > 0) {
            this.timeLengthView.setText(TimeUtil.toMinbyS(videoBody.getDuration()));
        }
        if (this.message.direct() == EMMessage.Direct.RECEIVE) {
            if (videoBody.getVideoFileLength() > 0) {
                this.sizeView.setText(TextFormater.getDataSize(videoBody.getVideoFileLength()));
            }
        } else if (videoBody.getLocalUrl() != null && new File(videoBody.getLocalUrl()).exists()) {
            this.sizeView.setText(TextFormater.getDataSize(new File(videoBody.getLocalUrl()).length()));
        }
        MyLog.d("video thumbnailStatus:" + videoBody.thumbnailDownloadStatus());
        if (this.message.direct() != EMMessage.Direct.RECEIVE) {
            handleSendMessage();
        } else if (videoBody.thumbnailDownloadStatus() == EMFileMessageBody.EMDownloadStatus.DOWNLOADING || videoBody.thumbnailDownloadStatus() == EMFileMessageBody.EMDownloadStatus.PENDING) {
            this.imageView.setImageResource(R.drawable.ease_default_image);
            setMessageReceiveCallback();
        } else {
            this.imageView.setImageResource(R.drawable.ease_default_image);
            if (localThumb != null) {
                showVideoThumbView(localThumb, this.imageView, videoBody.getThumbnailUrl(), this.message);
            }
        }
    }

    @Override // com.easeui.widget.chatrow.EaseChatRowFile, com.easeui.widget.chatrow.EaseChatRow
    protected void onBubbleClick() {
        EMVideoMessageBody videoBody = (EMVideoMessageBody) this.message.getBody();
        MyLog.d("video view is on click");
        Intent intent = new Intent(this.context, EaseShowVideoActivity.class);
        intent.putExtra(UserDao.IMAGE_COLUMN_NAME_LOCALPATH, FolderUtil.getCachePathVideo() + File.separator + this.message.getMsgId() + ".mp4");
        intent.putExtra("secret", videoBody.getSecret());
        intent.putExtra("remotepath", videoBody.getRemoteUrl());
        if (this.message != null && this.message.direct() == EMMessage.Direct.RECEIVE && !this.message.isAcked() && this.message.getChatType() == EMMessage.ChatType.Chat) {
            try {
                EMClient.getInstance().chatManager().ackMessageRead(this.message.getFrom(), this.message.getMsgId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.activity.startActivity(intent);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.easeui.widget.chatrow.EaseChatRowVideo$1] */
    private void showVideoThumbView(final String localThumb, final ImageView iv, String thumbnailUrl, final EMMessage message) {
        Bitmap bitmap = EaseImageCache.getInstance().get(localThumb);
        if (bitmap != null) {
            iv.setImageBitmap(bitmap);
        } else {
            new AsyncTask<Void, Void, Bitmap>() { // from class: com.easeui.widget.chatrow.EaseChatRowVideo.1
                /* JADX INFO: Access modifiers changed from: protected */
                public Bitmap doInBackground(Void... params) {
                    if (new File(localThumb).exists()) {
                        return ImageUtils.decodeScaleImage(localThumb, 160, 160);
                    }
                    return null;
                }

                /* JADX INFO: Access modifiers changed from: protected */
                public void onPostExecute(Bitmap result) {
                    super.onPostExecute((AnonymousClass1) result);
                    if (result != null) {
                        EaseImageCache.getInstance().put(localThumb, result);
                        iv.setImageBitmap(result);
                    } else if (message.status() == EMMessage.Status.FAIL && EaseCommonUtils.isNetWorkConnected(EaseChatRowVideo.this.activity)) {
                        EMClient.getInstance().chatManager().downloadThumbnail(message);
                    }
                }
            }.execute(new Void[0]);
        }
    }
}
