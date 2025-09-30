import java.io.*;
import java.util.*;

class Solution {
    
    public class Node{
        
        private int end, weight;
        
        public Node(int end, int weight){
            
            this.end = end;
            this.weight = weight;
        }
    }
    
    public ArrayList<ArrayList<Node>> adj = new ArrayList<>();
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        
        // 다익스트라
        init(paths, gates, summits, n);
        
        int[] answer = dijk(gates, summits, n);
        
        return answer;
    }
    
    private int[] dijk(int[] gates, int[] summits, int nodeCount){
        
        int[] intensity = new int[nodeCount+1];

        ArrayDeque<Node> q = new ArrayDeque<>();
        
        Arrays.fill(intensity, Integer.MAX_VALUE);
        
        for(int gate : gates){
    
            q.offer(new Node(gate, 0));
            intensity[gate] = 0;
        }
        
        while(!q.isEmpty()){            
            
            Node n = q.poll();
            
            if(n.weight > intensity[n.end]) continue;
            
            for(int i=0; i<adj.get(n.end).size(); i++){
                
                Node nn = adj.get(n.end).get(i);
                
                int dist = Math.max(intensity[n.end], nn.weight);
                
                if(intensity[nn.end] > dist){
                    
                    intensity[nn.end] = dist;
                    q.add(new Node(nn.end, dist));
                }
            }
        }
        
        int minSubmit = Integer.MAX_VALUE;
        int minIntensity = Integer.MAX_VALUE;
        
        Arrays.sort(summits);
        
        for(int summit : summits){
            
            if(intensity[summit] < minIntensity){
                
                minSubmit = summit;
                minIntensity = intensity[summit];
            }
        }
                
        return new int[]{minSubmit,minIntensity};
    }
    
    private void init(int[][] paths, int[] gates, int[] summits, int n){
        
        int len = paths.length;
        
        for(int i=0; i<=n; i++){
            
            adj.add(new ArrayList<Node>());
        }
        
        for(int[] path : paths){
            
            int start = path[0];
            int end = path[1];
            int weight = path[2];
            
            if(isGate(start, gates) || isSummit(end, summits)){
                
                adj.get(start).add(new Node(end, weight));
                
                
            }else if(isGate(end, gates) || isSummit(start, summits)){
                
                adj.get(end).add(new Node(start, weight));
                
            }else{
                
                adj.get(start).add(new Node(end, weight));
                adj.get(end).add(new Node(start, weight));
            }
        }
    }
    
    private boolean isGate(int node, int[] gates){
        
        for(int gate : gates){
            
            if(gate == node) return true;
        }
        
        return false;
    }
    
    private boolean isSummit(int node, int[] summits){
        
        for(int summit : summits){
            
            if(summit == node) return true;
            
        }
        
        return false;
    }
}