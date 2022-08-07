package com.em.ui;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import com.cdlinglu.common.BaseActivity;
import com.hy.frame.util.MyToast;
import com.vsf2f.f2f.R;

/* loaded from: classes.dex */
public class EditActivity extends BaseActivity {
    public static final String CONTENT = "content";
    public static final String LENGTH = "length";
    public static final String LINES = "lines";
    public static final String TITLE = "title";
    private EditText editText;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.em_activity_edit;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.change1, R.string.save);
        this.editText = (EditText) findViewById(R.id.editText);
        String title = getBundle().getString("title");
        String content = getBundle().getString(CONTENT);
        if (title != null) {
            setTitle(title);
        }
        if (content != null) {
            this.editText.setText(content);
        }
        this.editText.setSelection(this.editText.length());
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        int length = getBundle().getInt("length");
        String edit = this.editText.getText().toString();
        if (length == 0 || edit.length() <= length) {
            setResult(-1, new Intent().putExtra("data", edit));
            finish();
            return;
        }
        MyToast.show(this.context, (int) R.string.text_too_long);
    }
}
