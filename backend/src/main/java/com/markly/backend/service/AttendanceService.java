package com.markly.backend.service;

import com.markly.backend.model.Attendance;
import com.markly.backend.model.AttendanceStatus;
import com.markly.backend.repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public List<Attendance> getAttendanceForStudent(String studentEmail) {
        return attendanceRepository.findByStudentEmail(studentEmail);
    }


    // 🔹 Mark attendance
    public Attendance markAttendance(
            String studentEmail,
            String subject,
            AttendanceStatus status
    ) {

        LocalDate today = LocalDate.now();

        // 1️⃣ Check if attendance already marked today
        attendanceRepository
                .findByStudentEmailAndSubjectAndDate(studentEmail, subject, today)
                .ifPresent(a -> {
                    throw new RuntimeException("Attendance already marked for today");
                });

        // 2️⃣ Create attendance object
        Attendance attendance = new Attendance(
                studentEmail,
                subject,
                today,
                status,
                LocalDateTime.now()
        );

        // 3️⃣ Save to MongoDB
        return attendanceRepository.save(attendance);
    }
}
