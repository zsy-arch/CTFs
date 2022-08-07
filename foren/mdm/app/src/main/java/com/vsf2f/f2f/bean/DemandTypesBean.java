package com.vsf2f.f2f.bean;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes2.dex */
public class DemandTypesBean implements Serializable {
    private List<HomePageListBean> homePageList;
    private List<ChildsBean> obj;

    public List<ChildsBean> getObj() {
        return this.obj;
    }

    public void setObj(List<ChildsBean> obj) {
        this.obj = obj;
    }

    public List<HomePageListBean> getHomePageList() {
        return this.homePageList;
    }

    public void setHomePageList(List<HomePageListBean> homePageList) {
        this.homePageList = homePageList;
    }

    /* loaded from: classes2.dex */
    public static class ChildsBean implements Serializable {
        private boolean check;
        private List<ChildsBean> childs;
        private String code;
        private int id;
        private String name;

        public String getCode() {
            return this.code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getId() {
            return this.id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ChildsBean> getChilds() {
            return this.childs;
        }

        public void setChilds(List<ChildsBean> childs) {
            this.childs = childs;
        }

        public boolean isCheck() {
            return this.check;
        }

        public void setCheck(boolean check) {
            this.check = check;
        }
    }

    /* loaded from: classes2.dex */
    public static class HomePageListBean implements Serializable {
        private int id;
        private String name;

        public int getId() {
            return this.id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
