package com.vsf2f.f2f.ui.shop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.AddShopAdapter;
import com.vsf2f.f2f.bean.ShopMenuBean;
import com.vsf2f.f2f.ui.utils.listener.AddShopListener;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class ClassifyActivity extends BaseActivity {
    private AddShopAdapter adapter;
    private View finishView;
    private ExpandableListView listView;
    private List<String> groupName = new ArrayList();
    private List<List<String>> childName = new ArrayList();
    private List<ShopMenuBean> data = new ArrayList();

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_classify;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.classify, R.string.finish);
        this.listView = (ExpandableListView) getView(R.id.listView_add_shop_menu);
        setOnClickListener(R.id.btn_add_group);
        this.finishView = getHeaderRight();
        this.adapter = new AddShopAdapter(this, this.finishView, new AddShopListener() { // from class: com.vsf2f.f2f.ui.shop.ClassifyActivity.1
            @Override // com.vsf2f.f2f.ui.utils.listener.AddShopListener
            public void finish(String selectName, String id) {
                Intent intent = new Intent();
                intent.putExtra("selectName", selectName);
                intent.putExtra("shopMenusId", id);
                ClassifyActivity.this.setResult(-1, intent);
                ClassifyActivity.this.finish();
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.AddShopListener
            public void add(String parentId, String name) {
                ClassifyActivity.this.addShopMenu(parentId, name);
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.AddShopListener
            public void delete(String id) {
                ClassifyActivity.this.deleteShopMenu(id);
            }
        });
        this.listView.setAdapter(this.adapter);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        requestData();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        AjaxParams params = new AjaxParams();
        getClient().setShowDialog(true);
        getClient().get(R.string.API_SHOP_MENU, ComUtil.getZCApi(this.context, getString(R.string.API_SHOP_MENU)), params, ShopMenuBean.class, true);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        super.onRequestSuccess(result);
        switch (result.getRequestCode()) {
            case R.string.API_ADD_MENU /* 2131296287 */:
                requestData();
                return;
            case R.string.API_DELETE_MENU /* 2131296321 */:
                requestData();
                return;
            case R.string.API_SHOP_MENU /* 2131296435 */:
                if (result.getErrorCode() == 0) {
                    this.data = (List) result.getObj();
                    if (this.data == null) {
                        this.data = new ArrayList();
                    }
                    this.adapter.setData(this.data);
                    return;
                }
                MyLog.e("没有数据");
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_group /* 2131755892 */:
                final EditText inputServer = new EditText(this);
                inputServer.setSingleLine();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("新的分类名").setView(inputServer).setIcon(17301555).setNegativeButton("取消", (DialogInterface.OnClickListener) null).setPositiveButton("确认", new DialogInterface.OnClickListener() { // from class: com.vsf2f.f2f.ui.shop.ClassifyActivity.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int which) {
                        String name = inputServer.getText().toString().trim();
                        if (TextUtils.isEmpty(name)) {
                            MyToast.show(ClassifyActivity.this.context, "分类名不能为空");
                            return;
                        }
                        ClassifyActivity.this.groupName.add(name);
                        ClassifyActivity.this.childName.add(new ArrayList());
                        ClassifyActivity.this.addShopMenu("0", name);
                    }
                });
                builder.show();
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addShopMenu(String parentTypeId, String name) {
        AjaxParams params = new AjaxParams();
        params.put("parentId", parentTypeId);
        params.put("name", ComUtil.UTF(name));
        getClient().setShowDialog(true);
        getClient().post(R.string.API_ADD_MENU, ComUtil.getZCApi(this.context, getString(R.string.API_ADD_MENU)), params, String.class, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteShopMenu(String id) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        getClient().setShowDialog(true);
        getClient().post(R.string.API_DELETE_MENU, ComUtil.getZCApi(this.context, getString(R.string.API_DELETE_MENU)), params, String.class, false);
    }
}
