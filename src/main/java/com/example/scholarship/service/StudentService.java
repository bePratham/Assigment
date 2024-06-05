package com.example.scholarship.service;

import com.example.scholarship.model.EligibilityCriteria;
import com.example.scholarship.model.Student;
import com.example.scholarship.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final EligibilityCriteria eligibilityCriteria;

    public void processCsv(List<Student> students) {
        for (Student student : students) {
            student.setEligible(checkEligibility(student) ? "YES" : "NO");
            studentRepository.save(student);
        }
    }

    private boolean checkEligibility(Student student) {
        return student.getScience() > eligibilityCriteria.getScience()
                && student.getMaths() > eligibilityCriteria.getMaths()
                && student.getEnglish() > eligibilityCriteria.getEnglish()
                && student.getComputer() > eligibilityCriteria.getComputer();
    }

    @Async
    public CompletableFuture<Void> processCsvAsync(List<Student> students) {
        processCsv(students);
        return CompletableFuture.completedFuture(null);
    }

    public Student getStudentByRollNumber(String rollNumber) {
        return studentRepository.findById(rollNumber).orElse(null);
    }
}
