import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static final long INF = Long.MAX_VALUE;

    static public class Node {
        int end;
        long weight;
        int transfer;

        public Node(int end, long weight, int transfer) {
            this.end = end;
            this.weight = weight;
            this.transfer = transfer;
        }
    }

    static ArrayList<ArrayList<Node>> adj = new ArrayList<>();
    static int N, M;
    static int[] trainCompany;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        trainCompany = new int[N];

        for (int i = 0; i < N; i++) {
            trainCompany[i] = Integer.parseInt(br.readLine());
            adj.add(new ArrayList<>());
        }

        for (int start = 0; start < N; start++) {
            st = new StringTokenizer(br.readLine());
            for (int end = 0; end < N; end++) {
                int weight = Integer.parseInt(st.nextToken());
                if (weight != 0) {//0이면 연결되어 있지 않음
                    adj.get(start).add(new Node(end, weight, 0));
                }
            }
        }

        dijkstra();
    }

    private static void dijkstra() {

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.transfer == o2.transfer) {
                return (int) (o1.weight - o2.weight);
            } else return o1.transfer - o2.transfer;
        });
        pq.offer(new Node(0, 0, 0));

        boolean[] visited = new boolean[N];

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.end == M){
                System.out.println(cur.transfer + " " + cur.weight);
                break;
            }

            if (visited[cur.end]) continue;

            visited[cur.end] = true;
            for (Node next : adj.get(cur.end)) {

                if (visited[next.end]) continue;

                pq.offer(new Node(next.end,
                        cur.weight + next.weight,
                        trainCompany[cur.end] == trainCompany[next.end] ? cur.transfer : cur.transfer+1));

//                if (next.end != cur.end) {
//                    if (dist[next.end] > dist[cur.end] + next.weight) {
//                        dist[next.end] = dist[cur.end] + next.weight;
//                        pq.offer(new Node(next.end, dist[next.end], cur.transfer + 1));
//                    }
//                } else {
//                    if (dist[next.end] > dist[cur.end] + next.weight) {
//                        dist[next.end] = dist[cur.end] + next.weight;
//                        pq.offer(new Node(next.end, dist[next.end], cur.transfer));
//                    }
//                }


            }
        }
    }
}
