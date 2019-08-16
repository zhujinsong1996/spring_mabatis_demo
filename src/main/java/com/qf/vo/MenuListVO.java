package com.qf.vo;

import lombok.Data;

/**
 * Created by DELL on 2019/8/6.
 */
@Data
public class MenuListVO {

    int mid;
    String menuname;
    String menuicon;
    String menu_url;
    int parentid;
    boolean parentMenu;


    public String getMenu_url() {
        return menu_url==null?"":menu_url;
    }


}
