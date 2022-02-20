package com.fintech.chatbot_spring.Domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class UserAndUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="from_user_id")
    User fromUser;

    @ManyToOne
    @JoinColumn(name="to_user_id")
    User toUser;

    @ManyToOne
    @JoinColumn(name="message_id")
    Message message;
}