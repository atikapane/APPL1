package semester.pkg4_pengubahan.src.program.atm;

public class Deposit extends Transaction {

    private double amount; // amount to deposit
    private Keypad keypad; // reference to keypad
    private DepositSlot depositSlot; // reference to deposit slot
    private final static int CANCELED = 0; // constant for cancel option
    private CashDispenser cashDispenser;

    // Deposit constructor
    public Deposit(int userAccountNumber, Screen atmScreen,
            BankDatabase atmBankDatabase, Keypad atmKeypad,
            DepositSlot atmDepositSlot, CashDispenser atmCashDispenser) {

        // initialize superclass variables
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
        depositSlot = atmDepositSlot;
        cashDispenser = atmCashDispenser;
    }

    // prompt user to enter a deposit amount in cents 
    private double promptForDepositAmount() {
        Screen screen = getScreen(); // get reference to screen

        // display the prompt
        screen.displayMessage("\nEnter a deposit amount in CENTS "
                + "(or 0 to cancel): ");
        amount = keypad.getInput(); // receive input of deposit amount

        while (amount < 0) {
            screen.displayMessageLine("\nPlease enter a valid amount");
            screen.displayMessage("Enter a deposit amount in CENTS "
                    + "(or 0 to cancel): ");
            amount = keypad.getInput(); // receive input of deposit amount
        }

        // check whether the user canceled or entered a valid amount
        if (amount == CANCELED) {
            return CANCELED;
        } else {
            return amount / 100; // return dollar amount
        }
    }

    // perform transaction
    @Override
    public void execute() {
        amount = promptForDepositAmount();
        if (amount != CANCELED) {
            super.getScreen().displayMessageLine("Please insert a deposit "
                    + "envelope containing $" + amount + "\n");
            super.getScreen().displayMessageLine("Your envelope has been "
                    + "received.");
            super.getScreen().displayMessageLine("NOTE: The money just "
                    + "deposited will not be available until we verify the "
                    + "amount of any enclosed cash and your checks clear.");
            AdminMode adminMode = new AdminMode(
                    super.getBankDatabase(), cashDispenser);
            super.getScreen().displayMessageLine("Please wait while admin "
                    + "validate your envelope.");
            if (adminMode.validate()) {
                super.getBankDatabase().credit(super.getAccountNumber(), amount);
                super.getScreen().displayMessageLine("Deposit validated.");
                super.getBankDatabase().getAccount(getAccountNumber()).addTransaction(new AccountHistory("Deposit", amount));
            } else {
                super.getScreen().displayMessageLine("\nYour envelope is not "
                        + "valid...\n");
            }
        } else {
            super.getScreen().displayMessageLine("\nCancelling transaction..");
        }
    }
}
