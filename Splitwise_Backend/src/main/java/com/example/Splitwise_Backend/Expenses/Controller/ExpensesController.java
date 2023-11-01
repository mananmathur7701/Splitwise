package com.example.Splitwise_Backend.Expenses.Controller;


import com.example.Splitwise_Backend.Expenses.DTO.expenseDTO;
import com.example.Splitwise_Backend.Expenses.Entity.Expenses;
import com.example.Splitwise_Backend.Expenses.Service.ExpensesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/addExpense")
    @CrossOrigin("http://localhost:4200")
    public expenseDTO addExpense(@RequestBody Expenses expense )
    {
        Expenses expenses=  expensesService.addExpense(expense);
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
//
//
//    @PostMapping("/editExpense/{id}")
//    @CrossOrigin("http://localhost:4200")
//    public Expenses editExpense(@PathVariable int id)


}

