package com.example.Splitwise_Backend.Expenses.Controller;


import com.example.Splitwise_Backend.ExpenseSplit.Service.ExpenseSplitServiceImplementation;
import com.example.Splitwise_Backend.Expenses.DTO.expenseDTO;
import com.example.Splitwise_Backend.Expenses.Entity.Expenses;
import com.example.Splitwise_Backend.Expenses.Service.ExpensesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("http://localhost:4200")
public class ExpensesController {

    private  ExpensesService expensesService;


    private ModelMapper modelMapper;

    @Autowired
    public ExpensesController(ExpensesService expensesService,ModelMapper modelMapper) {
        this.expensesService = expensesService;
        this.modelMapper=modelMapper;
    }

    @PostMapping("/addExpenseToGroup")
    @CrossOrigin("http://localhost:4200")
    public expenseDTO addExpense(@RequestBody Map<String,String> data)
    {
        int id = Integer.parseInt(data.get("groupId"));
        float amountPaid = Float.parseFloat(data.get("amountPaid"));
        String comment = data.get("comment");
        Expenses expenses=  expensesService.addExpense(id,amountPaid,comment);
        // Call the service to save the new expense
        return modelMapper.map(expenses, expenseDTO.class);
       // return ExpensesService.addExpense(expenses);
    }




//    @DeleteMapping("/deleteExpense/{id}")
//    @CrossOrigin("http://localhost:4200")
//    public String deleteExpense(@PathVariable int id)
//
//
//    @GetMapping("/showAllExpense/{id}")
//    @CrossOrigin("http://localhost:4200")
//    public Expenses showExpenseDetail(@PathVariable int id)
//    {
//        return ExpenseSplitServiceImplementation.showAllExpenses(id);
//    }
//
//
//    @PostMapping("/editExpense/{id}")
//    @CrossOrigin("http://localhost:4200")
//    public Expenses editExpense(@PathVariable int id)


}

