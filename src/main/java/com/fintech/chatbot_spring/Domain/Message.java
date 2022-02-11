package com.fintech.chatbot_spring.Domain;

import lombok.*;

import javax.persistence.*;


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

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;
}


