package com.saritay.cms.repository;

import com.saritay.cms.dto.Active;
import com.saritay.cms.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
    List<Customer> findAllByIsActiveNot(Active active);
    Optional<Customer> findCustomerByIdAndIsActiveNot(Integer id, Active active);
}
