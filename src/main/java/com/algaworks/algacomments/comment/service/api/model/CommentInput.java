package com.algaworks.algacomments.comment.service.api.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommentInput {

    private String text;
    private String author;
}
