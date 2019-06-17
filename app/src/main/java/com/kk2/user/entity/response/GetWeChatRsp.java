package com.kk2.user.entity.response;

import java.util.List;

public class GetWeChatRsp {

        /**
         * UnionId : 8
         * AccountType : SubUser
         * SupplierId : 7
         * WeChats : [{"WeChatId":"wxid_ph4ajqi3eogc21","WeChatNo":"rocinante66","WeChatNick":"F.D.单细胞","Avatar":"http://wx.qlogo.cn/mmhead/ver_1/onmL8HcEDWq08tvy0sJSHoLhOwwqlhGmvVUEaXgAb8DY7abaX7uVwzw5VSHCiaiaV6JBBian3tJDasDI3QKH6H1uPoRTuw0BFEAkCP2ktwFgJI/96","Province":"中国","City":"北京","Gender":"Male","IsOnline":true,"LoginTime":"1559810440000","ModifyTime":"1559810440000","DeviceName":"867078031930546","LoginUnionId":"8"}]
         */

        private String UnionId;
        private String AccountType;
        private String SupplierId;
        private List<WeChatsBean> WeChats;

        public String getUnionId() {
            return UnionId;
        }

        public void setUnionId(String UnionId) {
            this.UnionId = UnionId;
        }

        public String getAccountType() {
            return AccountType;
        }

        public void setAccountType(String AccountType) {
            this.AccountType = AccountType;
        }

        public String getSupplierId() {
            return SupplierId;
        }

        public void setSupplierId(String SupplierId) {
            this.SupplierId = SupplierId;
        }

        public List<WeChatsBean> getWeChats() {
            return WeChats;
        }

        public void setWeChats(List<WeChatsBean> WeChats) {
            this.WeChats = WeChats;
        }

        public static class WeChatsBean {
            /**
             * WeChatId : wxid_ph4ajqi3eogc21
             * WeChatNo : rocinante66
             * WeChatNick : F.D.单细胞
             * Avatar : http://wx.qlogo.cn/mmhead/ver_1/onmL8HcEDWq08tvy0sJSHoLhOwwqlhGmvVUEaXgAb8DY7abaX7uVwzw5VSHCiaiaV6JBBian3tJDasDI3QKH6H1uPoRTuw0BFEAkCP2ktwFgJI/96
             * Province : 中国
             * City : 北京
             * Gender : Male
             * IsOnline : true
             * LoginTime : 1559810440000
             * ModifyTime : 1559810440000
             * DeviceName : 867078031930546
             * LoginUnionId : 8
             */

            private String WeChatId;
            private String WeChatNo;
            private String WeChatNick;
            private String Avatar;
            private String Province;
            private String City;
            private String Gender;
            private boolean IsOnline;
            private String LoginTime;
            private String ModifyTime;
            private String DeviceName;
            private String LoginUnionId;

            public String getWeChatId() {
                return WeChatId;
            }

            public void setWeChatId(String WeChatId) {
                this.WeChatId = WeChatId;
            }

            public String getWeChatNo() {
                return WeChatNo;
            }

            public void setWeChatNo(String WeChatNo) {
                this.WeChatNo = WeChatNo;
            }

            public String getWeChatNick() {
                return WeChatNick;
            }

            public void setWeChatNick(String WeChatNick) {
                this.WeChatNick = WeChatNick;
            }

            public String getAvatar() {
                return Avatar;
            }

            public void setAvatar(String Avatar) {
                this.Avatar = Avatar;
            }

            public String getProvince() {
                return Province;
            }

            public void setProvince(String Province) {
                this.Province = Province;
            }

            public String getCity() {
                return City;
            }

            public void setCity(String City) {
                this.City = City;
            }

            public String getGender() {
                return Gender;
            }

            public void setGender(String Gender) {
                this.Gender = Gender;
            }

            public boolean isIsOnline() {
                return IsOnline;
            }

            public void setIsOnline(boolean IsOnline) {
                this.IsOnline = IsOnline;
            }

            public String getLoginTime() {
                return LoginTime;
            }

            public void setLoginTime(String LoginTime) {
                this.LoginTime = LoginTime;
            }

            public String getModifyTime() {
                return ModifyTime;
            }

            public void setModifyTime(String ModifyTime) {
                this.ModifyTime = ModifyTime;
            }

            public String getDeviceName() {
                return DeviceName;
            }

            public void setDeviceName(String DeviceName) {
                this.DeviceName = DeviceName;
            }

            public String getLoginUnionId() {
                return LoginUnionId;
            }

            public void setLoginUnionId(String LoginUnionId) {
                this.LoginUnionId = LoginUnionId;
        }
    }
}
