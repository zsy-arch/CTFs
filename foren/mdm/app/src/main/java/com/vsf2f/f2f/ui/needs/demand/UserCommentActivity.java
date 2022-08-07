package com.vsf2f.f2f.ui.needs.demand;

import android.support.annotation.IdRes;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.HyUtil;
import com.hy.frame.view.NavGroup;
import com.hy.frame.view.NavView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.UserCommentAdapter;
import com.vsf2f.f2f.bean.UserCommentBean;
import java.util.List;

/* loaded from: classes2.dex */
public class UserCommentActivity extends BaseActivity implements NavGroup.OnCheckedChangeListener {
    private UserCommentAdapter adapter;
    private int commentType = 0;
    private ListView listView;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_user_comments;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.mine_evaluate, 0);
        this.listView = (ListView) findViewById(R.id.list);
        ((NavGroup) getView(R.id.main_groupFooter)).setOnCheckedChangeListener(this);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        getCommentList();
    }

    private void getCommentList() {
        getClient().get(R.string.API_COMMENT_SELF, ComUtil.getXDDApi(this.context, getString(R.string.API_COMMENT_SELF)) + "?commentType=" + this.commentType + "&pageNo=1&pageSize=20", UserCommentBean.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_COMMENT_SELF /* 2131296315 */:
                UserCommentBean comment = (UserCommentBean) result.getObj();
                updateCommemt(comment.getDatas());
                if (HyUtil.isEmpty(comment.getDatas())) {
                    showNoData();
                    return;
                }
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

    public void updateCommemt(List<UserCommentBean.DatasBean> list) {
        if (this.adapter == null) {
            this.adapter = new UserCommentAdapter(list);
            this.adapter.setCommentType(this.commentType);
            this.listView.setAdapter((ListAdapter) this.adapter);
            return;
        }
        this.adapter.setData(list);
        this.adapter.setCommentType(this.commentType);
        this.adapter.notifyDataSetChanged();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
    }

    @Override // com.hy.frame.view.NavGroup.OnCheckedChangeListener
    public void onCheckedChanged(NavGroup group, NavView nav, @IdRes int checkedId) {
        this.commentType = Integer.parseInt(nav.getTag().toString());
        getCommentList();
    }
}
