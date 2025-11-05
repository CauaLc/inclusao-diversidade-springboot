package br.com.fiap.inclusao_diversidade.config.security;

import br.com.fiap.inclusao_diversidade.repository.ColaboradorRepository;
import br.com.fiap.inclusao_diversidade.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class VerificarToken extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.replace("Bearer", "").trim();

            String login = tokenService.validarToken(token);

            if (login != null) {
                var usuario = colaboradorRepository.findByEmail(login);

                if (usuario != null) {
                    var authentication = new UsernamePasswordAuthenticationToken(
                            usuario,
                            null,
                            usuario.getAuthorities()
                    );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    System.out.println("⚠️ Usuário não encontrado para o e-mail extraído do token: " + login);
                }
            } else {
                System.out.println("⚠️ Token inválido ou sem subject (login nulo)");
            }
        }

        filterChain.doFilter(request, response);
    }
}