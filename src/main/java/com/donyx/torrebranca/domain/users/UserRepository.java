package com.donyx.torrebranca.domain.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

    boolean existsByEmail(@NotBlank @Email String email);

}