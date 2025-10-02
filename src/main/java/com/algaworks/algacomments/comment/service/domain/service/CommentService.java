package com.algaworks.algacomments.comment.service.domain.service;

import com.algaworks.algacomments.comment.service.api.client.ModerationClient;
import com.algaworks.algacomments.comment.service.api.model.CommentInput;
import com.algaworks.algacomments.comment.service.api.model.CommentOutput;
import com.algaworks.algacomments.comment.service.api.model.ModerationInput;
import com.algaworks.algacomments.comment.service.api.model.ModerationOutput;
import com.algaworks.algacomments.comment.service.domain.exception.ModerationRejectedException;
import com.algaworks.algacomments.comment.service.domain.model.CommentEntity;
import com.algaworks.algacomments.comment.service.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.UUID;

@Log
@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ModerationClient moderationClient;

    public CommentOutput commentCreate(CommentInput commentInput){

        CommentEntity commentEntityToInsert = CommentEntity.builder()
                .id(UUID.randomUUID())
                .text(commentInput.getText())
                .author(commentInput.getAuthor())
                .createdAt(OffsetDateTime.now())
                .build();

        ModerationInput moderationInput = ModerationInput.builder()
                .commentId(commentEntityToInsert.getId())
                .text(commentEntityToInsert.getText())
                .build();

        ModerationOutput moderationOutput = moderationClient.moderate(moderationInput);

        if(Boolean.FALSE.equals(moderationOutput.getApproved())){
            throw new ModerationRejectedException(moderationOutput.getReason());
        }

        CommentEntity commentEntity = commentRepository.saveAndFlush(commentEntityToInsert);

        return convertToModel(commentEntity);

    }


    public Page<CommentOutput> retrieveComments(Pageable pageable){

        Page<CommentEntity> commentEntityList = commentRepository.findAll(pageable);
        return commentEntityList.map(this::convertToModel);
    }

    public CommentOutput retrieveComment(UUID commentId){

        CommentEntity commentEntity = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToModel(commentEntity);
    }

    private CommentOutput convertToModel(CommentEntity commentEntity) {

        return CommentOutput.builder()
                .id(commentEntity.getId().toString())
                .text(commentEntity.getText())
                .author(commentEntity.getAuthor())
                .createdAt(commentEntity.getCreatedAt())
                .build();
    }

}
