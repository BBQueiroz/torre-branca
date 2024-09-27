package com.donyx.torrebranca.domain.users;

import com.donyx.torrebranca.domain.users.dto.DadosCreateUserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="TB_USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "email", "name"})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_USERS_SEQ")
    @SequenceGenerator(name = "TB_USERS_SEQ", sequenceName = "TB_USERS_SEQ", allocationSize = 1)
    @Column(name="ID_USER")
    private Long id;

    @Column(name="NOME", nullable = false)
    private String nome;

    @Column(name="EMAIL", nullable = false, unique = true, length = 100)
    private String email;

    @JsonIgnore
    @Column(name="SENHA", nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name="PERFIL", nullable = false)
    private UsuarioPerfil perfil;

    @Column(name="CREATE_DATETIME")
    private LocalDateTime createDateTime;

    @Column(name="UPDATE_DATETIME")
    private LocalDateTime updateDatetime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="UPDATE_USER_ID")
    private User updateUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.perfil.toString()));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public User(DadosCreateUserDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.perfil = dados.perfil();
        this.createDateTime = LocalDateTime.now();

    }

    public void mudarSenha(String encondedPassword, User user) {
        this.setSenha(encondedPassword);
        update(user);
    }

    public void update(User user){
        this.setUpdateUser(user);
        this.setUpdateDatetime(LocalDateTime.now());
    }
}