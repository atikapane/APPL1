/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semester.pkg4_pengubahan.src.program.atm;

public class BalanceInquiry extends Transaction {
    // BalanceInquiry constructor

    public BalanceInquiry(int userAccountNumber, Screen atmScreen,
            BankDatabase atmBankDatabase) {

        super(userAccountNumber, atmScreen, atmBankDatabase);
    }

    // performs the transaction
    @Override
    public void execute() {
        // get references to bank database and screen
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();

        // get the available balance for the account involved
        double availableBalance
                = bankDatabase.getAvailableBalance(getAccountNumber());

        // get the total balance for the account involved
        double totalBalance
                = bankDatabase.getTotalBalance(getAccountNumber());

        double monthlyFee
                = bankDatabase.getMonthlyFee(getAccountNumber());

        screen.displayMessageLine("\nBalance Information:");
        screen.displayMessage(" - Available balance: ");
        screen.displayDollarAmount(availableBalance);
        screen.displayMessage("\n - Total balance    : ");
        screen.displayDollarAmount(totalBalance);
        screen.displayMessage("\n - Monthly fee      : ");
        screen.displayDollarAmount(monthlyFee);
        screen.displayMessageLine("");
    }
}
