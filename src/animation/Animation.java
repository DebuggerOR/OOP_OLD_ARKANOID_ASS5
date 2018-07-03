package animation;

import biuoop.DrawSurface;

/**
 * Animation interface.
 */
public interface Animation {

    /**
     * Do one frame during the game.
     *
     * @param drawSurface drawSurface to do the frame with.
     */
    void doOneFrame(DrawSurface drawSurface);

    /**
     * Tells if animation needs to stop.
     *
     * @return if needs to stop.
     */
    boolean shouldStop();
}
