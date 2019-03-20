package semester.pkg4_pengubahan.src.program.atm;

public class Screen {
    // display a message without a carriage return

    public void displayMessage(String message) {
        System.out.print(message);
    }

    // display a message with a carriage return
    public void displayMessageLine(String message) {
        System.out.println(message);
    }

    // displays a dollar amount
    public void displayDollarAmount(double amount) {
        System.out.printf("$%,.2f", amount);
    }

    void displayMessageLine(Account accFrom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
