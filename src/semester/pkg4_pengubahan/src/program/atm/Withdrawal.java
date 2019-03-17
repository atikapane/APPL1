// Withdrawal.java
// Represents a withdrawal ATM transaction
package semester.pkg4_pengubahan.src.program.atm;

public class Withdrawal extends Transaction {

    private int amount; // amount to withdraw
    private Keypad keypad; // reference to keypad
    private CashDispenser cashDispenser; // reference to cash dispenser

    // constant corresponding to menu option to cancel
    private final static int CANCELED = 6;
    
    //withdrawal limit for every account type
    private final static int SISWA_LIMIT = 20;
    private final static int BISNIS_LIMIT = 20;
    private final static int MASA_DEPAN_LIMIT = 20;
    

    Screen screen = getScreen(); // get screen reference
    private boolean limitW;

    // Withdrawal constructor
    public Withdrawal(int userAccountNumber, Screen atmScreen,
            BankDatabase atmBankDatabase, Keypad atmKeypad,
            CashDispenser atmCashDispenser) {

        // initialize superclass variables
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
        cashDispenser = atmCashDispenser;
    }

    // display a menu of withdrawal amounts and the option to cancel;
    // return the chosen amount or 0 if the user chooses to cancel
    private int displayMenuOfAmounts() {
        int userChoice = 0; // local variable to store return value

        // array of amounts to correspond to menu numbers
        int[] amounts = {0, 20, 40, 60, 100, 200};

        // loop while no valid choice has been made
        while (userChoice == 0) {
            // display the withdrawal menu
            withdrawalMenu();
            // determine how to proceed based on the input value
            int input = keypad.getInput(); // get user input through keypad
            if (input >= 1 && input <= 5) {
                userChoice = amounts[input];
            } else if (input == CANCELED) {
                screen.displayMessageLine("Cancelling transaction..");
                return CANCELED;
            } else {
                screen.displayMessageLine("\nInvalid selection. Try again.");
                continue;
            }

            if (userChoice > super.getBankDatabase().getAvailableBalance(
                    super.getAccountNumber())) {
                screen.displayMessageLine("You don't have enough balance. "
                        + "Please enter another amount.");
                userChoice = 0;
            }
        }
        return userChoice; // return withdrawal amount or CANCELED
    }

    // perform transaction
    @Override
    public void execute() {
        limitW = false;
        amount = displayMenuOfAmounts();

        limitW = checkLimit(amount);

        if (limitW == true) {
            screen.displayMessageLine("You have exceed your withdrawal limit.");
        }

        if (amount != CANCELED && limitW == false) {
            if ((cashDispenser.isSufficientCashAvailable(amount))
                    && (getBankDatabase().getAvailableBalance(getAccountNumber()) >= 0)) {
                screen.displayMessageLine("Your cash has been dispensed. Please take your cash now.");
                cashDispenser.dispenseCash(amount);
                super.getBankDatabase().debit(super.getAccountNumber(), amount);
                super.getBankDatabase().getAccount(getAccountNumber()).addTransaction(new AccountHistory("Withdrawal", amount));
            } else {
                screen.displayMessageLine("\nThere is not enough cash in the "
                        + "machine. Please try again later.");
            }
        }
    }

    //check withdrawal limit
    //return true if amount excedeed limit, else false
    public boolean checkLimit(int amount) {
        Account account = super.getBankDatabase().getAccount(
                super.getAccountNumber());

        account.setWithdrawalLimit(amount);

        if (account.getJenis() == 1) {
            if (account.getWithdrawalLimit() > SISWA_LIMIT) {
                account.setWithdrawalLimit(-amount);
                return true;
            }
        } else if (account.getJenis() == 2) {
            if (account.getWithdrawalLimit() > BISNIS_LIMIT) {
                account.setWithdrawalLimit(-amount);
                return true;
            }
        } else if (account.getJenis() == 3) {
            if (account.getWithdrawalLimit() > MASA_DEPAN_LIMIT) {
                account.setWithdrawalLimit(-amount);
                return true;
            }
        }
        return false;
    }

    // display the withdrawal menu
    private void withdrawalMenu() {
        screen.displayMessageLine("\nWithdrawal Menu:");
        screen.displayMessageLine("1 - $20");
        screen.displayMessageLine("2 - $40");
        screen.displayMessageLine("3 - $60");
        screen.displayMessageLine("4 - $100");
        screen.displayMessageLine("5 - $200");
        screen.displayMessageLine("6 - Cancel transaction");
        screen.displayMessage("\nChoose a withdrawal amount: ");
    }
}
