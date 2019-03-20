/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semester.pkg4_pengubahan.src.program.atm;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AdminMode {

    private static final int ADD_ACCOUNT = 1;
    private static final int UNBLOCK_ACCOUNT = 2;
    private static final int VIEW_DISPENSER_BALANCE = 3;
    private static final int ADD_DISPENSER_BALANCE = 4;
    private static final int DEPOSIT_VALIDATION = 5;
    private static final int CHANGE_DATE = 6;
    private static final int EXIT = 7;

    public Calendar calendar = Calendar.getInstance();
    public Date date = new Date();
    public final DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");

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

    public void execute() throws ParseException {

        int opt = 0;
        while (opt != EXIT) {
            opt = displayAdminMenu();

            if (opt == ADD_ACCOUNT) {
                newAccountData();

            } else if (opt == UNBLOCK_ACCOUNT) {
                screen.displayMessage("\nEnter account number you want to "
                        + "unblock: ");
                int unblockedNasabahAccountNumber = keypad.getInput();
                bankDatabase.unblockNasabah(unblockedNasabahAccountNumber);
                screen.displayMessageLine("\nAccount unblocked.");

            } else if (opt == VIEW_DISPENSER_BALANCE) {
                screen.displayMessageLine("\nThere are "
                        + cashDispenser.getCount() + " cash of $20 bills "
                                + "available\n");

            } else if (opt == ADD_DISPENSER_BALANCE) {
                screen.displayMessage("\nSpecify the amount of $20 to be "
                        + "added :");
                int added = keypad.getInput();
                cashDispenser.setCount(cashDispenser.getCount() + added);
                screen.displayMessageLine("\nSuccessfully added to dispenser."
                        + "\n");

            } else if (opt == DEPOSIT_VALIDATION) {
                screen.displayMessageLine("\nValidation will be done when "
                        + "deposit occure.\n");

            } else if (opt == CHANGE_DATE) {
                String dateNow;
                
                screen.displayMessage("\nSpecify the current date.");
                screen.displayMessage("\nInput date in format (dd-mm-yyyy): ");
                dateNow = keypad.getInputStrings();
                date = dateFormat.parse(dateNow);
                screen.displayMessageLine("\nDate has been changed.");

            } else {
                screen.displayMessageLine("\nInvalid selection.\n");
            }
        }
    }

    //validating deposit envelope
    public boolean validate() {
        Keypad keypad = new Keypad();
        screen.displayMessage("\nInput 1 if the envelope is valid, 0 if not: ");
        int choice = keypad.getInput();
        return (choice == 1);
    }

    /**
     * @return the calendar
     */
    public Calendar getCallendar() {
        return calendar;
    }

    private int displayAdminMenu() {
        screen.displayMessageLine("Admin Mode Menu");
        screen.displayMessageLine("1 - Add Account");
        screen.displayMessageLine("2 - Unblock Account");
        screen.displayMessageLine("3 - View Dispenser Balance");
        screen.displayMessageLine("4 - Add Dispenser Balance");
        screen.displayMessageLine("5 - Deposit Validation");
        screen.displayMessageLine("6 - Change Date");
        screen.displayMessageLine("7 - Exit");
        screen.displayMessage("Choose option: ");
        return keypad.getInput(); // return user's selection
    }

    private void newAccountData() {
        screen.displayMessage("\nEnter account number: ");
        int addedAccountNumber = keypad.getInput();

        screen.displayMessage("Enter PIN           : ");
        int addedPIN = keypad.getInput();

        screen.displayMessage("Enter balance       : ");
        double addedTotalBalance = keypad.getInputDouble();
        double addedAvailableBalance = addedTotalBalance + 
                ((addedTotalBalance / 100) * 20);

        screen.displayMessageLine("\nAccount type 1(Siswa), "
                + "2(Bisnis), 3(Masa Depan)");
        screen.displayMessage("Enter account type  : ");
        int type = keypad.getInput();

        bankDatabase.addAccount(new Account(addedAccountNumber, addedPIN, 
                addedAvailableBalance, addedTotalBalance, type, 0));
        screen.displayMessage("Account has been successfully added.");
    }

    public Date getDate() {
        return date;
    }
}