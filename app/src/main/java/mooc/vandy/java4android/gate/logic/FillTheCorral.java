package mooc.vandy.java4android.gate.logic;

import java.util.Random;

import mooc.vandy.java4android.gate.ui.OutputInterface;

/**
 * This class uses your Gate class to fill the corral with snails.  We
 * have supplied you will the code necessary to execute as an app.
 * You must fill in the missing logic below.
 */
public class FillTheCorral {
    /**
     * Reference to the OutputInterface.
     */
    private OutputInterface mOut;
    private static final int STARTING_PASTURE = 5;

    /**
     * Constructor initializes the field.
     */
    FillTheCorral(OutputInterface out) {
        mOut = out;
    }

    // TODO -- Fill your code in here
    public void setCorralGates(Gate[] gate, Random selectDirection) {
        for (int i = 0; i < gate.length; i++){
            gate[i].setSwing(selectDirection.nextInt(3)-1);
        }
    }

    public boolean anyCorralAvailable(Gate[] corral) {
        boolean isOneOpen = false;
        for (int i = 0; i < corral.length; i++){
            if(corral[i].getSwingDirection() == corral[i].IN){
                isOneOpen = true;
            }
        }
        return isOneOpen;
    }

    public int corralSnails(Gate[] corral, Random rand) {
        int pasture = STARTING_PASTURE;
        int counter = 0;

        mOut.println("Initial gate setup:");
        for (int i = 0; i < corral.length; i++){
            if(corral[i].getSwingDirection() == corral[i].IN){
                mOut.println("Gate "+i+": This gate is open and swings to enter the pen only");
            }
            if(corral[i].getSwingDirection() == corral[i].OUT){
                mOut.println("Gate "+i+": This gate is open and swings to exit the pen only");
            }
            if(corral[i].getSwingDirection() == corral[i].CLOSED){
                mOut.println("Gate "+i+": This gate is closed");
            }
        }

        // Simulation Loop
        do {
            int snailsToMove = rand.nextInt(pasture)+1;
            int selectedGate = rand.nextInt(corral.length);
            mOut.println(snailsToMove + " are trying to move through corral " + selectedGate);
            if (corral[selectedGate].getSwingDirection() == Gate.IN) {
                pasture = pasture - snailsToMove;
            } else if (corral[selectedGate].getSwingDirection() == Gate.OUT) {
                pasture = pasture + snailsToMove;
            }
            // If the gate is close there is no change.
            counter++;
        } while (pasture > 0 && counter < 20);

        // Return the number of time we had to run the loop
        mOut.println("It took "+counter+" attempts to corral all of the snails.");
        return counter;
    }

}
