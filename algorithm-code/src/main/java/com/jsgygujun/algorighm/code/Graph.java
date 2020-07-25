package com.jsgygujun.algorighm.code;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 图
 */
public class Graph {

    private final int V; // 顶点树
    private int E; // 边数
    private List<Integer>[] adj; // 邻接表

    public Graph(int V) {
        this.V = V;
        adj = new List[V];
        for (int v = 0; v < V; ++v) {
            adj[v] = new ArrayList<Integer>();
        }
    }

    /**
     * 通过文本创建图
     * @param filename
     */
    public Graph(String filename) throws Exception {
        List<String> lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
        if (lines.size() < 1) {
            throw new Exception("Graph file format error, lines < 1!");
        }
        V = Integer.parseInt(lines.get(0));
        for (int i = 1; i < lines.size(); ++i) {
            String[] fields = lines.get(i).split(",", -1);
            if (fields.length != 2) {
                throw new Exception("Graph file format error, fields.length != 2");
            }
            addEdge(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]));
        }
    }

    /**
     * 返回图的顶点数
     * @return
     */
    public int V() {
        return V;
    }

    /**
     * 返回图的边数
     * @return
     */
    public int E() {
        return E;
    }

    /**
     * 添加一条边
     * @param u 起点
     * @param v 终点
     */
    public void addEdge(int u, int v) {
        adj[u].add(v);
        adj[v].add(u);
        ++E;
    }

    /**
     * 返回相邻的所有顶点
     * @param v
     * @return
     */
    public List<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * 返回节点的度
     * @param v
     * @return
     */
    public int degree(int v) {
        return adj[v].size();
    }

    /**
     * 返回图的最大度
     * @return
     */
    public int maxDegree() {
        int max = 0;
        for (int v = 0; v < V; ++v) {
            max = Math.max(max, adj[v].size());
        }
        return max;
    }

    /**
     * 计算图的平均度
     * @return
     */
    public double meanDegree() {
        return 2.0 * E / V;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("顶点数: " + V + ", 边数: " + E + "\n");
        for (int v = 0; v < V; ++v) {
            s.append(v).append(": ");
            for (int w : adj(v)) {
                s.append(w).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    void depthFirstSearch() {

    }

    /**
     * 深度优先遍历递归函数
     * @param s 起点
     * @param visited 访问记录
     * @param nodes 访问节点列表
     */
    void dfs(int s, boolean[] visited, List<Integer> nodes) {
        // 这边不需要判断s是否已经访问过，应为在下边用visited[w]过滤了
        visited[s] = true;
        nodes.add(s);
        for (int w : adj(s)) {
            if (!visited[w]) {
                dfs(w, visited, nodes);
            }
        }
    }
}
