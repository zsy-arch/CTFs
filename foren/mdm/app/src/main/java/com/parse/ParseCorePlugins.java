package com.parse;

import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes2.dex */
public class ParseCorePlugins {
    static final String FILENAME_CURRENT_CONFIG = "currentConfig";
    static final String FILENAME_CURRENT_INSTALLATION = "currentInstallation";
    static final String FILENAME_CURRENT_USER = "currentUser";
    private static final ParseCorePlugins INSTANCE = new ParseCorePlugins();
    static final String PIN_CURRENT_INSTALLATION = "_currentInstallation";
    static final String PIN_CURRENT_USER = "_currentUser";
    private AtomicReference<ParseObjectController> objectController = new AtomicReference<>();
    private AtomicReference<ParseUserController> userController = new AtomicReference<>();
    private AtomicReference<ParseSessionController> sessionController = new AtomicReference<>();
    private AtomicReference<ParseCurrentUserController> currentUserController = new AtomicReference<>();
    private AtomicReference<ParseCurrentInstallationController> currentInstallationController = new AtomicReference<>();
    private AtomicReference<ParseAuthenticationManager> authenticationController = new AtomicReference<>();
    private AtomicReference<ParseQueryController> queryController = new AtomicReference<>();
    private AtomicReference<ParseFileController> fileController = new AtomicReference<>();
    private AtomicReference<ParseAnalyticsController> analyticsController = new AtomicReference<>();
    private AtomicReference<ParseCloudCodeController> cloudCodeController = new AtomicReference<>();
    private AtomicReference<ParseConfigController> configController = new AtomicReference<>();
    private AtomicReference<ParsePushController> pushController = new AtomicReference<>();
    private AtomicReference<ParsePushChannelsController> pushChannelsController = new AtomicReference<>();
    private AtomicReference<ParseDefaultACLController> defaultACLController = new AtomicReference<>();
    private AtomicReference<LocalIdManager> localIdManager = new AtomicReference<>();
    private AtomicReference<ParseObjectSubclassingController> subclassingController = new AtomicReference<>();

    public static ParseCorePlugins getInstance() {
        return INSTANCE;
    }

    private ParseCorePlugins() {
    }

    public void reset() {
        this.objectController.set(null);
        this.userController.set(null);
        this.sessionController.set(null);
        this.currentUserController.set(null);
        this.currentInstallationController.set(null);
        this.authenticationController.set(null);
        this.queryController.set(null);
        this.fileController.set(null);
        this.analyticsController.set(null);
        this.cloudCodeController.set(null);
        this.configController.set(null);
        this.pushController.set(null);
        this.pushChannelsController.set(null);
        this.defaultACLController.set(null);
        this.localIdManager.set(null);
    }

    public ParseObjectController getObjectController() {
        if (this.objectController.get() == null) {
            this.objectController.compareAndSet(null, new NetworkObjectController(ParsePlugins.get().restClient()));
        }
        return this.objectController.get();
    }

    public void registerObjectController(ParseObjectController controller) {
        if (!this.objectController.compareAndSet(null, controller)) {
            throw new IllegalStateException("Another object controller was already registered: " + this.objectController.get());
        }
    }

    public ParseUserController getUserController() {
        if (this.userController.get() == null) {
            this.userController.compareAndSet(null, new NetworkUserController(ParsePlugins.get().restClient()));
        }
        return this.userController.get();
    }

    public void registerUserController(ParseUserController controller) {
        if (!this.userController.compareAndSet(null, controller)) {
            throw new IllegalStateException("Another user controller was already registered: " + this.userController.get());
        }
    }

    public ParseSessionController getSessionController() {
        if (this.sessionController.get() == null) {
            this.sessionController.compareAndSet(null, new NetworkSessionController(ParsePlugins.get().restClient()));
        }
        return this.sessionController.get();
    }

    public void registerSessionController(ParseSessionController controller) {
        if (!this.sessionController.compareAndSet(null, controller)) {
            throw new IllegalStateException("Another session controller was already registered: " + this.sessionController.get());
        }
    }

    public ParseCurrentUserController getCurrentUserController() {
        if (this.currentUserController.get() == null) {
            ParseObjectStore<ParseUser> fileStore = new FileObjectStore<>(ParseUser.class, new File(Parse.getParseDir(), FILENAME_CURRENT_USER), ParseUserCurrentCoder.get());
            this.currentUserController.compareAndSet(null, new CachedCurrentUserController(Parse.isLocalDatastoreEnabled() ? new OfflineObjectStore<>(ParseUser.class, PIN_CURRENT_USER, fileStore) : fileStore));
        }
        return this.currentUserController.get();
    }

    public void registerCurrentUserController(ParseCurrentUserController controller) {
        if (!this.currentUserController.compareAndSet(null, controller)) {
            throw new IllegalStateException("Another currentUser controller was already registered: " + this.currentUserController.get());
        }
    }

    public ParseQueryController getQueryController() {
        ParseQueryController controller;
        if (this.queryController.get() == null) {
            NetworkQueryController networkController = new NetworkQueryController(ParsePlugins.get().restClient());
            if (Parse.isLocalDatastoreEnabled()) {
                controller = new OfflineQueryController(Parse.getLocalDatastore(), networkController);
            } else {
                controller = new CacheQueryController(networkController);
            }
            this.queryController.compareAndSet(null, controller);
        }
        return this.queryController.get();
    }

    public void registerQueryController(ParseQueryController controller) {
        if (!this.queryController.compareAndSet(null, controller)) {
            throw new IllegalStateException("Another query controller was already registered: " + this.queryController.get());
        }
    }

    public ParseFileController getFileController() {
        if (this.fileController.get() == null) {
            this.fileController.compareAndSet(null, new ParseFileController(ParsePlugins.get().restClient(), Parse.getParseCacheDir("files")));
        }
        return this.fileController.get();
    }

    public void registerFileController(ParseFileController controller) {
        if (!this.fileController.compareAndSet(null, controller)) {
            throw new IllegalStateException("Another file controller was already registered: " + this.fileController.get());
        }
    }

    public ParseAnalyticsController getAnalyticsController() {
        if (this.analyticsController.get() == null) {
            this.analyticsController.compareAndSet(null, new ParseAnalyticsController(Parse.getEventuallyQueue()));
        }
        return this.analyticsController.get();
    }

    public void registerAnalyticsController(ParseAnalyticsController controller) {
        if (!this.analyticsController.compareAndSet(null, controller)) {
            throw new IllegalStateException("Another analytics controller was already registered: " + this.analyticsController.get());
        }
    }

    public ParseCloudCodeController getCloudCodeController() {
        if (this.cloudCodeController.get() == null) {
            this.cloudCodeController.compareAndSet(null, new ParseCloudCodeController(ParsePlugins.get().restClient()));
        }
        return this.cloudCodeController.get();
    }

    public void registerCloudCodeController(ParseCloudCodeController controller) {
        if (!this.cloudCodeController.compareAndSet(null, controller)) {
            throw new IllegalStateException("Another cloud code controller was already registered: " + this.cloudCodeController.get());
        }
    }

    public ParseConfigController getConfigController() {
        if (this.configController.get() == null) {
            this.configController.compareAndSet(null, new ParseConfigController(ParsePlugins.get().restClient(), new ParseCurrentConfigController(new File(ParsePlugins.get().getParseDir(), FILENAME_CURRENT_CONFIG))));
        }
        return this.configController.get();
    }

    public void registerConfigController(ParseConfigController controller) {
        if (!this.configController.compareAndSet(null, controller)) {
            throw new IllegalStateException("Another config controller was already registered: " + this.configController.get());
        }
    }

    public ParsePushController getPushController() {
        if (this.pushController.get() == null) {
            this.pushController.compareAndSet(null, new ParsePushController(ParsePlugins.get().restClient()));
        }
        return this.pushController.get();
    }

    public void registerPushController(ParsePushController controller) {
        if (!this.pushController.compareAndSet(null, controller)) {
            throw new IllegalStateException("Another push controller was already registered: " + this.pushController.get());
        }
    }

    public ParsePushChannelsController getPushChannelsController() {
        if (this.pushChannelsController.get() == null) {
            this.pushChannelsController.compareAndSet(null, new ParsePushChannelsController());
        }
        return this.pushChannelsController.get();
    }

    public void registerPushChannelsController(ParsePushChannelsController controller) {
        if (!this.pushChannelsController.compareAndSet(null, controller)) {
            throw new IllegalStateException("Another pushChannels controller was already registered: " + this.pushChannelsController.get());
        }
    }

    public ParseCurrentInstallationController getCurrentInstallationController() {
        if (this.currentInstallationController.get() == null) {
            ParseObjectStore<ParseInstallation> fileStore = new FileObjectStore<>(ParseInstallation.class, new File(ParsePlugins.get().getParseDir(), FILENAME_CURRENT_INSTALLATION), ParseObjectCurrentCoder.get());
            this.currentInstallationController.compareAndSet(null, new CachedCurrentInstallationController(Parse.isLocalDatastoreEnabled() ? new OfflineObjectStore<>(ParseInstallation.class, PIN_CURRENT_INSTALLATION, fileStore) : fileStore, ParsePlugins.get().installationId()));
        }
        return this.currentInstallationController.get();
    }

    public void registerCurrentInstallationController(ParseCurrentInstallationController controller) {
        if (!this.currentInstallationController.compareAndSet(null, controller)) {
            throw new IllegalStateException("Another currentInstallation controller was already registered: " + this.currentInstallationController.get());
        }
    }

    public ParseAuthenticationManager getAuthenticationManager() {
        if (this.authenticationController.get() == null) {
            this.authenticationController.compareAndSet(null, new ParseAuthenticationManager(getCurrentUserController()));
        }
        return this.authenticationController.get();
    }

    public void registerAuthenticationManager(ParseAuthenticationManager manager) {
        if (!this.authenticationController.compareAndSet(null, manager)) {
            throw new IllegalStateException("Another authentication manager was already registered: " + this.authenticationController.get());
        }
    }

    public ParseDefaultACLController getDefaultACLController() {
        if (this.defaultACLController.get() == null) {
            this.defaultACLController.compareAndSet(null, new ParseDefaultACLController());
        }
        return this.defaultACLController.get();
    }

    public void registerDefaultACLController(ParseDefaultACLController controller) {
        if (!this.defaultACLController.compareAndSet(null, controller)) {
            throw new IllegalStateException("Another defaultACL controller was already registered: " + this.defaultACLController.get());
        }
    }

    public LocalIdManager getLocalIdManager() {
        if (this.localIdManager.get() == null) {
            this.localIdManager.compareAndSet(null, new LocalIdManager(Parse.getParseDir()));
        }
        return this.localIdManager.get();
    }

    public void registerLocalIdManager(LocalIdManager manager) {
        if (!this.localIdManager.compareAndSet(null, manager)) {
            throw new IllegalStateException("Another localId manager was already registered: " + this.localIdManager.get());
        }
    }

    public ParseObjectSubclassingController getSubclassingController() {
        if (this.subclassingController.get() == null) {
            this.subclassingController.compareAndSet(null, new ParseObjectSubclassingController());
        }
        return this.subclassingController.get();
    }

    public void registerSubclassingController(ParseObjectSubclassingController controller) {
        if (!this.subclassingController.compareAndSet(null, controller)) {
            throw new IllegalStateException("Another subclassing controller was already registered: " + this.subclassingController.get());
        }
    }
}
