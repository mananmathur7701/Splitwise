package com.example.Splitwise_Backend.Groups.Repository;

import com.example.Splitwise_Backend.Groups.Entity.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupsRepo extends JpaRepository<Groups,Integer>{
     public List<Groups> findBygroupName(String groupName);
}
