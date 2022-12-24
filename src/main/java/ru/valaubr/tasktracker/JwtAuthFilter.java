package ru.valaubr.tasktracker;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.valaubr.tasktracker.configs.AuthConstant;
import ru.valaubr.tasktracker.entities.ApiUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@RequiredArgsConstructor
public class JwtAuthFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ApiUser apiUser = new ObjectMapper().readValue(request.getInputStream(), ApiUser.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            apiUser.getEmail(),
                            apiUser.getPassword(),
                            new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = JWT.create()
                .withSubject(((ApiUser) (authResult.getPrincipal())).getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + AuthConstant.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(AuthConstant.SECRET.getBytes()));
        response.addHeader(AuthConstant.HEADER_STRING, AuthConstant.TOKEN_PREFIX + token);
    }
}
