package com.markly.backend.repository;

import com.markly.backend.model.Attendance;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends MongoRepository<Attendance, String> {

    // 🔹 Check if attendance already marked for a student, subject, and date
    Optional<Attendance> findByStudentEmailAndSubjectAndDate(
            String studentEmail,
            String subject,
            LocalDate date
    );

    // 🔹 Get all attendance records of a student
    List<Attendance> findByStudentEmail(String studentEmail);
}
