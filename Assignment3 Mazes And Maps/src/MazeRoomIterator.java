import java.util.ArrayList;
import java.util.Iterator;

/**
 * a class to create an Iterator
 */
public class MazeRoomIterator implements Iterator<Action> {

    private int index = 0;
    private ArrayList<Action> actions;

    public MazeRoomIterator(Room room)
    {
        actions = new ArrayList<>();
        index = 0;

        //create the Actions
        if(room.getWest() != null)
        {
            actions.add(new Action("west",room.getWestKey(),room.getWest()));
        }

        if(room.getNorth() != null)
        {
            actions.add(new Action("north",room.getNorthKey(),room.getNorth()));
        }

        if(room.getEast() != null)
        {
            actions.add(new Action("east",room.getEastKey(),room.getEast()));
        }

        if(room.getSouth() != null)
        {
            actions.add(new Action("south",room.getSouthKey(),room.getSouth()));
        }

    }

    /**
     * a method that return a boolean indicating whither there is a next Action or not
     * @return true if there is a next Action, and false if not
     */
    @Override
    public boolean hasNext() {
        return index < actions.size();
    }

    /**
     * a method that return the next Action
     * @return the next Action
     */
    @Override
    public Action next() {
        return actions.get(index++);
    }
}