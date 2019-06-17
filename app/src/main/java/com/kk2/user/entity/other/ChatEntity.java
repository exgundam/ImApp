package com.kk2.user.entity.other;

import android.os.Parcel;
import android.os.Parcelable;

public class ChatEntity implements Parcelable {
    public String name;
    public String time;
    public String avatar;
    public String text;
    public String friendId;
    public int unReadCount;
    public String chatId;

    public ChatEntity(){

    }


    protected ChatEntity(Parcel in) {
        name = in.readString();
        time = in.readString();
        avatar = in.readString();
        text = in.readString();
        friendId = in.readString();
        unReadCount = in.readInt();
        chatId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(time);
        dest.writeString(avatar);
        dest.writeString(text);
        dest.writeString(friendId);
        dest.writeInt(unReadCount);
        dest.writeString(chatId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ChatEntity> CREATOR = new Creator<ChatEntity>() {
        @Override
        public ChatEntity createFromParcel(Parcel in) {
            return new ChatEntity(in);
        }

        @Override
        public ChatEntity[] newArray(int size) {
            return new ChatEntity[size];
        }
    };
}
