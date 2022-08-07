package com.autonavi.ae.gmap.glanimation;

import com.autonavi.ae.gmap.GLMapState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class ADGLMapAnimationMgr {
    private List<ADGLAnimation> list = Collections.synchronizedList(new ArrayList());

    public void setMapCoreListener() {
    }

    public synchronized void clearAnimations() {
        this.list.clear();
    }

    public synchronized int getAnimationsCount() {
        return this.list.size();
    }

    public synchronized void doAnimations(GLMapState gLMapState) {
        ADGLAnimation aDGLAnimation;
        if (gLMapState != null) {
            if (this.list.size() > 0 && (aDGLAnimation = this.list.get(0)) != null) {
                if (aDGLAnimation.isOver()) {
                    this.list.remove(aDGLAnimation);
                } else {
                    aDGLAnimation.doAnimation(gLMapState);
                }
            }
        }
    }

    public void addAnimation(ADGLAnimation aDGLAnimation) {
        ADGLAnimation aDGLAnimation2;
        if (aDGLAnimation != null) {
            synchronized (this.list) {
                if (!aDGLAnimation.isOver() && this.list.size() > 0 && (aDGLAnimation2 = this.list.get(this.list.size() - 1)) != null && (aDGLAnimation instanceof ADGLMapAnimGroup) && (aDGLAnimation2 instanceof ADGLMapAnimGroup) && ((ADGLMapAnimGroup) aDGLAnimation).typeEqueal((ADGLMapAnimGroup) aDGLAnimation2) && !((ADGLMapAnimGroup) aDGLAnimation)._needMove) {
                    this.list.remove(aDGLAnimation2);
                }
                this.list.add(aDGLAnimation);
            }
        }
    }
}
