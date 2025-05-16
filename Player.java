import greenfoot.*;

public class Player extends Actor {
    private int velocityY = 0;
    private final int gravity = 1;
    private final int jumpStrength = -15;
    private final int maxFallSpeed = 10;
    private boolean onGround = false;
    public String directionFacing = "left";

    private boolean swingSpawned = false;
    private GreenfootImage leftImage;
    private GreenfootImage rightImage;

    public Player() {
        leftImage = new GreenfootImage("images/vessel.png"); // Default is facing left
        rightImage = new GreenfootImage("images/vessel.png");
        rightImage.mirrorHorizontally(); // Create a mirrored version for right-facing

        setImage(leftImage); // Start facing left
    }

    public void act() {
        checkKeys();
        applyGravity();
        checkGround();
    }
    
        private void checkKeys() {
        boolean moved = false;
    
        if (Greenfoot.isKeyDown("left")) {
            move(-5);
            moved = true;
            if (!directionFacing.equals("left")) {
                directionFacing = "left";
                setImage(leftImage);
            }
            swingSpawned = false;
        }
    
        if (Greenfoot.isKeyDown("right")) {
            move(5);
            moved = true;
            if (!directionFacing.equals("right")) {
                directionFacing = "right";
                setImage(rightImage);
            }
            swingSpawned = false;
        }
    
        // Handle jump
        if (onGround && Greenfoot.isKeyDown("space")) {
            jump();
        }
    
        // Handle attacks — only one swing per press
        if (Greenfoot.isKeyDown("x") && !swingSpawned) {
            if (Greenfoot.isKeyDown("up")) {
                spawnSwing("up");
            } else if (Greenfoot.isKeyDown("down") && !onGround) {
                spawnSwing("down");
            } else {
                spawnSwing(directionFacing); // left or right
            }
            swingSpawned = true;
        }
    
        // Reset swing flag when X key is released
        if (!Greenfoot.isKeyDown("x")) {
            swingSpawned = false;
        }
    }


    private void jump() {
        velocityY = jumpStrength;
        onGround = false;
    }

    private void applyGravity() {
        velocityY += gravity;
        if (velocityY > maxFallSpeed) {
            velocityY = maxFallSpeed;
        }
        setLocation(getX(), getY() + velocityY);
    }

    private void checkGround() {
        World world = getWorld();

        if (isTouching(Ground.class)) {
            while (isTouching(Ground.class)) {
                setLocation(getX(), getY() - 1);
            }
            velocityY = 0;
            onGround = true;
        } else if (getY() + getImage().getHeight() / 2 >= world.getHeight()) {
            setLocation(getX(), world.getHeight() - getImage().getHeight() / 2);
            velocityY = 0;
            onGround = true;
        } else {
            onGround = false;
        }
    }

    private void spawnSwing(String direction) {
        MyWorld world = (MyWorld) getWorld();
        world.spawnSwing(direction, this);
    }
}
