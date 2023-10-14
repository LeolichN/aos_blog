package com.aos.core.exception;

import com.aos.core.constants.BaseConstant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// 返回200
public class FailureMessageException extends RuntimeException {

    private int showType;

    public FailureMessageException() { }

    public FailureMessageException(String message) {
        super(message);
        showType = BaseConstant.SHOW_TYPE_ERROR_MESSAGE;
    }

    public FailureMessageException(String message, int showType) {
        super(message);
        this.showType = showType;
    }

}
