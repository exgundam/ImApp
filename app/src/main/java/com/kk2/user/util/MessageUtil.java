package com.kk2.user.util;

import com.kk2.user.entity.other.ChatEntity;
import com.kk2.user.entity.response.ChatRoomMembersBean;
import com.kk2.user.entity.response.ChatRoomsBean;
import com.kk2.user.entity.response.FriendTalkRsp;
import com.kk2.user.entity.response.FriendsBean;

public class MessageUtil {
    public static final String MESSAGE_TYPE_TEXT="text";
    public static void setEntity(ChatEntity entity, FriendTalkRsp rsp){
        String content=rsp.getContent();
        content = MyUtils.Base64DecodeUtf8(content);
        entity.friendId=rsp.getFriendId();
        entity.time = "22：00";
        if (entity.friendId.endsWith("@chatroom")) {
            ChatRoomsBean chatRoomsBean = ChatUtils.getChatRoomEntity(entity.friendId);
            if (chatRoomsBean != null) {
                entity.avatar = chatRoomsBean.getAvatar();
                entity.name = chatRoomsBean.getNickName();
                switch (rsp.getContentType()){
                    case MESSAGE_TYPE_TEXT:
                        String memId = content.split(":\n")[0];
                        String talkContent = content.split(":\n")[1];
                        FriendsBean friendsBean = ChatUtils.getFriendEntity(memId);
                        if (friendsBean != null) {
                            entity.text = friendsBean.getFriendNick() + ": " + talkContent;
                        } else {
                            ChatRoomMembersBean roomMembersBean = ChatUtils.getChatMemberEntity(memId);
                            if (roomMembersBean != null)
                                entity.text = roomMembersBean.getNickname() + ": " + talkContent;
                        }
                        break;
                        default:
                            entity.text=content;
                            break;
                }

            }
        } else {
            FriendsBean friendsBean = ChatUtils.getFriendEntity(entity.friendId);
            if (friendsBean != null) {
                entity.avatar = friendsBean.getAvatar();
                entity.name = friendsBean.getFriendNick();
                entity.text =content;
            }
        }
    }
}
