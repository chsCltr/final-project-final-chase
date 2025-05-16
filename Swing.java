import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Swing extends Actor
{
    private int countdown = 10;  // Time before the swing is removed

    public void act()
    {
        // Decrease the countdown each frame
        countdown--;

        // When the countdown reaches 0, remove the swing from the world
        if (countdown <= 0) {
            getWorld().removeObject(this);  // Corrected to remove the object from the world
        }
    }
}
