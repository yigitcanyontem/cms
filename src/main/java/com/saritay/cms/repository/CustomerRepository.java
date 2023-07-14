package com.saritay.cms.repository;

import com.saritay.cms.dto.Active;
import com.saritay.cms.dto.Role;
import com.saritay.cms.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
    List<Customer> findAllByIsActiveNotAndRoleNot(Active active,Role role);
    Optional<Customer> findCustomerByIdAndIsActiveNotAndRoleNot(Integer id, Active active, Role role);
    Customer findCustomerByEmail(String email);
}
