package com.hyphenate.chat;

import com.hyphenate.EMChatRoomChangeListener;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.adapter.EMAChatRoom;
import com.hyphenate.chat.adapter.EMAChatRoomManager;
import com.hyphenate.chat.adapter.EMAChatRoomManagerListener;
import com.hyphenate.chat.adapter.EMAError;
import com.hyphenate.exceptions.HyphenateException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public class EMChatRoomManager {
    EMAChatRoomManager emaObject;
    EMClient mClient;
    private ExecutorService threadPool;
    private List<EMChatRoomChangeListener> chatRoomListeners = Collections.synchronizedList(new ArrayList());
    private List<EMChatRoom> chatRooms = Collections.synchronizedList(new ArrayList());
    EMAChatRoomManagerListener chatRoomListenerImpl = new EMAChatRoomManagerListener() { // from class: com.hyphenate.chat.EMChatRoomManager.6
        @Override // com.hyphenate.chat.adapter.EMAChatRoomManagerListener, com.hyphenate.chat.adapter.EMAChatRoomManagerListenerInterface
        public void onLeaveChatRoom(EMAChatRoom eMAChatRoom, int i) {
            EMClient.getInstance().chatManager().caches.remove(eMAChatRoom.getId());
            synchronized (EMChatRoomManager.this.chatRoomListeners) {
                for (EMChatRoomChangeListener eMChatRoomChangeListener : EMChatRoomManager.this.chatRoomListeners) {
                    if (i == 0) {
                        eMChatRoomChangeListener.onRemovedFromChatRoom(eMAChatRoom.getId(), eMAChatRoom.getName(), "");
                    } else {
                        eMChatRoomChangeListener.onChatRoomDestroyed(eMAChatRoom.getId(), eMAChatRoom.getName());
                    }
                }
            }
        }

        @Override // com.hyphenate.chat.adapter.EMAChatRoomManagerListener, com.hyphenate.chat.adapter.EMAChatRoomManagerListenerInterface
        public void onMemberJoinedChatRoom(EMAChatRoom eMAChatRoom, String str) {
            synchronized (EMChatRoomManager.this.chatRoomListeners) {
                for (EMChatRoomChangeListener eMChatRoomChangeListener : EMChatRoomManager.this.chatRoomListeners) {
                    eMChatRoomChangeListener.onMemberJoined(eMAChatRoom.getId(), str);
                }
            }
        }

        @Override // com.hyphenate.chat.adapter.EMAChatRoomManagerListener, com.hyphenate.chat.adapter.EMAChatRoomManagerListenerInterface
        public void onMemberLeftChatRoom(EMAChatRoom eMAChatRoom, String str) {
            synchronized (EMChatRoomManager.this.chatRoomListeners) {
                for (EMChatRoomChangeListener eMChatRoomChangeListener : EMChatRoomManager.this.chatRoomListeners) {
                    eMChatRoomChangeListener.onMemberExited(eMAChatRoom.getId(), eMAChatRoom.getName(), str);
                }
            }
        }
    };

    public EMChatRoomManager(EMClient eMClient, EMAChatRoomManager eMAChatRoomManager) {
        this.threadPool = null;
        this.emaObject = eMAChatRoomManager;
        this.emaObject.addListener(this.chatRoomListenerImpl);
        this.mClient = eMClient;
        this.threadPool = Executors.newCachedThreadPool();
    }

    private void handleError(EMAError eMAError) throws HyphenateException {
        if (eMAError.errCode() != 0) {
            throw new HyphenateException(eMAError);
        }
    }

    public void addChatRoomChangeListener(EMChatRoomChangeListener eMChatRoomChangeListener) {
        this.chatRoomListeners.add(eMChatRoomChangeListener);
    }

    public void asyncFetchChatRoomFromServer(final String str, final EMValueCallBack<EMChatRoom> eMValueCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMChatRoomManager.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    eMValueCallBack.onSuccess(EMChatRoomManager.this.fetchChatRoomFromServer(str));
                } catch (HyphenateException e) {
                    eMValueCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void asyncFetchPublicChatRoomsFromServer(final int i, final int i2, final EMValueCallBack<EMPageResult<EMChatRoom>> eMValueCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMChatRoomManager.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    eMValueCallBack.onSuccess(EMChatRoomManager.this.fetchPublicChatRoomsFromServer(i, i2));
                } catch (HyphenateException e) {
                    eMValueCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void asyncFetchPublicChatRoomsFromServer(final int i, final String str, final EMValueCallBack<EMCursorResult<EMChatRoom>> eMValueCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMChatRoomManager.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    eMValueCallBack.onSuccess(EMChatRoomManager.this.fetchPublicChatRoomsFromServer(i, str));
                } catch (HyphenateException e) {
                    eMValueCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public EMChatRoom fetchChatRoomFromServer(String str) throws HyphenateException {
        return fetchChatRoomFromServer(str, false);
    }

    public EMChatRoom fetchChatRoomFromServer(String str, boolean z) throws HyphenateException {
        EMAError eMAError = new EMAError();
        EMAChatRoom fetchChatroomSpecification = this.emaObject.fetchChatroomSpecification(str, eMAError, z);
        handleError(eMAError);
        return new EMChatRoom(fetchChatroomSpecification);
    }

    public EMCursorResult<EMChatRoom> fetchPublicChatRoomsFromServer(int i, String str) throws HyphenateException {
        EMAError eMAError = new EMAError();
        List<EMAChatRoom> fetchAllChatrooms = this.emaObject.fetchAllChatrooms(eMAError);
        handleError(eMAError);
        EMCursorResult<EMChatRoom> eMCursorResult = new EMCursorResult<>();
        ArrayList arrayList = new ArrayList();
        for (EMAChatRoom eMAChatRoom : fetchAllChatrooms) {
            arrayList.add(new EMChatRoom(eMAChatRoom));
        }
        eMCursorResult.setCursor(null);
        eMCursorResult.setData(arrayList);
        this.chatRooms.clear();
        this.chatRooms.addAll(arrayList);
        return eMCursorResult;
    }

    public EMPageResult<EMChatRoom> fetchPublicChatRoomsFromServer(int i, int i2) throws HyphenateException {
        EMAError eMAError = new EMAError();
        EMPageResult<EMAChatRoom> fetchChatroomsWithPage = this.emaObject.fetchChatroomsWithPage(i, i2, eMAError);
        handleError(eMAError);
        int pageCount = fetchChatroomsWithPage.getPageCount();
        EMPageResult<EMChatRoom> eMPageResult = new EMPageResult<>();
        ArrayList arrayList = new ArrayList();
        for (EMAChatRoom eMAChatRoom : (List) fetchChatroomsWithPage.getData()) {
            arrayList.add(new EMChatRoom(eMAChatRoom));
        }
        eMPageResult.setPageCount(pageCount);
        eMPageResult.setData(arrayList);
        this.chatRooms.clear();
        this.chatRooms.addAll(arrayList);
        return eMPageResult;
    }

    public List<EMChatRoom> getAllChatRooms() {
        return Collections.unmodifiableList(this.chatRooms);
    }

    public EMChatRoom getChatRoom(String str) {
        EMAChatRoom chatroom = this.emaObject.getChatroom(str);
        if (chatroom == null) {
            return null;
        }
        return new EMChatRoom(chatroom);
    }

    void importChatRoom(String str, String str2, String str3, String str4, List<String> list, int i) {
    }

    public void joinChatRoom(final String str, final EMValueCallBack<EMChatRoom> eMValueCallBack) {
        this.threadPool.submit(new Runnable() { // from class: com.hyphenate.chat.EMChatRoomManager.1
            @Override // java.lang.Runnable
            public void run() {
                EMAError eMAError = new EMAError();
                EMChatRoom eMChatRoom = new EMChatRoom(EMChatRoomManager.this.emaObject.joinChatRoom(str, eMAError));
                if (eMAError.errCode() == 0 || eMAError.errCode() == 701) {
                    eMValueCallBack.onSuccess(eMChatRoom);
                } else {
                    eMValueCallBack.onError(eMAError.errCode(), eMAError.errMsg());
                }
            }
        });
    }

    public void leaveChatRoom(final String str) {
        EMChatRoom chatRoom = getChatRoom(str);
        if (chatRoom != null) {
            boolean isChatroomOwnerLeaveAllowed = EMClient.getInstance().getOptions().isChatroomOwnerLeaveAllowed();
            String owner = chatRoom.getOwner();
            if (isChatroomOwnerLeaveAllowed || !owner.equals(EMSessionManager.getInstance().getLastLoginUser())) {
                EMClient.getInstance().chatManager().deleteConversation(str, true);
                this.threadPool.submit(new Runnable() { // from class: com.hyphenate.chat.EMChatRoomManager.2
                    @Override // java.lang.Runnable
                    public void run() {
                        EMChatRoomManager.this.emaObject.leaveChatRoom(str, new EMAError());
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onLogout() {
        this.chatRoomListeners.clear();
    }

    @Deprecated
    public void removeChatRoomChangeListener(EMChatRoomChangeListener eMChatRoomChangeListener) {
        removeChatRoomListener(eMChatRoomChangeListener);
    }

    public void removeChatRoomListener(EMChatRoomChangeListener eMChatRoomChangeListener) {
        this.chatRoomListeners.remove(eMChatRoomChangeListener);
    }
}
