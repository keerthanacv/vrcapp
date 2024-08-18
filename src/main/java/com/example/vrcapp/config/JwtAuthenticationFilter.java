package com.example.vrcapp.config;

import com.example.vrcapp.controller.UserController;
import com.example.vrcapp.model.Users;
import com.example.vrcapp.repository.UsersRepository;
import com.example.vrcapp.service.JwtService;
import com.example.vrcapp.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtService jwtService;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull  HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        LOGGER.info("Inside the JwtAuthenticationFilter");

        final String authHeader = request.getHeader("Authorization");

        final String jwt;

        final String userEmail;

        if(authHeader == null || !authHeader.startsWith("Bearer "))
        {
            filterChain.doFilter(request,response); // to continue to next filter
            return;
        }

        jwt = authHeader.substring(7);

        userEmail = jwtService.extractUserName(jwt); // extract userEmail from token

        if (userEmail !=null && SecurityContextHolder.getContext().getAuthentication() == null)
        {
            Users users = null; // get user from DB
            try {
                users = (Users) userService.loadUserByUsername(userEmail);
            } catch (Exception e) {
                throw new AuthenticationException("No valid details were provided");
            }

            if(jwtService.isTokenValid(jwt,users)) // validate token
            {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                users,
                                null,users.getAuthorities()
                        );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
            filterChain.doFilter(request,response);
        }
    }
}
