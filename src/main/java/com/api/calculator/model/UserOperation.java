package com.api.calculator.model;

import com.api.calculator.model.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_operation")
public class UserOperation extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String token;
    private BigDecimal result;
    @OneToMany(mappedBy = "userOperation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Operand> operandList;

}
