package cn.jpush.android.ui;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import cn.jpush.android.data.c;
import cn.jpush.android.util.b;
import java.util.List;

/* loaded from: classes.dex */
final class d implements AdapterView.OnItemClickListener {
    final /* synthetic */ List a;
    final /* synthetic */ ListViewActivity b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(ListViewActivity listViewActivity, List list) {
        this.b = listViewActivity;
        this.a = list;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        c cVar = (c) this.a.get(i);
        cVar.p = false;
        this.b.startActivity(b.a((Context) this.b, cVar, false));
    }
}
