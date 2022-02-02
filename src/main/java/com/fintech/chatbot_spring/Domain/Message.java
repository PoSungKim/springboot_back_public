package com.fintech.chatbot_spring.Domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public Message(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
