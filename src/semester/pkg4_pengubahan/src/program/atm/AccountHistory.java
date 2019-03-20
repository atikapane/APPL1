/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semester.pkg4_pengubahan.src.program.atm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Tiara Lestari
 */
public class AccountHistory {

    private String transactionType = new String();
    private Date date;    
    private Screen screen;
    private double amount;

    public AccountHistory(String type, double amount, Date date) {
        this.transactionType = type;
        this.amount = amount;
        this.date = date;
    }
    
    public AccountHistory(String type, double amount) {
        this.transactionType = type;
        this.amount = amount;
    }

    public String getType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    /**
     * @return the calendar
     */
    public Date getDate() {
        return date;
    }
    
    

}