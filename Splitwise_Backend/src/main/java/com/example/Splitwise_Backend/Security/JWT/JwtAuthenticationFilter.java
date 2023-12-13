//package com.example.Splitwise_Backend.Security.JWT;
//
//import com.example.Splitwise_Backend.Exceptions.GeneralException;
//import com.example.Splitwise_Backend.Security.UserDetails.UserService;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.MalformedJwtException;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Service;
//import org.springframework.web.filter.OncePerRequestFilter;
//import java.io.IOException;
//
//@Service
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private final JwtTokenHelper jwtTokenHelper;
//    private final UserService userService;
//
//    @Autowired
//    public JwtAuthenticationFilter(JwtTokenHelper jwtTokenHelper, UserService userService) {
//        this.jwtTokenHelper = jwtTokenHelper;
//        this.userService = userService;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
//    {
//        String requestToken = request.getHeader("Authorization");
//        System.out.println("token generated is " +requestToken);
//
//        String token=null;
//        String username= null;
//
//        if(requestToken != null && requestToken.startsWith("Bearer"))
//        {
//            token = requestToken.substring(7);
//
//            try
//            {
//                username =  this.jwtTokenHelper.getUsernameFromToken(token);
//            }
//            catch(IllegalArgumentException ex)
//            {
//                System.out.println("unable to get jwt token" +ex.getMessage());
//                throw new GeneralException("unable to get jwt token" +ex.getMessage());
//            }
//            catch(ExpiredJwtException ex)
//            {
//                System.out.println("token is expired...");
//                throw new GeneralException("token is expired...");
//            }
//            catch(MalformedJwtException ex)
//            {
//                System.out.println("invalid jwt");
//                throw new GeneralException("invalid jwt");
//            }
//
//        }
//        else
//        {
//            System.out.println("Jwt token does not start with bearer");
//            throw new GeneralException("Jwt token does not start with bearer");
//        }
//
//        //After we Get the token
//        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
//        {
//            UserDetails userDetails = this.userService.loadUserByUsername(username);
//            if(this.jwtTokenHelper.validateToken(token, userDetails))
//            {
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =  new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
//                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//            else
//            {
//                System.out.println("invalid jwt token");
//                throw new GeneralException("invalid jwt token");
//            }
//        }
//        else
//        {
//            System.out.println("username is null or context is not null");
//            throw new GeneralException("username is null or context is not null");
//        }
//        filterChain.doFilter(request, response);
//    }
//}
