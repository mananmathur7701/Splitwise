package com.example.Splitwise_Backend.ExpenseSplit.Service;
import com.example.Splitwise_Backend.ExpenseSplit.Entity.ExpenseSplit;
import com.example.Splitwise_Backend.ExpenseSplit.Repository.ExpenseSplitRepo;
import com.example.Splitwise_Backend.Expenses.Entity.Expenses;
import com.example.Splitwise_Backend.Groups.Entity.Groups;
import com.example.Splitwise_Backend.Groups.Repository.GroupsRepo;
import com.example.Splitwise_Backend.Users.Entity.Users;
import com.example.Splitwise_Backend.Expenses.Repository.ExpensesRepo;
import com.example.Splitwise_Backend.Users.Repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ExpenseSplitServiceImplementation implements ExpenseSplitService
{
    private final UsersRepo usersRepo;
    private final ExpenseSplitRepo expenseSplitRepo;
    private final ExpensesRepo expensesRepo;
    private final GroupsRepo groupsRepo;

    @Autowired
    public ExpenseSplitServiceImplementation(UsersRepo usersRepo, ExpenseSplitRepo expenseSplitRepo, ExpensesRepo expensesRepo, GroupsRepo groupsRepo)
    {
        this.usersRepo = usersRepo;
        this.expenseSplitRepo = expenseSplitRepo;
        this.expensesRepo = expensesRepo;
        this.groupsRepo = groupsRepo;
    }



    public ExpenseSplit saveExpenseSplit(int payerId, int payedToId, int groupId, int expenseId, float amountPaid)
    {
        Optional<Users> payer = usersRepo.findById(payerId);
        Optional<Users> payedTo = usersRepo.findById(payedToId);
        if(payer.isPresent() && payedTo.isPresent())
        {
            Optional<Groups> groupOfExpense = groupsRepo.findById(groupId);
            if(groupOfExpense.isPresent())
            {
                Optional<Expenses> expenseOfSettlement = expensesRepo.findById(expenseId);
                if(expenseOfSettlement.isPresent())
                {
                    ExpenseSplit expenseToBeSplitted = new ExpenseSplit();
                    expenseToBeSplitted.setShareAmount(amountPaid);
                    expenseToBeSplitted.setExpenses(expenseOfSettlement.get());
                    expenseToBeSplitted.setPayerId(payer.get());
                    expenseToBeSplitted.setPayedToId(payedTo.get());
                    expenseToBeSplitted.setGroups(groupOfExpense.get());
                    return expenseSplitRepo.save(expenseToBeSplitted);
                }
                else
                {
                    throw new RuntimeException("Expense Not Found");
                }
            }
            else
            {
                throw new RuntimeException("No Group Found Of Expense");
            }
        }
        else
        {
            throw new RuntimeException("Out Of Given Users, One User Not Found");
        }
    }
}
