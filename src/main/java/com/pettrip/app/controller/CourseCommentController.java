package com.pettrip.app.controller;

import com.pettrip.apiPayload.ApiResponse;
import com.pettrip.apiPayload.code.status.SuccessStatus;
import com.pettrip.app.dto.course.CourseCommentDTO.*;
import com.pettrip.service.course.CourseCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course/{courseId}/comment")
public class CourseCommentController {

    @Autowired
    private CourseCommentService courseCommentService;

    // 댓글 추가
    @PostMapping
    public ApiResponse<CommentAddResponseDTO> addComment(
            @PathVariable Long courseId,
            @RequestBody CommentAddRequestDTO requestDTO
    ) {
        CommentAddResponseDTO responseDTO = courseCommentService.addComment(courseId, requestDTO);
        return ApiResponse.of(SuccessStatus.COMMENT_ADDED_OK, responseDTO);
    }

    // 댓글 수정
    @PutMapping
    public ApiResponse<CommentUpdateResponseDTO> updateComment(
            @RequestBody CommentUpdateRequestDTO requestDTO
    ) {
        CommentUpdateResponseDTO responseDTO = courseCommentService.updateComment(requestDTO);
        return ApiResponse.of(SuccessStatus.COMMENT_UPDATED_OK, responseDTO);
    }

    // 댓글 삭제
    @DeleteMapping
    public ApiResponse<Void> deleteComment(@RequestBody CommentDeleteRequestDTO requestDTO) {
        courseCommentService.deleteComment(requestDTO);
        return ApiResponse.of(SuccessStatus.COMMENT_DELETED_OK, null);
    }
}