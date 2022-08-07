package com.amap.api.navi;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.amap.api.col.fn;
import com.amap.api.col.fo;
import com.amap.api.col.fr;
import com.amap.api.col.gr;
import com.amap.api.navi.model.AMapLaneInfo;
import com.amap.api.navi.model.AMapModelCross;
import com.amap.api.navi.model.AMapNaviCameraInfo;
import com.amap.api.navi.model.AMapNaviCross;
import com.amap.api.navi.model.AMapNaviInfo;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.navi.model.AMapNaviTrafficFacilityInfo;
import com.amap.api.navi.model.AMapServiceAreaInfo;
import com.amap.api.navi.model.AimLessModeCongestionInfo;
import com.amap.api.navi.model.AimLessModeStat;
import com.amap.api.navi.model.AmapCarLocation;
import com.amap.api.navi.model.NaviInfo;
import com.amap.api.navi.view.AutoNaviHudMirrorImage;
import com.autonavi.tbt.TrafficFacilityInfo;

/* loaded from: classes.dex */
public class AMapHudView extends FrameLayout implements View.OnClickListener, View.OnTouchListener, MyNaviListener {
    private static final long delayMillis = 2000;
    static final int[] hud_imgActions = {1191313441, 1191313441, 1191313441, 1191313442, 1191313443, 1191313444, 1191313445, 1191313446, 1191313447, 1191313448, 1191313431, 1191313432, 1191313433, 1191313434, 1191313435, 1191313436, 1191313437, 1191313438, 1191313439, 1191313440};
    AMapHudViewListener aMapHudVewListener;
    private AutoNaviHudMirrorImage autonaviHudMirrorImage;
    private String distanceTimeTextStr;
    private View frameLayout;
    private TextView limitSpeedTextView;
    private INavi mAMapNavi;
    private View mHudMirrorTitle;
    private CheckBox mMirrorModeCheckBox;
    private TextView nextRoadDistanceText;
    private TextView nextRoadNameText;
    private String nextRoadNameTextStr;
    private int resId;
    private TextView restDistanceText;
    private String restDistanceTextStr;
    private TextView restDistanceTime;
    private ImageView roadsignimg;
    private View title_btn_goback;
    boolean isLandscape = false;
    private int mWidth = 480;
    private int mHeight = 800;
    private int hudMode = 1;
    private boolean isHudMenu = true;
    private Handler disappearHudHandler = new Handler();
    private Runnable disappearHudTitleRunnable = new Runnable() { // from class: com.amap.api.navi.AMapHudView.1
        @Override // java.lang.Runnable
        public void run() {
            try {
                try {
                    AMapHudView.this.loadHideHudTitleAnimation();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Throwable th) {
                th.printStackTrace();
                gr.b(th, "AMapHudView", "disappearHudTitleRunnable");
            }
        }
    };
    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.amap.api.navi.AMapHudView.2
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            try {
                if (AMapHudView.this.autonaviHudMirrorImage != null) {
                    if (z) {
                        AMapHudView.this.hudMode = 2;
                    } else {
                        AMapHudView.this.hudMode = 1;
                    }
                    AMapHudView.this.setCheckBoxAndMirrorImageState(z);
                    AMapHudView.this.removeCallbacks();
                    AMapHudView.this.disappearHudHandler.postDelayed(AMapHudView.this.disappearHudTitleRunnable, AMapHudView.delayMillis);
                }
            } catch (Throwable th) {
                th.printStackTrace();
                gr.b(th, "AMapHudView", "mOnCheckedChangeListener");
            }
        }
    };
    private SpannableString nextRoadDisTextSpannableStr = null;

    public AMapHudView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        try {
            init(context);
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "AMapHudView", "AMapHudView(Context context, AttributeSet attrs, int defStyle) ");
        }
    }

    public AMapHudView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        try {
            init(context);
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "AMapHudView", "AMapHudView(Context context, AttributeSet attrs) ");
        }
    }

    public AMapHudView(Context context) {
        super(context);
        init(context);
    }

    public int getHudViewMode() {
        return this.hudMode;
    }

    public void setHudViewMode(int i) {
        try {
            this.hudMode = i;
            setCheckBoxAndMirrorImageState(this.hudMode == 2);
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "AMapHudView", "setHudViewMode(int mode)");
        }
    }

    public boolean getHudMenuEnabled() {
        return this.isHudMenu;
    }

    public void setHudMenuEnabled(Boolean bool) {
        this.isHudMenu = bool.booleanValue();
    }

    public final void onCreate(Bundle bundle) {
    }

    public final void onResume() {
    }

    public final void onPause() {
    }

    public final void onDestroy() {
        try {
            if (this.autonaviHudMirrorImage != null) {
                this.autonaviHudMirrorImage.recycleMirrorBitmap();
            }
            this.aMapHudVewListener = null;
            if (this.disappearHudHandler != null) {
                this.disappearHudHandler.removeCallbacksAndMessages(null);
                this.disappearHudHandler = null;
            }
            this.mAMapNavi.destroy();
            fr.a(f.aG, "AmapHudView-->onDestory()");
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "AMapHudView", "onDestroy()");
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
    }

    public void setHudViewListener(AMapHudViewListener aMapHudViewListener) {
        this.aMapHudVewListener = aMapHudViewListener;
    }

    private void init(Context context) {
        try {
            this.mAMapNavi = AMapNavi.getInstance(context);
            this.isLandscape = ((Activity) getContext()).getRequestedOrientation() == 0 || getResources().getConfiguration().orientation == 2;
            if (this.isLandscape) {
                this.frameLayout = fo.a((Activity) getContext(), 1191378945, null);
            } else {
                this.frameLayout = fo.a((Activity) getContext(), 1191378944, null);
            }
            addView(this.frameLayout);
            initResolution();
            initWidget();
            this.mAMapNavi.addAMapNaviListener(this);
            onNaviInfoUpdate(this.mAMapNavi.getNaviInfo());
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "AMapHudView", "init(Context context)");
        }
    }

    private void initResolution() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        this.mWidth = displayMetrics.widthPixels;
        this.mHeight = displayMetrics.heightPixels;
    }

    public boolean onTouchHudMirrorEvent(MotionEvent motionEvent) {
        try {
            if (this.isHudMenu) {
                loadShowHudTitleAnimation();
                removeCallbacks();
                this.disappearHudHandler.postDelayed(this.disappearHudTitleRunnable, delayMillis);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "AMapHudView", "onTouchHudMirrorEvent(MotionEvent event)");
        }
        return true;
    }

    private void loadShowHudTitleAnimation() {
        if (this.mHudMirrorTitle != null && this.mHudMirrorTitle.getVisibility() == 8) {
            Animation a = fo.a(getContext(), 1191444480);
            this.mHudMirrorTitle.setVisibility(0);
            this.mHudMirrorTitle.startAnimation(a);
        }
    }

    private void initWidget() {
        this.mHudMirrorTitle = this.frameLayout.findViewById(1191772167);
        this.autonaviHudMirrorImage = (AutoNaviHudMirrorImage) this.frameLayout.findViewById(1191772160);
        this.mMirrorModeCheckBox = (CheckBox) this.frameLayout.findViewById(1191772169);
        this.nextRoadNameText = (TextView) this.frameLayout.findViewById(1191772161);
        this.restDistanceText = (TextView) this.frameLayout.findViewById(1191772164);
        this.roadsignimg = (ImageView) this.frameLayout.findViewById(1191772162);
        this.nextRoadDistanceText = (TextView) this.frameLayout.findViewById(1191772163);
        this.title_btn_goback = this.frameLayout.findViewById(1191772168);
        this.restDistanceTime = (TextView) this.frameLayout.findViewById(1191772165);
        this.limitSpeedTextView = (TextView) this.frameLayout.findViewById(1191772166);
        getScreenInfo();
        setWidgetListener();
        updateHudWidgetContent();
    }

    private void getScreenInfo() {
        if (this.autonaviHudMirrorImage != null) {
            this.autonaviHudMirrorImage.mWidth = this.mWidth;
            this.autonaviHudMirrorImage.mHeight = this.mHeight - 50;
        }
    }

    private void updateHudWidgetContent() {
        if (this.nextRoadNameText != null) {
            this.nextRoadNameText.setText(this.nextRoadNameTextStr);
        }
        if (this.nextRoadDistanceText != null) {
            this.nextRoadDistanceText.setText(this.nextRoadDisTextSpannableStr);
        }
        if (this.restDistanceText != null) {
            this.restDistanceText.setText(this.restDistanceTextStr);
        }
        if (this.restDistanceTime != null) {
            this.restDistanceTime.setText(this.distanceTimeTextStr);
        }
        if (this.roadsignimg != null && this.resId != 0 && this.resId != 1) {
            this.roadsignimg.setBackgroundDrawable(fo.a().getDrawable(hud_imgActions[this.resId]));
            if (this.autonaviHudMirrorImage != null) {
                this.autonaviHudMirrorImage.invalidate();
                this.autonaviHudMirrorImage.postInvalidate();
            }
        }
    }

    private void setWidgetListener() {
        if (this.autonaviHudMirrorImage != null) {
            this.autonaviHudMirrorImage.setAMapHudView(this);
            setOnTouchListener(this);
        }
        if (this.mMirrorModeCheckBox != null) {
            this.mMirrorModeCheckBox.setOnCheckedChangeListener(this.mOnCheckedChangeListener);
        }
        if (this.title_btn_goback != null) {
            this.title_btn_goback.setOnClickListener(this);
        }
    }

    public void removeCallbacks() {
        if (this.disappearHudHandler != null && this.disappearHudTitleRunnable != null) {
            this.disappearHudHandler.removeCallbacks(this.disappearHudTitleRunnable);
        }
    }

    public void setCheckBoxAndMirrorImageState(boolean z) {
        if (this.mMirrorModeCheckBox != null) {
            this.mMirrorModeCheckBox.setChecked(z);
        }
        if (this.autonaviHudMirrorImage != null) {
            this.autonaviHudMirrorImage.setMirrorState(z);
            this.autonaviHudMirrorImage.invalidate();
            this.autonaviHudMirrorImage.postInvalidate();
        }
    }

    public void loadHideHudTitleAnimation() {
        if (this.mHudMirrorTitle != null && this.mHudMirrorTitle.getVisibility() == 0) {
            Animation a = fo.a(getContext(), 1191444481);
            a.setAnimationListener(new Animation.AnimationListener() { // from class: com.amap.api.navi.AMapHudView.3
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    AMapHudView.this.mHudMirrorTitle.setVisibility(8);
                }
            });
            this.mHudMirrorTitle.startAnimation(a);
        }
    }

    private boolean isHudMirror() {
        return this.hudMode == 2;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        try {
            if (this.autonaviHudMirrorImage != null) {
                this.autonaviHudMirrorImage.recycleMirrorBitmap();
                this.autonaviHudMirrorImage = null;
            }
            removeAllViews();
            if (getResources().getConfiguration().orientation == 2) {
                this.frameLayout = fo.a((Activity) getContext(), 1191378945, null);
            } else {
                this.frameLayout = fo.a((Activity) getContext(), 1191378944, null);
            }
            addView(this.frameLayout);
            initResolution();
            initWidget();
            getScreenInfo();
            onNaviInfoUpdate(this.mAMapNavi.getNaviInfo());
            setCheckBoxAndMirrorImageState(isHudMirror());
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "AMapHudView", "onConfigurationChanged(Configuration newConfig)");
        }
        super.onConfigurationChanged(configuration);
    }

    private void updateHudUI(NaviInfo naviInfo) {
        if (naviInfo != null) {
            try {
                this.nextRoadNameTextStr = naviInfo.m_NextRoadName;
                this.restDistanceTextStr = fn.a(naviInfo.getPathRetainDistance());
                this.nextRoadDisTextSpannableStr = switchStrFromMeter(naviInfo.m_SegRemainDis);
                this.resId = naviInfo.m_Icon;
                this.distanceTimeTextStr = fn.b(naviInfo.m_RouteRemainTime);
                if (this.mAMapNavi.getNaviSetting().isMonitorCameraEnabled() && this.mAMapNavi.getEngineType() == 0 && naviInfo.getCameraDistance() > 0 && this.limitSpeedTextView != null && naviInfo.m_CameraSpeed > 0) {
                    this.limitSpeedTextView.setText("" + naviInfo.m_CameraSpeed);
                    this.limitSpeedTextView.setVisibility(0);
                } else if (naviInfo.m_CameraSpeed == 0 && this.limitSpeedTextView != null) {
                    this.limitSpeedTextView.setVisibility(8);
                }
                updateHudWidgetContent();
            } catch (Throwable th) {
                th.printStackTrace();
                gr.b(th, "AMapHudView", "updateHudUI(NaviInfo naviInfo)");
            }
        }
    }

    private SpannableString switchStrFromMeter(int i) {
        if (i < 1000) {
            return getSpanableString(getContext(), i + "", "米");
        }
        return getSpanableString(getContext(), (Math.round((i / 1000.0f) * 10.0f) / 10.0f) + "", "公里");
    }

    private SpannableString getSpanableString(Context context, String str, String str2) {
        SpannableString spannableString = new SpannableString(str + str2);
        int b = fn.b(context, 60);
        int b2 = fn.b(context, 30);
        int length = str.length();
        spannableString.setSpan(new AbsoluteSizeSpan(b), 0, length, 33);
        spannableString.setSpan(new ForegroundColorSpan(-1), 0, length, 33);
        int length2 = str2.length() + length;
        spannableString.setSpan(new AbsoluteSizeSpan(b2), length, length2, 33);
        spannableString.setSpan(new ForegroundColorSpan(-1), length, length2, 33);
        return spannableString;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            if (this.title_btn_goback == view && this.aMapHudVewListener != null) {
                this.aMapHudVewListener.onHudViewCancel();
            }
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "AMapHudView", "onClick(View v)");
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        try {
            onTouchHudMirrorEvent(motionEvent);
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "AMapHudView", "onTouch(View arg0, MotionEvent arg1)");
            return false;
        }
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onInitNaviFailure() {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onInitNaviSuccess() {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onStartNavi(int i) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onTrafficStatusUpdate() {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onGetNavigationText(int i, String str) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onEndEmulatorNavi() {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onArriveDestination() {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onCalculateRouteSuccess() {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onCalculateRouteFailure(int i) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onReCalculateRouteForYaw() {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onReCalculateRouteForTrafficJam() {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onArrivedWayPoint(int i) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onGpsOpenStatus(boolean z) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onNaviInfoUpdate(NaviInfo naviInfo) {
        try {
            updateHudUI(naviInfo);
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "AMapHudView", "onNaviInfoUpdate(NaviInfo naviinfo)");
        }
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onNaviInfoUpdated(AMapNaviInfo aMapNaviInfo) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void updateCameraInfo(AMapNaviCameraInfo[] aMapNaviCameraInfoArr) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onServiceAreaUpdate(AMapServiceAreaInfo[] aMapServiceAreaInfoArr) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void showCross(AMapNaviCross aMapNaviCross) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void hideCross() {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void showLaneInfo(AMapLaneInfo[] aMapLaneInfoArr, byte[] bArr, byte[] bArr2) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void hideLaneInfo() {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onCalculateMultipleRoutesSuccess(int[] iArr) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void notifyParallelRoad(int i) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo aMapNaviTrafficFacilityInfo) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo[] aMapNaviTrafficFacilityInfoArr) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void OnUpdateTrafficFacility(TrafficFacilityInfo trafficFacilityInfo) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void updateAimlessModeStatistics(AimLessModeStat aimLessModeStat) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void updateAimlessModeCongestionInfo(AimLessModeCongestionInfo aimLessModeCongestionInfo) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onPlayRing(int i) {
    }

    @Override // com.amap.api.navi.MyNaviListener
    public void carProjectionChange(AmapCarLocation amapCarLocation) {
    }

    @Override // com.amap.api.navi.MyNaviListener
    public void showModeCross(AMapModelCross aMapModelCross) {
    }

    @Override // com.amap.api.navi.MyNaviListener
    public void hideModeCross() {
    }
}
