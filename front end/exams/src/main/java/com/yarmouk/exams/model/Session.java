package com.yarmouk.exams.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table
@NoArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Timestamp startTime;
    private Timestamp endTime;
    private String sessionKey;

    public Session(Long id) {
        this.userId = id;
        this.startTime = Timestamp.valueOf(LocalDateTime.now());
        this.endTime = Timestamp.valueOf(LocalDateTime.now().plusMinutes(60));
        this.sessionKey = UUID.randomUUID().toString();
    }
}
