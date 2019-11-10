package com.heilash.logistoffer.rest.controller;

import com.heilash.logistoffer.domain.model.Customer;
import com.heilash.logistoffer.domain.service.CustomerService;
import com.heilash.logistoffer.rest.message.CustomerRequest;
import com.heilash.logistoffer.rest.message.CustomerResponse;
import com.heilash.logistoffer.rest.message.CustomersResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

import static com.heilash.logistoffer.config.Constants.Service.BASIC_AUTH;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "REST API for Customers. API allows operations with customers: create, read, update, delete.",
        tags = "customers",
        authorizations = {@Authorization(BASIC_AUTH)})
@RestController
@RequestMapping(value = "/api/customers",
        produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CustomersController {

    private CustomerService customerService;
    private ModelMapper modelMapper;


    @ApiOperation(value = "${customers.operation.create-customer}",
            nickname = "createCustomer",
            authorizations = {@Authorization(BASIC_AUTH)})
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public CustomerResponse createCustomer(
            @ApiParam(value = "${customers.param.customer}")
            @RequestBody @Valid CustomerRequest request) {
        var customer = modelMapper.map(request.getCustomer(), Customer.class);
        customer = customerService.create(customer);
        var apiCustomer = modelMapper.map(customer, com.heilash.logistoffer.rest.model.Customer.class);
        return new CustomerResponse(apiCustomer);
    }

    @ApiOperation(value = "${customers.operation.get-customer}",
            nickname = "getCustomer",
            authorizations = {@Authorization(BASIC_AUTH)})
    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public CustomerResponse getCustomer(
            @ApiParam(value = "${customers.param.customer-id}")
            @PathVariable Long id) {
        var customer = customerService.findById(id);
        var apiCustomer = modelMapper.map(customer, com.heilash.logistoffer.rest.model.Customer.class);
        return new CustomerResponse(apiCustomer);
    }

    @ApiOperation(value = "${customers.operation.delete-customer}",
            nickname = "deleteCustomer",
            authorizations = {@Authorization(BASIC_AUTH)})
    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public CustomerResponse deleteCustomer(
            @ApiParam(value = "${customers.param.customer-id}")
            @PathVariable Long id) {
        var customer = customerService.deleteById(id);
        var apiCustomer = modelMapper.map(customer, com.heilash.logistoffer.rest.model.Customer.class);
        return new CustomerResponse(apiCustomer);
    }

    @ApiOperation(value = "${customers.operation.getAll-customer}",
            nickname = "getAllCustomer",
            authorizations = {@Authorization(BASIC_AUTH)})
    @GetMapping("/")
    @ResponseStatus(OK)
    public CustomersResponse getCustomers(){
        var customers = customerService.getAllCustomers();
        var apiCustomers = customers.stream()
                .map(customer -> modelMapper.map(customer, com.heilash.logistoffer.rest.model.Customer.class))
                .collect(Collectors.toList());
        return new CustomersResponse(apiCustomers);
    }

    @ApiOperation(value = "${customers.operation.update-customer}",
            nickname = "updateCustomer",
            authorizations = {@Authorization(BASIC_AUTH)})
    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public CustomerResponse updateCustomer(
            @ApiParam(value = "${customers.param.customer-id}")
            @PathVariable Long id,
            @RequestBody @Valid CustomerRequest request){
        var customer = modelMapper.map(request.getCustomer(), Customer.class);
        Customer domainCustomer = customerService.update(customer, id);
        var apiCustomer = modelMapper.map(domainCustomer, com.heilash.logistoffer.rest.model.Customer.class);
        return new CustomerResponse(apiCustomer);
    }

}
