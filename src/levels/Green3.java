package levels;

import general.Utils;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprites.BackGround;
import sprites.Block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Green3 class.
 */
public class Green3 implements LevelInformation {
    private Utils utils;

    /**
     * Constructs Green3.
     */
    public Green3() {
        this.utils = new Utils();
    }

    /**
     * Gives the name of the level.
     *
     * @return the name of the level.
     */
    @Override
    public String levelName() {
        return "Green3";
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
     * Gives background sprite.
     *
     * @return background sprite.
     */
    @Override
    public BackGround getBackground() {

        BackGround backGround = new BackGround(new Color(0, 180, 0));

        backGround.addShape(new Rectangle(new Point(100, 400),
                utils.getStdBlockWidth() * 2 + 30, 200, Color.BLACK));

        backGround.addShape(new Rectangle(new Point(110, 410),
                utils.getStdBlockWidth(), utils.getStdBlockHeight(), new Color(102, 205, 102)));

        backGround.addShape(new Rectangle(new Point(120 + utils.getStdBlockWidth(), 410),
                utils.getStdBlockWidth(), utils.getStdBlockHeight(), new Color(102, 205, 102)));

        backGround.addShape(new Rectangle(new Point(110, 420 + utils.getStdBlockHeight()),
                utils.getStdBlockWidth(),
                utils.getStdBlockHeight(), new Color(102, 205, 102)));

        backGround.addShape(new Rectangle(new Point(120 + utils.getStdBlockWidth(), 420 + utils.getStdBlockHeight()),
                utils.getStdBlockWidth(),
                utils.getStdBlockHeight(), new Color(102, 205, 102)));

        backGround.addShape(new Rectangle(new Point(110, 430 + 2 * utils.getStdBlockHeight()),
                utils.getStdBlockWidth(),
                utils.getStdBlockHeight(), new Color(102, 205, 102)));

        backGround.addShape(new Rectangle(new Point(120 + utils.getStdBlockWidth(), 430
                + 2 * utils.getStdBlockHeight()),
                utils.getStdBlockWidth(), utils.getStdBlockHeight(), new Color(102, 205, 102)));

        backGround.addShape(new Rectangle(new Point(110, 440 + 3 * utils.getStdBlockHeight()),
                utils.getStdBlockWidth(), utils.getStdBlockHeight(), new Color(102, 205, 102)));

        backGround.addShape(new Rectangle(new Point(120 + utils.getStdBlockWidth(), 440
                + 3 * utils.getStdBlockHeight()),
                utils.getStdBlockWidth(), utils.getStdBlockHeight(), new Color(102, 205, 102)));

        backGround.addShape(new Rectangle(new Point(110, 450 + 4 * utils.getStdBlockHeight()),
                utils.getStdBlockWidth() * 2 + 10,
                utils.getStdBlockHeight() + 20, new Color(102, 205, 102)));

        backGround.addShape(new Rectangle(new Point(155, 310),
                20, 90, new Color(100, 100, 100)));

        backGround.addShape(new Rectangle(new Point(163, 210),
                5, 100, new Color(51, 51, 51)));

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

        List<Color> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        colors.add(Color.BLUE);
        colors.add(Color.WHITE);

        for (int j = 0; j < 5; j++) {
            for (int i = 1; i < 11 - j; i++) {
                blocks.add(new Block(new Rectangle(new Point(utils.getStdWindowWidth() - utils.getStdBorderSize()
                        - i * utils.getStdBlockWidth(), 200 + j * utils.getStdBlockHeight()),
                        utils.getStdBlockWidth(), utils.getStdBlockHeight()), colors.get(j), j % 3 + 1));
            }
        }

        return blocks;
    }

    /**
     * Gives the number of the block to remove.
     *
     * @return the number of the block to remove.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }

    /**
     * Gives the number of the balls.
     *
     * @return the number of the balls.
     */
    @Override
    public int numberOfBalls() {
        return 2;
    }

    /**
     * Gives a list of initial velocities.
     *
     * @return a list of initial velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(-this.utils.getStdBallSpeed() / 2, -this.utils.getStdBallSpeed() / 2));
        velocities.add(new Velocity(this.utils.getStdBallSpeed() / 2, -this.utils.getStdBallSpeed() / 2));
        return velocities;
    }

    /**
     * Gives rectangle of killing block.
     *
     * @return rectangle of killing block.
     */
    @Override
    public geometry.Rectangle killingRectangle() {
        return new geometry.Rectangle(new Point(140, 350), utils.getStdBlockWidth(), utils.getStdBlockHeight());
    }

    /**
     * Gives rectangle of introducing block.
     *
     * @return rectangle of introducing block.
     */
    @Override
    public geometry.Rectangle introducingRectangle() {
        return new Rectangle(new Point(140, 375), utils.getStdBlockWidth(), utils.getStdBlockHeight());
    }
}
