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

}
