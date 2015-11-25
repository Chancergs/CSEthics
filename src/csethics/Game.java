
import javax.swing.*;


//Chance Ronning Glenn.
//Malware Minigame.


public class Game extends JFrame {
    //constructor
    public Game() {
        super("CSEthics Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(new CSEthics());
        pack();
        setLocationRelativeTo(null);    //put the gui in the center of the screen
        setVisible(true);
    }

    public static void main(String[] args) {
        Game newGame = new Game(); 
    }
}
