import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N,M;
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M];
        visited = new boolean[N];
        //N개 중에 M개 고르는 경우의 수

        sol(0,0);
    }

    private static void sol(int start, int depth) {

        if (depth == M){
            for (int i : arr) {
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }

        for (int i=0; i<N; i++){
            if (visited[i]) continue;

            arr[depth] = i+1;
            visited[i] = true;
            sol(start+1,depth+1);
            visited[i] = false;
        }

    }
}
