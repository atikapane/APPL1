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

    Transfer(BankDatabase bankDatabase, int numFrom, int numTo, double value) {
        this.bankDatabase = bankDatabase;
        this.numFrom = numFrom;
        this.numTo = numTo;
        this.value = value;
    }

    public void execute() {
        Screen screen = new Screen();
        assert (numFrom != numTo);
        Account accFrom = bankDatabase.getAccount(numFrom);
        Account accTo = bankDatabase.getAccount(numTo);
        assert (accFrom != null);
        assert (accTo != null);
        if (accFrom.getJenis() == 2) {
            if (accFrom.getTransferLimit() < 10000) {
                accFrom.setTransferLimit(value);
                accFrom.setAvailableBalance(accFrom.getAvailableBalance() - value);
                accFrom.setTotalBalance(accFrom.getTotalBalance() - value);
                accTo.setTotalBalance(accTo.getTotalBalance() + value);
            }else screen.displayMessageLine("\nYou have reach your transfer limit.");
        } else if (accFrom.getJenis() == 3) {
            if (accFrom.getTransferLimit() < 500) {
                accFrom.setTransferLimit(value);
                accFrom.setAvailableBalance(accFrom.getAvailableBalance() - value);
                accFrom.setTotalBalance(accFrom.getTotalBalance() - value);
                accTo.setTotalBalance(accTo.getTotalBalance() + value);
            }else screen.displayMessageLine("\nYou have reach your transfer limit.");
        } else screen.displayMessageLine("Your account cannot do this action");
    }
}
