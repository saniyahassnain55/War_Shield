package objects;
import java.awt.*;

public class FallingObjectStar extends FallingRectangle implements FallingObjects {

    public FallingObjectStar(int x, int speed) {
        super(x, speed); // Call the constructor of FallingRectangle (we're reusing its logic)
    }

    @Override
    public void move() {
        super.move(); // Use the existing movement logic from FallingRectangle
    }

    @Override
    public void draw(Graphics g) {
        // Drawing a star shape using lines (5 points star)
        int[] xPoints = {rectangle.x + rectangle.width / 2, rectangle.x + rectangle.width / 4, rectangle.x + rectangle.width, rectangle.x + rectangle.width / 4 * 3, rectangle.x};
        int[] yPoints = {rectangle.y, rectangle.y + rectangle.height, rectangle.y + rectangle.height, rectangle.y + rectangle.height / 4, rectangle.y + rectangle.height / 4};

        g.setColor(Color.ORANGE); // Set color to yellow for stars
        g.fillPolygon(xPoints, yPoints, 5); // Fill the polygon to create a star shape
    }

    @Override
    public boolean intersects(Rectangle platform) {
        // Check if any of the star's vertices are inside the platform
        int[] xPoints = {rectangle.x + rectangle.width / 2, rectangle.x + rectangle.width / 4, rectangle.x + rectangle.width, rectangle.x + rectangle.width / 4 * 3, rectangle.x};
        int[] yPoints = {rectangle.y, rectangle.y + rectangle.height, rectangle.y + rectangle.height, rectangle.y + rectangle.height / 4, rectangle.y + rectangle.height / 4};

        for (int i = 0; i < xPoints.length; i++) {
            if (platform.contains(xPoints[i], yPoints[i])) {
                return true; // If any vertex is inside the platform, return true
            }
        }
        return false; // Otherwise, return false
    }
}

