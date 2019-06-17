package com.kk2.user.entity.response;

public class FriendTalkRsp {

    /**
     * WeChatId : wxid_mtoqu5cui1lm12
     * FriendId : wxid_ingw614r1e4722
     * ContentType : Text
     * Content : c3M=
     * MsgId : 18
     * msgSvrId : 5223283798547597291
     */

    private String WeChatId;
    private String FriendId;
    private String ContentType;
    private String Content;
    private String MsgId;
    private String msgSvrId;

    public String getWeChatId() {
        return WeChatId;
    }

    public void setWeChatId(String WeChatId) {
        this.WeChatId = WeChatId;
    }

    public String getFriendId() {
        return FriendId;
    }

    public void setFriendId(String FriendId) {
        this.FriendId = FriendId;
    }

    public String getContentType() {
        return ContentType;
    }

    public void setContentType(String ContentType) {
        this.ContentType = ContentType;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String MsgId) {
        this.MsgId = MsgId;
    }

    public String getMsgSvrId() {
        return msgSvrId;
    }

    public void setMsgSvrId(String msgSvrId) {
        this.msgSvrId = msgSvrId;
    }
}
