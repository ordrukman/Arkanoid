// ID: 20909000

package game.objectsGame;
import biuoop.DrawSurface;
import game.GameLevel;
import game.listener.HitListener;
import game.listener.HitNotifier;
import game.objectsGame.geometry.Point;
import game.objectsGame.geometry.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Or Drukman
 * class Block: A Block has edges points, width and height- Rectangle and color.
 * Moreover a block is Collidable object.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    // fields:
    private Rectangle rectangle;
    private java.awt.Color color;
    private List<HitListener> hitListeners;

    // constructor:
    /**
     * Create a new Block with his rectangle.
     * @param rectangle - the rectangle of the block.
     * @param color - the block color.
     */
    public Block(Rectangle rectangle, java.awt.Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * @return return the "collision shape" of the object.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * @return return the color of the object.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with
     *    a given velocity.
     *    The return is the new velocity expected after the hit (based on
     *    the force the object inflicted on us).
     * @param collisionPoint - the collision Point with the block.
     * @param currentVelocity - the currentVelocity of the hit object with the block.
     * @param hitter - the ball that collide with the block.
     * @return Returns the new Velocity depends on the hit point.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getVelocityDx();
        double dy = currentVelocity.getVelocityDy();
        if (hitVerticalEdge(collisionPoint)) {
            dx = -dx;
        }
        if (hitHorizontalEdge(collisionPoint)) {
            dy = -dy;
        }
        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }

    /**
     * check if the collisionPoint is on VerticalEdge.
     * @param collisionPoint - the collision Point with the block.
     * @return Returns true if the collisionPoint is on VerticalEdge,
     *                 false otherwise.
     */
    public boolean hitVerticalEdge(Point collisionPoint) {
        if (((collisionPoint.getX() == this.rectangle.getUpperLeft().getX())
                && (collisionPoint.getX() == this.rectangle.getLowerLeft().getX()))
                || ((collisionPoint.getX() == this.rectangle.getUpperRight().getX())
                && (collisionPoint.getX() == this.rectangle.getLowerRight().getX()))) {
            return true;
        }
        return false;
    }

    /**
     * check if the collisionPoint is on HorizontalEdge.
     * @param collisionPoint - the collision Point with the block.
     * @return Returns true if the collisionPoint is on HorizontalEdge,
     *                 false otherwise.
     */
    public boolean hitHorizontalEdge(Point collisionPoint) {
        if (((collisionPoint.getY() == this.rectangle.getUpperLeft().getY())
                && (collisionPoint.getY() == this.rectangle.getUpperRight().getY()))
                || ((collisionPoint.getY() == this.rectangle.getLowerLeft().getY())
                && (collisionPoint.getY() == this.rectangle.getLowerRight().getY()))) {
            return true;
        }
        return false;
    }

    /**
     * draw the sprite- Block to the screen.
     * @param d - the cDrawSurface of the screen.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());
        d.setColor(java.awt.Color.black);
        d.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {
    }

    /**
     * adding the block to the list of Sprites and the list of Collidable objects in the gameLevel.
     * @param gameLevel the gameLevel object.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * removing the block from the list of Sprites and the list of Collidable objects in the gameLevel.
     * @param gameLevel the gameLevel object.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * adding the HitListener to the list of hitListeners for this block.
     * @param hl an HitListener object.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * removing the HitListener from the list of hitListeners for this block.
     * @param hl an HitListener object.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * notifying to all the HitListener list that his happened and according to the hit call to the hitEvent function.
     * @param hitter a Ball object.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
