import com.google.common.eventbus.Subscribe;

/**
 * MenuItemEventListener
 * CitySim
 * v0.1
 * 
 * Created by Felix Mo on 05-01-2012
 * 
 * Listens for menu item events and acts accordingly
 * 
 */

public class MenuItemEventListener extends CSEventListener
{

    @Subscribe
    public void listen(MenuItemEvent event) {
        CSLogger.sharedLogger().finer("\"" + event.message() + "\" was selected.");

        if (event.message().equals(MenuItemEvent.RESIDENTIAL)) {
            Map.getInstance().selection().setSelectionMode(true);
            City.getInstance().addObject(new Hint("Drag out the area you wish to zone as residential."), Hint.ORIGIN_X, Hint.ORIGIN_Y);
        }
        else if (event.message().equals(MenuItemEvent.INDUSTRIAL)) {
            Map.getInstance().selection().setSelectionMode(true);
        }
        else if (event.message().equals(MenuItemEvent.COMMERCIAL)) {
            Map.getInstance().selection().setSelectionMode(true);
        }
    }
}