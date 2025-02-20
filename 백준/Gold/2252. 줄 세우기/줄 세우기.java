import java.util.*;
import java.io.*;

/**
 * 인접리스트 방식으로 길찾기 처럼 풀이
 */

public class Main {

    static ArrayList<Integer>[] adj;
    static boolean[] visited;
    static int[] inDegree;
    static int N, M;
    static ArrayDeque<Integer> q = new ArrayDeque<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        inDegree = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {

            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            adj[A].add(B);
            inDegree[B]++;//값이 높을 수록 후순위 배치
        }

        topologySort();

        System.out.println(sb);
    }//main

    private static void topologySort() {
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }



        while(!q.isEmpty()){
            int node = q.poll();
            sb.append(node).append(" ");

            for(int i:adj[node]){
                inDegree[i]--;
                if(inDegree[i] == 0){
                    q.offer(i);
                }
            }
        }
    }
}
