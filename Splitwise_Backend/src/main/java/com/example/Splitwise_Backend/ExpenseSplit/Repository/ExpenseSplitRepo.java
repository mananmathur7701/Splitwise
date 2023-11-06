package com.example.Splitwise_Backend.ExpenseSplit.Repository;

import com.example.Splitwise_Backend.ExpenseSplit.Entity.ExpenseSplit;
import com.example.Splitwise_Backend.PaymentSplit.Entity.PaymentSplit;
import com.example.Splitwise_Backend.Users.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseSplitRepo extends JpaRepository<ExpenseSplit,Integer>
{
    Optional<List<ExpenseSplit>> findByPayerId(Users payer);
    Optional<List<ExpenseSplit>> findByPayedToId(Users payedTo);
    Optional<List<ExpenseSplit>> findByExpenses_Id(int expenseId);
    Optional<List<ExpenseSplit>> findByGroups_Id(int groupId);
}
