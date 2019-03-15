package semester.pkg4_pengubahan.src.program.atm;

import java.util.Scanner;

public class Keypad {

    private Scanner input; // reads data from the command line

    public Keypad() {
        input = new Scanner(System.in);
    }

    public int getInput() {
        return input.nextInt(); // user enters an integer
    }

    public double getInputDouble() {
        return input.nextDouble();
    }
    
    public String getInputString(){
        return input.nextLine();
    }
}
