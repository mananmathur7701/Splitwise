//package com.example.Splitwise_Backend.Security.UserDetails;
//
//import com.example.Splitwise_Backend.Exceptions.UserNotFoundException;
//import com.example.Splitwise_Backend.Users.Entity.Users;
//import com.example.Splitwise_Backend.Users.Repository.UsersRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//public class UserService implements UserDetailsService {
//
//    private UsersRepo usersRepo;
//
//    @Autowired
//    public UserService(UsersRepo usersRepo) {
//        this.usersRepo = usersRepo;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Users users = usersRepo.findByEmail(username);
//        if (users==null)
//        {
//            throw new UserNotFoundException("User Not Found");
//        }
//        else
//        {
//            return users;
//        }
//    }
//}
