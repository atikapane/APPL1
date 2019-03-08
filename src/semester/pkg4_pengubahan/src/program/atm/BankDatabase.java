/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semester.pkg4_pengubahan.src.program.atm;

import java.util.ArrayList;

public class BankDatabase {

    private ArrayList<Account> accounts = new ArrayList<Account>(); // array of Accounts

    public BankDatabase() {
        accounts.add(new Account(12345, 54321, 1000.0, 1200.0, 2)); // initial 2 account
        accounts.add(new Account(8765, 5678, 200.0, 200.0, 1));
        accounts.add(new Account(1357, 7531, 500, 500, 3));
    }

    public Account getAccount(int accountNumber) {
        for (int i = 0; i < (int) accounts.size(); i++) {
            if (accountNumber == accounts.get(i).getAccountNumber() && accounts.get(i).isBlocked() == false) {
                return accounts.get(i);
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

    public double getTotalBalance(int userAccountNumber) {
        return getAccount(userAccountNumber).getTotalBalance();
    }

    public void credit(int userAccountNumber, double amount) {
        getAccount(userAccountNumber).credit(amount);
    }

    public void debit(int userAccountNumber, double amount) {
        getAccount(userAccountNumber).debit(amount);
    }

    public void changePIN(int userAccountNumber) {
        Screen screen = new Screen();
        Keypad keypad = new Keypad();
        screen.displayMessage("Change PIN to: ");
        int pinAfter = keypad.getInput();
        for (int i = 0; i < (int) accounts.size(); i++) {
            if (accounts.get(i).getAccountNumber() == userAccountNumber) {
                accounts.get(i).setPIN(pinAfter);
            }
        }
    }

    public void blockAccount(int currentAccountNumber) {
//    System.out.println("mau ngeblock " + currentAccountNumber);
        for (int i = 0; i < (int) accounts.size(); i++) {
            if (accounts.get(i).getAccountNumber() == currentAccountNumber) {
                accounts.get(i).setBlocked(true);
                return;
            }
        }
    }

    public void tambahNasabah(Account addedNasabah) {
        accounts.add(addedNasabah);
    }

    public void unblockNasabah(int unblockedNasabahAccountNumber) {
        for (int i = 0; i < (int) accounts.size(); i++) {
            if (unblockedNasabahAccountNumber == accounts.get(i).getAccountNumber()) {
                accounts.get(i).setBlocked(false);
                return;
            }
        }
    }
}
