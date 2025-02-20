import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static class Node{
        int start;
        int end;
        int weight;

        public Node(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
    static int[] arr;
    static int N,M;
    static int answer;
    static PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o->o.weight));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        arr = new int[N+1];

        for(int i=1; i<=N; i++){
            arr[i] = i;
        }

        M = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int i=1; i<=M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.offer(new Node(a,b,c));
        }

        while(!pq.isEmpty()){
            Node node = pq.poll();

            int start = node.start;
            int end = node.end;
            int weight = node.weight;

            if(find(start) != find(end)){
                union(start,end);
                answer += weight;
            }
        }

        bw.write(answer +"");
        bw.flush();
        bw.close();
        br.close();

    }

    private static void union(int a ,int b){
        int node1 = find(a);
        int node2 = find(b);

        if(node1 != node2) arr[node1] = node2;
    }

    private static int find(int a){
        if (a == arr[a]) return a;
        else{
            arr[a] = find(arr[a]);
            return arr[a];
        }
    }
}
