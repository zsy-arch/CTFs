package com.vsf2f.f2f.ui.needs.demand;

import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.HyUtil;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.UserCommentAdapter;
import com.vsf2f.f2f.adapter.UserNeedsAdapter;
import com.vsf2f.f2f.bean.UserCommentBean;
import com.vsf2f.f2f.bean.UserNeedsBean;
import com.vsf2f.f2f.ui.utils.Constant;
import java.util.List;

/* loaded from: classes2.dex */
public class UserNeedsActivity extends BaseActivity {
    private ListView listView;
    private String username;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_user_needs;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.black_list, 0);
        this.listView = (ListView) findViewById(R.id.list);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        if (getBundle() != null) {
            this.username = getBundle().getString(Constant.USER_BUCKET);
            String type = getBundle().getString("type", "");
            char c = 65535;
            switch (type.hashCode()) {
                case -1335432629:
                    if (type.equals("demand")) {
                        c = 0;
                        break;
                    }
                    break;
                case 950398559:
                    if (type.equals("comment")) {
                        c = 2;
                        break;
                    }
                    break;
                case 1984153269:
                    if (type.equals(NotificationCompat.CATEGORY_SERVICE)) {
                        c = 1;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    setTitle("他发布的需求");
                    getDemandList();
                    return;
                case 1:
                    setTitle("他发布的服务");
                    getServiceList();
                    return;
                case 2:
                    setTitle("他收到的评价");
                    getCommentList();
                    return;
                default:
                    return;
            }
        }
    }

    private void getDemandList() {
        getClient().get(R.string.API_DEMAND_QUERY, ComUtil.getXDDApi(this.context, getString(R.string.API_DEMAND_QUERY)) + "?publishUser=" + this.username + "&pageNo=1&pageSize=10", UserNeedsBean.class);
    }

    private void getServiceList() {
        getClient().get(R.string.API_SERVICE_QUERY, ComUtil.getXDDApi(this.context, getString(R.string.API_SERVICE_QUERY)) + "?publishUser=" + this.username + "&pageNo=1&pageSize=10", UserNeedsBean.class);
    }

    private void getCommentList() {
        getClient().get(R.string.API_COMMENT_QUERY, ComUtil.getXDDApi(this.context, getString(R.string.API_COMMENT_QUERY)) + "?userName=" + this.username + "&pageNo=1&pageSize=20", UserCommentBean.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_COMMENT_QUERY /* 2131296314 */:
                updateCommemt(((UserCommentBean) result.getObj()).getDatas());
                return;
            case R.string.API_DEMAND_QUERY /* 2131296331 */:
                updateNeeds((UserNeedsBean) result.getObj(), false);
                return;
            case R.string.API_SERVICE_QUERY /* 2131296416 */:
                updateNeeds((UserNeedsBean) result.getObj(), true);
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    public void updateNeeds(UserNeedsBean needs, boolean isService) {
        if (needs == null || HyUtil.isEmpty(needs.getDatas())) {
            showNoData();
            return;
        }
        UserNeedsAdapter adapter = new UserNeedsAdapter(needs.getDatas());
        adapter.setService(isService);
        this.listView.setAdapter((ListAdapter) adapter);
    }

    public void updateCommemt(List<UserCommentBean.DatasBean> list) {
        if (HyUtil.isEmpty(list)) {
            showNoData();
            return;
        }
        this.listView.setAdapter((ListAdapter) new UserCommentAdapter(list));
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
    }
}
