import java.util.ArrayList;
import java.util.Iterator;

public class Room implements MazeRoom{

    //name and key in the current room
    private String name;
    private String key;

    //the rooms for each gate
    private Room west;
    private Room north;
    private Room east;
    private Room south;

    //the keys for each gate
    private String westKey;
    private String northKey;
    private String eastKey;
    private String southKey;

    public Room(String name, String key)
    {
        this.name = name;//the name of the room
        this.key = key;//the key found in the room
    }

    /**
     * a method  to set the South gate key
     * @param southKey the key Color for the gate
     */
    public void setSouthKey(String southKey) {
        this.southKey = southKey;
    }

    /**
     * a method  to set the west gate key
     * @param westKey the key Color for the gate
     */
    public void setWestKey(String westKey) {
        this.westKey = westKey;
    }

    /**
     * a method  to set the North gate key
     * @param northKey the key Color for the gate
     */
    public void setNorthKey(String northKey) {
        this.northKey = northKey;
    }

    /**
     * a method  to set the East gate key
     * @param eastKey the key Color for the gate
     */
    public void setEastKey(String eastKey) {
        this.eastKey = eastKey;
    }

    /**
     * a method to set the east gate room
     * @param east the room to be connected to the east gate
     */
    public void setEast(Room east) {
        this.east = east;
    }

    /**
     * a method to set the north gate room
     * @param north the room to be connected to the north gate
     */
    public void setNorth(Room north) {
        this.north = north;
    }

    /**
     * a method to set the west gate room
     * @param west the room to be connected to the west gate
     */
    public void setWest(Room west) {
        this.west = west;
    }

    /**
     * a method to set the south gate room
     * @param south the room to be connected to the south gate
     */
    public void setSouth(Room south) {
        this.south = south;
    }

    /**
     * a method to get the key for the west gate
     * @return the west gate key
     */
    public String getWestKey() {
        return westKey;
    }

    /**
     * a method to get the key for the north gate
     * @return the north gate key
     */
    public String getNorthKey() {
        return northKey;
    }

    /**
     * a method to get the key for the east gate
     * @return the east gate key
     */
    public String getEastKey() {
        return eastKey;
    }

    /**
     * a method to get the key for the south gate
     * @return the south gate key
     */
    public String getSouthKey() {
        return southKey;
    }

    /**
     * a method to get the north gate room
     * @return the north room
     */
    public Room getNorth() {
        return north;
    }

    /**
     * a method to get the west gate room
     * @return the west room
     */
    public Room getWest() {
        return west;
    }

    /**
     * a method to get the east gate room
     * @return the east room
     */
    public Room getEast() {
        return east;
    }

    /**
     * a method to get the south gate room
     * @return the south room
     */
    public Room getSouth() {
        return south;
    }

    /**
     * a method to get the name of the room
     * @return the rooms name
     */
    @Override
    public String name() { return name; }

    /**
     * a method that return the key found in the room
     * @return the key found in the room
     */
    @Override
    public String roomKey() { return key; }

    /**
     * a method that returns an Iterator of type Action
     * @return an Iterator of type Action
     */
    @Override
    public Iterator<Action> iterator() {

        return new MazeRoomIterator(this);
    }

}
