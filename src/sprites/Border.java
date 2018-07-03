package sprites;

import general.Utils;
import geometry.Point;
import geometry.Rectangle;

/**
 * Border class.
 */
public class Border extends Block {
    private Utils utils;

    /**
     * Constructs a border.
     *
     * @param numSide the side of the border clockwise (1 = up, 2 = right, 3 = bottom and 4 = left).
     */
    public Border(int numSide) {
        utils = new Utils();
        super.setColor(utils.getStdBorderColor());

        this.utils = new Utils();

        Point topLeft;
        Rectangle shape;

        switch (numSide) {
            // up
            case 1:
                topLeft = new Point(0, 0);
                shape = new Rectangle(topLeft, utils.getStdWindowWidth(), 1.75 * utils.getStdBorderSize());
                break;
            // right
            case 2:
                topLeft = new Point(utils.getStdWindowWidth() - utils.getStdBorderSize(), 0);
                shape = new Rectangle(topLeft, utils.getStdBorderSize(), utils.getStdWindowHeight());
                break;
            // bottom
            case 3:
                topLeft = new Point(0, utils.getStdWindowHeight() - utils.getStdBorderSize());
                shape = new Rectangle(topLeft, utils.getStdWindowWidth(), utils.getStdBorderSize());
                break;
            // left
            default:
                topLeft = new Point(0, 0);
                shape = new Rectangle(topLeft, utils.getStdBorderSize(), utils.getStdWindowHeight());
        }

        super.setShape(shape);
    }
}
