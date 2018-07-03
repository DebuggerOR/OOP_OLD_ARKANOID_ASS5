package sprites;

import biuoop.DrawSurface;
import animation.GameLevel;

import java.awt.Color;

/**
 * Score indicator class.
 */
public class NameIndicator implements Sprite {
    private GameLevel gameLevel;

    /**
     * Constructs a nameIndicator.
     *
     * @param gameLevel the gameLevel to add this to.
     */
    public NameIndicator(GameLevel gameLevel) {
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
        surface.drawText(500, 15, "Level Name: " + gameLevel.getLevelName(), 18);
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
        gameLevel.addSprite(this);
    }
}
