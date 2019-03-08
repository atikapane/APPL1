/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semester.pkg4_pengubahan.src.program.atm;

public class ATM {

    private boolean userAuthenticated; // whether user is authenticated
    private int currentAccountNumber; // current user's account number
    private Screen screen; // ATM's screen
    private Keypad keypad; // ATM's keypad
    private CashDispenser cashDispenser; // ATM's cash dispenser

    private BankDatabase bankDatabase; // account information database
    private Administration administration;
    private AdminMode adminMode;
    // constants corresponding to main menu options
    private static final int BALANCE_INQUIRY = 1;
    private static final int WITHDRAWAL = 2;
    private static final int DEPOSIT = 3;
    private static final int EXIT = 6;
    private static final int CHANGE_PIN = 4;
    private static final int TRANSFER = 5;

    // no-argument ATM constructor initializes instance variables
    public ATM() {
        userAuthenticated = false; // user is not authenticated to start
        currentAccountNumber = 0; // no current account number to start
        screen = new Screen(); // create screen
        keypad = new Keypad(); // create keypad 
        cashDispenser = new CashDispenser(); // create cash dispenser
        bankDatabase = new BankDatabase(); // create acct info database
        adminMode = new AdminMode(bankDatabase, cashDispenser);
        administration = new Administration(bankDatabase, adminMode);
    }

    // start ATM 
    public void run() {
        // welcome and authenticate user; perform transactions

        while (true) {
            administration.AdministrationFee();
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
    private void authenticateUser() {
        screen.displayMessage("\nPlease enter your account number: ");
        int accountNumber = keypad.getInput(); // input account number
        if (accountNumber == 0) {
            AdminMode adminMode = new AdminMode(bankDatabase, cashDispenser);
            screen.displayMessageLine("\nEntering Admin Mode..");
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
                screen.displayMessageLine(
                        "Invalid account number or PIN or your account might be blocked. Please try again.");
            }
        }
        // fail 3 times, block this account
        screen.displayMessageLine("Fail to enter PIN after 3 times, your account will be blocked! >:(");
        bankDatabase.blockAccount(accountNumber);
    }

    // display the main menu and perform transactions
    private void performTransactions() {
        // local variable to store transaction currently being processed
        Transaction currentTransaction = null;

        boolean userExited = false; // user has not chosen to exit

        // loop while user has not chosen option to exit system
        while (!userExited) {
            // show main menu and get user selection
            int mainMenuSelection = displayMainMenu();

            // decide how to proceed based on user's menu selection
            // user chose to perform one of three transaction types
            if (mainMenuSelection == BALANCE_INQUIRY) {
                // initialize as new object of chosen type
                currentTransaction = createTransaction(mainMenuSelection);
                currentTransaction.execute(); // execute transaction
            } else if (mainMenuSelection == WITHDRAWAL) {
                Withdrawal withdrawal = new Withdrawal(currentAccountNumber, screen, bankDatabase, keypad, cashDispenser);
                withdrawal.execute();
            } else if (mainMenuSelection == DEPOSIT) {
                Deposit deposit = new Deposit(currentAccountNumber, screen, bankDatabase, keypad, new DepositSlot(), cashDispenser);
                deposit.execute();
            } else if (mainMenuSelection == EXIT) { // user chose to terminate session
                screen.displayMessageLine("\nExiting the system...");
                userExited = true; // this ATM session should end
            } else if (mainMenuSelection == CHANGE_PIN) {
                bankDatabase.changePIN(currentAccountNumber);
            } else if (mainMenuSelection == TRANSFER) {
                screen.displayMessage("\nTransfer ke: ");
                int accTo = keypad.getInput();
                screen.displayMessage("Value: ");
                double value = keypad.getInputDouble();
                Transfer transfer = new Transfer(bankDatabase, currentAccountNumber, accTo, value);
                transfer.execute();
            } else {
                screen.displayMessageLine("\nYou did not enter a valid selection. Try again.");
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
        screen.displayMessageLine("6 - Exit");
        screen.displayMessage("Enter a choice: ");
        return keypad.getInput(); // return user's selection
    }

    private Transaction createTransaction(int type) {
        Transaction temp = null;

        switch (type) {
            case BALANCE_INQUIRY:
                temp = new BalanceInquiry(
                        currentAccountNumber, screen, bankDatabase);
                break;
        }

        return temp;
    }
}
