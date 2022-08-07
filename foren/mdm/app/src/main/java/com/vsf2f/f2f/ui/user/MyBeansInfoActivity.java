package com.vsf2f.f2f.ui.user;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.em.DemoHelper;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.HyUtil;
import com.hy.frame.view.recycler.callback.XRefreshViewListener;
import com.hy.frame.view.recycler.xRefreshView.XRefreshView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.MyCoinsListAdapter;
import com.vsf2f.f2f.bean.GoldInfoBean;
import com.vsf2f.f2f.ui.utils.GameUtil;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class MyBeansInfoActivity extends BaseActivity implements XRefreshViewListener {
    private TextView beansNum;
    private ListView listView;
    private XRefreshView xrv_pull;
    private List<GoldInfoBean.RecordsBean> list = new ArrayList();
    private int goldType = 0;
    private int pageNo = 1;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_my_beans_info;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.mycoininfo_title, 0);
        this.xrv_pull = (XRefreshView) findViewById(R.id.xrv_parent);
        this.xrv_pull.setXRefreshViewListener(this);
        this.xrv_pull.setPullRefreshEnable(true);
        this.xrv_pull.setPullLoadEnable(true);
        this.listView = (ListView) findViewById(R.id.mybeans_info_list);
        this.beansNum = (TextView) findViewById(R.id.mybeans_info_num);
        this.goldType = getBundle().getInt("type");
        if (this.goldType == 1) {
            setTitle(R.string.mybeaninfo_title);
            ((ImageView) findViewById(R.id.mybeans_info_icon)).setImageResource(R.drawable.icon_gold_beans);
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        requestList(1);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    public void requestList(int pageNo) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("method", "vsf2f.game.zqly.manor.coffer.trade_record");
            jsonObject.put("bizContent", ComUtil.UTF(String.format("{\"userName\":\"%s\",\"goldType\":%s,\"pageNo\":%s,\"pageSize\":20}", DemoHelper.getInstance().getCurrentUserName(), Integer.valueOf(this.goldType), Integer.valueOf(pageNo))));
            getClient().post(R.string.API_GAME_POST, ComUtil.getGAMEApi(this.context, getString(R.string.API_GAME_POST)), GameUtil.getVsSign(jsonObject.toString()), null, GoldInfoBean.class, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_GAME_POST /* 2131296348 */:
                this.xrv_pull.stopRefresh();
                this.xrv_pull.stopLoadMore();
                GoldInfoBean goldInfoBean = (GoldInfoBean) result.getObj();
                if (goldInfoBean.getCofferInfo().getType() == this.goldType) {
                    String coins = goldInfoBean.getCofferInfo().getNum();
                    if (!TextUtils.isEmpty(coins)) {
                        this.beansNum.setText(coins);
                    }
                }
                if (this.pageNo == 1) {
                    this.list.clear();
                }
                if (HyUtil.isNoEmpty(goldInfoBean.getRecords())) {
                    this.list.addAll(goldInfoBean.getRecords());
                } else {
                    this.xrv_pull.setLoadComplete(true);
                }
                if (HyUtil.isNoEmpty(this.list)) {
                    this.listView.setAdapter((ListAdapter) new MyCoinsListAdapter(this.list));
                    return;
                }
                findViewById(R.id.mybeans_info_empty).setVisibility(0);
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
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onRefresh() {
        this.pageNo = 1;
        requestList(1);
        this.xrv_pull.setLoadComplete(false);
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onLoadMore(boolean isSilence) {
        this.pageNo++;
        requestList(this.pageNo);
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onRelease(float direction) {
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onHeaderMove(double headerMovePercent, int offsetY) {
    }
}
