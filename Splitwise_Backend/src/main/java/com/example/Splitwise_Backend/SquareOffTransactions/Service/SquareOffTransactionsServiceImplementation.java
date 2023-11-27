package com.example.Splitwise_Backend.SquareOffTransactions.Service;
import com.example.Splitwise_Backend.Exceptions.SquareOffTransactionNotFoundException;
import com.example.Splitwise_Backend.Exceptions.UserNotFoundException;
import com.example.Splitwise_Backend.ExpenseSplit.Repository.ExpenseSplitRepo;
import com.example.Splitwise_Backend.ExpenseSplit.Service.ExpenseSplitServiceImplementation;
import com.example.Splitwise_Backend.SquareOffTransactions.Entity.SquareOffTransactions;
import com.example.Splitwise_Backend.SquareOffTransactions.Repository.SquareOffTransactionsRepo;
import com.example.Splitwise_Backend.Users.Entity.Users;
import com.example.Splitwise_Backend.Users.Repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SquareOffTransactionsServiceImplementation implements SquareOffTransactionsService
{
    private final SquareOffTransactionsRepo squareOffTransactionsRepo;
    private final UsersRepo usersRepo;
    private final ExpenseSplitRepo expenseSplitRepo;
    private final ExpenseSplitServiceImplementation expenseSplitServiceImplementation;

    @Autowired
    public SquareOffTransactionsServiceImplementation(SquareOffTransactionsRepo squareOffTransactionsRepo,
                                                      UsersRepo usersRepo,
                                                      ExpenseSplitRepo expenseSplitRepo, ExpenseSplitServiceImplementation expenseSplitServiceImplementation) {
        this.squareOffTransactionsRepo = squareOffTransactionsRepo;
        this.usersRepo = usersRepo;
        this.expenseSplitRepo = expenseSplitRepo;
        this.expenseSplitServiceImplementation = expenseSplitServiceImplementation;
    }

    @Override
    public SquareOffTransactions addSquareOffTransaction(float amount, int payerId, int payedToId) {
        Optional<Users> payer = usersRepo.findById(payerId);
        if(payer.isPresent())
        {
            Optional<Users> payee = usersRepo.findById(payedToId);
            if (payee.isPresent())
            {
                SquareOffTransactions squareOfEntry = new SquareOffTransactions();
                squareOfEntry.setAmount(amount);
                squareOfEntry.setPayerId(payer.get());
                squareOfEntry.setPayedToId(payee.get());
                squareOfEntry.setTime(Timestamp.from(Instant.now()));
                return squareOffTransactionsRepo.save(squareOfEntry);
            }
            else
            {
                throw new UserNotFoundException("Payer Not Found");
            }
        }
        else
        {
            throw new UserNotFoundException("Payer Not Found");
        }
    }

    @Override
    public String deleteSquareOffTransactions(int transactionId)
    {
        Optional<SquareOffTransactions> transactionToBeDeleted =  squareOffTransactionsRepo.findById(transactionId);
        if(transactionToBeDeleted.isPresent())
        {
            String result = "Deleted Entry Is \n".concat(transactionToBeDeleted.get().toString());
            squareOffTransactionsRepo.deleteById(transactionId);
            return result;
        }
        else
        {
            throw new SquareOffTransactionNotFoundException("Transaction Id Not Found");
        }
    }

    @Override
    public SquareOffTransactions updateSquareOffTransactions(int transactionId, float amount, int payerId, int payedToId)
    {
        Optional<SquareOffTransactions> transactionToBeDeleted =  squareOffTransactionsRepo.findById(transactionId);
        if(transactionToBeDeleted.isPresent())
        {
            Optional<Users> payer = usersRepo.findById(payerId);
            if(payer.isPresent())
            {
                Optional<Users> payee = usersRepo.findById(payedToId);
                if (payee.isPresent())
                {
                    SquareOffTransactions squareOfEntry = new SquareOffTransactions();
                    squareOfEntry.setId(transactionId);
                    squareOfEntry.setAmount(amount);
                    squareOfEntry.setPayerId(payer.get());
                    squareOfEntry.setPayedToId(payee.get());
                    squareOfEntry.setTime(Timestamp.from(Instant.now()));
                    return squareOffTransactionsRepo.save(squareOfEntry);
                }
                else
                {
                    throw new UserNotFoundException("Payer Not Found");
                }
            }
            else
            {
                throw new UserNotFoundException("Payer Not Found");
            }
        }
        else
        {
            throw new SquareOffTransactionNotFoundException("Transaction Id Not Found");
        }
    }

    @Override
    public SquareOffTransactions viewSquareOffTransactions(int transactionId)
    {
        Optional<SquareOffTransactions> transactionToBeViewed=  squareOffTransactionsRepo.findById(transactionId);
        if(transactionToBeViewed.isPresent())
        {
            return transactionToBeViewed.get();
        }
        else
        {
            throw new SquareOffTransactionNotFoundException("Transaction Id Not Found");
        }
    }

    @Override
    public float totalAmountByPayerId(int payerId)
    {
        Optional<Users> payer = usersRepo.findById(payerId);
        if(payer.isPresent())
        {
            float totalAmount = 0;
            Optional<List<SquareOffTransactions>> totalPaisaDiya = squareOffTransactionsRepo.findByPayerId(payer.get());
            if (totalPaisaDiya.isPresent())
            {
                for (SquareOffTransactions sq: totalPaisaDiya.get())
                {
                    totalAmount+= (float) sq.getAmount();
                }
                return totalAmount;
            }
            else
            {
                return 0;
            }
        }
        else
        {
            throw new UserNotFoundException("User Not Found Who Paid");
        }

    }

    @Override
    public float totalAmountByPaidToId(int payedToId) {
        Optional<Users> payee = usersRepo.findById(payedToId);
        if(payee.isPresent())
        {
            float totalAmount = 0;
            Optional<List<SquareOffTransactions>> totalPaisaLiya = squareOffTransactionsRepo.findByPayedToId(payee.get());
            if (totalPaisaLiya.isPresent())
            {
                for (SquareOffTransactions sq: totalPaisaLiya.get())
                {
                    totalAmount+= (float) sq.getAmount();
                }
                return totalAmount;
            }
            else
            {
                return 0;
            }
        }
        else
        {
            throw new UserNotFoundException("User Not Found Who Paid");
        }
    }

    @Override
    public ArrayList<String> dataToBeSentOnDashboard(int userId) {
        ArrayList<String> data = new ArrayList<>();
        float paisaLena = expenseSplitServiceImplementation.totalAmountJoLenaHaiByPaidToId(userId)-totalAmountByPayerId(userId);
        float paisaDena = expenseSplitServiceImplementation.totalAmountJoDenaHaiByPayerId(userId)-totalAmountByPaidToId(userId);
        float balance = paisaLena-paisaDena;

        data.add(Float.toString(paisaLena));
        data.add(Float.toString(paisaDena));
        data.add(Float.toString(balance));
        return data;
    }
}
