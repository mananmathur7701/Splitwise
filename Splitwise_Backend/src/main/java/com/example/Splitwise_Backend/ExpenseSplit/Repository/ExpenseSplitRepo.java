package com.example.Splitwise_Backend.ExpenseSplit.Repository;

import com.example.Splitwise_Backend.ExpenseSplit.Entity.ExpenseSplit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseSplitRepo extends JpaRepository<ExpenseSplit,Integer> {
}
