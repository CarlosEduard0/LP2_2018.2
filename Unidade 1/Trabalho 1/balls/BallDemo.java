import java.awt.*;
import java.util.Random;

/**
 * Class BallDemo - provides a demonstration of the
 * BouncingBall and Canvas classes.
 *
 * @author Michael Kolling and David J. Barnes
 * @version 2010.11.30
 */

public class BallDemo {
    private Canvas myCanvas;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 500;
    private Color[] colors = {Color.black, Color.blue, Color.gray, Color.red, Color.green, Color.cyan, Color.darkGray, Color.magenta, Color.yellow, Color.pink, Color.orange};

    /**
     * Create a BallDemo object.
     * Creates a fresh canvas and makes it visible.
     */
    public BallDemo() {
        myCanvas = new Canvas("Ball Demo", WIDTH, HEIGHT);
        myCanvas.setVisible(true);
    }

    /**
     * Simulate two bouncing balls
     */
    void bounce(int numBall) {
        if(numBall < 1) {
            System.err.println("Método bounce chamado com parâmetro numBall menor do que 1");
            return;
        }
        int ground = 450;   // position of the ground line
        int xStart = 50;    // x-start of the ground line
        int xLimit = myCanvas.getSize().width - 50;   // x-limit of the ground line
        BouncingBall[] balls = new BouncingBall[numBall];

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.setForegroundColor(Color.blue);
        myCanvas.drawLine(xStart, ground, xLimit, ground);

        // crate and show the balls
        Random random = new Random();
        for(int i = 0; i < numBall; i++) {
            balls[i] = new BouncingBall(xStart + random.nextInt(myCanvas.getSize().width - xStart), 20 + random.nextInt((myCanvas.getSize().height / 2) - 20), 16, colors[random.nextInt(colors.length)], ground, myCanvas);
            balls[i].draw();
        }

        // Make them bounce until both have gone beyond the xLimit.
        boolean finished = false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            finished = true;
            for(int i = 0; i < numBall; i++) {
                balls[i].move();
                if(balls[i].getXPosition() < xLimit) {
                    finished = false;
                }
            }
        }

        for(int i = 0; i < numBall; i++) {
            balls[i].erase();
        }
    }

    /**
     * Desenha um retângulo com 20px de distância da borda
     */
    void drawFrame() {
        Rectangle rect = new Rectangle(20, 20, myCanvas.getSize().width - 40, myCanvas.getSize().height - 40);
        myCanvas.draw(rect);
    }
}
