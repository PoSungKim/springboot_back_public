package com.fintech.chatbot_spring.Domain;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    @NonNull
    private String name;

    @Column
    @NonNull
    private String email;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;
}

