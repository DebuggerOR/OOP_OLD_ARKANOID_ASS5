package sprites;

import biuoop.DrawSurface;

/**
 * A Sprite interface.
 *
 * @author Ori.
 */
public interface Sprite {

    /**
     * Draws the sprite to the screen.
     *
     * @param surface the surface to draw on.
     */
    void drawOn(DrawSurface surface);

    /**
     * Notifies the sprite that time has passed.
     */
    void timePassed();
}