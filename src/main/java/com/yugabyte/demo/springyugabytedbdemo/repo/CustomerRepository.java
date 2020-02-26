package com.yugabyte.demo.springyugabytedbdemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yugabyte.demo.springyugabytedbdemo.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

	Customer findByEmailId(final String emailId);
}

