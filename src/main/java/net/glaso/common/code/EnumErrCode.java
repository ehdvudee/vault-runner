package net.glaso.common.code;

public enum EnumErrCode {
    ERR_UNCLASSIFIED_SERVER(10000, "Unclassified_server_error"),

    ERR_ILLEGAL_ARGUMENT(20001, "Argument is invalid.");

    private int errCode;
    private String errMsg;

    EnumErrCode(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public int getErrCode() {
        return this.errCode;
    }
    public String getErrMsg() {
        return this.errMsg;
    }
}
