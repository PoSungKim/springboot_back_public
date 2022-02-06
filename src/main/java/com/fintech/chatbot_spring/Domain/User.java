package com.fintech.chatbot_spring.Domain;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
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

    @Column
    @OneToMany(fetch = FetchType.EAGER)
    private List<Message> messages;

    @OneToOne(mappedBy = "user")
    @ToString.Exclude
    private UserMeta userMeta;
}