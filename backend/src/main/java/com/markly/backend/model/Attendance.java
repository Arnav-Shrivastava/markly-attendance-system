package com.markly.backend.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "attendance")
public class Attendance {

    // 🔹 Getters & Setters
    @Getter
    @Id
    private String id;

    @Getter
    @Setter
    private String studentEmail;

    @Getter
    @Setter
    private String subject;

    @Setter
    @Getter
    private LocalDate date;

    @Getter
    @Setter
    private AttendanceStatus status;

    @Getter
    @Setter
    private LocalDateTime markedAt;

    // 🔹 Constructors
    public Attendance() {}

    public Attendance(String studentEmail, String subject, LocalDate date,
                      AttendanceStatus status, LocalDateTime markedAt) {
        this.studentEmail = studentEmail;
        this.subject = subject;
        this.date = date;
        this.status = status;
        this.markedAt = markedAt;
    }

}
