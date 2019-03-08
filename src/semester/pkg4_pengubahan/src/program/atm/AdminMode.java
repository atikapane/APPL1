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
    screen.displayMessageLine("Admin Mode Menu");
    screen.displayMessageLine("1. Tambah nasabah");
    screen.displayMessageLine("2. Unblock nasabah");
    screen.displayMessageLine("3. Lihat uang di dispenser");
    screen.displayMessageLine("4. Tambah uang di dispenser");
    screen.displayMessageLine("5. Validasi deposit");
    screen.displayMessage("Choose option: ");
    int opt = keypad.getInput();
    if (opt == TAMBAH_NASABAH) {
      screen.displayMessageLine("Input (account number, pin, available balance, total balance) terpisah dengan spasi");
      int addedAccountNumber = keypad.getInput();
      int addedPIN = keypad.getInput();
      double addedAvailableBalance = keypad.getInputDouble();
      double addedTotalBalance = keypad.getInputDouble();
      int tipe = keypad.getInput();
      double limitT = 0.00;
      bankDatabase.tambahNasabah(new Account(addedAccountNumber, addedPIN, addedAvailableBalance, addedTotalBalance, tipe));
    } else if (opt == UNBLOCK_NASABAH) {
      screen.displayMessage("Unblock nasabah dengan accountNumber: ");
      int unblockedNasabahAccountNumber = keypad.getInput();
      bankDatabase.unblockNasabah(unblockedNasabahAccountNumber);
    } else if (opt == LIHAT_UANG_DI_DISPENSER) {
      screen.displayMessageLine("There is " + cashDispenser.getCount() + " uang 20 perak available");
    } else if (opt == TAMBAH_UANG_DI_DISPENSER) {
      screen.displayMessage("Tentukan jumlah uang 20 perak untuk ditambahkan: ");
      int added = keypad.getInput();
      cashDispenser.setCount(cashDispenser.getCount() + added);
    } else {
      // asumsiin aja si admin nginput valid lah, biar gampang...
    }
  }
  
  public boolean validate() {
    Keypad keypad = new Keypad();
    screen.displayMessageLine("\nInput 1 jika surat valid, 0 sebaliknya: ");
    int choice = keypad.getInput();
    return (choice == 1);
  }
}
