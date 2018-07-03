package sprites;

import biuoop.DrawSurface;
import animation.GameLevel;

import java.awt.Color;

/**
 * Lives indicator class.
 */
public class LivesIndicator implements Sprite {
    private GameLevel gameLevel;

    /**
     * Constructs a livesIndicator.
     *
     * @param gameLevel the gameLevel to add this to.
     */
    public LivesIndicator(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
    }

    /**
     * Draws this ball using given draw surface.
     *
     * @param surface use this to draw.
     */
    public void drawOn(DrawSurface surface) {
        // draw text in black
        surface.setColor(Color.BLACK);
        surface.drawText(150, 15, "Lives: " + gameLevel.getLivesUpdater().getCurrentLives().getValue(), 18);

        // draw small balls as the remaining lives
        for (int i = 0; i < this.gameLevel.getLivesUpdater().getCurrentLives().getValue(); i++) {
            surface.setColor(Color.BLACK);
            surface.drawCircle(222 + i * 11, 8, 5);
            surface.setColor(Color.WHITE);
            surface.fillCircle(222 + i * 11, 8, 5);
            surface.setColor(Color.RED);
            surface.fillCircle(222 + i * 11, 8, 1);
        }
    }

    /**
     *
     */
    @Override
    public void timePassed() {

    }

    /**
     * Adds this to game.
     */
    public void addToGame() {
        this.gameLevel.addSprite(this);
    }
}
