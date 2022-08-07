package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.Photo.util.FileUtils;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.FolderUtil;
import com.hy.frame.util.MyToast;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.dialog.BigimgMenuDialog;
import com.vsf2f.f2f.ui.utils.area.DateUtils;
import java.io.File;

/* loaded from: classes2.dex */
public class GroupCodeDialog extends BaseDialog {
    private Bitmap code;
    private String head;
    private String name;
    private BigimgMenuDialog picDialog;

    public GroupCodeDialog(Context context, String name, String head, Bitmap code) {
        super(context);
        this.name = name;
        this.head = head;
        this.code = code;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_group_code;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(-2.0f, -2.0f, 17);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        final LinearLayout group_parent = (LinearLayout) getView(R.id.group_parent);
        ImageView _imgcode = (ImageView) getView(R.id.group_code);
        ((TextView) getView(R.id.group_name)).setText(this.name);
        ComUtil.display(getContext(), (ImageView) getView(R.id.group_head), this.head, R.drawable.ease_group_icon);
        group_parent.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.dialog.GroupCodeDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                GroupCodeDialog.this.dismiss();
            }
        });
        _imgcode.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.vsf2f.f2f.ui.dialog.GroupCodeDialog.2
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View v) {
                GroupCodeDialog.this.showDlg(group_parent);
                return false;
            }
        });
        _imgcode.setImageBitmap(this.code);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDlg(final View v) {
        if (this.picDialog == null) {
            this.picDialog = new BigimgMenuDialog(getContext());
            this.picDialog.init(new BigimgMenuDialog.ConfirmDlgListener() { // from class: com.vsf2f.f2f.ui.dialog.GroupCodeDialog.3
                @Override // com.vsf2f.f2f.ui.dialog.BigimgMenuDialog.ConfirmDlgListener
                public void onDlgSavePhotoClick(BigimgMenuDialog dlg) {
                    GroupCodeDialog.this.saveCode(v);
                }

                @Override // com.vsf2f.f2f.ui.dialog.BigimgMenuDialog.ConfirmDlgListener
                public void onDlgCancelClick(BigimgMenuDialog dlg) {
                    GroupCodeDialog.this.picDialog.dismiss();
                }
            });
        }
        this.picDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveCode(View v) {
        String filePath = FolderUtil.getCachePathImage() + File.separator + DateUtils.getNowDateStr() + ".png";
        FileUtils.saveView(getContext(), v, filePath);
        MyToast.show(getContext(), "二维码成功保存至" + filePath);
    }
}
