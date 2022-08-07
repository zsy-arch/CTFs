package com.vsf2f.f2f.bean;

import com.litepal.crud.DataSupport;

/* loaded from: classes2.dex */
public class DBCircleDraftBean extends DataSupport {
    private String commodity_classify;
    private int commodity_discount;
    private String commodity_explain;
    private String commodity_freight;
    private String commodity_goodsCategoryId;
    private String commodity_goodsTypeGuid;
    private String commodity_goodsTypedId;
    private String commodity_inventory;
    private String commodity_menu;
    private String commodity_name;
    private String commodity_price;
    private String commodity_scroll;
    private String commodity_shopMenusId;
    private String draftId;
    private String img_cover;
    private String img_cover_id;
    private String img_list;
    private String nickname;
    private String text_content;
    private String text_options;
    private String text_title;
    private String type;
    private String username;

    public String getId() {
        return this.draftId;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
        this.draftId = this.username + type;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
        this.draftId = username + this.type;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImg_list() {
        return this.img_list;
    }

    public void setImg_list(String img_list) {
        this.img_list = img_list;
    }

    public String getImg_cover() {
        return this.img_cover;
    }

    public void setImg_cover(String img_cover) {
        this.img_cover = img_cover;
    }

    public String getImg_cover_id() {
        return this.img_cover_id;
    }

    public void setImg_cover_id(String img_cover_id) {
        this.img_cover_id = img_cover_id;
    }

    public String getText_content() {
        return this.text_content;
    }

    public void setCommodity_goodsTypedId(String commodity_goodsTypedId) {
        this.commodity_goodsTypedId = commodity_goodsTypedId;
    }

    public void setCommodity_goodsCategoryId(String commodity_goodsCategoryId) {
        this.commodity_goodsCategoryId = commodity_goodsCategoryId;
    }

    public void setCommodity_goodsTypeGuid(String commodity_goodsTypeGuid) {
        this.commodity_goodsTypeGuid = commodity_goodsTypeGuid;
    }

    public void setCommodity_shopMenusId(String commodity_shopMenusId) {
        this.commodity_shopMenusId = commodity_shopMenusId;
    }

    public String getCommodity_goodsTypedId() {
        return this.commodity_goodsTypedId;
    }

    public String getCommodity_goodsCategoryId() {
        return this.commodity_goodsCategoryId;
    }

    public String getCommodity_goodsTypeGuid() {
        return this.commodity_goodsTypeGuid;
    }

    public String getCommodity_shopMenusId() {
        return this.commodity_shopMenusId;
    }

    public String getText_options() {
        return this.text_options;
    }

    public void setText_options(String text_options) {
        this.text_options = text_options;
    }

    public String getText_title() {
        return this.text_title;
    }

    public void setText_title(String text_title) {
        this.text_title = text_title;
    }

    public void setText_content(String text_content) {
        this.text_content = text_content;
    }

    public String getCommodity_name() {
        return this.commodity_name;
    }

    public void setCommodity_name(String commodity_name) {
        this.commodity_name = commodity_name;
    }

    public String getCommodity_price() {
        return this.commodity_price;
    }

    public void setCommodity_price(String commodity_price) {
        this.commodity_price = commodity_price;
    }

    public String getCommodity_inventory() {
        return this.commodity_inventory;
    }

    public void setCommodity_inventory(String commodity_inventory) {
        this.commodity_inventory = commodity_inventory;
    }

    public String getCommodity_freight() {
        return this.commodity_freight;
    }

    public void setCommodity_freight(String commodity_freight) {
        this.commodity_freight = commodity_freight;
    }

    public String getCommodity_classify() {
        return this.commodity_classify;
    }

    public void setCommodity_classify(String commodity_classify) {
        this.commodity_classify = commodity_classify;
    }

    public String getCommodity_menu() {
        return this.commodity_menu;
    }

    public void setCommodity_menu(String commodity_menu) {
        this.commodity_menu = commodity_menu;
    }

    public int getCommodity_discount() {
        return this.commodity_discount;
    }

    public void setCommodity_discount(int commodity_discount) {
        this.commodity_discount = commodity_discount;
    }

    public String getCommodity_scroll() {
        return this.commodity_scroll;
    }

    public void setCommodity_scroll(String commodity_scroll) {
        this.commodity_scroll = commodity_scroll;
    }

    public String getCommodity_explain() {
        return this.commodity_explain;
    }

    public void setCommodity_explain(String commodity_explain) {
        this.commodity_explain = commodity_explain;
    }
}
