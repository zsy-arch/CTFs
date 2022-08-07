package com.hyphenate.chat;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.util.EMLog;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class MessageEncoder {
    public static final String ATTR_ACTION = "action";
    public static final String ATTR_ADDRESS = "addr";
    public static final String ATTR_EXT = "ext";
    public static final String ATTR_FILENAME = "filename";
    public static final String ATTR_FILE_LENGTH = "file_length";
    public static final String ATTR_FROM = "from";
    public static final String ATTR_IMG_HEIGHT = "height";
    public static final String ATTR_IMG_WIDTH = "width";
    public static final String ATTR_LATITUDE = "lat";
    public static final String ATTR_LENGTH = "length";
    public static final String ATTR_LOCALURL = "localurl";
    public static final String ATTR_LONGITUDE = "lng";
    public static final String ATTR_MSG = "msg";
    public static final String ATTR_PARAM = "param";
    public static final String ATTR_SECRET = "secret";
    public static final String ATTR_SIZE = "size";
    public static final String ATTR_THUMBNAIL = "thumb";
    public static final String ATTR_THUMBNAIL_SECRET = "thumb_secret";
    public static final String ATTR_THUMB_LOCALURL = "thumblocalurl";
    public static final String ATTR_TO = "to";
    public static final String ATTR_TYPE = "type";
    private static final String ATTR_TYPE_CMD = "cmd";
    private static final String ATTR_TYPE_IMG = "img";
    private static final String ATTR_TYPE_LOCATION = "loc";
    private static final String ATTR_TYPE_TXT = "txt";
    private static final String ATTR_TYPE_VIDEO = "video";
    private static final String ATTR_TYPE_VOICE = "audio";
    private static final String ATTR_TYPE_file = "file";
    public static final String ATTR_URL = "url";
    private static final String TAG = "encoder";

    public static EMMessage getMsgFromJson(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            EMContact eMContact = new EMContact(jSONObject.getString(ATTR_FROM));
            EMContact eMContact2 = new EMContact(jSONObject.getString(ATTR_TO));
            JSONArray jSONArray = jSONObject.getJSONArray("bodies");
            if (jSONArray.length() < 1) {
                EMLog.d(TAG, "wrong msg without body");
                return null;
            }
            JSONObject jSONObject2 = jSONArray.getJSONObject(0);
            String string = jSONObject2.getString("type");
            EMMessage.Type type = EMMessage.Type.TXT;
            if (string.equals(ATTR_TYPE_TXT)) {
                type = EMMessage.Type.TXT;
            } else if (string.equals("img")) {
                type = EMMessage.Type.IMAGE;
            } else if (string.equals(ATTR_TYPE_file)) {
                type = EMMessage.Type.FILE;
            } else if (string.equals(ATTR_TYPE_VIDEO)) {
                type = EMMessage.Type.VIDEO;
            } else if (string.equals(ATTR_TYPE_VOICE)) {
                type = EMMessage.Type.VOICE;
            } else if (string.equals(ATTR_TYPE_LOCATION)) {
                type = EMMessage.Type.LOCATION;
            } else if (string.equals(ATTR_TYPE_CMD)) {
                type = EMMessage.Type.CMD;
            }
            EMMessage createSendMessage = eMContact.username.equals(EMClient.getInstance().getCurrentUser()) ? EMMessage.createSendMessage(type) : EMMessage.createReceiveMessage(type);
            if (string.equals(ATTR_TYPE_TXT)) {
                createSendMessage.addBody(new EMTextMessageBody(jSONObject2.getString("msg").replaceAll("%22", "\"")));
            } else if (string.equals("img")) {
                String string2 = jSONObject2.getString("url");
                EMImageMessageBody eMImageMessageBody = new EMImageMessageBody(jSONObject2.getString(ATTR_FILENAME), string2, jSONObject2.has(ATTR_THUMBNAIL) ? jSONObject2.getString(ATTR_THUMBNAIL) : string2);
                if (jSONObject2.has(ATTR_LOCALURL)) {
                    eMImageMessageBody.setLocalUrl(jSONObject2.getString(ATTR_LOCALURL));
                }
                if (jSONObject2.has("secret")) {
                    eMImageMessageBody.setSecret(jSONObject2.getString("secret"));
                }
                if (jSONObject2.has(ATTR_THUMBNAIL_SECRET)) {
                    eMImageMessageBody.setThumbnailSecret(jSONObject2.getString(ATTR_THUMBNAIL_SECRET));
                }
                if (jSONObject2.has("size")) {
                    JSONObject jSONObject3 = jSONObject2.getJSONObject("size");
                    eMImageMessageBody.setSize(jSONObject3.getInt("width"), jSONObject3.getInt(ATTR_IMG_HEIGHT));
                }
                createSendMessage.addBody(eMImageMessageBody);
            } else if (string.equals(ATTR_TYPE_file)) {
                EMNormalFileMessageBody eMNormalFileMessageBody = new EMNormalFileMessageBody(jSONObject2.getString(ATTR_FILENAME), jSONObject2.getString("url"));
                eMNormalFileMessageBody.setFileLength(Integer.parseInt(jSONObject2.getString(ATTR_FILE_LENGTH)));
                if (jSONObject2.has(ATTR_LOCALURL)) {
                    eMNormalFileMessageBody.setLocalUrl(jSONObject2.getString(ATTR_LOCALURL));
                }
                if (jSONObject2.has("secret")) {
                    eMNormalFileMessageBody.setSecret(jSONObject2.getString("secret"));
                }
                createSendMessage.addBody(eMNormalFileMessageBody);
            } else if (string.equals(ATTR_TYPE_VIDEO)) {
                EMVideoMessageBody eMVideoMessageBody = new EMVideoMessageBody(jSONObject2.getString(ATTR_FILENAME), jSONObject2.getString("url"), jSONObject2.getString(ATTR_THUMBNAIL), jSONObject2.getInt("length"));
                if (jSONObject2.has(ATTR_LOCALURL)) {
                    eMVideoMessageBody.setLocalUrl(jSONObject2.getString(ATTR_LOCALURL));
                }
                if (jSONObject2.has(ATTR_FILE_LENGTH)) {
                    eMVideoMessageBody.setFileLength(jSONObject2.getLong(ATTR_FILE_LENGTH));
                }
                if (jSONObject2.has(ATTR_THUMB_LOCALURL)) {
                    eMVideoMessageBody.setLocalThumb(jSONObject2.getString(ATTR_THUMB_LOCALURL));
                }
                if (jSONObject2.has("secret")) {
                    eMVideoMessageBody.setSecret(jSONObject2.getString("secret"));
                }
                if (jSONObject2.has(ATTR_THUMBNAIL_SECRET)) {
                    eMVideoMessageBody.setThumbnailSecret(jSONObject2.getString(ATTR_THUMBNAIL_SECRET));
                }
                createSendMessage.addBody(eMVideoMessageBody);
            } else if (string.equals(ATTR_TYPE_VOICE)) {
                EMVoiceMessageBody eMVoiceMessageBody = new EMVoiceMessageBody(jSONObject2.getString(ATTR_FILENAME), jSONObject2.getString("url"), jSONObject2.getInt("length"));
                if (jSONObject2.has(ATTR_LOCALURL)) {
                    eMVoiceMessageBody.setLocalUrl(jSONObject2.getString(ATTR_LOCALURL));
                }
                if (jSONObject2.has("secret")) {
                    eMVoiceMessageBody.setSecret(jSONObject2.getString("secret"));
                }
                createSendMessage.addBody(eMVoiceMessageBody);
            } else if (string.equals(ATTR_TYPE_LOCATION)) {
                createSendMessage.addBody(new EMLocationMessageBody(jSONObject2.getString(ATTR_ADDRESS), jSONObject2.getDouble("lat"), jSONObject2.getDouble("lng")));
            } else if (string.equals(ATTR_TYPE_CMD)) {
            }
            if (createSendMessage != null) {
                createSendMessage.setFrom(eMContact.getUsername());
                createSendMessage.setTo(eMContact2.getUsername());
            }
            if (jSONObject.has(ATTR_EXT)) {
                JSONObject jSONObject4 = jSONObject.getJSONObject(ATTR_EXT);
                Iterator<String> keys = jSONObject4.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    Object obj = jSONObject4.get(next);
                    if (obj instanceof String) {
                        createSendMessage.setAttribute(next, (String) obj);
                    } else if (obj instanceof Integer) {
                        createSendMessage.setAttribute(next, ((Integer) obj).intValue());
                    } else if (obj instanceof Boolean) {
                        createSendMessage.setAttribute(next, ((Boolean) obj).booleanValue());
                    } else if (obj instanceof JSONObject) {
                        createSendMessage.setAttribute(next, (JSONObject) obj);
                    } else if (obj instanceof JSONArray) {
                        createSendMessage.setAttribute(next, (JSONArray) obj);
                    } else {
                        EMLog.e("msg", "unknow additonal msg attr:" + obj.getClass().getName());
                    }
                }
            }
            return createSendMessage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
