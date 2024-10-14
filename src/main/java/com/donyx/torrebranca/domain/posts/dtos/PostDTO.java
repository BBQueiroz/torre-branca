package com.donyx.torrebranca.domain.posts.dtos;

import com.donyx.torrebranca.domain.comments.dtos.CommentDTO;
import com.donyx.torrebranca.domain.posts.Post;
import com.donyx.torrebranca.domain.posts.PostStatus;
import com.donyx.torrebranca.domain.users.dto.UserDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record PostDTO(
        Long id,
        String titulo,
        String descricao,
        String conteudo,
        UserDTO autor,
        PostStatus status,
        LocalDateTime dataHora,
        List<CommentDTO> commentDTOList
) {
    public PostDTO(Post post){
        this(
                post.getId(),
                post.getTitulo(),
                post.getDescricao(),
                post.getConteudo(),
                new UserDTO(post.getAutor()),
                post.getStatus(),
                post.getDataHora(),
                post.getCommentList().stream()
                   .map(CommentDTO::new)
                       .collect(Collectors.toList())
        );
    }
}
