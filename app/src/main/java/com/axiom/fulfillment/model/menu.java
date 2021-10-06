package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class menu {
@SerializedName("HeadMenuDesc")
@Expose
private String headMenuDesc;
@SerializedName("HeadMenuActive")
@Expose
private String headMenuActive;
@SerializedName("HeadMenuFunction")
@Expose
private Object headMenuFunction;
@SerializedName("RoleId")
@Expose
private Integer roleId;
@SerializedName("HeadMenuId")
@Expose
private Integer headMenuId;
@SerializedName("OmchOrder")
@Expose
private Integer omchOrder;
@SerializedName("DetailMenu")
@Expose
private List<DetailedMenu> detailMenu = null;

public String getHeadMenuDesc() {
        return headMenuDesc;
        }

public void setHeadMenuDesc(String headMenuDesc) {
        this.headMenuDesc = headMenuDesc;
        }

public String getHeadMenuActive() {
        return headMenuActive;
        }

public void setHeadMenuActive(String headMenuActive) {
        this.headMenuActive = headMenuActive;
        }

public Object getHeadMenuFunction() {
        return headMenuFunction;
        }

public void setHeadMenuFunction(Object headMenuFunction) {
        this.headMenuFunction = headMenuFunction;
        }

public Integer getRoleId() {
        return roleId;
        }

public void setRoleId(Integer roleId) {
        this.roleId = roleId;
        }

public Integer getHeadMenuId() {
        return headMenuId;
        }

public void setHeadMenuId(Integer headMenuId) {
        this.headMenuId = headMenuId;
        }

public Integer getOmchOrder() {
        return omchOrder;
        }

public void setOmchOrder(Integer omchOrder) {
        this.omchOrder = omchOrder;
        }

public List<DetailedMenu> getDetailMenu() {
        return detailMenu;
        }

public void setDetailMenu(List<DetailedMenu> detailMenu) {
        this.detailMenu = detailMenu;
        }

}