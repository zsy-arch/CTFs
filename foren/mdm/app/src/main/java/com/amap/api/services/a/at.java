package com.amap.api.services.a;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.LatLonSharePoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.interfaces.IShareSearch;
import com.amap.api.services.share.ShareSearch;

/* compiled from: ShareSearchCore.java */
/* loaded from: classes.dex */
public class at implements IShareSearch {
    private static String b = "http://wb.amap.com/?r=%f,%f,%s,%f,%f,%s,%d,%d,%d,%s,%s,%s&sourceapplication=openapi/0";
    private static String c = "http://wb.amap.com/?q=%f,%f,%s&sourceapplication=openapi/0";
    private static String d = "http://wb.amap.com/?n=%f,%f,%f,%f,%d&sourceapplication=openapi/0";
    private static String e = "http://wb.amap.com/?p=%s,%f,%f,%s,%s&sourceapplication=openapi/0";
    private static final String f = String.valueOf("");
    private Context a;
    private ShareSearch.OnShareSearchListener g;

    public at(Context context) {
        this.a = context;
    }

    @Override // com.amap.api.services.interfaces.IShareSearch
    public void setOnShareSearchListener(ShareSearch.OnShareSearchListener onShareSearchListener) {
        this.g = onShareSearchListener;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.amap.api.services.a.at$1] */
    @Override // com.amap.api.services.interfaces.IShareSearch
    public void searchPoiShareUrlAsyn(final PoiItem poiItem) {
        try {
            new Thread() { // from class: com.amap.api.services.a.at.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    if (at.this.g != null) {
                        Message obtainMessage = q.a().obtainMessage();
                        obtainMessage.arg1 = 11;
                        obtainMessage.what = AMapException.CODE_AMAP_ENGINE_RESPONSE_ERROR;
                        obtainMessage.obj = at.this.g;
                        try {
                            String searchPoiShareUrl = at.this.searchPoiShareUrl(poiItem);
                            Bundle bundle = new Bundle();
                            bundle.putString("shareurlkey", searchPoiShareUrl);
                            obtainMessage.setData(bundle);
                            obtainMessage.arg2 = 1000;
                        } catch (AMapException e2) {
                            obtainMessage.arg2 = e2.getErrorCode();
                        } finally {
                            q.a().sendMessage(obtainMessage);
                        }
                    }
                }
            }.start();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.amap.api.services.a.at$2] */
    @Override // com.amap.api.services.interfaces.IShareSearch
    public void searchBusRouteShareUrlAsyn(final ShareSearch.ShareBusRouteQuery shareBusRouteQuery) {
        try {
            new Thread() { // from class: com.amap.api.services.a.at.2
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    if (at.this.g != null) {
                        Message obtainMessage = q.a().obtainMessage();
                        obtainMessage.arg1 = 11;
                        obtainMessage.what = AMapException.CODE_AMAP_ENGINE_RETURN_TIMEOUT;
                        obtainMessage.obj = at.this.g;
                        try {
                            String searchBusRouteShareUrl = at.this.searchBusRouteShareUrl(shareBusRouteQuery);
                            Bundle bundle = new Bundle();
                            bundle.putString("shareurlkey", searchBusRouteShareUrl);
                            obtainMessage.setData(bundle);
                            obtainMessage.arg2 = 1000;
                        } catch (AMapException e2) {
                            obtainMessage.arg2 = e2.getErrorCode();
                        } finally {
                            q.a().sendMessage(obtainMessage);
                        }
                    }
                }
            }.start();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.amap.api.services.a.at$3] */
    @Override // com.amap.api.services.interfaces.IShareSearch
    public void searchWalkRouteShareUrlAsyn(final ShareSearch.ShareWalkRouteQuery shareWalkRouteQuery) {
        try {
            new Thread() { // from class: com.amap.api.services.a.at.3
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    if (at.this.g != null) {
                        Message obtainMessage = q.a().obtainMessage();
                        obtainMessage.arg1 = 11;
                        obtainMessage.what = 1105;
                        obtainMessage.obj = at.this.g;
                        try {
                            String searchWalkRouteShareUrl = at.this.searchWalkRouteShareUrl(shareWalkRouteQuery);
                            Bundle bundle = new Bundle();
                            bundle.putString("shareurlkey", searchWalkRouteShareUrl);
                            obtainMessage.setData(bundle);
                            obtainMessage.arg2 = 1000;
                        } catch (AMapException e2) {
                            obtainMessage.arg2 = e2.getErrorCode();
                        } finally {
                            q.a().sendMessage(obtainMessage);
                        }
                    }
                }
            }.start();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.amap.api.services.a.at$4] */
    @Override // com.amap.api.services.interfaces.IShareSearch
    public void searchDrivingRouteShareUrlAsyn(final ShareSearch.ShareDrivingRouteQuery shareDrivingRouteQuery) {
        try {
            new Thread() { // from class: com.amap.api.services.a.at.4
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    if (at.this.g != null) {
                        Message obtainMessage = q.a().obtainMessage();
                        obtainMessage.arg1 = 11;
                        obtainMessage.what = 1104;
                        obtainMessage.obj = at.this.g;
                        try {
                            String searchDrivingRouteShareUrl = at.this.searchDrivingRouteShareUrl(shareDrivingRouteQuery);
                            Bundle bundle = new Bundle();
                            bundle.putString("shareurlkey", searchDrivingRouteShareUrl);
                            obtainMessage.setData(bundle);
                            obtainMessage.arg2 = 1000;
                        } catch (AMapException e2) {
                            obtainMessage.arg2 = e2.getErrorCode();
                        } finally {
                            q.a().sendMessage(obtainMessage);
                        }
                    }
                }
            }.start();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.amap.api.services.a.at$5] */
    @Override // com.amap.api.services.interfaces.IShareSearch
    public void searchNaviShareUrlAsyn(final ShareSearch.ShareNaviQuery shareNaviQuery) {
        try {
            new Thread() { // from class: com.amap.api.services.a.at.5
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    if (at.this.g != null) {
                        Message obtainMessage = q.a().obtainMessage();
                        obtainMessage.arg1 = 11;
                        obtainMessage.what = AMapException.CODE_AMAP_ENGINE_CONNECT_TIMEOUT;
                        obtainMessage.obj = at.this.g;
                        try {
                            String searchNaviShareUrl = at.this.searchNaviShareUrl(shareNaviQuery);
                            Bundle bundle = new Bundle();
                            bundle.putString("shareurlkey", searchNaviShareUrl);
                            obtainMessage.setData(bundle);
                            obtainMessage.arg2 = 1000;
                        } catch (AMapException e2) {
                            obtainMessage.arg2 = e2.getErrorCode();
                        } finally {
                            q.a().sendMessage(obtainMessage);
                        }
                    }
                }
            }.start();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.amap.api.services.a.at$6] */
    @Override // com.amap.api.services.interfaces.IShareSearch
    public void searchLocationShareUrlAsyn(final LatLonSharePoint latLonSharePoint) {
        try {
            new Thread() { // from class: com.amap.api.services.a.at.6
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    if (at.this.g != null) {
                        Message obtainMessage = q.a().obtainMessage();
                        obtainMessage.arg1 = 11;
                        obtainMessage.what = AMapException.CODE_AMAP_ENGINE_RESPONSE_DATA_ERROR;
                        obtainMessage.obj = at.this.g;
                        try {
                            String searchLocationShareUrl = at.this.searchLocationShareUrl(latLonSharePoint);
                            Bundle bundle = new Bundle();
                            bundle.putString("shareurlkey", searchLocationShareUrl);
                            obtainMessage.setData(bundle);
                            obtainMessage.arg2 = 1000;
                        } catch (AMapException e2) {
                            obtainMessage.arg2 = e2.getErrorCode();
                        } finally {
                            q.a().sendMessage(obtainMessage);
                        }
                    }
                }
            }.start();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.services.interfaces.IShareSearch
    public String searchPoiShareUrl(PoiItem poiItem) throws AMapException {
        if (poiItem != null) {
            try {
                if (poiItem.getLatLonPoint() != null) {
                    LatLonPoint latLonPoint = poiItem.getLatLonPoint();
                    return new ad(this.a, String.format(e, poiItem.getPoiId(), Double.valueOf(latLonPoint.getLatitude()), Double.valueOf(latLonPoint.getLongitude()), poiItem.getTitle(), poiItem.getSnippet())).a();
                }
            } catch (AMapException e2) {
                i.a(e2, "ShareSearch", "searchPoiShareUrl");
                throw e2;
            }
        }
        throw new AMapException("无效的参数 - IllegalArgumentException");
    }

    @Override // com.amap.api.services.interfaces.IShareSearch
    public String searchNaviShareUrl(ShareSearch.ShareNaviQuery shareNaviQuery) throws AMapException {
        String format;
        try {
            if (shareNaviQuery == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            ShareSearch.ShareFromAndTo fromAndTo = shareNaviQuery.getFromAndTo();
            if (fromAndTo.getTo() == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            LatLonPoint from = fromAndTo.getFrom();
            LatLonPoint to = fromAndTo.getTo();
            int naviMode = shareNaviQuery.getNaviMode();
            if (fromAndTo.getFrom() == null) {
                format = String.format(d, null, null, Double.valueOf(to.getLatitude()), Double.valueOf(to.getLongitude()), Integer.valueOf(naviMode));
            } else {
                format = String.format(d, Double.valueOf(from.getLatitude()), Double.valueOf(from.getLongitude()), Double.valueOf(to.getLatitude()), Double.valueOf(to.getLongitude()), Integer.valueOf(naviMode));
            }
            return new ad(this.a, format).a();
        } catch (AMapException e2) {
            i.a(e2, "ShareSearch", "searchNaviShareUrl");
            throw e2;
        }
    }

    @Override // com.amap.api.services.interfaces.IShareSearch
    public String searchLocationShareUrl(LatLonSharePoint latLonSharePoint) throws AMapException {
        try {
            if (latLonSharePoint != null) {
                return new ad(this.a, String.format(c, Double.valueOf(latLonSharePoint.getLatitude()), Double.valueOf(latLonSharePoint.getLongitude()), latLonSharePoint.getSharePointName())).a();
            }
            throw new AMapException("无效的参数 - IllegalArgumentException");
        } catch (AMapException e2) {
            i.a(e2, "ShareSearch", "searchLocationShareUrl");
            throw e2;
        }
    }

    @Override // com.amap.api.services.interfaces.IShareSearch
    public String searchBusRouteShareUrl(ShareSearch.ShareBusRouteQuery shareBusRouteQuery) throws AMapException {
        try {
            if (shareBusRouteQuery == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            int busMode = shareBusRouteQuery.getBusMode();
            ShareSearch.ShareFromAndTo shareFromAndTo = shareBusRouteQuery.getShareFromAndTo();
            if (shareFromAndTo.getFrom() == null || shareFromAndTo.getTo() == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            LatLonPoint from = shareFromAndTo.getFrom();
            LatLonPoint to = shareFromAndTo.getTo();
            return new ad(this.a, String.format(b, Double.valueOf(from.getLatitude()), Double.valueOf(from.getLongitude()), shareFromAndTo.getFromName(), Double.valueOf(to.getLatitude()), Double.valueOf(to.getLongitude()), shareFromAndTo.getToName(), Integer.valueOf(busMode), 1, 0, f, f, f)).a();
        } catch (AMapException e2) {
            i.a(e2, "ShareSearch", "searchBusRouteShareUrl");
            throw e2;
        }
    }

    @Override // com.amap.api.services.interfaces.IShareSearch
    public String searchDrivingRouteShareUrl(ShareSearch.ShareDrivingRouteQuery shareDrivingRouteQuery) throws AMapException {
        try {
            if (shareDrivingRouteQuery == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            int drivingMode = shareDrivingRouteQuery.getDrivingMode();
            ShareSearch.ShareFromAndTo shareFromAndTo = shareDrivingRouteQuery.getShareFromAndTo();
            if (shareFromAndTo.getFrom() == null || shareFromAndTo.getTo() == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            LatLonPoint from = shareFromAndTo.getFrom();
            LatLonPoint to = shareFromAndTo.getTo();
            return new ad(this.a, String.format(b, Double.valueOf(from.getLatitude()), Double.valueOf(from.getLongitude()), shareFromAndTo.getFromName(), Double.valueOf(to.getLatitude()), Double.valueOf(to.getLongitude()), shareFromAndTo.getToName(), Integer.valueOf(drivingMode), 0, 0, f, f, f)).a();
        } catch (AMapException e2) {
            i.a(e2, "ShareSearch", "searchDrivingRouteShareUrl");
            throw e2;
        }
    }

    @Override // com.amap.api.services.interfaces.IShareSearch
    public String searchWalkRouteShareUrl(ShareSearch.ShareWalkRouteQuery shareWalkRouteQuery) throws AMapException {
        try {
            if (shareWalkRouteQuery == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            int walkMode = shareWalkRouteQuery.getWalkMode();
            ShareSearch.ShareFromAndTo shareFromAndTo = shareWalkRouteQuery.getShareFromAndTo();
            if (shareFromAndTo.getFrom() == null || shareFromAndTo.getTo() == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            LatLonPoint from = shareFromAndTo.getFrom();
            LatLonPoint to = shareFromAndTo.getTo();
            return new ad(this.a, String.format(b, Double.valueOf(from.getLatitude()), Double.valueOf(from.getLongitude()), shareFromAndTo.getFromName(), Double.valueOf(to.getLatitude()), Double.valueOf(to.getLongitude()), shareFromAndTo.getToName(), Integer.valueOf(walkMode), 2, 0, f, f, f)).a();
        } catch (AMapException e2) {
            i.a(e2, "ShareSearch", "searchWalkRouteShareUrl");
            throw e2;
        }
    }
}
