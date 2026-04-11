package com.prajval.CityDealsApp.filters;

import com.prajval.CityDealsApp.enities.User;
import com.prajval.CityDealsApp.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    @Qualifier("handlerExceptionResolver")
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final JwtService jwtService;
    private final UserDetailsService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        try {
            final String requestTokenHeader = request.getHeader("Authorization");
            if (requestTokenHeader==null || !requestTokenHeader.startsWith("Bearer ")){
                filterChain.doFilter(request, response);
                return;
            }

            String token = requestTokenHeader.substring(7);
            String userEmail = jwtService.getEmailFromToken(token);

            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null &&
                    !jwtService.isTokenExpired(token))
            {
                User user = (User) userService.loadUserByUsername(userEmail);

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            filterChain.doFilter(request, response);
        }
        catch (Exception ex){
            handlerExceptionResolver.resolveException(request, response, null, ex);
        }
    }
}
