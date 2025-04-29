import java.io.*;
import java.util.*;

class Solution {
    
    public final boolean REVERSE_TREE = true;
    public final boolean NORMAL_TREE = false;
    public ArrayList<Integer>[] adj;
    public int maxNode;
    
    
    public int[] solution(int[] nodes, int[][] edges) {
        
        init(nodes, edges);
        
        int answer[] = dfs(nodes);
        
        return answer;
    }
    
    private int[] dfs(int[] nodes){
        
        boolean[] visited = new boolean[maxNode+1];
        
        int nomalTree = 0;
        int reverseTree = 0;
        
        for(int node : nodes){
            
            if(visited[node]) continue;
            
            ArrayDeque<Integer> s = new ArrayDeque<>();
            s.push(node);
            
            boolean isExistReverseRoot = false;
            boolean isExistNomalRoot = false;
            
            int nodeCount = 0;
            int nomalChildCount = 0;
            int reverseChildCount = 0;
            
            while(!s.isEmpty()){
                
                int n = s.pop();
                nodeCount++;
                visited[n] = true;
                
                boolean state = adj[n].size()%2 == n%2;
                /*
                    state == true
                    홀짝 트리의 루트 노드이자 
                    역홀짝 트리의 자식 노드가 될 수 있다.
                    
                    홀짝 트리의 자식 노드가 될 수없고 
                    역홀짝 트리의 루트 노드가 될 수 없다.
                    
                    state == false
                    홀짝 트리의 자식 노드이자 
                    역홀짝 트리의 루트 노드가 될 수 있다.
                    
                    홀짝 트리의 루트 노드가 될 수없고 
                    역홀짝 트리의 자식 노드가 될 수 없다.
                */
                if(state){
                    isExistNomalRoot = true;
                    reverseChildCount++;
                    
                }else{
                    isExistReverseRoot = true;
                    nomalChildCount++;
                }
                
                for(int a : adj[n]){
                    if(visited[a]) continue;
                    s.push(a);
                }

            }//while
            
                            
            if(reverseChildCount == nodeCount-1 && isExistReverseRoot){
                reverseTree++;
            }
            if(nomalChildCount == nodeCount-1 && isExistNomalRoot){
                nomalTree++;
            }
            
        }//for
        
        return new int[]{nomalTree,reverseTree};
    }
    
    
    private void init(int[] nodes, int[][] edges){
        
        Arrays.sort(nodes);
        
        maxNode = nodes[nodes.length-1];
        
        adj = new ArrayList[maxNode+1];
        
        for(int node : nodes){
            
            adj[node] = new ArrayList<>();
            
        }
        
        for(int[] edge : edges){
            
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
            
        }
    }
}