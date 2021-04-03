package ru.writeway.lesson7;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(10);
        graph.addEdge(7,5);
        graph.addEdge(0,5);
        graph.addEdge(0,4);
        graph.addEdge(4,9);
        graph.addEdge(4,2);
        graph.addEdge(4,8);
        graph.addEdge(8,3);
        graph.addEdge(8,1);
        graph.addEdge(1,6);

        SearchFirstPath bfp  = new SearchFirstPath(graph, 4, SearchFirstPath.SearchType.Breadth);
        System.out.println(bfp.hasPathTo(6));
        System.out.println(bfp.pathTo(6));
    }
}
