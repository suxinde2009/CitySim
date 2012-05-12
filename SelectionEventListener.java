import com.google.common.eventbus.Subscribe;
import greenfoot.*;
import java.awt.Point;

/**
 * Write a description of class SelectionEventListener here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SelectionEventListener extends CSEventListener
{

    @Subscribe
    public void listen(SelectionEvent event) {
        CSLogger.sharedLogger().finer("Message received: " + event.message());

        if (event.message().equals(SelectionEvent.TILES_SELECTED_FOR_ZONING)) {

            CSLogger.sharedLogger().info("Pending zone op: " + Zone.pendingOp());
//             System.out.println(event.tiles());

            ResidentialZone.zoneTiles(event.tiles());            

//             Map.getInstance().draw();
        }
    }
}
