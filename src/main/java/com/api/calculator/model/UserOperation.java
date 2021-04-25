package com.api.calculator.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Builder
@Table(name = "user_operation")
public class UserOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String token;
    private BigDecimal result;
    @OneToMany(mappedBy = "userOperation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Operand> operandList;

}
