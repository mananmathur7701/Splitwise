package com.example.Splitwise_Backend.ExpenseSplit.Service;
import com.example.Splitwise_Backend.Exceptions.ExpenseNotFoundException;
import com.example.Splitwise_Backend.Exceptions.GeneralException;
import com.example.Splitwise_Backend.Exceptions.GroupNotFoundException;
import com.example.Splitwise_Backend.Exceptions.UserNotFoundException;
import com.example.Splitwise_Backend.ExpenseSplit.DTO.ExpenseSplitDTO;
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

import java.util.ArrayList;
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
                    throw new ExpenseNotFoundException("Expense Not Found");
                }
            }
            else
            {
                throw new GroupNotFoundException("No Group Found Of Expense");
            }
        }
        else
        {
            throw new UserNotFoundException("Out Of Given Users, One User Not Found");
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
            throw new ExpenseNotFoundException("Expense Not Found");
        }
    }

    @Override
    public List<ExpenseSplitDTO> amountToBeRecievedByYou(int userId) {
        Optional<Users> userWhoRecieveAmount = usersRepo.findById(userId);
        if (userWhoRecieveAmount.isPresent())
        {
            Optional<List<ExpenseSplit>> allTransactionWhereUserRecieve = expenseSplitRepo.findByPayedToId(userWhoRecieveAmount.get());
            //return allTransactionWhereUserRecieve.get();
            if (allTransactionWhereUserRecieve.isPresent())
            {
                List<ExpenseSplitDTO> expenseSplitDTOList = new ArrayList<>();
                for (ExpenseSplit exp : allTransactionWhereUserRecieve.get())
                {
                    expenseSplitDTOList.add(new ExpenseSplitDTO(exp.getId(),exp.getShareAmount(), exp.getExpenses().getId(), exp.getExpenses().getComment(), exp.getGroups().getId(), exp.getGroups().getGroupName(), exp.getPayedToId().getId(), exp.getPayedToId().getEmail(), exp.getPayerId().getId(), exp.getPayerId().getEmail()));
                }
                return expenseSplitDTOList;
            }
            else
            {
                throw new GeneralException("No Transaction Of A Particular Expense");
            }
        }
        else
        {
            throw new UserNotFoundException("User Not Found");
        }

    }

    @Override
    public List<ExpenseSplitDTO> amountToBeGivenByYou(int userId)
    {
        Optional<Users> userWhoGivesAmount = usersRepo.findById(userId);
        if (userWhoGivesAmount.isPresent())
        {
            Optional<List<ExpenseSplit>> allTransactionWhereUserGives = expenseSplitRepo.findByPayerId(userWhoGivesAmount.get());
            if (allTransactionWhereUserGives.isPresent())
            {
                List<ExpenseSplitDTO> expenseSplitDTOList = new ArrayList<>();
                for (ExpenseSplit exp : allTransactionWhereUserGives.get())
                {
                    expenseSplitDTOList.add(new ExpenseSplitDTO(exp.getId(),exp.getShareAmount(), exp.getExpenses().getId(), exp.getExpenses().getComment(), exp.getGroups().getId(), exp.getGroups().getGroupName(), exp.getPayedToId().getId(), exp.getPayedToId().getEmail(), exp.getPayerId().getId(), exp.getPayerId().getEmail()));
                }
                return expenseSplitDTOList;
            }
            else
            {
                throw new GeneralException("No Transaction Of A Particular Expense");
            }
            //return allTransactionWhereUserGives.get();
        }
        else
        {
            throw new UserNotFoundException("User Not Found");
        }
    }


    @Override
    public List<ExpenseSplitDTO> expenseSplitOfAllGroups(int groupId)
    {
        Optional<Groups> expensesOfGroup= groupsRepo.findById(groupId);
        if (expensesOfGroup.isPresent())
        {
            Optional<List<ExpenseSplit>> allTransactionOfGroup = expenseSplitRepo.findByGroups_Id(groupId);
            if (allTransactionOfGroup.isPresent())
            {
                List<ExpenseSplitDTO> expenseSplitDTOList = new ArrayList<>();
                for (ExpenseSplit exp : allTransactionOfGroup.get())
                {
                    expenseSplitDTOList.add(new ExpenseSplitDTO(exp.getId(),exp.getShareAmount(), exp.getExpenses().getId(), exp.getExpenses().getComment(), exp.getGroups().getId(), exp.getGroups().getGroupName(), exp.getPayedToId().getId(), exp.getPayedToId().getEmail(), exp.getPayerId().getId(), exp.getPayerId().getEmail()));
                }
                return expenseSplitDTOList;
            }
            else
            {
                throw new GeneralException("No Transaction Of A Particular Group");
            }
        }
        else
        {
            throw new GroupNotFoundException("Group Not Found");
        }
    }

    @Override
    public List<ExpenseSplitDTO> expenseSplitOfParticularExpenseId(int expenseId)
    {
        Optional<Expenses> expensesOfExpenseSplit= expensesRepo.findById(expenseId);
        if (expensesOfExpenseSplit.isPresent())
        {
            Optional<List<ExpenseSplit>> allTransactionOfExpense = expenseSplitRepo.findByExpenses_Id(expenseId);
            if (allTransactionOfExpense.isPresent())
            {
                List<ExpenseSplitDTO> expenseSplitDTOList = new ArrayList<>();
                for (ExpenseSplit exp : allTransactionOfExpense.get())
                {
                    expenseSplitDTOList.add(new ExpenseSplitDTO(exp.getId(),exp.getShareAmount(), exp.getExpenses().getId(), exp.getExpenses().getComment(), exp.getGroups().getId(), exp.getGroups().getGroupName(), exp.getPayedToId().getId(), exp.getPayedToId().getEmail(),exp.getPayedToId().getFirstName().concat(" ").concat(exp.getPayedToId().getLastName()), exp.getPayerId().getId(), exp.getPayerId().getEmail(),exp.getPayerId().getFirstName().concat(" ").concat(exp.getPayerId().getLastName())));
                }
                return expenseSplitDTOList;
            }
            else
            {
                throw new GeneralException("No Transaction Of A Particular Expense");
            }
        }
        else
        {
            throw new ExpenseNotFoundException("Expense Not Found");
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
            throw new UserNotFoundException("User Not Found Who Needs To Pay");
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
            throw new UserNotFoundException("User Not Found Who Needs To Pay");
        }
    }

}
