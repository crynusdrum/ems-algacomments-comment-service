package com.algaworks.algacomments.comment.service.api.model;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Builder
@Data
public class CommentOutput {

    private String id;
    private String text;
    private String author;
    private OffsetDateTime createdAt;

}
