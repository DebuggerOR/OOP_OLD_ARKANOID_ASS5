package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import general.Utils;

import java.awt.Color;

/**
 * EndScreen class.
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private int numLives;
    private int score;
    private Utils utils;

    /**
     * Constructs an end screen.
     *
     * @param keyboard the keyboard.
     * @param numLives the number of remaining lives.
     * @param score the score.
     */
    public EndScreen(KeyboardSensor keyboard, int numLives, int score) {
        this.utils = new Utils();
        this.stop = false;
        this.keyboard = keyboard;
        this.numLives = numLives;
        this.score = score;
    }

    /**
     * Does one frame.
     *
     * @param surface the surface..
     */
    public void doOneFrame(DrawSurface surface) {
        if (this.numLives > 0) {
            surface.setColor(new Color(0, 102, 0));
            surface.fillRectangle(0, 0, utils.getStdWindowWidth(), utils.getStdWindowHeight());
            surface.setColor(Color.WHITE);
            surface.fillRectangle(20, 20, utils.getStdWindowWidth() - 40, utils.getStdWindowHeight() - 40);
            surface.setColor(new Color(0, 102, 0));
            surface.fillOval(270 - 2, 200 - 2, 260 + 4, 170 + 4);
            surface.setColor(Color.LIGHT_GRAY);
            surface.fillOval(270, 200, 260, 170);
            surface.setColor(new Color(0, 102, 0));
            surface.drawText(313 - 2, surface.getHeight() / 2 - 2, "You win!", 46);
            surface.setColor(Color.BLACK);
            surface.drawText(313, surface.getHeight() / 2, "You win!", 46);
            surface.drawText(290, (int) (0.67 * surface.getHeight()), "Your score is: " + score, 26);
            surface.drawText(25, this.utils.getStdWindowHeight() - 25, "Press space to close..", 18);

        } else {
            surface.setColor(new Color(153, 0, 0));
            surface.fillRectangle(0, 0, utils.getStdWindowWidth(), utils.getStdWindowHeight());
            surface.setColor(Color.WHITE);
            surface.fillRectangle(20, 20, utils.getStdWindowWidth() - 40, utils.getStdWindowHeight() - 40);
            surface.setColor(new Color(153, 0, 0));
            surface.fillOval(270 - 2, 200 - 2, 260 + 4, 170 + 4);
            surface.setColor(Color.LIGHT_GRAY);
            surface.fillOval(270, 200, 260, 170);
            surface.setColor(new Color(153, 0, 0));
            surface.drawText(280 - 2, surface.getHeight() / 2 - 2, "Game over", 46);
            surface.setColor(Color.BLACK);
            surface.drawText(280, surface.getHeight() / 2, "Game over", 46);
            surface.drawText(290, (int) (0.67 * surface.getHeight()), "Your score is: " + score, 26);
            surface.drawText(25, this.utils.getStdWindowHeight() - 25, "Press space to close..", 18);
        }

        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * Tells if animation should stop.
     *
     * @return if animation should stop..
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}