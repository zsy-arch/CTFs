package com.em.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.em.DemoHelper;
import com.em.db.UserDao;
import com.em.domain.RobotUser;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMContact;
import com.hyphenate.exceptions.HyphenateException;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class RobotsActivity extends BaseActivity {
    public static final String TAG = RobotsActivity.class.getSimpleName();
    private RobotAdapter adapter;
    private InputMethodManager inputMethodManager;
    private View progressBar;
    private List<RobotUser> robotList = new ArrayList();
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.em_fragment_robots;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.robot_chat, 0);
        this.inputMethodManager = (InputMethodManager) getSystemService("input_method");
        ListView mListView = (ListView) findViewById(R.id.list);
        this.swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        if (Build.VERSION.SDK_INT >= 14) {
            this.swipeRefreshLayout.setColorSchemeResources(17170450, 17170452, 17170456, 17170454);
        }
        this.progressBar = findViewById(R.id.progress_bar);
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.em.ui.RobotsActivity.1
            @Override // android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
            public void onRefresh() {
                RobotsActivity.this.getRobotNamesFromServer();
            }
        });
        Map<String, RobotUser> robotMap = DemoHelper.getInstance().getRobotList();
        if (robotMap != null) {
            this.robotList.addAll(robotMap.values());
        } else {
            this.progressBar.setVisibility(0);
            getRobotNamesFromServer();
        }
        this.adapter = new RobotAdapter(this, 1, this.robotList);
        mListView.setAdapter((ListAdapter) this.adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.em.ui.RobotsActivity.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(RobotsActivity.this, ChatActivity.class);
                intent.putExtra("username", ((RobotUser) parent.getItemAtPosition(position)).getUsername());
                RobotsActivity.this.startActivity(intent);
            }
        });
        mListView.setOnTouchListener(new View.OnTouchListener() { // from class: com.em.ui.RobotsActivity.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                if (RobotsActivity.this.getWindow().getAttributes().softInputMode == 2 || RobotsActivity.this.getCurrentFocus() == null) {
                    return false;
                }
                RobotsActivity.this.inputMethodManager.hideSoftInputFromWindow(RobotsActivity.this.getCurrentFocus().getWindowToken(), 2);
                return false;
            }
        });
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getRobotNamesFromServer() {
        asyncGetRobotNamesFromServer(new EMValueCallBack<List<EMContact>>() { // from class: com.em.ui.RobotsActivity.4
            public void onSuccess(final List<EMContact> value) {
                RobotsActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.RobotsActivity.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        RobotsActivity.this.progressBar.setVisibility(8);
                        RobotsActivity.this.swipeRefreshLayout.setRefreshing(false);
                        Map<String, RobotUser> mMap = new HashMap<>();
                        for (EMContact item : value) {
                            RobotUser user = new RobotUser(item.getUsername());
                            user.setNick(item.getNick());
                            user.setInitialLetter("#");
                            mMap.put(item.getUsername(), user);
                        }
                        RobotsActivity.this.robotList.clear();
                        RobotsActivity.this.robotList.addAll(mMap.values());
                        DemoHelper.getInstance().setRobotList(mMap);
                        new UserDao(RobotsActivity.this).saveRobotUser(RobotsActivity.this.robotList);
                        RobotsActivity.this.adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override // com.hyphenate.EMValueCallBack
            public void onError(int error, String errorMsg) {
                RobotsActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.RobotsActivity.4.2
                    @Override // java.lang.Runnable
                    public void run() {
                        RobotsActivity.this.swipeRefreshLayout.setRefreshing(false);
                        RobotsActivity.this.progressBar.setVisibility(8);
                    }
                });
            }
        });
    }

    private void asyncGetRobotNamesFromServer(final EMValueCallBack<List<EMContact>> callback) {
        ThreadPool.newThreadPool(new Runnable() { // from class: com.em.ui.RobotsActivity.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    callback.onSuccess(EMClient.getInstance().getRobotsFromServer());
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    callback.onError(e.getErrorCode(), e.toString());
                }
            }
        });
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

    /* loaded from: classes.dex */
    class RobotAdapter extends ArrayAdapter<RobotUser> {
        private LayoutInflater inflater;

        public RobotAdapter(Context context, int res, List<RobotUser> robots) {
            super(context, res, robots);
            this.inflater = LayoutInflater.from(context);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = this.inflater.inflate(R.layout.em_row_robots, parent, false);
            }
            ((TextView) convertView.findViewById(R.id.name)).setText(getItem(position).getNick());
            return convertView;
        }
    }
}
