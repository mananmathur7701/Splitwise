package com.example.Splitwise_Backend.MathematicalData.Service;

import com.example.Splitwise_Backend.DTO.LedgerDTO;

import java.util.ArrayList;
import java.util.List;

public interface MathematicalDataService {
    public ArrayList<String> dataToBeSentOnDashboard(int userId);
    public List<LedgerDTO> ledgerData(int userId);
    public float AmountUserOweOrOwes(int payerId, int payeeId);
}
