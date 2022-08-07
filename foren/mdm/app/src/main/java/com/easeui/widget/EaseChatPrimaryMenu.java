package com.easeui.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.cdlinglu.utils.PermissionUtil;
import com.vsf2f.f2f.R;

/* loaded from: classes.dex */
public class EaseChatPrimaryMenu extends EaseChatPrimaryMenuBase implements View.OnClickListener {
    private ImageView buttonMore;
    private View buttonPressToSpeak;
    private View buttonSend;
    private View buttonSetModeKeyboard;
    private View buttonSetModeVoice;
    private ImageView closeMenu;
    private EditText editText;
    private RelativeLayout edittext_layout;
    private ImageView faceChecked;
    private ImageView faceNormal;
    private LinearLayout llychat_layout;
    private LinearLayout llymenu_layout;
    private LinearLayout llyparent_layout;
    private ImageView openMenu;

    public EaseChatPrimaryMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public EaseChatPrimaryMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EaseChatPrimaryMenu(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.ease_widget_chat_primary_menu, this);
        this.buttonSetModeKeyboard = findViewById(R.id.chat_input_keyboard);
        this.buttonSetModeKeyboard.setOnClickListener(this);
        this.buttonSetModeVoice = findViewById(R.id.chat_input_voice);
        this.buttonSetModeVoice.setOnClickListener(this);
        this.faceChecked = (ImageView) findViewById(R.id.chat_input_iv_face_checked);
        this.faceChecked.setOnClickListener(this);
        this.faceNormal = (ImageView) findViewById(R.id.chat_input_iv_face_normal);
        this.faceNormal.setOnClickListener(this);
        this.buttonMore = (ImageView) findViewById(R.id.chat_input_btn_more);
        this.buttonMore.setOnClickListener(this);
        this.closeMenu = (ImageView) findViewById(R.id.chat_input_close_menu);
        this.closeMenu.setOnClickListener(this);
        this.openMenu = (ImageView) findViewById(R.id.chat_input_open_menu);
        this.openMenu.setOnClickListener(this);
        this.buttonSend = findViewById(R.id.chat_input_btn_send);
        this.buttonSend.setOnClickListener(this);
        this.llyparent_layout = (LinearLayout) findViewById(R.id.chat_input_parent);
        this.llymenu_layout = (LinearLayout) findViewById(R.id.chat_input_llyMenu);
        this.llychat_layout = (LinearLayout) findViewById(R.id.chat_input_llyChat);
        this.edittext_layout = (RelativeLayout) findViewById(R.id.chat_input_llyEdit);
        this.editText = (EditText) findViewById(R.id.chat_input_edit);
        this.editText.requestFocus();
        this.editText.addTextChangedListener(new TextWatcher() { // from class: com.easeui.widget.EaseChatPrimaryMenu.1
            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString().trim())) {
                    EaseChatPrimaryMenu.this.buttonMore.setVisibility(8);
                    EaseChatPrimaryMenu.this.buttonSend.setVisibility(0);
                    return;
                }
                EaseChatPrimaryMenu.this.buttonMore.setVisibility(0);
                EaseChatPrimaryMenu.this.buttonSend.setVisibility(8);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }
        });
        this.editText.setOnTouchListener(new View.OnTouchListener() { // from class: com.easeui.widget.EaseChatPrimaryMenu.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == 1) {
                    EaseChatPrimaryMenu.this.faceNormal.setVisibility(0);
                    EaseChatPrimaryMenu.this.faceChecked.setVisibility(8);
                    if (EaseChatPrimaryMenu.this.listener != null) {
                        EaseChatPrimaryMenu.this.listener.onEditTextClicked();
                    }
                }
                return false;
            }
        });
        this.buttonPressToSpeak = findViewById(R.id.chat_input_press_to_speak);
        this.buttonPressToSpeak.setOnTouchListener(new View.OnTouchListener() { // from class: com.easeui.widget.EaseChatPrimaryMenu.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                if (EaseChatPrimaryMenu.this.listener != null) {
                    return EaseChatPrimaryMenu.this.listener.onPressToSpeakBtnTouch(v, event);
                }
                return false;
            }
        });
    }

    public void setPressToSpeakRecorderView(EaseVoiceRecorderView voiceRecorderView) {
    }

    @Override // com.easeui.widget.EaseChatPrimaryMenuBase
    public void onEmojiconInputEvent(CharSequence emojiContent) {
        this.editText.append(emojiContent);
    }

    @Override // com.easeui.widget.EaseChatPrimaryMenuBase
    public void onEmojiconDeleteEvent() {
        if (!TextUtils.isEmpty(this.editText.getText())) {
            this.editText.dispatchKeyEvent(new KeyEvent(0L, 0L, 0, 67, 0, 0, 0, 0, 6));
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chat_input_open_menu /* 2131756494 */:
                this.llyparent_layout.setAnimation(moveToViewBottom());
                this.openMenu.setVisibility(8);
                this.closeMenu.setVisibility(0);
                this.llychat_layout.setVisibility(8);
                this.llymenu_layout.setVisibility(0);
                this.llyparent_layout.setAnimation(moveToViewLocation());
                return;
            case R.id.chat_input_close_menu /* 2131756495 */:
                this.llyparent_layout.setAnimation(moveToViewBottom());
                this.openMenu.setVisibility(0);
                this.closeMenu.setVisibility(8);
                this.llychat_layout.setVisibility(0);
                this.llymenu_layout.setVisibility(8);
                this.llyparent_layout.setAnimation(moveToViewLocation());
                return;
            case R.id.chat_input_llyChat /* 2131756496 */:
            case R.id.chat_input_press_to_speak /* 2131756499 */:
            case R.id.chat_input_llyEdit /* 2131756500 */:
            default:
                return;
            case R.id.chat_input_voice /* 2131756497 */:
                if (PermissionUtil.getAudioPermissions(this.activity, 111)) {
                    setModeVoice();
                    showNormalFaceImage();
                    if (this.listener != null) {
                        this.listener.onToggleVoiceBtnClicked();
                        return;
                    }
                    return;
                }
                return;
            case R.id.chat_input_keyboard /* 2131756498 */:
                setModeKeyboard();
                showNormalFaceImage();
                this.inputManager.showSoftInput(this.editText, 0);
                if (this.listener != null) {
                    this.listener.onToggleVoiceBtnClicked();
                    return;
                }
                return;
            case R.id.chat_input_edit /* 2131756501 */:
                this.faceNormal.setVisibility(0);
                this.faceChecked.setVisibility(8);
                if (this.listener != null) {
                    this.listener.onEditTextClicked();
                    return;
                }
                return;
            case R.id.chat_input_iv_face_normal /* 2131756502 */:
                break;
            case R.id.chat_input_iv_face_checked /* 2131756503 */:
                this.inputManager.showSoftInput(this.editText, 0);
                break;
            case R.id.chat_input_btn_more /* 2131756504 */:
                this.buttonSetModeVoice.setVisibility(0);
                this.buttonSetModeKeyboard.setVisibility(8);
                this.edittext_layout.setVisibility(0);
                this.buttonPressToSpeak.setVisibility(8);
                showNormalFaceImage();
                if (this.listener != null) {
                    this.listener.onToggleExtendClicked();
                    return;
                }
                return;
            case R.id.chat_input_btn_send /* 2131756505 */:
                if (this.listener != null) {
                    String s = this.editText.getText().toString();
                    this.editText.setText("");
                    this.listener.onSendBtnClicked(s);
                    return;
                }
                return;
        }
        toggleFaceImage();
        if (this.listener != null) {
            this.listener.onToggleEmojiconClicked();
        }
    }

    public static TranslateAnimation moveToViewBottom() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
        mHiddenAction.setDuration(1000L);
        return mHiddenAction;
    }

    public static TranslateAnimation moveToViewLocation() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
        mHiddenAction.setDuration(200L);
        return mHiddenAction;
    }

    protected void setModeVoice() {
        hideKeyboard();
        this.edittext_layout.setVisibility(8);
        this.buttonSetModeVoice.setVisibility(8);
        this.buttonSetModeKeyboard.setVisibility(0);
        this.buttonSend.setVisibility(8);
        this.buttonMore.setVisibility(0);
        this.buttonPressToSpeak.setVisibility(0);
        this.faceNormal.setVisibility(0);
        this.faceChecked.setVisibility(8);
    }

    protected void setModeKeyboard() {
        this.edittext_layout.setVisibility(0);
        this.buttonSetModeKeyboard.setVisibility(8);
        this.buttonSetModeVoice.setVisibility(0);
        this.editText.requestFocus();
        this.buttonPressToSpeak.setVisibility(8);
        if (TextUtils.isEmpty(this.editText.getText())) {
            this.buttonMore.setVisibility(0);
            this.buttonSend.setVisibility(8);
            return;
        }
        this.buttonMore.setVisibility(8);
        this.buttonSend.setVisibility(0);
    }

    protected void toggleFaceImage() {
        if (this.faceNormal.getVisibility() == 0) {
            showSelectedFaceImage();
        } else {
            showNormalFaceImage();
        }
    }

    private void showNormalFaceImage() {
        this.faceNormal.setVisibility(0);
        this.faceChecked.setVisibility(8);
    }

    private void showSelectedFaceImage() {
        this.faceNormal.setVisibility(8);
        this.faceChecked.setVisibility(0);
    }

    @Override // com.easeui.widget.EaseChatPrimaryMenuBase
    public void onExtendMenuContainerHide() {
        showNormalFaceImage();
    }

    @Override // com.easeui.widget.EaseChatPrimaryMenuBase
    public void onTextInsert(CharSequence text) {
        this.editText.getEditableText().insert(this.editText.getSelectionStart(), text);
        setModeKeyboard();
    }

    @Override // com.easeui.widget.EaseChatPrimaryMenuBase
    public EditText getEditText() {
        return this.editText;
    }

    @Override // com.easeui.widget.EaseChatPrimaryMenuBase
    public ImageView getBtnMOre() {
        return this.buttonMore;
    }

    @Override // com.easeui.widget.EaseChatPrimaryMenuBase
    public void showMenu(boolean show) {
        LinearLayout llyMenu_btn = (LinearLayout) findViewById(R.id.chat_input_switch_menu);
        if (show) {
            llyMenu_btn.setVisibility(0);
            onClick(this.openMenu);
            return;
        }
        llyMenu_btn.setVisibility(8);
        onClick(this.closeMenu);
    }
}
