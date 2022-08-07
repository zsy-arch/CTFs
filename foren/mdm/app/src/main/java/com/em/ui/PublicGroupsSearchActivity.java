package com.em.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.exceptions.HyphenateException;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.ThreadPool;

/* loaded from: classes.dex */
public class PublicGroupsSearchActivity extends BaseActivity {
    public static EMGroup searchedGroup;
    private RelativeLayout containerLayout;
    private EditText idET;
    private TextView nameText;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.em.ui.BaseActivity, com.easeui.ui.EaseBaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.em_activity_public_groups_search);
        this.containerLayout = (RelativeLayout) findViewById(R.id.rl_searched_group);
        this.idET = (EditText) findViewById(R.id.et_search_id);
        this.nameText = (TextView) findViewById(R.id.name);
        searchedGroup = null;
    }

    public void searchGroup(View v) {
        if (!TextUtils.isEmpty(this.idET.getText())) {
            final ProgressDialog pd = new ProgressDialog(this);
            pd.setMessage(getResources().getString(R.string.searching));
            pd.setCancelable(false);
            pd.show();
            ThreadPool.newThreadPool(new Runnable() { // from class: com.em.ui.PublicGroupsSearchActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        PublicGroupsSearchActivity.searchedGroup = EMClient.getInstance().groupManager().getGroupFromServer(PublicGroupsSearchActivity.this.idET.getText().toString());
                        PublicGroupsSearchActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.PublicGroupsSearchActivity.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                pd.dismiss();
                                PublicGroupsSearchActivity.this.containerLayout.setVisibility(0);
                                PublicGroupsSearchActivity.this.nameText.setText(PublicGroupsSearchActivity.searchedGroup.getGroupName());
                            }
                        });
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                        PublicGroupsSearchActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.PublicGroupsSearchActivity.1.2
                            @Override // java.lang.Runnable
                            public void run() {
                                pd.dismiss();
                                PublicGroupsSearchActivity.searchedGroup = null;
                                PublicGroupsSearchActivity.this.containerLayout.setVisibility(8);
                                if (e.getErrorCode() == 600) {
                                    Toast.makeText(PublicGroupsSearchActivity.this.getApplicationContext(), PublicGroupsSearchActivity.this.getResources().getString(R.string.group_not_existed), 0).show();
                                } else {
                                    Toast.makeText(PublicGroupsSearchActivity.this.getApplicationContext(), PublicGroupsSearchActivity.this.getResources().getString(R.string.search_failed) + " : " + PublicGroupsSearchActivity.this.getString(R.string.connect_fail_toast), 0).show();
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    public void enterToDetails(View view) {
        startActivity(new Intent(this, GroupSimpleDetailActivity.class));
    }
}
