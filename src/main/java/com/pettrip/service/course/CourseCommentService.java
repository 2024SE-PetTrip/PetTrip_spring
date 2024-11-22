package com.pettrip.service.course;

import com.pettrip.app.dto.course.CourseCommentDTO.*;
import com.pettrip.domain.User;
import com.pettrip.domain.course.Course;
import com.pettrip.domain.course.CourseComment;
import com.pettrip.repository.CourseCommentRepository;
import com.pettrip.repository.CourseRepository;
import com.pettrip.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CourseCommentService {

    @Autowired
    private CourseCommentRepository courseCommentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    // 댓글 추가
    public CommentAddResponseDTO addComment(Long courseId, CommentAddRequestDTO requestDTO) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course ID"));
        User user = userRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        CourseComment comment = new CourseComment();
        comment.setCourse(course);
        comment.setUser(user);
        comment.setCommentContent(requestDTO.getCommentContent());
        comment.setCreatedDate(LocalDateTime.now());
        comment.setUpdatedDate(LocalDateTime.now());

        CourseComment savedComment = courseCommentRepository.save(comment);

        return new CommentAddResponseDTO(
                savedComment.getUser().getId(), // User 클래스의 필드에 맞게 수정
                savedComment.getCourse().getCourseId(),
                savedComment.getCommentContent(),
                savedComment.getCreatedDate(),
                savedComment.getUpdatedDate()
        );
    }

    // 댓글 수정
    public CommentUpdateResponseDTO updateComment(CommentUpdateRequestDTO requestDTO) {
        CourseComment comment = courseCommentRepository.findById(requestDTO.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid comment ID"));

        comment.setCommentContent(requestDTO.getCommentContent());
        comment.setUpdatedDate(LocalDateTime.now());

        CourseComment updatedComment = courseCommentRepository.save(comment);

        return new CommentUpdateResponseDTO(
                updatedComment.getUser().getId(),
                updatedComment.getCourse().getCourseId(),
                updatedComment.getCommentContent(),
                updatedComment.getCreatedDate(),
                updatedComment.getUpdatedDate()
        );
    }

    // 댓글 삭제
    public void deleteComment(CommentDeleteRequestDTO requestDTO) {
        CourseComment comment = courseCommentRepository.findById(requestDTO.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid comment ID"));
        courseCommentRepository.delete(comment);
    }
}