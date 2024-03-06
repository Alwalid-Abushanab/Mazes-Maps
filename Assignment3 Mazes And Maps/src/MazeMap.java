import java.util.ArrayList;
import java.util.Stack;


/**
 * A Bidirectional map is a map where every gate/portal connects both ways
 * e.g. Room A east <-> Room B south
 * In a one directional map portals are one way only
 * e.g. Room A east -> Room B south
 * Finding a way out in an one directional map is more difficult because you can't backtrack
 **/
public abstract class MazeMap {
    // Dummy entrance and exit nodes
    protected MazeRoom entrance;

    /**
     * Prints a report indicating the rooms and keys that are accessible from the entrance
     **/
    public void mazeReport(){
        ArrayList<String> keys = new ArrayList<>();
        ArrayList<String> visited = new ArrayList<>();

        Stack<MazeRoom> toVisit = new Stack<>();
        toVisit.add(entrance);

        while (!toVisit.empty()) {
            MazeRoom current = toVisit.pop();
            if (!visited.contains(current.name())) {
                visited.add(current.name());
                if (!current.roomKey().equals("")) keys.add(current.roomKey());
                for (Action action: current) toVisit.add(action.room);
            }
        }

        System.out.println("The following rooms are accessible from the entrance:");
        for (String room: visited) System.out.println(room);

        System.out.println("The following keys are accessible from the entrance:");
        for (String key: keys) System.out.println(key);
    }

}
