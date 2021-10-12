// ID: 20909000

package game.listener;

/**
 * @author Or Drukman
 * interface HitNotifier: A HitNotifier is an object that can notify massages to listeners.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl an HitListener object.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl as a listener to hit events.
     * @param hl an HitListener object.
     */
    void removeHitListener(HitListener hl);
}
