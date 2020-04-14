package bank.model;

import bank.connection.DBConnection;
import bank.entity.Account;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountModel {
    public List<Account> getAccountsByClientId(String clientId) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String query = "select * from account where client_id = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, clientId);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()){
            String id = rs.getString("id");
            BigDecimal amount = rs.getBigDecimal("amount");
            String accCode = rs.getString("acc_code");
            Account account = new Account(UUID.fromString(id),clientId,amount,accCode);
            accounts.add(account);
        }
        return accounts;
    }

    public void updateAccount(Account account) throws SQLException {
        String query = "update account set client_id=?, amount=?, acc_code=? where id=?";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(query);

        stmt.setString(1, account.getClientId());
        stmt.setBigDecimal(2, account.getAmount());
        stmt.setString(3, account.getAccCode());
        stmt.setString(4, account.getId().toString());
        stmt.executeUpdate();
        stmt.close();
    }

    public void insertAccount(Account account) throws SQLException {
        String query = "insert into account values(?,?,?,?)";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(query);

        stmt.setString(1, account.getId().toString());
        stmt.setString(2, account.getClientId());
        stmt.setBigDecimal(3, account.getAmount());
        stmt.setString(4, account.getAccCode());
        stmt.execute();
        stmt.close();
    }
}
