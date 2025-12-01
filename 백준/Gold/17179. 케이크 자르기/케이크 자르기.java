import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, L;
    private static int[] cakeDiff;
    private static int[] cuttingNeeds;
    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        init();

        sol();

        answer();
    }

    private static void sol() {
        for (int cuttingCount : cuttingNeeds) {
            binarySearch(cuttingCount);
        }
    }

    private static void binarySearch(int cuttingCount) {
        int left = 1;
        int right = L;
        int ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (isPossible(mid, cuttingCount)) {
                left = mid + 1;
                ans = Math.max(ans, mid);
            } else {
                right = mid - 1;
            }
        }
        answer.append(ans).append("\n");
    }

    private static boolean isPossible(int mid, int cutting) {
        int cuttingCount = 0;
        int particle = 0;

        for (int i = 0; i < cakeDiff.length; i++) {
            particle += cakeDiff[i];

            if (particle >= mid) {
                cuttingCount++;
                particle = 0;
            }
        }

        return cuttingCount >= cutting + 1;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        cakeDiff = new int[M + 1];
        cuttingNeeds = new int[N];

        int previous = 0;
        for (int i = 0; i < M; i++) {
            int current = Integer.parseInt(br.readLine());

            cakeDiff[i] = current - previous;

            previous = current;
        }
        cakeDiff[M] = L - previous;

        for (int i = 0; i < N; i++) {
            cuttingNeeds[i] = Integer.parseInt(br.readLine());
        }
    }

    private static void answer() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}