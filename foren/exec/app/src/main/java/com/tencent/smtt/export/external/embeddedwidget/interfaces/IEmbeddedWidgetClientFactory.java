package com.tencent.smtt.export.external.embeddedwidget.interfaces;

import java.util.Map;

/* loaded from: classes.dex */
public interface IEmbeddedWidgetClientFactory {
    IEmbeddedWidgetClient createWidgetClient(String str, Map<String, String> map, IEmbeddedWidget iEmbeddedWidget);
}
