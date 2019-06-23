package com.kk2.user.core;

public interface ChatMsgType {

    String DeviceAuthReq="DeviceAuthReq";//微信登录授权
    String DeviceAuthRsp = "DeviceAuthRsp";

    String GetWeChatsReq = "GetWeChatsReq";//获取微信信息
    String GetWeChatsRsp="GetWeChatsRsp";

    String WeChatLoginNotice="WeChatLoginNotice";//登录
    String MsgReceivedAck="MsgReceivedAck";//临时用于登录成功

    String TriggerFriendPushTask="TriggerFriendPushTask";//获取好友列表
    String FriendPushNotice="FriendPushNotice";

    String AddFriendsTask="AddFriendsTask";//添加好友

    String TalkToFriendTask="TalkToFriendTask";//发送消息

    String TriggerChatroomPushTask="TriggerChatroomPushTask";//群聊信息
    String ChatroomPushNotice="ChatroomPushNotice";
    String ChatRoomMembersNotice="ChatRoomMembersNotice";

    String FriendTalkNotice="FriendTalkNotice";//单聊群聊

    String TriggerCirclePushTask="TriggerCirclePushTask";//获取自己的朋友圈

    String Error="Error";//错误



}
