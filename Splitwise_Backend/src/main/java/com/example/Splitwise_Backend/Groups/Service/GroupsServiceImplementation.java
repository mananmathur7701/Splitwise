package com.example.Splitwise_Backend.Groups.Service;

import com.example.Splitwise_Backend.Groups.Entity.Groups;
import com.example.Splitwise_Backend.Groups.Repository.GroupsRepo;
import com.example.Splitwise_Backend.Users.DTO.UsersViewDTO;
import com.example.Splitwise_Backend.Users.Entity.Users;
import com.example.Splitwise_Backend.Users.Repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupsServiceImplementation implements GroupsService{
    private final GroupsRepo groupsRepo;
    private final UsersRepo usersRepo;

    @Autowired
    public GroupsServiceImplementation(GroupsRepo groupsRepo, UsersRepo usersRepo) {
        this.groupsRepo = groupsRepo;
        this.usersRepo = usersRepo;
    }

    @Override
    public Groups createGroup(int userId, String groupName)
    {
        Optional<Users> whoCreatedGroup = usersRepo.findById(userId);
        if(whoCreatedGroup.isPresent())
        {
            Groups groupCreated = new Groups();
            groupCreated.setGroupName(groupName);
            groupCreated.setCreatedBy(whoCreatedGroup.get());
            groupCreated.setCreatedAt(Timestamp.from(Instant.now()));
            groupCreated.setUpdatedAt(Timestamp.from(Instant.now()));

            List<Users> userList = new ArrayList<>();
            userList.add(whoCreatedGroup.get());

            groupCreated.setUsers(userList);

            return groupsRepo.save(groupCreated);
//            return groupCreated;
        }
        else
        {
            throw new RuntimeException("No User Exists To Form Group");
        }
    }

    @Override
    public Groups groupsInfoById(int groupId) {
        Optional<Groups> groupInfo = groupsRepo.findById(groupId);
        if (groupInfo.isPresent())
        {
            return groupInfo.get();
        }
        else {
            throw new RuntimeException("Group ID Don't Exist");
        }

    }

    @Override
    public List<Groups> groupInfoByName(String groupName) {
        Optional<List<Groups>> groupInfo = Optional.ofNullable(groupsRepo.findBygroupName(groupName));
        if (groupInfo.isPresent())
        {
            return (List<Groups>) groupInfo.get();
        }
        else
        {
            throw new RuntimeException("Group ID Don't Exist");
        }
    }

    @Override
    public List<UsersViewDTO> getGroupMembers(int groupId)
    {

        Optional<Groups> whoseGroupMemberToBeFind = groupsRepo.findById(groupId);
        if(whoseGroupMemberToBeFind.isPresent())
        {
            List<Users> usersCompleteInformation=whoseGroupMemberToBeFind.get().getUsers();
            List<UsersViewDTO> usersInformation = new ArrayList<>();

            for(int i=0;i<usersCompleteInformation.toArray().length;i++)
            {
                UsersViewDTO usersViewDTO = new UsersViewDTO();
                usersViewDTO.setId(usersCompleteInformation.get(i).getId());
                usersViewDTO.setFirstName(usersCompleteInformation.get(i).getFirstName());
                usersViewDTO.setLastName(usersCompleteInformation.get(i).getLastName());
                usersViewDTO.setEmail(usersCompleteInformation.get(i).getEmail());
                usersViewDTO.setNumber(usersCompleteInformation.get(i).getNumber());

                usersInformation.add(usersViewDTO);
            }
            return usersInformation;
        }
        else
        {
            throw new RuntimeException("Group With Id : "+groupId+" Dont Exsists.");
        }
    }

    @Override
    public Groups editGroups(int groupId, String groupName) {
        Optional<Groups> groupToBeEdited = groupsRepo.findById(groupId);
        if (groupToBeEdited.isPresent()) {
            Groups group = groupToBeEdited.get();
            group.setGroupName(groupName);
            group.setUpdatedAt(Timestamp.from(Instant.now()));
            groupsRepo.save(group); // Use the correct repository
            return group;
        } else {
            throw new RuntimeException("Group not found with ID: " + groupId);
        }
    }

    @Override
    public String deleteGroup(int groupId) {
        Optional<Groups> groupToBeDeleted = groupsRepo.findById(groupId);
        if(groupToBeDeleted.isPresent())
        {
            String prompt = "Group Deleted With Id : "+groupId+" and Members : "+groupToBeDeleted.get().getUsers();
            groupsRepo.deleteById(groupId);
            return prompt;
        }
        else {
            throw new RuntimeException("Group Do Not Exist");
        }
    }

    @Override
    public Groups addUser(List<String> userEmail, int groupId) {
        Optional<Groups> groupToWhichUserAdded = groupsRepo.findById(groupId);
        if (groupToWhichUserAdded.isPresent())
        {
            for (String s : userEmail) {
                Optional<Users> userToBeAdded = Optional.ofNullable(usersRepo.findByEmail(s));
                if (userToBeAdded.isPresent())
                {
                    List<Users> userList= groupToWhichUserAdded.get().getUsers();
                    userList.add(userToBeAdded.get());
                    groupToWhichUserAdded.get().setUsers(userList);
                    groupsRepo.save(groupToWhichUserAdded.get());
                }
            }
            return groupToWhichUserAdded.get();
        }
        else
        {
            throw new RuntimeException("Group Not Found");
        }
    }

    @Override
    public Groups removeUser(String userEmail, int groupId)
    {
        Optional<Users> userToBeRemoved = Optional.ofNullable(usersRepo.findByEmail(userEmail));
        Optional<Groups> groupToWhichUserRemoved = groupsRepo.findById(groupId);
        if (groupToWhichUserRemoved.isPresent())
        {
            if (userToBeRemoved.isPresent())
            {
                List<Users> userList= groupToWhichUserRemoved.get().getUsers();
                userList.remove(userToBeRemoved.get());
                groupToWhichUserRemoved.get().setUsers(userList);
                groupsRepo.save(groupToWhichUserRemoved.get());
                return groupToWhichUserRemoved.get();
            }
            else
            {
                throw new RuntimeException("User Not Found");
            }
        }
        else
        {
            throw new RuntimeException("Group Not Found");
        }
    }

    @Override
    public List<Groups> allGroupsOfUsers(int userId)
    {
        Optional<Users> user = usersRepo.findById(userId);
        if (user.isPresent())
        {
            return user.get().getGroups();
        }
        else
        {
            throw new RuntimeException("User Not Found");
        }


//        List<Groups> groupsOfUser = new ArrayList<>();
//        Optional<Users> user = usersRepo.findById(userId);
//        if (user.isPresent())
//        {
//            List<Groups> allGroups = groupsRepo.findAll();
//            for(int i=0;i<allGroups.toArray().length;i++)
//            {
//                Groups group = allGroups.get(i);
//                List<Users> groupUsers = group.getUsers();
//                if(groupUsers.contains(user))
//                {
//                    groupsOfUser.add(allGroups.get(i));
//                }
//            }
//            return groupsOfUser;
//        }
//        else
//        {
//            throw new RuntimeException("User Not Found");
//        }
    }
}
