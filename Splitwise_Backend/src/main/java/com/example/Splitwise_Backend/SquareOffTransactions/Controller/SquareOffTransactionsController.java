package com.example.Splitwise_Backend.SquareOffTransactions.Controller;

import com.example.Splitwise_Backend.SquareOffTransactions.DTO.SquareOffDTO;
import com.example.Splitwise_Backend.SquareOffTransactions.Entity.SquareOffTransactions;
import com.example.Splitwise_Backend.SquareOffTransactions.Service.SquareOffTransactionsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
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
    public SquareOffDTO addSquareOffTransactions(@RequestBody Map<String,String>data)
    {
        float amount = Float.parseFloat(data.get("amount"));
        int payerId = Integer.parseInt(data.get("payerId"));
        int payeeId = Integer.parseInt(data.get("payeeId"));
        SquareOffTransactions s =squareOffTransactionsServiceImplementation.addSquareOffTransaction(amount,payerId,payeeId);
        SquareOffDTO squareOffDTO = new SquareOffDTO();
        squareOffDTO.setId(s.getId());
        squareOffDTO.setAmount(s.getAmount());
        squareOffDTO.setTime((Timestamp) s.getTime());
        squareOffDTO.setPayerId(s.getPayerId().getId());
        squareOffDTO.setPayerEmail(s.getPayerId().getEmail());
        squareOffDTO.setPayedToId(s.getPayedToId().getId());
        squareOffDTO.setPayedToEmail(s.getPayedToId().getEmail());
        return squareOffDTO;
    }

    @DeleteMapping("/deleteSquareOffTransaction/{id}")
    @CrossOrigin("http://localhost:4200")
    public String DeleteSquareOffTransactions(@PathVariable int id)
    {
        return squareOffTransactionsServiceImplementation.deleteSquareOffTransactions(id);
    }

    @PostMapping("/updateSquareOffTransaction/{id}")
    @CrossOrigin("http://localhost:4200")
    public SquareOffDTO updateSquareOffTransactions(@PathVariable int id,@RequestBody Map<String,String>data)
    {
        float amount = Float.parseFloat(data.get("amount"));
        int payerId = Integer.parseInt(data.get("payerId"));
        int payeeId = Integer.parseInt(data.get("payeeId"));
        SquareOffTransactions s= squareOffTransactionsServiceImplementation.updateSquareOffTransactions(id,amount,payerId,payeeId);
        SquareOffDTO squareOffDTO = new SquareOffDTO();
        squareOffDTO.setId(s.getId());
        squareOffDTO.setAmount(s.getAmount());
        squareOffDTO.setTime((Timestamp) s.getTime());
        squareOffDTO.setPayerId(s.getPayerId().getId());
        squareOffDTO.setPayerEmail(s.getPayerId().getEmail());
        squareOffDTO.setPayedToId(s.getPayedToId().getId());
        squareOffDTO.setPayedToEmail(s.getPayedToId().getEmail());
        return squareOffDTO;
    }

    @GetMapping("/showTransactionById/{id}")
    @CrossOrigin("http://localhost:4200")
    public SquareOffDTO showTransactionById(@PathVariable int id)
    {
        SquareOffTransactions s = squareOffTransactionsServiceImplementation.viewSquareOffTransactions(id);
        SquareOffDTO squareOffDTO = new SquareOffDTO();
        squareOffDTO.setId(s.getId());
        squareOffDTO.setAmount(s.getAmount());
        squareOffDTO.setTime((Timestamp) s.getTime());
        squareOffDTO.setPayerId(s.getPayerId().getId());
        squareOffDTO.setPayerEmail(s.getPayerId().getEmail());
        squareOffDTO.setPayedToId(s.getPayedToId().getId());
        squareOffDTO.setPayedToEmail(s.getPayedToId().getEmail());
        return squareOffDTO;
    }

//
//    @PostMapping("/showSquareOffTransactionBetweenTwoPerson")
//    @CrossOrigin("http://localhost:4200")
//    public SquareOffTransactions showSquareOffTransactionsBetweenPersons( )
//    {
//
//    }
}
