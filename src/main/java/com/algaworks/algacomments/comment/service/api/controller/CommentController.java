package com.algaworks.algacomments.comment.service.api.controller;

import com.algaworks.algacomments.comment.service.api.model.CommentInput;
import com.algaworks.algacomments.comment.service.api.model.CommentOutput;
import com.algaworks.algacomments.comment.service.api.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RequestMapping("/api/comments")
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CommentOutput commentCreate(@Valid @RequestBody CommentInput commentInput){

        return  commentService.commentCreate(commentInput);

    }

    @GetMapping()
    public Page<CommentOutput> retrieveComments(Pageable pageable){

        return commentService.retrieveComments(pageable);
    }

    @GetMapping("/{commentId}")
    public CommentOutput retrieveComment(@PathVariable UUID commentId) {

        return commentService.retrieveComment(commentId);
    }
}
