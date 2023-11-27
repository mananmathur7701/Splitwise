package com.example.Splitwise_Backend.ExpenseSplit.Service;
import com.example.Splitwise_Backend.ExpenseSplit.Entity.ExpenseSplit;
import com.example.Splitwise_Backend.ExpenseSplit.Repository.ExpenseSplitRepo;
import com.example.Splitwise_Backend.Expenses.Entity.Expenses;
import com.example.Splitwise_Backend.Groups.Entity.Groups;
import com.example.Splitwise_Backend.Groups.Repository.GroupsRepo;
import com.example.Splitwise_Backend.SquareOffTransactions.Entity.SquareOffTransactions;
import com.example.Splitwise_Backend.Users.Entity.Users;
import com.example.Splitwise_Backend.Expenses.Repository.ExpensesRepo;
import com.example.Splitwise_Backend.Users.Repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public String deleteExpenseSplit(int expenseId) {
        Optional<List<ExpenseSplit>> expenseSplitToBeDeleted = expenseSplitRepo.findByExpenses_Id(expenseId);
        if(expenseSplitToBeDeleted.isPresent())
        {
            String result = "Expense Split Deleted For Expense Id : "+expenseId;
            for (ExpenseSplit e:expenseSplitToBeDeleted.get())
            {
                result.concat("\n").concat(e.toString());
                expenseSplitRepo.delete(e);
            }
            return result;
        }
        else
        {
            throw new RuntimeException("Expense Not Found");
        }
    }

    @Override
    public List<ExpenseSplit> amountToBeRecievedByYou(int userId) {
        Optional<Users> userWhoRecieveAmount = usersRepo.findById(userId);
        if (userWhoRecieveAmount.isPresent())
        {
            Optional<List<ExpenseSplit>> allTransactionWhereUserRecieve = expenseSplitRepo.findByPayedToId(userWhoRecieveAmount.get());
            return allTransactionWhereUserRecieve.get();
        }
        else
        {
            throw new RuntimeException("User Not Found");
        }

    }

    @Override
    public List<ExpenseSplit> amountToBeGivenByYou(int userId)
    {
        Optional<Users> userWhoGivesAmount = usersRepo.findById(userId);
        if (userWhoGivesAmount.isPresent())
        {
            Optional<List<ExpenseSplit>> allTransactionWhereUserGives = expenseSplitRepo.findByPayerId(userWhoGivesAmount.get());
            return allTransactionWhereUserGives.get();
        }
        else
        {
            throw new RuntimeException("User Not Found");
        }
    }


    @Override
    public List<ExpenseSplit> expenseSplitOfAllGroups(int groupId)
    {
        Optional<Groups> expensesOfGroup= groupsRepo.findById(groupId);
        if (expensesOfGroup.isPresent())
        {
            Optional<List<ExpenseSplit>> allTransactionOfGroup = expenseSplitRepo.findByGroups_Id(groupId);
            return allTransactionOfGroup.get();
        }
        else
        {
            throw new RuntimeException("Group Not Found");
        }
    }

    @Override
    public List<ExpenseSplit> expenseSplitOfParticularExpenseId(int expenseId)
    {
        Optional<Expenses> expensesOfExpenseSplit= expensesRepo.findById(expenseId);
        if (expensesOfExpenseSplit.isPresent())
        {
            Optional<List<ExpenseSplit>> allTransactionOfExpense = expenseSplitRepo.findByExpenses_Id(expenseId);
            return allTransactionOfExpense.get();
        }
        else
        {
            throw new RuntimeException("Expense Not Found");
        }
    }





    @Override
    public float totalAmountJoDenaHaiByPayerId(int payerId)
    {
        Optional<Users> payer = usersRepo.findById(payerId);
        if(payer.isPresent())
        {
            float totalAmount = 0;
            Optional<List<ExpenseSplit>> totalPaisaDenaHai = expenseSplitRepo.findByPayerId(payer.get());
            if (totalPaisaDenaHai.isPresent())
            {
                for (ExpenseSplit ex: totalPaisaDenaHai.get())
                {
                    totalAmount+= ex.getShareAmount();
                }
                return totalAmount;
            }
            else
            {
                return totalAmount;
            }
        }
        else
        {
            throw new RuntimeException("User Not Found Who Needs To Pay");
        }

    }

    @Override
    public float totalAmountJoLenaHaiByPaidToId(int payedToId)
    {
        Optional<Users> payee = usersRepo.findById(payedToId);
        if(payee.isPresent())
        {
            float totalAmount = 0;
            Optional<List<ExpenseSplit>> totalPaisaLenaHai = expenseSplitRepo.findByPayedToId(payee.get());
            if (totalPaisaLenaHai.isPresent())
            {
                for (ExpenseSplit ex: totalPaisaLenaHai.get())
                {
                    totalAmount+= ex.getShareAmount();
                }
                return totalAmount;
            }
            else
            {
                return totalAmount;
            }
        }
        else
        {
            throw new RuntimeException("User Not Found Who Needs To Pay");
        }
    }

}
