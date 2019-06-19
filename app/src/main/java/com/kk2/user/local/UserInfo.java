package com.kk2.user.local;

import com.kk2.user.entity.response.ChatRoomMembersBean;
import com.kk2.user.entity.response.ChatRoomsBean;
import com.kk2.user.entity.response.FriendsBean;
import com.kk2.user.entity.response.WeChatsBean;

import java.util.HashMap;

public class UserInfo {
    public static String AccessToken;
    public static String AccountType;
    public static String UnionId;

    public static String weChatId;
    public static WeChatsBean weChatsBean;

    public static String inChatId;

    public static HashMap<String, FriendsBean> friendsBeanHashMap=new HashMap<>();
    public static HashMap<String, ChatRoomsBean> chatRoomsBeanHashMap = new HashMap<>();
    public static HashMap<String, ChatRoomMembersBean> ChatRoomMemBerBeanHashMap = new HashMap<>();
}
