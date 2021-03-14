package com.yaprof.yaprofApi.Controllers;

import com.yaprof.yaprofApi.Responsec.BaseResponse;

public interface BaseController {

    default BaseResponse fallback(String code,String fallbackMsg){
        return new BaseResponse(code,fallbackMsg,null);
    }
}
