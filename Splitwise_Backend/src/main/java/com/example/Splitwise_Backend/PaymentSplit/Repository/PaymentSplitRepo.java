package com.example.Splitwise_Backend.PaymentSplit.Repository;

import com.example.Splitwise_Backend.PaymentSplit.Entity.PaymentSplit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentSplitRepo extends JpaRepository<PaymentSplit,Integer> {
    public Optional<List<PaymentSplit>> findByUsers_Id(int userId);
    public Optional<List<PaymentSplit>> findByExpenses_Id(int expenseId);
}
