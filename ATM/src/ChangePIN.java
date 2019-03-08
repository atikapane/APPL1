/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author M. Dena Adryan
 */
public class ChangePIN extends Transaction{
    private Keypad keypad;
    
public ChangePIN(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad) {

      // initialize superclass variables
      
      super(userAccountNumber, atmScreen, atmBankDatabase);
       keypad = atmKeypad;
   
   } 

    @Override
    public void execute() {
        Screen screen = getScreen();
        boolean done = false;
        
        screen.displayMessageLine("Please input your current PIN");
        int input = keypad.getInput();
        if(bankDatabase.authenticateUser(getAccountNumber(), input)){
            screen.displayMessageLine("Please input your new PIN");
            input = keypad.getInput();
            int length = new Integer(input).toString().length();
            while(length != 5){
                screen.displayMessage("The length of PIN must be 5 digits");
                screen.displayMessageLine("Please input your new PIN");
                input = keypad.getInput();
                length = new Integer(input).toString().length();
            }
            screen.displayMessageLine("Please input again your new PIN");
            int input1 = keypad.getInput();
            if(input != input1){
                 screen.displayMessageLine("Please input the same new PIN");
            }else{
                bankDatabase.changePIN(getAccountNumber(), input);
                screen.displayMessageLine("Your PIN has been changed\n");
            }
        }
                
        
        
    }
}
