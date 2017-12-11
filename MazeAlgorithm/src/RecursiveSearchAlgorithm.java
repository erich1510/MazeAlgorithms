import java.util.*;

public class RecursiveSearchAlgorithm {

/*Generate Solving Algorithm*/
public static ArrayList<Integer> RecursiveSearchAlgorithm(MazeGraph.ProcessedGraph processedGraph) {
    Integer start = processedGraph.getStartNode();
    HashMap<Integer, Integer> preds = new HashMap<>();
    ArrayList<Integer> visited = new ArrayList<>();
    Queue<Integer> frontier = new ArrayDeque<>();
    frontier.add(start);
    visited.add(start);
    preds.put(start,null);
    while (frontier.size() != 0){
        Integer currVertex = frontier.poll();
        if (currVertex == processedGraph.getGoalNode()){
        }
        else{
            List<Integer> neighs = processedGraph.graph.getNeighbors(currVertex);
            for (Integer n : neighs) {
                if (!visited.contains(n)) {
                    preds.put(n, currVertex);
                    if (n == processedGraph.getGoalNode()){
                    }
                    visited.add(n);
                    frontier.add(n);
                }
            }
        }
    }
    return new ArrayList<>();
}


}
