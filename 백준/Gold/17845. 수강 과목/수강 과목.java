import java.io.*;
import java.util.*;

public class Main {
    public static class Subject{
        private int importance;
        private int time;

        public Subject(int importance, int time){
            this.importance = importance;
            this.time = time;
        }
    }
    public static int answer;
    public static int N,K;
    public static Subject[] arr;
    public static int[][] dp;

    public static void main(String[] args) throws IOException{
        init();

        sol();

        print();
    }

    public static void sol() throws IOException{
        for(int i=1; i<=K; i++){
            for(int j=0; j<=N; j++){
                if (j>=arr[i].time) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-arr[i].time] + arr[i].importance);
                    continue;
                }

                dp[i][j] = dp[i-1][j];
            }
        }
        answer = dp[K][N];
    }

    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());//최대 시간
        K = Integer.parseInt(st.nextToken());//과목 수

        dp = new int[K+1][N+1];
        arr = new Subject[K+1];

        for(int i=1;i<=K; i++){
            st = new StringTokenizer(br.readLine());

            int I = Integer.parseInt(st.nextToken());//중요도
            int T = Integer.parseInt(st.nextToken());//시간

            arr[i] = new Subject(I, T);
        }
    }

    public static void print() throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(String.valueOf(answer));
        bw.close();
    }
}
