package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("schedules")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedules {

    @Transient
    public static final String SEQUENCE_NAME = "schedule_sequence";
    @Id
    private Long id;

    private Long studentId;
    private Long courseId;

    private int grade;


}


