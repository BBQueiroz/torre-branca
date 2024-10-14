package com.donyx.torrebranca.domain.posts.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewPostDTO(
        @NotNull String titulo,
        @NotNull String descricao,
        @NotNull String conteudo
) {
}
