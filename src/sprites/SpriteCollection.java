package sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * A SpriteCollection class.
 *
 * @author Ori.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Constructs a new SpriteCollection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Adds given sprite to sprites.
     *
     * @param sprite a sprite to add to sprites.
     */
    public void removeSprite(Sprite sprite) {
        this.sprites.remove(sprite);
    }

    /**
     * Adds given sprite to sprites.
     *
     * @param sprite a sprite to add to sprites.
     */
    public void addSprite(Sprite sprite) {
        this.sprites.add(sprite);
    }

    /**
     * Calls timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> copy = new ArrayList<>(sprites);
        for (Sprite s : copy) {
            s.timePassed();
        }
    }

    /**
     * Calls drawOn(surface) on all sprites.
     *
     * @param surface the surface to draw on.
     */
    public void drawAllOn(DrawSurface surface) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(surface);
        }
    }
}