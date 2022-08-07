package com.em.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.CameraUtil;
import com.cdlinglu.utils.ComUtil;
import com.easeui.EaseConstant;
import com.easeui.utils.EaseUserUtils;
import com.easeui.widget.EaseAlertDialog;
import com.easeui.widget.EaseExpandGridView;
import com.easeui.widget.EaseSwitchButton;
import com.em.DemoHelper;
import com.em.db.DemoDBManager;
import com.hy.frame.adapter.MyBaseAdapter;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.KeyValueView;
import com.hy.http.AjaxParams;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.NetUtils;
import com.tencent.open.wpa.WPA;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.GroupInfoBean;
import com.vsf2f.f2f.bean.GroupMembersBean;
import com.vsf2f.f2f.ui.dialog.ExitGroupDialog;
import com.vsf2f.f2f.ui.dialog.GroupCodeDialog;
import com.vsf2f.f2f.ui.dialog.PictureDialog;
import com.vsf2f.f2f.ui.dialog.WarnDialog;
import com.vsf2f.f2f.ui.qrcode.QrcodeUtil;
import com.vsf2f.f2f.ui.report.ReportMainActivity;
import com.vsf2f.f2f.ui.user.UserVipActivity;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import com.vsf2f.f2f.ui.utils.listener.UploadPicListener;
import com.vsf2f.f2f.ui.utils.upload.UploadUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class GroupDetailsActivity extends BaseActivity implements CameraUtil.CameraDealListener, PictureDialog.ConfirmDlgListener {
    private static final String ITEM_ADD = "item_member_name_add";
    private static final String ITEM_DEL = "item_member_name_del";
    private static final int REQUEST_CODE_ADD_USER = 1;
    private static final int REQUEST_CODE_EDIT_GROUPCARD = 7;
    private static final int REQUEST_CODE_EDIT_GROUPNAME = 6;
    private static final int REQUEST_CODE_EXIT = 0;
    private static final int REQUEST_CODE_UP_GROUPLOGO = 4;
    private static final int REQUEST_CODE_UP_GROUPNAME = 2;
    private static final int REQUEST_CODE_UP_INVITE_ALLOW = 5;
    private static final int REQUEST_CODE_UP_THOUSANDS = 3;
    public static GroupDetailsActivity instance;
    private GroupGridAdapter adapter;
    private String bizId;
    private CameraUtil camera;
    private String currentUser;
    private Button deleteBtn;
    private Button exitBtn;
    private ExitGroupDialog exitGroup;
    private FrameLayout flyGroupBlock;
    private FrameLayout flyGroupInvite;
    private FrameLayout flyGroupUpdate;
    private String groupId;
    private KeyValueView kvGroupName;
    private KeyValueView kvGroupNum;
    private PictureDialog pictureDlg;
    private ProgressDialog progressDialog;
    private LinearLayout show_all;
    private TextView show_all_item1;
    private TextView show_all_item2;
    private EaseSwitchButton switchButton;
    private EaseSwitchButton switchInvite;
    private EaseSwitchButton switchUpdate;
    private String updateGroupName;
    private boolean isCreator = false;
    private boolean isInvita = false;
    private String groupCardName = "";
    private GroupInfoBean groupInfo = new GroupInfoBean();
    private List<GroupMembersBean> groupMembersList = new ArrayList();
    private int num = 12;
    private boolean isShowAll = false;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.em_activity_group_details;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.group_chat_information, 0);
        this.groupId = getBundle().getString(EaseConstant.EXTRA_GROUP_ID);
        this.currentUser = DemoHelper.getInstance().getCurrentUserName();
        this.bizId = EaseUserUtils.getGroupBizId(this.groupId);
        if (this.groupId == null || this.currentUser == null) {
            finish();
            return;
        }
        instance = this;
        this.switchButton = (EaseSwitchButton) getView(R.id.switch_block);
        this.switchInvite = (EaseSwitchButton) getView(R.id.switch_invite);
        this.switchUpdate = (EaseSwitchButton) getView(R.id.switch_update);
        this.show_all_item1 = (TextView) getView(R.id.show_all_item1);
        this.show_all_item2 = (TextView) getView(R.id.show_all_item2);
        this.show_all = (LinearLayout) getViewAndClick(R.id.show_all);
        this.exitBtn = (Button) getViewAndClick(R.id.btn_exit_group);
        this.deleteBtn = (Button) getViewAndClick(R.id.btn_delete_group);
        this.kvGroupNum = (KeyValueView) getViewAndClick(R.id.groupinfo_kvGroupNum);
        this.kvGroupName = (KeyValueView) getViewAndClick(R.id.groupinfo_kvGroupName);
        this.flyGroupInvite = (FrameLayout) getViewAndClick(R.id.groupinfo_flyGroupInvite);
        this.flyGroupUpdate = (FrameLayout) getViewAndClick(R.id.groupinfo_flyGroupUpdate);
        this.flyGroupBlock = (FrameLayout) getViewAndClick(R.id.groupinfo_flyGroupBlock);
        setOnClickListener(R.id.groupinfo_kvSearchMsg);
        setOnClickListener(R.id.groupinfo_kvClearMsg);
        setOnClickListener(R.id.groupinfo_kvReport);
        setOnClickListener(R.id.groupinfo_flyQrcode);
        this.adapter = new GroupGridAdapter(this, this.groupMembersList);
        initGridView();
    }

    public void initGridView() {
        EaseExpandGridView userGridview = (EaseExpandGridView) getView(R.id.gridview);
        userGridview.setAdapter((ListAdapter) this.adapter);
        userGridview.setOnTouchListener(new View.OnTouchListener() { // from class: com.em.ui.GroupDetailsActivity.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case 1:
                        if (!GroupDetailsActivity.this.adapter.isInDeleteMode()) {
                            return false;
                        }
                        GroupDetailsActivity.this.adapter.setInDeleteMode(false);
                        GroupDetailsActivity.this.adapter.notifyDataSetChanged();
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        if (this.bizId != null) {
            ((KeyValueView) getViewAndClick(R.id.groupinfo_kvGroupId)).setValue(this.bizId);
        }
        getGroupInfo();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    public void getGroupInfo() {
        getClient().get(R.string.API_GET_GROUP_INFO, ComUtil.getF2FApi(this.context, getString(R.string.API_GET_GROUP_INFO, new Object[]{this.bizId})) + "?countUser=1&loadfriends=1", new AjaxParams(), GroupInfoBean.class, false);
    }

    public void deleteGroup() {
        getClient().setShowDialog(true);
        getClient().post(R.string.API_DEL_GROUP, ComUtil.getF2FApi(this.context, getString(R.string.API_DEL_GROUP, new Object[]{this.bizId})), new AjaxParams());
    }

    public void exitGroup() {
        getClient().setShowDialog(true);
        getClient().post(R.string.API_EXIT_GROUP, ComUtil.getF2FApi(this.context, getString(R.string.API_EXIT_GROUP, new Object[]{this.currentUser, this.bizId})), new AjaxParams());
    }

    public void getGroupMembers() {
        getClient().get(R.string.API_GET_GROUP_MEMBER, ComUtil.getF2FApi(this.context, getString(R.string.API_GET_GROUP_MEMBER, new Object[]{this.bizId})), new AjaxParams(), GroupMembersBean.class, true);
    }

    public void addGroupMembers(String users) {
        getClient().post(R.string.API_ADD_GROUP_MEMBER, ComUtil.getF2FApi(this.context, getString(R.string.API_ADD_GROUP_MEMBER, new Object[]{users, this.bizId})), new AjaxParams());
    }

    public void delGroupMembers(String user) {
        getClient().setShowDialog(true);
        getClient().post(R.string.API_DEL_GROUP_MEMBER, ComUtil.getF2FApi(this.context, getString(R.string.API_DEL_GROUP_MEMBER, new Object[]{user, RequestParameters.SUBRESOURCE_DELETE, this.bizId})), new AjaxParams());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAvatar(String picId) {
        AjaxParams params = new AjaxParams();
        params.put("pictureId", picId);
        getClient().post(4, ComUtil.getF2FApi(this.context, getString(R.string.API_UP_GROUP, new Object[]{this.bizId})), params);
    }

    public void upGroupName(String name) {
        getClient().setShowDialog(true);
        AjaxParams params = new AjaxParams();
        params.put("groupName", name);
        getClient().post(2, ComUtil.getF2FApi(this.context, getString(R.string.API_UP_GROUP, new Object[]{this.bizId})), params);
    }

    public void upGroupCard(String name) {
        getClient().setShowDialog(true);
        AjaxParams params = new AjaxParams();
        params.put("nickName", name);
        getClient().post(R.string.API_UP_GROUP_REMARK, ComUtil.getF2FApi(this.context, getString(R.string.API_UP_GROUP_REMARK, new Object[]{this.bizId})), params);
    }

    public void openInvite(boolean invite) {
        int i = 1;
        try {
            JSONObject jsonObject = new JSONObject();
            JSONObject config = new JSONObject();
            if (!invite) {
                i = 0;
            }
            config.put("invita", i);
            jsonObject.put("config", config);
            String url = ComUtil.getF2FApi(this.context, getString(R.string.API_UP_GROUP, new Object[]{this.bizId}));
            getClient().setShowDialog(true);
            getClient().post(5, url, jsonObject.toString(), null, String.class, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (invite) {
            this.switchInvite.openSwitch();
        } else {
            this.switchInvite.closeSwitch();
        }
    }

    public void upgradeGroup() {
        if (getUserInfo().getLv() == 0) {
            showWarnDlg();
            return;
        }
        try {
            JSONObject jsonObject = new JSONObject();
            JSONObject config = new JSONObject();
            config.put("limit", 2000);
            jsonObject.put("config", config);
            getClient().post(3, ComUtil.getF2FApi(this.context, getString(R.string.API_UP_GROUP, new Object[]{this.bizId})), jsonObject.toString(), null, String.class, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void showWarnDlg() {
        new WarnDialog(this.context, getString(R.string.not_open_pms_prompt2), getString(R.string.open_vip_now), true, true, false).setListener(new BaseDialog.IConfirmListener() { // from class: com.em.ui.GroupDetailsActivity.2
            @Override // com.hy.frame.common.BaseDialog.IConfirmListener
            public void onDlgConfirm(BaseDialog dlg, int flag) {
                switch (flag) {
                    case 0:
                        GroupDetailsActivity.this.startAct(UserVipActivity.class);
                        return;
                    default:
                        return;
                }
            }
        }).show();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case 2:
                if (!TextUtils.isEmpty(this.updateGroupName)) {
                    this.groupInfo.setGroupName(this.updateGroupName);
                    EaseUserUtils.upGroupName(this.groupId, this.updateGroupName);
                    setResult(-1);
                    refreshTitle();
                    break;
                }
                break;
            case 3:
                MyToast.show(this.context, getString(R.string.toast_upgrade_success));
                this.switchUpdate.openSwitch();
                return;
            case 4:
                MyToast.show(this.context, "群头像修改成功");
                return;
            case 5:
                MyToast.show(this.context, getString(R.string.toast_change_success));
                return;
            case R.string.API_ADD_GROUP_MEMBER /* 2131296286 */:
                MyToast.show(this.context, "添加成功");
                getGroupMembers();
                return;
            case R.string.API_DEL_GROUP /* 2131296322 */:
                MyToast.show(this.context, "解散成功");
                deleteGroupData();
                finish();
                return;
            case R.string.API_DEL_GROUP_MEMBER /* 2131296323 */:
                MyToast.show(this.context, "踢出成功");
                refreshMembers();
                return;
            case R.string.API_EXIT_GROUP /* 2131296334 */:
                MyToast.show(this.context, "退出成功");
                exitGroupData();
                finish();
                return;
            case R.string.API_GET_GROUP_INFO /* 2131296355 */:
                this.groupInfo = (GroupInfoBean) result.getObj();
                if (this.groupInfo != null) {
                    if (this.currentUser.equals(this.groupInfo.getCreatorName())) {
                        this.isCreator = true;
                        this.isInvita = true;
                        this.num -= 2;
                    } else if (this.groupInfo.getConfig().getInvita() == 1) {
                        this.isInvita = true;
                        this.num--;
                    }
                    updateUI();
                    return;
                }
                return;
            case R.string.API_GET_GROUP_MEMBER /* 2131296356 */:
                this.groupMembersList = (List) result.getObj();
                this.groupInfo.setMembersNum(this.groupMembersList.size());
                refreshMembers();
                return;
            case R.string.API_UP_GROUP_REMARK /* 2131296450 */:
                break;
            default:
                return;
        }
        MyToast.show(this.context, getString(R.string.toast_change_success));
        updateListCardName(this.groupCardName);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
        switch (result.getRequestCode()) {
            case 5:
                MyToast.show(this.context, getString(R.string.toast_change_failed));
                if (!this.switchInvite.isSwitchOpen()) {
                    this.switchInvite.openSwitch();
                    break;
                } else {
                    this.switchInvite.closeSwitch();
                    break;
                }
            case R.string.API_DEL_GROUP_MEMBER /* 2131296323 */:
                break;
            default:
                return;
        }
        refreshMembers();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        updateMembers();
        this.adapter.setCreator(this.groupInfo.getCreatorName());
        if (this.isCreator) {
            this.deleteBtn.setVisibility(0);
            if (this.groupInfo.getConfig().getInvita() == 1) {
                this.switchInvite.openSwitch();
            } else {
                this.switchInvite.closeSwitch();
            }
            if (this.groupInfo.getConfig().getLimit() <= 200) {
                this.switchUpdate.closeSwitch();
            } else {
                this.switchUpdate.openSwitch();
            }
        } else {
            this.exitBtn.setVisibility(0);
            this.flyGroupBlock.setVisibility(8);
            this.flyGroupUpdate.setVisibility(8);
            this.flyGroupInvite.setVisibility(8);
        }
    }

    private void refreshTitle() {
        if (this.groupInfo == null) {
            this.groupInfo = new GroupInfoBean();
        }
        if (this.groupInfo.getGroupName() == null) {
            setTitle(getString(R.string.group_chat_information));
        } else if (getMembersCount() > 0) {
            setTitle(getString(R.string.group_chat_information) + "(" + getMembersCount() + "人)");
        } else {
            setTitle(getString(R.string.group_chat_information));
        }
        this.kvGroupName.setValue(this.groupInfo.getGroupName());
        this.kvGroupNum.setValue(getMembersCount() + "/" + this.groupInfo.getConfig().getLimit());
    }

    public void refreshCardName() {
        ThreadPool.newThreadPool(new Runnable() { // from class: com.em.ui.GroupDetailsActivity.3
            @Override // java.lang.Runnable
            public void run() {
                for (int i = 0; i < GroupDetailsActivity.this.groupMembersList.size(); i++) {
                    if (((GroupMembersBean) GroupDetailsActivity.this.groupMembersList.get(i)).getUserName().equals(GroupDetailsActivity.this.currentUser)) {
                        GroupDetailsActivity.this.groupCardName = ((GroupMembersBean) GroupDetailsActivity.this.groupMembersList.get(i)).getNickName();
                    }
                }
            }
        });
    }

    public void updateListCardName(String groupCardName) {
        for (int i = 0; i < this.groupMembersList.size(); i++) {
            if (this.groupMembersList.get(i).getUserName().equals(this.currentUser)) {
                this.groupMembersList.get(i).setNickName(groupCardName);
            }
        }
        if (this.adapter != null) {
            this.adapter.notifyDataSetChanged();
        }
    }

    private void updateMembers() {
        if (this.groupInfo != null && this.groupInfo.getFriends() != null) {
            this.groupMembersList = this.groupInfo.getFriends();
            refreshMembers();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshMembers() {
        refreshTitle();
        if (HyUtil.isNoEmpty(this.groupMembersList)) {
            setAdapterList();
            this.adapter.notifyDataSetChanged();
            return;
        }
        this.groupMembersList = new ArrayList();
    }

    private int getMembersCount() {
        if (this.groupInfo != null) {
            return this.groupMembersList.size();
        }
        return 0;
    }

    private void setAdapterList() {
        int i = 12;
        if (this.groupMembersList.size() >= 12) {
            if (this.isShowAll) {
                this.show_all_item1.setVisibility(8);
                this.show_all_item2.setVisibility(0);
                this.adapter.setList(this.groupMembersList);
            } else {
                this.show_all_item1.setVisibility(0);
                this.show_all_item2.setVisibility(8);
                GroupGridAdapter groupGridAdapter = this.adapter;
                List<GroupMembersBean> list = this.groupMembersList;
                if (this.isInvita) {
                    i = 11;
                }
                groupGridAdapter.setList(list.subList(0, i));
            }
            this.adapter.setAll(this.isShowAll);
            this.show_all.setVisibility(0);
        } else {
            this.adapter.setAll(true);
            this.adapter.setList(this.groupMembersList);
            this.show_all.setVisibility(8);
        }
        this.adapter.addList(addBtnData());
        this.adapter.notifyDataSetChanged();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        boolean z = true;
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.groupinfo_flyGroupInvite /* 2131755809 */:
                if (this.groupInfo == null || !HyUtil.isNetworkConnected(this.context)) {
                    Toast.makeText(this.context, "未连接网络", 0).show();
                    return;
                }
                if (this.switchInvite.isSwitchOpen()) {
                    z = false;
                }
                openInvite(z);
                return;
            case R.id.show_all /* 2131756528 */:
                if (this.isShowAll) {
                    z = false;
                }
                this.isShowAll = z;
                setAdapterList();
                return;
            case R.id.groupinfo_kvGroupName /* 2131756531 */:
                if (this.isCreator) {
                    bundle.putString("title", getString(R.string.change_the_group_name));
                    bundle.putString(EditActivity.CONTENT, this.groupInfo.getGroupName());
                    startActForResult(EditActivity.class, bundle, 6);
                    return;
                }
                MyToast.show(this.context, "群主才能更改群名称");
                return;
            case R.id.groupinfo_flyQrcode /* 2131756532 */:
                if (this.isCreator || this.groupInfo.getConfig().getInvita() == 1) {
                    int width = this.context.getResources().getDisplayMetrics().widthPixels - (this.context.getResources().getDimensionPixelSize(R.dimen.margin_big) * 2);
                    new GroupCodeDialog(this.context, this.groupInfo.getGroupName(), this.groupInfo.getGroupPic().getSpath(), QrcodeUtil.createQrImage("vsf2f://addgroup#" + this.bizId, width, width)).show();
                    return;
                }
                MyToast.show(this.context, "  需群主允许群成员邀请\n才可以查看与分享二维码！");
                return;
            case R.id.groupinfo_kvGroupMyName /* 2131756534 */:
                bundle.putString("title", "设置我在本群的昵称");
                bundle.putString(EditActivity.CONTENT, this.groupCardName);
                startActForResult(EditActivity.class, bundle, 7);
                return;
            case R.id.groupinfo_flyGroupUpdate /* 2131756537 */:
                if (this.groupInfo == null || this.groupInfo.getConfig().getLimit() <= 1000) {
                    upgradeGroup();
                    return;
                } else {
                    MyToast.show(this.context, "已经是千人大群了，无需再升级");
                    return;
                }
            case R.id.groupinfo_flyGroupBlock /* 2131756540 */:
                toggleBlockGroup();
                return;
            case R.id.groupinfo_kvBlacklist /* 2131756542 */:
            default:
                return;
            case R.id.groupinfo_kvSearchMsg /* 2131756543 */:
                startActivity(new Intent(this, GroupSearchMessageActivity.class).putExtra(EaseConstant.EXTRA_GROUP_ID, this.groupId));
                return;
            case R.id.groupinfo_kvClearMsg /* 2131756544 */:
                new EaseAlertDialog((Context) this, (String) null, getResources().getString(R.string.sure_to_empty_this), (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.em.ui.GroupDetailsActivity.4
                    @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                    public void onResult(boolean confirmed, Bundle bundle2) {
                        if (confirmed) {
                            GroupDetailsActivity.this.clearGroupHistoryBtn();
                        }
                    }
                }, true).show();
                return;
            case R.id.groupinfo_kvReport /* 2131756545 */:
                bundle.putInt("objType", 3);
                bundle.putString("objId", this.bizId);
                bundle.putString("reportedUser", this.groupInfo.getCreatorId() + "");
                startAct(ReportMainActivity.class, bundle);
                return;
            case R.id.btn_exit_group /* 2131756546 */:
                deleteGroupBtn(false);
                return;
            case R.id.btn_delete_group /* 2131756547 */:
                deleteGroupBtn(true);
                return;
        }
    }

    private void showPictureDlg() {
        if (this.camera == null) {
            this.camera = new CameraUtil(this, this);
        }
        if (this.pictureDlg == null) {
            this.pictureDlg = new PictureDialog(this.context);
            this.pictureDlg.init(this);
        }
        this.pictureDlg.show();
    }

    @Override // com.cdlinglu.utils.CameraUtil.CameraDealListener
    public void onCameraTakeSuccess(String path) {
        MyLog.e("onCameraTakeSuccess: " + path);
        this.camera.cropImageUri(1, 1, 256);
    }

    @Override // com.cdlinglu.utils.CameraUtil.CameraDealListener
    public void onCameraPickSuccess(String path) {
        MyLog.e("onCameraPickSuccess: " + path);
        this.camera.cropImageUri(path, 1, 1, 256);
    }

    @Override // com.cdlinglu.utils.CameraUtil.CameraDealListener
    public void onCameraCutSuccess(String path) {
        getClient().setShowDialog(R.string.toast_uploading);
        new File(path);
        List<String> paths = new ArrayList<>();
        paths.add(path);
        new UploadUtils().UploadPicturesGetOSS(this.context, this.currentUser, Constant.USER_BUCKET, 14, paths, null, new UploadPicListener() { // from class: com.em.ui.GroupDetailsActivity.5
            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onSuccess(List<Map<String, String>> list, List<String> picIds) {
                GroupDetailsActivity.this.updateAvatar(picIds.get(0));
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onFailed() {
                MyToast.show(GroupDetailsActivity.this.context, (int) R.string.upload_failed);
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onProgress(long currentSize, long totalSize) {
                MyLog.e("onProgress", currentSize + "/" + totalSize);
            }
        });
    }

    @Override // com.cdlinglu.utils.CameraUtil.CameraDealListener
    public void onFunctionCancel(String uri) {
    }

    @Override // com.vsf2f.f2f.ui.dialog.PictureDialog.ConfirmDlgListener
    public void onDlgCameraClick(PictureDialog dlg) {
        if (this.camera != null) {
            this.camera.onDlgCameraClick();
        }
    }

    @Override // com.vsf2f.f2f.ui.dialog.PictureDialog.ConfirmDlgListener
    public void onDlgPhotoClick(PictureDialog dlg) {
        if (this.camera != null) {
            this.camera.onDlgPhotoClick();
        }
    }

    @Override // com.vsf2f.f2f.ui.dialog.PictureDialog.ConfirmDlgListener
    public void onDlgCancelClick(PictureDialog dlg) {
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onLeftClick() {
        back();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        back();
    }

    public void back() {
        setResult(-1);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        instance = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (this.camera != null) {
            this.camera.onActivityResult(requestCode, resultCode, data);
        }
        String st1 = getResources().getString(R.string.being_added);
        if (resultCode == -1) {
            if (this.progressDialog == null) {
                this.progressDialog = new ProgressDialog(this);
                this.progressDialog.setMessage(st1);
                this.progressDialog.setCanceledOnTouchOutside(false);
            }
            switch (requestCode) {
                case 1:
                    getGroupMembers();
                    return;
                case 6:
                    String returnData = data.getStringExtra("data");
                    if (!TextUtils.isEmpty(returnData)) {
                        this.updateGroupName = returnData;
                        upGroupName(returnData);
                        return;
                    }
                    return;
                case 7:
                    String remark = data.getStringExtra("data");
                    if (!TextUtils.isEmpty(remark)) {
                        upGroupCard(remark);
                        this.groupCardName = remark;
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    private List<GroupMembersBean> addBtnData() {
        List<GroupMembersBean> addBtnList = new ArrayList<>();
        if (this.isInvita) {
            addBtnList.add(new GroupMembersBean(ITEM_ADD));
        }
        if (this.isCreator && this.adapter.isAll()) {
            addBtnList.add(new GroupMembersBean(ITEM_DEL));
        }
        return addBtnList;
    }

    protected void addUserToBlackList(final String username) {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setCanceledOnTouchOutside(false);
        pd.setMessage(getString(R.string.moving_to_blacklist));
        pd.show();
        ThreadPool.newThreadPool(new Runnable() { // from class: com.em.ui.GroupDetailsActivity.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EMClient.getInstance().groupManager().blockUser(GroupDetailsActivity.this.groupId, username);
                    GroupDetailsActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.GroupDetailsActivity.6.1
                        @Override // java.lang.Runnable
                        public void run() {
                            GroupDetailsActivity.this.refreshMembers();
                            pd.dismiss();
                            Toast.makeText(GroupDetailsActivity.this.getApplicationContext(), (int) R.string.move_into_blacklist_success, 0).show();
                        }
                    });
                } catch (HyphenateException e) {
                    GroupDetailsActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.GroupDetailsActivity.6.2
                        @Override // java.lang.Runnable
                        public void run() {
                            pd.dismiss();
                            Toast.makeText(GroupDetailsActivity.this.getApplicationContext(), (int) R.string.failed_to_move_into, 0).show();
                        }
                    });
                }
            }
        });
    }

    public void deleteGroupBtn(final boolean owner) {
        if (this.exitGroup == null) {
            this.exitGroup = new ExitGroupDialog(this.context, this.isCreator);
            this.exitGroup.setListener(new BaseDialog.IConfirmListener() { // from class: com.em.ui.GroupDetailsActivity.7
                @Override // com.hy.frame.common.BaseDialog.IConfirmListener
                public void onDlgConfirm(BaseDialog dlg, int flag) {
                    switch (flag) {
                        case 1:
                            if (owner) {
                                GroupDetailsActivity.this.deleteGroup();
                                return;
                            } else {
                                GroupDetailsActivity.this.exitGroup();
                                return;
                            }
                        default:
                            return;
                    }
                }
            });
        }
        this.exitGroup.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearGroupHistoryBtn() {
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(this.groupId, EMConversation.EMConversationType.GroupChat);
        if (conversation != null) {
            conversation.clearAllMessages();
            DemoDBManager.getInstance().clearMessage();
        }
        Toast.makeText(this, (int) R.string.messages_are_empty, 0).show();
    }

    private void exitGroupData() {
        getResources().getString(R.string.exit_the_group_chat_failure);
        ThreadPool.newThreadPool(new Runnable() { // from class: com.em.ui.GroupDetailsActivity.8
            @Override // java.lang.Runnable
            public void run() {
                try {
                    DemoHelper.getInstance().deleteGroup(GroupDetailsActivity.this.groupId);
                    GroupDetailsActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.GroupDetailsActivity.8.1
                        @Override // java.lang.Runnable
                        public void run() {
                            GroupDetailsActivity.this.finish();
                            if (ChatActivity.activityInstance != null) {
                                ChatActivity.activityInstance.finish();
                            }
                        }
                    });
                } catch (Exception e) {
                    GroupDetailsActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.GroupDetailsActivity.8.2
                        @Override // java.lang.Runnable
                        public void run() {
                            e.printStackTrace();
                        }
                    });
                }
            }
        });
    }

    private void deleteGroupData() {
        getResources().getString(R.string.dissolve_group_chat_tofail);
        ThreadPool.newThreadPool(new Runnable() { // from class: com.em.ui.GroupDetailsActivity.9
            @Override // java.lang.Runnable
            public void run() {
                try {
                    DemoHelper.getInstance().deleteGroup(GroupDetailsActivity.this.groupId);
                    GroupDetailsActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.GroupDetailsActivity.9.1
                        @Override // java.lang.Runnable
                        public void run() {
                            GroupDetailsActivity.this.finish();
                            if (ChatActivity.activityInstance != null) {
                                ChatActivity.activityInstance.finish();
                            }
                        }
                    });
                } catch (Exception e) {
                    GroupDetailsActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.GroupDetailsActivity.9.2
                        @Override // java.lang.Runnable
                        public void run() {
                            e.printStackTrace();
                        }
                    });
                }
            }
        });
    }

    private void addMembersToGroupData(final String[] newmembers) {
        final String st6 = getResources().getString(R.string.add_group_members_fail);
        ThreadPool.newThreadPool(new Runnable() { // from class: com.em.ui.GroupDetailsActivity.10
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (GroupDetailsActivity.this.currentUser.equals(GroupDetailsActivity.this.groupInfo.getCreatorName())) {
                        EMClient.getInstance().groupManager().addUsersToGroup(GroupDetailsActivity.this.groupId, newmembers);
                    } else {
                        EMClient.getInstance().groupManager().inviteUser(GroupDetailsActivity.this.groupId, newmembers, null);
                    }
                    GroupDetailsActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.GroupDetailsActivity.10.1
                        @Override // java.lang.Runnable
                        public void run() {
                            GroupDetailsActivity.this.refreshMembers();
                        }
                    });
                } catch (Exception e) {
                    GroupDetailsActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.GroupDetailsActivity.10.2
                        @Override // java.lang.Runnable
                        public void run() {
                            Toast.makeText(GroupDetailsActivity.this.getApplicationContext(), st6 + e.getMessage(), 0).show();
                        }
                    });
                }
            }
        });
    }

    private void toggleBlockGroup() {
        if (this.switchButton.isSwitchOpen()) {
            MyLog.e("change to unblock group msg");
            if (this.progressDialog == null) {
                this.progressDialog = new ProgressDialog(this);
                this.progressDialog.setCanceledOnTouchOutside(false);
            }
            this.progressDialog.setMessage(getString(R.string.is_unblock));
            this.progressDialog.show();
            ThreadPool.newThreadPool(new Runnable() { // from class: com.em.ui.GroupDetailsActivity.11
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        EMClient.getInstance().groupManager().unblockGroupMessage(GroupDetailsActivity.this.groupId);
                        GroupDetailsActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.GroupDetailsActivity.11.1
                            @Override // java.lang.Runnable
                            public void run() {
                                GroupDetailsActivity.this.switchButton.closeSwitch();
                                GroupDetailsActivity.this.progressDialog.dismiss();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                        GroupDetailsActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.GroupDetailsActivity.11.2
                            @Override // java.lang.Runnable
                            public void run() {
                                GroupDetailsActivity.this.progressDialog.dismiss();
                                Toast.makeText(GroupDetailsActivity.this.getApplicationContext(), (int) R.string.remove_group_of, 0).show();
                            }
                        });
                    }
                }
            });
            return;
        }
        String st8 = getResources().getString(R.string.group_is_blocked);
        final String st9 = getResources().getString(R.string.group_of_shielding);
        MyLog.d("change to block group msg");
        if (this.progressDialog == null) {
            this.progressDialog = new ProgressDialog(this);
            this.progressDialog.setCanceledOnTouchOutside(false);
        }
        this.progressDialog.setMessage(st8);
        this.progressDialog.show();
        ThreadPool.newThreadPool(new Runnable() { // from class: com.em.ui.GroupDetailsActivity.12
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EMClient.getInstance().groupManager().blockGroupMessage(GroupDetailsActivity.this.groupId);
                    GroupDetailsActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.GroupDetailsActivity.12.1
                        @Override // java.lang.Runnable
                        public void run() {
                            GroupDetailsActivity.this.switchButton.openSwitch();
                            GroupDetailsActivity.this.progressDialog.dismiss();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    GroupDetailsActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.GroupDetailsActivity.12.2
                        @Override // java.lang.Runnable
                        public void run() {
                            GroupDetailsActivity.this.progressDialog.dismiss();
                            Toast.makeText(GroupDetailsActivity.this.getApplicationContext(), st9, 0).show();
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class GroupGridAdapter extends MyBaseAdapter<GroupMembersBean> {
        private String creator;
        private boolean isAll;
        private boolean isInDeleteMode = false;

        public GroupGridAdapter(Context context, List<GroupMembersBean> objects) {
            super(context, objects);
        }

        public boolean isAll() {
            return this.isAll;
        }

        public void setAll(boolean all) {
            this.isAll = all;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public boolean isInDeleteMode() {
            return this.isInDeleteMode;
        }

        public void setInDeleteMode(boolean inDeleteMode) {
            this.isInDeleteMode = inDeleteMode;
        }

        @Override // android.widget.Adapter
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_group_detail_grid, (ViewGroup) null);
                holder = new ViewHolder();
                holder.imageView = (ImageView) convertView.findViewById(R.id.iv_avatar);
                holder.textView = (TextView) convertView.findViewById(R.id.tv_name);
                holder.badgeDeleteView = (ImageView) convertView.findViewById(R.id.badge_delete);
                holder.badgeCreateView = (ImageView) convertView.findViewById(R.id.badge_create);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            LinearLayout button = (LinearLayout) convertView.findViewById(R.id.button_avatar);
            final GroupMembersBean members = getItem(position);
            holder.badgeCreateView.setVisibility(8);
            if (isAll() && GroupDetailsActivity.ITEM_DEL.equals(members.getUserName())) {
                holder.textView.setText("");
                holder.imageView.setImageResource(R.mipmap.icon_group_reduce);
                if (!GroupDetailsActivity.this.isCreator) {
                    convertView.setVisibility(8);
                } else {
                    if (this.isInDeleteMode) {
                        convertView.setVisibility(8);
                    } else {
                        convertView.setVisibility(0);
                        holder.badgeDeleteView.setVisibility(8);
                    }
                    final String st10 = GroupDetailsActivity.this.getResources().getString(R.string.delete_button_is_clicked);
                    button.setOnClickListener(new View.OnClickListener() { // from class: com.em.ui.GroupDetailsActivity.GroupGridAdapter.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View v) {
                            MyLog.d(st10);
                            GroupGridAdapter.this.isInDeleteMode = true;
                            GroupGridAdapter.this.notifyDataSetChanged();
                        }
                    });
                }
            } else if (GroupDetailsActivity.ITEM_ADD.equals(members.getUserName())) {
                holder.textView.setText("");
                holder.imageView.setImageResource(R.mipmap.icon_group_add);
                if (!GroupDetailsActivity.this.isInvita) {
                    convertView.setVisibility(8);
                } else {
                    if (this.isInDeleteMode) {
                        convertView.setVisibility(8);
                    } else {
                        convertView.setVisibility(0);
                        holder.badgeDeleteView.setVisibility(8);
                    }
                    final String st11 = GroupDetailsActivity.this.getResources().getString(R.string.add_a_button_was_clicked);
                    button.setOnClickListener(new View.OnClickListener() { // from class: com.em.ui.GroupDetailsActivity.GroupGridAdapter.2
                        @Override // android.view.View.OnClickListener
                        public void onClick(View v) {
                            MyLog.d(st11);
                            Bundle bundle = new Bundle();
                            bundle.putString(com.hy.frame.util.Constant.FLAG_TYPE, "add");
                            bundle.putString(com.hy.frame.util.Constant.FLAG, GroupDetailsActivity.this.bizId);
                            GroupDetailsActivity.this.startActForResult(GroupPickContactsActivity.class, bundle, 1);
                        }
                    });
                }
            } else {
                convertView.setVisibility(0);
                button.setVisibility(0);
                holder.textView.setText(members.getNickName());
                if (members.getUserPic() != null) {
                    EaseUserUtils.setAvatarByPath(getContext(), members.getUserPic().getSpath(), holder.imageView);
                } else {
                    holder.imageView.setImageResource(R.mipmap.def_head);
                }
                if (position == 0) {
                    holder.badgeDeleteView.setVisibility(8);
                    holder.badgeCreateView.setVisibility(0);
                } else if (this.isInDeleteMode) {
                    holder.badgeDeleteView.setVisibility(0);
                } else {
                    holder.badgeDeleteView.setVisibility(8);
                }
                final String st12 = GroupDetailsActivity.this.getResources().getString(R.string.not_delete_myself);
                GroupDetailsActivity.this.getResources().getString(R.string.are_removed);
                GroupDetailsActivity.this.getResources().getString(R.string.delete_failed);
                final String st15 = GroupDetailsActivity.this.getResources().getString(R.string.confirm_the_members);
                button.setOnClickListener(new View.OnClickListener() { // from class: com.em.ui.GroupDetailsActivity.GroupGridAdapter.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        if (!GroupGridAdapter.this.isInDeleteMode) {
                            Bundle bundle = new Bundle();
                            bundle.putString("username", members.getUserName());
                            GroupDetailsActivity.this.startAct(UserProfileActivity.class, bundle);
                        } else if (GroupDetailsActivity.this.currentUser.equals(members.getUserName())) {
                            new EaseAlertDialog(GroupDetailsActivity.this, st12).show();
                        } else if (!NetUtils.hasNetwork(GroupDetailsActivity.this.getApplicationContext())) {
                            Toast.makeText(GroupDetailsActivity.this.getApplicationContext(), GroupDetailsActivity.this.getString(R.string.network_unavailable), 0).show();
                        } else {
                            MyLog.d(WPA.CHAT_TYPE_GROUP, "remove user from group:" + members.getNickName());
                            GroupDetailsActivity.this.delGroupMembers(members.getUserName());
                            GroupDetailsActivity.this.groupMembersList.remove(position);
                        }
                    }
                });
                button.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.em.ui.GroupDetailsActivity.GroupGridAdapter.4
                    @Override // android.view.View.OnLongClickListener
                    public boolean onLongClick(View v) {
                        if (EMClient.getInstance().getCurrentUser().equals(GroupDetailsActivity.this.currentUser)) {
                            return true;
                        }
                        if (GroupDetailsActivity.this.currentUser.equals(GroupDetailsActivity.this.groupInfo.getCreatorName())) {
                            new EaseAlertDialog((Context) GroupDetailsActivity.this, (String) null, st15, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.em.ui.GroupDetailsActivity.GroupGridAdapter.4.1
                                @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                                public void onResult(boolean confirmed, Bundle bundle) {
                                    if (confirmed) {
                                    }
                                }
                            }, true).show();
                        }
                        return false;
                    }
                });
            }
            return convertView;
        }

        @Override // com.hy.frame.adapter.MyBaseAdapter, android.widget.Adapter
        public int getCount() {
            return super.getCount();
        }
    }

    /* loaded from: classes.dex */
    private static class ViewHolder {
        ImageView badgeCreateView;
        ImageView badgeDeleteView;
        ImageView imageView;
        TextView textView;

        private ViewHolder() {
        }
    }
}
