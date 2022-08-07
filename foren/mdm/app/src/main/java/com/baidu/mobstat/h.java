package com.baidu.mobstat;

import java.util.Comparator;

/* loaded from: classes.dex */
public class h implements Comparator<i> {
    final /* synthetic */ g a;

    public h(g gVar) {
        this.a = gVar;
    }

    /* renamed from: a */
    public int compare(i iVar, i iVar2) {
        int i = iVar2.b - iVar.b;
        if (i != 0) {
            return i;
        }
        if (iVar.d && iVar2.d) {
            return 0;
        }
        if (iVar.d) {
            return -1;
        }
        if (iVar2.d) {
            return 1;
        }
        return i;
    }
}
