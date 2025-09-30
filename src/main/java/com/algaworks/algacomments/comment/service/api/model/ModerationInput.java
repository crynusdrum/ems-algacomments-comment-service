package com.algaworks.algacomments.comment.service.api.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class ModerationInput {

    private UUID commentId;
    private String text;
}
