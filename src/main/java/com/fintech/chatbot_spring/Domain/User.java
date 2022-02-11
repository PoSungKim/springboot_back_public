package com.fintech.chatbot_spring.Domain;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class User extends BaseDomain{

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @NotNull
    @Column(nullable = false)
    private String email;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private List<Message> messages = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    @ToString.Exclude
    private UserMeta userMeta;
}