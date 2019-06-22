package com.kk2.user.entity.request;

public class Content {
    //auth
    public String AuthType;
    public String Credential;
    //getWeChat
    public String AccountType;
    public String UnionId;
    //login
    public String SupplierId;
    public WeChatInfo[] WeChats;
    //getFriendList
    public String WeChatId;
    //addFriend
    public String[] Phones;
    public String message;
    public Integer TaskId;
    public String IMEI;
    public String Remark;
    //talkToFriend
    public String FriendId;
    public Integer ContentType;//1
    public String Content;
    public Integer MsgId;


}
