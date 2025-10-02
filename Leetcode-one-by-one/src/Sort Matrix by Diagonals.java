class Solution {
    public int[][] sortMatrix(int[][] grid){
        int n = grid.length;
        
        for(int r = 0; r < n; r++){
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            int i = r, j = 0;
            while(i < n && j < n){
                pq.offer(grid[i][j]);
                i++;
                j++;
            }

            i = r; j = 0;
            while(i < n && j < n){
                grid[i][j] = pq.poll();
                i++;
                j++;
            }
        }
        for(int col = 1; col < n; col++){
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            int i = 0, j = col;
            while(i < n && j < n){
                pq.offer(grid[i][j]);
                i++;
                j++;
            }

            i = 0; j = col;
            while(i < n && j < n){
                grid[i][j] = pq.poll();
                i++;
                j++;
            }
        }
        return grid;
    }
}
