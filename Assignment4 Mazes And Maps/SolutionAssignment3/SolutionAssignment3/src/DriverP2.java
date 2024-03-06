import java.io.IOException;

public class DriverP2 {
    public static void main(String[] args) throws Exception {
        BidirectionalMap myMap = new BidirectionalMap("SolutionAssignment3/SolutionAssignment3/maze.txt");
        try
        {
            String fileName = "MazeSolution.txt";
            if(myMap.noKeysSolution(fileName))
            {
                System.out.println("Solution saved to File " + fileName);
            }
            else
            {
                System.out.println("An exit was not found");
            }
        }
        catch (IOException e)
        {
            throw new Exception("File Could not be created");
        }
        catch (Exception e)
        {
            throw e;
        }
    }
}
