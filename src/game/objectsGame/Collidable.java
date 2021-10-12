// ID: 20909000

package game.objectsGame;

import game.objectsGame.geometry.Point;
import game.objectsGame.geometry.Rectangle;

/**
 * @author Or Drukman
 * interface Collidable: A Collidable is an object that can be collide.
 */
public interface Collidable {
    /**
     * @return return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

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
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
