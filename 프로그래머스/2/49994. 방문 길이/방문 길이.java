/*
*   
*   HashMap으로 각 이동한 좌표를 저장하여 이미 지나간 좌표인지 key-value로 탐색
*/

import java.util.*;

class Solution {
    
    public class Pair{
        int x;
        int y;
        
        public Pair(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
    
    
    
    public int solution(String dirs) {
        
        HashMap<String,Pair> command = new HashMap<>();
        Set<String> firstMove = new HashSet<>();
        Queue<Pair> q = new LinkedList<>();
        
        command.put("U",new Pair(0,1));
        command.put("D",new Pair(0,-1));
        command.put("R",new Pair(1,0));
        command.put("L",new Pair(-1,0));
        
        for(int i=0; i<dirs.length(); i++){
            
            String cmd = String.valueOf(dirs.charAt(i));
            
            q.offer(command.get(cmd));
        }
        int x = 0;
        int y = 0;
        int answer = 0;
        while(!q.isEmpty()){
            Pair p = q.poll();
            
            int px = p.x + x;
            int py = p.y + y;
            
            if(px < -5 || px > 5 || py <-5 || py >5) continue;
            
            String fromTo = String.valueOf(x)+String.valueOf(y)+String.valueOf(px)+String.valueOf(py);
            String toFrom = String.valueOf(px)+String.valueOf(py) + String.valueOf(x)+String.valueOf(y);
            
            firstMove.add(fromTo);
            firstMove.add(toFrom);
            
            x = px;
            y = py;  
            answer++;    
        }
        
        return firstMove.size()/2;
    }
}