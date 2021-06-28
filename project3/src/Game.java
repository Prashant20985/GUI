import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class Game extends JPanel {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    private static String TITLE = "Game";
    private static boolean running = false;
    private static int Score;
    static int CurrentScore=0;

    private static java.util.List<Block> ShapeList = new ArrayList<>();

    public Game() {
        //initializes square objects
        for(int i=0;i<50;i++)
            ShapeList.add(new Block());
    }

    public void paint(Graphics graphics) {

        //makes background black
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        //paints square objects to the screen
        for (Block sblock : ShapeList) {
            sblock.paint(graphics);
        }
    }

    public void update() {
        //calls the Block class update method on the square objects
        for (Block sblock : ShapeList) sblock.update();
    }
    public static void start() {
        running = true;
    }

    public static void main(String[] args) throws InterruptedException {
        new Game();
        Game.CreateFrame();
        }
        public static void CreateFrame() throws InterruptedException {
        Game game = new Game();
        //Creating Game Frame
        JFrame frame = new JFrame();
        frame.add(game);
        frame.setVisible(true);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle(TITLE);
        frame.setResizable(false);
        frame.setBackground(Color.BLACK);
        //Creating Status bar
        JPanel status = new JPanel();
        status.setBorder(new BevelBorder(BevelBorder.LOWERED));
        frame.add(status, BorderLayout.SOUTH);
        status.setPreferredSize(new Dimension(frame.getWidth(), 25));
        status.setLayout(new BoxLayout(status, BoxLayout.X_AXIS));
        JLabel statusLabel = new JLabel();
        status.add(statusLabel);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        //Removing Boxes on mouse click
        frame.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                for (Block s : ShapeList)
                    if ((x > s.squareXLocation) && (x < s.squareXLocation + s.squareWidth) && (y > s.squareYLocation) && (y < s.squareYLocation + s.squareHeight)) {
                        ShapeList.remove(s);
                       CurrentScore = Score++;
                       //updating Scores
                       statusLabel.setText("CURRENT SCORE: " + (CurrentScore+1));
                       if(CurrentScore+1>=15)
                           statusLabel.setText("CURRENT SCORE: " + (CurrentScore+1)+" (Poor)");
                       if(CurrentScore+1>=25)
                           statusLabel.setText("CURRENT SCORE: " + (CurrentScore+1)+" (Average)");
                       if(CurrentScore+1>=40){
                           statusLabel.setText("CURRENT SCORE: " + (CurrentScore+1)+" (Good)");
                       }

                    }
            }

        });

        start();
        //Start message to start game
        JOptionPane.showMessageDialog(frame, "Start Game", "Block Burster", JOptionPane.PLAIN_MESSAGE);

        //Setting time Limit of the Game to 1 minutes
        long GameTime = System.currentTimeMillis();
        long GameStop = GameTime + 60 * 1000;
        {
            while (running && System.currentTimeMillis() < GameStop) {
                game.update();
                game.repaint();
                Thread.sleep(10);
            }
            //Score Message after Game ends
            if (Score > 40) {
                JOptionPane.showMessageDialog(frame, "Winner Winner Chicken Dinner! \n" + Score + " POINTS", "Block Burster", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "YOU LOSE " + Score + " POINTS", "Block Burster", JOptionPane.PLAIN_MESSAGE);
            }
            System.exit(0);
        }

    }

}






