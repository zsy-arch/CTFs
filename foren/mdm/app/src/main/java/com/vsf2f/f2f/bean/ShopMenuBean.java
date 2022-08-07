package com.vsf2f.f2f.bean;

import java.util.List;

/* loaded from: classes2.dex */
public class ShopMenuBean {
    private List<ChildrenBean> children;
    private int id;
    private boolean leaf;
    private int lv;
    private String name;
    private int orderNum;
    private int parentId;
    private String text;
    private int underCount;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return this.parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLv() {
        return this.lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public int getOrderNum() {
        return this.orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isLeaf() {
        return this.leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public int getUnderCount() {
        return this.underCount;
    }

    public void setUnderCount(int underCount) {
        this.underCount = underCount;
    }

    public List<ChildrenBean> getChildren() {
        return this.children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }

    /* loaded from: classes2.dex */
    public static class ChildrenBean {
        private int id;
        private boolean leaf;
        private int lv;
        private String name;
        private int orderNum;
        private int parentId;
        private String text;
        private int underCount;

        public int getId() {
            return this.id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getParentId() {
            return this.parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getLv() {
            return this.lv;
        }

        public void setLv(int lv) {
            this.lv = lv;
        }

        public int getOrderNum() {
            return this.orderNum;
        }

        public void setOrderNum(int orderNum) {
            this.orderNum = orderNum;
        }

        public String getText() {
            return this.text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public boolean isLeaf() {
            return this.leaf;
        }

        public void setLeaf(boolean leaf) {
            this.leaf = leaf;
        }

        public int getUnderCount() {
            return this.underCount;
        }

        public void setUnderCount(int underCount) {
            this.underCount = underCount;
        }
    }
}
