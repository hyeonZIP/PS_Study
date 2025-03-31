import java.io.*;
import java.util.*;

public class Main{

    static public class Node{
        int end;
        long weight;

        public Node(int end, long weight){
            this.end = end;
            this.weight = weight;
        }
    }

    static int N,M;
    static ArrayList<ArrayList<Node>> adj = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        init();

        System.out.println(dijkstra());
    }

    private static long dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(o->o.weight));

        pq.offer(new Node(1,0));

        boolean[] visited = new boolean[N+1];
        long[] dist = new long[N+1];
        Arrays.fill(dist,Long.MAX_VALUE);
        dist[1]=0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if (visited[cur.end]) continue;
            visited[cur.end] = true;

            for (Node next : adj.get(cur.end)) {
                if (dist[next.end] > dist[cur.end]+next.weight){
                    dist[next.end] = dist[cur.end]+next.weight;
                    pq.offer(new Node(next.end, dist[next.end]));
                }
            }
        }
        
        return dist[N];
    }

    private static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i=0; i<=N; i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adj.get(a).add(new Node(b,c));
            adj.get(b).add(new Node(a,c));
        }
    }
}