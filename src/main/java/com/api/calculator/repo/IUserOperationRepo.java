package com.api.calculator.repo;

import com.api.calculator.model.UserOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserOperationRepo extends JpaRepository<UserOperation, Integer> {
}
