import greenfoot.*;

public class Swing extends Actor {
    private GreenfootImage[] swingFrames = new GreenfootImage[3];
    private int currentFrame = 0;
    private int frameDelay = 5;
    private int frameCounter = 0;

    public Swing() {
        for (int i = 0; i < 3; i++) {
            swingFrames[i] = new GreenfootImage("images/attacks/swing" + (i + 1) + ".png");
        }
        setImage(swingFrames[0]);
    }

    public void act() {
        frameCounter++;
        if (frameCounter >= frameDelay) {
            frameCounter = 0;
            currentFrame++;
            if (currentFrame >= swingFrames.length) {
                getWorld().removeObject(this);
                return;
            }
            setImage(swingFrames[currentFrame]);
        }
    }

    public void setRotationBasedOnDirection(String direction) {
        switch (direction) {
            case "right":
                setRotation(0);
                break;
            case "left":
                setRotation(180);
                break;
            case "up":
                setRotation(270);
                break;
            case "down":
                setRotation(90);
                break;
        }
    }
}
