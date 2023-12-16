package com.example.Splitwise_Backend.Expenses.Service;
import com.example.Splitwise_Backend.DTO.ExpenseInfoDTO;
import com.example.Splitwise_Backend.DTO.SplitData;
import com.example.Splitwise_Backend.Exceptions.ExpenseNotFoundException;
import com.example.Splitwise_Backend.Exceptions.GeneralException;
import com.example.Splitwise_Backend.Exceptions.GroupNotFoundException;
import com.example.Splitwise_Backend.Exceptions.UserNotFoundException;
import com.example.Splitwise_Backend.ExpenseSplit.Service.ExpenseSplitServiceImplementation;
import com.example.Splitwise_Backend.Expenses.DTO.ExpDTO;
import com.example.Splitwise_Backend.Expenses.DTO.expenseDTO;
import com.example.Splitwise_Backend.Expenses.Entity.Expenses;
import com.example.Splitwise_Backend.Expenses.Repository.ExpensesRepo;
import com.example.Splitwise_Backend.Groups.Entity.Groups;
import com.example.Splitwise_Backend.Groups.Repository.GroupsRepo;
import com.example.Splitwise_Backend.PaymentSplit.Service.PaymentSplitServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Hello
@Service
public class EspensesServiceImplementation implements ExpensesService{

    private final ExpensesRepo expensesRepo;
    private final GroupsRepo groupsRepo;
    private final PaymentSplitServiceImplementation paymentSplitServiceImplementation;
    private final ExpenseSplitServiceImplementation expenseSplitServiceImplementation;

    @Autowired
    public EspensesServiceImplementation(ExpensesRepo expensesRepo, GroupsRepo groupsRepo, PaymentSplitServiceImplementation paymentSplitServiceImplementation, ExpenseSplitServiceImplementation expenseSplitServiceImplementation) {
        this.expensesRepo = expensesRepo;
        this.groupsRepo = groupsRepo;
        this.paymentSplitServiceImplementation = paymentSplitServiceImplementation;
        this.expenseSplitServiceImplementation = expenseSplitServiceImplementation;
    }



    @Override
    public String addExpense(ExpenseInfoDTO expenseInfoDTO)
    {
        Optional<Groups> groupOfExpense = groupsRepo.findById(expenseInfoDTO.getGroupId());
        if(groupOfExpense.isPresent())
        {
            float sumOfAmount=0;
            Expenses newExpenses = new Expenses();
            newExpenses.setGroups(groupOfExpense.get());
            newExpenses.setComment(expenseInfoDTO.getComment());
            newExpenses.setAmountPaid(expenseInfoDTO.getAmountPaid());
            newExpenses.setSpentAt(expenseInfoDTO.getSpentAt());
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
                        paymentSplitServiceImplementation.savePaymentSplit(data.getUserId(), expenseId, data.getAmount());
                    }

//               Ab  Chadhega Who Owes How Much Ka Data
                    List<SplitData> usersWhoOwe = expenseInfoDTO.getSettlement();


                    // Create MaangneWaale and DeneWaale lists
                    List<SplitData> maangneWaale = new ArrayList<>();
                    List<SplitData> deneWaale = new ArrayList<>();

                    for (SplitData payee : usersWhoPaidWithAmount) {
                        boolean found = false;
                        for (SplitData settlement : usersWhoOwe) {
                            if (settlement.getUserId() == payee.getUserId()) {
                                float difference = payee.getAmount() - settlement.getAmount();
                                if (difference > 0) {
                                    maangneWaale.add(new SplitData(payee.getUserId(), difference));
                                } else if (difference < 0) {
                                    deneWaale.add(new SplitData(payee.getUserId(), -difference));
                                }
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            maangneWaale.add(payee);
                        }
                    }

                    for (SplitData settlement : usersWhoOwe) {
                        boolean found = false;
                        for (SplitData payee : usersWhoPaidWithAmount) {
                            if (settlement.getUserId()==payee.getUserId()) {
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            deneWaale.add(settlement);
                        }
                    }

//

                    // Calculate the settlement amounts
                    for (SplitData maangne : maangneWaale) {
                        for (SplitData dene : deneWaale) {
                            if (maangne.getUserId()!=dene.getUserId()) {
                                float amountToSettle = Math.min(maangne.getAmount(), dene.getAmount());
                                System.out.println(maangne.getUserId() + " Needs To Give " + amountToSettle + " To " + dene.getUserId());
                                expenseSplitServiceImplementation.saveExpenseSplit(dene.getUserId(),maangne.getUserId(),groupOfExpense.get().getId(), expenseId,amountToSettle);
                                maangne.setAmount(maangne.getAmount() - amountToSettle);
                                dene.setAmount(dene.getAmount() - amountToSettle);
                            }
                        }
                    }
                    return ("Sab Data Chadha Diya Maalik");

                }
                else
                {
                    throw new GeneralException("Amount Mismatched Of Payment. Please Check Either Amount Paid Is More Than Shares Given By User. Or User Has Entered Wrong Shares As It Exceeds Amount");
                }
            }
            else
            {
                throw new UserNotFoundException("No User List Found");
            }
        }
        else
        {
            throw new GroupNotFoundException("Group Not Found");
        }
    }

    @Override
    public String deleteExpense(int expenseId) {
        Optional<Expenses> expenseToBeDeleted = expensesRepo.findById(expenseId);
        if(expenseToBeDeleted.isPresent())
        {
            String prompt = "Expense deleted";
            expenseSplitServiceImplementation.deleteExpenseSplit(expenseId);
            paymentSplitServiceImplementation.deletePaymentSplit(expenseId);
            expensesRepo.deleteById(expenseId);
            return prompt;
        }
        else
        {
            throw new ExpenseNotFoundException("Expense does not exist");
        }
    }


    @Override
    public List<ExpDTO> showAllGroupExpense(int groupId) {
        Optional<List<Expenses>> exp = expensesRepo.findByGroups_Id(groupId);
        if(exp.isPresent())
        {
            List<ExpDTO> allExpOfGroup = new ArrayList<>();
            for (Expenses e : exp.get())
            {
                allExpOfGroup.add(new ExpDTO(e.getId(),e.getSpentAt(),e.getAmountPaid(),e.getComment(),e.getGroups().getGroupName()));
            }
            return allExpOfGroup;
        }
        else
        {
            throw new ExpenseNotFoundException("No expenses in the group Found");
        }
    }

    @Override
    public String editExpense(int expenseId,ExpenseInfoDTO expenseInfoDTO)
    {
       expenseSplitServiceImplementation.deleteExpenseSplit(expenseId);
       paymentSplitServiceImplementation.deletePaymentSplit(expenseId);
       Optional<Groups> groupOfUpdation = groupsRepo.findById(expenseInfoDTO.getGroupId());
       if(groupOfUpdation.isPresent())
       {
           Expenses expenseToBeUpdated = new Expenses();
           expenseToBeUpdated.setId(expenseId);
           expenseToBeUpdated.setAmountPaid(expenseInfoDTO.getAmountPaid());
           expenseToBeUpdated.setComment(expenseInfoDTO.getComment());
           expenseToBeUpdated.setGroups(groupOfUpdation.get());
           expenseToBeUpdated.setSpentAt(Timestamp.from(Instant.now()));

           float sumOfAmount = 0;
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
                       paymentSplitServiceImplementation.savePaymentSplit(data.getUserId(), expenseId, data.getAmount());
                   }

//               Ab  Chadhega Who Owes How Much Ka Data
                   List<SplitData> usersWhoOwe = expenseInfoDTO.getSettlement();


                   // Create MaangneWaale and DeneWaale lists
                   List<SplitData> maangneWaale = new ArrayList<>();
                   List<SplitData> deneWaale = new ArrayList<>();

                   for (SplitData payee : usersWhoPaidWithAmount) {
                       boolean found = false;
                       for (SplitData settlement : usersWhoOwe) {
                           if (settlement.getUserId() == payee.getUserId()) {
                               float difference = payee.getAmount() - settlement.getAmount();
                               if (difference > 0) {
                                   maangneWaale.add(new SplitData(payee.getUserId(), difference));
                               } else if (difference < 0) {
                                   deneWaale.add(new SplitData(payee.getUserId(), -difference));
                               }
                               found = true;
                               break;
                           }
                       }
                       if (!found) {
                           maangneWaale.add(payee);
                       }
                   }

                   for (SplitData settlement : usersWhoOwe) {
                       boolean found = false;
                       for (SplitData payee : usersWhoPaidWithAmount) {
                           if (settlement.getUserId()==payee.getUserId()) {
                               found = true;
                               break;
                           }
                       }
                       if (!found) {
                           deneWaale.add(settlement);
                       }
                   }

//

                   // Calculate the settlement amounts
                   for (SplitData maangne : maangneWaale) {
                       for (SplitData dene : deneWaale) {
                           if (maangne.getUserId()!=dene.getUserId()) {
                               float amountToSettle = Math.min(maangne.getAmount(), dene.getAmount());
                               System.out.println(maangne.getUserId() + " Needs To Give " + amountToSettle + " To " + dene.getUserId());
                               expenseSplitServiceImplementation.saveExpenseSplit(maangne.getUserId(),dene.getUserId(),groupOfUpdation.get().getId(), expenseId,amountToSettle);
                               maangne.setAmount(maangne.getAmount() - amountToSettle);
                               dene.setAmount(dene.getAmount() - amountToSettle);
                           }
                       }
                   }
                   return ("Sab Data Chadha Diya Maalik");

               }
               else
               {
                   throw new GeneralException("Amount Mismatched Of Payment. Please Check Either Amount Paid Is More Than Shares Given By User. Or User Has Entered Wrong Shares As It Exceeds Amount");
               }
           }
           else
           {
               throw new UserNotFoundException("No User List Found");
           }
       }
       else
       {
           throw new GroupNotFoundException("Group Not Found");
       }

    }

    @Override
    public ExpDTO expenseInfoById(int expenseId) {
        Optional<Expenses> expense = expensesRepo.findById(expenseId);
        if(expense.isPresent())
        {
            ExpDTO expDTO = new ExpDTO();
            expDTO.setId(expense.get().getId());
            expDTO.setSpentAt(expense.get().getSpentAt());
            expDTO.setComment(expense.get().getComment());
            expDTO.setAmountPaid(expense.get().getAmountPaid());
            expDTO.setGroupName(expense.get().getGroups().getGroupName());
            return expDTO;
        }
        else
        {
            throw new ExpenseNotFoundException("No expense found.");
        }
    }
}
