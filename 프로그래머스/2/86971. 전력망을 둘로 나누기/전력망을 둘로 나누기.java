import java.io.*;
import java.util.*;

class Solution {
    List<Integer>[] adj;
    int N;
    int minDiff = Integer.MAX_VALUE;
    
    public int solution(int n, int[][] wires) {
        adj = new ArrayList[n+1];
        N = n;
        for(int i=1; i<=n; i++){
            adj[i] = new ArrayList<>();
        }
        
        for(int[] wire : wires){
            adj[wire[0]].add(wire[1]);
            adj[wire[1]].add(wire[0]);
        }
        
        dfs(1,0);
        
        int answer = minDiff;
        
        return answer;
    }
    
    public int dfs(int current, int previous){
        int count = 1;
        
        for(int next : adj[current]){
            if(next != previous){
                int childCount = dfs(next, current);
                
                int diff = Math.abs(childCount-(N-childCount));
                minDiff = Math.min(minDiff, diff);
                
                count += childCount;
            }
        }
        
        return count;
    }
}