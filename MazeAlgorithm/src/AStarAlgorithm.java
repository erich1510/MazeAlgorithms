import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


//https://en.wikipedia.org/wiki/A*_search_algorithm
//use processedMaze: startPosition, goalPosition, openSquarePosition

public class AStarAlgorithm {
    MazeGraph.ProcessedMaze processedMaze;
    ArrayList<MazeGraph.Position> closedSet;
    ArrayList<MazeGraph.Position> openSet;
    Map<MazeGraph.Position, MazeGraph.Position> comeFrom;
    Map<MazeGraph.Position, Double> gScore;
    Map<MazeGraph.Position, Double> fScore;

    //Constructor of A* algorithm
    public AStarAlgorithm(MazeGraph.ProcessedMaze processedMaze){
        this.processedMaze = processedMaze;
        MazeGraph.Position start = processedMaze.startNode;
        closedSet = new ArrayList<MazeGraph.Position>(); //list of nodes that has been evaluated (on the final path)
        openSet = new ArrayList<MazeGraph.Position>(); //list of nodes that has been discovered
        openSet.add(start);
        comeFrom = new HashMap<MazeGraph.Position,MazeGraph.Position>();
        gScore = new HashMap<MazeGraph.Position, Double>();
        gScore.put(start, 0.0);
        fScore = new HashMap<MazeGraph.Position, Double>();
        fScore.put(start, heuristicCostEstimate(start));
    }

    //not complete
    private ArrayList findPath(){
        while (!openSet.isEmpty()){
            MazeGraph.Position current = findNextNode();
            if (current == processedMaze.goalNode){
                return contructPath(current);
            }
            openSet.remove(current);
            closedSet.add(current);

        }
        return null;
    }

    //find all neighbors
    private ArrayList findNeighs(MazeGraph.Position current){
        ArrayList<MazeGraph.Position> neighs = new ArrayList<MazeGraph.Position>();
        int x=current.x;
        int y = current.y;
        for (int j = -1; j <= 1; j += 2) {
            int k = 0;
            MazeGraph.Position neigh = new MazeGraph.Position(x+j, y+k);
            if (processedMaze.openSquares.contains(neigh)) {
                neighs.add(neigh);
            }
        }
        for (int k = -1; k <= 1; k += 2) {
            int j = 0;
            MazeGraph.Position neigh = new MazeGraph.Position(x + j, y + k);
            if (processedMaze.openSquares.contains(neigh)) {
                neighs.add(neigh);
            }
        }
        return neighs;
    }

    //contruct the path
    private ArrayList contructPath(MazeGraph.Position current){
        ArrayList<MazeGraph.Position> totalPath = new ArrayList<MazeGraph.Position>();
        totalPath.add(current);
        while (comeFrom.containsKey(current)){
            current = comeFrom.get(current);
            totalPath.add(current);
        }
        return totalPath;
    }

    //find node in openSet that has the smallest fScore
    private MazeGraph.Position findNextNode(){
        MazeGraph.Position current = openSet.get(0);
        for (MazeGraph.Position node: openSet){
            if (fScore.get(node)<fScore.get(current)){
                current = node;
            }
        }
        return current;
    }

    // calculate h(n)
    private double heuristicCostEstimate(MazeGraph.Position current){
        double h = 0;
        int currentX = current.x;
        int currentY = current.y;
        int goalX = processedMaze.goalNode.x;
        int goalY = processedMaze.goalNode.y;
        // 8 direction- diagonal distance
        //h = Math.abs(currentX-goalX)+Math.abs(currentY-goalY);
        // 4 direction-manhatten distance
        h = Math.sqrt(Math.abs(currentX-goalX)*Math.abs(currentX-goalX)+Math.abs(currentY-goalY)*Math.abs(currentY-goalY));
        return h;
    }
}
