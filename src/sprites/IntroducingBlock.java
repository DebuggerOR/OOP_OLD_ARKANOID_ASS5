package sprites;

import biuoop.DrawSurface;
import general.Utils;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import listeners.BallAdder;

import java.awt.Color;

/**
 * Introducing block class.
 */
public class IntroducingBlock extends Block {
    private boolean drawYellow;
    private Utils utils;

    /**
     * Constructs an introducing block with given shape.
     *
     * @param shape the shape of the block.
     * @param adder s.
     */
    public IntroducingBlock(Rectangle shape, BallAdder adder) {
        super(shape, Color.GREEN);
        this.utils = new Utils();
        super.setHitPoints(-1);
        this.drawYellow = false;
        super.addHitListener(adder);
    }

    /**
     * Notifies the object that we collided with it at collisionPoint with
     * a given velocity.
     *
     * @param hitter          the hitter.
     * @param collisionPoint  the point of the collision.
     * @param currentVelocity the velocity when it collides.
     * @return the return is the new velocity expected after the hit.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.drawYellow = true;
        return super.hit(hitter, collisionPoint, currentVelocity);
    }

    /**
     * Draws this ball using given draw surface.
     *
     * @param surface use this to draw.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        if (this.drawYellow) {
            // draw in yellow when hit
            this.drawYellow = false;
            super.setColor(Color.YELLOW);
        } else {
            // draw in green
            super.setColor(new Color(0, 153, 0));
        }

        // draw the regular block
        super.drawOn(surface);

        // draw inside only if the block is big enough
        if (super.getShape().getHeight() >= utils.getStdBlockHeight()) {
            // draw white circle
            surface.setColor(Color.WHITE);
            surface.fillCircle((int) (super.getShape().getUpperLeft().getX()
                    + 0.5 * utils.getStdBlockWidth()), (int) (super.getShape().getUpperLeft().getY()
                    + 0.5 * utils.getStdBlockHeight()), 12);
            // draw black frame
            surface.setColor(Color.BLACK);
            surface.drawCircle((int) (super.getShape().getUpperLeft().getX()
                    + 0.5 * utils.getStdBlockWidth()), (int) (super.getShape().getUpperLeft().getY()
                    + 0.5 * utils.getStdBlockHeight()), 12);
            // write I inside
            surface.drawText((int) (super.getShape().getUpperLeft().getX()
                    + 0.5 * utils.getStdBlockWidth() - 3), (int) (super.getShape().getUpperLeft().getY()
                    + 0.5 * utils.getStdBlockHeight() + 7), "I", 20);
        }
    }
}