package com.vsf2f.f2f.ui.circle;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.em.DemoHelper;
import com.em.ui.EditActivity;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.KeyValueView;
import com.hy.http.AjaxParams;
import com.hyphenate.chat.MessageEncoder;
import com.litepal.crud.DataSupport;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.DBCircleDraftBean;
import com.vsf2f.f2f.ui.dialog.SaveDraftDialog;
import java.util.List;

/* loaded from: classes2.dex */
public class CirclesAddTextActivity extends BaseActivity {
    public static final String TAG_THRIFT_FLOW = "TAG_THRIFT_FLOW";
    private String ADDTXT_TYPE = "1";
    private EditText editcontent;
    private GridView gridadd;
    private boolean isChange;
    private KeyValueView kvpublic;
    private List<String> list;
    private String username;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_circles_addpic;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.circle_add_txt, R.string.release);
        this.editcontent = (EditText) getView(R.id.circles_add_txtContent);
        this.kvpublic = (KeyValueView) getViewAndClick(R.id.kv_ispublic);
        this.gridadd = (GridView) getView(R.id.circle_add_pic);
        this.gridadd.setVisibility(8);
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        String content = this.editcontent.getText().toString();
        if (TextUtils.isEmpty(content)) {
            MyToast.show(this.context, "请输入要发布的内容");
            return;
        }
        getClient().showDialogNow();
        postTxtCircle(content);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        if (isNoLogin()) {
            MyToast.show(this.context, (int) R.string.login_hint);
            return;
        }
        this.username = DemoHelper.getInstance().getCurrentUserName();
        this.kvpublic.setSelected(AppShare.get(this.context).getBoolean(TAG_THRIFT_FLOW));
        this.kvpublic.getTxtKey().setText(this.kvpublic.isSelected() ? R.string.no_public : R.string.is_public);
        List<DBCircleDraftBean> beans = DataSupport.findAll(DBCircleDraftBean.class, new long[0]);
        for (int i = 0; i < beans.size(); i++) {
            if (beans.get(i) != null && this.ADDTXT_TYPE.equals(beans.get(i).getType())) {
                MyLog.e("5200", "查询到有保存");
                if (this.username.equals(beans.get(i).getUsername())) {
                    this.editcontent.setText(beans.get(i).getText_content());
                    this.editcontent.requestFocus();
                }
            }
        }
        this.editcontent.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.circle.CirclesAddTextActivity.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i1, int i22) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i1, int i22) {
                CirclesAddTextActivity.this.isChange = true;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    private void postTxtCircle(String content) {
        AjaxParams params = new AjaxParams();
        params.put(MessageEncoder.ATTR_ADDRESS, "");
        params.put("lgt", "");
        params.put("lat", "");
        params.put("udName", DemoHelper.getInstance().getDeviceModel());
        params.put("udid", DemoHelper.getInstance().getDeviceUni());
        params.put(EditActivity.CONTENT, ComUtil.UTF(content));
        params.put("categoryId", 0);
        params.put("type", "0");
        getClient().post(R.string.API_CIRCLES_PUBLIC, ComUtil.getF2FApi(this.context, getString(R.string.API_CIRCLES_PUBLIC)), params);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        getApp().removeFinish(CirclesAddActivity.class);
        publishResult(true);
        removeDraft();
        finish();
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        publishResult(false);
        finish();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.kv_ispublic /* 2131755228 */:
                this.kvpublic.setSelected(!this.kvpublic.isSelected());
                this.kvpublic.getTxtKey().setText(this.kvpublic.isSelected() ? R.string.no_public : R.string.is_public);
                AppShare.get(this.context).putBoolean(TAG_THRIFT_FLOW, this.kvpublic.isSelected());
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onLeftClick() {
        onBackPressed();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        hideSoftKeyboard();
        if (TextUtils.isEmpty(this.editcontent.getText().toString())) {
            finish();
        } else {
            new SaveDraftDialog(this.context, new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.circle.CirclesAddTextActivity.2
                @Override // com.hy.frame.common.BaseDialog.IConfirmListener
                public void onDlgConfirm(BaseDialog dlg, int flag) {
                    if (flag == 1) {
                        CirclesAddTextActivity.this.saveCircleText();
                    } else {
                        CirclesAddTextActivity.this.removeDraft();
                    }
                    CirclesAddTextActivity.this.finish();
                }
            }).show();
        }
    }

    public void saveCircleText() {
        DBCircleDraftBean andcommetsyBean = new DBCircleDraftBean();
        andcommetsyBean.setText_content(this.editcontent.getText().toString());
        andcommetsyBean.setUsername(this.username);
        andcommetsyBean.setType(this.ADDTXT_TYPE);
        removeDraft();
        Log.e("5200", "退出文字保存结果" + andcommetsyBean.save());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeDraft() {
        List<DBCircleDraftBean> beans = DataSupport.findAll(DBCircleDraftBean.class, new long[0]);
        for (int i = 0; i < beans.size(); i++) {
            if (beans.get(i) != null && this.ADDTXT_TYPE.equals(beans.get(i).getType()) && this.username.equals(beans.get(i).getUsername())) {
                beans.get(i).delete();
            }
        }
    }

    private void publishResult(boolean success) {
        getApp().remove(this);
        MyToast.show(getApp(), success ? getString(R.string.toast_publish_success) : "发布失败，请点击重试");
        Intent intent = new Intent(this.context, CircleActivity.class);
        intent.putExtra("publishType", this.ADDTXT_TYPE);
        intent.putExtra("publishState", success);
        startActivity(intent);
    }
}
