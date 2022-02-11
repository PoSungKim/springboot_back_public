package com.fintech.chatbot_spring.Domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class UserMeta extends BaseDomain{

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
