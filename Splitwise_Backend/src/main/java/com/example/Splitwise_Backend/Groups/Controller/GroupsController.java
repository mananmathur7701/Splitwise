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
    private GroupsServiceImplementation groupsServiceImplementation;

    @Autowired
    public GroupsController(GroupsServiceImplementation groupsServiceImplementation) {
        this.groupsServiceImplementation = groupsServiceImplementation;
    }

    @PostMapping("/createGroup")
    public Groups createGroup(@RequestBody Map<String,Object> details)
    {
        int userId = Integer.parseInt(details.get("userId").toString());
        String groupName = (String) details.get("groupName");
        return groupsServiceImplementation.createGroup(userId,groupName);
    }

    @GetMapping("/groupInfo/{id}")
    public Groups getGroupInformation(@PathVariable int id)
    {
        return groupsServiceImplementation.groupsInfoById(id);
    }

    @GetMapping("/groupInfo/{groupName}")
    public List<Groups> getGroupInformationFromName(@PathVariable String groupName)
    {
        return groupsServiceImplementation.groupInfoByName(groupName);
    }

    @GetMapping("/groupMembers/{id}")
    public List<UsersViewDTO> getGroupMembersDetails(@PathVariable int id)
    {
        return groupsServiceImplementation.getGroupMembers(id);
    }

    @PostMapping("/editGroup/{id}")
    public Groups editGroups(@PathVariable int id,@RequestBody Map<String,String> data)
    {
        String groupName = data.get("groupName");
        return groupsServiceImplementation.editGroups(id,groupName);
    }

    @DeleteMapping("/deleteGroup/{id}")
    public String deleteGroup(@PathVariable int id)
    {
        return groupsServiceImplementation.deleteGroup(id);
    }

    @PostMapping("/addUserToGroup/{id}")
    public Groups addUserToGroup(@PathVariable int id,@RequestBody List<String> emails)
    {
        return groupsServiceImplementation.addUser(emails,id);
    }

    @PostMapping("/deleteUserFromGroup/{id}")
    public Groups deleteUserFromGroup(@PathVariable int id, @RequestBody Map<String,String> data)
    {
        String userEmail = data.get("userEmail");
        return groupsServiceImplementation.removeUser(userEmail,id);
    }


    @GetMapping("/groupsOfUser/{id}")
    public List<Groups> allGroupsOfUser(@PathVariable int id)
    {
        return groupsServiceImplementation.allGroupsOfUsers(id);
    }

}
