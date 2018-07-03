package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import general.Utils;

import java.awt.Color;
import java.util.Random;

/**
 * Pause screen class.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private String message;
    private Utils utils;

    /**
     * Constructs a pause screen.
     *
     * @param keyboard the keyboard.
     */
    public PauseScreen(KeyboardSensor keyboard) {
        this.utils = new Utils();
        this.keyboard = keyboard;
        this.stop = false;
        this.message = this.generateRandomMessage();
    }

    /**
     * Does one frame of animation.
     *
     * @param surface the surface to draw on.
     */
    public void doOneFrame(DrawSurface surface) {
        surface.setColor(Color.YELLOW);
        surface.fillRectangle(0, 0, utils.getStdWindowWidth(), utils.getStdWindowHeight());
        surface.setColor(Color.WHITE);
        surface.fillRectangle(20, 20, utils.getStdWindowWidth() - 40, utils.getStdWindowHeight() - 40);
        surface.setColor(Color.YELLOW);
        surface.fillOval(270 - 2, 200 - 2, 260 + 4, 170 + 4);
        surface.setColor(Color.LIGHT_GRAY);
        surface.fillOval(270, 200, 260, 170);
        surface.setColor(Color.YELLOW);
        surface.drawText(290 - 2, surface.getHeight() / 2 - 2, "Game paused", 36);
        surface.setColor(Color.BLACK);
        surface.drawText(290, surface.getHeight() / 2, "Game paused", 36);
        surface.drawText(270, (int) (0.67 * surface.getHeight()), this.message, 26);
        surface.drawText(25, this.utils.getStdWindowHeight() - 25, "Press space to close..", 18);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * Checks if animation should stop.
     *
     * @return if animation should stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * Generates a random message.
     *
     * @return a random message.
     */
    private String generateRandomMessage() {
        Random random = new Random();
        int randNum = random.nextInt(5);
        String msg = "";

        switch (randNum) {
            case 0:
                msg = "WOW! What a gamer!";
                break;
            case 1:
                msg = "Rest a while my friend!";
                break;
            case 2:
                msg = "It's really difficult isn't it?";
                break;
            case 3:
                msg = "Eat some pizza friend!";
                break;
            case 4:
                msg = "We all believe in you!!!";
                break;
            default:
        }

        return msg;
    }
}
