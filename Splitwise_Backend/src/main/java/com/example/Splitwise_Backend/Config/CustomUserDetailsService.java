package com.example.Splitwise_Backend.Config;

import com.example.Splitwise_Backend.Users.Entity.Users;
import com.example.Splitwise_Backend.Users.Repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepo userRepo;

    @Autowired
    public CustomUserDetailsService(UsersRepo userRepo) {
        this.userRepo = userRepo;
    }

    public String password;
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        System.out.println("password for load user  " +this.password);
        Users user = this.userRepo.findByEmail(username);
        if(user==null){
            throw new UsernameNotFoundException("user not found");
        }
        return user;
    }
}
