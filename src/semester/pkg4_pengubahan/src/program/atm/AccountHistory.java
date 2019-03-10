/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semester.pkg4_pengubahan.src.program.atm;

import java.util.ArrayList;

/**
 *
 * @author Tiara Lestari
 */
public class AccountHistory {
    private String transactionType = new String();
    private double amount;
    
    public AccountHistory(String type, double amount){
        this.transactionType = type;
        this.amount = amount;
    }
}
