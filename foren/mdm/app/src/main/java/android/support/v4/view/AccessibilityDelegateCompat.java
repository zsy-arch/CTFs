package android.support.v4.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;

/* loaded from: classes.dex */
public class AccessibilityDelegateCompat {
    private static final View.AccessibilityDelegate DEFAULT_DELEGATE;
    private static final AccessibilityDelegateBaseImpl IMPL;
    final View.AccessibilityDelegate mBridge = IMPL.newAccessibilityDelegateBridge(this);

    /* loaded from: classes.dex */
    public static class AccessibilityDelegateBaseImpl {
        AccessibilityDelegateBaseImpl() {
        }

        public View.AccessibilityDelegate newAccessibilityDelegateBridge(final AccessibilityDelegateCompat compat) {
            return new View.AccessibilityDelegate() { // from class: android.support.v4.view.AccessibilityDelegateCompat.AccessibilityDelegateBaseImpl.1
                @Override // android.view.View.AccessibilityDelegate
                public boolean dispatchPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
                    return compat.dispatchPopulateAccessibilityEvent(host, event);
                }

                @Override // android.view.View.AccessibilityDelegate
                public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
                    compat.onInitializeAccessibilityEvent(host, event);
                }

                @Override // android.view.View.AccessibilityDelegate
                public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                    compat.onInitializeAccessibilityNodeInfo(host, AccessibilityNodeInfoCompat.wrap(info));
                }

                @Override // android.view.View.AccessibilityDelegate
                public void onPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
                    compat.onPopulateAccessibilityEvent(host, event);
                }

                @Override // android.view.View.AccessibilityDelegate
                public boolean onRequestSendAccessibilityEvent(ViewGroup host, View child, AccessibilityEvent event) {
                    return compat.onRequestSendAccessibilityEvent(host, child, event);
                }

                @Override // android.view.View.AccessibilityDelegate
                public void sendAccessibilityEvent(View host, int eventType) {
                    compat.sendAccessibilityEvent(host, eventType);
                }

                @Override // android.view.View.AccessibilityDelegate
                public void sendAccessibilityEventUnchecked(View host, AccessibilityEvent event) {
                    compat.sendAccessibilityEventUnchecked(host, event);
                }
            };
        }

        public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View.AccessibilityDelegate delegate, View host) {
            return null;
        }

        public boolean performAccessibilityAction(View.AccessibilityDelegate delegate, View host, int action, Bundle args) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(16)
    /* loaded from: classes.dex */
    public static class AccessibilityDelegateApi16Impl extends AccessibilityDelegateBaseImpl {
        AccessibilityDelegateApi16Impl() {
        }

        @Override // android.support.v4.view.AccessibilityDelegateCompat.AccessibilityDelegateBaseImpl
        public View.AccessibilityDelegate newAccessibilityDelegateBridge(final AccessibilityDelegateCompat compat) {
            return new View.AccessibilityDelegate() { // from class: android.support.v4.view.AccessibilityDelegateCompat.AccessibilityDelegateApi16Impl.1
                @Override // android.view.View.AccessibilityDelegate
                public boolean dispatchPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
                    return compat.dispatchPopulateAccessibilityEvent(host, event);
                }

                @Override // android.view.View.AccessibilityDelegate
                public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
                    compat.onInitializeAccessibilityEvent(host, event);
                }

                @Override // android.view.View.AccessibilityDelegate
                public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                    compat.onInitializeAccessibilityNodeInfo(host, AccessibilityNodeInfoCompat.wrap(info));
                }

                @Override // android.view.View.AccessibilityDelegate
                public void onPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
                    compat.onPopulateAccessibilityEvent(host, event);
                }

                @Override // android.view.View.AccessibilityDelegate
                public boolean onRequestSendAccessibilityEvent(ViewGroup host, View child, AccessibilityEvent event) {
                    return compat.onRequestSendAccessibilityEvent(host, child, event);
                }

                @Override // android.view.View.AccessibilityDelegate
                public void sendAccessibilityEvent(View host, int eventType) {
                    compat.sendAccessibilityEvent(host, eventType);
                }

                @Override // android.view.View.AccessibilityDelegate
                public void sendAccessibilityEventUnchecked(View host, AccessibilityEvent event) {
                    compat.sendAccessibilityEventUnchecked(host, event);
                }

                @Override // android.view.View.AccessibilityDelegate
                public AccessibilityNodeProvider getAccessibilityNodeProvider(View host) {
                    AccessibilityNodeProviderCompat provider = compat.getAccessibilityNodeProvider(host);
                    if (provider != null) {
                        return (AccessibilityNodeProvider) provider.getProvider();
                    }
                    return null;
                }

                @Override // android.view.View.AccessibilityDelegate
                public boolean performAccessibilityAction(View host, int action, Bundle args) {
                    return compat.performAccessibilityAction(host, action, args);
                }
            };
        }

        @Override // android.support.v4.view.AccessibilityDelegateCompat.AccessibilityDelegateBaseImpl
        public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View.AccessibilityDelegate delegate, View host) {
            AccessibilityNodeProvider provider = delegate.getAccessibilityNodeProvider(host);
            if (provider != null) {
                return new AccessibilityNodeProviderCompat(provider);
            }
            return null;
        }

        @Override // android.support.v4.view.AccessibilityDelegateCompat.AccessibilityDelegateBaseImpl
        public boolean performAccessibilityAction(View.AccessibilityDelegate delegate, View host, int action, Bundle args) {
            return delegate.performAccessibilityAction(host, action, args);
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 16) {
            IMPL = new AccessibilityDelegateApi16Impl();
        } else {
            IMPL = new AccessibilityDelegateBaseImpl();
        }
        DEFAULT_DELEGATE = new View.AccessibilityDelegate();
    }

    public View.AccessibilityDelegate getBridge() {
        return this.mBridge;
    }

    public void sendAccessibilityEvent(View host, int eventType) {
        DEFAULT_DELEGATE.sendAccessibilityEvent(host, eventType);
    }

    public void sendAccessibilityEventUnchecked(View host, AccessibilityEvent event) {
        DEFAULT_DELEGATE.sendAccessibilityEventUnchecked(host, event);
    }

    public boolean dispatchPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
        return DEFAULT_DELEGATE.dispatchPopulateAccessibilityEvent(host, event);
    }

    public void onPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
        DEFAULT_DELEGATE.onPopulateAccessibilityEvent(host, event);
    }

    public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
        DEFAULT_DELEGATE.onInitializeAccessibilityEvent(host, event);
    }

    public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
        DEFAULT_DELEGATE.onInitializeAccessibilityNodeInfo(host, info.unwrap());
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup host, View child, AccessibilityEvent event) {
        return DEFAULT_DELEGATE.onRequestSendAccessibilityEvent(host, child, event);
    }

    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View host) {
        return IMPL.getAccessibilityNodeProvider(DEFAULT_DELEGATE, host);
    }

    public boolean performAccessibilityAction(View host, int action, Bundle args) {
        return IMPL.performAccessibilityAction(DEFAULT_DELEGATE, host, action, args);
    }
}
