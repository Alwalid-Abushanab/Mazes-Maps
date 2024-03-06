import java.util.ArrayList;
import java.util.Iterator;

public class Room implements MazeRoom {

    private String name;
    private String key = "";
    private String northLock = "", southLock = "", eastLock = "", westLock = "";
    private MazeRoom northRoom, eastRoom, westRoom, southRoom;

    public Room(String name){
        this.name = name;
    }
    public void setEastRoom(MazeRoom east) {this.eastRoom = east; }
    public void setNorthRoom(MazeRoom north) {
        this.northRoom = north;
    }
    public void setSouthRoom(MazeRoom southRoom) { this.southRoom = southRoom; }
    public void setWestRoom(MazeRoom westRoom) {
        this.westRoom = westRoom;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setNorthLock(String key){
        this.northLock = key;
    }
    public void setSouthLock(String key){
        this.southLock = key;
    }
    public void setEastLock(String key){
        this.eastLock = key;
    }
    public void setWestLock(String key){
        this.westLock = key;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String roomKey() {
        return key;
    }

    public void setAll(MazeRoom room) {
        this.setEastRoom(room);
        this.setWestRoom(room);
        this.setNorthRoom(room);
        this.setSouthRoom(room);
    }

    @Override
    public MazeRoomIterator iterator() {
        return new MazeRoomIterator(this);
    }

    class MazeRoomIterator implements Iterator {
        int position;
        ArrayList<Action> actions = new ArrayList<>();
        // constructor
        MazeRoomIterator(Room room) {
            this.position = 0;
            if (room.westRoom!=null) actions.add(new Action("west", room.westLock, room.westRoom));
            if (room.northRoom!=null) actions.add(new Action("north", room.northLock, room.northRoom));
            if (room.eastRoom!=null) actions.add(new Action("east", room.eastLock, room.eastRoom));
            if (room.southRoom!=null) actions.add(new Action("south", room.southLock, room.southRoom));
        }

        // Checks if the next element exists
        public boolean hasNext() {
            if (position < actions.size()) return true;
            return false;
        }

        // moves the cursor/iterator to next element
        public Action next() {
            position += 1;
            return actions.get(position-1);
        }
    }
}
