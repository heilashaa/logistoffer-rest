package com.heilash.logistoffer.rest.message;

import com.heilash.logistoffer.rest.model.Error;
import lombok.Data;

@Data
public class BaseResponse {

    private Error error;
}
