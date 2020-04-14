package bank.model;

import bank.connection.DBConnection;
import bank.entity.Operation;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OperationModel {
    public List<Operation> getOperationByFromAccountId(String accountId) throws SQLException {
        List<Operation> operations = new ArrayList<>();
        String query = "select * from operation where account_from = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, accountId);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()){
            String id = rs.getString("id");
            String currDate = rs.getString("curr_date");
            String currency = rs.getString("currency");
            String accountTo = rs.getString("account_to");
            BigDecimal amount = rs.getBigDecimal("amount");
            BigDecimal moneyBefore = rs.getBigDecimal("money_before");
            BigDecimal moneyAfter= rs.getBigDecimal("money_after");
            Operation operation = new Operation(UUID.fromString(id), currDate, currency, accountId,
                    accountTo, amount, moneyBefore, moneyAfter);
            operations.add(operation);
        }
        return operations;
    }

    public void insertOperation(Operation operation) throws SQLException {
        String query = "insert into operation values(?,?,?,?,?,?,?,?)";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(query);

        stmt.setString(1, operation.getId().toString());
        stmt.setString(2, operation.getDate());
        stmt.setString(3, operation.getCurrency());
        stmt.setString(4, operation.getAccountFrom());
        stmt.setString(5, operation.getAccountTo());
        stmt.setBigDecimal(6, operation.getAmount());
        stmt.setBigDecimal(7, operation.getMoneyBefore());
        stmt.setBigDecimal(8, operation.getMoneyAfter());

        stmt.execute();
        stmt.close();
    }
}
