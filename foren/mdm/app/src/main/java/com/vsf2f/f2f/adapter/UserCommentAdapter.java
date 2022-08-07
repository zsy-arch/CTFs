package com.vsf2f.f2f.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.cdlinglu.utils.TimeUtil;
import com.hy.frame.view.MyGridView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.UserCommentBean;
import com.vsf2f.f2f.ui.needs.star.StarLinearLayout;
import java.util.List;

/* loaded from: classes2.dex */
public class UserCommentAdapter extends BaseAdapter {
    private int commentType = 0;
    private List<UserCommentBean.DatasBean> data;
    private LayoutInflater mInflater;

    public UserCommentAdapter(List<UserCommentBean.DatasBean> data) {
        this.data = data;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.data == null) {
            return 0;
        }
        return this.data.size();
    }

    public void setCommentType(int commentType) {
        this.commentType = commentType;
    }

    public void setData(List<UserCommentBean.DatasBean> data) {
        this.data = data;
    }

    @Override // android.widget.Adapter
    public Object getItem(int position) {
        return this.data.get(position);
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        HoldView holdView;
        UserCommentBean.DatasBean.CreatorObjBean userBean;
        UserCommentBean.DatasBean bean = this.data.get(position);
        if (convertView == null) {
            holdView = new HoldView();
            this.mInflater = LayoutInflater.from(parent.getContext());
            convertView = this.mInflater.inflate(R.layout.layout_item_user_comment, parent, false);
            holdView.sll_attidu = (StarLinearLayout) convertView.findViewById(R.id.sll_attidu);
            holdView.ll_parent = (LinearLayout) convertView.findViewById(R.id.ll_parent);
            holdView.mgv_pic = (MyGridView) convertView.findViewById(R.id.user_comment_mgv);
            holdView.riv_user = (ImageView) convertView.findViewById(R.id.riv_user);
            holdView.vip_crown = (ImageView) convertView.findViewById(R.id.vip_crown);
            holdView.tv_title = (TextView) convertView.findViewById(R.id.user_comment_title);
            holdView.tv_content = (TextView) convertView.findViewById(R.id.user_comment_detail);
            holdView.tv_score = (TextView) convertView.findViewById(R.id.user_comment_score);
            holdView.tv_time = (TextView) convertView.findViewById(R.id.user_comment_time);
            holdView.tv_type = (TextView) convertView.findViewById(R.id.user_comment_type);
            convertView.setTag(holdView);
        } else {
            holdView = (HoldView) convertView.getTag();
        }
        holdView.tv_content.setText(bean.getDescription());
        holdView.tv_time.setText(TimeUtil.formatDisplayTime(bean.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
        holdView.tv_score.setText(bean.getLevel() + "分");
        if ("4".equals(bean.getCreatorObj().getLv())) {
            holdView.vip_crown.setVisibility(0);
        } else {
            holdView.vip_crown.setVisibility(8);
        }
        holdView.sll_attidu.setStar(((float) bean.getLevel()) / 2.0f);
        holdView.mgv_pic.setAdapter((ListAdapter) new PicUrlPathAdapter(parent.getContext(), bean.getImgUrlList(), 4, R.dimen.margin_normal, R.dimen.padding_normal));
        holdView.mgv_pic.setClickable(false);
        holdView.mgv_pic.setEnabled(false);
        if (bean.getBizType() == 0) {
            holdView.tv_type.setTextColor(parent.getResources().getColor(R.color.demand_order_red));
            holdView.tv_type.setText(R.string.evaluate_demand);
        } else {
            holdView.tv_type.setTextColor(parent.getResources().getColor(R.color.orange));
            holdView.tv_type.setText(R.string.evaluate_service);
        }
        if (this.commentType == 0) {
            userBean = bean.getCreatorObj();
        } else {
            userBean = bean.getReceiverObj();
        }
        if (userBean != null) {
            Glide.with(parent.getContext()).load(userBean.getUserPic().getSpath()).error((int) R.mipmap.def_head).into(holdView.riv_user);
            holdView.tv_title.setText(userBean.getNickName());
        }
        return convertView;
    }

    /* loaded from: classes2.dex */
    static class HoldView {
        LinearLayout ll_parent;
        MyGridView mgv_pic;
        ImageView riv_user;
        StarLinearLayout sll_attidu;
        TextView tv_content;
        TextView tv_score;
        TextView tv_time;
        TextView tv_title;
        TextView tv_type;
        ImageView vip_crown;

        HoldView() {
        }
    }
}
