/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semester.pkg4_pengubahan.src.program.atm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class BankDatabaseTest {
  
  public BankDatabaseTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of getAccount method, of class BankDatabase.
   */
  @Test
  public void testGetAccount() {
    System.out.println("getAccount");
    int accountNumber = 0;
    BankDatabase instance = new BankDatabase();
    Account expResult = null;
    Account result = instance.getAccount(accountNumber);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of authenticateUser method, of class BankDatabase.
   */
  @Test
  public void testAuthenticateUser() {
    System.out.println("authenticateUser");
    int userAccountNumber = 0;
    int userPIN = 0;
    BankDatabase instance = new BankDatabase();
    boolean expResult = false;
    boolean result = instance.authenticateUser(userAccountNumber, userPIN);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getAvailableBalance method, of class BankDatabase.
   */
  @Test
  public void testGetAvailableBalance() {
    System.out.println("getAvailableBalance");
    int userAccountNumber = 0;
    BankDatabase instance = new BankDatabase();
    double expResult = 0.0;
    double result = instance.getAvailableBalance(userAccountNumber);
    assertEquals(expResult, result, 0.0);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getMonthlyFee method, of class BankDatabase.
   */
  @Test
  public void testGetMonthlyFee() {
    System.out.println("getMonthlyFee");
    int userAccountNumber = 0;
    BankDatabase instance = new BankDatabase();
    double expResult = 0.0;
    double result = instance.getMonthlyFee(userAccountNumber);
    assertEquals(expResult, result, 0.0);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getTotalBalance method, of class BankDatabase.
   */
  @Test
  public void testGetTotalBalance() {
    System.out.println("getTotalBalance");
    int userAccountNumber = 0;
    BankDatabase instance = new BankDatabase();
    double expResult = 0.0;
    double result = instance.getTotalBalance(userAccountNumber);
    assertEquals(expResult, result, 0.0);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of credit method, of class BankDatabase.
   */
  @Test
  public void testCredit() {
    System.out.println("credit");
    int userAccountNumber = 0;
    double amount = 0.0;
    BankDatabase instance = new BankDatabase();
    instance.credit(userAccountNumber, amount);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of debit method, of class BankDatabase.
   */
  @Test
  public void testDebit() {
    System.out.println("debit");
    int userAccountNumber = 0;
    double amount = 0.0;
    BankDatabase instance = new BankDatabase();
    instance.debit(userAccountNumber, amount);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of changePIN method, of class BankDatabase.
   */
  @Test
  public void testChangePIN() throws FileNotFoundException {
    System.out.println("changePIN");
    FileInputStream fis = new FileInputStream("test/ubah_pin.txt");
    BankDatabase bd = new BankDatabase();
    System.setIn(fis);
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      int accNum = sc.nextInt();
      int nextPin = sc.nextInt();
      int curPin = bd.getAccount(accNum).getPIN();
      assert(bd.getAccount(accNum) != null);
      bd.changePIN(accNum, nextPin);
      assertEquals(nextPin, bd.getAccount(accNum).getPIN());
    }
  }

  /**
   * Test of blockAccount method, of class BankDatabase.
   */
  @Test
  public void testBlockAccount() {
    System.out.println("blockAccount");
    int currentAccountNumber = 0;
    BankDatabase instance = new BankDatabase();
    instance.blockAccount(currentAccountNumber);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of addAccount method, of class BankDatabase.
   */
  @Test
  public void testAddAccount() {
    System.out.println("addAccount");
    Account addedNasabah = null;
    BankDatabase instance = new BankDatabase();
    instance.addAccount(addedNasabah);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of unblockNasabah method, of class BankDatabase.
   */
  @Test
  public void testUnblockNasabah() {
    System.out.println("unblockNasabah");
    int unblockedNasabahAccountNumber = 0;
    BankDatabase instance = new BankDatabase();
    instance.unblockNasabah(unblockedNasabahAccountNumber);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getAccounts method, of class BankDatabase.
   */
  @Test
  public void testGetAccounts() {
    System.out.println("getAccounts");
    BankDatabase instance = new BankDatabase();
    ArrayList<Account> expResult = null;
    ArrayList<Account> result = instance.getAccounts();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
