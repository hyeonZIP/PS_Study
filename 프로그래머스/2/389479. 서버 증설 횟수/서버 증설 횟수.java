import java.io.*;
import java.util.*;

class Solution {
    class Server{
        int start;
        int end;
        int value;
        
        public Server(int start, int end, int value){
            this.start = start;
            this.end = end;
            this.value = value;
        }
    }
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        
        Deque<Server> q = new ArrayDeque<>();
        
        int currentServer = 1;
        int time = 0;
        
        for(int player : players){
            if(!q.isEmpty() && q.peek().end == time){
                Server returnedServer = q.poll();
                currentServer -= returnedServer.value;
            }
            
            if(player >= currentServer * m){
                int requiredServer = getRequiredServer(player, currentServer, m);                
                currentServer += requiredServer;
                answer+=requiredServer;
                q.offer(new Server(time, time+k, requiredServer));
            }

            time++;
        }
        
        return answer;
    }
    
    private int getRequiredServer(int player, int currentServer, int m){
        int requiredServer = 0;
        while(player >= (currentServer + requiredServer) * m){
            requiredServer++;
        }
        
        return requiredServer;
    }
}