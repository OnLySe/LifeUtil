package com.zzq.base.bean;

public class ResponseMsgBean {

    private int msgCode;
    private String msg;

    public ResponseMsgBean(int msgCode, String msg) {
        this.msgCode = msgCode;
        this.msg = msg;
    }

    public int getMsgCode() {
        return msgCode;
    }

    public String getMsg() {
        return msg;
    }
}
