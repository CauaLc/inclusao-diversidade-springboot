package br.com.fiap.inclusao_diversidade.controller;

import br.com.fiap.inclusao_diversidade.DTO.ColaboradorRequestDTO;
import br.com.fiap.inclusao_diversidade.DTO.ColaboradorResponseDTO;
import br.com.fiap.inclusao_diversidade.DTO.LoginDTO;
import br.com.fiap.inclusao_diversidade.DTO.TokenDTO;
import br.com.fiap.inclusao_diversidade.model.Colaborador;
import br.com.fiap.inclusao_diversidade.service.ColaboradorService;
import br.com.fiap.inclusao_diversidade.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ColaboradorService colaboradorService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(
            @RequestBody
            @Valid
            LoginDTO usuarioDto
    ) {
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(
                        usuarioDto.email(),
                        usuarioDto.senha());

        Authentication auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.gerarToken((Colaborador) auth.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity registrar(@RequestBody @Valid ColaboradorRequestDTO colaboradorRequestDTO){

        ColaboradorResponseDTO usuarioSalvo = null;
        usuarioSalvo = colaboradorService.salvarUsuario(colaboradorRequestDTO);

        return ResponseEntity.ok(usuarioSalvo);

    }

}
