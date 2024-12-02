package objects;

import java.awt.*;

public interface FallingObjects {

    public void draw(Graphics g);
    public boolean intersects(Rectangle platform);
    public void move();
    public int getHeight();
}
