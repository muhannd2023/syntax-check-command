package com.yarmouk.exams.helper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class StringHelper {

    private final static int STRENGHT = 10;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(STRENGHT, new SecureRandom());

    public String encode(String text) {
        return encoder.encode(text);
    }

    public boolean equals(String text, String hashed) {
        return encoder.matches(text, hashed);
    }

}
