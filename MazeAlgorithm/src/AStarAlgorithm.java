import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


//https://en.wikipedia.org/wiki/A*_search_algorithm
//use processedMaze: startPosition, goalPosition, openSquarePosition

public class AStarAlgorithm {
    static ArrayList<MazeGraph.Position> closedSet;
    static ArrayList<MazeGraph.Position> openSet;
    static Map<MazeGraph.Position, MazeGraph.Position> comeFrom;
    static Map<MazeGraph.Position, Double> gScore;
   static  Map<MazeGraph.Position, Double> fScore;

    //not complete
    public static ArrayList findPath(MazeGraph.ProcessedMaze processedMaze){
        MazeGraph.Position start = processedMaze.startNode;
        closedSet = new ArrayList<MazeGraph.Position>(); //list of nodes that has been evaluated (on the final path)
        openSet = new ArrayList<MazeGraph.Position>(); //list of nodes that has been discovered
        openSet.add(start);
        comeFrom = new HashMap<MazeGraph.Position,MazeGraph.Position>();
        gScore = new HashMap<MazeGraph.Position, Double>();
        gScore.put(start, 0.0);
        fScore = new HashMap<MazeGraph.Position, Double>();
        fScore.put(start, heuristicCostEstimate(processedMaze,start));
        while (!openSet.isEmpty()){
            MazeGraph.Position current = findNextNode();
            if (current == processedMaze.goalNode){
                return contructPath(current);
            }
            openSet.remove(current);
            closedSet.add(current);

            ArrayList<MazeGraph.Position> neighs = findNeighs(processedMaze, current);
            for (MazeGraph.Position neigh: neighs){
                if (closedSet.contains(neigh)){
                    continue;
                }
                if(!openSet.contains(neigh)){
                    openSet.add(neigh);
                }
                double tentativeGScore = gScore.get(current)+1;
                if (!gScore.containsKey(neigh)){
                    comeFrom.put(neigh,current);
                    gScore.put(neigh,tentativeGScore);
                    double neighFScore = gScore.get(neigh)+heuristicCostEstimate(processedMaze, neigh);
                    fScore.put(neigh, neighFScore);
                }
                else
                    if (tentativeGScore<gScore.get(neigh) ){
                    comeFrom.put(neigh,current);
                    gScore.put(neigh,tentativeGScore);
                    double neighFScore = gScore.get(neigh)+heuristicCostEstimate(processedMaze, neigh);
                    fScore.put(neigh, neighFScore);
                }
            }

        }
        return null;
    }

    //find all neighbors
    private static ArrayList findNeighs(MazeGraph.ProcessedMaze processedMaze, MazeGraph.Position current){
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
    private static ArrayList contructPath(MazeGraph.Position current){
        ArrayList<MazeGraph.Position> totalPath = new ArrayList<MazeGraph.Position>();
        totalPath.add(current);
        while (comeFrom.containsKey(current)){
            current = comeFrom.get(current);
            totalPath.add(current);
        }
        return totalPath;
    }

    //find node in openSet that has the smallest fScore
    private static MazeGraph.Position findNextNode(){
        MazeGraph.Position current = openSet.get(0);
        for (MazeGraph.Position node: openSet){
            if (fScore.get(node)<fScore.get(current)){
                current = node;
            }
        }
        return current;
    }

    // calculate h(n)
    private static double heuristicCostEstimate(MazeGraph.ProcessedMaze processedMaze, MazeGraph.Position current){
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

    public static void testMaze(String mazeFile) {
    /*
     * Takes a filename as input. It reads the maze from that file, and
     * prints it. You can print the node-marked version instead by uncommenting
     * the next lines. Next it converts the maze to be a graph, returning the
     * graph object and the node numbers of the start and goal nodes. Once you
     * have define DFS and BFS, uncomment these lines to test and print the
     * result.
     */
        ArrayList<String> unprocessedMaze = MazeGraph.readMaze(mazeFile);         // reads the maze from a file
        MazeGraph.printMaze(unprocessedMaze);
        MazeGraph.ProcessedMaze processedMaze = MazeGraph.collectOpenSquares(unprocessedMaze);    // converts the maze to a graph for you
        ArrayList<String> mazeCopy = MazeGraph.nodeMarkedMaze(unprocessedMaze);
        MazeGraph.printMaze(mazeCopy);
        System.out.println("StartNode=" + processedMaze.startNode + " GoalNode=" + processedMaze.goalNode);  // shows how to access start and goal.
        // To access the graph, you would use processedGraph.graph
        ArrayList<Integer> path2 = findPath(processedMaze);
        MazeGraph.printPath("A*", path2);
    }

    public static void main(String args[]) {
        testMaze("/Users/xuchen/IdeaProjects/HW2/src/maze1.txt");

    }
}
