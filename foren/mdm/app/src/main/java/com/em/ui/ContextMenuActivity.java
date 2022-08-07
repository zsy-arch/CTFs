package com.em.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.easeui.EaseConstant;
import com.hy.frame.util.HyUtil;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.chat.MessageEncoder;
import com.hyphenate.exceptions.HyphenateException;
import com.vsf2f.f2f.R;

/* loaded from: classes.dex */
public class ContextMenuActivity extends BaseActivity {
    public static final int RESULT_CODE_CALLPHONE = 6;
    public static final int RESULT_CODE_COLLECT = 4;
    public static final int RESULT_CODE_COPY = 1;
    public static final int RESULT_CODE_DELETE = 2;
    public static final int RESULT_CODE_FORWARD = 3;
    public static final int RESULT_CODE_OPENURL = 5;
    public static final int RESULT_CODE_SENDRECALL = 7;
    private EMMessage message;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.em.ui.BaseActivity, com.easeui.ui.EaseBaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        View v;
        super.onCreate(savedInstanceState);
        this.message = (EMMessage) getIntent().getParcelableExtra("message");
        String extType = this.message.getStringAttribute("type", "");
        try {
            int type = this.message.getType().ordinal();
            if (type == EMMessage.Type.TXT.ordinal()) {
                if (this.message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_VIDEO_CALL, false) || this.message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_VOICE_CALL, false)) {
                    setContentView(R.layout.em_context_menu_for_location);
                } else if (this.message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_BIG_EXPRESSION, false)) {
                    setContentView(R.layout.em_context_menu_for_image);
                } else {
                    try {
                        if (TextUtils.isEmpty(extType)) {
                            this.message.getStringAttribute(MessageEncoder.ATTR_EXT);
                        }
                        if (TextUtils.isEmpty(extType)) {
                            throw new HyphenateException();
                        }
                        setContentView(R.layout.em_context_menu_for_cmd);
                    } catch (HyphenateException e) {
                        setContentView(R.layout.em_context_menu_for_text);
                        EMTextMessageBody txtBody = (EMTextMessageBody) this.message.getBody();
                        if (txtBody != null) {
                            String str = txtBody.getMessage();
                            final String hasMobile = HyUtil.cutMobile(str);
                            if (!TextUtils.isEmpty(hasMobile)) {
                                TextView phone = (TextView) findViewById(R.id.has_phone);
                                phone.setText("拨打号码：" + hasMobile);
                                phone.setVisibility(0);
                                phone.setOnClickListener(new View.OnClickListener() { // from class: com.em.ui.ContextMenuActivity.1
                                    @Override // android.view.View.OnClickListener
                                    public void onClick(View view) {
                                        ContextMenuActivity.this.callphone(hasMobile);
                                    }
                                });
                            }
                            final String hasUrl = HyUtil.cutUrl(str);
                            if (!TextUtils.isEmpty(hasUrl)) {
                                TextView url = (TextView) findViewById(R.id.has_url);
                                url.setText("打开网址：" + hasUrl);
                                url.setVisibility(0);
                                url.setOnClickListener(new View.OnClickListener() { // from class: com.em.ui.ContextMenuActivity.2
                                    @Override // android.view.View.OnClickListener
                                    public void onClick(View view) {
                                        ContextMenuActivity.this.openurl(hasUrl);
                                    }
                                });
                            }
                        }
                    }
                }
            } else if (type == EMMessage.Type.LOCATION.ordinal()) {
                setContentView(R.layout.em_context_menu_for_location);
            } else if (type == EMMessage.Type.IMAGE.ordinal()) {
                setContentView(R.layout.em_context_menu_for_image);
            } else if (type == EMMessage.Type.VOICE.ordinal()) {
                setContentView(R.layout.em_context_menu_for_voice);
            } else if (type == EMMessage.Type.VIDEO.ordinal()) {
                setContentView(R.layout.em_context_menu_for_video);
            } else if (type == EMMessage.Type.FILE.ordinal()) {
                setContentView(R.layout.em_context_menu_for_file);
            } else if (type == EMMessage.Type.CMD.ordinal()) {
                setContentView(R.layout.em_context_menu_for_cmd);
            }
            if (this.message.direct() == EMMessage.Direct.SEND && !extType.equals("redPacket") && System.currentTimeMillis() - this.message.getMsgTime() < 120000 && (v = findViewById(R.id.recall)) != null) {
                v.setVisibility(0);
            }
        } catch (NullPointerException e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    public void recall(View view) {
        setResult(7);
        finish();
    }

    public void openurl(String str) {
        Intent intent = new Intent();
        intent.putExtra("str", str);
        setResult(5, intent);
        finish();
    }

    public void callphone(String str) {
        Intent intent = new Intent();
        intent.putExtra("str", str);
        setResult(6, intent);
        finish();
    }

    public void collect(View view) {
        setResult(4);
        finish();
    }

    public void copy(View view) {
        setResult(1);
        finish();
    }

    public void delete(View view) {
        setResult(2);
        finish();
    }

    public void forward(View view) {
        setResult(3);
        finish();
    }
}
