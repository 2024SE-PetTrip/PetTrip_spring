package com.pettrip.app.dto.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

public class CourseCommentDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentAddRequestDTO {
        private Long userId;
        private String commentContent;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentUpdateRequestDTO {
        private String commentContent;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentResponseDTO {
        private Long commentId;
        private String commentContent;
        private String userName;
        private LocalDateTime createdDate;
        private LocalDateTime updatedDate;
    }
}
