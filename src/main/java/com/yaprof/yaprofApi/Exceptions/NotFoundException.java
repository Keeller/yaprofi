package com.yaprof.yaprofApi.Exceptions;

public class NotFoundException extends Exception {
    @Override
    public String getMessage() {
        return "value with this id not found. Reason: "+super.getMessage() ;
    }
}
