import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static public class Node {
        int node;
        int weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    static int[][] map;
    static ArrayList<ArrayList<Node>> adj = new ArrayList<>();
    static int N, Q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            adj.get(p).add(new Node(q, r));
            adj.get(q).add(new Node(p, r));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            int count = 0;

            ArrayDeque<Integer> q = new ArrayDeque<>();
            boolean[] visited = new boolean[N + 1];

            visited[v] = true;
            q.offer(v);

            while (!q.isEmpty()) {
                int cur = q.poll();

                ArrayList<Node> list = adj.get(cur);

                for (int j = 0; j < list.size(); j++) {
                    if (visited[list.get(j).node]) continue;
                    if (list.get(j).weight >= k) {
                        count++;
                        q.offer(list.get(j).node);
                        visited[list.get(j).node] = true;
                    }
                }
            }
            sb.append(count).append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
    }
}
