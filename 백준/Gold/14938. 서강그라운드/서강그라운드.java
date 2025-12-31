import java.io.*;
import java.util.*;

public class Main {
    private static class Node {
        int end;
        int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }

    private static int answer;
    private static int n, m, r;
    private static int[] arr;
    private static List<Node>[] adj;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    private static void sol() {
        for (int i = 1; i <= n; i++) {
            dijkstra(i);
        }
    }

    private static void dijkstra(int startNode) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));

        pq.offer(new Node(startNode, 0));

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[startNode] = 0;

        boolean[] visited = new boolean[n + 1];

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (visited[current.end]) {
                continue;
            }

            for (Node next : adj[current.end]) {
                if (!visited[next.end] && dist[next.end] > dist[current.end] + next.weight) {
                    dist[next.end] = dist[current.end] + next.weight;
                    pq.offer(new Node(next.end, dist[next.end]));
                }
            }
        }

        int result = 0;

        for (int i = 1; i <= n; i++) {
            if (dist[i] > m) {
                continue;
            }

            result += arr[i];
        }

        answer = Math.max(answer, result);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());// 지역의 개수
        m = Integer.parseInt(st.nextToken());// 수색범위
        r = Integer.parseInt(st.nextToken());// 길의 개수

        arr = new int[n + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        adj = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            adj[a].add(new Node(b, l));
            adj[b].add(new Node(a, l));
        }
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}