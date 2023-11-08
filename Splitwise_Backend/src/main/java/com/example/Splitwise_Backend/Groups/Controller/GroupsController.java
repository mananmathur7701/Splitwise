package com.example.Splitwise_Backend.Groups.Controller;

import com.example.Splitwise_Backend.Groups.Entity.Groups;
import com.example.Splitwise_Backend.Groups.Service.GroupsServiceImplementation;
import com.example.Splitwise_Backend.Users.DTO.UsersViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:4200")
public class GroupsController
{
    private final GroupsServiceImplementation groupsServiceImplementation;

    @Autowired
    public GroupsController(GroupsServiceImplementation groupsServiceImplementation) {
        this.groupsServiceImplementation = groupsServiceImplementation;
    }

    @PostMapping("/createGroup")
    @CrossOrigin("http://localhost:4200")
    public Groups createGroup(@RequestBody Map<String,String> details)
    {
        int userId = Integer.parseInt(details.get("userId"));
        String groupName = details.get("groupName");
        return groupsServiceImplementation.createGroup(userId,groupName);
    }

    @GetMapping("/groupInfoById/{id}")
    @CrossOrigin("http://localhost:4200")
    public Groups getGroupInformation(@PathVariable int id)
    {
        return groupsServiceImplementation.groupsInfoById(id);
    }

    @GetMapping("/groupInfo/{groupName}")
    @CrossOrigin("http://localhost:4200")
    public List<Groups> getGroupInformationFromName(@PathVariable String groupName)
    {
        return groupsServiceImplementation.groupInfoByName(groupName);
    }

    @GetMapping("/groupMembers/{id}")
    @CrossOrigin("http://localhost:4200")
    public List<UsersViewDTO> getGroupMembersDetails(@PathVariable int id)
    {
        return groupsServiceImplementation.getGroupMembers(id);
    }

    @PostMapping("/editGroup/{id}")
    @CrossOrigin("http://localhost:4200")
    public Groups editGroups(@PathVariable int id,@RequestBody Map<String,String> data)
    {
        String groupName = data.get("groupName");
        return groupsServiceImplementation.editGroups(id,groupName);
    }

    @DeleteMapping("/deleteGroup/{id}")
    @CrossOrigin("http://localhost:4200")
    public String deleteGroup(@PathVariable int id)
    {
        return groupsServiceImplementation.deleteGroup(id);
    }

    @PostMapping("/addUserToGroup/{id}")
    @CrossOrigin("http://localhost:4200")
    public Groups addUserToGroup(@PathVariable int id,@RequestBody List<String> emails)
    {
        return groupsServiceImplementation.addUser(emails,id);
    }

    @PostMapping("/deleteUserFromGroup/{id}")
    @CrossOrigin("http://localhost:4200")
    public Groups deleteUserFromGroup(@PathVariable int id, @RequestBody Map<String,String> data)
    {
        String userEmail = data.get("userEmail");
        return groupsServiceImplementation.removeUser(userEmail,id);
    }


    @GetMapping("/groupsOfUser/{id}")
    @CrossOrigin("http://localhost:4200")
    public List<Groups> allGroupsOfUser(@PathVariable int id)
    {
        return groupsServiceImplementation.allGroupsOfUsers(id);
    }

}
