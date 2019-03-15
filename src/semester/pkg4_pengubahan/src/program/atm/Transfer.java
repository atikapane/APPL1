/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semester.pkg4_pengubahan.src.program.atm;

class Transfer {

    private final int numFrom; //Account number transferor
    private int numTo; //Account number transferee
    private double value; //Transfer amount
    private Keypad keypad;
    private BankDatabase bankDatabase;
    private final static int CANCELED = 7;
    private Screen screen = new Screen();

    Transfer(BankDatabase bankDatabase, int numFrom, Keypad keypad) {
        this.bankDatabase = bankDatabase;
        this.numFrom = numFrom;
        this.keypad = keypad;
    }

    public void execute() {

        //Get amount and account destination
        value = displayMenuTransfer();

        if (value != CANCELED) {
            screen.displayMessage("\nEnter account number destination: ");
            numTo = keypad.getInput();

            //check if account number exist
            Account accFrom = bankDatabase.getAccount(numFrom);
            Account accTo = bankDatabase.getAccount(numTo);

            while (accFrom == accTo || accTo == null) {
                screen.displayMessageLine("\nInvalid account number.");
                return;
            }

            accFrom.setTransferLimit(value);
            //Check account transfer limit
            if (accFrom.getJenis() == 2) {
                //Transfer limit for Bisnis account = $1000
                //Transfer fee for Bisnis account = $0
                doTransfer(accFrom, accTo, 1000, 0);
            } else if (accFrom.getJenis() == 3) {
                //Transfer limit for Masa Depan account = $500
                //Transfer fee for Masa Depan account = $5
                doTransfer(accFrom, accTo, 500, 5);
            }
        }
    }

    private void doTransfer(Account accFrom, Account accTo, int limit,
            int transferFee) {
        if (accFrom.getTransferLimit() <= limit) {
            accTo.setTotalBalance(accTo.getTotalBalance() + value);
            accTo.setAvailableBalance(accTo.getAvailableBalance() + value);
            value += transferFee;
            accFrom.setAvailableBalance(accFrom.getAvailableBalance() - value);
            accFrom.setTotalBalance(accFrom.getTotalBalance() - value);
            screen.displayMessageLine("\nTransfer successful.");
            accFrom.addTransaction(new AccountHistory("Transfer", value));
        } else {
            screen.displayMessageLine("\nYou have exceed your transfer limit.");
            accFrom.setTransferLimit(-value);
        }
    }

    private double displayMenuTransfer() {
        int userChoice = 0;

        Screen screen = new Screen();

        int[] amounts = {0, 20, 40, 60, 100, 200};

        while (userChoice == 0) {

            transferMenu();

            int input = keypad.getInput(); // get user input through keypad
            if (input >= 1 && input <= 5) {
                userChoice = amounts[input];
            } else if (input == 6) {
                screen.displayMessage("\nInsert amount: ");
                userChoice = keypad.getInput();
                if (userChoice <= 0) {
                    screen.displayMessageLine("\nInvalid amount. Enter another "
                            + "amount.");
                    userChoice = 0;
                }
            } else if (input == CANCELED) {
                screen.displayMessageLine("Cancelling transaction..");
                return CANCELED;
            } else {
                screen.displayMessageLine("\nInvalid selection. Try again.");
            }

            //check whether transferor has enough available balance
            if (userChoice > bankDatabase.getAccount(numFrom).
                    getAvailableBalance()) {
                screen.displayMessageLine("You don't have enough balance. "
                        + "Please enter another amount.");
                userChoice = 0;
            }
        }
        return userChoice;
    }

    // display the transfer menu
    private void transferMenu() {
        screen.displayMessageLine("\nTransfer Menu:");
        screen.displayMessageLine("1 - $20");
        screen.displayMessageLine("2 - $40");
        screen.displayMessageLine("3 - $60");
        screen.displayMessageLine("4 - $100");
        screen.displayMessageLine("5 - $200");
        screen.displayMessageLine("6 - Another amount");
        screen.displayMessageLine("7 - Cancel transaction");
        screen.displayMessage("\nChoose amount: ");
    }
}
