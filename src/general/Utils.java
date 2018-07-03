package general;

import geometry.Circle;
import geometry.Point;
import geometry.Velocity;

import java.awt.Color;
import java.util.Random;

/**
 * Utils class.
 */
public class Utils {
    private int stdWindowWidth;
    private int stdWindowHeight;
    private int stdBorderSize;
    private Color stdBorderColor;

    private int stdPaddleWidth;
    private int stdPaddleHeight;
    private int stdPaddleSpeed;
    private Color stdPaddleColor;

    private int stdBallSpeed;
    private int stdBallSize;
    private Circle stdBallCircle;
    private Point stdStartPoint;

    private int stdBlockWidth;
    private int stdBlockHeight;

    private int stdHitPoints;

    private int stdLives;

    /**
     * Constructs Utils.
     */
    public Utils() {
        this.stdWindowWidth = 800;
        this.stdWindowHeight = 600;
        this.stdBorderSize = 25;
        this.stdBorderColor = new Color(153, 102, 0);

        this.stdPaddleHeight = 15;
        this.stdPaddleSpeed = 10;
        this.stdPaddleWidth = 90;
        this.stdPaddleColor = Color.ORANGE;

        this.stdBallSize = 5;
        this.stdBallSpeed = 8;
        this.stdStartPoint = new Point(this.stdWindowWidth / 2, 0.8 * this.stdWindowHeight);
        this.stdBallCircle = new Circle(this.getStdStartPoint(), this.stdBallSize);

        this.stdBlockWidth = 50;
        this.stdBlockHeight = 25;

        this.stdHitPoints = 2;

        this.stdLives = 7;
    }

    /**
     * Gives the starting point.
     *
     * @return the starting point.
     */
    public Point getStdStartPoint() {
        return this.stdStartPoint;
    }

    /**
     * Gives the blocks width.
     *
     * @return the blocks width.
     */
    public int getStdBlockWidth() {
        return stdBlockWidth;
    }

    /**
     * Gives the blocks height.
     *
     * @return the blocks height.
     */
    public int getStdBlockHeight() {
        return stdBlockHeight;
    }

    /**
     * Gives the number of the lives.
     *
     * @return the number of the lives.
     */
    public int getStdLives() {
        return stdLives;
    }

    /**
     * Gives the number of the hit points.
     *
     * @return the number of the hit points.
     */
    public int getStdHitPoints() {
        return stdHitPoints;
    }

    /**
     * Gives the color of the border.
     *
     * @return the color of the border.
     */
    public Color getStdBorderColor() {
        return stdBorderColor;
    }

    /**
     * Gives the color of the paddle.
     *
     * @return the color of the paddle.
     */
    public Color getStdPaddleColor() {
        return stdPaddleColor;
    }

    /**
     * Gives the size of the ball.
     *
     * @return the size of the ball.
     */
    public int getStdBallSize() {
        return stdBallSize;
    }

    /**
     * Gives the speed of the ball.
     *
     * @return the speed of the ball.
     */
    public int getStdBallSpeed() {
        return stdBallSpeed;
    }

    /**
     * Gives the circle of the ball.
     *
     * @return the circle of the ball.
     */
    public Circle getStdBallCircle() {
        return stdBallCircle;
    }

    /**
     * Gives the height of the paddle.
     *
     * @return the height of the paddle.
     */
    public int getStdPaddleHeight() {
        return stdPaddleHeight;
    }

    /**
     * Gives the speed of the paddle.
     *
     * @return the speed of the paddle.
     */
    public int getStdPaddleSpeed() {
        return stdPaddleSpeed;
    }

    /**
     * Gives the width of the paddle.
     *
     * @return the width of the paddle.
     */
    public int getStdPaddleWidth() {
        return stdPaddleWidth;
    }

    /**
     * Gives the height of the window.
     *
     * @return the height of the window.
     */
    public int getStdWindowHeight() {
        return stdWindowHeight;
    }

    /**
     * Gives the width of the window.
     *
     * @return the width of the window.
     */
    public int getStdWindowWidth() {
        return stdWindowWidth;
    }

    /**
     * Gives the size of the border.
     *
     * @return the size of the border.
     */
    public int getStdBorderSize() {
        return stdBorderSize;
    }

    /**
     * Generates a fine random velocity with given speed.
     *
     * @param speed to determine the velocity with.
     * @return a random velocity.
     */
    public Velocity genRandVelocity(double speed) {
        Random rand = new Random();
        // generate an angle between 300 and 60 (= 420)
        int num = rand.nextInt(120);
        int randAngle = 300 + num;
        return Velocity.fromAngleAndSpeed(randAngle, speed);
    }

    /**
     * Checks if given numbers are equal.
     * Allows small mistake in comparison.
     *
     * @param num1 num to check if equal to num2.
     * @param num2 num to check if equal to num1.
     * @return true if numbers are equal, else false.
     */
    public boolean doublesEqual(double num1, double num2) {
        double mistake = 0.001;
        return Math.abs(num1 - num2) < mistake;
    }
}