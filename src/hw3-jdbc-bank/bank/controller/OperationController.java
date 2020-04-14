package bank.controller;

import bank.entity.Operation;
import bank.model.OperationModel;
import exception.Trouble;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OperationController {
    private OperationModel OperationModel;

    public OperationController(OperationModel OperationModel) {
        this.OperationModel = OperationModel;
    }

    public void addOperation(String date, String currency, String accountFrom, String accountTo, BigDecimal amount,
                             BigDecimal moneyBefore, BigDecimal moneyAfter) throws Trouble {
        Operation operation = new Operation(UUID.randomUUID(), date, currency, accountFrom,
                accountTo, amount, moneyBefore, moneyAfter);
        try {
            OperationModel.insertOperation(operation);
        } catch (Exception ex) {
            throw new Trouble("Problem with database!", ex);
        }
    }

    public List<String> getHistory(String accountId) throws SQLException {
        List<Operation> operations = OperationModel.getOperationByFromAccountId(accountId);
        System.out.println(operations.size());
        List<String> result = new ArrayList<>();
        operations.forEach(op -> result.add(op.toString()));
        return result;
    }
}
