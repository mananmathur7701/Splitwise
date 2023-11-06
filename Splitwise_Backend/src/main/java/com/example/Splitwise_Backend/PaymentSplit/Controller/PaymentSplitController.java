package com.example.Splitwise_Backend.PaymentSplit.Controller;

import com.example.Splitwise_Backend.PaymentSplit.Entity.PaymentSplit;
import com.example.Splitwise_Backend.PaymentSplit.Service.PaymentSplitServiceImplementation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaymentSplitController
{
    private final PaymentSplitServiceImplementation paymentSplitServiceImplementation;

    public PaymentSplitController(PaymentSplitServiceImplementation paymentSplitServiceImplementation) {
        this.paymentSplitServiceImplementation = paymentSplitServiceImplementation;
    }

    @GetMapping("/showAllPaymentsDoneByUser/{id}")
    public List<PaymentSplit> allPaymentsOfUser(@PathVariable int id)
    {
        return paymentSplitServiceImplementation.paymentDoneByUser(id);
    }

    @GetMapping("/showAllPaymentsDoneOfExpense/{id}")
    public List<PaymentSplit> allPaymentsOfExpense(@PathVariable int id)
    {
        return paymentSplitServiceImplementation.paymentDoneForExpense(id);
    }
}
