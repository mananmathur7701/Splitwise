package com.example.Splitwise_Backend.Expenses.Controller;


import com.example.Splitwise_Backend.DTO.ExpenseInfoDTO;
import com.example.Splitwise_Backend.Expenses.DTO.ExpDTO;
import com.example.Splitwise_Backend.Expenses.DTO.expenseDTO;
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

    @PostMapping("/addExpenseToGroup")
    @CrossOrigin("http://localhost:4200")
    public String addExpense(@RequestBody ExpenseInfoDTO expenseInfoDTO)
    {
        return expensesServiceImplementation.addExpense(expenseInfoDTO);
    }

    @DeleteMapping("/deleteExpense/{id}")
    @CrossOrigin("http://localhost:4200")
    public String deleteExpense(@PathVariable int id)
    {
        return expensesServiceImplementation.deleteExpense(id);
    }

    @GetMapping("/showAllGroupExpense/{groupId}")
    @CrossOrigin("http://localhost:4200")
    public List<ExpDTO> showAllGroupExpense(@PathVariable int groupId)
    {
        return expensesServiceImplementation.showAllGroupExpense(groupId);
    }

    @GetMapping("/expenseInfoById/{Id}")
    @CrossOrigin("http://localhost:4200")
    public ExpDTO expenseInfoById(@PathVariable int Id)
    {
        return expensesServiceImplementation.expenseInfoById(Id);
    }

    @PostMapping("/editExpense/{id}")
    @CrossOrigin("http://localhost:4200")
    public String editExpense(@PathVariable int id,@RequestBody ExpenseInfoDTO expenseInfoDTO)
    {
        return expensesServiceImplementation.editExpense(id, expenseInfoDTO);
    }
}

