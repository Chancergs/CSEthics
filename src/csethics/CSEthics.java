
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.util.Random;


public class CSEthics extends JPanel {

    //game dimensions sizexsize
    private int size = 900;

    //variables to store amounts of things.
    private int virus; // worth 100 per turn, moderate risk.
    private int worm;  // worth 300 per turn, high risk, certain amount of unremovable risk.
    private int trojan;  // worth 50 per torm, low risk.
    //private int zombies; // will be used for special attacks that might do something, not sure yet.
    
    private int antivirus; // worth -10 per turn, reduces risk.
    
    private double curMoney;
    
    private double goal; //how much money must be made.
    private int daysLeft; //how many days are left before money is due.  
    
    private String messages; //the event message that pops up after something is triggered.
    
    private String[][] buttons; // The clickable (and sometimes unclickable) buttons

    //constructor
    public CSEthics() {
        setPreferredSize(new Dimension(size, size));
        addMouseListener(new GMouseListener());

        //back-end:  initialize the buttons
        buttons = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                   buttons[i][j] = " ";
            }
        }        
        
        virus = 0;
        worm = 0;
        trojan = 0;
        //zombies = 0;
        antivirus = 0;
        curMoney = 0;
        
        messages = " ";

        //income = (100 * virus) + (300 * worm) + (60 * trojan) + (-50 * antivirus); //this was turned into a function, but I felt like keeping it here.
        
        goal = 100000; 
        daysLeft = 100; 
        
        buttons[1][0] = "Money";
        buttons[2][0] = "Virus";
        buttons[3][0] = "Worm";
        buttons[4][0] = "Trojan";
        buttons[5][0] = "DDoS";
        buttons[6][0] = "Antivirus";
        buttons[7][0] = "Days Left";        
        
        //These pop up when you start the game.
        JOptionPane.showMessageDialog(this,
                  "You are an insidious malware creator, and your debt is large."
                + "\nYour goal is to earn $" + goal + " in " + daysLeft + " days"
                + "\nYour actions can have risks, and might bring about consequences."
                + "\nManage your malware so you don't lose."
                );
        JOptionPane.showMessageDialog(this, 
                "Click the malware at the top of the screen to activate. "
                + "\nVirus: +100 Income, + 2% risk"
                + "\nWorm: +300 Income, + 8% risk"
                + "\nTrojan: +60 Income, + 1% risk"
                + "\nDDoS: Prevents any risk events for this turn."
                + "\nAntivirus: -50 Income, -4% risk."
                );             
    }

    //back-end to front-end.  Get data from board and draw the GUI
    public void paint(Graphics g) {
        //front-end.  Draw GUI
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.BLACK);
        g.drawLine(100, 100, 800, 100);   //first horizontal line
        g.drawLine(100, 0, 100, 900);   //1 vertical line
        g.drawLine(200, 0, 200, 100);   //2 vertical line
        g.drawLine(300, 0, 300, 100);   //3 vertical line
        g.drawLine(400, 0, 400, 100);   //4 vertical line
        g.drawLine(500, 0, 500, 100);   //5 vertical line        
        g.drawLine(600, 0, 600, 100);   //6 vertical line        
        g.drawLine(700, 0, 700, 100);   //7 vertical line        
        g.drawLine(800, 0, 800, 900);   //8 vertical line        
        
        Font f = new Font("Times", Font.PLAIN, 20);
        g.setFont(f);
        FontMetrics fm = g.getFontMetrics();

        int a = fm.getAscent();
        int h = fm.getHeight();

        //back-end to front-end.  Populate GUI with values from array
        for (int i = 1; i < 8; i++) {
            for (int j = 0; j < 1; j++) {
                String curSquare = buttons[i][j];
                int w = fm.stringWidth(curSquare);

                g.drawString(curSquare, 100 * i + 50 - w / 2, 100 * j + 50 + a - h / 2);    
            }
        }        
        
        Font f2 = new Font("Times", Font.PLAIN, 14);
        g.setFont(f2);
        
        g.drawString(messages, 120, 120);  
        g.drawString("$" + curMoney, 137, 85);  
        g.drawString(" " + daysLeft, 737, 85);      
        g.drawString(" " + virus, 237, 85);          
        g.drawString(" " + worm, 337, 85);
        g.drawString(" " + trojan, 437, 85);
        g.drawString(" " + antivirus, 637, 85);

    }
    
    // INNER CLASS for a Mouse events:
    private class GMouseListener implements MouseListener {

        @Override
        public void mousePressed(MouseEvent e) {
            //System.out.println("press");
        }

        public void mouseReleased(MouseEvent e) {
            //System.out.println("release");
        }

        public void mouseEntered(MouseEvent e) {
            //System.out.println("mouse entered");
        }

        public void mouseExited(MouseEvent e) {
            //System.out.println("mouse exited");
        }

        //front-end to back-end.
        public void mouseClicked(MouseEvent e) {
            //get click data from the GUI and convert to back end board spot reference
            int x = e.getX() / 100;
            int y = e.getY() / 100;

            //front-end to back-end.  process click
            if ("Virus".equals(buttons[x][y])) { //virus bottom
                messages = "";   
                virus += 1;
                daysLeft -= 1;
                curMoney += income();
                riskEvent();
                repaint();
                checkForGameEnd();
            }
            if ("Worm".equals(buttons[x][y])) { //worm button
                messages = "";
                worm += 1;
                daysLeft -= 1;
                curMoney += income();
                riskEvent();
                repaint();
                checkForGameEnd();
            }
            if ("Trojan".equals(buttons[x][y])) { //trojan button
                messages = "";
                trojan += 1;
                daysLeft -= 1;
                curMoney += income();  
                riskEvent();
                repaint();
                checkForGameEnd();
            }
            if ("Antivirus".equals(buttons[x][y])) { //antivirus button
                messages = "";
                antivirus += 1;
                daysLeft -= 1;
                curMoney += income();
                riskEvent();
                repaint();
                checkForGameEnd();
            }            
            if ("DDoS".equals(buttons[x][y])) { //DDoS button
                messages = "";                
                daysLeft -= 1;
                curMoney += income();
                repaint();
                checkForGameEnd();
            }
        }
    }
    
    //calculates income
    public double income(){
        double income = (100 * virus) + (300 * worm) + (60 * trojan) + (-50 * antivirus);
                
        return income;
    };
    
    
    //calculates risk
    public double risk(){
        double risk = (2 * virus) + (8 * worm) + (1 * trojan) - (4 * antivirus);
                
        return risk;
    }    
    
    //Runs risk percentage, if risk is triggered, then it will randomly select from ten possible events. 
    public void riskEvent(){
        double eventNum = 0;
        Random r = new Random();
        
        if (r.nextDouble() * 100 <= risk()){
            //random number between 0 and 10. 
            eventNum = r.nextInt(11); 
            
            if(eventNum == 0){
                virus -= 4; 
                messages = "A few of your viruses have been detected, and are now blocked by most antiviruses.(-4 Viruses)"; // Text from these will pop up in a notification on the gui when the turn begins.
            }

            else if(eventNum == 1){
                worm -= 3;
                messages = "A few of your worms have been detected, and are now blocked by most antiviruses.(-4 Worms)";
            }

            else if(eventNum == 2){
                curMoney -= 3000;
                messages = "Your malware is traced back to you, and you are fined for three thousand dollars.";
            }

            else if(eventNum == 3){
                curMoney -= 1000;
                daysLeft -= 30;
                messages = "Your malware is traced back to you, and you are fined for one thousand dollars, and put in jail for a month.";
            }

            else if(eventNum == 4){
                daysLeft = 0;
                messages = "Your virus prevents someone from using Facebook, so they assassinate you (Your time is up).";
            }  

            else if(eventNum == 5){
                daysLeft += 10;
                messages = "One of your viruses breaks the computer of the people you are scrambling to pay (+10 days left).";
            }

            else if(eventNum == 6){
                virus -= 5;
                antivirus += 5;
                messages = "Somehow, some of your viruses magically turn into antiviruses (-5 viruses, +5 antiviruses).";
            }

            else if(eventNum == 7){
                trojan -= 1;
                messages = "You lose a trojan.";
            }    

            else if(eventNum == 8){
                messages = "Someone traces your virus, but luckily they go after the wrong person.";
            }

            else if(eventNum == 9){
                daysLeft -= 0;
                messages = "Your computer is hit by a DDoS attack (-1 day left).";
            }   

            else{
                daysLeft = 0;
                messages = "You are sent to jail for the rest of your life for your cybercrimes(Your time is up).";
            }
        }

    }
    
    //Checks to see if the game is over.
    public void checkForGameEnd() {
        if (daysLeft <= 0 && curMoney <= goal){
            JOptionPane.showMessageDialog(this, "Game over, you lose.");
            System.exit(0);            
        }    
        else if (curMoney >= goal){
            JOptionPane.showMessageDialog(this, "Game over, you win!");
            System.exit(0);   
        }
    }
}
