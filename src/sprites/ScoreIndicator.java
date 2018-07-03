package sprites;

import biuoop.DrawSurface;
import animation.GameLevel;
import general.Utils;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * Score indicator class.
 */
public class ScoreIndicator extends Block {
    private GameLevel gameLevel;
    private Utils utils;

    /**
     * Constructs Constructs a scoreIndicator.
     *
     * @param shape of the backGround.
     * @param gameLevel the gameLevel to add this to.
     */
    public ScoreIndicator(Rectangle shape, GameLevel gameLevel) {
        super(shape, Color.LIGHT_GRAY, 0);
        this.utils = new Utils();
        this.gameLevel = gameLevel;
    }

    /**
     * Constructs a scoreIndicator.
     *
     * @param gameLevel the gameLevel to add this to.
     */
    public ScoreIndicator(GameLevel gameLevel) {
        super(new Rectangle(new Point(0, 0), 0, 0), Color.WHITE);
        this.utils = new Utils();
        super.setShape(new Rectangle(new Point(0, 0), utils.getStdWindowWidth(), 0.75 * utils.getStdBorderSize()));
        this.gameLevel = gameLevel;
    }

    /**
     * Draws this ball using given draw surface.
     *
     * @param surface use this to draw.
     */
    public void drawOn(DrawSurface surface) {
        super.drawOn(surface);

        // draw text in black
        surface.setColor(Color.BLACK);
        surface.drawText(350, 15, "Score: " + gameLevel.getScoreUpdater().getCurrentScore().getValue(), 18);
    }

    /**
     * Adds this to game.
     */
    public void addToGame() {
        gameLevel.addSprite(this);
    }
}
