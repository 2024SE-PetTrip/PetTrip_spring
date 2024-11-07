package com.pettrip.apiPayload.exception.handler;

import com.pettrip.apiPayload.code.BaseErrorCode;
import com.pettrip.apiPayload.exception.GeneralException;

public class AppHandler extends GeneralException  {
    public AppHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
