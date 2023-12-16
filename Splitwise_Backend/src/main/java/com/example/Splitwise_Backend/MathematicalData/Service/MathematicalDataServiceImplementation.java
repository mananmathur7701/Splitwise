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
//        float paisaLena = (expenseSplitServiceImplementation.totalAmountJoLenaHaiByPaidToId(userId)-squareOffTransactionsServiceImplementation.totalAmountByPayerId(userId));
//        float paisaDena = expenseSplitServiceImplementation.totalAmountJoDenaHaiByPayerId(userId)-squareOffTransactionsServiceImplementation.totalAmountByPaidToId(userId);
//        float balance = paisaLena-paisaDena;
        float paisaLena =0;
        float paisaDena =0;
        float balance =0;

        List<LedgerDTO> allTransData = ledgerData(userId);
        for(LedgerDTO l : allTransData){
            if(l.getAmount()>=0){
                paisaLena = paisaLena+l.getAmount();
            }else if (l.getAmount()<0){
                paisaDena = paisaDena+l.getAmount();
            }
        }
        balance = paisaLena+paisaDena;
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
                    String mailId = friend.get().getEmail();
                    ledgerDTO.setId(id);
                    ledgerDTO.setName(friendName);
                    ledgerDTO.setMailId(mailId);
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
    public float AmountUserOweOrOwes(int amritId, int mananId)
    {
        Optional<Users> amrit = usersRepo.findById(amritId);
        if(amrit.isPresent())
        {
            Optional<Users> manan= usersRepo.findById(mananId);
            if(manan.isPresent())
            {


                //Amount Jaha Amrit Paise Diya
                float amountUserPaisaDiya =0;
                Optional<List<ExpenseSplit>> ListOfExpensesWhereUserPaid = expenseSplitRepo.findByPayerId(manan.get());
                if(ListOfExpensesWhereUserPaid.isPresent())
                {
                    for(ExpenseSplit exp: ListOfExpensesWhereUserPaid.get())
                    {
                        if(exp.getPayedToId().getId()==amritId)
                        {
                            amountUserPaisaDiya=amountUserPaisaDiya+exp.getShareAmount();
                        }
                    }
                }
                else
                {
                    return amountUserPaisaDiya;
                }

                // Square Off Mai User Kitna Paisa Liya Hai
                float amountUserPaisaDenaMaangtaHai=0;
                Optional<List<SquareOffTransactions>> whereUserPaisaDenaMaangtaHai= squareOffTransactionsRepo.findByPayerId(manan.get());
                if(whereUserPaisaDenaMaangtaHai.isPresent())
                {
                    for(SquareOffTransactions squareOffTransactions: whereUserPaisaDenaMaangtaHai.get())
                    {
                        if (squareOffTransactions.getPayedToId().getId()==amritId)
                        {
                            amountUserPaisaDenaMaangtaHai=amountUserPaisaDenaMaangtaHai+(float) squareOffTransactions.getAmount();
                        }
                    }
                }
                else
                {
                    return amountUserPaisaDenaMaangtaHai;
                }

            float amrit_ko_lena = amountUserPaisaDiya - amountUserPaisaDenaMaangtaHai;



                //Amount Jaha User Ko Paisa Dena Hai
                float amountUserPaisaDenaHai =0;
                Optional<List<ExpenseSplit>> ListOfExpensesWhereUserHasShare = expenseSplitRepo.findByPayerId(amrit.get());
                if(ListOfExpensesWhereUserHasShare.isPresent())
                {
                    for(ExpenseSplit exp: ListOfExpensesWhereUserHasShare.get())
                    {
                        if(exp.getPayedToId().getId()==mananId)
                        {
                            amountUserPaisaDenaHai=amountUserPaisaDenaHai+exp.getShareAmount();
                        }
                    }
                }
                else
                {
                    return amountUserPaisaDenaHai;
                }

                // SquareOff Mai User Kitna Paisa Diya Hai
                float amountUserPaisaMaangtaHai=0;
                Optional<List<SquareOffTransactions>> whereUserPaisaMaangtaHai= squareOffTransactionsRepo.findByPayerId(amrit.get());
                if(whereUserPaisaMaangtaHai.isPresent())
                {
                    for(SquareOffTransactions squareOffTransactions: whereUserPaisaMaangtaHai.get())
                    {
                        if (squareOffTransactions.getPayedToId().getId()==mananId)
                        {
                            amountUserPaisaMaangtaHai=amountUserPaisaMaangtaHai+(float) squareOffTransactions.getAmount();
                        }
                    }
                }
                else
                {
                    return amountUserPaisaMaangtaHai;
                }


                float amrit_ko_dena = amountUserPaisaDenaHai - amountUserPaisaMaangtaHai;

                float total_amount = amrit_ko_lena - amrit_ko_dena;
                return total_amount;


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
