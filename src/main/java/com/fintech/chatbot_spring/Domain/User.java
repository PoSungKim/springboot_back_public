package com.fintech.chatbot_spring.Domain;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class User {
    @NonNull
    private String name;
    @NonNull
    private String email;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

