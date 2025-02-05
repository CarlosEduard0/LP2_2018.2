import java.awt.*;
import java.awt.geom.*;

/**
 * Class BouncingBall - a graphical ball that observes the effect of gravity. The ball
 * has the ability to move. Details of movement are determined by the ball itself. It
 * will fall downwards, accelerating with time due to the effect of gravity, and bounce
 * upward again when hitting the ground.
 * <p>
 * This movement can be initiated by repeated calls to the "move" method.
 *
 * @author Bruce Quig
 * @author Michael Kolling (mik)
 * @author David J. Barnes
 * @version 2010.11.30
 */

public class BouncingBall {
    private static final int GRAVITY = 3;  // effect of gravity

    private int ballDegradation = 2;
    private Ellipse2D.Double circle;
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private final int groundPosition;      // y position of ground
    private Canvas canvas;
    private int ySpeed = 1;                // initial downward speed

    /**
     * Constructor for objects of class BouncingBall
     *
     * @param xPos          the horizontal coordinate of the ball
     * @param yPos          the vertical coordinate of the ball
     * @param ballDiameter  the diameter (in pixels) of the ball
     * @param ballColor     the color of the ball
     * @param groundPos     the position of the ground (where the wall will bounce)
     * @param drawingCanvas the canvas to draw this ball on
     */
    public BouncingBall(int xPos, int yPos, int ballDiameter, Color ballColor,
                        int groundPos, Canvas drawingCanvas) {
        xPosition = xPos;
        yPosition = yPos;
        color = ballColor;
        diameter = ballDiameter;
        groundPosition = groundPos;
        canvas = drawingCanvas;
    }

    /**
     * Draw this ball at its current position onto the canvas.
     */
    void draw() {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }

    /**
     * Erase this ball at its current position.
     */
    void erase() {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }

    /**
     * Move this ball according to its position and speed and redraw.
     */
    void move() {
        // remove from canvas at the current position
        erase();

        // compute new position
        ySpeed += GRAVITY;
        yPosition += ySpeed;
        xPosition += 2;

        // check if it has hit the ground
        if (yPosition >= (groundPosition - diameter) && ySpeed > 0) {
            yPosition = (int) (groundPosition - diameter);
            ySpeed = -ySpeed + ballDegradation;
        }

        // draw again at new position
        draw();
    }

    /**
     * Get the horizontal position of this ball
     *
     * @return the horizontal position of this ball
     */
    int getXPosition() {
        return xPosition;
    }

    /**
     * Get the vertical position of this ball
     *
     * @return the vertical position of this ball
     */
    public int getYPosition() {
        return yPosition;
    }
}
