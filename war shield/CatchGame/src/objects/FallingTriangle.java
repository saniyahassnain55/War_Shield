package objects;

import java.awt.*;
public class FallingTriangle extends FallingRectangle implements FallingObjects {
    public FallingTriangle(int x, int speed) {
        super(x, speed); // Call the constructor of FallingObject
    }

    @Override
    public void move() {
        super.move(); // Use the existing movement logic
    }


    @Override
    public void draw(Graphics g) {
        int[] xPoints = {rectangle.x, rectangle.x + rectangle.width / 2, rectangle.x + rectangle.width};
        int[] yPoints = {rectangle.y + rectangle.height, rectangle.y, rectangle.y + rectangle.height};

        g.setColor(Color.RED); // Set a different color for triangles
        g.fillPolygon(xPoints, yPoints, 3);
    }

    @Override
    public boolean intersects(Rectangle platform) {
        int[] xPoints = {rectangle.x, rectangle.x + rectangle.width / 2, rectangle.x + rectangle.width};
        int[] yPoints = {rectangle.y + rectangle.height, rectangle.y, rectangle.y + rectangle.height};

        // Check if any of the triangle's vertices are inside the platform
        for (int i = 0; i < xPoints.length; i++) {
            if (platform.contains(xPoints[i], yPoints[i])) {
                return true;
            }
        }
        return false;
    }
}
