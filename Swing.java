import greenfoot.*;

public class Swing extends Actor {
    private int countdown = 10;  // Time before the swing is removed

    public Swing() {
        GreenfootImage img = new GreenfootImage("images/swing.png");
        img.scale(80, 40);  // Adjust the size of the swing image
        setImage(img);
    }

    public void setRotationBasedOnDirection(String direction) {
        switch (direction) {
            case "right":
                setRotation(0);  // No rotation for right
                break;
            case "left":
                setRotation(180);  // Flip the swing for left
                break;
            case "up":
                setRotation(270);  // Rotate for upward swing
                break;
            case "down":
                setRotation(90);  // Rotate for downward swing
                break;
        }
    }

    public void act() {
        countdown--;
        if (countdown <= 0) {
            getWorld().removeObject(this);  // Remove swing after countdown
        }
    }
}
