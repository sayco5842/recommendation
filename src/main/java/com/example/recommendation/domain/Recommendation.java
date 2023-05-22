package com.example.recommendation.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recommendation_history")
public class Recommendation {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    @Column(name = "request_count")
    private Long requestCount;
    @Column(name = "created_date_time")
    private LocalDateTime createdDateTime;
    @Column(name = "updated_date_time")
    private LocalDateTime updatedDateTime;

    public static Recommendation of(String name,
                                    String email,
                                    Long requestCount,
                                    LocalDateTime createdDateTime,
                                    LocalDateTime updatedDateTime) {
        return Recommendation.builder()
                .name(name)
                .email(email)
                .requestCount(requestCount)
                .createdDateTime(createdDateTime)
                .updatedDateTime(updatedDateTime)
                .build();
    }

    public void incrementCount() {
        this.requestCount += 1;
    }
}


