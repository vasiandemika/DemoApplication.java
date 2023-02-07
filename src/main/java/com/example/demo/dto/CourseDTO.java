package com.example.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CourseDTO {

    private int code;
    private String name;
    private Date startTime;
    private Date endTime;
    private Long teacherId;

}
