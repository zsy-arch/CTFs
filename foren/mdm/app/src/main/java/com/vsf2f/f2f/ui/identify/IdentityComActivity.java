package com.vsf2f.f2f.ui.identify;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.easeui.widget.EaseAlertDialog;
import com.em.DemoHelper;
import com.em.ui.EditActivity;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.MyViewPager;
import com.hy.frame.view.NavView;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.PicAddBtnAdapter;
import com.vsf2f.f2f.bean.ApplyPayInfoBean;
import com.vsf2f.f2f.bean.MyCalendar;
import com.vsf2f.f2f.bean.ViewStateBean;
import com.vsf2f.f2f.bean.identifyComBean;
import com.vsf2f.f2f.ui.dialog.MyDatePickerDialog;
import com.vsf2f.f2f.ui.dialog.SelectDialog;
import com.vsf2f.f2f.ui.needs.demand.HelpSuccessActivity;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import com.vsf2f.f2f.ui.utils.listener.UploadPicListener;
import com.vsf2f.f2f.ui.utils.photo.ImageUtil;
import com.vsf2f.f2f.ui.utils.photo.PhotoPickerActivity;
import com.vsf2f.f2f.ui.utils.photo.PhotoPickerIntent;
import com.vsf2f.f2f.ui.utils.photo.PhotoPreviewActivity;
import com.vsf2f.f2f.ui.utils.photo.PhotoPreviewIntent;
import com.vsf2f.f2f.ui.utils.photo.SelectModel;
import com.vsf2f.f2f.ui.utils.upload.UploadUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpHost;

/* loaded from: classes2.dex */
public class IdentityComActivity extends BaseActivity {
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_CAMERA_CODE2 = 12;
    private static final int REQUEST_CAMERA_CODE3 = 13;
    private static final int REQUEST_CODE_EDIT_BUSAREA = 30;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private String behindPath;
    private String busArea;
    private String busLicenseNum;
    private int cardHolderType;
    private String cardNum;
    private int choiceId;
    private identifyComBean comBean;
    private String companyPhone;
    private String contractPhone;
    private String contractUser;
    private MyDatePickerDialog dateDialog;
    private MyDatePickerDialog dateDialog2;
    private EditText ed_busNum;
    private EditText ed_idcard;
    private EditText ed_realname;
    private String email;
    private String enterpriseName;
    private EditText et_addr;
    private EditText et_comname;
    private EditText et_comphone;
    private EditText et_comweb;
    private EditText et_email;
    private EditText et_phone;
    private EditText et_real;
    private String frontPath;
    private GridView gvPic;
    private boolean has;
    private String holdPath;
    private ImageView iv_behind;
    private ImageView iv_front;
    private ImageView iv_hold;
    private ImageView iv_license;
    private String licensePath;
    private MyCalendar mCalendar;
    private MyCalendar mCalendar2;
    private Map<String, String> mapSpec;
    private AjaxParams params;
    private PicAddBtnAdapter picAdapter;
    private String realName;
    private String registerAddress;
    private String specQual;
    private List<View> tabHead;
    private List<ViewStateBean> tabState;
    private TextView tv_busArea;
    private TextView tv_busDate;
    private TextView tv_busarea;
    private TextView tv_cardtime;
    private TextView tv_cardtype;
    private List<View> viewList;
    private MyViewPager viewPager;
    private String webSite;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private ArrayList<String> allPaths = new ArrayList<>();
    private String[] typeTexts = {"法 人", "经办人"};

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_identy_com;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeader(R.string.identy_com, 0);
        setHeaderLeftTxt(R.string.cancel);
        this.tabHead = new ArrayList();
        this.tabHead.add((NavView) getView(R.id.tab1));
        this.tabHead.add((NavView) getView(R.id.tab2));
        this.tabHead.add((NavView) getView(R.id.tab3));
        this.tabState = new ArrayList();
        this.tabState.add(new ViewStateBean((ImageView) getView(R.id.tab_state1), false, true));
        this.tabState.add(new ViewStateBean((ImageView) getView(R.id.tab_state2)));
        this.tabState.add(new ViewStateBean((ImageView) getView(R.id.tab_state3)));
        this.viewPager = (MyViewPager) findViewById(R.id.viewpager);
        LayoutInflater inflater = getLayoutInflater();
        View view1 = inflater.inflate(R.layout.layout_identy_com1, (ViewGroup) null);
        View view2 = inflater.inflate(R.layout.layout_identy_com2, (ViewGroup) null);
        View view3 = inflater.inflate(R.layout.layout_identy_com3, (ViewGroup) null);
        this.viewList = new ArrayList();
        this.viewList.add(view1);
        this.viewList.add(view2);
        this.viewList.add(view3);
        this.iv_hold = (ImageView) getViewAndClick(view2, R.id.iv_hold);
        this.iv_front = (ImageView) getViewAndClick(view2, R.id.iv_front);
        this.iv_behind = (ImageView) getViewAndClick(view2, R.id.iv_behind);
        this.iv_license = (ImageView) getViewAndClick(view3, R.id.iv_license);
        this.tv_cardtype = (TextView) getViewAndClick(view2, R.id.tv_cardtype);
        this.tv_cardtime = (TextView) getViewAndClick(view2, R.id.tv_cardtime);
        this.tv_busDate = (TextView) getViewAndClick(view3, R.id.tv_busdate);
        this.tv_busarea = (TextView) getViewAndClick(view3, R.id.tv_busarea);
        this.gvPic = (GridView) getView(view1, R.id.gv_pic);
        this.gvPic.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.vsf2f.f2f.ui.identify.IdentityComActivity.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (IdentityComActivity.this.imagePaths.size() >= 6 || i != IdentityComActivity.this.imagePaths.size()) {
                    PhotoPreviewIntent intent = new PhotoPreviewIntent(IdentityComActivity.this.context);
                    intent.setCurrentItem(i);
                    intent.setPhotoPaths(IdentityComActivity.this.imagePaths);
                    IdentityComActivity.this.startActivityForResult(intent, 20);
                    return;
                }
                PhotoPickerIntent intent2 = new PhotoPickerIntent(IdentityComActivity.this.context);
                intent2.setSelectModel(SelectModel.MULTI);
                intent2.setShowCamera(true);
                intent2.setMaxTotal(6);
                intent2.setSelectedPaths(IdentityComActivity.this.imagePaths);
                IdentityComActivity.this.startActivityForResult(intent2, 10);
            }
        });
        this.et_real = (EditText) getView(view1, R.id.et_real);
        this.et_email = (EditText) getView(view1, R.id.et_email);
        this.et_phone = (EditText) getView(view1, R.id.et_phone);
        this.et_comname = (EditText) getView(view1, R.id.et_comname);
        this.et_addr = (EditText) getView(view1, R.id.et_addr);
        this.et_comweb = (EditText) getView(view1, R.id.et_comweb);
        this.et_comphone = (EditText) getView(view1, R.id.et_comphone);
        this.ed_realname = (EditText) getView(view2, R.id.ed_realname);
        this.ed_idcard = (EditText) getView(view2, R.id.ed_idcard);
        this.ed_busNum = (EditText) getView(view3, R.id.ed_busNum);
        this.tv_busArea = (TextView) getView(view3, R.id.tv_busarea);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        if (getBundle() != null) {
            this.has = getBundle().getBoolean("has");
        }
        if (this.has) {
            requestData();
        }
        this.viewPager.setAdapter(new PagerAdapter() { // from class: com.vsf2f.f2f.ui.identify.IdentityComActivity.2
            @Override // android.support.v4.view.PagerAdapter
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override // android.support.v4.view.PagerAdapter
            public int getCount() {
                return IdentityComActivity.this.viewList.size();
            }

            @Override // android.support.v4.view.PagerAdapter
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) IdentityComActivity.this.viewList.get(position));
            }

            @Override // android.support.v4.view.PagerAdapter
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView((View) IdentityComActivity.this.viewList.get(position));
                return IdentityComActivity.this.viewList.get(position);
            }
        });
        setHeaderRightTxt(R.string.nextStep);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.cdlinglu.common.BaseActivity, com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.picAdapter = new PicAddBtnAdapter(this, this.imagePaths);
        this.picAdapter.setTotalNum(6);
        this.gvPic.setAdapter((ListAdapter) this.picAdapter);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        getClient().setShowDialog(true);
        getClient().get(R.string.QUERY_IDENTIFY_COM, ComUtil.getXDDApi(this.context, getString(R.string.QUERY_IDENTIFY_COM)) + "?userName=" + DemoHelper.getInstance().getCurrentUserName(), null, identifyComBean.class, false);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        this.et_real.setText(this.comBean.getContractUser());
        this.et_email.setText(this.comBean.getEmail());
        this.et_phone.setText(this.comBean.getContractPhone());
        this.et_comname.setText(this.comBean.getEnterpriseName());
        this.et_addr.setText(this.comBean.getRegisterAddress());
        this.et_comweb.setText(this.comBean.getWebSite());
        this.et_comphone.setText(this.comBean.getCompanyPhone());
        this.ed_realname.setText(this.comBean.getRealName());
        this.ed_idcard.setText(this.comBean.getCardNum());
        this.cardHolderType = this.comBean.getCardHolderType();
        this.tv_cardtype.setText(this.typeTexts[this.cardHolderType]);
        this.mCalendar = new MyCalendar(this.comBean.getCardValidTime());
        this.tv_cardtime.setText(this.mCalendar.showString());
        this.ed_busNum.setText(this.comBean.getBusLicenseNum() + "");
        this.tv_busArea.setText(this.comBean.getBusArea());
        this.mCalendar2 = new MyCalendar(this.comBean.getBusExpireDate());
        this.tv_busDate.setText(this.mCalendar2.showString());
        Glide.with(this.context).load(this.comBean.getIdCardPositiveImg()).into(this.iv_front);
        Glide.with(this.context).load(this.comBean.getIdCardOppositiveImg()).into(this.iv_behind);
        Glide.with(this.context).load(this.comBean.getHandTakePositiveImg()).into(this.iv_hold);
        Glide.with(this.context).load(this.comBean.getBusImgUrl()).into(this.iv_license);
        this.frontPath = "";
        this.behindPath = "";
        this.holdPath = "";
        this.licensePath = "";
        this.imagePaths.clear();
        if (HyUtil.isNoEmpty(this.comBean.getSpecQualUrlList())) {
            this.imagePaths.addAll(this.comBean.getSpecQualUrlList());
            this.picAdapter.notifyDataSetChanged();
            if (this.imagePaths.size() > 0) {
                this.mapSpec = new HashMap();
                String[] specQuals = this.comBean.getSpecQual().split(",");
                for (int i = 0; i < this.imagePaths.size(); i++) {
                    this.mapSpec.put(this.imagePaths.get(i), specQuals[i]);
                }
            }
        }
    }

    public void selectTab(int num) {
        this.viewPager.setCurrentItem(num);
        for (int i = 0; i < this.tabState.size(); i++) {
            if (i == num) {
                this.tabState.get(i).setSelect(true);
            } else {
                this.tabState.get(i).setSelect(false);
            }
        }
        refreshTab();
    }

    public void refreshTab() {
        for (int i = 0; i < this.tabState.size(); i++) {
            if (this.tabState.get(i).isSelect()) {
                this.tabHead.get(i).setSelected(true);
                ((ImageView) this.tabState.get(i).getView()).setImageResource(R.drawable.icon_radio_red);
            } else {
                this.tabHead.get(i).setSelected(false);
                if (this.tabState.get(i).isCheck()) {
                    ((ImageView) this.tabState.get(i).getView()).setImageResource(R.drawable.icon_radio_confirm_sel);
                } else {
                    ((ImageView) this.tabState.get(i).getView()).setImageResource(R.drawable.icon_radio_empty);
                }
            }
        }
    }

    private void getPhoto() {
        PhotoPickerIntent intent = new PhotoPickerIntent(this.context);
        intent.setSelectModel(SelectModel.SINGLE);
        intent.setShowCamera(true);
        startActivityForResult(intent, 12);
    }

    public void showMyDatePicker() {
        if (this.dateDialog == null) {
            this.dateDialog = new MyDatePickerDialog(this, new MyDatePickerDialog.OnDateSetListener() { // from class: com.vsf2f.f2f.ui.identify.IdentityComActivity.3
                @Override // com.vsf2f.f2f.ui.dialog.MyDatePickerDialog.OnDateSetListener
                public void onDateSet(DatePicker view, MyCalendar calendar) {
                    IdentityComActivity.this.mCalendar = calendar;
                    IdentityComActivity.this.tv_cardtime.setText(calendar.showString());
                }
            }, this.mCalendar);
            this.dateDialog.setCanEver();
            this.dateDialog.myShow();
            return;
        }
        this.dateDialog.reShow();
    }

    public void showMyDatePicker2() {
        if (this.dateDialog2 == null) {
            this.dateDialog2 = new MyDatePickerDialog(this, new MyDatePickerDialog.OnDateSetListener() { // from class: com.vsf2f.f2f.ui.identify.IdentityComActivity.4
                @Override // com.vsf2f.f2f.ui.dialog.MyDatePickerDialog.OnDateSetListener
                public void onDateSet(DatePicker view, MyCalendar calendar) {
                    IdentityComActivity.this.mCalendar2 = calendar;
                    IdentityComActivity.this.tv_busDate.setText(calendar.showString());
                }
            }, this.mCalendar);
            this.dateDialog2.setCanEver();
            this.dateDialog2.myShow();
            return;
        }
        this.dateDialog2.reShow();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(final View v) {
        this.choiceId = v.getId();
        switch (v.getId()) {
            case R.id.tv_cardtype /* 2131756974 */:
                new SelectDialog(this.context, "选择类型", this.typeTexts, new SelectDialog.SelectCallBack() { // from class: com.vsf2f.f2f.ui.identify.IdentityComActivity.5
                    @Override // com.vsf2f.f2f.ui.dialog.SelectDialog.SelectCallBack
                    public void select(int index) {
                        IdentityComActivity.this.cardHolderType = index;
                        ((TextView) v).setText(IdentityComActivity.this.typeTexts[index]);
                    }
                }).show();
                return;
            case R.id.ed_idcard /* 2131756975 */:
            case R.id.page3 /* 2131756980 */:
            case R.id.ed_busNum /* 2131756981 */:
            default:
                return;
            case R.id.tv_cardtime /* 2131756976 */:
                showMyDatePicker();
                return;
            case R.id.iv_front /* 2131756977 */:
            case R.id.iv_behind /* 2131756978 */:
            case R.id.iv_hold /* 2131756979 */:
            case R.id.iv_license /* 2131756984 */:
                getPhoto();
                return;
            case R.id.tv_busarea /* 2131756982 */:
                Bundle bundle = new Bundle();
                bundle.putString("title", "填写经营范围");
                bundle.putString(EditActivity.CONTENT, ((TextView) v).getText().toString());
                startActForResult(EditActivity.class, bundle, 30);
                return;
            case R.id.tv_busdate /* 2131756983 */:
                showMyDatePicker2();
                return;
        }
    }

    private void showPic(ArrayList<String> paths) {
        switch (this.choiceId) {
            case R.id.iv_front /* 2131756977 */:
                this.frontPath = paths.get(0);
                Glide.with(this.context).load(new File(this.frontPath)).into(this.iv_front);
                return;
            case R.id.iv_behind /* 2131756978 */:
                this.behindPath = paths.get(0);
                Glide.with(this.context).load(new File(this.behindPath)).into(this.iv_behind);
                return;
            case R.id.iv_hold /* 2131756979 */:
                this.holdPath = paths.get(0);
                Glide.with(this.context).load(new File(this.holdPath)).into(this.iv_hold);
                return;
            case R.id.page3 /* 2131756980 */:
            case R.id.ed_busNum /* 2131756981 */:
            case R.id.tv_busarea /* 2131756982 */:
            case R.id.tv_busdate /* 2131756983 */:
            default:
                return;
            case R.id.iv_license /* 2131756984 */:
                this.licensePath = paths.get(0);
                Glide.with(this.context).load(new File(this.licensePath)).into(this.iv_license);
                return;
        }
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onLeftClick() {
        if (this.viewPager.getCurrentItem() > 0) {
            selectTab(this.viewPager.getCurrentItem() - 1);
            if (this.viewPager.getCurrentItem() == 0) {
                setHeaderLeftTxt(R.string.cancel);
            }
            setHeaderRightTxt(R.string.nextStep);
            return;
        }
        onBackPressed();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        hideSoftKeyboard();
        new EaseAlertDialog(this, R.string.exit_enter_, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.identify.IdentityComActivity.6
            @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
            public void onResult(boolean confirmed, Bundle bundle) {
                if (confirmed) {
                    IdentityComActivity.this.finish();
                }
            }
        }, true).show();
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        switch (this.viewPager.getCurrentItem()) {
            case 0:
                this.contractUser = this.et_real.getText().toString().trim();
                if (!TextUtils.isEmpty(this.contractUser)) {
                    this.email = this.et_email.getText().toString().trim();
                    if (!TextUtils.isEmpty(this.email)) {
                        this.contractPhone = this.et_phone.getText().toString().trim();
                        if (!TextUtils.isEmpty(this.contractPhone)) {
                            this.enterpriseName = this.et_comname.getText().toString().trim();
                            if (!TextUtils.isEmpty(this.enterpriseName)) {
                                this.registerAddress = this.et_addr.getText().toString().trim();
                                if (!TextUtils.isEmpty(this.registerAddress)) {
                                    this.webSite = this.et_comweb.getText().toString().trim();
                                    if (!TextUtils.isEmpty(this.webSite)) {
                                        this.companyPhone = this.et_comphone.getText().toString().trim();
                                        if (!TextUtils.isEmpty(this.companyPhone)) {
                                            setHeaderLeftTxt(R.string.lastStep);
                                            break;
                                        } else {
                                            MyToast.show(this.context, this.et_comphone.getHint().toString());
                                            return;
                                        }
                                    } else {
                                        MyToast.show(this.context, this.et_comweb.getHint().toString());
                                        return;
                                    }
                                } else {
                                    MyToast.show(this.context, this.et_addr.getHint().toString());
                                    return;
                                }
                            } else {
                                MyToast.show(this.context, this.et_comname.getHint().toString());
                                return;
                            }
                        } else {
                            MyToast.show(this.context, this.et_phone.getHint().toString());
                            return;
                        }
                    } else {
                        MyToast.show(this.context, this.et_email.getHint().toString());
                        return;
                    }
                } else {
                    MyToast.show(this.context, this.et_real.getHint().toString());
                    return;
                }
            case 1:
                this.realName = this.ed_realname.getText().toString().trim();
                if (!TextUtils.isEmpty(this.realName)) {
                    this.cardNum = this.ed_idcard.getText().toString().trim();
                    if (!TextUtils.isEmpty(this.cardNum)) {
                        if (!HyUtil.hasChinese(this.cardNum)) {
                            if (!TextUtils.isEmpty(this.tv_cardtime.getText().toString().trim())) {
                                if (this.frontPath != null) {
                                    if (this.behindPath != null) {
                                        if (this.holdPath != null) {
                                            setHeaderRightTxt(R.string.finish);
                                            break;
                                        } else {
                                            MyToast.show(this.context, "请选择手持身份证照");
                                            return;
                                        }
                                    } else {
                                        MyToast.show(this.context, "请选择身份证反面照");
                                        return;
                                    }
                                } else {
                                    MyToast.show(this.context, "请选择身份证正面照");
                                    return;
                                }
                            } else {
                                MyToast.show(this.context, this.tv_cardtime.getHint().toString());
                                return;
                            }
                        } else {
                            MyToast.show(this.context, "身份证格式不正确");
                            return;
                        }
                    } else {
                        MyToast.show(this.context, this.ed_idcard.getHint().toString());
                        return;
                    }
                } else {
                    MyToast.show(this.context, this.ed_realname.getHint().toString());
                    return;
                }
            case 2:
                this.busLicenseNum = this.ed_busNum.getText().toString().trim();
                if (TextUtils.isEmpty(this.busLicenseNum)) {
                    MyToast.show(this.context, this.ed_busNum.getHint().toString());
                    return;
                }
                this.busArea = this.tv_busArea.getText().toString().trim();
                if (TextUtils.isEmpty(this.busArea)) {
                    MyToast.show(this.context, this.tv_busArea.getHint().toString());
                    return;
                } else if (TextUtils.isEmpty(this.tv_busDate.getText().toString().trim())) {
                    MyToast.show(this.context, this.tv_busDate.getHint().toString());
                    return;
                } else if (this.licensePath == null) {
                    MyToast.show(this.context, "请选择营业执照图片");
                    return;
                }
                break;
        }
        if (this.viewPager.getCurrentItem() < 2) {
            this.tabState.get(this.viewPager.getCurrentItem()).setCheck(true);
            selectTab(this.viewPager.getCurrentItem() + 1);
            return;
        }
        dealEditData();
    }

    private void dealEditData() {
        getClient().showDialogNow(R.string.toast_uploading);
        List<String> specList = new ArrayList<>(this.imagePaths);
        if (this.has) {
            for (int i = 0; i < this.imagePaths.size(); i++) {
                if (this.imagePaths.get(i).startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
                    specList.remove(i);
                    specList.add(i, this.mapSpec.get(this.imagePaths.get(i)));
                }
            }
        }
        this.allPaths.clear();
        this.allPaths.add(this.frontPath);
        this.allPaths.add(this.behindPath);
        this.allPaths.add(this.holdPath);
        this.allPaths.add(this.licensePath);
        this.allPaths.addAll(specList);
        ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.ui.identify.IdentityComActivity.7
            @Override // java.lang.Runnable
            public void run() {
                List<String> allPath = new ArrayList<>();
                long ids = System.currentTimeMillis();
                for (int i2 = 0; i2 < IdentityComActivity.this.allPaths.size(); i2++) {
                    allPath.add(((String) IdentityComActivity.this.allPaths.get(i2)).startsWith(HttpHost.DEFAULT_SCHEME_NAME) ? (String) IdentityComActivity.this.allPaths.get(i2) : ImageUtil.compressImage((String) IdentityComActivity.this.allPaths.get(i2), ids + "_" + i2, 1024));
                }
                IdentityComActivity.this.UpPic(allPath);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void UpPic(List<String> allPaths) {
        new UploadUtils().UploadFileGetUrl(this, "0001_auth/", DemoHelper.getInstance().getCurrentUserName(), "auth", 19, allPaths, new UploadPicListener() { // from class: com.vsf2f.f2f.ui.identify.IdentityComActivity.8
            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onSuccess(@Nullable List<Map<String, String>> list, @Nullable List<String> picIds) {
                IdentityComActivity.this.getClient().dialogDismiss();
                if (picIds == null) {
                    MyToast.show(IdentityComActivity.this.getApplicationContext(), IdentityComActivity.this.getString(R.string.upload_failed));
                    IdentityComActivity.this.getClient().dialogDismiss();
                    return;
                }
                if (IdentityComActivity.this.params == null) {
                    IdentityComActivity.this.params = new AjaxParams();
                }
                IdentityComActivity.this.specQual = "";
                for (int i = 0; i < picIds.size(); i++) {
                    switch (i) {
                        case 0:
                            if (!TextUtils.isEmpty(picIds.get(0))) {
                                IdentityComActivity.this.params.put("idCardPositiveImg", picIds.get(0));
                                break;
                            } else {
                                break;
                            }
                        case 1:
                            if (!TextUtils.isEmpty(picIds.get(1))) {
                                IdentityComActivity.this.params.put("idCardOppositiveImg", picIds.get(1));
                                break;
                            } else {
                                break;
                            }
                        case 2:
                            if (!TextUtils.isEmpty(picIds.get(2))) {
                                IdentityComActivity.this.params.put("handTakePositiveImg", picIds.get(2));
                                break;
                            } else {
                                break;
                            }
                        case 3:
                            if (!TextUtils.isEmpty(picIds.get(3))) {
                                IdentityComActivity.this.params.put("busImgUrl", picIds.get(3));
                                break;
                            } else {
                                break;
                            }
                        default:
                            IdentityComActivity.this.specQual += picIds.get(i) + ",";
                            break;
                    }
                }
                if (IdentityComActivity.this.specQual.length() > 1) {
                    IdentityComActivity.this.specQual = IdentityComActivity.this.specQual.substring(0, IdentityComActivity.this.specQual.length() - 1);
                }
                IdentityComActivity.this.params.put("specQual", IdentityComActivity.this.specQual);
                IdentityComActivity.this.publishResult();
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onFailed() {
                IdentityComActivity.this.getClient().dialogDismiss();
                MyToast.show(IdentityComActivity.this.getApplicationContext(), IdentityComActivity.this.getString(R.string.upload_failed));
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onProgress(long currentSize, long totalSize) {
                MyLog.e("onProgress", currentSize + "/" + totalSize);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void publishResult() {
        getClient().setShowDialog(true);
        this.params.put("contractUser", this.contractUser);
        this.params.put("contractPhone", this.contractPhone);
        this.params.put(NotificationCompat.CATEGORY_EMAIL, this.email);
        this.params.put("enterpriseName", this.enterpriseName);
        this.params.put("registerAddress", this.registerAddress);
        this.params.put("webSite", this.webSite);
        this.params.put("companyPhone", this.companyPhone);
        this.params.put("busLicenseNum", this.busLicenseNum);
        this.params.put("busArea", this.busArea);
        this.params.put("busExpireDate", this.mCalendar2.toString());
        this.params.put("realName", this.realName);
        this.params.put("cardHolderType", this.cardHolderType);
        this.params.put("cardNum", this.cardNum);
        this.params.put("cardValidTime", this.mCalendar.toString());
        getClient().post(R.string.API_IDENTIFY_COM, ComUtil.getXDDApi(this.context, getString(R.string.API_IDENTIFY_COM)), this.params, ApplyPayInfoBean.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_IDENTIFY_COM /* 2131296368 */:
                ApplyPayInfoBean bean = (ApplyPayInfoBean) result.getObj();
                if (bean.getNeedPay() == 1) {
                    bean.setPayTitle(getString(R.string.bail_poundage_com));
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("com", true);
                    bundle.putString("title", "申请成功");
                    bundle.putSerializable("payinfo", bean);
                    bundle.putInt(Constant.FLAG_TITLE, R.string.bail_poundage_com);
                    startAct(HelpSuccessActivity.class, bundle);
                } else {
                    MyToast.show(this.context, "申请成功");
                }
                finish();
                return;
            case R.string.QUERY_IDENTIFY_COM /* 2131296508 */:
                this.comBean = (identifyComBean) result.getObj();
                updateUI();
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case 10:
                    loadAdapter(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));
                    return;
                case 12:
                    showPic(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));
                    return;
                case 20:
                    loadAdapter(data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT));
                    return;
                case 30:
                    String area = data.getStringExtra("data");
                    if (!TextUtils.isEmpty(area)) {
                        this.tv_busarea.setText(area);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    private void loadAdapter(ArrayList<String> paths) {
        if (this.imagePaths == null) {
            this.imagePaths = new ArrayList<>();
        }
        this.imagePaths.clear();
        this.imagePaths.addAll(paths);
        this.picAdapter.notifyDataSetChanged();
    }
}
