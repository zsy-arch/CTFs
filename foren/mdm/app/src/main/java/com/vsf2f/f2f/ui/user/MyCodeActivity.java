package com.vsf2f.f2f.ui.user;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.Photo.util.FileUtils;
import com.cdlinglu.utils.ShareUtils;
import com.em.DemoHelper;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.Constant;
import com.hy.frame.util.FolderUtil;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.NavGroup;
import com.hy.frame.view.NavView;
import com.lidroid.xutils.ViewUtils;
import com.tencent.tauth.Tencent;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.ShareThirdBean;
import com.vsf2f.f2f.ui.dialog.ShareCodeDialog;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import com.vsf2f.f2f.ui.utils.web.WebUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/* loaded from: classes2.dex */
public class MyCodeActivity extends BaseActivity implements NavGroup.OnCheckedChangeListener {
    private String addfrenidurl;
    private String appurl;
    private int flag;
    private NavGroup groupFooter;
    private String guid;
    private ImageView imgCode;
    private Bitmap localBitmap;
    private String meurl;
    private String picPath;
    private ShareCodeDialog shareCodeDialog;
    private ShareUtils shareUtils;
    private String shopurl;
    private Bitmap urlBitmap;
    private String username;
    private String[] fileNames = {"addfriend.png", "shop.png", "app.png", "me.png"};
    Handler handler = new Handler() { // from class: com.vsf2f.f2f.ui.user.MyCodeActivity.2
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                MyCodeActivity.this.imgCode.setImageBitmap(MyCodeActivity.this.urlBitmap);
            } else {
                MyCodeActivity.this.imgCode.setImageBitmap(MyCodeActivity.this.localBitmap);
            }
        }
    };

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_qrocde_personal;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        ViewUtils.inject(this);
        Bundle bundle = getBundle();
        if (bundle == null || !bundle.containsKey(Constant.FLAG) || !bundle.containsKey(Constant.FLAG2)) {
            finish();
            return;
        }
        this.username = bundle.getString(Constant.FLAG);
        this.guid = bundle.getString(Constant.FLAG2);
        this.flag = bundle.getInt(Constant.FLAG_ID);
        this.imgCode = (ImageView) getView(R.id.img_qroced);
        this.groupFooter = (NavGroup) getView(R.id.shop_groupFooter);
        this.groupFooter.setOnCheckedChangeListener(this);
        setOnClickListener(R.id.top_imgShare);
        setOnClickListener(R.id.top_txtReGet);
        setOnClickListener(R.id.top_imgBack);
        this.shareUtils = new ShareUtils(this.context);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        String logo = "";
        if (getUserInfo(true).getUserPic().getSpath() != null) {
            logo = "&logo=true";
        }
        this.shopurl = WebUtils.getTokenUrl(this.context, getString(R.string.SHOP_QORCED, new Object[]{this.username + logo}));
        this.appurl = WebUtils.getTokenUrl(this.context, getString(R.string.APP_DOWNLOAD, new Object[]{this.username + logo}));
        this.meurl = WebUtils.getTokenUrl(this.context, getString(R.string.MY_QORCED, new Object[]{this.username + logo}));
        this.addfrenidurl = WebUtils.getTokenUrl(this.context, getString(R.string.ADD_FRIEND, new Object[]{this.username + logo}));
        this.groupFooter.setCheckedChildByPosition(this.flag);
        this.imgCode.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.vsf2f.f2f.ui.user.MyCodeActivity.1
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View v) {
                MyCodeActivity.this.showForwardDialog();
                return false;
            }
        });
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.top_imgBack /* 2131755207 */:
                finish();
                return;
            case R.id.top_imgShare /* 2131755509 */:
                showForwardDialog();
                return;
            case R.id.top_txtReGet /* 2131755510 */:
                clearLocalCode();
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.view.NavGroup.OnCheckedChangeListener
    public void onCheckedChanged(NavGroup navGroup, NavView navView, int i) {
        if (navView.getTag() != null) {
            this.imgCode.setImageBitmap(null);
            selectLoad(Integer.parseInt(navView.getTag().toString()));
        }
    }

    public void selectLoad(int position) {
        this.flag = position;
        switch (position) {
            case 0:
                loadImg(this.addfrenidurl, this.fileNames[position]);
                return;
            case 1:
                loadImg(this.shopurl, this.fileNames[position]);
                return;
            case 2:
                loadImg(this.appurl, this.fileNames[position]);
                return;
            case 3:
                loadImg(this.meurl, this.fileNames[position]);
                return;
            default:
                return;
        }
    }

    private String getFolderPath() {
        File folder = new File(FolderUtil.getCacheUserCode() + "/" + this.username + "/");
        if (!folder.exists()) {
            folder.mkdir();
        }
        return folder.getAbsolutePath();
    }

    private void loadImg(String imgUrl, String fileName) {
        File filePic = new File(getFolderPath(), fileName);
        this.picPath = filePic.getAbsolutePath();
        if (filePic.exists()) {
            getBitmapByPath(this.picPath);
            MyLog.w("localBitmap=" + this.picPath);
            return;
        }
        saveMyBitmap(imgUrl, filePic);
        MyLog.w(fileName + "_imgUrl=" + imgUrl);
    }

    public void getBitmapByPath(String picPath) {
        this.localBitmap = BitmapFactory.decodeFile(picPath, new BitmapFactory.Options());
        this.handler.sendEmptyMessage(2);
    }

    public void saveMyBitmap(final String imgUrl, final File filePic) {
        ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.ui.user.MyCodeActivity.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    InputStream is = new URL(imgUrl).openStream();
                    MyCodeActivity.this.urlBitmap = BitmapFactory.decodeStream(is);
                    MyCodeActivity.this.handler.sendEmptyMessage(1);
                    FileOutputStream out = new FileOutputStream(filePic);
                    MyCodeActivity.this.urlBitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
                    out.flush();
                    out.close();
                    is.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    MyLog.e("FileNotFoundException=" + e);
                } catch (IOException e2) {
                    e2.printStackTrace();
                    MyLog.e("IOException=" + e2);
                } catch (Exception e3) {
                    e3.printStackTrace();
                    MyLog.e("Exception=" + e3);
                }
            }
        });
    }

    public void clearLocalCode() {
        for (String fileName : this.fileNames) {
            new File(getFolderPath(), fileName).delete();
        }
        selectLoad(this.flag);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showForwardDialog() {
        if (this.shareCodeDialog == null) {
            this.shareCodeDialog = new ShareCodeDialog(this.context);
            this.shareCodeDialog.setListener(new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.user.MyCodeActivity.4
                @Override // com.hy.frame.common.BaseDialog.IConfirmListener
                public void onDlgConfirm(BaseDialog dlg, int resId) {
                    String meQroce = MyCodeActivity.this.getString(R.string.url_invite_copy, new Object[]{MyCodeActivity.this.guid});
                    String shopQroce = MyCodeActivity.this.getString(R.string.url_shop_copy, new Object[]{MyCodeActivity.this.username});
                    String appdownload = MyCodeActivity.this.getString(R.string.url_app_copy);
                    switch (MyCodeActivity.this.flag) {
                        case 0:
                        case 3:
                            MyCodeActivity.this.shareUtils.setShare(new ShareThirdBean("扫一扫二维码，注册App加我好友吧", meQroce, MyCodeActivity.this.meurl));
                            break;
                        case 1:
                            MyCodeActivity.this.shareUtils.setShare(new ShareThirdBean("扫一扫二维码，关注我的店铺吧", shopQroce, MyCodeActivity.this.shopurl));
                            break;
                        case 2:
                            MyCodeActivity.this.shareUtils.setShare(new ShareThirdBean("扫一扫二维码，下载APP", appdownload, MyCodeActivity.this.appurl));
                            break;
                    }
                    if (MyCodeActivity.this.localBitmap == null) {
                        MyCodeActivity.this.localBitmap = MyCodeActivity.this.urlBitmap;
                    }
                    switch (resId) {
                        case R.id.share_btnCircle /* 2131756355 */:
                            MyCodeActivity.this.shareUtils.wxSharePic(1, MyCodeActivity.this.localBitmap);
                            return;
                        case R.id.share_btnWx /* 2131756356 */:
                            MyCodeActivity.this.shareUtils.wxSharePic(0, MyCodeActivity.this.localBitmap);
                            return;
                        case R.id.share_btnQQfd /* 2131756357 */:
                            MyCodeActivity.this.shareUtils.qqSharePic(MyCodeActivity.this.picPath);
                            return;
                        case R.id.share_btnQzone /* 2131756358 */:
                            MyCodeActivity.this.shareUtils.qqZoneSharePic();
                            return;
                        case R.id.share_btnSave /* 2131756359 */:
                            FileUtils.saveAlbum(MyCodeActivity.this.context, new File(MyCodeActivity.this.picPath));
                            MyToast.show(MyCodeActivity.this.context, (int) R.string.save_success);
                            return;
                        case R.id.share_btnCopy /* 2131756360 */:
                            String url = "";
                            switch (MyCodeActivity.this.flag) {
                                case 0:
                                    MyToast.show(MyCodeActivity.this.context, "不支持此链接");
                                    break;
                                case 1:
                                    url = MyCodeActivity.this.getString(R.string.url_shop_copy, new Object[]{DemoHelper.getInstance().getCurrentUserName()});
                                    break;
                                case 2:
                                    url = MyCodeActivity.this.getString(R.string.url_app_copy);
                                    break;
                                case 3:
                                    url = MyCodeActivity.this.getString(R.string.invite_ems, new Object[]{MyCodeActivity.this.getUserInfo().getGuid()});
                                    break;
                            }
                            if (!TextUtils.isEmpty(url)) {
                                ComUtil.copySys(MyCodeActivity.this.context, url);
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                }
            });
        }
        this.shareCodeDialog.show();
    }

    private void shareWeChat(String path, String type) {
        ComponentName comp;
        Uri uriToImage = Uri.fromFile(new File(path));
        Intent shareIntent = new Intent();
        if (type.equals("circle")) {
            comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
        } else {
            comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");
        }
        shareIntent.setComponent(comp);
        shareIntent.setAction("android.intent.action.SEND");
        shareIntent.putExtra("android.intent.extra.STREAM", uriToImage);
        shareIntent.setType("image/jpeg");
        startActivity(Intent.createChooser(shareIntent, "分享图片"));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10103 || requestCode == 10104) {
            Tencent.onActivityResultData(requestCode, resultCode, data, this.shareUtils.getListener());
        }
    }
}
