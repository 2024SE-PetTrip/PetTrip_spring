package com.pettrip.domain.course;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Coordinate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int sequence;
    private double latitude;
    private double longitude;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course; // 이 위치가 속한 코스


}
