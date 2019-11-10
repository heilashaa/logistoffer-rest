package com.heilash.logistoffer.domain.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends ServiceException {

    public NotFoundException(String code) {
        super(code);
    }
}
