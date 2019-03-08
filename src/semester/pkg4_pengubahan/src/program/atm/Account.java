/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package semester.pkg4_pengubahan.src.program.atm;

public class Account {
   private int accountNumber; // account number
   private int pin; // PIN for authentication
   private double availableBalance; // funds available for withdrawal
   private double totalBalance; // funds available & pending deposits
   private boolean blocked;
   private int jenis;
        //   private int SISWA = 1;
        //   private int BISNIS = 2;
        //   private int MASA_DEPAN = 3;
   private double limitTarik = 0;
   private double transferLimit = 0;

   // Account constructor initializes attributes
   public Account(int theAccountNumber, int thePIN, 
      double theAvailableBalance, double theTotalBalance, int tipe) {
      this.accountNumber = theAccountNumber;
      this.availableBalance = theAvailableBalance;
      this.totalBalance = theTotalBalance;
      this.pin = thePIN;
      this.blocked = false;
      
      //tipe == 1 then siswa
      //tipe == 2 then bisnis
      //tipe == 3 then masa_depan
      this.jenis = tipe;
   }

   // determines whether a user-specified PIN matches PIN in Accountsss
   public boolean validatePIN(int userPIN) {
      if (userPIN == pin) {
         return true;
      } else {
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
   
   public int getJenis(){
       return this.jenis;
   }
   
   public double getTransferLimit(){
       return this.transferLimit;
   }
   
   public void setTransferLimit(double x){
       this.transferLimit += x;               
   }
   
   public void setAvailableBalance(double x){
     this.availableBalance = x;
   }
   
   public void setTotalBalance(double x){
     this.totalBalance = x;
   }

   public void credit(double amount) {
     this.availableBalance -= amount;
     this.totalBalance -= amount;
   }

   public void debit(double amount) {
     this.availableBalance += amount;
     this.totalBalance += amount;
   }

   public int getAccountNumber() {
      return accountNumber;  
   }
   
   public void setPIN(int pinToSet) {
     pin = pinToSet;
   }
   
   public boolean isBlocked() {
     return blocked;
   }
   
   public void setBlocked(boolean value) {
     blocked = value;
   }
   
   public double getLimitTarik() {
       return limitTarik;
   }
   
   public void setLimitTarik(double limitT) {
       this.limitTarik += limitT;
   }
}