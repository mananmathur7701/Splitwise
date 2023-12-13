//package com.example.Splitwise_Backend.Security;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import com.example.Splitwise_Backend.Security.JWT.JwtAuthenticationEntryPoint;
//import com.example.Splitwise_Backend.Security.JWT.JwtAuthenticationFilter;
//import com.example.Splitwise_Backend.Security.JWT.JwtTokenHelper;
//import com.example.Splitwise_Backend.Security.UserDetails.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity(debug = true)
//public class KhataBahiSecurityConfig {
//
//
//    private final UserService userService;
//    private final JwtAuthenticationEntryPoint entryPoint;
//    private final JwtAuthenticationFilter filter;
//    private final JwtTokenHelper tokenHelper;
//
//    @Autowired
//    public KhataBahiSecurityConfig(UserService userService, JwtAuthenticationEntryPoint entryPoint, JwtAuthenticationFilter filter, JwtTokenHelper tokenHelper) {
//        this.userService = userService;
//        this.entryPoint = entryPoint;
//        this.filter = filter;
//        this.tokenHelper = tokenHelper;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//         httpSecurity
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/auth/**").permitAll()
//                        .anyRequest().authenticated()
//                ).oauth2Login(Customizer.withDefaults())
//                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .httpBasic(Customizer.withDefaults());
//        return httpSecurity.build();
//
//    }
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception{
//        return configuration.getAuthenticationManager();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
//        provider.setUserDetailsService(this.userService);
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }
//}
