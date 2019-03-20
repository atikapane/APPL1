/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semester.pkg4_pengubahan.src.program.atm;

import java.text.ParseException;

public class ATM {

    private boolean userAuthenticated; // whether user is authenticated
    private int currentAccountNumber; // current user's account number
    private Screen screen; // ATM's screen
    private Keypad keypad; // ATM's keypad
    private CashDispenser cashDispenser; // ATM's cash dispenser

    private BankDatabase bankDatabase; // account information database

    // constants corresponding to main menu options
    private static final int BALANCE_INQUIRY = 1;
    private static final int WITHDRAWAL = 2;
    private static final int DEPOSIT = 3;
    private static final int EXIT = 7;
    private static final int CHANGE_PIN = 4;
    private static final int TRANSFER = 5;
    private static final int TRANSACTION_HISTORY = 6;

    // no-argument ATM constructor initializes instance variables
    public ATM() {
        userAuthenticated = false; // user is not authenticated to start
        currentAccountNumber = 0; // no current account number to start
        screen = new Screen(); // create screen
        keypad = new Keypad(); // create keypad 
        cashDispenser = new CashDispenser(); // create cash dispenser
        bankDatabase = new BankDatabase(); // create acct info database
        AdminMode adminMode = new AdminMode(bankDatabase, cashDispenser); //create adminMode
        Administration administration = new Administration(bankDatabase, adminMode); //create administration
    }

    // start ATM 
    public void run() throws ParseException {
        // welcome and authenticate user; perform transactions
        while (true) {
            // loop while user is not yet authenticated
            while (!userAuthenticated) {
                screen.displayMessageLine("\nWelcome!");
                authenticateUser(); // authenticate user
            }
            if (userAuthenticated) {
                performTransactions(); // user is now authenticated
                userAuthenticated = false; // reset before next ATM session
                currentAccountNumber = 0; // reset before next ATM session
                screen.displayMessageLine("\nThank you! Goodbye!");
            }
        }
    }

    // attempts to authenticate user against database
    private void authenticateUser() throws ParseException {
        screen.displayMessage("\nPlease enter your account number: ");
        int accountNumber = keypad.getInput(); // input account number
        if (accountNumber == 0) {
            AdminMode adminMode = new AdminMode(bankDatabase, cashDispenser);
            screen.displayMessageLine("\nEntering Admin Mode...");
            adminMode.execute();
            return;
        }

        for (int cnt = 0; cnt < 3; cnt++) {
            screen.displayMessage("\nEnter your PIN: "); // prompt for PIN
            int pin = keypad.getInput(); // input PIN
            // set userAuthenticated to boolean value returned by database
            userAuthenticated = bankDatabase.authenticateUser(accountNumber, pin);
            // check whether authentication succeeded
            if (userAuthenticated) {
                currentAccountNumber = accountNumber; // save user's account #
                return;
            } else {
                screen.displayMessageLine("Invalid account number or PIN or your "
                        + "account might be blocked. Please try again.");
            }
        }
        // fail 3 times, block this account
        screen.displayMessageLine("Failed to enter PIN 3 times, your account "
                + "has been blocked! >:(");
        screen.displayMessageLine("Please contact admin to unblock your account.");
        bankDatabase.blockAccount(accountNumber);
    }

    // display the main menu and perform transactions
    private void performTransactions() {
        // local variable to store transaction currently being processed
        Transaction currentTransaction = null;

        boolean userExited = false; // user has not chosen to exit
        int mainMenuSelection;
        Account acc = bankDatabase.getAccount(currentAccountNumber);

        // loop while user has not chosen option to exit system
        while (!userExited) {
            // show main menu and get user selection
            if (acc.getJenis() == 1) { //for user account type SISWA
                mainMenuSelection = displayMainMenuSiswa();
                if (mainMenuSelection == TRANSACTION_HISTORY) {
                    mainMenuSelection = EXIT;
                }
            } else {
                mainMenuSelection = displayMainMenu();
            }

            // decide how to proceed based on user's menu selection
            // user chose to perform one of three transaction types
            if (mainMenuSelection == BALANCE_INQUIRY) {
                // initialize as new object of chosen type
                currentTransaction = createTransaction(mainMenuSelection);
                currentTransaction.execute(); // execute transaction
            } else if (mainMenuSelection == WITHDRAWAL) {
                currentTransaction = createTransaction(mainMenuSelection);
                currentTransaction.execute(); // execute transaction
            } else if (mainMenuSelection == DEPOSIT) {
                // initialize as new object of chosen type
                currentTransaction = createTransaction(mainMenuSelection);
                currentTransaction.execute(); // execute transaction
            } else if (mainMenuSelection == EXIT) { // user chose to terminate session
                screen.displayMessageLine("\nExiting the system...");
                userExited = true; // this ATM session should end
            } else if (mainMenuSelection == CHANGE_PIN) {
                bankDatabase.changePIN(currentAccountNumber);
            } else if (mainMenuSelection == TRANSFER) {
                Transfer transfer = new Transfer(currentAccountNumber,
                        screen, bankDatabase, keypad, cashDispenser);
                transfer.execute();
            } else if (mainMenuSelection == TRANSACTION_HISTORY) {
                int input = displayHistoryMenu();
                bankDatabase.getAccount(currentAccountNumber).displayTransaction(input);
            } else {
                screen.displayMessageLine("\nYou did not enter a valid "
                        + "selection. Try again.");
            }
        }
    }

    // display the main menu and return an input selection
    private int displayMainMenu() {
        screen.displayMessageLine("\nMain menu:");
        screen.displayMessageLine("1 - View my balance");
        screen.displayMessageLine("2 - Withdraw cash");
        screen.displayMessageLine("3 - Deposit funds");
        screen.displayMessageLine("4 - Change PIN");
        screen.displayMessageLine("5 - Transfer");
        screen.displayMessageLine("6 - Transaction History");
        screen.displayMessageLine("7 - Exit");
        screen.displayMessage("Enter a choice: ");
        return keypad.getInput(); // return user's selection
    }

        private int displayMainMenuSiswa() {
        screen.displayMessageLine("\nMain menu:");
        screen.displayMessageLine("1 - View my balance");
        screen.displayMessageLine("2 - Withdraw cash");
        screen.displayMessageLine("3 - Deposit funds");
        screen.displayMessageLine("4 - Change PIN");
        screen.displayMessageLine("5 - Transaction History");
        screen.displayMessageLine("6 - Exit");
        screen.displayMessage("Enter a choice: ");
        return keypad.getInput(); // return user's selection
    }
        
    public int displayHistoryMenu() {
        screen.displayMessageLine("\n1. See Transfer History;");
        screen.displayMessageLine("2. See Withdraw History;");
        screen.displayMessageLine("3. See All History;");
        screen.displayMessageLine("Choose option : ");
        return keypad.getInput(); // return user's selection
    }

    private Transaction createTransaction(int type) {
        Transaction temp = null;

        switch (type) {
            case BALANCE_INQUIRY:
                temp = new BalanceInquiry(
                        currentAccountNumber, screen, bankDatabase);
                break;
            case WITHDRAWAL:
                temp = new Withdrawal(currentAccountNumber,
                        screen, bankDatabase, keypad, cashDispenser);
                break;
            case DEPOSIT:
                temp = new Deposit(currentAccountNumber, screen, bankDatabase, 
                        keypad, new DepositSlot(), cashDispenser);
                break;
        }

        return temp;
    }
}