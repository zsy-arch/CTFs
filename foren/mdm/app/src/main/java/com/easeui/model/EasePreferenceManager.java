package com.easeui.model;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import com.easeui.controller.EaseUI;
import java.util.Set;

/* loaded from: classes.dex */
public class EasePreferenceManager {
    private static final String KEY_AT_GROUPS = "AT_GROUPS";
    private static EasePreferenceManager instance;
    private SharedPreferences mSharedPreferences = EaseUI.getInstance().getContext().getSharedPreferences("EM_SP_AT_MESSAGE", 0);
    private SharedPreferences.Editor editor = this.mSharedPreferences.edit();

    @SuppressLint({"CommitPrefEdits"})
    private EasePreferenceManager() {
    }

    public static synchronized EasePreferenceManager getInstance() {
        EasePreferenceManager easePreferenceManager;
        synchronized (EasePreferenceManager.class) {
            if (instance == null) {
                instance = new EasePreferenceManager();
            }
            easePreferenceManager = instance;
        }
        return easePreferenceManager;
    }

    public void setAtMeGroups(Set<String> groups) {
        this.editor.remove(KEY_AT_GROUPS);
        this.editor.putStringSet(KEY_AT_GROUPS, groups);
        this.editor.apply();
    }

    public Set<String> getAtMeGroups() {
        return this.mSharedPreferences.getStringSet(KEY_AT_GROUPS, null);
    }
}
