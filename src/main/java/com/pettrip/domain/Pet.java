package com.pettrip.domain;

import com.pettrip.domain.User;
import com.pettrip.domain.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pet extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long petId;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", updatable = false, nullable = false)
    private User user;

    @Column(length = 10)
    private String petName;

    @Column
    private Integer petAge;

    @Column(length = 30)
    private String breed;

    @Column(length = 255)
    private String petImageUrl;

    @Column(nullable = false)
    private boolean isValidated = false; // 인증 여부 (default: false)
}