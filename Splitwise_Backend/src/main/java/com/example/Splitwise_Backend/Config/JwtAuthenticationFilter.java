package com.example.Splitwise_Backend.Config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenHelperNew  jwtTokenHelperNew;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // TODO Auto-generated method stub

        String requestToken = request.getHeader("Authorization");
        System.out.println("token generated is " +requestToken);

        String token=null;
        String username= null;

        if(requestToken != null && requestToken.startsWith("Bearer")) {
            token = requestToken.substring(7);

            try {
                username =  this.jwtTokenHelperNew.getUsernameFromToken(token);
            }catch(IllegalArgumentException ex)
            {
                System.out.println("unable to get jwt token" +ex.getMessage());
            }catch(ExpiredJwtException ex)
            {
                System.out.println("token is expired...");
            }catch(MalformedJwtException ex)
            {
                System.out.println("invalid jwt");
            }

        }
        else {
            System.out.println("Jwt token does not start with bearer");
        }


        //After we Get the token
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
        {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if(this.jwtTokenHelperNew.validateToken(token, userDetails))
            {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =  new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            else
            {
                System.out.println("invalid jwt token");
            }
        }
        else
        {
            System.out.println("username is null or context is not null");
        }

        filterChain.doFilter(request, response);
    }

}
