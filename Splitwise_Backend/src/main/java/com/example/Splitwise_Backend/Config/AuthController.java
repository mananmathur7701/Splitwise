package com.example.Splitwise_Backend.Config;

import com.example.Splitwise_Backend.Exceptions.UserNotFoundException;
import com.example.Splitwise_Backend.Users.DTO.UsersViewDTO;
import com.example.Splitwise_Backend.Users.Entity.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.security.Principal;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private JwtTokenHelperNew helper;
    @Autowired
    private CustomUserDetailsService service;
    @Autowired
    private AuthenticationManager manager;
    public String password;

    @GetMapping("/get-current-user")
//    @CrossOrigin("http://localhost:4200")
    public UsersViewDTO user(@NotNull Principal principal){
        System.out.println(principal);
        Users userToBeFind = (Users) this.service.loadUserByUsername(principal.getName());
        UsersViewDTO loggedInUser = new UsersViewDTO();
        loggedInUser.setId(userToBeFind.getId());
        loggedInUser.setEmail(userToBeFind.getEmail());
        loggedInUser.setFirstName(userToBeFind.getFirstName());
        loggedInUser.setLastName(userToBeFind.getLastName());
        loggedInUser.setNumber(userToBeFind.getNumber());
        return loggedInUser;
    }

    @PostMapping("/login")
//    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<JwtAuthResponse> createToken(@NotNull @RequestBody JwtAuthRequest request)
    {
//        this.password = request.getPassword();
        UserDetails userDetails = this.service.loadUserByUsername(request.getUsername());
        this.authenticate(request.getUsername(), request.getPassword());
        String generateToken = this.helper.generateToken(userDetails);
        System.out.println("*******************************" +generateToken);
        JwtAuthResponse authResponse = new JwtAuthResponse();
        authResponse.setAccessToken(generateToken);
        authResponse.setMessage("User logged in");

        return new ResponseEntity<JwtAuthResponse>(authResponse, HttpStatus.OK);

    }

    private void authenticate(String username, String password) {
        // TODO Auto-generated method stub
        UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(username, password);
        try {
            this.manager.authenticate(authtoken);
        }catch(Exception ex)
        {
            System.out.println("invalid details..!");
            throw new UserNotFoundException("Invalid Crediantials");
        }
    }
}
