import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Ground extends Actor {
    public Ground(int width, int height) {
        GreenfootImage img = new GreenfootImage(width, height);
        img.setColor(Color.BLACK);
        img.fill();
        setImage(img);
    }

    public void act() {
        // Ground does not need to act
    }
}
