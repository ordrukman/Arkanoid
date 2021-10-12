// ID: 20909000

package game.objectsGame;

import game.objectsGame.geometry.Point;

/**
 * @author Or Drukman
 * class CollisionInfo: A CollisionInfo object has the collisionPoint and the Collidable object.
 */
public class CollisionInfo {
    // fields:
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Create a new CollisionInfo object.
     * @param collisionPoint - the collisionPoint point of colliding.
     * @param collisionObject - the collisionObject object to collide with.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * @return return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
