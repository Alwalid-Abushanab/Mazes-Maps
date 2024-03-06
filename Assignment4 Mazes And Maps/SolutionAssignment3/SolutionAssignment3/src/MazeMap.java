import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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

    /**
     * a method to look for a solution for a maze
     * @param fileName the name of the file the solution should be saved into
     * @return a boolean value true indicating a solution was found, and false otherwise
     * @throws Exception if no maze was provided
     */
    public boolean noKeysSolution(String fileName) throws Exception
    {
        //create the file and file printer to write in the file
        File file = new File(fileName);
        PrintWriter myFile = new PrintWriter(file);

        //a boolean to indicate if a solution was reached or not
        boolean done = false;

        if (entrance == null)//empty maze
        {
            throw new Exception("The maze is empty.");
        }
        else
        {
            //create a stack for the rooms and add the entrance room to it, and create an arraylist to keep track of all the rooms that have been visited
            Stack<MazeRoom> rooms = new Stack<>();
            ArrayList<String> visited = new ArrayList<>();
            rooms.add(entrance);

            while (!rooms.empty() && !done)
            {
                //get the room at the first position in the stack
                MazeRoom current = rooms.pop();

                //go through each gate from this room
                for (Action action : current)
                {
                    if (current.name().equals("Entrance"))
                    {
                        //save the first move to the file, and add the next room, and break out of the for loop
                        myFile.println(current.name() + " -> " + action.room.name());
                        rooms.push(action.room);
                        break;
                    }
                    else if(action.room.name().equals("Exit"))
                    {
                        //save the last move to the file and break out of the for loop
                        myFile.println(current.name() + " -"+ action.gate + "> " + action.room.name());
                        done = true;
                        break;
                    }
                    else if(thereIsPath(action.room,visited))//if a solution can be reached from the current room
                    {
                        //save the current room to the file, and add it to the stack, and break out of the for loop
                        myFile.println(current.name() + " -"+ action.gate + "> " + action.room.name());
                        rooms.push(action.room);
                        break;
                    }
                }
                visited.add(current.name());
            }
        }

        // close the file printer
        myFile.close();

        //delete the file if an exit was  not found
        if(!done)
            file.delete();
        return done;//true if a solution was found, false otherwise
    }

    /**
     * a method to go through all possible gates from a provided room to check if the exit can be reached or not
     * @param current the current room the program in
     * @param visited all visited rooms
     * @return a boolean value indicating an exit can be reached or not
     */
    public boolean thereIsPath(MazeRoom current, ArrayList<String> visited)
    {
        Stack<MazeRoom> rooms = new Stack<>();
        rooms.add(current);

        while(!rooms.empty())
        {
            MazeRoom curr = rooms.pop();
            for(Action action: curr)
            {
                if(!visited.contains(action.room.name()))
                {
                    //a solution was found
                    if(action.room.name().equals("Exit"))
                        return true;
                    rooms.push(action.room);
                }
            }
            visited.add(curr.name());
        }
        return false;//a solution was not found from this room
    }
}
