package com.example.vestibular.controllers;

import com.example.vestibular.models.user.AuthenticationDTO;
import com.example.vestibular.models.user.LoginResponde;
import com.example.vestibular.models.user.RegisterDTO;
import com.example.vestibular.models.user.User;
import com.example.vestibular.repositories.UserRepository;
import com.example.vestibular.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    @Operation(summary = "Login users", description = "Login users")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        if(this.userRepository.findByLogin(data.login()) == null ) return ResponseEntity.badRequest().body("Usuario não existe, ou credencias incorretas!");

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(),data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponde(token));
    }

    @PostMapping("/register")
    @Operation(summary = "Register users", description = "Register users")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.userRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
