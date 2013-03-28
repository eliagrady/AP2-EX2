/**
 * Advanced Programming 2 - Ex 2
 * Author: Elia Grady
 * ID: 300907060
 * Course: Advanced Programming 2
 * T.A.: Igor Rochlin
 * Represents the Main function.
 * Creates a controller and run it's predefined sequence
 */

package controller;

public class Main {
    public static void main(String[] args) {
        // Open program - load configuration to DB
        Controller controller = Controller.getInstance();
        try {
            controller.getDatabaseInstance().restoreState();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("failed fetching data from Database. starting fresh");
        }
        try {
            controller.mainSequence();
        } catch (Throwable e) {
            System.out.print("Oops, program crashed!");
            try {
                // Close program - configuration saved to DB by the controller
                controller.getDatabaseInstance().saveState();
                System.out.println("Don't worry, changes saved!");
            } catch (Throwable e1) {
                System.out.println("Error saving database! changes were lost =( ");
            }
        }
        //saving done by exiting correctly
    }
}
