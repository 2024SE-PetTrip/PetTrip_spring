package com.pettrip.domain.care;

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
public class Evaluation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long evaluationId;

    @OneToOne
    @JoinColumn(name = "care_request_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CareRequest careRequest;

    @Column
    private Integer rating;

    @Column(length = 300)
    private String feedback;
}
