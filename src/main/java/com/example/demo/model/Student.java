package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Transient
    public static final String SEQUENCE_NAME = "student_sequence";
    @Id
    private Long id;

    private String name;
    private int cardNo;
    private String phoneNo;
    private String email;
    private String userName;
    private Binary certificate;

}
