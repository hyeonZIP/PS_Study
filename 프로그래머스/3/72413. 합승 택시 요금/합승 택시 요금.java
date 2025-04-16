import java.io.*;
import java.util.*;

/*
n:      노드 수
s:      시작 노드
a:      방문해야 하는 노드1
b:      방문해야 하는 노드2
fares:  연결 노드 및 가중치
*/

class Solution {
    
    public class Node{
        private int end;
        private int weight;
        private ArrayList<Integer> navi;
        
        public Node(int end, int weight, ArrayList<Integer> navi){
            this.end = end;
            this.weight = weight;
            this.navi = navi;
        }
    }
    
    final int INF = Integer.MAX_VALUE;
    
    ArrayList<ArrayList<Node>> adj = new ArrayList<>();
    int[] dist;
    boolean[] visited;
    int answer = INF;
    int N;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        N = n;
        
        for(int i=0; i<=n; i++){
            adj.add(new ArrayList<>());
        }
        
        for(int[] fare : fares){
            adj.get(fare[0]).add(new Node(fare[1],fare[2],new ArrayList<>()));
            adj.get(fare[1]).add(new Node(fare[0],fare[2],new ArrayList<>()));
        }
        
        int[] distAtS = dijkstra(s);
        int[] distAtA = dijkstra(a);
        int[] distAtB = dijkstra(b);
        
        for(int i=1; i<=n; i++){
            int S = distAtS[i];
            int A = distAtA[i];
            int B = distAtB[i];
            
            if(A != INF && B != INF && S != INF){
                answer = Math.min(answer, A+B+S);
            }
        }
        
        return answer;
    }
    
    public int[] dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o->o.weight));
        
        ArrayList<Integer> navi = new ArrayList<>();
        navi.add(start);
        
        pq.offer(new Node(start,0,navi));

        dist = new int[N+1];
        Arrays.fill(dist,INF);
        dist[start] = 0;
        
        visited = new boolean[N+1];
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            // if(cur.end == goalNode) return cur.weight;
            if(visited[cur.end]) continue;
            
            visited[cur.end] = true;
            
            for(Node next : adj.get(cur.end)){
                if(dist[next.end] > dist[cur.end]+next.weight){
                    dist[next.end] = dist[cur.end]+next.weight;
                    
                    ArrayList<Integer> addedNavi = new ArrayList<>(cur.navi);
                    addedNavi.add(next.end);
                    pq.offer(new Node(next.end,dist[next.end],addedNavi));
                }
            }
        }
        return dist;
    }
}