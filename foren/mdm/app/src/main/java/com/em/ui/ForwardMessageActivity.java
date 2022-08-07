package com.em.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.easeui.EaseConstant;
import com.easeui.domain.EaseUser;
import com.easeui.widget.EaseAlertDialog;
import com.hy.frame.util.Constant;
import com.vsf2f.f2f.R;

/* loaded from: classes.dex */
public class ForwardMessageActivity extends PickContactNoCheckboxActivity {
    private String forward_msg_id;
    private EaseUser selectUser;

    @Override // com.em.ui.PickContactNoCheckboxActivity
    protected void onListItemClick(int position) {
        this.forward_msg_id = getIntent().getStringExtra(EaseConstant.EXTRA_MSG_ID);
        this.selectUser = this.contactAdapter.getItem(position);
        new EaseAlertDialog((Context) this, (String) null, getString(R.string.confirm_forward_to, new Object[]{this.selectUser.getNick()}), (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.em.ui.ForwardMessageActivity.1
            @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
            public void onResult(boolean confirmed, Bundle bundle) {
                if (confirmed && ForwardMessageActivity.this.selectUser != null) {
                    try {
                        ChatActivity.activityInstance.finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(ForwardMessageActivity.this.context, ChatActivity.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("username", ForwardMessageActivity.this.selectUser.getUsername());
                    bundle1.putString(EaseConstant.EXTRA_MSG_ID, ForwardMessageActivity.this.forward_msg_id);
                    intent.putExtra(Constant.BUNDLE, bundle1);
                    ForwardMessageActivity.this.startActivity(intent);
                    ForwardMessageActivity.this.finish();
                }
            }
        }, true).show();
    }
}
