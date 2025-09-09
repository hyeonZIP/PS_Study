import java.io.*;
import java.util.*;

class Solution {

    public boolean[][] map;
    public int answer;
    
    public int solution(int n) {
        
        init(n);
        
        dfs(0,n);    
        
        return answer;
    }
    
    public void dfs(int depth, int n){
        
        if(depth == n){
            
            answer++;
            return;
        }
        

        for(int x=0; x<n; x++){

            if(validateQueenPosition(depth,x,n)){
            
                map[depth][x] = true;
                
                dfs(depth+1, n);
                
                map[depth][x] = false;
            }
        }
        
    }
    
    private boolean validateQueenPosition(int py, int px, int n){
        
        //세로 검증
        for(int y=0; y<n; y++){
            
            if(map[y][px]) return false;
        }
        
        //가로 검증
        for(int x=0; x<n; x++){
            
            if(map[py][x]) return false;
        }
        
        // 대각선 검증
        if(validateCross(py,px,1,1,n)) return false;
        if(validateCross(py,px,-1,-1,n)) return false;
        if(validateCross(py,px,1,-1,n)) return false;
        if(validateCross(py,px,-1,1,n)) return false;
        
        return true;
    }
    
    private boolean validateCross(int y, int x, int yParam, int xParam, int n){
        
        while(y >= 0 && y < n && x >= 0 && x < n){
            
            if(map[y][x]) return true;
                
            y += yParam;
            x += xParam;
        }
        
        return false;
    }

    
    private void init(int n){
        
        map = new boolean[n][n];
    }
}