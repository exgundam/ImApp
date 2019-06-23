package com.kk2.user.entity.request;

import com.alibaba.fastjson.JSON;
import com.kk2.user.base.BaseChatReq;

public class ReqEntity {
    private ReqEntity(){

    }

    public static Builder getBuilder() {
        return new ReqEntity.Builder();
    }

    public static class Builder {
        private BaseChatReq baseChatReq;
        private Content content;

        public Builder() {
            baseChatReq=new BaseChatReq();
            content = new Content();
        }
        public Builder setMsgType(String MsgType){
            baseChatReq.MsgType=MsgType;
            return this;
        }

        public Builder setCredential(String Credential) {
            content.Credential = Credential;
            return this;
        }

        public Builder setAuthType(String AuthType) {
            content.AuthType = AuthType;
            return this;
        }

        public Builder setAccountType(String AccountType) {
            content.AccountType = AccountType;
            return this;
        }

        public Builder setUnionId(String UnionId) {
            content.UnionId = UnionId;
            return this;
        }

        public Builder setSupplierId(String SupplierId) {
            content.SupplierId = SupplierId;
            return this;
        }

        public Builder setWeChats(WeChatInfo[] WeChats) {
            content.WeChats = WeChats;
            return this;
        }

        public Builder setWeChatId(String WeChatId) {
            content.WeChatId = WeChatId;
            return this;
        }

        public Builder setPhones(String[] Phones) {
            content.Phones = Phones;
            return this;
        }

        public Builder setMessage(String message) {
            content.message = message;
            return this;
        }

        public Builder setTaskId(Integer TaskId) {
            content.TaskId = TaskId;
            return this;
        }

        public Builder setIMEI(String IMEI) {
            content.IMEI = IMEI;
            return this;
        }

        public Builder setRemark(String Remark) {
            content.Remark = Remark;
            return this;
        }

        public Builder setFriendId(String FriendId) {
            content.FriendId = FriendId;
            return this;
        }

        public Builder setContentType(Integer ContentType) {
            content.ContentType = ContentType;
            return this;
        }

        public Builder setContent(String Content) {
            content.Content = Content;
            return this;
        }

        public Builder setMsgId(Integer MsgId) {
            content.MsgId = MsgId;
            return this;
        }

        public Builder setStartTime(Long StartTime) {
            content.StartTime = StartTime;
            return this;
        }

        public String buildJsonToString(){
            baseChatReq.Content=content;
            return JSON.toJSONString(baseChatReq);
        }


    }


}
