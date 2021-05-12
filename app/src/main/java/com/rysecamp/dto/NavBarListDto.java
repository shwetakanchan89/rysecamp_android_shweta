package com.rysecamp.dto;

/**
 * Created by sahil on 01/03/21.
 */

public class NavBarListDto {

    private ViewType viewType;
    private String menuName;
    private Integer menuIcon;

    public NavBarListDto(ViewType viewType) {
        this.viewType = viewType;
    }

    public NavBarListDto(ViewType viewType, String menuName, Integer menuIcon) {
        this.viewType = viewType;
        this.menuName = menuName;
        this.menuIcon = menuIcon;
    }

    public ViewType getViewType() {
        return viewType;
    }

    public void setViewType(ViewType viewType) {
        this.viewType = viewType;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(Integer menuIcon) {
        this.menuIcon = menuIcon;
    }

    public enum ViewType {
        HEADER, MENU_ITEM, FOOTER
    }
}
