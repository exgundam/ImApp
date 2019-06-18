package com.kk2.user.util;

import android.text.TextUtils;

import com.kk2.user.entity.response.ChatRoomMembersBean;
import com.kk2.user.entity.response.ChatRoomsBean;
import com.kk2.user.entity.response.FriendsBean;
import com.kk2.user.local.UserInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChatUtils {
    public static FriendsBean getFriendEntity(String friendId) {
        if (TextUtils.isEmpty(friendId)) {
            return null;
        }
        if (UserInfo.friendsBeanHashMap == null || UserInfo.friendsBeanHashMap.isEmpty()) {
            return null;
        }
        return UserInfo.friendsBeanHashMap.get(friendId);
    }

    public static ChatRoomsBean getChatRoomEntity(String friendId) {
        if (TextUtils.isEmpty(friendId)) {
            return null;
        }
        if (UserInfo.chatRoomsBeanHashMap == null || UserInfo.chatRoomsBeanHashMap.isEmpty()) {
            return null;
        }
        return UserInfo.chatRoomsBeanHashMap.get(friendId);
    }

    public static ChatRoomMembersBean getChatMemberEntity(String memId) {
        if (TextUtils.isEmpty(memId)) {
            return null;
        }
        if (UserInfo.chatRoomsBeanHashMap == null || UserInfo.chatRoomsBeanHashMap.isEmpty()) {
            return null;
        }
        return UserInfo.ChatRoomMemBerBeanHashMap.get(memId);
    }

    public static List<FriendsBean> getFriendList() {

        List<FriendsBean> friendsBeans = new ArrayList<>();
        for (Map.Entry<String, FriendsBean> entry : UserInfo.friendsBeanHashMap.entrySet()) {
            friendsBeans.add(entry.getValue());
        }
        return friendsBeans;
    }
}

