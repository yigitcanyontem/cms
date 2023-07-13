package com.saritay.cms.service;

import com.saritay.cms.dto.Active;
import com.saritay.cms.dto.CustomerDTO;
import com.saritay.cms.dto.CustomerRegistrationForm;
import com.saritay.cms.dto.CustomerUpdateForm;
import com.saritay.cms.entity.Customer;
import com.saritay.cms.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<Customer> getCustomers(){
        return customerRepository.findAllByIsActiveNot(Active.INACTIVE);
    }

    public Optional<Customer> getCustomer(Integer id){
        return customerRepository.findCustomerByIdAndIsActiveNot(id, Active.INACTIVE);
    }
    public void createCustomer(CustomerRegistrationForm form){
         Customer customer = new Customer(
                 form.firstName(),
                 form.lastName(),
                 form.email(),
                 form.telNo(),
                 form.password(),
                 form.gender(),
                 Active.ACTIVE
         );
         customerRepository.save(customer);
    }

    public void deleteCustomer(Integer id){
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.setIsActive(Active.INACTIVE);
        customerRepository.save(customer);
    }

    public void updateCustomer(Integer id, CustomerUpdateForm customerUpdateForm) {
        Customer customer = customerRepository.getReferenceById(id);
        String telNo = customerUpdateForm.telNo();
        String passwords = customerUpdateForm.password();
        String oldpassword = customerUpdateForm.oldpassword();
        if (!Objects.equals(customer.getTelNo(), telNo) && telNo != null){
            customer.setTelNo(telNo);
        }

        if (Objects.equals(customer.getPassword(),oldpassword) && passwords != null && oldpassword != null ){
            customer.setPassword(passwords);
        }
        customerRepository.save(customer);
    }
}
