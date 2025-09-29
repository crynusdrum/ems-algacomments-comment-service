package com.algaworks.algacomments.comment.service.api.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ModerationOutput {

    private Boolean approved;
    private String reason;
}
