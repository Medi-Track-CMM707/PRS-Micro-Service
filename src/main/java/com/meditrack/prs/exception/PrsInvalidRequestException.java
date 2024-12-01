package com.meditrack.prs.exception;

import com.meditrack.prs.constant.ErrorCode;
import lombok.Getter;

@Getter
public class PrsInvalidRequestException extends Exception {
    private final ErrorCode errorCode;

    public PrsInvalidRequestException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
