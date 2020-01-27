package mooc.vandy.java4android.gate.logic;

import java.util.Random;

import mooc.vandy.java4android.gate.ui.OutputInterface;

/**
 * This class uses your Gate class to manage a herd of snails.  We
 * have supplied you will the code necessary to execute as an app.
 * You must fill in the missing logic below.
 */
public class HerdManager {
    /**
     * Reference to the output.
     */
    private OutputInterface mOut;

    /**
     * The input Gate object.
     */
    private Gate mEastGate;

    /**
     * The output Gate object.
     */
    private Gate mWestGate;

    /**
     * Maximum number of iterations to run the simulation.
     */
    private static final int MAX_ITERATIONS = 10;

    /**
     *  The herd is constant
     */
    public static final int HERD = 24;

    /**
     * Constructor initializes the fields.
     */
    public HerdManager(OutputInterface out,
                       Gate westGate,
                       Gate eastGate) {
        mOut = out;

        mWestGate = westGate;
        mWestGate.open(Gate.IN);

        mEastGate = eastGate;
        mEastGate.open(Gate.OUT);
    }

    // TODO -- Fill your code in here
    public void simulateHerd(Random rand) {
        int pen = HERD;
        mOut.println("There are currently "+pen+" snails in the pen and "+(24-pen)+" snails in the pasture");

        // If one side has 0 the movement isn't random
        // according to the really poorly worded requirements
        for (int i = 0; i < MAX_ITERATIONS; i++) {
            if (pen == HERD) {
                int move = rand.nextInt(pen);
                move++;
                pen -= move;  // use East Gate
            } else if (pen == 0) {
                int move = rand.nextInt(HERD);
                move++;
                pen += move; // use West Gate
            } else {
                // Randomly choose a gate based on the passed in rand object
                int gateToUse = rand.nextInt(2);
                if (gateToUse == 0) {
                    // If it's 0 use east gate
                    int move = rand.nextInt(24-pen);
                    // Base 1
                    move++;
                    pen += move; // use West Gate
                } else {
                    // otherwise use west gate
                    int move = rand.nextInt(pen);
                    // Base 1
                    move++;
                    pen -= move; // use east Gate
                }
            }
            mOut.println("There are currently " + pen + " snails in the pen and " + (24-pen) + " snails in the pasture");
        }
    }
}
