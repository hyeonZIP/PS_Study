import java.io.*;
import java.util.*;

public class Main {
    private static StringBuilder answer = new StringBuilder();
    private static int memberCount;

    private static List<Integer>[] adj;
    private static int[] chairmans;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    private static void sol() {
        for (int i = 1; i <= memberCount; i++) {
            bfs(i);
        }

        int chairmanPoint = Integer.MAX_VALUE;
        List<Integer> chairman = new ArrayList<>();

        for (int i = 1; i <= memberCount; i++) {
            chairmanPoint = Math.min(chairmanPoint, chairmans[i]);
        }

        for (int i = 1; i <= memberCount; i++) {
            if (chairmans[i] == chairmanPoint) {
                chairman.add(i);
            }
        }

        answer.append(chairmanPoint).append(" ").append(chairman.size()).append("\n");
        for (int i : chairman) {
            answer.append(i).append(" ");
        }
    }

    private static void bfs(int start) {
        boolean[] visited = new boolean[memberCount + 1];
        visited[start] = true;

        Deque<int[]> q = new ArrayDeque<>();

        q.offer(new int[] { start, 0 });

        int maxDepth = Integer.MIN_VALUE;

        while (!q.isEmpty()) {
            int[] arr = q.poll();
            int current = arr[0];
            int depth = arr[1];
            maxDepth = Math.max(maxDepth, depth);

            for (int next : adj[current]) {
                if (visited[next]) {
                    continue;
                }

                visited[next] = true;
                q.offer(new int[] { next, depth + 1 });
            }
        }

        for (int i = 1; i <= memberCount; i++) {
            if (!visited[i]) {
                chairmans[start] = Integer.MAX_VALUE;
                return;
            }
        }

        chairmans[start] = maxDepth;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        memberCount = Integer.parseInt(br.readLine());

        adj = new ArrayList[memberCount + 1];
        chairmans = new int[memberCount + 1];

        for (int i = 1; i <= memberCount; i++) {
            adj[i] = new ArrayList<>();
        }

        StringTokenizer st;

        int member1, member2;

        while (true) {
            st = new StringTokenizer(br.readLine());

            member1 = Integer.parseInt(st.nextToken());
            member2 = Integer.parseInt(st.nextToken());

            if (member1 == -1 && member2 == -1) {
                break;
            }

            adj[member1].add(member2);
            adj[member2].add(member1);
        }
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(answer.toString());
        bw.close();
    }
}