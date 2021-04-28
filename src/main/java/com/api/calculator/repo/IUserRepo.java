package com.api.calculator.repo;

import com.api.calculator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User, Integer> {

    User findByUserName(String userName);
}
