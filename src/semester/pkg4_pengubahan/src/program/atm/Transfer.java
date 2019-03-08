/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semester.pkg4_pengubahan.src.program.atm;

class Transfer {
    private int numFrom;
    private int numTo;
    private double value;
    private Keypad keypad;
    private BankDatabase bankDatabase;
    private final static int CANCELED = 7;

    Transfer(BankDatabase bankDatabase, int numFrom, Keypad keypad) {
        this.bankDatabase = bankDatabase;
        this.numFrom = numFrom;
        this.keypad = keypad;
    }
    
    public void execute() {
        Screen screen = new Screen();

        //Get amount and account destination
        value = displayMenuTransfer();
        if (value != CANCELED) {
            screen.displayMessage("\nEnter account number destination: ");
            numTo = keypad.getInput();

            assert (numFrom != numTo);

            Account accFrom = bankDatabase.getAccount(numFrom);
            Account accTo = bankDatabase.getAccount(numTo);

            assert (accFrom != null);
            assert (accTo != null);

            accFrom.setTransferLimit(value);
            //Check account transfer limit
            if (accFrom.getJenis() == 2) {
                //Transfer limit for Bisnis account
                if (accFrom.getTransferLimit() <= 10000) {
                    accFrom.setAvailableBalance(accFrom.getAvailableBalance() - value);
                    accFrom.setTotalBalance(accFrom.getTotalBalance() - value);
                    accTo.setTotalBalance(accTo.getTotalBalance() + value);
                } else {
                    screen.displayMessageLine("\nYou have exceed your transfer limit.");
                    accFrom.setTransferLimit(-value);
                }
            } else if (accFrom.getJenis() == 3) {
                //Transfer limit for Masa Depan account
                if (accFrom.getTransferLimit() <= 500) {
                    accTo.setTotalBalance(accTo.getTotalBalance() + value);
                    value += 5; //Biaya transfer masa depan;
                    accFrom.setAvailableBalance(accFrom.getAvailableBalance() - value);
                    accFrom.setTotalBalance(accFrom.getTotalBalance() - value);
                } else {
                    screen.displayMessageLine("\nYou have exceed your transfer limit.");
                    accFrom.setTransferLimit(-value);
                }
            }
        }
    }

    private double displayMenuTransfer() {
        int userChoice = 0;

        Screen screen = new Screen();

        int[] amounts = {0, 20, 40, 60, 100, 200};

        while (userChoice == 0) {
            screen.displayMessageLine("\nTransfer Menu:");
            screen.displayMessageLine("1 - $20");
            screen.displayMessageLine("2 - $40");
            screen.displayMessageLine("3 - $60");
            screen.displayMessageLine("4 - $100");
            screen.displayMessageLine("5 - $200");
            screen.displayMessageLine("6 - Another amount");
            screen.displayMessageLine("7 - Cancel transaction");
            screen.displayMessage("\nChoose amount: ");

            int input = keypad.getInput(); // get user input through keypad
            if (input >= 1 && input <= 5) {
                userChoice = amounts[input];
            } else if (input == 6) {
                screen.displayMessage("\nInsert amount: ");
                userChoice = keypad.getInput();
            } else if (input == CANCELED) {
                screen.displayMessageLine("Cancelling transaction..");
                return CANCELED;
            } else {
                screen.displayMessageLine("\nInvalid selection. Try again.");
                continue;
            }
        }
        return userChoice;
    }
}
