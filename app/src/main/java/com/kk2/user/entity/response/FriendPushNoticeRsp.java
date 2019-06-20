package com.kk2.user.entity.response;

import java.util.List;

public class FriendPushNoticeRsp {

    /**
     * WeChatId : wxid_n4obp6tu9z9q12
     * Friends : [{"FriendId":"h12035534611","FriendNick":"华~~","Gender":"Male","Province":"江苏","City":"苏州","Avatar":"http://wx.qlogo.cn/mmhead/ver_1/m6d5M3ySiaZzvFHkofahf1dQhKI0BibpVn9jicicWQice1kXhbzuhzq6NIlhXhFRib4FPbabxiaakrLAIneDVm70AoNPbYribW2icUlTibeev3yWAvu9A/96","Type":1},{"FriendId":"wxid_0qr17lb60arf12","FriendNo":"HongyangJIa","FriendNick":"静如止水","Gender":"Male","Province":"中国","City":"北京","Avatar":"http://wx.qlogo.cn/mmhead/ver_1/oI2yw8OSUmtp6qg6asMPm9FN4ya0mmb6GLOOSiaPiazyRgjwCm0ltG7U5yFCRXjVGbiaRvvSicxcocmOMPE5r9wwBGoad31aZaPCpsGnFaYSjL0/132","Type":3},{"FriendId":"liuhuijie387993","FriendNick":"刘孜扬","Gender":"Male","Province":"美国","City":"Santa Maria","Avatar":"http://wx.qlogo.cn/mmhead/ver_1/gowG1vouibA6ibmM13wBiby6NiaxjNvspwRZwwAoCm5D9tt97Jia4bnc4R3X7QooQmEiaUj5LUVDJhKkLaLzjufeuZKHILgjYcppbH4Hz7TdhVTiaU/96","Type":1}]
     * Size : 100
     * Count : 3
     */

    private String WeChatId;
    private int Size;
    private int Count;
    private List<FriendsBean> Friends;

    public FriendPushNoticeRsp(){

    }

    public String getWeChatId() {
        return WeChatId;
    }

    public void setWeChatId(String WeChatId) {
        this.WeChatId = WeChatId;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int Size) {
        this.Size = Size;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int Count) {
        this.Count = Count;
    }

    public List<FriendsBean> getFriends() {
        return Friends;
    }

    public void setFriends(List<FriendsBean> Friends) {
        this.Friends = Friends;
    }
}
