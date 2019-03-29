/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package semester.pkg4_pengubahan.src.program.atm;

import java.util.ArrayList;

class Transfer {
  private final int numFrom;
  private final int numTo;
  private final double value;
  private BankDatabase bankDatabase;
  
  Transfer(BankDatabase bankDatabase, int numFrom, int numTo, double value) {
    this.bankDatabase = bankDatabase;
    this.numFrom = numFrom;
    this.numTo = numTo;
    this.value = value;
  }
  
  public static void errorToSelf() {
    System.out.println("Tidak dapat transfer ke diri sendiri...");
  }
  
  public static void errorAccToNotFound() {
    System.out.println("Akun tujuan tidak ada");
  }
  
  public void execute() {
    if (value <= 0) {
        System.out.println("Value tidak boleh 0 atau kurang");
        return;
    }
    if (value > bankDatabase.getAccount(numFrom).getAvailableBalance()) {
        System.out.println("Uang yang anda punya kurang untuk mentransfer sejumlah value");
        return;
    }
    if (numFrom == numTo) {
      Transfer.errorToSelf();
      return;
    }
    Account accFrom = bankDatabase.getAccount(numFrom);
    Account accTo = bankDatabase.getAccount(numTo);
    if (accTo == null) {
      Transfer.errorAccToNotFound();
      return;
    }
    accFrom.setAvailableBalance(accFrom.getAvailableBalance() - value);
    accFrom.setTotalBalance(accFrom.getTotalBalance() - value);
    accTo.setTotalBalance(accTo.getTotalBalance() + value);
  }
}
