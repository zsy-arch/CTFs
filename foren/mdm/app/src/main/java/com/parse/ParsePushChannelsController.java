package com.parse;

import bolts.Continuation;
import bolts.Task;
import java.util.Collections;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ParsePushChannelsController {
    private static final String TAG = "com.parse.ParsePushChannelsController";
    private static boolean loggedManifestError = false;

    private static ParseCurrentInstallationController getCurrentInstallationController() {
        return ParseCorePlugins.getInstance().getCurrentInstallationController();
    }

    public Task<Void> subscribeInBackground(final String channel) {
        checkManifestAndLogErrorIfNecessary();
        if (channel != null) {
            return getCurrentInstallationController().getAsync().onSuccessTask(new Continuation<ParseInstallation, Task<Void>>() { // from class: com.parse.ParsePushChannelsController.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // bolts.Continuation
                public Task<Void> then(Task<ParseInstallation> task) throws Exception {
                    ParseInstallation installation = task.getResult();
                    List<String> channels = installation.getList("channels");
                    if (channels != null && !installation.isDirty("channels") && channels.contains(channel)) {
                        return Task.forResult(null);
                    }
                    installation.addUnique("channels", channel);
                    return installation.saveInBackground();
                }
            });
        }
        throw new IllegalArgumentException("Can't subscribe to null channel.");
    }

    public Task<Void> unsubscribeInBackground(final String channel) {
        checkManifestAndLogErrorIfNecessary();
        if (channel != null) {
            return getCurrentInstallationController().getAsync().onSuccessTask(new Continuation<ParseInstallation, Task<Void>>() { // from class: com.parse.ParsePushChannelsController.2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // bolts.Continuation
                public Task<Void> then(Task<ParseInstallation> task) throws Exception {
                    ParseInstallation installation = task.getResult();
                    List<String> channels = installation.getList("channels");
                    if (channels == null || !channels.contains(channel)) {
                        return Task.forResult(null);
                    }
                    installation.removeAll("channels", Collections.singletonList(channel));
                    return installation.saveInBackground();
                }
            });
        }
        throw new IllegalArgumentException("Can't unsubscribe from null channel.");
    }

    private static void checkManifestAndLogErrorIfNecessary() {
        if (!loggedManifestError && ManifestInfo.getPushType() == PushType.NONE) {
            loggedManifestError = true;
            PLog.e(TAG, "Tried to subscribe or unsubscribe from a channel, but push is not enabled correctly. " + ManifestInfo.getNonePushTypeLogMessage());
        }
    }
}
