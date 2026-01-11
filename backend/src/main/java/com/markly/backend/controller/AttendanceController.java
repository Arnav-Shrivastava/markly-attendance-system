package com.markly.backend.controller;

import com.markly.backend.model.Attendance;
import com.markly.backend.model.AttendanceStatus;
import com.markly.backend.security.JwtService;
import com.markly.backend.service.AttendanceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final JwtService jwtService;

    public AttendanceController(
            AttendanceService attendanceService,
            JwtService jwtService
    ) {
        this.attendanceService = attendanceService;
        this.jwtService = jwtService;
    }

    // 🔹 Student marks attendance
    @PostMapping("/mark")
    @PreAuthorize("hasRole('STUDENT')")
    public Attendance markAttendance(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam String subject,
            @RequestParam AttendanceStatus status
    ) {

        // 1️⃣ Extract token
        String token = authHeader.substring(7); // remove "Bearer "

        // 2️⃣ Extract email from JWT
        String studentEmail = jwtService.extractEmail(token);

        // 3️⃣ Call service
        return attendanceService.markAttendance(
                studentEmail,
                subject,
                status
        );
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('STUDENT')")
    public List<Attendance> getMyAttendance(
            @RequestHeader("Authorization") String authHeader
    ) {

        String token = authHeader.substring(7);
        String studentEmail = jwtService.extractEmail(token);

        return attendanceService.getAttendanceForStudent(studentEmail);
    }

    @GetMapping("/percentage/subject")
    public Map<String, Object> subjectAttendance(
            @RequestParam String subject,
            @AuthenticationPrincipal UserDetails userDetails) {

        return attendanceService
                .getSubjectAttendancePercentage(userDetails.getUsername(), subject);
    }

}
