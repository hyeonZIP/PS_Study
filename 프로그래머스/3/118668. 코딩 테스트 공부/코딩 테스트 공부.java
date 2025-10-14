import java.io.*;
import java.util.*;

class Solution {
    
    private class State{
        
        private int alp;
        private int cop;
        private int time;
        
        public State(int alp, int cop, int time){
            
            this.alp = alp;
            this.cop = cop;
            this.time = time;
        }
    }
    
    private int maxAlpReq, maxCopReq;
    
    public int solution(int alp, int cop, int[][] problems) {
        
        init(problems);
        
        int answer = getMinimumTime(alp, cop, problems);
        
        return answer;
    }
    
    private int getMinimumTime(int alp, int cop, int[][] problems){
        
        if(alp >= maxAlpReq && cop >= maxCopReq) return 0;
        
        alp = Math.min(alp, maxAlpReq);
        cop = Math.min(cop, maxCopReq);
        
        return dijkstra(alp, cop, problems);
    }
    
    private int dijkstra(int alp, int cop, int[][] problems){
        
        int[][] dist = new int[maxAlpReq+1][maxCopReq+1];
        
        for(int[] d : dist){
            
            Arrays.fill(d,Integer.MAX_VALUE);
        }
        
        dist[alp][cop] = 0;
        
        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(o->o.time));
        
        pq.offer(new State(alp,cop,0));
        
        while(!pq.isEmpty()){
            
            State s = pq.poll();
            
            if(s.alp >= maxAlpReq && s.cop >= maxCopReq) return s.time;
            
            if(s.time > dist[s.alp][s.cop]) continue;
            
            if(s.alp < maxAlpReq){
                
                int nextAlp = s.alp + 1;
                
                if(s.time + 1 < dist[nextAlp][s.cop]){
                    
                    dist[nextAlp][s.cop] = s.time + 1;
                    pq.offer(new State(nextAlp, s.cop, s.time+1));
                }
            }
            
            if(s.cop < maxCopReq){
                
                int nextCop = s.cop + 1;
                
                if(s.time + 1 < dist[s.alp][nextCop]){
                    
                    dist[s.alp][nextCop] = s.time + 1;
                    pq.offer(new State(s.alp, nextCop, s.time+1));
                }
            }
            
            for(int[] problem : problems){
                
                if(s.alp >= problem[0] && s.cop >= problem[1]){
                    
                    int nextAlp = Math.min(s.alp + problem[2], maxAlpReq);
                    int nextCop = Math.min(s.cop + problem[3], maxCopReq);
                    int nextTime = s.time + problem[4];
                    
                    if(nextTime < dist[nextAlp][nextCop]){
                        
                        dist[nextAlp][nextCop] = nextTime;
                        pq.offer(new State(nextAlp, nextCop, nextTime));
                    }
                }
            }
        }//while
        
        return dist[maxAlpReq][maxCopReq];
    }
    
    private void init(int[][] problems){

        for(int[] problem : problems){
            
            maxAlpReq = Math.max(problem[0], maxAlpReq);
            maxCopReq = Math.max(problem[1], maxCopReq);
        }
    }
}