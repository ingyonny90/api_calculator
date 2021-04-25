package com.api.calculator.repo;

import com.api.calculator.model.Operand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOperandRepo extends JpaRepository<Operand, Integer> {
}
