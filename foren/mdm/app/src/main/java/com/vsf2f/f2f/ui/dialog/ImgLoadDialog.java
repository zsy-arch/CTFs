package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.cdlinglu.utils.Photo.util.FileUtils;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.FolderUtil;
import com.hy.frame.util.MyToast;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.area.DateUtils;
import java.io.File;

/* loaded from: classes2.dex */
public class ImgLoadDialog extends BaseDialog {
    private Bitmap bitmap;
    private String localUrl;
    private ShareForwardCodeDialog shareCodeDialog;
    private String url;

    public ImgLoadDialog(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_imgload;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(1280);
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(0);
        } else {
            getWindow().addFlags(67108864);
        }
        windowDeploy(-1.0f, -1.0f, 17);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        this.localUrl = FolderUtil.getCachePathImage() + File.separator + DateUtils.getNowDateStr() + ".jpg";
        final FrameLayout group_parent = (FrameLayout) getView(R.id.group_parent);
        ImageView imgView = (ImageView) getViewAndClick(R.id.imgview);
        Glide.with(getContext()).load(this.url).into(imgView);
        Glide.with(getContext()).load(this.url).asBitmap().into((BitmapTypeRequest<String>) new SimpleTarget<Bitmap>() { // from class: com.vsf2f.f2f.ui.dialog.ImgLoadDialog.1
            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, GlideAnimation glideAnimation) {
                onResourceReady((Bitmap) obj, (GlideAnimation<? super Bitmap>) glideAnimation);
            }

            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                ImgLoadDialog.this.bitmap = resource;
                FileUtils.saveBitmap(ImgLoadDialog.this.getContext(), ImgLoadDialog.this.bitmap, ImgLoadDialog.this.localUrl, false);
            }
        });
        imgView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.vsf2f.f2f.ui.dialog.ImgLoadDialog.2
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                ImgLoadDialog.this.showDlg(group_parent);
                return false;
            }
        });
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public String getLocalUrl() {
        return this.localUrl;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
        dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDlg(View v) {
        if (this.shareCodeDialog == null) {
            this.shareCodeDialog = new ShareForwardCodeDialog(getContext());
            this.shareCodeDialog.setListener(new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.dialog.ImgLoadDialog.3
                @Override // com.hy.frame.common.BaseDialog.IConfirmListener
                public void onDlgConfirm(BaseDialog dlg, int flag) {
                    if (flag == 1) {
                        FileUtils.saveAlbum(ImgLoadDialog.this.getContext(), new File(ImgLoadDialog.this.localUrl));
                        MyToast.show(ImgLoadDialog.this.getContext(), "保存成功");
                        return;
                    }
                    ImgLoadDialog.this.getListener().onDlgConfirm(ImgLoadDialog.this, flag);
                }
            });
        }
        this.shareCodeDialog.show();
    }
}
