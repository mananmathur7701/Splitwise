package com.example.Splitwise_Backend.Expenses.Repository;

import com.example.Splitwise_Backend.Expenses.Entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpensesRepo extends JpaRepository<Expenses, Integer>
{
    public Optional<List<Expenses>> findByGroups_Id(int groupId);
}
