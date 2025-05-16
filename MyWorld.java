import greenfoot.*;

public class MyWorld extends World {
    public MyWorld() {    
        super(600, 400, 1);

        // Load, scale, and set the background image
        GreenfootImage bg = new GreenfootImage("images/background.jpg");
        bg.scale(getWidth(), getHeight()); // Scale to fit world
        setBackground(bg); // Set the scaled image as background

        Player player = new Player();
        addObject(player, 300, 200);

        spawnScreen1();
    }
    
    public void spawnSwing(String direction, Player player) {
        Swing swing = new Swing();  

        int spawnX = player.getX();
        int spawnY = player.getY();

        switch (direction) {
            case "right": spawnX += 40; break;
            case "left":  spawnX -= 40; break;
            case "up":    spawnY -= 40; break;
            case "down":  spawnY += 40; break;
        }

        addObject(swing, spawnX, spawnY);
    }
    public void spawnScreen1(){
        Ground platform1 = new Ground(300, 20); // Wide platform
        Ground platform2 = new Ground(200, 30);
        Ground platform3 = new Ground(300,30);
        addObject(platform1, 120, 285); // Centered at bottom
        addObject(platform2, 300, 325);
        addObject(platform3, 500,380);
    }
}
