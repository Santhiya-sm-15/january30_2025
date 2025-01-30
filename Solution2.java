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