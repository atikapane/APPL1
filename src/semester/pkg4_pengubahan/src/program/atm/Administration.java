/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semester.pkg4_pengubahan.src.program.atm;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author M. Dena Adryan
 */
public class Administration {

    private BankDatabase bankDatabase;
    private AdminMode adminMode;

    public Administration(BankDatabase bankDatabase, AdminMode adminMode) {
        this.bankDatabase = bankDatabase;
        this.adminMode = adminMode;
    }

    public void AdministrationFee() {
        Calendar cal = adminMode.getCallendar();
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        if(dayOfMonth == 1){
            for (Account account : bankDatabase.getAccounts()) {
                account.setMonthlyFeeStatus(0);
            }
        }
        
        if (dayOfMonth == 3) {
            for (Account account : bankDatabase.getAccounts()) {
                if (account.getMonthlyFeeStatus() == 0) {
                    switch (account.getJenis()) {
                        case 1: 
                        
                    ;
                            break;
                        case 2:
                            bankDatabase.credit(account.getAccountNumber(), 1);
                            ;
                            break;
                        case 3:
                            bankDatabase.credit(account.getAccountNumber(), 5);
                            ;
                            break;
                    }
                    account.setMonthlyFeeStatus(1);
                }

            }
        }

    }
}
