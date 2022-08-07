package com.vsf2f.f2f.ui.circle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.FlagUtil;
import com.cdlinglu.utils.ShareUtils;
import com.easeui.widget.EaseAlertDialog;
import com.em.DemoHelper;
import com.em.ui.EditActivity;
import com.hy.frame.adapter.IAdapterListener;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.RefreshRecyclerView;
import com.hy.frame.view.recycler.callback.XRefreshViewListener;
import com.hy.http.AjaxParams;
import com.hyphenate.chat.MessageEncoder;
import com.tencent.tauth.Tencent;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.MyCircleAdapter;
import com.vsf2f.f2f.bean.CircleListBean;
import com.vsf2f.f2f.bean.CircleReadBean;
import com.vsf2f.f2f.ui.dialog.EditEnterDialog;
import com.vsf2f.f2f.ui.dialog.ShareForwardDialog;
import com.yolanda.nohttp.tools.NetUtil;
import java.util.List;

/* loaded from: classes2.dex */
public class MyCircleActivity extends BaseActivity implements XRefreshViewListener, IAdapterListener {
    private MyCircleAdapter adapter;
    private String clickFlag;
    private List<CircleReadBean> datas;
    private ShareForwardDialog editDialog;
    private boolean fresh;
    private String msgId;
    private int position;
    private String prevMsgId;
    private CircleReadBean readBean;
    private RefreshRecyclerView refreshView;
    private ShareUtils shareUtils;
    private int PAGE_SIZE = 20;
    private int PAGE_INDEX = 1;
    private long lastTime = 0;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_user_circle;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.mine_circle, 0);
        this.refreshView = (RefreshRecyclerView) getView(R.id.circles_recyclerView);
        this.refreshView.setOnRefreshListener(this);
        this.shareUtils = new ShareUtils(this.context);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        requestData();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        requestData(this.PAGE_INDEX);
    }

    public void requestData(int index) {
        AjaxParams params = new AjaxParams();
        String url = ComUtil.getF2FApi(this.context, getString(R.string.API_MYSELF_CIRCLES_MESSAGE) + "?lastTime=" + this.lastTime);
        if (this.fresh) {
            getClient().setShowDialog(false);
        } else {
            getClient().setShowDialog(true);
        }
        getClient().get(R.string.API_CIRCLES_MESSAGE, url, params, CircleListBean.class, false);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        if (HyUtil.isEmpty(this.datas)) {
            showNoData();
            return;
        }
        showCView();
        if (this.adapter == null) {
            this.adapter = new MyCircleAdapter(this.context, this.datas);
            this.adapter.setListener(this);
            this.refreshView.setAdapter(this.adapter);
            return;
        }
        this.adapter.setDatas(this.datas);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        boolean z;
        switch (result.getRequestCode()) {
            case R.string.API_CIRCLES_COMMENT_FORWARD /* 2131296307 */:
                if (this.clickFlag.equals("comment")) {
                    MyToast.show(this.context, getString(R.string.circle_comment));
                    if (this.readBean.getMsgHost() != null) {
                        if (this.readBean.getMsgHost().getComment() != null) {
                            this.readBean.getMsgHost().setComment((Integer.parseInt(this.readBean.getMsgHost().getComment()) + 1) + "");
                        } else {
                            this.readBean.getMsgHost().setComment("1");
                        }
                    }
                    updateUI();
                    return;
                } else if (this.clickFlag.equals(FlagUtil.URL_FORWARD)) {
                    MyToast.show(this.context, getString(R.string.circle_forward));
                    if (this.readBean.getMsgHost() != null) {
                        if (this.readBean.getMsgHost().getForward() != null) {
                            this.readBean.getMsgHost().setForward((Integer.parseInt(this.readBean.getMsgHost().getForward()) + 1) + "");
                        } else {
                            this.readBean.getMsgHost().setForward("1");
                        }
                    }
                    onRefresh();
                    return;
                } else {
                    return;
                }
            case R.string.API_CIRCLES_MESSAGE /* 2131296308 */:
                this.refreshView.setRefreshComplete();
                CircleListBean lists = (CircleListBean) result.getObj();
                List<CircleReadBean> beans = lists.getInfos();
                RefreshRecyclerView refreshRecyclerView = this.refreshView;
                if (lists.getLastTime() != 0) {
                    z = true;
                } else {
                    z = false;
                }
                refreshRecyclerView.setLoadMoreComplete(z);
                if (this.PAGE_INDEX <= 1) {
                    this.datas = beans;
                } else if (beans != null) {
                    this.datas.addAll(beans);
                }
                updateUI();
                return;
            case R.string.API_CIRCLES_PUBLIC /* 2131296309 */:
            case R.string.API_CIRCLES_READ /* 2131296310 */:
            default:
                return;
            case R.string.API_CIRCLES_ZAN_SAVE /* 2131296311 */:
                if (this.clickFlag.equals(FlagUtil.URL_SAVE)) {
                    MyToast.show(this.context, getString(R.string.circle_save));
                    this.readBean.setCollection(true);
                    if (this.readBean.getMsgHost() != null) {
                        if (this.readBean.getMsgHost().getCollection() != null) {
                            this.readBean.getMsgHost().setCollection((Integer.parseInt(this.readBean.getMsgHost().getCollection()) + 1) + "");
                        } else {
                            this.readBean.getMsgHost().setCollection("1");
                        }
                    }
                } else if (this.clickFlag.equals(FlagUtil.URL_ZAN)) {
                    MyToast.show(this.context, getString(R.string.circle_zan));
                    this.readBean.setLike(true);
                    if (this.readBean.getMsgHost() != null) {
                        if (this.readBean.getMsgHost().getLike() != null) {
                            this.readBean.getMsgHost().setLike((Integer.parseInt(this.readBean.getMsgHost().getLike()) + 1) + "");
                        } else {
                            this.readBean.getMsgHost().setLike("1");
                        }
                    }
                }
                updateUI();
                return;
            case R.string.API_CIRCLE_DELETE /* 2131296312 */:
                MyToast.show(this, (int) R.string.delete_success);
                this.datas.remove(this.position);
                this.adapter.notifyDataSetChanged();
                updateUI();
                return;
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
        switch (result.getRequestCode()) {
            case R.string.API_CIRCLE_DELETE /* 2131296312 */:
                MyToast.show(this, (int) R.string.delete_failed);
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
    }

    @Override // com.hy.frame.adapter.IAdapterListener
    public void onItemClick(int i, Object o, int position) {
        if (!NetUtil.isNetworkAvailable(this.context)) {
            MyToast.show(this.context, (int) R.string.network_unavailable);
            return;
        }
        this.readBean = (CircleReadBean) o;
        this.position = position;
        switch (i) {
            case -1:
                Bundle bundle2 = new Bundle();
                bundle2.putString(Constant.FLAG, this.datas.get(position).getMsgId());
                startActForResult(CircleDetailActivity.class, bundle2, Constant.FLAG_RECEIVE_NOTIFY);
                return;
            case R.id.circles_i_ibtnReply /* 2131755257 */:
                this.msgId = this.datas.get(position).getMsgId();
                this.prevMsgId = this.msgId;
                this.clickFlag = "comment";
                showEditDialog();
                return;
            case R.id.circles_i_ibtnShare /* 2131755259 */:
                this.prevMsgId = this.datas.get(position).getMsgId();
                this.msgId = this.datas.get(position).getFwmi() == null ? this.datas.get(position).getMsgId() : this.datas.get(position).getFwmi().getMsgId();
                this.clickFlag = FlagUtil.URL_FORWARD;
                showForwardDialog(position);
                return;
            case R.id.circles_i_ibtnZan /* 2131755261 */:
                zan_saveRequest(position, FlagUtil.URL_ZAN);
                this.clickFlag = FlagUtil.URL_ZAN;
                return;
            case R.id.circles_i_ibtnSave /* 2131755264 */:
                zan_saveRequest(position, FlagUtil.URL_SAVE);
                this.clickFlag = FlagUtil.URL_SAVE;
                return;
            case R.id.circle_i_llyForward /* 2131756765 */:
                if (this.datas.get(position).getFwmi() == null) {
                    MyToast.show(this, (int) R.string.message_delete);
                    return;
                }
                Bundle bundle1 = new Bundle();
                bundle1.putString(Constant.FLAG, this.datas.get(position).getFwmi().getMsgId());
                startActForResult(CircleDetailActivity.class, bundle1, 999);
                return;
            case R.id.circle_btnDel /* 2131756920 */:
                new EaseAlertDialog((Context) this, (int) R.string.prompt, (int) R.string.confirm_to_delete, new Bundle(), new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.circle.MyCircleActivity.1
                    @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                    public void onResult(boolean confirmed, Bundle bundle) {
                        if (confirmed) {
                            try {
                                MyCircleActivity.this.deleteRequest();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, true).show();
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onRefresh() {
        this.fresh = true;
        this.PAGE_INDEX = 1;
        getClient().setShowDialog(false);
        requestData(this.PAGE_INDEX);
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onLoadMore(boolean isSilence) {
        getClient().setShowDialog(false);
        this.PAGE_INDEX++;
        requestData(this.PAGE_INDEX);
        this.fresh = false;
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onRelease(float direction) {
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onHeaderMove(double headerMovePercent, int offsetY) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteRequest() {
        CircleReadBean circle;
        String type = "delete_info";
        if (!HyUtil.isEmpty(this.datas) && (circle = this.datas.get(this.position)) != null) {
            if (circle.getType() == 2) {
                type = "delete_forward";
            }
            this.msgId = circle.getMsgId();
            getClient().setShowDialog(false);
            getClient().post(R.string.API_CIRCLE_DELETE, ComUtil.getF2FApi(this.context, getString(R.string.API_CIRCLE_DELETE, new Object[]{type, this.msgId})), new AjaxParams(), String.class, false);
        }
    }

    public void zan_saveRequest(int position, String zan_save) {
        getClient().setShowDialog(false);
        String msgId = this.datas.get(position).getMsgId();
        getClient().post(R.string.API_CIRCLES_ZAN_SAVE, ComUtil.getF2FApi(this.context, getString(R.string.API_CIRCLES_ZAN_SAVE, new Object[]{zan_save, msgId})), new AjaxParams(), String.class, false);
    }

    public void showEditDialog() {
        new EditEnterDialog(this.context, new EditEnterDialog.setOnEditSendClickListener() { // from class: com.vsf2f.f2f.ui.circle.MyCircleActivity.2
            @Override // com.vsf2f.f2f.ui.dialog.EditEnterDialog.setOnEditSendClickListener
            public void onEditSendClickListener(String s) {
                if (MyCircleActivity.this.clickFlag.equals("comment")) {
                    MyCircleActivity.this.commentContent(s, "comment");
                } else if (MyCircleActivity.this.clickFlag.equals(FlagUtil.URL_FORWARD)) {
                    MyCircleActivity.this.commentContent(s, FlagUtil.URL_FORWARD);
                }
            }
        }).show();
    }

    public void commentContent(String content, String commen_forward) {
        getClient().setShowDialog(false);
        AjaxParams params = new AjaxParams();
        params.put(EditActivity.CONTENT, content);
        params.put(MessageEncoder.ATTR_ADDRESS, "");
        params.put("lgt", "");
        params.put("lat", "");
        params.put("udid", DemoHelper.getInstance().getDeviceUni());
        params.put("udName", DemoHelper.getInstance().getDeviceModel());
        getClient().post(R.string.API_CIRCLES_COMMENT_FORWARD, ComUtil.getF2FApi(this.context, getString(R.string.API_CIRCLES_COMMENT_FORWARD, new Object[]{commen_forward, this.msgId, this.prevMsgId})), params);
    }

    private void showForwardDialog(int position) {
        if (this.datas.get(position).getShareThird() != null) {
            this.shareUtils.setShare(this.datas.get(position).getShareThird());
        }
        if (this.editDialog == null) {
            this.editDialog = new ShareForwardDialog(this.context);
            this.editDialog.setListener(new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.circle.MyCircleActivity.3
                @Override // com.hy.frame.common.BaseDialog.IConfirmListener
                public void onDlgConfirm(BaseDialog dlg, int flag) {
                    switch (flag) {
                        case 1:
                            MyCircleActivity.this.showEditDialog();
                            return;
                        case 11:
                            MyCircleActivity.this.shareUtils.shareTask(1);
                            return;
                        case 12:
                            MyCircleActivity.this.shareUtils.shareTask(0);
                            return;
                        case 13:
                            MyCircleActivity.this.shareUtils.qqShare();
                            return;
                        case 14:
                            MyCircleActivity.this.shareUtils.qqZoneShare();
                            return;
                        default:
                            return;
                    }
                }
            });
        }
        this.editDialog.show();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10103 || requestCode == 10104) {
            Tencent.onActivityResultData(requestCode, resultCode, data, this.shareUtils.getListener());
        }
        if (resultCode != -1) {
            return;
        }
        if (requestCode == 999) {
            updateUI();
        } else if (requestCode == 607) {
            requestData();
        }
    }
}
