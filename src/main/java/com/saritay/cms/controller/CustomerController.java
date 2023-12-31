package com.saritay.cms.controller;

import com.saritay.cms.dto.*;
import com.saritay.cms.mapper.CustomerDTOMapper;
import com.saritay.cms.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200/")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerDTOMapper customerDTOMapper;

    @GetMapping("")
    public List<CustomerDTO> getCustomers(){
        return customerService.getCustomers().stream().map(customerDTOMapper).collect(Collectors.toList());
    }

    @GetMapping("id/{id}")
    public CustomerDTO getCustomer(@PathVariable Integer id){
        return customerService.getCustomer(id).stream().map(customerDTOMapper).toList().get(0);
    }

    @PostMapping("register")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerRegistrationForm customerRegistrationForm){
        customerService.createCustomer(customerRegistrationForm);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer id){
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Integer id,@RequestBody CustomerUpdateForm customerUpdateForm){
        customerService.updateCustomer(id, customerUpdateForm);
        return ResponseEntity.ok().build();
    }

    @PostMapping("upload/{id}")
    public String uploadImage(@RequestParam("file") MultipartFile file, @PathVariable Integer id) {
        return customerService.uploadPicture(file, id);
    }

    @PostMapping("login")
    public LoginReturnDTO uploadImage(@RequestBody CustomerLoginForm form) {
        return customerService.login(form);
    }

    @GetMapping("/images/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        String imagePath = "photos/" + imageName + ".jpg";
        File imageFile = new File(imagePath);
        Resource resource = new FileSystemResource(imageFile);

        if (resource.exists()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return ResponseEntity.ok().headers(headers).body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
