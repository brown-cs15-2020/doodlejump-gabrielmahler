package doodlejump;

/**
 * This is your Constants class. It defines some constants you will need
 * in DoodleJump, using the default values from the demo--you shouldn't
 * need to change any of these values unless you want to experiment. Feel
 * free to add more constants to this class!
 *
 * A NOTE ON THE GRAVITY CONSTANT:
 *   Because our y-position is in pixels rather than meters, we'll need our
 *   gravity to be in units of pixels/sec^2 rather than the usual 9.8m/sec^2.
 *   There's not an exact conversion from pixels to meters since different
 *   monitors have varying numbers of pixels per inch, but assuming a fairly
 *   standard 72 pixels per inch, that means that one meter is roughly 2800
 *   pixels. However, a gravity of 2800 pixels/sec2 might feel a bit fast. We
 *   suggest you use a gravity of about 1000 pixels/sec2. Feel free to change
 *   this value, but make sure your game is playable with the value you choose.
 */
public class Constants {

    public static final int GRAVITY = 1000; // acceleration constant (UNITS: pixels/s^2)
    public static final int REBOUND_VELOCITY = -800; // initial jump velocity (UNITS: pixels/s)
    public static final double DURATION = 0.016; // KeyFrame duration (UNITS: s)

    public static final int PLATFORM_WIDTH = 40; // (UNITS: pixels)
    public static final int PLATFORM_HEIGHT = 10; // (UNITS: pixels)
    public static final int DOODLE_WIDTH = 20; // (UNITS: pixels)
    public static final int DOODLE_HEIGHT = 40; // (UNITS: pixels)

    public static final int GAME_PANE_HEIGHT = 700;
    public static final int GAME_PANE_WIDTH = 500;

    public static final int DOODLE_BODY_HEIGHT = 25;
    public static final int DOODLE_BODY_WIDTH = 15;
    public static final int DOODLE_INITIAL_X = 250;
    public static final int DOODLE_INITIAL_Y = 650;
    public static final int DOODLE_MOVE_X = 25;

    public static final int PLATFORM_Y_SPAWN_MAX = 200; // weird, should be figured out better somehow
    public static final int PLATFORM_Y_SPAWN_MIN = 2 * DOODLE_BODY_HEIGHT;

    public static final int PLATFORM_X_SPAWN_DIFF = 100; // have to figure out what this is equal to app


    public static final int PLATFORM_MAX_NO = GAME_PANE_HEIGHT / PLATFORM_Y_SPAWN_MIN;

}
