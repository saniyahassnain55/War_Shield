package objects;

import java.awt.*;

public class FallingRectangle implements FallingObjects{
    protected Rectangle rectangle;
    int speed;

    public FallingRectangle(int x, int speed) {
        this.rectangle = new Rectangle(x, 0, 20, 20);
        this.speed = speed;
    }

    public void draw(Graphics g) {
        g.fillRect(this.rectangle.x, this.rectangle.y, this.rectangle.width, this.rectangle.height);
    }

    public boolean intersects(Rectangle platform) {
       return this.rectangle.y + this.rectangle.height > platform.y && this.rectangle.intersects(platform);
    }

    public void move() {
        rectangle.y += speed;
    }

    @Override
    public int getHeight() {
        return this.rectangle.y;
    }

 
}