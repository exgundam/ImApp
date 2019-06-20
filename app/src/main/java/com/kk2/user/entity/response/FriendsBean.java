package com.kk2.user.entity.response;

import android.os.Parcel;

import com.kk2.user.contacts.cn.CN;

import java.io.Serializable;

public class FriendsBean implements Serializable, CN {
    /**
     * FriendId : h12035534611
     * FriendNick : 华~~
     * Gender : Male
     * Province : 江苏
     * City : 苏州
     * Avatar : http://wx.qlogo.cn/mmhead/ver_1/m6d5M3ySiaZzvFHkofahf1dQhKI0BibpVn9jicicWQice1kXhbzuhzq6NIlhXhFRib4FPbabxiaakrLAIneDVm70AoNPbYribW2icUlTibeev3yWAvu9A/96
     * Type : 1
     * FriendNo : HongyangJIa
     */

    private String FriendId;
    private String FriendNick;
    private String Gender;
    private String Province;
    private String City;
    private String Avatar;
    private int Type;
    private String FriendNo;

    public FriendsBean(){

    }

    protected FriendsBean(Parcel in) {
        FriendId = in.readString();
        FriendNick = in.readString();
        Gender = in.readString();
        Province = in.readString();
        City = in.readString();
        Avatar = in.readString();
        Type = in.readInt();
        FriendNo = in.readString();
    }

    public String getFriendId() {
        return FriendId;
    }

    public void setFriendId(String FriendId) {
        this.FriendId = FriendId;
    }

    public String getFriendNick() {
        return FriendNick;
    }

    public void setFriendNick(String FriendNick) {
        this.FriendNick = FriendNick;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String Province) {
        this.Province = Province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String Avatar) {
        this.Avatar = Avatar;
    }

    public int getType() {
        return Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }

    public String getFriendNo() {
        return FriendNo;
    }

    public void setFriendNo(String FriendNo) {
        this.FriendNo = FriendNo;
    }

    @Override
    public String chinese() {
        return FriendNick;
    }
}
