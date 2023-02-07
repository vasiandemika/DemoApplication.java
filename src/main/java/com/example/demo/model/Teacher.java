package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("teachers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    @Transient
    public static final String SEQUENCE_NAME = "teacher_sequence";
    @Id
    private Long id;

    private String name;
    private String email;
    private String userName;
    private int cardNo;
    private Boolean isActive;
}
