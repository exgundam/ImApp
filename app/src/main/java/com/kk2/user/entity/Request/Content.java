package com.kk2.user.entity.Request;

public class Content {
    //login
    public String AuthType;
    public String Credential;
    //getWeChat
    public String AccountType;
    public String UnionId;
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
