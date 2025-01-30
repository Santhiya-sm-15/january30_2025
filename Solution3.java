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