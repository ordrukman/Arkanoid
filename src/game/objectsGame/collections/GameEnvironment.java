// ID: 20909000

package game.objectsGame.collections;
import game.objectsGame.Collidable;
import game.objectsGame.CollisionInfo;
import game.objectsGame.geometry.Line;
import game.objectsGame.geometry.Point;

import java.util.ArrayList;

/**
 * @author Or Drukman
 * class GameEnvironment: A GameEnvironment has all the collidable objects in the game environment.
 */
public class GameEnvironment {
    // fields:
    private ArrayList<Collidable> allCollidableObjects;

    /**
     * Create a new list of Collidable objects in the game environment.
     */
    public GameEnvironment() {
        this.allCollidableObjects = new ArrayList<Collidable>();
    }

    /**
     * add the given collidable to the environment.
     * @param c the new Collidable object.
     */
    public void addCollidable(Collidable c) {
        this.allCollidableObjects.add(c);
    }

    /**
     * remove the given collidable of the environment.
     * @param c the new Collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.allCollidableObjects.remove(c);
    }

    /**
     * return the list of Collidable objects from the game environment.
     * @return returns an ArrayList<Collidable> of all the Collidable object.
     */
    public ArrayList<Collidable> getAllCollidable() {
        return this.allCollidableObjects;
    }

    /**
     * Assume an object moving from line.start() to line.end().
     *     If this object will not collide with any of the collidables
     *     in this collection, return null. Else, return the information
     *     about the closest collision that is going to occur.
     * @param trajectory the trajectory of an object - ball.
     * @return CollisionInfo object that has the closest collision point and the object to collide with.
     * if there is no collide return null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // check if collide have happened.
        boolean thereIsCollide = false;
        // save the collideObject.
        Collidable collideObject = null;
        Point collisionPoint = trajectory.end();
        Point newCollisionPoint;
        // running through the list of CollidableObjects, check  and save the first collide point.
        for (Collidable object: this.allCollidableObjects) {
            newCollisionPoint = trajectory.closestIntersectionToStartOfLine(object.getCollisionRectangle());
            if (newCollisionPoint != null) {
                // check if this collide is the closest to the start point of the ball trajectory.
                if (newCollisionPoint.distance(trajectory.start()) < collisionPoint.distance(trajectory.start())) {
                    // collide have happened.
                    thereIsCollide = true;
                    // saving the collide point and the object.
                    collisionPoint = newCollisionPoint;
                    collideObject = object;
                }
            }
        }
        if (thereIsCollide) {
            return new CollisionInfo(collisionPoint, collideObject);
        }
        return null;
    }
}
