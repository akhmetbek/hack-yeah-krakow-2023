package com.demo.hackyeah.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.ZonedDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "message")
public class Message {
    @GeneratedValue
    @Id
    private Long id;

    @Column
    private String sessionId;

    @Column
    private String role;

    @Column
    private String content;

    @CreatedDate
    private ZonedDateTime createdDate;
}
