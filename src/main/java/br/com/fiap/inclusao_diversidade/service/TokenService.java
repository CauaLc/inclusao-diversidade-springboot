package br.com.fiap.inclusao_diversidade.service;


import br.com.fiap.inclusao_diversidade.model.Colaborador;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("minha.chave.secreta")
    private String palavraSecreta;

    public String gerarToken(Colaborador colaborador){

        try {
            Algorithm algorithm = Algorithm.HMAC256(palavraSecreta);

            String token = JWT.create()
                    .withIssuer("inclusao_diversidade")
                    .withSubject(colaborador.getEmail())
                    .withExpiresAt(gerarDataDeExpiracao())
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException e){
            throw new RuntimeException("Não foi possível gerar o token!");
        }

    }

    public String validarToken(String token){

        try {
            Algorithm algorithm = Algorithm.HMAC256(palavraSecreta);

            return JWT.require(algorithm)
                    .withIssuer("inclusao_diversidade")
                    .build()
                    .verify(token)
                    .getSubject();

        }catch (JWTVerificationException e){
            return "";
        }
    }

    private Instant gerarDataDeExpiracao(){
        return LocalDateTime
                .now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }


}