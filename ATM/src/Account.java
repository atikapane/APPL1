public class Account {
   public int accountNumber; // account number
   private int pin; // PIN for authentication
   private double availableBalance; // funds available for withdrawal
   private double totalBalance; // funds available & pending deposits
   private boolean blocked;

   // Account constructor initializes attributes
   public Account(int theAccountNumber, int thePIN, 
      double theAvailableBalance, double theTotalBalance) {
      accountNumber = theAccountNumber;
      pin = thePIN;
      availableBalance = theAvailableBalance;
      totalBalance = theTotalBalance;
      blocked = false;
   }
   
   


   // determines whether a user-specified PIN matches PIN in Account
   public boolean validatePIN(int userPIN) {
      if (userPIN == pin) {
         return true;
      }
      else {
         return false;
      }
   } 

   // returns available balance
   public double getAvailableBalance() {
      return availableBalance;
   } 
   

   
   // returns the total balance
   public double getTotalBalance() {
      return totalBalance;
   } 

   public void credit(double amount) {
     totalBalance -= amount;
     availableBalance -= amount;
   }

   public void debit(double amount) {
      totalBalance += amount;
   }
   
   public void changePin(int newPin){
       pin = newPin;
   }

   public int getAccountNumber() {
      return accountNumber;  
   }
   
   public void setBlockStatus(boolean status){
       blocked = status;
   }
   
   public boolean getBlockStatus(){
       return blocked;
   }
   
   

    /**
     * @param availableBalance the availableBalance to set
     */
//    public void changeAvailableBalance(double availableBalance) {
//        this.availableBalance = this.availableBalance - availableBalance;
//    }

    /**
     * @param totalBalance the totalBalance to set
     */
//    public void changeTotalBalance(double totalBalance) {
//        this.totalBalance = this.totalBalance - totalBalance;
//    }
} 