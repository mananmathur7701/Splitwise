package com.example.Splitwise_Backend.MathematicalData.Service;

import com.example.Splitwise_Backend.DTO.LedgerDTO;
import com.example.Splitwise_Backend.Exceptions.GeneralException;
import com.example.Splitwise_Backend.Exceptions.UserNotFoundException;
import com.example.Splitwise_Backend.ExpenseSplit.Entity.ExpenseSplit;
import com.example.Splitwise_Backend.ExpenseSplit.Repository.ExpenseSplitRepo;
import com.example.Splitwise_Backend.ExpenseSplit.Service.ExpenseSplitServiceImplementation;
import com.example.Splitwise_Backend.Friends.Service.FriendsServiceImplementation;
import com.example.Splitwise_Backend.SquareOffTransactions.Entity.SquareOffTransactions;
import com.example.Splitwise_Backend.SquareOffTransactions.Repository.SquareOffTransactionsRepo;
import com.example.Splitwise_Backend.SquareOffTransactions.Service.SquareOffTransactionsServiceImplementation;
import com.example.Splitwise_Backend.Users.Entity.Users;
import com.example.Splitwise_Backend.Users.Repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MathematicalDataServiceImplementation implements MathematicalDataService
{
    private final ExpenseSplitServiceImplementation expenseSplitServiceImplementation;
    private final SquareOffTransactionsServiceImplementation squareOffTransactionsServiceImplementation;
    private final FriendsServiceImplementation friendsServiceImplementation;
    private final UsersRepo usersRepo;
    private final ExpenseSplitRepo expenseSplitRepo;
    private final SquareOffTransactionsRepo squareOffTransactionsRepo;

    @Autowired
    public MathematicalDataServiceImplementation(ExpenseSplitServiceImplementation expenseSplitServiceImplementation, SquareOffTransactionsServiceImplementation squareOffTransactionsServiceImplementation, FriendsServiceImplementation friendsServiceImplementation,
                                                 UsersRepo usersRepo, ExpenseSplitRepo expenseSplitRepo,
                                                 SquareOffTransactionsRepo squareOffTransactionsRepo) {
        this.expenseSplitServiceImplementation = expenseSplitServiceImplementation;
        this.squareOffTransactionsServiceImplementation = squareOffTransactionsServiceImplementation;
        this.friendsServiceImplementation = friendsServiceImplementation;
        this.usersRepo = usersRepo;
        this.expenseSplitRepo = expenseSplitRepo;
        this.squareOffTransactionsRepo = squareOffTransactionsRepo;
    }

    @Override
    public ArrayList<String> dataToBeSentOnDashboard(int userId) {
        ArrayList<String> data = new ArrayList<>();
        float paisaLena = expenseSplitServiceImplementation.totalAmountJoLenaHaiByPaidToId(userId)-squareOffTransactionsServiceImplementation.totalAmountByPayerId(userId);
        float paisaDena = expenseSplitServiceImplementation.totalAmountJoDenaHaiByPayerId(userId)-squareOffTransactionsServiceImplementation.totalAmountByPaidToId(userId);
        float balance = paisaLena-paisaDena;

        data.add(Float.toString(paisaLena));
        data.add(Float.toString(paisaDena));
        data.add(Float.toString(balance));
        return data;
    }

    @Override
    public List<LedgerDTO> ledgerData(int userId)
    {
        List<LedgerDTO> friendsLedgerData = new ArrayList<>();
        Optional<Users> user = usersRepo.findById(userId);
        if(user.isPresent())
        {
            List<Integer> idOfUsersFriend = friendsServiceImplementation.idOfFriendsOfUsers(userId);
            System.out.println(idOfUsersFriend);
            for(Integer id : idOfUsersFriend)
            {
                Optional<Users> friend= usersRepo.findById(id);
                if (friend.isPresent())
                {
                    LedgerDTO ledgerDTO = new LedgerDTO();
                    String friendName =  friend.get().getFirstName()+" "+friend.get().getLastName();
                    float amount = AmountUserOweOrOwes(userId,id);
                    ledgerDTO.setName(friendName);
                    ledgerDTO.setAmount(amount);
                    friendsLedgerData.add(ledgerDTO);
                }
            }
            return friendsLedgerData;
        }
        else
        {
            throw new UserNotFoundException("User Not Found");
        }
    }

    @Override
    public float AmountUserOweOrOwes(int payerId, int payeeId)
    {
        Optional<Users> payer = usersRepo.findById(payerId);
        if(payer.isPresent())
        {
            Optional<Users> payee= usersRepo.findById(payeeId);
            if(payee.isPresent())
            {


                //Amount Jaha User Paise Diya
                float amountUserPaisaDiya =0;
                Optional<List<ExpenseSplit>> ListOfExpensesWhereUserPaid = expenseSplitRepo.findByPayerId(payer.get());
                if(ListOfExpensesWhereUserPaid.isPresent())
                {
                    for(ExpenseSplit exp: ListOfExpensesWhereUserPaid.get())
                    {
                        if(exp.getPayedToId().getId()==payeeId)
                        {
                            amountUserPaisaDiya=exp.getShareAmount();
                        }
                    }
                }
                else
                {
                    return amountUserPaisaDiya;
                }

                 //Amount Jaha User Ko Paisa Dena Hai
                float amountUserPaisaDenaHai =0;
                Optional<List<ExpenseSplit>> ListOfExpensesWhereUserHasShare = expenseSplitRepo.findByPayerId(payee.get());
                if(ListOfExpensesWhereUserHasShare.isPresent())
                {
                    for(ExpenseSplit exp: ListOfExpensesWhereUserHasShare.get())
                    {
                        if(exp.getPayedToId().getId()==payerId)
                        {
                            amountUserPaisaDenaHai=exp.getShareAmount();
                        }
                    }
                }
                else
                {
                    return amountUserPaisaDenaHai;
                }

                // SquareOff Mai User Kitna Paisa Diya Hai
                float amountUserPaisaMaangtaHai=0;
                Optional<List<SquareOffTransactions>> whereUserPaisaMaangtaHai= squareOffTransactionsRepo.findByPayerId(payer.get());
                if(whereUserPaisaMaangtaHai.isPresent())
                {
                    for(SquareOffTransactions squareOffTransactions: whereUserPaisaMaangtaHai.get())
                    {
                        if (squareOffTransactions.getPayerId().getId()==payeeId)
                        {
                            amountUserPaisaMaangtaHai=(float) squareOffTransactions.getAmount();
                        }
                    }
                }
                else
                {
                    return amountUserPaisaMaangtaHai;
                }

                // Square Off Mai User Kitna Paisa Liya Hai
                float amountUserPaisaDenaMaangtaHai=0;
                Optional<List<SquareOffTransactions>> whereUserPaisaDenaMaangtaHai= squareOffTransactionsRepo.findByPayerId(payee.get());
                if(whereUserPaisaDenaMaangtaHai.isPresent())
                {
                    for(SquareOffTransactions squareOffTransactions: whereUserPaisaDenaMaangtaHai.get())
                    {
                        if (squareOffTransactions.getPayerId().getId()==payerId)
                        {
                            amountUserPaisaDenaMaangtaHai=(float) squareOffTransactions.getAmount();
                        }
                    }
                }
                else
                {
                    return amountUserPaisaDenaMaangtaHai;
                }


                float totalamount = (amountUserPaisaDiya-amountUserPaisaDenaHai)+(amountUserPaisaMaangtaHai-amountUserPaisaDenaMaangtaHai);
                return totalamount;

            }
           else
           {
                throw new UserNotFoundException("Payee Not Found");}
        }
        else
        {
            throw new UserNotFoundException("Payer Not Found");
        }
    }
}
