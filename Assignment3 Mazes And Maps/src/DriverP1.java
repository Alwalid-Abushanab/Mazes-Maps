public class DriverP1 {

    public static void main(String[] args)
    {
        Room start = new Room("A","");
        Room west = new Room("W","");
        Room north = new Room("N","");
        Room east = new Room("E","");
        Room south = new Room("S","");

        start.setWest(west);
        start.setNorth(north);
        start.setEast(east);
        start.setEastKey("RED");
        start.setSouth(south);

        System.out.println("The following rooms can be Accessed from room " + start.name());
        for (Action current : start)
        {
            String key;

            if (current.key == null)
                key = " Without a key";
            else
                key = " With a " + current.key + " key";


            System.out.println("Using the " + current.gate + " we can access room " + current.room.name() + key + ".");

        }
    }
}
