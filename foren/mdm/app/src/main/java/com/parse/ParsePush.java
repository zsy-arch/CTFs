package com.parse;

import bolts.Continuation;
import bolts.Task;
import com.parse.ParseQuery;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ParsePush {
    static String KEY_DATA_MESSAGE = "alert";
    private static final String TAG = "com.parse.ParsePush";
    final State.Builder builder;

    static ParsePushController getPushController() {
        return ParseCorePlugins.getInstance().getPushController();
    }

    static ParsePushChannelsController getPushChannelsController() {
        return ParseCorePlugins.getInstance().getPushChannelsController();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ParseObjectSubclassingController getSubclassingController() {
        return ParseCorePlugins.getInstance().getSubclassingController();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkArgument(boolean expression, Object errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class State {
        private final Set<String> channelSet;
        private final JSONObject data;
        private final Long expirationTime;
        private final Long expirationTimeInterval;
        private final Boolean pushToAndroid;
        private final Boolean pushToIOS;
        private final ParseQuery.State<ParseInstallation> queryState;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes2.dex */
        public static class Builder {
            private Set<String> channelSet;
            private JSONObject data;
            private Long expirationTime;
            private Long expirationTimeInterval;
            private Boolean pushToAndroid;
            private Boolean pushToIOS;
            private ParseQuery<ParseInstallation> query;

            public Builder() {
            }

            public Builder(State state) {
                Set<String> unmodifiableSet;
                ParseQuery<ParseInstallation> parseQuery = null;
                if (state.channelSet() == null) {
                    unmodifiableSet = null;
                } else {
                    unmodifiableSet = Collections.unmodifiableSet(new HashSet(state.channelSet()));
                }
                this.channelSet = unmodifiableSet;
                this.query = state.queryState() != null ? new ParseQuery<>(new ParseQuery.State.Builder(state.queryState())) : parseQuery;
                this.expirationTime = state.expirationTime();
                this.expirationTimeInterval = state.expirationTimeInterval();
                this.pushToIOS = state.pushToIOS();
                this.pushToAndroid = state.pushToAndroid();
                JSONObject copyData = null;
                try {
                    copyData = new JSONObject(state.data().toString());
                } catch (JSONException e) {
                }
                this.data = copyData;
            }

            public Builder expirationTime(Long expirationTime) {
                this.expirationTime = expirationTime;
                this.expirationTimeInterval = null;
                return this;
            }

            public Builder expirationTimeInterval(Long expirationTimeInterval) {
                this.expirationTimeInterval = expirationTimeInterval;
                this.expirationTime = null;
                return this;
            }

            public Builder pushToIOS(Boolean pushToIOS) {
                ParsePush.checkArgument(this.query == null, "Cannot set push targets (i.e. setPushToAndroid or setPushToIOS) when pushing to a query");
                this.pushToIOS = pushToIOS;
                return this;
            }

            public Builder pushToAndroid(Boolean pushToAndroid) {
                ParsePush.checkArgument(this.query == null, "Cannot set push targets (i.e. setPushToAndroid or setPushToIOS) when pushing to a query");
                this.pushToAndroid = pushToAndroid;
                return this;
            }

            public Builder data(JSONObject data) {
                this.data = data;
                return this;
            }

            public Builder channelSet(Collection<String> channelSet) {
                boolean z;
                if (channelSet != null) {
                    z = true;
                } else {
                    z = false;
                }
                ParsePush.checkArgument(z, "channels collection cannot be null");
                Iterator i$ = channelSet.iterator();
                while (i$.hasNext()) {
                    ParsePush.checkArgument(i$.next() != null, "channel cannot be null");
                }
                this.channelSet = new HashSet(channelSet);
                this.query = null;
                return this;
            }

            public Builder query(ParseQuery<ParseInstallation> query) {
                boolean z = true;
                ParsePush.checkArgument(query != null, "Cannot target a null query");
                if (!(this.pushToIOS == null && this.pushToAndroid == null)) {
                    z = false;
                }
                ParsePush.checkArgument(z, "Cannot set push targets (i.e. setPushToAndroid or setPushToIOS) when pushing to a query");
                ParsePush.checkArgument(query.getClassName().equals(ParsePush.getSubclassingController().getClassName(ParseInstallation.class)), "Can only push to a query for Installations");
                this.channelSet = null;
                this.query = query;
                return this;
            }

            public State build() {
                if (this.data != null) {
                    return new State(this);
                }
                throw new IllegalArgumentException("Cannot send a push without calling either setMessage or setData");
            }
        }

        private State(Builder builder) {
            Set<String> unmodifiableSet;
            ParseQuery.State<ParseInstallation> state = null;
            if (builder.channelSet == null) {
                unmodifiableSet = null;
            } else {
                unmodifiableSet = Collections.unmodifiableSet(new HashSet(builder.channelSet));
            }
            this.channelSet = unmodifiableSet;
            this.queryState = builder.query != null ? builder.query.getBuilder().build() : state;
            this.expirationTime = builder.expirationTime;
            this.expirationTimeInterval = builder.expirationTimeInterval;
            this.pushToIOS = builder.pushToIOS;
            this.pushToAndroid = builder.pushToAndroid;
            JSONObject copyData = null;
            try {
                copyData = new JSONObject(builder.data.toString());
            } catch (JSONException e) {
            }
            this.data = copyData;
        }

        public Set<String> channelSet() {
            return this.channelSet;
        }

        public ParseQuery.State<ParseInstallation> queryState() {
            return this.queryState;
        }

        public Long expirationTime() {
            return this.expirationTime;
        }

        public Long expirationTimeInterval() {
            return this.expirationTimeInterval;
        }

        public Boolean pushToIOS() {
            return this.pushToIOS;
        }

        public Boolean pushToAndroid() {
            return this.pushToAndroid;
        }

        public JSONObject data() {
            try {
                return new JSONObject(this.data.toString());
            } catch (JSONException e) {
                return null;
            }
        }
    }

    public ParsePush() {
        this(new State.Builder());
    }

    public ParsePush(ParsePush push) {
        this(new State.Builder(push.builder.build()));
    }

    private ParsePush(State.Builder builder) {
        this.builder = builder;
    }

    public static Task<Void> subscribeInBackground(String channel) {
        return getPushChannelsController().subscribeInBackground(channel);
    }

    public static void subscribeInBackground(String channel, SaveCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(subscribeInBackground(channel), callback);
    }

    public static Task<Void> unsubscribeInBackground(String channel) {
        return getPushChannelsController().unsubscribeInBackground(channel);
    }

    public static void unsubscribeInBackground(String channel, SaveCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(unsubscribeInBackground(channel), callback);
    }

    public static Task<Void> sendMessageInBackground(String message, ParseQuery<ParseInstallation> query) {
        ParsePush push = new ParsePush();
        push.setQuery(query);
        push.setMessage(message);
        return push.sendInBackground();
    }

    public static void sendMessageInBackground(String message, ParseQuery<ParseInstallation> query, SendCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(sendMessageInBackground(message, query), callback);
    }

    public static Task<Void> sendDataInBackground(JSONObject data, ParseQuery<ParseInstallation> query) {
        ParsePush push = new ParsePush();
        push.setQuery(query);
        push.setData(data);
        return push.sendInBackground();
    }

    public static void sendDataInBackground(JSONObject data, ParseQuery<ParseInstallation> query, SendCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(sendDataInBackground(data, query), callback);
    }

    public void setChannel(String channel) {
        this.builder.channelSet(Collections.singletonList(channel));
    }

    public void setChannels(Collection<String> channels) {
        this.builder.channelSet(channels);
    }

    public void setQuery(ParseQuery<ParseInstallation> query) {
        this.builder.query(query);
    }

    public void setExpirationTime(long time) {
        this.builder.expirationTime(Long.valueOf(time));
    }

    public void setExpirationTimeInterval(long timeInterval) {
        this.builder.expirationTimeInterval(Long.valueOf(timeInterval));
    }

    public void clearExpiration() {
        this.builder.expirationTime(null);
        this.builder.expirationTimeInterval(null);
    }

    @Deprecated
    public void setPushToIOS(boolean pushToIOS) {
        this.builder.pushToIOS(Boolean.valueOf(pushToIOS));
    }

    @Deprecated
    public void setPushToAndroid(boolean pushToAndroid) {
        this.builder.pushToAndroid(Boolean.valueOf(pushToAndroid));
    }

    public void setData(JSONObject data) {
        this.builder.data(data);
    }

    public void setMessage(String message) {
        JSONObject data = new JSONObject();
        try {
            data.put(KEY_DATA_MESSAGE, message);
        } catch (JSONException e) {
            PLog.e(TAG, "JSONException in setMessage", e);
        }
        setData(data);
    }

    public Task<Void> sendInBackground() {
        final State state = this.builder.build();
        return ParseUser.getCurrentSessionTokenAsync().onSuccessTask(new Continuation<String, Task<Void>>() { // from class: com.parse.ParsePush.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public Task<Void> then(Task<String> task) throws Exception {
                return ParsePush.getPushController().sendInBackground(state, task.getResult());
            }
        });
    }

    public void send() throws ParseException {
        ParseTaskUtils.wait(sendInBackground());
    }

    public void sendInBackground(SendCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(sendInBackground(), callback);
    }
}
