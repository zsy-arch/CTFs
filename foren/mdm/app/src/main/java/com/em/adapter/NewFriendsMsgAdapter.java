package com.em.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.TimeUtil;
import com.em.db.InviteMessgeDao;
import com.em.domain.InviteMessage;
import com.em.ui.NewFriendsMsgActivity;
import com.em.ui.UserProfileActivity;
import com.hy.frame.util.Constant;
import com.hy.frame.util.MyLog;
import com.hyphenate.chat.EMClient;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.MainActivity;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import com.yolanda.nohttp.cache.CacheDisk;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class NewFriendsMsgAdapter extends ArrayAdapter<InviteMessage> {
    private Context context;
    private InviteMessgeDao messgeDao;

    public NewFriendsMsgAdapter(Context context, int textViewResourceId, List<InviteMessage> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.messgeDao = new InviteMessgeDao(context);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(this.context, R.layout.em_row_invite_msg, null);
            holder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            holder.reason = (TextView) convertView.findViewById(R.id.message);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.agree = (TextView) convertView.findViewById(R.id.agree);
            holder.refuse = (TextView) convertView.findViewById(R.id.refuse);
            holder.groupContainer = (LinearLayout) convertView.findViewById(R.id.ll_group);
            holder.groupname = (TextView) convertView.findViewById(R.id.tv_groupName);
            holder.containerView = (RelativeLayout) convertView.findViewById(R.id.rl_container);
            holder.state = (TextView) convertView.findViewById(R.id.tv_state);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String str1 = this.context.getResources().getString(R.string.has_agreed_to_your_friend_request);
        this.context.getResources().getString(R.string.request_to_add_you_as_a_friend);
        this.context.getResources().getString(R.string.apply_to_the_group_of);
        String str5 = this.context.getResources().getString(R.string.has_agreed_to);
        String str6 = this.context.getResources().getString(R.string.has_ignore_to);
        this.context.getResources().getString(R.string.invite_join_group);
        String str9 = this.context.getResources().getString(R.string.accept_join_group);
        String str10 = this.context.getResources().getString(R.string.refuse_join_group);
        final InviteMessage msg = getItem(position);
        holder.containerView.setOnClickListener(new View.OnClickListener() { // from class: com.em.adapter.NewFriendsMsgAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                String username = msg.getFrom();
                Intent intent = new Intent(NewFriendsMsgAdapter.this.context, UserProfileActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("username", username);
                intent.putExtra(Constant.BUNDLE, bundle);
                NewFriendsMsgAdapter.this.context.startActivity(intent);
            }
        });
        if (msg != null) {
            holder.agree.setVisibility(4);
            holder.refuse.setVisibility(4);
            if (msg.getGroupId() != null) {
                holder.groupname.setText(msg.getGroupName());
                holder.groupContainer.setVisibility(0);
            } else {
                holder.groupContainer.setVisibility(8);
            }
            try {
                JSONObject jsonObject = new JSONObject(msg.getReason());
                ComUtil.display(getContext(), holder.avatar, jsonObject.optString(CacheDisk.HEAD), R.mipmap.def_head);
                holder.name.setText(jsonObject.optString("nick"));
            } catch (Exception e) {
                e.printStackTrace();
                holder.name.setText(msg.getFrom());
            }
            holder.reason.setText(msg.getReason());
            holder.time.setText(TimeUtil.formatChatTime(msg.getTime()));
            if (msg.getStatus() == InviteMessage.InviteMesageStatus.BEAGREED) {
                holder.state.setVisibility(4);
                holder.reason.setText(str1);
                MainActivity.getInstance().setRefreshFriend();
            } else if (msg.getStatus() == InviteMessage.InviteMesageStatus.BEINVITEED || msg.getStatus() == InviteMessage.InviteMesageStatus.BEAPPLYED || msg.getStatus() == InviteMessage.InviteMesageStatus.GROUPINVITATION) {
                holder.state.setVisibility(4);
                holder.agree.setVisibility(0);
                holder.refuse.setVisibility(0);
                holder.agree.setOnClickListener(new View.OnClickListener() { // from class: com.em.adapter.NewFriendsMsgAdapter.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        NewFriendsMsgAdapter.this.acceptInvitation(holder.agree, holder.refuse, holder.state, msg);
                    }
                });
                holder.refuse.setOnClickListener(new View.OnClickListener() { // from class: com.em.adapter.NewFriendsMsgAdapter.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        NewFriendsMsgAdapter.this.refuseInvitation(holder.agree, holder.refuse, holder.state, msg);
                    }
                });
            } else if (msg.getStatus() == InviteMessage.InviteMesageStatus.AGREED) {
                holder.state.setVisibility(0);
                holder.state.setText(str5);
            } else if (msg.getStatus() == InviteMessage.InviteMesageStatus.REFUSED) {
                holder.state.setVisibility(0);
                holder.state.setText(str6);
            } else if (msg.getStatus() == InviteMessage.InviteMesageStatus.GROUPINVITATION_ACCEPTED) {
                holder.state.setVisibility(0);
                holder.state.setText(msg.getGroupInviter() + str9 + msg.getGroupName());
            } else if (msg.getStatus() == InviteMessage.InviteMesageStatus.GROUPINVITATION_DECLINED) {
                holder.state.setVisibility(0);
                holder.state.setText(msg.getGroupInviter() + str10 + msg.getGroupName());
            }
        }
        return convertView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void acceptInvitation(final TextView buttonAgree, final TextView buttonRefuse, final TextView stateView, final InviteMessage msg) {
        final ProgressDialog pd = new ProgressDialog(this.context);
        final String str2 = this.context.getResources().getString(R.string.has_agreed_to);
        final String str3 = this.context.getResources().getString(R.string.agree_with_failure);
        ThreadPool.newThreadPool(new Runnable() { // from class: com.em.adapter.NewFriendsMsgAdapter.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    MyLog.e("BEINVITEED be friends " + msg.getStatus());
                    if (msg.getStatus() == InviteMessage.InviteMesageStatus.BEINVITEED) {
                        MyLog.e("accept be friends " + msg.getStatus());
                        ((NewFriendsMsgActivity) NewFriendsMsgAdapter.this.getContext()).addFriend(msg.getFrom());
                        EMClient.getInstance().contactManager().acceptInvitation(msg.getFrom());
                    } else if (msg.getStatus() == InviteMessage.InviteMesageStatus.BEAPPLYED) {
                        EMClient.getInstance().groupManager().acceptApplication(msg.getFrom(), msg.getGroupId());
                    } else if (msg.getStatus() == InviteMessage.InviteMesageStatus.GROUPINVITATION) {
                        EMClient.getInstance().groupManager().acceptInvitation(msg.getGroupId(), msg.getGroupInviter());
                    }
                    msg.setStatus(InviteMessage.InviteMesageStatus.AGREED);
                    ContentValues values = new ContentValues();
                    values.put("status", Integer.valueOf(msg.getStatus().ordinal()));
                    NewFriendsMsgAdapter.this.messgeDao.updateMessage(msg.getId(), values);
                    ((Activity) NewFriendsMsgAdapter.this.context).runOnUiThread(new Runnable() { // from class: com.em.adapter.NewFriendsMsgAdapter.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            pd.dismiss();
                            stateView.setVisibility(0);
                            stateView.setText(str2);
                            buttonAgree.setVisibility(4);
                            buttonRefuse.setVisibility(4);
                        }
                    });
                } catch (Exception e) {
                    ((Activity) NewFriendsMsgAdapter.this.context).runOnUiThread(new Runnable() { // from class: com.em.adapter.NewFriendsMsgAdapter.4.2
                        @Override // java.lang.Runnable
                        public void run() {
                            pd.dismiss();
                            Toast.makeText(NewFriendsMsgAdapter.this.context, str3 + e.getMessage(), 0).show();
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refuseInvitation(final TextView buttonAgree, final TextView buttonRefuse, final TextView stateView, final InviteMessage msg) {
        final ProgressDialog pd = new ProgressDialog(this.context);
        String str1 = this.context.getResources().getString(R.string.refuse_with);
        final String str2 = this.context.getResources().getString(R.string.has_refused_to);
        final String str3 = this.context.getResources().getString(R.string.refuse_with_failure);
        pd.setMessage(str1);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        ThreadPool.newThreadPool(new Runnable() { // from class: com.em.adapter.NewFriendsMsgAdapter.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (msg.getStatus() == InviteMessage.InviteMesageStatus.BEINVITEED) {
                        EMClient.getInstance().contactManager().declineInvitation(msg.getFrom());
                    } else if (msg.getStatus() == InviteMessage.InviteMesageStatus.BEAPPLYED) {
                        EMClient.getInstance().groupManager().declineApplication(msg.getFrom(), msg.getGroupId(), "");
                    } else if (msg.getStatus() == InviteMessage.InviteMesageStatus.GROUPINVITATION) {
                        EMClient.getInstance().groupManager().declineInvitation(msg.getGroupId(), msg.getGroupInviter(), "");
                    }
                    msg.setStatus(InviteMessage.InviteMesageStatus.REFUSED);
                    ContentValues values = new ContentValues();
                    values.put("status", Integer.valueOf(msg.getStatus().ordinal()));
                    NewFriendsMsgAdapter.this.messgeDao.updateMessage(msg.getId(), values);
                    ((Activity) NewFriendsMsgAdapter.this.context).runOnUiThread(new Runnable() { // from class: com.em.adapter.NewFriendsMsgAdapter.5.1
                        @Override // java.lang.Runnable
                        public void run() {
                            pd.dismiss();
                            stateView.setVisibility(0);
                            stateView.setText(str2);
                            buttonAgree.setVisibility(4);
                            buttonRefuse.setVisibility(4);
                        }
                    });
                } catch (Exception e) {
                    ((Activity) NewFriendsMsgAdapter.this.context).runOnUiThread(new Runnable() { // from class: com.em.adapter.NewFriendsMsgAdapter.5.2
                        @Override // java.lang.Runnable
                        public void run() {
                            pd.dismiss();
                            Toast.makeText(NewFriendsMsgAdapter.this.context, str3 + e.getMessage(), 0).show();
                        }
                    });
                }
            }
        });
    }

    /* loaded from: classes.dex */
    private static class ViewHolder {
        TextView agree;
        ImageView avatar;
        RelativeLayout containerView;
        LinearLayout groupContainer;
        TextView groupname;
        TextView name;
        TextView reason;
        TextView refuse;
        TextView state;
        TextView time;

        private ViewHolder() {
        }
    }
}
