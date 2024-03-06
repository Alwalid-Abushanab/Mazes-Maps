import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * An abstract class that provides some simple functionality for Maze Maps
 **/
public class BidirectionalMap extends MazeMap{


    public BidirectionalMap(String filename){
        //creating File instance to the input file
        File input = new File(filename);
        Scanner scanner;

        try {
            //Creating Scanner instance to read File
            scanner = new Scanner(input);
        }catch (IOException e){
            // If something goes wrong (e.g. file does not exist) then return null
            this.entrance = null;
            return;
        }

        Map<String, Room> rooms = new HashMap<>();
        ArrayList<String> file_data = new ArrayList<>();

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            file_data.add(line);
            String[] words = line.split(" ");
            String current_name = words[0];

            if (!rooms.containsKey(current_name)) {
                Room newRoom = new Room(current_name);
                rooms.put(current_name, newRoom);
            }

            if (words.length > 3) {
                String next_name = words[3];
                // Sometimes a room may only appear as the 'accessed room'
                if (!rooms.containsKey(next_name)) {
                    Room newRoom = new Room(next_name);
                    rooms.put(next_name, newRoom);
                }
            }
            // If the line is a key description then add it to the room
            if (words[2].equals("key")) {
                rooms.get(current_name).setKey(words[1]);
            }
        }
        scanner.close();

        //entrance room
        Room entrance = rooms.get("Entrance");
        this.entrance = entrance;

        for (String line: file_data){
            String[] words = line.split(" ");
            String name = words[0];

            Room current_room = rooms.get(name);

            // If it is not a key description then ...
            if (!words[2].equals("key")) {
                // getting next room and door description (key color or empty)
                Room nextRoom = rooms.get(words[3]);
                String doorDescription = words[2].substring(1,words[2].length()-1);
                if (doorDescription.equals("-")) doorDescription = "";

                // setting connections and descriptions (it can be exit or entrance too)
                // One way
                if (words[1].equals("east")) {
                    current_room.setEastRoom(nextRoom);
                    current_room.setEastLock(doorDescription);
                }
                else if (words[1].equals("west")) {
                    current_room.setWestRoom(nextRoom);
                    current_room.setWestLock(doorDescription);
                }
                else if (words[1].equals("north")) {
                    current_room.setNorthRoom(nextRoom);
                    current_room.setNorthLock(doorDescription);
                }
                else if (words[1].equals("south")) {
                    current_room.setSouthRoom(nextRoom);
                    current_room.setSouthLock(doorDescription);
                }

                // Checking for Entrance
                if (words[3].equals("Entrance")) entrance.setAll(current_room);
                // the rest of the rooms
                else if (!words[3].equals("Exit")){
                    if (words[4].equals("east")) {
                        nextRoom.setEastRoom(current_room);
                        nextRoom.setEastLock(doorDescription);
                    }
                    else if (words[4].equals("west")) {
                        nextRoom.setWestRoom(current_room);
                        nextRoom.setWestLock(doorDescription);
                    }
                    else if (words[4].equals("north")) {
                        nextRoom.setNorthRoom(current_room);
                        nextRoom.setNorthLock(doorDescription);
                    }
                    else if (words[4].equals("south")) {
                        nextRoom.setSouthRoom(current_room);
                        nextRoom.setSouthLock(doorDescription);
                    }
                }
            }
        }
    }

    /**
     * Given two connected rooms, current and next, finds which gate in the next
     * room connects back to the current room.
     * @param current_room
     * @param next_room
     * @return String indicating the name of the gate (North, South,...)
     */
    private String findGateInNextRoom(MazeRoom current_room, MazeRoom next_room){
        if (next_room.name().equals("Exit")) return "";
        for (Action action: next_room) {
            if (action.room == current_room) return " " + action.gate;
        }
        return "";
    }

    /**
     * given a file name save a maze into it
     * @param fileName the name of the file the solution should be saved into
     * @return a boolean value indicating if the maze has been saved in a file or not (as long as an exception was not thrown, true will be returned)
     * @throws Exception if no maze was provided
     */
    public boolean saveToFile(String fileName) throws Exception
    {
        //create the file and file printer to write in the file
        File file = new File(fileName);
        PrintWriter myFile = new PrintWriter(file);

        if(entrance == null)//empty maze
        {
            throw new Exception("The maze is empty.");
        }
        else
        {
            //create a stack for the rooms and add the entrance room to it, and create an arraylist to keep track of all the rooms that have been visited
            Stack<MazeRoom> rooms = new Stack<>();
            ArrayList<String> visited = new ArrayList<>();
            rooms.add(entrance);

            while(!rooms.empty())
            {
                //get the room at the first position in the stack
                MazeRoom current = rooms.pop();

                //write the key in the room
                if(!visited.contains(current.name()))
                {
                    if (!current.roomKey().equals("")) {
                        myFile.println(current.name() + " " + current.roomKey() + " key");
                    }
                }

                //go through each gate from this room
                for(Action action: current)
                {
                    //if the program has not entered this room before
                    if(!visited.contains(action.room.name()))
                    {
                        //save the key to pass through the gate if any
                        String key = "<->";

                        if(!action.key.equals(""))
                        {
                            key = "<"+ action.key + ">";
                        }

                        if(current.name().equals("Entrance"))
                        {
                            myFile.println(action.room.name() + "" + findGateInNextRoom(current,action.room) + " " + key + " " + current.name());
                            rooms.add(action.room);
                            break;//break out of the loop (we only want to go through one of the 4 gates of the entrance since all of them lead to the same room)
                        }
                        else
                        {
                            //print how the two rooms are connected to the file
                            myFile.println(current.name() + " " + action.gate + " " + key + " " + action.room.name() + "" + findGateInNextRoom(current,action.room));
                            rooms.add(action.room);//add the room to the stack
                        }
                    }
                }
                visited.add(current.name());//save the current room as visited
            }
        }
        //close the file printer and return true
        myFile.close();
        return true;
    }
}