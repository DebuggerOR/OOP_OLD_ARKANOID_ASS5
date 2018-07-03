package listeners;

import sprites.Ball;
import sprites.Block;

/**
 * HitListener interface.
 */
public interface HitListener {

    /**
     * Handles hit event.
     *
     * @param beingHit the hit block.
     * @param hitter the hitter ball.
     */
    void hitEvent(Block beingHit, Ball hitter);
}