package com.hy.frame.common;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.hy.frame.R;
import com.hy.frame.bean.LoadCache;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.hy.http.MyHttpClient;

/* loaded from: classes2.dex */
public abstract class BaseFragment extends Fragment implements View.OnClickListener, IFragmentListener, IBaseActivity {
    private BaseApplication app;
    private MyHttpClient client;
    protected Context context;
    private FrameLayout flyMain;
    private boolean init;
    private LoadCache loadCache;
    private int showCount;
    private Toolbar toolbar;
    private boolean translucentStatus;
    private TextView txtTitle;

    public void setInit(boolean init) {
        this.init = init;
    }

    public int getShowCount() {
        return this.showCount;
    }

    public void setTranslucentStatus(boolean translucentStatus) {
        this.translucentStatus = translucentStatus;
    }

    @Override // android.support.v4.app.Fragment
    public void onStart() {
        super.onStart();
        if (!this.init) {
            this.init = true;
            initView();
            initData();
        }
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.showCount++;
        this.context = getActivity();
        this.app = (BaseApplication) getActivity().getApplication();
        boolean custumHeader = true;
        int layout = initLayoutId();
        View v = null;
        if (layout > 0) {
            v = inflater.inflate(layout, container, false);
            this.toolbar = (Toolbar) getView(v, R.id.head_toolBar);
        }
        if (this.toolbar == null) {
            custumHeader = false;
            v = inflater.inflate(R.layout.act_base, container, false);
            this.toolbar = (Toolbar) getView(v, R.id.head_toolBar);
        }
        this.toolbar = (Toolbar) getView(v, R.id.head_toolBar);
        this.toolbar.setTitle("");
        int statusBarHeight = getStatusBarHeight();
        if (this.translucentStatus && Build.VERSION.SDK_INT >= 19 && statusBarHeight > 0) {
            this.toolbar.setPadding(0, statusBarHeight, 0, 0);
        }
        this.txtTitle = (TextView) getView(v, R.id.head_vTitle);
        this.flyMain = (FrameLayout) getView(v, R.id.base_flyMain);
        if (!custumHeader && this.flyMain != null && layout > 0) {
            View.inflate(this.context, layout, this.flyMain);
        }
        this.init = false;
        return v;
    }

    public int getStatusBarHeight() {
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", f.a);
        if (resourceId > 0) {
            return getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public BaseApplication getApp() {
        return this.app;
    }

    @Override // android.support.v4.app.Fragment
    public Context getContext() {
        if (this.context == null) {
            this.context = getActivity();
        }
        return this.context;
    }

    private boolean initLoadView() {
        if (this.flyMain == null) {
            MyLog.e(getClass(), "Your layout must include 'FrameLayout',the ID must be 'base_flyMain'!");
            return false;
        } else if (this.loadCache != null) {
            return true;
        } else {
            if (getView(R.id.base_llyLoad) == null) {
                if (this.flyMain.getChildCount() > 0) {
                    this.flyMain.addView(View.inflate(this.context, R.layout.in_loading, null), 0);
                } else {
                    View.inflate(this.context, R.layout.in_loading, this.flyMain);
                }
            }
            this.loadCache = new LoadCache();
            this.loadCache.llyLoad = (LinearLayout) getView(R.id.base_llyLoad);
            this.loadCache.proLoading = (ProgressBar) getView(R.id.base_proLoading);
            this.loadCache.imgMessage = (ImageView) getView(R.id.base_imgMessage);
            this.loadCache.txtMessage = (TextView) getView(R.id.base_txtMessage);
            return true;
        }
    }

    protected void showLoading() {
        showLoading(getString(R.string.loading));
    }

    protected void showLoading(String msg) {
        if (initLoadView()) {
            int count = this.flyMain.getChildCount();
            for (int i = 0; i < count; i++) {
                View v = this.flyMain.getChildAt(i);
                if (i > 0) {
                    v.setVisibility(8);
                }
            }
            this.loadCache.showLoading(msg);
        }
    }

    protected void showNoData() {
        showNoData(getString(R.string.hint_no_data));
    }

    protected void showNoData(String msg) {
        showNoData(msg, R.drawable.img_hint_nodata);
    }

    protected void showNoData(String msg, int drawId) {
        if (initLoadView()) {
            int count = this.flyMain.getChildCount();
            for (int i = 0; i < count; i++) {
                View v = this.flyMain.getChildAt(i);
                if (i > 0) {
                    v.setVisibility(8);
                }
            }
            this.loadCache.showNoData(msg, drawId);
        }
    }

    protected void showCView() {
        if (initLoadView()) {
            int count = this.flyMain.getChildCount();
            for (int i = 0; i < count; i++) {
                View v = this.flyMain.getChildAt(i);
                if (i == 0) {
                    v.setVisibility(8);
                } else {
                    v.setVisibility(0);
                }
            }
        }
    }

    public void setTitle(@StringRes int titleId) {
        setTitle(getString(titleId));
    }

    public void setTitle(CharSequence title) {
        if (this.txtTitle != null) {
            this.txtTitle.setText(title);
        }
    }

    public TextView getTitleText() {
        if (this.txtTitle != null) {
            return this.txtTitle;
        }
        return null;
    }

    protected void hideHeader() {
        if (this.toolbar != null) {
            this.toolbar.setVisibility(8);
        }
    }

    protected void setHeaderLeft(@DrawableRes int left) {
        if (left <= 0) {
            return;
        }
        if (this.toolbar.findViewById(R.id.head_vLeft) == null) {
            ImageView img = (ImageView) getView(View.inflate(this.context, R.layout.in_head_left, this.toolbar), R.id.head_vLeft);
            img.setOnClickListener(this);
            img.setImageResource(left);
            return;
        }
        ((ImageView) getView(this.toolbar, R.id.head_vLeft)).setImageResource(left);
    }

    protected void setHeaderLeftTxt(@StringRes int left) {
        if (left <= 0) {
            return;
        }
        if (this.toolbar.findViewById(R.id.head_vLeft) == null) {
            TextView txt = (TextView) getView(View.inflate(this.context, R.layout.in_head_tleft, this.toolbar), R.id.head_vLeft);
            txt.setOnClickListener(this);
            txt.setText(left);
            if (this.txtTitle != null) {
                txt.setTextColor(this.txtTitle.getTextColors());
                return;
            }
            return;
        }
        ((TextView) getView(this.toolbar, R.id.head_vLeft)).setText(left);
    }

    protected void setHeaderRight(@DrawableRes int right) {
        if (right <= 0) {
            return;
        }
        if (this.toolbar.findViewById(R.id.head_vRight) == null) {
            ImageView img = (ImageView) getView(View.inflate(this.context, R.layout.in_head_right, this.toolbar), R.id.head_vRight);
            img.setOnClickListener(this);
            img.setImageResource(right);
            return;
        }
        ((ImageView) getView(this.toolbar, R.id.head_vRight)).setImageResource(right);
    }

    protected void setHeaderRightTxt(@StringRes int right) {
        if (right <= 0) {
            return;
        }
        if (this.toolbar.findViewById(R.id.head_vRight) == null) {
            TextView txt = (TextView) getView(View.inflate(this.context, R.layout.in_head_tright, this.toolbar), R.id.head_vRight);
            txt.setOnClickListener(this);
            txt.setText(right);
            if (this.txtTitle != null) {
                txt.setTextColor(this.txtTitle.getTextColors());
                return;
            }
            return;
        }
        ((TextView) getView(this.toolbar, R.id.head_vRight)).setText(right);
    }

    protected View getHeader() {
        return this.toolbar;
    }

    protected View getHeaderRight() {
        return this.toolbar.findViewById(R.id.head_vRight);
    }

    protected View getHeaderLeft() {
        return this.toolbar.findViewById(R.id.head_vLeft);
    }

    protected View getMainView() {
        return this.flyMain;
    }

    public void startAct(Class<?> cls) {
        startAct(cls, null);
    }

    public void startAct(Class<?> cls, Bundle bundle) {
        startAct(null, cls, bundle);
    }

    protected void startAct(Intent intent, Class<?> cls, Bundle bundle) {
        if (intent == null) {
            intent = new Intent();
        }
        if (bundle != null) {
            intent.putExtra(Constant.BUNDLE, bundle);
        }
        intent.putExtra(Constant.LAST_ACT, getClass().getSimpleName());
        intent.setClass(getActivity(), cls);
        startActivity(intent);
    }

    public void startActForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent(getActivity(), cls);
        if (bundle != null) {
            intent.putExtra(Constant.BUNDLE, bundle);
        }
        intent.putExtra(Constant.LAST_ACT, getClass().getSimpleName());
        startActivityForResult(intent, requestCode);
    }

    protected String getStringIds(Integer... ids) {
        if (ids.length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Integer num : ids) {
            sb.append(getString(num.intValue()));
        }
        return sb.toString();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (!HyUtil.isFastClick()) {
            if (v.getId() == R.id.head_vLeft) {
                onLeftClick();
            } else if (v.getId() == R.id.head_vRight) {
                onRightClick();
            } else {
                onViewClick(v);
            }
        }
    }

    public <T extends View> T getView(View v, @IdRes int id) {
        return (T) v.findViewById(id);
    }

    public <T extends View> T getView(@IdRes int id) {
        return (T) getView(getView(), id);
    }

    protected <T extends View> T getViewAndClick(@IdRes int id) {
        T v = (T) getView(id);
        v.setOnClickListener(this);
        return v;
    }

    protected <T extends View> T getViewAndClick(View view, @IdRes int id) {
        T v = (T) getView(view, id);
        v.setOnClickListener(this);
        return v;
    }

    protected void setOnClickListener(@IdRes int id) {
        if (getView() != null) {
            getView().findViewById(id).setOnClickListener(this);
        }
    }

    protected void setOnClickListener(View v, @IdRes int id) {
        v.findViewById(id).setOnClickListener(this);
    }

    public void onLeftClick() {
    }

    public void onRightClick() {
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        if (this.client != null) {
            this.client.onDestroy();
        }
        super.onDestroy();
    }

    protected void setClient(MyHttpClient client) {
        this.client = client;
    }

    public MyHttpClient getClient() {
        return this.client;
    }
}
