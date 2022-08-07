package com.hy.frame.util;

import android.content.Context;
import android.content.res.XmlResourceParser;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.hy.frame.bean.MenuInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes2.dex */
public class MenuUtil {
    public static final String KEY_CLS = "cls";
    public static final String KEY_PAGER = "pager";

    public static List<MenuInfo> get(Context context, int res) {
        List<MenuInfo> menus = new ArrayList<>();
        XmlResourceParser xrp = context.getResources().getXml(res);
        if (xrp != null) {
            MenuInfo menu = null;
            while (xrp.getEventType() != 1) {
                try {
                    if (xrp.getEventType() == 2) {
                        String tagname = xrp.getName();
                        int size = xrp.getAttributeCount();
                        if (tagname.endsWith("item") && size > 2) {
                            menu = new MenuInfo();
                            for (int i = 0; i < size; i++) {
                                try {
                                    String key = xrp.getAttributeName(i);
                                    String value = xrp.getAttributeValue(i);
                                    if (key.contains("id")) {
                                        menu.setId(Integer.parseInt(value.replace("@", "")));
                                    } else if (key.contains(f.aY)) {
                                        menu.setIcon(Integer.parseInt(value.replace("@", "")));
                                    } else if (key.contains("title")) {
                                        menu.setTitle(Integer.parseInt(value.replace("@", "")));
                                    } else {
                                        menu.putValue(key, value.replace("@", ""));
                                    }
                                } catch (IOException e) {
                                    e = e;
                                    e.printStackTrace();
                                    return menus;
                                } catch (XmlPullParserException e2) {
                                    e = e2;
                                    e.printStackTrace();
                                    return menus;
                                }
                            }
                            menus.add(menu);
                            xrp.next();
                        }
                    }
                    menu = menu;
                    xrp.next();
                } catch (IOException e3) {
                    e = e3;
                } catch (XmlPullParserException e4) {
                    e = e4;
                }
            }
        }
        return menus;
    }
}
