package com.algaworks.algacomments.comment.service.api.client;


import com.algaworks.algacomments.comment.service.api.model.ModerationtInput;
import com.algaworks.algacomments.comment.service.api.model.ModerationOutput;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.*;

@HttpExchange("/api")
public interface ModerationClient {

    @PostExchange("/moderate")
    ModerationOutput moderate(@RequestBody ModerationtInput body);
}
