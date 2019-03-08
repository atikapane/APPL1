// Withdrawal.java
// Represents a withdrawal ATM transaction

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
      
      super(userAccountNumber, atmScreen, atmBankDatabase); 
       
       keypad = atmKeypad;
      cashDispenser = atmCashDispenser;
      // initialize superclass variables
   }

   // perform transaction
   @Override
   public void execute() {
       displayMenuOfAmounts();
       
       
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

         int input = keypad.getInput(); // get user input through keypad

         // determine how to proceed based on the input value
         double availableBalance = 
         bankDatabase.getAvailableBalance(getAccountNumber());
         

         
         switch (input) {
            
            case 1: 
                if(cashDispenser.isSufficientCashAvailable(20) && availableBalance >= 20 ){
                    cashDispenser.dispenseCash(20);
//                    bankDatabase.changeAvaBal(20, getAccountNumber());
//                    bankDatabase.changeToBal(20, getAccountNumber());
                    bankDatabase.credit(getAccountNumber(), 20);
                    System.out.println("Your cash has been dispensed. Please take your cash now.");
                } // if the user chose a withdrawal amount 
                userChoice = amounts[input]; // save user's choice
                ; break;
            case 2: if(cashDispenser.isSufficientCashAvailable(40) && availableBalance >= 40){
                    cashDispenser.dispenseCash(40);
//                    bankDatabase.changeAvaBal(40, getAccountNumber());
//                    bankDatabase.changeToBal(40, getAccountNumber());
                    bankDatabase.credit(getAccountNumber(), 40);
                     System.out.println("Your cash has been dispensed. Please take your cash now.");
            } // (i.e., chose option 1, 2, 3, 4 or 5), return the
             userChoice = amounts[input]; // save user's choice               
            ; break;

            case 3: if(cashDispenser.isSufficientCashAvailable(60) && availableBalance >= 60){
                    cashDispenser.dispenseCash(60);
//                    bankDatabase.changeAvaBal(60, getAccountNumber());
//                    bankDatabase.changeToBal(60, getAccountNumber());
                    bankDatabase.credit(getAccountNumber(), 60);
                    System.out.println("Your cash has been dispensed. Please take your cash now.");
            }// corresponding amount from amounts array
             userChoice = amounts[input]; // save user's choice              
            ; break;

            case 4: if(cashDispenser.isSufficientCashAvailable(100) && availableBalance >= 100){
                    cashDispenser.dispenseCash(100);
//                    bankDatabase.changeAvaBal(100, getAccountNumber());
//                    bankDatabase.changeToBal(100, getAccountNumber());
                    bankDatabase.credit(getAccountNumber(), 100);
                    System.out.println("Your cash has been dispensed. Please take your cash now.");
            }
             userChoice = amounts[input]; // save user's choice
                            ; break;

            case 5: if(cashDispenser.isSufficientCashAvailable(200) && availableBalance >= 200){
                    cashDispenser.dispenseCash(200);
//                    bankDatabase.changeAvaBal(200, getAccountNumber());
//                    bankDatabase.changeToBal(200, getAccountNumber());
                    bankDatabase.credit(getAccountNumber(), 200);
                    System.out.println("Your cash has been dispensed. Please take your cash now.");
            }

               userChoice = amounts[input]; // save user's choice
               ;break;       
            case CANCELED: // the user chose to cancel
               userChoice = CANCELED; // save user's choice
               break;
            default: // the user did not enter a value from 1-6
               screen.displayMessageLine(
                  "\nInvalid selection. Try again.");
         } 
      } 

      return userChoice; // return withdrawal amount or CANCELED
   }
} 