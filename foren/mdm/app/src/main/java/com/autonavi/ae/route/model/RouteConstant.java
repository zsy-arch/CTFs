package com.autonavi.ae.route.model;

/* loaded from: classes.dex */
public class RouteConstant {

    /* loaded from: classes.dex */
    public static final class AbnormalState {
        public static final int ETbtAbnormalState_CheckFail = -1;
        public static final int ETbtAbnormalState_Normal = 0;
        public static final int ETbtAbnormalState_Other = -3;
        public static final int ETbtAbnormalState_Outside = -2;
    }

    /* loaded from: classes.dex */
    public static final class NetError {
        public static final int ETbtNetError_NoNetConn = 3;
        public static final int ETbtNetError_Other = -1;
        public static final int ETbtNetError_TimeOut = 1;
        public static final int ETbtNetError_UserCancel = 2;
    }

    /* loaded from: classes.dex */
    public static final class RequestState {
        public static final int ETbtRequestState_CallCenterError = 5;
        public static final int ETbtRequestState_DataBufError = 9;
        public static final int ETbtRequestState_EncodeFalse = 7;
        public static final int ETbtRequestState_EndNoRoad = 11;
        public static final int ETbtRequestState_EndPointFalse = 6;
        public static final int ETbtRequestState_HalfwayNoRoad = 12;
        public static final int ETbtRequestState_IllegalRequest = 4;
        public static final int ETbtRequestState_LackEndData = 24;
        public static final int ETbtRequestState_LackPreview = 8;
        public static final int ETbtRequestState_LackStartData = 18;
        public static final int ETbtRequestState_LackViaData = 25;
        public static final int ETbtRequestState_LackWayCityData = 20;
        public static final int ETbtRequestState_NULL = 0;
        public static final int ETbtRequestState_NetERROR = 2;
        public static final int ETbtRequestState_NetTimeOut = 16;
        public static final int ETbtRequestState_NoNetConn = 17;
        public static final int ETbtRequestState_OfflineRouteFail = 14;
        public static final int ETbtRequestState_SilentRerouteNoMeetCriteria = 23;
        public static final int ETbtRequestState_StartNoRoad = 10;
        public static final int ETbtRequestState_StartPointFalse = 3;
        public static final int ETbtRequestState_Success = 1;
        public static final int ETbtRequestState_TooFar = 19;
        public static final int ETbtRequestState_UnknownRouteFail = 13;
        public static final int ETbtRequestState_Updating = 22;
        public static final int ETbtRequestState_UserCancel = 15;
        public static final int ETbtRequestState_ViaPointFalse = 21;
    }

    /* loaded from: classes.dex */
    public static final class RouteType {
        public static final int ROUTE_TYPE_CHANGE_END = 9;
        public static final int ROUTE_TYPE_CHANGE_PARALLEL_WAY = 4;
        public static final int ROUTE_TYPE_CHANGE_STRATEGY = 3;
        public static final int ROUTE_TYPE_DAMAGED_ROAD = 7;
        public static final int ROUTE_TYPE_LIMIT_FORBID = 11;
        public static final int ROUTE_TYPE_LIMIT_LINE = 6;
        public static final int ROUTE_TYPE_MANUAL_REFRESH = 12;
        public static final int ROUTE_TYPE_NONE = -1;
        public static final int ROUTE_TYPE_NORMAL = 1;
        public static final int ROUTE_TYPE_OFFLINE_LIMIT_FORBID = 13;
        public static final int ROUTE_TYPE_OFF_ROUTE = 2;
        public static final int ROUTE_TYPE_PRESSURE = 8;
        public static final int ROUTE_TYPE_TRAFFIC_JAM = 5;
        public static final int ROUTE_TYPE_UPDATE_CITY_DATA = 10;
    }
}
