package com.example.Splitwise_Backend.Expenses.Controller;


import com.example.Splitwise_Backend.DTO.ExpenseInfoDTO;
import com.example.Splitwise_Backend.Expenses.Entity.Expenses;
import com.example.Splitwise_Backend.Expenses.Service.EspensesServiceImplementation;
import com.example.Splitwise_Backend.Expenses.Service.ExpensesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.Splitwise_Backend.Expenses.Service.EspensesServiceImplementation;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:4200")
public class ExpensesController {
    private final EspensesServiceImplementation expensesServiceImplementation;

    public ExpensesController(EspensesServiceImplementation expensesServiceImplementation) {
        this.expensesServiceImplementation = expensesServiceImplementation;
    }

    //    private  ExpensesService expensesService;
//    private ModelMapper modelMapper;


    @PostMapping("/addExpenseToGroup")
    @CrossOrigin("http://localhost:4200")
    public Expenses addExpense(@RequestBody ExpenseInfoDTO expenseInfoDTO)
    {
        return expensesServiceImplementation.addExpense(expenseInfoDTO);
//        ExpenseInfoDTO expenseToBeAdded = new ExpenseInfoDTO();
//
//        expenseToBeAdded.setGroupId(Integer.parseInt(data.get("groupId")));
//        expenseToBeAdded.setAmountPaid(Float.parseFloat(data.get("amountPaid")));
//        expenseToBeAdded.setComment(data.get("comment"));
//        expenseToBeAdded.set
//        float amountPaid = Float.parseFloat(data.get("amountPaid"));
//        String comment = data.get("comment");
//        Expenses expenses=  expensesService.addExpense(id,amountPaid,comment);
        // Call the service to save the new expense
        //return modelMapper.map(expenses, expenseDTO.class);
       // return ExpensesService.addExpense(expenses);
    }




//    @DeleteMapping("/deleteExpense/{id}")
//    @CrossOrigin("http://localhost:4200")
//    public String deleteExpense(@PathVariable int id)


    @GetMapping("/showAllGroupExpense/{groupId}")
    @CrossOrigin("http://localhost:4200")
    public List<Expenses> showExpenseDetail(@PathVariable int groupId)
    {
        return expensesServiceImplementation.showAllExpenses(groupId);
    }


//    @PostMapping("/editExpense/{id}")
//    @CrossOrigin("http://localhost:4200")
//    public Expenses editExpense(@PathVariable int id)


}

