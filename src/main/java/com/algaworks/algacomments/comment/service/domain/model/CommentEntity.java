package com.algaworks.algacomments.comment.service.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

//@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class CommentEntity {

    @Id
    private UUID id;
    private String text;
    private String author;
    private OffsetDateTime createdAt;
    //private OffsetDateTime createdAt = OffsetDateTime.now();
}
