package sprites;

import biuoop.DrawSurface;
import animation.GameLevel;
import geometry.Circle;

import java.awt.Color;

/**
 * Starting point class.
 */
public class StartingPoint implements Sprite {
    private Circle circle;

    /**
     * Constructs a StartingPoint by given circle.
     *
     * @param circle the circle.
     */
    public StartingPoint(Circle circle) {
        this.circle = circle;
    }

    /**
     * Gives the circle.
     *
     * @return the circle.
     */
    public Circle getCircle() {
        return circle;
    }

    /**
     * Draws the starting point on surface.
     *
     * @param surface the surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        // draw white circle
        surface.setColor(Color.WHITE);
        surface.fillCircle((int) circle.getCenter().getX(), (int) circle.getCenter().getY(), circle.getRadius());
        // draw red circle
        surface.setColor(Color.RED);
        surface.fillCircle((int) circle.getCenter().getX(), (int) circle.getCenter().getY(),
                (int) (0.8 * circle.getRadius()));
    }

    /**
     *
     */
    @Override
    public void timePassed() {

    }

    /**
     * Adds this ball to given game.
     *
     * @param gameLevel the GameLevel that this added to.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
