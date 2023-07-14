package com.saritay.cms.service;

import com.saritay.cms.dto.*;
import com.saritay.cms.entity.Customer;
import com.saritay.cms.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<Customer> getCustomers(){
        return customerRepository.findAllByIsActiveNotAndRoleNot(Active.INACTIVE, Role.ADMIN);
    }

    public Optional<Customer> getCustomer(Integer id){
        return customerRepository.findCustomerByIdAndIsActiveNotAndRoleNot(id, Active.INACTIVE, Role.ADMIN);
    }
    public void createCustomer(CustomerRegistrationForm form){
         Customer customer = new Customer(
                 form.firstName(),
                 form.lastName(),
                 form.email(),
                 form.telNo(),
                 form.password(),
                 form.gender(),
                 Role.CUSTOMER,
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

    public String uploadPicture(MultipartFile file, Integer id){
        if (file.isEmpty()) {
            return "No file selected";
        }
        try {
            byte[] bytes = file.getBytes();

            String filePath = "photos/" + id+".jpg";
            File serverFile = new File(filePath);

            FileUtils.writeByteArrayToFile(serverFile, bytes);

            return "File uploaded successfully";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error uploading file";
        }
    }

    public LoginReturnDTO login(CustomerLoginForm form) {
        Customer customer = customerRepository.findCustomerByEmail(form.email());
        if (customer.getPassword().equals(form.password()) && customer.getIsActive().equals(Active.ACTIVE)){
            return new LoginReturnDTO(
                    customer.getId(),
                    customer.getRole()
            );
        }else {
            throw new RuntimeException();
        }
    }
}
