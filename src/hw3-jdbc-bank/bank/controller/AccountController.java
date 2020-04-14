package bank.controller;

import bank.entity.Account;
import bank.entity.Users;
import bank.model.AccountModel;
import exception.Trouble;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import static bank.exchange.Exchange.getRate;

public class AccountController {
    private AccountModel AccountModel;
    private UsersController UsersController;
    private OperationController OperationController;
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public AccountController(AccountModel AccountModel, UsersController UsersController, OperationController OperationController) {
        this.AccountModel = AccountModel;
        this.UsersController = UsersController;
        this.OperationController = OperationController;
    }

    public String createAccount(String login, String accCode) throws SQLException, Trouble {
        UUID id = UUID.randomUUID();
        String clientId = UsersController.getUserIdByLogin(login);
        Account account = new Account(id, clientId, accCode);
        AccountModel.insertAccount(account);
        return id.toString();
    }

    public List<Account> getListOfUserAccounts(String login) throws Trouble, SQLException {
        List<Account> accounts;
        String clientId = UsersController.getUserIdByLogin(login);
        accounts = AccountModel.getAccountsByClientId(clientId);
        return accounts;
    }

    public BigDecimal increaseAmount(Account account, BigDecimal amount, String chosenCurrency) throws SQLException, Trouble {
        String accCurrency = account.getAccCode();
        if (!chosenCurrency.toUpperCase().equals(accCurrency))
            amount = exchange(amount, accCurrency, chosenCurrency);

        BigDecimal oldAmount = account.getAmount();
        BigDecimal newAmount = oldAmount.add(amount);
        Account tmp = new Account(account.getId(), account.getClientId(), newAmount, accCurrency);
        AccountModel.updateAccount(tmp);
        return newAmount;
    }

    public String moneyTransfer(Account accountFrom, String phone, BigDecimal amount) throws Trouble, SQLException {
        Account accountTo;
        Users usersTo = UsersController.getUserByPhone(phone);
        accountTo = AccountModel.getAccountsByClientId(usersTo.getId().toString()).get(0);
        return transfer(accountFrom, accountTo, amount);
    }

    public String moneyTransfer(Account accountFrom, Account accountTo, BigDecimal amount) throws Trouble, SQLException {
        return transfer(accountFrom, accountTo, amount);
    }

    private String transfer(Account account, Account accountTo, BigDecimal amount) throws Trouble, SQLException {
        String accToCurrency = accountTo.getAccCode();
        String accFromCurrency = account.getAccCode();
        BigDecimal oldAmountFrom = account.getAmount();
        BigDecimal newAmountFrom = account.getAmount().subtract(amount);
        BigDecimal newAmountTo;

        BigDecimal diffAmount = amount;
        if (!accToCurrency.toUpperCase().equals(accFromCurrency)) {
            BigDecimal exchangedAmount = exchange(amount, accToCurrency, accFromCurrency);
            newAmountTo = accountTo.getAmount().add(exchangedAmount);
            diffAmount = exchangedAmount;
        } else {
            newAmountTo = accountTo.getAmount().add(amount);
        }

        account.setAmount(newAmountFrom);
        accountTo.setAmount(newAmountTo);
        AccountModel.updateAccount(account);
        AccountModel.updateAccount(accountTo);

        OperationController.addOperation(dtf.format(LocalDateTime.now()), accFromCurrency, account.getId().toString(),
                accountTo.getId().toString(), amount, oldAmountFrom, newAmountFrom);
        return diffAmount + " (" + accToCurrency + ")";
    }

    private BigDecimal exchange(BigDecimal amount, String accCurrency, String chosenCurrency) throws Trouble {
        BigDecimal rate = getRate(chosenCurrency, accCurrency);
        return amount.multiply(rate);
    }
}
