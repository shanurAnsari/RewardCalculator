package com.customer.rewards.repository;

import com.customer.rewards.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
     Customer findByCustomerId(Long customerId);
}
