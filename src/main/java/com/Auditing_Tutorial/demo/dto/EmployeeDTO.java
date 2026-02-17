package com.Auditing_Tutorial.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeDTO {
    private Long id;

    private String name;


    private String email;

    private int age;

    private LocalDate dateOfJoining;


    private boolean isActive;

    private  String role ; //admin or user

    private Integer  primePassword;

    private Double salary;


}
