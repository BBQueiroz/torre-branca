package com.donyx.torrebranca.controllers;

import com.donyx.torrebranca.domain.users.User;
import com.donyx.torrebranca.domain.users.UserRepository;
import com.donyx.torrebranca.infra.security.TokenService;
import com.donyx.torrebranca.infra.security.dto.AuthenticationDTO;
import com.donyx.torrebranca.infra.security.dto.RegisterDTO;
import com.donyx.torrebranca.infra.security.dto.TokenDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private TokenService tokenService;
    private UserRepository userRepository;

    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository,TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(authenticationDTO.email(), authenticationDTO.password());
        var authentication = authenticationManager.authenticate(authenticationToken);
        var token = tokenService.generateToken((User) authentication.getPrincipal());
        var tokenDTO = new TokenDTO(token);
        return ResponseEntity.ok(tokenDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterDTO registerDto){
        if (userRepository.findByEmail(registerDto.login()) != null) return ResponseEntity.badRequest().body("Usuário já existe");

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());
        User newUser = new User(registerDto.login(), encryptedPassword, registerDto.perfil() );

        userRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body("usuário criado");
    }

}