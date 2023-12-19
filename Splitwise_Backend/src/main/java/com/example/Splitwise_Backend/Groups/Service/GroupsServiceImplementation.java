package com.example.Splitwise_Backend.Groups.Service;

import com.example.Splitwise_Backend.Exceptions.GroupNotFoundException;
import com.example.Splitwise_Backend.Exceptions.UserNotFoundException;
import com.example.Splitwise_Backend.Friends.Service.FriendsServiceImplementation;
import com.example.Splitwise_Backend.Groups.DTO.GroupsDTO;
import com.example.Splitwise_Backend.Groups.Entity.Groups;
import com.example.Splitwise_Backend.Groups.Repository.GroupsRepo;
import com.example.Splitwise_Backend.Users.DTO.UsersViewDTO;
import com.example.Splitwise_Backend.Users.Entity.Users;
import com.example.Splitwise_Backend.Users.Repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupsServiceImplementation implements GroupsService{
    private final GroupsRepo groupsRepo;
    private final UsersRepo usersRepo;
    private final JavaMailSender javaMailSender;

    private final FriendsServiceImplementation friendsServiceImplementation;

    @Autowired
    public GroupsServiceImplementation(GroupsRepo groupsRepo, UsersRepo usersRepo, JavaMailSender javaMailSender, FriendsServiceImplementation friendsServiceImplementation)
    {
        this.groupsRepo = groupsRepo;
        this.usersRepo = usersRepo;
        this.javaMailSender = javaMailSender;
        this.friendsServiceImplementation = friendsServiceImplementation;
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
            throw new UserNotFoundException("No User Exists To Form Group");
        }
    }

    @Override
    public GroupsDTO groupsInfoById(int groupId) {
        Optional<Groups> groupInfo = groupsRepo.findById(groupId);
        if (groupInfo.isPresent())
        {

            Groups group = groupInfo.get();
            GroupsDTO dto = new GroupsDTO(group.getId(),group.getGroupName(),group.getCreatedAt(),group.getUpdatedAt());
            return dto;
        }
        else {
            throw new GroupNotFoundException("Group ID Don't Exist");
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
            throw new GroupNotFoundException("Group ID Don't Exist");
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
            throw new GroupNotFoundException("Group With Id : "+groupId+" Dont Exsists.");
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
            throw new GroupNotFoundException("Group not found with ID: " + groupId);
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
            throw new GroupNotFoundException("Group Do Not Exist");
        }
    }

    @Override
    public Groups addUser(List<String> userEmail, int groupId) {
        Optional<Groups> groupToWhichUserAdded = groupsRepo.findById(groupId);
        if (groupToWhichUserAdded.isPresent())
        {
            for (String s : userEmail)
            {
                Optional<Users> userToBeAdded = Optional.ofNullable(usersRepo.findByEmail(s));
                if (userToBeAdded.isPresent())
                {
                    List<UsersViewDTO> groupMembers = getGroupMembers(groupId);
                    List<String> emails = new ArrayList<>();
                    for(UsersViewDTO u : groupMembers)
                    {
                        emails.add(u.getEmail());
                    }

                    if(!(emails.contains(s)))
                    {
                        List<Users> usersCompleteInformation=groupToWhichUserAdded.get().getUsers();
                        List<Integer> usersIdOfMembers = new ArrayList<>();
                        for(int i=0;i<usersCompleteInformation.toArray().length;i++)
                        {
                            usersIdOfMembers.add(usersCompleteInformation.get(i).getId());
                        }

                        for(int i=0;i<usersIdOfMembers.size();i++)
                        {
                            friendsServiceImplementation.addFriends(usersIdOfMembers.get(i),userToBeAdded.get().getId());
                        }

                        List<Users> userList= groupToWhichUserAdded.get().getUsers();
                        userList.add(userToBeAdded.get());
                        groupToWhichUserAdded.get().setUsers(userList);
                        groupsRepo.save(groupToWhichUserAdded.get());
                    }

                }
                else {
                    List<String> memberNames = groupToWhichUserAdded.get().getUsers().stream()
                            .map(user -> user.getFirstName() + " " + user.getLastName()) // Combine first name and last name
                            .toList();
                    String joinedNames = String.join(", ", memberNames);
                    sendMail(s,"Please Create an account in khata bahi. \n " +
                            "to be added in \" "+ groupToWhichUserAdded.get().getGroupName() + " \"\n" +
                            "the group members are " + joinedNames + "\n" +
                            "created by " + groupToWhichUserAdded.get().getCreatedBy().getFirstName().concat(" ").concat(groupToWhichUserAdded.get().getCreatedBy().getLastName()));
                }
            }
            return groupToWhichUserAdded.get();
        }
        else
        {
            throw new GroupNotFoundException("Group Not Found");
        }
    }

    public void sendMail(String toEmail, String body)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mananmathur321@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Invitation for creating an account in KHATA BAHI");
        message.setText(body);

        javaMailSender.send(message);
        System.out.println("Mail Send Successfully To : "+toEmail);
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
                throw new UserNotFoundException("User Not Found");
            }
        }
        else
        {
            throw new GroupNotFoundException("Group Not Found");
        }
    }

    @Override
    public List<GroupsDTO> allGroupsOfUsers(int userId)
    {
//        Optional<Users> user = usersRepo.findById(userId);
//        if (user.isPresent())
//        {
//            return user.get().getGroups();
//        }

        List<Groups> groupsOfUser = new ArrayList<>();
        Optional<Users> user = usersRepo.findById(userId);
        if (user.isPresent())
        {
            List<Groups> allGroups = groupsRepo.findAll();
            for(int i=0;i<allGroups.toArray().length;i++)
            {
                Groups group = allGroups.get(i);
                List<Users> groupUsers = group.getUsers();
                if(groupUsers.contains(user.get()))
                {
                    groupsOfUser.add(allGroups.get(i));
                }
            }
            List<GroupsDTO> groupsDTOOfGroupsOfUser = new ArrayList<>();
            for (Groups g:groupsOfUser)
            {
                GroupsDTO data = new GroupsDTO();
                data.setId(g.getId());
                data.setGroupName(g.getGroupName());
                data.setCreatedAt(g.getCreatedAt());
                data.setUpdatedAt(g.getUpdatedAt());
                groupsDTOOfGroupsOfUser.add(data);
            }
            System.out.println(groupsDTOOfGroupsOfUser);
            return groupsDTOOfGroupsOfUser;
        }
        else
        {
            throw new UserNotFoundException("User Not Found");
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
