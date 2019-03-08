public class BankDatabase {
   private Account[] accounts; // array of Accounts
   
   
//   BankDataBase db = new BankDatabase();
   
   public BankDatabase() {
      accounts = new Account[3]; // just 2 accounts for testing
      accounts[0] = new Account(12345, 54321, 1000.0, 1200.0);
      accounts[1] = new Account(87654, 45678, 200.0, 200.0);  
      accounts[2] = new Account(00000, 00000, 0.0 , 0.0);
   }
   
   private Account getAccount(int accountNumber) {
       for(int i = 0; i < accounts.length; i++){
           if(accountNumber == this.accounts[i].accountNumber){
               return accounts[i];
           }
       }
       
      return null; // if no matching account was found, return null
   } 
   
//   private int getIndex(int accountNumber){
//       for(int i = 0; i < accounts.length; i++){
//           if(accountNumber == this.accounts[i].accountNumber){
//               return i;
//           }
//       }
//       return -1;       
//   }
   
   

   public boolean authenticateUser(int userAccountNumber, int userPIN) {
      // attempt to retrieve the account with the account number
      Account userAccount = getAccount(userAccountNumber);
//      Account userAccount = new Account();
      
      // if account exists, return result of Account method validatePIN
      if (userAccount != null) {
         return userAccount.validatePIN(userPIN);
      }
      else {
         return false; // account number not found, so return false
      }
   } 
   
//   public int getWrongPIN(int userAccountNumber){
//       return getIndex()
//   }
   
   public double getAvailableBalance(int userAccountNumber) {
      return getAccount(userAccountNumber).getAvailableBalance();
   } 

   public double getTotalBalance(int userAccountNumber) {
      return getAccount(userAccountNumber).getTotalBalance();
   } 

//   public void changeAvaBal(double amount, int userAccountNumber){
//       getAccount(userAccountNumber).changeAvailableBalance(amount);
//   }
//   
//   public void changeToBal(double amount, int userAccountNumber){
//       getAccount(userAccountNumber).changeTotalBalance(amount);
//   }
   
   public void credit(int userAccountNumber, double amount) {
      getAccount(userAccountNumber).credit(amount);
   }

   public void debit(int userAccountNumber, double amount) {
      getAccount(userAccountNumber).debit(amount);
   } 
   
   public void changePIN(int userAccountNumber, int newPin){
       getAccount(userAccountNumber).changePin(newPin);
   }
   
   public void setBlock(int userAccountNumber, boolean block){
       getAccount(userAccountNumber).setBlockStatus(true);
   }
   
   public boolean getBlock(int userAccountNumber){
       return getAccount(userAccountNumber).getBlockStatus();
   }
}