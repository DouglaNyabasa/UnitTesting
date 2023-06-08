package com.example.integrationtestingemployeecrud.model;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Employee_table")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    @Column(name = "first_name")
    private  String firstname;

    @Column(name = "last_name")
    private  String lastName;

    @Column(name = "dob")
    private  String dateOfBirth;

    @Column(name = "phone_number")
    private  String phoneNumber;

    @Column(name = "email")
    private  String email;










}
