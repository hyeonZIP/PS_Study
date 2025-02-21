import java.util.*;
import java.io.*;

public class Main {

    static ArrayList<Integer>[] adj;
    static boolean[] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        adj = new ArrayList[N+1];
        visited = new boolean[N+1];

        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i = 1; i <=M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }

        int answer = dfs();

        bw.write(answer+"");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int dfs(){
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        stack.push(1);
        visited[1] = true;
        int infected = 0;

        while(!stack.isEmpty()){
            int next = stack.pop();

            for(int i : adj[next]){
                if(!visited[i]){
                    stack.push(i);
                    visited[i] = true;
                    infected++;
                }
            }
        }
        return infected;
    }
}
