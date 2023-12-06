package com.example.Splitwise_Backend.ExpenseSplit.Controller;

import com.example.Splitwise_Backend.ExpenseSplit.DTO.ExpenseSplitDTO;
import com.example.Splitwise_Backend.ExpenseSplit.Entity.ExpenseSplit;
import com.example.Splitwise_Backend.ExpenseSplit.Service.ExpenseSplitServiceImplementation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class ExpenseSplitController
{
    private final ExpenseSplitServiceImplementation expenseSplitServiceImplementation;

    public ExpenseSplitController(ExpenseSplitServiceImplementation expenseSplitServiceImplementation) {
        this.expenseSplitServiceImplementation = expenseSplitServiceImplementation;
    }

    @GetMapping("/SharesOfGroup/{id}")
    @CrossOrigin("http://localhost:4200")
    public List<ExpenseSplitDTO> sharesOfGroup(@PathVariable int id)
    {
        return expenseSplitServiceImplementation.expenseSplitOfAllGroups(id);
    }

    @GetMapping("/SharesOfParticularTransaction/{id}")
    @CrossOrigin("http://localhost:4200")
    public List<ExpenseSplitDTO> sharesOfTransaction(@PathVariable int id)
    {
        return expenseSplitServiceImplementation.expenseSplitOfParticularExpenseId(id);
    }

    // ISme ek DTO aur bana kar try karna hai ki hum kis group mai kaisse kitne paise maangte hai to group wise kisse kitne paise maang rhe hai wo bhi aa sakta hai


    @GetMapping("/expenseSplitWhereUserNeedsToPay/{id}")
    @CrossOrigin("http://localhost:4200")
    public List<ExpenseSplitDTO> sharesToBePaid(@PathVariable int id)
    {
        return expenseSplitServiceImplementation.amountToBeGivenByYou(id);
    }
}
