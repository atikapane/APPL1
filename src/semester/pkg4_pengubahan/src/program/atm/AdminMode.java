/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semester.pkg4_pengubahan.src.program.atm;

public class AdminMode {

    private static final int TAMBAH_NASABAH = 1;
    private static final int UNBLOCK_NASABAH = 2;
    private static final int LIHAT_UANG_DI_DISPENSER = 3;
    private static final int TAMBAH_UANG_DI_DISPENSER = 4;
    private static final int VALIDASI_DEPOSIT = 5;
    private static final int EXIT = 6;
    private BankDatabase bankDatabase;
    private CashDispenser cashDispenser;
    private Screen screen;
    private Keypad keypad;

    AdminMode(BankDatabase paramBankDatabase, CashDispenser paramCashDispenser) {
        bankDatabase = paramBankDatabase;
        cashDispenser = paramCashDispenser;
        screen = new Screen();
        keypad = new Keypad();
    }

    public void execute() {
        boolean adminExited = false; // user has not chosen to exit

        while (adminExited != true) {
            screen.displayMessageLine("\nAdmin Mode Menu:");
            screen.displayMessageLine("1 - Add new account");
            screen.displayMessageLine("2 - Unblock account");
            screen.displayMessageLine("3 - See money in dispenser");
            screen.displayMessageLine("4 - Add money into dispenser");
            screen.displayMessageLine("5 - Deposit validation");
            screen.displayMessageLine("6 - Exit");
            screen.displayMessage("Enter a choice: ");
            int opt = keypad.getInput();

            if (opt == TAMBAH_NASABAH) {
                screen.displayMessageLine("\nInput (account number, pin, available balance, total balance, account type) with a space in between every input.");
                int addedAccountNumber = keypad.getInput();
                int addedPIN = keypad.getInput();
                double addedAvailableBalance = keypad.getInputDouble();
                double addedTotalBalance = keypad.getInputDouble();
                int tipe = keypad.getInput();
                double limitT = 0.00;
                bankDatabase.tambahNasabah(new Account(addedAccountNumber, addedPIN, addedAvailableBalance, addedTotalBalance, tipe));
            } else if (opt == UNBLOCK_NASABAH) {
                screen.displayMessage("\nUnblock account with account number: ");
                int unblockedNasabahAccountNumber = keypad.getInput();
                bankDatabase.unblockNasabah(unblockedNasabahAccountNumber);
            } else if (opt == LIHAT_UANG_DI_DISPENSER) {
                screen.displayMessageLine("\nThere is " + cashDispenser.getCount() + " $20 bills available.");
            } else if (opt == TAMBAH_UANG_DI_DISPENSER) {
                screen.displayMessage("\nDetermine the number of $20 bills to add: ");
                int added = keypad.getInput();
                cashDispenser.setCount(cashDispenser.getCount() + added);
            } else if (opt == VALIDASI_DEPOSIT) {
                screen.displayMessage("\nValidation is done when adding a deposit\n.");
            } else if (opt == EXIT) {
                screen.displayMessageLine("\nExiting the system...");
                adminExited = true;
            }
        }
    }

    public boolean validate() {
        Keypad keypad = new Keypad();
        screen.displayMessageLine("\nEnter 1 if the letter is valid, otherwise, enter 0: ");
        int choice = keypad.getInput();
        return (choice == 1);
    }
}
