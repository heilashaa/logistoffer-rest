package com.heilash.logistoffer.rest.message;

import com.heilash.logistoffer.rest.model.Customer;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CustomerRequest {



    @NotNull
    private Customer customer;
}
