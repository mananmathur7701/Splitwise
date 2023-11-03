package com.example.Splitwise_Backend.Expenses.Service;
import com.example.Splitwise_Backend.DTO.ExpenseInfoDTO;
import com.example.Splitwise_Backend.DTO.SplitData;
import com.example.Splitwise_Backend.Expenses.Entity.Expenses;
import com.example.Splitwise_Backend.Expenses.Repository.ExpensesRepo;
import com.example.Splitwise_Backend.Groups.Entity.Groups;
import com.example.Splitwise_Backend.Groups.Repository.GroupsRepo;
import com.example.Splitwise_Backend.Users.Entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

//Hello
@Service
public class EspensesServiceImplementation implements ExpensesService{

    private final ExpensesRepo expensesRepo;
    private final GroupsRepo groupsRepo;

    @Autowired
    public EspensesServiceImplementation(ExpensesRepo expensesRepo, GroupsRepo groupsRepo) {
        this.expensesRepo = expensesRepo;
        this.groupsRepo = groupsRepo;
    }



    @Override
    public Expenses addExpense(ExpenseInfoDTO expenseInfoDTO) {
        Optional<Groups> groupOfExpense = groupsRepo.findById(expenseInfoDTO.getGroupId());
        if(groupOfExpense.isPresent())
        {
            float sumOfAmount=0;
            Expenses newExpenses = new Expenses();
            newExpenses.setGroups(groupOfExpense.get());
            newExpenses.setComment(expenseInfoDTO.getComment());
            newExpenses.setAmountPaid(expenseInfoDTO.getAmountPaid());
            newExpenses.setSpentAt(Timestamp.from(Instant.now()));
            expensesRepo.save(newExpenses);
            int expenseId = expensesRepo.save(newExpenses).getId();

//            Ab Chadhega Payment Split Ka Data
            List<SplitData> usersWhoPaidWithAmount = expenseInfoDTO.getPayee();
            if(!usersWhoPaidWithAmount.isEmpty())
            {
                for (SplitData data: usersWhoPaidWithAmount)
                {
                 sumOfAmount+=data.getAmount();
                }
                if(sumOfAmount==expenseInfoDTO.getAmountPaid())
                {
                    for (SplitData data: usersWhoPaidWithAmount)
                    {
                        ExpensesService expensesService
                    }
                }
                else
                {
                    throw new RuntimeException("Amount Mismatched Of Payment. Please Check Either Amount Paid Is More Than Shares Given By User. Or User Has Entered Wrong Shares As It Exceeds Amount");
                }
            }
            else
            {
                throw new RuntimeException("No User List Found");
            }

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
    public List<Expenses> showAllExpenses(int groupId) {
        Optional<List<Expenses>> exp = expensesRepo.findByGroups_Id(groupId);
        if(exp.isPresent())
        {

            return exp.get();
        }

//        expenseDTO expenseDTO = this.modelMapper.map(expense, expenseDTO.class);
        else
        {
            throw new RuntimeException("No expenses in the group Found");
        }

    }

    @Override
    public Expenses editExpense(int ExpenseId) {
        return null;
    }

    @Override
    public Expenses expenseInfoById(int expenseId) {
        Optional<List<Expenses>> expense = expensesRepo.findById(expenseId);
        if(expense.isPresent())
        {
            return  expense.get();
        }
        else
        {
            throw new RuntimeException("No expense found.");
        }
    }
}
