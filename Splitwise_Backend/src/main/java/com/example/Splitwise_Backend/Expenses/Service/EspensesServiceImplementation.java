package com.example.Splitwise_Backend.Expenses.Service;

import com.example.Splitwise_Backend.Expenses.DTO.expenseDTO;
import com.example.Splitwise_Backend.Expenses.Entity.Expenses;
import com.example.Splitwise_Backend.Expenses.Repository.ExpensesRepo;
import com.example.Splitwise_Backend.Groups.Entity.Groups;
import com.example.Splitwise_Backend.Groups.Repository.GroupsRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//Hello
@Service
public class EspensesServiceImplementation implements ExpensesService{

    private ExpensesRepo expensesRepo;
    private GroupsRepo groupsRepo;

    @Autowired
    public EspensesServiceImplementation(ExpensesRepo expensesRepo, GroupsRepo groupsRepo) {
        this.expensesRepo = expensesRepo;
        this.groupsRepo = groupsRepo;
    }

    //    @Autowired
//    private ModelMapper modelMapper;


    @Override
    public Expenses addExpense(int groupId,float amountPaid,String comment) {
        Optional<Groups> groupOfExpense = groupsRepo.findById(groupId);
        if(groupOfExpense.isPresent())
        {
            Expenses newExpenses = new Expenses();
            newExpenses.setGroups(groupOfExpense.get());
            newExpenses.setComment(comment);
            newExpenses.setAmountPaid(amountPaid);
            newExpenses.setSpentAt(Timestamp.from(Instant.now()));
            return expensesRepo.save(newExpenses);
        }

//        expenseDTO expenseDTO = this.modelMapper.map(expense, expenseDTO.class);
        else
        {
            throw new RuntimeException("Group Not Found");
        }
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
