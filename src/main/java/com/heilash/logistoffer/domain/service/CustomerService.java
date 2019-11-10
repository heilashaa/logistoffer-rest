package com.heilash.logistoffer.domain.service;

import com.heilash.logistoffer.domain.exception.NotFoundException;
import com.heilash.logistoffer.domain.model.Customer;
import com.heilash.logistoffer.persistence.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.heilash.logistoffer.domain.exception.Errors.CUSTOMER_NOT_FOUND;
import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;


    public Customer create(Customer customer) {
        if (nonNull(customer)) {
            var persistenceCustomer = customerRepository.save(modelMapper.map(customer, com.heilash.logistoffer.persistence.model.Customer.class));
            return modelMapper.map(persistenceCustomer, Customer.class);
        }
        return null;
    }

    public Customer findById(Long id) {
        var optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.map(customer -> modelMapper.map(customer, Customer.class))
                .orElseThrow(() -> new NotFoundException(CUSTOMER_NOT_FOUND));
    }

    public Customer deleteById(Long id) {
        var optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            customerRepository.delete(optionalCustomer.get());
            return optionalCustomer.map(customer -> modelMapper.map(customer, Customer.class)).orElse(null);
        }
        return null;
    }

    public List<Customer> getAllCustomers() {
        var customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> modelMapper.map(customer, Customer.class))
                .collect(Collectors.toList());
    }

    public Customer update(Customer customer, Long id) {
        var persistenceCustomer = customerRepository.getOne(id);
        if (nonNull(customerRepository)) {
            modelMapper.map(customer, persistenceCustomer);
            persistenceCustomer = customerRepository.save(persistenceCustomer);
            return modelMapper.map(persistenceCustomer, Customer.class);
        }
        return null;
    }

}
