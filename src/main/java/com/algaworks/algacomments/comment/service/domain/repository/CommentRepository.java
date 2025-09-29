package com.algaworks.algacomments.comment.service.domain.repository;

import com.algaworks.algacomments.comment.service.domain.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommentRepository  extends JpaRepository<CommentEntity, UUID> {
}
