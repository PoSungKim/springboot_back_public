package com.fintech.chatbot_spring.Domain;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Message extends BaseDomain{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column
    private String content;
}



