package com.example.Splitwise_Backend.Users.Controller;
import com.example.Splitwise_Backend.Users.DTO.UsersDTO;
import com.example.Splitwise_Backend.Users.DTO.UsersViewDTO;
import com.example.Splitwise_Backend.Users.Entity.Users;
import com.example.Splitwise_Backend.Users.Service.UsersServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
import java.util.Random;

@RestController
public class UsersController {
    private final UsersServiceImplementation usersServiceImplementation;
    private final JavaMailSender javaMailSender;
    private static int otp;
    UsersDTO newUserDTO= new UsersDTO();

    @Autowired
    public UsersController(UsersServiceImplementation usersServiceImplementation,JavaMailSender javaMailSender) {
        this.usersServiceImplementation = usersServiceImplementation;
        this.javaMailSender=javaMailSender;
    }

    public int generateOTP() {
        Random random = new Random();
        int min = 1000;
        int max = 9999;
        return random.nextInt(max - min + 1) + min;
    }

    public void sendMail(String toEmail, String body)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mananmathur321@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Confirmation OTP For Splitter");
        message.setText(body);

        javaMailSender.send(message);
        System.out.println("Mail Send Successfully To : "+toEmail);
    }

    @PostMapping("/createUser")
    public int createUser(@RequestBody Users users)
    {
        newUserDTO.setFirstName(users.getFirstName());
        newUserDTO.setLastName(users.getLastName());
        newUserDTO.setEmail(users.getEmail());
        newUserDTO.setNumber(users.getNumber());
        System.out.println(users.getPassword());
        newUserDTO.setPassword(users.getPassword());
        otp=generateOTP();

        System.out.println(newUserDTO.toString());
        System.out.println(otp);
        String mailMessage="Your OTP is "+otp;
        sendMail(users.getEmail(),mailMessage);
        return otp;
    }

    @PostMapping("/otpVerify")
    public Users verifyOTP(@RequestBody String optRecieved)
    {
        System.out.println("Hiiiiii-----------------------------------");
        System.out.println(newUserDTO.toString());
        if(String.valueOf(otp).equals(optRecieved))
        {
            return usersServiceImplementation.createUser(newUserDTO);
        }
        else
        {
            throw new RuntimeException("OTP Mismatched.\nTry Again........");
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id, @RequestBody String password)
    {
        return usersServiceImplementation.deleteUser(id,password);
    }

    @PutMapping("/editDetails/{id}")
    public Users editDetails(@PathVariable int id, @RequestBody Users users)
    {
        UsersViewDTO updatedUser= new UsersViewDTO();
        updatedUser.setId(id);
        updatedUser.setEmail(users.getEmail());
        updatedUser.setFirstName(users.getFirstName());
        updatedUser.setLastName(users.getLastName());
        updatedUser.setNumber(users.getNumber());

        return usersServiceImplementation.editDetails(updatedUser);
    }

    @PostMapping("/editPassword/{id}")
    public Users editPassword(@PathVariable int id,@RequestBody Map<String, String> passwords)
    {
        String oldPassword = passwords.get("oldPassword");
        String newPassword = passwords.get("newPassword");
        Users userWhosePwdToBeUpdated = usersServiceImplementation.findForUpadte(id);

        UsersDTO updatedUser=new UsersDTO();
        updatedUser.setId(id);
        updatedUser.setEmail(userWhosePwdToBeUpdated.getEmail());
        updatedUser.setFirstName(userWhosePwdToBeUpdated.getFirstName());
        updatedUser.setLastName(userWhosePwdToBeUpdated.getLastName());
        updatedUser.setNumber(userWhosePwdToBeUpdated.getNumber());

        if(oldPassword.equals(userWhosePwdToBeUpdated.getPassword()))
        {
           updatedUser.setPassword(newPassword);
            return usersServiceImplementation.editPassword(updatedUser);
        }
        else
        {
            throw new RuntimeException("Password Missmatched");
        }
    }

    @GetMapping("/userDetailsById/{id}")
    public UsersViewDTO showUser(@PathVariable int id)
    {
        return usersServiceImplementation.findById(id);
    }

    @GetMapping("/userDetailsByEmail/{email}")
    public UsersViewDTO showUser(@PathVariable String email)
    {
        return usersServiceImplementation.findByEmail(email);
    }
}
