package com.alipay.b.a.a.c;

import com.alipay.b.a.a.a.a;
import com.alipay.tscenter.biz.rpc.report.general.DataReportService;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;

/* loaded from: classes.dex */
final class c implements Runnable {
    final /* synthetic */ DataReportRequest a;
    final /* synthetic */ b b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(b bVar, DataReportRequest dataReportRequest) {
        this.b = bVar;
        this.a = dataReportRequest;
    }

    @Override // java.lang.Runnable
    public final void run() {
        DataReportResult dataReportResult;
        DataReportResult dataReportResult2;
        DataReportService dataReportService;
        try {
            dataReportService = this.b.c;
            DataReportResult unused = b.e = dataReportService.reportData(this.a);
        } catch (Throwable th) {
            DataReportResult unused2 = b.e = new DataReportResult();
            dataReportResult = b.e;
            dataReportResult.success = false;
            dataReportResult2 = b.e;
            dataReportResult2.resultCode = "static data rpc upload error, " + a.a(th);
            a.a(th);
        }
    }
}
