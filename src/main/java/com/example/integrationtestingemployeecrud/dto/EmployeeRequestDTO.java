package com.example.integrationtestingemployeecrud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDTO {

    private  String firstname;
    private  String lastName;
    private  String email;

    private  String dateOfBirth;
    private  String phoneNumber;



}
