package sprites;

import animation.GameLevel;
import biuoop.DrawSurface;
import collisions.Collidable;
import collisions.CollisionInfo;
import general.GameEnvironment;
import general.Utils;
import geometry.Circle;
import geometry.Line;
import geometry.Point;
import geometry.Velocity;
import geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Ball class.
 *
 * @author Ori.
 */
public class Ball implements Sprite {
    private Color color;
    private Circle circle;
    private Velocity velocity;
    private GameEnvironment environment;
    private Utils utils;

    /**
     * Constructs a ball by given center, radius and color.
     *
     * @param center the center of the ball.
     * @param radius the radius of the ball.
     * @param color  the color of the ball.
     */
    public Ball(Point center, int radius, Color color) {
        this.utils = new Utils();
        this.circle = new Circle(center, radius);
        this.color = color;
    }

    /**
     * Constructs a ball.
     *
     * @param circle the circle.
     */
    public Ball(Circle circle) {
        this.utils = new Utils();
        this.color = Color.WHITE;
        this.circle = circle;
    }

    /**
     * Constructs a ball by given center, radius, speed
     * and environment.
     *
     * @param center      the center of the ball.
     * @param radius      the radius of the ball.
     * @param speed       the speed of the ball.
     * @param environment the environment of the ball.
     */
    public Ball(Point center, int radius, double speed, GameEnvironment environment) {
        this(center, radius, Color.WHITE);
        this.environment = environment;
        this.utils = new Utils();
        this.velocity = utils.genRandVelocity(speed);
    }

    /**
     * Constructs a ball.
     *
     * @param center the center.
     * @param radius the radius.
     * @param velocity the velocity.
     * @param environment the environment.
     */
    public Ball(Point center, int radius, Velocity velocity, GameEnvironment environment) {
        this(center, radius, Color.WHITE);
        this.environment = environment;
        this.utils = new Utils();
        this.velocity = velocity;
    }

    /**
     * Constructs a ball.
     *
     * @param velocity the velocity.
     * @param environment the environment.
     */
    public Ball(Velocity velocity, GameEnvironment environment) {
        this.utils = new Utils();
        this.color = Color.WHITE;
        this.circle = utils.getStdBallCircle();
        this.velocity = velocity;
        this.environment = environment;
    }

    /**
     * Constructs a ball.
     *
     * @param circle the circle.
     * @param speed the speed of the ball.
     * @param environment the environment.
     */
    public Ball(Circle circle, double speed, GameEnvironment environment) {
        this.circle = circle;
        this.environment = environment;
        this.utils = new Utils();
        this.velocity = utils.genRandVelocity(speed);
    }

    /**
     * Gives the ball's data as string.
     *
     * @return ball's data as string.
     */
    public String toString() {
        return this.circle.getCenter().toString() + " r: " + this.circle.getRadius();
    }

    /**
     * Draws this ball using given draw surface.
     *
     * @param surface use this to draw.
     */
    public void drawOn(DrawSurface surface) {
        // fill circle with color
        surface.setColor(this.color);
        surface.fillCircle((int) circle.getCenter().getX(), (int) circle.getCenter().getY(), circle.getRadius());
        // draw circle with black
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) circle.getCenter().getX(), (int) circle.getCenter().getY(), circle.getRadius());
        // draw red point in the center
        surface.setColor(Color.RED);
        surface.drawCircle((int) circle.getCenter().getX(), (int) this.circle.getCenter().getY(), 1);
    }

    /**
     * Adds this ball to given game.
     *
     * @param gameLevel the GameLevel that this added to.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * Handles balls behavior when time passed.
     */
    public void timePassed() {
        // used try catch for even more protection
        try {
          this.moveOneStep();

        } catch (Exception e) {

            double xPos = this.circle.getCenter().getX();
            double yPos = this.circle.getCenter().getY();
            int epsilon = 1;
            this.circle.setCenter(new Point(xPos - epsilon, yPos - epsilon));

        }
    }

    /**
     * Handles the movement of the ball.
     * Calculate ball's trajectory.
     * If it doesn't hit a block so it moves.
     * If hits so move it near the collision point, notify about the collision
     * and update velocity.
     * Note: it could be implemented in many different ways, but here it follows
     * the pattern that written in the demands.
     */
    public void moveOneStep() {
        // solve case ball is on paddle because paddle moves
        this.checkOnPaddle();

        // solve case ball is on borders
        this.checkOnBorders();

        // solve case ball is on blocks (not borders)
        this.checkOnBlocks();

        // the end point of the trajectory and trajectory
        Point target = this.velocity.applyToPoint(this.circle.getCenter());
        Line trajectory = new Line(this.circle.getCenter(), target);

        // if moving on this trajectory hit anything
        if (!this.environment.isCollide(trajectory)) {
            // move the ball to the end of the trajectory
            this.circle.setCenter(target);
            return;
        }

        /*
        if there is a hit
        move the ball to almost the hit point but just slightly before it
        */
        double xPos = this.circle.getCenter().getX();
        double yPos = this.circle.getCenter().getY();

        // for fix position
        int epsilon = 1;
        int addX = 0;
        int addY = 0;

        // Collision info, point, object and rectangle of collision
        CollisionInfo infoCol = this.environment.getClosestCollision(trajectory);
        Point colPoint = infoCol.collisionPoint();
        Collidable colObj = infoCol.collisionObject();
        Rectangle colRec = colObj.getCollisionRectangle();

        // add some epsilon up, right, down or left
        if (colRec.isOnTop(colPoint)) {
            addY = -epsilon;
        }
        if (colRec.isOnRight(colPoint)) {
            addX = epsilon;
        }
        if (colRec.isOnBottom(colPoint)) {
            addY = epsilon;
        }
        if (colRec.isOnLeft(colPoint)) {
            addX = -epsilon;
        }
        Point newCenter = new Point(xPos + addX, yPos + addY);

        // notify hit object collision occurred and update the velocity
        this.velocity = colObj.hit(this, colPoint, this.velocity);
        this.circle.setCenter(this.velocity.applyToPoint(newCenter));
    }

    /**
     * Solves case ball is on paddle.
     */
    public void checkOnPaddle() {
        // sizes, ball's position and epsilon for fix
        int height = this.environment.getHeight();
        int border = this.environment.getBorder();
        double xPos = this.circle.getCenter().getX();
        int epsilon = this.circle.getRadius();

        // if on paddle move it up
        Rectangle paddleRec = environment.getPaddleRectangle();
        if (paddleRec.isPointInside(this.circle.getCenter())) {
            double pdlHeight = paddleRec.getHeight();
            this.circle.setCenter(new Point(xPos, height - 0.3 * border - pdlHeight - epsilon));
        }
    }

    /**
     * Solves case ball is on borders.
     */
    public void checkOnBorders() {
        // sizes, ball's position and epsilon for fix
        int width = this.environment.getWidth();
        int border = this.environment.getBorder();
        double xPos = this.circle.getCenter().getX();
        double yPos = this.circle.getCenter().getY();
        int epsilon = this.circle.getRadius();

        if (this.environment.getTop().isPointInside(this.circle.getCenter())) {
            // case on top move it down
            this.circle.setCenter(new Point(xPos, border + epsilon));

        } else if (this.environment.getRight().isPointInside(this.circle.getCenter())) {
            // case on right move it left
            this.circle.setCenter(new Point(width - border - epsilon, yPos));

        } else if (this.environment.getLeft().isPointInside(this.circle.getCenter())) {
            // case on left move it right
            this.circle.setCenter(new Point(border + epsilon, yPos));
        }
    }

    /**
     * Solves case ball is on blocks.
     */
    public void checkOnBlocks() {
        // ball's position and epsilon for fix
        double xPos = this.circle.getCenter().getX();
        double yPos = this.circle.getCenter().getY();
        int epsilon = this.circle.getRadius();

        // pass over blocks and check if ball is on
        List<Collidable> copy = new ArrayList<>(this.environment.getCollidables());

        for (Collidable collidable : copy) {
            if (collidable.getCollisionRectangle().isPointInside(this.circle.getCenter())) {
                // case on block move it down
                this.circle.setCenter(new Point(xPos, yPos + epsilon));
                break;
            }
        }
    }

    /**
     * Removes from game.
     *
     * @param gameLevel that removing from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}