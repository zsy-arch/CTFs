package com.easeui.widget.chatrow;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.easeui.model.EaseImageCache;
import com.easeui.ui.EaseShowBigImageActivity;
import com.easeui.utils.EaseCommonUtils;
import com.easeui.utils.EaseImageUtils;
import com.easeui.widget.chatrow.EaseChatRow;
import com.em.DemoHelper;
import com.em.db.UserDao;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMFileMessageBody;
import com.hyphenate.chat.EMImageMessageBody;
import com.hyphenate.chat.EMMessage;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import com.yolanda.nohttp.cookie.CookieDisk;
import java.io.File;

/* loaded from: classes.dex */
public class EaseChatRowImage extends EaseChatRowFile {
    protected ImageView imageView;
    private EMImageMessageBody imgBody;

    public EaseChatRowImage(Context context, EaseChatRow.GetClientListener listener, EMMessage message, int position, BaseAdapter adapter) {
        super(context, listener, message, position, adapter);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRowFile, com.easeui.widget.chatrow.EaseChatRow
    protected void onInflateView() {
        this.inflater.inflate(this.message.direct() == EMMessage.Direct.RECEIVE ? R.layout.ease_row_received_picture : R.layout.ease_row_sent_picture, this);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRowFile, com.easeui.widget.chatrow.EaseChatRow
    protected void onFindViewById() {
        this.percentageView = (TextView) findViewById(R.id.percentage);
        this.imageView = (ImageView) findViewById(R.id.image);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRowFile, com.easeui.widget.chatrow.EaseChatRow
    protected void onSetUpView() {
        this.imgBody = (EMImageMessageBody) this.message.getBody();
        if (this.message.direct() != EMMessage.Direct.RECEIVE) {
            showImageView(EaseImageUtils.getThumbnailImagePath(this.imgBody.getLocalUrl()), this.imageView, this.imgBody.getLocalUrl(), this.message);
            handleSendMessage();
        } else if (this.imgBody.thumbnailDownloadStatus() == EMFileMessageBody.EMDownloadStatus.DOWNLOADING || this.imgBody.thumbnailDownloadStatus() == EMFileMessageBody.EMDownloadStatus.PENDING) {
            this.imageView.setImageResource(R.drawable.ease_default_image);
            setMessageReceiveCallback();
        } else {
            this.progressBar.setVisibility(8);
            this.percentageView.setVisibility(8);
            this.imageView.setImageResource(R.drawable.ease_default_image);
            String thumbPath = this.imgBody.thumbnailLocalPath();
            if (!new File(thumbPath).exists()) {
                thumbPath = EaseImageUtils.getThumbnailImagePath(this.imgBody.getLocalUrl());
            }
            showImageView(thumbPath, this.imageView, this.imgBody.getLocalUrl(), this.message);
        }
    }

    @Override // com.easeui.widget.chatrow.EaseChatRowFile, com.easeui.widget.chatrow.EaseChatRow
    protected void onBubbleClick() {
        Intent intent = new Intent(this.context, EaseShowBigImageActivity.class);
        intent.putExtra("username", DemoHelper.getInstance().getCurrentUserName());
        if (this.message.getChatType() == EMMessage.ChatType.Chat) {
            intent.putExtra(UserDao.IMAGE_COLUMN_NAME_CHATNAME, this.message.direct() == EMMessage.Direct.RECEIVE ? this.message.getFrom() : this.message.getTo());
        } else {
            intent.putExtra(UserDao.IMAGE_COLUMN_NAME_CHATNAME, this.message.getTo());
        }
        intent.putExtra(UserDao.IMAGE_COLUMN_NAME_ID, this.message.getMsgId());
        File file = new File(this.imgBody.getLocalUrl());
        if (file.exists()) {
            intent.putExtra(CookieDisk.URI, Uri.fromFile(file));
        } else {
            intent.putExtra("secret", this.imgBody.getSecret());
            intent.putExtra("remotepath", this.imgBody.getRemoteUrl());
            intent.putExtra("localUrl", this.imgBody.getLocalUrl());
        }
        if (this.message != null && this.message.direct() == EMMessage.Direct.RECEIVE && !this.message.isAcked() && this.message.getChatType() == EMMessage.ChatType.Chat) {
            try {
                EMClient.getInstance().chatManager().ackMessageRead(this.message.getFrom(), this.message.getMsgId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.context.startActivity(intent);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.easeui.widget.chatrow.EaseChatRowImage$1] */
    private boolean showImageView(final String thumbernailPath, final ImageView iv, final String localFullSizePath, final EMMessage message) {
        Bitmap bitmap = EaseImageCache.getInstance().get(thumbernailPath);
        if (bitmap != null) {
            iv.setImageBitmap(bitmap);
        } else {
            new AsyncTask<Object, Void, Bitmap>() { // from class: com.easeui.widget.chatrow.EaseChatRowImage.1
                /* JADX INFO: Access modifiers changed from: protected */
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.AsyncTask
                public Bitmap doInBackground(Object... args) {
                    if (new File(thumbernailPath).exists()) {
                        return EaseImageUtils.decodeScaleImage(thumbernailPath, 160, 160);
                    }
                    if (new File(EaseChatRowImage.this.imgBody.thumbnailLocalPath()).exists()) {
                        return EaseImageUtils.decodeScaleImage(EaseChatRowImage.this.imgBody.thumbnailLocalPath(), 160, 160);
                    }
                    if (message.direct() != EMMessage.Direct.SEND || localFullSizePath == null || !new File(localFullSizePath).exists()) {
                        return null;
                    }
                    return EaseImageUtils.decodeScaleImage(localFullSizePath, 160, 160);
                }

                /* JADX INFO: Access modifiers changed from: protected */
                public void onPostExecute(Bitmap image) {
                    if (image != null) {
                        iv.setImageBitmap(image);
                        EaseImageCache.getInstance().put(thumbernailPath, image);
                    } else if (message.status() == EMMessage.Status.FAIL && EaseCommonUtils.isNetWorkConnected(EaseChatRowImage.this.activity)) {
                        ThreadPool.newThreadPool(new Runnable() { // from class: com.easeui.widget.chatrow.EaseChatRowImage.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                EMClient.getInstance().chatManager().downloadThumbnail(message);
                            }
                        });
                    }
                }
            }.execute(new Object[0]);
        }
        return true;
    }
}
