package CHelper.src.algo;

import java.util.*;

public class graphUnweightList {
    private LinkedList<Integer>[] edges;
    private int n;

    public graphUnweightList(int nodes) {
        n = nodes;
        edges = new LinkedList[n];
        for(int i=0; i<n; i++)
            edges[i] = new LinkedList<>();
    }

    public void addUndirectedEdge(int start, int end) {
        edges[start].add(end);
        edges[end].add(start);
    }

    public void addDirectedEdge(int start, int end){
        edges[start].add(end);
    }

    private StringBuilder Traversal;
    public String BFS(int startIndex) {
        Traversal = new StringBuilder();

        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];

        visited[startIndex] = true;
        queue.add(startIndex);

        while(!queue.isEmpty()) {
            startIndex = queue.poll();
            Traversal.append(startIndex);
            Traversal.append(" ");
            for (Integer elem : edges[startIndex]) {
                if (!visited[elem]) {
                    visited[elem] = true;
                    queue.add(elem);
                }
            }
        }
        Traversal.deleteCharAt(Traversal.length()-1);
        return Traversal.toString();
    }

    private boolean result;
    public boolean isConnectedDFS(int u, int w){
        boolean[] visited = new boolean[n];
        visited[u] = true;
        result = false;
        isConnectedDFSUtil(u, w, visited);
        return result;
    }

    private void isConnectedDFSUtil(int u, int w,boolean[] visited){
        if (!result){
            if(u==w)
                result=true;
            else {
                for(int i=0; i<n; i++){
                    if(edges[u].contains(i) && !visited[i]){
                        visited[i] = true;
                        isConnectedDFSUtil(i, w, visited);
                    }
                }
            }
        }
    }

    public boolean isConnectedBFS(int s, int d)
    {
        boolean visited[] = new boolean[n];
        LinkedList<Integer> queue = new LinkedList<>();
        visited[s]=true;
        queue.add(s);

        Iterator<Integer> i;
        while (!queue.isEmpty())
        {
            s = queue.poll();

            int item;
            i = edges[s].listIterator();
            while (i.hasNext())
            {
                item = i.next();
                if (item==d)
                    return true;

                if (!visited[item])
                {
                    visited[item] = true;
                    queue.add(item);
                }
            }
        }
        return false;
    }

    public Set<Set> connectivityGrouping() {
        Set<Set> set = new HashSet<>();

        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            Set<Integer> tmp = new HashSet<>();
            if (!visited[i]) {
                visited[i] = true;
                queue.add(i);

                while (!queue.isEmpty()) {
                    int startIndex = queue.poll();
                    tmp.add(startIndex);
                    for (Integer elem : edges[startIndex]) {
                        if (!visited[elem]) {
                            queue.add(elem);
                            visited[elem] = true;
                        }
                    }
                }
            }
            if(tmp.size()>0)
                set.add(tmp);
        }
        return set;
    }

    public List<Integer> connectivityGroupingCount() {
        List<Integer> list = new LinkedList<>();

        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            int count = 0;
            if (!visited[i]) {
                visited[i] = true;
                queue.add(i);

                while (!queue.isEmpty()) {
                    int startIndex = queue.poll();
                    count++;
                    for (Integer elem : edges[startIndex]) {
                        if (!visited[elem]) {
                            queue.add(elem);
                            visited[elem] = true;
                        }
                    }
                }
            }
            if(count>0)
                list.add(count);
        }
        return list;
    }
}
