package com.easeui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.easeui.domain.EaseEmojicon;
import com.easeui.domain.EaseEmojiconGroupEntity;
import com.easeui.model.EaseDefaultEmojiconDatas;
import com.easeui.utils.EaseSmileUtils;
import com.easeui.widget.EaseChatExtendMenu;
import com.easeui.widget.EaseChatPrimaryMenuBase;
import com.easeui.widget.emojicon.EaseEmojiconMenu;
import com.easeui.widget.emojicon.EaseEmojiconMenuBase;
import com.vsf2f.f2f.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class EaseChatInputMenu extends LinearLayout {
    protected EaseChatExtendMenu chatExtendMenu;
    protected FrameLayout chatExtendMenuContainer;
    protected EaseChatPrimaryMenuBase chatPrimaryMenu;
    private Context context;
    protected EaseEmojiconMenuBase emojiconMenu;
    protected FrameLayout emojiconMenuContainer;
    private Handler handler;
    private boolean inited;
    protected LayoutInflater layoutInflater;
    private ChatInputMenuListener listener;
    protected FrameLayout primaryMenuContainer;

    /* loaded from: classes.dex */
    public interface ChatInputMenuListener {
        void onBigExpressionClicked(EaseEmojicon easeEmojicon);

        boolean onPressToSpeakBtnTouch(View view, MotionEvent motionEvent);

        void onSendMessage(String str);
    }

    public EaseChatInputMenu(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    public EaseChatInputMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.handler = new Handler();
        init(context, attrs);
    }

    public EaseChatInputMenu(Context context) {
        super(context);
        this.handler = new Handler();
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.layoutInflater.inflate(R.layout.ease_widget_chat_input_menu, this);
        this.primaryMenuContainer = (FrameLayout) findViewById(R.id.primary_menu_container);
        this.emojiconMenuContainer = (FrameLayout) findViewById(R.id.emojicon_menu_container);
        this.chatExtendMenuContainer = (FrameLayout) findViewById(R.id.extend_menu_container);
        this.chatExtendMenu = (EaseChatExtendMenu) findViewById(R.id.extend_menu);
    }

    @SuppressLint({"InflateParams"})
    public void init(List<EaseEmojiconGroupEntity> emojiconGroupList) {
        if (!this.inited) {
            if (this.chatPrimaryMenu == null) {
                this.chatPrimaryMenu = (EaseChatPrimaryMenu) this.layoutInflater.inflate(R.layout.ease_layout_chat_primary_menu, (ViewGroup) null);
            }
            this.primaryMenuContainer.addView(this.chatPrimaryMenu);
            if (this.emojiconMenu == null) {
                this.emojiconMenu = (EaseEmojiconMenu) this.layoutInflater.inflate(R.layout.ease_layout_emojicon_menu, (ViewGroup) null);
                if (emojiconGroupList == null) {
                    emojiconGroupList = new ArrayList<>();
                    emojiconGroupList.add(new EaseEmojiconGroupEntity(R.drawable.expression_1, Arrays.asList(EaseDefaultEmojiconDatas.getData())));
                }
                ((EaseEmojiconMenu) this.emojiconMenu).init(emojiconGroupList);
            }
            this.emojiconMenuContainer.addView(this.emojiconMenu);
            processChatMenu();
            this.chatExtendMenu.init();
            this.inited = true;
        }
    }

    public void init() {
        init(null);
    }

    public void setCustomEmojiconMenu(EaseEmojiconMenuBase customEmojiconMenu) {
        this.emojiconMenu = customEmojiconMenu;
    }

    public void setCustomPrimaryMenu(EaseChatPrimaryMenuBase customPrimaryMenu) {
        this.chatPrimaryMenu = customPrimaryMenu;
    }

    public EaseChatPrimaryMenuBase getPrimaryMenu() {
        return this.chatPrimaryMenu;
    }

    public EaseChatExtendMenu getExtendMenu() {
        return this.chatExtendMenu;
    }

    public EaseEmojiconMenuBase getEmojiconMenu() {
        return this.emojiconMenu;
    }

    public void registerExtendMenuItem(String name, int drawableRes, int itemId, EaseChatExtendMenu.EaseChatExtendMenuItemClickListener listener) {
        this.chatExtendMenu.registerMenuItem(name, drawableRes, itemId, listener);
    }

    public void registerExtendMenuItem(int nameRes, int drawableRes, int itemId, EaseChatExtendMenu.EaseChatExtendMenuItemClickListener listener) {
        this.chatExtendMenu.registerMenuItem(nameRes, drawableRes, itemId, listener);
    }

    protected void processChatMenu() {
        this.chatPrimaryMenu.setChatPrimaryMenuListener(new EaseChatPrimaryMenuBase.EaseChatPrimaryMenuListener() { // from class: com.easeui.widget.EaseChatInputMenu.1
            @Override // com.easeui.widget.EaseChatPrimaryMenuBase.EaseChatPrimaryMenuListener
            public void onSendBtnClicked(String content) {
                if (EaseChatInputMenu.this.listener != null) {
                    EaseChatInputMenu.this.listener.onSendMessage(content);
                }
            }

            @Override // com.easeui.widget.EaseChatPrimaryMenuBase.EaseChatPrimaryMenuListener
            public void onToggleVoiceBtnClicked() {
                EaseChatInputMenu.this.hideExtendMenuContainer();
            }

            @Override // com.easeui.widget.EaseChatPrimaryMenuBase.EaseChatPrimaryMenuListener
            public void onToggleExtendClicked() {
                EaseChatInputMenu.this.toggleMore();
            }

            @Override // com.easeui.widget.EaseChatPrimaryMenuBase.EaseChatPrimaryMenuListener
            public void onToggleEmojiconClicked() {
                EaseChatInputMenu.this.toggleEmojicon();
            }

            @Override // com.easeui.widget.EaseChatPrimaryMenuBase.EaseChatPrimaryMenuListener
            public void onEditTextClicked() {
                EaseChatInputMenu.this.hideExtendMenuContainer();
            }

            @Override // com.easeui.widget.EaseChatPrimaryMenuBase.EaseChatPrimaryMenuListener
            public boolean onPressToSpeakBtnTouch(View v, MotionEvent event) {
                return EaseChatInputMenu.this.listener != null && EaseChatInputMenu.this.listener.onPressToSpeakBtnTouch(v, event);
            }
        });
        this.emojiconMenu.setEmojiconMenuListener(new EaseEmojiconMenuBase.EaseEmojiconMenuListener() { // from class: com.easeui.widget.EaseChatInputMenu.2
            @Override // com.easeui.widget.emojicon.EaseEmojiconMenuBase.EaseEmojiconMenuListener
            public void onExpressionClicked(EaseEmojicon emojicon) {
                if (emojicon.getType() != EaseEmojicon.Type.BIG_EXPRESSION) {
                    if (emojicon.getEmojiText() != null) {
                        EaseChatInputMenu.this.chatPrimaryMenu.onEmojiconInputEvent(EaseSmileUtils.getSmiledText(EaseChatInputMenu.this.context, emojicon.getEmojiText()));
                    }
                } else if (EaseChatInputMenu.this.listener != null) {
                    EaseChatInputMenu.this.listener.onBigExpressionClicked(emojicon);
                }
            }

            @Override // com.easeui.widget.emojicon.EaseEmojiconMenuBase.EaseEmojiconMenuListener
            public void onDeleteImageClicked() {
                EaseChatInputMenu.this.chatPrimaryMenu.onEmojiconDeleteEvent();
            }
        });
    }

    public void insertText(String text) {
        getPrimaryMenu().onTextInsert(text);
    }

    protected void toggleMore() {
        if (this.chatExtendMenuContainer.getVisibility() == 8) {
            hideKeyboard();
            this.handler.postDelayed(new Runnable() { // from class: com.easeui.widget.EaseChatInputMenu.3
                @Override // java.lang.Runnable
                public void run() {
                    EaseChatInputMenu.this.chatExtendMenuContainer.setVisibility(0);
                    EaseChatInputMenu.this.chatExtendMenu.setVisibility(0);
                    EaseChatInputMenu.this.emojiconMenu.setVisibility(8);
                }
            }, 50L);
        } else if (this.emojiconMenu.getVisibility() == 0) {
            this.emojiconMenu.setVisibility(8);
            this.chatExtendMenu.setVisibility(0);
        } else {
            this.chatExtendMenuContainer.setVisibility(8);
        }
    }

    protected void toggleEmojicon() {
        if (this.chatExtendMenuContainer.getVisibility() == 8) {
            hideKeyboard();
            this.handler.postDelayed(new Runnable() { // from class: com.easeui.widget.EaseChatInputMenu.4
                @Override // java.lang.Runnable
                public void run() {
                    EaseChatInputMenu.this.chatExtendMenuContainer.setVisibility(0);
                    EaseChatInputMenu.this.chatExtendMenu.setVisibility(8);
                    EaseChatInputMenu.this.emojiconMenu.setVisibility(0);
                }
            }, 50L);
        } else if (this.emojiconMenu.getVisibility() == 0) {
            this.chatExtendMenuContainer.setVisibility(8);
            this.emojiconMenu.setVisibility(8);
        } else {
            this.chatExtendMenu.setVisibility(8);
            this.emojiconMenu.setVisibility(0);
        }
    }

    private void hideKeyboard() {
        this.chatPrimaryMenu.hideKeyboard();
    }

    public void hideExtendMenuContainer() {
        this.chatExtendMenu.setVisibility(8);
        this.emojiconMenu.setVisibility(8);
        this.chatExtendMenuContainer.setVisibility(8);
        this.chatPrimaryMenu.onExtendMenuContainerHide();
    }

    public boolean onBackPressed() {
        if (this.chatExtendMenuContainer.getVisibility() != 0) {
            return true;
        }
        hideExtendMenuContainer();
        return false;
    }

    public void setChatInputMenuListener(ChatInputMenuListener listener) {
        this.listener = listener;
    }
}
