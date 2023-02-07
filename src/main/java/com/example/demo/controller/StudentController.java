package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.dto.StudentDTO;
import com.example.demo.services.SequenceGeneratorService;
import com.example.demo.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentRepository studentRepository;
    private final StudentService studentService;

    @GetMapping("/get")
    public List<Student> getAllStudents(){

        return studentService.getAll();
    }

    @PostMapping("/add")
    public void createStudent(@RequestBody StudentDTO studentDTO,
                              @RequestParam MultipartFile file) throws IOException {

        studentService.createStudent(studentDTO, file);
    }

    @DeleteMapping("/delete")
    public void deleteStudent(@RequestParam Long studentId) {
        studentService.deleteStudent(studentId);
    }
}
