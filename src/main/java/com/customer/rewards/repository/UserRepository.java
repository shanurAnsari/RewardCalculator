package com.customer.rewards.repository;

import com.customer.rewards.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    public long countById(Integer id);

}
