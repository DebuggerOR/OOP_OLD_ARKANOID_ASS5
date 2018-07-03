package listeners;

import animation.GameLevel;
import general.GameEnvironment;
import general.Utils;
import sprites.Ball;
import sprites.Block;

/**
 * Ball adder class.
 */
public class BallAdder implements HitListener {
    private GameLevel gameLevel;
    private Utils utils;

    /**
     * Constructs a ball adder.
     *
     * @param gameLevel the game level.
     */
    public BallAdder(GameLevel gameLevel) {
        this.utils = new Utils();
        this.gameLevel = gameLevel;
    }

    /**
     * Adds ball when hit.
     *
     * @param beingHit the hit block.
     * @param hitter the hitter ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        GameEnvironment environment = this.gameLevel.getEnvironment();

        Ball ball = new Ball(utils.getStdStartPoint(), this.gameLevel.getBallsSize(),
                gameLevel.getBallsSpeed(), environment);

        ball.addToGame(this.gameLevel);

        this.gameLevel.getBallRemover().getRemainingBalls().increase(1);
    }
}
