import greenfoot.*;

public class Player extends Actor {
    private int velocityY = 0;
    private final int gravity = 1;
    private final int jumpStrength = -10;
    private final int maxFallSpeed = 10;
    private boolean onGround = false;
    public String directionFacing = "left";
    
    private boolean swingSpawned = false;
    private int swingCooldown = 0;  // Time remaining until next swing
    private final int swingCooldownTime = 20;  // Cooldown frames
    private int swingDistance = 40;
    
    private GreenfootImage[] idleImages;
    private GreenfootImage[] runImages;
    private GreenfootImage attackImage; // Attack frame
    private int runFrame = 0;
    private int runDelay = 5;
    private int runCounter = 0;

    private boolean isJumping = false;
    private int jumpTimer = 0;
    private final int maxJumpTime = 8;

    private int attackDisplayCounter = 0;
    private final int attackDisplayTime = 10;

    public Player() {
        // Load idle image
        idleImages = new GreenfootImage[1];
        idleImages[0] = new GreenfootImage("player/idle.png");

        // Load run animation
        runImages = new GreenfootImage[4];
        for (int i = 0; i < runImages.length; i++) {
            runImages[i] = new GreenfootImage("player/run/run" + (i + 1) + ".png");
        }

        // Load attack image (swing3.png)
        attackImage = new GreenfootImage("images/player/attacks/swing3.png");

        // Set initial image facing left
        GreenfootImage start = new GreenfootImage(idleImages[0]);
        start.mirrorHorizontally();
        setImage(start);
    }

    public void act() {
        checkKeys();
        applyGravity();
        checkGround();
        animate();

        if (swingCooldown > 0) {
            swingCooldown--;
        }
    }

    private void checkKeys() {
        if (Greenfoot.isKeyDown("left")) {
            move(-5);
            directionFacing = "left";
            swingSpawned = false;
        }
        if (Greenfoot.isKeyDown("right")) {
            move(5);
            directionFacing = "right";
            swingSpawned = false;
        }
        if (onGround && Greenfoot.isKeyDown("space")) {
            isJumping = true;
            jumpTimer = 0;
            onGround = false;
            velocityY = jumpStrength;
        }
        if (isJumping && Greenfoot.isKeyDown("space")) {
            if (jumpTimer < maxJumpTime) {
                velocityY = jumpStrength;
                jumpTimer++;
            } else {
                isJumping = false;
            }
        }
        if (!Greenfoot.isKeyDown("space")) {
            isJumping = false;
        }

        if (Greenfoot.isKeyDown("x") && !swingSpawned && swingCooldown == 0) {
            showAttackFrame();
            spawnSwing();
            swingSpawned = true;
            swingCooldown = swingCooldownTime;
        }
        if (!Greenfoot.isKeyDown("x")) {
            swingSpawned = false;
        }
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
        Actor ground = getOneIntersectingObject(Ground.class);

        if (ground != null) {
            while (isTouching(Ground.class)) {
                setLocation(getX(), getY() - 1);
            }
            velocityY = 0;
            onGround = true;
        } else {
            onGround = false;
        }
        if (getY() + getImage().getHeight() / 2 >= world.getHeight()) {
            setLocation(getX(), world.getHeight() - getImage().getHeight() / 2);
            velocityY = 0;
            onGround = true;
        }
    }

    private void animate() {
        if (attackDisplayCounter > 0) {
            attackDisplayCounter--;
            GreenfootImage img = new GreenfootImage(attackImage);
            if (directionFacing.equals("left")) img.mirrorHorizontally();
            setImage(img);
            return;
        }

        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("right")) {
            runCounter++;
            if (runCounter >= runDelay) {
                runCounter = 0;
                runFrame = (runFrame + 1) % runImages.length;
            }
            GreenfootImage img = new GreenfootImage(runImages[runFrame]);
            if (directionFacing.equals("left")) img.mirrorHorizontally();
            setImage(img);
        } else {
            GreenfootImage idle = new GreenfootImage(idleImages[0]);
            if (directionFacing.equals("left")) idle.mirrorHorizontally();
            setImage(idle);
        }
    }

    private void showAttackFrame() {
        attackDisplayCounter = attackDisplayTime;
    }

    private void spawnSwing() {
        String dir = directionFacing;
        if (Greenfoot.isKeyDown("up")) dir = "up";
        else if (Greenfoot.isKeyDown("down") && !onGround) dir = "down";

        MyWorld world = (MyWorld) getWorld();
        Swing swing = new Swing();

        int spawnX = getX();
        int spawnY = getY();

        switch (dir) {
            case "right": spawnX += swingDistance; break;
            case "left":  spawnX -= swingDistance; break;
            case "up":    spawnY -= swingDistance; break;
            case "down":  spawnY += swingDistance; break;
        }

        swing.setRotationBasedOnDirection(dir);
        world.addObject(swing, spawnX, spawnY);
    }
}
