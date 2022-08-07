package com.vsf2f.f2f.ui.utils.area;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import com.hy.frame.util.MyLog;
import com.vsf2f.f2f.ui.utils.area.WheelView;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class LinkagePicker extends WheelPicker {
    protected ArrayList<String> firstList;
    protected OnLinkageListener onLinkageListener;
    protected boolean onlyTwo;
    protected ArrayList<ArrayList<String>> secondList;
    protected int selectedFirstIndex;
    protected String selectedFirstText;
    protected int selectedSecondIndex;
    protected String selectedSecondText;
    protected int selectedThirdIndex;
    protected String selectedThirdText;
    protected ArrayList<ArrayList<ArrayList<String>>> thirdList;

    /* loaded from: classes2.dex */
    public interface OnLinkageListener {
        void onPicked(String str, String str2, String str3);
    }

    public LinkagePicker(Context context) {
        super(context);
        this.firstList = new ArrayList<>();
        this.secondList = new ArrayList<>();
        this.thirdList = new ArrayList<>();
        this.selectedFirstText = "";
        this.selectedSecondText = "";
        this.selectedThirdText = "";
        this.selectedFirstIndex = 0;
        this.selectedSecondIndex = 0;
        this.selectedThirdIndex = 0;
        this.onlyTwo = false;
    }

    public LinkagePicker(Activity activity, ArrayList<String> firstList, ArrayList<ArrayList<String>> secondList) {
        this(activity, firstList, secondList, null);
    }

    public LinkagePicker(Activity activity, ArrayList<String> firstList, ArrayList<ArrayList<String>> secondList, ArrayList<ArrayList<ArrayList<String>>> thirdList) {
        super(activity);
        this.firstList = new ArrayList<>();
        this.secondList = new ArrayList<>();
        this.thirdList = new ArrayList<>();
        this.selectedFirstText = "";
        this.selectedSecondText = "";
        this.selectedThirdText = "";
        this.selectedFirstIndex = 0;
        this.selectedSecondIndex = 0;
        this.selectedThirdIndex = 0;
        this.onlyTwo = false;
        this.firstList = firstList;
        this.secondList = secondList;
        if (thirdList == null || thirdList.size() == 0) {
            this.onlyTwo = true;
        } else {
            this.thirdList = thirdList;
        }
    }

    public void setSelectedItem(String firstText, String secondText) {
        setSelectedItem(firstText, secondText, "");
    }

    public void setSelectedItem(String firstText, String secondText, String thirdText) {
        int i = 0;
        while (true) {
            if (i >= this.firstList.size()) {
                break;
            }
            String ft = this.firstList.get(i);
            if (ft.contains(firstText)) {
                this.selectedFirstIndex = i;
                MyLog.d("init select first text: " + ft + ", index:" + this.selectedFirstIndex);
                break;
            }
            i++;
        }
        ArrayList<String> secondTexts = this.secondList.get(this.selectedFirstIndex);
        int j = 0;
        while (true) {
            if (j >= secondTexts.size()) {
                break;
            }
            String st = secondTexts.get(j);
            if (st.contains(secondText)) {
                this.selectedSecondIndex = j;
                MyLog.d("init select second text: " + st + ", index:" + this.selectedSecondIndex);
                break;
            }
            j++;
        }
        if (!(TextUtils.isEmpty(thirdText) || this.thirdList.size() == 0)) {
            ArrayList<String> thirdTexts = this.thirdList.get(this.selectedFirstIndex).get(this.selectedSecondIndex);
            for (int k = 0; k < thirdTexts.size(); k++) {
                String tt = thirdTexts.get(k);
                if (tt.contains(thirdText)) {
                    this.selectedThirdIndex = k;
                    MyLog.d("init select third text: " + tt + ", index:" + this.selectedThirdIndex);
                    return;
                }
            }
        }
    }

    public void setOnLinkageListener(OnLinkageListener onLinkageListener) {
        this.onLinkageListener = onLinkageListener;
    }

    @Override // com.vsf2f.f2f.ui.utils.area.ConfirmPopup
    @NonNull
    protected View makeCenterView() {
        if (this.firstList.size() == 0 || this.secondList.size() == 0) {
            throw new IllegalArgumentException("please initial data at first, can't be empty");
        }
        LinearLayout layout = new LinearLayout(this.context);
        layout.setOrientation(0);
        layout.setGravity(17);
        WheelView firstView = new WheelView(this.context);
        int width = this.screenWidthPixels / 3;
        firstView.setLayoutParams(new LinearLayout.LayoutParams(width, -2));
        firstView.setTextSize(this.textSize);
        firstView.setTextColor(this.textColorNormal, this.textColorFocus);
        firstView.setLineVisible(this.lineVisible);
        firstView.setLineColor(this.lineColor);
        firstView.setOffset(this.offset);
        layout.addView(firstView);
        final WheelView secondView = new WheelView(this.context);
        secondView.setLayoutParams(new LinearLayout.LayoutParams(width, -2));
        secondView.setTextSize(this.textSize);
        secondView.setTextColor(this.textColorNormal, this.textColorFocus);
        secondView.setLineVisible(this.lineVisible);
        secondView.setLineColor(this.lineColor);
        secondView.setOffset(this.offset);
        layout.addView(secondView);
        final WheelView thirdView = new WheelView(this.context);
        thirdView.setLayoutParams(new LinearLayout.LayoutParams(width, -2));
        thirdView.setTextSize(this.textSize);
        thirdView.setTextColor(this.textColorNormal, this.textColorFocus);
        thirdView.setLineVisible(this.lineVisible);
        thirdView.setLineColor(this.lineColor);
        thirdView.setOffset(this.offset);
        layout.addView(thirdView);
        if (this.onlyTwo) {
            thirdView.setVisibility(8);
        }
        firstView.setItems(this.firstList, this.selectedFirstIndex);
        firstView.setOnWheelViewListener(new WheelView.OnWheelViewListener() { // from class: com.vsf2f.f2f.ui.utils.area.LinkagePicker.1
            @Override // com.vsf2f.f2f.ui.utils.area.WheelView.OnWheelViewListener
            public void onSelected(boolean isUserScroll, int selectedIndex, String item) {
                int i;
                int i2 = 0;
                LinkagePicker.this.selectedFirstText = item;
                LinkagePicker.this.selectedFirstIndex = selectedIndex;
                LinkagePicker.this.selectedThirdIndex = 0;
                WheelView wheelView = secondView;
                ArrayList<String> arrayList = LinkagePicker.this.secondList.get(LinkagePicker.this.selectedFirstIndex);
                if (isUserScroll) {
                    i = 0;
                } else {
                    i = LinkagePicker.this.selectedSecondIndex;
                }
                wheelView.setItems(arrayList, i);
                if (LinkagePicker.this.thirdList.size() != 0) {
                    WheelView wheelView2 = thirdView;
                    ArrayList<String> arrayList2 = LinkagePicker.this.thirdList.get(LinkagePicker.this.selectedFirstIndex).get(0);
                    if (!isUserScroll) {
                        i2 = LinkagePicker.this.selectedThirdIndex;
                    }
                    wheelView2.setItems(arrayList2, i2);
                }
            }
        });
        secondView.setItems(this.secondList.get(this.selectedFirstIndex), this.selectedSecondIndex);
        secondView.setOnWheelViewListener(new WheelView.OnWheelViewListener() { // from class: com.vsf2f.f2f.ui.utils.area.LinkagePicker.2
            @Override // com.vsf2f.f2f.ui.utils.area.WheelView.OnWheelViewListener
            public void onSelected(boolean isUserScroll, int selectedIndex, String item) {
                LinkagePicker.this.selectedSecondText = item;
                LinkagePicker.this.selectedSecondIndex = selectedIndex;
                if (LinkagePicker.this.thirdList.size() != 0) {
                    thirdView.setItems(LinkagePicker.this.thirdList.get(LinkagePicker.this.selectedFirstIndex).get(LinkagePicker.this.selectedSecondIndex), isUserScroll ? 0 : LinkagePicker.this.selectedThirdIndex);
                }
            }
        });
        if (this.thirdList.size() != 0) {
            thirdView.setItems(this.thirdList.get(this.selectedFirstIndex).get(this.selectedSecondIndex), this.selectedThirdIndex);
            thirdView.setOnWheelViewListener(new WheelView.OnWheelViewListener() { // from class: com.vsf2f.f2f.ui.utils.area.LinkagePicker.3
                @Override // com.vsf2f.f2f.ui.utils.area.WheelView.OnWheelViewListener
                public void onSelected(boolean isUserScroll, int selectedIndex, String item) {
                    LinkagePicker.this.selectedThirdText = item;
                    LinkagePicker.this.selectedThirdIndex = selectedIndex;
                }
            });
        }
        return layout;
    }

    @Override // com.vsf2f.f2f.ui.utils.area.ConfirmPopup
    public void onSubmit() {
        if (this.onLinkageListener == null) {
            return;
        }
        if (this.onlyTwo) {
            this.onLinkageListener.onPicked(this.selectedFirstText, this.selectedSecondText, null);
        } else {
            this.onLinkageListener.onPicked(this.selectedFirstText, this.selectedSecondText, this.selectedThirdText);
        }
    }
}
