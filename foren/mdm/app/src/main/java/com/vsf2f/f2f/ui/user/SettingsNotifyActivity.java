package com.vsf2f.f2f.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.cdlinglu.common.BaseActivity;
import com.easeui.widget.EaseSwitchButton;
import com.em.DemoHelper;
import com.em.DemoModel;
import com.em.ui.DiagnoseActivity;
import com.em.ui.SetServersActivity;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.util.EMLog;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class SettingsNotifyActivity extends BaseActivity {
    private EMOptions chatOptions;
    private EaseSwitchButton customServerSwitch;
    private EaseSwitchButton groupSwitch;
    private LinearLayout notifyGroup;
    private LinearLayout notifyMsg;
    private EaseSwitchButton notifySwitch;
    private EaseSwitchButton ownerLeaveSwitch;
    private DemoModel settingsModel;
    private EaseSwitchButton soundSwitch;
    private EaseSwitchButton speakerSwitch;
    private EaseSwitchButton switch_adaptive_video_encode;
    private EaseSwitchButton switch_auto_accept_group_invitation;
    private EaseSwitchButton switch_delete_msg_when_exit_group;
    private EaseSwitchButton vibrateSwitch;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_settings_notify;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.settings_msg_notify, 0);
        this.notifyMsg = (LinearLayout) getView(R.id.ll_msg_notification);
        this.notifyGroup = (LinearLayout) getView(R.id.ll_msg_groupnotify);
        this.notifySwitch = (EaseSwitchButton) getView(R.id.switch_notification);
        this.soundSwitch = (EaseSwitchButton) getView(R.id.switch_sound);
        this.groupSwitch = (EaseSwitchButton) getView(R.id.switch_sound_group);
        this.vibrateSwitch = (EaseSwitchButton) getView(R.id.switch_vibrate);
        this.speakerSwitch = (EaseSwitchButton) getView(R.id.switch_speaker);
        this.ownerLeaveSwitch = (EaseSwitchButton) getView(R.id.switch_owner_leave);
        this.switch_adaptive_video_encode = (EaseSwitchButton) getView(R.id.switch_adaptive_video_encode);
        this.switch_auto_accept_group_invitation = (EaseSwitchButton) getView(R.id.switch_auto_accept_group_invitation);
        this.customServerSwitch = (EaseSwitchButton) getViewAndClick(R.id.switch_custom_server);
        this.switch_delete_msg_when_exit_group = (EaseSwitchButton) getViewAndClick(R.id.switch_delete_msg_when_exit_group);
        setOnClickListener(R.id.rl_switch_notification);
        setOnClickListener(R.id.rl_switch_sound);
        setOnClickListener(R.id.rl_switch_sound_group);
        setOnClickListener(R.id.rl_switch_vibrate);
        setOnClickListener(R.id.rl_switch_speaker);
        setOnClickListener(R.id.rl_switch_auto_accept_group_invitation);
        setOnClickListener(R.id.rl_switch_adaptive_video_encode);
        setOnClickListener(R.id.rl_custom_server);
        setOnClickListener(R.id.ll_diagnose);
        setOnClickListener(R.id.rl_switch_chatroom_owner_leave);
        setOnClickListener(R.id.rl_switch_delete_msg_when_exit_group);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.settingsModel = DemoHelper.getInstance().getModel();
        this.chatOptions = EMClient.getInstance().getOptions();
        if (this.settingsModel.getSettingMsgNotification()) {
            this.notifySwitch.openSwitch();
        } else {
            this.notifyMsg.setVisibility(8);
            this.notifySwitch.closeSwitch();
        }
        if (this.settingsModel.getSettingMsgNotification()) {
            this.notifySwitch.openSwitch();
        } else {
            this.notifyMsg.setVisibility(8);
            this.notifySwitch.closeSwitch();
        }
        if (this.settingsModel.getSettingMsgSound()) {
            this.soundSwitch.openSwitch();
            this.notifyGroup.setVisibility(0);
        } else {
            this.soundSwitch.closeSwitch();
            this.notifyGroup.setVisibility(8);
        }
        if (this.settingsModel.getSettingGroupMsgSound()) {
            this.groupSwitch.openSwitch();
        } else {
            this.groupSwitch.closeSwitch();
        }
        if (this.settingsModel.getSettingMsgVibrate()) {
            this.vibrateSwitch.openSwitch();
        } else {
            this.vibrateSwitch.closeSwitch();
        }
        if (this.settingsModel.getSettingMsgSpeaker()) {
            this.speakerSwitch.openSwitch();
        } else {
            this.speakerSwitch.closeSwitch();
        }
        if (this.settingsModel.isChatroomOwnerLeaveAllowed()) {
            this.ownerLeaveSwitch.openSwitch();
        } else {
            this.ownerLeaveSwitch.closeSwitch();
        }
        if (this.settingsModel.isDeleteMessagesAsExitGroup()) {
            this.switch_delete_msg_when_exit_group.openSwitch();
        } else {
            this.switch_delete_msg_when_exit_group.closeSwitch();
        }
        if (this.settingsModel.isAutoAcceptGroupInvitation()) {
            this.switch_auto_accept_group_invitation.openSwitch();
        } else {
            this.switch_auto_accept_group_invitation.closeSwitch();
        }
        if (this.settingsModel.isAdaptiveVideoEncode()) {
            this.switch_adaptive_video_encode.openSwitch();
            EMClient.getInstance().callManager().getCallOptions().enableFixedVideoResolution(false);
        } else {
            this.switch_adaptive_video_encode.closeSwitch();
            EMClient.getInstance().callManager().getCallOptions().enableFixedVideoResolution(true);
        }
        if (this.settingsModel.isCustomServerEnable()) {
            this.customServerSwitch.openSwitch();
        } else {
            this.customServerSwitch.closeSwitch();
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
    }

    @Override // com.hy.frame.common.BaseActivity, android.view.View.OnClickListener
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_switch_groupNotify /* 2131755577 */:
                if (this.notifySwitch.isSwitchOpen()) {
                    this.notifySwitch.closeSwitch();
                    this.notifyMsg.setVisibility(8);
                    this.settingsModel.setSettingMsgNotification(false);
                    return;
                }
                this.notifySwitch.openSwitch();
                this.notifyMsg.setVisibility(0);
                this.settingsModel.setSettingMsgNotification(true);
                return;
            case R.id.rl_switch_notification /* 2131755579 */:
                if (this.notifySwitch.isSwitchOpen()) {
                    this.notifySwitch.closeSwitch();
                    this.notifyMsg.setVisibility(8);
                    this.settingsModel.setSettingMsgNotification(false);
                    return;
                }
                this.notifySwitch.openSwitch();
                this.notifyMsg.setVisibility(0);
                this.settingsModel.setSettingMsgNotification(true);
                return;
            case R.id.rl_switch_sound /* 2131755582 */:
                if (this.soundSwitch.isSwitchOpen()) {
                    this.soundSwitch.closeSwitch();
                    this.settingsModel.setSettingMsgSound(false);
                    this.notifyGroup.setVisibility(8);
                    return;
                }
                this.soundSwitch.openSwitch();
                this.settingsModel.setSettingMsgSound(true);
                this.notifyGroup.setVisibility(0);
                return;
            case R.id.rl_switch_sound_group /* 2131755585 */:
                if (this.groupSwitch.isSwitchOpen()) {
                    this.groupSwitch.closeSwitch();
                    this.settingsModel.setSettingGroupMsgSound(false);
                    return;
                }
                this.groupSwitch.openSwitch();
                this.settingsModel.setSettingGroupMsgSound(true);
                return;
            case R.id.rl_switch_vibrate /* 2131755587 */:
                if (this.vibrateSwitch.isSwitchOpen()) {
                    this.vibrateSwitch.closeSwitch();
                    this.settingsModel.setSettingMsgVibrate(false);
                    return;
                }
                this.vibrateSwitch.openSwitch();
                this.settingsModel.setSettingMsgVibrate(true);
                return;
            case R.id.rl_switch_speaker /* 2131755589 */:
                if (this.speakerSwitch.isSwitchOpen()) {
                    this.speakerSwitch.closeSwitch();
                    this.settingsModel.setSettingMsgSpeaker(false);
                    return;
                }
                this.speakerSwitch.openSwitch();
                this.settingsModel.setSettingMsgSpeaker(true);
                return;
            case R.id.rl_custom_server /* 2131755591 */:
                startActivity(new Intent(this, SetServersActivity.class));
                return;
            case R.id.switch_custom_server /* 2131755592 */:
                if (this.customServerSwitch.isSwitchOpen()) {
                    this.customServerSwitch.closeSwitch();
                    this.settingsModel.enableCustomServer(false);
                    return;
                }
                this.customServerSwitch.openSwitch();
                this.settingsModel.enableCustomServer(true);
                return;
            case R.id.ll_diagnose /* 2131755593 */:
                startActivity(new Intent(this, DiagnoseActivity.class));
                return;
            case R.id.rl_switch_chatroom_owner_leave /* 2131755594 */:
                if (this.ownerLeaveSwitch.isSwitchOpen()) {
                    this.ownerLeaveSwitch.closeSwitch();
                    this.settingsModel.allowChatroomOwnerLeave(false);
                    this.chatOptions.allowChatroomOwnerLeave(false);
                    return;
                }
                this.ownerLeaveSwitch.openSwitch();
                this.settingsModel.allowChatroomOwnerLeave(true);
                this.chatOptions.allowChatroomOwnerLeave(true);
                return;
            case R.id.rl_switch_delete_msg_when_exit_group /* 2131755596 */:
                if (this.switch_delete_msg_when_exit_group.isSwitchOpen()) {
                    this.switch_delete_msg_when_exit_group.closeSwitch();
                    this.settingsModel.setDeleteMessagesAsExitGroup(false);
                    this.chatOptions.setDeleteMessagesAsExitGroup(false);
                    return;
                }
                this.switch_delete_msg_when_exit_group.openSwitch();
                this.settingsModel.setDeleteMessagesAsExitGroup(true);
                this.chatOptions.setDeleteMessagesAsExitGroup(true);
                return;
            case R.id.rl_switch_auto_accept_group_invitation /* 2131755598 */:
                if (this.switch_auto_accept_group_invitation.isSwitchOpen()) {
                    this.switch_auto_accept_group_invitation.closeSwitch();
                    this.settingsModel.setAutoAcceptGroupInvitation(false);
                    this.chatOptions.setAutoAcceptGroupInvitation(false);
                    return;
                }
                this.switch_auto_accept_group_invitation.openSwitch();
                this.settingsModel.setAutoAcceptGroupInvitation(true);
                this.chatOptions.setAutoAcceptGroupInvitation(true);
                return;
            case R.id.rl_switch_adaptive_video_encode /* 2131755600 */:
                EMLog.d("switch", "" + (!this.switch_adaptive_video_encode.isSwitchOpen()));
                if (this.switch_adaptive_video_encode.isSwitchOpen()) {
                    this.switch_adaptive_video_encode.closeSwitch();
                    this.settingsModel.setAdaptiveVideoEncode(false);
                    EMClient.getInstance().callManager().getCallOptions().enableFixedVideoResolution(true);
                    return;
                }
                this.switch_adaptive_video_encode.openSwitch();
                this.settingsModel.setAdaptiveVideoEncode(true);
                EMClient.getInstance().callManager().getCallOptions().enableFixedVideoResolution(false);
                return;
            case R.id.head_vLeft /* 2131756685 */:
                onLeftClick();
                return;
            default:
                return;
        }
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (DemoHelper.getInstance().isConflict) {
            outState.putBoolean("isConflict", true);
        }
    }
}
