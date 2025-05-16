import greenfoot.*;

public class MyWorld extends World {
    public MyWorld() {    
        super(600, 400, 1);

        Player player = new Player();
        addObject(player, 300, 200);

        // Create custom-sized ground blocks
        Ground platform1 = new Ground(200, 20); // Wide platform
        Ground platform2 = new Ground(100, 30); // Taller platform

        addObject(platform1, 300, 350); // Centered at bottom
        addObject(platform2, 500, 300); // Right side
    }
}

