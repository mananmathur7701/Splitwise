package com.example.Splitwise_Backend.Expenses.Controller;


import com.example.Splitwise_Backend.Expenses.Entity.Expenses;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
public class ExpensesController {
    @PostMapping("/addExpense")
    @CrossOrigin("http://localhost:4200")
    public int addExpense(@RequestBody Expenses expense )



    @DeleteMapping("/deleteExpense/{id}")
    @CrossOrigin("http://localhost:4200")
    public String deleteExpense(@PathVariable int id)


    @GetMapping("/showAllExpense/{id}")
    @CrossOrigin("http://localhost:4200")
    public Expenses showExpenseDetail(@PathVariable int id)


    @PostMapping("/editExpense/{id}")
    @CrossOrigin("http://localhost:4200")
    public Expenses editExpense(@PathVariable int id)


}

