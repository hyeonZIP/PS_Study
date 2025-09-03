import java.io.*;
import java.util.*;

class Solution {
    
    public HashMap<String, int[]> cmd = new HashMap<>();
    
    public int solution(String dirs) {
        
        init();
        
        return sol(dirs);
    }
    
    private int sol(String dirs){
        
        HashSet<String> answer = new HashSet<>();
        
        int y = 5;
        int x = 5;
        
        String[] dir = dirs.split("");
        
        for(String s : dir){
            
            int[] arr = cmd.get(s);
            
            if(isOutOfRange(y+arr[0], x+arr[1])){
                
                continue;
            }
            
            int previousY = y;
            int previousX = x;
            
            y += arr[0];
            x += arr[1];
            
            answer.add(previousY+""+previousX+""+y+""+x);
            answer.add(y+""+x+""+previousY+""+previousX);
        }
        
        return answer.size()/2;
    }
    
    private boolean isOutOfRange(int y, int x){
        
        return y < 0 || x < 0 || y > 10 || x > 10;
    }
    
    private void init(){
        
        cmd.put("U", new int[]{1,0});
        cmd.put("D", new int[]{-1,0});
        cmd.put("R", new int[]{0,1});
        cmd.put("L", new int[]{0,-1});
    }
}