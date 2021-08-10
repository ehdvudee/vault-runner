package net.glaso.common.excpetions;

import net.glaso.common.code.EnumErrCode;

public class VaultRunnerException extends RuntimeException {


    private EnumErrCode errCode;

    public VaultRunnerException(EnumErrCode errCode) {
        super(errCode.getErrMsg());
        this.errCode = errCode;
    }

    public VaultRunnerException(EnumErrCode errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
    }

    public VaultRunnerException(EnumErrCode errCode, Exception e) {
        super(errCode.getErrMsg(), e);
        this.errCode = errCode;
    }

    public VaultRunnerException(EnumErrCode errCode, String msg, Exception e) {
        super(msg, e);
        this.errCode = errCode;
    }
}
