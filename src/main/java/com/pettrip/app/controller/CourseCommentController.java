package com.pettrip.app.controller;

import com.pettrip.apiPayload.ApiResponse;
import com.pettrip.apiPayload.code.status.SuccessStatus;
import com.pettrip.app.dto.course.CourseCommentDTO.*;
import com.pettrip.service.course.CourseCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course/{courseId}")
public class CourseCommentController {

    @Autowired
    private CourseCommentService courseCommentService;

    // 댓글 추가
    @PostMapping("/addComment")
    public ApiResponse<Void> addComment(
            @PathVariable Long courseId,
            @RequestBody CommentAddRequestDTO requestDTO
    ) {
        courseCommentService.addComment(courseId, requestDTO);
        return ApiResponse.of(SuccessStatus.COMMENT_ADDED_OK, null);
    }

    // 댓글 수정
    @PutMapping("/updateComment/{commentId}")
    public ApiResponse<Void> updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentUpdateRequestDTO requestDTO
    ) {
        courseCommentService.updateComment(commentId, requestDTO);
        return ApiResponse.of(SuccessStatus.COMMENT_UPDATED_OK, null);
    }

    // 댓글 삭제
    @DeleteMapping("/deleteComment/{commentId}")
    public ApiResponse<Void> deleteComment(@PathVariable Long commentId) {
        courseCommentService.deleteComment(commentId);
        return ApiResponse.of(SuccessStatus.COMMENT_DELETED_OK, null);
    }
}