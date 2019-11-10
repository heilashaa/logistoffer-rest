package com.heilash.logistoffer.rest.message;

import com.heilash.logistoffer.rest.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomersResponse extends BaseResponse{

    private List<Customer> customers;
}
