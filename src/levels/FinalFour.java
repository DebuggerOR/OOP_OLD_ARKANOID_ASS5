package levels;

import general.Utils;
import geometry.Point;
import geometry.Line;
import geometry.Rectangle;
import geometry.Velocity;
import geometry.Circle;
import sprites.BackGround;
import sprites.Block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * FinalFour class.
 */
public class FinalFour implements LevelInformation {
    private Utils utils;

    /**
     * Constructs a FinalFour.
     */
    public FinalFour() {
        this.utils = new Utils();
    }

    /**
     * Gives the name of the level.
     *
     * @return the name of the level.
     */
    @Override
    public String levelName() {
        return "FinalFour";
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
     * Gives the width of the paddle.
     *
     * @return the width of the paddle.
     */
    @Override
    public int paddleWidth() {
        return this.utils.getStdPaddleWidth();
    }

    /**
     * Gives the speed of the paddle.
     *
     * @return the speed of the paddle.
     */
    @Override
    public int paddleSpeed() {
        return (int) (1.5 * this.utils.getStdPaddleSpeed());
    }

    /**
     * Gives background sprite.
     *
     * @return background sprite.
     */
    @Override
    public BackGround getBackground() {
        BackGround backGround = new BackGround(new Color(51, 151, 200));

        int dxRain = 30;

        int xPox = 100;
        int yPos = 400;

        for (int i = 0; i < 10; i++) {
            backGround.addShape(new Line(new Point(xPox + i * 8 - 10, yPos),
                    new Point(xPox + i * 8 - 10 + dxRain, utils.getStdWindowHeight()), Color.WHITE));
        }

        backGround.addShape(new Circle(new Point(xPox, yPos), 30, new Color(204, 204, 204)));
        backGround.addShape(new Circle(new Point(xPox + 15, yPos + 15), 30, new Color(194, 194, 194)));
        backGround.addShape(new Circle(new Point(xPox + 30, yPos - 15), 30, new Color(184, 184, 184)));
        backGround.addShape(new Circle(new Point(xPox + 45, yPos + 15), 30, new Color(174, 174, 174)));
        backGround.addShape(new Circle(new Point(xPox + 60, yPos), 30, new Color(164, 164, 164)));

        xPox = 600;
        yPos = 450;

        for (int i = 0; i < 10; i++) {
            backGround.addShape(new Line(new Point(xPox + i * 8 - 10, yPos),
                    new Point(xPox + i * 8 - 10 + dxRain, utils.getStdWindowHeight()), Color.WHITE));
        }

        backGround.addShape(new Circle(new Point(xPox, yPos), 30, new Color(204, 204, 204)));
        backGround.addShape(new Circle(new Point(xPox + 15, yPos + 15), 30, new Color(194, 194, 194)));
        backGround.addShape(new Circle(new Point(xPox + 30, yPos - 15), 30, new Color(184, 184, 184)));
        backGround.addShape(new Circle(new Point(xPox + 45, yPos + 15), 30, new Color(174, 174, 174)));
        backGround.addShape(new Circle(new Point(xPox + 60, yPos), 30, new Color(164, 164, 164)));

        xPox = 350;
        yPos = 200;

        for (int i = 0; i < 10; i++) {
            backGround.addShape(new Line(new Point(xPox + i * 8 - 10, yPos),
                    new Point(xPox + i * 8 - 10 + dxRain, utils.getStdWindowHeight()), Color.WHITE));
        }

        backGround.addShape(new Circle(new Point(xPox, yPos), 30, new Color(204, 204, 204)));
        backGround.addShape(new Circle(new Point(xPox + 15, yPos + 15), 30, new Color(194, 194, 194)));
        backGround.addShape(new Circle(new Point(xPox + 30, yPos - 15), 30, new Color(184, 184, 184)));
        backGround.addShape(new Circle(new Point(xPox + 45, yPos + 15), 30, new Color(174, 174, 174)));
        backGround.addShape(new Circle(new Point(xPox + 60, yPos), 30, new Color(164, 164, 164)));

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
        colors.add(Color.GREEN);
        colors.add(Color.WHITE);
        colors.add(Color.PINK);
        colors.add(Color.CYAN);

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 7; j++) {
                // keep raw 3 and column 2 and 12 for killing and introducing blocks
                if (j == 3) {
                    if (i == 2 || i == 12) {
                        continue;
                    }
                }
                blocks.add(new Block(new Rectangle(new Point(utils.getStdBorderSize()
                        + i * utils.getStdBlockWidth(), 150 + j * utils.getStdBlockHeight()),
                        utils.getStdBlockWidth(), utils.getStdBlockHeight()), colors.get(j),
                        (j + 1) % 2 + 1));
            }
        }

        return blocks;
    }

    /**
     * Gives the number of the blocks to remove.
     *
     * @return the number of the blocks to remove.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 103;
    }

    /**
     * Gives the number of the balls.
     *
     * @return the number of the balls.
     */
    @Override
    public int numberOfBalls() {
        return 3;
    }

    /**
     * Gives a list of initial velocities.
     *
     * @return a list of initial velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(-0.5 * utils.getStdBallSpeed(), -0.5 * utils.getStdBallSpeed()));
        velocities.add(new Velocity(0, -0.5 * utils.getStdBallSpeed() * 1.4));
        velocities.add(new Velocity(0.5 * utils.getStdBallSpeed(), -0.5 * utils.getStdBallSpeed()));
        return velocities;
    }

    /**
     * Gives rectangle of killing block.
     *
     * @return rectangle of killing block.
     */
    @Override
    public Rectangle killingRectangle() {
        return new Rectangle(new Point(utils.getStdBorderSize() + 2 * utils.getStdBlockWidth(),
                150 + 3 * utils.getStdBlockHeight()), utils.getStdBlockWidth(), utils.getStdBlockHeight());
    }

    /**
     * Gives rectangle of introducing block.
     *
     * @return rectangle of introducing block.
     */
    @Override
    public Rectangle introducingRectangle() {
        return new Rectangle(new Point(utils.getStdBorderSize() + 12 * utils.getStdBlockWidth(),
                150 + 3 * utils.getStdBlockHeight()), utils.getStdBlockWidth(), utils.getStdBlockHeight());
    }
}
