package com.yarmouk.backend.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "SESSION")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
