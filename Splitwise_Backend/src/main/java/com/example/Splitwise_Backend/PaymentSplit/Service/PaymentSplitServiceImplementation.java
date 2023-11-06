package com.example.Splitwise_Backend.PaymentSplit.Service;
import com.example.Splitwise_Backend.Expenses.Entity.Expenses;
import com.example.Splitwise_Backend.Expenses.Repository.ExpensesRepo;
import com.example.Splitwise_Backend.PaymentSplit.Entity.PaymentSplit;
import com.example.Splitwise_Backend.PaymentSplit.Repository.PaymentSplitRepo;
import com.example.Splitwise_Backend.Users.Entity.Users;
import com.example.Splitwise_Backend.Users.Repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
                throw new RuntimeException("Expense Not Found");
            }
        }
        else
        {
            throw new RuntimeException("User Not Found");
        }
    }

    @Override
    public List<PaymentSplit> paymentDoneByUser(int userId)
    {
        Optional<Users> useWhoDonePayments= usersRepo.findById(userId);
        if(useWhoDonePayments.isPresent())
        {
            Optional<List<PaymentSplit>> paymentsDoneByUser = paymentSplitRepo.findByUsers_Id(userId);
            return paymentsDoneByUser.get();
        }
        else
        {
            throw new RuntimeException("User Not Found");
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
                throw new RuntimeException("Empty List");
            }
        }
        else
        {
            throw new RuntimeException("User Not Found");
        }
    }

    @Override
    public List<PaymentSplit> paymentDoneForExpense(int expenseId)
    {
        Optional<Expenses> expenseToBeFound = expensesRepo.findById(expenseId);
        if(expenseToBeFound.isPresent())
        {
            Optional<List<PaymentSplit>> expenseWhosePaymentsDone= paymentSplitRepo.findByExpenses_Id(expenseId);
            return expenseWhosePaymentsDone.get();
        }
        else
        {
            throw new RuntimeException("User Not Found");
        }
    }
}
