package com.pettrip.domain.course;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId; // AUTO_INCREMENT ID
    private String courseName; // 코스 이름 또는 추가 정보

    @OneToMany(mappedBy = "course")
    private List<Coordinate> coordinates; // 이 코스에 속한 좌표들

}