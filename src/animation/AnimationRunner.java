package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * Animation runner class.
 */
public class AnimationRunner {
    private GUI gui;
    private Sleeper sleeper;

    /**
     * Constructs an AnimationRunner by given sleeper and gui.
     *
     * @param sleeper given sleeper for the animation.
     * @param gui     given gui for the animation.
     */
    public AnimationRunner(Sleeper sleeper, GUI gui) {
        this.gui = gui;
        this.sleeper = sleeper;
    }

    /**
     * Runs the animation.
     *
     * @param animation given animation to run.
     */
    public void run(Animation animation) {
        // frames per seconds and milliseconds per frame
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        // while the animation should run
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis();

            DrawSurface surface = gui.getDrawSurface();
            animation.doOneFrame(surface);
            gui.show(surface);

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}