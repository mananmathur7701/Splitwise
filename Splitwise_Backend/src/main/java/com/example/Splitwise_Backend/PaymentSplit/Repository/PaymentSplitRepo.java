package com.example.Splitwise_Backend.PaymentSplit.Repository;

import com.example.Splitwise_Backend.PaymentSplit.Entity.PaymentSplit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentSplitRepo extends JpaRepository<PaymentSplit,Integer> {
}
