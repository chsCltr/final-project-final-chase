import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Ground extends Actor {
    public Ground(int width, int height) {
        GreenfootImage img = new GreenfootImage(width, height);
        //img.setColor(Color.BLACK);
        img.setColor(new Color(255, 255, 255, 50)); // Black with some transparency
        img.fill();
        setImage(img);
    }

    public void act() {
        // Ground does not need to act
    }
}
