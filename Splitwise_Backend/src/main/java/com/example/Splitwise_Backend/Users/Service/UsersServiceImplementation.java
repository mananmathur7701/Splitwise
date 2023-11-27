package com.example.Splitwise_Backend.Users.Service;
import com.example.Splitwise_Backend.Exceptions.GeneralException;
import com.example.Splitwise_Backend.Exceptions.UserNotFoundException;
import com.example.Splitwise_Backend.Users.DTO.UsersDTO;
import com.example.Splitwise_Backend.Users.DTO.UsersViewDTO;
import com.example.Splitwise_Backend.Users.Entity.Users;
import com.example.Splitwise_Backend.Users.Repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsersServiceImplementation implements UsersService{
    private final UsersRepo usersRepo;


    @Autowired
    public UsersServiceImplementation(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    @Override
    public Users createUser(UsersDTO usersDTO) {
        Users users = new Users();
        users.setId(users.getId());
        users.setEmail(usersDTO.getEmail());
        users.setFirstName(usersDTO.getFirstName());
        users.setLastName(usersDTO.getLastName());
        users.setNumber(usersDTO.getNumber());
        users.setPassword(usersDTO.getPassword());
        usersRepo.save(users);
        return users;
    }

    @Override
    public UsersViewDTO loginUser(String email, String password) {

        Optional<Users> userToBeFind = Optional.ofNullable(usersRepo.findByEmail(email));
        if(userToBeFind.isPresent())
        {
            if(password.equals(userToBeFind.get().getPassword()))
            {
                UsersViewDTO loggedInUser = new UsersViewDTO();
                loggedInUser.setId(userToBeFind.get().getId());
                loggedInUser.setEmail(email);
                loggedInUser.setFirstName(userToBeFind.get().getFirstName());
                loggedInUser.setLastName(userToBeFind.get().getLastName());
                loggedInUser.setNumber(userToBeFind.get().getNumber());

                return loggedInUser;
            }
            else
            {
                throw new GeneralException("Password Missmatch");
            }
        }
        else
        {
            throw new UserNotFoundException("User Not Found1");
        }
    }

    @Override
    public String deleteUser(Integer id, String password) {
        Optional<Users> userToBeDeleted = usersRepo.findById(id);
        if(userToBeDeleted.isPresent())
        {
            System.out.println(userToBeDeleted.get().getPassword());
            if(password.equals(userToBeDeleted.get().getPassword()))
            {
                String message="User Deleted With Id : "+id+" and Username : "+userToBeDeleted.get().getFirstName();
                usersRepo.deleteById(id);
                return message;
            }
            else
            {
                throw new GeneralException("Password Missmatch");
            }
        }
        else
        {
            throw new UserNotFoundException("User Not Found");
        }
    }


    @Override
    public Users editDetails(UsersViewDTO usersViewDTO) {
        Users userToUpdate = findForUpadte(usersViewDTO.getId()); // Retrieve the user

        if (userToUpdate != null) {
            userToUpdate.setFirstName(usersViewDTO.getFirstName());
            userToUpdate.setLastName(usersViewDTO.getLastName());
            userToUpdate.setEmail(usersViewDTO.getEmail());
            userToUpdate.setNumber(usersViewDTO.getNumber());

            usersRepo.save(userToUpdate); // Save the updated user
            return userToUpdate;
        } else {
            throw new UserNotFoundException("User Not Found");
        }
    }


    @Override
    public Users editPassword(UsersDTO usersDTO) {
        Users userWhosePwdToBeUpdated = findForUpadte(usersDTO.getId()); // Retrieve the user

        if (userWhosePwdToBeUpdated != null) {
            userWhosePwdToBeUpdated.setPassword(usersDTO.getPassword()); // Update the password
            usersRepo.save(userWhosePwdToBeUpdated); // Save the updated user
            return userWhosePwdToBeUpdated;
        } else {
            throw new UserNotFoundException("User Not Found");
        }
    }

    @Override
    public UsersViewDTO findByEmail(String email)
    {
        Optional<Users> userToBeFind = Optional.ofNullable(usersRepo.findByEmail(email));
        if(userToBeFind.isPresent())
        {
            UsersViewDTO usersDTOfindByEmail= new UsersViewDTO();
            usersDTOfindByEmail.setId(userToBeFind.get().getId());
            usersDTOfindByEmail.setEmail(userToBeFind.get().getEmail());
            usersDTOfindByEmail.setFirstName(userToBeFind.get().getFirstName());
            usersDTOfindByEmail.setLastName(userToBeFind.get().getLastName());
            usersDTOfindByEmail.setNumber(userToBeFind.get().getNumber());

            return usersDTOfindByEmail;
        }
        else
        {
            throw  new UserNotFoundException("User Not Found");

        }
    }

    @Override
    public UsersViewDTO findById(int id) {
        Optional<Users> userToBeFind = usersRepo.findById(id);
        if(userToBeFind.isPresent())
        {
            UsersViewDTO usersDTOfindById= new UsersViewDTO();
            usersDTOfindById.setId(userToBeFind.get().getId());
            usersDTOfindById.setEmail(userToBeFind.get().getEmail());
            usersDTOfindById.setFirstName(userToBeFind.get().getFirstName());
            usersDTOfindById.setLastName(userToBeFind.get().getLastName());
            usersDTOfindById.setNumber(userToBeFind.get().getNumber());

            return usersDTOfindById;
        }
        else
        {
            throw new UserNotFoundException("User Not Found");
        }
    }

    @Override
    public Users findForUpadte(int id) {
        Optional<Users> userToBeFind = usersRepo.findById(id);
        if(userToBeFind.isPresent())
        {
            return userToBeFind.get();
        }
        else
        {
            throw new UserNotFoundException("User Not Found");
        }
    }
}
