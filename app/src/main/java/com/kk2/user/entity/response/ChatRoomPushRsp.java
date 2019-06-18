package com.kk2.user.entity.response;

import java.util.List;

public class ChatRoomPushRsp {

    /**
     * WeChatId : wxid_n4obp6tu9z9q12
     * ChatRooms : [{"UserName":"22034238786@chatroom","NickName":"拉群耍","MemberList":["wxid_htyqbey6pbc212","woshizhide2011","wxid_wrr2mzwcl86t22","liuhuijie387993","wxid_n4obp6tu9z9q12","sraliu","wxid_pcgmdndxh07p12","wxid_qhokc8epz0ag21","wxid_mtoqu5cui1lm12","wxid_ph4ajqi3eogc21","wxid_4282772823412","wxid_lsrovjiwnof622","wuzhiguoblog","wxid_4258262562812"],"Owner":"wxid_htyqbey6pbc212"},{"UserName":"17201671717@chatroom","NickName":"刘孜扬、小禹哥哥、F.D.单细胞、通州T1-百废待兴","MemberList":["liuhuijie387993","wxid_n4obp6tu9z9q12","wxid_ph4ajqi3eogc21","wxid_htyqbey6pbc212"],"Owner":"liuhuijie387993","Avatar":"http://wx.qlogo.cn/mmcrhead/OM4v0FU2h0sGuNnex2a3Ah043GQwicLvmvVrnBzCcOYDHIkBJqOfuuGvrYicNTKqO6xYkbWGHbSBTOsvSS2HXLIZv3T456h4rc/0"}]
     * Size : 2
     * Count : 6
     * Page : 1
     */

    private String WeChatId;
    private int Size;
    private int Count;
    private int Page;
    private List<ChatRoomsBean> ChatRooms;

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

    public int getPage() {
        return Page;
    }

    public void setPage(int Page) {
        this.Page = Page;
    }

    public List<ChatRoomsBean> getChatRooms() {
        return ChatRooms;
    }

    public void setChatRooms(List<ChatRoomsBean> ChatRooms) {
        this.ChatRooms = ChatRooms;
    }

}
