package com.autonavi.ae.gmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.SparseArray;
import com.autonavi.ae.gmap.callback.GLMapCoreCallback;
import com.autonavi.ae.gmap.callback.JniIndoorCallback;
import com.autonavi.ae.gmap.callback.JniMapCoreCallback;
import com.autonavi.ae.gmap.callback.JniMapHeatCallback;
import com.autonavi.ae.gmap.callback.JniScenicCallback;
import com.autonavi.ae.gmap.gesture.EAMapPlatformGestureInfo;
import com.autonavi.ae.gmap.glanimation.ADGLMapAnimFling;
import com.autonavi.ae.gmap.glanimation.ADGLMapAnimGroup;
import com.autonavi.ae.gmap.glanimation.ADGLMapAnimationMgr;
import com.autonavi.ae.gmap.glinterface.GLGeoPoint;
import com.autonavi.ae.gmap.gloverlay.BaseMapOverlay;
import com.autonavi.ae.gmap.gloverlay.GLOverlayBundle;
import com.autonavi.ae.gmap.gloverlay.GLTextureProperty;
import com.autonavi.ae.gmap.listener.MapListener;
import com.autonavi.ae.gmap.listener.RouteBoardDataListener;
import com.autonavi.ae.gmap.maploader.BaseMapLoader;
import com.autonavi.ae.gmap.maploader.ConnectionManager;
import com.autonavi.ae.gmap.maploader.GuideMapLoader;
import com.autonavi.ae.gmap.maploader.HeatMapLoader;
import com.autonavi.ae.gmap.maploader.IndoorMapLoader;
import com.autonavi.ae.gmap.maploader.MapSourceGridData;
import com.autonavi.ae.gmap.maploader.MessageDataThread;
import com.autonavi.ae.gmap.maploader.NetworkState;
import com.autonavi.ae.gmap.maploader.NormalMapLoader;
import com.autonavi.ae.gmap.maploader.OpenLayerMapLoader;
import com.autonavi.ae.gmap.maploader.TilesProcessingCtrl;
import com.autonavi.ae.gmap.maploader.TmcMapLoader;
import com.autonavi.ae.gmap.maploader.VMapDataCache;
import com.autonavi.ae.gmap.scenic.Label3rd;
import com.autonavi.ae.gmap.scenic.MapHeatListener;
import com.autonavi.ae.gmap.scenic.ScenicListener;
import com.autonavi.ae.gmap.scenic.ScenicWidgetItem;
import com.autonavi.ae.gmap.style.MapTilsCacheAndResManager;
import com.autonavi.ae.gmap.style.MapTilsCacheAndResManagerImpl;
import com.autonavi.ae.gmap.style.StyleItem;
import com.autonavi.ae.gmap.style.StylesIconsUpdate;
import com.autonavi.ae.gmap.utils.GLMapServerUtil;
import com.autonavi.ae.gmap.utils.GLMapStaticValue;
import com.autonavi.ae.gmap.utils.GLMapUtil;
import com.autonavi.ae.gmap.utils.GLStateInstance;
import com.autonavi.ae.gmap.utils.TextTextureGenerator;
import com.autonavi.amap.mapcore.CameraUpdateMessage;
import com.autonavi.amap.mapcore.MapConfig;
import com.autonavi.amap.mapcore.interfaces.IAMap;
import com.autonavi.amap.mapcore.interfaces.IAMapListener;
import com.autonavi.amap.mapcore.message.GestureMapMessage;
import com.autonavi.amap.mapcore.message.HoverGestureMapMessage;
import com.autonavi.amap.mapcore.message.MoveGestureMapMessage;
import com.autonavi.amap.mapcore.message.RotateGestureMapMessage;
import com.autonavi.amap.mapcore.message.ScaleGestureMapMessage;
import com.umeng.analytics.a;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/* loaded from: classes.dex */
public class GLMapEngine implements JniIndoorCallback, JniMapCoreCallback, JniMapHeatCallback, JniScenicCallback {
    public static final int ANIM_ACTION_MOVE = 1;
    public static final int ANIM_ACTION_ROTATECAMERA = 8;
    public static final int ANIM_ACTION_ROTATEMAP = 4;
    public static final int ANIM_ACTION_ZOOM = 2;
    public static final int DATA_IN_DISK = 1;
    public static final int DATA_IN_MEMORY = 2;
    public static final int DATA_NOTEXIT = 0;
    private static final int LONG_LONG_TICK_COUNT = 3;
    private static final int LONG_TICK_COUNT = 2;
    private static final int MAX_CUR_SCREEN_GRIDS_COUNT = 200;
    private static final int NORMAL_TICK_COUNT = 1;
    public static final int TAP_LINES = 2;
    public static final int TAP_POINTS = 1;
    public static final int TAP_POLYGON = 4;
    private Context mContext;
    private IAMap mGlMapView;
    private long mNativeMapengineInstance;
    private TextTextureGenerator mTextTextureGenerator;
    public String mUserAgent;
    private ADGLMapAnimationMgr map_anims_mgr;
    public static int BUILDINGHEIGHT = 0;
    private static final Charset charset = Charset.forName("utf-8");
    private List<MapSourceGridData> geometryReqMapGrids = new ArrayList();
    private List<MapSourceGridData> roadReqMapGrids = new ArrayList();
    private List<MapSourceGridData> bldReqMapGrids = new ArrayList();
    private List<MapSourceGridData> regionReqMapGrids = new ArrayList();
    private List<MapSourceGridData> poiReqMapGrids = new ArrayList();
    private List<MapSourceGridData> versionMapGrids = new ArrayList();
    private List<MapSourceGridData> indoorMapGrids = new ArrayList();
    private List<MapSourceGridData> vectmcReqMapGirds = new ArrayList();
    private List<MapSourceGridData> stiReqMapGirds = new ArrayList();
    private List<MapSourceGridData> mapReqModels = new ArrayList();
    private List<MapSourceGridData> guideReqMapGirds = new ArrayList();
    private List<MapSourceGridData> mapHeatReqMapGirds = new ArrayList();
    private List<MapSourceGridData> mCurScreenGirds = new ArrayList();
    private List<MapSourceGridData> mEagleEyeScreenGirds = null;
    private int mCurScreenGridsCount = 0;
    private Object mCurGridFillLock = new Object();
    private ConnectionManager mConnectionManager = null;
    public TilesProcessingCtrl mTileProcessCtrl = null;
    private GLMapCoreCallback mapcoreListener = null;
    private RouteBoardDataListener mBoardDataListener = null;
    private ScenicListener mScenicListener = null;
    public MapListener mMapListener = null;
    public IAMapListener mAMapListener = null;
    private MapHeatListener mMapHeatListener = null;
    private List<CameraUpdateMessage> mStateMessageList = new Vector();
    private List<GestureMapMessage> mGestureMessageList = new Vector();
    private List<GestureMapMessage> mGestureEndMessageList = new Vector();
    private boolean canStopRender = false;
    boolean isMoveCameraStep = false;
    boolean isGestureStep = false;
    private int map_gesture_count = 0;
    private Object mut_lock = new Object();
    private int mShowErrowTipCouont = 0;
    private GLMapState copyGLMapState = null;
    MapTilsCacheAndResManager mCacheMgr = null;
    GLMapState s = new GLMapState();
    private SparseArray<GLStateInstance> mStateSparseArray = new SparseArray<>();
    private byte[] tmp_3072bytes_data = ByteBuffer.allocate(3072).array();

    private static native void nativeAddEngine(int i);

    private static native void nativeAddFlingAnimation(int i, long j, int i2, int i3, int i4);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeAddGeoAndScreenCenterGroupAnimation(int i, long j, int i2, int i3, int i4, int i5, int i6, boolean z);

    private static native void nativeAddGroupAnimation(int i, long j, int i2, int i3, float f, int i4, int i5, int i6, int i7, boolean z);

    private static native void nativeAddLabels3rd(int i, long j, int i2, Label3rd[] label3rdArr, boolean z);

    private static native void nativeAddMapGestureMsg(int i, long j, int i2, float f, float f2, float f3);

    private static native void nativeAddMapNaviMsg(int i, long j, long j2, long j3, Point point, Point point2, float f, float f2, float f3, float f4, float f5);

    private static native boolean nativeAddOverlayTexture(int i, long j, int i2, int i3, float f, float f2, Bitmap bitmap, boolean z, boolean z2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeAddPivotZoomAnimation(int i, long j, int i2, float f, int i3, int i4);

    private static native void nativeAddPoiFilter(int i, long j, int i2, int i3, int i4, float f, float f2, float f3, float f4, String str, int i5);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeAddZoomRotateAnimation(int i, long j, int i2, float f, int i3, int i4, int i5);

    private static native void nativeAppendOpenLayer(int i, long j, byte[] bArr);

    private static native boolean nativeCanStopRenderMap(int i, long j);

    private static native void nativeChangeMapEnv(int i, long j, String str);

    private static native void nativeChangeSurface(int i, long j, int i2, int i3, int i4, int i5, int i6, int i7, GLMapEngine gLMapEngine);

    private static native void nativeClearAllMessage(int i, long j);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeClearAnimation(int i, long j);

    private static native void nativeClearAnimationByType(int i, long j, int i2);

    private static native void nativeClearLabels3rd(int i, long j, int i2, boolean z);

    private static native void nativeClearOverlayTexture(int i, long j);

    private static native void nativeClearPoiFilter(int i, long j);

    private static native void nativeClearSelectedScenicPois(int i, long j);

    private static native long nativeCreate(String str, String str2, int i);

    private static native int nativeCreateEngineWithFrame(int i, long j, int i2, int i3, int i4, int i5, int i6, int i7, boolean z);

    protected static native long nativeCreateOverlay(int i, long j, int i2);

    private static native void nativeCreateSurface(int i, long j, GLMapEngine gLMapEngine);

    private static native boolean nativeDeleteOpenLayer(int i, long j, int i2);

    protected static native void nativeDestoryOverlay(int i, long j);

    private static native void nativeDestorySurface(long j, GLMapEngine gLMapEngine);

    private static native void nativeDestroy(long j);

    private static native void nativeDrawFrame(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeFinishAnimations(int i, long j);

    private static native int nativeGetAnimationCount(int i, long j);

    private static native int[] nativeGetEngineIDArray(long j);

    private static native int nativeGetEngineIDWithGestureInfo(long j, EAMapPlatformGestureInfo eAMapPlatformGestureInfo);

    private static native int nativeGetGestureMsgCount(int i, long j);

    private static native long nativeGetGlOverlayMgrPtr(int i, long j);

    private static native boolean nativeGetIsProcessBuildingMark(int i, long j);

    private static native String nativeGetMapCachePath(int i, long j);

    public static native String nativeGetMapEngineVersion(int i);

    private static native String nativeGetMapHeatPoiId(int i, long j);

    private static native int[] nativeGetMapModeState(int i, long j, boolean z);

    private static native long nativeGetMapStateInstance(int i, long j);

    private static native String nativeGetRealCityDataVerSion(int i, long j, int i2);

    private static native void nativeGetScreenGrids(int i, long j, byte[] bArr, int i2);

    private static native boolean nativeGetSrvViewStateBoolValue(int i, long j, int i2);

    private static native float nativeGetSrvViewStateFloatValue(int i, long j, int i2);

    private static native int nativeGetSrvViewStateIntValue(int i, long j, int i2);

    private static native int nativeIndoorBuildingHitTest(int i, long j, int i2, int i3, byte[] bArr);

    private static native void nativeInsertEngine(int i, long j);

    private static native void nativeInsertOpenLayer(int i, long j, byte[] bArr, int i2);

    private static native int nativeIsRealCityAnimateFinish(int i, long j);

    private static native boolean nativeIsShowFeatureSpotIcon(int i, long j);

    private static native boolean nativeIsShowLandMarkBuildingPoi(int i, long j);

    private static native boolean nativeIsShowMask(int i, long j);

    private static native boolean nativeIsSkinExist(int i, long j, int[] iArr);

    private static native int nativeIsSupportRealcity(int i, long j, int i2);

    public static native void nativeLoadStartResource(int i, long j, GLMapEngine gLMapEngine, int[] iArr, int i2, int i3);

    /* JADX INFO: Access modifiers changed from: private */
    public static native boolean nativeMapDataControl(int i, long j, int i2, int i3, int i4, int i5, byte[] bArr);

    private static native void nativePopRenderState(int i, long j);

    private static native void nativePostDrawFrame(long j);

    private static native void nativePushRendererState(int i, long j);

    private static native int nativePutMapdata(int i, long j, int i2, byte[] bArr, int i3);

    private static native void nativePutResourceData(int i, long j, byte[] bArr);

    private static native void nativeRemoveEngine(int i, long j);

    private static native void nativeRemovePoiFilter(int i, long j, String str);

    private static native int nativeSelectMapPois(int i, long j, int i2, int i3, int i4, byte[] bArr);

    private static native void nativeSetBackGroundColor(int i, long j, float f, float f2, float f3, float f4);

    private static native void nativeSetIndoorBuildingToBeActive(int i, long j, String str, int i2, String str2);

    private static native void nativeSetInternalTexture(int i, long j, byte[] bArr, int i2);

    private static native void nativeSetMapHeatPoiRegion(int i, long j, String str, int[] iArr, int[] iArr2);

    private static native boolean nativeSetMapModeAndStyle(int i, long j, int[] iArr, boolean z, boolean z2, StyleItem[] styleItemArr);

    private static native void nativeSetMaskColor(int i, long j, int i2);

    private static native void nativeSetParmater(int i, long j, int i2, int i3, int i4, int i5, int i6);

    private static native void nativeSetScenicFilter(int i, long j, int i2, int[] iArr);

    private static native void nativeSetSearchedSubwayIds(int i, long j, String[] strArr);

    private static native void nativeSetServiceViewRect(int i, long j, int i2, int i3, int i4, int i5, int i6, int i7);

    private static native void nativeSetServiceViewVisible(int i, boolean z, long j);

    private static native void nativeSetShowFeatureSpotIcon(int i, long j, boolean z);

    private static native void nativeSetShowMask(int i, long j, boolean z);

    private static native void nativeSetSrvViewStateBoolValue(int i, long j, int i2, boolean z);

    private static native void nativeSetSrvViewStateFloatValue(int i, long j, int i2, float f);

    private static native void nativeSetSrvViewStateIntValue(int i, long j, int i2, int i3);

    private static native int nativeTmcCacheCheckValid(int i, long j, String str, boolean z, boolean z2, byte[] bArr);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeUpdateStyleOrIcons(int i, long j, String str, int i2);

    private static native void nativeUpdateTmcCache(int i, long j, int i2, byte[] bArr, String str, boolean z);

    public void pushRendererState() {
        if (this.mNativeMapengineInstance != 0) {
            nativePushRendererState(1, this.mNativeMapengineInstance);
        }
    }

    public void popRendererState() {
        if (this.mNativeMapengineInstance != 0) {
            nativePopRenderState(1, this.mNativeMapengineInstance);
        }
    }

    public int getStateMessageCount() {
        return this.mStateMessageList.size();
    }

    public void addMessage(CameraUpdateMessage cameraUpdateMessage) {
        if (this.mStateMessageList != null) {
            this.mStateMessageList.add(cameraUpdateMessage);
        }
    }

    public synchronized CameraUpdateMessage getStateMessage() {
        CameraUpdateMessage cameraUpdateMessage;
        if (this.mStateMessageList == null || this.mStateMessageList.size() != 0) {
            cameraUpdateMessage = this.mStateMessageList.get(0);
            this.mStateMessageList.remove(cameraUpdateMessage);
        } else {
            cameraUpdateMessage = null;
        }
        return cameraUpdateMessage;
    }

    public GLMapEngine(Context context, IAMap iAMap) {
        this.mContext = null;
        this.mTextTextureGenerator = null;
        this.mNativeMapengineInstance = 0L;
        this.mGlMapView = null;
        this.mUserAgent = "";
        this.map_anims_mgr = null;
        this.mContext = context;
        this.mGlMapView = iAMap;
        this.map_anims_mgr = new ADGLMapAnimationMgr();
        this.mTextTextureGenerator = new TextTextureGenerator();
        String fontVersion = this.mTextTextureGenerator.getFontVersion();
        File dir = context.getDir("Map_Assert", 0);
        if (!dir.exists()) {
            dir.mkdir();
        }
        this.mNativeMapengineInstance = nativeCreate(dir.getPath() + "/", fontVersion, BUILDINGHEIGHT);
        this.mUserAgent = System.getProperty("http.agent") + " amap/" + GLMapUtil.getAppVersionName(context);
    }

    public MapTilsCacheAndResManager getMapCacheMgr() {
        return this.mCacheMgr;
    }

    public void initConnectionManager() {
        this.mConnectionManager = new ConnectionManager(this);
        this.mTileProcessCtrl = new TilesProcessingCtrl();
        this.mConnectionManager.start();
    }

    public void loadStartResource(int i, int i2, int i3, int i4, boolean z, int i5, int i6) {
        int[] iArr = {i2, i3, i4, 0, 0};
        if (this.mNativeMapengineInstance != 0) {
            nativeLoadStartResource(i, this.mNativeMapengineInstance, this, iArr, i5, i6);
        }
    }

    public void changeSurface(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        if (this.mNativeMapengineInstance != 0) {
            nativeChangeSurface(i, this.mNativeMapengineInstance, i2, i3, i4, i5, i6, i7, this);
        }
    }

    public void destorySurface() {
        if (this.mTileProcessCtrl != null) {
            this.mTileProcessCtrl.clearAll();
        }
        if (this.mConnectionManager != null) {
            this.mConnectionManager.threadFlag = false;
            if (this.mConnectionManager.isAlive()) {
                try {
                    this.mConnectionManager.interrupt();
                } catch (Exception e) {
                }
                this.mConnectionManager.shutDown();
                this.mConnectionManager = null;
            }
        }
        if (this.mStateSparseArray != null) {
            this.mStateSparseArray.clear();
        }
        MoveGestureMapMessage.destory();
        HoverGestureMapMessage.destory();
        RotateGestureMapMessage.destory();
        ScaleGestureMapMessage.destory();
        if (this.mNativeMapengineInstance != 0) {
            nativeDestorySurface(this.mNativeMapengineInstance, this);
        }
        this.tmp_3072bytes_data = null;
        synchronized (this.mut_lock) {
            if (this.mNativeMapengineInstance != 0) {
                nativeDestroy(this.mNativeMapengineInstance);
                if (this.copyGLMapState != null) {
                    this.copyGLMapState.recycle();
                    this.copyGLMapState = null;
                }
                this.mNativeMapengineInstance = 0L;
            }
        }
    }

    public void drawFrame() {
        if (this.mNativeMapengineInstance != 0) {
            processMessage();
            synchronized (GLMapEngine.class) {
                nativeDrawFrame(this.mNativeMapengineInstance);
                nativePostDrawFrame(this.mNativeMapengineInstance);
            }
        }
    }

    private void processMessage() {
        try {
            GLMapState cloneMapState = getCloneMapState();
            cloneMapState.reset();
            processGestureMessage(cloneMapState);
            if (this.mGestureMessageList.size() <= 0) {
                processStateMapMessage(cloneMapState);
            } else if (this.mStateMessageList.size() > 0) {
                this.mStateMessageList.clear();
            }
            processAnimations(cloneMapState);
            setMapState(1, cloneMapState);
        } catch (Exception e) {
        }
    }

    private void processAnimations(GLMapState gLMapState) {
        try {
            if (this.map_anims_mgr.getAnimationsCount() > 0) {
                gLMapState.recalculate();
                this.map_anims_mgr.doAnimations(gLMapState);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void processGestureMessage(GLMapState gLMapState) {
        GestureMapMessage remove;
        if (this.mGestureMessageList.size() > 0) {
            this.isGestureStep = true;
            if (gLMapState != null) {
                gLMapState.recalculate();
                while (this.mGestureMessageList.size() > 0 && (remove = this.mGestureMessageList.remove(0)) != null) {
                    if (remove.width == 0) {
                        remove.width = this.mGlMapView.getMapWidth();
                    }
                    if (remove.height == 0) {
                        remove.height = this.mGlMapView.getMapHeight();
                    }
                    int mapGestureState = remove.getMapGestureState();
                    if (mapGestureState == 100) {
                        gestureBegin();
                    } else if (mapGestureState == 101) {
                        remove.runCameraUpdate(gLMapState);
                    } else if (mapGestureState == 102) {
                        gestureEnd();
                    }
                    this.mGestureEndMessageList.add(remove);
                }
                if (this.mGestureEndMessageList.size() == 1) {
                    recycleMessage();
                }
            }
        } else if (this.isGestureStep) {
            this.isGestureStep = false;
        }
    }

    private void gestureBegin() {
        this.map_gesture_count++;
    }

    private void gestureEnd() {
        this.map_gesture_count--;
        if (this.map_gesture_count == 0) {
            recycleMessage();
        }
    }

    private void recycleMessage() {
        GestureMapMessage remove;
        while (this.mGestureEndMessageList.size() > 0 && (remove = this.mGestureEndMessageList.remove(0)) != null) {
            if (remove instanceof MoveGestureMapMessage) {
                ((MoveGestureMapMessage) remove).recycle();
            } else if (remove instanceof HoverGestureMapMessage) {
                ((HoverGestureMapMessage) remove).recycle();
            } else if (remove instanceof RotateGestureMapMessage) {
                ((RotateGestureMapMessage) remove).recycle();
            } else if (remove instanceof ScaleGestureMapMessage) {
                ((ScaleGestureMapMessage) remove).recycle();
            }
        }
    }

    private void processStateMapMessage(GLMapState gLMapState) {
        CameraUpdateMessage remove;
        if (this.mStateMessageList.size() > 0) {
            this.isMoveCameraStep = true;
            if (gLMapState != null) {
                while (this.mStateMessageList.size() > 0 && (remove = this.mStateMessageList.remove(0)) != null) {
                    if (remove.width == 0) {
                        remove.width = this.mGlMapView.getMapWidth();
                    }
                    if (remove.height == 0) {
                        remove.height = this.mGlMapView.getMapHeight();
                    }
                    gLMapState.recalculate();
                    remove.runCameraUpdate(gLMapState);
                }
            }
        } else if (this.isMoveCameraStep) {
            this.isMoveCameraStep = false;
        }
    }

    public Context getContext() {
        return this.mContext.getApplicationContext();
    }

    public void changeMapEnv(int i) {
        if (this.mNativeMapengineInstance != 0) {
            nativeChangeMapEnv(i, this.mNativeMapengineInstance, "");
            this.mCacheMgr.setMapCachePath(nativeGetMapCachePath(i, this.mNativeMapengineInstance));
            this.mCacheMgr.checkDir();
        }
    }

    public boolean isMapEngineValid() {
        boolean z;
        synchronized (this.mut_lock) {
            z = this.mNativeMapengineInstance != 0;
        }
        return z;
    }

    public String getMapSvrAddress() {
        return GLMapServerUtil.BASE_MAP_URL_SERVER_PUBLIC;
    }

    public String getMapIndoorAddress() {
        return GLMapServerUtil.INDOOR_MAP_URL_SERVER_PUBLIC;
    }

    public void setMapCoreListener(GLMapCoreCallback gLMapCoreCallback) {
        this.mapcoreListener = gLMapCoreCallback;
    }

    public void clearAllMessages(int i) {
        nativeClearAllMessage(i, this.mNativeMapengineInstance);
    }

    public synchronized void addGestureMessage(int i, GestureMapMessage gestureMapMessage, boolean z, int i2, int i3) {
        if (gestureMapMessage != null) {
            gestureMapMessage.isGestureScaleByMapCenter = z;
            this.mGestureMessageList.add(gestureMapMessage);
        }
    }

    public boolean isInMapAction(int i) {
        return nativeGetGestureMsgCount(i, this.mNativeMapengineInstance) > 0;
    }

    public boolean isInMapAnimation(int i) {
        return getAnimateionsCount() > 0;
    }

    public int getAnimateionsCount() {
        if (this.mNativeMapengineInstance != 0) {
            return this.map_anims_mgr.getAnimationsCount();
        }
        return 0;
    }

    public void setMapState(int i, GLMapState gLMapState) {
        setMapState(i, gLMapState, true);
    }

    public synchronized void setMapState(int i, GLMapState gLMapState, boolean z) {
        if (this.mNativeMapengineInstance != 0) {
            if (!(!z || this.mGlMapView == null || this.mGlMapView.getMapConfig() == null)) {
                this.mGlMapView.checkMapState(gLMapState);
            }
            gLMapState.setNativeMapengineState(i, this.mNativeMapengineInstance);
        }
    }

    public synchronized GLMapState getNewMapState(int i) {
        return this.mNativeMapengineInstance != 0 ? new GLMapState(i, this.mNativeMapengineInstance) : null;
    }

    public synchronized GLMapState getCloneMapState() {
        if (this.copyGLMapState == null) {
            this.copyGLMapState = new GLMapState(1, getMapEnginePtr());
        }
        this.copyGLMapState.setMapZoomer(this.mGlMapView.getMapConfig().getS_z());
        this.copyGLMapState.setCameraDegree(this.mGlMapView.getMapConfig().getS_c());
        this.copyGLMapState.setMapAngle(this.mGlMapView.getMapConfig().getS_r());
        this.copyGLMapState.setMapGeoCenter(this.mGlMapView.getMapConfig().getS_x(), this.mGlMapView.getMapConfig().getS_y());
        return this.copyGLMapState;
    }

    public synchronized GLMapState getMapState(int i) {
        GLMapState gLMapState;
        if (this.mNativeMapengineInstance != 0) {
            this.s.setMapstateInstance(getStateInstanceWithEngineID(i));
            gLMapState = this.s;
        } else {
            gLMapState = null;
        }
        return gLMapState;
    }

    public long getMapStateInstance(int i) {
        return getStateInstanceWithEngineID(i);
    }

    private List<MapSourceGridData> getReqGridList(int i) {
        switch (i) {
            case 0:
                return this.roadReqMapGrids;
            case 1:
                return this.bldReqMapGrids;
            case 2:
            case 5:
            case 6:
            case 13:
            case 14:
            default:
                return null;
            case 3:
                return this.stiReqMapGirds;
            case 4:
                return this.vectmcReqMapGirds;
            case 7:
                return this.regionReqMapGrids;
            case 8:
                return this.poiReqMapGrids;
            case 9:
                return this.versionMapGrids;
            case 10:
                return this.indoorMapGrids;
            case 11:
                return this.guideReqMapGirds;
            case 12:
                return this.mapHeatReqMapGirds;
            case 15:
                return this.geometryReqMapGrids;
        }
    }

    private void processRequiredData(final int i, List<MapSourceGridData> list, int i2, String str) {
        byte[] bArr;
        int nativeTmcCacheCheckValid;
        if (!NetworkState.getInstance().isInternetConnected(this.mContext)) {
            if (this.mShowErrowTipCouont <= 0) {
                this.mShowErrowTipCouont++;
                onException(i, 1002);
            }
            if (this.mapcoreListener != null) {
                this.mapcoreListener.postQueueEvent(new Runnable() { // from class: com.autonavi.ae.gmap.GLMapEngine.1
                    @Override // java.lang.Runnable
                    public void run() {
                        GLMapEngine.this.setParamater(i, 5001, 1002, 0, 0, 0);
                    }
                });
                return;
            }
            return;
        }
        this.mShowErrowTipCouont = 0;
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            MapSourceGridData mapSourceGridData = list.get(i3);
            if (!this.mTileProcessCtrl.isProcessing(mapSourceGridData.getKeyGridName())) {
                if (i2 == 4) {
                    String gridName = mapSourceGridData.getGridName();
                    if (!TextUtils.isEmpty(gridName) && 1 != (nativeTmcCacheCheckValid = nativeTmcCacheCheckValid(i, this.mNativeMapengineInstance, gridName, true, false, (bArr = new byte[100])))) {
                        if (nativeTmcCacheCheckValid == 0) {
                            byte b = bArr[0];
                            String str2 = null;
                            if (b > 0) {
                                try {
                                    str2 = new String(bArr, 1, b, "UTF-8");
                                } catch (UnsupportedEncodingException e) {
                                }
                            }
                            if (!TextUtils.isEmpty(str2)) {
                                mapSourceGridData.mObj = str2;
                            }
                        }
                        arrayList.add(mapSourceGridData);
                    }
                } else {
                    arrayList.add(mapSourceGridData);
                }
            }
        }
        if (arrayList.size() > 0) {
            BaseMapLoader baseMapLoader = null;
            switch (i2) {
                case 4:
                    int[] mapModeState = getMapModeState(i, false);
                    if (mapModeState != null) {
                        baseMapLoader = new TmcMapLoader(i, this, i2);
                        baseMapLoader.setGetRequestMethod(false);
                        ((NormalMapLoader) baseMapLoader).setMapParams((int) getMapZoomer(i), mapModeState[0], mapModeState[2]);
                        break;
                    }
                    break;
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                default:
                    int[] mapModeState2 = getMapModeState(i, false);
                    if (mapModeState2 != null) {
                        baseMapLoader = new NormalMapLoader(i, this, i2);
                        baseMapLoader.setGetRequestMethod(false);
                        ((NormalMapLoader) baseMapLoader).setMapParams((int) getMapZoomer(i), mapModeState2[0], mapModeState2[2]);
                        break;
                    }
                    break;
                case 10:
                    baseMapLoader = new IndoorMapLoader(i, this, i2);
                    break;
                case 11:
                    baseMapLoader = new GuideMapLoader(i, this, i2);
                    baseMapLoader.setGetRequestMethod(false);
                    break;
                case 12:
                    baseMapLoader = new HeatMapLoader(i, this, i2);
                    baseMapLoader.setGetRequestMethod(false);
                    ((HeatMapLoader) baseMapLoader).setMapHeatPoiId(str);
                    break;
            }
            int size2 = arrayList.size();
            for (int i4 = 0; i4 < size2; i4++) {
                MapSourceGridData mapSourceGridData2 = (MapSourceGridData) arrayList.get(i4);
                this.mTileProcessCtrl.addProcessingTile(mapSourceGridData2.getKeyGridName());
                baseMapLoader.addRequestTiles(mapSourceGridData2);
            }
            if (this.mConnectionManager != null) {
                this.mConnectionManager.insertConntionTask(baseMapLoader);
            }
        }
    }

    public boolean isGridsInScreen(int i, List<MapSourceGridData> list, int i2) {
        try {
            if (list.size() != 0 && isMapEngineValid()) {
                synchronized (this.mCurGridFillLock) {
                    int size = list.size();
                    for (int i3 = 0; i3 < size; i3++) {
                        if (isGridInList(i, list.get(i3).getGridName())) {
                            return true;
                        }
                    }
                    return false;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public int GetCurrentGrideNameLen(int i) {
        int i2;
        try {
            if (!isMapEngineValid()) {
                i2 = 0;
            } else {
                synchronized (this.mCurGridFillLock) {
                    i2 = this.mCurScreenGridsCount <= 0 ? 0 : i == 1 ? this.mCurScreenGirds.get(0).getGridName().length() : i == 2 ? this.mEagleEyeScreenGirds.get(0).getGridName().length() : 0;
                }
            }
            return i2;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean isGridInScreen(int i, int i2, String str) {
        boolean z = false;
        try {
            if (isMapEngineValid()) {
                synchronized (this.mCurGridFillLock) {
                    if (isGridInList(i, str)) {
                        z = true;
                    }
                }
            }
            return z;
        } catch (Exception e) {
            return true;
        }
    }

    public boolean isIndoorGridsInScreen(List<MapSourceGridData> list, int i) {
        return true;
    }

    public boolean isIndoorGridInScreen(int i, String str, short s) {
        return true;
    }

    private boolean isIndoorGridInList(String str, List<MapSourceGridData> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getKeyGridName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    private boolean isGridInList(int i, String str) {
        int i2 = this.mCurScreenGridsCount;
        for (int i3 = 0; i3 < i2; i3++) {
            if (i == 1) {
                if (this.mCurScreenGirds.get(i3).getGridName().startsWith(str)) {
                    return true;
                }
            } else if (i == 2 && this.mEagleEyeScreenGirds.get(i3).getGridName().startsWith(str)) {
                return true;
            }
        }
        return false;
    }

    public void clearException(int i) {
        if (this.mapcoreListener != null) {
            this.mapcoreListener.clearException(i);
        }
    }

    public void onException(int i, int i2) {
        if (this.mapcoreListener != null) {
            this.mapcoreListener.onException(i, i2);
        }
    }

    @Override // com.autonavi.ae.gmap.callback.JniMapCoreCallback
    public void onMapSurfaceRenderer(int i, int i2) {
        try {
            switch (i2) {
                case 0:
                    if (2 == IsRealCityAnimateFinish(i) && this.mMapListener != null) {
                        this.mMapListener.onRealCityAnimateFinish(i);
                        return;
                    }
                    return;
                case 1:
                    if (this.mMapListener != null) {
                        this.mMapListener.beforeDrawFrame(i, getMapState(i));
                        return;
                    }
                    return;
                case 3:
                    if (this.mAMapListener != null) {
                        this.mAMapListener.beforeDrawLabel(i, getMapState(i));
                        return;
                    }
                    return;
                case 4:
                    if (this.mAMapListener != null) {
                        this.mAMapListener.afterDrawLabel(i, getMapState(i));
                        return;
                    }
                    return;
                case 5:
                    if (this.mMapListener != null) {
                        this.mMapListener.afterDrawFrame(i, getMapState(i));
                    }
                    if (this.mAMapListener != null) {
                        this.mAMapListener.afterDrawFrame(i, getMapState(i));
                        return;
                    }
                    return;
                case 11:
                    fillCurScreenGrids(i);
                    return;
                case 10000:
                    if (this.mapcoreListener != null) {
                        this.mapcoreListener.resetRenderTimeLong();
                        return;
                    }
                    return;
                default:
                    return;
            }
        } catch (Throwable th) {
        }
    }

    @Override // com.autonavi.ae.gmap.callback.JniMapCoreCallback
    public void onMapSufaceChanged(int i, int i2, int i3) {
    }

    @Override // com.autonavi.ae.gmap.callback.JniMapCoreCallback
    public void onMapProcessEvent(int i) {
    }

    @Override // com.autonavi.ae.gmap.callback.JniMapCoreCallback
    public void onMapDataRequired(int i, int i2, String[] strArr) {
        List<MapSourceGridData> reqGridList;
        String str = null;
        if (!(strArr == null || strArr.length == 0 || this.mNativeMapengineInstance == 0 || (reqGridList = getReqGridList(i2)) == null)) {
            for (int i3 = 0; i3 < reqGridList.size(); i3++) {
                reqGridList.get(i3).recycle();
            }
            reqGridList.clear();
            int length = strArr.length;
            MapSourceGridData mapSourceGridData = null;
            for (int i4 = 0; i4 < length; i4++) {
                if (i2 == 12) {
                    str = nativeGetMapHeatPoiId(i, this.mNativeMapengineInstance);
                    if (!TextUtils.isEmpty(str)) {
                        mapSourceGridData = MapSourceGridData.obtain();
                        mapSourceGridData.setGridData(strArr[i4], str, i2);
                    }
                } else {
                    mapSourceGridData = MapSourceGridData.obtain();
                    mapSourceGridData.setGridData(strArr[i4], i2);
                }
                if (mapSourceGridData != null) {
                    reqGridList.add(mapSourceGridData);
                }
            }
            if (i2 != 5) {
                processRequiredData(i, reqGridList, i2, str);
            }
        }
    }

    @Override // com.autonavi.ae.gmap.callback.JniMapCoreCallback
    public byte[] onCharBitmapRequired(int i, int i2) {
        byte[] textPixelBuffer = this.mTextTextureGenerator.getTextPixelBuffer(i2);
        if (this.mapcoreListener != null) {
            this.mapcoreListener.resetRenderTimeLong();
        }
        return textPixelBuffer;
    }

    @Override // com.autonavi.ae.gmap.callback.JniMapCoreCallback
    public byte[] onMapCharsWidthsRequired(int i, int[] iArr, int i2, int i3) {
        return this.mTextTextureGenerator.getCharsWidths(iArr);
    }

    @Override // com.autonavi.ae.gmap.callback.JniMapCoreCallback
    public void onClearCache(int i) {
        VMapDataCache.getInstance().reset();
    }

    @Override // com.autonavi.ae.gmap.callback.JniIndoorCallback
    public void onIndoorBuildingActivity(int i, byte[] bArr) {
        if (this.mGlMapView != null) {
            try {
                this.mGlMapView.onIndoorBuildingActivity(i, bArr);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    @Override // com.autonavi.ae.gmap.callback.JniIndoorCallback
    public void onIndoorDataRequired(int i, int i2, String[] strArr, int[] iArr, int[] iArr2) {
        List<MapSourceGridData> reqGridList;
        if (!(strArr == null || strArr.length == 0 || (reqGridList = getReqGridList(i2)) == null)) {
            for (int i3 = 0; i3 < reqGridList.size(); i3++) {
                reqGridList.get(i3).recycle();
            }
            reqGridList.clear();
            for (int i4 = 0; i4 < strArr.length; i4++) {
                MapSourceGridData obtain = MapSourceGridData.obtain();
                obtain.setGridData(strArr[i4], i2, iArr[i4], iArr2[i4]);
                reqGridList.add(obtain);
            }
            if (i2 != 5) {
                processRequiredData(i, reqGridList, i2, null);
            }
        }
    }

    private List<MapSourceGridData> parseTiles(byte[] bArr, int i) {
        Throwable th;
        ArrayList arrayList;
        try {
            byte b = bArr[0];
            if (b <= 0) {
                return null;
            }
            arrayList = new ArrayList();
            int i2 = 1;
            for (int i3 = 0; i3 < b; i3++) {
                int i4 = i2 + 1;
                try {
                    byte b2 = bArr[i2];
                    String str = new String(bArr, i4, b2, "utf-8");
                    i2 = b2 + i4 + 1;
                    arrayList.add(new MapSourceGridData(str, i));
                } catch (Throwable th2) {
                    th = th2;
                    th.printStackTrace();
                    return arrayList;
                }
            }
            return arrayList;
        } catch (Throwable th3) {
            arrayList = null;
            th = th3;
        }
    }

    public ArrayList<String> getCurScreenGridList() {
        ArrayList<String> arrayList = new ArrayList<>();
        synchronized (this.mCurGridFillLock) {
            for (int i = 0; i < this.mCurScreenGridsCount; i++) {
                arrayList.add(this.mCurScreenGirds.get(i).getGridName());
            }
        }
        return arrayList;
    }

    private void fillCurScreenGrids(int i) throws UnsupportedEncodingException {
        if (this.mNativeMapengineInstance != 0) {
            synchronized (this.mCurGridFillLock) {
                nativeGetScreenGrids(i, this.mNativeMapengineInstance, this.tmp_3072bytes_data, 5);
                byte b = this.tmp_3072bytes_data[0];
                if (b > 0) {
                    byte b2 = b > 200 ? (byte) 200 : b;
                    this.mCurScreenGridsCount = 0;
                    if (i == 1) {
                        if (this.mCurScreenGirds != null) {
                            for (int i2 = 0; i2 < this.mCurScreenGirds.size(); i2++) {
                                this.mCurScreenGirds.get(i2).recycle();
                            }
                            this.mCurScreenGirds.clear();
                        } else {
                            this.mCurScreenGirds = new ArrayList();
                        }
                    } else if (i == 2) {
                        if (this.mEagleEyeScreenGirds != null) {
                            for (int i3 = 0; i3 < this.mEagleEyeScreenGirds.size(); i3++) {
                                this.mEagleEyeScreenGirds.get(i3).recycle();
                            }
                            this.mEagleEyeScreenGirds.clear();
                        } else {
                            this.mEagleEyeScreenGirds = new ArrayList();
                        }
                    }
                    int i4 = 1;
                    for (int i5 = 0; i5 < b2 && i4 < 3072; i5++) {
                        int i6 = i4 + 1;
                        byte b3 = this.tmp_3072bytes_data[i4];
                        if (b3 <= 0 || b3 > 20 || i6 + b3 > 3072) {
                            break;
                        }
                        String str = new String(this.tmp_3072bytes_data, i6, b3, charset);
                        i4 = b3 + i6 + 1;
                        if (i == 1) {
                            MapSourceGridData obtain = MapSourceGridData.obtain();
                            obtain.setGridData(str, 5);
                            this.mCurScreenGirds.add(obtain);
                            this.mCurScreenGridsCount++;
                        } else if (i == 2) {
                            MapSourceGridData obtain2 = MapSourceGridData.obtain();
                            obtain2.setGridData(str, 5);
                            this.mEagleEyeScreenGirds.add(obtain2);
                            this.mCurScreenGridsCount++;
                        }
                    }
                }
            }
        }
    }

    public static long bytes2Long(byte[] bArr) {
        long j = 0;
        for (int i = 0; i < 8; i++) {
            j = (j << 8) | (bArr[i] & 255);
        }
        return j;
    }

    public boolean canStopMapRender(int i) {
        synchronized (this.mut_lock) {
            if (this.mNativeMapengineInstance == 0) {
                return true;
            }
            return nativeCanStopRenderMap(i, this.mNativeMapengineInstance);
        }
    }

    public void setInternaltexture(int i, byte[] bArr, int i2) {
        if (bArr != null) {
            nativeSetInternalTexture(i, this.mNativeMapengineInstance, bArr, i2);
        }
    }

    public synchronized void setMapCenter(int i, int i2, int i3) {
        GLMapState.nativeSetMapCenter(getStateInstanceWithEngineID(i), i2, i3);
    }

    public synchronized Point getMapCenter(int i) {
        Point point;
        point = new Point();
        GLMapState.nativeGetMapCenter(getStateInstanceWithEngineID(i), point);
        return point;
    }

    public synchronized void setMapZoom(int i, float f) {
        if (f > getMaxZoomLevel(i)) {
            f = getMaxZoomLevel(i);
        }
        GLMapState.nativeSetMapZoomer(getStateInstanceWithEngineID(i), f);
    }

    public synchronized byte[] getLabelBuffer(int i, int i2, int i3, int i4) {
        byte[] bArr;
        bArr = new byte[3072];
        nativeSelectMapPois(i, this.mNativeMapengineInstance, i2, i3, i4, bArr);
        return bArr;
    }

    public void clearSelectMapPois(int i) {
        nativeClearSelectedScenicPois(i, this.mNativeMapengineInstance);
    }

    public boolean putMapHeatData(int i, byte[] bArr, int i2, int i3, int i4, String str) {
        Throwable th;
        boolean z;
        if (bArr.length == 0 || str == null || str.length() == 0) {
            return false;
        }
        synchronized (this.mut_lock) {
            if (this.mNativeMapengineInstance != 0) {
                try {
                    byte[] bytes = str.getBytes("utf-8");
                    int length = bytes.length;
                    byte[] bArr2 = new byte[length + 1 + i3];
                    bArr2[0] = (byte) length;
                    System.arraycopy(bytes, 0, bArr2, 1, length);
                    System.arraycopy(bArr, i2, bArr2, length + 1, i3);
                    z = nativePutMapdata(i, this.mNativeMapengineInstance, 12, bArr2, i4) > 0;
                } catch (Throwable th2) {
                    th = th2;
                    z = false;
                }
                try {
                    if (this.mapcoreListener != null) {
                        this.mapcoreListener.resetRenderTimeLong();
                    }
                    z = z;
                } catch (Throwable th3) {
                    th = th3;
                    th.printStackTrace();
                    return z;
                }
            } else {
                z = false;
            }
        }
        return z;
    }

    public void putMapTMCData(int i, int i2, byte[] bArr, String str, boolean z) {
        synchronized (this.mut_lock) {
            if (this.mNativeMapengineInstance != 0) {
                nativeUpdateTmcCache(i, this.mNativeMapengineInstance, i2, bArr, str, z);
                if (this.mapcoreListener != null) {
                    this.mapcoreListener.resetRenderTimeLong();
                }
            }
        }
    }

    public void PutResourceData(int i, byte[] bArr) {
        synchronized (this.mut_lock) {
            if (this.mNativeMapengineInstance != 0) {
                nativePutResourceData(i, this.mNativeMapengineInstance, bArr);
                if (this.mapcoreListener != null) {
                    this.mapcoreListener.resetRenderTimeLong();
                }
            }
        }
    }

    public void UpdateStyleOrIcons(final int i, final String str, final int i2) {
        if (this.mapcoreListener != null && 0 != this.mNativeMapengineInstance) {
            this.mapcoreListener.postQueueEvent(new Runnable() { // from class: com.autonavi.ae.gmap.GLMapEngine.2
                @Override // java.lang.Runnable
                public void run() {
                    GLMapEngine.nativeUpdateStyleOrIcons(i, GLMapEngine.this.mNativeMapengineInstance, str, i2);
                }
            });
        }
    }

    public boolean putMapData(int i, byte[] bArr, int i2, int i3, int i4, int i5) {
        boolean z = true;
        z = false;
        synchronized (this.mut_lock) {
            if (this.mNativeMapengineInstance != 0) {
                if (bArr.length != i3 || i2 != 0) {
                    byte[] bArr2 = new byte[i3];
                    System.arraycopy(bArr, i2, bArr2, 0, i3);
                    if (nativePutMapdata(i, this.mNativeMapengineInstance, i4, bArr2, i5) <= 0) {
                        z = false;
                    }
                } else if (nativePutMapdata(i, this.mNativeMapengineInstance, i4, bArr, i5) <= 0) {
                    z = false;
                }
                if (this.mapcoreListener != null) {
                    this.mapcoreListener.resetRenderTimeLong();
                }
            }
        }
        return z;
    }

    public void setIndoorBuildingToBeActive(int i, String str, int i2, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            nativeSetIndoorBuildingToBeActive(i, this.mNativeMapengineInstance, str, i2, str2);
            if (this.mapcoreListener != null) {
                this.mapcoreListener.resetRenderTimeLongLong();
            }
        }
    }

    public void resetRenderTime(int i) {
        if (this.mapcoreListener != null) {
            this.mapcoreListener.resetRenderTimeLongLong();
        }
    }

    public float getMapZoomer(int i) {
        return GLMapState.nativeGetMapZoomer(getStateInstanceWithEngineID(i));
    }

    public int getMaxZoomLevel(int i) {
        return GLMapState.nativeGetMaxZoomLevel(getStateInstanceWithEngineID(i));
    }

    public int getMinZoomLevel(int i) {
        return GLMapState.nativeGetMinZoomLevel(getStateInstanceWithEngineID(i));
    }

    public synchronized void setCameraDegree(int i, float f) {
        if (f == 65.0f) {
            setParamater(i, 1900, 0, 0, 1, 0);
        }
        GLMapState.nativeSetCameraDegree(getStateInstanceWithEngineID(i), f);
    }

    public synchronized float getCameraDegree(int i) {
        return GLMapState.nativeGetCameraDegree(getStateInstanceWithEngineID(i));
    }

    public synchronized void setMapAngle(int i, float f) {
        if (f < 0.0f) {
            f += 360.0f;
        } else if (f >= 360.0f) {
            f -= 360.0f;
        }
        GLMapState.nativeSetMapAngle(getStateInstanceWithEngineID(i), f);
    }

    public synchronized float getMapAngle(int i) {
        return GLMapState.nativeGetMapAngle(getStateInstanceWithEngineID(i));
    }

    public void screenToMapGPoint(int i, float f, float f2, PointF pointF) {
        GLMapState.nativeScreenToMapGPoint(getStateInstanceWithEngineID(i), f, f2, pointF);
    }

    public void p20ToMapPoint(int i, int i2, int i3, PointF pointF) {
        GLMapState.nativeP20ToMapPoint(getStateInstanceWithEngineID(i), i2, i3, pointF);
    }

    public void p20ToScreenPoint(int i, int i2, int i3, PointF pointF) {
        GLMapState.nativeP20ToScreenPoint(getStateInstanceWithEngineID(i), i2, i3, pointF);
    }

    public void mapToScreenPoint(int i, float f, float f2, PointF pointF) {
        GLMapState.nativeMapToScreenPoint(getStateInstanceWithEngineID(i), f, f2, pointF);
    }

    public void mapToP20Point(int i, float f, float f2, Point point) {
        GLMapState.nativeMapToP20Point(getStateInstanceWithEngineID(i), f, f2, point);
    }

    public void screenToP20Point(int i, float f, float f2, Point point) {
        GLMapState.nativeScreenToP20Point(getStateInstanceWithEngineID(i), f, f2, point);
    }

    public float getGLUnitWithWin(int i, int i2) {
        return GLMapState.nativeGetGLUnitWithWin(getStateInstanceWithEngineID(i), i2);
    }

    public float getGLUnitWithWinByY(int i, int i2, int i3) {
        return GLMapState.nativeGetGLUnitWithWinByY(getStateInstanceWithEngineID(i), i2, i3);
    }

    public void getPixel20Bound(int i, Rect rect) {
        GLMapState.nativeGetPixel20Bound(getStateInstanceWithEngineID(i), rect);
    }

    public void mapToScreenPointWithZ(int i, float f, float f2, float f3, PointF pointF) {
        GLMapState.nativeMapToScreenPointWithZ(getStateInstanceWithEngineID(i), f, f2, f3, pointF);
    }

    public float getGLUnitWithPixel20(int i, int i2) {
        return GLMapState.nativeGetGLUnitWithPixel20(getStateInstanceWithEngineID(i), i2);
    }

    public void clearAnimations(int i, boolean z) {
        if (z) {
            this.map_anims_mgr.clearAnimations();
        } else if (this.mapcoreListener != null) {
            this.mapcoreListener.postQueueEvent(new Runnable() { // from class: com.autonavi.ae.gmap.GLMapEngine.3
                @Override // java.lang.Runnable
                public void run() {
                    GLMapEngine.this.map_anims_mgr.clearAnimations();
                }
            });
        }
    }

    public void clearAnimations(int i, boolean z, int i2) {
        if (z) {
            this.map_anims_mgr.clearAnimations();
        } else if (this.mapcoreListener != null) {
            this.mapcoreListener.postQueueEvent(new Runnable() { // from class: com.autonavi.ae.gmap.GLMapEngine.4
                @Override // java.lang.Runnable
                public void run() {
                    GLMapEngine.this.map_anims_mgr.clearAnimations();
                }
            });
        }
    }

    public void AddGroupAnimation(int i, final int i2, final float f, final int i3, final int i4, final int i5, final int i6, boolean z) {
        this.mapcoreListener.postQueueEvent(new Runnable() { // from class: com.autonavi.ae.gmap.GLMapEngine.5
            @Override // java.lang.Runnable
            public void run() {
                ADGLMapAnimGroup aDGLMapAnimGroup = new ADGLMapAnimGroup(i2);
                aDGLMapAnimGroup.setToCameraDegree(i4, 0);
                aDGLMapAnimGroup.setToMapAngle(i3, 0);
                aDGLMapAnimGroup.setToMapLevel(f, 0);
                aDGLMapAnimGroup.setToMapCenterGeo(i5, i6, 0);
                if (GLMapEngine.this.map_anims_mgr != null && aDGLMapAnimGroup != null && aDGLMapAnimGroup.isValid()) {
                    GLMapEngine.this.map_anims_mgr.addAnimation(aDGLMapAnimGroup);
                }
            }
        });
    }

    public void AddGeoAndScreenCenterGroupAnimation(final int i, final int i2, final GLGeoPoint gLGeoPoint, final Point point, final boolean z) {
        if (this.mapcoreListener != null && gLGeoPoint != null && point != null) {
            this.mapcoreListener.postQueueEvent(new Runnable() { // from class: com.autonavi.ae.gmap.GLMapEngine.6
                @Override // java.lang.Runnable
                public void run() {
                    GLMapEngine.nativeAddGeoAndScreenCenterGroupAnimation(i, GLMapEngine.this.mNativeMapengineInstance, i2, gLGeoPoint.x, gLGeoPoint.y, point.x, point.y, z);
                }
            });
        }
    }

    public void startPivotZoomRotateAnim(final int i, final Point point, final float f, int i2, final int i3) {
        if (f != -9999.0f || i2 != -9999) {
            final int i4 = (i2 == -9999 || i2 >= 0) ? i2 : i2 + a.q;
            this.mapcoreListener.postQueueEvent(new Runnable() { // from class: com.autonavi.ae.gmap.GLMapEngine.7
                @Override // java.lang.Runnable
                public void run() {
                    GLMapEngine.nativeClearAnimation(i, GLMapEngine.this.mNativeMapengineInstance);
                    if (point != null) {
                        GLMapEngine.nativeAddZoomRotateAnimation(i, GLMapEngine.this.mNativeMapengineInstance, i3, f, i4, point.x, point.y);
                    } else {
                        GLMapEngine.nativeAddZoomRotateAnimation(i, GLMapEngine.this.mNativeMapengineInstance, i3, f, i4, 0, 0);
                    }
                }
            });
        }
    }

    public void startPivotZoomAnim(final int i, final Point point, final float f, final int i2) {
        if (point != null && point.x <= 0 && point.y <= 0) {
            point.y = 1;
        }
        MapConfig mapConfig = this.mGlMapView.getMapConfig();
        if (mapConfig != null && f <= mapConfig.getMaxZoomLevel() && f >= mapConfig.getMinZoomLevel()) {
            this.mapcoreListener.postQueueEvent(new Runnable() { // from class: com.autonavi.ae.gmap.GLMapEngine.8
                @Override // java.lang.Runnable
                public void run() {
                    GLMapEngine.nativeFinishAnimations(i, GLMapEngine.this.mNativeMapengineInstance);
                    if (point != null) {
                        GLMapEngine.nativeAddPivotZoomAnimation(i, GLMapEngine.this.mNativeMapengineInstance, i2, f, point.x, point.y);
                    } else {
                        GLMapEngine.nativeAddPivotZoomAnimation(i, GLMapEngine.this.mNativeMapengineInstance, i2, f, 0, 0);
                    }
                }
            });
        }
    }

    public void startMapSlidAnim(final int i, final Point point, final float f, final float f2) {
        this.mapcoreListener.postQueueEvent(new Runnable() { // from class: com.autonavi.ae.gmap.GLMapEngine.9
            @Override // java.lang.Runnable
            public void run() {
                float f3;
                float f4;
                if (point != null) {
                    try {
                        GLMapEngine.this.clearAnimations(i, true);
                        GLMapState cloneMapState = GLMapEngine.this.getCloneMapState();
                        cloneMapState.reset();
                        cloneMapState.recalculate();
                        float f5 = f;
                        float f6 = f2;
                        float abs = Math.abs(f5);
                        float abs2 = Math.abs(f6);
                        if ((abs > abs2 ? abs : abs2) <= 12000) {
                            f3 = f5;
                            f4 = f6;
                        } else if (abs > abs2) {
                            if (f5 > 0.0f) {
                                f3 = 12000;
                            } else {
                                f3 = -12000;
                            }
                            f4 = f6 * (12000 / abs);
                        } else {
                            f3 = (12000 / abs2) * f5;
                            f4 = f6 > 0.0f ? 12000 : -12000;
                        }
                        ADGLMapAnimFling aDGLMapAnimFling = new ADGLMapAnimFling(500, GLMapEngine.this.mGlMapView.getMapWidth() / 2, GLMapEngine.this.mGlMapView.getMapHeight() / 2);
                        aDGLMapAnimFling.setPositionAndVelocity(f3, f4);
                        aDGLMapAnimFling.commitAnimation(cloneMapState);
                        GLMapEngine.this.map_anims_mgr.addAnimation(aDGLMapAnimFling);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }
        });
    }

    public void FinishAnimations(final int i, boolean z) {
        if (z) {
            nativeFinishAnimations(i, this.mNativeMapengineInstance);
        } else if (this.mapcoreListener != null) {
            this.mapcoreListener.postQueueEvent(new Runnable() { // from class: com.autonavi.ae.gmap.GLMapEngine.10
                @Override // java.lang.Runnable
                public void run() {
                    GLMapEngine.nativeFinishAnimations(i, GLMapEngine.this.mNativeMapengineInstance);
                }
            });
        }
    }

    public void setOvelayBundle(int i, GLOverlayBundle<BaseMapOverlay<?, ?>> gLOverlayBundle) {
        if (this.mStateSparseArray.get(i) != null && i >= 0) {
            this.mStateSparseArray.get(i).mOverlayBundle = gLOverlayBundle;
        }
    }

    public void addOverlayTexture(int i, GLTextureProperty gLTextureProperty) {
        if (gLTextureProperty != null && gLTextureProperty.mBitmap != null && !gLTextureProperty.mBitmap.isRecycled()) {
            nativeAddOverlayTexture(i, this.mNativeMapengineInstance, gLTextureProperty.mId, gLTextureProperty.mAnchor, gLTextureProperty.mXRatio, gLTextureProperty.mYRatio, gLTextureProperty.mBitmap, gLTextureProperty.isGenMimps, gLTextureProperty.isRepeat);
        }
    }

    public GLOverlayBundle<BaseMapOverlay<?, ?>> getOvelayBundle(int i) {
        if (this.mStateSparseArray.get(i) == null || i < 0) {
            return null;
        }
        return this.mStateSparseArray.get(i).mOverlayBundle;
    }

    public long getGlOverlayMgrPtr(int i) {
        return nativeGetGlOverlayMgrPtr(i, this.mNativeMapengineInstance);
    }

    public long getMapEnginePtr() {
        return this.mNativeMapengineInstance;
    }

    public long createOverlay(int i, int i2) {
        return nativeCreateOverlay(i, this.mNativeMapengineInstance, i2);
    }

    public static void destoryOverlay(int i, long j) {
        nativeDestoryOverlay(i, j);
    }

    public synchronized void setParamater(int i, int i2, int i3, int i4, int i5, int i6) {
        if (this.mNativeMapengineInstance != 0) {
            nativeSetParmater(i, this.mNativeMapengineInstance, i2, i3, i4, i5, i6);
        }
    }

    public void addPoiFilter(int i, int i2, int i3, int i4, float f, float f2, String str, int i5) {
        if (this.mNativeMapengineInstance != 0) {
            nativeAddPoiFilter(i, this.mNativeMapengineInstance, i2, i3, i4, f, f2, 3.0f, 20.0f, str, i5);
        }
    }

    public void removePoiFilter(int i, String str) {
        if (this.mNativeMapengineInstance != 0) {
            nativeRemovePoiFilter(i, this.mNativeMapengineInstance, str);
        }
    }

    public void clearPoiFilter(int i) {
        if (this.mNativeMapengineInstance != 0) {
            nativeClearPoiFilter(i, this.mNativeMapengineInstance);
        }
    }

    public void setSearchedSubwayIds(int i, String[] strArr) {
        if (strArr != null && this.mNativeMapengineInstance != 0) {
            nativeSetSearchedSubwayIds(i, this.mNativeMapengineInstance, strArr);
        }
    }

    public void setNaviRouteBoardDataListener(RouteBoardDataListener routeBoardDataListener) {
        this.mBoardDataListener = routeBoardDataListener;
    }

    @Override // com.autonavi.ae.gmap.callback.JniMapCoreCallback
    public void onRoadTips(int i, byte[] bArr) {
        if (this.mBoardDataListener != null && bArr != null && bArr.length > 0) {
            this.mBoardDataListener.setRouteBoardData(i, bArr);
        }
    }

    @Override // com.autonavi.ae.gmap.callback.JniMapCoreCallback
    public void onOfflineMap(int i, String str, int i2) {
        if (str.length() != 0 && this.mMapListener != null) {
            this.mMapListener.onOfflineMap(i, str, i2);
        }
    }

    public void setScenicWidgetFilter(int i, ScenicWidgetItem scenicWidgetItem) {
        if (scenicWidgetItem == null) {
            nativeSetScenicFilter(i, this.mNativeMapengineInstance, 0, null);
            return;
        }
        int[] iArr = new int[scenicWidgetItem.mFilters.length * 3];
        for (int i2 = 0; i2 < scenicWidgetItem.mFilters.length; i2++) {
            iArr[i2 * 3] = scenicWidgetItem.mFilters[i2].mMainKey;
            iArr[(i2 * 3) + 1] = scenicWidgetItem.mFilters[i2].mSubKey;
            iArr[(i2 * 3) + 2] = scenicWidgetItem.mFilters[i2].mPoiFlag;
        }
        nativeSetScenicFilter(i, this.mNativeMapengineInstance, scenicWidgetItem.mItemIndex, iArr);
    }

    public void putScenicData(int i, byte[] bArr, int i2, String str) {
        synchronized (this.mut_lock) {
            if (this.mNativeMapengineInstance != 0) {
                byte[] bytes = str.getBytes("utf-8");
                byte length = (byte) bytes.length;
                byte[] bArr2 = new byte[length + i2 + 1];
                bArr2[0] = length;
                System.arraycopy(bytes, 0, bArr2, 1, length);
                System.arraycopy(bArr, 0, bArr2, length + 1, i2);
                nativePutMapdata(i, this.mNativeMapengineInstance, 101, bArr2, 0);
            }
        }
    }

    public void setShowMask(int i, boolean z) {
        nativeSetShowMask(i, this.mNativeMapengineInstance, z);
    }

    public boolean isShowMask(int i) {
        return nativeIsShowMask(i, this.mNativeMapengineInstance);
    }

    public void setMaskColor(int i, int i2) {
        nativeSetMaskColor(i, this.mNativeMapengineInstance, i2);
    }

    public boolean isShowLandMarkBuildingPoi(int i) {
        return nativeIsShowLandMarkBuildingPoi(i, this.mNativeMapengineInstance);
    }

    public void setShowFeatureSpotIcon(boolean z) {
    }

    public boolean isShowFeatureSpotIcon(int i) {
        return nativeIsShowFeatureSpotIcon(i, this.mNativeMapengineInstance);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
    @Override // com.autonavi.ae.gmap.callback.JniScenicCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onScenicActive(final int r9, byte[] r10) {
        /*
            r8 = this;
            r2 = 1
            r3 = 0
            r1 = 0
            if (r10 == 0) goto L_0x007a
            r0 = 0
            r4 = 1
            byte r5 = r10[r0]     // Catch: Throwable -> 0x0070
            com.autonavi.ae.gmap.scenic.ScenicInfor r0 = new com.autonavi.ae.gmap.scenic.ScenicInfor     // Catch: Throwable -> 0x0070
            r0.<init>()     // Catch: Throwable -> 0x0070
            java.lang.String r1 = new java.lang.String     // Catch: Throwable -> 0x0078
            java.lang.String r6 = "utf-8"
            r1.<init>(r10, r4, r5, r6)     // Catch: Throwable -> 0x0078
            r0.mAoiId = r1     // Catch: Throwable -> 0x0078
            int r1 = r5 + 1
            int r4 = r1 + 1
            byte r1 = r10[r1]     // Catch: Throwable -> 0x0078
            if (r1 == 0) goto L_0x0064
            r1 = r2
        L_0x0020:
            r0.mHasWidget = r1     // Catch: Throwable -> 0x0078
            int r5 = r4 + 1
            byte r1 = r10[r4]     // Catch: Throwable -> 0x0078
            if (r1 == 0) goto L_0x0066
            r1 = r2
        L_0x0029:
            r0.mHasGuideMap = r1     // Catch: Throwable -> 0x0078
            int r4 = r5 + 1
            byte r1 = r10[r5]     // Catch: Throwable -> 0x0078
            if (r1 == 0) goto L_0x0068
            r1 = r2
        L_0x0032:
            r0.mHasGuideVoice = r1     // Catch: Throwable -> 0x0078
            int r5 = r4 + 1
            byte r1 = r10[r4]     // Catch: Throwable -> 0x0078
            if (r1 == 0) goto L_0x006a
            r1 = r2
        L_0x003b:
            r0.mHasFootPrint = r1     // Catch: Throwable -> 0x0078
            int r4 = r5 + 1
            byte r1 = r10[r5]     // Catch: Throwable -> 0x0078
            if (r1 == 0) goto L_0x006c
            r1 = r2
        L_0x0044:
            r0.mHasThermal = r1     // Catch: Throwable -> 0x0078
            int r5 = r4 + 1
            byte r1 = r10[r4]     // Catch: Throwable -> 0x0078
            if (r1 == 0) goto L_0x006e
            r1 = r2
        L_0x004d:
            r0.mHasRoute = r1     // Catch: Throwable -> 0x0078
            int r1 = r5 + 1
            byte r1 = r10[r5]     // Catch: Throwable -> 0x0078
            r0.mRouteNumber = r1     // Catch: Throwable -> 0x0078
        L_0x0055:
            com.autonavi.ae.gmap.callback.GLMapCoreCallback r1 = r8.mapcoreListener
            if (r1 == 0) goto L_0x0063
            com.autonavi.ae.gmap.callback.GLMapCoreCallback r1 = r8.mapcoreListener
            com.autonavi.ae.gmap.GLMapEngine$11 r2 = new com.autonavi.ae.gmap.GLMapEngine$11
            r2.<init>()
            r1.postOnUIThread(r2)
        L_0x0063:
            return
        L_0x0064:
            r1 = r3
            goto L_0x0020
        L_0x0066:
            r1 = r3
            goto L_0x0029
        L_0x0068:
            r1 = r3
            goto L_0x0032
        L_0x006a:
            r1 = r3
            goto L_0x003b
        L_0x006c:
            r1 = r3
            goto L_0x0044
        L_0x006e:
            r1 = r3
            goto L_0x004d
        L_0x0070:
            r0 = move-exception
            r7 = r0
            r0 = r1
            r1 = r7
        L_0x0074:
            r1.printStackTrace()
            goto L_0x0055
        L_0x0078:
            r1 = move-exception
            goto L_0x0074
        L_0x007a:
            r0 = r1
            goto L_0x0055
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.ae.gmap.GLMapEngine.onScenicActive(int, byte[]):void");
    }

    public void setScenicListener(ScenicListener scenicListener) {
        this.mScenicListener = scenicListener;
    }

    public void setScenicGuideEnable(int i, boolean z) {
        setParamater(i, GLMapStaticValue.AM_PARAMETERNAME_PROCESS_GUIDE, z ? 1 : 0, 0, 0, 0);
    }

    public void setMapListener(IAMapListener iAMapListener) {
        this.mAMapListener = iAMapListener;
    }

    public void setMapHeatListener(MapHeatListener mapHeatListener) {
        this.mMapHeatListener = mapHeatListener;
    }

    public void setMapHeatEnable(int i, boolean z) {
        setParamater(i, GLMapStaticValue.AM_PARAMETERNAME_MAP_HEAT, z ? 1 : 0, 0, 0, 0);
    }

    public void setMapHeatPoiRegion(int i, String str, int[] iArr, int[] iArr2) {
        nativeSetMapHeatPoiRegion(i, this.mNativeMapengineInstance, str, iArr, iArr2);
    }

    public void addLabels3rd(int i, int i2, Label3rd[] label3rdArr, boolean z) {
        nativeAddLabels3rd(i, this.mNativeMapengineInstance, i2, label3rdArr, z);
    }

    public void clearLabels3rd(int i, int i2, boolean z) {
        nativeClearLabels3rd(i, this.mNativeMapengineInstance, i2, z);
    }

    @Override // com.autonavi.ae.gmap.callback.JniMapHeatCallback
    public void onMapHeatActive(int i, boolean z) {
        if (this.mMapHeatListener != null) {
            this.mMapHeatListener.onMapHeatActive(i, z);
        }
    }

    public boolean doMapDataControl(int i, int i2, int i3, int i4, int i5) {
        if (this.mNativeMapengineInstance != 0 && i2 == 1 && i3 == 2 && i5 == 1) {
            new MessageDataThread(i, this, i2, i3, i4, i5).start();
        }
        return true;
    }

    public void setMapDataConrol(final int i, final int i2, final int i3, final int i4, final int i5, final byte[] bArr) {
        if (this.mNativeMapengineInstance != 0 && this.mapcoreListener != null) {
            this.mapcoreListener.postQueueEvent(new Runnable() { // from class: com.autonavi.ae.gmap.GLMapEngine.12
                @Override // java.lang.Runnable
                public void run() {
                    GLMapEngine.nativeMapDataControl(i, GLMapEngine.this.mNativeMapengineInstance, i2, i3, i4, i5, bArr);
                }
            });
        }
    }

    @Override // com.autonavi.ae.gmap.callback.JniMapCoreCallback
    public void onTransferParam(int i, int[] iArr) {
        if (iArr != null) {
            switch (iArr[0]) {
                case 10000:
                    if (this.mapcoreListener != null) {
                        this.mapcoreListener.resetRenderTime(false);
                        return;
                    }
                    return;
                case 10001:
                    if (iArr[1] == 0) {
                    }
                    return;
                case GLMapStaticValue.AM_CALLBACK_NEED_NEXTFRAME /* 10002 */:
                    if (this.mapcoreListener != null) {
                        switch (iArr[1]) {
                            case 2:
                                this.mapcoreListener.resetRenderTimeLong();
                                return;
                            case 3:
                                this.mapcoreListener.resetRenderTimeLongLong();
                                return;
                            default:
                                this.mapcoreListener.resetRenderTime(false);
                                return;
                        }
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    }

    @Override // com.autonavi.ae.gmap.callback.JniMapCoreCallback
    public void onOpenLayerDataRequired(int i, String str, int i2, int i3, int i4, String[] strArr) {
        OpenLayerMapLoader openLayerMapLoader = new OpenLayerMapLoader(i, this, str, i2, i3, i4);
        if (openLayerMapLoader.createDownUrl(strArr) && this.mConnectionManager != null) {
            openLayerMapLoader.setGetRequestMethod(false);
            this.mConnectionManager.insertConntionTask(openLayerMapLoader);
        }
    }

    @Override // com.autonavi.ae.gmap.callback.JniMapCoreCallback
    public void onLoadTextureByName(int i, String str, int i2) {
    }

    @Override // com.autonavi.ae.gmap.callback.JniMapCoreCallback
    public byte[] OnMapLoadResourceByName(int i, String str) {
        byte[] bArr = null;
        if (str == null) {
            return null;
        }
        if (this.mGlMapView.getMapConfig().isCustomStyleEnable()) {
            if (str.startsWith("icons_5")) {
                bArr = getMapCacheMgr().getOtherResDataFromDisk(this.mGlMapView.getMapConfig().getCustomTextureResourcePath());
            } else if (str.startsWith("bktile")) {
                bArr = getMapCacheMgr().getOtherResData(str);
                int customBackgroundColor = this.mGlMapView.getMapConfig().getCustomBackgroundColor();
                if (customBackgroundColor != 0) {
                    bArr = writeCustomBackground(bArr, customBackgroundColor);
                }
            }
            if (bArr != null) {
                return bArr;
            }
        }
        return getMapCacheMgr().getOtherResData(str);
    }

    public byte[] writeCustomBackground(byte[] bArr, int i) {
        try {
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
            Bitmap copy = decodeByteArray.copy(decodeByteArray.getConfig(), true);
            int width = decodeByteArray.getWidth();
            int height = decodeByteArray.getHeight();
            for (int i2 = 1; i2 < width; i2++) {
                for (int i3 = 1; i3 < height; i3++) {
                    copy.setPixel(i2, i3, i);
                }
            }
            byte[] bitmap2bytes = bitmap2bytes(copy);
            if (bitmap2bytes == null) {
                bitmap2bytes = bArr;
            }
            copy.recycle();
            decodeByteArray.recycle();
            return bitmap2bytes;
        } catch (Throwable th) {
            th.printStackTrace();
            return bArr;
        }
    }

    private byte[] bitmap2bytes(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        byte[] bArr = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (Throwable th2) {
            byteArrayOutputStream = null;
            th = th2;
        }
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            bArr = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th3) {
                th3.printStackTrace();
            }
        } catch (Throwable th4) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th5) {
                th5.printStackTrace();
            }
            return bArr;
        }
        return bArr;
    }

    @Override // com.autonavi.ae.gmap.callback.JniMapCoreCallback
    public void OnMapResourceRequired(int i, String str, int i2) {
        if (!TextUtils.isEmpty(str)) {
            MapTilsCacheAndResManager.RetStyleIconsFile retStyleIconsFile = new MapTilsCacheAndResManager.RetStyleIconsFile();
            if (this.mCacheMgr != null) {
                retStyleIconsFile.fullName = str;
                String[] split = str.split("_|\\.");
                if (split.length >= 2) {
                    retStyleIconsFile.name = split[0] + "_" + split[1];
                    try {
                        retStyleIconsFile.clientVersion = Integer.parseInt(split[2]);
                        if (split.length > 3) {
                            retStyleIconsFile.serverVersion = Integer.parseInt(split[3]);
                        } else {
                            retStyleIconsFile.serverVersion = 1;
                        }
                    } catch (NumberFormatException e) {
                        retStyleIconsFile.clientVersion = 1;
                        retStyleIconsFile.serverVersion = 1;
                    }
                    retStyleIconsFile.type = i2;
                    new StylesIconsUpdate(i, this, retStyleIconsFile).start(getMapSvrAddress() + "/ws/mps/vmap?");
                }
            }
        }
    }

    @Override // com.autonavi.ae.gmap.callback.JniMapCoreCallback
    public void OnMapResourceReLoad(int i, String str, int i2) {
        UpdateStyleOrIcons(i, str, i2);
    }

    public void appendOpenLayer(int i, byte[] bArr) {
        if (this.mNativeMapengineInstance != 0) {
            nativeAppendOpenLayer(i, this.mNativeMapengineInstance, bArr);
        }
    }

    public void deleteOpenLayer(int i, int i2) {
        if (this.mNativeMapengineInstance != 0) {
            nativeDeleteOpenLayer(i, this.mNativeMapengineInstance, i2);
        }
    }

    public void onClearOverlayTexture(int i) {
        nativeClearOverlayTexture(i, this.mNativeMapengineInstance);
    }

    public int IsSupportRealcity(int i, int i2) {
        return nativeIsSupportRealcity(i, this.mNativeMapengineInstance, i2);
    }

    public String GetRealCityDataVerSion(int i, int i2) {
        return nativeGetRealCityDataVerSion(i, this.mNativeMapengineInstance, i2);
    }

    public int IsRealCityAnimateFinish(int i) {
        return nativeIsRealCityAnimateFinish(i, this.mNativeMapengineInstance);
    }

    public boolean SetMapModeAndStyle(int i, int i2, int i3, int i4, boolean z, boolean z2, StyleItem[] styleItemArr) {
        return nativeSetMapModeAndStyle(i, this.mNativeMapengineInstance, new int[]{i2, i3, i4, 0, 0}, z, z2, styleItemArr);
    }

    public boolean IsSkinExist(int i, int i2, int i3, int i4, int i5, int i6) {
        return nativeIsSkinExist(i, this.mNativeMapengineInstance, new int[]{i2, i3, i4, i5, i6});
    }

    public void createEngineWithFrame(int i, Rect rect, int i2, int i3) {
        if (rect != null) {
            if (i == 2 && this.mEagleEyeScreenGirds == null) {
                this.mEagleEyeScreenGirds = new ArrayList();
            }
            if (this.mCacheMgr == null) {
                this.mCacheMgr = new MapTilsCacheAndResManagerImpl(this.mContext, nativeGetMapCachePath(i, this.mNativeMapengineInstance));
                this.mCacheMgr.checkDir();
            }
            nativeCreateEngineWithFrame(i, this.mNativeMapengineInstance, rect.left, rect.top, rect.width(), rect.height(), i2, i3, false);
            this.mStateSparseArray.put(i, new GLStateInstance(i, nativeGetMapStateInstance(i, this.mNativeMapengineInstance)));
        }
    }

    public void removeEngine(int i) {
        nativeRemoveEngine(i, this.mNativeMapengineInstance);
        if (this.mStateSparseArray != null) {
            this.mStateSparseArray.remove(i);
        }
    }

    public void setEngineVisible(int i, boolean z) {
        nativeSetServiceViewVisible(i, z, this.mNativeMapengineInstance);
    }

    public int[] getEngineIDs() {
        return nativeGetEngineIDArray(this.mNativeMapengineInstance);
    }

    public int getEngineIDWithType(int i) {
        switch (i) {
            case 0:
            default:
                return 1;
            case 1:
                return 2;
        }
    }

    public boolean isEngineCreated(int i) {
        int[] engineIDs = getEngineIDs();
        if (engineIDs == null || engineIDs.length <= 0) {
            return false;
        }
        for (int i2 : engineIDs) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    private long getStateInstanceWithEngineID(int i) {
        if (this.mStateSparseArray.get(i) == null || i < 0) {
            return 0L;
        }
        return this.mStateSparseArray.get(i).getStateInstance();
    }

    public int getEngineIDWithGestureInfo(EAMapPlatformGestureInfo eAMapPlatformGestureInfo) {
        if (this.mNativeMapengineInstance != 0) {
            return nativeGetEngineIDWithGestureInfo(this.mNativeMapengineInstance, eAMapPlatformGestureInfo);
        }
        return 1;
    }

    public void setBackGroundColor(int i, float f, float f2, float f3, float f4) {
        nativeSetBackGroundColor(i, this.mNativeMapengineInstance, f, f2, f3, f4);
    }

    public void setServiceViewRect(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        nativeSetServiceViewRect(i, this.mNativeMapengineInstance, i2, i3, i4, i5, i6, i7);
    }

    public boolean getSrvViewStateBoolValue(int i, int i2) {
        return nativeGetSrvViewStateBoolValue(i, this.mNativeMapengineInstance, i2);
    }

    public int getSrvViewStateIntValue(int i, int i2) {
        return nativeGetSrvViewStateIntValue(i, this.mNativeMapengineInstance, i2);
    }

    public float getSrvViewStateFloatValue(int i, int i2) {
        return nativeGetSrvViewStateFloatValue(i, this.mNativeMapengineInstance, i2);
    }

    public void setSrvViewStateBoolValue(int i, int i2, boolean z) {
        nativeSetSrvViewStateBoolValue(i, this.mNativeMapengineInstance, i2, z);
    }

    public void setSrvViewStateIntValue(int i, int i2, int i3) {
        nativeSetSrvViewStateIntValue(i, this.mNativeMapengineInstance, i2, i3);
    }

    public void setSrvViewStateFloatValue(int i, int i2, float f) {
        nativeSetSrvViewStateFloatValue(i, this.mNativeMapengineInstance, i2, f);
    }

    public int[] getMapModeState(int i, boolean z) {
        return nativeGetMapModeState(i, this.mNativeMapengineInstance, z);
    }

    public boolean getIsProcessBuildingMark(int i) {
        return nativeGetIsProcessBuildingMark(i, this.mNativeMapengineInstance);
    }

    @Override // com.autonavi.ae.gmap.callback.JniMapCoreCallback
    public void OnMapAnimationFinished(int i, int i2) {
        if (this.mMapListener != null && -1 != i2) {
            this.mMapListener.onMapAnimationFinished(i, i2);
        }
    }

    public void onLogReport(int i, int i2, int i3, String str) {
    }

    public void onLogOfflineDataStatusReport(int i, String str, String str2, String str3) {
    }
}
