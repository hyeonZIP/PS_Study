import java.io.*;
import java.util.*;

public class Main {
    private static StringBuilder answer = new StringBuilder();
    private static int N, M;
    private static int[] A, B;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    private static void sol() {
        List<Integer> result = new ArrayList<>();

        int aIndex = 0;
        int bIndex = 0;

        while (true) {
            int max = -1;
            int aIdx = -1;
            int bIdx = -1;

            for (int i = aIndex; i < N; i++) {
                for (int j = bIndex; j < M; j++) {
                    if (A[i] == B[j] && A[i] > max) {
                        max = A[i];
                        aIdx = i;
                        bIdx = j;
                    }
                }
            }

            if (max == -1) {
                break;
            }

            result.add(max);
            aIndex = aIdx + 1;
            bIndex = bIdx + 1;
        }

        answer.append(result.size()).append("\n");
        for (int i : result) {
            answer.append(i).append(" ");
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        B = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(answer.toString());
        bw.close();
    }
}