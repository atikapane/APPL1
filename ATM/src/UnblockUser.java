/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author M. Dena Adryan
 */
public class UnblockUser extends Transaction{
        private Keypad keypad;
    
public UnblockUser(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad) {

      // initialize superclass variables
      
      super(userAccountNumber, atmScreen, atmBankDatabase);
       keypad = atmKeypad;
   
   } 

    @Override
    public void execute() {
        Screen screen = getScreen();
        screen.displayMessageLine("Please input user account number to be unblocked");
        int input = keypad.getInput();
        
        bankDatabase.setBlock(input, false);
        
        screen.displayMessageLine("The user status has been unblocked");
    }
}
