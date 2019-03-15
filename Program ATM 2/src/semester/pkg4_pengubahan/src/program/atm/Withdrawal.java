// Withdrawal.java
// Represents a withdrawal ATM transaction

package semester.pkg4_pengubahan.src.program.atm;

public class Withdrawal extends Transaction {
   private int amount; // amount to withdraw
   private Keypad keypad; // reference to keypad
   private CashDispenser cashDispenser; // reference to cash dispenser

   // constant corresponding to menu option to cancel
   private final static int CANCELED = 6;

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

      Screen screen = getScreen(); // get screen reference
      
      // array of amounts to correspond to menu numbers
      int[] amounts = {0, 20, 40, 60, 100, 200};

      // loop while no valid choice has been made
      while (userChoice == 0) {
         // display the withdrawal menu
         screen.displayMessageLine("\nWithdrawal Menu:");
         screen.displayMessageLine("1 - $20");
         screen.displayMessageLine("2 - $40");
         screen.displayMessageLine("3 - $60");
         screen.displayMessageLine("4 - $100");
         screen.displayMessageLine("5 - $200");
         screen.displayMessageLine("6 - Cancel transaction");
         screen.displayMessage("\nChoose a withdrawal amount: ");

         // determine how to proceed based on the input value
         int input = keypad.getInput(); // get user input through keypad
         userChoice = (input == 6 ? CANCELED : amounts[input]);
         if (input >= 1 && input <= 5) {
             screen.displayMessageLine("Your cash has been dispensed. Please take your cash now.");
         } else if (input == CANCELED) {
             screen.displayMessageLine("Cancelling transaction..");
             return CANCELED;
         } else {
            screen.displayMessageLine("\nInvalid selection. Try again.");
            continue;
         }
      } 
      return userChoice; // return withdrawal amount or CANCELED
   }

   // perform transaction
   @Override
   public void execute() {
       int amountToWithdraw = displayMenuOfAmounts();
       if (amountToWithdraw != CANCELED) {
         super.getBankDatabase().getAccount(super.getAccountNumber()).credit((double) amountToWithdraw);
         cashDispenser.dispenseCash(amount);
       }
   }
} 