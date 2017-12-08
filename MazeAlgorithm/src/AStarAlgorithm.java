import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


//https://en.wikipedia.org/wiki/A*_search_algorithm
//use processedMaze: startPosition, goalPosition, openSquarePosition

public class AStarAlgorithm {

    //Constructor of A* algorithm
    public AStarAlgorithm(MazeGraph.ProcessedMaze processedMaze){
        MazeGraph.Position start = processedMaze.startNode;
        ArrayList<MazeGraph.Position> closedSet = new ArrayList<MazeGraph.Position>(); //list of nodes that has been evaluated (on the final path)
        ArrayList<MazeGraph.Position> openSet = new ArrayList<MazeGraph.Position>(); //list of nodes that has been discovered
        openSet.add(start);
        Map<MazeGraph.Position, MazeGraph.Position> comeFrom = new HashMap<MazeGraph.Position,MazeGraph.Position>();
        Map<MazeGraph.Position, Integer> gScore = new HashMap<MazeGraph.Position, Integer>();
        gScore.put(start, 0);
        Map<MazeGraph.Position, Integer> fScore = new HashMap<MazeGraph.Position, Integer>();
        fScore.put(start, 0);
    }

    // calculate h(n)
    private int heuristicCostEstimate(MazeGraph.ProcessedMaze processedMaze, MazeGraph.Position current){
        int h = 0;
        // 8 direction- diagonal distance
        int currentX = current.x;
        int currentY = current.y;
        int goalX = processedMaze.goalNode.x;
        int goalY = processedMaze.goalNode.y;
        // 4 direction-manhatten distance

        return h;
    }
}
