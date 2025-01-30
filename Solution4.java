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