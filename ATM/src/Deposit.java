public class Deposit extends Transaction {
   private double amount; // amount to deposit
   private Keypad keypad; // reference to keypad
   private DepositSlot depositSlot; // reference to deposit slot
   private final static int CANCELED = 0; // constant for cancel option

   // Deposit constructor
   public Deposit(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad, 
      DepositSlot atmDepositSlot) {

      // initialize superclass variables
      
      super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
        depositSlot = atmDepositSlot;
   } 

   // perform transaction
   @Override
   public void execute() {
      
      double nilai = promptForDepositAmount();
      
       if(nilai != 0){
            System.out.println("Please insert a deposit envelope containing $"+ nilai/100 + "0.");
//            bankDatabase.changeToBal(-nilai, getAccountNumber());
              bankDatabase.debit(getAccountNumber(), -nilai);
            
            if(depositSlot.isEnvelopeReceived()){
                System.out.println("Your envelope has been received. \nNote: The money just deposited will not be available\nuntil we verify the amount of any enclosed cash and your\nchecks clear.");
            }
        }else{
           System.out.println("Canceling transaction...");
       }
    }

   // prompt user to enter a deposit amount in cents 
   private double promptForDepositAmount() {
      Screen screen = getScreen(); // get reference to screen

      // display the prompt
      screen.displayMessage("\nPlease enter a deposit amount in " + 
         "CENTS (or 0 to cancel): ");
      int input = keypad.getInput(); // receive input of deposit amount
      
      // check whether the user canceled or entered a valid amount
      if (input == 0) {
         return 0;
      }
      else {
//         return (double) input / 100; // return dollar amount
        return (double) input;
      }
   }
} 
