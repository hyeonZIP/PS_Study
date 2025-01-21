import java.util.*;
class Solution {
    
    static int n;
    static int m;
    static int[] dx = {-1,1,0,0};//상하좌우
    static int[] dy = {0,0,-1,1};//상하좌우
    static int[][] copiedMap;//얕은 복사 maps
    static boolean[][] visited;//방문 체크
    static int[][] count;//걸음수 저장
    static Queue<Pair> q = new LinkedList<>();

    public static class Pair{
        int x;
        int y;

        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static int solution(int[][] maps){

        n = maps.length;
        m = maps[0].length;

        copiedMap = maps;//얕은 복사
        visited = new boolean[n][m];
        count = new int[n][m];
        count[0][0] = 1;
        visited[0][0] = true;

        q.offer(new Pair(0,0));

        bfs();

        if (count[n-1][m-1] == 0){
            return -1;
        }
        return count[n-1][m-1];
    }

    private static void bfs(){
        while(!q.isEmpty()){
            Pair p = q.poll();
            for (int i=0; i<4; i++){
                int x = p.x + dx[i];
                int y = p.y + dy[i];

                if (isRange(x,y) && isBlock(x,y) && isVisit(x,y)){
                    q.offer(new Pair(x,y));
                    visited[x][y] = true;
                    count[x][y] = count[p.x][p.y] + 1;
                }
            }
        }
    }

    private static boolean isRange(int x, int y){
        return x>=0 && y>=0 && x<n && y<m;
    }
    private static boolean isBlock(int x, int y){
        return copiedMap[x][y] != 0;
    }
    private static boolean isVisit(int x, int y){
        return !visited[x][y];
    }
}