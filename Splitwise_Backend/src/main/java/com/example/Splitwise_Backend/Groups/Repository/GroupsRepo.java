package com.example.Splitwise_Backend.Groups.Repository;

import com.example.Splitwise_Backend.Groups.Entity.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupsRepo extends JpaRepository<Groups,Integer>{
}
