package com.example.Splitwise_Backend.PaymentSplit.Service;

import com.example.Splitwise_Backend.Expenses.Entity.Expenses;
import com.example.Splitwise_Backend.Expenses.Repository.ExpensesRepo;
import com.example.Splitwise_Backend.PaymentSplit.Entity.PaymentSplit;
import com.example.Splitwise_Backend.PaymentSplit.Repository.PaymentSplitRepo;
import com.example.Splitwise_Backend.Users.Entity.Users;
import com.example.Splitwise_Backend.Users.Repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentSplitServiceImplementation implements PaymentSplitService{
    private PaymentSplitRepo paymentSplitRepo;
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
}
