package bank.connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {
    private static java.sql.Connection connection;
    private static final String USER = "JAVA_COUR";
    private static final String PASSWORD = "5555";
    private static final String URL = "jdbc:oracle:thin:@//localhost:1521/orcl";

    private static final String accountTable = "CREATE TABLE account " +
            "(id VARCHAR(50) NOT NULL, " +
            "client_id VARCHAR(50) NOT NULL, " +
            "amount Decimal NOT NULL, " +
            "acc_code VARCHAR(50) NOT NULL, " +
            "PRIMARY KEY (id))";

    private static final String usersTable = "CREATE TABLE users " +
            "(id VARCHAR(50) NOT NULL, " +
            "login VARCHAR(50) NOT NULL, " +
            "password VARCHAR(50) NOT NULL, " +
            "address VARCHAR(50) NOT NULL, " +
            "phone VARCHAR(50) NOT NULL, " +
            "PRIMARY KEY (id))";

    private static final String operationTable = "CREATE TABLE operation " +
            "(id VARCHAR(50) NOT NULL, " +
            "curr_date VARCHAR(50) NOT NULL, " +
            "currency VARCHAR(50) NOT NULL, " +
            "account_from VARCHAR(50) NOT NULL, " +
            "account_to VARCHAR(50) NOT NULL, " +
            "amount Decimal NOT NULL, " +
            "money_before Decimal NOT NULL, " +
            "money_after Decimal NOT NULL, " +
            "PRIMARY KEY (id))";

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static boolean makeConnection() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void createTable(String tableName, Connection con) throws SQLException {
        PreparedStatement stmt = null;
        switch (tableName) {
            case "ACCOUNT":
                stmt = con.prepareStatement(accountTable);
                break;
            case "OPERATION":
                stmt = con.prepareStatement(operationTable);
                break;
            case "USERS":
                stmt = con.prepareStatement(usersTable);
                break;
        }
        if (stmt != null) stmt.execute();
    }


    public static boolean createTables() {
        Connection con = DBConnection.getConnection();
        try {
            DatabaseMetaData dbm = con.getMetaData();
            List<String> tableNames = new ArrayList<String>() {{
                add("ACCOUNT");
                add("OPERATION");
                add("USERS");
            }};
            for (String tableName : tableNames) {
                ResultSet tables = dbm.getTables(null, null, tableName, null);
                if (!tables.next()) {
                    createTable(tableName, con);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
