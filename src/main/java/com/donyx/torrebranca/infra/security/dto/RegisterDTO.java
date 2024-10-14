package com.donyx.torrebranca.infra.security.dto;

import com.donyx.torrebranca.domain.users.UsuarioPerfil;

public record RegisterDTO(String login, String password, UsuarioPerfil perfil) {
}