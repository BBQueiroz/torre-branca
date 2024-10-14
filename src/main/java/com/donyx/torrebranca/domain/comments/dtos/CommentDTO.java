package com.donyx.torrebranca.domain.comments.dtos;

import com.donyx.torrebranca.domain.comments.Comment;
import com.donyx.torrebranca.domain.posts.dtos.PostDTO;
import com.donyx.torrebranca.domain.users.dto.UserDTO;

import java.time.LocalDateTime;

public record CommentDTO(
        long id,
        String titulo,
        String conteudo,
        UserDTO autor,
        LocalDateTime createDatetime
) {
    public CommentDTO(Comment comment){
        this(
                comment.getId(),
                comment.getTitulo(),
                comment.getConteudo(),
                new UserDTO(comment.getAutor()),
                comment.getCreateDatetime()
        );
    }
}
