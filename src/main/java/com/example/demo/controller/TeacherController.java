package com.example.demo.controller;

import com.example.demo.dto.TeacherDTO;
import com.example.demo.model.Teacher;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.services.SequenceGeneratorService;
import com.example.demo.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/get")
    public List<Teacher> getTeacher() {

        return teacherService.getTeacher();
    }

    @PostMapping("/add")
    public void createTeacher(@RequestBody TeacherDTO teacherDTO) {

        this.teacherService.createTeacher(teacherDTO);
    }

    @DeleteMapping("/delete")
    public void deleteTeacher(@RequestParam Long teacherId) {
        teacherService.deleteTeacher(teacherId);
    }

    @PutMapping("/update")
    public void updateTeacher(
            @RequestParam Long teacherId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {

        teacherService.updateTeacher(teacherId, name, email);
    }
}
