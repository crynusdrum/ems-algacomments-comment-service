package com.algaworks.algacomments.comment.service.api.service;

import com.algaworks.algacomments.comment.service.api.model.CommentInput;
import com.algaworks.algacomments.comment.service.api.model.CommentOutput;
import com.algaworks.algacomments.comment.service.domain.model.CommentEntity;
import com.algaworks.algacomments.comment.service.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    public CommentOutput commentCreate(CommentInput commentInput){

        CommentEntity commentEntityToInsert = CommentEntity.builder()
                .id(UUID.randomUUID())
                .text(commentInput.getText())
                .author(commentInput.getAuthor())
                .createdAt(OffsetDateTime.now())
                .build();

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
