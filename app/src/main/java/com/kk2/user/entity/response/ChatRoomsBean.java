package com.kk2.user.entity.response;

import java.util.List;

public class ChatRoomsBean {
    /**
     * UserName : 22034238786@chatroom
     * NickName : 拉群耍
     * MemberList : ["wxid_htyqbey6pbc212","woshizhide2011","wxid_wrr2mzwcl86t22","liuhuijie387993","wxid_n4obp6tu9z9q12","sraliu","wxid_pcgmdndxh07p12","wxid_qhokc8epz0ag21","wxid_mtoqu5cui1lm12","wxid_ph4ajqi3eogc21","wxid_4282772823412","wxid_lsrovjiwnof622","wuzhiguoblog","wxid_4258262562812"]
     * Owner : wxid_htyqbey6pbc212
     * Avatar : http://wx.qlogo.cn/mmcrhead/OM4v0FU2h0sGuNnex2a3Ah043GQwicLvmvVrnBzCcOYDHIkBJqOfuuGvrYicNTKqO6xYkbWGHbSBTOsvSS2HXLIZv3T456h4rc/0
     */

    private String UserName;
    private String NickName;
    private String Owner;
    private String Avatar;
    private List<String> MemberList;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String NickName) {
        this.NickName = NickName;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String Owner) {
        this.Owner = Owner;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String Avatar) {
        this.Avatar = Avatar;
    }

    public List<String> getMemberList() {
        return MemberList;
    }

    public void setMemberList(List<String> MemberList) {
        this.MemberList = MemberList;
    }
}
