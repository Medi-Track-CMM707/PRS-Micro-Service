package com.meditrack.prs.exception;

import com.meditrack.prs.constant.ErrorCode;
import lombok.Getter;

@Getter
public class PrsNotFoundException extends Exception {
    private final ErrorCode errorCode;

    public PrsNotFoundException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
