/*
*   HashMap에 각 커맨드 별로 좌표 이동 값을 담는다
*   dirs의 입력에 따라 나온 커맨드 별 좌표 이동 값을 Queue에 삽입한다
*   BFS와 비슷한 형태로 Queue가 빌 때까지 반복한다
*   Set을 이용하여 A to B 그리고 B to A 의 형태로 좌표를 String으로 변경하여 저장한다
*   Set의 중복 제거 특성을 이용하여 이미 지나온 길인지 거르고
*   정답을 출력할 땐 나누기 2를 해준다
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