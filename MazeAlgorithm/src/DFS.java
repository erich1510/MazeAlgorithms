import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DFS {


    public static ArrayList<Integer> DFS(MazeGraph.ProcessedGraph processedGraph) {
    /*
     * Takes in a ProcessedGraph object which contains a graph represented as an adjacency list, the node number for the starting point,
     * and for the goal point. It computes and returns a path from start to goal (if one exists), using
     * the Breadth-First Seach algorithm. The search starts from the startNode, and continues only until the
     * goalNode is reached. If there is no path from start to goal then an empty list is returned.
     */
        Integer start = processedGraph.getStartNode();
        HashMap<Integer, Integer> preds = new HashMap<>();
        ArrayList<Integer> visited = new ArrayList<>();
        visited.add(start);
        preds.put(start,null);
        return DFSRecur(processedGraph, preds, start, visited);
    }


    private static ArrayList<Integer> DFSRecur (MazeGraph.ProcessedGraph processedGraph, HashMap<Integer, Integer> preds, Integer
            currVertex, ArrayList<Integer> visited){
        if (currVertex== processedGraph.getGoalNode()){
            return MazeGraph.ReconstructPath(preds, processedGraph.getStartNode(), processedGraph.getGoalNode());
        }
        else{
            List<Integer> neighs = processedGraph.graph.getNeighbors(currVertex);
            for (Integer n : neighs) {
                if (!visited.contains(n)) {
                    visited.add(n);
                    preds.put(n, currVertex);
                    ArrayList<Integer> res = DFSRecur(processedGraph, preds, n, visited);
                    if (res.size() != 0) {
                        return res;
                    }
                }
            }
            return new ArrayList<>();
        }

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
        MazeGraph.ProcessedGraph processedGraph = MazeGraph.mazeToGraph(unprocessedMaze);    // converts the maze to a graph for you
        ArrayList<String> mazeCopy = MazeGraph.nodeMarkedMaze(unprocessedMaze);
        MazeGraph.printMaze(mazeCopy);
        System.out.println("StartNode=" + processedGraph.startNode + " GoalNode=" + processedGraph.goalNode);  // shows how to access start and goal.
        // To access the graph, you would use processedGraph.graph
        ArrayList<Integer> path1 = DFS(processedGraph);
        MazeGraph.printPath("BFS", path1);
//        ArrayList<Integer> path2 = DFS(processedGraph);
//        printPath("DFS", path2);
    }

    public static void main(String args[]) {
        testMaze("/Users/xuchen/IdeaProjects/HW2/src/maze1.txt");
        testMaze("/Users/xuchen/IdeaProjects/HW2/src/maze2.txt");
        testMaze("/Users/xuchen/IdeaProjects/HW2/src/maze3.txt");
        testMaze("/Users/xuchen/IdeaProjects/HW2/src/maze4.txt");
        testMaze("/Users/xuchen/IdeaProjects/HW2/src/maze5.txt");
        testMaze("/Users/xuchen/IdeaProjects/HW2/src/maze6.txt");
        testMaze("/Users/xuchen/IdeaProjects/HW2/src/maze7.txt");
        testMaze("/Users/xuchen/IdeaProjects/HW2/src/maze8.txt");
        testMaze("/Users/xuchen/IdeaProjects/HW2/src/maze9.txt");
        testMaze("/Users/xuchen/IdeaProjects/HW2/src/margaret_atwood.txt");
        testMaze("/Users/xuchen/IdeaProjects/HW2/src/maze10.png");
    }

}
