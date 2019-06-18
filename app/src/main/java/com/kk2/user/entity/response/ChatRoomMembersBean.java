package com.kk2.user.entity.response;

public class ChatRoomMembersBean {
    /**
     * Wxid : wxid_22yoy75h0af612
     * Nickname : 小星星club
     * Avatar : http: //wx.qlogo.cn/mmhead/ver_1/yBrwqOyQeI2SayxP8PzEKZH9d85N6PYHaibtKKH80skictlJCARTOJLF4iaZ6WmIQt2viclXZYobGTP6W6dwnwRfBGRWJTb7sXMG2EjADoSMOqc/132
     * Type : 4
     */

    private String Wxid;
    private String Nickname;
    private String Avatar;
    private int Type;

    public String getWxid() {
        return Wxid;
    }

    public void setWxid(String Wxid) {
        this.Wxid = Wxid;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String Nickname) {
        this.Nickname = Nickname;
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
}
