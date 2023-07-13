package com.saritay.cms.mapper;


import com.saritay.cms.dto.CustomerDTO;
import com.saritay.cms.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CustomerDTOMapper implements Function<Customer, CustomerDTO> {
    @Override
    public CustomerDTO apply(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getTelNo(),
                customer.getGender()
        );
    }
}

