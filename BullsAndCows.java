import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
public class BullsAndCows {

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static void main(String args[]) {

        final int MIN = 123; //minimum value of random
        final int MAX = 10000; // maximum value of random
        final int GAME_OVER = 4; // number of bulls for winning the game
        String number; // keep the input number from the user
        String rand = String.format("%04d", getRandomNumber(MIN, MAX)); // randomized number and add leading zeroes if necessary
        List<InputGuess> guessesList = new ArrayList<>(); // keep all the guesses in list to present the results
        int counter = 0;  // keep the number of tries to guess the rand number

        JOptionPane.showMessageDialog(null,"Welcome to BULLS AND COWS game!");

        // create an object from CheckInputValidity type,
        // which check if randomized number is valid
        CheckInputValidity c1 = new CheckInputValidity(rand);
        while (!c1.check()){
            rand =  String.format("%04d", getRandomNumber(MIN, MAX));
            c1.setInput(rand);
        }
        // System.out.println("the randomized number is: " + rand);

        CheckInputValidity c2 = new CheckInputValidity("0"); // to check if input number is valid
        boolean gameOver = false; // the game will run until this value is set to 'true'
        boolean isInputValid = false;
        String s5 = ""; // to present previous guesses


        while (!gameOver) { // game's loop
            if (!guessesList.isEmpty()) { // if there are previous guesses
                s5 = "Previous Guesses:\n\n";
                for (InputGuess guess : guessesList) { // run through the guessesList
                    s5 = s5 + guess.toString() + "\n"; // accumulate the results in 's5'
                }
            }

            if (!guessesList.isEmpty()) { // add scrollbar if necessary
                JTextArea textArea = new JTextArea(s5, 20, 15);
                JScrollPane sp = new JScrollPane(textArea);
                sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
                JOptionPane.showMessageDialog(null, sp);
            }

            number = JOptionPane.showInputDialog("Enter number with 4 digit: ");
            if (number == null) // if user choose cancel
                gameOver = true;
            else{
                c2.setInput(number); // update c2.input field to the user's input number
                isInputValid = c2.check(); // check this number validity
                while (!isInputValid) { // if not valid, continue to get number from user
                    number = JOptionPane.showInputDialog("Wrong valid. try again: ");
                    if (number == null) { // if user choose cancel
                        gameOver = true;
                        break;
                    }
                    c2.setInput(number);
                    isInputValid = c2.check();
                }
                if (gameOver) // if user choose 'cancel' in the Message Window
                    break;
                counter++;
                // create an object InputGuess type,
                // which keeps the guesses results (bulls and cows) to present it later
                InputGuess g1 = new InputGuess(number, rand);
                g1.checkBullsAndCows(); // calculate bulls and cows for the input number
                guessesList.add(g1); // add the number and this result to guessesList

                if (g1.getBulls() == GAME_OVER) { // if the user guess 4 bulls
                    gameOver = true;
                    JOptionPane.showMessageDialog(null, "YOU WIN!!!\n The correct answer is: " + rand + "\n Number of guesses: " + counter);
                    int a = JOptionPane.showConfirmDialog(null, "NEW GAME?");
                    if (a == JOptionPane.YES_OPTION) { // if user want another game
                        gameOver = false; // loop's game will continue to run
                        counter = 0; // clear the guess's contur
                        guessesList.clear(); // clear the guess's list
                        s5 = ""; // clear the previous results string
                        rand = String.format("%04d", getRandomNumber(MIN, MAX)); // randomized new number
                        c1.setInput(rand); //update c1.input field to the rand input number
                        while (!c1.check()) { // check validity, and if not continue to randomized
                            rand = String.format("%04d", getRandomNumber(MIN, MAX));
                            c1.setInput(rand);
                        }
                         // System.out.println("the randomized number2 is: " + rand);
                    }
                }
            }
        }
    }
}
