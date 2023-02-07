package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("courses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Transient
    public static final String SEQUENCE_NAME = "course_sequence";
    @Id
    private Long id;

    private int code;
    private String name;
    private Date startTime;
    private Date endTime;
    private int studentNo;
    private Long teacherId;

}
