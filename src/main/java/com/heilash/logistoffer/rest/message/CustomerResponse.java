package com.heilash.logistoffer.rest.message;

import com.heilash.logistoffer.rest.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse extends BaseResponse {

    private Customer customer;

}
