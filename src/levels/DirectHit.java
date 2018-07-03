package levels;

import general.Utils;
import geometry.Point;
import geometry.Line;
import geometry.Rectangle;
import geometry.Velocity;
import geometry.Cycle;
import sprites.BackGround;
import sprites.Block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * DirectHit class.
 */
public class DirectHit implements LevelInformation {
    private Utils utils;

    /**
     * Constructs a DirectHit level.
     */
    public DirectHit() {
        this.utils = new Utils();
    }

    /**
     * Gives the name of the level.
     *
     * @return the name of the level.
     */
    @Override
    public String levelName() {
        return "DirectHit";
    }

    /**
     * Gives the width of the paddle.
     *
     * @return the width of the paddle.
     */
    @Override
    public int paddleWidth() {
        return this.utils.getStdPaddleWidth();
    }

    /**
     * Gives the height of the paddle.
     *
     * @return the height of the paddle.
     */
    @Override
    public int paddleHeight() {
        return this.utils.getStdPaddleHeight();
    }

    /**
     * Gives the speed of the paddle.
     *
     * @return the speed of the paddle.
     */
    @Override
    public int paddleSpeed() {
        return this.utils.getStdPaddleSpeed();
    }

    /**
     * Gives a background sprite.
     *
     * @return a background sprite.
     */
    @Override
    public BackGround getBackground() {
        // black background
        BackGround backGround = new BackGround(Color.BLACK);
        // cycles
        backGround.addShape(new Cycle(400 , 185, 75, Color.BLUE));
        backGround.addShape(new Cycle(400 , 185, 100, Color.WHITE));
        backGround.addShape(new Cycle(400 , 185, 125, Color.RED));
        backGround.addShape(new Cycle(400 , 185, 150, Color.WHITE));
        backGround.addShape(new Cycle(400 , 185, 175, Color.BLUE));
        // lines
        backGround.addShape(new Line(new Point(100, 185), new Point(700, 185), Color.BLUE));
        backGround.addShape(new Line(new Point(400, 0), new Point(400, 485), Color.RED));

        return backGround;
    }

    /**
     * Gives the regular blocks of the level as a list.
     *
     * @return the regular blocks of the level as a list.
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block(new Rectangle(new Point(375, 173), utils.getStdBlockWidth(),
                utils.getStdBlockHeight()), Color.RED, 1));
        return blocks;
    }

    /**
     * Gives the number of the blocks to remove.
     *
     * @return the number of the blocks to remove.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    /**
     * Gives the number of the balls.
     *
     * @return the number of the balls.
     */
    @Override
    public int numberOfBalls() {
        return 1;
    }

    /**
     * Gives a list of initial velocities.
     *
     * @return a list of initial velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(0, -1 * utils.getStdBallSpeed()));
        return velocities;
    }

    /**
     * Gives rectangle of killing block.
     *
     * @return rectangle of killing block.
     */
    @Override
    public Rectangle killingRectangle() {
        return new Rectangle(new Point(0, 0), 0, 0);
    }

    /**
     * Gives rectangle of introducing block.
     *
     * @return rectangle of introducing block.
     */
    @Override
    public Rectangle introducingRectangle() {
        return new Rectangle(new Point(0, 0), 0, 0);
    }
}
