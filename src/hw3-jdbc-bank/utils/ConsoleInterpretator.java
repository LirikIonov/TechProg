package utils;

import bank.connection.DBConnection;
import bank.controller.AccountController;
import bank.controller.OperationController;
import bank.controller.UsersController;
import bank.entity.Account;
import bank.model.AccountModel;
import bank.model.OperationModel;
import bank.model.UsersModel;
import exception.Trouble;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ConsoleInterpretator {
    private static boolean allIsReady = false;
    private OperationController OperationController = new OperationController(new OperationModel());
    private UsersController UsersController = new UsersController(new UsersModel());
    private AccountController AccountController = new AccountController(new AccountModel(), UsersController, OperationController);
    private String username;
    private int NEGINF = Integer.MIN_VALUE;

    public void run() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nPress number to choose one of these options:");
            mainMenu();
            int type = processInput();
            switch (type) {
                case 1:
                    operations();
                    break;
                case 2:
                    exit = true;
                    break;
                default:
                    break;
            }
        }
    }

    public boolean setUp() {
        try {
            initTables();
        } catch (Trouble ex) {
            System.out.println("Internal exception returned from database. Aborting application " + Arrays.toString(ex.getStackTrace()));
            return false;
        }
        return true;
    }

    private boolean checkIfLoggedIn(String text) {
        if (username == null) {
            System.out.println(text);
            return false;
        }
        return true;
    }

    private void operations() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nPress number to choose one of these options:");
            operationsMenu();
            int type = processInput();
            switch (type) {
                case 1:
                    userCreate();
                    break;
                case 2:
                    authorize();
                    break;
                case 3:
                    deauthorize();
                    break;
                case 4:
                    accountCreate();
                    break;
                case 5:
                    addMoney();
                    break;
                case 6:
                    financeTransfer();
                    break;
                case 7:
                    historyDisplay();
                    break;
                case 8:
                    username = null;
                    exit = true;
                    break;
                default:
                    break;
            }
        }
    }

    private int processInput() {
        int k = NEGINF;
        while (k == NEGINF) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String tmp = reader.readLine();
                if (StringUtils.isNumeric(tmp)) {
                    k = Integer.parseInt(tmp);
                }
            } catch (IOException io) {
                System.out.println("Incorrect input!");
            }
        }
        return k;
    }

    private void addMoney() {
        if (!checkIfLoggedIn("You must be logged in for this action!")) return;

        System.out.println("Choose index of the account for adding money: ");
        Account account;
        try {
            account = accountSelect();
        } catch (Trouble ex) {
            System.out.println(ex.getMessage());
            return;
        }

        String currency = null;
        try {
            currency = currencyChoise();
        } catch (Trouble ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("Enter money amount");
        String str = readLine();
        if (!StringUtils.isNumeric(str)) {
            System.out.println("Only numerical input is allowed!");
            return;
        }

        double amount = Double.parseDouble(str);
        if (amount <= 0.0) {
            System.out.println("Input cant be negative!");
            return;
        }

        BigDecimal newAmount;
        try {
            newAmount = AccountController.increaseAmount(account, BigDecimal.valueOf(amount), currency);
        } catch (SQLException | Trouble ex) {
            System.out.println("No money was transferred. Reason: " +
                    ex.getMessage() + '\n' +
                    Arrays.toString(ex.getStackTrace()));
            return;
        }
        System.out.println("New amount=" + newAmount + " accoundId=" + account.getAccCode());
    }

    private void deauthorize() {
        if (!checkIfLoggedIn("You cant deauthorize if you already not authorized")) return;
        username = null;
        System.out.println("User " + username + " is deauthorized.");
    }

    private void authorize() {
        if (username != null) {
            System.out.println(username + " if already authorised!");
            return;
        }

        System.out.println("Login:");
        String login = readLine();
        System.out.println("Password:");
        String password = readLine();
        boolean findUser;
        try {
            findUser = UsersController.existUserByLogin(login);
        } catch (SQLException ex) {
            System.out.println("Internal error, please try again.");
            return;
        }
        if (!findUser) {
            System.out.println("Theres no user with such login=" + login);
            return;
        }

        boolean wasAuth;
        try {
            wasAuth = UsersController.authorise(login, password);
        } catch (SQLException e) {
            System.out.println("Internal error, please try again.");
            return;
        }
        if (wasAuth) {
            username = login;
            System.out.println("Authentication of user with login " + username + " is successful.");
        } else {
            System.out.println("Entered wrong password!");
        }
    }

    private void accountCreate() {
        if (!checkIfLoggedIn("You must be logged in for this action!")) return;
        String accCode = null;
        try {
            accCode = currencyChoise();
        } catch (Trouble ex) {
            System.out.println(ex.getMessage());
        }

        String id;
        try {
            id = AccountController.createAccount(username, accCode);
        } catch (SQLException | Trouble ex) {
            System.out.println("Account haven't been created, please try again. Reason: " +
                    ex.getMessage() + '\n' +
                    Arrays.toString(ex.getStackTrace()));
            return;
        }
        System.out.println("Bank account with ID " + id + " with " + accCode + " currency successfully created.");
    }

    private void userCreate() {
        System.out.println("Login: ");
        String login = readLine();
        boolean isExist;
        try {
            isExist = UsersController.existUserByLogin(login);
        } catch (SQLException ex) {
            System.out.println("Internal error");
            return;
        }
        if (isExist) {
            System.out.println("User " + login + " is already exist.");
            return;
        }

        System.out.println("Password:");
        String password = readLine();
        System.out.println("Phone:");
        String phone = readLine();
        System.out.println("Address:");
        String address = readLine();

        try {
            username = UsersController.createUser(login, password, phone, address);
        } catch (SQLException | Trouble ex) {
            System.out.println("Cant create user. Reason: " +
                    ex.getMessage() + '\n' +
                    Arrays.toString(ex.getStackTrace()));
            return;
        }
        System.out.println("Created user " + username + ".");
    }

    private void financeTransfer() {
        if (!checkIfLoggedIn("You must be logged in for this action!")) return;
        System.out.println("Choose index of the account which is source for money transferring.");
        Account accountFrom;
        try {
            accountFrom = accountSelect();
        } catch (Trouble ex) {
            System.out.println("Error when selecting account. Reason: " +
                    ex.getMessage() + '\n' +
                    Arrays.toString(ex.getStackTrace()));
            return;
        }

        System.out.println("Enter amount of preferrable transfer");
        String str = readLine();
        double amount;
        if (!StringUtils.isNumeric(str)) {
            System.out.println("Only numerical input is allowed!");
            return;
        } else {
            amount = Double.parseDouble(str);
            if (amount <= 0.0) {
                System.out.println("Input cant be negative!");
                return;
            }
        }

        System.out.println("1) Transfer by phone.\n" +
                "2) Transfer between personal accounts.");
        int type;
        str = readLine();
        if (!StringUtils.isNumeric(str)) {
            System.out.println("Only numerical input is allowed!");
            return;
        } else {
            type = Integer.parseInt(str);
            if (type == 0 || type > 2) {
                System.out.println("Input cant be negative!");
                return;
            }
        }

        if (type == 1) {
            System.out.println("Enter users phone: ");
            String phone = readLine();
            String newAmount;

            try {
                newAmount = AccountController.moneyTransfer(accountFrom, phone, BigDecimal.valueOf(amount));
            } catch (SQLException | Trouble ex) {
                System.out.println("Cant transfer money. Reason: " +
                        ex.getMessage() + '\n' +
                        Arrays.toString(ex.getStackTrace()));
                return;
            }
            System.out.println("Transferred amount=" + newAmount + " to phone=" + phone);
        } else {
            System.out.println("Choose account for transferring: ");
            Account accountTo;
            try {
                accountTo = accountSelect();
            } catch (Trouble ex) {
                System.out.println("Error when selecting account. Reason: " +
                        ex.getMessage() + '\n' +
                        Arrays.toString(ex.getStackTrace()));
                return;
            }

            String resultAmount;
            try {
                resultAmount = AccountController.moneyTransfer(accountFrom, accountTo, BigDecimal.valueOf(amount));
            } catch (SQLException | Trouble ex) {
                System.out.println("Cant transfer money. Reason: " +
                        ex.getMessage() + '\n' +
                        Arrays.toString(ex.getStackTrace()));
                return;
            }
            System.out.println("Transferred amount " + resultAmount + "to accountId=" + accountFrom.getAccCode());
        }
    }

    private void historyDisplay() {
        if (!checkIfLoggedIn("You must be logged in for this action!")) return;
        System.out.println("Choose index of the account for seeing its history.");
        Account accountFrom;
        try {
            accountFrom = accountSelect();
        } catch (Trouble ex) {
            System.out.println("There is no account in system\n" + Arrays.toString(ex.getStackTrace()));
            return;
        }

        List<String> operations;
        try {
            operations = OperationController.getHistory(accountFrom.getId().toString());
        } catch (SQLException ex) {
            System.out.println("Internal DB error: " +
                    ex.getMessage() + '\n' +
                    Arrays.toString(ex.getStackTrace()));
            return;
        }

        operations.forEach(System.out::println);

    }

    private Account accountSelect() throws Trouble {
        List<Account> accounts;
        try {
            accounts = AccountController.getListOfUserAccounts(username);
        } catch (SQLException | Trouble ex) {
            throw new Trouble(ex.getMessage(), ex);
        }

        if (accounts.isEmpty()) {
            throw new Trouble("There is zero accounts saved in system.");
        }

        for (int i = 0; i < accounts.size(); i++) {
            Account current = accounts.get(i);
            System.out.println("[" + (i + 1) + "]" +
                    " amount = " + current.getAmount() +
                    ", currency = " + current.getAccCode());
        }

        int idx;
        String str = readLine();
        if (!StringUtils.isNumeric(str)) {
            throw new Trouble("Error when trying to parse accounts number");
        }

        idx = Integer.parseInt(str);
        if (idx == 0 || idx > accounts.size()) {
            throw new Trouble("Error when trying to get account by its number");
        }
        return accounts.get(idx - 1);
    }

    private String currencyChoise() throws Trouble {
        System.out.println("Choose one of the available currencies:");
        System.out.println("1 - EUR");
        System.out.println("2 - RUB");
        System.out.println("3 - USD");

        int type;
        String str = readLine();
        if (!StringUtils.isNumeric(str)) {
            throw new Trouble("Only numerical input is allowed!");
        } else {
            type = Integer.parseInt(str);
            if (type < 1 || type > 3) {
                throw new Trouble("Wrong number typed in!");
            }
        }
        String accCode = null;
        switch (type) {
            case 1: accCode = "EUR"; break;
            case 2: accCode = "RUB"; break;
            case 3: accCode = "USD"; break;
            default: break;
        }
        return accCode;
    }

    private void mainMenu() {
        System.out.println("1) Make some business");
        System.out.println("2) Exit");
    }

    private void operationsMenu() {
        System.out.println("1) Create an user");
        System.out.println("2) Authorise");
        System.out.println("3) Deauthorize");
        System.out.println("4) Create an account");
        System.out.println("5) Add money");
        System.out.println("6) Transfer money");
        System.out.println("7) Print history");
        System.out.println("8) Exit");
    }

    private String readLine() {
        String tmp = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            tmp = reader.readLine();
        } catch (IOException io) {
            io.printStackTrace();
        }
        return tmp;
    }

    private void initTables() throws Trouble {
        if (!allIsReady) {
            if (!DBConnection.makeConnection()) {
                throw new Trouble("Exception when trying to make connection");
            }
            DBConnection.createTables();
            allIsReady = true;
        }
    }
}