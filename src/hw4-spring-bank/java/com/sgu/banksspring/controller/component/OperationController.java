package com.sgu.banksspring.controller.component;

import com.sgu.banksspring.entity.Operation;
import com.sgu.banksspring.model.OperationModel;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperationController {
    private OperationModel operationModel;

    public OperationController(OperationModel OperationModel) {
        this.operationModel = OperationModel;
    }

    public void addOperation(String date, String currency, Long accountFrom, Long accountTo, BigDecimal amount,
                             BigDecimal moneyBefore, BigDecimal moneyAfter) {
        Operation operation = new Operation(date, currency, accountFrom,
                accountTo, amount, moneyBefore, moneyAfter);
        operationModel.save(operation);
    }

    public List<String> getHistory(Long accountId) {
        List<Operation> operations = operationModel.findByAccountFrom(accountId);
        System.out.println(operations.size());
        List<String> result = new ArrayList<>();
        operations.forEach(op -> result.add(op.toString()));
        return result;
    }
}
