package com.yaprof.yaprofApi.Responsec;

import com.yaprof.yaprofApi.ResponseData.IResponseData;

public class BaseResponse {
    private String code;
    private String error;
    private IResponseData responseData;

    public BaseResponse(String code, String error, IResponseData responseData) {
        this.code = code;
        this.error = error;
        this.responseData = responseData;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public IResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(IResponseData responseData) {
        this.responseData = responseData;
    }
}
