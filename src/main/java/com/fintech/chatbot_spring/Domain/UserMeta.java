package com.fintech.chatbot_spring.Domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class UserMeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column
    private Integer accessCount;

    @NonNull
    @Column
    private LocalDateTime lastAccessDate;

    @OneToOne(optional = false)
    @ToString.Exclude
    private User user;
}
