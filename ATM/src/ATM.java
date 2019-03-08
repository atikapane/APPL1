public class ATM {
   private boolean userAuthenticated; // whether user is authenticated
   private int currentAccountNumber; // current user's account number
   private Screen screen; // ATM's screen
   private Keypad keypad; // ATM's keypad
   private CashDispenser cashDispenser; // ATM's cash dispenser
   private DepositSlot depositSlot;
//   private int counter;
//   private int userNumber;
   private BankDatabase bankDatabase; // account information database

   // constants corresponding to main menu options
   private static final int BALANCE_INQUIRY = 1;
   private static final int WITHDRAWAL = 2;
   private static final int DEPOSIT = 3;
   private static final int EXIT = 5;
   private static final int CHANGE_PIN = 4;
   private static final int UNBLOCK_USER = 6;
   private static final int CHECK_DISPENSER_BILLS = 7;
   private static final int ADD_DISPENSER_BILLS = 8;
   

   // no-argument ATM constructor initializes instance variables
   public ATM() {
      userAuthenticated = false; // user is not authenticated to start
      currentAccountNumber = 0; // no current account number to start
      screen = new Screen(); // create screen
      keypad = new Keypad(); // create keypad 
      cashDispenser = new CashDispenser(); // create cash dispenser
      bankDatabase = new BankDatabase(); // create acct info database
   }

   // start ATM 
   public void run() {
      // welcome and authenticate user; perform transactions
      while (true) {
         // loop while user is not yet authenticated
         while (!userAuthenticated ) {
            screen.displayMessageLine("\nWelcome!");       
            authenticateUser(); // authenticate user
         }
         
         if(currentAccountNumber == 00000){
             performTransactionsAdmin();
         }else{
            performTransactions(); // user is now authenticated
         }
         userAuthenticated = false; // reset before next ATM session
         currentAccountNumber = 0; // reset before next ATM session
         screen.displayMessageLine("\nThank you! Goodbye!");
      }
   }

   // attempts to authenticate user against database
   private void authenticateUser() {
      screen.displayMessage("\nPlease enter your account number: ");
      int accountNumber = keypad.getInput(); // input account number
      if(bankDatabase.getBlock(accountNumber)){
          screen.displayMessageLine("Your account has been blocked");
      }else{
      
      for(int counter = 0; counter < 3; counter++){
            screen.displayMessage("\nEnter your PIN: "); // prompt for PIN
            int pin = keypad.getInput(); // input PIN
      
             // set userAuthenticated to boolean value returned by database
            userAuthenticated = 
                bankDatabase.authenticateUser(accountNumber, pin);
            // check whether authentication succeeded
            if (userAuthenticated) {
              currentAccountNumber = accountNumber; // save user's account #
              return;
            } 
            else {
                screen.displayMessageLine(
             "Invalid account number or PIN. Please try again.");
                if(counter == 2){
                    bankDatabase.setBlock(accountNumber, true);
                }
            } 
      }
      }
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
         switch (mainMenuSelection) {
            // user chose to perform one of three transaction types
            case BALANCE_INQUIRY:         
              
               // initialize as new object of chosen type
               currentTransaction = 
                  createTransaction(mainMenuSelection);

               currentTransaction.execute(); // execute transaction
               break; 
               
            case WITHDRAWAL:
                
                currentTransaction =
                        createTransaction(mainMenuSelection);
                
                currentTransaction.execute();
                break;
            
            case DEPOSIT:
                
                currentTransaction =
                        createTransaction(mainMenuSelection);
                
                currentTransaction.execute();
                break;
                
            case CHANGE_PIN:
                
                currentTransaction =
                        createTransaction(mainMenuSelection);
                
                currentTransaction.execute();
                break;
                
            case EXIT: // user chose to terminate session
               screen.displayMessageLine("\nExiting the system...");
               userExited = true; // this ATM session should end
               break;
            default: // 
               screen.displayMessageLine(
                  "\nYou did not enter a valid selection. Try again.");
               break;
         }
      } 
   } 
   
   private void performTransactionsAdmin() {
      // local variable to store transaction currently being processed
      Transaction currentTransaction = null;
      
      boolean userExited = false; // user has not chosen to exit

      // loop while user has not chosen option to exit system
      while (!userExited) {
         // show main menu and get user selection
         int mainMenuSelection = displayMainMenuAdmin();
         int input;
         // decide how to proceed based on user's menu selection
         switch (mainMenuSelection) {
            // user chose to perform one of three transaction types
            
               
            case UNBLOCK_USER:
                
//                currentTransaction =
//                        createTransaction(mainMenuSelection);
//                
//                currentTransaction.execute();
                
//                Screen screen = getScreen();
            screen.displayMessageLine("Please input user account number to be unblocked");
             input = keypad.getInput();
        
             bankDatabase.setBlock(input, false);
        
                 screen.displayMessageLine("The user status has been unblocked");
                
               
                break;
                
            case CHECK_DISPENSER_BILLS:         
                int count = cashDispenser.getBillsCount();
                screen.displayMessageLine("There are " + count + " bills remaining");
                
              
               break; 
               
            case ADD_DISPENSER_BILLS:                       
                screen.displayMessageLine("Please insert bills");
                input = keypad.getInput();
                cashDispenser.changeBillsBy(input);
                screen.displayMessageLine("Your bills added succesfully");
                
               break;
            
                
            case EXIT: // user chose to terminate session
               screen.displayMessageLine("\nExiting the system...");
               userExited = true; // this ATM session should end
               break;
            default: // 
               screen.displayMessageLine(
                  "\nYou did not enter a valid selection. Try again.");
               break;
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
      screen.displayMessageLine("5 - Exit\n");
      screen.displayMessage("Enter a choice: ");
      return keypad.getInput(); // return user's selection
   } 
   
    private int displayMainMenuAdmin() {
      screen.displayMessageLine("\nMain menu:");
      screen.displayMessageLine("6 - Unblock account");
      screen.displayMessageLine("7 - Check dispenser bills");
//      screen.displayMessageLine("3 - Deposit funds");
//      screen.displayMessageLine("4 - Change PIN");
//      screen.displayMessageLine("5 - Exit\n");
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
         
         case WITHDRAWAL:
            temp = new Withdrawal(currentAccountNumber, screen, bankDatabase, keypad, cashDispenser);
            break;
             
         case DEPOSIT:
             depositSlot = new DepositSlot();
             temp = new Deposit(currentAccountNumber, screen, bankDatabase, keypad, depositSlot);
             break;
             
         case CHANGE_PIN:
             temp = new ChangePIN(currentAccountNumber, screen, bankDatabase, keypad);
             break;
             
//         case UNBLOCK_USER:
//             temp = new UnblockUser(currentAccountNumber, screen, bankDatabase, keypad);
//             break;
      }

      return temp;
   } 
}