package animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 * Countdown animation class.
 */
public class CountDownAnimation implements Animation {
    private boolean stop;
    private boolean isStart;
    private int countFrom;
    private double numSeconds;
    private SpriteCollection gameScreen;

    /**
     * Constructs a CountDownAnimation by given numOfSeconds, countFrom and gameScreen.
     *
     * @param numOfSeconds given num of seconds to sleep.
     * @param countFrom given num to start the countdown from.
     * @param gameScreen given sprites to show on screen.
     */
    public CountDownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.isStart = true;
    }

    /**
     * Does one frame of animation.
     *
     * @param surface to draw on.
     */
    public void doOneFrame(DrawSurface surface) {
        Sleeper sleeper = new Sleeper();
        // sleep for numSeconds at each start of one frame
        if (!this.isStart) {
            sleeper.sleepFor((long) this.numSeconds);
        }
        this.isStart = false;

        // draw regular screen
        this.gameScreen.drawAllOn(surface);
        // draw white ellipse with black frame
        surface.setColor(Color.WHITE);
        surface.fillOval(325, 230, 150, 100);
        surface.setColor(Color.BLACK);
        surface.drawOval(325, 230, 150, 100);

        if (this.countFrom > 0) {
            // case > 0 display countdown
            surface.setColor(Color.LIGHT_GRAY);
            surface.drawText(surface.getWidth() / 2 - 15 - 2, surface.getHeight() / 2 - 2, "" + countFrom, 50);
            surface.setColor(Color.BLACK);
            surface.drawText(surface.getWidth() / 2 - 15, surface.getHeight() / 2, "" + countFrom--, 50);

        } else if (this.countFrom == 0) {
            // case 0 display go
            this.countFrom--;

            surface.setColor(Color.RED);
            surface.drawText(surface.getWidth() / 2 - 55 - 2, surface.getHeight() / 2 - 2, "GO!!", 50);
            surface.setColor(Color.BLACK);
            surface.drawText(surface.getWidth() / 2 - 55, surface.getHeight() / 2, "GO!!", 50);

        } else {
            // case < 0 stop countdown
            this.stop = true;
        }
    }

    /**
     * Tells if animation should stop.
     *
     * @return if animation should stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}