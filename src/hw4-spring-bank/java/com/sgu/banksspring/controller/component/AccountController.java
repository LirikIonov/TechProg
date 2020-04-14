package com.sgu.banksspring.controller.component;

import com.sgu.banksspring.entity.Account;
import com.sgu.banksspring.entity.Users;
import com.sgu.banksspring.model.AccountModel;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.sgu.banksspring.exchange.Exchange.getRate;

public class AccountController {
    private AccountModel accountModel;
    private OperationController operationController;
    private UsersController usersController;
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public AccountController(AccountModel accountModel, OperationController operationController, UsersController usersController) {
        this.accountModel = accountModel;
        this.operationController = operationController;
        this.usersController = usersController;
    }

    public void createAccount(String login, String accCode) {
        Long clientId = usersController.getUserIdByLogin(login);
        Account account = new Account(clientId, accCode);
        accountModel.save(account);
    }

    public List<Account> getListOfUserAccounts(String login) {
        Long clientId = usersController.getUserIdByLogin(login);
        List<Account> accounts = accountModel.findByClientId(clientId);
        return accounts;
    }

    public BigDecimal increaseAmount(Long accountId, BigDecimal amount, String chosenCurrency) {
        Account account = accountModel.findById(accountId).get();
        String accCurrency = account.getAccCode();
        if (!chosenCurrency.toUpperCase().equals(accCurrency))
            amount = exchange(amount, accCurrency, chosenCurrency);

        BigDecimal oldAmount = account.getAmount();
        BigDecimal newAmount = oldAmount.add(amount);
        Account tmp = new Account(account.getId(), account.getClientId(),  newAmount, accCurrency);
        accountModel.save(tmp);
        return newAmount;
    }

    public BigDecimal moneyTransfer(Long accountId, String phone, BigDecimal amount) {
        Account account = accountModel.findById(accountId).get();
        Users usersTo = usersController.getUserByPhone(phone);
        Account accountTo = accountModel.findByClientId(usersTo.getId()).get(0);
        if (account.equals(accountTo))
            return null;
        return transfer(account, accountTo, amount);
    }

    public BigDecimal moneyTransfer(Long accountFromId, Long accountToId, BigDecimal amount) {
        if(accountFromId.equals(accountToId))
            return null;
        Account accountFrom = accountModel.findById(accountFromId).get();
        Account accountTo = accountModel.findById(accountToId).get();
        return transfer(accountFrom, accountTo, amount);
    }

    private BigDecimal transfer(Account account, Account accountTo, BigDecimal amount) {
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
        accountModel.save(account);
        accountModel.save(accountTo);

        operationController.addOperation(dtf.format(LocalDateTime.now()), accFromCurrency, account.getId(),
                accountTo.getId(), amount, oldAmountFrom, newAmountFrom);
        return diffAmount;
    }

    private BigDecimal exchange(BigDecimal amount, String accCurrency, String chosenCurrency) {
        BigDecimal rate = getRate(chosenCurrency, accCurrency);
        return amount.multiply(rate);
    }
}
