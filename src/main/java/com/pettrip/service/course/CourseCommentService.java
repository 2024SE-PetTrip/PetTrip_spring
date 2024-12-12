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

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseCommentService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseCommentRepository courseCommentRepository;

    // 댓글 추가
    @Transactional
    public void addComment(Long courseId, CommentAddRequestDTO requestDTO) {
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

        courseCommentRepository.save(comment);
    }

    // 댓글 수정
    @Transactional
    public void updateComment(Long commentId, CommentUpdateRequestDTO requestDTO) {
        CourseComment comment = courseCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid comment ID"));

        comment.setCommentContent(requestDTO.getCommentContent());
        comment.setUpdatedDate(LocalDateTime.now());

        courseCommentRepository.save(comment);
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(Long commentId) {
        CourseComment comment = courseCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid comment ID"));

        courseCommentRepository.delete(comment);
    }

    //코스에 달린 댓글 가져오기
    @Transactional
    public List<CommentResponseDTO> getCommentsByCourseId(Long courseId) {
        List<CourseComment> comments = courseCommentRepository.findByCourse_CourseId(courseId);

        return comments.stream()
                .map(comment -> new CommentResponseDTO(
                        comment.getCommentId(),
                        comment.getCommentContent(),
                        comment.getUser().getNickname(),
                        comment.getCreatedDate(),
                        comment.getUpdatedDate()
                ))
                .collect(Collectors.toList());
    }
}
