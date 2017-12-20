import java.util.*;

public class BFS {


    public static ArrayList<Integer> BFS(MazeGraph.ProcessedGraph processedGraph) {
    /*
     * Takes in a ProcessedGraph object which contains a graph represented as an adjacency list, the node number for the starting point,
     * and for the goal point. It computes and returns a path from start to goal (if one exists), using
     * the Depth-First Seach algorithm. The search starts from the startNode, and continues only until the
     * goalNode is reached. If there is no path from start to goal then an empty list is returned.
     */
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
                return MazeGraph.ReconstructPath(preds, processedGraph.getStartNode(), processedGraph.getGoalNode());
            }
            else{
                List<Integer> neighs = processedGraph.graph.getNeighbors(currVertex);
                for (Integer n : neighs) {
                    if (!visited.contains(n)) {
                        preds.put(n, currVertex);
                        if (n == processedGraph.getGoalNode()){
                            MazeGraph.ReconstructPath(preds,processedGraph.getStartNode(),processedGraph.getGoalNode());
                        }
                        visited.add(n);
                        frontier.add(n);
                    }
                }
            }
        }
        return new ArrayList<>();
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
        ArrayList<Integer> path2 = BFS(processedGraph);
        MazeGraph.printPath("BFS", path2);
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
