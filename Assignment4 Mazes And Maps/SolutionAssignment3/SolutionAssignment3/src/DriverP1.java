import java.io.IOException;

public class DriverP1 {
    public static void main(String[] args) throws Exception {
        BidirectionalMap myMap = new BidirectionalMap("SolutionAssignment3/SolutionAssignment3/maze.txt");
        try
        {
            String fileName = "SavedMap.txt";
            if(myMap.saveToFile(fileName))
            {
                System.out.println("Map Saved to file " + fileName);
            }
        }
        catch (IOException e)
        {
            throw new Exception("File could not be created");
        }
        catch (Exception e)
        {
            throw e;
        }
    }
}
