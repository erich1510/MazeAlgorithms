import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AStarAlgorithm {

    //Constructor of A* algorithm
    public AStarAlgorithm(MazeGraph.ProcessedGraph processedGraph){
        Integer start = processedGraph.getStartNode();
        ArrayList<Integer> closedSet = new ArrayList<Integer>(); //list of nodes that has been evaluated (on the final path)
        ArrayList<Integer> openSet = new ArrayList<Integer>(); //list of nodes that has been discovered
        openSet.add(start);
        Map<Integer, Integer> comeFrom = new HashMap<Integer, Integer>();
        Map<Integer, Integer> gScore = new HashMap<Integer, Integer>();
        gScore.put(start, 0);


    }
}
