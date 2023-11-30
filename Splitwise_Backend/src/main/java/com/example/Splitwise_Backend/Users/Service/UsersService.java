package com.example.Splitwise_Backend.Users.Service;

import com.example.Splitwise_Backend.Users.DTO.UsersDTO;
import com.example.Splitwise_Backend.Users.DTO.UsersViewDTO;
import com.example.Splitwise_Backend.Users.Entity.Users;

import java.util.Optional;

public interface UsersService {
    public Users createUser(UsersDTO usersDTO);
    public UsersViewDTO loginUser(String email,String password);
    public String deleteUser(Integer id,String password);
    public UsersViewDTO editDetails(UsersViewDTO usersViewDTO);
    public UsersDTO editPassword(Integer id, String oldPassword, String newPassword);
    public UsersViewDTO findByEmail(String email);
    public UsersViewDTO findById(int id);
    public Users findForUpadte(int id);
}
