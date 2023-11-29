package com.example.Splitwise_Backend.SquareOffTransactions.Controller;

import com.example.Splitwise_Backend.SquareOffTransactions.Entity.SquareOffTransactions;
import com.example.Splitwise_Backend.SquareOffTransactions.Service.SquareOffTransactionsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:4200")
public class SquareOffTransactionsController
{
    private final SquareOffTransactionsServiceImplementation squareOffTransactionsServiceImplementation;

    @Autowired
    public SquareOffTransactionsController(SquareOffTransactionsServiceImplementation squareOffTransactionsServiceImplementation) {
        this.squareOffTransactionsServiceImplementation = squareOffTransactionsServiceImplementation;
    }

//    @GetMapping("/BalancesOfUsers/{id}")
//    @CrossOrigin("http://localhost:4200")
//    public ArrayList<String> dashboardData(@PathVariable int id)
//    {
//        return squareOffTransactionsServiceImplementation.dataToBeSentOnDashboard(id);
//    }

    @PostMapping("/addSquareOffTransaction")
    @CrossOrigin("http://localhost:4200")
    public SquareOffTransactions addSquareOffTransactions(@RequestBody Map<String,String>data)
    {
        float amount = Float.parseFloat(data.get("amount"));
        int payerId = Integer.parseInt(data.get("payerId"));
        int payeeId = Integer.parseInt(data.get("payeeId"));
        return squareOffTransactionsServiceImplementation.addSquareOffTransaction(amount,payerId,payeeId);
    }

    @DeleteMapping("/deleteSquareOffTransaction/{id}")
    @CrossOrigin("http://localhost:4200")
    public String DeleteSquareOffTransactions(@PathVariable int id)
    {
        return squareOffTransactionsServiceImplementation.deleteSquareOffTransactions(id);
    }

    @PostMapping("/updateSquareOffTransaction/{id}")
    @CrossOrigin("http://localhost:4200")
    public SquareOffTransactions updateSquareOffTransactions(@PathVariable int id,@RequestBody Map<String,String>data)
    {
        float amount = Float.parseFloat(data.get("amount"));
        int payerId = Integer.parseInt(data.get("payerId"));
        int payeeId = Integer.parseInt(data.get("payeeId"));
        return squareOffTransactionsServiceImplementation.updateSquareOffTransactions(id,amount,payerId,payeeId);
    }

    @GetMapping("/showTransactionById/{id}")
    @CrossOrigin("http://localhost:4200")
    public SquareOffTransactions showTransactionById(@PathVariable int id)
    {
        return squareOffTransactionsServiceImplementation.viewSquareOffTransactions(id);
    }

//
//    @PostMapping("/showSquareOffTransactionBetweenTwoPerson")
//    @CrossOrigin("http://localhost:4200")
//    public SquareOffTransactions showSquareOffTransactionsBetweenPersons( )
//    {
//
//    }
}
