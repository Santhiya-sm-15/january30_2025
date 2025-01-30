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