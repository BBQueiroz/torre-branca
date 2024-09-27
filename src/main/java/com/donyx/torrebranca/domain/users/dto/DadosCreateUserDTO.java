package com.donyx.torrebranca.domain.users.dto;

import com.donyx.torrebranca.domain.users.UsuarioPerfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosCreateUserDTO(
        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotNull
        BigDecimal salario,

        @NotNull
        UsuarioPerfil perfil

) {

}
