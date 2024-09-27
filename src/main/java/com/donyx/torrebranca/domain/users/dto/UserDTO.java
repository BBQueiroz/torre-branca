package com.donyx.torrebranca.domain.users.dto;

import com.donyx.torrebranca.domain.users.User;
import com.donyx.torrebranca.domain.users.UsuarioPerfil;

public record UserDTO(
        Long id,

        String nome,

        String email,

        UsuarioPerfil perfil
) {
    public UserDTO(User user) {
        this(user.getId(), user.getNome(), user.getEmail(), user.getPerfil());
    }
}
