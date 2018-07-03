package sprites;

import biuoop.DrawSurface;
import animation.GameLevel;
import general.Utils;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Background class.
 *
 * @author Ori.
 */
public class BackGround implements Sprite {
    private int width;
    private int height;
    private Color color;
    private ArrayList<Shape> shapes;
    private Utils utils;

    /**
     * Constructs a background with given sizes, color and message.
     *
     * @param color   the color of the background.
     */
    public BackGround(Color color) {
        this.utils = new Utils();
        this.width = this.utils.getStdWindowWidth();
        this.height = this.utils.getStdWindowHeight();
        this.color = color;
        this.shapes = new ArrayList<>();
        this.shapes.add(new Rectangle(new Point(0, 0), width, height, this.color));
    }

    /**
     * Handles background behavior as time passed.
     */
    public void timePassed() {
    }

    /**
     * Draws background.
     *
     * @param surface the surface that background drawn on.
     */
    public void drawOn(DrawSurface surface) {
        // draw rectangle in color
        surface.setColor(this.color);
        surface.fillRectangle(0, 0, this.width, this.height);

        for (Shape shape : this.shapes) {
            surface.setColor(shape.color());
            shape.drawOn(surface);
        }
    }

    /**
     * Adds this background to given game.
     *
     * @param gameLevel that this added to.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * Adds shape to this.
     *
     * @param shape the shape to add.
     */
    public void addShape(Shape shape) {
        this.shapes.add(shape);
    }
}
