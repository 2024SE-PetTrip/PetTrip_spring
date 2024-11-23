package com.pettrip.app.dto.course;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseSearchDTO {
    private String title; // 코스 제목 검색
    private String location; // 위치 검색
    private Integer maxDuration; // 최대 이동 시간 (분 단위)
    private List<String> tags; // 태그 리스트
}

