package CHelper.src.algo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class graphWeightMatrix {
    private int[][] edges;
    private int n;

    public graphWeightMatrix(int nodes){
        n = nodes;
        edges = new int[n][n];
    }

    public void addUndirectedEdge(int start, int end, int weight){
        edges[start][end] = weight;
        edges[end][start] = weight;
    }

    public void addDirectedEdge(int start, int end, int weight){
        edges[start][end] = weight;
    }

    private StringBuilder Traversal;
    public String BFS(int startIndex){
        Traversal = new StringBuilder();

        boolean visited[] = new boolean[n];
        LinkedList<Integer> queue = new LinkedList<>();

        queue.add(startIndex);
        visited[startIndex] = true;

        while(!queue.isEmpty()){
            startIndex = queue.poll();
            Traversal.append(startIndex);
            Traversal.append(" ");
            for(int i=0; i<n; i++){
                if(!visited[i] && edges[startIndex][i]>0){
                    visited[i] = true;
                    queue.add(i);
                }
            }

        }
        Traversal.deleteCharAt(Traversal.length()-1);
        return Traversal.toString();
    }

    public String DFS(int startIndex){
        Traversal = new StringBuilder();
        boolean[] visited = new boolean[n];
        DFSUtil(startIndex, visited, Traversal);

        Traversal.deleteCharAt(Traversal.length()-1);
        return Traversal.toString();
    }

    public String DFSAll(int startIndex){
        Traversal = new StringBuilder();
        boolean[] visited = new boolean[n];

        for(int i=0; i<n; i++)
            if(!visited[i])
                DFSUtil(i, visited, Traversal);

        Traversal.deleteCharAt(Traversal.length()-1);
        return Traversal.toString();
    }

    private void DFSUtil(int s, boolean[] visited, StringBuilder Traversal){
        visited[s] = true;
        Traversal.append(s);
        Traversal.append(" ");

        for(int i=0; i<n; i++){
            if(edges[s][i]>0 && !visited[i]){
                DFSUtil(i, visited, Traversal);
            }
        }
    }

    private int count = 0;
    public int noOfCycles(){
        //int cycle = 0;   //can't use
        count = 0;
        boolean[] visited = new boolean[n];
        for(int i=0; i<n; i++)
            if(!visited[i])
                noOfCyclesUtil(i, visited);
        return count;
    }

    private void noOfCyclesUtil(int s, boolean[] visited){
        visited[s] = true;

        for(int i=0; i<n; i++){
            if(edges[s][i]>0 && visited[i]){
                count++;
            }
            if(edges[s][i]>0 && !visited[i]){
                noOfCyclesUtil(i, visited);
            }
        }
    }

    private boolean cycle;
    public boolean hasCycles(){
        //int cycle = 0;   //can't use
        cycle = false;
        boolean[] visited = new boolean[n];
        for(int i=0; i<n; i++)
            if(!visited[i])
                noOfCyclesUtil(i, visited);
        return cycle;
    }

    private void hasCyclesUtil(int s, boolean[] visited){
        visited[s] = true;
        if(!cycle)
            for(int i=0; i<n; i++){
                if(edges[s][i]>0 && visited[i]){
                    cycle = true;
                    break;
                }
                if(edges[s][i]>0 && !visited[i]){
                    hasCyclesUtil(i, visited);
                }
            }
    }

    public String topologicalSorting(){
        Traversal = new StringBuilder();
        boolean[] visited = new boolean[n];

        for(int i=0; i<n; i++)
            if(!visited[i])
                topologicalSortingUtil(visited, i);

        Traversal.deleteCharAt(Traversal.length()-1);
        return Traversal.toString();
    }

    public void topologicalSortingUtil(boolean[] visited, int s){
        visited[s]=true;
        Traversal.append(s);
        Traversal.append(" ");
        for(int i=0; i<n; i++){
            if(edges[s][i]>0 && !visited[i]){
                topologicalSortingUtil(visited, i);
            }
        }
    }

    private boolean result;
    public boolean isConnectedDFS(int u, int w){
        result = false;
        boolean[] visited = new boolean[n];
        visited[u] = true;
        isConnectedDFSUtil(u, w, visited);
        return result;
    }

    private void isConnectedDFSUtil(int u, int w,boolean[] visited){
        if (!result){
            if(u==w)
                result=true;
            else {
                for(int i=0; i<n; i++){
                    if(edges[u][i]>0 && !visited[i]){
                        visited[i] = true;
                        isConnectedDFSUtil(i, w, visited);
                    }
                }
            }
        }
    }

    public Set<Set> connectivityGrouping(){
        Set<Set> set = new HashSet<>();

        boolean visited[] = new boolean[n];
        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            Set<Integer> tmp = new HashSet<>();
            if (!visited[i]) {
                visited[i] = true;

                while (!queue.isEmpty()) {
                    int startIndex = queue.poll();
                    tmp.add(startIndex);
                    for (int j = 0; j < n; j++) {
                        if (!visited[j] && edges[startIndex][j] > 0) {
                            visited[j] = true;
                            queue.add(j);
                        }
                    }

                }
            }
            if(tmp.size()>0)
                set.add(tmp);
        }
        return set;
    }

    public List<Integer> connectivityGroupingCount(){
        List<Integer> list = new LinkedList<>();

        boolean visited[] = new boolean[n];
        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            int count = 0;
            if (!visited[i]) {
                visited[i] = true;

                while (!queue.isEmpty()) {
                    int startIndex = queue.poll();
                    count++;
                    for (int j = 0; j < n; j++) {
                        if (!visited[j] && edges[startIndex][j] > 0) {
                            visited[j] = true;
                            queue.add(j);
                        }
                    }

                }
            }
            if(count>0)
                list.add(count);
        }
        return list;
    }

    private int minDistance(int dist[], Boolean sptSet[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index=-1;

        for (int v = 0; v < n; v++)
            if (!sptSet[v] && dist[v] <= min)
            {
                min = dist[v];
                min_index = v;
            }
        return min_index;
    }

    public int[] shotestPathDijkstra(int src)
    {
        int dist[] = new int[n];
        Boolean sptSet[] = new Boolean[n];

        for (int i = 0; i < n; i++)
        {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        dist[src] = 0;
        for (int count = 0; count < n-1; count++)
        {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;
            for (int v = 0; v < n; v++)
                if (!sptSet[v] && edges[u][v]!=0 && dist[u] != Integer.MAX_VALUE && dist[u]+edges[u][v] < dist[v])
                    dist[v] = dist[u] + edges[u][v];
        }

        return dist;
    }

    private int minKey(int key[], Boolean mstSet[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index=-1;

        for (int v = 0; v < n; v++)
            if (!mstSet[v] && key[v] < min)
            {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }

    private void printMST(int parent[])
    {
        System.out.println("Edge   Weight");
        for (int i = 1; i < n; i++)
            System.out.println(parent[i]+" - "+ i+"    "+ edges[i][parent[i]]);
    }

    public int[][] MSTPrims(int startNode)
    {
        int parent[] = new int[n];
        int key[] = new int [n];
        Boolean mstSet[] = new Boolean[n];
        for (int i = 0; i < n; i++)
        {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }
        key[startNode] = 0;
        parent[startNode] = -1;

        for (int count = 0; count < n-1; count++)
        {
            int u = minKey(key, mstSet);
            mstSet[u] = true;
            for (int v = 0; v < n; v++)
                if (edges[u][v]!=0 && !mstSet[v] && edges[u][v] <  key[v])
                {
                    parent[v]  = u;
                    key[v] = edges[u][v];
                }
        }
        int[][] mst = new int[n-1][3];
        int j=-1;
        for (int i = 0; i < n; i++) {
            if(i==startNode)
                continue;
            mst[++j][0] = parent[i];
            mst[j][1] = i;
            mst[j][2] = edges[i][parent[i]];
        }
        return mst;
    }
}
