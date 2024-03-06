import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class BidirectionalMap extends MazeMap{

    public BidirectionalMap(String fileName) throws FileNotFoundException
    {
        File map = new File(fileName); //create the file

        Scanner mapReader = new Scanner(map);//a scanner for the file

        //ArrayLists for the color of each key, and the room that has it
        ArrayList<String> keyRooms = new ArrayList<>();
        ArrayList<String> keyColors = new ArrayList<>();

        //ArrayLists containing the names of all the rooms, and all the rooms
        ArrayList<String> roomsNames = new ArrayList<>();
        LinkedList<Room> rooms = new LinkedList<>();

        //read all the instructions, and get the name of the rooms and keys
        while(mapReader.hasNextLine())
        {
            //get a line
            String line = mapReader.nextLine();
            //separate the line into an array of words
            String[] words = line.split(" ");

            if(line.contains(">"))
            {
                if(!roomsNames.contains(words[0]))
                {
                    roomsNames.add(words[0]);
                }
                if(!roomsNames.contains(words[3]))
                {
                    roomsNames.add(words[3]);
                }
            }
            else if(words.length > 1) //the line is not empty
            {
                keyRooms.add(words[0]);
                keyColors.add(words[1]);
            }
        }

        //create the rooms
        for (String roomsName : roomsNames)
        {
            if (keyRooms.contains(roomsName))
            {
                rooms.add(new Room(roomsName, keyColors.get(keyRooms.indexOf(roomsName))));
            }
            else
            {
                rooms.add(new Room(roomsName, ""));
            }
        }

        mapReader = new Scanner(map); //re-start the scanner from the beggining of the file

        //loop until the file is empty, to link the rooms
        while(mapReader.hasNextLine())
        {
            //get a line
            String line = mapReader.nextLine();
            //separate the line into an array of words
            String[] words = line.split(" ");

            if (words.length > 1)//the line is not empty
            {
                if (line.contains(">"))//the line is about connecting two rooms together
                {
                    Room first = rooms.get(roomsNames.indexOf(words[0]));//the name of the first room
                    Room second = rooms.get(roomsNames.indexOf(words[3]));//the name of the second room

                    //get the gate key color
                    if(words[2].equals("<->"))
                        words[2] = null;
                    else
                        words[2] = words[2].substring(1,words[2].length()-1);


                    //Assign the gates the appropriate value
                    String firstGate = words[1];
                    String secondGate;
                    try {
                        secondGate = words[4];
                    }
                    catch (Exception e)
                    {
                        secondGate = "north";//for the Exit and Entrance rooms
                    }

                    //check which two gates were given and assign the given rooms and keys to them.
                    if (firstGate.equals("west") && secondGate.equals("west"))
                    {
                        first.setWest(second);
                        second.setWest(first);

                        first.setWestKey(words[2]);
                        second.setWestKey(words[2]);
                    }
                    else if (firstGate.equals("west") && secondGate.equals("east"))
                    {
                        first.setWest(second);
                        second.setEast(first);

                        first.setWestKey(words[2]);
                        second.setEastKey(words[2]);
                    }
                    else if (firstGate.equals("west") && secondGate.equals("north"))
                    {
                        first.setWest(second);
                        second.setNorth(first);

                        first.setWestKey(words[2]);
                        second.setNorthKey(words[2]);
                    }
                    else if (firstGate.equals("west") && secondGate.equals("south"))
                    {
                        first.setWest(second);
                        second.setSouth(first);

                        first.setWestKey(words[2]);
                        second.setSouthKey(words[2]);
                    }
                    else if (firstGate.equals("east") && secondGate.equals("west"))
                    {
                        first.setEast(second);
                        second.setWest(first);

                        first.setEastKey(words[2]);
                        second.setWestKey(words[2]);
                    }
                    else if (firstGate.equals("east") && secondGate.equals("east"))
                    {
                        first.setEast(second);
                        second.setEast(first);

                        first.setEastKey(words[2]);
                        second.setEastKey(words[2]);
                    }
                    else if (firstGate.equals("east") && secondGate.equals("north"))
                    {
                        first.setEast(second);
                        second.setNorth(first);

                        first.setEastKey(words[2]);
                        second.setNorthKey(words[2]);
                    }
                    else if (firstGate.equals("east") && secondGate.equals("south"))
                    {
                        first.setEast(second);
                        second.setSouth(first);

                        first.setEastKey(words[2]);
                        second.setSouthKey(words[2]);
                    }
                    else if (firstGate.equals("north") && secondGate.equals("west"))
                    {
                        first.setNorth(second);
                        second.setWest(first);

                        first.setNorthKey(words[2]);
                        second.setWestKey(words[2]);
                    }
                    else if (firstGate.equals("north") && secondGate.equals("east"))
                    {
                        first.setNorth(second);
                        second.setEast(first);

                        first.setNorthKey(words[2]);
                        second.setEastKey(words[2]);
                    }
                    else if (firstGate.equals("north") && secondGate.equals("north"))
                    {
                        first.setNorth(second);
                        second.setNorth(first);

                        first.setNorthKey(words[2]);
                        second.setNorthKey(words[2]);
                    }
                    else if (firstGate.equals("north") && secondGate.equals("south"))
                    {
                        first.setNorth(second);
                        second.setSouth(first);

                        first.setNorthKey(words[2]);
                        second.setSouthKey(words[2]);
                    }
                    else if (firstGate.equals("south") && secondGate.equals("west"))
                    {
                    first.setSouth(second);
                    second.setWest(first);

                    first.setSouthKey(words[2]);
                    second.setWestKey(words[2]);
                    }
                    else if (firstGate.equals("south") && secondGate.equals("east"))
                    {
                        first.setSouth(second);
                        second.setEast(first);

                        first.setSouthKey(words[2]);
                        second.setEastKey(words[2]);
                    }
                    else if (firstGate.equals("south") && secondGate.equals("north"))
                    {
                        first.setSouth(second);
                        second.setNorth(first);

                        first.setSouthKey(words[2]);
                        second.setNorthKey(words[2]);
                    }
                    else if (firstGate.equals("south") && secondGate.equals("south"))
                    {
                        first.setSouth(second);
                        second.setSouth(second);

                        first.setSouthKey(words[2]);
                        second.setSouthKey(words[2]);
                    }
                    rooms.set(roomsNames.indexOf(words[0]),first);
                    rooms.set(roomsNames.indexOf(words[0]),second);
                }
            }
        }
        entrance = rooms.get(roomsNames.indexOf("Entrance"));// assign the entrance room to the entrance variable
    }
}