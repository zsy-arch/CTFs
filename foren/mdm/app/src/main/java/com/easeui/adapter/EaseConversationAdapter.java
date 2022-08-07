package com.easeui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cdlinglu.utils.TimeUtil;
import com.easeui.domain.EaseUser;
import com.easeui.model.EaseAtMessageHelper;
import com.easeui.utils.EaseCommonUtils;
import com.easeui.utils.EaseSmileUtils;
import com.easeui.utils.EaseUserUtils;
import com.easeui.widget.EaseConversationList;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.GroupBean;
import com.vsf2f.f2f.ui.utils.Constant;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class EaseConversationAdapter extends ArrayAdapter<EMConversation> {
    private ConversationFilter conversationFilter;
    private List<EMConversation> conversationList;
    private List<EMConversation> copyConversationList = new ArrayList();
    private EaseConversationList.EaseConversationListHelper cvsListHelper;
    private boolean notifyByFilter;
    protected int primaryColor;
    protected int primarySize;
    protected int secondaryColor;
    protected int secondarySize;
    protected int timeColor;
    protected float timeSize;

    public EaseConversationAdapter(Context context, int resource, List<EMConversation> objects) {
        super(context, resource, objects);
        this.conversationList = objects;
        this.copyConversationList.addAll(objects);
    }

    public void setDataResouce(List<EMConversation> objects) {
        this.conversationList = objects;
        this.copyConversationList = new ArrayList();
        this.copyConversationList.addAll(objects);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public int getCount() {
        return this.conversationList.size();
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public EMConversation getItem(int arg0) {
        if (arg0 < this.conversationList.size()) {
            return this.conversationList.get(arg0);
        }
        return null;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_row_chat_history, parent, false);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        if (holder == null) {
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.unreadLabel = (TextView) convertView.findViewById(R.id.unread_msg_number);
            holder.message = (TextView) convertView.findViewById(R.id.message);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            holder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            holder.group_dot = (ImageView) convertView.findViewById(R.id.group_dot);
            holder.msgState = convertView.findViewById(R.id.msg_state);
            holder.list_itease_layout = (RelativeLayout) convertView.findViewById(R.id.list_itease_layout);
            holder.motioned = (TextView) convertView.findViewById(R.id.mentioned);
            convertView.setTag(holder);
        }
        holder.list_itease_layout.setBackgroundResource(R.drawable.ease_mm_listitem);
        EMConversation conversation = getItem(position);
        String username = conversation.conversationId();
        if (conversation.getType() == EMConversation.EMConversationType.GroupChat) {
            if (EaseAtMessageHelper.get().hasAtMeMsg(conversation.conversationId())) {
                holder.motioned.setVisibility(0);
            } else {
                holder.motioned.setVisibility(8);
            }
            GroupBean group = EaseUserUtils.getGroupInfo(username);
            EaseUserUtils.setGroupPic(group, holder.avatar);
            EaseUserUtils.setGroupName(group, holder.name);
        } else {
            if (username.equals("admin")) {
                holder.avatar.setImageResource(R.drawable.icon_chat_admin);
                holder.name.setText(R.string.system_notice);
            } else if (username.equals(Constant.COM_SOURCE_USER)) {
                holder.avatar.setImageResource(R.drawable.icon_chat_service);
                holder.name.setText(R.string.system_service);
            } else {
                EaseUserUtils.setUserAvatar(getContext(), username, holder.avatar);
                EaseUserUtils.setUserNick(username, holder.name);
            }
            holder.motioned.setVisibility(8);
        }
        if (conversation.getUnreadMsgCount() > 0) {
            if (conversation.getUnreadMsgCount() > 99) {
                holder.unreadLabel.setText("99+");
            } else {
                holder.unreadLabel.setText(String.valueOf(conversation.getUnreadMsgCount()));
            }
            holder.unreadLabel.setVisibility(0);
        } else {
            holder.unreadLabel.setVisibility(4);
        }
        if (conversation.getAllMsgCount() != 0) {
            EMMessage lastMessage = conversation.getLastMessage();
            String content = null;
            if (this.cvsListHelper != null) {
                content = this.cvsListHelper.onSetItemSecondaryText(lastMessage);
            }
            holder.message.setText(EaseSmileUtils.getSmiledSmall(getContext(), EaseCommonUtils.getMessageDigest(lastMessage, getContext())), TextView.BufferType.SPANNABLE);
            if (content != null) {
                holder.message.setText(content);
            }
            holder.time.setText(TimeUtil.formatChatTime(lastMessage.getMsgTime()));
            if (lastMessage.direct() == EMMessage.Direct.SEND && lastMessage.status() == EMMessage.Status.FAIL) {
                holder.msgState.setVisibility(0);
            } else {
                holder.msgState.setVisibility(8);
            }
        }
        holder.name.setTextColor(this.primaryColor);
        holder.message.setTextColor(this.secondaryColor);
        holder.time.setTextColor(this.timeColor);
        if (this.primarySize != 0) {
            holder.name.setTextSize(0, this.primarySize);
        }
        if (this.secondarySize != 0) {
            holder.message.setTextSize(0, this.secondarySize);
        }
        if (this.timeSize != 0.0f) {
            holder.time.setTextSize(0, this.timeSize);
        }
        return convertView;
    }

    @Override // android.widget.ArrayAdapter, android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (!this.notifyByFilter) {
            this.copyConversationList.clear();
            this.copyConversationList.addAll(this.conversationList);
            this.notifyByFilter = false;
        }
    }

    @Override // android.widget.ArrayAdapter, android.widget.Filterable
    public Filter getFilter() {
        if (this.conversationFilter == null) {
            this.conversationFilter = new ConversationFilter(this.conversationList);
        }
        return this.conversationFilter;
    }

    public void setPrimaryColor(int primaryColor) {
        this.primaryColor = primaryColor;
    }

    public void setSecondaryColor(int secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public void setTimeColor(int timeColor) {
        this.timeColor = timeColor;
    }

    public void setPrimarySize(int primarySize) {
        this.primarySize = primarySize;
    }

    public void setSecondarySize(int secondarySize) {
        this.secondarySize = secondarySize;
    }

    public void setTimeSize(float timeSize) {
        this.timeSize = timeSize;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class ConversationFilter extends Filter {
        List<EMConversation> mOriginalValues;

        public ConversationFilter(List<EMConversation> mList) {
            this.mOriginalValues = null;
            this.mOriginalValues = mList;
        }

        @Override // android.widget.Filter
        protected Filter.FilterResults performFiltering(CharSequence prefix) {
            String compareName;
            Filter.FilterResults results = new Filter.FilterResults();
            if (this.mOriginalValues == null) {
                this.mOriginalValues = new ArrayList();
            }
            if (prefix == null || prefix.length() == 0) {
                results.values = EaseConversationAdapter.this.copyConversationList;
                results.count = EaseConversationAdapter.this.copyConversationList.size();
            } else {
                String prefixString = prefix.toString();
                int count = this.mOriginalValues.size();
                ArrayList<EMConversation> newValues = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    EMConversation value = this.mOriginalValues.get(i);
                    String username = value.conversationId();
                    GroupBean bean = EaseUserUtils.getGroupInfo(username);
                    if (bean == null || TextUtils.isEmpty(bean.getGroupName())) {
                        EaseUser user = EaseUserUtils.getUserInfo(username);
                        if (user != null) {
                            compareName = user.getNick();
                        } else {
                            compareName = username;
                        }
                    } else {
                        compareName = bean.getGroupName();
                    }
                    if (compareName.contains(prefixString)) {
                        newValues.add(value);
                    }
                }
                results.values = newValues;
                results.count = newValues.size();
            }
            return results;
        }

        @Override // android.widget.Filter
        protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
            EaseConversationAdapter.this.conversationList = new ArrayList();
            if (results.values != null) {
                EaseConversationAdapter.this.conversationList.addAll((List) results.values);
            }
            if (results.count > 0) {
                EaseConversationAdapter.this.notifyByFilter = true;
                EaseConversationAdapter.this.notifyDataSetChanged();
                return;
            }
            EaseConversationAdapter.this.notifyByFilter = true;
            EaseConversationAdapter.this.notifyDataSetInvalidated();
        }
    }

    public void setCvsListHelper(EaseConversationList.EaseConversationListHelper cvsListHelper) {
        this.cvsListHelper = cvsListHelper;
    }

    /* loaded from: classes.dex */
    private static class ViewHolder {
        ImageView avatar;
        ImageView group_dot;
        RelativeLayout list_itease_layout;
        TextView message;
        TextView motioned;
        View msgState;
        TextView name;
        TextView time;
        TextView unreadLabel;

        private ViewHolder() {
        }
    }
}
