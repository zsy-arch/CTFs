package com.parse;

import bolts.Task;
import com.parse.http.ParseHttpBody;
import com.parse.http.ParseHttpRequest;
import com.parse.http.ParseHttpResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import u.aly.av;

/* loaded from: classes2.dex */
public class ParseRESTCommand extends ParseRequest<JSONObject> {
    static final String HEADER_APPLICATION_ID = "X-Parse-Application-Id";
    static final String HEADER_APP_BUILD_VERSION = "X-Parse-App-Build-Version";
    static final String HEADER_APP_DISPLAY_VERSION = "X-Parse-App-Display-Version";
    static final String HEADER_CLIENT_KEY = "X-Parse-Client-Key";
    static final String HEADER_CLIENT_VERSION = "X-Parse-Client-Version";
    static final String HEADER_INSTALLATION_ID = "X-Parse-Installation-Id";
    private static final String HEADER_MASTER_KEY = "X-Parse-Master-Key";
    static final String HEADER_OS_VERSION = "X-Parse-OS-Version";
    private static final String HEADER_SESSION_TOKEN = "X-Parse-Session-Token";
    private static final String PARAMETER_METHOD_OVERRIDE = "_method";
    static final String USER_AGENT = "User-Agent";
    static URL server = null;
    String httpPath;
    private String installationId;
    final JSONObject jsonParameters;
    private String localId;
    public String masterKey;
    private String operationSetUUID;
    private final String sessionToken;

    private static LocalIdManager getLocalIdManager() {
        return ParseCorePlugins.getInstance().getLocalIdManager();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static abstract class Init<T extends Init<T>> {
        private String httpPath;
        private String installationId;
        private JSONObject jsonParameters;
        private String localId;
        public String masterKey;
        private ParseHttpRequest.Method method = ParseHttpRequest.Method.GET;
        private String operationSetUUID;
        private String sessionToken;

        abstract T self();

        public T sessionToken(String sessionToken) {
            this.sessionToken = sessionToken;
            return self();
        }

        public T installationId(String installationId) {
            this.installationId = installationId;
            return self();
        }

        public T masterKey(String masterKey) {
            this.masterKey = masterKey;
            return self();
        }

        public T method(ParseHttpRequest.Method method) {
            this.method = method;
            return self();
        }

        public T httpPath(String httpPath) {
            this.httpPath = httpPath;
            return self();
        }

        public T jsonParameters(JSONObject jsonParameters) {
            this.jsonParameters = jsonParameters;
            return self();
        }

        public T operationSetUUID(String operationSetUUID) {
            this.operationSetUUID = operationSetUUID;
            return self();
        }

        public T localId(String localId) {
            this.localId = localId;
            return self();
        }
    }

    /* loaded from: classes2.dex */
    public static class Builder extends Init<Builder> {
        @Override // com.parse.ParseRESTCommand.Init
        public Builder self() {
            return this;
        }

        public ParseRESTCommand build() {
            return new ParseRESTCommand(this);
        }
    }

    public ParseRESTCommand(String httpPath, ParseHttpRequest.Method httpMethod, Map<String, ?> parameters, String sessionToken) {
        this(httpPath, httpMethod, parameters != null ? (JSONObject) NoObjectsEncoder.get().encode(parameters) : null, sessionToken);
    }

    public ParseRESTCommand(String httpPath, ParseHttpRequest.Method httpMethod, JSONObject jsonParameters, String sessionToken) {
        this(httpPath, httpMethod, jsonParameters, null, sessionToken);
    }

    private ParseRESTCommand(String httpPath, ParseHttpRequest.Method httpMethod, JSONObject jsonParameters, String localId, String sessionToken) {
        super(httpMethod, createUrl(httpPath));
        this.httpPath = httpPath;
        this.jsonParameters = jsonParameters;
        this.localId = localId;
        this.sessionToken = sessionToken;
    }

    public ParseRESTCommand(Init<?> builder) {
        super(((Init) builder).method, createUrl(((Init) builder).httpPath));
        this.sessionToken = ((Init) builder).sessionToken;
        this.installationId = ((Init) builder).installationId;
        this.masterKey = builder.masterKey;
        this.httpPath = ((Init) builder).httpPath;
        this.jsonParameters = ((Init) builder).jsonParameters;
        this.operationSetUUID = ((Init) builder).operationSetUUID;
        this.localId = ((Init) builder).localId;
    }

    public static ParseRESTCommand fromJSONObject(JSONObject jsonObject) {
        String httpPath = jsonObject.optString("httpPath");
        ParseHttpRequest.Method httpMethod = ParseHttpRequest.Method.fromString(jsonObject.optString("httpMethod"));
        String sessionToken = jsonObject.optString("sessionToken", null);
        return new ParseRESTCommand(httpPath, httpMethod, jsonObject.optJSONObject("parameters"), jsonObject.optString("localId", null), sessionToken);
    }

    public void enableRetrying() {
        setMaxRetries(4);
    }

    private static String createUrl(String httpPath) {
        if (httpPath == null) {
            return server.toString();
        }
        try {
            return new URL(server, httpPath).toString();
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void addAdditionalHeaders(ParseHttpRequest.Builder requestBuilder) {
        if (this.installationId != null) {
            requestBuilder.addHeader(HEADER_INSTALLATION_ID, this.installationId);
        }
        if (this.sessionToken != null) {
            requestBuilder.addHeader(HEADER_SESSION_TOKEN, this.sessionToken);
        }
        if (this.masterKey != null) {
            requestBuilder.addHeader(HEADER_MASTER_KEY, this.masterKey);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.parse.ParseRequest
    public ParseHttpRequest newRequest(ParseHttpRequest.Method method, String url, ProgressCallback uploadProgressCallback) {
        ParseHttpRequest request;
        if (this.jsonParameters == null || method == ParseHttpRequest.Method.POST || method == ParseHttpRequest.Method.PUT) {
            request = super.newRequest(method, url, uploadProgressCallback);
        } else {
            request = super.newRequest(ParseHttpRequest.Method.POST, url, uploadProgressCallback);
        }
        ParseHttpRequest.Builder requestBuilder = new ParseHttpRequest.Builder(request);
        addAdditionalHeaders(requestBuilder);
        return requestBuilder.build();
    }

    @Override // com.parse.ParseRequest
    protected ParseHttpBody newBody(ProgressCallback uploadProgressCallback) {
        if (this.jsonParameters == null) {
            throw new IllegalArgumentException(String.format("Trying to execute a %s command without body parameters.", this.method.toString()));
        }
        try {
            JSONObject parameters = this.jsonParameters;
            if (this.method == ParseHttpRequest.Method.GET || this.method == ParseHttpRequest.Method.DELETE) {
                parameters = new JSONObject(this.jsonParameters.toString());
                parameters.put(PARAMETER_METHOD_OVERRIDE, this.method.toString());
            }
            return new ParseByteArrayHttpBody(parameters.toString(), "application/json");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override // com.parse.ParseRequest
    public Task<JSONObject> executeAsync(ParseHttpClient client, ProgressCallback uploadProgressCallback, ProgressCallback downloadProgressCallback, Task<Void> cancellationToken) {
        resolveLocalIds();
        return super.executeAsync(client, uploadProgressCallback, downloadProgressCallback, cancellationToken);
    }

    @Override // com.parse.ParseRequest
    public Task<JSONObject> onResponseAsync(ParseHttpResponse response, ProgressCallback downloadProgressCallback) {
        InputStream responseStream;
        Task<JSONObject> task;
        try {
            responseStream = null;
            try {
                responseStream = response.getContent();
                String content = new String(ParseIOUtils.toByteArray(responseStream));
                ParseIOUtils.closeQuietly(responseStream);
                int statusCode = response.getStatusCode();
                if (statusCode < 200 || statusCode >= 600) {
                    return Task.forError(newPermanentException(-1, content));
                }
                try {
                    JSONObject json = new JSONObject(content);
                    if (statusCode >= 400 && statusCode < 500) {
                        task = Task.forError(newPermanentException(json.optInt("code"), json.optString(av.aG)));
                    } else if (statusCode >= 500) {
                        task = Task.forError(newTemporaryException(json.optInt("code"), json.optString(av.aG)));
                    } else {
                        task = Task.forResult(json);
                    }
                    return task;
                } catch (JSONException e) {
                    return Task.forError(newTemporaryException("bad json response", e));
                }
            } catch (IOException e2) {
                Task<JSONObject> forError = Task.forError(e2);
                ParseIOUtils.closeQuietly(responseStream);
                return forError;
            }
        } catch (Throwable th) {
            ParseIOUtils.closeQuietly(responseStream);
            throw th;
        }
    }

    public String getCacheKey() {
        String json;
        if (this.jsonParameters != null) {
            try {
                json = toDeterministicString(this.jsonParameters);
            } catch (JSONException e) {
                throw new RuntimeException(e.getMessage());
            }
        } else {
            json = "";
        }
        if (this.sessionToken != null) {
            json = json + this.sessionToken;
        }
        return String.format("ParseRESTCommand.%s.%s.%s", this.method.toString(), ParseDigestUtils.md5(this.httpPath), ParseDigestUtils.md5(json));
    }

    static String toDeterministicString(Object o) throws JSONException {
        JSONStringer stringer = new JSONStringer();
        addToStringer(stringer, o);
        return stringer.toString();
    }

    private static void addToStringer(JSONStringer stringer, Object o) throws JSONException {
        if (o instanceof JSONObject) {
            stringer.object();
            JSONObject object = (JSONObject) o;
            Iterator<String> keyIterator = object.keys();
            ArrayList<String> keys = new ArrayList<>();
            while (keyIterator.hasNext()) {
                keys.add(keyIterator.next());
            }
            Collections.sort(keys);
            Iterator i$ = keys.iterator();
            while (i$.hasNext()) {
                String key = i$.next();
                stringer.key(key);
                addToStringer(stringer, object.opt(key));
            }
            stringer.endObject();
        } else if (o instanceof JSONArray) {
            JSONArray array = (JSONArray) o;
            stringer.array();
            for (int i = 0; i < array.length(); i++) {
                addToStringer(stringer, array.get(i));
            }
            stringer.endArray();
        } else {
            stringer.value(o);
        }
    }

    public static boolean isValidCommandJSONObject(JSONObject jsonObject) {
        return jsonObject.has("httpPath");
    }

    public static boolean isValidOldFormatCommandJSONObject(JSONObject jsonObject) {
        return jsonObject.has("op");
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        try {
            if (this.httpPath != null) {
                jsonObject.put("httpPath", this.httpPath);
            }
            jsonObject.put("httpMethod", this.method.toString());
            if (this.jsonParameters != null) {
                jsonObject.put("parameters", this.jsonParameters);
            }
            if (this.sessionToken != null) {
                jsonObject.put("sessionToken", this.sessionToken);
            }
            if (this.localId != null) {
                jsonObject.put("localId", this.localId);
            }
            return jsonObject;
        } catch (JSONException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String getSessionToken() {
        return this.sessionToken;
    }

    public String getOperationSetUUID() {
        return this.operationSetUUID;
    }

    public void setOperationSetUUID(String operationSetUUID) {
        this.operationSetUUID = operationSetUUID;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }

    public String getLocalId() {
        return this.localId;
    }

    private void maybeChangeServerOperation() throws JSONException {
        String objectId;
        if (this.localId != null && (objectId = getLocalIdManager().getObjectId(this.localId)) != null) {
            this.localId = null;
            this.httpPath += String.format("/%s", objectId);
            this.url = createUrl(this.httpPath);
            if (this.httpPath.startsWith("classes") && this.method == ParseHttpRequest.Method.POST) {
                this.method = ParseHttpRequest.Method.PUT;
            }
        }
    }

    public void resolveLocalIds() {
        try {
            ArrayList<JSONObject> localPointers = new ArrayList<>();
            getLocalPointersIn(this.jsonParameters, localPointers);
            Iterator i$ = localPointers.iterator();
            while (i$.hasNext()) {
                JSONObject pointer = i$.next();
                String objectId = getLocalIdManager().getObjectId((String) pointer.get("localId"));
                if (objectId == null) {
                    throw new IllegalStateException("Tried to serialize a command referencing a new, unsaved object.");
                }
                pointer.put("objectId", objectId);
                pointer.remove("localId");
            }
            maybeChangeServerOperation();
        } catch (JSONException e) {
        }
    }

    public void retainLocalIds() {
        if (this.localId != null) {
            getLocalIdManager().retainLocalIdOnDisk(this.localId);
        }
        try {
            ArrayList<JSONObject> localPointers = new ArrayList<>();
            getLocalPointersIn(this.jsonParameters, localPointers);
            Iterator i$ = localPointers.iterator();
            while (i$.hasNext()) {
                getLocalIdManager().retainLocalIdOnDisk((String) i$.next().get("localId"));
            }
        } catch (JSONException e) {
        }
    }

    public void releaseLocalIds() {
        if (this.localId != null) {
            getLocalIdManager().releaseLocalIdOnDisk(this.localId);
        }
        try {
            ArrayList<JSONObject> localPointers = new ArrayList<>();
            getLocalPointersIn(this.jsonParameters, localPointers);
            Iterator i$ = localPointers.iterator();
            while (i$.hasNext()) {
                getLocalIdManager().releaseLocalIdOnDisk((String) i$.next().get("localId"));
            }
        } catch (JSONException e) {
        }
    }

    protected static void getLocalPointersIn(Object container, ArrayList<JSONObject> localPointers) throws JSONException {
        if (container instanceof JSONObject) {
            JSONObject object = (JSONObject) container;
            if (!"Pointer".equals(object.opt("__type")) || !object.has("localId")) {
                Iterator<String> keyIterator = object.keys();
                while (keyIterator.hasNext()) {
                    getLocalPointersIn(object.get(keyIterator.next()), localPointers);
                }
            } else {
                localPointers.add((JSONObject) container);
                return;
            }
        }
        if (container instanceof JSONArray) {
            JSONArray array = (JSONArray) container;
            for (int i = 0; i < array.length(); i++) {
                getLocalPointersIn(array.get(i), localPointers);
            }
        }
    }
}
