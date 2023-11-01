package com.example.Splitwise_Backend.Expenses.Service;

import com.example.Splitwise_Backend.Expenses.DTO.expenseDTO;
import com.example.Splitwise_Backend.Expenses.Entity.Expenses;
import com.example.Splitwise_Backend.Expenses.Repository.ExpensesRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

//Hello
@Service
public class EspensesServiceImplementation implements ExpensesService{

    @Autowired
    private ExpensesRepo ExpensesRepo;

//    @Autowired
//    private ModelMapper modelMapper;

    @Autowired
    protected EspensesServiceImplementation(ExpensesRepo expensesRepo) {
        ExpensesRepo = expensesRepo;
    }

    @Override
    public Expenses addExpense(Expenses expense) {
        expense.setSpentAt(new Timestamp(new Date().getTime()));
        Expenses expenses = ExpensesRepo.save(expense);
//        expenseDTO expenseDTO = this.modelMapper.map(expense, expenseDTO.class);

        return expenses;
    }

    @Override
    public String deleteExpense(int expenseId) {
        return null;
    }


    @Override
    public List<Expenses> showAllExpenses() {
        return null;
    }

    @Override
    public Expenses editExpense(int ExpenseId) {
        return null;
    }

    @Override
    public Expenses expenseInfoById(int expenseId) {
        return null;
    }

    @Override
    public List<Expenses> allExpensesOfGroup(int groupId) {
        return null;
    }
}
