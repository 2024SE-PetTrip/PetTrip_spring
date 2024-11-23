package com.pettrip.domain.course;

import com.pettrip.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class CourseComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course; // 댓글이 속한 코스

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 댓글 작성자

    @Column(nullable = false, length = 500)
    private String commentContent; // 댓글 내용

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate; // 댓글 생성일

    @Column(nullable = false)
    private LocalDateTime updatedDate; // 댓글 수정일
}