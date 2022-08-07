package com.vsf2f.f2f.ui.utils.area;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import com.easeui.utils.EaseCommonUtils;
import com.vsf2f.f2f.ui.utils.area.LinkagePicker;
import com.vsf2f.f2f.ui.utils.area.WheelView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class AddressPicker extends LinkagePicker {
    private OnAddressPickListener onAddressPickListener;
    private boolean hideProvince = false;
    private boolean hideCounty = false;
    private List<Province> provinceList = new ArrayList();

    /* loaded from: classes2.dex */
    public static class County extends Area {
    }

    /* loaded from: classes2.dex */
    public interface OnAddressPickListener {
        void onAddressPicked(Province province, City city, County county);
    }

    public AddressPicker(Context context, ArrayList<Province> data) {
        super(context);
        parseData(data);
    }

    private void parseData(ArrayList<Province> data) {
        int provinceSize = data.size();
        this.provinceList.clear();
        this.provinceList.addAll(data);
        for (int x = 0; x < provinceSize; x++) {
            Province pro = data.get(x);
            this.firstList.add(pro.getAreaName());
            ArrayList<City> cities = pro.getCities();
            ArrayList<String> xCities = new ArrayList<>();
            ArrayList<ArrayList<String>> xCounties = new ArrayList<>();
            int citySize = cities.size();
            for (int y = 0; y < citySize; y++) {
                City cit = cities.get(y);
                xCities.add(cit.getAreaName());
                ArrayList<County> counties = cit.getCounties();
                ArrayList<String> yCounties = new ArrayList<>();
                int countySize = counties.size();
                if (countySize == 0) {
                    yCounties.add(cit.getAreaName());
                } else {
                    for (int z = 0; z < countySize; z++) {
                        yCounties.add(counties.get(z).getAreaName());
                    }
                }
                xCounties.add(yCounties);
            }
            this.secondList.add(xCities);
            this.thirdList.add(xCounties);
        }
    }

    @Override // com.vsf2f.f2f.ui.utils.area.LinkagePicker
    public void setSelectedItem(String province, String city, String county) {
        super.setSelectedItem(province, city, county);
    }

    public void setHideProvince(boolean hideProvince) {
        this.hideProvince = hideProvince;
    }

    public void setHideCounty(boolean hideCounty) {
        this.hideCounty = hideCounty;
    }

    public void setOnAddressPickListener(OnAddressPickListener listener) {
        this.onAddressPickListener = listener;
    }

    @Override // com.vsf2f.f2f.ui.utils.area.LinkagePicker
    @Deprecated
    public void setOnLinkageListener(LinkagePicker.OnLinkageListener onLinkageListener) {
        throw new UnsupportedOperationException("Please use setOnAddressPickListener instead.");
    }

    @Override // com.vsf2f.f2f.ui.utils.area.LinkagePicker, com.vsf2f.f2f.ui.utils.area.ConfirmPopup
    @NonNull
    protected View makeCenterView() {
        if (this.hideCounty) {
            this.hideProvince = false;
        }
        if (this.firstList.size() == 0) {
            throw new IllegalArgumentException("please initial data at first, can't be empty");
        }
        LinearLayout layout = new LinearLayout(this.context);
        layout.setOrientation(0);
        layout.setGravity(17);
        WheelView provinceView = new WheelView(this.context);
        int width = this.screenWidthPixels / 3;
        provinceView.setLayoutParams(new LinearLayout.LayoutParams(width, -2));
        provinceView.setTextSize(this.textSize);
        provinceView.setTextColor(this.textColorNormal, this.textColorFocus);
        provinceView.setLineVisible(this.lineVisible);
        provinceView.setLineColor(this.lineColor);
        provinceView.setOffset(this.offset);
        layout.addView(provinceView);
        if (this.hideProvince) {
            provinceView.setVisibility(8);
        }
        final WheelView cityView = new WheelView(this.context);
        cityView.setLayoutParams(new LinearLayout.LayoutParams(width, -2));
        cityView.setTextSize(this.textSize);
        cityView.setTextColor(this.textColorNormal, this.textColorFocus);
        cityView.setLineVisible(this.lineVisible);
        cityView.setLineColor(this.lineColor);
        cityView.setOffset(this.offset);
        layout.addView(cityView);
        final WheelView countyView = new WheelView(this.context);
        countyView.setLayoutParams(new LinearLayout.LayoutParams(width, -2));
        countyView.setTextSize(this.textSize);
        countyView.setTextColor(this.textColorNormal, this.textColorFocus);
        countyView.setLineVisible(this.lineVisible);
        countyView.setLineColor(this.lineColor);
        countyView.setOffset(this.offset);
        layout.addView(countyView);
        if (this.hideCounty) {
            countyView.setVisibility(8);
        }
        provinceView.setItems(this.firstList, this.selectedFirstIndex);
        provinceView.setOnWheelViewListener(new WheelView.OnWheelViewListener() { // from class: com.vsf2f.f2f.ui.utils.area.AddressPicker.1
            @Override // com.vsf2f.f2f.ui.utils.area.WheelView.OnWheelViewListener
            public void onSelected(boolean isUserScroll, int selectedIndex, String item) {
                int i;
                int i2 = 0;
                AddressPicker.this.selectedFirstText = item;
                AddressPicker.this.selectedFirstIndex = selectedIndex;
                AddressPicker.this.selectedThirdIndex = 0;
                WheelView wheelView = cityView;
                List<String> list = (List) AddressPicker.this.secondList.get(AddressPicker.this.selectedFirstIndex);
                if (isUserScroll) {
                    i = 0;
                } else {
                    i = AddressPicker.this.selectedSecondIndex;
                }
                wheelView.setItems(list, i);
                WheelView wheelView2 = countyView;
                List<String> list2 = (List) ((ArrayList) AddressPicker.this.thirdList.get(AddressPicker.this.selectedFirstIndex)).get(0);
                if (!isUserScroll) {
                    i2 = AddressPicker.this.selectedThirdIndex;
                }
                wheelView2.setItems(list2, i2);
            }
        });
        cityView.setItems((List) this.secondList.get(this.selectedFirstIndex), this.selectedSecondIndex);
        cityView.setOnWheelViewListener(new WheelView.OnWheelViewListener() { // from class: com.vsf2f.f2f.ui.utils.area.AddressPicker.2
            @Override // com.vsf2f.f2f.ui.utils.area.WheelView.OnWheelViewListener
            public void onSelected(boolean isUserScroll, int selectedIndex, String item) {
                AddressPicker.this.selectedSecondText = item;
                AddressPicker.this.selectedSecondIndex = selectedIndex;
                countyView.setItems((List) ((ArrayList) AddressPicker.this.thirdList.get(AddressPicker.this.selectedFirstIndex)).get(AddressPicker.this.selectedSecondIndex), isUserScroll ? 0 : AddressPicker.this.selectedThirdIndex);
            }
        });
        countyView.setItems((List) ((ArrayList) this.thirdList.get(this.selectedFirstIndex)).get(this.selectedSecondIndex), this.selectedThirdIndex);
        countyView.setOnWheelViewListener(new WheelView.OnWheelViewListener() { // from class: com.vsf2f.f2f.ui.utils.area.AddressPicker.3
            @Override // com.vsf2f.f2f.ui.utils.area.WheelView.OnWheelViewListener
            public void onSelected(boolean isUserScroll, int selectedIndex, String item) {
                AddressPicker.this.selectedThirdText = item;
                AddressPicker.this.selectedThirdIndex = selectedIndex;
            }
        });
        return layout;
    }

    @Override // com.vsf2f.f2f.ui.utils.area.LinkagePicker, com.vsf2f.f2f.ui.utils.area.ConfirmPopup
    public void onSubmit() {
        if (this.onAddressPickListener != null) {
            Province province = this.provinceList.get(this.selectedFirstIndex);
            City city = this.provinceList.get(this.selectedFirstIndex).getCities().get(this.selectedSecondIndex);
            County county = null;
            if (!this.hideCounty) {
                county = this.provinceList.get(this.selectedFirstIndex).getCities().get(this.selectedSecondIndex).getCounties().get(this.selectedThirdIndex);
            }
            this.onAddressPickListener.onAddressPicked(province, city, county);
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Area {
        private String areaId;
        private String areaName;

        public String getAreaId() {
            return this.areaId;
        }

        public void setAreaId(String areaId) {
            this.areaId = areaId;
        }

        public String getAreaName() {
            return this.areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String toString() {
            return "areaId=" + this.areaId + ",areaName=" + this.areaName;
        }
    }

    /* loaded from: classes2.dex */
    public static class Province extends Area {
        private ArrayList<City> cities = new ArrayList<>();

        public ArrayList<City> getCities() {
            return this.cities;
        }

        public void setCities(ArrayList<City> cities) {
            this.cities = cities;
        }
    }

    /* loaded from: classes2.dex */
    public static class City extends Area {
        private ArrayList<County> counties = new ArrayList<>();
        private String initialLetter;

        public String getInitialLetter() {
            if (this.initialLetter == null) {
                this.initialLetter = EaseCommonUtils.setUserInitialLetter(getAreaName());
            }
            return this.initialLetter;
        }

        public void setInitialLetter(String initialLetter) {
            this.initialLetter = initialLetter;
        }

        public ArrayList<County> getCounties() {
            return this.counties;
        }

        public void setCounties(ArrayList<County> counties) {
            this.counties = counties;
        }
    }
}
