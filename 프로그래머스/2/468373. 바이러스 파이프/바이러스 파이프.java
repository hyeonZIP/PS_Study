import java.io.*;
import java.util.*;

class Solution {
    
    public class Wrap{
        Set<Integer> nodes = new HashSet<>();
        int count;
        
        public Wrap(Set<Integer> nodes, int count){
            this.nodes = nodes;
            this.count = count;
        }
    }
    
    int answer = 1;
    public int solution(int n, int infection, int[][] edges, int k) {
        List<Integer>[][] map1 = new ArrayList[n+1][4];
        List<Integer>[][] map2 = new ArrayList[n+1][4];
        
        for(int i=1; i<=n; i++){
            for(int j=1; j<=3; j++){
                map1[i][j] = new ArrayList<>();
                map2[i][j] = new ArrayList<>();
            }
        }
        
        for(int i=0; i<edges.length; i++){
            int a = edges[i][0];
            int b = edges[i][1];
            int pipeType = edges[i][2];
            
            map1[a][pipeType].add(b);
            map2[b][pipeType].add(a);
        }
        
        Deque<Wrap> q = new ArrayDeque<>();
        Set<Integer> nodes = new HashSet<>();
        nodes.add(infection);
        q.offer(new Wrap(nodes, 0));
        
        boolean[] visited = new boolean[n+1];
        visited[infection] = true;
        
        while(!q.isEmpty()){
            Wrap wrap = q.poll();
            Set<Integer> currents = wrap.nodes;
            int count = wrap.count;
                        
            if(count >= k){
                answer = Math.max(answer, currents.size());
                continue;
            }
            
            for(int pipeType = 1; pipeType<=3; pipeType++){
                Set<Integer> nexts = new HashSet<>();
                nexts.addAll(currents);
                
                ArrayDeque<Integer> qq = new ArrayDeque<>();
                boolean[] v = new boolean[n+1];
                for(int current : currents){
                    qq.offer(current);
                    v[current] = true;
                }
                
                while(!qq.isEmpty()){
                    int c = qq.poll();
                                        
                    for(int nn : map1[c][pipeType]){
                        if(!v[nn]){
                            v[nn] = true;
                            nexts.add(nn);
                            qq.offer(nn);
                        }
                    }
                    for(int nn : map2[c][pipeType]){
                        if(!v[nn]){
                            v[nn] = true;
                            nexts.add(nn);
                            qq.offer(nn);
                        }
                    }
                }
                
                q.offer(new Wrap(nexts, count+1));
            }
        }
        
        return answer;
    }
}