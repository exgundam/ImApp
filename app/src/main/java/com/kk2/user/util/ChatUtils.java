package com.kk2.user.util;

import android.text.TextUtils;

import com.kk2.user.entity.response.FriendsBean;
import com.kk2.user.local.UserInfo;

import java.util.List;

public class ChatUtils {
    public static FriendsBean getFriendEntity(String friendId) {
        if (TextUtils.isEmpty(friendId)) {
            return null;
        }
        List<FriendsBean> friendsBeans = UserInfo.friendPushNoticeRsp.getFriends();
        for (FriendsBean friendsBean : friendsBeans) {
            if (friendId.equals(friendsBean.getFriendId())) {
                return friendsBean;
            }
        }
        return null;
    }
}

