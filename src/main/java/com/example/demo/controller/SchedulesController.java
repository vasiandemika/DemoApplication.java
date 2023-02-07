package com.example.demo.controller;

import com.example.demo.dto.SchedulesDTO;
import com.example.demo.model.Schedules;
import com.example.demo.services.SchedulesService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@JsonIgnoreProperties(value = {"_class"})
@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class SchedulesController {

    private final SchedulesService schedulesService;

    @GetMapping(path = "/get/grade")
    public int getGrade(@RequestParam Long studentId,@RequestParam Long courseId) {
        return this.schedulesService.getGrade(studentId, courseId);
    }

    @GetMapping(path = "/get/all")
    public List<SchedulesDTO> getSchedules(){

        return this.schedulesService.getSchedules();
    }

    @PostMapping("/add/student")
    public void addStudentToCourse(@RequestBody SchedulesDTO schedulesDTO) {
        schedulesService.addStudentToCourse(schedulesDTO);
    }

    @PutMapping("/add/grade")
    public void updateGrade(@RequestParam Long studentId,@RequestParam Long courseId, @RequestParam int grade){
        schedulesService.updateGrade(studentId, courseId, grade);
    }
}
