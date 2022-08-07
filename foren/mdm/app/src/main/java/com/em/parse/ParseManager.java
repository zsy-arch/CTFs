package com.em.parse;

import android.content.Context;
import com.easeui.domain.EaseUser;
import com.easeui.utils.EaseCommonUtils;
import com.em.DemoHelper;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.util.EMLog;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class ParseManager {
    private static final String CONFIG_AVATAR = "avatar";
    private static final String CONFIG_NICK = "nickname";
    private static final String CONFIG_TABLE_NAME = "hxuser";
    private static final String CONFIG_USERNAME = "username";
    private static final String ParseAppID = "UUL8TxlHwKj7ZXEUr2brF3ydOxirCXdIj9LscvJs";
    private static final String ParseClientKey = "B1jH9bmxuYyTcpoFfpeVslhmLYsytWTxqYqKQhBJ";
    private static final String TAG = ParseManager.class.getSimpleName();
    private static ParseManager instance = new ParseManager();

    private ParseManager() {
    }

    public static ParseManager getInstance() {
        return instance;
    }

    public void onInit(Context context) {
        Parse.enableLocalDatastore(context.getApplicationContext());
        Parse.initialize(context, ParseAppID, ParseClientKey);
    }

    public boolean updateParseNickName(String nickname) {
        String username = EMClient.getInstance().getCurrentUser();
        ParseQuery<ParseObject> pQuery = ParseQuery.getQuery(CONFIG_TABLE_NAME);
        pQuery.whereEqualTo("username", username);
        try {
            ParseObject pUser = pQuery.getFirst();
            if (pUser == null) {
                return false;
            }
            pUser.put("nickname", nickname);
            pUser.save();
            return true;
        } catch (ParseException e) {
            if (e.getCode() == 101) {
                ParseObject pUser2 = new ParseObject(CONFIG_TABLE_NAME);
                pUser2.put("username", username);
                pUser2.put("nickname", nickname);
                try {
                    pUser2.save();
                    return true;
                } catch (ParseException e1) {
                    e1.printStackTrace();
                    EMLog.e(TAG, "parse error " + e1.getMessage());
                    e.printStackTrace();
                    EMLog.e(TAG, "parse error " + e.getMessage());
                    return false;
                }
            }
            e.printStackTrace();
            EMLog.e(TAG, "parse error " + e.getMessage());
            return false;
        } catch (Exception e2) {
            EMLog.e(TAG, "updateParseNickName error");
            e2.printStackTrace();
            return false;
        }
    }

    public void getContactInfos(List<String> usernames, final EMValueCallBack<List<EaseUser>> callback) {
        ParseQuery<ParseObject> pQuery = ParseQuery.getQuery(CONFIG_TABLE_NAME);
        pQuery.whereContainedIn("username", usernames);
        pQuery.findInBackground(new FindCallback<ParseObject>() { // from class: com.em.parse.ParseManager.1
            @Override // com.parse.FindCallback
            public void done(List<ParseObject> arg0, ParseException arg1) {
                if (arg0 != null) {
                    ArrayList arrayList = new ArrayList();
                    for (ParseObject pObject : arg0) {
                        EaseUser user = new EaseUser(pObject.getString("username"));
                        ParseFile parseFile = pObject.getParseFile("avatar");
                        if (parseFile != null) {
                            user.setAvatar(parseFile.getUrl());
                        }
                        user.setNick(pObject.getString("nickname"));
                        user.setInitialLetter(EaseCommonUtils.setUserInitialLetter(user.getNick()));
                        arrayList.add(user);
                    }
                    callback.onSuccess(arrayList);
                    return;
                }
                callback.onError(arg1.getCode(), arg1.getMessage());
            }
        });
    }

    public void asyncGetCurrentUserInfo(final EMValueCallBack<EaseUser> callback) {
        final String username = EMClient.getInstance().getCurrentUser();
        asyncGetUserInfo(username, new EMValueCallBack<EaseUser>() { // from class: com.em.parse.ParseManager.2
            public void onSuccess(EaseUser value) {
                callback.onSuccess(value);
            }

            @Override // com.hyphenate.EMValueCallBack
            public void onError(int error, String errorMsg) {
                if (error == 101) {
                    ParseObject pUser = new ParseObject(ParseManager.CONFIG_TABLE_NAME);
                    pUser.put("username", username);
                    pUser.saveInBackground(new SaveCallback() { // from class: com.em.parse.ParseManager.2.1
                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // com.parse.SaveCallback
                        public void done(ParseException arg0) {
                            if (arg0 == null) {
                                callback.onSuccess(new EaseUser(username));
                            }
                        }
                    });
                    return;
                }
                callback.onError(error, errorMsg);
            }
        });
    }

    public void asyncGetUserInfo(final String username, final EMValueCallBack<EaseUser> callback) {
        ParseQuery<ParseObject> pQuery = ParseQuery.getQuery(CONFIG_TABLE_NAME);
        pQuery.whereEqualTo("username", username);
        pQuery.getFirstInBackground(new GetCallback<ParseObject>() { // from class: com.em.parse.ParseManager.3
            @Override // com.parse.GetCallback
            public void done(ParseObject pUser, ParseException e) {
                if (pUser != null) {
                    String nick = pUser.getString("nickname");
                    ParseFile pFile = pUser.getParseFile("avatar");
                    if (callback != null) {
                        EaseUser user = DemoHelper.getInstance().getContactList().get(username);
                        if (user != null) {
                            user.setNick(nick);
                            if (!(pFile == null || pFile.getUrl() == null)) {
                                user.setAvatar(pFile.getUrl());
                            }
                        } else {
                            user = new EaseUser(username);
                            user.setNick(nick);
                            if (!(pFile == null || pFile.getUrl() == null)) {
                                user.setAvatar(pFile.getUrl());
                            }
                        }
                        callback.onSuccess(user);
                    }
                } else if (callback != null) {
                    callback.onError(e.getCode(), e.getMessage());
                }
            }
        });
    }

    public String uploadParseAvatar(byte[] data) {
        Exception e;
        ParseObject pUser;
        ParseException e2;
        ParseException e1;
        ParseObject pUser2;
        String username = EMClient.getInstance().getCurrentUser();
        ParseQuery<ParseObject> pQuery = ParseQuery.getQuery(CONFIG_TABLE_NAME);
        pQuery.whereEqualTo("username", username);
        ParseObject pUser3 = null;
        try {
            pUser3 = pQuery.getFirst();
            if (pUser3 == null) {
                pUser = new ParseObject(CONFIG_TABLE_NAME);
                try {
                    pUser.put("username", username);
                    pUser3 = pUser;
                } catch (ParseException e3) {
                    e2 = e3;
                    if (e2.getCode() == 101) {
                        try {
                            pUser2 = new ParseObject(CONFIG_TABLE_NAME);
                        } catch (ParseException e4) {
                            e1 = e4;
                        }
                        try {
                            pUser2.put("username", username);
                            ParseFile pFile = new ParseFile(data);
                            pUser2.put("avatar", pFile);
                            pUser2.save();
                            return pFile.getUrl();
                        } catch (ParseException e5) {
                            e1 = e5;
                            e1.printStackTrace();
                            EMLog.e(TAG, "parse error " + e1.getMessage());
                            return null;
                        }
                    } else {
                        e2.printStackTrace();
                        EMLog.e(TAG, "parse error " + e2.getMessage());
                    }
                    return null;
                } catch (Exception e6) {
                    e = e6;
                    EMLog.e(TAG, "uploadParseAvatar error");
                    e.printStackTrace();
                    return null;
                }
            }
            ParseFile pFile2 = new ParseFile(data);
            pUser3.put("avatar", pFile2);
            pUser3.save();
            return pFile2.getUrl();
        } catch (ParseException e7) {
            e2 = e7;
            pUser = pUser3;
        } catch (Exception e8) {
            e = e8;
        }
    }
}
