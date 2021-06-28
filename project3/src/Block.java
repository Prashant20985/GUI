import java.awt.*;
import java.util.Random;

public class Block {

    public int squareHeight=50;
    public int squareWidth=50;
    public int squareXLocation;
    public int squareYLocation = -squareHeight;

    private int fallSpeed = 1;
    Random rand = new Random();



    //creates a random value inside the window and stores it in squareXLocation
    public int generateRandomXLocation(){
        return squareXLocation = rand.nextInt(Game.WIDTH - squareHeight);
    }

    //creates a random value that is not zero and stores it in fallSpeed(so squares do not get stuck at 0 speed)
    public int generateRandomFallSpeed(){
        return fallSpeed = rand.ints(1, 1, 10).findFirst().getAsInt();
    }

    //paints the square with the variables generated in the random methods
    public void paint(Graphics g){
        g.setColor(Color.red);
        g.fillRect(squareXLocation,squareYLocation, squareHeight, squareWidth);
    }

    //sets the squareWidth and square fallSpeed to a random value for every square created
    public Block(){
        generateRandomXLocation();
        generateRandomFallSpeed();
    }

    public void update(){
        //changes the squares xLocation and fallSpeed if the created square reaches the bottom of the screen
        if(squareYLocation >= Game.HEIGHT){
            generateRandomXLocation();
            generateRandomFallSpeed();
            squareYLocation = -squareHeight;
        }

        //moves the square down if the square is inside the window
        if(squareYLocation <= Game.HEIGHT){
            squareYLocation += fallSpeed;
        }

    }
}
