package com.example.scholarship.controller;

import com.example.scholarship.model.Student;
import com.example.scholarship.service.StudentService;
import com.example.scholarship.utils.CsvUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/")
    public String giveResponse() {
        return "Hello Worl";
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) {
        try {
            List<Student> students = CsvUtils.read(Student.class, file.getInputStream());
            System.out.println(students);
            studentService.processCsvAsync(students);
            return ResponseEntity.ok("File processing started");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing file: " + e.getMessage());
        }
    }

    @PostMapping("/upload-manual")
    public ResponseEntity<String> uploadCsvManually() {
        try {

            System.out.println("Absolute File Path: ");
            File file = new File(
                    "C:\\Users\\pskat\\OneDrive\\Desktop\\scholarship\\src\\main\\java\\com\\example\\scholarship\\controller\\test.csv");
            System.out.println("File Exists: " + file.exists());

            List<Student> students = CsvUtils.read(Student.class, new FileInputStream(file));

            for (Student student : students) {
                System.out.println(student);
            }

            studentService.processCsvAsync(students);
            return ResponseEntity.ok("File processing started");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing file: " + e.getMessage());
        }
    }

    @GetMapping("/{rollNumber}")
    public ResponseEntity<Student> getStudentByRollNumber(@PathVariable String rollNumber) {
        Student student = studentService.getStudentByRollNumber(rollNumber);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Student(rollNumber, "N/A", 0, 0, 0, 0, "N/A"));
        }
        return ResponseEntity.ok(student);
    }
}
