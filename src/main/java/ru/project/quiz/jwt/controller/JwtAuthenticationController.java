package ru.project.quiz.jwt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.quiz.jwt.dto.JwtRequestDTO;
import ru.project.quiz.jwt.dto.JwtResponseDTO;
import ru.project.quiz.jwt.util.JwtTokenUtil;
import ru.project.quiz.service.ituser.ITUserService;

@RestController
@Tag(name = "Контроллер аутентификации")
@RequestMapping("/auth")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private ITUserService itUserService;

    @Operation(summary = "Получение JWT токена")
    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponseDTO> createAuthenticationToken(@RequestBody JwtRequestDTO authenticationRequest) {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = itUserService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponseDTO(token));
    }

    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
