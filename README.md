# january30_2025
The problems that I solved today

1.You are given a positive integer n representing the number of nodes in an undirected graph. The nodes are labeled from 1 to n. You are also given a 2D integer array edges, where edges[i] = [ai, bi] indicates that there is a bidirectional edge between nodes ai and bi. Notice that the given graph may be disconnected. Divide the nodes of the graph into m groups (1-indexed) such that: Each node in the graph belongs to exactly one group. For every pair of nodes in the graph that are connected by an edge [ai, bi], if ai belongs to the group with index x, and bi belongs to the group with index y, then |y - x| = 1. Return the maximum number of groups (i.e., maximum m) into which you can divide the nodes. Return -1 if it is impossible to group the nodes with the given conditions.

Code:
class Solution {
    ArrayList<Integer>[] adj;
    public boolean bipartite(int src,int[] color)
    {
        for(int a:adj[src])
        {
            if(color[a]==color[src])
                return false;
            if(color[a]!=-1)
                continue;
            color[a]=1-color[src];
            if(!bipartite(a,color))
                return false;
        }
        return true;
    }
    public int bfs(int src)
    {
        Queue<Integer> q=new LinkedList<>();
        boolean[] visited=new boolean[adj.length];
        q.add(src);
        visited[src]=true;
        int cnt=0;
        while(!q.isEmpty())
        {
            int level=q.size();
            for(int i=0;i<level;i++)
            {
                int x=q.poll();
                for(int a:adj[x])
                {
                    if(!visited[a])
                    {
                        visited[a]=true;
                        q.add(a);
                    }
                }
            }
            cnt++;
        }
        return cnt;
    }
    public int groups(int src,int[] dist,boolean[] visited)
    {
        int max=dist[src];
        visited[src]=true;
        for(int a:adj[src])
        {
            if(!visited[a])
                max=Math.max(max,groups(a,dist,visited));
        }
        return max;
    }
    public int magnificentSets(int n, int[][] edges) {
        adj=new ArrayList[n+1];
        int i;
        for(i=1;i<=n;i++)
            adj[i]=new ArrayList<>();
        for(int[] x:edges)
        {
            adj[x[0]].add(x[1]);
            adj[x[1]].add(x[0]);
        }
        int[] color=new int[n+1];
        Arrays.fill(color,-1);
        for(i=1;i<=n;i++)
        {
            if(color[i]==-1)
            {
                color[i]=0;
                if(!bipartite(i,color))
                    return -1;
            }
        }
        int[] dist=new int[n+1];
        for(i=1;i<=n;i++)
            dist[i]=bfs(i);
        int max=0;
        boolean[] visited=new boolean[n+1];
        for(i=1;i<=n;i++)
        {
            if(!visited[i])
                max+=groups(i,dist,visited);
        }
        return max;
    }
}

2.Given a weighted, undirected and connected graph where you have given adjacency list adj. You have to find the shortest distance of all the vertices from the source vertex src, and return a list of integers denoting the shortest distance between each node and source vertex src. Note: The Graph doesn't contain any negative weight edge.

Code:
class Solution {
    ArrayList<Integer> dijkstra(ArrayList<ArrayList<iPair>> adj, int src) {
        PriorityQueue<iPair> pq=new PriorityQueue<>((a,b)->Integer.compare(a.second,b.second));
        pq.add(new iPair(src,0));
        ArrayList<Integer> dist=new ArrayList<>();
        int n=adj.size(),i;
        for(i=0;i<n;i++)
            dist.add(Integer.MAX_VALUE);
        dist.set(src,0);
        while(!pq.isEmpty())
        {
            iPair p=pq.poll();
            int u=p.first;
            int dis=p.second;
            for(iPair x:adj.get(u))
            {
                int v=x.first;
                int d=x.second;
                if(dist.get(u)+d<dist.get(v))
                {
                    dist.set(v,dist.get(u)+d);
                    pq.add(new iPair(v,dist.get(v)));
                }
            }
        }
        return dist;
    }
}

3.Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1. A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that: All the visited cells of the path are 0. All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner). The length of a clear path is the number of visited cells of this path.

Code:
class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid[0][0]==1)
            return -1;
        Queue<int[]> q=new LinkedList<>();
        q.add(new int[]{0,0});
        int n=grid.length,m=grid[0].length;
        int[][] dist=new int[n][m];
        for(int[] d:dist)
            Arrays.fill(d,Integer.MAX_VALUE);
        dist[0][0]=1;
        int[][] dir={{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};
        while(!q.isEmpty())
        {
            int[] x=q.poll();
            int r=x[0];
            int c=x[1];
            for(int[] d:dir)
            {
                int nr=r+d[0];
                int nc=c+d[1];
                if(nr>=0 && nr<n && nc>=0 && nc<m && grid[nr][nc]==0)
                {
                    if(dist[r][c]+1<dist[nr][nc])
                    {
                        dist[nr][nc]=dist[r][c]+1;
                        q.add(new int[]{nr,nc});
                    }
                }
            }
        }
        return dist[n-1][m-1]==Integer.MAX_VALUE?-1:dist[n-1][m-1];
    }
}

4.You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort. A route's effort is the maximum absolute difference in heights between two consecutive cells of the route. Return the minimum effort required to travel from the top-left cell to the bottom-right cell.

Code:
class Solution {
    public int minimumEffortPath(int[][] heights) {
        int n=heights.length,m=heights[0].length;
        int[][] dist=new int[n][m];
        for(int[] d:dist)
            Arrays.fill(d,Integer.MAX_VALUE);
        dist[0][0]=0;
        int[][] dir={{0,-1},{-1,0},{0,1},{1,0}};
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->Integer.compare(a[0],b[0]));
        pq.add(new int[]{0,0,0});
        while(!pq.isEmpty())
        {
            int[] x=pq.poll();
            int r=x[1];
            int c=x[2];
            int dis=x[0];
            if(r==n-1 && c==m-1)
                return dis;
            for(int[] d:dir)
            {
                int nr=r+d[0];
                int nc=c+d[1];
                if(nr>=0 && nr<n && nc>=0 && nc<m)
                {
                    int max=Math.max(Math.abs(heights[r][c]-heights[nr][nc]),dis);
                    if(max<dist[nr][nc])
                    {
                        dist[nr][nc]=max;
                        pq.add(new int[]{max,nr,nc});
                    }
                }
            }
        }
        return 0;
    }
}

5.There are n cities and m edges connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from the city fromi to city toi with cost pricei. You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1. Note: The price from city A to B may be different From the price from city B to A.

Code:
class Solution {
    ArrayList<int[]>[] adj;
    public int CheapestFLight(int n,int flights[][],int src,int dst,int k) {
        adj=new ArrayList[n];
        int i;
        for(i=0;i<n;i++)
            adj[i]=new ArrayList<>();
        for(int[] x:flights)
            adj[x[0]].add(new int[]{x[1],x[2]});
        int[] dist=new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[src]=0;
        Queue<int[]> q=new LinkedList<>();
        q.add(new int[]{0,src,0});
        while(!q.isEmpty())
        {
            int[] x=q.poll();
            int steps=x[0];
            int v=x[1];
            int dis=x[2];
            if(steps>k)
                continue;
            for(int[] a:adj[v])
            {
                int p=a[0];
                int d=a[1];
                if(dis+d<dist[p])
                {
                    dist[p]=dis+d;
                    q.add(new int[]{steps+1,p,dist[p]});
                }
            }
        }
        return dist[dst]==Integer.MAX_VALUE?-1:dist[dst];
    }
}
