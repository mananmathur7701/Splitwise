package com.example.Splitwise_Backend.Users.Service;

import com.example.Splitwise_Backend.Users.DTO.UsersDTO;
import com.example.Splitwise_Backend.Users.DTO.UsersViewDTO;
import com.example.Splitwise_Backend.Users.Entity.Users;

import java.util.Optional;

public interface UsersService {
    public Users createUser(UsersDTO usersDTO);
    public UsersViewDTO loginUser(String email,String password);
    public String deleteUser(Integer id,String password);
    public Users editDetails(UsersViewDTO usersViewDTO);
    public Users editPassword(UsersDTO usersDTO);
    public UsersViewDTO findByEmail(String email);
    public UsersViewDTO findById(int id);
    public Users findForUpadte(int id);
}
