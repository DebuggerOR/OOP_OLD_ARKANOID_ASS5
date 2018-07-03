package sprites;

import biuoop.DrawSurface;
import collisions.Collidable;
import animation.GameLevel;
import general.Utils;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import listeners.HitListener;
import listeners.HitNotifier;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Block class.
 *
 * @author Ori.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle shape;
    private Color color;
    private int hitPoints;
    private List<HitListener> hitListeners;
    private Utils utils;

    /**
     * Constructs a block.
     */
    public Block() {
        this.utils = new Utils();
        this.color = Color.WHITE;
        this.hitPoints = -1;
        this.shape = null;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructs a block with given shape and color.
     *
     * @param shape     the shape of the block.
     * @param color     the color of the block.
     * @param hitPoints the hit points of the block.
     * @param hitListener  d
     */
    public Block(Rectangle shape, Color color, int hitPoints, HitListener hitListener) {
        this.utils = new Utils();
        this.shape = shape;
        this.color = color;
        this.hitPoints = hitPoints;
        this.hitListeners = new ArrayList<>();
        this.hitListeners.add(hitListener);
    }

    /**
     * Constructs a new block.
     *
     * @param shape the shape.
     * @param color the color.
     * @param hitPoints the hit points.
     */
    public Block(Rectangle shape, Color color, int hitPoints) {
        this.utils = new Utils();
        this.shape = shape;
        this.color = color;
        this.hitPoints = hitPoints;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructs a new block.
     *
     * @param color the color.
     * @param hitPoints the hit points.
     */
    public Block(Color color, int hitPoints) {
        this.utils = new Utils();
        this.color = color;
        this.hitPoints = hitPoints;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructs a new block.
     *
     * @param color the color.
     * @param shape the shape.
     */
    public Block(Rectangle shape, Color color) {
        this.utils = new Utils();
        this.shape = shape;
        this.color = color;
        this.hitPoints = this.utils.getStdHitPoints();
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Gives the hit points.
     *
     * @return the hit points.
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * Sets the color of the block.
     *
     * @param newColor the new color of the block.
     */
    public void setColor(Color newColor) {
        this.color = newColor;
    }

    /**
     * Sets the hit points.
     *
     * @param newHitPoints the new hit points.
     */
    public void setHitPoints(int newHitPoints) {
        this.hitPoints = newHitPoints;
    }

    /**
     * Gives the shape.
     *
     * @return the shape.
     */
    public Rectangle getShape() {
        return shape;
    }

    /**
     * Sets the shape.
     *
     * @param newShape the new shape.
     */
    public void setShape(Rectangle newShape) {
        this.shape = newShape;
    }

    /**
     * Gives the collision shape of the object.
     *
     * @return the collision shape of the object.
     */
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    /**
     * Gives the block's data as string.
     *
     * @return block's data as string.
     */
    public String toString() {
        return this.shape.toString();
    }

    /**
     * Checks if this block is equal to given other block.
     *
     * @param other block that checked if equal to this.
     * @return true if this is equal to other, else false.
     */
    public boolean equals(Block other) {
        //equal if shape, color and hitPoints are equal
        boolean shapeEqual = this.shape.equals(other.getCollisionRectangle());
        boolean colorEqual = this.color.equals(other.color);
        boolean numHitsEqual = (this.hitPoints == other.hitPoints);
        return shapeEqual && colorEqual && numHitsEqual;
    }

    /**
     * Notifies the object that we collided with it at collisionPoint with
     * a given velocity.
     *
     * @param hitter d
     * @param collisionPoint  the point of the collision.
     * @param currentVelocity the velocity when it collides.
     * @return the return is the new velocity expected after the hit.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // update numHits
        if (hitPoints > 0) {
            this.hitPoints--;
        }
        // dx and dy
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        // take the lines clockwise
        Line top = this.shape.getLines().get(0);
        Line right = this.shape.getLines().get(1);
        Line bottom = this.shape.getLines().get(2);
        Line left = this.shape.getLines().get(3);

        // booleans if hit the upper, lower, left or right side
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

        this.notifyHit(hitter);

        // return updated velocity
        return currentVelocity;
    }

    /**
     * Draws this ball using given draw surface.
     *
     * @param surface use this to draw.
     */
    public void drawOn(DrawSurface surface) {
        // block coordinates sizes
        int startX = (int) this.shape.getUpperLeft().getX();
        int startY = (int) this.shape.getUpperLeft().getY();
        int height = (int) this.shape.getHeight();
        int width = (int) this.shape.getWidth();

        // draw rectangle with color
        surface.setColor(this.color);
        surface.fillRectangle(startX, startY, width, height);
        // draw frame
        surface.setColor(Color.BLACK);
        surface.drawRectangle(startX, startY, width, height);

        // case 1 hitPoint draw !
        if (this.hitPoints == 1) {
            if (shape.getHeight() == utils.getStdBlockHeight()) {
                surface.setColor(Color.YELLOW);

                surface.fillCircle((int) (shape.getUpperLeft().getX()
                        + 0.5 * utils.getStdBlockWidth()), (int) (shape
                        .getUpperLeft().getY() + 0.5 * utils.getStdBlockHeight()), 12);

                surface.setColor(Color.BLACK);

                surface.drawCircle((int) (shape.getUpperLeft().getX()
                        + 0.5 * utils.getStdBlockWidth()), (int) (shape
                        .getUpperLeft().getY() + 0.5 * utils.getStdBlockHeight()), 12);

                surface.fillCircle((int) (shape.getUpperLeft().getX()
                        + 0.5 * utils.getStdBlockWidth()), (int) (shape
                        .getUpperLeft().getY() + 0.5 * utils.getStdBlockHeight() + 5), 5);

                surface.fillCircle((int) (shape.getUpperLeft().getX()
                        + 0.5 * utils.getStdBlockWidth() - 6), (int) (shape
                        .getUpperLeft().getY() + 0.5 * utils.getStdBlockHeight() - 3), 3);

                surface.fillCircle((int) (shape.getUpperLeft().getX()
                        + 0.5 * utils.getStdBlockWidth() + 6), (int) (shape
                        .getUpperLeft().getY() + 0.5 * utils.getStdBlockHeight() - 3), 3);
            }
        }
    }

    /**
     * Adds this to sprites and collidables.
     *
     * @param gameLevel the GameLevel that
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * Removes from game.
     *
     * @param gameLevel the gameLevel to remove from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     *
     */
    public void timePassed() {
    }

    /**
     * Notifies hit.
     *
     * @param hitter the hitter ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Removes hit listener.
     *
     * @param hl the hit listener to be removed.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Adds hit listener.
     *
     * @param hl the hit listener to be added.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Gives hit listeners.
     *
     * @return hit listeners.
     */
    public List<HitListener> getHitListeners() {
        return hitListeners;
    }
}