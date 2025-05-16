import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Player extends Actor {
    private int velocityY = 0;
    private int gravity = 1;
    private int jumpStrength = -15;
    private int maxFallSpeed = 10;
    private boolean onGround = false;

    public void act() {
        checkKeys();
        applyGravity();
        checkGround();
    }

    public void checkKeys() {
        if (Greenfoot.isKeyDown("left")) {
            move(-5);
        }
        if (Greenfoot.isKeyDown("right")) {
            move(5);
        }
        if (onGround && Greenfoot.isKeyDown("space")) {
            jump();
        }
    }

    public void jump() {
        velocityY = jumpStrength;
        onGround = false;
    }

    public void applyGravity() {
        velocityY += gravity;

        if (velocityY > maxFallSpeed) {
            velocityY = maxFallSpeed;
        }

        setLocation(getX(), getY() + velocityY);
    }

        public void checkGround() {
        World world = getWorld();
        // If touching a Ground object
        if (isTouching(Ground.class)) {
            while (isTouching(Ground.class)) {
                setLocation(getX(), getY() - 1);
            }
            velocityY = 0;
            onGround = true;
        }
        // If standing at the bottom of the world
        else if (getY() + getImage().getHeight() / 2 >= world.getHeight()) {
            setLocation(getX(), world.getHeight() - getImage().getHeight() / 2);
            velocityY = 0;
            onGround = true;
        }
        else {
            onGround = false;
        }
    }
}
