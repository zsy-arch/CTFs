package com.em.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.easeui.EaseConstant;
import com.easeui.domain.EaseUser;
import com.easeui.widget.EaseAlertDialog;
import com.vsf2f.f2f.R;

/* loaded from: classes.dex */
public class ForwardCardActivity extends PickContactNoCheckboxActivity {
    @Override // com.em.ui.PickContactNoCheckboxActivity
    protected void onListItemClick(int position) {
        final EaseUser selectUser = this.contactAdapter.getItem(position);
        new EaseAlertDialog((Context) this, (String) null, getString(R.string.confirm_forward_card, new Object[]{selectUser.getNick()}), (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.em.ui.ForwardCardActivity.1
            @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
            public void onResult(boolean confirmed, Bundle bundle) {
                if (confirmed) {
                    Intent intent = new Intent();
                    intent.putExtra("username", selectUser.getUsername());
                    intent.putExtra(EaseConstant.EXTRA_NICK_NAME, selectUser.getNickname());
                    intent.putExtra(EaseConstant.EXTRA_USER_AVATAR, selectUser.getAvatar());
                    ForwardCardActivity.this.setResult(-1, intent);
                    ForwardCardActivity.this.finish();
                }
            }
        }, true).show();
    }
}
