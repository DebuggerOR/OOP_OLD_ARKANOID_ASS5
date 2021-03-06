package sprites;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collisions.Collidable;
import animation.GameLevel;
import general.GameEnvironment;
import general.Utils;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

import java.awt.Color;

/**
 * A Paddle class.
 *
 * @author Ori.
 */
public class Paddle implements Sprite, Collidable {
    private Rectangle shape;
    private int speed;
    private Color color;
    private GameEnvironment environment;
    private biuoop.KeyboardSensor keyboard;
    private Utils utils;

    /**
     * Constructs a paddle by given sizes, speed, color,
     * environment and keyboard.
     *
     * @param width       the width of the paddle.
     * @param height      the height of the paddle.
     * @param speed s.
     * @param environment the GameLevel environment.
     * @param keyboard    the keyboard sensor.
     */
    public Paddle(int width, int height, int speed, GameEnvironment environment, KeyboardSensor keyboard) {
        this.utils = new Utils();
        // const color
        this.color = Color.ORANGE;
        this.speed = speed;
        this.environment = environment;
        this.keyboard = keyboard;
        this.setShape(width, height);
    }

    /**
     * Constructs a paddle.
     *
     * @param environment the environment.
     * @param keyboard the keyboard.
     */
    public Paddle(GameEnvironment environment, KeyboardSensor keyboard) {
        this.utils = new Utils();
        this.environment = environment;
        this.keyboard = keyboard;
        this.color = utils.getStdPaddleColor();
        this.setShape(utils.getStdPaddleWidth(), utils.getStdPaddleHeight());
        this.speed = utils.getStdPaddleSpeed();
    }

    /**
     * Moves the paddle left.
     */
    public void moveLeft() {
        // the new xPos of the paddle
        int newX = (int) shape.getUpperLeft().getX() - speed;
        // change to new xPos if not pass the borders
        if (newX >= environment.getBorder()) {
            shape.getUpperLeft().setX(newX);
        }
    }

    /**
     * Moves the paddle right.
     */
    public void moveRight() {
        // paddle width and new xPos of the paddle
        int width = (int) this.shape.getWidth();
        int newX = (int) shape.getUpperLeft().getX() + speed;
        // change to new xPos if not pass the borders
        if (newX + width <= environment.getWidth() - environment.getBorder()) {
            shape.getUpperLeft().setX(newX);
        }
    }

    /**
     * Gets the height of the paddle.
     *
     * @return the height of the paddle.
     */
    public int getHeight() {
        return (int) this.shape.getHeight();
    }

    /**
     * Sets the shape of the paddle by given width and height.
     *
     * @param width  of the shape.
     * @param height of the shape.
     */
    public void setShape(int width, int height) {
        // xPos and yPos of topLeft of paddle
        double xPos = (this.utils.getStdWindowWidth() - width) / 2;
        double yPos = this.utils.getStdWindowHeight() - 0.3 * this.utils.getStdBorderSize() - height;
        // sets the rectangle of the paddle
        this.shape = new Rectangle(new Point(xPos, yPos), width, height);
    }

    /**
     * Gets the shape of the paddle.
     *
     * @return the shape of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    /**
     * Handles movement of paddle according to pressed keys.
     */
    public void timePassed() {
        // case left key is pressed move left
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        // case right key is pressed move right
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * Draws the paddle.
     *
     * @param surface the surface that paddle drawn on.
     */
    public void drawOn(DrawSurface surface) {
        // width and height of paddle
        int height = (int) shape.getHeight();
        int width = (int) shape.getWidth();

        // position of the top left point of paddle
        int startX = (int) getCollisionRectangle().getUpperLeft().getX();
        int startY = (int) getCollisionRectangle().getUpperLeft().getY();

        // draw paddles circle in color
        surface.setColor(color);
        surface.fillRectangle(startX, startY, width, height);

        // draw paddled frame in black
        surface.setColor(Color.BLACK);
        surface.drawRectangle(startX, startY, width, height);
    }

    /**
     * Adds this paddle to the game.
     *
     * @param gameLevel the GameLevel that the paddle added to.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * Notifies the object that we collided with it at collisionPoint with
     * a given velocity.
     *
     * @param hitter dsf
     * @param collisionPoint  the point of the collision.
     * @param currentVelocity the velocity when it collides.
     * @return the return is the new velocity expected after the hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // dx, dy and ball speed
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double ballSpeed = currentVelocity.getSpeed();

        // width of region on the paddle, fifth of paddles width
        int fifth = (int) this.shape.getWidth() / 5;

        // x position of paddle and collision
        int startX = (int) getCollisionRectangle().getUpperLeft().getX();
        int collisionX = (int) collisionPoint.getX();

        // update velocity according to the region of the hit
        if (startX <= collisionX && collisionX < startX + fifth) {
            // case hit region 1
            return Velocity.fromAngleAndSpeed(300, ballSpeed);

        } else if (startX + fifth <= collisionX
                && collisionX <= startX + 2 * fifth) {
            // case hit region 2
            return Velocity.fromAngleAndSpeed(330, ballSpeed);

        } else if (startX + 3 * fifth <= collisionX
                && collisionX <= startX + 4 * fifth) {
            // case hit region 4
            return Velocity.fromAngleAndSpeed(30, ballSpeed);

        } else if (startX + 4 * fifth < collisionX
                && collisionX <= startX + 5 * fifth) {
            // case hit region 5
            return Velocity.fromAngleAndSpeed(60, ballSpeed);
        }

        // take the lines clockwise
        Line top = this.shape.getLines().get(0);
        Line right = this.shape.getLines().get(1);
        Line bottom = this.shape.getLines().get(2);
        Line left = this.shape.getLines().get(3);

        // booleans if hit the top, bottom, left or right side
        boolean onTop = collisionPoint.onSegment(top.start(), top.end());
        boolean onRight = collisionPoint.onSegment(right.start(), right.end());
        boolean onBtm = collisionPoint.onSegment(bottom.start(), bottom.end());
        boolean onLeft = collisionPoint.onSegment(left.start(), left.end());

        // update dx and dy according the side of the hit
        if (onTop) {
            currentVelocity.setDy(-1 * Math.abs(dy));
        }
        if (onRight) {
            currentVelocity.setDx(Math.abs(dx));
        }
        if (onBtm) {
            currentVelocity.setDy(Math.abs(dy));
        }
        if (onLeft) {
            currentVelocity.setDx(-1 * Math.abs(dx));
        }

        // return updated velocity
        return currentVelocity;
    }

    /**
     * Removes paddle from game.
     *
     * @param gameLevel the gameLevel to remove from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }
}