package ru.writeway.lesson7;

import java.util.LinkedList;

public class SearchFirstPath {
    private boolean[] marked;
    private int[] edgeTo;
    private int source;

    public enum SearchType {
        Depth, Breadth
    }

    public SearchFirstPath(Graph g, int source, SearchType type) {
        this.source = source;
        marked = new boolean[g.getVertexCount()];
        edgeTo = new int[g.getVertexCount()];
        if (type == SearchType.Breadth) {
            bfs(g, source);
        } else {
            dfs(g, source);
        }
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (int w : g.getAdjList(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            }
        }
    }

    private void bfs(Graph g, int source) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(source);
        marked[source] = true;
        while (!queue.isEmpty()){
            int vertex = queue.removeFirst();
            for (int w : g.getAdjList(vertex)) {
                if(!marked[w]){
                    marked[w] = true;
                    edgeTo[w] = vertex;
                    queue.addLast(w);
                }
            }
        }
    }

    public boolean hasPathTo(int w) {
        return marked[w];
    }

    public LinkedList<Integer> pathTo(int w) {
        if (!hasPathTo(w)) {
            return null;
        }
        LinkedList<Integer> stack = new LinkedList<>();
        int vertex = w;
        while (vertex != source) {
            stack.push(vertex);
            vertex = edgeTo[vertex];
        }
        return stack;
    }
}
