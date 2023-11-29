package com.example.Splitwise_Backend.Friends.Service;
import com.example.Splitwise_Backend.Exceptions.UserNotFoundException;
import com.example.Splitwise_Backend.Friends.Entity.Friends;
import com.example.Splitwise_Backend.Friends.Repository.FriendsRepo;
import com.example.Splitwise_Backend.Users.Entity.Users;
import com.example.Splitwise_Backend.Users.Repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FriendsServiceImplementation implements FriendsService
{
    private final FriendsRepo friendsRepo;
    private final UsersRepo usersRepo;

    @Autowired
    public FriendsServiceImplementation(FriendsRepo friendsRepo, UsersRepo usersRepo) {
        this.friendsRepo = friendsRepo;
        this.usersRepo = usersRepo;
    }


    @Override
    public void addFriends(int userIdWhoAddingFriend, int userIdWhoIsAddedAsFriend) {
        Optional<Users> sender = usersRepo.findById(userIdWhoAddingFriend);
        Optional<Users> receiver = usersRepo.findById(userIdWhoIsAddedAsFriend);

        if (!sender.isPresent()) {
            throw new UserNotFoundException("User Who Is Sending Friend Request Not Found");
        }

        if (!receiver.isPresent()) {
            throw new UserNotFoundException("User To Be Added As Friend Not Found");
        }

        if (userIdWhoAddingFriend == userIdWhoIsAddedAsFriend) {
            // Self-adding is not allowed
            return;
        }

        if (idOfFriendsOfUsers(userIdWhoAddingFriend).contains(userIdWhoIsAddedAsFriend)) {
            // Users are already friends
            return;
        }

        Friends friends = new Friends();
        friends.setUserWhoAddedFriend(sender.get());
        friends.setUserWhoIsAddedAsFriend(receiver.get());
        friendsRepo.save(friends);

        Friends reverseFriends = new Friends();
        reverseFriends.setUserWhoAddedFriend(receiver.get());
        reverseFriends.setUserWhoIsAddedAsFriend(sender.get());
        friendsRepo.save(reverseFriends);

        String result = sender.get().getFirstName() + " Has Added Friend " + receiver.get().getFirstName();
    }


    @Override
    public List<Friends> friendsOfUser(int userId) {
        Optional<Users> users = usersRepo.findById(userId);
        if(users.isPresent())
        {
            return friendsRepo.findByUserWhoAddedFriend_Id(userId).get();
        }
        else
        {
            throw new UserNotFoundException("User Not Found");
        }
    }

    @Override
    public List<String> EmailsOfFriendsOfUser(int userId)
    {
        Optional<Users> users = usersRepo.findById(userId);
        if(users.isPresent())
        {
            List<String> friendsEmailList = new ArrayList<>();
            List<Friends> friendsList = friendsRepo.findByUserWhoAddedFriend_Id(userId).get();
            for (Friends f : friendsList)
            {
                friendsEmailList.add(f.getUserWhoIsAddedAsFriend().getEmail());
            }
            return friendsEmailList;
        }
        else
        {
            throw new UserNotFoundException("User Not Found");
        }
    }

    @Override
    public List<Integer> idOfFriendsOfUsers(int userId) {
        Optional<Users> users = usersRepo.findById(userId);
        if(users.isPresent())
        {
            List<Integer> friendsIdList = new ArrayList<>();
            List<Friends> friendsList = friendsRepo.findByUserWhoAddedFriend_Id(userId).get();
            for (Friends f : friendsList)
            {
                friendsIdList.add(f.getUserWhoIsAddedAsFriend().getId());
            }
            System.out.println(friendsIdList);
            return friendsIdList;
        }
        else
        {
            throw new UserNotFoundException("User Not Found");
        }
    }
}
