package com.example.Splitwise_Backend.Groups.Service;
import com.example.Splitwise_Backend.Groups.DTO.GroupsDTO;
import com.example.Splitwise_Backend.Groups.Entity.Groups;
import com.example.Splitwise_Backend.Users.DTO.UsersViewDTO;

import java.sql.Timestamp;
import java.util.List;

public interface GroupsService {
    public Groups createGroup(int userId, String groupName);
    public Groups groupsInfoById(int groupId);
    public List<Groups> groupInfoByName(String groupName);
    public List<UsersViewDTO> getGroupMembers(int groupId);
    public Groups editGroups(int groupId, String groupName);
    public String deleteGroup(int groupId);
    public Groups addUser(List<String> userEmail, int groupId);
    public Groups removeUser(String userEmail, int groupId);
    public List<GroupsDTO> allGroupsOfUsers(int userId);
}
