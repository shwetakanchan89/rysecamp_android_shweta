package com.rysecamp.utils;

import com.rysecamp.dto.ResponseDto;

/**
 * Created by Sahil Sharma on 10/10/18.
 */

public class ErrorParser {

    public static ResponseDto parseError(Throwable e) {
        if (e instanceof RetrofitException) {
            try {
                RetrofitException error = (RetrofitException) e;
                ResponseDto response = error.getErrorBodyAs(ResponseDto.class);
                if (response == null)
                    response = new ResponseDto();
                response.setStatus(Boolean.FALSE);
                if (error.getResponse() != null)
                    response.setStatusCode(error.getResponse().code());
                if (error.getKind() != null)
                    response.setKind(error.getKind().name());
                if (response.getErrorMessage() == null || response.getErrorMessage().isEmpty())
                    response.setErrorMessage(e.getMessage());
                return response;
            } catch (Exception e1) {
                e1.printStackTrace();
                ResponseDto response = new ResponseDto();
                response.setStatus(Boolean.FALSE);
                response.setErrorMessage(e.getMessage());
                return response;
            }
        }
        return null;
    }

}
