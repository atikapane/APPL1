/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package semester.pkg4_pengubahan.src.program.atm;

class Transfer {
  private final int numFrom;
  private final int numTo;
  private final double value;
  private BankDatabase bankDatabase;
  private double biaya;
  
  Transfer(BankDatabase bankDatabase, int numFrom, int numTo, double value, double biaya) {
    this.bankDatabase = bankDatabase;
    this.numFrom = numFrom;
    this.numTo = numTo;
    this.value = value;
    this.biaya = biaya;
  }
  
  public void execute() {
    assert(numFrom != numTo);
    Account accFrom = bankDatabase.getAccount(numFrom);
    Account accTo = bankDatabase.getAccount(numTo);
    assert(accFrom != null);
    assert(accTo != null);
    accFrom.setAvailableBalance(accFrom.getAvailableBalance() - this.value - this.biaya);
    accFrom.setTotalBalance(accFrom.getTotalBalance() - this.value - this.biaya);
    accTo.setTotalBalance(accTo.getTotalBalance() + value);
  }
}
