package com.example.Splitwise_Backend.MathematicalData.Controller;

import com.example.Splitwise_Backend.DTO.LedgerDTO;
import com.example.Splitwise_Backend.MathematicalData.Service.MathematicalDataServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MathematicalDataController
{
    private final MathematicalDataServiceImplementation mathematicalDataServiceImplementation;

    @Autowired
    public MathematicalDataController(MathematicalDataServiceImplementation mathematicalDataServiceImplementation) {
        this.mathematicalDataServiceImplementation = mathematicalDataServiceImplementation;
    }

    @GetMapping("/BalancesOfUsers/{id}")
    @CrossOrigin("http://localhost:4200")
    public ArrayList<String> dashboardData(@PathVariable int id)
    {
        return mathematicalDataServiceImplementation.dataToBeSentOnDashboard(id);
    }

    @GetMapping("/LedgerOfUser/{id}")
    @CrossOrigin("http://localhost:4200")
    public List<LedgerDTO> ledgerData(@PathVariable int id)
    {
        return mathematicalDataServiceImplementation.ledgerData(id);
    }
}
