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
  private BankDatabase bankDatabase;
  
  AdminMode(BankDatabase paramBankDatabase) {
    bankDatabase = paramBankDatabase;
  }
  
  public void execute() {
    Screen screen = new Screen();
    screen.displayMessageLine("Admin Mode Menu");
    screen.displayMessageLine("1. Tambah nasabah");
    screen.displayMessageLine("2. Unblock nasabah");
    screen.displayMessageLine("3. Lihat uang di dispenser");
    screen.displayMessageLine("4. Tambah uang di dispenser");
    screen.displayMessageLine("5. Validasi deposit");
    screen.displayMessage("Choose option: ");
    Keypad keypad = new Keypad();
    int opt = keypad.getInput();
    if (opt == TAMBAH_NASABAH) {
      screen.displayMessageLine("Input (account number, pin, available balance, total balance) terpisah dengan spasi");
      int addedAccountNumber = keypad.getInput();
      int addedPIN = keypad.getInput();
      double addedAvailableBalance = keypad.getInputDouble();
      double addedTotalBalance = keypad.getInputDouble();
      bankDatabase.tambahNasabah(new Account(addedAccountNumber, addedPIN, addedAvailableBalance, addedTotalBalance));
    } else if (opt == UNBLOCK_NASABAH) {
      screen.displayMessage("Unblock nasabah dengan accountNumber: ");
      int unblockedNasabahAccountNumber = keypad.getInput();
      bankDatabase.unblockNasabah(unblockedNasabahAccountNumber);
    } else if (opt == LIHAT_UANG_DI_DISPENSER) {
      
    } else if (opt == TAMBAH_UANG_DI_DISPENSER) {
      
    } else if (opt == VALIDASI_DEPOSIT) {
      
    } else {
      
    }
  }
}
