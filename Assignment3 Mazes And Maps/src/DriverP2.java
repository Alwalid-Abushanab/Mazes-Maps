import java.io.FileNotFoundException;

public class DriverP2 {

    public static void main(String[] args) throws FileNotFoundException {
        MazeMap maze = new BidirectionalMap("src/maze.txt");

        maze.mazeReport();
    }
}
