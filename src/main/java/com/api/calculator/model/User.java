package com.api.calculator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usr_session")
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    @Column(name = "user_name")
    private String userName;
    private String password;
}
