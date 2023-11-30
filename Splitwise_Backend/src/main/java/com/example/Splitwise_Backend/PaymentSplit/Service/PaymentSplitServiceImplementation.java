package com.example.Splitwise_Backend.PaymentSplit.Service;
import com.example.Splitwise_Backend.Exceptions.ExpenseNotFoundException;
import com.example.Splitwise_Backend.Exceptions.GeneralException;
import com.example.Splitwise_Backend.Exceptions.UserNotFoundException;
import com.example.Splitwise_Backend.Expenses.Entity.Expenses;
import com.example.Splitwise_Backend.Expenses.Repository.ExpensesRepo;
import com.example.Splitwise_Backend.PaymentSplit.DTO.PaymentSplitDTO;
import com.example.Splitwise_Backend.PaymentSplit.Entity.PaymentSplit;
import com.example.Splitwise_Backend.PaymentSplit.Repository.PaymentSplitRepo;
import com.example.Splitwise_Backend.Users.Entity.Users;
import com.example.Splitwise_Backend.Users.Repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentSplitServiceImplementation implements PaymentSplitService
{
    private final PaymentSplitRepo paymentSplitRepo;
    private final UsersRepo usersRepo;
    private final ExpensesRepo expensesRepo;

    @Autowired
    public PaymentSplitServiceImplementation(PaymentSplitRepo paymentSplitRepo, UsersRepo usersRepo, ExpensesRepo expensesRepo)
    {
        this.paymentSplitRepo = paymentSplitRepo;
        this.usersRepo = usersRepo;
        this.expensesRepo = expensesRepo;
    }

    @Override
    public PaymentSplit savePaymentSplit(int userId, int expenseId, double amountPaid)
    {
        Optional<Users> userWhoPays = usersRepo.findById(userId);
        if(userWhoPays.isPresent())
        {
            Optional<Expenses> expenseInUserPaid = expensesRepo.findById(expenseId);
            if(expenseInUserPaid.isPresent())
            {
                PaymentSplit paymentRecorded = new PaymentSplit();
                paymentRecorded.setExpenses(expenseInUserPaid.get());
                paymentRecorded.setUsers(userWhoPays.get());
                paymentRecorded.setAmountPaid(amountPaid);
                return paymentSplitRepo.save(paymentRecorded);
            }
            else
            {
                throw new ExpenseNotFoundException("Expense Not Found");
            }
        }
        else
        {
            throw new UserNotFoundException("User Not Found");
        }
    }

    @Override
    public List<PaymentSplitDTO> paymentDoneByUser(int userId)
    {
        Optional<Users> useWhoDonePayments= usersRepo.findById(userId);
        if(useWhoDonePayments.isPresent())
        {
            Optional<List<PaymentSplit>> paymentsDoneByUser = paymentSplitRepo.findByUsers_Id(userId);
            if(paymentsDoneByUser.isPresent())
            {
                List<PaymentSplitDTO> paymentSplitDTOS = new ArrayList<>();
                for(PaymentSplit split : paymentsDoneByUser.get())
                {
                    paymentSplitDTOS.add(new PaymentSplitDTO(split.getId(), split.getAmountPaid(),split.getExpenses().getId(),split.getExpenses().getComment(), split.getExpenses().getGroups().getGroupName(),split.getUsers().getId()));
                }
                return paymentSplitDTOS;
            }
            else
            {
                throw new GeneralException("No Payments Done By User");
            }
        }
        else
        {
            throw new UserNotFoundException("User Not Found");
        }
    }

    @Override
    public String deletePaymentSplit(int expenseId)
    {
        Optional<Expenses> expenseToBeFound = expensesRepo.findById(expenseId);
        if(expenseToBeFound.isPresent())
        {
            String result = "Payments Deleted For Expense Id : " + expenseId;
            Optional<List<PaymentSplit>> expenseWhosePaymentsDeleted= paymentSplitRepo.findByExpenses_Id(expenseId);
            if (expenseWhosePaymentsDeleted.isPresent())
            {
                for (PaymentSplit p : expenseWhosePaymentsDeleted.get())
                {
                    result.concat("\n").concat(p.toString());
                    paymentSplitRepo.delete(p);
                }
                return result;
            }
            else
            {
                throw new GeneralException("Empty List");
            }
        }
        else
        {
            throw new UserNotFoundException("User Not Found");
        }
    }

    @Override
    public List<PaymentSplitDTO> paymentDoneForExpense(int expenseId)
    {
        Optional<Expenses> expenseToBeFound = expensesRepo.findById(expenseId);
        if(expenseToBeFound.isPresent())
        {
            Optional<List<PaymentSplit>> expenseWhosePaymentsDone= paymentSplitRepo.findByExpenses_Id(expenseId);
            if(expenseWhosePaymentsDone.isPresent())
            {
                List<PaymentSplitDTO> paymentSplitDTOS = new ArrayList<>();
                for(PaymentSplit split : expenseWhosePaymentsDone.get())
                {
                    paymentSplitDTOS.add(new PaymentSplitDTO(split.getId(), split.getAmountPaid(),split.getExpenses().getId(),split.getExpenses().getComment(), split.getExpenses().getGroups().getGroupName(),split.getUsers().getId()));
                }
                return paymentSplitDTOS;
            }
            else
            {
                throw new GeneralException("No Payments Done For Expense");
            }
        }
        else
        {
            throw new UserNotFoundException("User Not Found");
        }
    }
}
