package com.saritay.cms.mapper;


import com.saritay.cms.dto.CustomerDTO;
import com.saritay.cms.entity.Customer;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.function.Function;

@Service
public class CustomerDTOMapper implements Function<Customer, CustomerDTO> {
    public String getImage(Integer id) throws IOException {
        String imagePath = "static/"+id+".jpg" ;
        ClassPathResource resource = new ClassPathResource(imagePath);
        InputStream inputStream = resource.getInputStream();

        byte[] imageBytes = IOUtils.toByteArray(inputStream);
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    @Override
    public CustomerDTO apply(Customer customer) {
        try {
            return new CustomerDTO(
                    customer.getId(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getEmail(),
                    customer.getTelNo(),
                    customer.getGender(),
                    getImage(customer.getId())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

