package levels;

import geometry.Rectangle;
import geometry.Velocity;
import sprites.BackGround;
import sprites.Block;

import java.util.List;

/**
 * Level information class.
 */
public interface LevelInformation {

    /**
     * Gives the name of the level.
     *
     * @return the name of the level.
     */
    String levelName();

    /**
     * Gives the width of the paddle.
     *
     * @return the width of the paddle.
     */
    int paddleWidth();

    /**
     * Gives the height of the paddle.
     *
     * @return the height of the paddle.
     */
    int paddleHeight();

    /**
     * Gives the speed of the paddle.
     *
     * @return the speed of the paddle.
     */
    int paddleSpeed();

    /**
     * Gives background sprite.
     *
     * @return background sprite.
     */
    BackGround getBackground();

    /**
     * Gives the regular blocks of the level as a list.
     *
     * @return the regular blocks of the level as a list.
     */
    List<Block> blocks();

    /**
     * Gives the number of the block to remove.
     *
     * @return the number of the block to remove.
     */
    int numberOfBlocksToRemove();

    /**
     * Gives the number of the balls.
     *
     * @return the number of the balls.
     */
    int numberOfBalls();

    /**
     * Gives a list of initial velocities.
     *
     * @return a list of initial velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Gives rectangle of killing block.
     *
     * @return rectangle of killing block.
     */
    Rectangle killingRectangle();

    /**
     * Gives rectangle of introducing block.
     *
     * @return rectangle of introducing block.
     */
    Rectangle introducingRectangle();
}