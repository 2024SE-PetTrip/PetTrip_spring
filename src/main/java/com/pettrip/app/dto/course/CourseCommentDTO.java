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
    public static class CommentAddRequestDTO {
        private Long userId;
        private Long courseId;
        private String commentContent;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class CommentAddResponseDTO {
        private Long userId;
        private Long courseId;
        private String commentContent;
        private LocalDateTime createdDate;
        private LocalDateTime updatedDate;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CommentUpdateRequestDTO {
        private Long userId;
        private Long courseId;
        private String commentContent;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class CommentUpdateResponseDTO {
        private Long userId;
        private Long courseId;
        private String commentContent;
        private LocalDateTime createdDate;
        private LocalDateTime updatedDate;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CommentDeleteRequestDTO {
        private Long userId;
        private Long courseId;
        private String commentContent;
    }
}
