/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semester.pkg4_pengubahan.src.program.atm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Account {

    /**
     * @return the type
     */
    private int accountNumber; // account number
    private int pin; // PIN for authentication
    private double availableBalance; // funds available for withdrawal
    private double totalBalance; // funds available & pending deposits
    private boolean blocked; // account status blocked or not
    private int monthlyFeeStatus;
    private int jenis;
    private ArrayList<AccountHistory> transaction = new ArrayList<>();
    private int type; // account type
    private Screen screen;
    private Keypad keypad;

//   private int SISWA = 1;
//   private int BISNIS = 2;
//   private int MASA_DEPAN = 3;
    private double withdrawalLimit = 0;
    private double transferLimit = 0;

    // Account constructor initializes attributes
    public Account(int theAccountNumber, int thePIN, double theAvailableBalance,
            double theTotalBalance, int type, int atmMonthlyFeeStatus) {
        this.accountNumber = theAccountNumber;
        this.availableBalance = theAvailableBalance;
        this.totalBalance = theTotalBalance;
        this.monthlyFeeStatus = atmMonthlyFeeStatus; //account hasn't paid monthly fee
        this.pin = thePIN;
        this.blocked = false; //account state is not blocked
        this.keypad = new Keypad();
        this.type = type;

    }

    // determines whether a user-specified PIN matches PIN in Accountsss
    public boolean validatePIN(int userPIN) {
        if (userPIN == pin) {
            return true;
        } else {
            return false;
        }
    }

    public void addTransaction(AccountHistory added) {
        transaction.add(added);
    }

    public void displayTransaction(int type) {
        Screen screen = new Screen();
        String type1;
        int month = 0;

        screen.displayMessageLine("\nTransaction History");

        if (type == 1) {
            type1 = "Transfer";
        } else if (type == 2) {
            type1 = "Withdrawal";
            screen.displayMessageLine("Which month do you want to see (mm): ");
            month = (keypad.getInput()) - 1;
            Collections.sort(transaction, new Comparator<AccountHistory>() {
                public int compare(AccountHistory a1, AccountHistory a2) {
                    return Double.compare(a1.getAmount(), a2.getAmount());
                }
            });
        } else {
            type1 = "all";
        }

        screen.displayMessageLine("Account number: " + accountNumber);
        for (int i = 0; i < transaction.size(); i++) {
            if (type1.equals("all")) {
                screen.displayMessage("\n" + transaction.get(i).getDate() + " ");
                screen.displayMessage(transaction.get(i).getType() + "  ");
                screen.displayDollarAmount(transaction.get(i).getAmount());
            } else if (transaction.get(i).getType().equals(type1)) {
                if (type1.equals("Withdrawal")) {
                    screen.displayMessage("\n" + transaction.get(i).getDate() + " ");
                } else if (month == transaction.get(i).getDate().getMonth()) {
                    screen.displayMessage("\n" + transaction.get(i).getDate() + " ");
                }

                screen.displayMessage(transaction.get(i).getType() + " ");
                screen.displayDollarAmount(transaction.get(i).getAmount());
            }
        }
        screen.displayMessageLine("");
    }

// returns available balance
    public double getAvailableBalance() {
        return availableBalance;
    }

    // returns the total balance
    public double getTotalBalance() {
        return totalBalance;
    }

    public int getJenis() {
        return this.type;
    }

    public double getTransferLimit() {
        return this.transferLimit;
    }

    public void setTransferLimit(double x) {
        this.transferLimit += x;
    }

    public void setAvailableBalance(double x) {
        this.availableBalance = x;
    }

    public void setTotalBalance(double x) {
        this.totalBalance = x;
    }

    //credit saat deposit, bertambah hanya pada totalBalance
    //available balance harus di validate terlebih dahulu
    public void credit(double amount) {
        this.totalBalance += amount;
        this.availableBalance += amount;
    }

    //debit saat uang berkurang
    public void debit(double amount) {
        this.availableBalance -= amount;
        this.totalBalance -= amount;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public void setPIN(int pinToSet) {
        this.pin = pinToSet;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean value) {
        blocked = value;
    }

    public int getMonthlyFeeStatus() {
        return monthlyFeeStatus;
    }

    public void setMonthlyFeeStatus(int monthlyFeeStatus) {
        this.monthlyFeeStatus = monthlyFeeStatus;
    }

    /**
     * @return the withdrawalLimit
     */
    public double getWithdrawalLimit() {
        return withdrawalLimit;
    }

    /**
     * @param withdrawalLimit the withdrawalLimit to set
     */
    public void setWithdrawalLimit(double withdrawalLimit) {
        this.withdrawalLimit = withdrawalLimit;
    }
}
