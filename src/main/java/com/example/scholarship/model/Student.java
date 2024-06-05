package com.example.scholarship.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    private String rollNumber;
    private String studentName;
    private int science;
    private int maths;
    private int english;
    private int computer;
    private String eligible;
}
