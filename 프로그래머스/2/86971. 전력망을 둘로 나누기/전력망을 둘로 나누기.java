import java.io.*;
import java.util.*;

class Solution {
    
    int[] parent;
    int answer = Integer.MAX_VALUE;
    
    public int solution(int n, int[][] wires) {
        
        for(int i=0; i<wires.length; i++){
            init(n);
            
            for(int j = 0; j<wires.length; j++){
                if(i==j) continue;
                
                int a = wires[j][0];
                int b = wires[j][1];
                
                if(find(a) != find(b)){
                    union(a, b);
                }
            }
            
            HashMap<Integer,Integer> map = new HashMap<>();
            
            for(int j=1; j<=n; j++){
                int a = find(j);
                map.put(a,map.getOrDefault(a,0)+1);
            }
            
            if(map.size() == 2){
                ArrayList<Integer> list = new ArrayList<>(map.values());
                answer = Math.min(answer, Math.abs(list.get(0)-list.get(1)));
            }
        }

        return answer;
    }
    
    public void init(int n){
        parent = new int[n+1];
        
        for(int i=1; i<=n; i++){
            parent[i] = i;
        }
    }
    
    public void union(int a, int b){
        a = find(a);
        b = find(b);
        
        if(a < b){
            parent[b] = a;
        }else{
            parent[a] = b;
        }
    }
    
    public int find(int a){
        if(a == parent[a]) return a;
        else return parent[a] = find(parent[a]);
    }
}