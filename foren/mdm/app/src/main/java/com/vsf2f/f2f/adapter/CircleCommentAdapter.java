package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.adapter.MyBaseAdapter;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.CircleCommentBean;
import java.util.List;

/* loaded from: classes2.dex */
public class CircleCommentAdapter extends MyBaseAdapter<CircleCommentBean> {
    private ItemClick itemClick;

    /* loaded from: classes2.dex */
    public interface ItemClick {
        void clickComment(String str);
    }

    public CircleCommentAdapter(Context context, List<CircleCommentBean> datas, ItemClick itemClick) {
        super(context, datas);
        this.itemClick = itemClick;
    }

    @Override // android.widget.Adapter
    public View getView(int position, View v, ViewGroup group) {
        if (v == null) {
            v = inflate(R.layout.item_cicles_comment);
            new ViewCache(v);
        }
        ViewCache h = (ViewCache) v.getTag();
        CircleCommentBean item = getItem(position);
        String str = ComUtil.getColorText(item.getNickName(), "#1E90FF");
        if (!TextUtils.isEmpty(item.getReplyNickName())) {
            str = str + " 回复" + ComUtil.getColorText(item.getReplyNickName(), "#1E90FF");
        }
        h.txtContent.setText(Html.fromHtml(str + "：" + item.getMsgContent()));
        final String msgId = item.getMsgId();
        h.lly_parent.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.CircleCommentAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v2) {
                CircleCommentAdapter.this.itemClick.clickComment(msgId);
            }
        });
        return v;
    }

    /* loaded from: classes2.dex */
    class ViewCache {
        private LinearLayout lly_parent;
        private TextView txtContent;

        public ViewCache(View v) {
            v.setTag(this);
            this.lly_parent = (LinearLayout) CircleCommentAdapter.this.getView(v, R.id.lly_parent);
            this.txtContent = (TextView) CircleCommentAdapter.this.getView(v, R.id.circles_txtContent);
        }
    }
}
