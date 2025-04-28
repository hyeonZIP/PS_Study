import java.io.*;
import java.util.*;

class Solution {
    
    public final int EMPTY = 0;
    public final int OIL = 1;
    public final int[] dy = {0,0,1,-1};
    public final int[] dx = {1,-1,0,0};
    
    public boolean[][] visited;
    public int[][] map;
    public int[] oilWeight;
    public int height;
    public int width;
    
    public int solution(int[][] land) {
        
        init(land);
        
        estimateOilWeight();
        
        Arrays.sort(oilWeight);
        
        int answer = oilWeight[oilWeight.length-1];
        
        return answer;
    }

    /*
        시추가 석유에 도달했을 경우 연결된 모든 석유 방문 체크
    */
    public void dfs(int y, int x){
        
        HashSet<Integer> xPos = new HashSet<>();
        
        xPos.add(x);
        
        int oilW = 1;
        
        visited[y][x] = true;
        
        ArrayDeque<int[]> s = new ArrayDeque<int[]>();
        
        s.push(new int[]{y,x});
        
        while(!s.isEmpty()){
            
            int[] arr = s.pop();
            
            for(int i=0; i<4; i++){
                int py = arr[0] + dy[i];
                int px = arr[1] + dx[i];
                
                if(isOutOfRange(py,px)) continue;
                if(visited[py][px]) continue;
                if(map[py][px] == EMPTY) continue;
                
                visited[py][px] = true;
                
                oilW++;
                
                s.push(new int[]{py,px});
                
                xPos.add(px);
            }
            
        }//while
        
        for(int pos : xPos){
            oilWeight[pos] += oilW;
        }
    }
    
    public boolean isOutOfRange(int y, int x){
        return y < 0 || x < 0 || y >= height || x >= width;
    }
    
    /*
        X좌표 별 oilWeight 측정
    */
    public void estimateOilWeight(){

        for(int y = 0; y<height; y++){
            
            for(int x = 0; x<width; x++){
                
                if(visited[y][x] || map[y][x] == EMPTY) continue;
                
                dfs(y, x);
                
            }
        }
    }
    
    /*
        visited, map, oilWeight 생성
    */
    public void init(int[][] land){
        
        height = land.length;
        width = land[0].length;
        
        visited = new boolean[height][width];
        map = land;
        oilWeight = new int[width];
    }
}