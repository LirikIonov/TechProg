package bank.model;

import bank.connection.DBConnection;
import bank.entity.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UsersModel {
    public Users getUserByPhone(String phone) throws SQLException {
        String query = "select * from users where phone = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, phone);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            String address = rs.getString("address");
            UUID id = UUID.fromString(rs.getString("id"));
            String login = rs.getString("login");
            String password = rs.getString("password");
            return new Users(id, login, password, address, phone);
        } else {
            return null;
        }
    }

    public Users getUserByLogin(String login) throws SQLException {
        String query = "select * from users where login = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, login);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            String address = rs.getString("address");
            UUID id = UUID.fromString(rs.getString("id"));
            String phone = rs.getString("phone");
            String password = rs.getString("password");
            return new Users(id, login, password, address, phone);
        } else {
            return null;
        }
    }

    public void insertUser(Users users) throws SQLException {
        String query = "insert into users values(?,?,?,?,?)";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(query);

        stmt.setString(1, users.getId().toString());
        stmt.setString(2, users.getLogin());
        stmt.setString(3, users.getPassword());
        stmt.setString(4, users.getAddress());
        stmt.setString(5, users.getPhone());

        stmt.execute();
        stmt.close();
    }
}
