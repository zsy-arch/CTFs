package com.parse;

import bolts.Task;
import com.parse.ParsePush;

/* loaded from: classes2.dex */
public class ParsePushController {
    static final String DEVICE_TYPE_ANDROID = "android";
    static final String DEVICE_TYPE_IOS = "ios";
    private final ParseHttpClient restClient;

    public ParsePushController(ParseHttpClient restClient) {
        this.restClient = restClient;
    }

    public Task<Void> sendInBackground(ParsePush.State state, String sessionToken) {
        return buildRESTSendPushCommand(state, sessionToken).executeAsync(this.restClient).makeVoid();
    }

    ParseRESTCommand buildRESTSendPushCommand(ParsePush.State state, String sessionToken) {
        boolean willPushToIOS;
        String deviceType = null;
        if (state.queryState() == null) {
            boolean willPushToAndroid = state.pushToAndroid() != null && state.pushToAndroid().booleanValue();
            if (state.pushToIOS() == null || !state.pushToIOS().booleanValue()) {
                willPushToIOS = false;
            } else {
                willPushToIOS = true;
            }
            if (!willPushToIOS || !willPushToAndroid) {
                if (willPushToIOS) {
                    deviceType = DEVICE_TYPE_IOS;
                } else if (willPushToAndroid) {
                    deviceType = "android";
                }
            }
        }
        return ParseRESTPushCommand.sendPushCommand(state.queryState(), state.channelSet(), deviceType, state.expirationTime(), state.expirationTimeInterval(), state.data(), sessionToken);
    }
}
