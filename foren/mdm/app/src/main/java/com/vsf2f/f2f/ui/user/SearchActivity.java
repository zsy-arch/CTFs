package com.vsf2f.f2f.ui.user;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import com.cdlinglu.common.BaseActivity;
import com.em.ui.AddContactActivity;
import com.hy.frame.util.Constant;
import com.hy.frame.util.MyToast;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.circle.CircleActivity;
import com.vsf2f.f2f.ui.user.pwd.LoginActivity;
import com.vsf2f.f2f.ui.utils.web.WebUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/* loaded from: classes2.dex */
public class SearchActivity extends BaseActivity {
    private EditText editSearch;
    private ImageButton ibtnClear;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_search;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        setOnClickListener(R.id.top_imgBack);
        setOnClickListener(R.id.search_llFriend);
        setOnClickListener(R.id.search_llCircle);
        setOnClickListener(R.id.search_llShop);
        setOnClickListener(R.id.search_llProduct);
        this.ibtnClear = (ImageButton) getViewAndClick(R.id.search_bar_ibtnClear);
        this.editSearch = (EditText) getView(R.id.search_bar_editText);
        this.editSearch.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.user.SearchActivity.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                SearchActivity.this.ibtnClear.setVisibility(s.length() > 0 ? 0 : 4);
            }
        });
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        if (isNoLogin()) {
            MyToast.show(this, (int) R.string.login_hint);
            startAct(LoginActivity.class);
            finish();
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    private void search(String type, String searchStr) {
        Bundle bundle = new Bundle();
        try {
            searchStr = URLEncoder.encode(URLEncoder.encode(searchStr, "utf-8"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        bundle.putString(Constant.FLAG, WebUtils.getTokenUrl(this.context, getString(R.string.SHOP_PRODUCT_URL) + "?search_type=" + type + "&key=" + searchStr));
        bundle.putString(Constant.FLAG_TITLE, "");
        bundle.putBoolean(Constant.FLAG2, true);
        startAct(WebKitLocalActivity.class, bundle);
        this.editSearch.getText().clear();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        String str = this.editSearch.getText().toString().trim();
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.top_imgBack /* 2131755207 */:
                finish();
                return;
            case R.id.search_llFriend /* 2131755544 */:
                bundle.putString("username", str);
                startAct(AddContactActivity.class, bundle);
                this.editSearch.getText().clear();
                return;
            case R.id.search_llCircle /* 2131755545 */:
                if (TextUtils.isEmpty(str)) {
                    MyToast.show(this, (int) R.string.toast_input_search);
                    return;
                }
                bundle.putString(com.vsf2f.f2f.ui.utils.Constant.SEARCH_STR, str);
                startAct(CircleActivity.class, bundle);
                return;
            case R.id.search_llShop /* 2131755546 */:
                search("shop", str);
                return;
            case R.id.search_llProduct /* 2131755547 */:
                search("pro", str);
                return;
            case R.id.search_bar_ibtnClear /* 2131757120 */:
                this.editSearch.getText().clear();
                return;
            default:
                return;
        }
    }
}
