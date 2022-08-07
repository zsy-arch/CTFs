package com.vsf2f.f2f.ui.needs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.em.DemoHelper;
import com.hy.frame.adapter.IAdapterListener;
import com.hy.frame.adapter.MyBaseAdapter;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hyphenate.util.HanziToPinyin;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.AmapUtils;
import com.vsf2f.f2f.ui.utils.Constant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public class NeedsSearchActivity extends BaseActivity implements IAdapterListener, AMapLocationListener {
    private String KEY_SEARCH_HISTORY_KEYWORD;
    private EditText editSearch;
    private ImageButton ibtnClear;
    private HisAdapter mArrAdapter;
    private List<String> mHistoryKeywords = new ArrayList();
    private LinearLayout mSearchHistoryLl;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_search_needs;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        setOnClickListener(R.id.top_imgBack);
        setOnClickListener(R.id.top_txtSearch);
        setOnClickListener(R.id.search_type);
        this.KEY_SEARCH_HISTORY_KEYWORD = "key_search_history_keyword" + DemoHelper.getInstance().getCurrentUserName();
        this.ibtnClear = (ImageButton) getViewAndClick(R.id.search_bar_ibtnClear);
        this.editSearch = (EditText) getView(R.id.search_bar_editText);
        this.editSearch.setHint("搜索关键字");
        this.editSearch.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.needs.NeedsSearchActivity.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                NeedsSearchActivity.this.ibtnClear.setVisibility(s.length() > 0 ? 0 : 4);
            }
        });
        if (HyUtil.isEmpty(getBundle().getString("latitude"))) {
            AmapUtils.getLocation(this.context, this);
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        initSearchHistory();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    public void hot_item(View v) {
        String str = ((TextView) v).getText().toString().trim();
        Bundle bundle = getBundle();
        bundle.putString("keyWord", str);
        startAct(NeedsSearchEndActivity.class, bundle);
    }

    public void search(String str) {
        if (TextUtils.isEmpty(str)) {
            MyToast.show(this.context, "请输入关键字");
        } else if (!ComUtil.checkContent(str, Constant.CONTENTMARTCH)) {
            MyToast.show(this, "不支持特殊字符搜索");
        } else {
            Bundle bundle = getBundle();
            bundle.putString("keyWord", str);
            startAct(NeedsSearchEndActivity.class, bundle);
            save(str);
        }
    }

    public void save(String str) {
        String remove;
        String oldText = AppShare.get(this.context).getShared().getString(this.KEY_SEARCH_HISTORY_KEYWORD, "");
        MyLog.e("tag", "" + oldText);
        if (!TextUtils.isEmpty(oldText)) {
            oldText = "," + oldText;
        }
        if (!TextUtils.isEmpty(str)) {
            int has = this.mHistoryKeywords.indexOf(str);
            if (has == -1) {
                this.mHistoryKeywords.add(0, str);
                AppShare.get(this.context).putString(this.KEY_SEARCH_HISTORY_KEYWORD, str + oldText);
            } else {
                String replace = "";
                if (has != 0) {
                    if (has == this.mHistoryKeywords.size() - 1) {
                        remove = "," + str;
                    } else {
                        remove = "," + str + ",";
                        replace = ",";
                    }
                    this.mHistoryKeywords.remove(str);
                    this.mHistoryKeywords.add(0, str);
                    AppShare.get(this.context).putString(this.KEY_SEARCH_HISTORY_KEYWORD, str + oldText.replace(remove, replace));
                } else {
                    return;
                }
            }
        }
        this.mArrAdapter.notifyDataSetChanged();
        if (this.mSearchHistoryLl.getVisibility() == 8) {
            this.mSearchHistoryLl.setVisibility(0);
        }
    }

    public void initSearchHistory() {
        this.mSearchHistoryLl = (LinearLayout) findViewById(R.id.search_history_ll);
        ListView listView = (ListView) findViewById(R.id.search_history_lv);
        setOnClickListener(R.id.clear_history_btn);
        String history = AppShare.get(this.context).getString(this.KEY_SEARCH_HISTORY_KEYWORD);
        if (!TextUtils.isEmpty(history)) {
            this.mHistoryKeywords.addAll(Arrays.asList(history.split(",")));
        }
        if (this.mHistoryKeywords.size() > 0) {
            this.mSearchHistoryLl.setVisibility(0);
        } else {
            this.mSearchHistoryLl.setVisibility(8);
        }
        this.mArrAdapter = new HisAdapter(this, this.mHistoryKeywords);
        this.mArrAdapter.setListener(this);
        listView.setAdapter((ListAdapter) this.mArrAdapter);
        this.mArrAdapter.notifyDataSetChanged();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.top_imgBack /* 2131755207 */:
                finish();
                return;
            case R.id.top_txtSearch /* 2131755548 */:
                search(this.editSearch.getText().toString().replace(HanziToPinyin.Token.SEPARATOR, ""));
                return;
            case R.id.search_type /* 2131755549 */:
                startActForResult(NeedsTypeChoiceActivity.class, null, 1002);
                return;
            case R.id.clear_history_btn /* 2131755554 */:
                if (this.mHistoryKeywords != null) {
                    this.mHistoryKeywords.clear();
                    this.mArrAdapter.notifyDataSetChanged();
                    this.mSearchHistoryLl.setVisibility(8);
                    AppShare.get(this.context).putString(this.KEY_SEARCH_HISTORY_KEYWORD, "");
                    return;
                }
                return;
            case R.id.search_bar_ibtnClear /* 2131757120 */:
                this.editSearch.getText().clear();
                return;
            default:
                return;
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case 1002:
                    String typeId = data.getStringExtra("id");
                    String typeName = data.getStringExtra("name");
                    Bundle bundle = getBundle();
                    bundle.putString("id", typeId);
                    bundle.putString("name", typeName);
                    bundle.putInt("shareTypeIndex", 0);
                    startAct(NeedsSortByTypeActivity.class, bundle);
                    return;
                default:
                    return;
            }
        }
    }

    @Override // com.hy.frame.adapter.IAdapterListener
    public void onItemClick(int id, Object obj, int position) {
        String remove;
        String str = (String) obj;
        switch (id) {
            case R.id.contentTextView /* 2131756896 */:
                this.editSearch.setText(str);
                this.editSearch.setSelection(str.length());
                search(str);
                return;
            case R.id.contentImgView /* 2131756897 */:
                String replace = "";
                int has = this.mHistoryKeywords.indexOf(str);
                if (has != -1) {
                    if (has == 0) {
                        remove = str;
                    } else if (has == this.mHistoryKeywords.size() - 1) {
                        remove = "," + str;
                    } else {
                        remove = "," + str + ",";
                        replace = ",";
                    }
                    AppShare.get(this.context).putString(this.KEY_SEARCH_HISTORY_KEYWORD, AppShare.get(this.context).getString(this.KEY_SEARCH_HISTORY_KEYWORD).replace(remove, replace));
                    this.mHistoryKeywords.remove(str);
                    this.mArrAdapter.notifyDataSetChanged();
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // com.amap.api.location.AMapLocationListener
    public void onLocationChanged(AMapLocation aMapLocation) {
        getBundle().putString("latitude", aMapLocation.getLatitude() + "");
        getBundle().putString("longitude", aMapLocation.getLongitude() + "");
    }

    /* loaded from: classes2.dex */
    public class HisAdapter extends MyBaseAdapter<String> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public HisAdapter(Context context, List<String> datas) {
            super(context, datas);
            NeedsSearchActivity.this = this$0;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View v, ViewGroup viewGroup) {
            if (v == null) {
                v = inflate(R.layout.item_search_needs_history);
            }
            ((TextView) getView(v, R.id.contentTextView)).setText(getItem(i));
            setOnClickListener(getView(v, R.id.contentTextView), i);
            setOnClickListener(getView(v, R.id.contentImgView), i);
            return v;
        }
    }
}
