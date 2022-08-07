package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import com.bumptech.glide.RequestManager;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@TargetApi(11)
/* loaded from: classes.dex */
public class RequestManagerFragment extends Fragment {
    private final HashSet<RequestManagerFragment> childRequestManagerFragments;
    private final ActivityFragmentLifecycle lifecycle;
    private RequestManager requestManager;
    private final RequestManagerTreeNode requestManagerTreeNode;
    private RequestManagerFragment rootRequestManagerFragment;

    public RequestManagerFragment() {
        this(new ActivityFragmentLifecycle());
    }

    @SuppressLint({"ValidFragment"})
    RequestManagerFragment(ActivityFragmentLifecycle lifecycle) {
        this.requestManagerTreeNode = new FragmentRequestManagerTreeNode();
        this.childRequestManagerFragments = new HashSet<>();
        this.lifecycle = lifecycle;
    }

    public void setRequestManager(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ActivityFragmentLifecycle getLifecycle() {
        return this.lifecycle;
    }

    public RequestManager getRequestManager() {
        return this.requestManager;
    }

    public RequestManagerTreeNode getRequestManagerTreeNode() {
        return this.requestManagerTreeNode;
    }

    private void addChildRequestManagerFragment(RequestManagerFragment child) {
        this.childRequestManagerFragments.add(child);
    }

    private void removeChildRequestManagerFragment(RequestManagerFragment child) {
        this.childRequestManagerFragments.remove(child);
    }

    @TargetApi(17)
    public Set<RequestManagerFragment> getDescendantRequestManagerFragments() {
        if (this.rootRequestManagerFragment == this) {
            return Collections.unmodifiableSet(this.childRequestManagerFragments);
        }
        if (this.rootRequestManagerFragment == null || Build.VERSION.SDK_INT < 17) {
            return Collections.emptySet();
        }
        HashSet<RequestManagerFragment> descendants = new HashSet<>();
        for (RequestManagerFragment fragment : this.rootRequestManagerFragment.getDescendantRequestManagerFragments()) {
            if (isDescendant(fragment.getParentFragment())) {
                descendants.add(fragment);
            }
        }
        return Collections.unmodifiableSet(descendants);
    }

    @TargetApi(17)
    private boolean isDescendant(Fragment fragment) {
        Fragment root = getParentFragment();
        while (fragment.getParentFragment() != null) {
            if (fragment.getParentFragment() == root) {
                return true;
            }
            fragment = fragment.getParentFragment();
        }
        return false;
    }

    @Override // android.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.rootRequestManagerFragment = RequestManagerRetriever.get().getRequestManagerFragment(getActivity().getFragmentManager());
        if (this.rootRequestManagerFragment != this) {
            this.rootRequestManagerFragment.addChildRequestManagerFragment(this);
        }
    }

    @Override // android.app.Fragment
    public void onDetach() {
        super.onDetach();
        if (this.rootRequestManagerFragment != null) {
            this.rootRequestManagerFragment.removeChildRequestManagerFragment(this);
            this.rootRequestManagerFragment = null;
        }
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
        this.lifecycle.onStart();
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        this.lifecycle.onStop();
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.lifecycle.onDestroy();
    }

    @Override // android.app.Fragment, android.content.ComponentCallbacks2
    public void onTrimMemory(int level) {
        if (this.requestManager != null) {
            this.requestManager.onTrimMemory(level);
        }
    }

    @Override // android.app.Fragment, android.content.ComponentCallbacks
    public void onLowMemory() {
        if (this.requestManager != null) {
            this.requestManager.onLowMemory();
        }
    }

    /* loaded from: classes.dex */
    private class FragmentRequestManagerTreeNode implements RequestManagerTreeNode {
        private FragmentRequestManagerTreeNode() {
        }

        @Override // com.bumptech.glide.manager.RequestManagerTreeNode
        public Set<RequestManager> getDescendants() {
            Set<RequestManagerFragment> descendantFragments = RequestManagerFragment.this.getDescendantRequestManagerFragments();
            HashSet<RequestManager> descendants = new HashSet<>(descendantFragments.size());
            for (RequestManagerFragment fragment : descendantFragments) {
                if (fragment.getRequestManager() != null) {
                    descendants.add(fragment.getRequestManager());
                }
            }
            return descendants;
        }
    }
}
