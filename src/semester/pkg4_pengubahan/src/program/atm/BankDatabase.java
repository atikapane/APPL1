/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semester.pkg4_pengubahan.src.program.atm;

import java.util.ArrayList;

public class BankDatabase {
    // array of Accounts
    private ArrayList<Account> accounts = new ArrayList<Account>(); 

    public BankDatabase() {
        accounts.add(new Account(12345, 54321, 1000.0, 1200.0, 2, 0));
        accounts.add(new Account(8765, 5678, 200.0, 240.0, 1, 0));
        accounts.add(new Account(1357, 7531, 500, 600, 3, 0));
    }

    public Account getAccount(int accountNumber) {
        for (int i = 0; i < (int) getAccounts().size(); i++) {
            if (accountNumber == getAccounts().get(i).getAccountNumber() 
                    && getAccounts().get(i).isBlocked() == false) {
                return getAccounts().get(i);
            }
        }
        return null; // if no matching account was found, return null
    }

    public boolean authenticateUser(int userAccountNumber, int userPIN) {
        // attempt to retrieve the account with the account number
        Account userAccount = getAccount(userAccountNumber);

        // if account exists, return result of Account method validatePIN
        if (userAccount != null) {
            return userAccount.validatePIN(userPIN);
        } else {
            return false; // account number not found, so return false
        }
    }

    public double getAvailableBalance(int userAccountNumber) {
        return getAccount(userAccountNumber).getAvailableBalance();
    }

    public double getMonthlyFee(int userAccountNumber) {
        return getAccount(userAccountNumber).getMonthlyFeeStatus();
    }

    public double getTotalBalance(int userAccountNumber) {
        return getAccount(userAccountNumber).getTotalBalance();
    }

    public void credit(int userAccountNumber, double amount) {
        getAccount(userAccountNumber).credit(amount);
    }

    public void debit(int userAccountNumber, double amount) {
        getAccount(userAccountNumber).debit(amount);
    }

    public void changePIN(int userAccountNumber, int changeTo) {
        for (int i = 0; i < (int) getAccounts().size(); i++) {
            if (getAccounts().get(i).getAccountNumber() == userAccountNumber) {
                getAccounts().get(i).setPIN(changeTo);
            }
        }
    }

    public void blockAccount(int currentAccountNumber) {
//    System.out.println("mau ngeblock " + currentAccountNumber);
        for (int i = 0; i < (int) getAccounts().size(); i++) {
            if (getAccounts().get(i).getAccountNumber() == currentAccountNumber) {
                getAccounts().get(i).setBlocked(true);
                return;
            }
        }
    }

    public void addAccount(Account addedNasabah) {
        getAccounts().add(addedNasabah);
    }

    public void unblockNasabah(int unblockedNasabahAccountNumber) {
        for (int i = 0; i < (int) getAccounts().size(); i++) {
            if (unblockedNasabahAccountNumber == getAccounts().get(i).
                    getAccountNumber()) {
                getAccounts().get(i).setBlocked(false);
                return;
            }
        }
    }

    /**
     * @return the accounts
     */
    public ArrayList<Account> getAccounts() {
        return accounts;
    }
}
