package com.example.Splitwise_Backend.SquareOffTransactions.Repository;
import com.example.Splitwise_Backend.SquareOffTransactions.Entity.SquareOffTransactions;
import com.example.Splitwise_Backend.Users.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SquareOffTransactionsRepo extends JpaRepository<SquareOffTransactions,Integer>
{
    Optional<List<SquareOffTransactions>> findByPayerId(Users payer);
    Optional<List<SquareOffTransactions>> findByPayedToId(Users payedTo);
}
