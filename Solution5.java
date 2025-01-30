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