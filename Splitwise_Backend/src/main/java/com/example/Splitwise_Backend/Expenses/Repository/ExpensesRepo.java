package com.example.Splitwise_Backend.Expenses.Repository;

import com.example.Splitwise_Backend.Expenses.Entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesRepo extends JpaRepository<Expenses, Integer> {
}
