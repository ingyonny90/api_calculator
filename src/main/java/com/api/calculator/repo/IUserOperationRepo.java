package com.api.calculator.repo;

import com.api.calculator.model.UserOperation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserOperationRepo extends JpaRepository<UserOperation, Integer> {

    Optional<UserOperation> findByToken(String token);
}
