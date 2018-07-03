package levels;

import general.Utils;
import geometry.Point;
import geometry.Rectangle;
import geometry.Circle;
import geometry.Velocity;
import geometry.Line;
import sprites.BackGround;
import sprites.Block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * WideEasy class.
 */
public class WideEasy implements LevelInformation {
    private Utils utils;
    private int yPos;

    /**
     * Constructs WideEasy.
     */
    public WideEasy() {
        this.utils = new Utils();
        // yPos of the blocks line
        this.yPos = 260;
    }

    /**
     * Gives the name of the level.
     *
     * @return the name of the level.
     */
    @Override
    public String levelName() {
        return "WideEasy";
    }

    /**
     * Gives the width of the paddle.
     *
     * @return the width of the paddle.
     */
    @Override
    public int paddleWidth() {
        // wide paddle
        return 6 * this.utils.getStdPaddleWidth();
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
        // slower paddle
        return (int) (0.5 * this.utils.getStdPaddleSpeed());
    }

    /**
     * Gives background sprite.
     *
     * @return background sprite.
     */
    @Override
    public BackGround getBackground() {
        // center of the sun
        Point sunCenter = new Point(160, 160);
        // bool to change color of sun shine
        boolean turnColor = true;
        // blue background
        BackGround backGround = new BackGround(new Color(51, 153, 255));

        // draw sine shine in two colors
        for (int i = utils.getStdBorderSize(); i < utils.getStdWindowWidth() - 2 * utils.getStdBorderSize(); i += 15) {
            if (turnColor) {
                // yellow sun shine
                backGround.addShape(new Line(sunCenter, new Point(i, 270), new Color(255, 255, 153)));
            } else {
                // orange sun shine
                backGround.addShape(new Line(sunCenter, new Point(i, 270), new Color(255, 204, 0)));
            }
            // turn color
            turnColor = !turnColor;
        }

        // sun
        backGround.addShape(new Circle(sunCenter, 70, new Color(255, 255, 153)));
        backGround.addShape(new Circle(sunCenter, 55, new Color(255, 255, 0)));
        // suns smile using yellow and black
        backGround.addShape(new Circle(sunCenter, 40, Color.BLACK));
        backGround.addShape(new Circle(new Point(sunCenter.getX(), sunCenter.getY() - 10), 45,
                new Color(255, 255, 0)));
        backGround.addShape(new Circle(new Point(sunCenter.getX() - 15, sunCenter.getY() - 20), 7,
                Color.BLACK));
        backGround.addShape(new Circle(new Point(sunCenter.getX() + 15, sunCenter.getY() - 20), 7,
                Color.BLACK));

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

        // 2 red blocks
        blocks.add(new Block(new Rectangle(new Point(utils.getStdBorderSize(), yPos),
                utils.getStdBlockWidth(), utils.getStdBlockHeight()), Color.RED, utils.getStdHitPoints()));

        blocks.add(new Block(new Rectangle(new Point(utils.getStdBorderSize() + utils.getStdBlockWidth(), yPos),
                utils.getStdBlockWidth(), utils.getStdBlockHeight()), Color.RED, utils.getStdHitPoints()));

        // 2 orange blocks
        blocks.add(new Block(new Rectangle(new Point(utils.getStdBorderSize() + 2 * utils.getStdBlockWidth(), yPos),
                utils.getStdBlockWidth(), utils.getStdBlockHeight()), Color.ORANGE, utils.getStdHitPoints()));

        blocks.add(new Block(new Rectangle(new Point(utils.getStdBorderSize() + 3 * utils.getStdBlockWidth(), yPos),
                utils.getStdBlockWidth(), utils.getStdBlockHeight()), Color.ORANGE, utils.getStdHitPoints()));

        // 2 yellow blocks
        blocks.add(new Block(new Rectangle(new Point(utils.getStdBorderSize() + 4 * utils.getStdBlockWidth(), yPos),
                utils.getStdBlockWidth(), utils.getStdBlockHeight()), Color.YELLOW, utils.getStdHitPoints()));

        blocks.add(new Block(new Rectangle(new Point(utils.getStdBorderSize() + 5 * utils.getStdBlockWidth(), yPos),
                utils.getStdBlockWidth(), utils.getStdBlockHeight()), Color.YELLOW, utils.getStdHitPoints()));

        // 2 green blocks with a gap between
        blocks.add(new Block(new Rectangle(new Point(utils.getStdBorderSize() + 6 * utils.getStdBlockWidth(), yPos),
                utils.getStdBlockWidth(), utils.getStdBlockHeight()), Color.GREEN, utils.getStdHitPoints()));

        blocks.add(new Block(new Rectangle(new Point(utils.getStdBorderSize() + 8 * utils.getStdBlockWidth(), yPos),
                utils.getStdBlockWidth(), utils.getStdBlockHeight()), Color.GREEN, utils.getStdHitPoints()));

        // 2 blue blocks
        blocks.add(new Block(new Rectangle(new Point(utils.getStdBorderSize() + 9 * utils.getStdBlockWidth(), yPos),
                utils.getStdBlockWidth(), utils.getStdBlockHeight()), Color.BLUE, utils.getStdHitPoints()));

        blocks.add(new Block(new Rectangle(new Point(utils.getStdBorderSize() + 10 * utils.getStdBlockWidth(), yPos),
                utils.getStdBlockWidth(), utils.getStdBlockHeight()), Color.BLUE, utils.getStdHitPoints()));

        // 2 pink blocks
        blocks.add(new Block(new Rectangle(new Point(utils.getStdBorderSize() + 11 * utils.getStdBlockWidth(), yPos),
                utils.getStdBlockWidth(), utils.getStdBlockHeight()), Color.PINK, utils.getStdHitPoints()));

        blocks.add(new Block(new Rectangle(new Point(utils.getStdBorderSize() + 12 * utils.getStdBlockWidth(), yPos),
                utils.getStdBlockWidth(), utils.getStdBlockHeight()), Color.PINK, utils.getStdHitPoints()));

        // 2 cyan blocks
        blocks.add(new Block(new Rectangle(new Point(utils.getStdBorderSize() + 13 * utils.getStdBlockWidth(), yPos),
                utils.getStdBlockWidth(), utils.getStdBlockHeight()), Color.CYAN, utils.getStdHitPoints()));

        blocks.add(new Block(new Rectangle(new Point(utils.getStdBorderSize() + 14 * utils.getStdBlockWidth(), yPos),
                utils.getStdBlockWidth(), utils.getStdBlockHeight()), Color.CYAN, utils.getStdHitPoints()));

        return blocks;
    }

    /**
     * Gives the number of the block to remove.
     *
     * @return the number of the block to remove.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 14;
    }

    /**
     * Gives the number of the balls.
     *
     * @return the number of the balls.
     */
    @Override
    public int numberOfBalls() {
        return 10;
    }

    /**
     * Gives a list of initial velocities.
     *
     * @return a list of initial velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();

        // right
        velocities.add(Velocity.fromAngleAndSpeed(15, 0.75 * this.utils.getStdBallSpeed()));
        velocities.add(Velocity.fromAngleAndSpeed(30, 0.75 * this.utils.getStdBallSpeed()));
        velocities.add(Velocity.fromAngleAndSpeed(45, 0.75 * this.utils.getStdBallSpeed()));
        velocities.add(Velocity.fromAngleAndSpeed(60, 0.75 * this.utils.getStdBallSpeed()));
        velocities.add(Velocity.fromAngleAndSpeed(75, 0.75 * this.utils.getStdBallSpeed()));
        // left
        velocities.add(Velocity.fromAngleAndSpeed(345, 0.75 * this.utils.getStdBallSpeed()));
        velocities.add(Velocity.fromAngleAndSpeed(330, 0.75 * this.utils.getStdBallSpeed()));
        velocities.add(Velocity.fromAngleAndSpeed(315, 0.75 * this.utils.getStdBallSpeed()));
        velocities.add(Velocity.fromAngleAndSpeed(300, 0.75 * this.utils.getStdBallSpeed()));
        velocities.add(Velocity.fromAngleAndSpeed(285, 0.75 * this.utils.getStdBallSpeed()));

        return velocities;
    }

    /**
     * Gives rectangle of killing block.
     *
     * @return rectangle of killing block.
     */
    @Override
    public Rectangle killingRectangle() {
        // no kill block
        return new Rectangle(new Point(0, 0), 0, 0);
    }

    /**
     * Gives rectangle of introducing block.
     *
     * @return rectangle of introducing block.
     */
    @Override
    public Rectangle introducingRectangle() {
        // placed in the gap that in the line of the blocks at yPos
        return new Rectangle(new Point(utils.getStdBorderSize() + 7 * utils.getStdBlockWidth(), yPos),
                utils.getStdBlockWidth(), utils.getStdBlockHeight());
    }
}
