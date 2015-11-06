
package csethics;

import java.util.Random;

/**
 *
 * @author Chance
 */

public class Income {
    //variables to store amounts of things.
    private int virus; // worth 100 per turn, moderate risk.
    private int worm;  // worth 300 per turn, high risk, certain amount of unremovable risk.
    private int trojan;  // worth 50 per torm, low risk.
    private int zombies; // will be used for special attacks that might do something, not sure yet.
    
    //will demonstrate level of antivirus, rather than number. Antivirus will probably be an upgradeable thing.
    private int antivirus; // worth -10 per turn, reduces risk.
    
    private double income; //how much money comes in at the beginning of a turn.
    
    private double goal; //how much money must be made.
    private int daysLeft; //how many days are left before money is due.
          
    
    //constructor
    public Income() {
        virus = 0;
        worm = 0;
        trojan = 0;
        zombies = 0;
        antivirus = 0;
        
        //income values will probably change.
        income = (100 * virus) + (300 * worm) + (60 * trojan) + (-50 * antivirus);
        goal = 100000; //placeholder value
        daysLeft = 50; //placeholder value
    }
    
    
    
    // Every turn this risk value will be called, and if things go badly there will be drawbacks. Might add a wormRisk() function, but not sure how I want to balance that yet.
    public double getRisk(){
        double risk = (0.2 * virus) + (worm) + (.05 * trojan) - (0.2 * antivirus);
                
        return risk;
    }
    
    // A risk check will be called at the beginning of each turn, and if it happens there are consequences. They will be randomly chosen by this function.
    public String riskEvent(){
        double eventNum = 0;
        Random r = new Random();
        
        //random number between 0 and 10. There will definitely be more than ten too, I just need to start thinking of things that can happen, but that's pretty low on the todo list.
        eventNum = r.nextDouble() * 10; 
        
        
        
        // This is super messy and really not efficient, I'll probably figure out a better way to do it. 
        if(eventNum == 0){
            virus -= 1; //consequences can be pretty simple, but I'll figure out some complicated interesting ones too.
            return "Something bad happened involving your malware."; // Text from these will pop up in a notification on the gui when the turn begins.
        }
        
        else if(eventNum == 1){
            return "";
        }
        
        else if(eventNum == 2){
            return "";
        }
        
        else if(eventNum == 3){
            return "";
        }
        
        else if(eventNum == 4){
            return "";
        }  
        
        else if(eventNum == 5){
            return "";
        }
        
        else if(eventNum == 6){
            return "";
        }
        
        else if(eventNum == 7){
            return "";
        }    
        
        else if(eventNum == 8){
            return "";
        }
        
        else if(eventNum == 9){
            return "";
        }   
        
        else{
            return "";
        }
        
    }
    
    
    
    
    
    
    // get functions. Some might not be used, but they're good to have.
    public int getVirus(){
        return virus;
    }
    
    public int getWorm(){
        return worm;
    }
    
    public int getTrojan(){
        return trojan;
    }
    
    public int getZombies(){
        return zombies;
    }
    
    public int getAntivirus(){
        return antivirus;
    }
    
    public double getIncome(){
        return income;
    }
    
    public int daysLeft(){
        return daysLeft;
    }
    
    public double goal(){
        return goal;
    }
    
    //GUI things will probably go down here, but might go in another class, not sure yet. 
    //Haven't done this yet because I'm still not sure on some game mechanic things and want to get the backend done first.
    
}
