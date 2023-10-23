package com.example.Splitwise_Backend.SquareOffTransactions.Repository;

import com.example.Splitwise_Backend.SquareOffTransactions.Entity.SquareOffTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SquareOffTransactionsRepo extends JpaRepository<SquareOffTransactions,Integer> {
}
